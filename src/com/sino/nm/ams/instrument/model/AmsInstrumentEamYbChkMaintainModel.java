package com.sino.nm.ams.instrument.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.nm.ams.instrument.dto.AmsInstrumentEamYbChkMaintainDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * Created by MyEclipse.
 * User: yushibo
 * Date: 2009-3-6
 * Time: 9:52:18
 * To change this template use File | Settings | File Templates.
 */
public class AmsInstrumentEamYbChkMaintainModel extends AMSSQLProducer {

	/**
	 * ���ܣ������Ǳ����(AMS) ETS_ITEM_INFO  ETS_SYSTEM_ITEM   EAM_YB_CHK_MAINSTAIN  ���ݿ�SQL����㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsInstrumentYbChkMaintainDTO ���β���������
	 */

	public AmsInstrumentEamYbChkMaintainModel(SfUserDTO userAccount, AmsInstrumentEamYbChkMaintainDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsInstrumentEamYbChkMaintainDTO dto = (AmsInstrumentEamYbChkMaintainDTO)dtoParameter;
		String sqlStr = "SELECT"
						+ " EII.BARCODE,"
						+ " EII.VENDOR_BARCODE,"
						+ " EII.ATTRIBUTE3,"
						+ " EII.ORGANIZATION_ID,"
//			        	+ " SU.USERNAME,"
						+ " dbo.APP_GET_USER_NAME(EYCM.CHECK_USER_ID) USERNAME,"
						+ " dbo.APP_GET_FLEX_VALUE(EYCM.CHECK_STATUS, 'YB_CHECK_RESULT') CHECK_STATUS_NAME,"
						+ " ESI.ITEM_CATEGORY2,"
						+ " ESI.ITEM_NAME,"
						+ " ESI.ITEM_SPEC,"
						+ " EYCM.CHECK_STATUS,"
						+ " EYCM.CHECK_USER_ID,"
						+ " EYCM.CHECK_DATE,"
						+ " EII.ITEM_STATUS,"
//						+ " EYCM.STATUS,"
						+ " EYCM.REMARK,"
						+ " EII.START_DATE"
						+ " FROM"
						+ " ETS_ITEM_INFO EII,"
						+ " EAM_YB_CHK_MAINSTAIN EYCM,"
//						+ " SF_USER SU,"
						+ " ETS_SYSTEM_ITEM ESI"
						+ " WHERE"
						+ " EII.BARCODE *= EYCM.BARCODE"
						+ " AND EII.ITEM_CODE = ESI.ITEM_CODE"
						+ " AND (EII.DISABLE_DATE IS NULL OR EII.DISABLE_DATE > GETDATE())"
						+ " AND ESI.ITEM_CATEGORY = 'YQYB'\n" +
                          " AND EII.ITEM_STATUS <>'DISCARDED'\n"
//						+ " AND EYCM.CHECK_USER_ID = SU.USER_ID"
//						+ " AND EYCM.TASK_ID =* ?"
						+ " AND (EYCM.TASK_ID IS NULL OR EYCM.TASK_ID = '' OR EYCM.TASK_ID = ?)"
						+ " AND EII.ORGANIZATION_ID=? "
						+ " AND EII.BARCODE LIKE dbo.NVL(?, EII.BARCODE)"
						+ " AND ("+ SyBaseSQLUtil.nullStringParam() +" OR ESI.ITEM_CATEGORY2 LIKE ?)";

		sqlArgs.add(dto.getTaskId());
		sqlArgs.add(userAccount.getOrganizationId());
		sqlArgs.add(dto.getBarcode1());
		sqlArgs.add(dto.getItemCategory2());
		sqlArgs.add(dto.getItemCategory2());
		sqlArgs.add(dto.getItemCategory2());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);

		return sqlModel;
	}
}
