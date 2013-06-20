package com.sino.rds.share.util;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.HandlerException;
import com.sino.base.util.StrUtil;
import com.sino.base.web.EventHandler;
import com.sino.base.web.EventHandlers;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.rds.foundation.exception.WebException;
import com.sino.rds.foundation.web.component.WebRadio;
import com.sino.rds.foundation.web.util.radio.RadioProduceRule;
import com.sino.rds.foundation.web.util.radio.RadioProducer;
import com.sino.rds.foundation.web.util.radio.RadioProducerFactory;
import com.sino.rds.share.constant.RDSDictionaryList;

import java.sql.Connection;

public class RDSRadioCreator {
    private BaseUserDTO userAccount = null;
    private Connection conn = null;

    public RDSRadioCreator(BaseUserDTO userAccount, Connection conn) {
        this.userAccount = userAccount;
        this.conn = conn;
    }

    public static WebRadio getBooleanRadio(String radioName, String checkedValue) {
        WebRadio radio = new WebRadio(radioName);
        radio.addValueCaption(RDSDictionaryList.TRUE_VALUE, "��");
        radio.addValueCaption(RDSDictionaryList.FALSE_VALUE, "��");
        radio.setCheckedValue(checkedValue);
        return radio;
    }

    public static WebRadio getEnableRadio(String checkedValue) {
        return getBooleanRadio("enabled", checkedValue);
    }

    public static WebRadio getAddBlankRadio(String checkedValue) {
        return getBooleanRadio("addBlank", checkedValue);
    }

    public static WebRadio getNullAbleRadio(String checkedValue) {
        return getBooleanRadio("nullAble", checkedValue);
    }

    /**
     * ���ܣ������ֵ�������
     *
     * @param dictionaryCode �ֵ����
     * @param checkedValue   ѡ����ֵ
     * @param handlers       �¼��������
     * @return ��ѡ�����
     * @throws WebException
     */
    public WebRadio getDictionaryRadio(String dictionaryCode, String checkedValue, EventHandlers handlers) throws WebException {
        RadioProduceRule radioRule = new RadioProduceRule();
        radioRule.setConnection(conn);
        radioRule.setValueField("CODE");
        radioRule.setDescField("VALUE");
        SQLModel sqlModel = RDSOptionSQLProducer.getAllDictionaryModel(dictionaryCode);
        radioRule.setDataSource(sqlModel);
        radioRule.setCheckedValue(checkedValue);
        radioRule.setCheckBoxName(StrUtil.getJavaField(dictionaryCode));
        if (handlers != null) {
            radioRule.setHandlers(handlers);
        }
        RadioProducer optProducer = RadioProducerFactory.getOptionProducer(radioRule);
        return optProducer.getWebRadio();
    }

    /**
     * ���ܣ������ֵ�������
     *
     * @param dictionaryCode �ֵ����
     * @param checkedValue   ѡ����ֵ
     * @return ��ѡ�����
     * @throws WebException
     */
    public WebRadio getDictionaryRadio(String dictionaryCode, String checkedValue) throws WebException {
        return getDictionaryRadio(dictionaryCode, checkedValue, null);
    }

    /**
     * ���ܣ������ֵ�������
     *
     * @param checkedValue ѡ����ֵ
     * @return ��ѡ�����
     * @throws WebException
     */
    public WebRadio getLovTypeRadio(String checkedValue) throws WebException {
        WebRadio radio = getDictionaryRadio(RDSDictionaryList.LOV_TYPE, checkedValue);
        try {
            EventHandlers handlers = new EventHandlers();
            EventHandler handler = new EventHandler();
            handler.setEventName("onclick");
            handler.setFunName("do_LovTypeChange");
            handlers.addHandler(handler);
            radio.addEventHandlers(handlers);
        } catch (HandlerException ex) {
            ex.printLog();
            throw new WebException(ex);
        }
        return radio;
    }
}
