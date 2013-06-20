package com.sino.nm.ams.mss.dao;

import com.sino.framework.dto.BaseUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.util.CalendarUtil;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.datatrans.*;
import com.sino.base.log.Logger;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.nm.ams.mss.dto.MssAssetsAddDTO;
import com.sino.nm.ams.mss.dto.MssAssetsAddLDTO;
import com.sino.nm.ams.mss.model.MssAssetsAddModel;
import com.sino.nm.ams.mss.model.MssAssetsAddLModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.newasset.dao.AmsAssetsChkLogDAO;
import com.sino.ams.newasset.dto.AmsAssetsChkLogDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: T_yuyao
 * Date: 2011-4-26
 * Time: 11:53:40
 * To change this template use File | Settings | File Templates.
 */
public class MssAssetsAddDAO extends AMSBaseDAO {

	private MssAssetsAddDTO assetsAdd = null;
	private MssAssetsAddModel aModel;

	public MssAssetsAddDAO(SfUserDTO userAccount, MssAssetsAddDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		this.assetsAdd = dtoParameter;
	}

	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		MssAssetsAddDTO dtoPara = (MssAssetsAddDTO) dtoParameter;
		sqlProducer = new MssAssetsAddModel((SfUserDTO) userAccount, dtoPara);
		aModel = (MssAssetsAddModel)sqlProducer;
	}

	/**
	 * ���浥��
	 * @param lineSet ������
	 * @return success
	 * @throws java.sql.SQLException
     */
	public boolean saveData(DTOSet lineSet) throws SQLException {
		boolean success = false;
		boolean autoCommit = true;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String headId = assetsAdd.getHeadId();
			assetsAdd.setCreatedDate(CalendarUtil.getCurrDate());
			OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), "ASSETS");
			assetsAdd.setBillNo(ong.getOrderNum());
			if (headId.equals("")) {
				SeqProducer seq = new SeqProducer(conn);
//				headId = String.valueOf(seq.getStrNextSeq("ETS_ASSETS_ADD_H_S"));
				headId = seq.getGUID();
				assetsAdd.setHeadId(headId);
//				assetsAdd.setStatus("0"); daizb modify 20111109
				assetsAdd.setStatus(0);
				
				createData();
			} else {
//				assetsAdd.setStatus("0"); daizb modify 20111109
				assetsAdd.setStatus(0);
				updateData();
				deleteLines(headId);
			}
			saveLines(lineSet);
			success = true;
		} catch (SQLException e) {
			Logger.logError(e);
		} catch (DataHandleException e) {
			e.printLog();
		} catch (CalendarException e) {
			e.printLog();
		} finally {
			if(success){
				prodMessage("SPARE_SAVE_SUCCESS");
				conn.commit();
			} else {
				prodMessage("SPARE_SAVE_FAILURE");
				conn.rollback();
			}
			conn.setAutoCommit(autoCommit);
		}
		return success;
	}

	/**
	 * �ύ����
	 */
	public boolean submitData(DTOSet lineSet) throws SQLException {
		boolean success = false;
		boolean autoCommit = true;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String headId = assetsAdd.getHeadId();
			assetsAdd.setCreatedDate(CalendarUtil.getCurrDate());
			if (headId.equals("")) {
				OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), "MSS");
				assetsAdd.setBillNo(ong.getOrderNum());
				SeqProducer seq = new SeqProducer(conn);
//				headId = String.valueOf(seq.getStrNextSeq("ETS_MSS_ASSETS_ADD_H_S"));
				headId = seq.getGUID();
				assetsAdd.setHeadId(headId);
