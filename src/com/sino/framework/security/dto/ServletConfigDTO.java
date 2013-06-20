package com.sino.framework.security.dto;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.SinoBaseObject;
import com.sino.base.dto.DTO;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class ServletConfigDTO extends SinoBaseObject implements DTO {
    private boolean loadConnPools = false;
    private boolean loadMessages = false;
    private boolean startSMSService = false;
    private boolean startSOAService = false;
    private boolean startListener = false;
    private boolean startWorkorderDefineService = false;
    private int listenFrequency = 0;
    private boolean startRecycleServer = false;
    private boolean checkUnique = false;
    private boolean checkEmpty = false;
    private boolean checkLength = false;
    private boolean checkNumber = false;
    private String systemName = "";
    private String sysAdminRole = "";//ϵͳ����Ա
    private String cityAdminRole = "";//���й���Ա
    private String provAssetsMgr = "";//ȫʡ�ʲ�����Ա
    private String compAssetsMgr = "";//��˾�ʲ�����Ա
    private String deptAssetsMgr = "";//�����ʲ�����Ա
    private String mtlAssetsMgr = "";//רҵ�ʲ�����Ա

    private int provinceOrgId;
    private String proCompanyCode = "";
    private int tdProvinceOrgId = 0;//TD�����V��ID
    private String tdProCompanyCode = "";//TD���󶱜y�u
    private String proCompanyName = "";
    private String provinceCode = "";
    private String tdProvinceCode = "";
    private String loginImage = "";
    private String topImage = "";
    private boolean rcvProcEnabled = false;//�Ƿ����õ����ʲ�������������
    private boolean subProcEnabled = false;//�Ƿ������ʲ���ֵ��������
    private boolean freeProcEnabled = false;//�Ƿ������ʲ�������������
    private boolean assignArchiveUser = false;//��������(�����繤�����ʲ��̵㹤��)ʱ�Ƿ�ָ���鵵��

    private String initRunner = "";//��������������̬���е���
    private String envName = "";//������������ʾ���ƣ��������ֲ��Ի���������ʽ�����ȱ����û������

    private static List ignoreFields = null;

    static {
        ignoreFields = new ArrayList();//�����ֶο��Բ���������WEB.XML�ļ���
        ignoreFields.add("startListener");
        ignoreFields.add("listenFrequency");
        ignoreFields.add("startRecycleServer");
        ignoreFields.add("checkUnique");
        ignoreFields.add("checkEmpty");
        ignoreFields.add("checkNumber");
        ignoreFields.add("subProcEnabled");
        ignoreFields.add("subProcEnabled");
        ignoreFields.add("freeProcEnabled");
        ignoreFields.add("envName");
    }

    /**
     * ���ܣ���ȡ���й���Ա��ɫ����
     * @return String
     */
    public String getCityAdminRole() {
        return cityAdminRole;
    }

    /**
     * ���ܣ���ȡ�����ļ�����Ƶ��
     * @return int
     */
    public int getListenFrequency() {
        return listenFrequency;
    }

    /**
     * ���ܣ����ӳ��Ƿ����
     * @return boolean
     */
    public boolean isLoadConnPools() {
        return loadConnPools;
    }

    /**
     * ���ܣ���Ϣ�����Ƿ����
     * @return boolean
     */
    public boolean isLoadMessages() {
        return loadMessages;
    }

    /**
     * ��ȡʡ��˾OU��֯ID
     * @return String
     */
    public int getProvinceOrgId() {
        return provinceOrgId;
    }

    /**
     * ���ܣ������ļ��������Ƿ����
     * @return boolean
     */
    public boolean isStartListener() {
        return startListener;
    }

    /**
     * �ֻ����ŷ����Ƿ����
     * @return boolean
     */
    public boolean isStartSMSService() {
        return startSMSService;
    }

    /**
     * ���ܣ���ȡϵͳ����Ա��ɫ����
     * @return String
     */
    public String getSysAdminRole() {
        return sysAdminRole;
    }

    /**
     * ���ܣ���ȡ��Ӧ��ϵͳ������
     * @return String
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * ���ܣ��Ƿ�����ݽ��пռ��
     * @return boolean
     */
    public boolean isCheckEmpty() {
        return checkEmpty;
    }

    /**
     * ���ܣ��Ƿ�����ݽ��г��ȼ��
     * �������ַ����ֶ�����
     * @return boolean
     */
    public boolean isCheckLength() {
        return checkLength;
    }

    /**
     * ���ܣ��Ƿ�����ݽ���Ψһ�Լ��
     * @return boolean
     */
    public boolean isCheckUnique() {
        return checkUnique;
    }

    /**
     * ���ܣ���ȡʡ��˾����
     * @return String
     */
    public String getProCompanyCode() {
        return proCompanyCode;
    }

    /**
     * ���ܣ���ȡʡ��˾����
     * @return String
     */
    public String getProCompanyName() {
        return proCompanyName;
    }

    /**
     * ���ܣ���ȡʡ����
     * @return String
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * ���ܣ��Ƿ�������Դ������������WebQuickQuery
     * @return boolean
     */
    public boolean isStartRecycleServer() {
        return startRecycleServer;
    }

    /**
     * ���ܣ��Ƿ�������ּ��
     * @return boolean
     */
    public boolean isCheckNumber() {
        return checkNumber;
    }

    /**
     * ���ܣ���ȡ��½ҳ�汳��ͼƬ
     * @return String
     */
    public String getLoginImage() {
        return loginImage;
    }

    /**
     * ���ܣ���ȡ��½�ɹ����ܶ�������ͼƬ
     * @return String
     */
    public String getTopImage() {
        return topImage;
    }

    public String getInitRunner() {
        return initRunner;
    }

    public String getCompAssetsMgr() {
        return compAssetsMgr;
    }

    public String getDeptAssetsMgr() {
        return deptAssetsMgr;
    }

    public String getMtlAssetsMgr() {
        return mtlAssetsMgr;
    }

    public String getProvAssetsMgr() {
        return provAssetsMgr;
    }

    public boolean isFreeProcEnabled() {
        return freeProcEnabled;
    }

    public boolean isRcvProcEnabled() {
        return rcvProcEnabled;
    }

    public boolean isSubProcEnabled() {
        return subProcEnabled;
    }

    public boolean isAssignArchiveUser() {
        return assignArchiveUser;
    }

    public String getEnvName() {
        return envName;
    }

    /**
     * ���ܣ����õ��й���Ա��ɫ����
     * @param cityAdminRole String
     */
    public void setCityAdminRole(String cityAdminRole) {
        this.cityAdminRole = cityAdminRole;
    }

    /**
     * ���ܣ����ü���������Ƶ��
     * @param listenFrequency int
     */
    public void setListenFrequency(int listenFrequency) {
        this.listenFrequency = listenFrequency;
    }

    /**
     * ���ܣ��������ݿ����ӳصļ�����
     * @param loadConnPools boolean
     */
    public void setLoadConnPools(boolean loadConnPools) {
        this.loadConnPools = loadConnPools;
    }

    /**
     * ���ܣ�������Ϣ�������������
     * @param loadMessages boolean
     */
    public void setLoadMessages(boolean loadMessages) {
        this.loadMessages = loadMessages;
    }

    /**
     * ���ܣ�����ʡ��˾OU��֯ID
     * @param provinceOrgId String
     */
    public void setProvinceOrgId(int provinceOrgId) {
        this.provinceOrgId = provinceOrgId;
    }

    /**
     * ���ܣ����ü�������������
     * @param startListener boolean
     */
    public void setStartListener(boolean startListener) {
        this.startListener = startListener;
    }

    /**
     * ���ܣ������ֻ����ŷ�����������
     * @param startSMSService boolean
     */
    public void setStartSMSService(boolean startSMSService) {
        this.startSMSService = startSMSService;
    }

    /**
     * ���ܣ�����ϵͳ����Ա��ɫ����
     * @param sysAdminRole String
     */
    public void setSysAdminRole(String sysAdminRole) {
        this.sysAdminRole = sysAdminRole;
    }

    /**
     * ���ܣ�����Ӧ��ϵͳ����
     * @param systemName String
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    /**
     * ���ܣ������Ƿ���Ҫ���пռ��
     * @param checkEmpty boolean
     */
    public void setCheckEmpty(boolean checkEmpty) {
        this.checkEmpty = checkEmpty;
    }

    /**
     * ���ܣ������Ƿ���Ҫ���г��ȼ��
     * @param checkLength boolean
     */
    public void setCheckLength(boolean checkLength) {
        this.checkLength = checkLength;
    }

    /**
     * ���ܣ������Ƿ���Ҫ����Ψһ�Լ��
     * @param checkUnique boolean
     */
    public void setCheckUnique(boolean checkUnique) {
        this.checkUnique = checkUnique;
    }

    /**
     * ���ܣ�����ʡ��˾����
     * @param proCompanyCode String
     */
    public void setProCompanyCode(String proCompanyCode) {
        this.proCompanyCode = proCompanyCode;
    }

    /**
     * ���ܣ�����ʡ��˾����
     * @param proCompanyName String
     */
    public void setProCompanyName(String proCompanyName) {
        this.proCompanyName = proCompanyName;
    }

    /**
     * ���ܣ�����ʡ����
     * @param provinceCode String
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * ���ܣ������Ƿ�������Դ������
     * @param startRecycleServer boolean
     */
    public void setStartRecycleServer(boolean startRecycleServer) {
        this.startRecycleServer = startRecycleServer;
    }

    /**
     * ���ܣ������Ƿ�������ּ��
     * @param checkNumber boolean
     */
    public void setCheckNumber(boolean checkNumber) {
        this.checkNumber = checkNumber;
    }

    public void setLoginImage(String loginImage) {
        this.loginImage = loginImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage;
    }

    public void setInitRunner(String initRunner) {
        this.initRunner = initRunner;
    }

    public void setCompAssetsMgr(String compAssetsMgr) {
        this.compAssetsMgr = compAssetsMgr;
    }

    public void setDeptAssetsMgr(String deptAssetsMgr) {
        this.deptAssetsMgr = deptAssetsMgr;
    }

    public void setMtlAssetsMgr(String mtlAssetsMgr) {
        this.mtlAssetsMgr = mtlAssetsMgr;
    }

    public void setProvAssetsMgr(String provAssetsMgr) {
        this.provAssetsMgr = provAssetsMgr;
    }

    public void setFreeProcEnabled(boolean freeProcEnabled) {
        this.freeProcEnabled = freeProcEnabled;
    }

    public void setRcvProcEnabled(boolean rcvProcEnabled) {
        this.rcvProcEnabled = rcvProcEnabled;
    }

    public void setSubProcEnabled(boolean subProcEnabled) {
        this.subProcEnabled = subProcEnabled;
    }

    public void setAssignArchiveUser(boolean assignArchiveUser) {
        this.assignArchiveUser = assignArchiveUser;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public List getIgnoreFields() {
        return ignoreFields;
    }

    /**
     * SOAՆ�O���^���ǋC
     * @return boolean
     */
    public boolean isStartSOAService() {
        return startSOAService;
    }

    /**
     * �M��SOAՆ�O?�ےY�m
     * @param startSOAService
     */
    public void setStartSOAService(boolean startSOAService) {
        this.startSOAService = startSOAService;
    }

    public int getTdProvinceOrgId() {
        return tdProvinceOrgId;
    }

    public void setTdProvinceOrgId(int tdProvinceOrgId) {
        this.tdProvinceOrgId = tdProvinceOrgId;
    }

    public String getTdProCompanyCode() {
        return tdProCompanyCode;
    }

    public void setTdProCompanyCode(String tdProCompanyCode) {
        this.tdProCompanyCode = tdProCompanyCode;
    }

    public String getTdProvinceCode() {
        return tdProvinceCode;
    }

    public void setTdProvinceCode(String tdProvinceCode) {
        this.tdProvinceCode = tdProvinceCode;
    }

    public boolean isStartWorkorderDefineService() {
        return startWorkorderDefineService;
    }

    public void setStartWorkorderDefineService(boolean startWorkorderDefineService) {
        this.startWorkorderDefineService = startWorkorderDefineService;
    }
}
