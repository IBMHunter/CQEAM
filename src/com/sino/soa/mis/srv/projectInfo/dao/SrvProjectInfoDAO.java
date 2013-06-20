package com.sino.soa.mis.srv.projectInfo.dao;


import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.mis.srv.projectInfo.dto.SrvProjectInfoDTO;
import com.sino.soa.mis.srv.projectInfo.model.SrvProjectInfoModel;

import java.sql.Connection;


/**
 * <p>Title: SrvProjectInfoDAO</p>
 * <p>Description:�����Զ����ɷ������SrvProjectInfoDAO��</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author zhoujs
 * @version 1.0
 */


public class SrvProjectInfoDAO extends AMSBaseDAO {

    /**
     * ���ܣ���Ŀ��Ϣ���� SRV_PROJECT_INFO ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SrvProjectInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public SrvProjectInfoDAO(SfUserDTO userAccount, SrvProjectInfoDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SrvProjectInfoDTO dtoPara = (SrvProjectInfoDTO) dtoParameter;
        super.sqlProducer = new SrvProjectInfoModel((SfUserDTO) userAccount, dtoPara);
    }

	/**
	 * 
	 * @param projectNum
	 * @return
	 * @throws QueryException
	 */
    public boolean isProjecdtExists(String projectNum) throws QueryException {
        SrvProjectInfoModel projectInfoModel = (SrvProjectInfoModel)sqlProducer;
        boolean isExists = true;
        SQLModel sqlModel = new SQLModel();
        sqlModel = projectInfoModel.existsProjectModel(projectNum);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        isExists = sq.hasResult();

        return isExists;
    }

    

}