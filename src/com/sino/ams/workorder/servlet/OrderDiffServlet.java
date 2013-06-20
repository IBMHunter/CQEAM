package com.sino.ams.workorder.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dao.EtsWorkorderBatchDAO;
import com.sino.ams.workorder.dao.EtsWorkorderDAO;
import com.sino.ams.workorder.dto.EtsWorkorderBatchDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.dto.OrderDiffDTO;
import com.sino.ams.workorder.model.OrderDiffModel;
import com.sino.ams.workorder.util.WorkOrderUtil;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;

/**
 * User: zhoujs
 * Date: 2007-10-30
 * Time: 16:39:38
 * Function:���鵵����ҳ����ʾ(������Ϣ������ɨ�������ϴ�Ѳ�������������)
 * ���鵵���ܵ���Servlet
 */
public class OrderDiffServlet extends BaseServlet {

    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;

        try {
            conn = DBManager.getDBConnection();
            WorkOrderUtil orderUtil = new WorkOrderUtil();
            //===================================
            SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(req);
            String systemId = StrUtil.nullToString(req.getParameter("systemid")); //��������

            if (systemId.equals("")) {
                Logger.logError("������������Ϊ�գ��޴˹�����Ϣ��");
                return;
            }
//            String action = StrUtil.nullToString(req.getParameter("act"));
//            if (StrUtil.isEmpty(action)) {
//                action = "present";
//            }
//            boolean isFirstCheck = false;
            String lastAchievingOrder = "";//�ϴ�δ�鵵Ѳ�칤����

            boolean isProvinceUser = userAccount.isProvinceUser();

            boolean matchEnable = !isProvinceUser;
//            String firstPendingOrder = "";
            boolean hasDiff = false;
//            String objectNo = null;
//            int diffCount = 0;

            OrderDiffModel orderDiffModel = new OrderDiffModel();

            SQLModel sqlModel = new SQLModel();
            SimpleQuery simpleQuery = null;
            RowSet curDtlRowSet = new RowSet();
            RowSet preDtlRowSet = new RowSet();
            DTOSet diffDTOSet = new DTOSet();
            /******************************************************************
             *********   ��һ������ȡ������ϸ��Ϣ  ****************************
             ******************************************************************/

            EtsWorkorderDTO workorderDTO = new EtsWorkorderDTO();
            workorderDTO.setSystemid(systemId);
            EtsWorkorderDAO etsWorkorderDAO = new EtsWorkorderDAO(userAccount, workorderDTO, conn);
            etsWorkorderDAO.setDTOClassName(EtsWorkorderDTO.class.getName());
            workorderDTO = (EtsWorkorderDTO) etsWorkorderDAO.getDataByPrimaryKey();

            EtsWorkorderBatchDTO etsWorkorderBatchDTO = new EtsWorkorderBatchDTO();
            etsWorkorderBatchDTO.setWorkorderBatch(workorderDTO.getWorkorderBatch());
            EtsWorkorderBatchDAO etsWorkorderBatchDAO = new EtsWorkorderBatchDAO(userAccount, etsWorkorderBatchDTO, conn);
            etsWorkorderBatchDAO.setDTOClassName(EtsWorkorderBatchDTO.class.getName());
            etsWorkorderBatchDTO = etsWorkorderBatchDAO.getBatchByNo();

            /*******************************************************************
             ************ �ڶ�����ȡ������ǰɨ���� ***************************
             ******************************************************************/
            sqlModel = orderDiffModel.getScanDtlModel(workorderDTO.getWorkorderNo());
            simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                curDtlRowSet = simpleQuery.getSearchResult();
            }

