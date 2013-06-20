package com.sino.sinoflow.dao;


import java.sql.Connection;
import java.util.List;

import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.util.FlowTaskTool;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.dto.SfWorkScheduleDTO;
import com.sino.sinoflow.model.SfWorkScheduleModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfWorkScheduleDAO</p>
 * <p>Description:�����Զ����ɷ������SfWorkScheduleDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class SfWorkScheduleDAO extends BaseDAO {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ�����ʱ���ܱ� SF_WORK_SCHEDULE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfWorkScheduleDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfWorkScheduleDAO(SfUserBaseDTO userAccount, SfWorkScheduleDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfWorkScheduleDTO dtoPara = (SfWorkScheduleDTO)dtoParameter;
		super.sqlProducer = new SfWorkScheduleModel((SfUserBaseDTO)userAccount, dtoPara);
	}
	
	/**
	 * ���ܣ���ɾ��Ӧ��
	 * @param String[]
	 * @throws DataHandleException 
	 */
	public void del(String[] ids) throws DataHandleException {
		DBOperator.updateRecord(((SfWorkScheduleModel)sqlProducer).del(ids), conn);
	}
	
	/**
	 * ���ܣ������ڻ�ԭΪ�ַ���
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @throws ContainerException 
	 * @throws NumberFormatException 
	 */
	public void setWorkingDateStr(DTOSet ds) throws NumberFormatException, ContainerException{
		
		for(int i=0; i<ds.getSize();i++){
			SfWorkScheduleDTO sd = (SfWorkScheduleDTO)ds.getDTO(i);
			List list = FlowTaskTool.findNum(Integer.parseInt(sd.getWorkingDate()));
			String str = "";
			for(int j=0;j<list.size();j++){
				int ig = Integer.parseInt(list.get(j).toString());
				switch (ig){
					case 1 : 
						str += ����һ +" ";
						break;
					case 2 :
						str += ���ڶ� +" ";
						break;
					case 4 :
						str += ������ +" ";
						break;
					case 8 :
						str += ������ +" ";
						break;
					case 16 :
						str += ������ +" ";
						break;
					case 32 :
						str += ������ +" ";
						break;
					case 64 :
						str += ������;
						break;
				}
			}
			sd.setWorkStr(str);
		}
	}
}