package com.sino.td.match.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.match.dto.AmsMactPropertyDTO;
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
import com.sino.td.match.model.AmsMatchedTdListModel;

/**
 * Created by IntelliJ IDEA.
 * User: jiangtao
 * Date: 2007-11-21
 * Time: 11:54:53
 * To change this template use File | Settings | File Templates.
 */
public class AmsMatchedTdListDAO extends AMSBaseDAO  {

	/**
	 * ���ܣ����캯����
	 *
	 * @param userAccount  UserAccountDTO �û���Ϣ
	 * @param dtoParameter DTO ���������ݿ⽻��ʱ��Ҫ�Ĳ�����
	 * @param conn         Connection ���ݿ�����
	 */
	public AmsMatchedTdListDAO(SfUserDTO userAccount, AmsMactPropertyDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}
	/**
	* ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	*
	* @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	* @param dtoParameter DTO ���β���������
	*/

	 protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
	   AmsMactPropertyDTO dtoPara = (AmsMactPropertyDTO) dtoParameter;
	   super.sqlProducer = new AmsMatchedTdListModel((SfUserDTO) userAccount, dtoPara);
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
