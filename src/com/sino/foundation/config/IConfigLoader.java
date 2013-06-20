package com.sino.foundation.config;

import com.sino.base.exception.ConfigException;

import java.io.File;
import java.io.InputStream;

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

public interface IConfigLoader {

    /**
     * ���ܣ����������ļ�������
     *
     * @param configFilter �����ļ�������
     */
    void setConfigFilter(ConfigFileFilter configFilter);

    /**
     * ���ܣ����������ļ�
     *
     * @param configFile File
     */
    void setConfigFile(File configFile);

    /**
     * ���ܣ����������ļ���������
     *
     * @param in InputStream
     */
    void setConfigInputStream(InputStream in);


    /**
     * ���ܣ�����WebӦ�ø�·��
     *
     * @param contextPath WebӦ�ø�·��
     */
    void setContextPath(String contextPath);

    /**
     * ���ܣ����������ļ�
     *
     * @throws com.sino.base.exception.ConfigException
     *          ���������ļ�����ʱ�׳������쳣
     */
    void loadConfig() throws ConfigException;

    /**
     * ���ܣ�ж�ؼ��ص�����
     * <B>Ĭ�Ͻ����ͷ��ɼ��ؽ��������ݡ�<B>
     * <B>�������ļ������ж����ж�ع�����Ӧ�����Ǳ��������������ݿ����ӳص�ж�ػ�Ӧ��ж�����ݿ�����</B>
     */
    void unloadConfig();

    /**
     * ���ܣ����ظ��������ļ����غ�Ľ������
     *
     * @return LoadedResult
     */
    ConfigLoadedResult getLoadedResult();
}
