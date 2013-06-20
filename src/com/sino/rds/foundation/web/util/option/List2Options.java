package com.sino.rds.foundation.web.util.option;

import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.rds.foundation.exception.WebException;
import com.sino.rds.foundation.web.component.WebOption;
import com.sino.rds.foundation.web.component.WebOptions;

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

public class List2Options extends OptionProducer {
    List2Options() {
    }

    /**
     * ���ܣ���ȡ�����б�
     *
     * @return WebOptions
     */
    public WebOptions getOptions() throws WebException {
        WebOptions options = new WebOptions();
        try {
            List listData = (List) produceRule.getDataSource();
            String valueField = produceRule.getValueField();
            String descField = produceRule.getDescField();
            if (listData != null && !StrUtil.isEmpty(valueField) && !StrUtil.isEmpty(descField)) {
                int dataCount = listData.size();
                if (produceRule.isAddBlank()) {
                    options.addBlankOption();
                }
                String EMPTY_STR = "&nbsp;&nbsp;&nbsp;&nbsp;"; //�����ո�
                Object firstValue = null;
                Object secondValue = null;
                String selectedValue = produceRule.getSelectedValue();
                boolean rightStep = produceRule.isRightStep();
                String containStr = produceRule.getRightStepStr();
                produceAttributeNames();
                for (int i = 0; i < dataCount; i++) {
                    Object data = listData.get(i);
                    firstValue = ReflectionUtil.getProperty(data, valueField);
                    secondValue = ReflectionUtil.getProperty(data, descField);
                    if (firstValue != null) {
                        WebOption option = new WebOption();
                        option.setValue(firstValue.toString());
                        String strValue = secondValue.toString();
                        if (rightStep) {
                            int containNumber = StrUtil.containNum(firstValue.toString(), containStr);
                            strValue = StrUtil.getStrByCount(EMPTY_STR, containNumber) + strValue;
                        }
                        option.setLabel(strValue);
                        if (selectedValue != null && firstValue.toString().equals(selectedValue)) {
                            option.setSelected(true);
                        }
                        if (hasAttribute) {
                            for(int j = 0; j < attributeNames.length; j++){
                                String attributeName = attributeNames[j];
                                String srcProperty = srcProperties[j];
                                String attProperty = StrUtil.nullToString(ReflectionUtil.getProperty(data, srcProperty));
                                option.addAttribute(attributeName, attProperty);
                            }
                        }
                        options.addOption(option);
                    }
                }
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new WebException(ex.getMessage());
        }
        return options;
    }
}
