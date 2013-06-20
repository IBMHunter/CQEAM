package com.sino.rds.foundation.web.util.radio;

import com.sino.rds.foundation.exception.WebException;
import com.sino.rds.foundation.web.component.WebRadio;

public abstract class RadioProducer {
    RadioProducer() {
    }

    protected RadioProduceRule produceRule = null;

    /**
     * ���ܣ������б��������
     *
     * @param produceRule OptionProduceRule
     */
    public void setProduceRule(RadioProduceRule produceRule) {
        this.produceRule = produceRule;
    }

    /**
     * ���ܣ���ȡWeb�������б�
     *
     * @return WebOptions
     * @throws com.sino.rds.foundation.exception.WebException
     *
     */
    public abstract WebRadio getWebRadio() throws WebException;
}

