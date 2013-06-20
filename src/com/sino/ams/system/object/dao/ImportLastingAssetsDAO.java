package com.sino.ams.system.object.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.onnet.dto.AmsItemOnNetDTO;
import com.sino.ams.system.object.dto.LastingAssetsDTO;
import com.sino.ams.system.object.model.ImportLastingAssetsModel;
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
import com.sino.base.util.ConvertUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-5-21
 * Time: 15:39:32
 * To change this template use File | Settings | File Templates.
 */
public class ImportLastingAssetsDAO  extends AMSBaseDAO {

    private SfUserDTO sfUser = null;
    /**
     * ���ܣ��ص㵼�� AMS_OBJECT_IMPORT ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsItemOnNetDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public ImportLastingAssetsDAO(SfUserDTO userAccount, AmsItemOnNetDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        LastingAssetsDTO dtoPara = (LastingAssetsDTO)dtoParameter;
        super.sqlProducer = new ImportLastingAssetsModel((SfUserDTO)userAccount, dtoPara);
    }

    /**
     * ���ܣ�ɾ���ӿڱ�����ݡ�
     * @throws com.sino.base.exception.SQLModelException
     */
     public void deleteImportModel() throws SQLModelException, QueryException, DataHandleException {
            ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
            SQLModel sqlModel = eoModel.deleteImportModel();
            DBOperator.updateRecord(sqlModel, conn);
    }


