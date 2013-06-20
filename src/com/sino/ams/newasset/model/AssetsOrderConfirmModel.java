package com.sino.ams.newasset.model;


import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: AdminConfirmModel</p>
 * <p>Description:�����Զ�����SQL��������AdminConfirmModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class AssetsOrderConfirmModel extends AMSSQLProducer {

	/**
	 * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsTransLineDTO ���β���������
	 */
	public AssetsOrderConfirmModel(BaseUserDTO userAccount, DTO dtoParameter) {
		super(userAccount, dtoParameter);
	}


	/**
	 * ���ܣ�������������ʲ�����ȷ�ϵ�SQL
	 * @return SQLModel �����ʲ�������SQL
	 */
	public SQLModel getOrderAssetsModel() {
		SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT AATH.FROM_ORGANIZATION_ID,\n" +
                "       AATH.TO_ORGANIZATION_ID,\n" +
                "       AATH.TRANS_ID,\n" +
                "       AATH.TRANS_NO,\n" +
                "       AATH.TRANSFER_TYPE,\n" +
                "       AATL.BARCODE,\n" +
                "       AATL.NEW_BARCODE,\n" +
                "       AATL.OLD_LOCATION,\n" +
                "       AATL.OLD_RESPONSIBILITY_USER,\n" +
                "       AATL.OLD_RESPONSIBILITY_DEPT,\n" +
                "       AATL.ASSIGNED_TO_LOCATION,\n" +
                "       AATL.RESPONSIBILITY_USER,\n" +
                "       AATL.RESPONSIBILITY_DEPT,\n" +
                "       AOA.ADDRESS_ID OLD_ADDRESS_ID,\n" +
                "       AOA2.ADDRESS_ID\n" +
                "FROM   AMS_ASSETS_TRANS_LINE   AATL,\n" +
                "       AMS_ASSETS_TRANS_HEADER AATH,\n" +
                "       AMS_OBJECT_ADDRESS      AOA,\n" +
                "       AMS_OBJECT_ADDRESS      AOA2\n" +
                "WHERE  AATL.TRANS_ID = AATH.TRANS_ID\n" +
                "       AND AATL.OLD_LOCATION = AOA.OBJECT_NO\n" +
                "       AND AATL.ASSIGNED_TO_LOCATION = AOA2.OBJECT_NO\n" +
                "       AND AATH.TRANS_ID = ?";
        List<String> sqlArgs = new ArrayList<String>();
        AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) dtoParameter;
        sqlArgs.add(dto.getTransId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
	}
}