            /*******************************************************************
             ************ ��������ȡ�����ص��ϴ�Ѳ���ɨ���� (ֻ��Ѳ�칤��)*****************
             ******************************************************************/
            String prevScanOrderNo = "";
            if (workorderDTO.getWorkorderType().equals(DictConstant.ORDER_TYPE_CHECK)) {
                /**
                 * step1:
                 * ��ѯ�ù���ǰ�Ƿ����Ѿ��·���δ�鵵�Ĺ���
                 * �������ô�ù����Ͳ�������ǰһ�������鵵ǰ�鵵��
                 */
//               hasNotAchievedOrder= !orderUtil.getPrevPendingOrderOfBase(conn, workorderDTO).equals("");
                lastAchievingOrder = orderUtil.getPrevPendingOrderOfBase(conn, workorderDTO);
                /**
                 * step2:
                 * ��ѯ�û�վ��רҵ�Ƿ��Ѿ��й鵵�Ĺ�����
                 * prevScanOrderId���Ǹù����š�
                 */
                prevScanOrderNo = orderUtil.getPrevScanOrderId(conn, workorderDTO);
                /**
                 *  step4:
                 *  ����û�վ��רҵ���ǵ�һ��Ѳ��,��ʾ�ϴ�ɨ����
                 *  ������ʾ�ϴ�ɨ����
                 */
//                firstPendingOrder = StrUtil.isEmpty(firstPendingOrder) && StrUtil.isEmpty(prevScanOrderNo) ? "" : firstPendingOrder;
//                isFirstCheck = orderUtil.isFirstCheck(conn, workorderDTO.getWorkorderObjectNo());
                if (StrUtil.isNotEmpty(prevScanOrderNo)) {
                    sqlModel = orderDiffModel.getPreScanDtlModel(prevScanOrderNo);
                    simpleQuery = new SimpleQuery(sqlModel, conn);
                    simpleQuery.executeQuery();
                    if (simpleQuery.hasResult()) {
                        preDtlRowSet = simpleQuery.getSearchResult();
                    }
                }

            }

            /**************************************************************************
             ****** ���Ĳ���ȡ����ɨ�������������ֻ��Ѳ�칤�������ӹ�������ʾ���***********
             *************************************************************************/
            if (workorderDTO.getWorkorderType().equals(DictConstant.ORDER_TYPE_CHECK) || workorderDTO.getWorkorderType().equals(DictConstant.ORDER_TYPE_HDV)) {

//                sqlModel=orderDiffModel.getInsertDiffModel(workorderDTO.getWorkorderNo(),workorderDTO.getWorkorderObjectNo());
//                boolean isSuccess= DBOperator.updateRecord(sqlModel,conn);
//                if (StrUtil.isEmpty(prevScanOrderNo)) {
//                     orderDiffModel.getScanDtlModel(workorderDTO.getWorkorderNo());
//                } else {

                if (workorderDTO.getWorkorderType().equals(DictConstant.ORDER_TYPE_CHECK)) {
                    sqlModel = orderDiffModel.getOrderDiffModel(workorderDTO.getWorkorderObjectNo(), workorderDTO.getWorkorderNo(), workorderDTO.getAttribute7());
                }
                if (workorderDTO.getWorkorderType().equals(DictConstant.ORDER_TYPE_HDV)) {
                    sqlModel=orderDiffModel.getHDVOrderModel(workorderDTO.getWorkorderNo());
                }

//                }
                {
                    simpleQuery = new SimpleQuery(sqlModel, conn);
                }
                simpleQuery.setDTOClassName(OrderDiffDTO.class.getName());
                simpleQuery.executeQuery();
                diffDTOSet = simpleQuery.getDTOSet();
            }

            OptionProducer oPrd = new OptionProducer(userAccount, conn);
            String boundenHtml = oPrd.getUsersOfGroup(workorderDTO.getGroupId(), "", false);

            /****************************************************************
             * ============OVER==============================================
             *****************************************************************/
            req.setAttribute(WebAttrConstant.BOUNDEN, boundenHtml);
            req.setAttribute("diffCount", String.valueOf(hasDiff)); //
            req.setAttribute("matchEnable", String.valueOf(matchEnable));
            req.setAttribute("showAllDiv", workorderDTO.getWorkorderType().equals(DictConstant.ORDER_TYPE_CHECK) ? ("Y") : ("N"));
            req.setAttribute(WebAttrConstant.CUR_SCAN_DTL, curDtlRowSet);
            req.setAttribute(WebAttrConstant.PRE_SCAN_DTL, preDtlRowSet);
            req.setAttribute(WebAttrConstant.ETS_WORKORDER_DTO, workorderDTO);
            req.setAttribute(WebAttrConstant.ETS_WORKORDER_BATCH_DTO, etsWorkorderBatchDTO);
            req.setAttribute(WebAttrConstant.DIFF_DTOSET, diffDTOSet);
            req.setAttribute("firstPendingOrder", lastAchievingOrder);
            forwardURL = URLDefineList.ORDER_DIFF_PAGE;

        } catch (PoolException e) {
            e.printLog();
            message = getMessage(MsgKeyConstant.CONN_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (QueryException e) {
            e.printLog();
            message = getMessage(MsgKeyConstant.QUERY_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (ContainerException e) {
            e.printLog();
            message = getMessage(MsgKeyConstant.CONTAINER_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            ServletForwarder forwarder = new ServletForwarder(req, res);
            forwarder.forwardView(forwardURL);
        }
    }

}