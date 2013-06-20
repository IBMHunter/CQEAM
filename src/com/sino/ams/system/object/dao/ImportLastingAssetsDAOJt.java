package com.sino.ams.system.object.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.onnet.dto.AmsItemOnNetDTO;
import com.sino.ams.system.object.dto.LastingAssetsDTOJt;
import com.sino.ams.system.object.model.ImportLastingAssetsModelJt;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-5-26
 * Time: 11:28:18
 * To change this template use File | Settings | File Templates.
 */
public class ImportLastingAssetsDAOJt extends AMSBaseDAO {

    private final static String[] fieldNames = {
            "companyCode", "barcode", "itemName", "itemSpec",
            "employeeNumber", "employeeName", "workorderObjectCode",
            "workorderObjectName", "itemQty", "manufacturerName",
            "equipmentPerformance", "contentCode", "contentName",
            "specialityDept", "maintainUser", "rentStartDate",
            "rentEndDate", "rentPerson", "contractNumber", "contractName",
            "tenancy", "yearRental", "monthReantal", "remark"
    };

    public ImportLastingAssetsDAOJt(SfUserDTO userAccount, AmsItemOnNetDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        LastingAssetsDTOJt dtoPara = (LastingAssetsDTOJt) dtoParameter;
        super.sqlProducer = new ImportLastingAssetsModelJt((SfUserDTO) userAccount, dtoPara);
    }

