package com.sino.ams.system.item.dao;


import java.sql.Connection;
import java.util.List;

import com.sino.ams.system.item.dto.EtsSysitemDistributeDTO;
import com.sino.ams.system.item.model.EtsSysitemDistributeModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.calen.DateConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsSysitemDistributeDAO</p>
 * <p>Description:�����Զ����ɷ������EtsSysitemDistributeDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsSysitemDistributeDAO extends BaseDAO {

	private EtsSysitemDistributeModel etsSysitemDistributeModel = null;
	private SfUserDTO SfUser = null;

	/**
	 * ���ܣ�������֯����� ETS_SYSITEM_DISTRIBUTE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsSysitemDistributeDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsSysitemDistributeDAO(SfUserDTO userAccount, EtsSysitemDistributeDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		SfUser = userAccount;
		initSQLProducer(userAccount, dtoParameter);
	}

    

    /**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsSysitemDistributeDTO dtoPara = (EtsSysitemDistributeDTO)dtoParameter;
		super.sqlProducer = new EtsSysitemDistributeModel((SfUserDTO)userAccount, dtoPara);
		etsSysitemDistributeModel = (EtsSysitemDistributeModel)sqlProducer;
	}

	/**
	 * ���ܣ�����������֯������ETS_SYSITEM_DISTRIBUTE�����ݡ�
	 * @return boolean
	 */
	public void createData() throws DataHandleException{
//		boolean operateResult = super.createData();
		 super.createData();
		getMessage().addParameterValue("������֯�����");
//		return operateResult;
	}

	/**
	 * ���ܣ�����������֯������ETS_SYSITEM_DISTRIBUTE�����ݡ�
	 * @return boolean
	 */
	public void updateData() throws DataHandleException{
//		boolean operateResult = super.updateData();
		 super.updateData();
		getMessage().addParameterValue("������֯�����");
//		return operateResult;
	}

	/**
	 * ���ܣ�ɾ��������֯������ETS_SYSITEM_DISTRIBUTE�����ݡ�
     * @param itemCode
	 * @return boolean
     * @throws DataHandleException
	 */
	public void deleteData(String itemCode) throws DataHandleException {
        SQLModel sqlModel = null;
        sqlModel = etsSysitemDistributeModel.getDataDeleteModel(itemCode);
        DBOperator.updateRecord(sqlModel, conn);
      
            }


//    public EtsSysitemDistributeDAO(Connection conn, SfUserDTO userAccount) {
//        this.conn = conn;
//        this.etsSysitemDistributeModel = new EtsSysitemDistributeModel(userAccount);
//        this.userAccount = userAccount;
//    }

    /**
     * ���ܣ������豸�����������
      * @param distrDatas
     * @throws DataHandleException
     */
     public void createDistriDatas(DTOSet distrDatas) throws DataHandleException {
        List sqModels = etsSysitemDistributeModel.getCreateOrgModel(distrDatas);
        DBOperator.updateBatchRecords(sqModels, conn);
    }

    public RowSet produceLinesData(String itemCode) throws QueryException {
            SQLModel sqlModel = etsSysitemDistributeModel.getPrimaryKeyDataModel(itemCode);
            SimpleQuery query = new SimpleQuery(sqlModel, conn);
            query.setCalPattern(DateConstant.LINE_PATTERN);
            query.executeQuery();
            return query.getSearchResult();
        }


    public void deleteItemCodes(String[] itemCodes)   {
      try {

         EtsSysitemDistributeModel etsSysitemDistributeModel = (EtsSysitemDistributeModel) sqlProducer;
		 SQLModel sqlModel = etsSysitemDistributeModel.getDeleteItemModel(itemCodes);

        DBOperator.updateRecord(sqlModel, conn);
    } catch (DataHandleException e) {
        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
    }
}

}