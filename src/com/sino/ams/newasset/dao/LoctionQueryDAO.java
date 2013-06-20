package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.model.AmsAssetsAddressVModel;
import com.sino.ams.newasset.model.LocationQueryModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
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
 * <p>Title: AmsAssetsCheckBatchDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsCheckBatchDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class LoctionQueryDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��ʲ��̵�����(EAM) AMS_ASSETS_CHECK_BATCH ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public LoctionQueryDAO(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
		sqlProducer = new LocationQueryModel((SfUserDTO) userAccount,dto);
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
			AmsAssetsAddressVModel modelProducer = new AmsAssetsAddressVModel(userAccount, dto);
			SQLModel sqlModel = modelProducer.getDataByForeignKeyModel("workorderObjectNo");
			String locationName = dto.getWorkorderObjectName();
			String reportTitle = "�ص�" + locationName + "�µ��ʲ�";//��������̫�������ܳ���31���ַ�.
			reportTitle = "�ص㵼���ʲ�";
			String fileName = reportTitle + ".xls";
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			Map fieldMap = getFieldMap();
			rule.setFieldMap(fieldMap);
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(reportTitle);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			rule.setCalPattern(LINE_PATTERN);
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

	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("BARCODE", "�ʲ���ǩ");
		fieldMap.put("ASSET_NUMBER", "�ʲ����");
		fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
		fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
		fieldMap.put("COST", "�ʲ�ԭֵ");
		fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
		fieldMap.put("DEPRN_COST", "�ʲ���ֵ");
		fieldMap.put("RESPONSIBILITY_USER_NAME", "������");
		fieldMap.put("DEPT_NAME", "���β���");
		fieldMap.put("MAINTAIN_USER_NAME", "ʹ����");
		return fieldMap;
	}
}