    public void deleteImportModel() throws DataHandleException {
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.deleteImportModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ����뵽�ӿڱ�
     *
     * @param dtoSet DTOSet
     * @return boolean
     * @throws DataHandleException
     * @throws SQLModelException
     */
    public boolean itemImportData(DTOSet dtoSet) throws DataHandleException {
        boolean operateResult = false;
        boolean autoCommit = true;
        PreparedStatement pstmt = null;
        try {
            if (dtoSet != null && dtoSet.getSize() > 0) {
                autoCommit = conn.getAutoCommit();
                conn.setAutoCommit(false);
                ImportLastingAssetsModelJt modelProducer = (ImportLastingAssetsModelJt) sqlProducer;
                SQLModel sqlModel = modelProducer.insertImportModel();
                pstmt = conn.prepareCall(sqlModel.getSqlStr());
                int fieldCount = fieldNames.length;
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    LastingAssetsDTOJt eoDTO = (LastingAssetsDTOJt) dtoSet.getDTO(i);
                    String fieldName = null;
                    String fieldValue = null;
                    for(int j = 0; j < fieldCount ; j++){
                        fieldName = fieldNames[j];
                        fieldValue = StrUtil.nullToString(ReflectionUtil.getProperty(eoDTO, fieldName));  
                    	if( j == 8 ){
                    		pstmt.setInt( j + 1, StrUtil.strToInt( fieldValue ) );
                    	}else{
                    		pstmt.setString(j + 1, fieldValue);
                    	}
                        	
                    }
//                    fieldName = fieldNames[ fieldCount-1 ];
//                    fieldValue = StrUtil.nullToString(ReflectionUtil.getProperty(eoDTO, fieldName)); 
//                    pstmt.setInt( fieldCount, StrUtil.strToInt( fieldValue ) );
                    
                    pstmt.setInt(fieldCount + 1,  userAccount.getUserId() );
                    pstmt.addBatch();
                    if(i % 100 == 0 && i > 0){
                        pstmt.executeBatch();
                    }
                }
                pstmt.executeBatch();
            }
            operateResult = true;
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            try {
                DBManager.closeDBStatement(pstmt);
                if (operateResult) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
        return operateResult;
    }

    public String doVerifyData() throws DataHandleException {
        CallableStatement cs = null;
        String errorMsg = "";
        try {
            String callStr = "{CALL dbo.ARIP_CHECK_DATA(?, ?)}";
            cs = conn.prepareCall(callStr);
            cs.setInt(1, userAccount.getUserId() );
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            errorMsg = cs.getString(2);
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            DBManager.closeDBStatement(cs);
        }
        return errorMsg;
    }

    /**
     * ���ܣ����뵽��ets_item_info�ύ����
     *
     * @throws DataHandleException
     */
    public String submitOrderDtl() throws DataHandleException {
        CallableStatement cs = null;
        String errorMsg = "";
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String callStr = "{CALL dbo.ARIP_TRANS_DATA(?, ?)}";
            cs = conn.prepareCall(callStr);
            cs.setInt(1,  userAccount.getUserId() );
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            errorMsg = cs.getString(2);
            operateResult = true;
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            try {
                DBManager.closeDBStatement(cs);
                if (operateResult) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
        return errorMsg;
    }

    public RowSet getImportErrors() throws QueryException {
        ImportLastingAssetsModelJt modelProducer = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = modelProducer.getImportErrorModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        return simpleQuery.getSearchResult();
    }

    public void doVerifyData(DTOSet dtoSet) throws SQLModelException, QueryException, ContainerException {
        if (dtoSet != null && dtoSet.getSize() > 0) {
            for (int i = 0; i < dtoSet.getSize(); i++) {
                LastingAssetsDTOJt lastingDTO = (LastingAssetsDTOJt) dtoSet.getDTO(i);
                if (StrUtil.isEmpty(lastingDTO.getCompanyCode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "��˾���벻��Ϊ��");
                } else if (StrUtil.isEmpty(lastingDTO.getBarcode())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�����ʲ���ǩ�Ų���Ϊ��");
                } else if (StrUtil.isEmpty(lastingDTO.getItemName())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "�ʲ����Ʋ���Ϊ��");
                }
//                else if (StrUtil.isEmpty(lastingDTO.getItemSpec())) {
//                    insertImprotErrorData(lastingDTO.getBarcode(), "����ͺŲ���Ϊ��");
//                }
                else if (StrUtil.isEmpty(lastingDTO.getEmployeeNumber())) {
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
                } else if (!validateTenancy( String.valueOf( lastingDTO.getItemQty() ) ) && !StrUtil.isEmpty(lastingDTO.getItemQty())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "������������������");
                } else if (!validateManufacturer(lastingDTO.getManufacturerName()) && !StrUtil.isEmpty(lastingDTO.getManufacturerName())) {
                    insertImprotErrorData(lastingDTO.getBarcode(), "���̲�����");
                }
            }
        }
    }

    public boolean validateManufacturer(String manufacturerName) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateManufacturer(manufacturerName);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
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

    public boolean validateOU(String companyCode) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateOU(companyCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public boolean validateTenancy(String tenancy) throws SQLModelException, QueryException {
        boolean isTrue = true;
        if (!StrUtil.isNumber(tenancy)) {
            isTrue = false;
        }
        return isTrue;
    }

    public boolean validateStartDate(String date) throws SQLModelException, QueryException {
        boolean isTrue = true;
        if (!CalendarUtil.isValidDate(date)) {
            isTrue = false;
        }
        return isTrue;
    }

    public boolean validateEmployee(String deptCode, String employeeNumber) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateEmployee(deptCode, employeeNumber);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public boolean validateBarcodeOu(String barcode, String companyCode) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        String subBarcode = barcode.substring(0, 4);
        if (subBarcode.equals(companyCode)) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public boolean validateItemNS(String itemName, String itemSpec) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateItemNS(itemName, itemSpec);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public boolean validateNewEmployeeNum(String newEmployeeNum) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateNewEmployeeNum(newEmployeeNum);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public boolean validateObjectCode(String newObjectCode) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateObjectCode(newObjectCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public boolean validateContentCode(String contentCode) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateContentCode(contentCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public boolean validateNewResDept(String newResDept) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateNewResDept(newResDept);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public void insertImprotErrorData(String barcode, String codeError) throws SQLModelException {
        try {
            ImportLastingAssetsModelJt onNetModel = (ImportLastingAssetsModelJt) sqlProducer;
            SQLModel sqlModel = onNetModel.insertImprotErrorData(barcode, codeError);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (DataHandleException ex) {
            ex.printLog();
        }
    }

    public boolean validateSameRentBarcode(String barcode) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode = true;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateSameRentBarcode(barcode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = false;
        }
        return hasBarcode;
    }

    public boolean validateSameEiiBarcode(String barcode) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode = true;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateSameEiiBarcode(barcode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = false;
        }
        return hasBarcode;
    }

    public boolean validateSameBarcode(String barcode) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode = true;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
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

    public boolean importHasError() throws SQLModelException, QueryException {
        boolean hasError = false;
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.hasErrorModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasError = true;
        }
        return hasError;
    }

    public DTOSet getImport() throws QueryException, SQLModelException {
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SimpleQuery sq = new SimpleQuery(eoModel.getQueryImportModel(), conn);
        sq.setDTOClassName(LastingAssetsDTOJt.class.getName());
        sq.executeQuery();
        return sq.getDTOSet();
    }

    public String getItemCode(String itemName, String itemSpce) throws SQLModelException, QueryException, ContainerException {
        String itemCode = "";
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
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

    public String getAddressId(String objectCode) throws SQLModelException, QueryException, ContainerException {
        String addressId = "";
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
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

    public String getEmployeeId(String employeeNumber) throws SQLModelException, QueryException, ContainerException {
        String employeeId = "";
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
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

    public String getDeptCode(String employeeNumber) throws SQLModelException, QueryException, ContainerException {
        String employeeId = "";
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
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


    public String getManufacturerId(String manufacturerName) throws SQLModelException, QueryException, ContainerException {
        String manufacturerId = "";
        ImportLastingAssetsModelJt eoModel = (ImportLastingAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.getManufacturerIdModel(manufacturerName);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            manufacturerId = StrUtil.nullToString(row.getValue("MANUFACTURER_ID"));
        }
        return manufacturerId;
    }


    public File getExportFile() throws DataTransException, SQLModelException {
        ImportLastingAssetsModelJt modelProducer = (ImportLastingAssetsModelJt) sqlProducer;
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

    private Map getFieldMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("COMPANY_CODE", "��˾����");
        fieldMap.put("BARCODE", "�����ʲ���ǩ��");
        fieldMap.put("ITEM_NAME", "�ʲ�����");
        fieldMap.put("ITEM_SPEC", "����ͺ�");
        fieldMap.put("EMPLOYEE_NUMBER", "�����˱��");
        fieldMap.put("EMPLOYEE_NAME", "����������");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���ϱ���");
        fieldMap.put("ITEM_QTY", "����");
        fieldMap.put("MANUFACTURER_ID", "����");
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


    /**
     * ���ܣ����뵽��ets_item_info�ύ����
     *
     * @param dtoSet      DTOSet
     * @return boolean
     * @throws DataHandleException
     */
    public boolean submitOrderDtl(DTOSet dtoSet) throws DataHandleException {
        boolean operatorResult = false;
        try {
            if (dtoSet != null && dtoSet.getSize() > 0) {
                SQLModel sqlModel = new SQLModel();
                ImportLastingAssetsModelJt modelProducer = new ImportLastingAssetsModelJt(userAccount, null);

                for (int i = 0; i < dtoSet.getSize(); i++) {
                    LastingAssetsDTOJt eoDTO = (LastingAssetsDTOJt) dtoSet.getDTO(i);
                    ImportLastingAssetsDAOJt ImObDAO = new ImportLastingAssetsDAOJt(userAccount, null, conn);
                    if (!StrUtil.isEmpty(eoDTO.getItemName()) && !StrUtil.isEmpty(eoDTO.getItemSpec())) {//��ȡITEM_CODE
                        String itemCode = ImObDAO.getItemCode(eoDTO.getItemName(), eoDTO.getItemSpec());
                        eoDTO.setItemCode(itemCode);
                    }
                    if (!StrUtil.isEmpty(eoDTO.getWorkorderObjectCode())) {//��ȡADDRESS_ID
                        String addressId = ImObDAO.getAddressId(eoDTO.getWorkorderObjectCode());
                        eoDTO.setAddressId(addressId);
                    }
                    if (!StrUtil.isEmpty(eoDTO.getEmployeeNumber())) {//��ȡEMPLOYEE_ID
                        String employeeId = ImObDAO.getEmployeeId(eoDTO.getEmployeeNumber());
                        eoDTO.setEmployeeId(employeeId);
                    }

                    //��ȡ����ID
                    if (!StrUtil.isEmpty(eoDTO.getManufacturerName())) {
                        String manufacturerId = ImObDAO.getManufacturerId(eoDTO.getManufacturerName());
                        eoDTO.setManufacturerId(manufacturerId);
                    }

                    //��ȡ���β��ű��
                    if (!StrUtil.isEmpty(eoDTO.getEmployeeNumber())) {
                        String deptCode = ImObDAO.getDeptCode(eoDTO.getEmployeeNumber());
                        eoDTO.setResponsibilityDept(deptCode);
                    }
                    eoDTO.setRentId(getNextRentId(conn));
                    eoDTO.setSystemid(getNextSystemId(conn));
                    modelProducer.setDTOParameter(eoDTO);

                    sqlModel = modelProducer.getDataCreateModel();
                    DBOperator.updateRecord(sqlModel, conn);
                    sqlModel = modelProducer.getRENTCreateModel();
                    DBOperator.updateRecord(sqlModel, conn);
                }
            }
            operatorResult = true;
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        } catch (ContainerException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (QueryException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        }
        return operatorResult;
    }

    private String getNextRentId(Connection conn) throws SQLException {
        SeqProducer seqProducer = new SeqProducer(conn);
        return seqProducer.getGUID();
    }

    private String getNextSystemId(Connection conn) throws SQLException {
        SeqProducer seqProducer = new SeqProducer(conn);
        return seqProducer.getGUID();
	}
}