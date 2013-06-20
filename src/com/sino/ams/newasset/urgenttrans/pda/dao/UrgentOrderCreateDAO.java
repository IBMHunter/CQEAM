package com.sino.ams.newasset.urgenttrans.pda.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.urgenttrans.constant.UrgentAppConstant;
import com.sino.ams.newasset.urgenttrans.dto.UrgentHeaderDTO;
import com.sino.ams.newasset.urgenttrans.model.UrgentModel;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.XMLParseException;
import com.sino.base.log.Logger;
import com.sino.framework.security.dto.FilterConfigDTO;
import com.sino.pda.dao.OrderCreateDAO;

/**
 * 
 * @ϵͳ����: 
 * @��������: 
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Aug 3, 2011
 */
public class UrgentOrderCreateDAO extends OrderCreateDAO{
	/**
	 *
	 * @param conn ���ݿ�����
	 * @param filterConfig FilterConfigDTO �����ļ���Ϣ
	 * @param filePath String ���صĹ����ļ�
	 */
	public UrgentOrderCreateDAO(Connection conn, FilterConfigDTO filterConfig, String filePath) {
		super(conn, filterConfig, filePath);
	}
	
	/**
	 * ���ܣ���ʼ����������
	 * @throws XMLParseException
	 */
	protected void initOrderData() throws XMLParseException {
		UrgentOrderXMLParser chkParser = new UrgentOrderXMLParser();
		chkParser.parseXML(filterConfig, conn, filePath);
		orderParameter = chkParser.getOrder();
		userAccount = chkParser.getCreatedUser();
		if(  userAccount.getUserId() <= 0 ){
			throw new XMLParseException( "�û���ϢΪ��" );
		}
		UrgentHeaderDTO order = (UrgentHeaderDTO)orderParameter; 
		order.setCreatedBy( userAccount.getUserId() );
		sqlProducer = new UrgentModel( userAccount,  order );
//		AmsAssetsCheckHeaderDTO checkOrder = (AmsAssetsCheckHeaderDTO)orderParameter;
//		sqlProducer = new ChkOrderPDACreateModel(userAccount, checkOrder);
	}

	@Override
	protected void createOrderBatch() throws DataHandleException { 
	}

	@Override
	protected void createOrderHeader() throws DataHandleException {
		try {
			UrgentHeaderDTO dto = (UrgentHeaderDTO)orderParameter; 
			SeqProducer seqProducer = new SeqProducer(conn);
			String transId = seqProducer.getGUID();
			dto.setTransId( transId ); 
			 
			
			 
	        dto.setCreatedBy( userAccount.getUserId()); // ���ô�����
	        dto.setCreated( userAccount.getUsername()); // ���ô�����
	        dto.setFromOrganizationId( userAccount.getOrganizationId());
	        dto.setFromCompanyName( userAccount.getCompany()); 
	        dto.setTransTypeValue( UrgentAppConstant.PDA_CREATE_TYPE_NAME );
	        dto.setTransType( UrgentAppConstant.TRANS_TYPE );
	        dto.setCurrCreationDate(); 
	        dto.setTransStatus( AssetsDictConstant.DISTRIBUTED  ); 
	        dto.setImplementBy( userAccount.getUserId() );
	          
	        String companyCode = userAccount.getCompanyCode(); //���ǲ��ø÷��������¿������Ӿ���Ϊû��Ҫ
			String transType = dto.getTransType();
			OrderNumGenerator numberProducer = new OrderNumGenerator(conn,companyCode, transType);
			orderNo = numberProducer.getOrderNum();
			dto.setTransNo( orderNo ); 
			
			UrgentModel modelProducer = (UrgentModel)sqlProducer;
			modelProducer.setDTOParameter( dto );
			SQLModel sqlModel = modelProducer.createHeaderModel(dto);
			DBOperator.updateRecord(sqlModel, conn);

			setOrderData( dto );
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} 
	}

	@Override
	protected void createOrderLine() throws DataHandleException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasPreviousOrder() {
		// TODO Auto-generated method stub
		return false;
	}


}
