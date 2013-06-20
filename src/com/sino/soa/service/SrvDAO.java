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
import com.sino.soa.mis.srv.AssetPeriodStatus.dao.SrvAssetPeriodDAO;
import com.sino.soa.mis.srv.AssetPeriodStatus.dto.SrvAssetPeriodStatusDTO;
import com.sino.soa.mis.srv.AssetPeriodStatus.srv.InquiryAssetPeriodStatusSrv;
import com.sino.soa.mis.srv.accountbalance.srv.SBFIGLTransAccountBalanceSrv;
import com.sino.soa.mis.srv.assetcategory.dao.SBFIFASrvAssetCategoryDAO;
import com.sino.soa.mis.srv.assetcategory.dto.SBFIFASrvAssetCategoryDTO;
import com.sino.soa.mis.srv.assetcategory.srv.SBFIFAInquiryAssetCategorySrv;
import com.sino.soa.mis.srv.assetsinfoupdate.srv.TransAssetHeaderInfoSrv;
import com.sino.soa.mis.srv.assettransbtwcompanysrv.dao.SBFIFAAssetsTransBtwCompanyDAO;
import com.sino.soa.mis.srv.assettransbtwcompanysrv.dto.SBFIFAAssetsTransBtwCompanyDTO;
import com.sino.soa.mis.srv.assettransincompanysrv.dao.SBFIFAAssetsTransInCompanyDAO;
import com.sino.soa.mis.srv.assettransincompanysrv.dto.SBFIFAAssetsTransInCompanyDTO;
import com.sino.soa.mis.srv.employee.dao.SBHRHRSrvEmpAssignDAO;
import com.sino.soa.mis.srv.employee.dao.SBHRHRSrvEmpInfoDAO;
import com.sino.soa.mis.srv.employee.dto.SBHRHRSrvEmployeeInfoDTO;
import com.sino.soa.mis.srv.employee.srv.SBHRHRInquiryEmpAssignInfoSrv;
import com.sino.soa.mis.srv.employee.srv.SBHRHRInquiryEmpBaseInfoSrv;
import com.sino.soa.mis.srv.inquiryassetlocation.dao.SrvAssetLocationDAO;
import com.sino.soa.mis.srv.inquiryassetlocation.dto.SrvAssetLocationDTO;
import com.sino.soa.mis.srv.inquiryassetlocation.srv.SrvAssetLocationSrv;
import com.sino.soa.mis.srv.orgstructure.dao.SBHRHRInquiryOrgStructureDAO;
import com.sino.soa.mis.srv.orgstructure.dto.SBHRHRInquiryOrgStructureDTO;
import com.sino.soa.mis.srv.orgstructure.srv.SBHRHRInquiryOrgStructureSrv;
import com.sino.soa.mis.srv.projectInfo.dao.SrvProjectInfoDAO;
import com.sino.soa.mis.srv.projectInfo.dto.SrvProjectInfoDTO;
import com.sino.soa.mis.srv.projectInfo.srv.InquiryProjectInfoSrv;
import com.sino.soa.mis.srv.transTaskInfo.srv.TransTaskInfoSrv;
import com.sino.soa.mis.srv.transassetdistribution.srv.TransAssetDistributionSrv;
import com.sino.soa.mis.srv.transfaassetinfo.srv.TransFaAssetInfoSrv;
import com.sino.soa.mis.srv.valueinfo.dao.SBSYSYInquiryVSetValueInfoDAO;
import com.sino.soa.mis.srv.valueinfo.dto.SBSYSYInquiryVSetValueInfoDTO;
import com.sino.soa.mis.srv.valueinfo.srv.SBSYSYInquiryVSetValueInfoSrv;
import com.sino.soa.mis.srv.vendor.dao.SrvVendorInfoDAO;
import com.sino.soa.mis.srv.vendor.dto.SrvVendorInfoDTO;
import com.sino.soa.mis.srv.vendor.srv.InquiryVendorInfoSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.SynUpdateDateUtils;
import com.sino.soa.util.dto.SynLogDTO;
import com.sino.sso.dao.SSOUserLoginDAO;

