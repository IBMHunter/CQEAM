package com.sino.ams.newasset.lose.dto;

import com.sino.ams.appbase.dto.AMSFlowDTO;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;

/**
 * 
 * @ϵͳ����: ��ʧ
 * @��������: 
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Jul 14, 2011
 */
public class LoseDTO extends AMSFlowDTO implements DTO{
	LoseHeaderDTO headerDTO = null;
	DTOSet lines = null;   
	public String act = "" ; 
	
	public LoseHeaderDTO getHeaderDTO() {
		return headerDTO;
	}

	public void setHeaderDTO(LoseHeaderDTO headerDTO) {
		this.headerDTO = headerDTO;
	}

	public DTOSet getLines() {
		return lines;
	}

	public void setLines(DTOSet lines) {
		this.lines = lines;
	}

	public String getAct() {
		return act;
	}

	public void setAct(String act) {
		this.act = act;
	}
}