package com.sino.ams.newasset.scrap.model;

import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.model.AmsAssetsTransHeaderModel;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * 
 * @ϵͳ����: ����ʵ�ﱨ������
 * @��������: 
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Jul 5, 2011
 */
public class TransModel extends AmsAssetsTransHeaderModel {
	private SfUserDTO user = null;

	/**
	 * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݿ�SQL����㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsTransHeaderDTO ���β���������
	 */
	public TransModel(SfUserDTO userAccount,
			AmsAssetsTransHeaderDTO dtoParameter) {
		super(userAccount, dtoParameter);
		this.user = (SfUserDTO) userAccount;
	}

	 
}