/**
 * User: zhoujs
 * Date: 2009-5-24 16:04:20
 * Function:�Զ�����ʱ
 */
public class SrvDAO {

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
            logDTO.setSynType(SrvType.SRV_FA_CATEGORY);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS�ʲ����ʼ");
            logUtil.synLog(logDTO, conn);

            SBFIFAInquiryAssetCategorySrv service = new SBFIFAInquiryAssetCategorySrv();
            String lastUpdateDate = logUtil.getLastUpdateDate(SrvType.SRV_FA_CATEGORY, conn);
            SBFIFASrvAssetCategoryDAO srvAssetCategoryDAO = new SBFIFASrvAssetCategoryDAO(user, new SBFIFASrvAssetCategoryDTO(), conn);
            service.setLastUpdateDate(lastUpdateDate);
            service.execute();
            SrvReturnMessage srm = service.getReturnMessage();
            if (srm.getErrorFlag().equals("Y")) {
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_CATEGORY, conn);
                DTOSet ds = service.getDs();
                if (ds.getSize() > 0) {
                    count = srvAssetCategoryDAO.SavaAssetCategory(ds);
                }
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_CATEGORY, conn);
            }
            errorCount = srvAssetCategoryDAO.getErrorCount();
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_FA_CATEGORY);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS�ʲ����������ɹ�" + count + "��ʧ��" + errorCount + "����ʱ" + resumeTime + "����");
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
        SBHRHRInquiryEmpBaseInfoSrv employeeInfoSrv = new SBHRHRInquiryEmpBaseInfoSrv();
        SynLogDTO logDTO = null;
        SynLogUtil logUtil = new SynLogUtil();
        try {
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_EMPLOYEE);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MISԱ����Ϣ��ʼ");
            logUtil.synLog(logDTO, conn);

            employeeInfoSrv.setStartLastUpdateDate(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_EMPLOYEE, conn));
            employeeInfoSrv.excute();
            SrvReturnMessage srvMessage = employeeInfoSrv.getReturnMessage();

            if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                DTOSet ds = employeeInfoSrv.getDs();
                SBHRHRSrvEmpInfoDAO srvEmployeeInfoDAO = new SBHRHRSrvEmpInfoDAO(user, null, conn);
                for (int i = 0; i < ds.getSize(); i++) {
                    SBHRHRSrvEmployeeInfoDTO dto = (SBHRHRSrvEmployeeInfoDTO) ds.getDTO(i);
                    srvEmployeeInfoDAO.setDTOParameter(dto);
                    try {
                        if (srvEmployeeInfoDAO.isServiceTypeExists(dto.getEmployeeNumber())) {
                            srvEmployeeInfoDAO.updateData();
                        } else {
                            srvEmployeeInfoDAO.createData();
                        }
                        baseTotalCount++;
                    } catch (Throwable e) {
                        Logger.logError(e);
                        logDTO = new SynLogDTO();
                        logDTO.setSynType(SrvType.SRV_EMPLOYEE);
                        logDTO.setCreatedBy(user.getUserId());
                        logDTO.setSynMsg(e.toString());
                        logUtil.synLog(logDTO, conn);
                        baseErrorCount++;
                    }
                }
                SBHRHRInquiryEmpAssignInfoSrv empAssignInfoSrv = new SBHRHRInquiryEmpAssignInfoSrv();
                empAssignInfoSrv.setStartLastUpdateDate(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_EMPLOYEE, conn));
                empAssignInfoSrv.execute();
                SrvReturnMessage empMessage = empAssignInfoSrv.getReturnMessage();
                if (empMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                    DTOSet dtos = empAssignInfoSrv.getDs();
                    SBHRHRSrvEmpAssignDAO srvEmpAssignInfoDAO = new SBHRHRSrvEmpAssignDAO(user, null, conn);
                    for (int i = 0; i < dtos.getSize(); i++) {
                        SBHRHRSrvEmployeeInfoDTO dto = (SBHRHRSrvEmployeeInfoDTO) dtos.getDTO(i);
                        srvEmpAssignInfoDAO.setDTOParameter(dto);
                        try {
                            if (srvEmpAssignInfoDAO.isEmployeeExists(dto.getEmployeeNumber())) {
                                srvEmpAssignInfoDAO.updateData();
                                assignTotalCount++;
                            }
                        } catch (Throwable e) {
                            Logger.logError(e);
                            logDTO = new SynLogDTO();
                            logDTO.setSynType(SrvType.SRV_EMPLOYEE);
                            logDTO.setCreatedBy(user.getUserId());
                            logDTO.setSynMsg(e.toString());
                            logUtil.synLog(logDTO, conn);
                            assignErrorCount++;
                        }
                    }
                }
                if (baseErrorCount + assignErrorCount == 0) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_EMPLOYEE, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_EMPLOYEE, conn);
                }
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_EMPLOYEE);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MISԱ����Ϣ��������ͬ��" + (baseTotalCount + baseErrorCount + assignTotalCount + assignErrorCount) + "����¼��" +
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
            logDTO.setSynType(SrvType.SRV_SET_VALUESET);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MISֵ��ֵ��Ϣ����ʼ");
            logUtil.synLog(logDTO, conn);

            SBSYSYInquiryVSetValueInfoDAO mFndFlexValuesDAO = new SBSYSYInquiryVSetValueInfoDAO(user, null, conn);
            List valuesList = mFndFlexValuesDAO.getAllFlexValues(source);
            if (valuesList != null && valuesList.size() > 0) {
                SBSYSYInquiryVSetValueInfoSrv setValueInfoSrv = new SBSYSYInquiryVSetValueInfoSrv();
                setValueInfoSrv.setStartLastUpdateDate(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_SET_VALUESET, conn));

                for (int i = 0; i < valuesList.size(); i++) {
                    String flexValueName = (String) valuesList.get(i);
                    setValueInfoSrv.setFlexValueName(flexValueName);
                    setValueInfoSrv.execute();
                    SrvReturnMessage srvMessage = setValueInfoSrv.getReturnMessage();
                    if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                        DTOSet ds = setValueInfoSrv.getDs();
                        for (int j = 0; j < ds.getSize(); j++) {
                            SBSYSYInquiryVSetValueInfoDTO valueSetDTO = (SBSYSYInquiryVSetValueInfoDTO) ds.getDTO(j);
                            mFndFlexValuesDAO.setDTOParameter(valueSetDTO);
                            try {
                                if (mFndFlexValuesDAO.isexistsSetValueModel(valueSetDTO.getFlexValue(), valueSetDTO.getFlexValueSetId())) {
                                    mFndFlexValuesDAO.updateData();
                                } else {
                                    mFndFlexValuesDAO.createData();
                                }
                                totalCount++;
                            } catch (Throwable e) {
                                Logger.logError(e);
                                logDTO = new SynLogDTO();
                                logDTO.setSynType(SrvType.SRV_SET_VALUESET);
                                logDTO.setCreatedBy(user.getUserId());
                                logDTO.setSynMsg(e.toString());
                                logUtil.synLog(logDTO, conn);
                                errorCount++;
                            }
                        }
                    }
                }
                if (errorCount == 0) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_SET_VALUESET, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_SET_VALUESET, conn);
                }
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_SET_VALUESET);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MISֵ��ֵ��Ϣ����������ɹ�" + totalCount + "��ʧ��" + errorCount + "����ʱ" + resumeTime + "����");
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
            logDTO.setSynType(SrvType.SRV_ORG_STRUCTURE);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS��֯�ṹ��ʼ");
            logUtil.synLog(logDTO, conn);

            SBHRHRInquiryOrgStructureSrv service = new SBHRHRInquiryOrgStructureSrv();
            service.excute();
            SrvReturnMessage srm = service.getReturnMessage();
            SBHRHRInquiryOrgStructureDAO dao = new SBHRHRInquiryOrgStructureDAO(user, new SBHRHRInquiryOrgStructureDTO(), conn);
            if (srm.getErrorFlag().equalsIgnoreCase("Y")) {
                DTOSet ds = service.getDs();
                if (ds.getSize() > 0) {
                    ds = filterDtoSet(ds);
                    count = dao.synOrgStructure(ds);
                }
            }
            falsecount = dao.getErrorCount();
            if (falsecount == 0) {//û�з�������ż�¼ͬ�����͵ĸ���ʱ��
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_ORG_STRUCTURE, conn);
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_ORG_STRUCTURE, conn);
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_ORG_STRUCTURE);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS��֯�ṹ������ͬ��" + (count + falsecount) + "����¼���ɹ�" + count + "��ʧ��" + (falsecount) + "����ʱ" + resumeTime + "����");
            logUtil.synLog(logDTO, conn);
        } catch (CalendarException e) {
            Logger.logError(e);
        } catch (DTOException e) {
            Logger.logError(e);
        } catch (DataHandleException e) {
            Logger.logError(e);
        }
    }

    private DTOSet filterDtoSet(DTOSet ds) {
        DTOSet returnds = new DTOSet();
        for (int i = 0; i < ds.getSize(); i++) {
            SBHRHRInquiryOrgStructureDTO dto = (SBHRHRInquiryOrgStructureDTO) ds.getDTO(i);
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
            logDTO.setSynType(SrvType.SRV_PA_PROJECT);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS��Ŀ��Ϣ��ʼ");
            logUtil.synLog(logDTO, conn);

            InquiryProjectInfoSrv projectInfoSrv = new InquiryProjectInfoSrv();
            projectInfoSrv.execute();
            SrvReturnMessage srvMessage = projectInfoSrv.getReturnMessage();
            if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                DTOSet ds = projectInfoSrv.getDs();
                SrvProjectInfoDAO srvProjectInfoDAO = new SrvProjectInfoDAO(user, null, conn);
                for (int i = 0; i < ds.getSize(); i++) {
                    SrvProjectInfoDTO dto = (SrvProjectInfoDTO) ds.getDTO(i);
                    srvProjectInfoDAO.setDTOParameter(dto);
                    try {
                        if (SynUpdateDateUtils.getBetweenDays(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_PA_PROJECT, conn), (dto.getLastUpdateDate().toString())) > 0) {
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
                        logDTO.setSynType(SrvType.SRV_PA_PROJECT);
                        logDTO.setCreatedBy(user.getUserId());
                        logDTO.setSynMsg(e.toString());
                        logUtil.synLog(logDTO, conn);
                        errorCount++;
                    }
                }
                if (errorCount == 0) {//������Ϊ0ʱ���ܸ���ͬ��������־��¼������������������©
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_PA_PROJECT, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_PA_PROJECT, conn);
                }
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_PA_PROJECT);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS��Ŀ��Ϣ�������ɹ�" + totalCount + "��ʧ��" + errorCount + "����ʱ" + resumeTime + "����");
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
            logDTO.setSynType(SrvType.SRV_VENDOR);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS��Ӧ����Ϣ��ʼ");
            logUtil.synLog(logDTO, conn);

            InquiryVendorInfoSrv service = new InquiryVendorInfoSrv();
            service.setLastUpdateDate(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_VENDOR, conn));
            service.excute();
            SrvReturnMessage srvMessage = service.getReturnMessage();
            SrvVendorInfoDAO srvVendorInfoDAO = new SrvVendorInfoDAO(user, new SrvVendorInfoDTO(), conn);
            if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_VENDOR, conn);
                DTOSet ds = service.getDs();
                if (ds.getSize() > 0) {
                    count = srvVendorInfoDAO.synVendorInfo(ds);
                }
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_VENDOR, conn);
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_VENDOR);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS��Ӧ����Ϣ������ͬ��" + (count + errorCount) + "����¼���ɹ�" + count + "��ʧ��" + errorCount + "����ʱ" + resumeTime + "����");
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


            InquiryAssetPeriodStatusSrv service = new InquiryAssetPeriodStatusSrv();
            SrvAssetPeriodStatusDTO dtoParameter = new SrvAssetPeriodStatusDTO();
            SrvAssetPeriodDAO srvAssetPeriodDAO = new SrvAssetPeriodDAO(user, dtoParameter, conn);
            String bookType[] = logUtil.getBookTypeCodeModel(conn, MIS_CONSTANT.SOURCE_MIS);
            SrvReturnMessage srm=new SrvReturnMessage();


            for (int i = 0; i < bookType.length; i++) {
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_FA_PERIOD);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg( bookType[i] +":ͬ��MIS�ʲ�����ڼ俪ʼ");
                logUtil.synLog(logDTO, conn);
                service.setBookTypeCode(bookType[i]);
                service.execute();
                srm = service.getReturnMessage();

                if (srm.getErrorFlag().equals("Y")) {
                    DTOSet ds = service.getDs();
                    totalCount += ds.getSize();
                    trueCount += srvAssetPeriodDAO.synAssetPeriod(ds);
                }
                resumeTime = System.currentTimeMillis() - start;
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_FA_PERIOD);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg( bookType[i] +":ͬ��MIS�ʲ�����ڼ������ͬ��" + totalCount + "����¼���ɹ�" + trueCount + "��ʧ��" + (totalCount - trueCount) + "����ʱ" + resumeTime + "����");
                if (srm.getErrorFlag().equals("N")) {
                    logDTO.setSynMsg(logDTO.getSynMsg()+".������Ϣ��"+srm.getErrorMessage());
                }
                logUtil.synLog(logDTO, conn);
            }
            if (totalCount == trueCount) {
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_PERIOD, conn);
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_PERIOD, conn);
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
            logDTO.setSynType(SrvType.SRV_FA_LOCATION);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS�ʲ��ص㿪ʼ");
            logUtil.synLog(logDTO, conn);

            SrvAssetLocationSrv service = new SrvAssetLocationSrv();
            SrvAssetLocationDTO dtoParameter = new SrvAssetLocationDTO();
            SrvAssetLocationDAO srvAssetLocationDAO = new SrvAssetLocationDAO(user, dtoParameter, conn);
            service.setLastUpDate(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_FA_LOCATION, conn));
            SrvProcessModel spm = new SrvProcessModel();
            SQLModel sqlModel = spm.getLocalCodeModel(MIS_CONSTANT.SOURCE_MIS, SinoConfig.getLoc1SetNameMis());
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            Row row = null;
            if (simp.hasResult()) {
                RowSet rs = simp.getSearchResult();
                for (int i = 0; i < rs.getSize(); i++) {
                    row = rs.getRow(i);
                    String cc = row.getStrValue("FLEX_VALUE");
                    service.setSegment1(cc);
                    service.excute();
                    SrvReturnMessage srm = service.getReturnMessage();
                    if (srm.getErrorFlag().equals("Y")) {
                        DTOSet ds = service.getDs();
                        totalCount += ds.getSize();
                        if (ds.getSize() > 0) {
                            count += srvAssetLocationDAO.synAssetLocation(ds, cc);
                        }
                    }
                }
                if (totalCount == count) { //ȫ���ɹ��Ÿ���ͬ��������־�������´λ�ȡ��������
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_LOCATION, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_LOCATION, conn);
                }
            }
            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_FA_LOCATION);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS�ʲ��ص������ͬ��" + totalCount + "����¼���ɹ�" + count + "��ʧ��" + (totalCount - count) + "����ʱ" + resumeTime + "����");
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
            truncateData("ZTE_FA_ASSET_HEADER", conn);
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_FA_HEADERINFO);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS�ʲ�ͷ��Ϣ(ODI)��ʼ");
            logUtil.synLog(logDTO, conn);

            TransAssetHeaderInfoSrv transAssetSrv = new TransAssetHeaderInfoSrv();
            String envCode = SynUpdateDateUtils.getEnvCode("TransAssetHeaderInfoSrv", conn);
