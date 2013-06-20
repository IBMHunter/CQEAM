package com.sino.sms.service;

/**
 * Created by IntelliJ IDEA.
 * User: yuyao
 * To change this template use File | Settings | File Templates.
 */

import java.sql.Connection;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.util.DBOperator;
import com.sino.base.log.Logger;


public class AmsEmailSend {


//    public void sendEmail() throws NoSuchProviderException {
    public void sendEmail() throws NoSuchProviderException {
        Connection conn = null;
        try {
            conn = DBManager.getDBConnection();
            AmsEmailSendModel sendModel = new AmsEmailSendModel();
            SimpleQuery sq = new SimpleQuery(sendModel.getConfig(), conn);
            sq.executeQuery();
            RowSet rows = sq.getSearchResult();
            String serverName = (String) rows.getRow(0).getValue("SERVERIP");
            String emailName = (String) rows.getRow(0).getValue("EMAIL_NAME");
            String userName = (String) rows.getRow(0).getValue("USERNAME");
            String password = (String) rows.getRow(0).getValue("PASSWORD");
            String enable = (String) rows.getRow(0).getValue("ENABLED");
            // ���� properties ����������˷����ʼ��������ĵ�ַ��

            Properties mailProps = new Properties();

            mailProps.put("mail.smtp.host", serverName); //"mail.smtp.host"����ɶ���У�"serverName"��������ʵ���õġ�
            mailProps.put("mail.smtp.auth", "true");

            // ���� session

            Session mailSession = Session.getDefaultInstance(mailProps);

            // ���� �ʼ���message��message����������ʼ��ڶ��еĲ��������Ƿ�װ����set����ȥ���õ�

            MimeMessage message = new MimeMessage(mailSession);
            if (enable.equals("Y")) {
                Transport transport = mailSession.getTransport("smtp");
                transport.connect(serverName, userName, password);
                SimpleQuery sq1 = new SimpleQuery(sendModel.getUserMail(), conn);
                sq1.executeQuery();
                RowSet rows1 = sq1.getSearchResult();
                Row row = null;
                if (rows1 != null && !rows1.isEmpty()) {
                    for (int i = 0; i < rows1.getSize(); i++) {
                        try {
                            row = rows1.getRow(i);
                            String content = (String) row.getValue("MSG");
                            String id = (String) row.getValue("EMAIL_ID");
                            String email = (String) row.getValue("EMAIL");
                            // ���÷�����
                            message.setFrom(new InternetAddress(emailName));
                            //������
                            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

                            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//                            message.setSubject("=?GB2312?B?" + enc.encode(content.getBytes()) + "?=");
                            // �ʼ�����
                            String m= MimeUtility.encodeText(content,"gb2312","B");
                            message.setSubject(m);
                           message.setText(content,"gb2312");
//                            DBOperator.updateRecord(sendModel.updateUserMail(id), conn);
                            transport.sendMessage(message, message.getAllRecipients());
                        } catch (Exception ee) {
                            Logger.logError(ee);
//                            ee.printStackTrace();
                        }

                    }
                    transport.close();
                }


            }

        }
        catch (Exception exc) {
            Logger.logError(exc);
//            exc.printStackTrace();
        } finally {
            DBManager.closeDBConnection(conn);
        }
    }

}

