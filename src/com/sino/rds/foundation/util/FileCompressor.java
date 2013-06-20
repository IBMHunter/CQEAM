package com.sino.rds.foundation.util;


import com.sino.base.SinoBaseObject;
import com.sino.base.constant.CompressMethodConstant;
import com.sino.base.constant.WorldConstant;
import com.sino.base.exception.CompressException;
import com.sino.base.exception.FileException;
import com.sino.base.file.FileProcessor;
import com.sino.base.log.Logger;
import com.sino.base.util.PlatUtil;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.GZIPOutputStream;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>@todo���ڴ˼��뱾������������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class FileCompressor extends SinoBaseObject {
	private CompressConfig config = null;
	private List<File> files = null;
	private String method = "";
	private String targetPath = "";
	private String fileName = "";
	private String encode = "";
	private GZIPOutputStream gZipOut = null;
	private ZipOutputStream zipOut = null;

	/**
	 * ���ܣ�����ѹ�����á�
	 * @param config CompressConfig
	 * @throws CompressException
	 */
	public void setConfig(CompressConfig config) throws CompressException {
		clearConfigData();
		this.config = config;
		files = config.getSrcFiles();
		method = config.getCompressMethod();
		targetPath = config.getTargetPath();
		fileName = config.getFileName();
		int index = fileName.lastIndexOf(".");
		if(index == -1){
			fileName += ".zip";
		} else {
			fileName = fileName.substring(0, index + 1) + "zip";
		}
		encode = config.getEncode();
		if (!targetPath.endsWith(WorldConstant.FILE_SEPARATOR)) {
			targetPath += WorldConstant.FILE_SEPARATOR;
		}
		targetPath += fileName;
	}

	/**
	 * ���ܣ�ѹ���ļ�
	 * @throws CompressException
	 */
	public void compress() throws CompressException {
		try {
			startCompress();
			compressFile();
			endCompress();
			if (config.isDeleteSrc()) {
				deleteSrcFiles();
			}
		} catch (FileException ex) {
			ex.printLog();
			throw new CompressException(ex);
		}
	}

	private void startCompress() throws CompressException {
		try {
			OutputStream fileOut = new FileOutputStream(targetPath);
			if (method.equals(CompressMethodConstant.GZIP_METHOD)) {
				gZipOut = new GZIPOutputStream(fileOut);
			} else if (method.endsWith(CompressMethodConstant.ZIP_METHOD)) {
				CheckedOutputStream chOut = new CheckedOutputStream(fileOut, new CRC32());
				zipOut = new ZipOutputStream(chOut);
				zipOut.setComment(CompressMethodConstant.ZIP_COMMENT);
			}
		} catch (FileNotFoundException ex) {
			Logger.logError(ex);
			throw new CompressException(ex);
		} catch (IOException ex) {
			Logger.logError(ex);
			throw new CompressException(ex);
		}
	}

	/**
	 * ���ܣ��ݹ��������ѹ���ļ����ļ��С�
	 * @throws CompressException
	 */
	private void compressFile() throws CompressException {
		int fileCount = files.size();
		File file = null;
		for (int i = 0; i < fileCount; i++) {
			file = files.get(i);
			if (file.isFile()) {
				fileToZip(file);
			} else {
				dirToZip(file);
			}
		}
	}

	/**
	 * ���ܣ����ļ���ӵ�ѹ������
	 * @param file File �ļ�
	 * @throws CompressException
	 */
	private void fileToZip(File file) throws CompressException {
		try {
			FileInputStream fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis, 1024);
			byte b[] = new byte[1024];
			int len = 0;
			if (method.equals(CompressMethodConstant.GZIP_METHOD)) {
				while ((len = bis.read(b)) != -1) {
					gZipOut.write(b, 0, len);
				}
			} else if (method.equals(CompressMethodConstant.ZIP_METHOD)) {
				String enteryName = file.getAbsolutePath();
				if(PlatUtil.isWindowsPlat()){
					enteryName = enteryName.substring(2);
				}
				ZipEntry nextEntry = new ZipEntry(enteryName);
				zipOut.putNextEntry(nextEntry);
				while ((len = bis.read(b)) != -1) {
					zipOut.write(b, 0, len);
				}
			}
			bis.close();
			fis.close();
		} catch (IOException ex) {
			Logger.logError(ex);
			throw new CompressException(ex);
		}
	}

	/**
	 * ���ܣ�ѹ���ļ���
	 * @param file File
	 * @throws CompressException
	 */
	private void dirToZip(File file) throws CompressException {
		File listfile[] = file.listFiles();
		for (File tmpFile:listfile) {
			if (tmpFile.isFile()) {
				fileToZip(tmpFile);
			} else if (tmpFile.isDirectory()) {
				dirToZip(tmpFile);
			}
		}
	}

	private void endCompress() throws CompressException {
		try {
			if (method.equals(CompressMethodConstant.ZIP_METHOD)) {
				zipOut.flush();
				zipOut.close();
			} else if (method.equals(CompressMethodConstant.GZIP_METHOD)) {
				gZipOut.flush();
				gZipOut.close();
			}
		} catch (IOException ex) {
			Logger.logError(ex);
			throw new CompressException(ex);
		}
	}

	/**
	 * ���ܣ�ɾ��Դ�ļ�
	 * @throws FileException
	 */
	private void deleteSrcFiles() throws FileException {
		int fileCount = files.size();
		File file = null;
		FileProcessor fileProcessor = new FileProcessor();
		for (int i = 0; i < fileCount; i++) {
			file = files.get(i);
			fileProcessor.delete(file);
		}
	}

	/**
	 * ���ܣ����ѹ��������Ϣ
	 */
	private void clearConfigData() {
		this.files = null;
		this.config = null;
		this.method = "";
		this.targetPath = "";
		this.fileName = "";
	}

	/**
	 * ���ܣ���ȡѹ������ļ���
	 * @return File
	 */
	public File getCompressedFile(){
		return new File(targetPath);
	}
}

