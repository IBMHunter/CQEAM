package com.sino.ams.newasset.model;


import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.AmsMisDeptDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.framework.dto.BaseUserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ItemFinanceReportModel</p>
 * <p>Description:�����Զ�����SQL��������AdminConfirmModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class ItemFinanceReportModel extends AMSSQLProducer {

	/**
	 * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsTransLineDTO ���β���������
	 */
	public ItemFinanceReportModel(BaseUserDTO userAccount, DTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

    public SQLModel getReportModel(){
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT EFV.VALUE FINANCE_PROP,\n" +
                "       COUNT(1) ITEM_COUNT\n" +
                "FROM   ETS_ITEM_INFO      EII,\n" +
                "       AMS_MIS_DEPT       AMD,\n" +
                "       ETS_FLEX_VALUE_SET EFVS,\n" +
                "       ETS_FLEX_VALUES    EFV\n" +
                "WHERE  EII.FINANCE_PROP = EFV.CODE\n" +
                "       AND EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
                "       AND EFVS.CODE = 'FINANCE_PROP'\n" +
                "       AND EII.RESPONSIBILITY_DEPT = AMD.DEPT_CODE\n" +
                "       AND EII.ORGANIZATION_ID = ?\n";
        List sqlArgs = new ArrayList();
        sqlArgs.add(userAccount.getOrganizationId());
        if(!(userAccount.isComAssetsManager() || userAccount.isMtlAssetsManager())){
            if(userAccount.isDptAssetsManager()){
                DTOSet depts = userAccount.getPriviDeptCodes();
                String deptSQL = "'";
                if( null != depts ){
	                int deptCount = depts.getSize();
	                
	                for(int i = 0; i < deptCount; i++){
	                    AmsMisDeptDTO dept = (AmsMisDeptDTO)depts.getDTO(i);
	                    deptSQL += dept.getDeptCode() + "'";
	                    if(i < deptCount - 1){
	                        deptSQL += ", '";
	                    }
	                }
                }
                sqlStr += " AND AMD.DEPT_CODE IN("+deptSQL+")\n";
            } else {
                sqlStr += " AND EII.RESPONSIBILITY_USER = ?\n";
                sqlArgs.add(userAccount.getEmployeeId());
            }
        }
        sqlStr += "GROUP  BY EFV.VALUE";

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getFinanceDictionaryModel(){
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT EFV.VALUE FINANCE_PROP,\n" +
                 "      0 ITEM_COUNT\n" +
                "FROM   ETS_FLEX_VALUE_SET EFVS,\n" +
                "       ETS_FLEX_VALUES    EFV\n" +
                "WHERE  EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
                "       AND EFVS.CODE = 'FINANCE_PROP'"
                + "     AND EFV.CODE <> 'PRJ_MTL' "
                ;
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }
}
