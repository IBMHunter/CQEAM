package com.sino.ams.newasset.rent.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;

public class AssetsQueryModel extends AMSSQLProducer {
	/**
	 * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݿ�SQL����㹹�캯��
	 * 
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsAddressVDTO ���β���������
	 */
	public AssetsQueryModel(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}
	
	public SQLModel getPageQueryModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		String sqlStr = 
		" SELECT EII.BARCODE, \n" +
		"        EII.ITEM_CODE, \n" + 
		"        ESI.ITEM_NAME, \n" + //�ʲ�����
		"        ESI.ITEM_SPEC, \n" + //�ʲ��ͺ�
		"        EII.ADDRESS_ID, \n" +
		"        AOA.OBJECT_NO, \n" +
		"        EO.WORKORDER_OBJECT_CODE, \n" + //�ص����
		"        EO.WORKORDER_OBJECT_NAME, \n" + //�ص�����
		"        EII.RESPONSIBILITY_USER, \n" +
		"        AME.USER_NAME RESPONSIBILITY_USER_NAME, \n" + //������
		"        AME.EMPLOYEE_NUMBER, \n" + //Ա����
		"        EII.RESPONSIBILITY_DEPT, \n" +
		"        AMD.DEPT_NAME DEPT_NAME, \n" + //���β���
		"        EII.MAINTAIN_USER MAINTAIN_USER_NAME, \n" + //ʹ����
		"        CASE EII.ITEM_STATUS WHEN 'DISCARDED' THEN '�ѱ���' ELSE EII.ITEM_STATUS END ITEM_STATUS \n" + //�ʲ�״̬
		"   FROM ETS_ITEM_INFO EII, \n" + 
		"        ETS_SYSTEM_ITEM ESI, \n" + 
		"        AMS_OBJECT_ADDRESS AOA, \n" + 
		"        ETS_OBJECT EO, \n" +
		"        AMS_MIS_EMPLOYEE AME, \n" +
		"        AMS_MIS_DEPT AMD \n" +
		"  WHERE 1 = 1 \n" +
		"    AND EII.ITEM_CODE = ESI.ITEM_CODE \n" +
//		"    AND EII.ORGANIZATION_ID = ? \n" +
		"    AND EII.ADDRESS_ID = AOA.ADDRESS_ID \n" +
		"    AND AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO \n" +
		"    AND EII.RESPONSIBILITY_USER *= AME.EMPLOYEE_ID \n" +
		"    AND EII.RESPONSIBILITY_DEPT *= AMD.DEPT_CODE \n" +
		"    AND (  EII.ITEM_STATUS =  'SEND_REPAIR' ) \n" +
//		"    AND EII.FINANCE_PROP IN ('DH_ASSET', 'DG_ASSETS', 'SPARE') \n" +
		//"    AND ESI.ITEM_NAME LIKE dbo.NVL(?, ESI.ITEM_NAME) \n" +
		//"    AND ESI.ITEM_SPEC LIKE dbo.NVL(?, ESI.ITEM_SPEC) \n" +
		//"    AND EII.BARCODE LIKE dbo.NVL(?, EII.BARCODE) \n" +
		"    AND (? = '' OR ESI.ITEM_NAME LIKE ?) \n" +
		"    AND (? = '' OR ESI.ITEM_SPEC LIKE ?) \n" +
		"    AND (? = '' OR EII.BARCODE LIKE ?) \n" +
		"    AND EXISTS( " +
		"    SELECT  " +
		"    NULL FROM  " +
		"    AMS_ASSETS_TRANS_HEADER AH ,  " +
		"    AMS_ASSETS_TRANS_LINE AL WHERE   " +
		"    AH.TRANS_ID = AL.TRANS_ID  " +
		"	 AND AL.BARCODE = EII.BARCODE " +
		"    AND AH.TRANS_TYPE = 'ASS-REPAIR'  " + 
		"    AND AH.TRANS_STATUS = 'APPROVED'  " +
		"    ) " 
		; 
		
		
		sqlArgs.add(dto.getItemName());
		sqlArgs.add(dto.getItemName());
		sqlArgs.add(dto.getItemSpec());
		sqlArgs.add(dto.getItemSpec());
		sqlArgs.add(dto.getBarcode());
		sqlArgs.add(dto.getBarcode());
		 
		if (userAccount.isComAssetsManager()) {
			sqlStr += "    AND EII.ORGANIZATION_ID = ? \n" ;
			sqlArgs.add( userAccount.getOrganizationId() );
		}else{
			if (!userAccount.getEmployeeId().equals("")) {
				sqlStr += "    AND EII.RESPONSIBILITY_USER = ? \n" ;
				sqlArgs.add(userAccount.getEmployeeId());
			} else {
				sqlStr += "    AND EII.RESPONSIBILITY_USER = '-1' \n" ;
			}
		}

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
