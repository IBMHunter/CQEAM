package com.sino.ams.workorder.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.GroupScanModel;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: �й��ƶ��ʲ�ʵ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class GroupScanDAO extends AMSBaseDAO {
	public GroupScanDAO(SfUserDTO userAccount, EtsWorkorderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 *
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 * @todo Implement this com.sino.framework.dao.BaseDAO method
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EtsWorkorderDTO dto = (EtsWorkorderDTO)dtoParameter;
		sqlProducer = new GroupScanModel((SfUserDTO)userAccount, dto);
	}

	/**
	 * ���ܣ���ȡ����Ѳ��ص���ϸ
	 * @return RowSet
	 * @throws QueryException
	 */
	public RowSet getScanedLocations() throws QueryException {
		RowSet rows = new RowSet();
		try {
			GroupScanModel modelProducer = (GroupScanModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getLocationsModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			rows = simp.getSearchResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return rows;
	}

	/**
	 * ���ܣ���ȡ��һ��ɨ��ص�
	 * @return EtsWorkorderDTO
	 * @throws QueryException
	 */
	public EtsWorkorderDTO getFirstScanLocation() throws QueryException {
		EtsWorkorderDTO firstDTO = new EtsWorkorderDTO();
		try {
			GroupScanModel modelProducer = (GroupScanModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getLocationsModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.setDTOClassName(EtsWorkorderDTO.class.getName());
			simp.executeQuery();
			if(simp.hasResult()){
				firstDTO = (EtsWorkorderDTO)simp.getFirstDTO();
			}
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return firstDTO;
	}

	/**
	 * ���ܣ���ȡ����Ѳ���豸��ϸ
	 * @return RowSet
	 * @throws QueryException
	 */
	public RowSet getScanedItems() throws QueryException {
		RowSet rows = new RowSet();
		try {
			GroupScanModel modelProducer = (GroupScanModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getItemsModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			rows = simp.getSearchResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return rows;
	}

	/**
	 * ���ܣ���ȡ����Ѳ���豸��ϸExcel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			EtsWorkorderDTO dto = (EtsWorkorderDTO)dtoParameter;
			GroupScanModel modelProducer = (GroupScanModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getItemsModel();
			String reportTitle = "�ص�"
								 + dto.getWorkorderObjectLocation()
								 + "ɨ���豸";
			String fileName = reportTitle + ".xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			Map fieldMap = new HashMap();
			fieldMap.put("BARCODE", "��ǩ��");
			fieldMap.put("ITEM_CATEGORY", "�豸רҵ");
			fieldMap.put("ITEM_NAME", "�豸����");
			fieldMap.put("ITEM_SPEC", "�豸�ͺ�");
			fieldMap.put("ITEM_UNIT", "������λ");
			fieldMap.put("WORKORDER_OBJECT_LOCATION", "��ǰ�ص�");
			fieldMap.put("USER_NAME", "������");
			fieldMap.put("DEPT_NAME", "���β���");
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
}
