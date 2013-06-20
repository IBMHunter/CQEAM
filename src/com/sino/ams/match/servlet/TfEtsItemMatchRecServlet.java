package com.sino.ams.match.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.CheckBoxProp;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.upload.RequestParser;
import com.sino.ams.constant.AMSActionConstant;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.match.dao.TfEtsItemMatchRecDAO;
import com.sino.ams.match.dto.TfEtsItemMatchRecDTO;
import com.sino.ams.match.model.TfEtsItemMatchRecModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;
                                                                     
/**
 * <p>Title: EtsItemMatchRecServlet</p>
 * <p>Description:�����Զ����ɷ������EtsItemMatchRecServlet�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @version 1.0
 */

public class TfEtsItemMatchRecServlet extends BaseServlet {

    /**
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws ServletException
     * @throws IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//               String MATCH_MODE_SPARE = "0";        ����ȷ��
//               String MATCH_MODE_SPARE_RET = "1";    ����������ϵ
//               String MATCH_MODE_PRJMTL = "2";       ��������ȷ��
//               String MATCH_MODE_PRJMTL_RET = "3";   ������������ƥ���ϵ
//               String MATCH_MODE_OTHER = "4";        �豸����
//               String MATCH_MODE_0THER_RET = "5";    �����豸����
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        String action = req.getParameter("act");
        action = StrUtil.nullToString(action);
        Connection conn = null;
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            TfEtsItemMatchRecDTO dtoParameter = null;
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(TfEtsItemMatchRecDTO.class.getName());
            dtoParameter = (TfEtsItemMatchRecDTO) req2DTO.getDTO(req);
            conn = getDBConnection(req);
            TfEtsItemMatchRecDAO etsItemMatchRecDAO = new TfEtsItemMatchRecDAO(user, dtoParameter, conn);
            String matchType = dtoParameter.getMatchType();
            ServletConfigDTO configDTO = getServletConfig(req);
            if (action.equals("")) {
                if (matchType.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {      // �����ʲ�ƥ���ϵ����
                    forwardURL = URLDefineList.TF_UNYOKE_PAGE + "?matchType=" + matchType;
                } else {
                    forwardURL = URLDefineList.TF_FINANCE_PROP_SET_PAGE + "?matchType=" + matchType;
                }
            } else if (action.equals(WebActionConstant.QUERY_ACTION)) {
                if (matchType.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {
                    BaseSQLProducer sqlProducer = new TfEtsItemMatchRecModel(user, dtoParameter);
				    PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				    pageDAO.setServletConfig(configDTO);
				    CheckBoxProp checkProp = new CheckBoxProp("subCheck");
				    checkProp.addDbField("SYSTEM_ID");
				    checkProp.addDbField("ASSET_ID");
				    pageDAO.setWebCheckProp(checkProp);
				    pageDAO.produceWebData();
                    forwardURL = URLDefineList.TF_UNYOKE_PAGE + "?matchType=" + matchType + "&unyokeFlag=" + dtoParameter.getUnyokeFlag();
                } else {
                    BaseSQLProducer sqlProducer = new TfEtsItemMatchRecModel(user, dtoParameter);
                    PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                    pageDAO.produceWebData();
                    forwardURL = URLDefineList.TF_FINANCE_PROP_SET_PAGE + "?matchType=" + matchType;
                }
            } else if (action.equals(AMSActionConstant.MATCH_ACTION)) {
                DTOSet dtos = getDTOs(req, matchType);
                if (matchType.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) {
                    dtos = getCheckedAssets(req, matchType);
                    etsItemMatchRecDAO.delDTOs(dtos);
                    forwardURL = URLDefineList.TF_UNYOKE_PAGE + "?matchType=" + matchType;
                }
//2009.3.31�޸�su
//                else if(matchType.equals(WebAttrConstant.MATCH_MODE_RENT)) {//�����ʲ�ȷ��
//                    String [] systemIds=req.getParameterValues("systemId");
//                    etsItemMatchRecDAO.confirmRentAssets(systemIds);
//                    forwardURL = URLDefineList.FINANCE_PROP_SET_PAGE + "?matchType=" + matchType;
//                }
//                else if(matchType.equals(WebAttrConstant.MATCH_MODE_DG)) {//�����ʲ�ȷ��
//                    String [] systemIds=req.getParameterValues("systemId");
//                    etsItemMatchRecDAO.confirmDGAssets(systemIds);
//                    forwardURL = URLDefineList.FINANCE_PROP_SET_PAGE + "?matchType=" + matchType;
//                }
//                else if(matchType.equals(WebAttrConstant.MATCH_MODE_LC)) {//��ֵ�׺��ʲ�ȷ��
//                    String [] systemIds=req.getParameterValues("systemId");
//                    etsItemMatchRecDAO.confirmLCAssets(systemIds);
//                    forwardURL = URLDefineList.FINANCE_PROP_SET_PAGE + "?matchType=" + matchType;
//                }
//                else if(matchType.equals(WebAttrConstant.MATCH_MODE_CT)) {//��ͨ�ʲ�ȷ��
//                    String [] systemIds=req.getParameterValues("systemId");
//                    etsItemMatchRecDAO.confirmCTAssets(systemIds);
//                    forwardURL = URLDefineList.FINANCE_PROP_SET_PAGE + "?matchType=" + matchType;
//                }
                else {
                    etsItemMatchRecDAO.saveDTOs(dtos);
                    if(matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL) || matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL_RET)){
                    	DTOSet dtoes = this.getSystemIds(req);
                		etsItemMatchRecDAO.getDistributePrj(dtoParameter.getPrjId(), dtoes);
                    }
                    forwardURL = URLDefineList.TF_FINANCE_PROP_SET_SERVLET + "?act="+ WebActionConstant.QUERY_ACTION + "&matchType=" + matchType;
                }
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
        } catch (DataHandleException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.DTO_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DTOException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.DTO_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (QueryException ex) {
            ex.printLog();
            message = getMessage(MsgKeyConstant.QUERY_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (StrException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (UploadException ex) {
            //�����ʵ�����������Ϣ
            forwardURL = "���ֽ���¼������ݣ����ص�ԭҳ�棬����ʾ�����������Ϣ";
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
            //����ʵ������޸�ҳ����ת���롣
        }
    }

    private DTOSet getDTOs(HttpServletRequest req, String matchType) throws UploadException {
        DTOSet dtos = new DTOSet();
        try {
            RequestParser reqParser = new RequestParser();
            reqParser.transData(req);
            String[] exarr = reqParser.getParameterValues("systemId");
//            String[] arr = reqParser.getParameterValues("assetId");
            if (exarr != null) {
                int exLength = exarr.length;
                for (int i = 0; i < exLength; i++) {
                	TfEtsItemMatchRecDTO dto = new TfEtsItemMatchRecDTO();
                    dto.setSystemId(exarr[i]);
//                    dto.setAssetId(arr[i]);
                    if (matchType.equals(WebAttrConstant.MATCH_MODE_SPARE)) { //����ȷ��
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_SPARE);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_SPARE_RET)) {    //����������ϵ
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_SPARE);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL)) {    //��������ȷ��
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_PRJ);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_PRJMTL_RET)) {   //������������ƥ���ϵ
//                        dto.setAssetId(arr[i]);
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_PRJ);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_OTHER)) {     //�豸����
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_OTHER);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_0THER_RET)) {  //�����豸����
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_OTHER);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_RENT)) {  //�����ʲ�ȷ��
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_RENT);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_RENT_RET)) {  //�����ʲ�����
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_RENT);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_DG)) {  //�����ʲ�ȷ��
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_DG);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_DG_RET)) {  //�����ʲ�����
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_DG);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_LC)) {  //��ֵ�׺��ʲ�ȷ��
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_DH);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_LC_RET)) {  //��ֵ�׺��ʲ�����
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_DH);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_TD)) {  //TD�ʲ�ȷ��
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_TD);
                        dtos.addDTO(dto);
                    } else if (matchType.equals(WebAttrConstant.MATCH_MODE_TD_RET)) {  //TD�ʲ�����
                        dto.setMatchType(matchType);
                        dto.setOldFinanceProp(DictConstant.FIN_PROP_TD);
                        dto.setNewFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        dtos.addDTO(dto);
                    }
//                    else if (matchType.equals(WebAttrConstant.MATHC_MODE_CHANGED_ASSETS_RET)) { // �����ʲ�ƥ���ϵ����
//                        dto.setMatchType(matchType);
//                        String flag = req.getParameter("unyokeFlag");
//                        if (flag.equals("1")) {                   //�ʲ�ƥ�䳷��
//                            dto.setOldFinanceProp(DictConstant.FIN_PROP_ASSETS);
//                            dto.setNewFinanceProp(DictConstant.FIN_PROP_UNKNOW);
//                            dtos.addDTO(dto);
//                        } else if (flag.equals("0")) {            //ת��ƥ�䳷��
//                            dto.setOldFinanceProp(DictConstant.FIN_PROP_ASSETS);
//                            dto.setNewFinanceProp(DictConstant.FIN_PROP_PRJ);
//                            dtos.addDTO(dto);
//                        }
//                    }
                }
            }
        } catch (DTOException ex) {
            ex.printLog();
            throw new UploadException(ex);
        }
        return dtos;
    }

    private DTOSet getCheckedAssets(HttpServletRequest req, String matchType) throws ServletException {
		DTOSet dtos = new DTOSet();
		try {
			RequestParser parser = new RequestParser();
			CheckBoxProp checkProp = new CheckBoxProp("subCheck");
			checkProp.setIgnoreOtherField(true);
			parser.setCheckBoxProp(checkProp);
			parser.transData(req);
			String[] systemids = parser.getParameterValues("systemId");
			String[] assetIds = parser.getParameterValues("assetId");          
            if(systemids != null){
				int checkedCount = systemids.length;
				for(int i = 0; i < checkedCount; i++){
					TfEtsItemMatchRecDTO dto = new TfEtsItemMatchRecDTO();
                    dto.setSystemId(systemids[i]);
					dto.setAssetId(assetIds[i]);
                    dto.setMatchType(matchType);
                    //String flag = req.getParameter("unyokeFlag");
                    String flag = "1";
                    if (flag.equals("1")) {                   //�ʲ�ƥ�䳷��
                            dto.setOldFinanceProp(DictConstant.FIN_PROP_ASSETS);
                            dto.setNewFinanceProp(DictConstant.FIN_PROP_UNKNOW);
                        } else if (flag.equals("0")) {            //ת��ƥ�䳷��
                            dto.setOldFinanceProp(DictConstant.FIN_PROP_ASSETS);
                            dto.setNewFinanceProp(DictConstant.FIN_PROP_PRJ);
                        }
                    dtos.addDTO(dto);
				}
			}
		} catch (UploadException ex) {
			ex.printLog();
			throw new ServletException(ex);
		} catch (StrException ex) {
			ex.printLog();
			throw new ServletException(ex);
		} catch (DTOException ex) {
			ex.printLog();
			throw new ServletException(ex);
		}
		return dtos;
	}
    
    
    private DTOSet getSystemIds(HttpServletRequest req) throws UploadException {
        DTOSet dtos = new DTOSet();
        try {
            CheckBoxProp checkProp = new CheckBoxProp("subCheck");
            checkProp.setIgnoreOtherField(true);
            RequestParser reqParser = new RequestParser();
            reqParser.setCheckBoxProp(checkProp);
            reqParser.transData(req);
            String[] exarr = reqParser.getParameterValues("systemId");
            if (exarr != null) {
            	TfEtsItemMatchRecDTO dto;
                String inarr;
                for (int i = 0; i < exarr.length; i++) {
                    inarr = exarr[i];
                    if(inarr != null && !inarr.equals("")){
                    	dto = new TfEtsItemMatchRecDTO();
                        dto.setSystemId(inarr);
                        dtos.addDTO(dto);
                    }
                }
            }
        } catch (StrException ex) {
            ex.printLog();
            throw new UploadException(ex);
        } catch (DTOException ex) {
            ex.printLog();
            throw new UploadException(ex);
        }
        return dtos;
  }
}
