package com.sino.sms.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.commerceware.cmpp.OutOfBoundsException;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.exception.DataHandleException;
import com.sino.sms.service.MessageSendService;

/**
 * Created by wwb.
 * User: demo
 * Date: 2007-6-28
 * Time: 16:17:28
 * ��Ϊ com.sino.flow.bean.MsgBusiness�����������ķ����ţ���ֻ�ǰ���Ҫ���͵Ķ����ȴ浽���
 * ��˱��������������Ͷ��š��ǽ�MessageSendService���װһ�㣬�����Զ�����������ȡ������
 * ���͹���Ķ��Ž��ᱸ�ݵ���־��
 */
public class MessageSender {
    private Connection conn;

    public MessageSender(Connection conn) {
        this.conn = conn;
    }

    /**
     * ���Ͷ��ţ�ֱ�ӷ��ͣ��������ˣ����ֵ����ݿ�
     *
     * @param cellPhone �ֻ���
     * @param content   ��������
     * @param remark    ��ע��
     * @param sendBy    ������
     */
    public void send(String cellPhone, String content, String remark, String sendBy) throws OutOfBoundsException, SQLException, DataHandleException {
        MessageSendService sendService = new MessageSendService();
        String[] msgArr = splitMsg(content);
        for (int j = 0; j < msgArr.length; j++) {
            sendService.setMessage(msgArr[j]);
            ArrayList al = new ArrayList();
            al.add(cellPhone);
            sendService.setPhoneList(al);
            sendService.sendMessage();
            sendService.clearPhoneList();
            addLog(cellPhone, msgArr[j], remark, sendBy);
        }
    }

    /**
     * @param cellPhone the phone number which receive the message
     * @param content   the message content
     * @param remark    the remark
     * @throws SQLException
     * @throws DataHandleException backup the message to database
     */
    private void addLog(String cellPhone, String content, String remark, String sendBy) throws SQLException, DataHandleException {
        String sql = "INSERT INTO SF_MSG_LOG\n" +
                "  (CELL_PHONE, CONTENT, SEND_DATE, REMARK,SEND_BY)\n" +
                "VALUES\n" +
                "  (?, ?, GETDATE(), ?, ?)";
        ArrayList al = new ArrayList();
        al.add(cellPhone);
        al.add(content);
        al.add(remark);
        al.add(sendBy);
        SQLModel sm = new SQLModel();
        sm.setSqlStr(sql);
        sm.setArgs(al);
        DBOperator.updateRecord(sm, conn);
    }

    private String[] splitMsg(String msg) {
        int length = msg.length() / 60 + 1;
        String[] contentArray = new String[length];
        for (int i = 0; i < length; i++) {
            if ((i + 1) * 60 > msg.length()) {
                contentArray[i] = msg.substring(i * 60, msg.length());
            } else {
                contentArray[i] = msg.substring(i * 60, (i + 1) * 60);
            }
            if (i == length - 1) {
                contentArray[i] += "[��]";
            } else {
                contentArray[i] += "[" + (i + 1) + "]";
            }

        }
        return contentArray;
    }
}
