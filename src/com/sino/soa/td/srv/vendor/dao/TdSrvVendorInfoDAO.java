package com.sino.soa.td.srv.vendor.dao;


import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.common.SrvType;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.SynUpdateDateUtils;
import com.sino.soa.util.dto.SynLogDTO;
import com.sino.soa.td.srv.vendor.dto.TdSrvVendorInfoDTO;
import com.sino.soa.td.srv.vendor.model.TdSrvVendorInfoModel;

import java.sql.Connection;
import java.text.ParseException;



/**
 * <p>Title: SrvAssetBookDAO</p>
 * <p>Description:�����Զ����ɷ������SrvAssetBookDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author wangzhipeng
 * date:2011-09-08
 * DSC:��Ӧ����Ϣ�ӿ�_TD
 */


public class TdSrvVendorInfoDAO extends BaseDAO {

	private SfUserDTO sfUser = null;
	
	private int errorCount = 0;

	/**
	 * ���ܣ��ʲ��˲����� SRV_ASSET_BOOK ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SrvAssetBookDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public TdSrvVendorInfoDAO(SfUserDTO userAccount, TdSrvVendorInfoDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		TdSrvVendorInfoDTO dtoPara = (TdSrvVendorInfoDTO)dtoParameter;
		super.sqlProducer = new TdSrvVendorInfoModel((SfUserDTO)userAccount, dtoPara);
	}
	
	/**
	 * ���ܣ��Թ�Ӧ����Ϣ�ĸ����Ƿ�ɹ���
	 * @param DTOSet ds ���������
	 */
	public int synVendorInfo(DTOSet ds){
		SynLogUtil log=new SynLogUtil();
        SQLModel sqlModel = null;
        int count=0;
        try {
      		RowSet rs=getOuInfom();
        	for (int i =0 ; i < ds.getSize(); i++) {
        		TdSrvVendorInfoDTO dto = (TdSrvVendorInfoDTO) ds.getDTO(i);
        		initSQLProducer(sfUser,dto);
        		TdSrvVendorInfoModel modelProducer = (TdSrvVendorInfoModel) sqlProducer;
        		String syDate = SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_VENDOR, conn);
        		//2009-10-30
        		if(SynUpdateDateUtils.getBetweenDays(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_VENDOR, conn),(dto.getLastUpdateDate().toString()))>0){
        			if(!isEcouInformation(dto.getVendorNumber(),rs)){
						sqlModel=modelProducer.getDataCreateModel();
					}
					else {
						sqlModel=modelProducer.getDataUpdateModel();	
					}
					if(DBOperator.updateRecord(sqlModel, conn)){
						count++;
					}
        		}
        			
        	}
		} catch (DataHandleException e) {
			errorCount++;
			e.printLog();
			SimpleCalendar s=new SimpleCalendar();
			SynLogDTO logDto=new SynLogDTO();
			logDto.setSynType(SrvType.SRV_VENDOR);
			logDto.setSynMsg(e.getMessage());
			logDto.setCreatedBy(sfUser.getUserId());
			try {
				logDto.setCreationDate(s.getCalendarValue());
			} catch (CalendarException e1) {
				e1.printLog();
			}
			try {
				log.synLog(logDto, conn);
			} catch (DataHandleException e1) {
				e1.printLog();
			}
		} catch (QueryException e) {
			e.printLog();
		} catch (CalendarException e) {
			e.printLog();
		} catch (ContainerException e) {
			e.printLog();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * ���ܣ������ou�����Ƿ�������ݿ�ou���С�
	 * @param DTOSet ds ���������
	 */
	public boolean isEcouInformation(String ou,RowSet rs){
		boolean returnFlag=false;
		if(rs==null){
			return returnFlag;
		}
        Row row=null;
        for(int i = 0; i < rs.getSize(); i++){
        	row = rs.getRow(i);
        	try {
				if(ou.equals(row.getValue("SEGMENT1")))
					returnFlag=true;
			} catch (ContainerException e) {
				e.printLog();
			}
        }
        return returnFlag;
		
	}
	
	public RowSet getOuInfom(){
		TdSrvVendorInfoModel modelProducer = (TdSrvVendorInfoModel) sqlProducer;
         SQLModel sqlModel = modelProducer.getEcouInforModel();
         SimpleQuery simp = new SimpleQuery(sqlModel, conn);
         RowSet rs=null;
         try {
			simp.executeQuery();
			if(simp.hasResult()){
				rs = simp.getSearchResult();
			}

		} catch (QueryException e) {
			 e.printLog();
		}
		return rs;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

}