//            String bookTypeCodes[] = logUtil.getBookTypeCodeModel(conn, MIS_CONSTANT.SOURCE_MIS);
            String bookTypeCodes[] = logUtil.getBookTypeCode(conn, MIS_CONSTANT.SOURCE_MIS);//����ʤ�޸ģ����ӻ�ȡ�����ʲ����˲�����
            String lastUpdateDate = SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_FA_HEADERINFO, conn);
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
                            logDTO.setSynType(SrvType.SRV_FA_HEADERINFO);
                            logDTO.setCreatedBy(user.getUserId());
                            logDTO.setSynMsg("ͬ��MIS�ʲ�ͷ��Ϣ(ODI)�ɹ�����ʱ" + resumeTime + "���룬�ʲ��˲���" + bookTypeCodes[i]);
                            logUtil.synLog(logDTO, conn);
                        } else {
                            resumeTime = System.currentTimeMillis() - start;
                            logDTO = new SynLogDTO();
                            logDTO.setSynType(SrvType.SRV_FA_HEADERINFO);
                            logDTO.setCreatedBy(user.getUserId());
                            logDTO.setSynMsg("ͬ��MIS�ʲ�ͷ��Ϣ(ODI)ʧ�ܡ���ʱ" + resumeTime + "���룬�ʲ��˲���" + bookTypeCodes[i] + "��������Ϣ��" + srvMessage.getErrorMessage());
                            logUtil.synLog(logDTO, conn);
                            hasError = true;
                        }
                    } catch (Throwable ex) {
                        hasError = true;
                    }
                }
                if (!hasError) {//��¼��־λ�����������©���ݶ�ȡ������
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_HEADERINFO, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_HEADERINFO, conn);
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
            truncateData("ZTE_FA_ASSET_DISTRIBUTION", conn);
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_FA_DISTRIBUTION);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS�ʲ���������Ϣ(ODI)��ʼ");
            logUtil.synLog(logDTO, conn);

            TransAssetDistributionSrv transAssetDistributionSrv = new TransAssetDistributionSrv();
            String envCode = SynUpdateDateUtils.getEnvCode("TransAssetDistributionSrv", conn);
