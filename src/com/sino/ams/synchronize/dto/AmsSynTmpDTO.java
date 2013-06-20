package com.sino.ams.synchronize.dto;

import com.sino.base.SinoBaseObject;
import com.sino.base.dto.DTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class AmsSynTmpDTO extends SinoBaseObject implements DTO{
	private String sourceStr = "";
	private String targetStr = "";

	public AmsSynTmpDTO() {
		super();
	}

	public String getTargetStr() {
		return targetStr;
	}

	public String getSourceStr() {
		return sourceStr;
	}

	public void setSourceStr(String sourceStr) {
		this.sourceStr = sourceStr;
	}

	public void setTargetStr(String targetStr) {
		this.targetStr = targetStr;
	}
}
