package com.sino.ams.newasset.report.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;


/**
 * <p>Title: DonateAssetsReportModel</p>
 * <p>Description:�����Զ�����SQL��������DonateAssetsReportModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class DonateAssetsReportModel extends AMSSQLProducer {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ������ʲ�ͳ�� ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsHouseInfoDTO ���β���������
	 */
	public DonateAssetsReportModel(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter) {
		super(userAccount, dtoParameter);
		sfUser = userAccount;
	}
	
	/**
	 * ���ܣ�����Զ����ɾ����ʲ�ͳ�� ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getPageQueryModel() throws SQLModelException{ 
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		String sqlStr = "SELECT   EOCM.COMPANY,\n"		+
							"	  AMD.DEPT_NAME,\n"		+
				           "      ESI.ITEM_NAME,\n" +
				           "      ESI.ITEM_SPEC,\n" +
				           "      EFAHR.ASSET_ID,\n" +
				           "      EII.BARCODE,\n" +
				           "       1 ITEM_QTY,\n" +
				           "	  EFAHR.COST\n"	+
				          "  FROM ETS_ITEM_INFO    		EII,\n" +
				           "       ETS_SYSTEM_ITEM  		ESI,\n" +
				           "       ETS_FA_ASSETS_HIS_REP    EFAHR,\n" +
				           "       AMS_MIS_DEPT     		AMD,\n" +
		                    "      ETS_OU_CITY_MAP 			EOCM,\n" +
		                    "      ETS_ITEM_MATCH  			EIM,\n" +
		                    "	   ETS_ITEM_INFO_ATTR_CHG   EIIAC\n"	+
				           " WHERE EII.ITEM_STATUS = 'DONATE'\n" +
				           "   AND EFAHR.TAG_NUMBER = EIIAC.BAR_CODE\n"	+
                    "          AND EII.SYSTEMID = EIM.SYSTEMID\n" +
                    "          AND EFAHR.ASSET_ID = EIM.ASSET_ID\n" +
                    "   	   AND ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                    "          AND EOCM.ORGANIZATION_ID = EFAHR.ORGANIZATION_ID\n" +
                    "          AND EII.RESPONSIBILITY_DEPT *= AMD.DEPT_CODE\n" +
                    "		   AND EFAHR.PERIOD_NAME = EIIAC.PERIOD_NAME \n"	+
			        "          AND EFAHR.PERIOD_NAME = ?\n" +

                    "          AND ( " + SyBaseSQLUtil.isNull() + "  OR EOCM.ORGANIZATION_ID = ?)\n" +       
                   "   		   AND AMD.DEPT_CODE = ISNULL(?, AMD.DEPT_CODE)" +
                   "   		   AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_NAME LIKE ?)"	+
                   "   		   AND ( " + SyBaseSQLUtil.isNull() + "  OR EII.BARCODE LIKE ?) "	+
                   "     ORDER BY EII.SYSTEMID";
		
//		 sqlArgs.add(dto.getPeriodNameByHisRep());
         sqlArgs.add(dto.getOrganizationId());
         sqlArgs.add(dto.getOrganizationId());
	      
	     sqlArgs.add(dto.getResponsibilityDept());
	     sqlArgs.add(dto.getItemName());
	     sqlArgs.add(dto.getItemName());
	     sqlArgs.add(dto.getBarcode());
	     sqlArgs.add(dto.getBarcode());

         sqlModel.setSqlStr(sqlStr);
		 sqlModel.setArgs(sqlArgs);
		
		return sqlModel;
	}

}
