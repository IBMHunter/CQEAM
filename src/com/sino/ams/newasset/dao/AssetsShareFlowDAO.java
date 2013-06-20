package com.sino.ams.newasset.dao;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.freeflow.FreeFlowDTO;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsReservedDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.model.AssetsShareFlowModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.data.Row;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
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
import com.sino.flow.bean.FlowAction;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.dto.BaseUserDTO;

public class AssetsShareFlowDAO extends AMSBaseDAO {

    public AssetsShareFlowDAO(SfUserDTO userAccount, FreeFlowDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        FreeFlowDTO ffDTO=(FreeFlowDTO) dtoParameter;
        super.sqlProducer = new AssetsShareFlowModel((SfUserDTO)userAccount, ffDTO);
    }


    /**
         * ���ܣ������ʲ����ݣ������������ϣ�����
         * @param lineSet DTOSet �ʲ�����
         * @param flowDTO ��������
         * @return boolean
         */
        public boolean saveOrder(DTOSet lineSet, FlowDTO flowDTO) {
            boolean operateResult = false;
            boolean autoCommit = true;
            FreeFlowDTO dtoPara = (FreeFlowDTO) dtoParameter;
            String transType = dtoPara.getTransType();
            try {
                autoCommit = conn.getAutoCommit();
                conn.setAutoCommit(false);
                String transId = saveOrderHeader(flowDTO);
                saveOrderLines(transId, lineSet);
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
         * @param flowDTO ��������
         * @return boolean
         */
        public boolean submitOrder(DTOSet lineSet, FlowDTO flowDTO) {
            boolean operateResult = false;
            boolean autoCommit = true;
            FreeFlowDTO dtoPara = (FreeFlowDTO) dtoParameter;
            String transType = dtoPara.getTransType();
            try {
                autoCommit = conn.getAutoCommit();
                conn.setAutoCommit(false);
                String transId = saveOrderHeader(flowDTO);
                saveOrderLines(transId, lineSet);
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
                        } else if (transType.equals(AssetsDictConstant.ASS_SHARE)){
                        	prodMessage(AssetsMessageKeys.ASSETS_SHARE_FAILURE);
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
                        } else if (transType.equals(AssetsDictConstant.ASS_SHARE)){
                        	prodMessage(AssetsMessageKeys.ASSETS_SHARE_SUCCESS);
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
        
       public boolean doDelete(DTOSet lineSet, FlowDTO flowDTO) {
            boolean operateResult = false;
            boolean autoCommit = true;
            FreeFlowDTO dtoPara = (FreeFlowDTO) dtoParameter;
            String transType = dtoPara.getTransType();
            try {
                autoCommit = conn.getAutoCommit();
                conn.setAutoCommit(false);
                String transId = "";
                FreeFlowDTO headerDTO = (FreeFlowDTO)dtoParameter;
                transId = headerDTO.getTransId();
                      deleteLines();
                    deleteReserveAssets();
                saveOrderLines(transId, lineSet);
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
         * @param flowDTO ������������
         * @return String
         * @throws DataHandleException
         */
        private String saveOrderHeader(FlowDTO flowDTO) throws DataHandleException {
            String transId = "";
            try {
                FreeFlowDTO headerDTO = (FreeFlowDTO)dtoParameter;
                transId = headerDTO.getTransId();
                String transNo = headerDTO.getTransNo();
                headerDTO.setFromPerson(userAccount.getEmployeeNumber());
                String act = headerDTO.getAct();
                flowDTO.setApproveContent(FlowConstant.APPROVE_CONTENT_NEW);
                if (transNo.equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
                    if(StrUtil.isEmpty(transId)){
                        SeqProducer seq = new SeqProducer(conn);
                        transId = StrUtil.nullToString(seq.getStrNextSeq("AMS_ASSETS_TRANS_HEADER_S"));
                        headerDTO.setTransId(transId);
                    }
                    String companyCode = userAccount.getCompanyCode(); //���ǲ��ø÷��������¿������Ӿ���Ϊû��Ҫ
//				EtsOuCityMapDTO ouDTO = new EtsOuCityMapDTO();
//				ouDTO.setOrganizationId(headerDTO.getFromOrganizationId());
//				EtsOuCityMapDAO ouDAO = new EtsOuCityMapDAO(userAccount, ouDTO, conn);
//				ouDAO.setDTOClassName(ouDTO.getClass().getName());
//				ouDTO = (EtsOuCityMapDTO)ouDAO.getDataByPrimaryKey();
//				String companyCode = ouDTO.getCompanyCode();

                    String transType = headerDTO.getTransType();
                    OrderNumGenerator numberProducer = new OrderNumGenerator(conn,companyCode, transType);
                    headerDTO.setTransNo(numberProducer.getOrderNum());
                    setDTOParameter(headerDTO);
                    createData();
                    if (act.equals(AssetsActionConstant.SUBMIT_ACTION)) {
                        executeFlow(flowDTO, false); //��������
                    } else {
                        executeFlow(flowDTO, true); //������ת
                    }
                } else {
                    updateData();
                    deleteLines();
                    deleteReserveAssets();
                    if (act.equals(AssetsActionConstant.SUBMIT_ACTION)) {
                        executeFlow(flowDTO, false); //������ת
                    }
                }
            } catch (SQLException ex) {
                Logger.logError(ex);
                throw new DataHandleException(ex);
//		} catch (QueryException ex) {
//			Logger.logError(ex);
//			throw new DataHandleException(ex);
            }
            return transId;
        }

        /**
         * ���ܣ�ִ������
         * @param flowDTO FlowDTO
         * @param add2Flow �ǳ��μ������̻�����������������
         * @throws SQLException
         * @throws DataHandleException
         */
        private void executeFlow(FlowDTO flowDTO, boolean add2Flow) throws
                SQLException, DataHandleException {
            try {
                FreeFlowDTO headerDTO = (FreeFlowDTO)
                                                    dtoParameter;
                flowDTO.setActivity(FlowConstant.FLOW_CODE_NEXT);
                flowDTO.setApplyId(headerDTO.getTransId());
                flowDTO.setSessionUserId(userAccount.getUserId());
                flowDTO.setSessionUserName(userAccount.getUsername());
                flowDTO.setApplyNo(headerDTO.getTransNo());
                if (add2Flow) {
                    FlowAction fa = new FlowAction(conn);
                    fa.setDto(flowDTO);
                    fa.add2Flow("�ʲ������������");
                } else {
                    FlowAction fa = new FlowAction(conn, flowDTO);
                    fa.flow();
                }
            } catch (QueryException ex) {
                ex.printLog();
                throw new DataHandleException(ex);
            }
        }

        /**
         * ���ܣ����浥������Ϣ
         * @param transId String
         * @param lineSet DTOSet
         * @throws DataHandleException
         */
        private void saveOrderLines(String transId, DTOSet lineSet) throws
                DataHandleException {
            if (lineSet != null && !lineSet.isEmpty()) {
                FreeFlowDTO orderDTO = (FreeFlowDTO)
                                                   dtoParameter;
                AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(
                        userAccount, null, conn);
                String transferType = orderDTO.getTransferType();
                for (int i = 0; i < lineSet.getSize(); i++) {
                    AmsAssetsTransLineDTO lineData = (AmsAssetsTransLineDTO)
                                                     lineSet.getDTO(i);
                    if (lineData.getBarcode().equals("")) {
                        continue;
                    }
                    lineData.setTransId(transId);
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
         * @throws DataHandleException
         */
        private void deleteLines() throws DataHandleException {
            AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
            FreeFlowDTO headerDTO = (FreeFlowDTO)
                                                dtoParameter;
            lineDTO.setTransId(headerDTO.getTransId());
            AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount,
                    lineDTO, conn);
            lineDAO.DeleteByForeignKey("transId");
        }

        /**
         * ���ܣ�ɾ�������ݱ������ʲ�
         * @throws DataHandleException
         */
        private void deleteReserveAssets() throws DataHandleException {
            AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
            FreeFlowDTO headerDTO = (FreeFlowDTO)
                                                dtoParameter;
            reserveDTO.setTransId(headerDTO.getTransId());
            AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount,
                    reserveDTO, conn);
            reserveDAO.DeleteByForeignKey("transId");
        }

        /**
         * ���ܣ������ʲ�
         * @param batrcode String
         * @throws DataHandleException
         */
        private void createReserveAssets(String batrcode) throws
                DataHandleException {
            FreeFlowDTO headerDTO = (FreeFlowDTO)
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
         * ���ܣ������ݴ�ĵ���
         * @param transIds String[]
         * @return boolean
         */
        public boolean cancelOrders(String[] transIds) {
            boolean operateResult = false;
            boolean autoCommit = true;
            FreeFlowDTO headerDTO = (FreeFlowDTO) dtoParameter;
            AssetsShareFlowModel modelProducer = (AssetsShareFlowModel) sqlProducer;
            try {
                autoCommit = conn.getAutoCommit();
                conn.setAutoCommit(false);
                String transId = transIds[0];
                headerDTO.setTransId(transId);
                setDTOParameter(headerDTO);
                setDTOClassName(FreeFlowDTO.class.getName());
                headerDTO = (FreeFlowDTO) getDataByPrimaryKey();
                headerDTO.setServletConfig(servletConfig);
                FlowAction flowProcessor = new FlowAction(conn);
                FlowDTO flowDTO = new FlowDTO();
                flowDTO.setProcName(headerDTO.getProcdureName());
                SQLModel sqlModel = null;
                AmsAssetsReservedDAO reserveDAO = new AmsAssetsReservedDAO(userAccount, null, conn);
                AmsAssetsReservedDTO reserveDTO = new AmsAssetsReservedDTO();
                AmsAssetsTransLineDAO lineDAO = new AmsAssetsTransLineDAO(userAccount, null, conn);
                AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
                for (int i = 0; i < transIds.length; i++) {
                    transId = transIds[i];
                    sqlModel = modelProducer.getOrderCancelModel(transId); //��������
                    DBOperator.updateRecord(sqlModel, conn);

                    reserveDTO.setTransId(transId); //ɾ����������
                    reserveDAO.setDTOParameter(reserveDTO);
                    reserveDAO.DeleteByForeignKey("transId");

                    lineDTO.setTransId(transId);
                    lineDAO.setDTOParameter(lineDTO);
                    lineDAO.cancelLinesByHeader();

                    flowDTO.setApplyId(transId); //ɾ���ڰ�������
                    flowProcessor.setDto(flowDTO);
                    flowProcessor.cancel();
                }
                operateResult = true;
            } catch (DataHandleException ex) {
                ex.printLog();
            } catch (SQLException ex) {
                Logger.logError(ex);
            } catch (QueryException ex) {
                ex.printLog();
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

        public File exportFile() throws DataTransException { //����ģ��
            File file = null;
            DataTransfer transfer = null;
            AssetsShareFlowModel modelProducer = (AssetsShareFlowModel)
                                                      sqlProducer;
            SQLModel sqlModel = modelProducer.getOrderModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);

            FreeFlowDTO headerDTO = (FreeFlowDTO)
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
        public boolean isGroupFlowId() throws QueryException{
        	AssetsShareFlowModel modelProducer = (AssetsShareFlowModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getGroupPidModel();
            SimpleQuery simp = new SimpleQuery(sqlModel,conn);
            simp.executeQuery();
            return simp.hasResult();
        }

        public String findGroupFlowId() throws QueryException{
            String GroupPid = "";
            try {
            	AssetsShareFlowModel modelProducer = (AssetsShareFlowModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getGroupPidModel();
            SimpleQuery simp = new SimpleQuery(sqlModel,conn);
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


}
