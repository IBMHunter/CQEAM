package com.sino.ams.dzyh.dao;


import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EamDhChgLogDTO;
import com.sino.ams.dzyh.model.EamDhChgLogModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.WebFileDownException;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EamDhChgLogDAO</p>
 * <p>Description:�����Զ����ɷ������EamDhChgLogDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Administrator
 * @version 1.0
 */


public class EamDhChgLogDAO extends AMSBaseDAO {


	/**
	 * ���ܣ���ֵ�׺�Ʒ�䶯��ʷ��(EAM) EAM_DH_CHG_LOG ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhChgLogDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamDhChgLogDAO(SfUserDTO userAccount, EamDhChgLogDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		EamDhChgLogDTO dto = (EamDhChgLogDTO)dtoParameter;
		sqlProducer = new EamDhChgLogModel(userAccount, dto);
	}

	/**
	 * ���ܣ���ȡ��ֵ�׺��豸�䶯��־��ʷ
	 * @return DTOSet
	 * @throws QueryException
	 */
	public DTOSet getChgLogHistory() throws QueryException {
		EamDhChgLogModel modelProducer = (EamDhChgLogModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getBarcodeChgHisModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.executeQuery();
		simp.setDTOClassName(EamDhChgLogDTO.class.getName());
		return simp.getDTOSet();
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		EamDhChgLogModel modelProducer = null;
		try {
			modelProducer = (EamDhChgLogModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "��ֵ�׺��豸�䶯��־��ʷ��Ϣ";
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
			throw new WebFileDownException(ex);
		} catch (DataTransException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		}
		return file;
	}

	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("BARCODE", "��ǩ��");
		fieldMap.put("ITEM_CATEGORY_VALUE", "�豸���");
		fieldMap.put("ITEM_NAME", "�豸����");
		fieldMap.put("ITEM_SPEC", "�豸�ͺ�");
		fieldMap.put("SET_CODE", "Ŀ¼�������");
		fieldMap.put("SET_NAME", "Ŀ¼��������");
		fieldMap.put("CATALOG_CODE", "Ŀ¼��Ŵ���");
		fieldMap.put("CATALOG_NAME", "Ŀ¼�������");
		fieldMap.put("CHG_TYPE_VALUE", "�������");
		fieldMap.put("FROM_DEPT_NAME", "���ǰ���β���");
		fieldMap.put("TO_DEPT_NAME", "��������β���");
		fieldMap.put("FROM_LOCATION_CODE", "���ǰ�ص����");
		fieldMap.put("TO_LOCATION_CODE", "�����ص����");
		fieldMap.put("FROM_LOCATION_NAME", "���ǰ�ص�����");
		fieldMap.put("TO_LOCATION_NAME", "�����ص����");
		fieldMap.put("FROM_RESPONSIBILITY_USER_NAME", "���ǰ������");
		fieldMap.put("TO_RESPONSIBILITY_USER_NAME", "�����������");
		fieldMap.put("FROM_MAINTAIN_USER", "���ǰ������");
		fieldMap.put("TO_MAINTAIN_USER", "����󱣹���");
		fieldMap.put("CREATION_DATE", "���ʱ��");
		fieldMap.put("CREATED_USER", "��־������");
		fieldMap.put("REF_NO", "�������");
		return fieldMap;
	}
}
