package com.sino.ams.match.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.match.dto.TfAmsMactPropertyDTO;
import com.sino.ams.match.model.TfAmsMactPropertyModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;

public class TfAmsMactPropertyDAO extends AMSBaseDAO  {

	/**
	 * ���ܣ����캯����
	 *
	 * @param userAccount  UserAccountDTO �û���Ϣ
	 * @param dtoParameter DTO ���������ݿ⽻��ʱ��Ҫ�Ĳ�����
	 * @param conn         Connection ���ݿ�����
	 */
	public TfAmsMactPropertyDAO(SfUserDTO userAccount, TfAmsMactPropertyDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}
	/**
	* ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	*
	* @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	* @param dtoParameter DTO ���β���������
	*/

	 protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
	   TfAmsMactPropertyDTO dtoPara = (TfAmsMactPropertyDTO) dtoParameter;
	   super.sqlProducer = new TfAmsMactPropertyModel((SfUserDTO) userAccount, dtoPara);
	}


	/**
	 * ���ܣ�����Excel�ļ���
	 * @return File
	 * @throws DataTransException
	 */
	public File exportFile() throws DataTransException {           //����
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			String fileName = "��ƥ���ʲ��嵥.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("BARCODE", "EAM����");
			fieldMap.put("TAG_NUMBER", "MIS����");
			fieldMap.put("ITEM_NAME", "EAM����");
			fieldMap.put("ASSETS_DESCRIPTION", "MIS����");
			fieldMap.put("ITEM_SPEC", "EAM�ͺ�");
			fieldMap.put("MODEL_NUMBER", "MIS�ͺ�");
			fieldMap.put("WORKORDER_OBJECT_CODE", "EAM�ص����");
			fieldMap.put("ASSETS_LOCATION_CODE", "MIS�ص����");
			fieldMap.put("WORKORDER_OBJECT_LOCATION", "EAM�ʲ��ص�");
			fieldMap.put("ASSETS_LOCATION", "MIS�ʲ��ص�");
			fieldMap.put("USER_NAME", "EAM������");
			fieldMap.put("ASSIGNED_TO_NAME", "MIS������");
			fieldMap.put("COST_NAME_AMS", "EAM�ɱ�����");
			fieldMap.put("COST_NAME_MIS", "MIS�ɱ�����");
            fieldMap.put("CONTENT_CODE", "EAMĿ¼����");
            fieldMap.put("CONTENT_NAME", "EAMĿ¼����");
            fieldMap.put("FA_CODE", "MISĿ¼����");
            fieldMap.put("FA_CATEGORY2", "MISĿ¼����");
			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle("��ƥ���ʲ��嵥");
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
