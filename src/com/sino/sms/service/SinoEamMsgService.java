package com.sino.sms.service;

import java.sql.Connection;

import javax.mail.NoSuchProviderException;

import com.sino.base.config.SMSConfig;
import com.sino.base.data.Row;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.framework.security.dto.ServletConfigDTO;
import com.sino.sms.constant.SMSConstant;

/**
 * <p>Title: SinoIES</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author
 * @version 1.0
 */
public class SinoEamMsgService extends Thread {

    private MessageProcessService msgProcessService = null;
    private SMSConfig smsConfig = null;
    private String systemName = "";
    String proCode = "";
    String code = "";
    private ServletConfigDTO servletCon = null;

    public SinoEamMsgService() {
        super();
        msgProcessService = new MessageProcessService();
    }

    public void setSMSConfig(SMSConfig smsConfig) {
        msgProcessService.setSMSConfig(smsConfig);
        setSmsConfig(smsConfig);
        this.systemName = smsConfig.getSystemName();
    }

    public void setServletConfig(ServletConfigDTO servletCon) {
        this.proCode = servletCon.getProvinceCode();
        code = servletCon.getProvinceCode();
    }


    public void run() {
         Connection conn = null;
        System.out.println("start sms service...");
//        if(proCode.equals("42")){
            while (true) {
            try {
                //�����ռ�����
                SMSMsgCreate sc=new SMSMsgCreate();
                sc.AutoCreateMsg();  //�ռ�
              EamMsgSend es =new EamMsgSend();
                es.sendMsg();       //����
                conn = DBManager.getDBConnection();
                sleep(86400000);

            } catch (PoolException ex) {
                ex.printLog();
            } catch (InterruptedException ex) {
                Logger.logError(ex);
            }/* catch (NoSuchProviderException e) {
                Logger.logError(e);
            }*/ finally {
                DBManager.closeDBConnection(conn);
            }
        }
      /*  }else*/ /*{
           while (true) {
            try {
              //ɽ�� ���� ����+�ʼ�����
                
                conn = DBManager.getDBConnection();
                SMSSend msgSend = MsgSendFactory.getMsgSendFactory(smsConfig, conn, systemName);
                SfMsg sm = new SfMsg();
                //�����ռ������� INBOX
                if (sendMsg(conn, SMSConstant.INBOX)) {
                    if (smsConfig.getSendMessage().equalsIgnoreCase("Y")) {
                        msgSend.sendMessages(sm.getInBoxMsg(conn));
                    }
                    if (smsConfig.getSendMail().equalsIgnoreCase("Y")) {
                        msgSend.sendMails(sm.getInBoxMsg(conn));
                    }
                }
                //������鵵���� ORDER_ACHIEVE
                if (sendMsg(conn, SMSConstant.ORDER_ACHIEVE)) {
                    if (smsConfig.getSendMessage().equalsIgnoreCase("Y")) {
                        msgSend.sendMessages(sm.getArchieveMsg(conn));
                    }
                    if (smsConfig.getSendMail().equalsIgnoreCase("Y")) {
                        msgSend.sendMails(sm.getArchieveMsg(conn));
                    }
                }
                //�����̵㹤����鵵���� CHECK_ORDER_ACHIEVE
                if (sendMsg(conn, SMSConstant.CHECK_ORDER_ACHIEVE)) {
                    if (smsConfig.getSendMessage().equalsIgnoreCase("Y")) {
                        msgSend.sendMessages(sm.getCheckOrderArchieveMsg(conn));
                    }
                    if (smsConfig.getSendMail().equalsIgnoreCase("Y")) {
                        msgSend.sendMails(sm.getCheckOrderArchieveMsg(conn));
                    }
                }
                //���������ʲ� RENT_MIND
                if (sendMsg(conn, SMSConstant.RENT_MIND)) {
                    if (smsConfig.getSendMessage().equalsIgnoreCase("Y")) {
                        msgSend.sendMessages(sm.getRentMindMsg(conn));
                    }
                    if (smsConfig.getSendMail().equalsIgnoreCase("Y")) {
                        msgSend.sendMails(sm.getRentMindMsg(conn));
                    }
                }
                //�����Ѿ����������Ϣ��״̬��
                msgProcessService.finishMessages(conn);
                //���ͷ����̶���Ϣ
                msgSend.sendMessages(sm.getDefineMsg(conn));

                sleep(360000000);

            } catch (PoolException ex) {
                ex.printLog();
            } catch (InterruptedException ex) {
                Logger.logError(ex);
            } catch (QueryException e) {
                e.printLog();
            } catch (ContainerException e) {
                e.printLog();
            } catch (DTOException e) {
                e.printLog();
            } catch (DataHandleException e) {
                e.printLog();
            } finally {
                DBManager.closeDBConnection(conn);
            }
        }
        }*/


    }

    /**
     * ���ݶ���Ϣ���� �ж��Ƿ���Ҫ���Ͷ���Ϣ
     *
     * @param conn
     * @param msgCode
     * @return
     * @throws QueryException
     * @throws ContainerException
     */
    public boolean sendMsg(Connection conn, String msgCode) throws QueryException, ContainerException {
        SQLModel sqlModel = new SQLModel();
        String strSql = "SELECT sf_msg.need_send_message('" + msgCode + "') SEND ";
        sqlModel.setSqlStr(strSql);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        Row row = sq.getFirstRow();
        return String.valueOf(row.getValue("SEND")).equals("1");
    }

    public SMSConfig getSmsConfig() {
        return smsConfig;
    }

    public void setSmsConfig(SMSConfig smsConfig) {
        this.smsConfig = smsConfig;
    }
}