package com.sino.ams.system.object.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.EtsFaAssetsDTO;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.object.model.AssetsNoMatchResponsibleModel;
import com.sino.ams.system.object.model.CommonObjectModel;
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
import com.sino.base.exception.WebFileDownException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsNoMatchResponsibleDAO extends AMSBaseDAO {
	public AssetsNoMatchResponsibleDAO(BaseUserDTO userAccount, EtsFaAssetsDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		model = new AssetsNoMatchResponsibleModel((SfUserDTO) userAccount, dtoParameter);
	}


	private AssetsNoMatchResponsibleModel model = null;

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		EtsFaAssetsDTO dto = (EtsFaAssetsDTO)dtoParameter;
		sqlProducer = new AssetsNoMatchResponsibleModel(user, dto);
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		try {
			AssetsNoMatchResponsibleModel modelProducer = (AssetsNoMatchResponsibleModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "�������벿�Ų����ѯ";
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
		} catch (DataTransException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		}
		return file;
	}


	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("COMPANY", "��˾����");
		fieldMap.put("RESPONSIBILITY_DEPT", "�ʲ��������β���");
		fieldMap.put("BARCODE", "��ǩ��");
		fieldMap.put("ITEM_NAME", "�ʲ�����");
		fieldMap.put("ITEM_SPEC", "����ͺ�");
		fieldMap.put("USER_NAME", "������");
		fieldMap.put("DEPT_NAME", "��������������");

		fieldMap.put("CONTENT_CODE", "�ʲ�Ŀ¼����");
		fieldMap.put("CONTENT_NAME", "�ʲ�Ŀ¼����");
		fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���");
		fieldMap.put("WORKORDER_OBJECT_NAME", "�ص�����");
		return fieldMap;
	}


}
