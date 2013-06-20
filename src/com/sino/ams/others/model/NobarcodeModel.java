package com.sino.ams.others.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.spare.dto.AmsItemTransHDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * Created by IntelliJ IDEA.
 * User: yuyao
 * Date: 2008-7-4
 * Time: 10:06:55
 * To change this template use File | Settings | File Templates.
 */
public class NobarcodeModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ���������ͷ��(EAM) AMS_ITEM_TRANS_H ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsItemTransHDTO ���β���������
     */
    public NobarcodeModel(SfUserDTO userAccount, AmsItemTransHDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ����ɱ�������ͷ��(EAM) AMS_ITEM_TRANS_H���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) dtoParameter;
            String sqlStr = "INSERT INTO "
                    + " AMS_NOBARCODE_TRANS_H("
                    + " TRANS_ID,"
                    + " TRANS_NO,"
                    + " TRANS_TYPE,"
                    + " TRANS_STATUS,"
                    + " FROM_USER,"
                    + " TO_USER,"
                    + " FROM_DEPT,"
                    + " TO_DEPT,"
                    + " FROM_OBJECT_NO,"
                    + " TO_OBJECT_NO,"
                    + " FROM_ORGANIZATION_ID,"
                    + " TO_ORGANIZATION_ID,"
                    + " TRANS_DATE,"
                    + " RCV_USER,"
                    + " CREATION_DATE,"
                    + " CREATED_BY,"
                    + " REMARK, "
                    + " ATTRIBUTE1,"
                    + " ATTRIBUTE2,"
                    + " ATTRIBUTE3,"
                    + " RESPECT_RETURN_DATE,"
                    + " REASON,"
                    + " DEPT_CODE,"
                    + " AUTHORIZATION_USER,"
                    + " INV_MANAGER"
                    + ") VALUES ("
                    + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            sqlArgs.add(amsItemTransH.getTransId());
            sqlArgs.add(amsItemTransH.getTransNo());
            sqlArgs.add(amsItemTransH.getTransType());
            sqlArgs.add(amsItemTransH.getTransStatus());
            sqlArgs.add(amsItemTransH.getFromUser());
            sqlArgs.add(amsItemTransH.getToUser());
            sqlArgs.add(amsItemTransH.getFromDept());
            sqlArgs.add(amsItemTransH.getToDept());
            sqlArgs.add(amsItemTransH.getFromObjectNo());
            sqlArgs.add(amsItemTransH.getToObjectNo());
            sqlArgs.add(amsItemTransH.getFromOrganizationId());
            sqlArgs.add(amsItemTransH.getToOrganizationId());
            sqlArgs.add(amsItemTransH.getTransDate());
            sqlArgs.add(amsItemTransH.getRcvUser());
            sqlArgs.add(amsItemTransH.getCreatedBy());
            sqlArgs.add(amsItemTransH.getRemark());
