package com.sino.ams.newasset.free.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>Title: AmsAssetsTransHeaderModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsTransHeaderModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class FreeAssetsHeaderModel extends AMSSQLProducer {
    private SfUserDTO user = null;

    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransHeaderDTO ���β���������
     */
    public FreeAssetsHeaderModel(SfUserDTO userAccount,
                                 AmsAssetsTransHeaderDTO dtoParameter) {
        super(userAccount, dtoParameter);
        this.user = (SfUserDTO) userAccount;
    }

    /**
     * ���ܣ�����Զ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     * @throws com.sino.base.exception.SQLModelException
     *          ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
            String sqlStr = "INSERT INTO "
                    + " AMS_ASSETS_TRANS_HEADER("
                    + " TRANS_ID,"
                    + " TRANS_NO,"
                    + " TRANS_TYPE,"
                    + " TRANSFER_TYPE,"
                    + " TRANS_STATUS,"
                    + " FROM_ORGANIZATION_ID,"
                    + " TO_ORGANIZATION_ID,"
                    + " FROM_DEPT,"
                    + " FROM_GROUP,"
                    + " TO_DEPT,"
                    + " FROM_OBJECT_NO,"
                    + " TO_OBJECT_NO,"
                    + " FROM_PERSON,"
                    + " TO_PERSON,"
                    + " CREATED_REASON,"
                    + " CREATION_DATE,"
                    + " CREATED_BY,"
                    + " RECEIVED_USER,"
                    + " TRANS_DATE,"
                    + " FA_CONTENT_CODE,"
                    + " LOSSES_NAME,"
                    + " LOSSES_DATE,"
                    + " IS_THRED,"
                    + "	EMERGENT_LEVEL"
                    + ") VALUES ("
                    + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?, ?, ?, ?, ?, ?)";
            sqlArgs.add(dto.getTransId());
            sqlArgs.add(dto.getTransNo());
            sqlArgs.add(dto.getTransType());
            sqlArgs.add(dto.getTransferType());
            sqlArgs.add(dto.getTransStatus());
            sqlArgs.add(dto.getFromOrganizationId());
            sqlArgs.add(dto.getToOrganizationId());
            sqlArgs.add(dto.getFromDept());
            sqlArgs.add(dto.getFromGroup());
            sqlArgs.add(dto.getToDept());
            sqlArgs.add(dto.getFromObjectNo());
            sqlArgs.add(dto.getToObjectNo());
            sqlArgs.add(dto.getFromPerson());
            sqlArgs.add(dto.getToPerson());
            sqlArgs.add(dto.getCreatedReason());
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(dto.getReceivedUser());
            sqlArgs.add(dto.getTransDate());
            sqlArgs.add(dto.getFaContentCode());
            sqlArgs.add(dto.getLossesName());
            sqlArgs.add(dto.getLossesDate());
            sqlArgs.add(dto.getThred());
            sqlArgs.add(dto.getEmergentLevel());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     * @throws com.sino.base.exception.SQLModelException
     *          ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
            String sqlStr = "UPDATE AMS_ASSETS_TRANS_HEADER"
                    + " SET"
                    + " TRANS_NO = ?,"
                    + " TRANS_STATUS = ?,"
                    + " TO_ORGANIZATION_ID = ?,"
                    + " FROM_DEPT = ?,"
                    + " TO_DEPT = ?,"
                    + " FROM_OBJECT_NO = ?,"
                    + " TO_OBJECT_NO = ?,"
                    + " FROM_PERSON = ?,"
                    + " TO_PERSON = ?,"
                    + " CREATED_REASON = ?,"
                    + " LAST_UPDATE_DATE = GETDATE(),"
                    + " LAST_UPDATE_BY = ?,"
                    + " RECEIVED_USER = ?,"
                    + " FA_CONTENT_CODE = ?,"
                    + " TRANS_DATE = ?,"
                    + " LOSSES_DATE = ?,"
                    + " LOSSES_NAME = ?,"
                    + " EMERGENT_LEVEL = ?"
                    + " WHERE" + " TRANS_ID = ?";

            sqlArgs.add(dto.getTransNo());
            sqlArgs.add(dto.getTransStatus());
            sqlArgs.add(dto.getToOrganizationId());
            sqlArgs.add(dto.getFromDept());
            sqlArgs.add(dto.getToDept());
            sqlArgs.add(dto.getFromObjectNo());
            sqlArgs.add(dto.getToObjectNo());
            sqlArgs.add(dto.getFromPerson());
            sqlArgs.add(dto.getToPerson());
            sqlArgs.add(dto.getCreatedReason());
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(dto.getReceivedUser());
            sqlArgs.add(dto.getFaContentCode());
            sqlArgs.add(dto.getTransDate());
            sqlArgs.add(dto.getLossesDate());
            sqlArgs.add(dto.getLossesName());
            sqlArgs.add(dto.getEmergentLevel());
            sqlArgs.add(dto.getTransId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        String sqlStr = "DELETE FROM" + " AMS_ASSETS_TRANS_HEADER" + " WHERE"
                + " TRANS_ID = ?";
        sqlArgs.add(dto.getTransId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        String sqlStr = " SELECT"
                + " AATH.TRANS_ID,"
                + " AATH.TRANS_NO,"
                + " AATH.FROM_DEPT,"
                + " AATH.TO_DEPT,"
                + " AATH.TRANS_TYPE,"
                + " AATH.TRANSFER_TYPE,"
                + " AATH.TRANS_STATUS,"
                + " AATH.TRANS_DATE,"
                + " AATH.CREATION_DATE,"
                + " AATH.CREATED_BY,"
                + " AATH.LAST_UPDATE_DATE,"
                + " AATH.LAST_UPDATE_BY,"
                + " AATH.CANCELED_DATE,"
                + " AATH.CANCELED_REASON,"
                + " AATH.CREATED_REASON,"
                + " AATH.APPROVED_DATE,"
                + " AATH.FROM_ORGANIZATION_ID,"
                + " AATH.FROM_GROUP,"
                + " AATH.FA_CONTENT_CODE,"
                + " AATH.LOSSES_NAME,"
                + " AATH.LOSSES_DATE,"
                + " AATH.IS_THRED,"
                + " AMD.DEPT_NAME FROM_DEPT_NAME,"
                + " dbo.APP_GET_FLEX_VALUE(AATH.TRANS_STATUS, 'ORDER_STATUS') TRANS_STATUS_DESC,"
                + " dbo.APP_GET_FLEX_VALUE(AATH.TRANS_TYPE, 'ORDER_TYPE_ASSETS') TRANS_TYPE_VALUE,"
                + " dbo.APP_GET_FLEX_VALUE(AATH.FA_CONTENT_CODE, 'FA_CONTENT_CODE') FA_CONTENT_NAME,"
                + " SU.USERNAME CREATED," + " SU.EMAIL,"
                + " SU.MOBILE_PHONE PHONE_NUMBER,"
                + " SG.GROUP_NAME FROM_GROUP_NAME," + "0 GROUP_PROP,"
                + " EOCM.BOOK_TYPE_CODE," + " EOCM.BOOK_TYPE_NAME,"
                + " EOCM.COMPANY FROM_COMPANY_NAME,"
                + " AMD2.DEPT_NAME USER_DEPT_NAME,"
                + " AATH.TO_ORGANIZATION_ID,"
                + " EOCM2.COMPANY TO_COMPANY_NAME,"
                + "	AATH.EMERGENT_LEVEL,"
                + "	dbo.APP_GET_FLEX_VALUE(AATH.EMERGENT_LEVEL, 'EMERGENT_LEVEL') EMERGENT_LEVEL_NAME"
                + " FROM"
                + " AMS_ASSETS_TRANS_HEADER AATH,"
                + " ETS_OU_CITY_MAP         EOCM,"
                + " AMS_MIS_DEPT            AMD,"
                + " SF_GROUP                SG,"
                + " SF_USER                 SU,"
                + " AMS_MIS_EMPLOYEE        AME,"
                + " AMS_MIS_DEPT            AMD2,"
                + " ETS_OU_CITY_MAP         EOCM2" + " WHERE"
                + " AATH.FROM_ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                + " AND AATH.FROM_DEPT *= AMD.DEPT_CODE"
                + " AND AATH.FROM_GROUP *= SG.GROUP_ID"
                + " AND AATH.CREATED_BY = SU.USER_ID"
                + " AND SU.EMPLOYEE_NUMBER *= AME.EMPLOYEE_NUMBER"
                + " AND AME.DEPT_CODE *= AMD2.DEPT_CODE"
                + " AND AATH.TO_ORGANIZATION_ID *= EOCM2.ORGANIZATION_ID"
                + " AND TRANS_ID = ?";
        sqlArgs.add(dto.getTransId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADERҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws com.sino.base.exception.SQLModelException
     *          ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
            String sqlStr = "SELECT"
                    + " AATH.TRANS_ID,"
                    + " AATH.TRANS_NO,"
                    + " AATH.TRANS_TYPE,"
                    + " AATH.TRANSFER_TYPE,"
                    + " AATH.TRANS_STATUS,"
                    + " AATH.FROM_ORGANIZATION_ID,"
                    + " AATH.LOSSES_NAME,"
                    + " AATH.LOSSES_DATE,"
                    + " EOCM.COMPANY,"
                    + " dbo.NVL(AMD.DEPT_NAME, EOCM.COMPANY) FROM_DEPT_NAME,"
                    + " AATH.RECEIVED_USER,"
                    + " AATH.CREATION_DATE,"
                    + " dbo.APP_GET_FLEX_VALUE(AATH.TRANS_STATUS, 'ORDER_STATUS') TRANS_STATUS_DESC,"
                    + " dbo.APP_GET_FLEX_VALUE(AATH.TRANS_TYPE, 'ORDER_TYPE_ASSETS') TRANS_TYPE_VALUE,"
                    + " SU.USERNAME CREATED"
                    + " FROM"
                    + " AMS_ASSETS_TRANS_HEADER AATH,"
                    + " AMS_MIS_DEPT       AMD,"
                    + " ETS_OU_CITY_MAP    EOCM,"
                    + " SF_USER            SU"
                    + " WHERE"
                    + " AATH.FROM_ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                    + " AND AATH.FROM_DEPT *= AMD.DEPT_CODE"
                    + " AND AATH.CREATED_BY = SU.USER_ID"
                    + " AND AATH.TRANS_TYPE = ?"
                    + " AND AATH.TRANS_STATUS = ?"
                    + " AND AATH.CREATED_BY = ?"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR AATH.TRANS_NO LIKE ?)"
                    + " AND AATH.CREATION_DATE >= ISNULL(?, AATH.CREATION_DATE)"
                    + " AND AATH.CREATION_DATE <= ISNULL(?, AATH.CREATION_DATE)"
                    + " AND ( " + SyBaseSQLUtil.isNull() + "  OR AATH.TRANSFER_TYPE = dbo.NVL(?, AATH.TRANSFER_TYPE))"
                    + " ORDER BY" + " AATH.TRANSFER_TYPE," + " AATH.TRANS_NO";
            sqlArgs.add(dto.getTransType());
            sqlArgs.add(dto.getTransStatus());
            sqlArgs.add(userAccount.getUserId());
            sqlArgs.add(dto.getTransNo());
            sqlArgs.add(dto.getTransNo());
            sqlArgs.add(dto.getStartDate());
            sqlArgs.add(dto.getSQLEndDate());
            sqlArgs.add(dto.getTransferType());
            sqlArgs.add(dto.getTransferType());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }

    /**
     * ���ܣ���������
     * @param transId String
     * @return SQLModel
     */
    public SQLModel getOrderCancelModel(String transId) {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "UPDATE"
                + " AMS_ASSETS_TRANS_HEADER"
                + " SET"
                + " TRANS_STATUS = ?,"
                + " CANCELED_DATE = GETDATE(),"
                + " CANCELED_REASON = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " TRANS_ID = ?";
        List sqlArgs = new ArrayList();
        sqlArgs.add(AssetsDictConstant.CANCELED);
        sqlArgs.add(AssetsDictConstant.CANCAL_REASON);
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(transId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�
     * @return SQLModel
     */
    public SQLModel getOrderModel() { //����ģ��
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT \n" + " AATH.TRANS_ID,\n"
                + " ''   MB1,''   MB2,''   MB3,''   MB4,\n"
                + " ''   MB5,''   MB6,''   MB7,''   MB8,\n"
                + " ''   MB9,''   MB10,''   MB11,''   MB12,\n"
                + " ''   MB13,''   MB14,''   MB15,''   MB16,\n"
                + " ''   MB17,''   MB18,''   MB19,''   MB20,\n"
                + " ''   MB21,''   MB22,''   MB23,''   MB24,\n"
                + " ''   MB25,''   MB26,''   MB27 \n" + " FROM \n"
                + " AMS_ASSETS_TRANS_HEADER AATH \n";
        List sqlArgs = new ArrayList();
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������û�����Ӧ��PID��������
     * @return SQLModel
     */
    public SQLModel getGroupPidModel() {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = " SELECT DISTINCT (SG1.P_FLOW_ID)\n" +
                "  FROM SF_USER SU, SF_GROUP SG1, SF_GROUP SG2, SF_USER_RIGHT SUR\n" +
                " WHERE SU.USER_ID = SUR.USER_ID\n" +
                "   AND SUR.GROUP_ID = SG1.GROUP_ID\n" +
//                "   AND (SG2.GROUPNAME LIKE '�й�˾���粿%' OR SG2.GROUPNAME LIKE '�й�˾�ۺϲ�%' OR\n" +
//                "       SG2.GROUPNAME LIKE '�й�˾�г���%')\n" +
                "   AND (SG2.GROUPNAME LIKE '�й�˾���粿%' OR SG2.GROUPNAME LIKE '�й�˾�ۺϲ�%')\n" +
                "   AND SG1.P_FLOW_ID = SG2.GROUP_ID\n" +
                "   AND SU.USER_ID = ?";
        List sqlArgs = new ArrayList();
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    //�ж��Ƿ����

    public SQLModel getIsFinanceGroupModel() {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = " SELECT DISTINCT (SG1.P_FLOW_ID)\n" +
                "  FROM SF_USER SU, SF_GROUP SG1, SF_GROUP SG2, SF_USER_RIGHT SUR\n" +
                " WHERE SU.USER_ID = SUR.USER_ID\n" +
                "   AND SUR.GROUP_ID = SG1.GROUP_ID\n" +
                "   AND (SG2.GROUPNAME LIKE 'ʡ��˾����%' OR SG2.GROUPNAME LIKE '�й�˾����%')\n" +
                "   AND SG1.P_FLOW_ID = SG2.GROUP_ID\n" +
                "   AND SU.USER_ID = ?";
        List sqlArgs = new ArrayList();
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getThredDeptModel(String fDept, String tDept) {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT COUNT(1) IS_THRED\n" +
                "  FROM SF_GROUP       SG,\n" +
                "       AMS_MIS_DEPT   AMD,\n" +
                "       SF_GROUP_MATCH SGM\n" +
                " WHERE SG.GROUP_ID = SGM.GROUP_ID\n" +
                "       AND AMD.DEPT_CODE = SGM.DEPT_CODE\n" +
                "       AND  " + SyBaseSQLUtil.isNotNull("SG.GROUP_THRED") + "\n" +
                "       AND (AMD.DEPT_CODE = ? OR AMD.DEPT_CODE = ?)\n" +
                " GROUP BY SG.GROUP_THRED";
//        String sqlStr = "SELECT COUNT(1) IS_THRED\n" +
//                "  FROM SF_GROUP T\n" +
//                " WHERE (T.GROUP_ID =\n" +
//                "       (SELECT SM.GROUP_ID FROM SF_GROUP_MATCH SM WHERE SM.DEPT_CODE = ?) AND\n" +
//                "       T.GROUP_THRED =\n" +
//                "       (SELECT SM.GROUP_ID FROM SF_GROUP_MATCH SM WHERE SM.DEPT_CODE = ?))\n" +
//                "       OR\n" +
//                "       (T.GROUP_ID =\n" +
//                "       (SELECT SM.GROUP_ID FROM SF_GROUP_MATCH SM WHERE SM.DEPT_CODE = ?) AND\n" +
//                "       T.GROUP_THRED =\n" +
//                "       (SELECT SM.GROUP_ID FROM SF_GROUP_MATCH SM WHERE SM.DEPT_CODE = ?))";
        List sqlArgs = new ArrayList();
        sqlArgs.add(fDept);
        sqlArgs.add(tDept);
//        sqlArgs.add(tDept);
//        sqlArgs.add(fDept);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������û�����Ӧ��PID��������
     * @return SQLModel
     */
    public SQLModel getIsGroupFlowIdModel(String fromGroup) {
        SQLModel sqlModel = new SQLModel();
        String sqlStr = " SELECT DISTINCT (SG.P_FLOW_ID)\n"
                + " FROM SF_USER SU," + " SF_GROUP SG,"
                + " SF_USER_RIGHT SUR\n" + "WHERE SU.USER_ID = SUR.USER_ID "
                + " AND SUR.GROUP_ID = SG.GROUP_ID "
                + " AND SG.P_FLOW_ID IN(2185, 2223, 2774)"
                + " AND SG.GROUP_ID = " + fromGroup + " AND SU.USER_ID = ?";
        List sqlArgs = new ArrayList();
        sqlArgs.add(userAccount.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    //EXCEL����ʱ�����û�е���ɹ���BARCODE

    public SQLModel getQueryBarcodeAllExcelModel(String excel,
                                                 AmsAssetsTransHeaderDTO dto, String returnModel) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String mtlMgrProps = user.getMtlMgrProps();
        if ("Y".equalsIgnoreCase(userAccount.getIsTd())) {
            String sqlStr = "SELECT AAAV.BARCODE FROM TD_ASSETS_ADDRESS_V AAAV WHERE AAAV.BARCODE IN ("
                    + excel + ") AND AAAV.BARCODE NOT IN (" + returnModel + ")";
            sqlArgs.add(user.getOrganizationId());
            if (!StrUtil.isEmpty(dto.getFromDept())) { //��������
                sqlArgs.add(dto.getFromDept());
            }
            if (!user.isDptAssetsManager() && !user.isComAssetsManager()
                    && mtlMgrProps.equals("")) { //����������
                if (!dto.getTransferType().equals("BTW_COMP")) {
                    sqlArgs.add(user.getEmployeeId());
                }
            }
            if (dto.getTransType().equals(AssetsDictConstant.ASS_RED)) { //����
                sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
                sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_FREED);
            } else if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS)) { //����
                sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
                sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_FREED);
            } else if (dto.getTransType().equals(AssetsDictConstant.ASS_CLR)) { //����ǰ�᣺������������
                sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
                sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_FREED);
            } else if (dto.getTransType().equals(AssetsDictConstant.ASS_CLR)) { //����ǰ�᣺�ѱ��ϻ�������
                sqlArgs.add(AssetsDictConstant.ITEM_STATUS_DISCARDED);
                sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_FREED);
            } else if (dto.getTransType().equals(AssetsDictConstant.ASS_SUB)) { //��ֵǰ�᣺������������
                sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
                sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_FREED);
            }
            sqlArgs.add(AssetsDictConstant.FA_CONTENT_CODE);
            sqlArgs.add(dto.getFaContentCode());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } else {
            String sqlStr = "SELECT AAAV.BARCODE FROM AMS_ASSETS_ADDRESS_V AAAV WHERE AAAV.BARCODE IN ("
                    + excel + ") AND AAAV.BARCODE NOT IN (" + returnModel + ")";
            sqlArgs.add(user.getOrganizationId());
            if (!StrUtil.isEmpty(dto.getFromDept())) { //��������
                sqlArgs.add(dto.getFromDept());
            }
            if (!user.isDptAssetsManager() && !user.isComAssetsManager()
                    && mtlMgrProps.equals("")) { //����������
                if (!dto.getTransferType().equals("BTW_COMP")) {
                    sqlArgs.add(user.getEmployeeId());
                }
            }
            if (dto.getTransType().equals(AssetsDictConstant.ASS_RED)) { //����
                sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
                sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_FREED);
            } else if (dto.getTransType().equals(AssetsDictConstant.ASS_DIS)) { //����
                sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
                sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_FREED);
            } else if (dto.getTransType().equals(AssetsDictConstant.ASS_CLR)) { //����ǰ�᣺������������
                sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
                sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_FREED);
            } else if (dto.getTransType().equals(AssetsDictConstant.ASS_CLR)) { //����ǰ�᣺�ѱ��ϻ�������
                sqlArgs.add(AssetsDictConstant.ITEM_STATUS_DISCARDED);
                sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_FREED);
            } else if (dto.getTransType().equals(AssetsDictConstant.ASS_SUB)) { //��ֵǰ�᣺������������
                sqlArgs.add(AssetsDictConstant.ITEM_STATUS_NORMAL);
                sqlArgs.add(AssetsDictConstant.ASSETS_STATUS_FREED);
            }
            sqlArgs.add(AssetsDictConstant.FA_CONTENT_CODE);
            sqlArgs.add(dto.getFaContentCode());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        }
        return sqlModel;
    }

    /**
     * ���ļ�ֵ�ʲ�״̬
     * @return
     */
    public SQLModel getFreeAssetsModel(String headerId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
//        String sqlStr =
//                "UPDATE ETS_ITEM_INFO\n" +
//                        "   SET ITEM_STATUS = ?\n" +
//                        " WHERE EXISTS (SELECT 1\n" +
//                        "          FROM AMS_ASSETS_TRANS_LINE\n" +
//                        "         WHERE TRANS_ID = ?\n" +
//                        "           AND BARCODE = BARCODE)";
        String sqlStr = " UPDATE ETS_ITEM_INFO \n" +
			        	"   SET ITEM_STATUS = ?\n" + 
			        	" WHERE BARCODE IN \n" + 
			        	"       (SELECT BARCODE\n" + 
			        	"          FROM AMS_ASSETS_TRANS_LINE\n" + 
			        	"         WHERE TRANS_ID = ?)";

        sqlArgs.add(DictConstant.STATUS_FREE);
        sqlArgs.add(headerId);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }
    
  //�ж��Ƿ�����ʵ�ﲿ��
    public SQLModel getIsSpecialGroupModel(int fromGroup) {
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT *\n" +
                "  FROM SF_GROUP      SG,\n" +
                "       SF_USER       SU,\n" +
                "       SF_USER_RIGHT SUR\n" +
                " WHERE SG.GROUP_ID = SUR.GROUP_ID\n" +
                "   AND SU.USER_ID = SUR.USER_ID\n" +
                "   AND SG.SPECIALITY_DEPT = 'Y'\n" +
                "   AND SU.USER_ID = ?" +
                "   AND SG.GROUP_ID = ?";
		List sqlArgs = new ArrayList();
		sqlArgs.add(userAccount.getUserId());
		sqlArgs.add(fromGroup);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
