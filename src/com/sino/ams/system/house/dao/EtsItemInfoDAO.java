package com.sino.ams.system.house.dao;


import java.sql.Connection;

import com.sino.ams.system.fixing.dto.EtsItemInfoDTO;
import com.sino.ams.system.house.model.AmsHouseInfoModel;
import com.sino.ams.system.house.model.EtsItemInfoModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsItemInfoDAO</p>
 * <p>Description:�����Զ����ɷ������EtsItemInfoDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsItemInfoDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���ǩ����Ϣ(EAM) ETS_ITEM_INFO ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemInfoDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsItemInfoDAO(SfUserDTO userAccount, EtsItemInfoDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsItemInfoDTO dtoPara = (EtsItemInfoDTO)dtoParameter;
		super.sqlProducer = new EtsItemInfoModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ������ǩ����Ϣ(EAM)��ETS_ITEM_INFO�����ݡ�
	 * @return boolean
	 */
	public void createData() throws DataHandleException {
//		boolean operateResult = super.createData();
		 super.createData();
		getMessage().addParameterValue("��ǩ����Ϣ");
//		return operateResult;
	}

	/**
	 * ���ܣ����±�ǩ����Ϣ(EAM)��ETS_ITEM_INFO�����ݡ�
	 * @return boolean
	 */
	public void updateData() throws DataHandleException{
//		boolean operateResult = super.updateData();
		 super.updateData();
		getMessage().addParameterValue("��ǩ����Ϣ");
//		return operateResult;
	}

	/**
	 * ���ܣ�ɾ����ǩ����Ϣ(EAM)��ETS_ITEM_INFO�����ݡ�
	 * @return boolean
	 */
	public void deleteData() throws DataHandleException{
//		boolean operateResult = super.deleteData();
		 super.deleteData();
		getMessage().addParameterValue("��ǩ����Ϣ");
//		return operateResult;
	}


    public void updateAttribute1(String Barcode) throws DataHandleException {
        AmsHouseInfoModel amsHouseInfoModel = (AmsHouseInfoModel) sqlProducer;
		SQLModel sqlModel = amsHouseInfoModel.getAttribute1Model(Barcode);
		DBOperator.updateRecord(sqlModel, conn);
    }

    
}