//            sqlArgs.add(amsItemTransH.getLastUpdateDate());
//            sqlArgs.add(amsItemTransH.getLastUpdateBy());
            sqlArgs.add(amsItemTransH.getAttribute1());
            sqlArgs.add(amsItemTransH.getAttribute2());
            sqlArgs.add(amsItemTransH.getAttribute3());
            sqlArgs.add(amsItemTransH.getRespectReturnDate());
            sqlArgs.add(amsItemTransH.getReason());
            sqlArgs.add(amsItemTransH.getDeptCode());
            sqlArgs.add(amsItemTransH.getAuthorizationUser());
            sqlArgs.add(amsItemTransH.getInvManager());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException e) {
            throw new SQLModelException(e);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ�������ͷ��(EAM) AMS_ITEM_TRANS_H���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_ITEM_TRANS_H"
                + " SET"
                + " TRANS_NO = ?,"
                + " TRANS_TYPE = ?,"
                + " TRANS_STATUS = ?,"
                + " FROM_USER = ?,"
                + " TO_USER = ?,"
                + " FROM_DEPT = ?,"
                + " TO_DEPT = ?,"
                + " FROM_OBJECT_NO = ?,"
                + " TO_OBJECT_NO = ?,"
                + " FROM_ORGANIZATION_ID = ?,"
//                + " TO_ORGANIZATION_ID = ?,"
                + " TRANS_DATE = ?,"
                + " RCV_USER = ?,"
                + " LAST_UPDATE_DATE = ?,"
                + " LAST_UPDATE_BY = ?,"
                + " REMARK = ?, "
                + " RESPECT_RETURN_DATE = ?, "
                + " REASON = ?,"
                + " DEPT_CODE = ?,"
                + " AUTHORIZATION_USER = ?,"
                + " INV_MANAGER = ?"
                + " WHERE"
                + " TRANS_ID = ?";

        try {
            sqlArgs.add(amsItemTransH.getTransNo());
            sqlArgs.add(amsItemTransH.getTransType());
            sqlArgs.add(amsItemTransH.getTransStatus());
            sqlArgs.add(amsItemTransH.getFromUser());
            sqlArgs.add(amsItemTransH.getToUser());
            sqlArgs.add(amsItemTransH.getFromDept());
            sqlArgs.add(amsItemTransH.getToDept());
            sqlArgs.add(amsItemTransH.getFromObjectNo());
            sqlArgs.add(amsItemTransH.getToObjectNo());
            sqlArgs.add(amsItemTransH.getFromOrganizationId());
//            sqlArgs.add(amsItemTransH.getToOrganizationId());
            sqlArgs.add(amsItemTransH.getTransDate());
            sqlArgs.add(amsItemTransH.getRcvUser());
            sqlArgs.add(amsItemTransH.getLastUpdateDate());
            sqlArgs.add(amsItemTransH.getLastUpdateBy());
            sqlArgs.add(amsItemTransH.getRemark());
            sqlArgs.add(amsItemTransH.getRespectReturnDate());
            sqlArgs.add(amsItemTransH.getReason());
            sqlArgs.add(amsItemTransH.getDeptCode());
            sqlArgs.add(amsItemTransH.getAuthorizationUser());
            sqlArgs.add(amsItemTransH.getInvManager());
            sqlArgs.add(amsItemTransH.getTransId());
        } catch (CalendarException e) {
            throw new SQLModelException(e);
        }

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ�������ͷ��(EAM) AMS_ITEM_TRANS_H����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_ITEM_TRANS_H"
                + " WHERE"
                + " TRANS_ID = ?";
        sqlArgs.add(amsItemTransH.getTransId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ�������ͷ��(EAM) AMS_ITEM_TRANS_H������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) dtoParameter;
        String sqlStr = "SELECT AITH.TRANS_ID,\n" +
                "       AITH.TRANS_NO,\n" +
                "       AITH.TRANS_TYPE,\n" +
                "       AITH.TRANS_STATUS,\n" +
                "       AITH.CREATED_BY,\n" +
                "       AITH.CREATION_DATE,\n" +
                "       AITH.TRANS_DATE,\n" +
                "       AITH.FROM_OBJECT_NO,\n" +
                "       AITH.TO_OBJECT_NO,\n" +
                "       AITH.FROM_ORGANIZATION_ID,\n" +
                "       AITH.TO_ORGANIZATION_ID,\n" +
                "       AITH.RESPECT_RETURN_DATE RESPECT_RETURN_DATE,\n" +
                "       EOCM.COMPANY FROM_ORGANIZATION_NAME,\n" +
                "       EOCM2.COMPANY TO_ORGANIZATION_NAME,\n" +
                "       SUV.USERNAME CREATED_USER,\n" +
                "       EO.WORKORDER_OBJECT_NAME FROM_OBJECT_NAME,\n" +
                "       EO.WORKORDER_OBJECT_LOCATION FROM_OBJECT_LOCATION,\n" +
                "       EO2.WORKORDER_OBJECT_NAME TO_OBJECT_NAME,\n" +
                "       EO2.WORKORDER_OBJECT_LOCATION TO_OBJECT_LOCATION,\n" +
                "       EFV.VALUE TRANS_STATUS_NAME," +
                "       AITH.REMARK, \n" +
                "       AITH.ATTRIBUTE1, \n" +
                "       AITH.ATTRIBUTE2, \n" +
                "       AITH.ATTRIBUTE3, \n" +
                "       AITH.REASON, \n" +
                "       AITH.DEPT_CODE, \n" +
                "       AITH.AUTHORIZATION_USER, \n" +
                "       AITH.INV_MANAGER, \n" +
                "       AMD.DEPT_NAME \n" +
                "  FROM AMS_NOBARCODE_TRANS_H   AITH,\n" +
                "       ETS_OBJECT         EO,\n" +
                "       ETS_OBJECT         EO2,\n" +
                "       SF_USER_V          SUV,\n" +
                "       ETS_OU_CITY_MAP    EOCM,\n" +
                "       ETS_OU_CITY_MAP    EOCM2,\n" +
                "       ETS_FLEX_VALUES    EFV,\n" +
                "       ETS_FLEX_VALUE_SET EFVS,\n" +
                "       AMS_MIS_DEPT       AMD\n" +
                " WHERE AITH.FROM_OBJECT_NO *= EO.WORKORDER_OBJECT_NO\n" +
                "   AND AITH.TO_OBJECT_NO *= EO2.WORKORDER_OBJECT_NO\n" +
                "   AND AITH.CREATED_BY = SUV.USER_ID\n" +
                "   AND AITH.TRANS_STATUS = EFV.CODE\n" +
                "   AND AITH.FROM_ORGANIZATION_ID *= EOCM.ORGANIZATION_ID\n" +
                "   AND AITH.TO_ORGANIZATION_ID *= EOCM2.ORGANIZATION_ID\n" +
                "   AND EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
                "   AND EFVS.CODE = 'ORDER_STATUS'" +
                "   AND AITH.DEPT_CODE *= AMD.DEPT_CODE" +
                "   AND TRANS_ID = ?";
        sqlArgs.add(amsItemTransH.getTransId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ�������ͷ��(EAM) AMS_ITEM_TRANS_H����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     */
    public SQLModel getDataMuxModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " TRANS_ID,"
                + " TRANS_NO,"
                + " TRANS_TYPE,"
                + " TRANS_STATUS,"
                + " FROM_USER,"
                + " TO_USER,"
                + " FROM_DEPT,"
                + " TO_DEPT,"
                + " FROM_OBJECT_NO,"
                + " TO_OBJECT_NO,"
                + " FROM_ORGANIZATION_ID,"
                + " TO_ORGANIZATION_ID,"
                + " TRANS_DATE,"
                + " RCV_USER,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + " FROM"
                + " AMS_ITEM_TRANS_H"
                + " WHERE"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR TRANS_ID LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR TRANS_NO LIKE ?)"
                + "AND TRANS_TYPE = dbo.NVL(?,TRANS_TYPE)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR TRANS_STATUS LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR FROM_USER LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR TO_USER LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR FROM_DEPT LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR TO_DEPT LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR FROM_OBJECT_NO LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR TO_OBJECT_NO LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR FROM_ORGANIZATION_ID LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR TO_ORGANIZATION_ID LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR TRANS_DATE LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR RCV_USER LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
                + "AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
        sqlArgs.add(amsItemTransH.getTransId());
        sqlArgs.add(amsItemTransH.getTransId());
        sqlArgs.add(amsItemTransH.getTransNo());
        sqlArgs.add(amsItemTransH.getTransNo());
        sqlArgs.add(amsItemTransH.getTransType());
        sqlArgs.add(amsItemTransH.getTransStatus());
        sqlArgs.add(amsItemTransH.getTransStatus());
        sqlArgs.add(amsItemTransH.getFromUser());
        sqlArgs.add(amsItemTransH.getFromUser());
        sqlArgs.add(amsItemTransH.getToUser());
        sqlArgs.add(amsItemTransH.getToUser());
        sqlArgs.add(amsItemTransH.getFromDept());
        sqlArgs.add(amsItemTransH.getFromDept());
        sqlArgs.add(amsItemTransH.getToDept());
        sqlArgs.add(amsItemTransH.getToDept());
        sqlArgs.add(amsItemTransH.getFromObjectNo());
        sqlArgs.add(amsItemTransH.getFromObjectNo());
        sqlArgs.add(amsItemTransH.getToObjectNo());
        sqlArgs.add(amsItemTransH.getToObjectNo());
        sqlArgs.add(amsItemTransH.getFromOrganizationId());
        sqlArgs.add(amsItemTransH.getFromOrganizationId());
        sqlArgs.add(amsItemTransH.getToOrganizationId());
        sqlArgs.add(amsItemTransH.getToOrganizationId());
        try {
            sqlArgs.add(amsItemTransH.getTransDate());
            sqlArgs.add(amsItemTransH.getTransDate());
            sqlArgs.add(amsItemTransH.getRcvUser());
            sqlArgs.add(amsItemTransH.getRcvUser());
            sqlArgs.add(amsItemTransH.getCreationDate());
            sqlArgs.add(amsItemTransH.getCreationDate());
            sqlArgs.add(amsItemTransH.getCreatedBy());
            sqlArgs.add(amsItemTransH.getCreatedBy());
            sqlArgs.add(amsItemTransH.getLastUpdateDate());
            sqlArgs.add(amsItemTransH.getLastUpdateDate());
        } catch (CalendarException e) {
            throw new SQLModelException(e);
        }
        sqlArgs.add(amsItemTransH.getLastUpdateBy());
        sqlArgs.add(amsItemTransH.getLastUpdateBy());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ�������ͷ��(EAM) AMS_ITEM_TRANS_Hҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsItemTransHDTO amsItemTransH = (AmsItemTransHDTO) dtoParameter;
            String sqlStr="" ;
        if(amsItemTransH.getTransType().equals("FTMRK")){
           sqlStr = "SELECT AITH.TRANS_ID,\n" +
                "       AITH.TRANS_NO,\n" +
                "       AITH.CREATED_BY,\n" +
                "       AITH.CREATION_DATE CREATION_DATE,\n" +
                "       AITH.TRANS_DATE TRANS_DATE,\n" +
                "       SUV.USERNAME CREATED_USER,\n" +
                "       EO.WORKORDER_OBJECT_NAME,\n" +
                "       EFV.VALUE ORDER_STATUS_NAME\n" +
                "FROM   AMS_NOBARCODE_TRANS_H AITH,\n" +
                "       ETS_OBJECT            EO,\n" +
                "       SF_USER_V             SUV,\n" +
                "       ETS_FLEX_VALUES       EFV,\n" +
                "       ETS_FLEX_VALUE_SET    EFVS\n" +
                "WHERE  AITH.TO_OBJECT_NO *= EO.WORKORDER_OBJECT_NO\n" +
                "       AND AITH.CREATED_BY = SUV.USER_ID\n" +
                "       AND AITH.TRANS_STATUS = EFV.CODE\n" +
                "       AND EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
                "       AND EFVS.CODE = 'ORDER_STATUS'\n" +
                "       AND ( " + SyBaseSQLUtil.isNull() + "  OR TRANS_NO = ?)\n" +
                "       AND ( " + SyBaseSQLUtil.isNull() + "  OR TRANS_STATUS = ?)\n" +
                "       AND ( " + SyBaseSQLUtil.isNull() + "  OR TO_OBJECT_NO = ?)\n" +
                "       AND ( " + SyBaseSQLUtil.isNull() + "  OR TO_ORGANIZATION_ID = ?)\n" +
//                "       AND AITH.TRANS_TYPE = dbo.NVL(?, AITH.TRANS_TYPE)\n" +
                "       AND AITH.CREATION_DATE >= ISNULL(?, AITH.CREATION_DATE)\n" +
                "       AND AITH.CREATION_DATE <= ISNULL(?, AITH.CREATION_DATE)" +
                   "    AND AITH.TRANS_TYPE='FTMRK'\n" +
                "ORDER  BY AITH.CREATION_DATE DESC"; 
        }else {
             sqlStr="SELECT AITH.TRANS_ID,\n" +
                     "       AITH.TRANS_NO,\n" +
                     "       AITH.CREATED_BY,\n" +
                     "       AITH.CREATION_DATE CREATION_DATE,\n" +
                     "       AITH.TRANS_DATE TRANS_DATE,\n" +
                     "       SUV.USERNAME CREATED_USER,\n" +
                     "       EO.WORKORDER_OBJECT_NAME,\n" +
                     "       EFV.VALUE ORDER_STATUS_NAME\n" +
                     "FROM   AMS_NOBARCODE_TRANS_H AITH,\n" +
                     "       ETS_OBJECT            EO,\n" +
                     "       SF_USER_V             SUV,\n" +
                     "       ETS_FLEX_VALUES       EFV,\n" +
                     "       ETS_FLEX_VALUE_SET    EFVS\n" +
                     "WHERE  AITH.FROM_OBJECT_NO *= EO.WORKORDER_OBJECT_NO\n" +
                     "       AND AITH.CREATED_BY = SUV.USER_ID\n" +
                     "       AND AITH.TRANS_STATUS = EFV.CODE\n" +
                     "       AND EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
                     "       AND EFVS.CODE = 'ORDER_STATUS'\n" +
                     "       AND ( " + SyBaseSQLUtil.isNull() + "  OR TRANS_NO = ?)\n" +
                     "       AND ( " + SyBaseSQLUtil.isNull() + "  OR TRANS_STATUS = ?)\n" +
                     "       AND ( " + SyBaseSQLUtil.isNull() + "  OR TO_OBJECT_NO = ?)\n" +
                     "       AND ( " + SyBaseSQLUtil.isNull() + "  OR TO_ORGANIZATION_ID = ?)\n" +
//                     "       AND AITH.TRANS_TYPE = dbo.NVL(?, AITH.TRANS_TYPE)\n" +
                     "       AND AITH.CREATION_DATE >= ISNULL(?, AITH.CREATION_DATE)\n" +
                     "       AND AITH.CREATION_DATE <= ISNULL(?, AITH.CREATION_DATE)" +
                     "      AND AITH.TRANS_TYPE='FTMCK'\n" +
                     "ORDER  BY AITH.CREATION_DATE DESC";
        }

        sqlArgs.add(amsItemTransH.getTransNo());
        sqlArgs.add(amsItemTransH.getTransNo());
//		sqlArgs.add(amsItemTransH.getTransType());
//		sqlArgs.add(amsItemTransH.getTransType());
        sqlArgs.add(amsItemTransH.getTransStatus());
        sqlArgs.add(amsItemTransH.getTransStatus());
//		sqlArgs.add(amsItemTransH.getFromUser());
//		sqlArgs.add(amsItemTransH.getFromUser());
//		sqlArgs.add(amsItemTransH.getToUser());
//		sqlArgs.add(amsItemTransH.getToUser());
//		sqlArgs.add(amsItemTransH.getFromDept());
//		sqlArgs.add(amsItemTransH.getFromDept());
//		sqlArgs.add(amsItemTransH.getToDept());
//		sqlArgs.add(amsItemTransH.getToDept());
//		sqlArgs.add(amsItemTransH.getFromObjectNo());
//		sqlArgs.add(amsItemTransH.getFromObjectNo());
        sqlArgs.add(amsItemTransH.getToObjectNo());
        sqlArgs.add(amsItemTransH.getToObjectNo());
//		sqlArgs.add(amsItemTransH.getFromOrganizationId());
//		sqlArgs.add(amsItemTransH.getFromOrganizationId());
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(sfUser.getOrganizationId());
//        sqlArgs.add(amsItemTransH.getTransType());
        try {
            sqlArgs.add(amsItemTransH.getFromDate());
            sqlArgs.add(amsItemTransH.getToDate());
        } catch (CalendarException e) {
            throw new SQLModelException(e);
        }
//		sqlArgs.add(amsItemTransH.getRcvUser());
//		sqlArgs.add(amsItemTransH.getRcvUser());
//		sqlArgs.add(amsItemTransH.getCreationDate());
//		sqlArgs.add(amsItemTransH.getCreationDate());
//		sqlArgs.add(amsItemTransH.getCreatedBy());
//		sqlArgs.add(amsItemTransH.getCreatedBy());
//		sqlArgs.add(amsItemTransH.getLastUpdateDate());
//		sqlArgs.add(amsItemTransH.getLastUpdateDate());
//		sqlArgs.add(amsItemTransH.getLastUpdateBy());
//		sqlArgs.add(amsItemTransH.getLastUpdateBy());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}