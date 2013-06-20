package com.sino.ams.system.switches.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.util.ArrUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * <p>Title: EtsObjectModel</p>
 * <p>Description:�����Զ�����SQL��������EtsObjectModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsObjectModel extends BaseSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ��ʲ��ص��(EAM) ETS_OBJECT ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectDTO ���β���������
	 */
	public EtsObjectModel(SfUserDTO userAccount, EtsObjectDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}

	public SQLModel getCategoryNameModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
		String sqlStr = "SELECT "
						+ " EFV.VALUE"
						+ " FROM"
						+ " ETS_FLEX_VALUES EFV,"
						+ " ETS_FLEX_VALUE_SET EFVS"
						+ " WHERE"
						+ " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFV.CODE = ?"
                        + " AND EFVS.CODE = ?";
		sqlArgs.add(etsObject.getObjectCategory());
        sqlArgs.add(DictConstant.OBJECT_CATEGORY);
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�ִ���������ݲ�����
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
		String sqlStr = "INSERT INTO \n"
						+ " ETS_OBJECT(\n"
						+ " WORKORDER_OBJECT_NO,\n"
						+ " WORKORDER_OBJECT_CODE,\n"
						+ " WORKORDER_OBJECT_NAME,\n"
						+ " WORKORDER_OBJECT_LOCATION,\n"
						+ " ORGANIZATION_ID,\n"
						+ " COUNTY_CODE,\n"
						+ " REMARK,\n"
						+ " OBJECT_CATEGORY,\n"
						+ " ISALL,\n"
						+ " CREATION_DATE,\n"
						+ " CREATED_BY,\n"
						+ " PROJECT_ID,\n"
                        + " LOCATION_CODE,"
                        + " LAST_UPDATE_DATE\n"
						+ ") VALUES ("
						+ " ?, ?, ?, ?, ?, ?, ?, ?, ?,  GETDATE(), ?, ?, ?,GETDATE())";

        sqlArgs.add(etsObject.getWorkorderObjectNo());
		sqlArgs.add(etsObject.getWorkorderObjectCode());
		sqlArgs.add(etsObject.getWorkorderObjectName());
		sqlArgs.add(etsObject.getWorkorderObjectLocation());
        if(StrUtil.isEmpty(etsObject.getOrganizationId())){   //���Ӵ��ж��Ƿ������صص㵼������
            sqlArgs.add(sfUser.getOrganizationId());
        }else{
            sqlArgs.add(etsObject.getOrganizationId());
        }
		sqlArgs.add(etsObject.getCountyCode());
//		sqlArgs.add(etsObject.getDisableDate());
		sqlArgs.add(etsObject.getRemark());
		sqlArgs.add(etsObject.getObjectCategory());
		sqlArgs.add(etsObject.getIsall());
		sqlArgs.add(sfUser.getUserId());
		sqlArgs.add(etsObject.getProjectId());
        sqlArgs.add(etsObject.getWorkorderObjectCode());

        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

