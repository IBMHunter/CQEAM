package com.sino.soa.mis.srv.employee.servlet;

import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.common.SrvType;
import com.sino.soa.common.SrvURLDefineList;
import com.sino.soa.mis.srv.employee.dao.SBHRHRSrvEmpAssignDAO;
import com.sino.soa.mis.srv.employee.dao.SBHRHRSrvEmpInfoDAO;
import com.sino.soa.mis.srv.employee.dto.SBHRHRSrvEmployeeInfoDTO;
import com.sino.soa.mis.srv.employee.srv.SBHRHRInquiryEmpAssignInfoSrv;
import com.sino.soa.mis.srv.employee.srv.SBHRHRInquiryEmpBaseInfoSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.SynUpdateDateUtils;
import com.sino.soa.util.dto.SynLogDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-9-7   
 * Time: 15:47:56
 * To change this template use File | Settings | File Templates.
 */
public class SBHRHRSrvEmployeeInfoServlet extends BaseServlet {

    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = SrvURLDefineList.SRV_EMPLOYEE_PAGE;
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;
        boolean autoCommit = true;
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(SBHRHRSrvEmployeeInfoDTO.class.getName());
            SBHRHRSrvEmployeeInfoDTO dtoParameter = (SBHRHRSrvEmployeeInfoDTO) req2DTO.getDTO(req);
            String action = dtoParameter.getAct();
            String assetsType = StrUtil.nullToString(req.getParameter("assetsType"));
            dtoParameter.setAssetsType(assetsType);
            conn = getDBConnection(req);
            autoCommit = conn.getAutoCommit();
            int baseErrorCount = 0;
            int baseTotalCount = 0;
            int assignErrorCount = 0;
            int assignTotalCount = 0;
            long resumeTime = 0;
            req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
            if (action.equals("")) {
            	req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                forwardURL = SrvURLDefineList.SRV_EMPLOYEE_PAGE;
            } else if (action.equals(WebActionConstant.SYSCHRONIZE_ACTION)) {
                long start = System.currentTimeMillis();
                SynLogDTO logDTO = null;
                SynLogUtil logUtil = new SynLogUtil();
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_EMPLOYEE);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��Ա����Ϣ��ʼ");
                logUtil.synLog(logDTO, conn);
                //1.��ѯԱ��������Ϣ����
                SBHRHRInquiryEmpBaseInfoSrv employeeInfoSrv = new SBHRHRInquiryEmpBaseInfoSrv();
                employeeInfoSrv.setStartLastUpdateDate(dtoParameter.getStartLastUpdateDate());
                employeeInfoSrv.setEndLastUpdateDate(dtoParameter.getEndLastUpdateDate());
//                employeeInfoSrv.setEmployeeNumber("41260175");
                employeeInfoSrv.excute();
                SrvReturnMessage srvMessage = employeeInfoSrv.getReturnMessage();
                if (srvMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                    SynUpdateDateUtils.createLastUpdateDate(SrvType.SRV_EMPLOYEE, conn);
                    DTOSet ds = employeeInfoSrv.getDs();       //Ա����Ϣ
                    for (int i = 0; i < ds.getSize(); i++) {
                        SBHRHRSrvEmployeeInfoDTO dto = (SBHRHRSrvEmployeeInfoDTO) ds.getDTO(i);
                        SBHRHRSrvEmpInfoDAO srvEmployeeInfoDAO = new SBHRHRSrvEmpInfoDAO(user, dto, conn);
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
                            logDTO.setSynType(SrvType.SRV_EMPLOYEE);
                            logDTO.setCreatedBy(user.getUserId());
                            logDTO.setSynMsg(e.toString());
                            logUtil.synLog(logDTO, conn);
                            baseErrorCount++;
                        }
                    }
                   //2.��ѯԱ������(��ҳ)����
                    SBHRHRInquiryEmpAssignInfoSrv empAssignInfoSrv = new SBHRHRInquiryEmpAssignInfoSrv();  
                    //ע���˴�ʱ����ǰ��200��
                    //System.out.println("����ʱ��:"+SynUpdateDateUtils.getDate3(dtoParameter.getStartLastUpdateDate()));
                    empAssignInfoSrv.setStartLastUpdateDate(SynUpdateDateUtils.getDate3(dtoParameter.getStartLastUpdateDate()));
                    empAssignInfoSrv.execute();
                    SrvReturnMessage empMessage = empAssignInfoSrv.getReturnMessage();
                    if (empMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                    	SBHRHRSrvEmpAssignDAO srvEmpAssignInfoDAO1 = new SBHRHRSrvEmpAssignDAO(user, dtoParameter, conn);
                        DTOSet dtos = empAssignInfoSrv.getDs();
                        for (int i = 0; i < dtos.getSize(); i++) {
                            SBHRHRSrvEmployeeInfoDTO dto = (SBHRHRSrvEmployeeInfoDTO) dtos.getDTO(i); 
							 dto.setDeptCode(srvEmpAssignInfoDAO1.getEmpDeptCodeModel(dto.getOrganization()));  //HR_DEPT_NAME
							 dto.setCompanyCode(srvEmpAssignInfoDAO1.getEmpCompanyCodeModel(dto.getOrganization()));
                            SBHRHRSrvEmpAssignDAO srvEmpAssignInfoDAO = new SBHRHRSrvEmpAssignDAO(user, dto, conn);
                            try {
                                if (srvEmpAssignInfoDAO.isEmployeeExists(dto.getEmployeeNumber())) {
                                    srvEmpAssignInfoDAO.updateData();
                                    assignTotalCount++;
                                }
                            } catch (Exception e) {
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
                }
                SynUpdateDateUtils.updateLastUpdateDate(SrvType.SRV_EMPLOYEE, conn);
                resumeTime = System.currentTimeMillis() - start;
                logDTO = new SynLogDTO();
                logDTO.setSynType(SrvType.SRV_EMPLOYEE);
                logDTO.setCreatedBy(user.getUserId());
                logDTO.setSynMsg("ͬ��Ա����Ϣ������");
                logUtil.synLog(logDTO, conn);

                message = new Message();
                message.setMessageValue("��ͬ��"+(baseTotalCount+baseErrorCount+assignTotalCount+assignErrorCount)+"����¼��" +
                                        "Ա��������Ϣͬ���ɹ�"+baseTotalCount+"��ʧ��"+baseErrorCount+"��" +
                                        "Ա��������Ϣͬ���ɹ�"+assignTotalCount+"��ʧ��"+assignErrorCount+"��" +
                                        "��ʱ"+resumeTime+"����");
                req.setAttribute(QueryConstant.QUERY_DTO, dtoParameter);
                forwardURL = SrvURLDefineList.SRV_EMPLOYEE_PAGE;
            } else {
                message = getMessage(MsgKeyConstant.INVALID_REQ);
                message.setIsError(true);
                forwardURL = MessageConstant.MSG_PRC_SERVLET;
            }
        } catch (PoolPassivateException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.POOL_PASSIVATE_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DTOException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.DTO_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DataHandleException ex) {
            ex.printLog();
            message.setIsError(true);
            message.setMessageValue(ex.toString());
        } catch (SQLException e) {
            Logger.logError(e);
            message.setIsError(true);
            message.setMessageValue(e.toString());
        } catch (CalendarException e) {
            Logger.logError(e);
            message.setIsError(true);
            message.setMessageValue(e.toString());
        } catch (DatatypeConfigurationException e) {
            Logger.logError(e);
            message.setIsError(true);
            message.setMessageValue(e.toString());
        } catch (ContainerException e) {
            Logger.logError(e);
            message.setIsError(true);
            message.setMessageValue(e.toString());
        } catch (QueryException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
        }
    }

}