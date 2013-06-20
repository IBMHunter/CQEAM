package com.sino.ams.synchronize.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.synchronize.dto.AmsSynTmpDTO;
import com.sino.ams.synchronize.dto.EamSyschronizeDTO;
import com.sino.ams.synchronize.model.AssetsCommitModel;
import com.sino.ams.synchronize.model.RetiredAssetsModel;
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
import com.sino.base.exception.DataTransException;
import com.sino.base.log.Logger;
import com.sino.base.util.ArrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-3-18
 * Time: 13:18:36
 * To change this template use File | Settings | File Templates.
 */
public class AssetsCommitDAO extends AMSBaseDAO {


	/**
	 * ���ܣ�eAM�����ص�ͬ�� ���ݷ��ʲ㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemMatchDTO ���β���������
	 * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AssetsCommitDAO(SfUserDTO userAccount, EamSyschronizeDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EamSyschronizeDTO dto = (EamSyschronizeDTO) dtoParameter;
		SfUserDTO user = (SfUserDTO) userAccount;
		super.sqlProducer = new AssetsCommitModel(user, dto);
	}

	/**
	 * ���ܣ��ʲ�����ͬ��
	 * @param systemIds String[]
	 */
	public synchronized void syschronizeAssets(String[] systemIds) {
		boolean operateResult = false;
		boolean autoCommit = true;
		CallableStatement cs = null;
		try {
			int assetsCount = systemIds.length;
			String sourceStr = ArrUtil.arrToSqlStr(systemIds);
			String targetStr = "";
			AmsSynTmpDAO synTmpDAO = new AmsSynTmpDAO(userAccount, null, conn);
			autoCommit = conn.getAutoCommit();
			for(int i = 0; i < assetsCount; i++){
				targetStr = systemIds[i];
				AmsSynTmpDTO assetsDTO = new AmsSynTmpDTO();
				assetsDTO.setSourceStr(sourceStr);
				assetsDTO.setTargetStr(targetStr);
				synTmpDAO.setDTOParameter(assetsDTO);
				synTmpDAO.createData();
			}
			EamSyschronizeDTO dto = (EamSyschronizeDTO) dtoParameter;
			String transferType = dto.getTransferType();
			String callStr = "";
			callStr = "{CALL AMS_SYN_PKG.SYN_CANNIBALIZE_RESULT(?, ?, ?)}";
			cs = conn.prepareCall(callStr);
			int i = 1;
			cs.setInt(i++, userAccount.getOrganizationId());
			cs.setInt(i++, userAccount.getUserId());
			cs.setString(i, transferType);
			cs.execute();
			synTmpDAO.deleteData();
			operateResult = true;
		} catch (Exception ex) {
			Logger.logError(ex);
		} finally {
			try {
				if (operateResult) {
					conn.commit();
					prodMessage("SUBMIT_SUCCESS");
				} else {
					conn.rollback();
					prodMessage("SUBMIT_FAILURE");
					getMessage().setIsError(true);
				}
				conn.setAutoCommit(autoCommit);
				DBManager.closeDBStatement(cs);
			} catch (SQLException ex) {
				Logger.logError(ex);
			}
		}
	}

	/**
	 * ͬ�������ʲ�
	 * @param systemId String
	 */
	public void syschronizeRetiredAssets(String systemId) {
		CallableStatement cs = null;
		String callStr = "{CALL AMS_SYN_PKG.SYN_ASSETS_RETIRED_RESULT(?, ?, ?, ?)}";
		try {
			cs = conn.prepareCall(callStr);
			cs.setInt(1, userAccount.getOrganizationId());
			cs.setString(2, systemId);
			cs.registerOutParameter(3, Types.NUMERIC);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.closeDBStatement(cs);
		}
	}


	public File getExportFile() throws DataTransException {
		AssetsCommitModel modelProducer = (AssetsCommitModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getPageQueryModel();
		String reportTitle = "EAM�ʲ������������";
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
		rule.setFieldMap(getFieldMap());
		CustomTransData custData = new CustomTransData();
		custData.setReportTitle(reportTitle);
		custData.setReportPerson(userAccount.getUsername());
		custData.setNeedReportDate(true);
		rule.setCustData(custData);
		rule.setCalPattern(CAL_PATT_50);
		TransferFactory factory = new TransferFactory();
		DataTransfer transfer = factory.getTransfer(rule);
		transfer.transData();
		return (File) transfer.getTransResult();
	}

	private Map getFieldMap() {
		Map fieldMap = new HashMap();
		fieldMap.put("TRANSFER_TYPE", "������ʽ");
		fieldMap.put("TRANS_NO", "��������");
		fieldMap.put("NEW_BARCODE", "�ʲ���ǩ");
		fieldMap.put("ASSET_NUMBER", "�ʲ����");
		fieldMap.put("NEW_ITEM_NAME", "�ʲ�����");
		fieldMap.put("NEW_ITEM_SPEC", "�ʲ��ͺ�");
		fieldMap.put("OLD_ASSETS_LOCATION", "ԭ�ʲ��ص�");
		fieldMap.put("NEW_ASSETS_LOCATION", "���ʲ��ص�");
		fieldMap.put("OLD_DEPT_NAME", "���β���");
		fieldMap.put("OLD_USER_NAME", "ԭ������");
		fieldMap.put("NEW_USER_NAME", "��������");
		return fieldMap;
	}

	/**
	 * �������Excel
	 * @return File
	 * @throws DataTransException
	 */
	public File getRetiredExportFile() throws DataTransException {
		RetiredAssetsModel modelProducer = new RetiredAssetsModel(userAccount, (EamSyschronizeDTO) dtoParameter);
		SQLModel sqlModel = modelProducer.getPageQueryModel();
		String reportTitle = "EAM�ʲ����Ͻ������";
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
		rule.setFieldMap(getRetireFieldMap());
		CustomTransData custData = new CustomTransData();
		custData.setReportTitle(reportTitle);
		custData.setReportPerson(userAccount.getUsername());
		custData.setNeedReportDate(true);
		rule.setCustData(custData);
		rule.setCalPattern(CAL_PATT_50);
		TransferFactory factory = new TransferFactory();
		DataTransfer transfer = factory.getTransfer(rule);
		transfer.transData();
		return (File) transfer.getTransResult();
	}

	private Map getRetireFieldMap() {
		Map fieldMap = new HashMap();

		fieldMap.put("TRANS_NO", "��������");
		fieldMap.put("NEW_BARCODE", "�ʲ���ǩ��");
		fieldMap.put("ASSET_NUMBER", "�ʲ����");
		fieldMap.put("NEW_ITEM_NAME", "�ʲ�����");
		fieldMap.put("NEW_ITEM_SPEC", "�ʲ��ͺ�");
		fieldMap.put("OLD_ASSETS_LOCATION", "�ʲ��ص�");
		fieldMap.put("OLD_DEPT_NAME", "���β���");
		fieldMap.put("OLD_USER_NAME", "������");
		fieldMap.put("COST", "�ʲ��ɱ�");
		fieldMap.put("DEPRN_RESERVE", "�ۼ��۾�");
		fieldMap.put("SCRAP_VALUE", "��ֵ");
		fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
		fieldMap.put("LIFE_IN_YEARS", "�۾�����");
		fieldMap.put("REMAIN_MONTHS", "ʣ���·�");

		return fieldMap;
	}
}
