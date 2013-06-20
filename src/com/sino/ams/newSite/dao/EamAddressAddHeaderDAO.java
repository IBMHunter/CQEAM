package com.sino.ams.newSite.dao;

import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.newSite.dto.EamAddressAddHDTO;
import com.sino.ams.newSite.dto.EamAddressAddLDTO;
import com.sino.ams.newSite.model.EamAddressAddHModel;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.user.dao.EtsOuCityMapDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author ���� :wangzhipeng
 * @version ����ʱ�䣺Apr 12, 2011 2:11:46 PM
 *          ��˵��:�����ص㹤����DAO
 */
public class EamAddressAddHeaderDAO extends AMSProcedureBaseDAO {

    public EamAddressAddHeaderDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EamAddressAddHDTO dtoPara = (EamAddressAddHDTO) dtoParameter;
        sqlProducer = new EamAddressAddHModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ�����ص㵥��
     * ͷ��Ϣ������Ϣ �ύ������
     */
    public boolean saveOrder(DTOSet lineSet) throws SQLModelException, ContainerException, QueryException {
        boolean result = false;
        boolean autoCommit = true;
        EamAddressAddHDTO dtoPara = (EamAddressAddHDTO) dtoParameter;
        boolean isNewData = dtoPara.getTransNo().equals(AssetsWebAttributes.ORDER_AUTO_PROD);
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String excel = dtoPara.getExcel();
            saveOrderHeader();
            deleteOrderLines();
            if (StrUtil.isEmpty(excel)) {
                saveOrderLines(lineSet);       //��������Ϣ
            } else {            //��֤������
                EamAddressAddImportDAO ImObDAO = new EamAddressAddImportDAO(userAccount, null, conn);
                ImObDAO.importObject(lineSet, dtoPara.getTransId());
            }
            result = processProcedure();
        } catch (DataHandleException ex) {
            result = false;
            Logger.logError(ex);
        } catch (Throwable ex) {
            result = false;
            Logger.logError(ex);
        } finally {
            try {
                if (!result) {
                    conn.rollback();
                    prodMessage("OBJECT_ORDER_PROCESS_FAILURE");
                    if (isNewData) {
                        rollbackData();
                    }
                } else {
                    conn.commit();
                    prodMessage("OBJECT_ORDER_PROCESS_SUCCESS");
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("����");
                message.setIsError(!result);
            } catch (SQLException ex) {
                result = false;
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return result;
    }

    public boolean importOrder(DTOSet lineSet) throws SQLModelException, ContainerException, QueryException {
        boolean result = false;
        boolean autoCommit = true;
        EamAddressAddHDTO dtoPara = (EamAddressAddHDTO) dtoParameter;
        boolean isNewData = dtoPara.getTransNo().equals(AssetsWebAttributes.ORDER_AUTO_PROD);
        boolean importResult = true;
        String excel = dtoPara.getExcel();
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            saveOrderHeader();
            deleteOrderLines();
            if (StrUtil.isEmpty(excel)) {
                saveOrderLines(lineSet);       //��������Ϣ
            } else {            //��֤������
                EamAddressAddImportDAO ImObDAO = new EamAddressAddImportDAO(userAccount, null, conn);
                importResult = ImObDAO.importObject(lineSet, dtoPara.getTransId());
            }
            result = processProcedure();                   //���̿�ʼ��ת
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            try {
                boolean needMessage = true;
                if (!StrUtil.isEmpty(excel)) {
                    message = new Message();
                    if (importResult) {
                        message.setMessageValue("�ص����ݵ���ɹ�");
                    } else {
                        message.setMessageValue("�ص����ݵ���ʧ�ܣ���鿴������ʾ��");
                        message.setIsError(true);
                    }
                    needMessage = false;
                }
                if (!result) {
                    conn.rollback();
                    if (isNewData) {
                        rollbackData();
                    }
                } else {
                    conn.commit();
                }
                if(needMessage){
                    if (!result) {
                        prodMessage("OBJECT_ORDER_PROCESS_FAILURE");
                    } else {
                        prodMessage("OBJECT_ORDER_PROCESS_SUCCESS");
                    }
                    message.addParameterValue("����");
                    message.setIsError(!result);
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                result = false;
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return result;
    }

    /**
     * ���̱��ύ���ύ��ets_object(�ص��)��
     * �ύ�����������1.��ʼ�������� ������ύ��Ϣ  2.��˱���ֱ����ת
     * ���������1.������ֱ���ύ 2.���ȱ������Ϣ���ٽ����ύ��
     * ������������, AppFlowBaseDTO ���̶���
     *
     * @throws ContainerException
     * @throws QueryException
     */
    public boolean submitOrder(DTOSet lineSet) throws SQLModelException, QueryException, ContainerException {
        boolean result = false;
        boolean autoCommit = true;
        EamAddressAddHDTO dtoPara = (EamAddressAddHDTO) dtoParameter;
        boolean isNewData = dtoPara.getTransNo().equals(AssetsWebAttributes.ORDER_AUTO_PROD);
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            if (dtoPara.getSf_task_attribute1().equals("form1")) {     //���̵�һ�����

                dtoPara.setTransStatus(DictConstant.IN_PROCESS);
                dtoParameter = dtoPara;

                this.saveOrderHeader();            //���浥��ͷ
                this.deleteOrderLines();            //ɾ������Ϣ
                this.saveOrderLines(lineSet);                //��������Ϣ
            } else {
                if (dtoPara.isFlowOver()) {
                    dtoPara.setTransStatus(DictConstant.APPROVED);//�����ݸ��µ���ʽ��
                    saveEtsObject(lineSet);
                } else {
                    dtoPara.setTransStatus(DictConstant.IN_PROCESS);
                }
                updateTransStatus();      //�޸�����״̬
            }
            result = processProcedure();
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!result) {
                    conn.rollback();
                    prodMessage("OBJECT_ORDER_PROCESS_FAILURE");
                    if (isNewData) {
                        rollbackData();
                    }
                } else {
                    conn.commit();
                    prodMessage("OBJECT_ORDER_PROCESS_SUCCESS");
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("�ύ");
                message.setIsError(!result);
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return result;
    }


    /**
     * ���ܣ����浥��ͷ��Ϣ
     *
     * @throws SQLException
     * @throws DataHandleException
     */
    private void saveOrderHeader() throws DataHandleException {
        try {
            EamAddressAddHDTO dtoPara = (EamAddressAddHDTO) dtoParameter;
            if (dtoPara.getTransNo().equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {        // �ж��Ƿ����ͷ��Ϣ��������
                OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), "ASS-LOC");
                String orderNo = ong.getOrderNum();
                dtoPara.setTransNo(orderNo);
                if (dtoPara.getTransId().length() == 0) {
                    SeqProducer seq = new SeqProducer(conn);
                    dtoPara.setTransId(seq.getGUID());
                }
                createData();
            } else {
                updateData();
            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        }
    }


    /**
     * ���ܣ�ɾ����������Ϣ
     * ������transId
     */
    private void deleteOrderLines() throws DataHandleException {
        EamAddressAddHDTO dto = (EamAddressAddHDTO) dtoParameter;
        EamAddressAddHModel modelProducer = (EamAddressAddHModel) sqlProducer;
        modelProducer.setDTOParameter(dto);
        SQLModel sqlModel = modelProducer.deleteImportModel();
        DBOperator.updateRecord(sqlModel, conn);
    }


    /**
     * ���ܣ����浥������Ϣ
     *
     * @throws DataHandleException
     */
    private void saveOrderLines(DTOSet lineSet) throws DataHandleException {
        if (lineSet != null && !lineSet.isEmpty()) {
            EamAddressAddHDTO dtoPara = (EamAddressAddHDTO) dtoParameter;
            String transId = dtoPara.getTransId();
            EamAddressAddLDAO lineDAO = new EamAddressAddLDAO(userAccount, null, conn);
            for (int i = 0; i < lineSet.getSize(); i++) {
                EamAddressAddLDTO lineData = (EamAddressAddLDTO) lineSet.getDTO(i);
                lineData.setTransId(transId);
                lineDAO.setDTOParameter(lineData);
                lineDAO.createData();
            }
        }
    }

    /**
     * ���ܣ�����������Ϣ
     *
     * @return
     */
    protected void prepareProcedureData() {
        EamAddressAddHDTO dtoPara = (EamAddressAddHDTO) dtoParameter;
        dtoPara.setPrimaryKey(dtoPara.getTransId());
        dtoPara.setOrderNo(dtoPara.getTransNo());
        dtoPara.setOrderName(dtoPara.getTransType());
    }


    public void delImportEtsObject(String transId) throws DataHandleException, SQLModelException {
        EamAddressAddHModel model = (EamAddressAddHModel) sqlProducer;
        SQLModel sqlModel = model.delImportModel(transId);
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * �޸ı���״̬Ϊ����״̬:0   �������ص����
     *
     * @throws DataHandleException
     * @throws ContainerException
     * @throws QueryException
     * @throws SQLModelException
     */
    private void saveEtsObject(DTOSet lineSet) throws DataHandleException, SQLModelException, QueryException, ContainerException {
        if (lineSet != null && !lineSet.isEmpty()) {
            EamAddressAddHModel model = (EamAddressAddHModel) sqlProducer;
            SQLModel sqlModel = null;
            for (int i = 0; i < lineSet.getSize(); i++) {
                EamAddressAddLDTO lineData = (EamAddressAddLDTO) lineSet.getDTO(i);
                if (lineData.getAddrMaintainType().equals("����") || lineData.getAddrMaintainType().equals("ADD")) { //����
                    EtsObjectDTO etsDto = fillDTO(lineData);
                    etsDto.setWorkorderObjectNo(getNextGUID());
                    //commonObjectDAO.setDTOParameter(etsDto);
                    //etsDto.setWorkorderObjectCode(commonObjectDAO.getNextWorkorderObjectCode(SinoConfig.getProvinceCode())); //���õص����
                    sqlModel = model.createDoEtsObject(etsDto);
                    DBOperator.updateRecord(sqlModel, conn);

                    sqlModel = model.getAddressCreateModel(etsDto);
                    DBOperator.updateRecord(sqlModel, conn);
                    //if (!userAccount.getIsTt().equals("Y")) {
	                    if (!isExistsLocCode(etsDto.getWorkorderObjectCode())) { //�ж������ĵص��Ƿ��ڵص�ڶ��δ���ά�����д���
	                    	EtsOuCityMapDAO etsOuCityMapDAO = new EtsOuCityMapDAO(userAccount, null, conn);
	                        etsDto.setCompanyCode(etsOuCityMapDAO.getCompanyCodeByOrgId(etsDto.getOrganizationId()));
	                    	
	                    	sqlModel = model.getInsertLocCodeModel(etsDto);
	                        DBOperator.updateRecord(sqlModel, conn);
	                    }
                    //}

                    //sqlModel = model.updateWorkorderObjectCode(etsDto.getWorkorderObjectCode(), lineData.getLineId());
                    //DBOperator.updateRecord(sqlModel, conn);
                } else if (lineData.getAddrMaintainType().equals("�޸�") || lineData.getAddrMaintainType().equals("UPDATE")) { //�޸�
                    EtsObjectDTO etsDto = fillDTO(lineData);
                    sqlModel = model.updateEtsObjectInfo(etsDto);
                    DBOperator.updateRecord(sqlModel, conn);
                    
                    //�޸�����ص���Ϣ
                    sqlModel = model.updateEtsObjectLocInfo(etsDto);
                    DBOperator.updateRecord(sqlModel, conn);
                } else { //ʧЧ
                    sqlModel = model.disabledEtsObject(lineData.getWorkorderObjectCode());
                    DBOperator.updateRecord(sqlModel, conn);
                }
            }
        }
    }

    /**
     * �޸�����״̬
     * ����״̬:transStatus
     */
    public void updateTransStatus() throws DataHandleException {
        EamAddressAddHModel modelProducer = (EamAddressAddHModel) sqlProducer;
        SQLModel sqlModel = modelProducer.updateTransStatus();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ��ȡ������Ϣ
     */
    public DTOSet getLineData() throws QueryException {
        EamAddressAddHDTO dto = (EamAddressAddHDTO) dtoParameter;
        EamAddressAddHModel model = (EamAddressAddHModel) sqlProducer;
        SQLModel sqlModel = model.getLineDataModel(dto);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(EamAddressAddLDTO.class.getName());
        simp.executeQuery();
        return simp.getDTOSet();
    }

    /**
     * ������Ϣת��Ϊ EtsObjectDTO
     *
     * @param lineData
     * @return
     * @throws ContainerException
     * @throws QueryException
     * @throws SQLModelException
     */
    public EtsObjectDTO fillDTO(EamAddressAddLDTO lineData) throws SQLModelException, QueryException, ContainerException {
        EtsObjectDTO dto = new EtsObjectDTO();
        dto.setWorkorderObjectCode(lineData.getWorkorderObjectCode());     //�ص����
        dto.setWorkorderObjectName(lineData.getWorkorderObjectName());     //�ص�����
        dto.setWorkorderObjectLocation(lineData.getWorkorderObjectName()); //���ڵص�
        dto.setOrganizationId(userAccount.getOrganizationId());            //��֯ID()
        dto.setBtsNo(lineData.getBtsNo());
        String countyCode = "";
        if (lineData.getAddrMaintainType().equals("����")) {
            countyCode = lineData.getCountyCode();
        } else {
            countyCode = lineData.getWorkorderObjectCode().substring(0, lineData.getWorkorderObjectCode().indexOf("."));
        }
        dto.setCountyCode(countyCode);                       //���ش���    �����ֶβ���Ϊ��
        dto.setObjectCategory(getObjCatgoryCode(lineData.getObjectCategory()));              //�ص����
        dto.setIsTempAddr(0);                                           //1��ʱ�ص�,0��ʽ�ص�
        dto.setCreatedBy(userAccount.getUserId());           //������
        if (lineData.getAreaType().length() == 1) {
            lineData.setAreaType("0" + lineData.getAreaType());
        }
        if (userAccount.getIsTt().equals("Y")) {  //��ͨ
        	dto.setIsTd("Y");
        } else {
        	dto.setIsTd(userAccount.getIsTd());
        }
        dto.setAreaType(lineData.getAreaType());          //��������
        dto.setDeptCode(userAccount.getDeptCode());              //����
        dto.setCity(getCountyCode(lineData.getCity()));                  //��
        dto.setCounty(getCountyCode(lineData.getCounty()));              //����
        if (lineData.getObjectCategory().equals("JZ") || lineData.getObjectCategory().equals("YY")) { //��վ��Ӫҵ�������ñ���
            dto.setBtsNo(lineData.getBtsNo());
        }
        return dto;
    }


    public String getCountyCode(String strCode) throws SQLModelException, QueryException, ContainerException {
        EamAddressAddHModel model = (EamAddressAddHModel) sqlProducer;
        SQLModel sqlModel = model.getCountyCode(strCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        String str = "";
        if (simpleQuery.hasResult()) {
            str = simpleQuery.getSearchResult().getRow(0).getStrValue("COUNTY_CODE");
        }
        return str;
    }

    /**
     * ��ȡ�������
     *
     * @param str
     * @return
     * @throws SQLModelException
     * @throws QueryException
     * @throws ContainerException
     */
    public String getAreaCountyCode(String str) throws SQLModelException, QueryException, ContainerException {
        EamAddressAddHModel model = (EamAddressAddHModel) sqlProducer;
        SQLModel sqlModel = model.getAreaCountyCode(str);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        String code = "";
        if (simpleQuery.hasResult()) {
            code = simpleQuery.getSearchResult().getRow(0).getStrValue("COUNTY_CODE");
        }
        return code;
    }

    /**
     * ���ܣ���ȡ�ص�רҵ����
     *
     * @throws SQLModelException
     * @throws ContainerException
     */
    public String getObjCatgoryCode(String category) throws SQLModelException, QueryException, ContainerException {
        EamAddressAddHModel eoModel = (EamAddressAddHModel) sqlProducer;
        SQLModel sqlModel = eoModel.OCModel(category);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        String str = "";
        if (simpleQuery.hasResult()) {
            str = simpleQuery.getSearchResult().getRow(0).getStrValue("CODE");
        }
        return str;
    }

    /**
     * ��ȡExcel��Ϣ eam_address_L ��������
     */
    public DTOSet getImpDS(String transId) throws SQLModelException, QueryException {
        DTOSet ds = new DTOSet();
        EamAddressAddHModel model = (EamAddressAddHModel) sqlProducer;
        SQLModel sqlModel = model.getAllQueryImportModel(transId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.setDTOClassName(EamAddressAddLDTO.class.getName());
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            ds = simpleQuery.getDTOSet();
        }
        return ds;
    }

    private void rollbackData() {
        EamAddressAddHDTO dtoPara = (EamAddressAddHDTO) dtoParameter;
        dtoPara.setTransId("");
        dtoPara.setTransNo(AssetsWebAttributes.ORDER_AUTO_PROD);
    }


    public void rejectOrder() {
        boolean operateResult = rejectProcedure();
        if (operateResult) {
            prodMessage(AssetsMessageKeys.REJECT_ORDER_SUCCESS);
        } else {
            prodMessage(AssetsMessageKeys.REJECT_ORDER_FAILURE);
            message.setIsError(true);
        }
        EamAddressAddHDTO dto = (EamAddressAddHDTO) dtoParameter;
        message.addParameterValue(dto.getTransType());
        message.addParameterValue(dto.getTransNo());
    }

    /**
     * ���ܣ������ݴ�ĵ���
     *
     * @return boolean
     */
    public boolean cancelOrder() {
        boolean operateResult = false;
        boolean autoCommit = true;
        EamAddressAddHDTO headerDTO = (EamAddressAddHDTO) dtoParameter;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            headerDTO.setTransStatus(DictConstant.CANCELED);
            EamAddressAddHModel modelProducer = (EamAddressAddHModel) sqlProducer;
            SQLModel sqlModel = modelProducer.updateTransStatus();
            DBOperator.updateRecord(sqlModel, conn);
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
                message.addParameterValue(headerDTO.getTransType());
                message.setIsError(!operateResult);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    /**
     * ���ص�ڶ��δ����Ƿ����
     *
     * @throws SQLModelException
     */
    public boolean isExistsLocCode(String workorderObjectCode) throws SQLModelException, QueryException {
        boolean hasBarcode = true;
        EamAddressAddHModel modelProducer = (EamAddressAddHModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsExistsLocCode(workorderObjectCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (!simpleQuery.hasResult()) {
            hasBarcode = false;
        }
        return hasBarcode;
    }

}
