package com.sino.soa.td.srv.assetretire.servlet;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.DatatypeConfigurationException;

import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.*;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.CheckBoxProp;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.ams.bean.OrgOptionProducer;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.soa.common.SrvType;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ErrorItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ImportAssetRetirmentSrvInputItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ResponseItem;
import com.sino.soa.td.srv.assetretire.dao.TDAssetRetirementDAO;
import com.sino.soa.td.srv.assetretire.dto.TDAssetRetirementDTO;
import com.sino.soa.td.srv.assetretire.model.TDAssetRetirementModel;
import com.sino.soa.td.srv.assetretire.srv.TDImportAssetRetirmentSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.XMLGregorianCalendarUtil;
import com.sino.soa.util.dto.EtsMisfaTransactionRespDTO;
import com.sino.soa.util.dto.EtsMisfaUpdateBatchDTO;
import com.sino.soa.util.dto.SynLogDTO;

/**
 * User: wangzhipeng
 * Date: 2011-09-08
 * Function:�ʲ�����(SOA)_TD
 */

public class TDImportAssetRetirmentServlet extends BaseServlet {
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String forwardURL = "/srv/assetRetire/SynAssetRetireTd.jsp";
        Message message = SessionUtil.getMessage(req);
        String action = req.getParameter("act");
        action = StrUtil.nullToString(action);
        Connection conn = null;
        try {
            conn = getDBConnection(req);
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(TDAssetRetirementDTO.class.getName());
            TDAssetRetirementDTO dto = (TDAssetRetirementDTO) req2DTO.getDTO(req);
            TDAssetRetirementDAO dao = new TDAssetRetirementDAO(user, dto, conn);
            ServletConfigDTO servletConfig = SessionUtil.getServletConfigDTO(req);
            OrgOptionProducer optionProducer = new OrgOptionProducer(user, conn);
            String orgOption = optionProducer.getSynOrgnizationOption(dto.getOrganizationId(), "Y");     //��ȡTD��֯:Y (�˴����������Ƿ�TD�ʲ�)
            req.setAttribute("OU_OPTION", orgOption);
            if (action.equals("")) {
                req.setAttribute(QueryConstant.QUERY_DTO, dto);
            } else if (action.equals(WebActionConstant.QUERY_ACTION)) { //��ѯ����
                BaseSQLProducer sqlProducer = new TDAssetRetirementModel(user, dto);
                PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
                pageDAO.setServletConfig(servletConfig);
                CheckBoxProp checkProp = new CheckBoxProp("subCheck");
                checkProp.addDbField("SYSTEMID");
                pageDAO.setWebCheckProp(checkProp);
                pageDAO.setCalPattern(LINE_PATTERN);
                pageDAO.produceWebData();
                req.setAttribute(QueryConstant.QUERY_DTO, dto);
            } else if (action.equals(WebActionConstant.EXPORT_ACTION)) { //����
                File file = dao.getExportFile();
                WebFileDownload fileDown = new WebFileDownload(req, res);
                fileDown.setFilePath(file.getAbsolutePath());
                fileDown.download();
                file.delete();
            } else if (action.equals(WebActionConstant.SYSCHRONIZE_ACTION)) { //ͬ������
                long startTime = System.currentTimeMillis();
                SynLogUtil logUtil = new SynLogUtil();
                SynLogDTO logDTO = null;
                EtsMisfaUpdateBatchDTO updateBatchDTO = null;
                List<ImportAssetRetirmentSrvInputItem> inputItemList = new ArrayList<ImportAssetRetirmentSrvInputItem>();
                ImportAssetRetirmentSrvInputItem inputItem = null;
                String[] systemId = req.getParameterValues("systemId");    //�����ʲ���Ϣ
                EtsMisfaTransactionRespDTO rsRespDTO = logUtil.getMisfaResp(user.getOrganizationId(), user.getEmployeeNumber(), conn);
                if (rsRespDTO == null) {
                    String msgValue = "������ͬ���û����ٲ�����";
                    message = new Message();
                    message.setMessageValue(msgValue);
                    message.setIsError(true);
                } else {
                    if (systemId != null) {
                        RowSet rs = dao.getRetirementAssets(systemId);
                        logDTO = new SynLogDTO();
                        logDTO.setSynType(SrvType.SRV_TD_IMP_RETIRE);
                        logDTO.setCreatedBy(user.getUserId());
                        logDTO.setSynMsg("����TD�ʲ�ͬ����ʼ��");
                        logUtil.synLog(logDTO, conn);
                        SeqProducer sp = new SeqProducer(conn);
                        String batchSeq = sp.getGUID();
                        updateBatchDTO = new EtsMisfaUpdateBatchDTO();
                        updateBatchDTO.setBatchId(batchSeq);
                        updateBatchDTO.setOrganizationId(user.getOrganizationId());
                        updateBatchDTO.setCreatedBy(user.getUserId());
//                    updateBatchDTO.setTransStatus(2); //����ʤע�ͣ�
                        updateBatchDTO.setTransStatus(1);//����ʤ��ӣ�1��ʾ����ͬ��
                        updateBatchDTO.setTransType(SrvType.SRV_TD_IMP_RETIRE);
                        updateBatchDTO.setRemark("���ι�ͬ��" + rs.getSize() + "��");
                        logUtil.createMisUpdateBatch(updateBatchDTO, conn);
//                    dao.logRetireAssets(systemId, batchSeq);//����ʤע�ͣ���־��¼�ŵ�����SOA����֮��
                        for (int i = 0; i < rs.getSize(); i++) {
                            Row row = rs.getRow(i);
                            inputItem = new ImportAssetRetirmentSrvInputItem();
                            inputItem.setPRIKEY(row.getStrValue("BARCODE"));
                            inputItem.setBOOKTYPECODE(row.getStrValue("BOOK_TYPE_CODE"));
                            inputItem.setTAGNUMBER(row.getStrValue("TAG_NUMBER"));
                            inputItem.setDATERRETIRED(XMLGregorianCalendarUtil.getXMLGregorianCalendar(row.getStrValue("DATE_RRETIRED")));
//                        inputItem.setRETIREMENTTYPECODE(row.getStrValue("REJECT_TYPE"));//NORMAL��TRANSFER ��������
                            inputItem.setRETIREMENTTYPECODE("");//NORMAL��TRANSFER ��������
                            inputItem.setCURRENTCOST(new BigDecimal(row.getStrValue("COST")).setScale(2, BigDecimal.ROUND_HALF_UP));
                            inputItem.setRETIREMENTCOST(new BigDecimal(row.getStrValue("COST")).setScale(2, BigDecimal.ROUND_HALF_UP));
                            inputItem.setCREATEDBY(rsRespDTO.getUserId());
//                        inputItem.setEMPLOYEENUMBER(rsRespDTO.getEmployeeNumber());
                            inputItemList.add(inputItem);
                        }
                        TDImportAssetRetirmentSrv srv = new TDImportAssetRetirmentSrv();
                        srv.setSrvInputItems(inputItemList);
                        srv.excute();
                        dao.logRetireAssets(systemId, batchSeq);//����ʤ��ӣ���־��¼�ŵ�����SOA����֮��
                        message = new Message();
                        String msgValue = "";
                        if (srv.getReturnMessage().getErrorFlag().equalsIgnoreCase("Y")) {
                            updateBatchDTO.setRemark("���ι�ͬ��'TD�ʲ�����'" + rs.getSize() + "��,ȫ��ͬ���ɹ�!");
                            updateBatchDTO.setErrmsg("");
//                    updateBatchDTO.setTransStatus(2); //����ʤע�ͣ�
                            updateBatchDTO.setTransStatus(1);//����ʤ��ӣ�1��ʾ����ͬ��
                            logUtil.updateMisUpdateBach(updateBatchDTO, conn);
                            List<ResponseItem> responseItemList = srv.getResponseItemList();
                            dao.updateResponseLog(batchSeq);
                            msgValue = "����TD�ʲ����ϳɹ�����ʱ" + (System.currentTimeMillis() - startTime) + "���롣";
                        } else {
                            updateBatchDTO.setTransStatus(2);
                            updateBatchDTO.setErrmsg("ͬ��ʧ�ܣ���������Ϣ�鿴���屨����Ϣ");
                            updateBatchDTO.setRemark("���ι�ͬ��'TD�ʲ�����'" + rs.getSize() + "��,ͬ��ʧ��,ʧ����Ϣ:"+srv.getReturnMessage().getErrorMessage());
                            logUtil.updateMisUpdateBach(updateBatchDTO, conn);
                            List<ErrorItem> errorItemList = srv.getErrorItemList();
                            if (errorItemList != null && errorItemList.size() > 0) {
                                dao.updateErrorLog(errorItemList, batchSeq);
                            }
                            msgValue = "����TD�ʲ�����ʧ�ܣ���ʱ" + (System.currentTimeMillis() - startTime) + "���롣";
                            //����TD�ʲ����ϳɹ�����ʱ

                            //���ͬ��ʧ�ܵļ�¼������ͬ����¼����������־��¼ȫ����¼Ϊʧ�ܣ����ٴβ�ѯʱ����ѯ������ͬ���ɹ��ļ�¼���ٴ�ͬ���ͻᱨ��
                        }
                        message.setMessageValue(msgValue);
                        logDTO = new SynLogDTO();
                        logDTO.setSynType(SrvType.SRV_TD_IMP_RETIRE);
                        logDTO.setCreatedBy(user.getUserId());
                        logDTO.setSynMsg("����TD�ʲ�ͬ��������");
                        logUtil.synLog(logDTO, conn);
                    }
                }
                req.setAttribute(QueryConstant.QUERY_DTO, dto);
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
        } catch (StrException ex) {
            ex.printLog();
            message = getMessage(AssetsMessageKeys.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (ContainerException e) {
            e.printStackTrace();
        } catch (CalendarException e) {
            e.printStackTrace();
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        } catch (DataHandleException e) {
            e.printStackTrace();
        } catch (SQLModelException e1) {
            e1.printStackTrace();
        } catch (WebFileDownException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeDBConnection(conn);
            setHandleMessage(req, message);
            if (!StrUtil.isEmpty(forwardURL)) {
                ServletForwarder forwarder = new ServletForwarder(req, res);
                forwarder.forwardView(forwardURL);
            }
        }
    }
}