/**
	 * ���ܣ�ִ�������޸Ĳ�����
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
		String sqlStr = "UPDATE ETS_OBJECT"
						+ " SET"
						+ " WORKORDER_OBJECT_CODE = ?,"
						+ " WORKORDER_OBJECT_NAME = ?,"
						+ " WORKORDER_OBJECT_LOCATION = ?,"
						+ " COUNTY_CODE = ?,"
						+ " REMARK = ?,"
						+ " ISALL = ?,"
//						+ " IS_TEMP_ADDR = ?,"
						+ " LAST_UPDATE_DATE = GETDATE(),"
						+ " LAST_UPDATE_BY = ?,"
						+ " PROJECT_ID = ?"
						+ " WHERE"
						+ " WORKORDER_OBJECT_NO = ?";

		sqlArgs.add(etsObject.getWorkorderObjectCode());
		sqlArgs.add(etsObject.getWorkorderObjectName());
		sqlArgs.add(etsObject.getWorkorderObjectLocation());
		sqlArgs.add(etsObject.getCountyCode());
		sqlArgs.add(etsObject.getRemark());
		sqlArgs.add(etsObject.getIsall());
//		sqlArgs.add(etsObject.getIsTempAddr());
		sqlArgs.add(sfUser.getOrganizationId());
		sqlArgs.add(etsObject.getProjectId());
		sqlArgs.add(etsObject.getWorkorderObjectNo());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�ִ�е���ʧЧ������
	 * @return SQLModel ��������ʧЧ��SQLModel
	 */
	public SQLModel getDataDeleteModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
		String sqlStr = "UPDATE "
						+ " ETS_OBJECT"
						+ " SET"
						+ " DISABLE_DATE = GETDATE()"
						+ " WHERE"
						+ " WORKORDER_OBJECT_NO = ?";
		sqlArgs.add(etsObject.getWorkorderObjectNo());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�ִ����ϸ�����õ�SQLMODEL��
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel() { //����ϸ
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
		String sqlStr = "SELECT "
						+ " EO.WORKORDER_OBJECT_NO,"
						+ " EO.WORKORDER_OBJECT_CODE,"
						+ " EO.WORKORDER_OBJECT_NAME,"
						+ " EO.WORKORDER_OBJECT_LOCATION,"
						+ " EO.ORGANIZATION_ID,"
						+ " EO.COUNTY_CODE,"
						+ " EO.DISABLE_DATE,"
						+ " EO.REMARK,"
						+ " EO.OBJECT_CATEGORY,"
						+ " EO.ISALL,"
						+ " EO.IS_TEMP_ADDR,"
						+ " EO.CREATION_DATE,"
						+ " EO.CREATED_BY,"
						+ " EO.LAST_UPDATE_DATE,"
						+ " EO.LAST_UPDATE_BY,"
						+ " EO.PROJECT_ID,"
                        + " AMS_PUB_PKG.GET_PROJECT_NAME(EO.PROJECT_ID) PROJECT_NAME"
//                        + " EPPA.NAME PROJECT_NAME"
						+ " FROM"
						+ " ETS_OBJECT EO"
//						+ " ETS_PA_PROJECTS_ALL EPPA"
						+ " WHERE"
//						+ " EO.PROJECT_ID = EPPA.PROJECT_ID"
						+ " EO.WORKORDER_OBJECT_NO = ?";
		sqlArgs.add(etsObject.getWorkorderObjectNo());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


	/**
	 * ���ܣ���ѯ�õ�SQLMOL��
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() { //��ѯ
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
		String sqlStr = "SELECT "
						+ " EO.WORKORDER_OBJECT_NO,"
						+ " EO.WORKORDER_OBJECT_CODE,"
						+ " EO.WORKORDER_OBJECT_NAME,"
						+ " EO.WORKORDER_OBJECT_LOCATION,"
						+ " EO.ORGANIZATION_ID,"
						+ " EC.COUNTY_NAME,"
						+ " EO.DISABLE_DATE,"
						+ " EO.REMARK,"
						+ " EO.OBJECT_CATEGORY,"
						+" CASE WHEN EO.ISALL='0' THEN 'Ѳ�챾רҵ�豸' WHEN EO.ISALL='1' THEN 'Ѳ������רҵ�豸'  END ISALL,"
						+ " EO.IS_TEMP_ADDR,"
						+ " EO.CREATION_DATE,"
						+ " EO.CREATED_BY,"
						+ " EO.LAST_UPDATE_DATE,"
						+ " EO.LAST_UPDATE_BY,"
						+ " EO.PROJECT_ID,"
                        + " AMS_PUB_PKG.GET_PROJECT_NAME(EO.PROJECT_ID) PROJECT_NAME,"
//                        + " EPPA.NAME PROJECT_NAME,"
						+ " EFV.VALUE CATEGORY_NAME"
						+ " FROM"
						+ " ETS_OBJECT EO,"
//						+ " ETS_PA_PROJECTS_ALL EPPA,"
						+ " ETS_FLEX_VALUES EFV,"
						+ " ETS_FLEX_VALUE_SET EFVS,"

                        + " ETS_OU_CITY_MAP    EOCM,"

						+ " ETS_COUNTY EC"
						+ " WHERE"
//						+ " EO.PROJECT_ID = EPPA.PROJECT_ID"
						+ "  EO.OBJECT_CATEGORY = EFV.CODE"
						+ " AND EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
						+ " AND EFVS.CODE = ?"
						+ " AND EO.COUNTY_CODE *= EC.COUNTY_CODE"
                        + " AND EO.ORGANIZATION_ID = EOCM.ORGANIZATION_ID \n "
                        + " AND EC.COMPANY_CODE = EOCM.COMPANY_CODE \n "
						+ " AND EO.WORKORDER_OBJECT_CODE LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_CODE)"
						+ " AND EO.WORKORDER_OBJECT_NAME LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_NAME)"
						+ " AND EO.WORKORDER_OBJECT_LOCATION LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_LOCATION)"
						+ " AND EO.COUNTY_CODE LIKE dbo.NVL(?, EO.COUNTY_CODE)"
						+ " AND EO.OBJECT_CATEGORY = ?"
						+ " AND EO.ORGANIZATION_ID = ?"
                        + " ORDER BY EO.WORKORDER_OBJECT_CODE DESC";;
		sqlArgs.add(DictConstant.OBJECT_CATEGORY);
		sqlArgs.add(etsObject.getWorkorderObjectCode());
		sqlArgs.add(etsObject.getWorkorderObjectName());
		sqlArgs.add(etsObject.getWorkorderObjectLocation());
		sqlArgs.add(etsObject.getCountyCode());
		sqlArgs.add(etsObject.getObjectCategory());
		sqlArgs.add(sfUser.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�ִ������ʧЧ������
	 * @param  workorderObjectNos
	 * @return SQLModel ����������SQLModel
	 */
	public SQLModel getDisabledModel(String[] workorderObjectNos) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String orderno = ArrUtil.arrToSqlStr(workorderObjectNos);
		String sqlStr = "UPDATE "
						+ " ETS_OBJECT"
						+ " SET"
						+ " DISABLE_DATE = GETDATE(),LAST_UPDATE_DATE=GETDATE(),LAST_UPDATE_BY= "+sfUser.getUserId()
						+ " WHERE"
						+ " WORKORDER_OBJECT_NO IN (" + orderno + ")";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

/**
	 * ���ܣ�ִ������ʧЧ������
	 * @param  workorderObjectNos
	 * @return SQLModel ����������SQLModel
	 */
	public SQLModel getEfficientModel(String[] workorderObjectNos) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String orderno = ArrUtil.arrToSqlStr(workorderObjectNos);
		String sqlStr = "UPDATE "
						+ " ETS_OBJECT"
						+ " SET"
						+ " DISABLE_DATE = NULL,LAST_UPDATE_DATE=GETDATE(),LAST_UPDATE_BY= "+sfUser.getUserId()
						+ " WHERE"
						+ " WORKORDER_OBJECT_NO IN (" + orderno + ")";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

/**
	 * ���ܣ�ִ����ЧЧ������
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getInureModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		EtsObjectDTO etsObject = (EtsObjectDTO) dtoParameter;
		String sqlStr = "UPDATE "
						+ " ETS_OBJECT"
						+ " SET"
						+ " DISABLE_DATE = NULL,LAST_UPDATE_DATE=GETDATE(),LAST_UPDATE_BY= "+sfUser.getUserId()
						+ " WHERE"
						+ " WORKORDER_OBJECT_NO = ?";
		sqlArgs.add(etsObject.getWorkorderObjectNo());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

    	/**
	 * ���ܣ���ȡ�жϵ�ǰ�û��Ƿ���Ȩ��ִ�����ݱ༭������BARCODE �Ĵ�����
	 * @return SQLModel
	 */
	public SQLModel getVerifyWorkNoModel(String workorderObjectCode) {
        SQLModel sqlModel = new SQLModel();
        List strArg = new ArrayList();
        String strSql ="SELECT 1 FROM ETS_OBJECT EO WHERE EO.WORKORDER_OBJECT_CODE = ?";
        strArg.add(workorderObjectCode);
        sqlModel.setSqlStr(strSql);
        sqlModel.setArgs(strArg);
        return sqlModel;
    }


   	/**
	 * ���ܣ���ȡ�жϵ�ǰ�û��Ƿ���Ȩ��ִ�����ݱ༭������BARCODE �Ĵ�����
	 * @return SQLModel
	 */
	public SQLModel getVerifyBarcode(String[] workorderObjectNos) {
        SQLModel sqlModel = new SQLModel();
        List strArg = new ArrayList();
        String orderno = ArrUtil.arrToSqlStr(workorderObjectNos);
        String strSql ="SELECT EII.BARCODE\n" +
                "  FROM ETS_SYSTEM_ITEM    ESI,\n" +
                "       ETS_ITEM_INFO      EII,\n" +
                "       AMS_OBJECT_ADDRESS AOA,\n" +
                "       ETS_OBJECT         EO\n" +
                " WHERE ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                "   AND EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                "   AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                "   AND EO.WORKORDER_OBJECT_NO IN ("+orderno+")";
//        strArg.add(workorderObjectCode);
        sqlModel.setSqlStr(strSql);
        sqlModel.setArgs(strArg);
        return sqlModel;
    }

      /**
     * ���ܣ�����Զ������ʲ��ص��(EAM) ETS_OBJECT���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getAOACreateModel(){
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EtsObjectDTO etsObject = (EtsObjectDTO)dtoParameter;
        String sqlStr = "INSERT INTO "
            + " AMS_OBJECT_ADDRESS("
            + " ADDRESS_ID,"
            + " OBJECT_NO,"
            + " ORGANIZATION_ID"
            + ") VALUES ("
            + "  NEWID() , ?, ?)";

        sqlArgs.add(etsObject.getWorkorderObjectNo());
        sqlArgs.add(sfUser.getOrganizationId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
