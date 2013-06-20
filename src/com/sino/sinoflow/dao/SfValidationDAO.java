package com.sino.sinoflow.dao;


import java.sql.Connection;
import java.util.List;

import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.util.FlowTaskTool;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.dto.SfValidationDTO;
import com.sino.sinoflow.model.SfValidationModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfValidationDAO</p>
 * <p>Description:�����Զ����ɷ������SfValidationDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfValidationDAO extends BaseDAO {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ��Ϸ��Լ����Ϣ SF_VALIDATION ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfValidationDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfValidationDAO(SfUserBaseDTO userAccount, SfValidationDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfValidationDTO dtoPara = (SfValidationDTO)dtoParameter;
		super.sqlProducer = new SfValidationModel((SfUserBaseDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ���������empty��Ϊ����ҳ����ʾ
	 * @param ds DTOSet
	 */
	public void setEmpty(DTOSet ds){
		for(int i=0;i<ds.getSize();i++){
			SfValidationDTO sf = (SfValidationDTO)ds.getDTO(i);
			List list = FlowTaskTool.findNum(sf.getValidationType());
			for (int j = 0; j < list.size(); j++) {
				int ig = Integer.parseInt(list.get(j).toString());
				if(ig == 1){
					sf.setEmpty(true);
					break;
				}
			}
		}
	}
	
	/**
	 * ���ܣ���ɾ��Ӧ��
	 * @param ids String[]
	 * @throws DataHandleException 
	 */
	public void del(String[] ids) throws DataHandleException {
		DBOperator.updateRecord((( SfValidationModel)sqlProducer).del(ids), conn);
	}
}