package com.sino.foundation.config;

import com.sino.base.exception.StrException;
import com.sino.base.util.StrUtil;

import java.io.File;
import java.io.FilenameFilter;

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
public class ConfigFileFilter implements FilenameFilter {
    private String fileExtention = "";

    public ConfigFileFilter(String fileExtention) {
        if (fileExtention != null) {
            this.fileExtention = fileExtention;
        }
    }

    public boolean accept(File fileDir, String name) {
        boolean acceptValue = true;
        try {
            File file = new File(fileDir, name);
            if (file.isDirectory()) {
                acceptValue = true;
            } else if (fileExtention.length() > 0) {
                acceptValue = StrUtil.endsWith(name, fileExtention);
            }
        } catch (StrException ex) {
            ex.printLog();
            acceptValue = false;
        }
        return acceptValue;
    }

    /**
     * ���ܣ��Ƿ�ɼ���ָ���ļ�
     *
     * @param file �ļ�����
     * @return true��ʾ���ϼ�������, false��ʾ�����ϼ�������
     */
    public boolean isCertificate(File file) {
        boolean needLoadConfig = true;
        try {
            if (file == null) {
                needLoadConfig = false;
            } else {
                String name = file.getName();
                needLoadConfig = StrUtil.endsWith(name, fileExtention);
            }
        } catch (StrException ex) {
            ex.printLog();
            needLoadConfig = false;
        }
        return needLoadConfig;
    }
}