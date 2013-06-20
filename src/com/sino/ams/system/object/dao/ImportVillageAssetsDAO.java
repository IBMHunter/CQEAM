package com.sino.ams.system.object.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.onnet.dto.AmsItemOnNetDTO;
import com.sino.ams.system.object.dto.ImportVillageAssetsDTO;
import com.sino.ams.system.object.model.ImportVillageAssetsModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-5-23
 * Time: 19:44:07
 * To change this template use File | Settings | File Templates.
 */
public class ImportVillageAssetsDAO  extends AMSBaseDAO {

    private SfUserDTO sfUser = null;
    /**
     * ���ܣ��ص㵼�� AMS_OBJECT_IMPORT ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsItemOnNetDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public ImportVillageAssetsDAO(SfUserDTO userAccount, AmsItemOnNetDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        ImportVillageAssetsDTO dtoPara = (ImportVillageAssetsDTO)dtoParameter;
        super.sqlProducer = new ImportVillageAssetsModel((SfUserDTO)userAccount, dtoPara);
    }

    /**
     * ���ܣ�ɾ���ӿڱ�����ݡ�
     * @throws com.sino.base.exception.SQLModelException
     */
     public void deleteImportModel() throws SQLModelException, QueryException, DataHandleException {
            ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
            SQLModel sqlModel = eoModel.deleteImportModel();
            DBOperator.updateRecord(sqlModel, conn);
    }


