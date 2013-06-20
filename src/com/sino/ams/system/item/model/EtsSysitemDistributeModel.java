package com.sino.ams.system.item.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.item.dto.EtsSysitemDistributeDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.util.ArrUtil;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsSysitemDistributeModel</p>
 * <p>Description:�����Զ�����SQL��������EtsSysitemDistributeModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */
//public class EtsSysitemDistributeModel  {
//    private EtsSysitemDistributeDTO etsSysitemDistribute = null;
//    private SfUserDTO SfUser = null;
//
//    public EtsSysitemDistributeModel(SfUserDTO etsSysitemDistribute) {
//        this.etsSysitemDistribute = etsSysitemDistribute;
//    }




public class EtsSysitemDistributeModel extends BaseSQLProducer {

	private EtsSysitemDistributeDTO etsSysitemDistribute = null;
	private SfUserDTO SfUser = null;

	/**
	 * ���ܣ�������֯����� ETS_SYSITEM_DISTRIBUTE ���ݿ�SQL����㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsSysitemDistributeDTO ���β���������
	 */
	public EtsSysitemDistributeModel(SfUserDTO userAccount, EtsSysitemDistributeDTO dtoParameter) {
		super(userAccount, dtoParameter);
		SfUser = userAccount;
		this.etsSysitemDistribute = (EtsSysitemDistributeDTO)dtoParameter;
	}

