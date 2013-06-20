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
 * Function:		����������ά��
 */
public class NleModel extends AMSSQLProducer {
    private AmsElementMatchDTO dto = null;


    public NleModel(BaseUserDTO userAccount, AmsElementMatchDTO dtoParameter) {
        super(userAccount, dtoParameter);
        this.dto =  dtoParameter;
    }

    /**
	 * 
	 * Function:		��ѯ�������������Լ�¼��ҳ����
	 * @return			SQLModel   ����ҳ�淭ҳ��ѯSQLModel
	 * @author  		����
	 * @Version 		0.1
	 * @Date:   		Apr 27, 2009
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT AN.AMS_LNE_ID ROWNUM,AN.AMS_LNE_ID, AN.LNE_CODE, AN.LNE_NAME"
					+	"  FROM AMS_NLE AN"
					+	" WHERE (" + SyBaseSQLUtil.nullStringParam() + " OR AN.LNE_CODE LIKE ? )"
					+	"   AND (" + SyBaseSQLUtil.nullStringParam() + " OR AN.LNE_NAME LIKE ? )";

		SyBaseSQLUtil.nullStringParamArgs(sqlArgs, dto.getLneCode() );
		SyBaseSQLUtil.nullStringParamArgs(sqlArgs, dto.getLneName() );
//		sqlArgs.add(dto.getLneCode());
//		sqlArgs.add(dto.getLneName());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * ���ܣ�	ͨ������Զ�����SQLModel, ɾ�����������ԡ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		
		String[] tmp = dto.getAmsLneId().split(",");
		String amsLneId = "";
		for (int i = 0; i < tmp.length; i++) {
			amsLneId += "'" + tmp[i] + "',";
		}
		amsLneId = amsLneId.substring(0, amsLneId.length() - 1);
		
		String sqlStr = "DELETE FROM"
					+ "  AMS_NLE  "
					+ "  WHERE"
					//+ "  AMS_LNE_ID IN ('" + dto.getAmsLneId() + "')";
					+ "  AMS_LNE_ID IN (" + amsLneId + ")";
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;		
	}
	
	/**
	 * Function:			�õ�ѡ�������������Լ�¼
	 * @return SQLModel 	ɾ����SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr =  "SELECT AN.AMS_LNE_ID, AN.LNE_CODE, AN.LNE_NAME"
						+	"  FROM AMS_NLE AN"
						+	" WHERE AN.AMS_LNE_ID = ?";
		sqlArgs.add(dto.getAmsLneId());
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
		String sqlStr =
		"INSERT INTO AMS_NLE\n" +
	    "  (AMS_LNE_ID, LNE_CODE, LNE_NAME)\n" +
	    " VALUES\n" +
	    "  ( NEWID(), ?, ?)";
		sqlArgs.add(dto.getLneCode());
		sqlArgs.add(dto.getLneName());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	/**
	 * ���ܣ������ж������α����Ƿ���ڵ�SQL
	 * @return SQLModel
	 */
	public SQLModel getObjectEsistModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " 1"
						+ " FROM"
						+ " AMS_NLE AN"
						+ " WHERE"
						+ " AN.LNE_CODE = ?";
		sqlArgs.add(dto.getLneCode());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
	
	

}
