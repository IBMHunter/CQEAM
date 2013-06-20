package com.sino.foundation.config;

import com.sino.base.constant.WorldConstant;
import com.sino.base.util.PathUtil;
import com.sino.base.util.StrUtil;

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
public abstract class ConfigPathUtil {
    /**
     * ���ܣ���ȡ���ļ��ָ�����β�������ļ�·����
     *
     * @param relativePath String
     * @return String
     */
    public static String getConfigPath(String relativePath) {
        String configPath = "";
        if (relativePath.startsWith("classpath:")) {
            relativePath = StrUtil.trim(relativePath, "classpath:", true);
            configPath = PathUtil.getAbsolutePath("", relativePath);
        } else if (relativePath.indexOf("WEB-INF/classes") == 0) {
            relativePath = StrUtil.trim(relativePath, "WEB-INF/classes");
            configPath = PathUtil.getAbsolutePath("", relativePath);
        } else if (relativePath.indexOf("/WEB-INF/classes") == 0) {
            relativePath = StrUtil.trim(relativePath, "/WEB-INF/classes");
            configPath = PathUtil.getAbsolutePath("", relativePath.substring(1));
        } else {
            configPath = PathUtil.getAbsolutePath(relativePath);
        }
        configPath = StrUtil.replaceStr(configPath, "%20", WorldConstant.EMPTY_SPACE);
        return configPath;
    }
}
