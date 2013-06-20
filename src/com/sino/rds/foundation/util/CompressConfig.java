package com.sino.rds.foundation.util;

import com.sino.base.SinoBaseObject;
import com.sino.base.constant.CompressMethodConstant;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CompressConfig extends SinoBaseObject {
	private List<File> srcFiles = null;
	private String targetPath = "";
	private String compressMethod = CompressMethodConstant.ZIP_METHOD;
	private String fileName = "";
	private String encode = "GBK";
	private boolean deleteSrc = false;

	public CompressConfig() {
		srcFiles = new ArrayList<File>();
	}

	/**
	 * ���ܣ������ѹ���ļ���
	 * @param file File ��ѹ���ļ����������ļ���Ҳ�����ǰ����ļ������ļ��е��ļ��С�
	 */
	public void addFile(File file) {
		if(file == null){
			return;
		}
		if(!file.exists()){
			return;
		}
		if (!srcFiles.contains(file)) {
			srcFiles.add(file);
		}
	}

	/**
	 * ���ܣ������ѹ���ļ���
	 * @param file String ��ѹ���ļ����������ļ���Ҳ�����ǰ����ļ������ļ��е��ļ��С�
	 */
	public void addFile(String file) {
		File srcFile = new File(file);
		addFile(srcFile);
	}

	/**
	 * ���ܣ������ļ��б�
	 * @param files List
	 */
	public void setFiles(List files) {
		if(files == null || files.isEmpty()){
			return;
		}
		int fileCount = files.size();
		srcFiles.clear();
		Object file = null;
		for(int i = 0; i < fileCount; i++){
			file = files.get(i);
			if(file == null){
				continue;
			}
			if(file instanceof String){
				addFile((String)file);
			} else if(file instanceof File){
				addFile((File)file);
			}
		}
	}

	/**
	 * ���ܣ��趨ѹ��������Ŀǰ��֧��ZIP��GZIP��ʽ��
	 * @param compressMethod String
	 */
	public void setCompressMethod(String compressMethod) {
		this.compressMethod = compressMethod;
	}

	/**
	 * ���ܣ�����Ŀ��·����
	 * @param targetPath String
	 */
	public void setTargetPath(String targetPath) {
		this.targetPath = targetPath;
	}

	/**
	 * ���ܣ�����ѹ������ļ�����
	 * @param fileName String
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public void setDeleteSrc(boolean deleteSrc) {
		this.deleteSrc = deleteSrc;
	}

	/**
	 * ���ܣ���ȡѹ������
	 * @return String
	 */
	public String getCompressMethod() {
		return compressMethod;
	}

	/**
	 * ���ܣ���ȡ�ļ�����
	 * @return String
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * ���ܣ���ȡ��ѹ���ļ��б�
	 * @return List
	 */
	public List<File> getSrcFiles() {
		return srcFiles;
	}

	/**
	 * ���ܣ�����Ŀ��·����
	 * @return String
	 */
	public String getTargetPath() {
		return targetPath;
	}

	public String getEncode() {
		return encode;
	}

	public boolean isDeleteSrc() {
		return deleteSrc;
	}
}
