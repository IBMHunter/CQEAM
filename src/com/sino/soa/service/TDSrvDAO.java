package com.sino.soa.service;

import java.sql.Connection;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.config.SinoConfig;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.soa.common.MIS_CONSTANT;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.common.SrvType;
import com.sino.soa.td.srv.AssetPeriodStatus.dao.TdSrvAssetPeriodDAO;
import com.sino.soa.td.srv.AssetPeriodStatus.dto.TdSrvAssetPeriodStatusDTO;
import com.sino.soa.td.srv.AssetPeriodStatus.srv.TDInquiryAssetPeriodStatusSrv;
import com.sino.soa.td.srv.accountbalance.srv.SBFIGLTdTransAccountBalanceSrv;
import com.sino.soa.td.srv.assetcategory.dao.SBFIFATdSrvAssetCategoryDAO;
import com.sino.soa.td.srv.assetcategory.dto.SBFIFATdSrvAssetCategoryDTO;
import com.sino.soa.td.srv.assetcategory.srv.SBFIFATdInquiryAssetCategorySrv;
import com.sino.soa.td.srv.assetsinfoupdate.srv.TDTransAssetHeaderInfoSrv;
import com.sino.soa.td.srv.assettransbtwcompanysrv.dao.SBFIFATdAssetsTransBtwCompanyDAO;
import com.sino.soa.td.srv.assettransbtwcompanysrv.dto.SBFIFATdAssetsTransBtwCompanyDTO;
import com.sino.soa.td.srv.assettransincompanysrv.dao.SBFIFATdAssetsTransInCompanyDAO;
import com.sino.soa.td.srv.assettransincompanysrv.dto.SBFIFATdAssetsTransInCompanyDTO;
import com.sino.soa.td.srv.employee.dao.SBHRHRSrvTdEmpAssignDAO;
import com.sino.soa.td.srv.employee.dao.SBHRHRSrvTdEmpInfoDAO;
import com.sino.soa.td.srv.employee.dto.SBHRHRSrvTdEmployeeInfoDTO;
import com.sino.soa.td.srv.employee.srv.SBHRHRTdInquiryEmpAssignInfoSrv;
import com.sino.soa.td.srv.employee.srv.SBHRHRTdInquiryEmpBaseInfoSrv;
import com.sino.soa.td.srv.inquiryassetlocation.dao.TdSrvAssetLocationDAO;
import com.sino.soa.td.srv.inquiryassetlocation.dto.TdSrvAssetLocationDTO;
import com.sino.soa.td.srv.inquiryassetlocation.srv.TDSrvAssetLocationSrv;
import com.sino.soa.td.srv.orgstructure.dao.SBHRHRTdInquiryOrgStructureDAO;
import com.sino.soa.td.srv.orgstructure.dto.SBHRHRTdInquiryOrgStructureDTO;
import com.sino.soa.td.srv.orgstructure.srv.SBHRHRTdInquiryOrgStructureSrv;
import com.sino.soa.td.srv.projectInfo.dao.TDSrvProjectInfoDAO;
import com.sino.soa.td.srv.projectInfo.dto.TDSrvProjectInfoDTO;
import com.sino.soa.td.srv.projectInfo.srv.TDInquiryProjectInfoSrv;
import com.sino.soa.td.srv.transTaskInfo.srv.TDTransTaskInfoSrv;
import com.sino.soa.td.srv.transassetdistribution.srv.TDTransAssetDistributionSrv;
import com.sino.soa.td.srv.transfaassetinfo.srv.TDTransFaAssetInfoSrv;
import com.sino.soa.td.srv.valueinfo.dao.SBSYSYTdInquiryVSetValueInfoDAO;
import com.sino.soa.td.srv.valueinfo.dto.SBSYSYTdInquiryVSetValueInfoDTO;
import com.sino.soa.td.srv.valueinfo.srv.SBSYSYTdInquiryVSetValueInfoSrv;
import com.sino.soa.td.srv.vendor.dao.TdSrvVendorInfoDAO;
import com.sino.soa.td.srv.vendor.dto.TdSrvVendorInfoDTO;
import com.sino.soa.td.srv.vendor.srv.TDInquiryVendorInfoSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.SynUpdateDateUtils;
import com.sino.soa.util.dto.SynLogDTO;
import com.sino.sso.dao.SSOUserLoginDAO;

/**
 * User: zhoujs
 * Date: 2009-5-24 16:04:20
 * Function:�Զ�����ʱ (TD)
 */
public class TDSrvDAO {

