package com.sino.ams.newasset.free.dao;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.data.Row;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dao.AmsAssetsReservedDAO;
import com.sino.ams.newasset.dao.AmsAssetsTransLineDAO;
import com.sino.ams.newasset.dao.AmsItemInfoHistoryDAO;
import com.sino.ams.newasset.dto.AmsAssetsReservedDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.ams.newasset.free.model.FreeAssetsHeaderModel;
import com.sino.ams.newasset.model.AmsAssetsTransHeaderModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsAssetsTransHeaderDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsTransHeaderDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class FreeAssetsHeaderDAO extends AMSProcedureBaseDAO {

    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransHeaderDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public FreeAssetsHeaderDAO(SfUserDTO userAccount, AmsAssetsTransHeaderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsTransHeaderDTO dtoPara = (AmsAssetsTransHeaderDTO) dtoParameter;
        sqlProducer = new FreeAssetsHeaderModel((SfUserDTO) userAccount, dtoPara);
    }


    /**
     * ���ܣ������ʲ����ݣ������������ϣ�����
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
     * ���ܣ��ύ�ʲ����ݣ������������ϣ�����
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
                freeAssets();
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
                    headerDTO.setTransStatus(AssetsDictConstant.APPROVED);
                }
                updateData();
                deleteLines();
                deleteReserveAssets();
            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        }
    }

    protected void prepareProcedureData(){
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        headerDTO.setPrimaryKey(headerDTO.getTransId());
        headerDTO.setOrderNo(headerDTO.getTransNo());
        headerDTO.setOrderName(AssetsDictConstant.PROCEDURE_NAME_FREE);
    }

    private String saveNewOrderHeader() throws DataHandleException {
        String transId = "";
        try {
            AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
            transId = headerDTO.getTransId();
            String transNo = headerDTO.getTransNo();
            headerDTO.setFromPerson(userAccount.getEmployeeNumber());
            String act = headerDTO.getAct();
            if (transNo.equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
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
        return transId;
    }

    public boolean rejectOrder() {
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
        return operateResult;
    }
    /**
     * ���ܣ����浥������Ϣ
     * @param lineSet DTOSet
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    private void saveOrderLines(DTOSet lineSet) throws DataHandleException {
        if (lineSet != null && !lineSet.isEmpty()) {
            AmsAssetsTransHeaderDTO orderDTO = (AmsAssetsTransHeaderDTO)dtoParameter;
            AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO( userAccount, null, conn);
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
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        reserveDTO.setTransId(headerDTO.getTransId());
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount,reserveDTO, conn);
        reserveDAO.DeleteByForeignKey("transId");
    }

    /**
     * ���ļ�ֵ�ʲ�״̬
     * @throws DataHandleException
     */
    private void freeAssets() throws DataHandleException {
        FreeAssetsHeaderModel headerModel = (FreeAssetsHeaderModel) sqlProducer;
        AmsAssetsTransHeaderDTO batchDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        SQLModel sqlModel = headerModel.getFreeAssetsModel(batchDTO.getTransId());
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ������ʲ�
     * @param batrcode String
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    private void createReserveAssets(String batrcode) throws DataHandleException {
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
        reserveDTO.setTransId(headerDTO.getTransId());
        reserveDTO.setBarcode(batrcode);
        reserveDTO.setCurrCalendar("reservedDate");
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount, reserveDTO, conn);
        reserveDAO.createData();
    }


    /**
     * ���ܣ������ݴ�ĵ���
     *
     * @return boolean
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
            deleteReservedAssets();
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
        FreeAssetsHeaderModel modelProducer = (FreeAssetsHeaderModel) sqlProducer;
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

    /**
     * ���ܣ����������ʲ�
     * @throws DataHandleException
     */
    private void deleteReservedAssets() throws DataHandleException{
        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO) dtoParameter;
        AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
        reserveDTO.setTransId(headerDTO.getTransId()); //ɾ����������
        AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount, reserveDTO, conn);
        reserveDAO.DeleteByForeignKey("transId");
    }

    public File exportFile() throws DataTransException { //����ģ��
        File file = null;
        DataTransfer transfer = null;
        AmsAssetsTransHeaderModel modelProducer = (AmsAssetsTransHeaderModel)
                sqlProducer;
        SQLModel sqlModel = modelProducer.getOrderModel();
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setCalPattern(CalendarConstant.LINE_PATTERN);
        rule.setSourceConn(conn);

        AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO)
                dtoParameter;
        String transferType = headerDTO.getTransferType();
        String fileName = "";
        if (transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)) { //�����ڵ���
            fileName = "�����ڵ���.xls";
        } else if (transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)) { //�����ڵ���
            fileName = "���ż����.xls";
        } else if (transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)) { //���м����
            fileName = "���м����.xls";
        }

        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);
        DataRange range = new DataRange();
        rule.setDataRange(range);

        Map fieldMap = new HashMap();
        fieldMap.put("MB1", "�ʲ���ǩ");
        fieldMap.put("MB2", "�ʲ����");
        fieldMap.put("MB3", "�ʲ�����");
        fieldMap.put("MB4", "�ʲ��ͺ�");
        fieldMap.put("MB5", "����");
        if (transferType.equals(AssetsDictConstant.TRANS_INN_DEPT)) {
            fieldMap.put("MB6", "�����ص�NO");
            fieldMap.put("MB7", "�����ص�");
            fieldMap.put("MB8", "ԭ������Ա��ID");
            fieldMap.put("MB9", "ԭ������");
            fieldMap.put("MB10", "����ص�NO");
            fieldMap.put("MB11", "����ص�");
            fieldMap.put("MB12", "��������Ա��ID");
            fieldMap.put("MB13", "��������");
            fieldMap.put("MB14", "��������");
            fieldMap.put("MB15", "ժҪ");
        } else if (transferType.equals(AssetsDictConstant.TRANS_BTW_DEPT)) {
            fieldMap.put("MB6", "�����ص�NO");
            fieldMap.put("MB7", "�����ص�");
            fieldMap.put("MB8", "ԭ������Ա��ID");
            fieldMap.put("MB9", "ԭ������");
            fieldMap.put("MB10", "���벿�Ŵ���");
            fieldMap.put("MB11", "���벿��");
            fieldMap.put("MB12", "����ص�N0");
            fieldMap.put("MB13", "����ص�");
            fieldMap.put("MB14", "��������Ա��ID");
            fieldMap.put("MB15", "��������");
            fieldMap.put("MB16", "��������");
            fieldMap.put("MB17", "��ע");
        } else if (transferType.equals(AssetsDictConstant.TRANS_BTW_COMP)) {
            fieldMap.put("MB6", "ԭֵ");
            fieldMap.put("MB7", "�ۼ��۾�");
            fieldMap.put("MB8", "��ֵ");
            fieldMap.put("MB9", "��������");
            fieldMap.put("MB10", "�������Ŵ���");
            fieldMap.put("MB11", "��������");
            fieldMap.put("MB12", "�����ص�NO");
            fieldMap.put("MB13", "�����ص�");
            fieldMap.put("MB14", "ԭ������Ա��ID");
            fieldMap.put("MB15", "ԭ������");
            fieldMap.put("MB16", "ԭ�۾��˻�");
            fieldMap.put("MB17", "ԭ���");
            fieldMap.put("MB18", "���벿�Ŵ���");
            fieldMap.put("MB19", "���벿��");
            fieldMap.put("MB20", "����ص�NO");
            fieldMap.put("MB21", "����ص�");
            fieldMap.put("MB22", "��������Ա��ID");
            fieldMap.put("MB23", "��������");
            fieldMap.put("MB24", "���۾��˻�");
            fieldMap.put("MB25", "�����");
            fieldMap.put("MB26", "��������");
            fieldMap.put("MB27", "��ע");
        }

        rule.setFieldMap(fieldMap);
        CustomTransData custData = new CustomTransData();
