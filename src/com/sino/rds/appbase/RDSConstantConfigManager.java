package com.sino.rds.appbase;

import com.sino.base.exception.ConfigException;
import com.sino.base.exception.ReflectException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.util.SystemUtil;
import com.sino.framework.dto.BaseUserDTO;

import java.io.InputStream;
import java.util.Properties;

public abstract class RDSConstantConfigManager {
    private static String userClass = "";
    private static Class cls = null;
    private static String userId = "";
    private static long limitMergeSize = 0L;

    static {
        try {
            if (StrUtil.isEmpty(userClass)) {
                InputStream in = RDSConstantConfigManager.class.getResourceAsStream("RDSConstantConfig.properties");
                Properties properties = new Properties();
                properties.load(in);
                userClass = (String) properties.get("userClass");
                userId = (String) properties.get("userId");
                String errorMsg = "";
                String path = "/com/sino/rds/appbase/RDSConstantConfig.properties";
                if (StrUtil.isEmpty(userClass)) {
                    errorMsg = "���ڡ�"
                            + path
                            + "���������û��࣬�����ΪuserClass";
                    throw new ConfigException(errorMsg);
                }
                if (StrUtil.isEmpty(userId)) {
                    errorMsg = "���ڡ�"
                            + path
                            + "���������û�ID�ֶΣ������ΪuserId";
                    throw new ConfigException(errorMsg);
                }
                cls = Class.forName(userClass);
                Class baseCls = BaseUserDTO.class;
                if (!SystemUtil.isDerivedClass(cls, baseCls)) {
                    errorMsg = "�ڡ�"
                            + path
                            + "�������õ��û��ࡰ"
                            + userClass
                            + "�������û�����"
                            + baseCls.getName()
                            + "������";
                    throw new ConfigException(errorMsg);
                }
                if (!ReflectionUtil.hasProperty(cls, userId)) {
                    errorMsg = "�ڡ�"
                            + path
                            + "�������õ��û��ࡰ"
                            + userClass
                            + "���������ԡ�"
                            + userId
                            + "��";
                    throw new ConfigException(errorMsg);
                }
                String strValue = String.valueOf(properties.get("limitMergeSize"));
                if(StrUtil.isNumber(strValue)){
                    limitMergeSize = Long.parseLong(strValue);
                }
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
    }

    public static String getUserId(BaseUserDTO userAccount) {
        String returnUserId = "";
        try {
            if (userAccount != null) {
                returnUserId = String.valueOf(ReflectionUtil.getProperty(userAccount, userId));
            }
        } catch (ReflectException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return returnUserId;
    }

    public static long getLimitMergeSize(){
        return limitMergeSize;
    }

    public static boolean contains(String fieldName){
        return ReflectionUtil.hasProperty(cls, fieldName);
    }
}
