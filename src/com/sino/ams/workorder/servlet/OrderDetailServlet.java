package com.sino.ams.workorder.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.bean.OptionProducer;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.URLDefineList;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dao.EtsWorkorderBatchDAO;
import com.sino.ams.workorder.dao.EtsWorkorderDAO;
import com.sino.ams.workorder.dto.EtsWorkorderBatchDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.OrderDiffModel;
import com.sino.ams.workorder.model.OrderExtendModel;
import com.sino.ams.workorder.util.WorkOrderUtil;
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
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;

/**
 * User: V-jiachuanchuan
 * Date: 2007-11-12
 * Time: 17:25:36
 * Function:����ҳ����ʾ
 */
public class OrderDetailServlet extends BaseServlet {

    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String forwardURL = "";
        Message message = SessionUtil.getMessage(req);
        Connection conn = null;

        try {
            conn = DBManager.getDBConnection();
            //===================================
            SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(req);
//            String systemId = StrUtil.nullToString(req.getParameter("systemid")); //��������
            WorkOrderUtil orderUtil = new WorkOrderUtil();
            String systemId = ""; //��������
            String workorderNo = StrUtil.nullToString(req.getParameter("WORKORDER_NO")); //��������
            if (systemId.equals("")) {
                Logger.logError("������������Ϊ�գ��޴˹�����Ϣ��");
                //  return;
            }
//            String action = StrUtil.nullToString(req.getParameter("act"));
//            if (StrUtil.isEmpty(action)) {
//                action = "present";
//            }
//            boolean hasError = false;

            boolean isProvinceUser = userAccount.isProvinceUser();

            boolean matchEnable = !isProvinceUser;
            String firstPendingOrder = "";
            boolean hasDiff = false;
//            String objectNo = null;
//            int diffCount = 0;
            OrderDiffModel orderDiffModel = new OrderDiffModel();
            OrderExtendModel orderExtendModel = new OrderExtendModel();
            SQLModel sqlModel = new SQLModel();
            SimpleQuery simpleQuery = null;
            RowSet curDtlRowSet = new RowSet();
            RowSet preDtlRowSet = new RowSet();
            RowSet diffDtlRowSet = new RowSet();
            RowSet fixNewRowSet = new RowSet();
            DTOSet diffDTOSet = new DTOSet();

            /******************************************************************
             *********   ��һ������ȡ������ϸ��Ϣ  ****************************
             ******************************************************************/

            EtsWorkorderDTO workorder = new EtsWorkorderDTO();
            workorder.setSystemid(systemId);
            workorder.setWorkorderNo(workorderNo);
            EtsWorkorderDAO etsWorkorderDAO = new EtsWorkorderDAO(userAccount, workorder, conn);
            etsWorkorderDAO.setDTOClassName(EtsWorkorderDTO.class.getName());
            workorder = (EtsWorkorderDTO) etsWorkorderDAO.getDataByPrimaryKey();
            String aa = workorder.getWorkorderFlagDesc();

            /******************************************************************
             *********   �ڶ�������ȡ��������ϸ��Ϣ  ****************************
             ******************************************************************/

            EtsWorkorderBatchDTO workorderBatchDTO = new EtsWorkorderBatchDTO();
            workorderBatchDTO.setWorkorderBatch(workorder.getWorkorderBatch());
            EtsWorkorderBatchDAO etsWorkorderBatchDAO = new EtsWorkorderBatchDAO(userAccount, workorderBatchDTO, conn);
            etsWorkorderBatchDAO.setDTOClassName(EtsWorkorderBatchDTO.class.getName());
            DTOSet batchSet = new DTOSet();
            batchSet = (DTOSet) etsWorkorderBatchDAO.getDataByForeignKey("workorderBatch");
            workorderBatchDTO = (EtsWorkorderBatchDTO) batchSet.getDTO(0);
//            workorderBatchDTO = (EtsWorkorderBatchDTO);
//                    RowSet rossw=(RowSet)etsWorkorderBatchDAO.getDataByForeignKey("workorderBatch");

            /*******************************************************************
             ************ �ڶ�����ȡ������ǰɨ���� ***************************
             ******************************************************************/
            sqlModel = orderDiffModel.getScanDtlModel(workorder.getWorkorderNo());
            simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                curDtlRowSet = simpleQuery.getSearchResult();
            }
            req.setAttribute(WebAttrConstant.CUR_SCAN_DTL, curDtlRowSet);