//            String bookTypeCodes[] = logUtil.getBookTypeCodeModel(conn, MIS_CONSTANT.SOURCE_MIS);
            String bookTypeCodes[] = logUtil.getBookTypeCode(conn, MIS_CONSTANT.SOURCE_MIS);//����ʤ�޸ģ����ӻ�ȡ�����ʲ����˲�����
            String lastUpdateDate = SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_FA_DISTRIBUTION, conn);
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
                            logDTO.setSynType(SrvType.SRV_FA_DISTRIBUTION);
                            logDTO.setCreatedBy(user.getUserId());
                            logDTO.setSynMsg("ͬ��MIS�ʲ���������Ϣ(ODI)�ɹ�����ʱ" + resumeTime + "���룬�ʲ��˲���" + bookTypeCodes[i]);
                            logUtil.synLog(logDTO, conn);
                        } else {
                            resumeTime = System.currentTimeMillis() - start;
                            logDTO = new SynLogDTO();
                            logDTO.setSynType(SrvType.SRV_FA_DISTRIBUTION);
                            logDTO.setCreatedBy(user.getUserId());
                            logDTO.setSynMsg("ͬ��MIS�ʲ���������Ϣ(ODI)ʧ�ܡ���ʱ" + resumeTime + "���룬�ʲ��˲���" + bookTypeCodes[i] + "��������Ϣ��" + srvMessage.getErrorMessage());
                            logUtil.synLog(logDTO, conn);
                            hasError = true;
                        }
                    } catch (Throwable ex) {
                        hasError = true;
                    }
                }
                if (!hasError) {//��¼��־λ�����������©���ݶ�ȡ������
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_DISTRIBUTION, conn);
                    SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_DISTRIBUTION, conn);
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
            truncateData("ZTE_FA_ASSET_INFO", conn);
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_FA_TRANS_ASSET);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS�ʲ�������Ϣ(ODI)��ʼ");
            logUtil.synLog(logDTO, conn);

            String bookTypeCodes[] = logUtil.getBookTypeCode(conn, MIS_CONSTANT.SOURCE_MIS);//����ʤ�޸ģ����ӻ�ȡ�����ʲ����˲�����
            String envCode = SynUpdateDateUtils.getEnvCode("TransFaAssetInfoSrv", conn);
