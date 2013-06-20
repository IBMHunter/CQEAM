package com.sino.sms.service;

import java.sql.Connection;

import javax.mail.NoSuchProviderException;

import com.commerceware.cmpp.OutOfBoundsException;
import com.sino.base.config.SMSConfig;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.PoolException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.sms.dto.SfMsgDefineDTO;
import com.sino.sms.dto.SfMsgSendInfoDTO;

/**
 * <p>Title: SinoIES</p>
 * <p>Description: �����ƶ�Ӧ���ɹ�ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class SinoMsgService extends Thread {

    private MessageProcessService msgProcessService = null;
    private MessageSendService sendService = null;

    public SinoMsgService() {
        super();
        msgProcessService = new MessageProcessService();
        sendService = new MessageSendService();
    }

    public void setSMSConfig(SMSConfig smsConfig) {
        msgProcessService.setSMSConfig(smsConfig);
    }

    public void run() {
        Connection conn = null;
        DTOSet messages = null;
        SfMsgDefineDTO message = null;
        int msgCount = 0;
        DTOSet sendInfos = null;
        SfMsgSendInfoDTO sendDTO = null;
        String cellPhone = "";


        boolean needSend = false;
        System.out.println("������Ϣ����...");
        while (true) {
            try {
                sleep(300000);

                AmsEmailSend send = new AmsEmailSend();
                send.sendEmail();

                conn = DBManager.getDBConnection();
                msgProcessService.finishMessages(conn);//�����Ѿ����������Ϣ��״̬��

                messages = msgProcessService.getNeedSendMessages(conn);
                if (messages == null || messages.isEmpty()) {
                    continue;
                }
                msgCount = messages.getSize();
                for (int i = 0; i < msgCount; i++) {
                    message = (SfMsgDefineDTO) messages.getDTO(i);
                    sendInfos = msgProcessService.getMsgSendInfos(conn, message);
                    if (sendInfos == null || sendInfos.isEmpty()) {
                        continue;
                    }
                    sendService.setMessage(message.getMsgContent());
                    for (int j = 0; j < sendInfos.getSize(); j++) {
                        sendDTO = (SfMsgSendInfoDTO) sendInfos.getDTO(j);
                        cellPhone = sendDTO.getMsgCellPhone();

                        needSend = msgProcessService.needSend(conn, sendDTO);

                        if (needSend) {//��Ҫ����
                            sendService.addPhoneNum(cellPhone);
                            if (sendService.getPhoneCount() >= sendService.getMaxPhoneNum()) {
                                sendService.sendMessage();
                                sendService.clearPhoneList();
                            }
                        }
                        if (needSend) {
                            msgProcessService.processSendLog(conn, sendDTO, cellPhone);
                        }
                    }
                    if (sendService.getPhoneCount() > 0) {
                        sendService.sendMessage();
                        sendService.clearPhoneList();
                    }
                }
            } catch (PoolException ex) {
                ex.printLog();
            } catch (InterruptedException ex) {
                Logger.logError(ex);
            } catch (QueryException ex) {
                ex.printLog();
            } catch (DataHandleException ex) {
                ex.printLog();
            } catch (OutOfBoundsException ex) {
                Logger.logError(ex);
            }catch(NoSuchProviderException e){
              Logger.logError(e);
        } finally {
                DBManager.closeDBConnection(conn);
            }
        }
    }
}
