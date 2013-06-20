package com.sino.rds.foundation.web.util.radio;

import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.rds.foundation.exception.WebException;
import com.sino.rds.foundation.web.component.WebRadio;

import java.util.List;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class List2Radio extends RadioProducer {
    List2Radio() {
    }


    /**
     * ���ܣ���ȡ��ѡ��ť�б�
     *
     * @return WebOptions
     */
    public WebRadio getWebRadio() throws WebException {
        WebRadio webRadio = null;
        try {
            List listData = (List) produceRule.getDataSource();
            String valueField = produceRule.getValueField();
            String descField = produceRule.getDescField();
            if (listData != null && !StrUtil.isEmpty(valueField) && !StrUtil.isEmpty(descField)) {
                webRadio = new WebRadio(produceRule.getCheckBoxName());
                int dataCount = listData.size();
                webRadio.setCheckedValue(produceRule.getCheckedValue());
                for (int i = 0; i < dataCount; i++) {
                    Object data = listData.get(i);
                    String value = StrUtil.nullToString(ReflectionUtil.getProperty(data, valueField));
                    String label = StrUtil.nullToString(ReflectionUtil.getProperty(data, descField));
                    webRadio.addValueCaption(value, label);
                }
                webRadio.addEventHandlers(produceRule.getHandlers());
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new WebException(ex.getMessage());
        }
        return webRadio;
    }
}
