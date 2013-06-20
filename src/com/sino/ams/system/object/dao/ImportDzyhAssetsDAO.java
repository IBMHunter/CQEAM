package com.sino.ams.system.object.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.object.dto.ImportDzyhAssetsDTO;
import com.sino.ams.system.object.model.ImportDzyhAssetsModel;
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
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-5-27
 * Time: 10:09:23
 * To change this template use File | Settings | File Templates.
 */
public class ImportDzyhAssetsDAO  extends AMSBaseDAO {

    private SfUserDTO sfUser = null;
    /**
     * ���ܣ��ص㵼�� AMS_OBJECT_IMPORT ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsItemOnNetDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public ImportDzyhAssetsDAO(SfUserDTO userAccount, ImportDzyhAssetsDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        ImportDzyhAssetsDTO dtoPara = (ImportDzyhAssetsDTO)dtoParameter;
        super.sqlProducer = new ImportDzyhAssetsModel((SfUserDTO)userAccount, dtoPara);
    }

    /**
     * ���ܣ�ɾ���ӿڱ�����ݡ�
     * @throws com.sino.base.exception.SQLModelException
     */
     public void deleteImportModel() throws SQLModelException, QueryException, DataHandleException {
            ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
            SQLModel sqlModel = eoModel.deleteImportModel();
            DBOperator.updateRecord(sqlModel, conn);
    }


    /**
     * ���ܣ����ӿڱ����ݵ���Ч�ԡ�
     */
    public void doVerifyData(DTOSet dtoSet) throws SQLModelException, QueryException, ContainerException {
        if (dtoSet != null && dtoSet.getSize() > 0) {
            for (int i = 0; i < dtoSet.getSize(); i++) {
                ImportDzyhAssetsDTO dzyhDTO = (ImportDzyhAssetsDTO) dtoSet.getDTO(i);
                if (StrUtil.isEmpty(dzyhDTO.getCompanyCode())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "��˾���벻��Ϊ��");
                } else if (StrUtil.isEmpty(dzyhDTO.getBarcode())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "��ֵ�׺��ʲ���ǩ�Ų���Ϊ��");
                } else if (StrUtil.isEmpty(dzyhDTO.getItemName())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "�ʲ����Ʋ���Ϊ��");
                } else if (StrUtil.isEmpty(dzyhDTO.getItemSpec())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "����ͺŲ���Ϊ��");
                } else if (StrUtil.isEmpty(dzyhDTO.getItemUnit())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "��λ����Ϊ��");
                } else if (StrUtil.isEmpty(dzyhDTO.getSpecialityDept())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "ʵ�ﲿ�Ŵ��벻��Ϊ��");
                } else if (StrUtil.isEmpty(dzyhDTO.getSpecialityUser())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "רҵ�����˱�Ų���Ϊ��");
                } else if (StrUtil.isEmpty(dzyhDTO.getAddress())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "�ص㲻��Ϊ��");
                }
//                else if (StrUtil.isEmpty(lastingDTO.getResponsibilityDept())) {
//                    insertImprotErrorData(lastingDTO.getBarcode(), "���β��Ų���Ϊ��");
//                }
                else if (StrUtil.isEmpty(dzyhDTO.getResponsibilityUser())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "���β����ʲ�����Ա��Ų���Ϊ��");
                } else if (!validateOU(dzyhDTO.getCompanyCode())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "���û���Ӧ�Ĺ�˾���벻��ȷ");
                } else if (!validateBarcodeOu(dzyhDTO.getBarcode(), dzyhDTO.getCompanyCode())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "�������Ӧ��˾����ȷ");
                } else if (!validateSameBarcode(dzyhDTO.getBarcode())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "EXCEL�������ظ�");
                } else if (!validateSameEiiBarcode(dzyhDTO.getBarcode())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "������ʵ��ϵͳ���Ѵ���");
                } else if (!validateNewResDept(dzyhDTO.getSpecialityDept())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "ʵ�ﲿ�Ŵ��벻����");
                } else if (!validateNewEmployeeNum(dzyhDTO.getSpecialityUser())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "��˾רҵ�����˱�Ų�����");
                }