    /**
     * ���ܣ����ӿڱ����ݵ���Ч�ԡ�
     */
    public void doVerifyData(DTOSet dtoSet) throws SQLModelException, QueryException, ContainerException {
        if (dtoSet != null && dtoSet.getSize() > 0) {
            for (int i = 0; i < dtoSet.getSize(); i++) {
                ImportVillageAssetsDTO villageDTO = (ImportVillageAssetsDTO) dtoSet.getDTO(i);
                if (StrUtil.isEmpty(villageDTO.getCompanyCode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "��˾���벻��Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getBarcode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "��ͨ�ʲ���ǩ�Ų���Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getItemName())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ����Ʋ���Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getItemSpec())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "����ͺŲ���Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getEmployeeNumber())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�����˱�Ų���Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getEmployeeName())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "��������������Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getWorkorderObjectCode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ��ص���벻��Ϊ��");
                } else if (!validateOU(villageDTO.getCompanyCode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "��˾���벻��ȷ");
                } else if (!validateBarcodeOu(villageDTO.getBarcode(), villageDTO.getCompanyCode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�������Ӧ��˾����ȷ");
                } else if (!validateSameBarcode(villageDTO.getBarcode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "EXCEL�������ظ�");
                } else if (!validateSameEiiBarcode(villageDTO.getBarcode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "������ʵ��ϵͳ���Ѵ���");
                } else if (!validateItemNS(villageDTO.getItemName(), villageDTO.getItemSpec()) && (!StrUtil.isEmpty(villageDTO.getItemName()) || !StrUtil.isEmpty(villageDTO.getItemSpec()))) {
                    insertImprotErrorData(villageDTO.getBarcode(), "���ƺ��ͺŲ�����");
                } else if (!validateNewEmployeeNum(villageDTO.getEmployeeNumber()) && !StrUtil.isEmpty(villageDTO.getEmployeeNumber())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�����˱�Ų�����");
                } else if (!validateObjectCode(villageDTO.getWorkorderObjectCode()) && !StrUtil.isEmpty(villageDTO.getWorkorderObjectCode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ��ص���벻����");
                } else if (!validateContentCode(villageDTO.getContentCode()) && !StrUtil.isEmpty(villageDTO.getContentCode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ���������ϲ�����");
                } else if (!validateNewResDept(villageDTO.getSpecialityDept()) && !StrUtil.isEmpty(villageDTO.getSpecialityDept())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "ʵ�ﲿ�Ŵ��벻����");
                } else if (!validateNewResDept(villageDTO.getMaintainDept()) && !StrUtil.isEmpty(villageDTO.getMaintainDept())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "ʹ�ò��Ŵ��벻����");
                } else if (!validateEmployee(villageDTO.getMaintainDept(), villageDTO.getEmployeeNumber()) && !StrUtil.isEmpty(villageDTO.getMaintainDept())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "ʹ�ò��ź������˱�Ų�һ��");
                } else if (!validateStartDate(villageDTO.getVillageStartDate()) && !StrUtil.isEmpty(villageDTO.getVillageStartDate())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�������ڸ�ʽ����ȷ");
                } else if (!validateTenancy(villageDTO.getPrice()) && !StrUtil.isEmpty(villageDTO.getPrice())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ɱ���������������");
                } else if (!validateTenancy(villageDTO.getDeprnCost()) && !StrUtil.isEmpty(villageDTO.getDeprnCost())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "��ֵ��������������");
                } else if (!validateTenancy(villageDTO.getScrapValue()) && !StrUtil.isEmpty(villageDTO.getScrapValue())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "��ֵ��������������");
                } else if (!validateTenancy(villageDTO.getUseYear()) && !StrUtil.isEmpty(villageDTO.getUseYear())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "ʹ�����ޱ�������������");
                } else if (!validateTenancy(villageDTO.getRemainMonth()) && !StrUtil.isEmpty(villageDTO.getRemainMonth())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "ʣ��������������������");
                } else if (!validateBarcodeLength(villageDTO.getBarcode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�������Ϊ13λ");
                } else if (!validateBarcode(villageDTO.getBarcode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ���ǩ�Ŵ���Сд��ĸ");
                } else if (!validateBarcode2(villageDTO.getBarcode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ���ǩ���в����ڡ�CT����DG��");
                }
            }
        }
    }

    public boolean validateBarcode2(String barCode) {
        boolean isTrue = true;
        int count = barCode.indexOf("CT");
        int count2 = barCode.indexOf("DG");
        if (count < 0 && count2 < 0) {
            isTrue = false;
        }
        return isTrue;
    }

    public boolean validateBarcode(String barCode) {
        boolean isUpper = barCode.toUpperCase().equals(barCode);
        return isUpper;
    }

    public boolean validateBarcodeLength(String barCode) {
        boolean isTrue = true;
        int length = barCode.length();
        if (length != 13) {
            isTrue = false;
        }
        return isTrue;
    }

    /**
     * ���ܣ�У�鵱ǰ�û�������ͨ�������ʲ���Ȩ��
     * @throws SQLModelException
     */
     public boolean validateOU(String companyCode) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateOU(companyCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��ɱ�����ֵ����ֵ��ʹ�����ޡ�ʣ��������������������
     * @throws SQLModelException
     */
     public boolean validateTenancy(String tenancy) throws SQLModelException, QueryException {
        boolean isTrue = true;
        if (!StrUtil.isNumber(tenancy)) {
            isTrue = false;
        }
        return isTrue;
    }

    /**
     * ���ܣ�У���������ڸ�ʽ�Ƿ���ȷ
     * @throws SQLModelException
     */
     public boolean validateStartDate(String date) throws SQLModelException, QueryException {
        boolean isTrue = true;
        if (!CalendarUtil.isValidDate(date)) {
            isTrue = false;
        }
        return isTrue;
    }

    /**
     * ���ܣ�У�������ˣ����β��Ŷ�Ӧ�Ƿ���ȷ
     * @throws SQLModelException
     */
     public boolean validateEmployee(String deptCode, String employeeNumber) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateEmployee(deptCode, employeeNumber);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��BARCODE�Ƿ����ڱ���˾BARCODE
     * @throws SQLModelException
     */
     public boolean validateBarcodeOu(String barcode, String companyCode) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        String subBarcode = barcode.substring(0,4);
        if (subBarcode.equals(companyCode)) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��NEW_ITEM_NAME��NEW_ITEM_SPEC�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateItemNS(String itemName, String itemSpec) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateItemNS(itemName, itemSpec);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��EMPLOYEE_NUMBER�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateNewEmployeeNum(String newEmployeeNum) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateNewEmployeeNum(newEmployeeNum);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��NEW_OBJECT_CODE�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateObjectCode(String newObjectCode) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateObjectCode(newObjectCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
     }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��NEW_OBJECT_CODE�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateContentCode(String contentCode) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateContentCode(contentCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
     }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��NEW_RESPONSIBILITY_DEPT�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateNewResDept(String newResDept) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateNewResDept(newResDept);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
     }

    /**
     * ���ܣ����������Ϣ��
     * @throws SQLModelException
     */
     public void insertImprotErrorData(String barcode,String codeError) throws SQLModelException {
        try {
            ImportVillageAssetsModel onNetModel = (ImportVillageAssetsModel) sqlProducer;
            SQLModel sqlModel = onNetModel.insertImprotErrorData(barcode,codeError);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (DataHandleException ex) {
            ex.printLog();
        }
    }

    /**
     * ���ܣ�У��BARCODE��ETS_ITEM_INFO���Ƿ����
     * @throws SQLModelException
     */
     public boolean validateSameEiiBarcode(String barcode) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode=true;
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateSameEiiBarcode(barcode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = false;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��BARCODE�����ظ�
     * @throws SQLModelException
     */
     public boolean validateSameBarcode(String barcode) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode=true;
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateSameBarcode(barcode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            int count = Integer.parseInt(StrUtil.nullToString(row.getValue("BAR_QTY")));
            if (count > 1) {
                hasBarcode = false;
            }
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У����AMS_VILLAGE_ASSETS_IMPORT�Ƿ���ڵ������
     * @throws SQLModelException
     */
     public boolean importHasError() throws SQLModelException, QueryException {
        boolean hasError=false;
            ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
            SQLModel sqlModel = eoModel.hasErrorModel();
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if(simpleQuery.hasResult()){
               hasError = true;
            }
        return hasError;
    }

    /**
     * ���ܣ���AMS_VILLAGE_ASSETS_IMPORT�л�ȡ����ɹ�������
     * @throws QueryException
     */
       public DTOSet getImport() throws QueryException, SQLModelException {
            ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
            SimpleQuery sq = new SimpleQuery(eoModel.getQueryImportModel(), conn);
            sq.setDTOClassName(ImportVillageAssetsDTO.class.getName());
            sq.executeQuery();
            return sq.getDTOSet();
        }

    /**
     * ���ܣ�ͨ��NEW_ITEM_NAME,NEW_ITEM_SPECȡ��ITEM_CODE
     * @throws SQLModelException
     */
     public String getItemCode(String itemName, String itemSpce) throws SQLModelException, QueryException, ContainerException {
        String itemCode = "";
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.getItemCodeModel(itemName, itemSpce);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            itemCode = StrUtil.nullToString(row.getValue("ITEM_CODE"));
        }
        return itemCode;
    }

    /**
     * ���ܣ�ͨ��NEW_OBJECT_CODEȡ��ADDRESS_ID
     * @throws SQLModelException
     */
     public String getAddressId(String objectCode) throws SQLModelException, QueryException, ContainerException {
        String addressId = "";
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.getAddressIdModel(objectCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            addressId = StrUtil.nullToString(row.getValue("ADDRESS_ID"));
        }
        return addressId;
    }

    /**
     * ���ܣ�ͨ��NEW_EMPLOYEE_NUMBERȡ��EMPLOYEE_ID
     * @throws SQLModelException
     */
     public String getEmployeeId(String employeeNumber) throws SQLModelException, QueryException, ContainerException {
        String employeeId = "";
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.getEmployeeIdModel(employeeNumber);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            employeeId = StrUtil.nullToString(row.getValue("EMPLOYEE_ID"));
        }
        return employeeId;
    }

    /**
     * ���ܣ�ͨ��NEW_EMPLOYEE_NUMBERȡ��DEPT_CODE
     * @throws SQLModelException
     */
     public String getDeptCode(String employeeNumber) throws SQLModelException, QueryException, ContainerException {
        String employeeId = "";
        ImportVillageAssetsModel eoModel = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.getDeptCodeModel(employeeNumber);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            employeeId = StrUtil.nullToString(row.getValue("DEPT_CODE"));
        }
        return employeeId;
    }

    /**
     * ���ܣ�������Ϣ����EXCEL
     */
    public File getExportFile() throws DataTransException, SQLModelException {
        ImportVillageAssetsModel modelProducer = (ImportVillageAssetsModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getImportErrorModel();
        String reportTitle = "��ͨ�ʲ����������Ϣ";
        String fileName = reportTitle + ".xls";
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setSourceConn(conn);
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);
        DataRange range = new DataRange();
        rule.setDataRange(range);
        rule.setFieldMap(getFieldMap());
        CustomTransData custData = new CustomTransData();
        custData.setReportTitle(reportTitle);
        custData.setReportPerson(userAccount.getUsername());
        custData.setNeedReportDate(true);
        rule.setCustData(custData);
        rule.setCalPattern(LINE_PATTERN);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        return (File) transfer.getTransResult();
    }

    private Map getFieldMap(){
        Map fieldMap = new HashMap();
        fieldMap.put("COMPANY_CODE", "��˾����");
        fieldMap.put("BARCODE", "��ͨ�ʲ���ǩ��");
        fieldMap.put("ITEM_NAME", "�ʲ�����");
        fieldMap.put("ITEM_SPEC", "����ͺ�");
        fieldMap.put("EMPLOYEE_NUMBER", "�����˱��");
        fieldMap.put("EMPLOYEE_NAME", "����������");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���ϱ���");
        fieldMap.put("EQUIPMENT_PERFORMANCE", "�豸����");
        fieldMap.put("CONTENT_CODE", "�ʲ����������");
        fieldMap.put("CONTENT_NAME", "�ʲ��������");
        fieldMap.put("WORKORDER_OBJECT_NAME", "�ʲ��ص�����");
        fieldMap.put("SPECIALITY_DEPT", "ʵ�ﲿ�Ŵ���");
        fieldMap.put("MAINTAIN_USER", "ʹ��������");
        fieldMap.put("MAINTAIN_DEPT", "ʹ�ò��Ŵ���");
        fieldMap.put("PRICE", "�ɱ�");
        fieldMap.put("DEPRN_COST", "��ֵ");
        fieldMap.put("SCRAP_VALUE", "��ֵ");
        fieldMap.put("VILLAGE_START_DATE", "��������");
        fieldMap.put("USE_YEAR", "ʹ������");
        fieldMap.put("REMAIN_MONTH", "ʣ������");
        fieldMap.put("REMARK", "��ע");
        fieldMap.put("ERROR", "������Ϣ");
        return fieldMap;
    }
}
