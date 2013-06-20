package com.sino.soa.td.srv.inquiryassetlocation.dao;

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
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.common.SrvType;
import com.sino.soa.mis.srv.inquiryassetlocation.dto.SrvAssetLocationDTO;
import com.sino.soa.td.srv.inquiryassetlocation.dto.TdSrvAssetLocationDTO;
import com.sino.soa.td.srv.inquiryassetlocation.model.TdSrvAssetLocationModel;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.dto.SynLogDTO;

/**
 * <p>Title: SrvAssetCategoryDAO</p>
 * <p>Description:�����Զ����ɷ������SrvAssetCategoryDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * date��Sep 15, 2011 9:39:57 PM
 * author��wzp
 * function��ͬ��MIS�ʲ��ص�_TD
 */


public class TdSrvAssetLocationDAO extends AMSBaseDAO {

    /**
     * ���ܣ��ʲ������� SRV_ASSET_CATEGORY ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SrvAssetCategoryDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public TdSrvAssetLocationDAO(SfUserDTO userAccount, TdSrvAssetLocationDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        TdSrvAssetLocationDTO dtoPara = (TdSrvAssetLocationDTO) dtoParameter;
        super.sqlProducer = new TdSrvAssetLocationModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ��Ե�ַ��Ϣ�ĸ����Ƿ�ɹ���(��TD)
     *
     * @param ds                   DTOSet ��������ݶ���
     * @param segment1������(�ʲ��ص��һ��)
     * @return �ɹ����µļ�¼��
     */
//    public int synAssetLocation(DTOSet ds, String segment1) {
//        SynLogUtil log = new SynLogUtil();
//        SQLModel sqlModel = null;
//        int count = 0;
//        try {
//            TdSrvAssetLocationDTO firstLocation = (TdSrvAssetLocationDTO) ds.getDTO(0);
//            Row row = getEcomCode(firstLocation.getLocationCombinationCode());
//            String orgId = row.getStrValue("ORGANIZATION_ID");
//            String bookTypeCode = row.getStrValue("BOOK_TYPE_CODE");
//            TdSrvAssetLocationModel modelProducer = (TdSrvAssetLocationModel) sqlProducer;
//            for (int i = 0; i < ds.getSize(); i++) {
//                SrvAssetLocationDTO dto = (SrvAssetLocationDTO) ds.getDTO(i);
//                dto.setOrgId(orgId);
//                dto.setBookTypeCode(bookTypeCode);
//                setDTOParameter(dto);
//                if (getOuInfom() < 1) {
//                    sqlModel = modelProducer.getDataCreateModel();
//                } else {
//                    sqlModel = modelProducer.getDataUpdateModel();
//                }
//                if (DBOperator.updateRecord(sqlModel, conn)) {
//                    count++;
//                }
//            }
//
//        } catch (Throwable e) {
//            Logger.logError(e);
//            SimpleCalendar s = new SimpleCalendar();
//            SynLogDTO logDto = new SynLogDTO();
//            logDto.setSynType(SrvType.SRV_FA_LOCATION);
//            logDto.setSynMsg(e.getMessage());
//            logDto.setCreatedBy(userAccount.getUserId());
//            try {
//                logDto.setCreationDate(s.getCalendarValue());
//            } catch (CalendarException e1) {
//                e1.printLog();
//            }
//            try {
//                log.synLog(logDto, conn);
//            } catch (DataHandleException e1) {
//                e1.printLog();
//            }
//        }
//        return count;
//    }

    /**
     * ���ܣ�TD��ַ��Ϣ�ĸ����Ƿ�ɹ���
     * @param ds  DTOSet ��������ݶ���
     * @param segment1������(�ʲ��ص��һ��)
     * @return �ɹ����µļ�¼��
     */
    public int synTdAssetLocation(DTOSet ds, String segment1) {
        SynLogUtil log = new SynLogUtil();
        SQLModel sqlModel = null;
        int count = 0;
        Row row =null;
        String orgId ="";
        String bookTypeCode ="";
        try {
        	TdSrvAssetLocationDTO firstLocation = (TdSrvAssetLocationDTO) ds.getDTO(0);
            row = getEcomCode(firstLocation.getLocationCombinationCode());
            if(row!=null){ //4110
                 orgId = row.getStrValue("ORGANIZATION_ID");
                 bookTypeCode = row.getStrValue("BOOK_TYPE_CODE");
            }else{    //8110
            	row=getEcomCode2(firstLocation.getLocationCombinationCode());
            	if(row!=null){
                	orgId = row.getStrValue("ORGANIZATION_ID");
                    bookTypeCode = row.getStrValue("BOOK_TYPE_CODE");
            	}
            }
            TdSrvAssetLocationModel modelProducer = (TdSrvAssetLocationModel) sqlProducer;
            for (int i = 0; i < ds.getSize(); i++) {
            	TdSrvAssetLocationDTO dto = (TdSrvAssetLocationDTO) ds.getDTO(i);
                dto.setOrgId(orgId);
                dto.setBookTypeCode(bookTypeCode);
                setDTOParameter(dto);
                if (isExistTdAssetsLocation() < 1) {
                    sqlModel = modelProducer.getTdDataCreateModel();
                } else {
                    sqlModel = modelProducer.getTdDataUpdateModel();
                }
                if (DBOperator.updateRecord(sqlModel, conn)) {
                    count++;
                }
            }
        } catch (Throwable e) {
            Logger.logError(e);
            SimpleCalendar s = new SimpleCalendar();
            SynLogDTO logDto = new SynLogDTO();
            logDto.setSynType(SrvType.SRV_TD_FA_LOCATION);
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

//    public int getOuInfom() {
//        TdSrvAssetLocationModel modelProducer = (TdSrvAssetLocationModel) sqlProducer;
//        SQLModel sqlModel = modelProducer.getEcouInforModel();
//        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
//        RowSet rs = null;
//        try {
//            simp.executeQuery();
//            if (simp.hasResult()) {
//                rs = simp.getSearchResult();
//                return rs.getSize();
//            }
//
//        } catch (QueryException e) {
//            e.printLog();
//        }
//        return 0;
//    }

    /**
     * ���ܣ��ж��Ƿ����д�TD��ַ
     *
     * @return
     */
    public int isExistTdAssetsLocation() {
        TdSrvAssetLocationModel modelProducer = (TdSrvAssetLocationModel) sqlProducer;
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
     * ���ܣ����ݵص����ڶ��λ�ȡOU��֯ID���ʲ��˲����� (OU1_4110)
     * @param objectCode �ص����
     * @return row �����ݶ���
     */
    private Row getEcomCode(String objectCode) {
        Row row = null;
        TdSrvAssetLocationModel modelProducer = (TdSrvAssetLocationModel) sqlProducer;
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
    
    /**
     * ���ܣ����ݵص����ڶ��λ�ȡOU��֯ID���ʲ��˲����� (OU2_8110)
     * @param objectCode �ص����
     * @return row �����ݶ���
     */
    private Row getEcomCode2(String objectCode) {
        Row row = null;
        TdSrvAssetLocationModel modelProducer = (TdSrvAssetLocationModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getEcomCodeModel2(objectCode);
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