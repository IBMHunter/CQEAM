package com.sino.ams.sampling.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingHeaderDTO;
import com.sino.ams.sampling.report.model.AssetsSamplingReportModel2;
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
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-10-10
 * Time: 13:50:48
 * To change this template use File | Settings | File Templates.
 */
public class AssetsSamplingReportDAO2 extends AMSBaseDAO {

	public AssetsSamplingReportDAO2(SfUserDTO userAccount, AmsAssetsSamplingHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		AmsAssetsSamplingHeaderDTO dto = (AmsAssetsSamplingHeaderDTO) dtoParameter;
		sqlProducer = new AssetsSamplingReportModel2(user, dto);
	}

	public File getExportFile() throws DataTransException {
		File file = null;
		try {
			AssetsSamplingReportModel2 modelProducer = (AssetsSamplingReportModel2)sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "��鹤����ر���(������)";
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
			rule.setFieldMap(getFieldMap());
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
		fieldMap.put("COMPANY", "��˾����");
		fieldMap.put("TASK_NO", "������");
		fieldMap.put("TASK_NAME", "��������");
		fieldMap.put("CREATION_DATE", "��������");
		fieldMap.put("END_DATE", "Ҫ���������");
		fieldMap.put("SAMPLING_RATIO", "���ٷֱ�");
		fieldMap.put("REQ_COUNT", "Ҫ��������");
        fieldMap.put("SCAN_COUNT", "ʵ���������");
        fieldMap.put("FINISH_RATE", "�����ɰٷֱ�");
        fieldMap.put("IDENTICAL_COUNT", "��ʵ�����");
        fieldMap.put("IDENTICAL_RATE", "��ʵ�����");
        return fieldMap;
	}
}
