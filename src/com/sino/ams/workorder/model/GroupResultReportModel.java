package com.sino.ams.workorder.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: �й��ƶ��ʲ�ʵ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class GroupResultReportModel extends AMSSQLProducer {

	public GroupResultReportModel(SfUserDTO userAccount, EtsWorkorderDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

	/**
	 * ���ܣ�������ͳ��Ѳ��ص���
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getPageQueryModel() throws SQLModelException{
		SQLModel sqlModel = new SQLModel();
		try {
			List sqlArgs = new ArrayList();
			String sqlStr = "SELECT"
							+ " SG.GROUP_ID,"
							+ " SG.GROUP_NAME,"
							+ " COUNT(TMP_V.GROUP_ID) SCAN_COUNT"
							+ " FROM"
							+ " SF_GROUP      SG,"
							+ " (SELECT DISTINCT"
							+ " EW.GROUP_ID,"
							+ " EW.WORKORDER_OBJECT_NO"
							+ " FROM"
							+ " ETS_WORKORDER      EW"
							+ " WHERE"
							+ " EW.WORKORDER_TYPE = ?"
							+ " AND (EW.WORKORDER_FLAG = ? OR EW.WORKORDER_FLAG = ?)"
							+ " AND EW.GROUP_ID = dbo.NVL(?, EW.GROUP_ID)"
							+ " AND ((EW.UPLOAD_DATE >= dbo.NVL(?, EW.UPLOAD_DATE)"
							+ " AND EW.UPLOAD_DATE <= dbo.NVL(?, EW.UPLOAD_DATE))"
							+ " OR (EW.UPLOAD_DATE >= dbo.NVL(?, EW.UPLOAD_DATE)"
							+ " AND EW.UPLOAD_DATE <= dbo.NVL(?, EW.UPLOAD_DATE)))) TMP_V"
							+ " WHERE"
							+ " SG.GROUP_ID *= TMP_V.GROUP_ID"
							+ " AND SG.ORGANIZATION_ID = ?"
							+ " AND SG.GROUP_ID = dbo.NVL(?, SG.GROUP_ID)"
							+ " GROUP BY"
							+ " SG.GROUP_ID,"
							+ " SG.GROUP_NAME";
			EtsWorkorderDTO dto = (EtsWorkorderDTO) dtoParameter;
			sqlArgs.add(DictConstant.ORDER_TYPE_CHECK);
			sqlArgs.add(DictConstant.WORKORDER_STATUS_UPLOADED);
			sqlArgs.add(DictConstant.WORKORDER_STATUS_ACHIEVED);
			sqlArgs.add(dto.getGroupId());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getSQLEndDate());
			sqlArgs.add(dto.getStartDate());
			sqlArgs.add(dto.getSQLEndDate());
			sqlArgs.add(userAccount.getOrganizationId());
			sqlArgs.add(dto.getGroupId());
			sqlModel.setSqlStr(sqlStr);
			sqlModel.setArgs(sqlArgs);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new SQLModelException(ex);
		}
		return sqlModel;
	}
}