    /**
	 * ���ܣ�����Զ�����������֯����� ETS_SYSITEM_DISTRIBUTE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public SQLModel getDataCreateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
       if( SfUser.getOrganizationId() == SyBaseSQLUtil.ORG_ID  ){
        String sqlStr = "INSERT INTO "
			+ " ETS_SYSITEM_DISTRIBUTE("
			+ " SYSTEM_ID,"
			+ " ITEM_CODE,"
			+ " ORGANIZATION_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
            + " LAST_UPDATE_DATE,"
            + " LAST_UPDATE_BY"
			+ ") VALUES ("
			+ "NEWID() , ?, ?, " +SyBaseSQLUtil.getCurDate() + ", ?, " +SyBaseSQLUtil.getCurDate() + ", ?)";
		
		sqlArgs.add(etsSysitemDistribute.getItemCode());
		sqlArgs.add(etsSysitemDistribute.getOrganizationId());
		sqlArgs.add(SfUser.getUserId());
		sqlArgs.add(SfUser.getUserId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
       }else{
          String sqlStr = "INSERT INTO "
			+ " ETS_SYSITEM_DISTRIBUTE("
			+ " SYSTEM_ID,"
			+ " ITEM_CODE,"
			+ " ORGANIZATION_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
            + " IS_TMP,"
            + " LAST_UPDATE_DATE,"
            + " LAST_UPDATE_BY"
            + ") VALUES ("
            + "NEWID() , ?, ?, " +SyBaseSQLUtil.getCurDate() + ", ?,'Y', " +SyBaseSQLUtil.getCurDate() + ", ?)";

		sqlArgs.add(etsSysitemDistribute.getItemCode());
		sqlArgs.add(etsSysitemDistribute.getOrganizationId());
		sqlArgs.add(SfUser.getUserId());
		sqlArgs.add(SfUser.getUserId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs); 
       }
        return sqlModel;
	}

    /**
	 * ���ܣ�
     * @param  distrDatas DTOSet
	 * @return SQLModel �������ݲ�����SQLModel
	 */
	public List getCreateOrgModel(DTOSet distrDatas){
        List sqModels = new ArrayList();
        EtsSysitemDistributeDTO dtoPara = null;
     if((SfUser.isProvinceUser()) || (SfUser.isSysAdmin())){     //ʡ��˾
        for (int i = 0; i < distrDatas.getSize(); i++){
            dtoPara = (EtsSysitemDistributeDTO)distrDatas.getDTO(i);        //��ȡָ����������
            SQLModel sqlModel = new SQLModel();
            List sqlArgs = new ArrayList();
            String sqlStr = " INSERT INTO "
                + " ETS_SYSITEM_DISTRIBUTE("
                + " SYSTEM_ID,"
                + " ITEM_CODE,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + ") VALUES (NEWID(), ?, ?, " +SyBaseSQLUtil.getCurDate() + ", ?, " +SyBaseSQLUtil.getCurDate() + ", ?)";
            sqlArgs.add(dtoPara.getItemCode());
            sqlArgs.add(dtoPara.getOrganizationId());
            sqlArgs.add(SfUser.getUserId());
            sqlArgs.add(SfUser.getUserId());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            sqModels.add(sqlModel);
        }
     }else{                                                  //���ڵ��й�˾���ӵ��豸���� ��������Ϊ��ʱ�豸
         for (int i = 0; i < distrDatas.getSize(); i++){
            dtoPara = (EtsSysitemDistributeDTO)distrDatas.getDTO(i);        //��ȡָ����������
            SQLModel sqlModel = new SQLModel();
            List sqlArgs = new ArrayList();
            String sqlStr = "INSERT INTO "
                + " ETS_SYSITEM_DISTRIBUTE("
                + " SYSTEM_ID,"
                + " ITEM_CODE,"
                + " ORGANIZATION_ID,"
                + " CREATION_DATE,"
                + " CREATED_BY,"
                + " IS_TMP,"
                + " LAST_UPDATE_DATE,"
                + " LAST_UPDATE_BY"
                + ") VALUES ("
                + "NEWID()  , ?, ?, " +SyBaseSQLUtil.getCurDate() + ", ?,'N', " +SyBaseSQLUtil.getCurDate() + ", ?)";
            sqlArgs.add(dtoPara.getItemCode());
            sqlArgs.add(dtoPara.getOrganizationId());
            sqlArgs.add(SfUser.getUserId());
            sqlArgs.add(SfUser.getUserId());
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            sqModels.add(sqlModel);
        }
     }
        return sqModels;
	}
    /**
	 * ���ܣ�����Զ�����������֯����� ETS_SYSITEM_DISTRIBUTE���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel �������ݸ�����SQLModel
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "UPDATE ETS_SYSITEM_DISTRIBUTE"
			+ " SET"
			+ " ITEM_CODE = ?,"
			+ " ORGANIZATION_ID = ?,"
			+ " CREATION_DATE = ?,"
			+ " CREATED_BY = ?,"
			+ " LAST_UPDATE_DATE = ?,"
			+ " LAST_UPDATE_BY = ?"
			+ " WHERE"
			+ " SYSTEM_ID = ?";
		
		sqlArgs.add(etsSysitemDistribute.getItemCode());
		sqlArgs.add(etsSysitemDistribute.getOrganizationId());
		sqlArgs.add(etsSysitemDistribute.getCreationDate());
		sqlArgs.add(etsSysitemDistribute.getCreatedBy());
		sqlArgs.add(etsSysitemDistribute.getLastUpdateDate());
		sqlArgs.add(etsSysitemDistribute.getLastUpdateBy());
		sqlArgs.add(etsSysitemDistribute.getSystemId());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����������֯����� ETS_SYSITEM_DISTRIBUTE����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ��������ɾ����SQLModel
	 */
	public SQLModel getDataDeleteModel(String itemCode){
        SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "DELETE FROM"
			+ " ETS_SYSITEM_DISTRIBUTE"
			+ " WHERE"
			+ " ITEM_CODE = ?";
		sqlArgs.add(itemCode);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
        return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����������֯����� ETS_SYSITEM_DISTRIBUTE������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(String itemCode){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " SYSTEM_ID,"
			+ " ITEM_CODE,"
			+ " ORGANIZATION_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " ETS_SYSITEM_DISTRIBUTE"
			+ " WHERE"
			+ " ITEM_CODE = ?";
		sqlArgs.add(itemCode);
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����������֯����� ETS_SYSITEM_DISTRIBUTE����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
	 */
	public SQLModel getDataMuxModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " SYSTEM_ID,"
			+ " ITEM_CODE,"
			+ " ORGANIZATION_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " ETS_SYSITEM_DISTRIBUTE"
			+ " WHERE"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR SYSTEM_ID LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR ITEM_CODE LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR ORGANIZATION_ID LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
			+ "( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
		sqlArgs.add(etsSysitemDistribute.getSystemId());
		sqlArgs.add(etsSysitemDistribute.getSystemId());
		sqlArgs.add(etsSysitemDistribute.getItemCode());
		sqlArgs.add(etsSysitemDistribute.getItemCode());
		sqlArgs.add(etsSysitemDistribute.getOrganizationId());
		sqlArgs.add(etsSysitemDistribute.getOrganizationId());
		sqlArgs.add(etsSysitemDistribute.getCreationDate());
		sqlArgs.add(etsSysitemDistribute.getCreationDate());
		sqlArgs.add(etsSysitemDistribute.getCreatedBy());
		sqlArgs.add(etsSysitemDistribute.getCreatedBy());
		sqlArgs.add(etsSysitemDistribute.getLastUpdateDate());
		sqlArgs.add(etsSysitemDistribute.getLastUpdateDate());
		sqlArgs.add(etsSysitemDistribute.getLastUpdateBy());
		sqlArgs.add(etsSysitemDistribute.getLastUpdateBy());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ�������������ֶ� itemCode �����ѯ����SQL��
	 * ����Զ���������������֯����� ETS_SYSITEM_DISTRIBUTE��ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @param itemCode String 
	 * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
	 */
	private SQLModel getDataByItemCodeModel(String itemCode){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " SYSTEM_ID,"
			+ " ORGANIZATION_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " ETS_SYSITEM_DISTRIBUTE"
			+ " WHERE"
			+ " ITEM_CODE = ?";
		sqlArgs.add(itemCode);
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * ���ܣ����������ȡ����
	 * @param foreignKey ���������ֶ����ơ�
	 * @return SQLModel
	 */
	public SQLModel getDataByForeignKeyModel(String foreignKey){
		SQLModel sqlModel = null;
		if(foreignKey.equals("itemCode")){
			sqlModel = getDataByItemCodeModel(etsSysitemDistribute.getItemCode());
		}
		return sqlModel;
	}

	/**
	 * ���ܣ�����Զ�����������֯����� ETS_SYSITEM_DISTRIBUTEҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
	 * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
	 */
	public SQLModel getPageQueryModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT "
			+ " SYSTEM_ID,"
			+ " ITEM_CODE,"
			+ " ORGANIZATION_ID,"
			+ " CREATION_DATE,"
			+ " CREATED_BY,"
			+ " LAST_UPDATE_DATE,"
			+ " LAST_UPDATE_BY"
			+ " FROM"
			+ " ETS_SYSITEM_DISTRIBUTE"
			+ " WHERE"
			+ " ( " + SyBaseSQLUtil.isNull() + "  OR SYSTEM_ID LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR ITEM_CODE LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR ORGANIZATION_ID LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATION_DATE LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR CREATED_BY LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_DATE LIKE ?)"
			+ "AND ( " + SyBaseSQLUtil.isNull() + "  OR LAST_UPDATE_BY LIKE ?)";
		sqlArgs.add(etsSysitemDistribute.getSystemId());
		sqlArgs.add(etsSysitemDistribute.getSystemId());
		sqlArgs.add(etsSysitemDistribute.getItemCode());
		sqlArgs.add(etsSysitemDistribute.getItemCode());
		sqlArgs.add(etsSysitemDistribute.getOrganizationId());
		sqlArgs.add(etsSysitemDistribute.getOrganizationId());
		sqlArgs.add(etsSysitemDistribute.getCreationDate());
		sqlArgs.add(etsSysitemDistribute.getCreationDate());
		sqlArgs.add(etsSysitemDistribute.getCreatedBy());
		sqlArgs.add(etsSysitemDistribute.getCreatedBy());
		sqlArgs.add(etsSysitemDistribute.getLastUpdateDate());
		sqlArgs.add(etsSysitemDistribute.getLastUpdateDate());
		sqlArgs.add(etsSysitemDistribute.getLastUpdateBy());
		sqlArgs.add(etsSysitemDistribute.getLastUpdateBy());
		
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}



 	public SQLModel getDeleteItemModel(String[] itemCodes) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String orderno = ArrUtil.arrToSqlStr(itemCodes);
		String sqlStr =  "DELETE FROM ETS_SYSITEM_DISTRIBUTE WHERE ITEM_CODE IN (" + orderno + ")";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

}