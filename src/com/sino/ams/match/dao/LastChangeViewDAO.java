package com.sino.ams.match.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.match.model.LastChangeViewModel;
import com.sino.ams.system.fixing.dto.EtsItemInfoDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.data.Row;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class LastChangeViewDAO extends AMSBaseDAO {

	public LastChangeViewDAO(BaseUserDTO userAccount, EtsItemInfoDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		EtsItemInfoDTO dto = (EtsItemInfoDTO)dtoParameter;
		sqlProducer = new LastChangeViewModel(user, dto);
	}

	/**
	 * ���ܣ���ȡ�ϴα����Ϣ
	 * @return String
	 * @throws QueryException
	 */
	public String getLastChangeInfo() throws QueryException {
		StringBuffer changeInfo = new StringBuffer();
		try {
			LastChangeViewModel modelProducer = (LastChangeViewModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getLastChangeModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				Row row = simp.getFirstRow();
				changeInfo.append("��ǩ�ţ�");
				changeInfo.append(row.getValue("BARCODE"));
				changeInfo.append("<br>����û���");
				changeInfo.append(row.getValue("LAST_CHG_USER"));
				changeInfo.append("<br>�������ͣ�");
				changeInfo.append(row.getValue("LAST_ORDER_TYPE"));
				changeInfo.append("<br>������ݣ�");
				changeInfo.append(row.getValue("LAST_ORDER_NO"));
				changeInfo.append("<br>������ڣ�");
				changeInfo.append(row.getValue("LAST_CHG_DATE"));
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return changeInfo.toString();
	}
}
