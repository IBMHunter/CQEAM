package com.sino.ams.print.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.print.dto.BarcodeMaxMaintainDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.sql.BaseSQLProducer;

public class BarcodeMaxMaintainModel extends BaseSQLProducer {

	public BarcodeMaxMaintainModel(BaseUserDTO userAccount, BarcodeMaxMaintainDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}
	
	private BarcodeMaxMaintainDTO dto = (BarcodeMaxMaintainDTO) super.dtoParameter;
	
	/**
	 * Function:		����OU��ѯ���д�ӡ������ǩ�б�
	 * @param organizationId	OU���
	 * @return			SQLModel   ����ҳ�淭ҳ��ѯSQLModel
	 * @author  		����
	 * @Version 		0.1
	 * @Date:   		Apr 27, 2009
	 */
	public SQLModel getPageQueryModel() {		
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT MAX(ATNS.TAG_SEQ)+1 TAG_SEQ, ATNS.COMPANY_ID, ATNS.ASSETS_TYPE, " +
				"					(SELECT EOCM.COMPANY FROM ETS_OU_CITY_MAP EOCM  WHERE ATNS.COMPANY_ID = EOCM.COMPANY_CODE) COMPANY_NAME"
						+	"	FROM AMS_TAG_NUM_SEQ ATNS" 
						+	"	WHERE ATNS.COMPANY_ID = dbo.NVL(?, ATNS.COMPANY_ID)"
						+	"	AND ATNS.ASSETS_TYPE LIKE dbo.NVL(?, ATNS.ASSETS_TYPE) " 
						+	"	GROUP BY ATNS.COMPANY_ID, ATNS.ASSETS_TYPE";		
		sqlArgs.add(dto.getCompanyId());
		sqlArgs.add(dto.getAssetsType());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * Function:			�õ�ѡ������ǩ��¼
	 * @return SQLModel 	ɾ����SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr =  "SELECT ATNS.TAG_SEQ+1 TAG_SEQ, ATNS.COMPANY_ID, ATNS.ASSETS_TYPE, EOCM.COMPANY COMPANY_NAME"
						+		"	FROM AMS_TAG_NUM_SEQ ATNS, ETS_OU_CITY_MAP EOCM"
						+		"	WHERE ATNS.COMPANY_ID = EOCM.COMPANY_CODE"
						+		"	  AND ATNS.COMPANY_ID = dbo.NVL(?, ATNS.COMPANY_ID)"
						+		"	  AND ATNS.TAG_SEQ = CONVERT(INT, dbo.NVL(?, CONVERT(VARCHAR, ATNS.TAG_SEQ)))	"
						+		"	  AND ATNS.ASSETS_TYPE = dbo.NVL(?, ATNS.ASSETS_TYPE)";
		sqlArgs.add(dto.getCompanyId());
		sqlArgs.add(StrUtil.strToInt(dto.getTagSeq()));
		sqlArgs.add(dto.getAssetsType());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	

    /**
	 * Function��		�޸�ָ������ǩ��¼��
	 * @return SQLModel	�޸���SQLModel
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE " +
				"			AMS_TAG_NUM_SEQ " +
				"				SET TAG_SEQ = ? " +
				"					WHERE COMPANY_ID = ? " +
				"					  AND ASSETS_TYPE = ?";
		sqlArgs.add((Integer.parseInt(dto.getTagSeq())-1));
        sqlArgs.add(dto.getCompanyId());
		sqlArgs.add(dto.getAssetsType());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * 
	 * Function:		Ϊ��������ǩά���ṩSQLModel
	 * @return			SQLModel
	 * @author  		����
	 * @Version 		0.1
	 * @Date:   		May 1, 2009
	 */
	public SQLModel getBarcodeMaxMaintainModel() { 
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT MAX(ATNS.TAG_SEQ)+1 TAG_SEQ, ATNS.COMPANY_ID, ATNS.ASSETS_TYPE, " +
		"					(SELECT EOCM.COMPANY FROM ETS_OU_CITY_MAP EOCM  WHERE ATNS.COMPANY_ID = EOCM.COMPANY_CODE) COMPANY_NAME"
				+	"	FROM AMS_TAG_NUM_SEQ ATNS" 
				+	"	WHERE ATNS.COMPANY_ID = dbo.NVL(?, ATNS.COMPANY_ID)"
				+	"	AND ATNS.ASSETS_TYPE LIKE dbo.NVL(?, ATNS.ASSETS_TYPE) " 
				+	"	GROUP BY ATNS.COMPANY_ID, ATNS.ASSETS_TYPE";		
		sqlArgs.add(dto.getCompanyId());
		sqlArgs.add(dto.getAssetsType());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
