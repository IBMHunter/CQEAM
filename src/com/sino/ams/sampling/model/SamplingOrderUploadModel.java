package com.sino.ams.sampling.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class SamplingOrderUploadModel extends AMSSQLProducer {

	public SamplingOrderUploadModel(BaseUserDTO userAccount, AmsAssetsSamplingHeaderDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}


	/**
	 * ���ܣ�������µ�ǰ�û��ϴ�������SQL
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getOrderUploadModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO)
										  dtoParameter;
			String sqlStr = "UPDATE"
							+ " AMS_ASSETS_SAMPLING_HEADER"
							+ " SET"
							+ " ORDER_STATUS = ?,"
							+ " SCANOVER_DATE = ?,"
							+ " SCANOVER_BY = ?,"
							+ " UPLOAD_DATE = GETDATE(),"
							+ " UPLOAD_BY = ?"
//							+ " EDCH.LAST_UPDATE_DATE = GETDATE(),"
//							+ " EDCH.LAST_UPDATE_BY = ?"
							+ " WHERE"
							+ " HEADER_ID = ?";
			sqlArgs.add(dto.getOrderStatus());
			sqlArgs.add(dto.getScanoverDate());
			sqlArgs.add(dto.getScanoverBy());
			sqlArgs.add(userAccount.getUserId());
//			sqlArgs.add(userAccount.getUserId());
			sqlArgs.add(dto.getHeaderId());

			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}
}
