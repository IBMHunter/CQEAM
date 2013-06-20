package com.sino.soa.mis.srv.inquiryassetlocation.dao;


import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.common.SrvType;
import com.sino.soa.mis.srv.inquiryassetlocation.dto.SrvAssetLocationDTO;
import com.sino.soa.mis.srv.inquiryassetlocation.model.SrvAssetLocationModel;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.dto.SynLogDTO;

import java.sql.Connection;


/**
 * <p>Title: SrvAssetCategoryDAO</p>
 * <p>Description:�����Զ����ɷ������SrvAssetCategoryDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author wzp
 * @version 1.0
 *          update:2011-09-08
 */


public class SrvAssetLocationDAO extends AMSBaseDAO {

    /**
     * ���ܣ��ʲ������� SRV_ASSET_CATEGORY ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SrvAssetCategoryDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public SrvAssetLocationDAO(SfUserDTO userAccount, SrvAssetLocationDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SrvAssetLocationDTO dtoPara = (SrvAssetLocationDTO) dtoParameter;
        super.sqlProducer = new SrvAssetLocationModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ��Ե�ַ��Ϣ�ĸ����Ƿ�ɹ���(��TD)
     *
     * @param ds                   DTOSet ��������ݶ���
     * @param segment1������(�ʲ��ص��һ��)
     * @return �ɹ����µļ�¼��
     */
    public int synAssetLocation(DTOSet ds, String segment1) {
        SynLogUtil log = new SynLogUtil();
        SQLModel sqlModel = null;
        String orgId ="";
        String bookTypeCode ="";
        int count = 0;
        try {
            SrvAssetLocationDTO firstLocation = (SrvAssetLocationDTO) ds.getDTO(0);
            Row row = getEcomCode(firstLocation.getLocationCombinationCode());
            if(row!=null){
                orgId = row.getStrValue("ORGANIZATION_ID");
                bookTypeCode = row.getStrValue("BOOK_TYPE_CODE");
            }
            SrvAssetLocationModel modelProducer = (SrvAssetLocationModel) sqlProducer;
            for (int i = 0; i < ds.getSize(); i++) {
                SrvAssetLocationDTO dto = (SrvAssetLocationDTO) ds.getDTO(i);
                dto.setOrgId(orgId);
                dto.setBookTypeCode(bookTypeCode);
//                initSQLProducer(userAccount, dto);//����ʤע�ͣ��÷������ٴ�ʵ����ģ�Ͷ���ռ����Դ
                setDTOParameter(dto);//����ʤ�޸�
                if (getOuInfom() < 1) {  //������
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
            SimpleCalendar s = new SimpleCalendar();
            SynLogDTO logDto = new SynLogDTO();
            logDto.setSynType(SrvType.SRV_FA_LOCATION);
            logDto.setSynMsg(e.getMessage());
            logDto.setCreatedBy(userAccount.getUserId());
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
        }
        return count;
    }

    /**
     * ����:�ж�MIS�̶��ʲ��ص�����Ƿ���ڵص�������ϴ���
     *
     * @return
     */
    public int getOuInfom() {
        SrvAssetLocationModel modelProducer = (SrvAssetLocationModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getEcouInforModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        RowSet rs = null;
        try {
            simp.executeQuery();
            if (simp.hasResult()) {
                rs = simp.getSearchResult();
                return rs.getSize();
            }

        } catch (QueryException e) {
            e.printLog();
        }
        return 0;
    }

    /**
     * ���ܣ��ж��Ƿ����д�TD��ַ
     *
     * @return
     */
    public int isExistTdAssetsLocation() {
        SrvAssetLocationModel modelProducer = (SrvAssetLocationModel) sqlProducer;
        SQLModel sqlModel = modelProducer.isExistTdAssetsLocation();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        RowSet rs = null;
        try {
            simp.executeQuery();
            if (simp.hasResult()) {
                rs = simp.getSearchResult();
                return rs.getSize();
            }

        } catch (QueryException e) {
            e.printLog();
        }
        return 0;
    }

    /**
     * ���ܣ����ݵص����ڶ��λ�ȡOU��֯ID���ʲ��˲�����
     *
     * @param objectCode �ص����
     * @return row �����ݶ���
     */
    private Row getEcomCode(String objectCode) {
        Row row = null;
        SrvAssetLocationModel modelProducer = (SrvAssetLocationModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getEcomCodeModel(objectCode);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        try {
            simp.executeQuery();
            if (simp.hasResult()) {
                row = simp.getFirstRow();
            }
        } catch (QueryException e) {
            e.printLog();
        }
        return row;
    }
}