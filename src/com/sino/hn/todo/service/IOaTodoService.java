package com.sino.hn.todo.service;

import com.sino.hn.todo.dto.OaResponseDTO;
import com.sino.sinoflow.todo.dto.OaTodoDTO;

/**
 * 
 * 
 * @ϵͳ����:  
 * @��������: ��������
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sangjun
 * @����ʱ��: Dec 3, 2011
 */
public interface IOaTodoService {
	public void clear();

	public boolean saveSender(OaTodoDTO todoDTO);
	
	public OaResponseDTO getResponseDTO();
}
