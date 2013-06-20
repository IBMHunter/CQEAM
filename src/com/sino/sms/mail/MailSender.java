package com.sino.sms.mail;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.tags.ImageTag;
import org.htmlparser.util.ParserException;


/**
 * �ʼ��������,�����ʹ�÷������ո����main����
 * @author Liudong
 */
public abstract class MailSender extends Authenticator{

    private String username = null;  //�ʼ������ʺ��û���
    private String userpasswd = null; //�ʼ������ʺ��û�����
    protected BodyPart messageBodyPart = null;
    protected Multipart multipart = new MimeMultipart("related");
    protected MimeMessage mailMessage = null;
    protected Session mailSession = null;
    protected Properties mailProperties = System.getProperties();
    protected InternetAddress mailFromAddress = null;
    protected InternetAddress mailToAddress = null;
    protected Authenticator authenticator = null;
    protected String mailSubject = "";
    protected Date mailSendDate = null;

    /**
     * ���캯��
     * @param  mailConfig
     */
    protected MailSender(MailConfig mailConfig) {
        this.username = mailConfig.getUserName();
        this.userpasswd = mailConfig.getUserPassword();
        mailProperties.put("mail.smtp.host", mailConfig.getSmtpHost());
        mailProperties.put("mail.smtp.auth", mailConfig.getSmtpAuth()); //����smtp��֤���ܹؼ���һ��
        mailSession = Session.getDefaultInstance(mailProperties);
        mailMessage = new MimeMessage(mailSession);
        messageBodyPart = new MimeBodyPart();
    }
    /**
     * ����һ�����ı��ʼ�����ʵ��
     * @param mailConfig
     * @return
     */
    public static MailSender getTextMailSender(MailConfig mailConfig) {
        return new MailSender(mailConfig) {
            public void setMailContent(String mailContent) throws MessagingException {
                messageBodyPart.setText(mailContent);
//                messageBodyPart.setContent(mailContent,"text/html;charset=GB2312");
                multipart.addBodyPart(messageBodyPart);
            }
        };
    }
    /**
     * ����һ�����ı��ʼ�����ʵ��
     * @param mailConfig
     * @return
     */
    public static MailSender getHtmlMailSender(MailConfig mailConfig) {
        return new MailSender(mailConfig) {
            private ArrayList arrayList1 = new ArrayList();
            private ArrayList arrayList2 = new ArrayList();

            public void setMailContent(String mailContent) throws MessagingException {
                String htmlContent = getContent("<img src=", mailContent);
                messageBodyPart.setContent(htmlContent, "text/html;charset=GB2312");
                multipart.addBodyPart(messageBodyPart);
                //���ô���html�ļ��е�ͼƬ����
                processHtmlImage(mailContent);
            }

            //����htmlҳ���ϵ�ͼƬ�������£�
            private void processHtmlImage(String mailContent) throws MessagingException {
                for (int i = 0; i < arrayList1.size(); i++) {
                    messageBodyPart = new MimeBodyPart();
                    DataSource source = new FileDataSource((String) arrayList1.get(i));
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    String contentId = "<" + (String) arrayList2.get(i) + ">";
                    messageBodyPart.setHeader("Content-ID", contentId);
                    messageBodyPart.setFileName((String) arrayList1.get(i));
                    multipart.addBodyPart(messageBodyPart);
                }
            }

            //����Ҫ���͵�html�ļ�����Ҫ�����html�ļ��е�ͼƬ
            private String getContent(String searchString, String mailContent) {
                try {
                    Parser parser = Parser.createParser(new String(mailContent.getBytes(), ISO8859_1));
                    Node[] images = parser.extractAllNodesThatAre(ImageTag.class);
                    for(int i=0;i<images.length;i++) {
                        ImageTag imgTag = (ImageTag) images[i];
                        if(!imgTag.getImageURL().toLowerCase().startsWith("http://"))
                            arrayList1.add(imgTag.getImageURL());
                    }
                } catch (UnsupportedEncodingException e1) {
                } catch (ParserException e) {}
                String afterReplaceStr = mailContent;
                //��html�ļ�����"cid:"+Content-ID���滻ԭ����ͼƬ����
                for (int m = 0; m < arrayList1.size(); m++) {
                    arrayList2.add(createRandomStr());
                    String addString = "cid:" + (String) arrayList2.get(m);
                    afterReplaceStr = mailContent.replaceAll(
                            (String) arrayList1.get(m), addString);
                }
                return afterReplaceStr;
            }

            //����һ������ַ�����Ϊ�˸�ͼƬ�趨Content-IDֵ
            private String createRandomStr() {
                char[] randomChar = new char[8];
                for (int i = 0; i < 8; i++) {
                    randomChar[i] = (char) (Math.random() * 26 + 'a');
                }
                String replaceStr = new String(randomChar);
                return replaceStr;
            }

            private final static String ISO8859_1 = "8859_1";
        };
    }
    /**
     * ����ʵ���ʼ������û���֤
     * @see javax.mail.Authenticator#getPasswordAuthentication
     */
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, userpasswd);
    }

    /**
     * �����ʼ�����
     * @param mailSubject
     * @throws javax.mail.MessagingException
     */
    public void setSubject(String mailSubject) throws MessagingException {
        this.mailSubject = mailSubject;
        mailMessage.setSubject(mailSubject,"GB2312");
    }

    /**
     * �������඼��Ҫʵ�ֵĳ��󷽷���Ϊ��֧�ֲ�ͬ���ʼ�����
     * @param mailContent
     * @throws javax.mail.MessagingException
     */
    protected abstract void setMailContent(String mailContent) throws MessagingException;

    /**
     * �����ʼ���������
     * @param sendDate
     * @throws javax.mail.MessagingException
     */
    public void setSendDate(Date sendDate) throws MessagingException {
        this.mailSendDate = sendDate;
        mailMessage.setSentDate(sendDate);
    }

    /**
     * �����ʼ����͸���
     * @param attachmentName
     * @throws javax.mail.MessagingException
     */
    public void setAttachments(String attachmentName) throws MessagingException {
        messageBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachmentName);
        messageBodyPart.setDataHandler(new DataHandler(source));
        int index = attachmentName.lastIndexOf(File.separator);
        String attachmentRealName = attachmentName.substring(index + 1);
        messageBodyPart.setFileName(attachmentRealName);
        multipart.addBodyPart(messageBodyPart);
    }

    /**
     * ���÷����˵�ַ
     * @param mailFrom
     * @throws javax.mail.MessagingException
     */
    public void setMailFrom(String mailFrom) throws MessagingException {
        mailFromAddress = new InternetAddress(mailFrom);
        mailMessage.setFrom(mailFromAddress);
    }

    /**
     * �����ռ��˵�ַ���ռ�������Ϊto,cc,bcc(��Сд����)
     * @param mailTo   �ʼ������ߵ�ַ
     * @param mailType ֵΪto,cc,bcc
     * @author Liudong
     */
    public void setMailTo(String[] mailTo, String mailType) throws Exception {
        for (int i = 0; i < mailTo.length; i++) {
            mailToAddress = new InternetAddress(mailTo[i]);
            if (mailType.equalsIgnoreCase("to")) {
                mailMessage.addRecipient(Message.RecipientType.TO,mailToAddress);
            } else if (mailType.equalsIgnoreCase("cc")) {
                mailMessage.addRecipient(Message.RecipientType.CC,mailToAddress);
            } else if (mailType.equalsIgnoreCase("bcc")) {
                mailMessage.addRecipient(Message.RecipientType.BCC,mailToAddress);
            } else {
                throw new Exception("Unknown mailType: " + mailType + "!");
            }
        }
    }

     public void setMailTo(String mailTo, String mailType) throws Exception {
            mailToAddress = new InternetAddress(mailTo);
            if (mailType.equalsIgnoreCase("to")) {
                mailMessage.addRecipient(Message.RecipientType.TO,mailToAddress);
            } else if (mailType.equalsIgnoreCase("cc")) {
                mailMessage.addRecipient(Message.RecipientType.CC,mailToAddress);
            } else if (mailType.equalsIgnoreCase("bcc")) {
                mailMessage.addRecipient(Message.RecipientType.BCC,mailToAddress);
            } else {
                throw new Exception("Unknown mailType: " + mailType + "!");
            }
    }
    /**
     * ��ʼ�����ʼ�
     * @throws javax.mail.MessagingException
     * @throws javax.mail.SendFailedException
     */
    public void sendMail() throws MessagingException, SendFailedException {
        if (mailToAddress == null)
            throw new MessagingException("�����������д�ռ��˵�ַ��");
        mailMessage.setContent(multipart);
        
        Transport.send(mailMessage);
    }



}