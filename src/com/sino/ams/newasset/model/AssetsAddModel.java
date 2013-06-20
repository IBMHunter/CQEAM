package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.AssetsAddDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;

/**
 * <p>Title: SinoIES</p>
 * <p>Description: �����ƶ�IESϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ai
 * @date: 2008-3-13
 * ���������ʲ�
 */
public class AssetsAddModel extends AMSSQLProducer {
              SfUserDTO sfDto=null;
    public AssetsAddModel(SfUserDTO userAccount, AssetsAddDTO dtoParameter) {

        super(userAccount, dtoParameter);
          this.sfDto=userAccount;
    }

    /**
     * ���ܣ�ִ���������ݲ�����
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
//            try {
        List sqlArgs = new ArrayList();
        AssetsAddDTO dto = (AssetsAddDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                        + " ETS_ASSETS_ADD_H("
                        + " HEAD_ID,"
                        + " BILL_NO,"
                        + " STATUS,"
                        + " CREATE_USER,"
                        + " CREATED_DATE,"
                        + " SPEC_DEPT,"
                        + " REMARK"
                        + ") "
                        + " VALUES (?, ?, ?, ?, GETDATE(), ?, ?)";

        sqlArgs.add(dto.getHeadId());
        sqlArgs.add(dto.getBillNo());
        sqlArgs.add(StrUtil.strToInt(dto.getStatus()));
        sqlArgs.add(userAccount.getUserId());
        sqlArgs.add(dto.getSpecialityDept());
        sqlArgs.add(dto.getRemark());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ�������ͷ��(EAM) ETS_ASSETS_ADD_H������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AssetsAddDTO dto = (AssetsAddDTO) dtoParameter;
        String sqlStr = "SELECT EAAH.HEAD_ID,\n" +
                        "       EAAH.BILL_NO,\n" +
                        "       CASE WHEN EAAH.STATUS = 0 THEN 'δ���' ELSE '�����' END STATUS,"+
                        "       EAAH.CREATE_USER  CREATED_BY,\n " +
                        "       EAAH.CREATED_DATE CREATED_DATE,\n" +
                        "       EAAH.REMARK,\n" +
                        "       EAAH.SPEC_DEPT,\n" +
                        "       AMD.DEPT_NAME SPECIALITY_DEPT, \n" +
                        "       SU.USERNAME  CREATE_USER\n" +
                        "  FROM ETS_ASSETS_ADD_H  EAAH,\n" +
                        "		AMS_MIS_DEPT      AMD, \n" +
                        "       SF_USER           SU\n" +
                        " WHERE EAAH.CREATE_USER = SU.USER_ID\n" +
                        "   AND EAAH.SPEC_DEPT *= AMD.DEPT_CODE \n" +
                        "   AND EAAH.HEAD_ID = ?";
        sqlArgs.add(dto.getHeadId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ����ɱ�������ͷ��(EAM) ETS_ASSETS_ADD_H���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AssetsAddDTO dto = (AssetsAddDTO) dtoParameter;
        String sqlStr = "UPDATE ETS_ASSETS_ADD_H"
                        + " SET"
                        + "     STATUS = ?, "
                        + "     SPEC_DEPT = ?, "
                        + "     REMARK = ? "
                        + " WHERE"
                        + "     HEAD_ID = ?";
//            try {
        sqlArgs.add(StrUtil.strToInt(dto.getStatus()));
        sqlArgs.add(dto.getSpecialityDept());
        sqlArgs.add(dto.getRemark());
        sqlArgs.add(dto.getHeadId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����������ʲ���ѯ��
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws com.sino.base.exception.SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AssetsAddDTO dto = (AssetsAddDTO) dtoParameter;
//             AssetsAddLDTO dtoL = (AssetsAddLDTO) dtoParameter;
            String sqlStr = "SELECT EAAH.HEAD_ID,\n" +
                    "       EAAH.BILL_NO,\n" +
                    " CASE WHEN EAAH.STATUS=0 THEN 'δ���' ELSE '�����' END STATUS,"+
                    "       EAAH.CREATE_USER,\n" +
                    "       EAAH.CREATED_DATE,\n" +
                    "       EAAH.SPEC_DEPT,\n" +
                    "       EAAL.BARCODE,\n" +
                    "       EAAL.ITEM_CODE,\n" +
                    "       ESI.ITEM_NAME,\n" +
                    "       ESI.ITEM_SPEC,\n" +
                    "       SU.USERNAME\n" +
                    "FROM   ETS_ASSETS_ADD_H EAAH,\n" +
                    "       ETS_ASSETS_ADD_L EAAL,\n" +
                    "       ETS_SYSTEM_ITEM  ESI,\n" +
                    "       SF_USER          SU\n" +
                    "WHERE  EAAH.HEAD_ID = EAAL.HEAD_ID\n" +
                    "       AND EAAL.ITEM_CODE = ESI.ITEM_CODE\n" +
                    "       AND EAAH.CREATE_USER = SU.USER_ID\n" +
                    "       AND SU.ORGANIZATION_ID = ?\n" +
                    "       AND SU.USERNAME LIKE dbo.NVL(?, SU.USERNAME)\n" +
                    "       AND ESI.ITEM_SPEC LIKE dbo.NVL(?, ESI.ITEM_SPEC)\n" +
                    "       AND ESI.ITEM_NAME LIKE dbo.NVL(?, ESI.ITEM_NAME)\n" +
                    "       AND EAAH.STATUS = ISNULL(?, EAAH.STATUS)\n" +
                    "       AND (? = '' OR EAAH.CREATED_DATE >= ISNULL(?, EAAH.CREATED_DATE))\n" +
                    "       AND (? = '' OR EAAH.CREATED_DATE <= ISNULL(?, EAAH.CREATED_DATE))\n" +
                    "       AND EAAL.BARCODE LIKE dbo.NVL(?, EAAL.BARCODE)\n" +
                    "ORDER  BY EAAH.BILL_NO,\n" +
                    "          EAAL.BARCODE";
            sqlArgs.add(sfDto.getOrganizationId());
            sqlArgs.add(dto.getCreateUser());
            sqlArgs.add(dto.getItemSpec());
            sqlArgs.add(dto.getItemName());
            if ("".equals(dto.getStatus()) || null == dto.getStatus()) {
            	sqlArgs.add(null);
            } else {
            	sqlArgs.add(StrUtil.strToInt(dto.getStatus()));
            }
            sqlArgs.add(dto.getFromDate());
            sqlArgs.add(dto.getFromDate());
            sqlArgs.add(dto.getToDate());
            sqlArgs.add(dto.getToDate());
            sqlArgs.add(dto.getBarcod());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }
     public SQLModel getQueryAdressModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

            List sqlArgs = new ArrayList();
            AssetsAddDTO dto = (AssetsAddDTO) dtoParameter;
//             AssetsAddLDTO dtoL = (AssetsAddLDTO) dtoParameter;
            String sqlStr = "SELECT EO.WORKORDER_OBJECT_NAME, EO.WORKORDER_OBJECT_CODE\n" +
                    "  FROM AMS_OBJECT_ADDRESS AOA, ETS_OBJECT EO\n" +
                    " WHERE EO.WORKORDER_OBJECT_NO = AOA.OBJECT_NO\n" +
                    "   AND EO.OBJECT_CATEGORY = '45'\n" +
                    "   AND EO.ORGANIZATION_ID = ?";
            sqlArgs.add(sfDto.getOrganizationId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }
      public SQLModel getQueryItemModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

            List sqlArgs = new ArrayList();
            AssetsAddDTO dto = (AssetsAddDTO) dtoParameter;
//             AssetsAddLDTO dtoL = (AssetsAddLDTO) dtoParameter;
            String sqlStr = "SELECT ESI.ITEM_NAME, ESI.ITEM_SPEC\n" +
                    "  FROM ETS_SYSTEM_ITEM ESI, ETS_SYSITEM_DISTRIBUTE ESD\n" +
                    " WHERE ESI.ITEM_CODE = ESD.ITEM_CODE\n" +
//                    "   AND ESI.ITEM_CATEGORY IN ('HOUSE', 'LAND', 'OTHERS')\n" +
                    "   AND ESD.ORGANIZATION_ID = ?\n" +
                    " GROUP BY ESI.ITEM_NAME, ESI.ITEM_SPEC";
            sqlArgs.add(sfDto.getOrganizationId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
      
        return sqlModel;
    }
      public SQLModel getQueryDeptModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();

            List sqlArgs = new ArrayList();
            AssetsAddDTO dto = (AssetsAddDTO) dtoParameter;
//             AssetsAddLDTO dtoL = (AssetsAddLDTO) dtoParameter;
            String sqlStr = "SELECT AMD.DEPT_NAME, AMD.DEPT_CODE\n" +
                    "  FROM AMS_MIS_DEPT AMD, ETS_OU_CITY_MAP EOCM\n" +
                    " WHERE EOCM.COMPANY_CODE = AMD.COMPANY_CODE\n" +
                    "   AND EOCM.ORGANIZATION_ID = ?";
            sqlArgs.add(sfDto.getOrganizationId());

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }
    public SQLModel getOrderModel() { //����ģ��
		SQLModel sqlModel = new SQLModel();
		String sqlStr = "SELECT '4210-20017179' BARCODE,\n" +
                "       '�ʼǱ�����' ITEM_NAME,\n" +
                "       'IBM T61' ITEM_SPEC,\n" +
                "       '������Ϣϵͳ��' ADDRES,\n" +
                "       '42610000.�����ƶ�������Ϣϵͳ��' DEPT,\n" +
                "       '����' USER_NAME,\n" +
                "       '����' M_USER,\n" +
                "       '�����ʲ�' REMARK\n" ;
                //"  FROM AMS_EMAIL_CONFIG";
		List sqlArgs = new ArrayList();
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
