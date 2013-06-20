package com.sino.ams.newasset.urgenttrans.pda.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.newasset.urgenttrans.constant.UrgentAppConstant;
import com.sino.ams.newasset.urgenttrans.dto.UrgentHeaderDTO;
import com.sino.ams.newasset.urgenttrans.dto.UrgentLineDTO;
import com.sino.ams.newasset.urgenttrans.pda.model.UrgentUploadModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.pda.dao.OrderUploadDAO;

public class UrgentOrderInUploadDAO extends OrderUploadDAO {

	public UrgentOrderInUploadDAO(SfUserDTO userAccount, Connection conn) {
		super(userAccount, conn);
	}
	
	@Override
	public boolean uploadOrders(String filePath) {
		boolean operateResult = false;
		try { 
			UrgentOrderXMLProcessor xmlParser = new UrgentOrderXMLProcessor(
					conn);
			xmlParser.parseXML(filePath); 
			List<String> headerIds = xmlParser.getHeaderIds();
			String headerId = "";

			UrgentHeaderDTO orderHeader = new UrgentHeaderDTO();
  
			
//			ChkOrderLineUploadDAO lineDAO = new ChkOrderLineUploadDAO(
//					userAccount, orderLine, conn);

			for (int i = 0; i < headerIds.size(); i++) {
				headerId = (String) headerIds.get(i);
				orderHeader = xmlParser.getHeader(headerId);
				
				if( !orderHeader.getOrderType().equals( UrgentAppConstant.IN_ORDER_NAME )){
					continue;
				}
				
				DTOSet chkLines = xmlParser.getLines(headerId);// ��ȡ���ι����ύ���豸��Ϣ
				
				if( isAutoArchive() ){ //�Զ��鵵
					orderHeader.setTransStatus( UrgentAppConstant.STATUS_ARCHIVED );
					this.updateHeaderStatus( orderHeader ); 
					this.updateAssetsAddress( headerId ); 
					this.deleteReserved( headerId );  
					this.saveEIIHistoty( headerId );
				}else{
					orderHeader.setTransStatus( UrgentAppConstant.STATUS_TRANS_IN );
					this.updateHeaderStatus( orderHeader );
//					saveLines( chkLines , headerId ); 
				}
			}
			operateResult = true;
		} catch (Exception ex) {
			Logger.logError(ex);
		}
		return operateResult;
	}
	
	/**
	 * ������
	 * @param chkLines
	 * @param headerId
	 * @throws DataHandleException
	 */
	public void saveLines( DTOSet chkLines , String headerId ) throws DataHandleException{
		UrgentLineDTO orderLine = new UrgentLineDTO();
		if (chkLines != null && !chkLines.isEmpty()) {
			for (int j = 0; j < chkLines.getSize(); j++) {
				orderLine = (UrgentLineDTO) chkLines.getDTO(j);
				orderLine.setTransId( headerId );
				updateLineStatus( orderLine ); 
			}
		}
	}
	
	
	
 
	/**
	 * ���ܣ����µ���ͷ״̬Ϊ�ѵ��롣
	 * 
	 * @throws DataHandleException
	 */
	public void updateHeaderStatus(UrgentHeaderDTO headerDTO)
			throws DataHandleException {
		UrgentUploadModel model = new UrgentUploadModel(userAccount, headerDTO);
		SQLModel sqlModel = model.updateHeaderStatusModel(headerDTO);
		DBOperator.updateRecord(sqlModel, conn);
	}
	
	
	

	/**
	 * ���ܣ������� -- ������
	 * 
	 * @throws DataHandleException
	 */
	public void updateLineStatus( UrgentLineDTO lineDTO )
			throws DataHandleException {
		UrgentUploadModel model = new UrgentUploadModel(userAccount, null );
		SQLModel sqlModel = model.updateLineStatusModel( lineDTO );
		DBOperator.updateRecord(sqlModel, conn);
	}
	
	/**
	 * ���ܣ������� -- ������
	 * 
	 * @throws DataHandleException
	 * @throws QueryException 
	 * @throws ContainerException 
	 */
	public String findPDAArchiveControlModel()throws  QueryException, ContainerException {
		String controlValue = null;
		UrgentUploadModel model = new UrgentUploadModel(userAccount, null );
		SQLModel sqlModel = model.findPDAArchiveControlModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.executeQuery();
		if (simp.hasResult()) {
			controlValue = simp.getFirstRow().getStrValue("KEYVALUE");
		} 
		return controlValue;
	}
	
