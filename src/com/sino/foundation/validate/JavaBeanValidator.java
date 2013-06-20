package com.sino.foundation.validate;


import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.foundation.exception.EmptyException;

import java.util.List;

/**
* <p>Title: SinoApplication</p>
* <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
* <p>@todo����ʱλ�ڸð��£�����ʵ����Ŀ����֤֮�󣬽����������⣬��ȡ��Ŀǰ���������ù���</p>
* <p>Copyright: ����˼ŵ����Ȩ����Copyright (c) 2003~2008��
* <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
* <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
* @author ����ʤ
* @version 0.1
 */
public class JavaBeanValidator {

    public static void validateJavaBean(Object javaBean) throws EmptyException {
        String errorMsg = "";
        if (javaBean == null) {
            errorMsg = "������Ϊ��";
            throw new EmptyException(errorMsg);
        }
        List properties = ReflectionUtil.getProperties(javaBean.getClass());
        int propCount = properties.size();
        String fieldName = "";
        Object fieldValue = null;
        String className = javaBean.getClass().getName();
        try {
            for (int i = 0; i < propCount; i++) {
                fieldName = String.valueOf(properties.get(i));
                fieldValue = ReflectionUtil.getProperty(javaBean, fieldName);
                if (StrUtil.isEmpty(fieldValue)) {
                    errorMsg = "����"
                            + className
                            + "�����ֶΡ�"
                            + fieldName
                            + "����ֵΪ��";
                    throw new EmptyException(errorMsg);
                }
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new EmptyException(ex.getMessage());
        }
    }

    public static void validateJavaBean(Object javaBean, List<String> ignoreFields) throws EmptyException {
        String errorMsg = "";
        if (javaBean == null) {
            errorMsg = "������Ϊ��";
            throw new EmptyException(errorMsg);
        }
        List properties = ReflectionUtil.getProperties(javaBean.getClass());
        int propCount = properties.size();
        String fieldName = "";
        Object fieldValue = null;
        String className = javaBean.getClass().getName();
        try {
            for (int i = 0; i < propCount; i++) {
                fieldName = String.valueOf(properties.get(i));
                if (ignoreFields.contains(fieldName)) {
                    continue;
                }
                fieldValue = ReflectionUtil.getProperty(javaBean, fieldName);
                if (StrUtil.isEmpty(fieldValue)) {
                    errorMsg = "����"
                            + className
                            + "�����ֶΡ�"
                            + fieldName
                            + "����ֵΪ��";
                    throw new EmptyException(errorMsg);
                }
            }
        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new EmptyException(ex.getMessage());
        }
    }
}
