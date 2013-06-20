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
import com.sino.sinoflow.dto.SfAutoValueDTO;
import com.sino.sinoflow.model.SfAutoValueModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfAutoValueDAO</p>
 * <p>Description:�����Զ����ɷ������SfAutoValueDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfAutoValueDAO extends BaseDAO {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ��Զ���ֵ��Ϣ SF_AUTO_VALUE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfAutoValueDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfAutoValueDAO(SfUserBaseDTO userAccount, SfAutoValueDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfAutoValueDTO dtoPara = (SfAutoValueDTO)dtoParameter;
		super.sqlProducer = new SfAutoValueModel((SfUserBaseDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ��������Ժ�ʱ��ֵ��Ϊ����ҳ����ʾ
	 * @param ds DTOSet
	 */
	public void setAssignOn(DTOSet ds){
		for(int i=0;i<ds.getSize();i++){
			SfAutoValueDTO sav = (SfAutoValueDTO)ds.getDTO(i);
			List list = FlowTaskTool.findNum(sav.getAssignOn());
			
			for(int j=0;j<list.size();j++){
				int ig = Integer.parseInt(list.get(j).toString());
				switch (ig) {
					case 1:
						sav.setTh(true);
						break;
					case 2:
						sav.setTs(true);
						break;
					case 4:
						sav.setWc(true);
						break;
					case 8:
						sav.setZc(true);
						break;
					case 16:
						sav.setQs(true);
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
		DBOperator.updateRecord((( SfAutoValueModel)sqlProducer).del(ids), conn);
	}
	
}