package com.sino.appbase.config;

import com.sino.base.SinoBaseObject;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class SOAConfig extends SinoBaseObject {
    private String systemName;
    private String encoding;
    private String host;
    private int port;
    private String icpID;
    private String icpAuth;
    private String startTime;
    private String endTime;

    public SOAConfig() {
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setIcpAuth(String icpAuth) {
        this.icpAuth = icpAuth;
    }

    public void setIcpID(String icpID) {
        this.icpID = icpID;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getIcpAuth() {
        return icpAuth;
    }

    public String getIcpID() {
        return icpID;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }
}