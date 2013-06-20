package com.sino.soa.td.srv.projectInfo.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.td.srv.projectInfo.dto.TDSrvProjectInfoDTO;
import com.sino.soa.td.srv.projectInfo.model.TDSrvProjectInfoModel;
import java.sql.Connection;


/**
 * <p>Title: SrvProjectInfoDAO</p>
 * <p>Description:�����Զ����ɷ������SrvProjectInfoDAO��</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author wangzp
 * @version 1.0
 */

public class TDSrvProjectInfoDAO extends AMSBaseDAO {

    /**
     * ���ܣ���Ŀ��Ϣ���� SRV_PROJECT_INFO ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SrvProjectInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public TDSrvProjectInfoDAO(SfUserDTO userAccount, TDSrvProjectInfoDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
    	TDSrvProjectInfoDTO dtoPara = (TDSrvProjectInfoDTO) dtoParameter;
        super.sqlProducer = new TDSrvProjectInfoModel((SfUserDTO) userAccount, dtoPara);
    }

	/**
	 * 
	 * @param projectNum
	 * @return
	 * @throws QueryException
	 */
    public boolean isProjecdtExists(String projectNum) throws QueryException {
    	TDSrvProjectInfoModel projectInfoModel = (TDSrvProjectInfoModel)sqlProducer;
        SQLModel sqlModel = projectInfoModel.existsProjectModel(projectNum);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.hasResult();
    }

    

}