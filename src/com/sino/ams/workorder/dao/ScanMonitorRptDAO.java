package com.sino.ams.workorder.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.ScanMonitorRptModel;
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
 * <p>Description: 中国移动资产实物管理系统</p>
 * <p>Copyright: 北京思诺博信息技术有限公司版权所有Copyright (c) 2007</p>
 * <p>Company: 北京思诺博信息技术有限公司</p>
 * @author 唐明胜
 * @version 1.0
 */
public class ScanMonitorRptDAO extends AMSBaseDAO {

	public ScanMonitorRptDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * 功能：SQL生成器baseSQLProducer的初始化。具体的DAO继承时初始化具体的SQL生成器。
	 *
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 * @todo Implement this com.sino.framework.dao.BaseDAO method
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EtsWorkorderDTO dto = (EtsWorkorderDTO)dtoParameter;
		sqlProducer = new ScanMonitorRptModel((SfUserDTO)userAccount, dto);
	}


	/**
	 * 功能：导出查询SQL资产数据
	 * @return String 返回导出Excel文件
	 * @throws WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		try {
			ScanMonitorRptModel assetsModel = (ScanMonitorRptModel) sqlProducer;
			SQLModel sqlModel = assetsModel.getPageQueryModel();
			String reportTitle = "代维监控报表";
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
		fieldMap.put("MAINTAIN_COMPANY_NAME", "代维公司名称");
		fieldMap.put("RESPONSIBILITY_COUNT", "责任地点数量");
		fieldMap.put("SCAN_OVER_COUNT", "已巡检地点数");
		fieldMap.put("NOT_SCAN_COUNT", "未巡检地点数");
		fieldMap.put("SCAN_OVER_RATE", "巡检完成率");
		return fieldMap;
	}
}
