package com.sino.ams.match.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.AMSActionConstant;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.match.dao.HideMisAssetDAO;
import com.sino.ams.match.dto.HideMisAssetDTO;
import com.sino.ams.match.model.HideMisAssetModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2007-12-5
 * Time: 10:53:41
 * To change this template use File | Settings | File Templates.
 */
public class HideMisAssetServlet extends BaseServlet {
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		String action = req.getParameter("act");
		action = StrUtil.nullToString(action);
		Connection conn = null;
		try {
			SfUserDTO user = (SfUserDTO)SessionUtil.getUserAccount(req);
			HideMisAssetDTO dtoParameter = null;
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(HideMisAssetDTO.class.getName());
			dtoParameter = (HideMisAssetDTO)req2DTO.getDTO(req);
			conn = getDBConnection(req);
            OptionProducer  opt= new OptionProducer(user,conn);
           String countyOption=   opt.getCountyOptionMIS(dtoParameter.getCountyCode());
            HideMisAssetDAO barcodeMatchDAO = new HideMisAssetDAO(user, dtoParameter, conn);
			if (action.equals("")) {
                 req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
                forwardURL = URLDefineList.HIDE_MIS_PAGE;
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new HideMisAssetModel(user, dtoParameter);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.produceWebData();
                req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
                forwardURL = URLDefineList.HIDE_MIS_PAGE;
			} else if (action.equals(AMSActionConstant.NEW_ACTION)) {

                HideMisAssetDTO etsItemMatch = (HideMisAssetDTO)req.getAttribute("��ȡ��Ϊʧ�ܶ����ֵ����ݣ������ʵ������޸�");
				if(etsItemMatch == null){
					etsItemMatch= dtoParameter;//��ʾû����ʧ�ܶ����ֵ����ݣ������Ĭ�ϵĶ������ݣ�������com.sino.ams.match.dto.EtsItemMatchDTO�Ĺ��캯��ȷ��
				}
				req.setAttribute("��ϸ�������ԣ������ʵ������޸�", etsItemMatch);
				forwardURL = URLDefineList.HIDE_MIS_PAGE;
			} else if (action.equals(WebActionConstant.DETAIL_ACTION)) {
				barcodeMatchDAO.setDTOClassName(HideMisAssetDTO.class.getName());
				HideMisAssetDTO etsItemMatch = (HideMisAssetDTO)barcodeMatchDAO.getDataByPrimaryKey();
				if(etsItemMatch == null){
					etsItemMatch = new HideMisAssetDTO();
					message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
					message.setIsError(true);
				}
				req.setAttribute("��ϸ�������ԣ������ʵ������޸�", etsItemMatch);
				forwardURL = URLDefineList.HIDE_MIS_PAGE;
			} else if (action.equals(AMSActionConstant.CREATE_ACTION)) {    //�������β���
				String[] assetIds = req.getParameterValues("assetIds");
                String remark = req.getParameter("reMark");
              String ret=  barcodeMatchDAO.doHide(assetIds,remark);

                 req.setAttribute(WebAttrConstant.COUNTY_OPTION, countyOption);
                req.setAttribute(WebAttrConstant.BARCODE_MATCH_DTO,dtoParameter);
                forwardURL =URLDefineList.HIDE_MIS_PAGE +"?ret="+ret;

            } else if (action.equals(WebActionConstant.UPDATE_ACTION)) {
				barcodeMatchDAO.updateData();
				forwardURL = URLDefineList.HIDE_MIS_PAGE;
			} else if (action.equals(WebActionConstant.DELETE_ACTION)) {   //��������ƥ�����
               String[] systemids = req.getParameterValues("systemids");
                barcodeMatchDAO.deleteData();
				forwardURL = URLDefineList.HIDE_MIS_PAGE;
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
		} catch (QueryException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.QUERY_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DataHandleException ex) {
			ex.printLog();
			//�����ʵ�����������Ϣ
			forwardURL = "���ֽ���¼������ݣ����ص�ԭҳ�棬����ʾ�����������Ϣ";
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
			//����ʵ������޸�ҳ����ת���롣
		}
}    }
