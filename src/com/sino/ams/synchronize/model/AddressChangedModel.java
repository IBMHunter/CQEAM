package com.sino.ams.synchronize.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.synchronize.dto.EamSyschronizeDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

/**
 * Created by IntelliJ IDEA.
 * User: jialongchuan
 * Date: 2008-11-26
 * Time: 12:00:13
 * To change this template use File | Settings | File Templates.
 */

public class AddressChangedModel extends AMSSQLProducer {
	private EamSyschronizeDTO dto = null;

/**
	 * ���ܣ��ص���Ϣ���ͬ�� ���ݿ�SQL����㹹�캯��
	 *
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemMatchDTO ���β���������
	 */
	public AddressChangedModel(SfUserDTO userAccount, EamSyschronizeDTO dtoParameter) {
		super(userAccount, dtoParameter);
		dto = dtoParameter;
	}

/**
	 * ���ܣ�����Զ�����LOCUSҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 *
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr;
		sqlStr = "SELECT EO.WORKORDER_OBJECT_CODE,\n" +
				 "       EO.WORKORDER_OBJECT_NAME,\n" +
				 "       EFAL.ASSETS_LOCATION,\n" +
				 "       SU.USERNAME\n" +
				 "FROM   ETS_OBJECT             EO,\n" +
				 "       ETS_FA_ASSETS_LOCATION EFAL,\n" +
				 "       SF_USER                SU\n" +
				 "WHERE  EO.WORKORDER_OBJECT_CODE = EFAL.ASSETS_LOCATION_CODE\n" +
				 "AND    EO.ORGANIZATION_ID = EFAL.ORG_ID\n" +
				 "AND    EO.WORKORDER_OBJECT_NAME <> EFAL.ASSETS_LOCATION\n" +
				 "AND    EO.LAST_UPDATE_BY *= SU.USER_ID\n" +
				 "AND    EFAL.BOOK_TYPE_CODE LIKE '%FA%'\n" +
				 "AND    ( " + SyBaseSQLUtil.isNull() + "  OR EO.WORKORDER_OBJECT_CODE LIKE ?)\n" +
				 "AND    ( " + SyBaseSQLUtil.isNull() + "  OR EO.WORKORDER_OBJECT_NAME LIKE ?)\n" +
				 "AND    ( " + SyBaseSQLUtil.isNull() + "  OR EFAL.ASSETS_LOCATION LIKE ?)\n" +
				 "AND    ( " + SyBaseSQLUtil.isNull() + "  OR EO.LAST_UPDATE_BY LIKE ?)\n" +
				 "AND    EO.ORGANIZATION_ID=?\n";
		sqlArgs.add(dto.getWorkorderObjectCode());
		sqlArgs.add(dto.getWorkorderObjectCode());
		sqlArgs.add(dto.getWorkorderObjectName());
		sqlArgs.add(dto.getWorkorderObjectName());
		sqlArgs.add(dto.getAssetsLocation());
		sqlArgs.add(dto.getAssetsLocation());
		sqlArgs.add(dto.getLastUpdateBy());
		sqlArgs.add(dto.getLastUpdateBy());
		sqlArgs.add(userAccount.getOrganizationId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
