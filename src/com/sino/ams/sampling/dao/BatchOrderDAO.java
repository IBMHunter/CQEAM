package com.sino.ams.sampling.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.sampling.model.BatchOrderModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.Row;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class BatchOrderDAO extends AMSBaseDAO {

	public BatchOrderDAO(SfUserDTO userAccount, AmsAssetsSamplingHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO)dtoParameter;
		sqlProducer = new BatchOrderModel(user, dto);
	}


	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		BatchOrderModel modelProducer = null;
		try {
			modelProducer = (BatchOrderModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "��鹤��";
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
		fieldMap.put("ORDER_NO", "�������");
		fieldMap.put("SAMPLING_LOCATION_NAME", "���ص�");
		fieldMap.put("CREATION_DATE", "������������");
		fieldMap.put("IMPLEMENT_USER", "����ִ����");
		fieldMap.put("IMPLEMENT_DAYS", "����ִ������");
		fieldMap.put("ORDER_STATUS_VALUE", "����״̬");
		fieldMap.put("SAMPLEDED_OU_NAME", "ִ�й�˾");
		fieldMap.put("TASK_NO", "������");
		fieldMap.put("TASK_NAME", "��������");
		fieldMap.put("TASK_CREATION_DATE", "���񴴽�����");
		fieldMap.put("START_DATE", "������ʼ����");
		fieldMap.put("END_DATE", "�����ֹ����");
		fieldMap.put("CREATED_OU_NAME", "���񴴽���˾");
		fieldMap.put("TASK_CREATED_USER", "���񴴽���");
		return fieldMap;
	}

	/**
	 * ���ܣ���ȡ���񴴽���˾������ִ�й�˾��Ϣ(��������ʱʹ��)
	 * @throws QueryException
	 */
	public void appendOuInfo() throws QueryException {
		try {
			AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO) dtoParameter;
			if(dto.getCreatedOu() != -1){
				return;
			}
			BatchOrderModel modelProducer = (BatchOrderModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getOrgInfoModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				Row row = simp.getFirstRow();
				dto.setSampledOu(StrUtil.strToInt(row.getStrValue("SAMPLED_OU")));
				dto.setSampledOuName(row.getStrValue("SAMPLED_OU_NAME"));
				dto.setCreatedOu(Integer.parseInt(row.getStrValue("CREATED_OU")));
				dto.setCreatedOuName(row.getStrValue("CREATED_OU_NAME"));
				setDTOParameter(dto);
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
	}
}
