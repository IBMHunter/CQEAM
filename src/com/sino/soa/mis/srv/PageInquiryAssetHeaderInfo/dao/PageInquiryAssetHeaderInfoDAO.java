package com.sino.soa.mis.srv.PageInquiryAssetHeaderInfo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.sino.soa.common.SrvType;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.dto.SynLogDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.util.StrUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.mis.srv.PageInquiryAssetHeaderInfo.dto.InquiryAssetHeaderInfoSrvDTO;
import com.sino.soa.mis.srv.PageInquiryAssetHeaderInfo.model.PageInquiryAssetHeaderInfolModel;

/**
 * <p>Title: SrvAssetBookDAO</p>
 * <p>Description:�����Զ����ɷ������SrvAssetBookDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * user:wangzp
 * function: ��ѯ�ʲ�ͷ������Ϣ����ҳ��
 */


public class PageInquiryAssetHeaderInfoDAO extends BaseDAO {

    private SfUserDTO sfUser = null;
    private int errorCount = 0;

    /**
     * ���ܣ��ʲ��˲����� SRV_ASSET_BOOK ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SrvAssetBookDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public PageInquiryAssetHeaderInfoDAO(SfUserDTO userAccount, InquiryAssetHeaderInfoSrvDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
        
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
    	InquiryAssetHeaderInfoSrvDTO dtoPara = (InquiryAssetHeaderInfoSrvDTO) dtoParameter;
        super.sqlProducer = new PageInquiryAssetHeaderInfolModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ������ʲ�ͷ����Ϣ��
     * @param ds ���������
     */
    public int saveRetireAssets(DTOSet ds) {
        SynLogUtil log = new SynLogUtil();
        SQLModel sqlModel = null;
        int count = 0;
        try {
            for (int i = 0; i < ds.getSize(); i++) {
            	InquiryAssetHeaderInfoSrvDTO dto = (InquiryAssetHeaderInfoSrvDTO) ds.getDTO(i);
                initSQLProducer(sfUser, dto);
                PageInquiryAssetHeaderInfolModel modelProducer = (PageInquiryAssetHeaderInfolModel) sqlProducer;                
                if (!isAssetExists()) {
                    sqlModel = modelProducer.getDataCreateModel();   //����
                } else {
                    sqlModel = modelProducer.getDataUpdateModel();
                }
                if (DBOperator.updateRecord(sqlModel, conn)) {
                    count++;
                } 
                
            }
        } catch (DataHandleException e) {
        	errorCount++;
            e.printLog();
            try {
                SimpleCalendar s = new SimpleCalendar();
                SynLogDTO logDto = new SynLogDTO();
                logDto.setSynType(SrvType.SRV_FA_RETIRE);
                logDto.setSynMsg(e.getMessage());
                logDto.setCreatedBy(sfUser.getUserId());
                logDto.setCreationDate(s.getCalendarValue());
                log.synLog(logDto, conn);
            } catch (CalendarException e1) {
                e1.printLog();
            } catch (DataHandleException e1) {
                e1.printLog();
            }
        } catch (QueryException e) {
            count = -1;
            e.printStackTrace();
            try {
                SimpleCalendar s = new SimpleCalendar();
                SynLogDTO logDto = new SynLogDTO();
                logDto.setSynType(SrvType.SRV_FA_RETIRE);
                logDto.setSynMsg(e.getMessage());
                logDto.setCreatedBy(sfUser.getUserId());
                logDto.setCreationDate(s.getCalendarValue());
                log.synLog(logDto, conn);
            } catch (CalendarException e1) {
                e1.printLog();
            } catch (DataHandleException e1) {
                e1.printLog();
            }
        }
        return count;
    }

    /**
     * ����ʲ�ͷ�Ƿ����
     * @retur ���ڣ�true;  ������:false
     * @throws QueryException
     */
    public boolean isAssetExists() throws QueryException {
        PageInquiryAssetHeaderInfolModel modelProducer = (PageInquiryAssetHeaderInfolModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getAssetExistsModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }
    
    /**
     * �����ʲ�ͷ��Ϣ(ZTE_ ��ETS_FA_ASSET��)
     * @return
     */
    @SuppressWarnings("unused")
	public boolean assetHeaderODI() {
    	boolean isSyn= false;
        CallableStatement cStmt = null;
        String sqlStr = "{call dbo.AUTO_SYN_ASSET_HEADER_ODI()}";
        try {
			cStmt = conn.prepareCall(sqlStr);
            cStmt.execute();
            isSyn= true;
		} catch (SQLException e) {
			e.printStackTrace();
			isSyn= false;
		} finally {
            DBManager.closeDBStatement(cStmt);
        }
        return isSyn;
    }
    
	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

}