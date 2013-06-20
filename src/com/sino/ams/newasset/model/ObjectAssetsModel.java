package com.sino.ams.newasset.model;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.ObjectAssetsDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: ItemInfoHistoryModel</p>
 * <p>Description:�����Զ�����SQL��������ItemInfoHistoryModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class ObjectAssetsModel extends AMSSQLProducer {

	/**
	 * ���ܣ��豸�ص�䶯��ʷ��(EAM) AMS_ITEM_INFO_HISTORY ���ݿ�SQL����㹹�캯��
	 * @param userAccount BaseUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter DTO ���β���������
	 */
	public ObjectAssetsModel(BaseUserDTO userAccount, DTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�����Զ������豸�ص�䶯��ʷ��(EAM) AMS_ITEM_INFO_HISTORY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getPageQueryModel() {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		ObjectAssetsDTO dto = (ObjectAssetsDTO) dtoParameter;
		String sqlStr = "SELECT EII.BARCODE,\n" +
                "       EO.WORKORDER_OBJECT_NO,\n" +
                "       EFV.VALUE ITEM_CATEGORY_NAME,\n" +
                "       ESI.ITEM_NAME,\n" +
                "       ESI.ITEM_SPEC,\n" +
                "       AME.USER_NAME,\n" +
                "       AMD.DEPT_NAME,\n" +
                "       EO.WORKORDER_OBJECT_CODE,\n" +
                "       EO.WORKORDER_OBJECT_NAME\n" +
                "FROM   ETS_ITEM_INFO      EII,\n" +
                "       ETS_OBJECT         EO,\n" +
                "       AMS_OBJECT_ADDRESS AOA,\n" +
                "       AMS_MIS_DEPT       AMD,\n" +
                "       AMS_MIS_EMPLOYEE   AME,\n" +
                "       ETS_SYSTEM_ITEM    ESI,\n" +
                "       ETS_FLEX_VALUES    EFV,\n" +
                "       ETS_FLEX_VALUE_SET EFVS\n" +
                "WHERE  EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                "       AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                "       AND AOA.BOX_NO = '0000'\n" +
                "       AND AOA.NET_UNIT = '0000'\n" +
                "       AND EII.ITEM_CODE = ESI.ITEM_CODE\n" +
                "       AND ESI.ITEM_CATEGORY = EFV.CODE\n" +
                "       AND EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID\n" +
                "       AND EFVS.CODE = 'ITEM_TYPE'\n" +
                "       AND EII.RESPONSIBILITY_USER *= AME.EMPLOYEE_ID\n" +
                "       AND EII.RESPONSIBILITY_DEPT *= AMD.DEPT_CODE\n" +
                "       AND EII.ORGANIZATION_ID = ?\n";
        sqlArgs.add(userAccount.getOrganizationId());

        String checkLocation = dto.getCheckLocation();
        String[] locationArr = StrUtil.splitStr(checkLocation, "$");
        String clauseSQL = "";
        for(int i = 0; i < locationArr.length; i++){
            String location = locationArr[i];
            int index = location.indexOf("_");
            String locationNo = location.substring(0, index);
            String itemCategory = location.substring(index + 1);
            if(i > 0){
                clauseSQL += " OR ";
            }
            clauseSQL += "(EO.WORKORDER_OBJECT_NO = ? AND ESI.ITEM_CATEGORY = dbo.NVL(?, ESI.ITEM_CATEGORY))\n";
            sqlArgs.add(locationNo);
            sqlArgs.add(itemCategory);
        }
        sqlStr += "AND (" + clauseSQL + ")\n";
        sqlStr += "ORDER BY EII.ADDRESS_ID";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