//            custData.setReportTitle(fileName);
//            custData.setReportPerson(sfUser.getUsername());
        custData.setNeedReportDate(false);
        rule.setCustData(custData);

        TransferFactory factory = new TransferFactory();
        transfer = factory.getTransfer(rule);
        transfer.transData();
        file = (File) transfer.getTransResult();
        return file;
    }

    /**
     * ���ܣ������û�����Ӧ��PID��������
     */
    public boolean isGroupFlowId() throws QueryException {
        FreeAssetsHeaderModel modelProducer = (FreeAssetsHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getGroupPidModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    //�ж��Ƿ�ʵ�ﲿ��
    public boolean isSpecialGroup(int fromGroup) throws QueryException {
        FreeAssetsHeaderModel modelProducer = (FreeAssetsHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsSpecialGroupModel(fromGroup);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }


    //�ж��Ƿ����
    public boolean isFinanceGroup() throws QueryException {
        FreeAssetsHeaderModel modelProducer = (FreeAssetsHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsFinanceGroupModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    /**
     * Function:        �ж��û���ǰ��������Ƿ�רҵ���
     * author:         ����
     * date            2009-08-25
     * param           //fromGroupName
     * @return boolean����
     * @throws com.sino.base.exception.QueryException
     *
     */
    public boolean isProfessionalGroup(String fromGroup) throws QueryException {
        FreeAssetsHeaderModel modelProducer = (FreeAssetsHeaderModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsGroupFlowIdModel(fromGroup);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    public String findGroupFlowId() throws QueryException {
        String GroupPid = "";
        try {
            FreeAssetsHeaderModel modelProducer = (FreeAssetsHeaderModel) sqlProducer;
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
            FreeAssetsHeaderModel modelProducer = (FreeAssetsHeaderModel) sqlProducer;
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
        String orderURL = "/servlet/com.sino.ams.newasset.free.servlet.FreeAssetsHeaderServlet";
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
}
