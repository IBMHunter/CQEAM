package com.sino.soa.mis.srv.transTaskInfo.dao;


import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.mis.srv.transTaskInfo.dto.PageInquiryTaskinfoDTO;   
import com.sino.soa.mis.srv.transTaskInfo.model.PageInquiryTaskinfoModel;  

import java.sql.Connection;

/**
 * <p>Title: SrvTaskinfoDAO</p>
 * <p>Description:�����Զ����ɷ������SrvTaskinfoDAO��</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author wangzp
 * function:��ѯ��Ŀ������Ϣ����(��ҳ) 
 */

public class PageInquiryTaskinfoDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ�SRV_TASKINFO ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SrvTaskinfoDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public PageInquiryTaskinfoDAO(SfUserDTO userAccount, PageInquiryTaskinfoDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		PageInquiryTaskinfoDTO dtoPara = (PageInquiryTaskinfoDTO)dtoParameter;
		super.sqlProducer = new PageInquiryTaskinfoModel((SfUserDTO)userAccount, dtoPara);
	}
	
	/**
	 * 
	 * @param taskNumber
	 * @return (true:���� false:������)
	 * @throws QueryException
	 */
	public boolean isProjectTaskExists(String taskNumber,String projectNum) throws QueryException{
		PageInquiryTaskinfoModel projectTaskInfoModel = new PageInquiryTaskinfoModel((SfUserDTO) userAccount, (PageInquiryTaskinfoDTO) dtoParameter);
	        boolean isExists = true;
	        SQLModel sqlModel = new SQLModel();
	        sqlModel = projectTaskInfoModel.existsProjectTaskModel(taskNumber, projectNum);
	        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
	        sq.executeQuery();
	        isExists = sq.hasResult();
	        return isExists;
	}
	
	
}