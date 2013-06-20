package com.sino.sms.mail;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 * User: zhoujs
 * Date: 2010-4-26 9:01:44
 * Function:
 */
public class AmsMailSender {
    public static boolean sendMail(MailConfig mailConfig, String reciever, String title, String content) {

        boolean isSuccess=true;

//        mailConfig.setSmtpHost("smtp.sinoprof.com");
//        mailConfig.setUserName("zhoujinsong");
//        mailConfig.setUserPassword("password");
//        mailConfig.setSender("zhoujinsong@sinoprof.com");

        MailSender sendmail = MailSender.getTextMailSender(mailConfig);
        System.out.println(mailConfig.getSmtpHost());
        System.out.println(mailConfig.getUserName());
        System.out.println(mailConfig.getUserPassword());
        System.out.println(mailConfig.getSender());

        try {
            Properties mailProps = new Properties();

            mailProps.put("mail.smtp.host", mailConfig.getSmtpHost()); //"mail.smtp.host"����ɶ���У�"serverName"��������ʵ���õġ�
            mailProps.put("mail.smtp.auth", "true");
            Session mailSession = Session.getDefaultInstance(mailProps);
            // ���� �ʼ���message��message����������ʼ��ڶ��еĲ��������Ƿ�װ����set����ȥ���õ�

            MimeMessage message = new MimeMessage(mailSession);
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(mailConfig.getSmtpHost(), mailConfig.getUserName(), mailConfig.getUserPassword());

            // ��ʼ�����ʼ�
            System.out.println("���ڷ����ʼ������Ժ�.......");
            // ���÷�����
            message.setFrom(new InternetAddress(mailConfig.getSender()));
            //������
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(reciever));

            sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//                            message.setSubject("=?GB2312?B?" + enc.encode(content.getBytes()) + "?=");
            // �ʼ�����
            String m = MimeUtility.encodeText(title, "gb2312", "B");
            message.setSubject(m);
            message.setText(content, "gb2312");
            transport.sendMessage(message, message.getAllRecipients());
            System.out.println("��ϲ�㣬�ʼ��Ѿ��ɹ�����!");
        } catch (Exception ex) {
            ex.printStackTrace();
            isSuccess=false;
        }

        return isSuccess;

    }
}
