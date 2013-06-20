package com.sino.ams.newasset.report.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.report.dto.ReportInDataDTO;
import com.sino.ams.newasset.report.model.ReportInDataModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.data.Row;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-5-14
 * Time: 16:50:12
 * To change this template use File | Settings | File Templates.
 */
public class ReportInDataDAO extends AMSBaseDAO {

	public ReportInDataDAO(SfUserDTO userAccount, ReportInDataDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 *
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		ReportInDataDTO dto = (ReportInDataDTO) dtoParameter;
		sqlProducer = new ReportInDataModel(user, dto);
	}

   /**
	 *
	 * Function:		�ж�ָ����KPI�����Ƿ�����KPIָ��
     * @param kpiCode   KPI����
	 * @return			boolean���ͣ�false��ʾ������KPIָ��
	 * @author  		����
	 * @Date:   		2009-09-25
	 */
	public Row getIsKpi(String kpiCode) throws QueryException {
       boolean result = false;
       ReportInDataModel ridModel = (ReportInDataModel)sqlProducer;
	   SQLModel sqlModel = ridModel.getIsKpiModel();
       SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
       simpleQuery.executeQuery();
       Row row = null;
       if(simpleQuery.hasResult()){
           row = simpleQuery.getFirstRow();
       } else {
           row = null;
       }
       return row;
    }

}
