package com.sino.ams.newasset.urgenttrans.service;

import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.ams.newasset.urgenttrans.constant.UrgentAppConstant;
import com.sino.ams.newasset.urgenttrans.dto.UrgentDTO;
import com.sino.ams.newasset.urgenttrans.model.UrgentModel;
import com.sino.ams.newasset.urgenttrans.pda.dao.UrgentOrderInUploadDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.framework.dao.PageQueryDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;

public class UrgentService extends UrgentBaseService {

    public UrgentService(SfUserDTO user, UrgentDTO dto, Connection conn) {
        super(user, dto, conn);
    }

    @Override
    public boolean doSave() {
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            headerDTO.setTransStatus(AssetsDictConstant.SAVE_TEMP);
            this.saveHeader();
            operateResult = super.processProcedure(false);
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    this.msg = "����ʧ��";
                    conn.rollback();
                } else {
                    this.msg = "����ɹ������ݺ���" + headerDTO.getTransNo();
                    conn.commit();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
        return operateResult;
    }

    @Override
    public boolean doSubmit() {
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String sfAtt3 = headerDTO.getSf_task_attribute3();
            String flowCode = headerDTO.getFlowCode();

            if (sfAtt3.equals(UrgentAppConstant.ATT3_FILL_DATA)) {
                if (flowCode.equals(UrgentAppConstant.FLOW_CODE_END)) {
                    //�·�
                    headerDTO.setTransStatus(AssetsDictConstant.DISTRIBUTED);
                    this.saveHeader();
                } else {
                    headerDTO.setTransStatus(AssetsDictConstant.IN_PROCESS);
                    this.saveHeader();
                }
            } else if (sfAtt3.equals(UrgentAppConstant.ATT3_ASS_APPROVED)) {
                if (flowCode.equals(UrgentAppConstant.FLOW_CODE_END)) { //�·�
                    doApproved();
                }

            } else if (sfAtt3.equals(UrgentAppConstant.ATT3_SUPER_ASS_APPROVE)) {
                //�·�
                doApproved();
            }
            operateResult = super.processProcedure(true);
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    this.msg = ORDER_TITLE + headerDTO.getTransNo() + "�ύʧ��";
                    conn.rollback();
                } else {
                    this.msg = ORDER_TITLE + headerDTO.getTransNo() + "�ύ�ɹ�";
                    conn.commit();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
        return operateResult;
    }

    public boolean doCancel() {
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            headerDTO.setTransStatus( AssetsDictConstant.CANCELED );
            headerDAO.updateHeaderStatus(headerDTO);
            operateResult = super.cancelProcedure();
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    this.msg = "����"+leaseDTO.getHeaderDTO().getTransNo()+"����ʧ��";
                    conn.rollback();
                } else {
                    this.msg = "����"+leaseDTO.getHeaderDTO().getTransNo()+"�����ɹ�";
                    conn.commit();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
        return operateResult;
    }


    /**
     * ȡ��ϸ����
     * @throws QueryException
     */
    public void prodData() throws QueryException {
        headerDTO = this.setFlowIdToDTO(headerDTO);
        prodHeader();
    }

    /**
     * ȡ��ϸ����(�鵵)
     * @throws QueryException
     */
    public void prodArchiveData() throws QueryException {
        headerDTO = this.setFlowIdToDTO(headerDTO);
        prodHeader();
        prodLines();
    }

    /**
     * �·�
     * @return
     * @throws DataHandleException
     */
    public void doApproved() throws DataHandleException {
        headerDTO.setTransStatus(AssetsDictConstant.DISTRIBUTED);
        headerDAO.updateHeaderStatus(headerDTO);
    }


    /**
     * ��ҳ��ѯ
     * @param req
     * @throws QueryException
     */
    public void pageQuery(HttpServletRequest req) throws QueryException {
        PageQueryDAO pageDao = new PageQueryDAO(req, conn, new UrgentModel((SfUserDTO) userAccount, headerDTO));
        try {
            pageDao.produceWebData();
        } catch (QueryException e) {
            e.printLog();
            throw e;
        }

    }

    /**
     * �ʲ������ʷ
     * @throws DataHandleException
     */
    private void saveItemInfoHistory() throws DataHandleException {
        AmsItemInfoHistoryDTO historyDTO = new AmsItemInfoHistoryDTO();
        historyDTO.setOrderCategory(UrgentAppConstant.ORDER_CATEGORY);
        historyDTO.setOrderNo(headerDTO.getOrderNo());
        historyDTO.setOrderDtlUrl(UrgentAppConstant.ORDER_DTL_URL);
        historyDTO.setRemark(UrgentAppConstant.TRANS_TYPE_NAME);
        super.saveItemInfoHistory(historyDTO);
    }

    /**
     * �鵵
     * @return
     */
    public boolean doArchive() {

        headerDTO.setTransStatus(UrgentAppConstant.STATUS_ARCHIVED);
        UrgentOrderInUploadDAO uploadDAO = new UrgentOrderInUploadDAO(userAccount, conn);
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            headerDTO.setTransStatus(UrgentAppConstant.STATUS_ARCHIVED);
            uploadDAO.updateHeaderStatus(headerDTO);
//			uploadDAO.saveEIIHistoty( headerDTO.getTransId() );
            uploadDAO.updateAssetsAddress(headerDTO.getTransId());
            saveItemInfoHistory();

            uploadDAO.deleteReserved(headerDTO.getTransId());
            operateResult = true;
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } catch (SQLException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    this.msg = "�鵵ʧ��";
                    conn.rollback();
                } else {
                    this.msg = "�鵵�ɹ�";
                    conn.commit();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
        return operateResult;
    }
}
