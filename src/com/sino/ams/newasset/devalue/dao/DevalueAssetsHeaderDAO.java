package com.sino.ams.newasset.devalue.dao;

import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dao.AmsAssetsReservedDAO;
import com.sino.ams.newasset.dao.AmsAssetsTransLineDAO;
import com.sino.ams.newasset.dao.AmsItemInfoHistoryDAO;
import com.sino.ams.newasset.devalue.model.DevalueAssetsHeaderModel;
import com.sino.ams.newasset.dto.AmsAssetsReservedDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.data.Row;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class DevalueAssetsHeaderDAO extends AMSProcedureBaseDAO {
    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransHeaderDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public DevalueAssetsHeaderDAO(SfUserDTO userAccount, AmsAssetsTransHeaderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsTransHeaderDTO dtoPara = (AmsAssetsTransHeaderDTO) dtoParameter;
        sqlProducer = new DevalueAssetsHeaderModel((SfUserDTO) userAccount, dtoPara);
    }


    /**
     * ���ܣ������ʲ����ݣ������������ϣ����ã���ֵ
     * @param lineSet DTOSet �ʲ�����
     * @return boolean
     */
    public boolean saveOrder(DTOSet lineSet) {
        boolean operateResult = false;
        boolean autoCommit = true;
        AmsAssetsTransHeaderDTO dtoPara = (AmsAssetsTransHeaderDTO) dtoParameter;
        String transType = dtoPara.getTransType();
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            saveOrderHeader();
            saveOrderLines(lineSet);
            operateResult = processProcedure(false);
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DEVALUE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DEVALUE_FAILURE);
                    }
                } else {
                    conn.commit();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DEVALUE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DEVALUE_SUCCESS);
                    }
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("����");
                message.setIsError(!operateResult);
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    public boolean newSaveOrder(DTOSet lineSet) {
        boolean operateResult = false;
        boolean autoCommit = true;
        AmsAssetsTransHeaderDTO dtoPara = (AmsAssetsTransHeaderDTO) dtoParameter;
        String transType = dtoPara.getTransType();
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            saveNewOrderHeader();
            saveOrderLines(lineSet);
            operateResult = processProcedure(false);
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DEVALUE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DEVALUE_FAILURE);
                    }
                } else {
                    conn.commit();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DEVALUE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DEVALUE_SUCCESS);
                    }
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("����");
                message.setIsError(!operateResult);
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    /**
     * ���ܣ��ύ�ʲ����ݣ������������ϣ����ã���ֵ
     * @param lineSet DTOSet �ʲ�����
     * @return boolean
     */
    public boolean submitOrder(DTOSet lineSet) {
        boolean operateResult = false;
        boolean autoCommit = true;
        AmsAssetsTransHeaderDTO dtoPara = (AmsAssetsTransHeaderDTO) dtoParameter;
        String transType = dtoPara.getTransType();
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            saveOrderHeader();
            saveOrderLines(lineSet);
            if (dtoPara.getSf_end().equals("1")) {
                //devalueAssets();//��ʱ�������ʲ�״̬
                deleteReserveAssets();
                logItemChgHistory(lineSet);
            }
            operateResult = processProcedure(true);
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DEVALUE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DEVALUE_FAILURE);
                    }
                } else {
                    conn.commit();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DEVALUE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DEVALUE_SUCCESS);
                    }
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("�ύ");
                message.setIsError(!operateResult);
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    public boolean newSubmitOrder(DTOSet lineSet) {
        boolean operateResult = false;
        boolean autoCommit = true;
        AmsAssetsTransHeaderDTO dtoPara = (AmsAssetsTransHeaderDTO) dtoParameter;
        String transType = dtoPara.getTransType();
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            saveNewOrderHeader();
            saveOrderLines(lineSet);
            operateResult = processProcedure(true);
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DEVALUE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DEVALUE_FAILURE);
                    }
                } else {
                    conn.commit();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DEVALUE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DEVALUE_SUCCESS);
                    }
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("�ύ");
                message.setIsError(!operateResult);
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    public boolean doDelete(DTOSet lineSet) {
        boolean operateResult = false;
        boolean autoCommit = true;
        AmsAssetsTransHeaderDTO dtoPara = (AmsAssetsTransHeaderDTO) dtoParameter;
        String transType = dtoPara.getTransType();
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            deleteLines();
            deleteReserveAssets();
            saveOrderLines(lineSet);
            operateResult = true;
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_FAILURE);
                    } else if (transType.equals(AssetsDictConstant.ASS_DEVALUE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DEVALUE_FAILURE);
                    }
                } else {
                    conn.commit();
                    if (transType.equals(AssetsDictConstant.ASS_RED)) {
                        prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DIS)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DISCARD_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_CLR)) {
                        prodMessage(AssetsMessageKeys.ASSETS_CLEAR_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_FREE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_FREE_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_SUB)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SUB_SUCCESS);
                    } else if (transType.equals(AssetsDictConstant.ASS_DEVALUE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_DEVALUE_SUCCESS);
                    }
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("ɾ��");
                message.setIsError(!operateResult);
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    /**
     * ���ܣ����浥��ͷ��Ϣ
     * @return String
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    private void saveOrderHeader() throws DataHandleException {
        try {
            AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
            String transNo = headerDTO.getTransNo();
            headerDTO.setFromPerson(userAccount.getEmployeeNumber());
            if (transNo.equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
                String transId = headerDTO.getTransId();
                if (StrUtil.isEmpty(transId)) {
                    SeqProducer seq = new SeqProducer(conn);
                    transId = seq.getGUID();
                    headerDTO.setTransId(transId);
                }
                String companyCode = userAccount.getCompanyCode(); //���ǲ��ø÷��������¿������Ӿ���Ϊû��Ҫ

                String transType = headerDTO.getTransType();
                OrderNumGenerator numberProducer = new OrderNumGenerator(conn, companyCode, transType);
                headerDTO.setTransNo(numberProducer.getOrderNum());
                setDTOParameter(headerDTO);
                createData();

            } else {
                if (headerDTO.getSf_end().equals("1")) {
                    ((AmsAssetsTransHeaderDTO) dtoParameter).setTransStatus(AssetsDictConstant.APPROVED);
                }
                updateData();
                deleteLines();
                deleteReserveAssets();
            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
//		} catch (QueryException ex) {
//			Logger.logError(ex);
//			throw new DataHandleException(ex);
        }
    }

    protected void prepareProcedureData(){
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        headerDTO.setPrimaryKey(headerDTO.getTransId());
        headerDTO.setOrderNo(headerDTO.getTransNo());
        headerDTO.setOrderName(AssetsDictConstant.PROCEDURE_NAME_DEVALUE);
    }

    private void saveNewOrderHeader() throws DataHandleException {
        try {
            AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
            String transNo = headerDTO.getTransNo();
            headerDTO.setFromPerson(userAccount.getEmployeeNumber());
            if (transNo.equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
                String transId = headerDTO.getTransId();
                if (StrUtil.isEmpty(transId)) {
                    SeqProducer seq = new SeqProducer(conn);
                    transId = seq.getGUID();
                    headerDTO.setTransId(transId);
                }
                String companyCode = userAccount.getCompanyCode(); //���ǲ��ø÷��������¿������Ӿ���Ϊû��Ҫ
                String transType = headerDTO.getTransType();
                OrderNumGenerator numberProducer = new OrderNumGenerator(conn, companyCode, transType);
                headerDTO.setTransNo(numberProducer.getOrderNum());
                setDTOParameter(headerDTO);
                createData();
            } else {
                updateData();
                deleteLines();
                deleteReserveAssets();
            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        }
    }

    public void rejectOrder() {
        boolean operateResult = rejectProcedure();
        if(operateResult){
            prodMessage(AssetsMessageKeys.REJECT_ORDER_SUCCESS);
        } else {
            prodMessage(AssetsMessageKeys.REJECT_ORDER_FAILURE);
            message.setIsError(true);
        }
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        message.addParameterValue(dto.getTransTypeValue());
        message.addParameterValue(dto.getTransNo());
    }


    /**
     * ���ܣ����浥������Ϣ
     * @param lineSet DTOSet
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    private void saveOrderLines(DTOSet lineSet) throws
            DataHandleException {
        if (lineSet != null && !lineSet.isEmpty()) {
            AmsAssetsTransHeaderDTO orderDTO = (AmsAssetsTransHeaderDTO)dtoParameter;
            AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount, null, conn);
            String transferType = orderDTO.getTransferType();
            for (int i = 0; i < lineSet.getSize(); i++) {
                AmsAssetsTransLineDTO lineData = (AmsAssetsTransLineDTO)lineSet.getDTO(i);
                if (lineData.getBarcode().equals("")) {
                    continue;
                }
                lineData.setTransId(orderDTO.getTransId());
                lineData.setLineStatus(orderDTO.getTransStatus());
                if (lineData.getOldResponsibilityDept().equals("")) {
                    lineData.setOldResponsibilityDept(StrUtil.nullToString(orderDTO.getFromDept()));
                }
                if (transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)) { //�����ڵ���
                    lineData.setResponsibilityDept(StrUtil.nullToString(orderDTO.getFromDept()));
                }
                lineDAO.setDTOParameter(lineData);
                lineDAO.createData();
                createReserveAssets(lineData.getBarcode()); //�����ʲ�
            }
        }
    }

    /**
     * ���ܣ�ɾ�����ݵ�����Ϣ
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    private void deleteLines() throws DataHandleException {
        AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        lineDTO.setTransId(headerDTO.getTransId());
        AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount, lineDTO, conn);
        lineDAO.DeleteByForeignKey("transId");
    }

    /**
     * ���ܣ�ɾ�������ݱ������ʲ�
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    private void deleteReserveAssets() throws DataHandleException {
        AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO)
                dtoParameter;
        reserveDTO.setTransId(headerDTO.getTransId());
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount,
                reserveDTO, conn);
        reserveDAO.DeleteByForeignKey("transId");
    }

    /**
     * ���ļ�ֵ�ʲ�״̬
     * @throws DataHandleException
     */
    private void devalueAssets() throws DataHandleException {
    	DevalueAssetsHeaderModel headerModel = (DevalueAssetsHeaderModel) sqlProducer;
        AmsAssetsTransHeaderDTO batchDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        SQLModel sqlModel = headerModel.getDevalueAssetsModel(batchDTO.getTransId());
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ������ʲ�
     * @param batrcode String
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    private void createReserveAssets(String batrcode) throws
            DataHandleException {
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO)
                dtoParameter;
        AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
        reserveDTO.setTransId(headerDTO.getTransId());
        reserveDTO.setBarcode(batrcode);
        reserveDTO.setCurrCalendar("reservedDate");
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount,
                reserveDTO, conn);
        reserveDAO.createData();
    }


    /**
     * ���ܣ������û�����Ӧ��PID��������
     */
    public boolean isGroupFlowId() throws QueryException {
    	DevalueAssetsHeaderModel modelProducer = (DevalueAssetsHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getGroupPidModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    //�ж��Ƿ�ʵ�ﲿ��
    public boolean isSpecialGroup(int fromGroup) throws QueryException {
    	DevalueAssetsHeaderModel modelProducer = (DevalueAssetsHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsSpecialGroupModel(fromGroup);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    //�ж��Ƿ����
    public boolean isFinanceGroup() throws QueryException {
    	DevalueAssetsHeaderModel modelProducer = (DevalueAssetsHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsFinanceGroupModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    /**
     * Function:       �ж��û���ǰ��������Ƿ�רҵ���
     * date            2009-08-25
     * param           //fromGroupName
     * @return boolean����
     * @throws com.sino.base.exception.QueryException
     *
     */
    public boolean isProfessionalGroup(String fromGroup) throws QueryException {
    	DevalueAssetsHeaderModel modelProducer = (DevalueAssetsHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsGroupFlowIdModel(fromGroup);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    public String findGroupFlowId() throws QueryException {
        String GroupPid = "";
        try {
        	DevalueAssetsHeaderModel modelProducer = (DevalueAssetsHeaderModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getGroupPidModel();
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                Row row = simp.getFirstRow();
                GroupPid = row.getStrValue("P_FLOW_ID");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return GroupPid;
    }

    public boolean findThredDept(String fDept, String tDept) throws QueryException {
        boolean isThredDept = false;
        try {
        	DevalueAssetsHeaderModel modelProducer = (DevalueAssetsHeaderModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getThredDeptModel(fDept, tDept);
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                Row row = simp.getFirstRow();
                String thredDept = row.getStrValue("IS_THRED");
//                if (thredDept.equals("2")) {
//                    isThredDept = true;
//                }
                if (!thredDept.equals("0")) {
                    isThredDept = true;
                } else {
                    isThredDept = false;
                }
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return isThredDept;
    }

    public void logItemChgHistory(DTOSet lineSet) {
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        AmsItemInfoHistoryDAO historyDAO = new AmsItemInfoHistoryDAO(userAccount, null, conn);
        String orderURL = "/servlet/com.sino.ams.newasset.devalue.servlet.DevalueAssetsHeaderServlet";
        orderURL += "?act=DETAIL_ACTION";
        orderURL += "&transId=" + dto.getTransId();
        for (int i = 0; i < lineSet.getSize(); i++) {
            AmsAssetsTransLineDTO lineData = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
            String barcode = lineData.getBarcode();

            AmsItemInfoHistoryDTO historyDTO = new AmsItemInfoHistoryDTO();
            historyDTO.setOrderCategory("3");
            historyDTO.setOrderNo(dto.getTransNo());
            historyDTO.setCreatedBy(userAccount.getUserId());
            historyDTO.setOrderDtlUrl(orderURL);
            historyDTO.setBarcode(barcode);

            historyDAO.setDTOParameter(historyDTO);
            historyDAO.recordHistory();
        }
    }
    
    /**
     * ����
     *
     * @return
     */
    public boolean cancelOrders() {
        boolean operateResult = false;
        boolean autoCommit = true;
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);

            cancelOrderHeader();
            cancelOrderLines();
            deleteReserveAssets();

            operateResult = cancelProcedure();
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                    prodMessage(AssetsMessageKeys.ORDER_CANCEL_SUCCESS);
                } else {
                    conn.rollback();
                    prodMessage(AssetsMessageKeys.ORDER_CANCEL_FAILURE);
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue(headerDTO.getTransTypeValue());
                message.setIsError(!operateResult);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }


    /**
     * ���ܣ���������ͷ
     * @throws DataHandleException
     */
    private void cancelOrderHeader() throws DataHandleException{
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        DevalueAssetsHeaderModel modelProducer = (DevalueAssetsHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getOrderCancelModel(headerDTO.getTransId()); //��������
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ�����������
     * @throws DataHandleException
     */
    private void cancelOrderLines() throws DataHandleException{
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
        lineDTO.setTransId(headerDTO.getTransId());
        AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount, lineDTO, conn);
        lineDAO.cancelLinesByHeader();

    }

}
