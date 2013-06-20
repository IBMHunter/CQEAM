package com.sino.ams.system.cost.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.cost.dto.AmsMisCostMatchDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-7-31
 * Time: 10:43:28
 * To change this template use File | Settings | File Templates.
 */
public class AmsMisCostMatchModel extends AMSSQLProducer {

    /**
     * ���ܣ���������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM) ETS_OBJECT_CATEGORY ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsObjectCategoryDTO ���β���������
     */
    public AmsMisCostMatchModel(SfUserDTO userAccount, AmsMisCostMatchDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ��ɱ����ĵĲ�ѯmodel��
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
//		try {
            List sqlArgs = new ArrayList();
            AmsMisCostMatchDTO dto = (AmsMisCostMatchDTO)dtoParameter;
            String sqlStr =  
            	"SELECT AMDM.COST_CENTER_CODE COST_CODE,\n" +
            	"       ACCV.COST_CENTER_NAME COST_NAME,\n" + 
            	"       AMDM.DEPT_CODE DEPT_ID,\n" + 
            	"       AMD.DEPT_NAME\n" + 
            	"  FROM AMS_COST_CENTER_V ACCV, AMS_COST_DEPT_MATCH AMDM, AMS_MIS_DEPT AMD\n" + 
            	" WHERE CONVERT(VARCHAR,ACCV.COST_CENTER_CODE) = AMDM.COST_CENTER_CODE\n" ; 
            if( StrUtil.isEmpty( dto.getDeptName() ) ){
            	sqlStr +=    "   AND AMDM.DEPT_CODE *= AMD.DEPT_CODE\n" ;
            }else{
            	sqlStr +=    "   AND AMDM.DEPT_CODE = AMD.DEPT_CODE\n" ;
            }
        	
        	sqlStr += "   AND ACCV.COMPANY_CODE = AMDM.COMPANY_CODE\n" +
        	"   AND ( " + SyBaseSQLUtil.isNull() + "  OR AMDM.COST_CENTER_CODE = ? OR ACCV.COST_CENTER_NAME like ? )\n" + 
        	"   AND ( " + SyBaseSQLUtil.isNull() + "  OR AMDM.DEPT_CODE = ?  OR AMD.DEPT_NAME like ? )\n" + 
        	" ORDER BY AMDM.COST_CENTER_CODE";


            sqlArgs.add(dto.getCostCenterCode());
            sqlArgs.add(dto.getCostCenterCode());
            sqlArgs.add(dto.getCostCenterCode());
            sqlArgs.add(dto.getDeptName());
            sqlArgs.add(dto.getDeptName());
            sqlArgs.add(dto.getDeptName());
//            if ((!userAccount.isProvinceUser()) && (!userAccount.isSysAdmin())) {
//                   sqlStr +="  AND  AMD.COMPANY_CODE = ? " ;
//                  sqlArgs.add(userAccount.getCompanyCode());
//            }
        
//            sqlStr +=" ORDER BY AMD.DEPT_NAME,AMDM.COST_CENTER_CODE";

            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
//		} catch (CalendarException ex) {
//			ex.printLog();
//			throw new SQLModelException(ex);
//		}
        return sqlModel;
    }

  /*
  **���ܣ����н��ƥ�������
   */
    public SQLModel getDelMatchModel(String deptId){
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr=" DELETE " +
                      " FROM " +
                      " AMS_COST_DEPT_MATCH " +
				      "	WHERE DEPT_CODE= ? " ;
        sqlArgs.add(deptId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}
