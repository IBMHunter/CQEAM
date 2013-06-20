package com.sino.ams.workorder.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.record.formula.functions.If;

import com.sino.ams.workorder.dto.ZeroTurnBursurHDTO;
import com.sino.ams.workorder.dto.ZeroTurnLineDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

public class ZeroTurnBursurModel  extends BaseSQLProducer{
	
	private SfUserBaseDTO sfUser = null;
	private ZeroTurnBursurHDTO deptGroup = null;
	
	public ZeroTurnBursurModel(BaseUserDTO userAccount, DTO dtoParameter) {
		super(userAccount, dtoParameter);
		// TODO Auto-generated constructor stub
	}


	/**
	 * ���ܣ�����Զ������������ SF_GROUPҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		ZeroTurnBursurHDTO lineDTO = (ZeroTurnBursurHDTO) dtoParameter;
		String startDate=lineDTO.getCreationBeginDate();
		String endDate=lineDTO.getCreationEndDate();
		String status=lineDTO.getArrivalStatus();
		
	    String sqlStr = "  SELECT EIT.MIS_PROCURE_CODE," +
	    		"EIT.BARCODE," +
	    		"EIT.ARRIVAL_STATUS, " +//--- ԭ��ΪREIMBURSE_STATUS
	    		" EIT.ARRIVAL_STATUS_NAME, " +//--- ԭ��ΪREIMBURSE_STATUS_NAME
	    		" EIT.ARRIVAL_DATE,  " +//--- ԭ��ΪREIMBURSE_DATE,
	    		" EIT.ASSETS_DESCRIPTION," +
	    		" EIT.ITEM_SPEC," +
	    		" EIT.OBJECT_NO, " +
	    		"  EIT.RESPONSIBILITY_USER," +
	    		" EIT.RESPONSIBILITY_DEPT," +
	    		"  EIT.PRICE," +
	    		" CASE WHEN EIT.ARRIVAL_DATE>=(SELECT PERIOD_OPEN_DATE FROM SRV_ASSET_PERIOD_STATUS " +
	    		" WHERE PERIOD_STATUS = 'OPEN' AND BOOK_TYPE_CODE = 'CQMC_FA_3910') THEN 'Y' ELSE 'N' END IS_PERIOD," +
	    		" CASE WHEN EII.ITEM_STATUS = 'PRE_ASSETS' THEN 'δ����' ELSE '�ѵ���' END IS_RECEIVED,  " +//-- ������ʾ���ֶ�
	    		"  EIT.COST_CENTER_NAME, " +//-- ������ʾ���ֶ�
	    		"  EIT.MANUFACTURER_NAME " +//-- ������ʾ���ֶ�
	    		" FROM " +
	    		" ETS_ITEM_TURN EIT, ETS_ITEM_INFO EII" +
	    		" WHERE" +
	    		" EII.BARCODE = EIT.BARCODE " +
	    		"  AND EIT.ARRIVAL_STATUS   = dbo.NVL(?, EIT.ARRIVAL_STATUS) ";
	    		sqlArgs.add(status);
	    		if(!lineDTO.getCostCenterName().equals(null)&&!lineDTO.getCostCenterName().equals("")){
	    			sqlStr+="  AND EIT.COST_CENTER_NAME LIKE dbo.NVL(?, EIT.COST_CENTER_NAME)" ;
	    			sqlArgs.add(lineDTO.getCostCenterName());
	    		}
	    		if(!lineDTO.getManufacturerName().equals(null)&&!lineDTO.getManufacturerName().equals("")){
	    			sqlStr+="  AND EIT.MANUFACTURER_NAME LIKE dbo.NVL(?, EIT.MANUFACTURER_NAME)" ;
	    			sqlArgs.add(lineDTO.getManufacturerName());
	    		}
	    		if(!lineDTO.getMisProcureCode().equals(null)&&!lineDTO.getMisProcureCode().equals("")){
	    			sqlStr+="  AND EIT.MIS_PROCURE_CODE LIKE dbo.NVL(?, EIT.MIS_PROCURE_CODE)";
	    			sqlArgs.add(lineDTO.getMisProcureCode());
	    		}
	    		
//	    		"  AND EIT.CREATION_DATE >= ? AND EIT.CREATION_DATE <= ?" +
	    		
	    if(startDate.equals("")&&endDate.equals("")){
	    	
	    }else if(startDate.equals("")&&!endDate.equals("")){
	    	sqlStr+=" AND EIT.CREATION_DATE<?";
	    	sqlArgs.add(endDate);
	    }else if(!startDate.equals("")&&endDate.equals("")){
	    	sqlStr+=" AND EIT.CREATION_DATE>?";
	    	sqlArgs.add(startDate);
	    }else {
	    	sqlStr+=" AND EIT.CREATION_DATE>? AND EIT.CREATION_DATE<?";
	    	sqlArgs.add(startDate);
	    	sqlArgs.add(endDate);
	    }
	   
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	
	public SQLModel reimburser(String barcode){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
	    String sqlStr = 
					    " UPDATE ETS_ITEM_TURN SET  \n"+
					    " ARRIVAL_STATUS='Y',\n"+
					    " ARRIVAL_DATE=getdate(),\n"+
					    " ARRIVAL_STATUS_NAME='�ѷ���'\n"+
					    " WHERE BARCODE=?\n";

	    sqlArgs.add(barcode);
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	
	public SQLModel reimburserCancle(String barcode){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
	    String sqlStr = 
					    " UPDATE ETS_ITEM_TURN SET  \n"+
					    " ARRIVAL_STATUS='N',\n"+
					    " ARRIVAL_DATE=null,\n"+
					    " ARRIVAL_STATUS_NAME='δ����'\n"+
					    " WHERE BARCODE=?\n";

	    sqlArgs.add(barcode);
        sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
