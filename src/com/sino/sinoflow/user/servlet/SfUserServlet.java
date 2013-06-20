package com.sino.sinoflow.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.FlowTaskTool;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.FilterConfigDTO;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.sinoflow.bean.optionProducer.OptionProducer;
import com.sino.sinoflow.constant.URLDefineList;
import com.sino.sinoflow.constant.WebAttrConstant;
import com.sino.sinoflow.user.dao.SfUserBaseDAO;
import com.sino.sinoflow.user.dao.SfUserChgLogDAO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;
import com.sino.sinoflow.user.dto.SfUserChgLogDTO;
import com.sino.sinoflow.user.model.SfUserBaseModel;


/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����ʤ��Ȩ����Copyright (c) 2003~2007��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Copyright: ������Ȩ����˼ŵ����Ϣ�������޹�˾��һ����Χ��ʹ��</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 *          <p/>
 *          �޸���:����
 *          �޸�����:20111.09.04
 */


public class SfUserServlet extends BaseServlet {

    /**
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        String action = req.getParameter("act");
        action = StrUtil.nullToString(action);
        Connection conn = null;
        PrintWriter pw = null;
        JSONArray retArray = new JSONArray();
        try {
            FilterConfigDTO filterDTO = getFilterConfig(req);
            //SfUserBaseDTO userAccount = (SfUserBaseDTO) SessionUtil.getUserAccount(req);// ��session�л�ȡ���ݣ�����ʵ����������޸ġ�
            SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(req);// ��session�л�ȡ���ݣ�����ʵ����������޸ġ�
            ServletConfigDTO configDTO = SessionUtil.getServletConfigDTO(req);
            if (!( userAccount.isCityAdmin() || userAccount.isSysAdmin() ) ) {
                message = getMessage("USER.NO_AUTHORITY");
                ServletConfigDTO servletConfig = getServletConfig(req);
                message.addParameterValue(servletConfig.getSysAdminRole());
                message.addParameterValue(servletConfig.getCityAdminRole());
                forwardURL = filterDTO.getNoPriviledgeURL();
            } else {
                Request2DTO req2DTO = new Request2DTO();
//                req2DTO.setDTOClassName(SfUserBaseDTO.class.getName());//����ʤע�ͣ�������Ҫ��ȡӦ��ʵ�ʵ�UserDTO����
                req2DTO.setDTOClassName(filterDTO.getUserDTO());
                SfUserBaseDTO dto = (SfUserBaseDTO) req2DTO.getDTO(req);

                dto.setOrganizationId(dto.getOrgId());

                conn = DBManager.getDBConnection();
                SfUserBaseDAO sfUserDAO = new SfUserBaseDAO(userAccount, dto, conn);
//                if (!dto.getUserId().equals("")) {
                if (dto.getUserId() > 0 && !action.equals(WebActionConstant.UPDATE_ACTION)) {
//                    sfUserDAO.setDTOClassName(SfUserBaseDTO.class.getName());//����ʤע�ͣ�������Ҫ��ȡӦ��ʵ�ʵ�UserDTO����
                    sfUserDAO.setDTOClassName(filterDTO.getUserDTO());
                    dto = (SfUserBaseDTO) sfUserDAO.getDataByPrimaryKey();
                }

                OptionProducer op = new OptionProducer(userAccount, conn);


                String opt = op.getProjectOption(dto.getProjectName(), "");
                req.setAttribute(WebAttrConstant.PROJECT_OPTION_STR, opt);

                opt = op.getPrjGroupOption(dto.getGroupName(), dto.getProjectName(), dto.getOrganizationId());
                req.setAttribute(WebAttrConstant.GROUP_OPTION_STR_SELECT, opt);

                opt = op.getPrjRoleOption(dto.getRoleName(), dto.getProjectName());
                req.setAttribute(WebAttrConstant.ROLE_OPTION_STR_SELECT, opt);

//                req.setAttribute("OU_OPTIONS", op.getAllOrganization(dto.getOrganizationId(), true));
                req.setAttribute("OU_OPTIONS", op.getAllOrganization(String.valueOf(dto.getOrganizationId()), true));

                req.setAttribute("workTime", op.getWorkScheduleOption(null));
                req.setAttribute("enabledOptionString", "<option value='Y'>��Ч</option><option value='N'>ʧЧ</option>");

                if (action.equals("")) {
                    forwardURL = URLDefineList.USER_LIST_PAGE;
                } else if (action.equals(WebActionConstant.QUERY_ACTION)) {
                    BaseSQLProducer sqlProducer = new SfUserBaseModel(userAccount, dto);
                    PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                    pageDAO.setCalPattern("YYYY-MM-DD");
                    pageDAO.produceWebData();
                    forwardURL = URLDefineList.USER_LIST_PAGE;
                } else if (action.equals(WebActionConstant.NEW_ACTION)) {
                    forwardURL = URLDefineList.USER_DETAIL_PAGE;
                } else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
                    sfUserDAO.setDTOClassName(filterDTO.getUserDTO());
                    sfUserDAO.setCalPattern("YYYY-MM-DD");
                    SfUserBaseDTO sfUser = (SfUserBaseDTO) sfUserDAO.getDataByPrimaryKey();
//                    req.setAttribute("OU_OPTIONS", op.getAllOrganization(sfUser.getOrganizationId(), true));
                    req.setAttribute("OU_OPTIONS", op.getAllOrganization(String.valueOf(dto.getOrganizationId()), true));

                    if (sfUser == null) {
                        sfUser = new SfUserBaseDTO();
                        message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
                        message.setIsError(true);
                    }

                    String str = formatStr(sfUserDAO.getUserAuthority());
                    String strRole = formatRoleStr(sfUserDAO.getUserAuthority());
                    String strDept = formatDeptStr(sfUserDAO.getUserDept());

                    req.setAttribute(WebAttrConstant.USER_ATTR, sfUser);
                    req.setAttribute("str", str);
                    req.setAttribute("strDept", strDept);
                    req.setAttribute("strRole", strRole);
//                    req.setAttribute("workTime", op.getWorkScheduleOption(sfUser.getWorkScheduleId()));
                    req.setAttribute("workTime", op.getWorkScheduleOption(String.valueOf(sfUser.getWorkScheduleId())));

                    if (sfUser.getEnabled().equals("Y")) {
                        req.setAttribute("enabledOptionString", "<option value='Y' selected >��Ч</option><option value='N'>ʧЧ</option>");
                    } else {
                        req.setAttribute("enabledOptionString", "<option value='Y'>��Ч</option><option value='N' selected>ʧЧ</option>");
                    }

                    forwardURL = URLDefineList.USER_DETAIL_PAGE;
                } else if (action.equals(WebActionConstant.CREATE_ACTION)) {
                    dto.setOrganizationId(dto.getOrgId());
                    String str = req.getParameter("str");
                    String strDept = req.getParameter("strDept");
                    String cat = StrUtil.nullToString(req.getParameter("manRadio"));
                    if (!FlowTaskTool.isExist("SF_USER", "LOGIN_NAME", dto.getLoginName(), conn)) {
//                        SeqProducer seqProducer = new SeqProducer(conn);
//                        int userId = seqProducer.getStrNextSeq("SF_USER_S");
//                        dto.setUserId(userId);
//                        sfUserDAO.createData();
//                        sfUserDAO.saveUserAuthority(str, "", cat);
//                        sfUserDAO.saveUserDeptPri(strDept, "");
//                        sfUserDAO.saveUserAssetsPrvi(userId,str,"",configDTO.getProvinceCode());
                        dto.setLoginName(dto.getLoginName().toUpperCase());
                        dto = sfUserDAO.doSave(dto, str, cat, strDept, configDTO);
                        boolean isSuccess = sfUserDAO.isSuccess();
                        if (isSuccess) {
                            SfUserChgLogDTO chgLogDTO = new SfUserChgLogDTO();
                            chgLogDTO = fillChgLog(userAccount,dto,"����","�����û�"+dto.getUsername());
                            SfUserChgLogDAO chgLogDAO = new SfUserChgLogDAO(userAccount, chgLogDTO, conn);
                            chgLogDAO.createData();
                            req.setAttribute(WebAttrConstant.USER_ATTR, dto);
                            message = getMessage(MsgKeyConstant.CREATE_DATA_SUCCESS);
                            message.addParameterValue("�û�");
                            forwardURL = URLDefineList.USER_LIST_SERVLET;
                        } else {
                            dto.setUserId(0);
                            req.setAttribute(WebAttrConstant.USER_ATTR, dto);
                            req.setAttribute("str ", str);
                            req.setAttribute("strDept ", strDept);
                            message = new Message();
                            message.setMessageValue(sfUserDAO.getMsg());
                            forwardURL = URLDefineList.USER_DETAIL_PAGE;
                        }
                    } else {
                        req.setAttribute(WebAttrConstant.USER_ATTR, dto);
                        message = new Message();
                        message.setMessageValue("�û��Ѵ���");
                        forwardURL = URLDefineList.USER_DETAIL_PAGE;
                    }

                } else if (action.equals(WebActionConstant.UPDATE_ACTION)) {
                    dto.setOrganizationId(dto.getOrgId());
                    String str = req.getParameter("str");
                    String strDept = req.getParameter("strDept");
                    String strRole = req.getParameter("strRole");
                    String cat = StrUtil.nullToString(req.getParameter("manRadio"));
                    String strRemark = req.getParameter("strRemark");
                    sfUserDAO.doUpdate(dto, str, cat, strDept, configDTO);
                    boolean isSuccess = sfUserDAO.isSuccess();
                    if (isSuccess) {
                    	if (StrUtil.isNotEmpty(strRemark)) {
                    		 SfUserChgLogDTO chgLogDTO = fillChgLog(userAccount, dto, "�޸�", "�޸��û�" + dto.getUsername()+"��Ϣ("+strRemark+")");
                             SfUserChgLogDAO chgLogDAO = new SfUserChgLogDAO(userAccount, chgLogDTO, conn);
                             chgLogDAO.createData();
                    	}                        
                        req.setAttribute(WebAttrConstant.USER_ATTR, dto);
                        message = getMessage(MsgKeyConstant.UPDATE_DATA_SUCCESS);       
                        message.addParameterValue("�û�");
                        forwardURL = URLDefineList.USER_LIST_SERVLET;
                    } else {
                        req.setAttribute(WebAttrConstant.USER_ATTR, dto);
                        req.setAttribute("str", str);
                        req.setAttribute("strDept", strDept);
                        message = new Message();
                        message.setIsError(true);
                        message.setMessageValue(sfUserDAO.getMsg());
                        forwardURL = URLDefineList.USER_DETAIL_PAGE;
                    }
//                    sfUserDAO.updateData();
//                    sfUserDAO.saveUserAuthority(str, "update", cat);
//                    sfUserDAO.saveUserDeptPri(strDept, "update");
//                    sfUserDAO.saveUserAssetsPrvi(dto.getUserId(),str,"update",configDTO.getProvinceCode());

                } else if (action.equals(WebActionConstant.DELETE_ACTION)) {
                    sfUserDAO.delUserAuthority();
                    sfUserDAO.deleteData();
                    message = sfUserDAO.getMessage();
                    message = getMessage(MsgKeyConstant.DELETE_DATA_SUCCESS);
                    message.addParameterValue("�û�");
                    forwardURL = URLDefineList.USER_LIST_SERVLET;
                } else {
                    message = getMessage(MsgKeyConstant.INVALID_REQ);
                    message.setIsError(true);
                    forwardURL = MessageConstant.MSG_PRC_SERVLET;
                }
            }
        } catch (PoolException ex) {
            Logger.logError(ex);
            message = getMessage(MsgKeyConstant.CONN_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (ContainerException ex) {
            Logger.logError(ex);
            message = getMessage(MsgKeyConstant.CONN_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DTOException ex) {
            Logger.logError(ex);
            message = getMessage(MsgKeyConstant.DTO_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (QueryException ex) {
            Logger.logError(ex);
            message = getMessage(MsgKeyConstant.QUERY_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DataHandleException ex) {
            Logger.logError(ex);
            message = getMessage(MsgKeyConstant.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (SQLException e) {
            Logger.logError(e);
        } finally {
            if (pw != null) {
                pw.print(retArray.toString());
                pw.close();
                pw = null;
            }
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            if (!forwardURL.equals("")) {
                forwarder.forwardView(forwardURL);
            }
        }
    }

    /**
     * ����ѯ�������ʽ��Ϊ�ַ���
     * @param rowSet RowSet
     * @return String
     */
    private String formatStr(RowSet rowSet) {
        if (rowSet == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < rowSet.getSize(); i++) {
                Row r = rowSet.getRow(i);
                sb.append(r.getValue("PROJECT_NAME"));
                sb.append(",");
                sb.append(r.getValue("GROUP_NAME"));
                sb.append(",");
                sb.append(r.getValue("ROLE_NAME"));
                sb.append(";");
            }
        } catch (ContainerException e) {
            e.printLog();
        }
        return sb.substring(0, sb.length() - 1).toString();
    }

    private String formatDeptStr(RowSet rowSet) {
        if (rowSet == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < rowSet.getSize(); i++) {
                Row r = rowSet.getRow(i);
                sb.append(r.getValue("DEPT_NAME"));
                sb.append(";");
            }
        } catch (ContainerException e) {
            e.printLog();
        }
        return sb.substring(0, sb.length() - 1).toString();
    }
    
    private String formatRoleStr(RowSet rowSet) {
        if (rowSet == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < rowSet.getSize(); i++) {
                Row r = rowSet.getRow(i);
                sb.append(r.getValue("ROLE_NAME"));
                sb.append(";");
            }
        } catch (ContainerException e) {
            e.printLog();
        }
        return sb.substring(0, sb.length() - 1).toString();
    }

    private SfUserChgLogDTO fillChgLog(SfUserBaseDTO userAccount, SfUserBaseDTO user, String chgType, String remark) {
        SfUserChgLogDTO chgLogDTO = new SfUserChgLogDTO();
        chgLogDTO.setUserId(user.getUserId());
        chgLogDTO.setChgType(chgType);
        chgLogDTO.setOperator(userAccount.getUserId());
        chgLogDTO.setRemark(remark);
        return chgLogDTO;
    }
}