//            String periodName = SynUpdateDateUtils.getPeriodName(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_FA_TRANS_ASSET, conn));
            //����ʤע�ͣ�����ڼ���Ҫ��ȡ�رյ�������ڼ�

            TransFaAssetInfoSrv transAssetInfoSrv = new TransFaAssetInfoSrv();

            for (int i=0;i<bookTypeCodes.length;i++) {
                transAssetInfoSrv.setEnvCode(envCode);
                transAssetInfoSrv.setPeriodName(periodName);
                transAssetInfoSrv.setBookTypeCode(bookTypeCodes[i]);
                transAssetInfoSrv.excute();
                SrvReturnMessage srvMessage = transAssetInfoSrv.getReturnMessage();
                if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_FA_TRANS_ASSET);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��MIS�ʲ�������Ϣ(ODI)�ɹ�����ʱ" + resumeTime + "���롣");
                    logUtil.synLog(logDTO, conn);
                } else {
                    resumeTime = System.currentTimeMillis() - start;
                    logDTO = new SynLogDTO();
                    logDTO.setSynType(SrvType.SRV_FA_TRANS_ASSET);
                    logDTO.setCreatedBy(user.getUserId());
                    logDTO.setSynMsg("ͬ��MIS�ʲ�������Ϣ(ODI)ʧ�ܡ���ʱ" + resumeTime + "���롣������Ϣ��" + srvMessage.getErrorMessage());
                    logUtil.synLog(logDTO, conn);
                }
            }
        } catch (Throwable e) {
            Logger.logError(e);
            hasError = true;
        } finally {
            if (!hasError) {
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_FA_TRANS_ASSET, conn);
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_FA_TRANS_ASSET, conn);
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
            truncateData("ZTE_GL_ACCOUNT_BALANCE", conn);
            logDTO = new SynLogDTO();
            logDTO.setSynType(SrvType.SRV_ACCOUNT_BALANCE);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS��Ŀ���(ODI)��ʼ");
            logUtil.synLog(logDTO, conn);
            SBFIGLTransAccountBalanceSrv transAccountBalanceSrv = new SBFIGLTransAccountBalanceSrv();
            String envCode = SynUpdateDateUtils.getEnvCode("TransAccountBalanceSrv", conn);