//                else if (!validateNewResDept(lastingDTO.getResponsibilityDept())) {
//                    insertImprotErrorData(lastingDTO.getBarcode(), "���β��Ŵ��벻����");
//                }
                else if (!validateNewEmployeeNum(dzyhDTO.getResponsibilityUser())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "���β����ʲ�����Ա��Ų�����");
                } else if (!validateEmployee(dzyhDTO.getSpecialityDept(), dzyhDTO.getSpecialityUser())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "ʵ�ﲿ�ź�רҵ�����˱�Ų�һ��");
                }
//                else if (!validateEmployee(lastingDTO.getResponsibilityDept(), lastingDTO.getResponsibilityUser())) {
//                    insertImprotErrorData(lastingDTO.getBarcode(), "���β��ź������˱�Ų�һ��");
//                }
                else if (!validateStartDate(dzyhDTO.getDzyhStartDate()) && !StrUtil.isEmpty(dzyhDTO.getDzyhStartDate())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "�������ڸ�ʽ����ȷ");
                } else if (!validateTenancy(dzyhDTO.getPrice()) && !StrUtil.isEmpty(dzyhDTO.getPrice())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "�ɱ���������������");
                //ģ��͵����û��IS_TD�ֶ�
/*                } else if (!validateTD(dzyhDTO.getTd()) && !StrUtil.isEmpty(dzyhDTO.getTd())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "TD������д����");*/
                } else if (!validateBarcode(dzyhDTO.getBarcode())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "�ʲ���ǩ�Ŵ���Сд��ĸ");
                } else if (!validateBarcodeLength(dzyhDTO.getBarcode())) {
                    insertImprotErrorData(dzyhDTO.getBarcode(), "�������Ϊ13λ");
                }
            }
        }
    }

    private String getNextSysDistributeCode(Connection conn) throws SQLException {
        SeqProducer seqProducer = new SeqProducer(conn);
		return String.valueOf( seqProducer.getStrNextSeq("ETS_SYSITEM_DISTRIBUTE") );
    }

    public boolean validateBarcodeLength(String barCode) {
        boolean isTrue = true;
        int length = barCode.length();
        if (length != 13) {
            isTrue = false;
        }
        return isTrue;
    }

    public boolean validateBarcode(String barCode) {
        boolean isUpper = barCode.toUpperCase().equals(barCode);
        return isUpper;
    }

    /**
     * ���ܣ�����ETS_SYSITEM_DISTRIBUTE�豸����Ȩ�ޱ�
     */
     public void insertDistribute(String itemCode, Connection conn) throws DataHandleException, SQLException, SQLModelException, QueryException, ContainerException {
        try {
            ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
            SQLModel sqlModel = eoModel.findOu();
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            RowSet rs = simpleQuery.getSearchResult();
            for (int i=0; i<rs.getSize(); i++) {
                String distributeCode = getNextSysDistributeCode(conn);
                String orgId = StrUtil.nullToString(rs.getRow(i).getValue("ORGANIZATION_ID"));
                DBOperator.updateRecord(eoModel.insertDistribute(distributeCode, itemCode, orgId), conn);
            }
            conn.commit();
        }  catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            e.printStackTrace();
            throw e;
        } catch (DataHandleException e) {
            try {
                conn.rollback();
                } catch (SQLException e1) {
                     Logger.logError(e1);
                }
            Logger.logError(e);
            throw e;
        }
    }

    /**
     * ���ܣ���ETS_SYSTEM_ITEM���Ҳ�����Ӧ��ITEM_CODE,����ITEM_CODE
     */
     public void insertSystemItem(String newItemCode, String itemName, String itemSpce, String itemUnit) throws DataHandleException, SQLException {
        try {
            ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
            SQLModel sqlModel = eoModel.insertSystemItem(newItemCode, itemName, itemSpce, itemUnit);
            DBOperator.updateRecord(sqlModel, conn);
            conn.commit();
        }  catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            e.printStackTrace();
            throw e;
        } catch (DataHandleException e) {
            try {
                conn.rollback();
                } catch (SQLException e1) {
                     Logger.logError(e1);
                }
            Logger.logError(e);
            throw e;
        }
    }

    /**
     * ���ܣ�У��TD����
     */
     public boolean validateTD(String td){
        boolean isTrue = false;
        if (td.equals("Y") || td.equals("N")) {
            isTrue = true;
        }
        return isTrue;
    }

    /**
     * ���ܣ�У�鵱ǰ�û�������ֵ�׺��ʲ���Ȩ��
     * @throws SQLModelException
     */
     public boolean validateOU(String companyCode) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
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
        ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateEmployee(deptCode, employeeNumber);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_DZYH_ASSETS_IMPORT��BARCODE�Ƿ����ڱ���˾BARCODE
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
     * ���ܣ�У��AMS_DZYH_ASSETS_IMPORT��EMPLOYEE_NUMBER�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateNewEmployeeNum(String newEmployeeNum) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateNewEmployeeNum(newEmployeeNum);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_DZYH_ASSETS_IMPORT��NEW_RESPONSIBILITY_DEPT�Ƿ���Ч
     * @throws SQLModelException
     */
     public boolean validateNewResDept(String newResDept) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
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
            ImportDzyhAssetsModel onNetModel = (ImportDzyhAssetsModel) sqlProducer;
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
        ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateSameEiiBarcode(barcode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = false;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У��AMS_DZYH_ASSETS_IMPORT��BARCODE�����ظ�
     * @throws SQLModelException
     */
     public boolean validateSameBarcode(String barcode) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode=true;
        ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
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
     * ���ܣ�У����AMS_DZYH_ASSETS_IMPORT�Ƿ���ڵ������
     * @throws SQLModelException
     */
     public boolean importHasError() throws SQLModelException, QueryException {
        boolean hasError=false;
            ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
            SQLModel sqlModel = eoModel.hasErrorModel();
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if(simpleQuery.hasResult()){
               hasError = true;
            }
        return hasError;
    }

    /**
     * ���ܣ���AMS_DZYH_ASSETS_IMPORT�л�ȡ����ɹ�������
     * @throws QueryException
     */
       public DTOSet getImport() throws QueryException, SQLModelException {
            ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
            SimpleQuery sq = new SimpleQuery(eoModel.getQueryImportModel(), conn);
            sq.setDTOClassName(ImportDzyhAssetsDTO.class.getName());
            sq.executeQuery();
            return sq.getDTOSet();
        }

    /**
     * ���ܣ�ͨ��NEW_ITEM_NAME,NEW_ITEM_SPECȡ��ITEM_CODE
     * @throws SQLModelException
     */
     public String getItemCode(String itemName, String itemSpce) throws SQLModelException, QueryException, ContainerException {
        String itemCode = "";
        ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
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
     * ���ܣ�ͨ��NEW_EMPLOYEE_NUMBERȡ��EMPLOYEE_ID
     * @throws SQLModelException
     */
     public String getEmployeeId(String employeeNumber) throws SQLModelException, QueryException, ContainerException {
        String employeeId = "";
        ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
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
        ImportDzyhAssetsModel eoModel = (ImportDzyhAssetsModel) sqlProducer;
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
        ImportDzyhAssetsModel modelProducer = (ImportDzyhAssetsModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getImportErrorModel();
        String reportTitle = "��ֵ�׺��ʲ����������Ϣ";
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
        fieldMap.put("BARCODE", "��ֵ�׺��ʲ���ǩ��");
        fieldMap.put("ITEM_NAME", "�ʲ�����");
        fieldMap.put("ITEM_SPEC", "����ͺ�");
        fieldMap.put("ITEM_UNIT", "��λ");
        fieldMap.put("SPECIALITY_DEPT", "ʵ�ﲿ�Ŵ���");
        fieldMap.put("SPECIALITY_USER", "רҵ�����˱��");
        fieldMap.put("ADDRESS", "�ص�");
        fieldMap.put("RESPONSIBILITY_DEPT", "���β��Ŵ���");
        fieldMap.put("RESPONSIBILITY_USER", "���β��Ź���Ա���");
        fieldMap.put("MAINTAIN_USER", "ʹ��������");
        fieldMap.put("PRICE", "�ɱ�");
        fieldMap.put("IS_TD", "�Ƿ�TD");
        fieldMap.put("DZYH_START_DATE", "��������");
        fieldMap.put("REMARK", "��ע");
        fieldMap.put("ERROR", "������Ϣ");
        return fieldMap;
    }
}
