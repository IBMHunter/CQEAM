package com.sino.ams.system.object.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.object.dto.ImportVillageAssetsDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-5-23
 * Time: 19:43:50
 * To change this template use File | Settings | File Templates.
 */
public class ImportVillageAssetsModel extends AMSSQLProducer {
    private SfUserDTO sfUser = null;

    public ImportVillageAssetsModel(SfUserDTO userAccount, ImportVillageAssetsDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }


    /**
     * ���ܣ�ɾ���ӿڱ�����ݡ�
     * @return SQLModel �������ݸ�����SQLModel
     * @throws com.sino.base.exception.SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel deleteImportModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " AMS_VILLAGE_ASSETS_IMPORT ALAI"
                + " WHERE"
                + " ALAI.USER_ID = ?";
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����뵽�ӿڱ�
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel insertImportModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        ImportVillageAssetsDTO eoDTO = (ImportVillageAssetsDTO) dtoParameter;
        String sqlStr = "INSERT INTO AMS_VILLAGE_ASSETS_IMPORT\n" +
                "  (COMPANY_CODE,\n" +
                "   BARCODE,\n" +
                "   ITEM_NAME,\n" +
                "   ITEM_SPEC,\n" +
                "   EMPLOYEE_NUMBER,\n" +
                "   EMPLOYEE_NAME,\n" +
                "   WORKORDER_OBJECT_CODE,\n" +
                "   EQUIPMENT_PERFORMANCE,\n" +
                "   CONTENT_CODE,\n" +
                "   CONTENT_NAME,\n" +
                "   WORKORDER_OBJECT_NAME,\n" +
                "   SPECIALITY_DEPT,\n" +
                "   MAINTAIN_USER,\n" +
                "   MAINTAIN_DEPT,\n" +
                "   PRICE,\n" +
                "   DEPRN_COST,\n" +
                "   SCRAP_VALUE,\n" +
                "   VILLAGE_START_DATE,\n" +
                "   USE_YEAR,\n" +
                "   REMAIN_MONTH,\n" +
                "   REMARK,\n" +
                "   USER_ID)\n" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        sqlArgs.add(eoDTO.getCompanyCode());
        sqlArgs.add(eoDTO.getBarcode());
        sqlArgs.add(eoDTO.getItemName());
        sqlArgs.add(eoDTO.getItemSpec());
        sqlArgs.add(eoDTO.getEmployeeNumber());
        sqlArgs.add(eoDTO.getEmployeeName());
        sqlArgs.add(eoDTO.getWorkorderObjectCode());
        sqlArgs.add(eoDTO.getEquipmentPerformance());
        sqlArgs.add(eoDTO.getContentCode());
        sqlArgs.add(eoDTO.getContentName());
        sqlArgs.add(eoDTO.getWorkorderObjectName());
        sqlArgs.add(eoDTO.getSpecialityDept());
        sqlArgs.add(eoDTO.getMaintainUser());
        sqlArgs.add(eoDTO.getMaintainDept());
        sqlArgs.add(eoDTO.getPrice());
        sqlArgs.add(eoDTO.getDeprnCost());
        sqlArgs.add(eoDTO.getScrapValue());
        sqlArgs.add(eoDTO.getVillageStartDate());
        sqlArgs.add(eoDTO.getUseYear());
        sqlArgs.add(eoDTO.getRemainMonth());
        sqlArgs.add(eoDTO.getRemark());
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��BARCODE�Ƿ���ETS_ITEM_INFO�д���SQL
     * @param barcode String
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel validateSameEiiBarcode(String barcode) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1 FROM ETS_ITEM_INFO EII WHERE EII.BARCODE = ?";
        sqlArgs.add(barcode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��BARCODE�Ƿ���ETS_ITEM_INFO�д���SQL
     * @param barcode String
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel validateSameBarcode(String barcode) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT COUNT(1) BAR_QTY\n" +
                "FROM   AMS_VILLAGE_ASSETS_IMPORT AVAI\n" +
                "WHERE  AVAI.BARCODE = ?\n" +
                "AND    AVAI.USER_ID = ?";
        sqlArgs.add(barcode);
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����ص���������¼�¼SQL
     * @param barcode    String
     * @param codeError   String
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel insertImprotErrorData(String barcode, String codeError) throws
            SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE"
                + " AMS_VILLAGE_ASSETS_IMPORT ALAI"
                + " SET"
                + " ALAI.ERROR = ?"
                + " WHERE"
                + " ALAI.BARCODE = ?";
        sqlArgs.add(codeError);
        sqlArgs.add(barcode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��NEW_ITEM_NAME��NEW_ITEM_SPEC�Ƿ���Ч
     * @param itemName String
     * @param itemSpec String
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel validateItemNS(String itemName, String itemSpec) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1\n" +
                         " FROM ETS_SYSTEM_ITEM ESI, \n" +
                              " ETS_SYSITEM_DISTRIBUTE ESD\n" +
                        " WHERE ESI.ITEM_CODE = ESD.ITEM_CODE \n" +
                          " AND ESI.ITEM_NAME = ? \n" +
                          " AND ESI.ITEM_SPEC = ? \n" +
                          " AND ESD.ORGANIZATION_ID = ?";
        sqlArgs.add(itemName);
        sqlArgs.add(itemSpec);
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��NEW_ITEM_NAME��NEW_ITEM_SPEC�Ƿ���Ч
     * @param newObjectCode String
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel validateObjectCode(String newObjectCode) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1 FROM ETS_OBJECT EO WHERE EO.WORKORDER_OBJECT_CODE = ? AND EO.ORGANIZATION_ID = ?";
        sqlArgs.add(newObjectCode);
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT���ʲ��������Ƿ���Ч
     * @param contentCode String
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel validateContentCode(String contentCode) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT COUNT(1) FROM FA_CATEGORIES@MIS_FA FC WHERE FC.SEGMENT1||'.'||FC.SEGMENT2||'.000' = ?";
        sqlArgs.add(contentCode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��NEW_EMPLOYEE_NUMBER�Ƿ���Ч
     * @param newResDept String
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel validateNewResDept(String newResDept) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1 FROM AMS_MIS_DEPT AMD WHERE AMD.DEPT_CODE = ?";
        sqlArgs.add(newResDept);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�У��AMS_VILLAGE_ASSETS_IMPORT��NEW_EMPLOYEE_NUMBER�Ƿ���Ч
     * @param newEmployeeNum String
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel validateNewEmployeeNum(String newEmployeeNum) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1 FROM AMS_MIS_EMPLOYEE AME WHERE AME.EMPLOYEE_NUMBER = ?";
        sqlArgs.add(newEmployeeNum);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ��鿴AMS_VILLAGE_ASSETS_IMPORT�����Ƿ��д����¼
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel hasErrorModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        ImportVillageAssetsDTO eoDTO = (ImportVillageAssetsDTO) dtoParameter;
        String sqlStr = "SELECT 1 FROM AMS_VILLAGE_ASSETS_IMPORT AVAL WHERE  " + SyBaseSQLUtil.isNotNull("AVAL.ERROR") + "  AND AVAL.USER_ID = ?";
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ��鿴AMS_VILLAGE_ASSETS_IMPORT���д����¼
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getImportErrorModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT AVAI.COMPANY_CODE,\n" +
                "       AVAI.BARCODE,\n" +
                "       AVAI.ITEM_NAME,\n" +
                "       AVAI.ITEM_SPEC,\n" +
                "       AVAI.EMPLOYEE_NUMBER,\n" +
                "       AVAI.EMPLOYEE_NAME,\n" +
                "       AVAI.WORKORDER_OBJECT_CODE,\n" +
                "       AVAI.EQUIPMENT_PERFORMANCE,\n" +
                "       AVAI.CONTENT_CODE,\n" +
                "       AVAI.CONTENT_NAME,\n" +
                "       AVAI.WORKORDER_OBJECT_NAME,\n" +
                "       AVAI.SPECIALITY_DEPT,\n" +
                "       AVAI.MAINTAIN_USER,\n" +
                "       AVAI.MAINTAIN_DEPT,\n" +
                "       AVAI.PRICE,\n" +
                "       AVAI.DEPRN_COST,\n" +
                "       AVAI.SCRAP_VALUE,\n" +
                "       AVAI.VILLAGE_START_DATE,\n" +
                "       AVAI.USE_YEAR,\n" +
                "       AVAI.REMAIN_MONTH,\n" +
                "       AVAI.REMARK,\n" +
                "       AVAI.ERROR\n" +
                "FROM   AMS_VILLAGE_ASSETS_IMPORT AVAI\n" +
                "WHERE  AVAI.USER_ID = ?";
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ѯAMS_VILLAGE_ASSETS_IMPORT�����ص�����ȷ����
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getQueryImportModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT AVAI.COMPANY_CODE,\n" +
                "       AVAI.BARCODE,\n" +
                "       AVAI.ITEM_NAME,\n" +
                "       AVAI.ITEM_SPEC,\n" +
                "       AVAI.EMPLOYEE_NUMBER,\n" +
                "       AVAI.EMPLOYEE_NAME,\n" +
                "       AVAI.WORKORDER_OBJECT_CODE,\n" +
                "       AVAI.EQUIPMENT_PERFORMANCE,\n" +
                "       AVAI.CONTENT_CODE,\n" +
                "       AVAI.CONTENT_NAME,\n" +
                "       AVAI.WORKORDER_OBJECT_NAME,\n" +
                "       AVAI.SPECIALITY_DEPT,\n" +
                "       AVAI.MAINTAIN_USER,\n" +
                "       AVAI.MAINTAIN_DEPT,\n" +
                "       AVAI.PRICE,\n" +
                "       AVAI.DEPRN_COST,\n" +
                "       AVAI.SCRAP_VALUE,\n" +
                "       AVAI.VILLAGE_START_DATE START_DATE,\n" +
                "       AVAI.USE_YEAR,\n" +
                "       AVAI.REMAIN_MONTH,\n" +
                "       AVAI.REMARK\n" +
                "FROM   AMS_VILLAGE_ASSETS_IMPORT AVAI\n" +
                "WHERE  AVAI.ERROR " + SyBaseSQLUtil.isNullNoParam() + " \n" +
                "       AND AVAI.USER_ID = ?";
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�ͨ��NEW_ITEM_NAME,NEW_ITEM_SPECȡ��ITEM_CODE
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getItemCodeModel(String itemName, String itemSpec) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT ESI.ITEM_CODE FROM ETS_SYSTEM_ITEM ESI WHERE ESI.ITEM_NAME = ? AND ESI.ITEM_SPEC = ?";
        sqlArgs.add(itemName);
        sqlArgs.add(itemSpec);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�ͨ��NEW_OBJECT_CODEȡ��ADDRESS_ID
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getAddressIdModel(String objectCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT AOA.ADDRESS_ID " +
                         " FROM ETS_OBJECT EO, " +
                              " AMS_OBJECT_ADDRESS AOA " +
                        " WHERE EO.WORKORDER_OBJECT_NO = AOA.OBJECT_NO " +
                          " AND EO.WORKORDER_OBJECT_CODE = ?";
        sqlArgs.add(objectCode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�ͨ��NEW_EMPLOYEE_NUMBERȡ��EMPLOYEE_ID
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getEmployeeIdModel(String employeeNumber) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT AME.EMPLOYEE_ID FROM AMS_MIS_EMPLOYEE AME WHERE AME.EMPLOYEE_NUMBER = ?";
        sqlArgs.add(employeeNumber);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

        public SQLModel getDataCreateModel() throws SQLModelException {
            SQLModel sqlModel = new SQLModel();
            List sqlArgs = new ArrayList();
            try {
                ImportVillageAssetsDTO eoDTO = (ImportVillageAssetsDTO) dtoParameter;
                String sqlStr = "INSERT INTO ETS_ITEM_INFO EII\n" +
                        "  (EII.SYSTEMID,\n" +
                        "   EII.BARCODE,\n" +
                        "   EII.ITEM_CODE,\n" +
                        "   EII.RESPONSIBILITY_DEPT,\n" +
                        "   EII.RESPONSIBILITY_USER,\n" +
                        "   EII.ADDRESS_ID,\n" +
                        "   EII.CONTENT_CODE,\n" +
                        "   EII.CONTENT_NAME,\n" +
                        "   EII.SPECIALITY_DEPT,\n" +
                        "   EII.MAINTAIN_USER,\n" +
                        "   EII.MAINTAIN_DEPT,\n" +
                        "   EII.START_DATE,\n" +
                        "   EII.PRICE,\n" +
                        "   EII.REMARK,\n" +
                        "   EII.OTHER_INFO,\n" +
                        "   EII.CREATION_DATE,\n" +
                        "   EII.ATTRIBUTE1,\n" +
                        "   EII.ORGANIZATION_ID,\n" +
                        "   EII.CREATED_BY)\n" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), 'CT', ?, ?)";
                sqlArgs.add(eoDTO.getSystemid());
                sqlArgs.add(eoDTO.getBarcode());
                sqlArgs.add(eoDTO.getItemCode());
                sqlArgs.add(eoDTO.getResponsibilityDept());
                sqlArgs.add(eoDTO.getEmployeeId());
                sqlArgs.add(eoDTO.getAddressId());
                sqlArgs.add(eoDTO.getContentCode());
                sqlArgs.add(eoDTO.getContentName());
                sqlArgs.add(eoDTO.getSpecialityDept());
                sqlArgs.add(eoDTO.getMaintainUser());
                sqlArgs.add(eoDTO.getMaintainDept());
                sqlArgs.add(eoDTO.getStartDate());
                sqlArgs.add(eoDTO.getPrice());
                sqlArgs.add(eoDTO.getRemark());
                sqlArgs.add(eoDTO.getEquipmentPerformance());
                sqlArgs.add(userAccount.getOrganizationId());
                sqlArgs.add(userAccount.getUserId());
                sqlModel.setSqlStr(sqlStr);
                sqlModel.setArgs(sqlArgs);
            } catch (CalendarException ex) {
                ex.printLog();
                throw new SQLModelException(ex);
            }
            return sqlModel;
    }

    /**
     * ���ܣ�У��У�鵱ǰ�û����������ʲ���Ȩ��
     * @param companyCode String
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel validateOU(String companyCode) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1 FROM ETS_OU_CITY_MAP EOCM WHERE EOCM.COMPANY_CODE = ? AND EOCM.ORGANIZATION_ID = ?";
        sqlArgs.add(companyCode);
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�У�������ˣ����β��Ŷ�Ӧ�Ƿ���ȷ
     * @param deptCode String
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel validateEmployee(String deptCode, String employeeNumber) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1 FROM AMS_MIS_EMPLOYEE AME WHERE AME.DEPT_CODE = ? AND AME.EMPLOYEE_NUMBER = ?";
        sqlArgs.add(deptCode);
        sqlArgs.add(employeeNumber);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�ͨ��NEW_EMPLOYEE_NUMBERȡ��DEPT_CODE
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDeptCodeModel(String employeeNumber) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT AME.DEPT_CODE FROM AMS_MIS_EMPLOYEE AME WHERE AME.EMPLOYEE_NUMBER = ?";
        sqlArgs.add(employeeNumber);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
