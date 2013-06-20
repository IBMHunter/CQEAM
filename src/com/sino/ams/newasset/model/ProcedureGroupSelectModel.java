package com.sino.ams.newasset.model;


import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: ProcedureGroupSelectModel</p>
 * <p>Description:�����Զ�����SQL��������AdminConfirmModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class ProcedureGroupSelectModel extends AMSSQLProducer {

	/**
	 * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter DTO ���β���������
	 */
	public ProcedureGroupSelectModel(BaseUserDTO userAccount, DTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

    public SQLModel getProSpecialGroupModel(){
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT SG.GROUP_NAME\n" +
                "FROM   SF_GROUP SG\n" +
                "WHERE  SG.SPECIALITY_DEPT = 'Y'\n" +
                "       AND SG.ENABLED = 'Y'\n" +
                "       AND SG.ORGANIZATION_ID = " + servletConfig.getProvinceOrgId();
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }
}
