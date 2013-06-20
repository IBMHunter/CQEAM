package com.sino.ams.match.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.match.dto.EtsItemMatchMisDTO;
import com.sino.ams.match.model.EtsItemMatchMisModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Suhp
 * Date: 2007-11-26
 * Time: 20:18:58
 * To change this template use File | Settings | File Templates.
 */

public class EtsItemMatchMisDAO extends BaseDAO {
	private SfUserDTO sfUser = null;
	private EtsItemMatchMisModel itemMatchMis = null;

	public EtsItemMatchMisDAO(SfUserDTO userAccount, EtsItemMatchMisDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
		itemMatchMis = new EtsItemMatchMisModel((SfUserDTO) userAccount, dtoParameter);
	}

	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EtsItemMatchMisDTO dtoPara = (EtsItemMatchMisDTO) dtoParameter;
		super.sqlProducer = new EtsItemMatchMisModel((SfUserDTO) userAccount, dtoPara);

	}

	/**
	 * ���ܣ�����������ETS�豸Excel�ļ���
	 *
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 *
	 */
	public File exportEtsFile() throws DataTransException {
		File file = null;
//        try {
//            SQLModel sqlModel = sqlProducer.getPageQueryModel();
		SQLModel sqlModel = itemMatchMis.getPageQueryModel();
		TransRule rule = new TransRule();
		rule.setDataSource(sqlModel);
		rule.setCalPattern(CalendarConstant.LINE_PATTERN);
		rule.setSourceConn(conn);
		String fileName = "�������豸�嵥.xls";
		String filePath = WorldConstant.USER_HOME;
		filePath += WorldConstant.FILE_SEPARATOR;
		filePath += fileName;
		rule.setTarFile(filePath);
		DataRange range = new DataRange();
		rule.setDataRange(range);

		Map fieldMap = new HashMap();
		fieldMap.put("BARCODE", "��ǩ��");
		fieldMap.put("ITEM_NAME", "�豸����");
		fieldMap.put("ITEM_SPEC", "����ͺ�");
		fieldMap.put("ITEM_CATEGORY", "�豸רҵ");
		fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
		fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
		fieldMap.put("COST_CENTER_NAME", "�ɱ�����");
		rule.setFieldMap(fieldMap);

		CustomTransData custData = new CustomTransData();
		custData.setReportTitle("�������豸����");
		custData.setReportPerson(sfUser.getUsername());
		custData.setNeedReportDate(true);
		rule.setCustData(custData);
		TransferFactory factory = new TransferFactory();
		DataTransfer transfer = factory.getTransfer(rule);
		transfer.transData();
		file = (File) transfer.getTransResult();
//        } catch (SQLModelException ex) {
//            ex.printLog();
//            throw new DataTransException(ex);
//        }
		return file;
	}

	/**
	 * ���ܣ�����������Mis�ʲ�Excel�ļ���
	 *
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 *
	 */
	public File exportMisFile() throws DataTransException {
		File file = null;
//        try {
//            SQLModel sqlModel = sqlProducer.getPageQueryModel();
			SQLModel sqlModel = itemMatchMis.getMisPageQueryModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setCalPattern(CalendarConstant.LINE_PATTERN);
			rule.setSourceConn(conn);
			String fileName = "�������ʲ��嵥.csv";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("TAG_NUMBER", "�ʲ�����");
			fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
			fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
			fieldMap.put("FA_CATEGORY1", "�ʲ�����1");
			fieldMap.put("FA_CATEGORY2", "�ʲ�����2");
			fieldMap.put("REMARK", "����ԭ��");
			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle("�������ʲ�����");
			custData.setReportPerson(sfUser.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
//        } catch (SQLModelException ex) {
//            ex.printLog();
//            throw new DataTransException(ex);
//        }
		return file;
	}

	/**
	 * ���ܣ����������ε�ETS�豸��
	 *
	 * @ File
	 * @throws com.sino.base.exception.DataTransException
	 *
	 */
	public void repealEtsData(String[] systemids) throws DataHandleException {
		EtsItemMatchMisModel etsItemMatchModel = (EtsItemMatchMisModel) sqlProducer;
		SQLModel sqlModel = etsItemMatchModel.getDisabledEtsModel(systemids);
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * ���ܣ����������ε�MIS�ʲ���
	 *
	 * @ File
	 * @throws com.sino.base.exception.DataTransException
	 *
	 */
	public void repealMisData(String[] assetIds) throws DataHandleException {
		EtsItemMatchMisModel etsItemMatchModel = (EtsItemMatchMisModel) sqlProducer;
		SQLModel sqlModel = etsItemMatchModel.getDisabledMisModel(assetIds);
		DBOperator.updateRecord(sqlModel, conn);
	}

}
