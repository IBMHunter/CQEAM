package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.model.AATConfirmYNModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: EtsFaAssetsDAO</p>
 * <p>Description:�����Զ����ɷ������EtsFaAssetsDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 */

public class AATConfirmYNDAO extends AMSBaseDAO {

	private String confirmYN;

	/**
	 * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsTransLineDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AATConfirmYNDAO(SfUserDTO userAccount,
						   AmsAssetsTransLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsTransLineDTO dtoPara = (AmsAssetsTransLineDTO) dtoParameter;
		super.sqlProducer = new AATConfirmYNModel((SfUserDTO) userAccount, dtoPara, confirmYN);
	}

	/**
	 * ���ܣ������ʲ���ȷ�ϡ�
	 * @param confirmAssets DTOSet
	 * @return boolean
	 */
	public boolean confirmAssets(DTOSet confirmAssets) {
		boolean operateResult = false;
		AssetsConfirmDAO confirmDAO = null;
		try {
			confirmDAO = new AssetsConfirmDAO(userAccount, null, conn);
			operateResult = confirmDAO.confirmAssets(confirmAssets);
		} finally {
			if (confirmDAO != null) {
				message = confirmDAO.getMessage();
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ�����ѡ����ʲ���������
	 * @param barcodes String[]
	 * @return String ���ص���Excel�ļ�
	 * @throws DataTransException
	 */
	public File exportCheckedAssets(String[] barcodes) throws
			DataTransException {
		File file = null;
		try {
			AATConfirmYNModel modelProducer = (AATConfirmYNModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getExpCheckedAssetsModel(barcodes);
			file = getExportFile(sqlModel);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws DataTransException
	 */
	public File exportQueryAssets(String confirmYN) throws DataTransException {
		File file = null;
		try {
			AATConfirmYNModel modelProducer = (AATConfirmYNModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel2(confirmYN);
			file = getExportFile(sqlModel);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}

	/**
	 * ���ܣ�����SQL�����ļ�
	 * @param sqlModel SQLModel
	 * @return File
	 * @throws DataTransException
	 */
	private File getExportFile(SQLModel sqlModel) throws DataTransException {
		File file = null;
		String reportTitle = "�����ʲ��Ƿ�ȷ��";
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
		Map fieldMap = getConfirmAssetsMap();
		rule.setFieldMap(fieldMap);
		CustomTransData custData = new CustomTransData();
		custData.setReportTitle(reportTitle);
		custData.setReportPerson(userAccount.getUsername());
		custData.setNeedReportDate(true);
		rule.setCustData(custData);
//		rule.setSheetSize(2000);
		rule.setCalPattern(LINE_PATTERN);
		TransferFactory factory = new TransferFactory();
		DataTransfer transfer = factory.getTransfer(rule);
		transfer.transData();
		file = (File) transfer.getTransResult();
		return file;
	}

	/**
	 * ���ܣ���ȡ��ȷ���ʲ��ĵ�����ͷ
	 * @return Map
	 */
	private static Map getConfirmAssetsMap() {
		Map fieldMap = new HashMap();
		fieldMap.put("TRANS_NO", "��������");
		fieldMap.put("BARCODE", "�ʲ���ǩ");
		fieldMap.put("ASSET_NUMBER", "�ʲ����");
		fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
		fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
		fieldMap.put("COST", "�ʲ�ԭֵ");
		fieldMap.put("DEPRN_COST", "�ۼ��۾�");
		fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
		fieldMap.put("CURRENT_UNITS", "����");
		fieldMap.put("OLD_LOCATION_NAME", "ԭ�ص�");
		fieldMap.put("OLD_RESPONSIBILITY_USER_NAME", "ԭ������");
		fieldMap.put("OLD_RESPONSIBILITY_DEPT_NAME", "ԭ���β���");
		fieldMap.put("ASSIGNED_TO_LOCATION_NAME", "����ص�");
		fieldMap.put("RESPONSIBILITY_USER_NAME", "��������");
		fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���벿��");
		fieldMap.put("CONFIRM_YN", "ȷ��״̬");
		fieldMap.put("REMARK", "ժҪ");
		return fieldMap;
	}
}