    /**
     * ���ܣ����ӿڱ����ݵ���Ч�ԡ�
     */
    public void doVerifyData(DTOSet dtoSet) throws SQLModelException, QueryException, ContainerException {
        if (dtoSet != null && dtoSet.getSize() > 0) {
            for (int i = 0; i < dtoSet.getSize(); i++) {
                LastingAssetsDTO lastingDTO = (LastingAssetsDTO) dtoSet.getDTO(i);
                if (StrUtil.isEmpty(lastingDTO.getCompanyCode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "��˾���벻��Ϊ��");
                } else if (StrUtil.isEmpty(lastingDTO.getBarcode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�����ʲ���ǩ�Ų���Ϊ��");
                } else if (StrUtil.isEmpty(lastingDTO.getItemName())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�ʲ����Ʋ���Ϊ��");
                } else if (StrUtil.isEmpty(lastingDTO.getItemSpec())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "����ͺŲ���Ϊ��");
                } else if (StrUtil.isEmpty(lastingDTO.getEmployeeNumber())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�����˱�Ų���Ϊ��");
                } else if (StrUtil.isEmpty(lastingDTO.getEmployeeName())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "��������������Ϊ��");
                } else if (StrUtil.isEmpty(lastingDTO.getWorkorderObjectCode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�ʲ��ص���벻��Ϊ��");
                } else if (!validateOU(lastingDTO.getCompanyCode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "��˾���벻��ȷ");
                } else if (!validateBarcodeOu(lastingDTO.getBarcode(), lastingDTO.getCompanyCode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�������Ӧ��˾����ȷ");
                } else if (!validateSameBarcode(lastingDTO.getBarcode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "EXCEL�������ظ�");
                } else if (!validateSameEiiBarcode(lastingDTO.getBarcode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "������ʵ��ϵͳ���Ѵ���");
                } else if (!validateSameRentBarcode(lastingDTO.getBarcode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "������ʵ��ϵͳ���Ѵ���");
                } else if (!validateItemNS(lastingDTO.getItemName(), lastingDTO.getItemSpec()) && (!StrUtil.isEmpty(lastingDTO.getItemName()) || !StrUtil.isEmpty(lastingDTO.getItemSpec()))) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "���ƺ��ͺŲ�����");
                } else if (!validateNewEmployeeNum(lastingDTO.getEmployeeNumber()) && !StrUtil.isEmpty(lastingDTO.getEmployeeNumber())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�����˱�Ų�����");
                } else if (!validateObjectCode(lastingDTO.getWorkorderObjectCode()) && !StrUtil.isEmpty(lastingDTO.getWorkorderObjectCode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�ʲ��ص���벻����");
                } else if (!validateContentCode(lastingDTO.getContentCode()) && !StrUtil.isEmpty(lastingDTO.getContentCode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�ʲ���������ϲ�����");
                } else if (!validateNewResDept(lastingDTO.getSpecialityDept()) && !StrUtil.isEmpty(lastingDTO.getSpecialityDept())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "ʵ�ﲿ�Ŵ��벻����");
                } else if (!validateNewResDept(lastingDTO.getMaintainDept()) && !StrUtil.isEmpty(lastingDTO.getMaintainDept())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "ʹ�ò��Ŵ��벻����");
                } else if (!validateEmployee(lastingDTO.getMaintainDept(), lastingDTO.getEmployeeNumber()) && !StrUtil.isEmpty(lastingDTO.getMaintainDept())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "ʹ�ò��ź������˱�Ų�һ��");
                } else if (!validateStartDate(lastingDTO.getRentStartDate()) && !StrUtil.isEmpty(lastingDTO.getRentStartDate())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "��ʼ���ڸ�ʽ����ȷ");
                } else if (!validateStartDate(lastingDTO.getRentEndDate()) && !StrUtil.isEmpty(lastingDTO.getRentEndDate())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "��ֹ���ڸ�ʽ����ȷ");
                } else if (!validateTenancy(lastingDTO.getTenancy()) && !StrUtil.isEmpty(lastingDTO.getTenancy())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "���ڱ�������������");
                } else if (!validateBarcodeLength(lastingDTO.getBarcode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�������Ϊ13λ");
                } else if (!validateBarcode(lastingDTO.getBarcode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�ʲ���ǩ�Ŵ���Сд��ĸ");
                } else if (!validateBarcode2(lastingDTO.getBarcode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�ʲ���ǩ���в����ڡ�ZL��");
                }
            }
        }
    }

    public boolean validateBarcode2(String barCode) {
        boolean isTrue = true;
        int count = barCode.indexOf("ZL");
        if (count < 0) {
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
     * ���ܣ�У�鵱ǰ�û����������ʲ���Ȩ��
     * @throws SQLModelException
     */
     public boolean validateOU(String companyCode) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateOU(companyCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У�����ڱ�������������
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
     * ���ܣ�У����ʼ���ں���ֹ���ڸ�ʽ�Ƿ���ȷ
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
     * ���ܣ�У��AMS_ITEM_IMPORT��BARCODE�Ƿ������ETS_ITEM_INFO��
     * @throws SQLModelException
     */
     public boolean validateEmployee(String deptCode, String employeeNumber) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateEmployee(deptCode, employeeNumber);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_ITEM_IMPORT��BARCODE�Ƿ����ڱ���˾BARCODE
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
     * ���ܣ�У��AMS_ITEM_IMPORT��NEW_ITEM_NAME��NEW_ITEM_SPEC�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateItemNS(String itemName, String itemSpec) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateItemNS(itemName, itemSpec);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_ITEM_IMPORT��NEW_EMPLOYEE_NUMBER�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateNewEmployeeNum(String newEmployeeNum) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateNewEmployeeNum(newEmployeeNum);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_ITEM_IMPORT��NEW_OBJECT_CODE�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateObjectCode(String newObjectCode) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateObjectCode(newObjectCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
     }

    /**
     * ���ܣ�У��AMS_ITEM_IMPORT��NEW_OBJECT_CODE�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateContentCode(String contentCode) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateContentCode(contentCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
     }

    /**
     * ���ܣ�У��AMS_ITEM_IMPORT��NEW_RESPONSIBILITY_DEPT�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateNewResDept(String newResDept) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
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
            ImportLastingAssetsModel onNetModel = (ImportLastingAssetsModel) sqlProducer;
            SQLModel sqlModel = onNetModel.insertImprotErrorData(barcode,codeError);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (DataHandleException ex) {
            ex.printLog();
        }
    }

    /**
     * ���ܣ�У��BARCODE��AMS_RENT_INFO���Ƿ����
     * @throws SQLModelException
     */
     public boolean validateSameRentBarcode(String barcode) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode=true;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateSameRentBarcode(barcode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = false;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��BARCODE��ETS_ITEM_INFO���Ƿ����
     * @throws SQLModelException
     */
     public boolean validateSameEiiBarcode(String barcode) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode=true;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateSameEiiBarcode(barcode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = false;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_ITEM_IMPORT��BARCODE�����ظ�
     * @throws SQLModelException
     */
     public boolean validateSameBarcode(String barcode) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode=true;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
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
     * ���ܣ�У��AMS_ITEM_IMPORT��NEW_MANUFACTURER_ID�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateNewManufactId(String NewManufactId) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateNewManufactId(NewManufactId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
     }

    /**
     * ���ܣ�У����AMS_ITEM_IMPORT�Ƿ���ڵ������
     * @throws SQLModelException
     */
     public boolean importHasError() throws SQLModelException, QueryException {
        boolean hasError=false;
            ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
            SQLModel sqlModel = eoModel.hasErrorModel();
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if(simpleQuery.hasResult()){
               hasError = true;
            }
        return hasError;
    }

    /**
     * ���ܣ���AMS_ITEM_IMPORT�л�ȡ����ɹ�������
     * @throws QueryException
     */
       public DTOSet getImport() throws QueryException, SQLModelException {
            ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
            SimpleQuery sq = new SimpleQuery(eoModel.getQueryImportModel(), conn);
            sq.setDTOClassName(LastingAssetsDTO.class.getName());
            sq.executeQuery();
            return sq.getDTOSet();
        }

    /**
     * ���ܣ�ͨ��NEW_ITEM_NAME,NEW_ITEM_SPECȡ��ITEM_CODE
     * @throws SQLModelException
     */
     public String getItemCode(String itemName, String itemSpce) throws SQLModelException, QueryException, ContainerException {
        String itemCode = "";
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
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
     public int getAddressId(String objectCode) throws SQLModelException, QueryException, ContainerException {
        int addressId = -1;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.getAddressIdModel(objectCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            addressId = ConvertUtil.String2Int( StrUtil.nullToString(row.getValue("ADDRESS_ID")) );
        }
        return addressId;
    }

    /**
     * ���ܣ�ͨ��NEW_EMPLOYEE_NUMBERȡ��EMPLOYEE_ID
     * @throws SQLModelException
     */
     public int getEmployeeId(String employeeNumber) throws SQLModelException, QueryException, ContainerException {
	 	int employeeId = -1 ;
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.getEmployeeIdModel(employeeNumber);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            employeeId = ConvertUtil.String2Int( StrUtil.nullToString(row.getValue("EMPLOYEE_ID")) );
        }
        return employeeId;
    }

    /**
     * ���ܣ�ͨ��NEW_EMPLOYEE_NUMBERȡ��DEPT_CODE
     * @throws SQLModelException
     */
     public String getDeptCode(String employeeNumber) throws SQLModelException, QueryException, ContainerException {
        String employeeId = "";
        ImportLastingAssetsModel eoModel = (ImportLastingAssetsModel) sqlProducer;
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
        ImportLastingAssetsModel modelProducer = (ImportLastingAssetsModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getImportErrorModel();
        String reportTitle = "�����ʲ����������Ϣ";
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
        fieldMap.put("BARCODE", "�����ʲ���ǩ��");
        fieldMap.put("ITEM_NAME", "�ʲ�����");
        fieldMap.put("ITEM_SPEC", "����ͺ�");
        fieldMap.put("EMPLOYEE_NUMBER", "�����˱��");
        fieldMap.put("EMPLOYEE_NAME", "����������");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���ϱ���");
        fieldMap.put("POWER", "�����");
        fieldMap.put("EQUIPMENT_PERFORMANCE", "�豸����");
        fieldMap.put("CONTENT_CODE", "�ʲ����������");
        fieldMap.put("CONTENT_NAME", "�ʲ��������");
        fieldMap.put("WORKORDER_OBJECT_NAME", "�ʲ��ص�����");
        fieldMap.put("SPECIALITY_DEPT", "ʵ�ﲿ�Ŵ���");
        fieldMap.put("MAINTAIN_USER", "ʹ��������");
        fieldMap.put("MAINTAIN_DEPT", "ʹ�ò��Ŵ���");
        fieldMap.put("START_DATE", "��ʼ����");
        fieldMap.put("END_DATE", "��ֹ����");
        fieldMap.put("RENT_PERSON", "ǩԼ��λ");
        fieldMap.put("TENANCY", "����");
        fieldMap.put("YEAR_RENTAL", "�����");
        fieldMap.put("MONTH_REANTAL", "�����");
        fieldMap.put("REMARK", "��ע");
        fieldMap.put("ERROR", "������Ϣ");
        return fieldMap;
    }
}
