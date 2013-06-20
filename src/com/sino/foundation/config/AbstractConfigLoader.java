package com.sino.foundation.config;


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

public abstract class AbstractConfigLoader implements IConfigLoader {
    protected ConfigLoadedResult loadedResult = null; //�������������������
    protected File configFile = null;
    protected InputStream in = null;
    protected String contextPath = "";
    protected ConfigFileFilter configFilter = null;

    public void setConfigFilter(ConfigFileFilter configFilter) {
        this.configFilter = configFilter;
    }

    /**
     * ���ܣ����������ļ�
     *
     * @param configFile File
     */
    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }

    /**
     * ���ܣ����������ļ���������
     *
     * @param in InputStream
     */
    public void setConfigInputStream(InputStream in) {
        this.in = in;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    /**
     * ���ܣ�ж�ؼ��ص�����
     * <B>Ĭ�Ͻ����ͷ��ɼ��ؽ��������ݡ�<B>
     * <B>�������ļ������ж����ж�ع�����Ӧ�����Ǳ��������������ݿ����ӳص�ж�ػ�Ӧ��ж�����ݿ�����</B>
     */
    public void unloadConfig() {
        if (loadedResult != null) {
            loadedResult.releaseData();
        }
        loadedResult = null;
    }

    /**
     * ���ܣ����ظ��������ļ����غ�Ľ������
     *
     * @return LoadedResult
     */
    public ConfigLoadedResult getLoadedResult() {
        return loadedResult;
    }
}
