package com.sino.ams.instrument.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.instrument.dto.AmsInstrumentEamYbMaintainDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;

public class AmsInstrumentEamYbMaintainReturnModel extends AMSSQLProducer {
	
	/**
     * ���ܣ������Ǳ����(EAM) ETS_ITEM_INFO  ETS_SYSTEM_ITEM   EAM_YB_MAINTAIN  ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsInstrumentEamYbMaintainDTO ���β���������
     */
	
	public AmsInstrumentEamYbMaintainReturnModel(SfUserDTO userAccount, AmsInstrumentEamYbMaintainDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsInstrumentEamYbMaintainDTO dto = (AmsInstrumentEamYbMaintainDTO)dtoParameter;
        String sqlStr = "SELECT"
			        	+ " EII.BARCODE,"
			        	+ " EII.VENDOR_BARCODE,"
			        	+ " EII.ATTRIBUTE3,"
			        	+ " ESI.ITEM_CATEGORY2,"
			        	+ " ESI.ITEM_NAME,"
			        	+ " ESI.ITEM_SPEC,"
			        	+ " EYM.REPAIR_DATE,"
			        	+ " EYM.RETURN_DATE_PLAN,"
			        	+ " EYM.STATUS,"
			        	+ " EII.START_DATE"
						+ " FROM"
						+ " ETS_ITEM_INFO EII,"
						+ " EAM_YB_MAINTAIN EYM,"
						+ " ETS_SYSTEM_ITEM ESI"
						+ " WHERE"
						+ " EII.BARCODE = EYM.BARCODE"
						+ " AND EII.ITEM_CODE = ESI.ITEM_CODE"
						+ " AND EYM.STATUS = 'YB_STATUS_REPAIR'"
						+ " AND EYM.ORG_ID = ?"
    					+ " AND EYM.BARCODE LIKE dbo.NVL(?, EYM.BARCODE)"
						+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_CATEGORY2 LIKE ?)"
    					+ " AND ESI.ITEM_NAME LIKE dbo.NVL(?, ESI.ITEM_NAME)"
						+ " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?)";
        
        sqlArgs.add(userAccount.getOrganizationId());
        sqlArgs.add(dto.getBarcode());
        sqlArgs.add(dto.getItemCategory2());
        sqlArgs.add(dto.getItemCategory2());
        sqlArgs.add(dto.getItemName());
        sqlArgs.add(dto.getItemSpec());
        sqlArgs.add(dto.getItemSpec());
        
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}
	
	//�õ����޵������Ǳ��
	public SQLModel getInsSendRepairReturnInvModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
			+ " EO.WORKORDER_OBJECT_NO"
			+ " FROM"
			+ " ETS_OBJECT EO"
			+ " WHERE"
			+ " EO.ORGANIZATION_ID = ?"
			+ " AND EO.OBJECT_CATEGORY = ?"
			+ " AND EO.BUSINESS_CATEGORY = ?";
		sqlArgs.add(userAccount.getOrganizationId());
		sqlArgs.add(DictConstant.INV_NORMAL);
		sqlArgs.add("INV_BIZ_INSTRU");
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
