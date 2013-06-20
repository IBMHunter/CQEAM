package com.sino.sms.service;

import java.util.List;

import com.sino.base.config.SMSConfig;
import com.sino.base.dto.DTOSet;
import com.sino.sms.dto.SfMsgDefineDTO;

/**
 * User: zhoujs
 * Date: 2008-4-2
 * Time: 10:23:32
 * Function:
 */
public abstract class SMSSend {
    protected SMSConfig smsConfig = null;


    protected SMSSend(SMSConfig smsConfig) {
        this.smsConfig = smsConfig;
    }

    /**
     * ���Ͷ���
     * @param rcvCellPhone �����ֻ���
     * @param msgContent   ��������
     * @return
     */
    public abstract boolean sendMessage(String rcvCellPhone, String msgContent);
    
     public abstract boolean sendMail(SMSConfig smsConfig,String rcvMail, String msgContent);

    /**
     * ���Ͷ���
     * @param rcvCellPhone  �����ֻ���
     * @param msgContent    ��������
     * @param sendCellPhone �����ֻ���
     * @return
     */
    public abstract boolean sendMessage(String rcvCellPhone, String msgContent, String sendCellPhone);

    /**
     * ���Ͷ��ż���
     * @param msgDTOSet
     */
    public void sendMessages(DTOSet msgDTOSet) {
        for (int i = 0; i < msgDTOSet.getSize(); i++) {
            SfMsgDefineDTO msgDTO = (SfMsgDefineDTO) msgDTOSet.getDTO(i);
            if (msgDTO.getCellPhone() != "") {
                sendMessage(msgDTO.getCellPhone(), msgDTO.getMsgContent());
            }
        }
    }
    public void sendMails(DTOSet msgDTOSet) {
//        for (int i = 0; i < msgDTOSet.getSize(); i++) {
        for (int i = 0; i < 1; i++) {
            SfMsgDefineDTO msgDTO = (SfMsgDefineDTO) msgDTOSet.getDTO(i);
            if (msgDTO.getCellPhone() != "") {
                sendMail(smsConfig,msgDTO.getMail(), msgDTO.getMsgContent());
            }
        }
    }

    /**
     * ������Ϣ������
     * @param phoneList
     * @param message
     */
    public void sendMessageToUsers(List phoneList, String message) {
        for (int i = 0; i < phoneList.size(); i++) {
            String phone = phoneList.get(i).toString();
            sendMessage(phone, message);
        }
    }

    public SMSConfig getSmsConfig() {
        return smsConfig;
    }

    public void setSmsConfig(SMSConfig smsConfig) {
        this.smsConfig = smsConfig;
    }
}
