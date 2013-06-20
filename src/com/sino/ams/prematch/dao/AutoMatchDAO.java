package com.sino.ams.prematch.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import oracle.jdbc.driver.OracleTypes;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.prematch.dto.AmsPaMatchDTO;
import com.sino.ams.prematch.model.AutoMatchModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

public class AutoMatchDAO extends AMSBaseDAO {
	public AutoMatchDAO(SfUserDTO userAccount, AmsPaMatchDTO dtoParameter, Connection conn) {
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
		SfUserDTO user = (SfUserDTO)userAccount;
		AmsPaMatchDTO dto = (AmsPaMatchDTO)dtoParameter;
		sqlProducer = new AutoMatchModel(user, dto);
	}

	/**
	 * ���ܣ�����ת��׼���嵥��Excel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File getExportFile() throws DataTransException {
		File file = null;
		AutoMatchModel modelProducer = (AutoMatchModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getPageQueryModel();
		String reportTitle = userAccount.getCompany() + "���Զ�ƥ���嵥";
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
		return file;
	}

	/**
	 * ���ܣ���ȡת��׼���嵥�����ֶ�ӳ��
	 * @return Map
	 */
	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("BARCODE", "EAM��ǩ");
		fieldMap.put("ITEM_NAME", "EAM����");
		fieldMap.put("ITEM_SPEC", "EAM�ͺ�");
		fieldMap.put("PROJECT_NUMBER_AMS", "EAM��Ŀ���");
		fieldMap.put("PROJECT_NAME_AMS", "EAM��Ŀ����");
		fieldMap.put("WORKORDER_OBJECT_CODE", "EAM�ص����");
		fieldMap.put("WORKORDER_OBJECT_NAME", "EAM�ص�����");
		fieldMap.put("EMPLOYEE_NUMBER", "EAMԱ�����");
		fieldMap.put("USER_NAME", "EAM������");
		fieldMap.put("START_DATE", "EAM��������");

		fieldMap.put("TAG_NUMBER", "PA��ǩ");
		fieldMap.put("ASSETS_DESCRIPTION", "PA����");
		fieldMap.put("MODEL_NUMBER", "PA�ͺ�");
		fieldMap.put("PROJECT_NUMBER", "PA��Ŀ���");
		fieldMap.put("PROJECT_NAME", "PA��Ŀ����");
		fieldMap.put("ASSETS_LOCATION_CODE", "PA�ص����");
		fieldMap.put("ASSETS_LOCATION", "PA�ص�����");
		fieldMap.put("ASSIGNED_TO_NUMBER", "PAԱ�����");
		fieldMap.put("ASSIGNED_TO_NAME", "PA������");
		fieldMap.put("DATE_PLACED_IN_SERVICE", "PA��������");

		return fieldMap;
	}

	/**
	 * ���ܣ��Զ�ƥ��(��ѡƥ��)
	 * @param systemIds String[]
	 * @param tagNumbers String[]
	 * @throws DataHandleException
	 */
	public void matchItems(String[] systemIds, String[] tagNumbers) throws DataHandleException {
		boolean operateResult = false;
		boolean autoCommit = true;
		CallableStatement cStmt = null;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			AmsPaMatchDAO matchDAO = new AmsPaMatchDAO(userAccount, null, conn);
			AmsPaMatchDTO dto = null;
			cStmt = conn.prepareCall("{CALL AMS_PA_IMPORT_PKG.SYN_PA_DATA(?, ?, ?)}");
			for (int i = 0; i < systemIds.length; i++) {
				dto = new AmsPaMatchDTO();
				dto.setSystemId(systemIds[i]);
				dto.setTagNumber(tagNumbers[i]);
				dto.setRemark("�Զ�ƥ��");
				setDTOParameter(dto);
				if(StrUtil.isEmpty(systemIds[i])){
					importPaAssets(cStmt);
					dto = (AmsPaMatchDTO) dtoParameter;
				}
				matchDAO.setDTOParameter(dto);
				matchDAO.createData();
			}
			operateResult = true;
		} catch (Exception ex) {
			Logger.logError(ex);
		} finally{
			try {
				if (operateResult) {
					prodMessage(CustMessageKey.MATCH_SUCCESS);
					conn.commit();
				} else {
					conn.rollback();
					prodMessage(CustMessageKey.MATCH_FAILURE);
				}
				conn.setAutoCommit(autoCommit);
				DBManager.closeDBStatement(cStmt);
				message.setIsError(!operateResult);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
			}
		}
	}

	/**
	 * ���ܣ���PA�ʲ���ETS_ITEM_INFO��
	 * @param cStmt CallableStatement
	 * @throws SQLException
	 */
	private void importPaAssets(CallableStatement cStmt) throws SQLException{
		AmsPaMatchDTO dto = (AmsPaMatchDTO) dtoParameter;
		cStmt.registerOutParameter(1, OracleTypes.NUMBER);
		cStmt.setString(2, dto.getTagNumber());
		cStmt.setInt(3, userAccount.getUserId());
		cStmt.execute();
		dto.setSystemId(StrUtil.nullToString(cStmt.getString(1)));
		setDTOParameter(dto);
	}
}
