package com.sino.framework.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dto.AMSBaseDTO;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.message.MessageManager;

import com.sino.framework.dto.BaseLocaleDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.dto.ServletConfigDTO;

/**
 * <p>Title: SinoCMS</p>
 * <p>Description: �����ƶ���ͬ����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public abstract class BaseService {
    protected BaseUserDTO userAccount = null;
    protected DTO dtoParameter = null;
    protected Connection conn = null;
    protected Message message = null;//���ܣ�ִ��ĳ������֮�����Ϣ��
    protected ServletConfigDTO servletConfig = null;

    public BaseService(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        this.userAccount = userAccount;
        this.dtoParameter = dtoParameter;
        this.conn = conn;
        this.message = new Message();
    }

    public void setDTOParameter(DTO dtoParameter) {
        this.dtoParameter = dtoParameter;
    }

    /**
     * ���ܣ�����Web.xml���õ������Ϣ
     *
     * @param servletConfig ServletConfigDTO
     */
    public void setServletConfig(ServletConfigDTO servletConfig) {
        this.servletConfig = servletConfig;
    }

    /**
     * ���ܣ�������Ϣ������ȡ��Ϣ����
     *
     * @param messageKey String
     */
    protected void prodMessage(String messageKey) {
        message = new Message();
        Message refMessage = null;
        if (dtoParameter instanceof BaseLocaleDTO) {
            BaseLocaleDTO localeDTO = (BaseLocaleDTO) dtoParameter;
            refMessage = MessageManager.getMessage(localeDTO.getLocale(), messageKey);
        } else {
            refMessage = MessageManager.getMessage(messageKey);
        }
        if (refMessage != null) {
            message.setMessageKey(messageKey);
            message.setMessageValue(refMessage.getMessageValue(false));
            message.setParent(refMessage.getParent());
        } else {
            message = Message.getUndefinedMessage();
        }
    }

    public Message getMessage() {
        return message;
    }

    public DTO getDTOParameter() {
        return dtoParameter;
    }


    /**
     * ���ܣ�����������ơ�
     * @param operateResult Ӧ��ִ�еĽ��
     * @param defaultCommit ���ݿ��������Ĭ���ύ��ʽ
     * @throws DataHandleException
     */
    public void endTransaction(boolean operateResult, boolean defaultCommit) throws DataHandleException {
        try {
            boolean autoCommit = conn.getAutoCommit();
            if (autoCommit) {
                throw new DataHandleException("���λỰδ�������������...");
            }
            if (operateResult) {
                conn.commit();
            } else {
                conn.rollback();
            }
            conn.setAutoCommit(defaultCommit);
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        }
    }

    /**
     * ���ܣ���������������
     * @param operateResult Ӧ��ִ�еĽ��
     * @param isNew �ύ��DTO�Ƿ�Ϊ������
     */
    public void processPrimaryKey(boolean operateResult, boolean isNew) {
        if(!operateResult && isNew){
            if(dtoParameter instanceof AMSBaseDTO){
                AMSBaseDTO dto = (AMSBaseDTO)dtoParameter;
                dto.clearPrimaryKey();
            }
        }
    }

    /**
     * ���ܣ�����������ơ�
     * @param operateResult Ӧ��ִ�еĽ��
     * @throws DataHandleException
     */
    public void endTransaction(boolean operateResult) throws DataHandleException {
        endTransaction(operateResult, true);
    }
}
