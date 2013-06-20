package com.sino.sms.service.tinysoap.client;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.base.db.conn.DBManager;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.exception.PoolException;

public class AlertClient {
    /**
     * ����ƽ̨�Ŀͻ������࣬�ṩ�������ѡ����Ͷ������������������������ֱ���÷������� �����ӿ�
     * @param appId
     * @param seqCode
     * @param title
     * @param content
     * @param uri
     * @param phoneNo ����Ҫ�����ڲ�ʹ�����
     * @param uid
     * @param misId
     */
    public static String sendAlert(String uri, String appId, String seqCode, String title,
                                   String content, String wapurl, String phoneNo, String uid, String misId) {

        String soapbody = "<SendAlertRequest>" +
                "<AppId>" + appId + "</AppId>" +
                "<SeqCode>" + seqCode + "</SeqCode>" +
                "<Alert><Title>"
                + title + "</Title><Content>" + content + "</Content><PhoneNo>"
                + phoneNo + "</PhoneNo><Uid>" + uid + "</Uid><MisId>" + misId
                + "</MisId><URL>" + wapurl + "</URL></Alert></SendAlertRequest>";

        // String uri = "http://localhost:6666";
        // "http://192.168.6.176:8080/alert/services/HuilinAlertService";


        return TinySoap.send(uri, soapbody,"SendAlertRequest");
    }

    public static String sendNoAlert(String uri, String appId, String seqCode,
                                     String content, String phoneNo) {
        String soapbody = "<SendSmsRequest>" +
                "<AppId>" + appId + "</AppId>" +
                "<SeqCode>" + seqCode + "</SeqCode>" +
                "<Content>" + content + "</Content>" +
                "<PhoneNo>" + phoneNo + "</PhoneNo>" +
                "</SendSmsRequest>";

        // String uri = "http://localhost:6666";
        // "http://192.168.6.176:8080/alert/services/HuilinAlertService";


        return TinySoap.send(uri, soapbody,"SendSmsRequest");
    }

    /**
     * ����soap����ָ���Ľӿ�
     * @param args
     */
    public static void main(String[] args) {

//        String appId = "101014";
//
//        String seqCode = "12345678910";
//        String title = "����";
//        String content = "�����ӳ��ط�";
//        String url = "http://test.com";
////		String url =  null;
//        String uid = "sangyong";
////		String misId = "2";
//        String misId = "41000511";
////		String phoneno = "13834220402";
//        String phoneno = "13546366172";
//        String uri = "http://localhost:8080/alert/services/HuilinAlertService";

        Connection conn = null;

        String smsAppId = "101014";
        String uri = "http://10.204.4.38:8080/alert/services/HuilinAlertService";
//        uri=SMSContanst.
        String seqCode = "";
        try {
            conn = DBManager.getDBConnection();
            SeqProducer sq = new SeqProducer(conn);
            seqCode = "AMS_" + sq.getStrNextSeq("SMS_S");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (PoolException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeDBConnection(conn);
        }
        String content = "hi,";
        String phoneNo = "13546366172";
        String out = sendNoAlert(uri, smsAppId, seqCode, content, phoneNo);

//        String out = AlertClient.sendAlert(uri, appId, seqCode, title, content, url, phoneno, uid, misId);
        System.out.println("-----------------------����ֵ����--------------------\n");
        System.out.println(out);
    }
}
