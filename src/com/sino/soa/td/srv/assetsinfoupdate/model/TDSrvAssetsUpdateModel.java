package com.sino.soa.td.srv.assetsinfoupdate.model;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.soa.td.srv.assetsinfoupdate.dto.TDSrvEamSyschronizeDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.util.StrUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: AssetsUpdateModel</p>
 * <p>Description:�����Զ�����SQL��������AssetsUpdateModel��</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-26
 * Function:�ʲ�������Ϣ�޸�_TD
 */
public class TDSrvAssetsUpdateModel extends AMSSQLProducer {
	
	
	public TDSrvAssetsUpdateModel(SfUserDTO userAccount, TDSrvEamSyschronizeDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ����:��ȡ�ʲ�������󼯺�
	 * @param assetid(MIS�̶��ʲ���)
	 * @return
	 */
	public SQLModel getAssetsUpdateDTO(String assetid){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT " +
				"ESI.ITEM_SPEC,\n" +
				"EFA.MANUFACTURER_NAME,\n" +
				"EFA.BOOK_TYPE_CODE,\n" +
				"EFA.ASSETS_DESCRIPTION,\n" +
				"ESI.ITEM_NAME,\n" +
				"EFA.MODEL_NUMBER,\n" +
				"AM.MANUFACTURER_NAME as EAMMANUFNAME,\n" +
				"EII.BARCODE,\n" +
				"EFA.TAG_NUMBER," +
				"EFA.ORGANIZATION_ID,\n" +
				//��������
				"EII.CONSTRUCT_STATUS,\n"+
				"EII.OPE_ID,\n"+
				"EII.NLE_ID,\n"+
				"EII.CEX_ID \n"+
				
				"FROM \n " +
				"ETS_ITEM_INFO EII," +
				"AMS_MANUFACTURER AM, \n" +
				"ETS_FA_ASSETS_TD EFA," +
				"ETS_ITEM_MATCH_TD EIM," +
				"ETS_SYSTEM_ITEM ESI \n" +
				"WHERE\n" +
				"AM.MANUFACTURER_ID = EII.MANUFACTURER_ID " +
				"AND EIM.ASSET_ID = EFA.ASSET_ID " +
				"AND EII.SYSTEMID = EIM.SYSTEMID \n" +
				"AND ESI.ITEM_CODE = EII.ITEM_CODE " +
				"AND EFA.ASSET_ID = CONVERT(INT,?) ";
		
		sqlArgs.add(assetid);
		sqlModel.setArgs(sqlArgs);
		sqlModel.setSqlStr(sqlStr);
		System.out.println(sqlStr);
		return sqlModel;
	}
	
	/**
	 * ����:�õ������ʲ��˲�
	 * @return
	 */
	public SQLModel  getBookTypeCode(){
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT BOOK_TYPE_CODE, COMPANY_CODE + STR_REPLACE(COMPANY, 'OU_', ' ') COMPANY\n" +
            "  FROM ETS_OU_CITY_MAP";
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
        
	}
}



