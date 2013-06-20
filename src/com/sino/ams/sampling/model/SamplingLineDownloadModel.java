package com.sino.ams.sampling.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.sampling.dto.AmsAssetsSamplingLineDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class SamplingLineDownloadModel extends AMSSQLProducer {

	public SamplingLineDownloadModel(BaseUserDTO userAccount, AmsAssetsSamplingLineDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}


	/**
	 * ���ܣ������ȡ��ǰ�û�ĳһ��鹤���µ����д�����ʲ�SQL
	 * @return SQLModel
	 */
	public SQLModel getDownloadAssetsModel() {
		SQLModel sqlModel = new SQLModel();
		AmsAssetsSamplingLineDTO dto = (AmsAssetsSamplingLineDTO) dtoParameter;
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT"
						+ " AASL.HEADER_ID,"
						+ " AASL.BARCODE,"
						+ " AASL.ITEM_CODE,"
						+ " AASL.START_DATE,"
						+ " AASL.ITEM_CATEGORY,"
						+ " AASL.ITEM_NAME,"
						+ " AASL.ITEM_SPEC,"
						+ " AASL.RESPONSIBILITY_USER,"
						+ " AASL.RESPONSIBILITY_DEPT"
						+ " FROM"
						+ " AMS_ASSETS_SAMPLING_LINE AASL"
						+ " WHERE"
						+ " AASL.HEADER_ID = ?";
		sqlArgs.add(dto.getHeaderId());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
}