            /*******************************************************************
             ************ ��������ȡ�����ص��ϴ�Ѳ���ɨ���� *****************
             ******************************************************************/

            if (workorder.getWorkorderType().equals(DictConstant.ORDER_TYPE_CHECK) || workorder.getWorkorderType().equals(DictConstant.ORDER_TYPE_HDV)) {
                /**
                 * step1:
                 * ��ѯ�ù���ǰ�Ƿ����Ѿ��·���δ�鵵�Ĺ���
                 * �������ô�ù����Ͳ�������ǰһ�������鵵ǰ�鵵��
                 */
                firstPendingOrder = orderUtil.getPrevPendingOrderOfBase(conn, workorder);
                /**
                 * step2:
                 * ��ѯ�û�վ��רҵ�Ƿ��Ѿ��й鵵�Ĺ�����
                 * prevScanOrderId���Ǹù����š�
                 */
                String prevScanOrderNo = orderUtil.getPrevScanOrderId(conn, workorder);
                /**
                 *  step4:
                 *  ����û�վ��רҵ���ǵ�һ��Ѳ��,��ʾ�ϴ�ɨ����
                 *  ������ʾ�ϴ�ɨ����
                 */

                if (!StrUtil.isEmpty(prevScanOrderNo)) {
                    sqlModel = orderDiffModel.getPreScanDtlModel(prevScanOrderNo);
                    simpleQuery = new SimpleQuery(sqlModel, conn);
                    simpleQuery.executeQuery();
                    if (simpleQuery.hasResult()) {
                        preDtlRowSet = simpleQuery.getSearchResult();
                    }
                }

            }

            /**************************************************************************
             ****** ���Ĳ���ȡ����ɨ�������������ֻ��Ѳ�칤������ʾ���***********
             *************************************************************************/
//            if (workorder.getWorkorderType().equals(DictConstant.ORDER_TYPE_CHECK)) {
//                sqlModel=orderDiffModel.getOrderDiffModel(workorder.getWorkorderObjectNo(),workorder.getWorkorderNo());
//                simpleQuery=new SimpleQuery(sqlModel,conn);
//                simpleQuery.setDTOClassName(OrderDiffDTO.class.getName());
//                simpleQuery.executeQuery();
//                diffDTOSet=simpleQuery.getDTOSet();
//            }
            /*ȡ�ƻ�����*/
            sqlModel = orderExtendModel.getSchemeQueryModel(false, workorder.getWorkorderNo());
            simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                fixNewRowSet = simpleQuery.getSearchResult();
            }
            /*ȡ������*/
            sqlModel = orderExtendModel.getDiffOfOrderModel(workorder.getWorkorderNo());
            simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                diffDtlRowSet = simpleQuery.getSearchResult();
            }

            OptionProducer oPrd = new OptionProducer(userAccount, conn);
            String boundenHtml = oPrd.getUsersOfGroup(workorder.getGroupId(), "", false);

            req.setAttribute(WebAttrConstant.BOUNDEN, boundenHtml);
            req.setAttribute("diffCount", String.valueOf(hasDiff));
            req.setAttribute("matchEnable", String.valueOf(matchEnable));
            req.setAttribute("isCheck", workorder.getWorkorderType().equals(DictConstant.ORDER_TYPE_CHECK) ? ("Y") : ("N"));
            req.setAttribute(WebAttrConstant.ETS_WORKORDER_BATCH_DTO, workorderBatchDTO);
            req.setAttribute(WebAttrConstant.ETS_WORKORDER_DTO, workorder);
            req.setAttribute(WebAttrConstant.CUR_SCAN_DTL, curDtlRowSet);
            req.setAttribute(WebAttrConstant.PRE_SCAN_DTL, preDtlRowSet);
            req.setAttribute(WebAttrConstant.CUR_OBJ_SCHEME_RST, fixNewRowSet);
            req.setAttribute(WebAttrConstant.DIFF_SCAN_DTL, diffDtlRowSet);
            req.setAttribute(WebAttrConstant.DIFF_DTOSET, diffDTOSet);
            req.setAttribute("firstPendingOrder", String.valueOf(firstPendingOrder));
            forwardURL = URLDefineList.ORDER_DETAIL;

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