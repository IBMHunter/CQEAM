package com.sino.ams.system.manydimensions.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.match.amselementmatch.dto.AmsElementMatchDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.dto.BaseUserDTO;

/**
 * User: ����
 * Date: 2009-6-16
 * Time: 17:32:55
 * Function:		ҵ��ƽ̨����ά��
 */
public class OpeModel extends AMSSQLProducer {
    private AmsElementMatchDTO dto = null;


    public OpeModel(BaseUserDTO userAccount, AmsElementMatchDTO dtoParameter) {
        super(userAccount, dtoParameter);
        this.dto =  dtoParameter;
    }

    /**
	 * 
	 * Function:		��ѯ����ҵ��ƽ̨���Լ�¼��ҳ����
	 * @return			SQLModel   ����ҳ�淭ҳ��ѯSQLModel
	 * @author  		����
	 * @Version 		0.1
	 * @Date:   		Apr 27, 2009
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT AO.AMS_OPE_ID, \n" +
				        "       AO.AMS_OPE_ID ROWNUM, \n" +
				        "       AO.OPE_CODE, \n" +
				        "       AO.OPE_NAME\n" +
				        "  FROM AMS_OPE AO\n" +
				        " WHERE (" + SyBaseSQLUtil.nullStringParam() + " OR AO.OPE_CODE LIKE ? )\n" +
				        "   AND (" + SyBaseSQLUtil.nullStringParam() + " OR AO.OPE_NAME LIKE ? )";
		
		SyBaseSQLUtil.nullStringParamArgs(sqlArgs, dto.getOpeCode() );
		SyBaseSQLUtil.nullStringParamArgs(sqlArgs, dto.getOpeName() );
//		sqlArgs.add(dto.getOpeCode());
//		sqlArgs.add(dto.getOpeName());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * ���ܣ�	ͨ������Զ�����SQLModel, ɾ��ҵ��ƽ̨���ԡ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		
		String[] tmp = dto.getAmsOpeId().split(",");
		String amsOpeId = "";
		for (int i = 0; i < tmp.length; i++) {
			amsOpeId += "'" + tmp[i] + "',";
		}
		amsOpeId = amsOpeId.substring(0, amsOpeId.length() - 1);
		
		String sqlStr = "DELETE FROM"
			+ " 				AMS_OPE "
			+ " 		 WHERE"
			//+ " 				AMS_OPE_ID IN ('" + dto.getAmsOpeId() + "')";
			+ " 				AMS_OPE_ID IN (" + amsOpeId + ")";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;		
	}
	
	/**
	 * Function:			�õ�ѡ����ҵ��ƽ̨���Լ�¼
	 * @return SQLModel 	ɾ����SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr =  "";
		sqlArgs.add(dto.getAmsOpeId());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 *
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO AMS_OPE\n" +
						"  (AMS_OPE_ID, OPE_CODE, OPE_NAME)\n" +
						" VALUES\n" +
						"  (NEWID(), ?, ?)";
		sqlArgs.add(dto.getOpeCode());
		sqlArgs.add(dto.getOpeName());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * ���ܣ������ж�ҵ��ƽ̨�����Ƿ���ڵ�SQL
	 * @return SQLModel
	 */
	public SQLModel getObjectEsistModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " 1"
						+ " FROM"
						+ " AMS_OPE AO"
						+ " WHERE"
						+ " AO.OPE_CODE = ?";
		sqlArgs.add(dto.getOpeCode());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	
	

}
