package com.sino.ams.newasset.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.assetsharing.model.AssetSharingModel;
import com.sino.ams.newasset.dto.AmsAssetsReservedDTO;
import com.sino.ams.newasset.model.AmsAssetsReservedModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsAssetsReservedDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsReservedDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class AmsAssetsReservedDAO extends AMSBaseDAO {
	AmsAssetsReservedModel amsAssetsReservedModel = null;
    /**
     * ���ܣ��ʲ��������� AMS_ASSETS_RESERVED ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsReservedDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsAssetsReservedDAO(SfUserDTO userAccount,
                                AmsAssetsReservedDTO dtoParameter,
                                Connection conn) {
        super(userAccount, dtoParameter, conn);
        this.initSQLProducer(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsReservedDTO dtoPara = (AmsAssetsReservedDTO) dtoParameter;
        amsAssetsReservedModel = new AmsAssetsReservedModel((SfUserDTO) userAccount,
                dtoPara);
        super.sqlProducer =amsAssetsReservedModel;
    }
    
 
	
	/**
	 * ���ܣ������� -- sj add 
	 * 
	 * @throws DataHandleException
	 * @throws SQLModelException 
	 */
	public void createReserved( String transId , String barcode  )
			throws DataHandleException  {
		amsAssetsReservedModel = new AmsAssetsReservedModel( userAccount,  null );
		SQLModel sqlModel = amsAssetsReservedModel.getDataCreateModel( transId ,  barcode );
		DBOperator.updateRecord(sqlModel, conn);
	}
	
	/**
	 * ���ܣ�ɾ���� -- sj add 
	 * 
	 * @throws DataHandleException
	 */
	public void deleteReserved( String transId )
			throws DataHandleException {
		SQLModel sqlModel = amsAssetsReservedModel.getDataDeleteModel( transId ) ;
		DBOperator.updateRecord(sqlModel, conn);
	}
}
