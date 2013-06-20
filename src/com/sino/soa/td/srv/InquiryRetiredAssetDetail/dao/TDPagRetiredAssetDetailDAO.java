package com.sino.soa.td.srv.InquiryRetiredAssetDetail.dao;

import java.sql.Connection;
import com.sino.soa.common.SrvType;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.dto.SynLogDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.mis.srv.InquiryRetiredAssetDetail.dto.PageRetiredAssetDTO;
import com.sino.soa.td.srv.InquiryRetiredAssetDetail.model.TDPageRetiredAssetDetailModel;

/**
 * <p>Title: SrvAssetBookDAO</p>
 * <p>Description:�����Զ����ɷ������SrvAssetBookDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * user:wangzp
 * function:��ѯ�����ʲ�������Ϣ����ҳ��_TD
 */


public class TDPagRetiredAssetDetailDAO extends BaseDAO {

    private SfUserDTO sfUser = null;
    private int errorCount = 0;

    /**
     * ���ܣ��ʲ��˲����� SRV_ASSET_BOOK ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SrvAssetBookDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public TDPagRetiredAssetDetailDAO(SfUserDTO userAccount, PageRetiredAssetDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        PageRetiredAssetDTO dtoPara = (PageRetiredAssetDTO) dtoParameter;
        super.sqlProducer = new TDPageRetiredAssetDetailModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ����±����ʲ���Ϣ��
     * @param ds ���������
     */
    public int saveRetireAssets(DTOSet ds) {
        SynLogUtil log = new SynLogUtil();
        SQLModel sqlModel = null;
        int count = 0;
        try {
            for (int i = 0; i < ds.getSize(); i++) {
                PageRetiredAssetDTO dto = (PageRetiredAssetDTO) ds.getDTO(i);
                initSQLProducer(sfUser, dto);
                TDPageRetiredAssetDetailModel modelProducer = (TDPageRetiredAssetDetailModel) sqlProducer;                
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
     * ����ʲ��Ƿ����
     * @retur ���ڣ�true;  ������:false
     * @throws QueryException
     */
    public boolean isAssetExists() throws QueryException {
        TDPageRetiredAssetDetailModel modelProducer = (TDPageRetiredAssetDetailModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getAssetExistsModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

}