	/**
	 * �ֶ��鵵/�Զ��鵵 �ж�
	 * @return
	 * @throws QueryException
	 * @throws ContainerException
	 */
	public boolean isAutoArchive() throws QueryException, ContainerException{
		String controlValue = findPDAArchiveControlModel(); 
		if( "1".equals( controlValue ) ){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * ɾ������
	 * @param transId
	 * @throws DataHandleException
	 */
	public void deleteReserved(String transId ) throws DataHandleException{
		UrgentUploadModel model = new UrgentUploadModel(userAccount, null );
		SQLModel sqlModel = model.deleteReservedModel( transId );
		DBOperator.updateRecord(sqlModel, conn);
	}
	
	/**
	 * �����ʲ��ص�
	 * @param transId
	 * @throws DataHandleException
	 */
	public void updateAssetsAddress(String transId ) throws DataHandleException{
		UrgentUploadModel model = new UrgentUploadModel(userAccount, null );
		SQLModel sqlModel = model.updateAssetsAddressModel( transId );
		DBOperator.updateRecord(sqlModel, conn);
	}
	
	/**
	 * ��¼���ʲ��޸���ʷ��
	 * @throws DataHandleException 
	 */
	public void saveEIIHistoty( String transId ) throws DataHandleException{
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		StringBuffer sql = new StringBuffer();
		sql.append( " INSERT INTO \n " );
		sql.append( " AMS_ITEM_INFO_HISTORY( \n " );
		sql.append( " HISTORY_ID, \n " );
		sql.append( " BARCODE, \n " );
		sql.append( " ADDRESS_ID, \n " );
		sql.append( " ITEM_CODE, \n " );
		sql.append( " ITEM_STATUS, \n " );
		sql.append( " RESPONSIBILITY_USER, \n " );
		sql.append( " RESPONSIBILITY_DEPT, \n " );
		sql.append( " ORDER_NO, \n " );
		sql.append( " ORDER_CATEGORY, \n " );
		sql.append( " ORDER_DTL_URL, \n " );
		sql.append( " CREATION_DATE, \n " );
		sql.append( " CREATED_BY, \n " );
		sql.append( " REMARK \n " );
		sql.append( " ) ( \n " );   
		sql.append( " SELECT \n " );
		sql.append( " NEWID() , \n " );
		sql.append( " EII.BARCODE , \n " );
		sql.append( " EII.ADDRESS_ID,\n " );
		sql.append( " EII.ITEM_CODE, \n " );
		sql.append( " EII.ITEM_STATUS, \n " );
		sql.append( " EII.RESPONSIBILITY_USER, \n " );
		sql.append( " EII.RESPONSIBILITY_DEPT, \n " );
		
		sql.append( " AATH.TRANS_NO, \n " );
		sql.append( " ?, \n " );
		sql.append( " ?, \n " ); 
		sql.append( " GETDATE(), \n " ); 
		sql.append(	" ? "); 
		sql.append(	" , ? " );  
		sql.append( "  FROM  \n " );
		sql.append( " ETS_ITEM_INFO EII, \n " );
		sql.append( " AMS_ASSETS_TRANS_HEADER AATH, \n " );
		sql.append( " AMS_ASSETS_TRANS_LINE AATL \n " );
		sql.append( " WHERE  \n " );
		sql.append( " AATH.TRANS_ID = AATL.TRANS_ID  \n " );
		sql.append( " AND AATL.BARCODE = EII.BARCODE   \n " ); 
		sql.append( " AND AATH.TRANS_ID = ?  \n " ); 
		sql.append( " AND AATL.TRANS_ID = ?  \n " ); 
		sql.append( " )  \n " );
		
		sqlArgs.add( UrgentAppConstant.ORDER_CATEGORY );
		sqlArgs.add( UrgentAppConstant.ORDER_DTL_URL );
		sqlArgs.add( userAccount.getUserId()  ); 
		sqlArgs.add( UrgentAppConstant.TRANS_TYPE_NAME );
		
		sqlArgs.add( transId );
		sqlArgs.add( transId );
		
		sqlModel.setArgs( sqlArgs );
		sqlModel.setSqlStr( sql.toString() );
		
		DBOperator.updateRecord( sqlModel , conn );
		  
	}
}