//            String periodName = SynUpdateDateUtils.getPeriodName(SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_ACCOUNT_BALANCE, conn));
//����ʤע�ͣ�����ڼ���Ҫ��ȡ����ѹرջ���ڼ�
            transAccountBalanceSrv.setEnvCode(envCode);
            transAccountBalanceSrv.setPeriodName(periodName);
            transAccountBalanceSrv.excute();
            SrvReturnMessage srvMessage = transAccountBalanceSrv.getReturnMessage();
            if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                resumeTime = System.currentTimeMillis() - start;
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_ACCOUNT_BALANCE);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��MIS��Ŀ���(ODI)�ɹ�����ʱ" + resumeTime + "���롣");
                logUtil.synLog(logDTO, conn);
            } else {
                resumeTime = System.currentTimeMillis() - start;
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_ACCOUNT_BALANCE);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��MIS��Ŀ���(ODI)ʧ�ܡ���ʱ" + resumeTime + "���롣������Ϣ��" + srvMessage.getErrorMessage());
                logUtil.synLog(logDTO, conn);
                hasError = true;
            }
        } catch (Throwable e) {
            Logger.logError(e);
            hasError = true;
        } finally {
            if (!hasError) {
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_ACCOUNT_BALANCE, conn);
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_ACCOUNT_BALANCE, conn);
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
            logDTO.setSynType(SrvType.SRV_PA_TASK);
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS��Ŀ������Ϣ(ODI)��ʼ");
            logUtil.synLog(logDTO, conn);
            TransTaskInfoSrv transTaskInfoSrv = new TransTaskInfoSrv();
            String envCode = SynUpdateDateUtils.getEnvCode("TransTaskInfoSrv", conn);
            String lastUpdateDate = SynUpdateDateUtils.getLastUpdateDate(SrvType.SRV_PA_TASK, conn);
            transTaskInfoSrv.setEnvCode(envCode);
            transTaskInfoSrv.setStratLastUpdateDate(lastUpdateDate);
            transTaskInfoSrv.excute();
            SrvReturnMessage srvMessage = transTaskInfoSrv.getReturnMessage();
            if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_PA_TASK, conn);
                resumeTime = System.currentTimeMillis() - start;
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_PA_TASK);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��MIS��Ŀ������Ϣ(ODI)�ɹ�����ʱ" + resumeTime + "����");
                logUtil.synLog(logDTO, conn);
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_PA_TASK, conn);
            } else {
                resumeTime = System.currentTimeMillis() - start;
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_PA_TASK);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��MIS��Ŀ������Ϣ(ODI)ʧ�ܡ���ʱ" + resumeTime + "���롣������Ϣ��" + srvMessage.getErrorMessage());
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
            logDTO.setSynMsg("ͬ��MIS��˾�ڵ�����ʼ");
            logUtil.synLog(logDTO, conn);

            SBFIFAAssetsTransInCompanyDAO commitDAO = new SBFIFAAssetsTransInCompanyDAO(user, new SBFIFAAssetsTransInCompanyDTO(), conn);
            commitDAO.autoSyschronizeAssets();

            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType("INTRANSCOMPANY");
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS��˾�ڵ�����������ʱ" + resumeTime + "����");
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
            logDTO.setSynMsg("ͬ��MIS��˾�������ʼ");
            logUtil.synLog(logDTO, conn);

            SBFIFAAssetsTransBtwCompanyDAO commitDAO = new SBFIFAAssetsTransBtwCompanyDAO(user, new SBFIFAAssetsTransBtwCompanyDTO(), conn);
            commitDAO.autoSyschronizeAssets();

            resumeTime = System.currentTimeMillis() - start;
            logDTO = new SynLogDTO();
            logDTO.setSynType("BTWTRANSCOMPANY");
            logDTO.setCreatedBy(user.getUserId());
            logDTO.setSynMsg("ͬ��MIS��˾�������������ʱ" + resumeTime + "����");
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
        SrvDAO sr = new SrvDAO();
        try {
			Connection conn =  DBManager.getDBConnection();
	        sr.synFaLocation(conn, userAccount);
		} catch (PoolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//        String na = "AA";
//        sr.truncateData(na, null);
    }
}
