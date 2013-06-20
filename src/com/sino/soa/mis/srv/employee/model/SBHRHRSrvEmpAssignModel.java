package com.sino.soa.mis.srv.employee.model;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.soa.mis.srv.employee.dto.SBHRHRSrvEmployeeInfoDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-9-7
 * Time: 15:48:08
 * To change this template use File | Settings | File Templates.
 */
public class SBHRHRSrvEmpAssignModel extends BaseSQLProducer {

	 private SfUserDTO sfUser = null;

	 public SBHRHRSrvEmpAssignModel(SfUserDTO userAccount, SBHRHRSrvEmployeeInfoDTO dtoParameter){
		 super(userAccount,dtoParameter);
		 sfUser =userAccount;
	 }

	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		SBHRHRSrvEmployeeInfoDTO srcEmployeeInfo =(SBHRHRSrvEmployeeInfoDTO)dtoParameter;
		String sqlStr = "UPDATE AMS_MIS_EMPLOYEE"
					   +" SET "
					   +" HR_DEPT_ID = ?,"
					   +" HR_DEPT_NAME = ?,"
					   +" DEPT_CODE = CONVERT(VARCHAR , ?)," 
					   +" COMPANY_CODE =? "
					   +" WHERE " 
					   +" EMPLOYEE_NUMBER = ?";
		 sqlArgs.add(srcEmployeeInfo.getOrganizationID());
		 sqlArgs.add(srcEmployeeInfo.getOrganization());
		 sqlArgs.add(srcEmployeeInfo.getDeptCode());
		 sqlArgs.add(srcEmployeeInfo.getCompanyCode());
		 sqlArgs.add(srcEmployeeInfo.getEmployeeNumber());
		 sqlModel.setArgs(sqlArgs);
		 sqlModel.setSqlStr(sqlStr);
		 return sqlModel;
	}

	 /**
	 * ���ܣ��ж��Ƿ��и������ݡ�
	 * @return SQLModel
	 */
	public SQLModel existsEmpAssignModel(String employeeNumber){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr ="SELECT * FROM AMS_MIS_EMPLOYEE AME WHERE AME.EMPLOYEE_NUMBER = ?";
		sqlArgs.add(employeeNumber);
		sqlModel.setArgs(sqlArgs);
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}
	
	 /**
	 * ���ܣ�����HR_DEPT_NAME�ڱ�AMS_MIS_DEPT�л�ȡCOMPANY_CODE,DEPT_CODE
	 * @return SQLModel
	 */
	public SQLModel getEmpCompanyCodeModel(String hrDeptName){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr ="SELECT DEPT_CODE,COMPANY_CODE FROM AMS_MIS_DEPT WHERE STR_REPLACE(DEPT_NAME,'�й��ƶ�ͨ�ż������ɹ����޹�˾',NULL)= STR_REPLACE(?,'�й��ƶ�ͨ�ż������ɹ����޹�˾',NULL)";
		sqlArgs.add(hrDeptName);
		sqlModel.setArgs(sqlArgs);
		sqlModel.setSqlStr(sqlStr);
		return sqlModel;
	}
	
	
}