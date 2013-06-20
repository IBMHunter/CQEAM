package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ChkOrderUpLoadModel extends AmsAssetsCheckHeaderModel {

	public ChkOrderUpLoadModel(BaseUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}



	/**
	 * ���ܣ�������µ�ǰ�û��ϴ�������SQL
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getUploadChkOrdersModel() throws SQLModelException {
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO) dtoParameter;
			String sqlStr = "UPDATE"
							+ " AMS_ASSETS_CHECK_HEADER"
							+ " SET"
							+ " ORDER_STATUS = ?,"
							+ " SCANOVER_DATE = ?,"
							+ " SCANOVER_BY = ?,"
							+ " NEW_LOCATION = ?,"
							+ " UPLOAD_DATE = GETDATE(),"
							+ " UPLOAD_BY = ?"
							+ " WHERE"
							+ " HEADER_ID = ?";
			sqlArgs.add(dto.getOrderStatus());
			sqlArgs.add(dto.getScanoverDate());
			sqlArgs.add(dto.getScanoverBy());
			sqlArgs.add(dto.getNewLocation());
			sqlArgs.add(dto.getUploadBy());
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
