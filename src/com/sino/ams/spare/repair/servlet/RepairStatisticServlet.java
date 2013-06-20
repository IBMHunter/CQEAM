package com.sino.ams.spare.repair.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;

import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.spare.repair.dao.RepairStatisticDAO;
import com.sino.ams.spare.repair.dto.RepairStatisticDTO;
import com.sino.ams.spare.repair.model.RepairStatisticModel;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: V-jiachuanchuan
 * Date: 2007-11-29
 * Time: 14:51:36
 * To change this template use File | Settings | File Templates.
 */
public class RepairStatisticServlet extends BaseServlet {
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		String action = req.getParameter("act");
		action = StrUtil.nullToString(action);
		Connection conn = null;
//        String act = req.getParameter("act");
		try {
			SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);//����ʻ���Ϣ
			RepairStatisticDTO dtoParameter = null;//bean�࣬�洢��Ϣ
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(RepairStatisticDTO.class.getName()); //�����������DTOClass������
			dtoParameter = (RepairStatisticDTO) req2DTO.getDTO(req);   //�ӱ���ȡ���������쵥��DTO
			dtoParameter.setCalPattern(CalendarConstant.LINE_PATTERN);//����������ʽ
			conn = DBManager.getDBConnection();
			RepairStatisticDAO situsDAO = new RepairStatisticDAO(user, dtoParameter, conn);
			OptionProducer optProducer = new OptionProducer(user, conn);
			//��֯
			String ouoption = optProducer.getAllOrganization(dtoParameter.getOrgId(),true);
			req.setAttribute(WebAttrConstant.OU_OPTION, ouoption);
			//���
			String yearOption = optProducer.getYearOption(dtoParameter.getYear());
			req.setAttribute(WebAttrConstant.LAST_FIVE_YEAR_OPTION, yearOption);
			//�·�
			String monthOption = optProducer.getMonthOption(dtoParameter.getMonth());
			req.setAttribute(WebAttrConstant.FULL_MONTH_OPTION, monthOption);

//            String repairQuery = dtoParameter.getRepairQuery();

			String repairQuery = req.getParameter("repairQuery");

			if (action.equals("")) {
				if (repairQuery.equals(DictConstant.REPAIR_STATISTIC_ITEM_NAME)) {
					forwardURL = URLDefineList.REPAIR_STATISTIC;
				} else if (repairQuery.equals(DictConstant.REPAIR_STATISTIC_VENDOR)) {
					forwardURL = URLDefineList.REPAIR_STATISTIC_VENDOR;
				}
			} else if (action.equals(WebActionConstant.QUERY_ACTION)) {

				BaseSQLProducer sqlProducer = new RepairStatisticModel(user, dtoParameter);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
//                pageDAO.setCalPattern(CalendarConstant.LINE_PATTERN);
				pageDAO.produceWebData();
				if (repairQuery.equals(DictConstant.REPAIR_STATISTIC_ITEM_NAME)) {
					forwardURL = URLDefineList.REPAIR_STATISTIC;
				} else if (repairQuery.equals(DictConstant.REPAIR_STATISTIC_VENDOR)) {
					forwardURL = URLDefineList.REPAIR_STATISTIC_VENDOR;
				}
			} else if (action.equals(WebActionConstant.EXPORT_ACTION)) {      //����
				if (repairQuery.equals(DictConstant.REPAIR_STATISTIC_ITEM_NAME)) {
					File file = situsDAO.exportFile();
//                situsDAO.setCalPattern(CalendarConstant.LINE_PATTERN);
					WebFileDownload fileDown = new WebFileDownload(req, res);
					fileDown.setFilePath(file.getAbsolutePath());
					fileDown.download();
					file.delete();
				} else if (repairQuery.equals(DictConstant.REPAIR_STATISTIC_VENDOR)) {
					File file = situsDAO.vendorFile();
//                situsDAO.setCalPattern(CalendarConstant.LINE_PATTERN);
					WebFileDownload fileDown = new WebFileDownload(req, res);
					fileDown.setFilePath(file.getAbsolutePath());
					fileDown.download();
					file.delete();
				}

			}
		} catch (PoolException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.CONN_ERROR);
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
		} catch (DataTransException e) {
			e.printStackTrace();
		} catch (WebFileDownException e) {
			e.printStackTrace();
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
		}
	}

}


