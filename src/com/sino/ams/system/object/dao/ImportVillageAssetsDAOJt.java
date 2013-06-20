package com.sino.ams.system.object.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.onnet.dto.AmsItemOnNetDTO;
import com.sino.ams.system.object.dto.ImportVillageAssetsDTOJt;
import com.sino.ams.system.object.dto.LastingAssetsDTOJt;
import com.sino.ams.system.object.model.ImportLastingAssetsModelJt;
import com.sino.ams.system.object.model.ImportVillageAssetsModelJt;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImportVillageAssetsDAOJt extends AMSBaseDAO {

    private final static String[] fieldNames = {
            "companyCode", "barcode", "itemName", "itemSpec",
            "employeeNumber", "employeeName", "workorderObjectCode",
            "workorderObjectName", "financeProp", "itemQty", "actualQty",
            "equipmentPerformance", "contentCode", "contentName",
            "specialityDept", "maintainUser", "price", "villageStartDate",
            "tfDepreciation", "tfNetAssetValue", "tfDeprnCost", "projectid",
            "projectName", "constructStatus", "isShare", "manufacturerId", "remark"
    };

    public ImportVillageAssetsDAOJt(SfUserDTO userAccount, AmsItemOnNetDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        ImportVillageAssetsDTOJt dtoPara = (ImportVillageAssetsDTOJt) dtoParameter;
        super.sqlProducer = new ImportVillageAssetsModelJt((SfUserDTO) userAccount, dtoPara);
    }

    public void deleteImportModel() throws SQLModelException, QueryException, DataHandleException {
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
                ImportVillageAssetsModelJt modelProducer = (ImportVillageAssetsModelJt) sqlProducer;
                SQLModel sqlModel = modelProducer.insertImportModel();
                pstmt = conn.prepareCall(sqlModel.getSqlStr());
                int fieldCount = fieldNames.length;
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    ImportVillageAssetsDTOJt eoDTO = (ImportVillageAssetsDTOJt) dtoSet.getDTO(i);
                    for (int j = 0; j < fieldCount; j++) {
                        String fieldName = fieldNames[j];
                        String fieldValue = StrUtil.nullToString(ReflectionUtil.getProperty(eoDTO, fieldName));
                        pstmt.setString(j + 1, fieldValue);
                    }
                    pstmt.setInt(fieldCount + 1, userAccount.getUserId());
                    pstmt.addBatch();
                    if (i % 100 == 0 && i > 0) {
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
            String callStr = "{CALL dbo.CTZC_CHECK_DATA(?, ?)}";
            cs = conn.prepareCall(callStr);
            cs.setInt(1, userAccount.getUserId());
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
            String callStr = "{CALL dbo.CTZC_TRANS_DATA(?, ?)}";
            cs = conn.prepareCall(callStr);
            cs.setInt(1, userAccount.getUserId());
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.execute();
            errorMsg = cs.getString(2);
            
            if( StrUtil.isEmpty( errorMsg )){
            	operateResult = true;
            }else{
            	operateResult = false;
            }
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
        ImportVillageAssetsModelJt modelProducer = (ImportVillageAssetsModelJt) sqlProducer;
        SQLModel sqlModel = modelProducer.getImportErrorModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        return simpleQuery.getSearchResult();
    }


    public void doVerifyData(DTOSet dtoSet) throws SQLModelException, QueryException, ContainerException {
        if (dtoSet != null && dtoSet.getSize() > 0) {
            for (int i = 0; i < dtoSet.getSize(); i++) {
                ImportVillageAssetsDTOJt villageDTO = (ImportVillageAssetsDTOJt) dtoSet.getDTO(i);
                if (StrUtil.isEmpty(villageDTO.getCompanyCode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "��˾���벻��Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getBarcode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ���ǩ�Ų���Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getItemName())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ����Ʋ���Ϊ��");
                }
//                else if (StrUtil.isEmpty(villageDTO.getItemSpec())) {
//                    insertImprotErrorData(villageDTO.getBarcode(), "����ͺŲ���Ϊ��");
//                }
                else if (StrUtil.isEmpty(villageDTO.getEmployeeNumber())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�����˱�Ų���Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getEmployeeName())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "��������������Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getWorkorderObjectCode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ��ص�������ϱ��벻��Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getFinanceProp())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "ͨ���ʲ����Ͳ���Ϊ��");
                } else if (StrUtil.isEmpty(villageDTO.getContentCode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ���������ϲ���Ϊ��");
                }
//                else if (StrUtil.isEmpty(villageDTO.getConstructStatus())) {
//                    insertImprotErrorData(villageDTO.getBarcode(), "�Ƿ񹲽��豸����Ϊ��");
//                } else if (StrUtil.isEmpty(villageDTO.getShare())) {
//                    insertImprotErrorData(villageDTO.getBarcode(), "�Ƿ����豸����Ϊ��");
//                }
//                else if (StrUtil.isEmpty(villageDTO.getManufacturerId())) {
//                    insertImprotErrorData(villageDTO.getBarcode(), "���̴��벻��Ϊ��");
//                }
                else if (!validateOU(villageDTO.getCompanyCode())) {
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
                } else if (!validateBarcodeLength(villageDTO.getBarcode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�������Ϊ13λ");
                } else if (!validateBarcode(villageDTO.getBarcode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ���ǩ�Ŵ���Сд��ĸ");
                } else if (!validateBarcode2(villageDTO.getBarcode())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�ʲ���ǩ���в����ڡ�TF��");
                } else if (!validateFinanceProp(villageDTO.getFinanceProp())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "ͨ���ʲ����ͱ���Ϊ��CT����QD��");
                } else if (!validateTenancy(villageDTO.getItemQty()) && !StrUtil.isEmpty(villageDTO.getItemQty())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "������������������");
                } else if (!validateTenancy(villageDTO.getActualQty()) && !StrUtil.isEmpty(villageDTO.getActualQty())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "������·��Դ����������������");
                } else if (!validateProject(villageDTO.getProjectid()) && !StrUtil.isEmpty(villageDTO.getProjectid())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "��Ŀ��Ų�����");
                } else if (!validateManufacturer(villageDTO.getManufacturerId()) && !StrUtil.isEmpty(villageDTO.getManufacturerId())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "���̲�����");
                } else if (!validateContractStatus(villageDTO.getConstructStatus()) && !StrUtil.isEmpty(villageDTO.getConstructStatus())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�Ƿ񹲽��豸������");
                } else if (!validateShareStatus(villageDTO.getShare()) && !StrUtil.isEmpty(villageDTO.getShare())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�Ƿ����豸������");
                } else if (!validateStartDate(villageDTO.getVillageStartDate()) && !StrUtil.isEmpty(villageDTO.getVillageStartDate())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "�������ڸ�ʽ����ȷ");
                } else if (!validateTenancy(villageDTO.getPrice()) && !StrUtil.isEmpty(villageDTO.getPrice())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "ͨ���ʲ�ԭֵ��������������");
                } else if (!validateTenancy(villageDTO.getTfNetAssetValue()) && !StrUtil.isEmpty(villageDTO.getTfNetAssetValue())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "ͨ���ʲ���ֵ��������������");
                } else if (!validateTenancy(villageDTO.getTfDeprnCost()) && !StrUtil.isEmpty(villageDTO.getTfDeprnCost())) {
                    insertImprotErrorData(villageDTO.getBarcode(), "ͨ���ʲ������������������");
                }
            }
        }
    }

    public boolean validateShareStatus(String NewShareStatus) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateNewShareStatus(NewShareStatus);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public boolean validateContractStatus(String NewContractStatus) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateNewContractStatus(NewContractStatus);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public boolean validateManufacturer(String manufacturerId) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateManufacturer(manufacturerId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public boolean validateProject(String projectid) throws SQLModelException, QueryException {
        boolean hasBarcode = false;
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.validateProject(projectid);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasBarcode = true;
        }
        return hasBarcode;
    }

    public boolean validateFinanceProp(String financeProp) {
        boolean isTrue = true;
        if (!financeProp.equals("CT") && !financeProp.equals("QD")) {
            isTrue = false;
        }
        return isTrue;
    }

    public boolean validateBarcode2(String barCode) {
        boolean isTrue = true;
        int count = barCode.indexOf("TF");
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
            ImportVillageAssetsModelJt onNetModel = (ImportVillageAssetsModelJt) sqlProducer;
            SQLModel sqlModel = onNetModel.insertImprotErrorData(barcode, codeError);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (DataHandleException ex) {
            ex.printLog();
        }
    }

    public boolean validateSameEiiBarcode(String barcode) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode = true;
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.hasErrorModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasError = true;
        }
        return hasError;
    }

    public DTOSet getImport() throws QueryException, SQLModelException {
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
        SimpleQuery sq = new SimpleQuery(eoModel.getQueryImportModel(), conn);
        sq.setDTOClassName(ImportVillageAssetsDTOJt.class.getName());
        sq.executeQuery();
        return sq.getDTOSet();
    }

    public String getItemCode(String itemName, String itemSpce) throws SQLModelException, QueryException, ContainerException {
        String itemCode = "";
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
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

    public String getConstructStatusId(String constructStatusName) throws SQLModelException, QueryException, ContainerException {
        String constructStatusId = "";
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.getConstructStatusIdModel(constructStatusName);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            constructStatusId = StrUtil.nullToString(row.getValue("CODE"));
        }
        return constructStatusId;
    }

    public String getShareId(String shareName) throws SQLModelException, QueryException, ContainerException {
        String shareId = "";
        ImportVillageAssetsModelJt eoModel = (ImportVillageAssetsModelJt) sqlProducer;
        SQLModel sqlModel = eoModel.getShareIdModel(shareName);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            shareId = StrUtil.nullToString(row.getValue("CODE"));
        }
        return shareId;
    }

    public File getExportFile() throws DataTransException, SQLModelException {
        ImportVillageAssetsModelJt modelProducer = (ImportVillageAssetsModelJt) sqlProducer;
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

    private Map getFieldMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("COMPANY_CODE", "��˾����");
        fieldMap.put("BARCODE", "�ʲ���ǩ��");
        fieldMap.put("ITEM_NAME", "�ʲ�����");
        fieldMap.put("ITEM_SPEC", "����ͺ�");
        fieldMap.put("EMPLOYEE_NUMBER", "�����˱��");
        fieldMap.put("EMPLOYEE_NAME", "����������");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���ϱ���");
        fieldMap.put("WORKORDER_OBJECT_NAME", "�ʲ��ص�����");
        fieldMap.put("FINANCE_PROP", "ͨ���ʲ�����");
        fieldMap.put("ITEM_QTY", "����");
        fieldMap.put("ACTUAL_QTY", "������·��Դ��");
        fieldMap.put("EQUIPMENT_PERFORMANCE", "�豸����");
        fieldMap.put("CONTENT_CODE", "�ʲ����������");
        fieldMap.put("CONTENT_NAME", "�ʲ��������");
        fieldMap.put("SPECIALITY_DEPT", "ʵ�ﲿ�Ŵ���");
        fieldMap.put("MAINTAIN_USER", "ʹ��������");
        fieldMap.put("PRICE", "ͨ���ʲ�ԭֵ");
        fieldMap.put("VILLAGE_START_DATE", "�ʲ���������");
        fieldMap.put("TF_DEPRECIATION", "�۾�����");
        fieldMap.put("TF_NET_ASSET_VALUE", "ͨ���ʲ���ֵ");
        fieldMap.put("TF_DEPRN_COST", "ͨ���ʲ�����");
        fieldMap.put("PROJECTID", "��Ŀ���");
        fieldMap.put("PROJECT_NAME", "��Ŀ����");
        fieldMap.put("CONSTRUCT_STATUS", "�Ƿ񹲽��豸");
        fieldMap.put("IS_SHARE", "�Ƿ����豸");
        fieldMap.put("MANUFACTURER_ID", "����");
        fieldMap.put("REMARK", "��ע");
        fieldMap.put("ERROR", "������Ϣ");
        return fieldMap;
    }
}
