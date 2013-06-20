package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.match.model.OverplusEquipmentModel;
import com.sino.ams.newasset.dto.EtsFaAssetsDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * User: ����
 * Date: 2009-6-14
 * Time: 16:56:27
 * Function:	��ӯ�ʲ�ͳ��
 */
public class InventoryProfitAssetsReportDAO extends AMSBaseDAO {

	public InventoryProfitAssetsReportDAO(SfUserDTO userAccount, EtsFaAssetsDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EtsFaAssetsDTO dtoPara = (EtsFaAssetsDTO) dtoParameter;
		super.sqlProducer = new OverplusEquipmentModel((SfUserDTO) userAccount, dtoPara);
	}

	/**
	 * ���ܣ�����Excel�ļ���
	 *
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 *
	 */
	public File exportFile() throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setCalPattern(CalendarConstant.LINE_PATTERN);
			rule.setSourceConn(conn);
			String fileName = "��ӯ�ʲ�ͳ�Ʊ���.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("BARCODE", "EAM����");
			fieldMap.put("ITEM_NAME", "EAM�豸����");
			fieldMap.put("ITEM_SPEC", "EAM����ͺ�");
			fieldMap.put("ITEM_CATEGORY", "�豸����");
			fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
			fieldMap.put("WORKORDER_OBJECT_NAME", "�豸�ص�");
			fieldMap.put("COST_CENTER_NAME", "�ɱ�����");
			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle("��ӯ�ʲ�ͳ�Ʊ���");
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}
}
