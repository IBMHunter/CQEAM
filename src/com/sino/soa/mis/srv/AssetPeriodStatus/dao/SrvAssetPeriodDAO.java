package com.sino.soa.mis.srv.AssetPeriodStatus.dao;


import java.sql.Connection;

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
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.common.SrvType;
import com.sino.soa.mis.srv.AssetPeriodStatus.dto.SrvAssetPeriodStatusDTO;
import com.sino.soa.mis.srv.AssetPeriodStatus.model.SrvAssetPeriodModel;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.dto.SynLogDTO;


/**
 * <p>Title: SrvAssetBookDAO</p>
 * <p>Description:�����Զ����ɷ������SrvAssetBookDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author zhoujs
 * @version 1.0
 */


public class SrvAssetPeriodDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ��ʲ��˲����� SRV_ASSET_BOOK ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SrvAssetBookDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public SrvAssetPeriodDAO(SfUserDTO userAccount, SrvAssetPeriodStatusDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SrvAssetPeriodStatusDTO dtoPara = (SrvAssetPeriodStatusDTO) dtoParameter;
        super.sqlProducer = new SrvAssetPeriodModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ��Ի���ڼ���Ϣ����ͬ��
     * @param ds DTOSet���������
     */
    public int synAssetPeriod(DTOSet ds) {
        SynLogUtil log = new SynLogUtil();
        SQLModel sqlModel = null;
        int count = 0;
        try {
            RowSet rs = getOuInfom();
            SrvAssetPeriodModel modelProducer = (SrvAssetPeriodModel) sqlProducer;
            for (int i = 0; i < ds.getSize(); i++) {
                SrvAssetPeriodStatusDTO dto = (SrvAssetPeriodStatusDTO) ds.getDTO(i);
                dto.setOrganizationId(SynLogUtil.getOrganizationId(conn, dto.getBookTypeCode()));
                setDTOParameter(dto);
                if (!isEcouInformation(dto.getBookTypeCode(), dto.getMisPeriodName(), rs)) {
                    sqlModel = modelProducer.getDataCreateModel();
                } else {
                    sqlModel = modelProducer.getDataUpdateModel();
                }
                if (DBOperator.updateRecord(sqlModel, conn)) {
                    count++;
                }
            }
         	
        } catch (Throwable e) {
            Logger.logError(e);
            SynLogDTO logDto = new SynLogDTO();
            logDto.setSynType(SrvType.SRV_FA_PERIOD);
            logDto.setSynMsg(e.getMessage());
            logDto.setCreatedBy(sfUser.getUserId());
            try {
                logDto.setCreationDate(CalendarUtil.getCurrCalendar());
            } catch (CalendarException e1) {
                e1.printLog();
            }
            try {
                log.synLog(logDto, conn);
            } catch (DataHandleException e1) {
                e1.printLog();
            }
        }
        return count;
    }

    /**
     * ���ܣ������ou�����Ƿ�������ݿ�ou���С�
     * @param ou
     * @param period
     * @param rs
     * @return
     */
    public boolean isEcouInformation(String ou, String period, RowSet rs) {
        boolean returnFlag = false;
        if (rs == null) {
            return returnFlag;
        }
        Row row = null;
        for (int i = 0; i < rs.getSize(); i++) {
            row = rs.getRow(i);
            try {
                if (ou.equals(row.getValue("BOOK_TYPE_CODE")) && period.equals(row.getValue("MIS_PERIOD_NAME")))
                    returnFlag = true;
            } catch (ContainerException e) {
                e.printLog();
            }
        }
        return returnFlag;

    }

    public RowSet getOuInfom() {
        SrvAssetPeriodModel modelProducer = (SrvAssetPeriodModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getEcouInforModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        RowSet rs = null;
        try {
            simp.executeQuery();
            if (simp.hasResult()) {
                rs = simp.getSearchResult();
            }

        } catch (QueryException e) {
            e.printLog();
		}
		return rs;
	}

}