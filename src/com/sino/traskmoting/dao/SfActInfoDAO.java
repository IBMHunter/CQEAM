package com.sino.traskmoting.dao;


import java.sql.Connection;

import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;
import com.sino.traskmoting.dto.SfActInfoDTO;
import com.sino.traskmoting.model.SfActInfoModel;


/**
 * <p>Title: SfActInfoDAO</p>
 * <p>Description:�����Զ����ɷ������SfActInfoDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Administrator
 * @version 1.0
 */


public class SfActInfoDAO extends BaseDAO {

	private SfUserBaseDTO sfUserBase = null;
    private SfActInfoModel sfActInfoModel=null;
	/**
	 * ���ܣ���ת���̣��ڰ���ת��Ϣ SF_ACT_INFO ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfActInfoDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfActInfoDAO(SfUserBaseDTO userAccount, SfActInfoDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUserBase = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		SfActInfoDTO dtoPara = (SfActInfoDTO)dtoParameter;
		super.sqlProducer = new SfActInfoModel((SfUserBaseDTO)userAccount, dtoPara);
	}

	//�����û���Ϣ��ȡ�����
	public DTOSet getTraskUserMotingModel(Connection conn) throws QueryException {
		// TODO Auto-generated method stub
		   DTOSet dtoSet=new DTOSet();
		   SQLModel sqlModel = sfActInfoModel.getTraskUserMotingModel();
		   if(sqlModel==null){
			   
	       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
				simp.setDTOClassName(SfActInfoDTO.class.getName());
				simp.executeQuery();
				dtoSet=simp.getDTOSet();
				}
			 
		return dtoSet;
	 
	}
	
	//���ݽ�ɫ��Ϣ��ȡ�����
	public DTOSet getTraskRoleMotingModel(Connection conn) throws QueryException {
		DTOSet dtoSet = new DTOSet();
		SQLModel sqlModel = sfActInfoModel.getTraskRoleMotingModel();
		if(sqlModel == null){
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.setDTOClassName(SfActInfoDTO.class.getName());
			simp.executeQuery();
			dtoSet = simp.getDTOSet();
		}
		return dtoSet;
	}
}