package com.sino.foundation.config.system;


import com.sino.base.constant.WorldConstant;

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
public class EntryConfig{
	private String name = "";
	private String value = "";
	private String loaderClassName = "";
	private boolean loadConfig = false;
	private String entryPath = "";//�����ļ�����·���������ļ�����
	private String absolutePath = "";
	private boolean supportReload = false;//�Ƿ�֧���ȼ���
	private String description = "";//��������
	private String postProcessor = "";//���ú�����
	private String fileExtention = "";//�ļ�����׺
    private long delayTime = 0L;//����������к��ô���������ѡ����Ч����ʾ���ô������ӳ������ĺ�����

	public EntryConfig() {
		super();
	}

	public String getValue() {
		return value;
	}

	public String getLoaderClassName() {
		return loaderClassName;
	}

	public String getName() {
		return name;
	}

	public boolean isLoadConfig() {
		return loadConfig;
	}

	public String getEntryPath() {
		return entryPath;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setLoaderClassName(String loaderClassName) {
		this.loaderClassName = loaderClassName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLoadConfig(boolean loadConfig) {
		this.loadConfig = loadConfig;
	}

	public void setEntryPath(String entryPath) {
		this.entryPath = entryPath;
        String localPath = "";
		if(!entryPath.endsWith(WorldConstant.FILE_SEPARATOR)){
			localPath = entryPath + WorldConstant.FILE_SEPARATOR + value;
		} else {
			localPath = entryPath + value;
		}
        setAbsolutePath(localPath);
	}

	public void setSupportReload(boolean supportReload) {
		this.supportReload = supportReload;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAbsolutePath(){
		return this.absolutePath;
	}

	public boolean isSupportReload() {
		return supportReload;
	}

	public String getDescription() {
		return description;
	}

    public String getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(String postProcessor) {
        this.postProcessor = postProcessor;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public boolean equals(EntryConfig anotherEntry){
        boolean equals = false;
        if(anotherEntry != null){
            return name.equals(anotherEntry.getName());
        }
        return equals;
    }

    public String getFileExtention() {
        return fileExtention;
    }

    public void setFileExtention(String fileExtention) {
        if(fileExtention != null){
            this.fileExtention = fileExtention;
        }
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }
}
