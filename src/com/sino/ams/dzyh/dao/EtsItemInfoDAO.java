package com.sino.ams.dzyh.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EtsItemInfoDTO;
import com.sino.ams.dzyh.model.EtsItemInfoModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsItemInfoDAO</p>
 * <p>Description:�����Զ����ɷ������EtsItemInfoDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class EtsItemInfoDAO extends AMSBaseDAO {

	/**
	 * ���ܣ���ǩ����Ϣ(EAM) ETS_ITEM_INFO ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemInfoDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsItemInfoDAO(SfUserDTO userAccount, EtsItemInfoDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsItemInfoDTO dtoPara = (EtsItemInfoDTO)dtoParameter;
		super.sqlProducer = new EtsItemInfoModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ���ֵ�׺�Ʒ���ܲ�ѯ
	 * @return
	 * @throws SQLModelException
	 * @throws QueryException
	 */
	/*public EtsItemInfoDTO getSumQueryDAO() throws SQLModelException, QueryException {
		EtsItemInfoDTO etsiiDto=(EtsItemInfoDTO)dtoParameter;
		EtsItemInfoModel etsiModel=new EtsItemInfoModel(userAccount,etsiiDto);
		SQLModel sModel=etsiModel.getSumQueryModel();
		SimpleQuery sQurey=new SimpleQuery(sModel,conn);
		sQurey.executeQuery();
		
		
		return etsiiDto;
	}*/
}