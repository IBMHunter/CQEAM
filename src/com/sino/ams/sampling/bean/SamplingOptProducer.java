package com.sino.ams.sampling.bean;

import java.sql.Connection;

import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.QueryException;
import com.sino.base.web.DatabaseForWeb;


/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class SamplingOptProducer extends AssetsOptProducer {

	public SamplingOptProducer(SfUserDTO userAccount, Connection conn) {
		super(userAccount, conn);
	}

	/**
	 * ���ܣ���ȡָ������δ�����OU��֯�����б�
	 * @param taskId String
	 * @return String
	 * @throws QueryException
	 */
	public String getTaskLeftOuOPt(String taskId) throws QueryException {
		SQLModel sqlModel = OptPrdSQLProducer.getTaskLeftOuOptModel(taskId);
		DatabaseForWeb db2web = new DatabaseForWeb(sqlModel, conn);
		return db2web.getOptionHtml();
	}

	/**
	 * ���ܣ���ȡָ�������ѷ����OU��֯�����б�
	 * @param taskId String
	 * @return String
	 * @throws QueryException
	 */
	public String getTaskSampledOuOpt(String taskId) throws QueryException{
		SQLModel sqlModel = OptPrdSQLProducer.getTaskSampledOuOptModel(taskId);
		DatabaseForWeb db2web = new DatabaseForWeb(sqlModel, conn);
		return db2web.getOptionHtml();
	}

	/**
	 * ���ܣ����칤��״̬�����б��
	 * @param selectedValue String
	 * @return String
	 * @throws QueryException
	 */
	public String getOrderStatusOpt(String selectedValue) throws QueryException{
		SQLModel sqlModel = OptPrdSQLProducer.getOrderStatusModel();
		DatabaseForWeb db2web = new DatabaseForWeb(sqlModel, conn);
		return db2web.getOptionHtml(selectedValue, true);
	}
}