    /**
     * ��ѯ�ʲ����
     */
    public void synAssetCategory(Connection conn, SfUserDTO user) {
        int count = 0;
        long resumeTime = 0;
        int errorCount = 0;
        long start = System.currentTimeMillis();
        SynLogDTO logDTO = null;
        try {
            SynLogUtil logUtil = new SynLogUtil();
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_FA_CATEGORY);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD�ʲ����ʼ");
            logUtil.synLog(logDTO, conn);

            SBFIFATdInquiryAssetCategorySrv service = new SBFIFATdInquiryAssetCategorySrv();
            String lastUpdateDate = logUtil.getLastUpdateDate(SrvType.SRV_TD_FA_CATEGORY, conn);
            SBFIFATdSrvAssetCategoryDAO srvAssetCategoryDAO = new SBFIFATdSrvAssetCategoryDAO(user, new SBFIFATdSrvAssetCategoryDTO(), conn);
            service.setLastUpdateDate(lastUpdateDate);
            service.execute();
            SrvReturnMessage srm = service.getReturnMessage();
            if (srm.getErrorFlag().equals("Y")) {
                DTOSet ds = service.getDs();
                if (ds.getSize() > 0) {
                    count = srvAssetCategoryDAO.SavaAssetCategory(ds);
                }
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_FA_CATEGORY, conn);
            }
            errorCount = srvAssetCategoryDAO.getErrorCount();
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_FA_CATEGORY);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD�ʲ����������ɹ�" + count + "��ʧ��" + errorCount + "����ʱ" + resumeTime + "����");
            logUtil.synLog(logDTO, conn);
        } catch (DatatypeConfigurationException e) {
            Logger.logError(e);
        } catch (PoolException e) {
            Logger.logError(e);
        } catch (QueryException e) {
            Logger.logError(e);
        } catch (ContainerException e) {
            Logger.logError(e);
        } catch (CalendarException e) {
            Logger.logError(e);
        } catch (DTOException e) {
            Logger.logError(e);
        } catch (DataHandleException e) {
            Logger.logError(e);
        }
    }

    /**
     * ��ѯԱ����Ϣ
     */
    public void synEmployeeInfo(Connection conn, SfUserDTO user) {
        long resumeTime = 0;
        int baseErrorCount = 0;
        int baseTotalCount = 0;
        int assignErrorCount = 0;
        int assignTotalCount = 0;
        long start = System.currentTimeMillis();
        SBHRHRTdInquiryEmpBaseInfoSrv employeeInfoSrv = new SBHRHRTdInquiryEmpBaseInfoSrv();
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        try {
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_EMPLOYEE);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TDԱ����Ϣ��ʼ");
            logUtil.synLog(logDTO, conn);

            employeeInfoSrv.setStartLastUpdateDate(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_EMPLOYEE, conn));
            employeeInfoSrv.excute();
            SrvReturnMessage srvMessage = employeeInfoSrv.getReturnMessage();
            if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                DTOSet ds = employeeInfoSrv.getDs();
                for (int i = 0; i < ds.getSize(); i++) {
                    SBHRHRSrvTdEmployeeInfoDTO dto = (SBHRHRSrvTdEmployeeInfoDTO) ds.getDTO(i);
                    SBHRHRSrvTdEmpInfoDAO srvEmployeeInfoDAO = new SBHRHRSrvTdEmpInfoDAO(user, dto, conn);
                    try {
                        if (srvEmployeeInfoDAO.isServiceTypeExists(dto.getEmployeeNumber())) {
                            srvEmployeeInfoDAO.updateData();
                        } else {
                            srvEmployeeInfoDAO.createData();
                        }
                        baseTotalCount++;
                    } catch (Exception e) {
                        Logger.logError(e);
                        logDTO = new SynLogDTO();
                        logDTO.setSynType(SrvType.SRV_TD_EMPLOYEE);
                        logDTO.setCreatedBy(user.getUserId());
                        logDTO.setSynMsg(e.toString());
                        logUtil.synLog(logDTO, conn);
                        baseErrorCount++;
                    }
                }
                SBHRHRTdInquiryEmpAssignInfoSrv empAssignInfoSrv = new SBHRHRTdInquiryEmpAssignInfoSrv();
                empAssignInfoSrv.setStartLastUpdateDate(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_EMPLOYEE, conn));
                empAssignInfoSrv.execute();
                SrvReturnMessage empMessage = empAssignInfoSrv.getReturnMessage();
                if (empMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                    DTOSet dtos = empAssignInfoSrv.getDs();
                    for (int i = 0; i < dtos.getSize(); i++) {
                        SBHRHRSrvTdEmployeeInfoDTO dto = (SBHRHRSrvTdEmployeeInfoDTO) dtos.getDTO(i);
                        SBHRHRSrvTdEmpAssignDAO srvEmpAssignInfoDAO = new SBHRHRSrvTdEmpAssignDAO(user, dto, conn);
                        try {
                            if (srvEmpAssignInfoDAO.isEmployeeExists(dto.getEmployeeNumber())) {
                                srvEmpAssignInfoDAO.updateData();
                                assignTotalCount++;
                            }
                        } catch (Exception e) {
                            Logger.logError(e);
                            logDTO = new SynLogDTO();
                            logDTO.setSynType(SrvType.SRV_TD_EMPLOYEE);
                            logDTO.setCreatedBy(user.getUserId());
                            logDTO.setSynMsg(e.toString());
                            logUtil.synLog(logDTO, conn);
                            assignErrorCount++;
                        }
                    }
                }
                if (baseErrorCount + assignErrorCount == 0) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_EMPLOYEE, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_EMPLOYEE, conn);
                }
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_EMPLOYEE);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TDԱ����Ϣ��������ͬ��" + (baseTotalCount + baseErrorCount + assignTotalCount + assignErrorCount) + "����¼��" +
                    "Ա��������Ϣͬ���ɹ�" + baseTotalCount + "��ʧ��" + baseErrorCount + "��" +
                    "Ա��������Ϣͬ���ɹ�" + assignTotalCount + "��ʧ��" + assignErrorCount + "��" +
                    "��ʱ" + resumeTime + "����");
            logUtil.synLog(logDTO, conn);
        } catch (Throwable e) {
            Logger.logError(e);
        }
    }

    /**
     * ��ѯֵ����Ϣ
     */
    public void synSetValue(Connection conn, SfUserDTO user, String source) {
        int totalCount = 0;
        long resumeTime = 0;
        int errorCount = 0;
        long start = System.currentTimeMillis();
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        try {
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_SET_VALUESET);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TDֵ��ֵ��Ϣ����ʼ");
            logUtil.synLog(logDTO, conn);

            SBSYSYTdInquiryVSetValueInfoDAO mFndFlexValuesDAO = new SBSYSYTdInquiryVSetValueInfoDAO(user, null, conn);
            List valuesList = mFndFlexValuesDAO.getAllFlexValues(source);
            if (valuesList != null && valuesList.size() > 0) {
                SBSYSYTdInquiryVSetValueInfoSrv setValueInfoSrv = new SBSYSYTdInquiryVSetValueInfoSrv();
                setValueInfoSrv.setStartLastUpdateDate(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_SET_VALUESET, conn));
                for (int i = 0; i < valuesList.size(); i++) {
                    String flexValueName = (String) valuesList.get(i);
                    setValueInfoSrv.setFlexValueName(flexValueName);
                    setValueInfoSrv.execute();
                    SrvReturnMessage srvMessage = setValueInfoSrv.getReturnMessage();
                    if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                        DTOSet ds = setValueInfoSrv.getDs();
                        for (int j = 0; j < ds.getSize(); j++) {
                            SBSYSYTdInquiryVSetValueInfoDTO valueSetDTO = (SBSYSYTdInquiryVSetValueInfoDTO) ds.getDTO(j);
                            mFndFlexValuesDAO.setDTOParameter(valueSetDTO);
                            try {
                                if (mFndFlexValuesDAO.isexistsSetValueModel(valueSetDTO.getFlexValue(), valueSetDTO.getFlexValueSetId())) {
                                    mFndFlexValuesDAO.updateData();
                                } else {
                                    mFndFlexValuesDAO.createData();
                                }
                                totalCount++;
                            } catch (DataHandleException e) {
                                Logger.logError(e);
                                logDTO = new SynLogDTO();
                                logDTO.setSynType(SrvType.SRV_TD_SET_VALUESET);
                                logDTO.setCreatedBy(user.getUserId());
                                logDTO.setSynMsg(e.toString());
                                logUtil.synLog(logDTO, conn);
                                errorCount++;
                            }
                        }
                    }
                }
                if (errorCount == 0) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_SET_VALUESET, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_SET_VALUESET, conn);
                }
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_SET_VALUESET);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TDֵ��ֵ��Ϣ����������ɹ�" + totalCount + "��ʧ��" + errorCount + "����ʱ" + resumeTime + "����");
            logUtil.synLog(logDTO, conn);
        } catch (Throwable e) {
            Logger.logError(e);
        }
    }

    /**
     * ��ѯ��֯�ṹ
     */
    public void synOrgstructure(Connection conn, SfUserDTO user) {
        int count = 0;
        long resumeTime = 0;
        int falsecount = 0;
        long start = System.currentTimeMillis();
        SynLogDTO logDTO = null;
        try {
            SynLogUtil logUtil = new SynLogUtil();
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_ORG_STRUCTURE);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��֯�ṹ��ʼ");
            logUtil.synLog(logDTO, conn);

            SBHRHRTdInquiryOrgStructureSrv service = new SBHRHRTdInquiryOrgStructureSrv();
            service.excute();
            SrvReturnMessage srm = service.getReturnMessage();
            SBHRHRTdInquiryOrgStructureDAO dao = new SBHRHRTdInquiryOrgStructureDAO(user, new SBHRHRTdInquiryOrgStructureDTO(), conn);
            if (srm.getErrorFlag().equalsIgnoreCase("Y")) {
                DTOSet ds = service.getDs();
                if (ds.getSize() > 0) {
                    ds = filterDtoSet(ds);
                    count = dao.synOrgStructure(ds);
                }
            }
            falsecount = dao.getErrorCount();
            if (falsecount == 0) {//û�з�������ż�¼ͬ�����͵ĸ���ʱ��
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_ORG_STRUCTURE, conn);
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_ORG_STRUCTURE, conn);
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_ORG_STRUCTURE);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��֯�ṹ������ͬ��" + (count + falsecount) + "����¼���ɹ�" + count + "��ʧ��" + (falsecount) + "����ʱ" + resumeTime + "����");
            logUtil.synLog(logDTO, conn);
        } catch (CalendarException e) {
            Logger.logError(e);
        } catch (DTOException e) {
            Logger.logError(e);
        } catch (DataHandleException e) {
            Logger.logError(e);
        }
    }

    public DTOSet filterDtoSet(DTOSet ds) {
        DTOSet returnds = new DTOSet();
        for (int i = 0; i < ds.getSize(); i++) {
            SBHRHRTdInquiryOrgStructureDTO dto = (SBHRHRTdInquiryOrgStructureDTO) ds.getDTO(i);
            if (dto.getOrganizationName().indexOf("OU_") < 0 && dto.getOrganizationName().indexOf("IO_") < 0 && dto.getOrganizationName().indexOf("IA_") < 0 && dto.getOrganizationName().indexOf("LA_") < 0) {
                try {
                    returnds.addDTO(dto);
                } catch (DTOException e) {
                    e.printLog();
                }
            }
        }
        return returnds;
    }

    /**
     * ��ѯ��Ŀ��Ϣ
     */
    public void synProjectInfo(Connection conn, SfUserDTO user) {
        int totalCount = 0;
        int errorCount = 0;
        long resumeTime = 0;
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        long start = System.currentTimeMillis();
        try {
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_PA_PROJECT);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��Ŀ��Ϣ��ʼ");
            logUtil.synLog(logDTO, conn);

            TDInquiryProjectInfoSrv projectInfoSrv = new TDInquiryProjectInfoSrv();
            projectInfoSrv.execute();
            SrvReturnMessage srvMessage = projectInfoSrv.getReturnMessage();

            if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                DTOSet ds = projectInfoSrv.getDs();
                TDSrvProjectInfoDAO srvProjectInfoDAO = new TDSrvProjectInfoDAO(user, null, conn);
                for (int i = 0; i < ds.getSize(); i++) {
                    TDSrvProjectInfoDTO dto = (TDSrvProjectInfoDTO) ds.getDTO(i);
                    srvProjectInfoDAO.setDTOParameter(dto);
                    try {
                        if (SynUpdateDateUtils.getBetweenDays(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_PA_PROJECT, conn), (dto.getLastUpdateDate().toString())) > 0) {
                            if (srvProjectInfoDAO.isProjecdtExists(dto.getSegment1())) {
                                srvProjectInfoDAO.updateData();
                            } else {
                                srvProjectInfoDAO.createData();
                            }
                            totalCount++;
                        }

                    } catch (Throwable e) {
                        Logger.logError(e);
                        logDTO = new SynLogDTO();
                        logDTO.setSynType(SrvType.SRV_TD_PA_PROJECT);
                        logDTO.setCreatedBy(user.getUserId());
                        logDTO.setSynMsg(e.toString());
                        logUtil.synLog(logDTO, conn);
                        errorCount++;
                    }
                }
                if (errorCount == 0) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_PA_PROJECT, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_PA_PROJECT, conn);
                }
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_PA_PROJECT);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��Ŀ��Ϣ�������ɹ�" + totalCount + "��ʧ��" + errorCount + "����ʱ" + resumeTime + "����");
            logUtil.synLog(logDTO, conn);
        } catch (Throwable e) {
            Logger.logError(e);
        }
    }

    /**
     * ��ѯ��Ӧ����Ϣ
     */
    public void synVendorInfo(Connection conn, SfUserDTO user) {
        int count = 0;
        int errorCount = 0;
        long resumeTime = 0;
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        long start = System.currentTimeMillis();
        try {
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_VENDOR);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��Ӧ����Ϣ��ʼ");
            logUtil.synLog(logDTO, conn);

            TDInquiryVendorInfoSrv service = new TDInquiryVendorInfoSrv();
            service.setLastUpdateDate(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_VENDOR, conn));
            service.execute();
            SrvReturnMessage srvMessage = service.getReturnMessage();
            TdSrvVendorInfoDAO srvVendorInfoDAO = new TdSrvVendorInfoDAO(user, new TdSrvVendorInfoDTO(), conn);
            if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_VENDOR, conn);
                DTOSet ds = service.getDs();
                if (ds.getSize() > 0) {
                    count = srvVendorInfoDAO.synVendorInfo(ds);
                }
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_VENDOR, conn);
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_VENDOR);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��Ӧ����Ϣ������ͬ��" + (count + errorCount) + "����¼���ɹ�" + count + "��ʧ��" + errorCount + "����ʱ" + resumeTime + "����");
            logUtil.synLog(logDTO, conn);
        } catch (DataHandleException e) {
            Logger.logError(e);
        } catch (QueryException e) {
            Logger.logError(e);
        } catch (ContainerException e) {
            Logger.logError(e);
        } catch (Exception e) {
            Logger.logError(e);
        }
    }

    /**
     * ��ѯ����ڼ�
     */
    public void synPeriodStatus(Connection conn, SfUserDTO user) {
        int trueCount = 0;
        int totalCount = 0;
        long resumeTime = 0;
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        long start = System.currentTimeMillis();
        try {

            TDInquiryAssetPeriodStatusSrv service = new TDInquiryAssetPeriodStatusSrv();
            TdSrvAssetPeriodStatusDTO dtoParameter = new TdSrvAssetPeriodStatusDTO();
            TdSrvAssetPeriodDAO srvAssetPeriodDAO = new TdSrvAssetPeriodDAO(user, dtoParameter, conn);
            String bookType[] = logUtil.getBookTypeCodeModel(conn, MIS_CONSTANT.SOURCE_TD);

            for (int i = 0; i < bookType.length; i++) {
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_TD_FA_PERIOD);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg( bookType[i] +":ͬ��TD�ʲ�����ڼ俪ʼ");
                logUtil.synLog(logDTO, conn);
                service.setBookTypeCode(bookType[i]);
                service.execute();
                SrvReturnMessage srm = service.getReturnMessage();
                if (srm.getErrorFlag().equals("Y")) {
                    DTOSet ds = service.getDs();
                    totalCount += ds.getSize();
                    trueCount += srvAssetPeriodDAO.synAssetPeriod(ds);
                }
                resumeTime = System.currentTimeMillis() - start;
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_TD_FA_PERIOD);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg( bookType[i] +":ͬ��TD�ʲ�����ڼ������ͬ��" + totalCount + "����¼���ɹ�" + trueCount + "��ʧ��" + (totalCount - trueCount) + "����ʱ" + resumeTime + "����");
                if (srm.getErrorFlag().equals("N")) {
                    logDTO.setSynMsg(logDTO.getSynMsg() + ".������Ϣ��" + srm.getErrorMessage());
                }
                logUtil.synLog(logDTO, conn);

            }
            if (totalCount == trueCount) {
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_FA_PERIOD, conn);
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_FA_PERIOD, conn);
            }

        } catch (Exception e) {
            Logger.logError(e);
        }
    }

    /**
     * ��ѯ�ʲ��ص�
     */
    public void synFaLocation(Connection conn, SfUserDTO user) {
        int totalCount = 0;
        int count = 0;
        long resumeTime = 0;
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        long start = System.currentTimeMillis();
        try {
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_FA_LOCATION);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD�ʲ��ص㿪ʼ");
            logUtil.synLog(logDTO, conn);

            TDSrvAssetLocationSrv service = new TDSrvAssetLocationSrv();
            TdSrvAssetLocationDTO dtoParameter = new TdSrvAssetLocationDTO();
            TdSrvAssetLocationDAO srvAssetLocationDAO = new TdSrvAssetLocationDAO(user, dtoParameter, conn);
            service.setLastUpDate(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_FA_LOCATION, conn));
            SrvProcessModel spm = new SrvProcessModel();
            SQLModel sqlModel = spm.getLocalCodeModel(MIS_CONSTANT.SOURCE_TD, SinoConfig.getLoc1SetNameTD());
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            Row row = null;
            if (simp.hasResult()) {
                RowSet rs = simp.getSearchResult();
                for (int i = 0; i < rs.getSize(); i++) {
                    row = rs.getRow(i);
                    String cc = row.getStrValue("FLEX_VALUE");
                    service.setSegment1(cc);
                    service.execute();
                    SrvReturnMessage srm = service.getReturnMessage();
                    if (srm.getErrorFlag().equals("Y")) {
                        DTOSet ds = service.getDs();
                        totalCount += ds.getSize();
                        if (ds.getSize() > 0) {
                            count += srvAssetLocationDAO.synTdAssetLocation(ds, cc);
                        }
                    }
                }
                if (totalCount == count) { //ȫ���ɹ��Ÿ���ͬ��������־�������´λ�ȡ��������
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_FA_LOCATION, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_FA_LOCATION, conn);
                }
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_FA_LOCATION);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD�ʲ��ص������ͬ��" + totalCount + "����¼���ɹ�" + count + "��ʧ��" + (totalCount - count) + "����ʱ" + resumeTime + "����");
            logUtil.synLog(logDTO, conn);
        } catch (Exception e) {
            Logger.logError(e);
        }
    }

    /**
     * ��ѯ�ʲ�ͷ������Ϣ(ODI)
     */
    public void synAssetHeaderInfo(Connection conn, SfUserDTO user) {
        long resumeTime = 0;
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        long start = System.currentTimeMillis();
        try {
            truncateData("ZTE_TD_ASSET_HEADER", conn);
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_FA_HEADERINFO);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD�ʲ�ͷ��Ϣ(ODI)��ʼ");
            logUtil.synLog(logDTO, conn);

            TDTransAssetHeaderInfoSrv transAssetSrv = new TDTransAssetHeaderInfoSrv();
            String envCode = SynUpdateDateUtils.getEnvCode("TDTransAssetHeaderInfoSrv", conn);
//            String bookTypeCodes[] = logUtil.getBookTypeCodeModel(conn, MIS_CONSTANT.SOURCE_TD);
            String bookTypeCodes[] = logUtil.getBookTypeCode(conn, MIS_CONSTANT.SOURCE_TD);//����ʤ�޸ģ����ӻ�ȡ�����ʲ����˲�����

            String lastUpdateDate = SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_FA_HEADERINFO, conn);
            boolean hasError = false;
            if (bookTypeCodes != null && bookTypeCodes.length > 0) {
                for (int i = 0; i < bookTypeCodes.length; i++) {
                    transAssetSrv.setEnvCode(envCode);
                    transAssetSrv.setBookTypeCode(bookTypeCodes[i]);
                    transAssetSrv.setStartLastUpdateDate(lastUpdateDate);
                    try {
                        transAssetSrv.excute();
                        SrvReturnMessage srvMessage = transAssetSrv.getReturnMessage();
                        if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                            resumeTime = System.currentTimeMillis() - start;
                            logDTO = new SynLogDTO();
                            logDTO.setSynType(SrvType.SRV_TD_FA_HEADERINFO);
                            logDTO.setCreatedBy(user.getUserId());
                            logDTO.setSynMsg("ͬ��TD�ʲ�ͷ��Ϣ(ODI)�ɹ�����ʱ" + resumeTime + "���룬�ʲ��˲���" + bookTypeCodes[i]);
                            logUtil.synLog(logDTO, conn);
                        } else {
                            resumeTime = System.currentTimeMillis() - start;
                            logDTO = new SynLogDTO();
                            logDTO.setSynType(SrvType.SRV_TD_FA_HEADERINFO);
                            logDTO.setCreatedBy(user.getUserId());
                            logDTO.setSynMsg("ͬ��TD�ʲ�ͷ��Ϣ(ODI)ʧ�ܡ���ʱ" + resumeTime + "���룬�ʲ��˲���" + bookTypeCodes[i] + "��������Ϣ��" + srvMessage.getErrorMessage());
                            logUtil.synLog(logDTO, conn);
                            hasError = true;
                        }
                    } catch (Throwable ex) {
                        hasError = true;
                    }
                }
                if (!hasError) {//��¼��־λ�����������©���ݶ�ȡ������
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_FA_HEADERINFO, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_FA_HEADERINFO, conn);
                }
            }
        } catch (Throwable e) {
            Logger.logError(e);
        }
    }

    /**
     * ��ѯ�ʲ���������Ϣ(ODI)
     */
    public void synAssetDistribute(Connection conn, SfUserDTO user) {
        long resumeTime = 0;
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        long start = System.currentTimeMillis();
        try {
            truncateData("ZTE_TD_ASSET_DISTRIBUTION", conn);
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_FA_DISTRIBUTION);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD�ʲ���������Ϣ(ODI)��ʼ");
            logUtil.synLog(logDTO, conn);

            TDTransAssetDistributionSrv transAssetDistributionSrv = new TDTransAssetDistributionSrv();
            String envCode = SynUpdateDateUtils.getEnvCode("TDTransAssetDistributionSrv", conn);
//            String bookTypeCodes[] = logUtil.getBookTypeCodeModel(conn, MIS_CONSTANT.SOURCE_TD);
            String bookTypeCodes[] = logUtil.getBookTypeCode(conn, MIS_CONSTANT.SOURCE_TD);//����ʤ�޸ģ����ӻ�ȡ�����ʲ����˲�����

            String lastUpdateDate = SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_FA_DISTRIBUTION, conn);
            boolean hasError = false;
            if (bookTypeCodes != null && bookTypeCodes.length > 0) {
                for (int i = 0; i < bookTypeCodes.length; i++) {
                    transAssetDistributionSrv.setEnvCode(envCode);
                    transAssetDistributionSrv.setBookTypeCode(bookTypeCodes[i]);
                    transAssetDistributionSrv.setStratLastUpdateDate(lastUpdateDate);
                    try {
                        transAssetDistributionSrv.excute();
                        SrvReturnMessage srvMessage = transAssetDistributionSrv.getReturnMessage();
                        if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                            resumeTime = System.currentTimeMillis() - start;
                            logDTO = new SynLogDTO();
                            logDTO.setSynType(SrvType.SRV_TD_FA_DISTRIBUTION);
                            logDTO.setCreatedBy(user.getUserId());
                            logDTO.setSynMsg("ͬ��TD�ʲ���������Ϣ(ODI)�ɹ�����ʱ" + resumeTime + "���룬�ʲ��˲���" + bookTypeCodes[i]);
                            logUtil.synLog(logDTO, conn);
                        } else {
                            resumeTime = System.currentTimeMillis() - start;
                            logDTO = new SynLogDTO();
                            logDTO.setSynType(SrvType.SRV_TD_FA_DISTRIBUTION);
                            logDTO.setCreatedBy(user.getUserId());
                            logDTO.setSynMsg("ͬ��TD�ʲ���������Ϣ(ODI)ʧ�ܡ���ʱ" + resumeTime + "���룬�ʲ��˲���" + bookTypeCodes[i] + "��������Ϣ��" + srvMessage.getErrorMessage());
                            logUtil.synLog(logDTO, conn);
                            hasError = true;
                        }
                    } catch (Throwable ex) {
                        hasError = true;
                    }
                }
                if (!hasError) {//��¼��־λ�����������©���ݶ�ȡ������
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_FA_DISTRIBUTION, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_FA_DISTRIBUTION, conn);
                }
            }
        } catch (Throwable e) {
            Logger.logError(e);
        }
    }

    /**
     * ��ѯ�ʲ�������Ϣ����(ODI)
     */
    public void synAssetInfo(Connection conn, SfUserDTO user, String periodName) {
        long resumeTime = 0;
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        long start = System.currentTimeMillis();
        boolean hasError = false;
        try {
            truncateData("ZTE_TD_ASSET_INFO", conn);
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_FA_TRANS_ASSET);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD�ʲ�������Ϣ(ODI)��ʼ");
            logUtil.synLog(logDTO, conn);

            String bookTypeCodes[] = logUtil.getBookTypeCode(conn, MIS_CONSTANT.SOURCE_TD);//����ʤ�޸ģ����ӻ�ȡ�����ʲ����˲�����

            TDTransFaAssetInfoSrv transAssetInfoSrv = new TDTransFaAssetInfoSrv();
            String envCode = SynUpdateDateUtils.getEnvCode("TDTransFaAssetInfoSrv", conn);
//            String periodName = SynUpdateDateUtils.getPeriodName(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_FA_TRANS_ASSET, conn));
//����ʤע�ͣ�����ڼ���Ҫ��ȡ�رյ�������ڼ�

            for (int i=0;i<bookTypeCodes.length;i++) {
                transAssetInfoSrv.setEnvCode(envCode);
                transAssetInfoSrv.setPeriodName(periodName);
                transAssetInfoSrv.setBookTypeCode(bookTypeCodes[i]);
                transAssetInfoSrv.excute();
                SrvReturnMessage srvMessage = transAssetInfoSrv.getReturnMessage();
                if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_TD_FA_TRANS_ASSET);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��TD�ʲ�������Ϣ(ODI)�ɹ�����ʱ" + resumeTime + "����");
                    logUtil.synLog(logDTO, conn);
                } else {
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_TD_FA_TRANS_ASSET);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��TD�ʲ�������Ϣ(ODI)ʧ�ܡ���ʱ" + resumeTime + "���롣������Ϣ��" + srvMessage.getErrorMessage());
                    logUtil.synLog(logDTO, conn);
                    hasError = true;
                }
            }
        } catch (Throwable e) {
            Logger.logError(e);
            hasError = true;
        } finally {
            if (!hasError) {
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_FA_TRANS_ASSET, conn);
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_FA_TRANS_ASSET, conn);
            }
        }
    }

    /**
     * ��ѯ��Ŀ���(ODI)
     */
    public void synAccountBlance(Connection conn, SfUserDTO user, String periodName) {
        long resumeTime = 0;
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        long start = System.currentTimeMillis();
        boolean hasError = false;
        try {
            truncateData("ZTE_TD_ACCOUNT_BALANCE", conn);
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_ACCOUNT_BALANCE);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��Ŀ���(ODI)��ʼ");
            logUtil.synLog(logDTO, conn);
            SBFIGLTdTransAccountBalanceSrv transAccountBalanceSrv = new SBFIGLTdTransAccountBalanceSrv();
            String envCode = SynUpdateDateUtils.getEnvCode("TDTransAccountBalanceSrv", conn);
//            String periodName = SynUpdateDateUtils.getPeriodName(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_ACCOUNT_BALANCE, conn));
//����ʤע�ͣ���Ҫ��ȡ�����ѹرջ���ڼ�
            transAccountBalanceSrv.setEnvCode(envCode);
            transAccountBalanceSrv.setPeriodName(periodName);
            transAccountBalanceSrv.excute();
            SrvReturnMessage srvMessage = transAccountBalanceSrv.getReturnMessage();
            if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                resumeTime = System.currentTimeMillis() - start;
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_TD_ACCOUNT_BALANCE);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��TD��Ŀ���(ODI)�ɹ�����ʱ" + resumeTime + "����");
                logUtil.synLog(logDTO, conn);
            } else {
                resumeTime = System.currentTimeMillis() - start;
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_TD_ACCOUNT_BALANCE);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��TD��Ŀ���(ODI)ʧ�ܡ���ʱ" + resumeTime + "���롣������Ϣ��" + srvMessage.getErrorMessage());
                logUtil.synLog(logDTO, conn);
                hasError = true;
            }
        } catch (Throwable e) {
            Logger.logError(e);
            hasError = true;
        } finally {
            if (!hasError) {
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_ACCOUNT_BALANCE, conn);
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_ACCOUNT_BALANCE, conn);
            }
        }
    }

    /**
     * ��ѯ��Ŀ������Ϣ(ODI)
     */
    public void synTransTaskInfo(Connection conn, SfUserDTO user) {
        long resumeTime = 0;
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        long start = System.currentTimeMillis();
        try {
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_TD_PA_TASK);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��Ŀ������Ϣ(ODI)��ʼ");
            logUtil.synLog(logDTO, conn);
            TDTransTaskInfoSrv transTaskInfoSrv = new TDTransTaskInfoSrv();
            String envCode = SynUpdateDateUtils.getEnvCode("TDTransTaskInfoSrv", conn);
            String lastUpdateDate = SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_TD_PA_TASK, conn);
            transTaskInfoSrv.setEnvCode(envCode);
            transTaskInfoSrv.setStratLastUpdateDate(lastUpdateDate);
            transTaskInfoSrv.excute();
            SrvReturnMessage srvMessage = transTaskInfoSrv.getReturnMessage();
            if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_TD_PA_TASK, conn);
                resumeTime = System.currentTimeMillis() - start;
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_TD_PA_TASK);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��TD��Ŀ������Ϣ(ODI)�ɹ�����ʱ" + resumeTime + "����");
                logUtil.synLog(logDTO, conn);
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_TD_PA_TASK, conn);
            } else {
                resumeTime = System.currentTimeMillis() - start;
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_TD_PA_TASK);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��TD��Ŀ������Ϣ(ODI)ʧ�ܡ���ʱ" + resumeTime + "���롣������Ϣ��" + srvMessage.getErrorMessage());
                logUtil.synLog(logDTO, conn);
            }
        } catch (QueryException e) {
            Logger.logError(e);
        } catch (ContainerException e) {
            Logger.logError(e);
        } catch (Exception e) {
            Logger.logError(e);
        }
    }

    /**
     * ͬ����˾�ڵ���
     */
    public void synTransInCompany(Connection conn, SfUserDTO user) {
        long resumeTime = 0;
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        long start = System.currentTimeMillis();
        try {
            logDTO = new SynLogDTO();
            logDTO.setSynType("INTRANSCOMPANY");
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��˾�ڵ�����ʼ");
            logUtil.synLog(logDTO, conn);

            SBFIFATdAssetsTransInCompanyDAO commitDAO = new SBFIFATdAssetsTransInCompanyDAO(user, new SBFIFATdAssetsTransInCompanyDTO(), conn);
            commitDAO.autoSyschronizeAssets();

            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType("INTRANSCOMPANY");
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��˾�ڵ�����������ʱ" + resumeTime + "����");
            logUtil.synLog(logDTO, conn);
        } catch (Exception e) {
            Logger.logError(e);
        }
    }

    /**
     * ͬ����˾�����
     */
    public void synTransBtwCompany(Connection conn, SfUserDTO user) {
        long resumeTime = 0;
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        long start = System.currentTimeMillis();
        try {
            logDTO = new SynLogDTO();
            logDTO.setSynType("BTWTRANSCOMPANY");
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��˾�������ʼ");
            logUtil.synLog(logDTO, conn);

            SBFIFATdAssetsTransBtwCompanyDAO commitDAO = new SBFIFATdAssetsTransBtwCompanyDAO(user, new SBFIFATdAssetsTransBtwCompanyDTO(), conn);
            commitDAO.autoSyschronizeAssets();

            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType("BTWTRANSCOMPANY");
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��TD��˾�������������ʱ" + resumeTime + "����");
            logUtil.synLog(logDTO, conn);
        } catch (Exception e) {
            Logger.logError(e);
        }
    }

    public void truncateData(String tableName, Connection conn) {
//                Connection conn = null;
        try {
//                    conn = DBManager.getDBConnection();
            SQLModel sqlModel = new SQLModel();
            String sqlStr = "TRUNCATE TABLE  " + tableName;
            sqlModel.setSqlStr(sqlStr);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }/*finally {
            DBManager.closeDBConnection(conn);
        }*/
    }
    public static void main(String args[]) {
    	SfUserDTO userAccount = new SfUserDTO();
    	ServletConfigDTO configDTO=new ServletConfigDTO();
        SSOUserLoginDAO ssoUserLoginDAO = new SSOUserLoginDAO(configDTO);
        userAccount = ssoUserLoginDAO.validateUser("SINOADMIN");
        TDSrvDAO sr = new TDSrvDAO();
        
        try {
			Connection conn =  DBManager.getDBConnection();
	        sr.synPeriodStatus(conn, userAccount);
		} catch (PoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//        String na = "AA";
//        sr.truncateData(na, null);


    }
    
}