//				assetsAdd.setStatus("1"); daizb modify 20111109
				assetsAdd.setStatus(1);
				createData();
			} else {
//				assetsAdd.setStatus("1"); daizb modify 20111109
				assetsAdd.setStatus(1);
				updateData();
				deleteLines(headId);
			}
			saveLines(lineSet);
			saveEIILines(lineSet);
			success = true;
		} catch (SQLException e) {
			Logger.logError(e);
		} catch (DataHandleException e) {
			e.printLog();
		} catch (CalendarException e) {
			e.printLog();
		} finally {
			if(success){
				prodMessage("SPARE_SAVE_SUCCESS");
				conn.commit();
			} else {
				prodMessage("SPARE_SAVE_FAILURE");
				conn.rollback();
			}
			conn.setAutoCommit(autoCommit);
		}
		return success;
	}

	/**
	 * ��������Ϣ�����ݱ�
	 * @param lineSet ������
	 */
	public void saveLines(DTOSet lineSet) throws DataHandleException {
		if (lineSet != null && !lineSet.isEmpty()) {
			MssAssetsAddLDAO lineDAO = new MssAssetsAddLDAO(userAccount, null, conn);
			for (int i = 0; i < lineSet.getSize(); i++) {
				MssAssetsAddLDTO lineData = (MssAssetsAddLDTO) lineSet.getDTO(i);
				lineData.setHeadId(assetsAdd.getHeadId());
				lineDAO.setDTOParameter(lineData);
				lineDAO.createData();
			}
		}
	}

	public void deleteLines(String headId) throws DataHandleException {
		MssAssetsAddLModel model = new MssAssetsAddLModel(userAccount, null);
		DBOperator.updateRecord(model.getDeleteByHeadIdModel(headId), conn);
	}

	//����Ϣд��ETS_ITEM_INFO
	public void saveEIILines(DTOSet lineSet) throws DataHandleException {
		if (lineSet != null && !lineSet.isEmpty()) {
			MssAssetsAddLDAO lineDAO = new MssAssetsAddLDAO(userAccount, null, conn);
			MssAssetsAddLModel model = new MssAssetsAddLModel(userAccount, null);
			AmsAssetsChkLogDAO chkDAO = new AmsAssetsChkLogDAO(userAccount, null, conn);
			AmsAssetsChkLogDTO chkLineDTO = null;
			for (int i = 0; i < lineSet.getSize(); i++) {
				MssAssetsAddLDTO lineData = (MssAssetsAddLDTO) lineSet.getDTO(i);
				lineData.setHeadId(assetsAdd.getHeadId());
				lineDAO.setDTOParameter(lineData);
//				DBOperator.updateRecord(model.getCreateEIIModel(lineData), conn);
				DBOperator.updateRecord(model.getCreateESIModel(lineData), conn);
				chkLineDTO = getChkLineDTO(lineData);
				chkDAO.setDTOParameter(chkLineDTO);
//				chkDAO.saveCheckLogData();
			}
		}
	}

	/**
	 * ���ܣ��������ʲ�����AMS_ASSETS_CHK_LOG���¼��������ƥ���в�ѯ����ȷ�ʲ�
	 * @param lineData AssetsAddLDTO
	 * @return AmsAssetsChkLogDTO
	 */
	private AmsAssetsChkLogDTO getChkLineDTO(MssAssetsAddLDTO lineData){
		AmsAssetsChkLogDTO chkLineDTO = new AmsAssetsChkLogDTO();
		chkLineDTO.setBarcode(lineData.getBarcode());
		chkLineDTO.setChkTimes(0);
		chkLineDTO.setHeaderId(lineData.getHeadId());
		chkLineDTO.setItemCode(lineData.getItemCode());
		chkLineDTO.setItemName(lineData.getItemName1());
		chkLineDTO.setItemSpec(lineData.getItemSpec1());
		chkLineDTO.setResponsibilityDept(lineData.getDeptCode());
		chkLineDTO.setResponsibilityUser(lineData.getEmployeeId());
		chkLineDTO.setLastChkNo(assetsAdd.getBillNo());
		chkLineDTO.setAddressId(lineData.getAddressId());
		chkLineDTO.setOrderType("ASSETS");
		chkLineDTO.setCreatedBy(userAccount.getUserId());
		chkLineDTO.setOrganizationId(userAccount.getOrganizationId());
		return chkLineDTO;
	}

	public File exportAdressFile() throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = aModel.getQueryAdressModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);

			String fileName = "�ʲ��ص�ͳ�Ʊ���.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);

			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("WORKORDER_OBJECT_NAME", "�ص�����");
			fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���");
			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(fileName);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			/*rule.setSheetSize(1000);*/
			//���÷�ҳ��ʾ
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
	 public File exportItmeFile() throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = aModel.getQueryItemModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);

			String fileName = "�ʲ����ƹ��ͳ�Ʊ���.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);

			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("ITEM_NAME", "����");
			fieldMap.put("ITEM_SPEC", "�ͺ�");
			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(fileName);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			/*rule.setSheetSize(1000);*/
			//���÷�ҳ��ʾ
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
	 public File exportDeptFile() throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = aModel.getQueryDeptModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);

			String fileName = "�ʲ����β���ͳ�Ʊ���.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);

			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("DEPT_NAME", "��������");
			fieldMap.put("DEPT_CODE", "���ű��");
			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(fileName);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			/*rule.setSheetSize(1000);*/
			//���÷�ҳ��ʾ
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
	public File exportFile() throws DataTransException { //����ģ��
		File file = null;
		DataTransfer transfer = null;
		SQLModel sqlModel = aModel.getOrderModel();
		TransRule rule = new TransRule();
		rule.setDataSource(sqlModel);
		rule.setCalPattern(CalendarConstant.LINE_PATTERN);
		rule.setSourceConn(conn);

		String fileName = "���ӹ����ʲ�ģ��.xls";

		String filePath = WorldConstant.USER_HOME;
		filePath += WorldConstant.FILE_SEPARATOR;
		filePath += fileName;
		rule.setTarFile(filePath);
		DataRange range = new DataRange();
		rule.setDataRange(range);

		Map fieldMap = new HashMap();
		fieldMap.put("BARCODE", "�ʲ�����");
		fieldMap.put("ITEM_NAME", "�ʲ�����");
		fieldMap.put("ITEM_SPEC", "�ʲ��ͺ�");
		fieldMap.put("ADDRES", "�ص�");
		fieldMap.put("DEPT", "���β���");
		fieldMap.put("USER_NAME", "������");
		fieldMap.put("M_USER", "ʹ����");
		fieldMap.put("REMARK", "��ע");

		rule.setFieldMap(fieldMap);
		CustomTransData custData = new CustomTransData();
//            custData.setReportTitle(fileName);
//            custData.setReportPerson(userAccount.getUsername());
		custData.setNeedReportDate(false);
		rule.setCustData(custData);

		TransferFactory factory = new TransferFactory();
		transfer = factory.getTransfer(rule);
		transfer.transData();
		file = (File) transfer.getTransResult();
		return file;
	}
    public File exportBarcodeFile() throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = aModel.getQueryBarcodeModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);

			String fileName = "�����ʲ�������Ϣ.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);

			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("LINE_COUNT", "�ʲ����");
			fieldMap.put("BARCODE", "�ʲ�����");
			fieldMap.put("ITEM_NAME", "�ʲ�����");
			fieldMap.put("ITEM_SPEC", "����ͺ�");
			fieldMap.put("START_DATE", "��������");
			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(fileName);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			/*rule.setSheetSize(1000);*/
			//���÷�ҳ��ʾ
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