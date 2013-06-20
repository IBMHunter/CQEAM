package com.sino.ams.newasset.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.bean.TransferRoadValidator;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO;
import com.sino.ams.newasset.model.ArchiveHeaderModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class ArchiveHeaderDAO extends AMSBaseDAO {
	public ArchiveHeaderDAO(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount  BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsCheckHeaderDTO dtoPara = (AmsAssetsCheckHeaderDTO) dtoParameter;
		sqlProducer = new ArchiveHeaderModel((SfUserDTO) userAccount, dtoPara);
	}

	/**
	 * ���ܣ��鵵�̵㹤��
	 * @param chkLines DTOSet
	 * @return boolean
	 */
	public boolean archiveChkOrder(DTOSet chkLines) {
		boolean operateResult = false;
		boolean hasPreviousOrder = false;
		boolean autoCommit = true;
		AmsAssetsCheckHeaderDTO headerDTO = (AmsAssetsCheckHeaderDTO) dtoParameter;
		try {
			AmsAssetsCheckHeaderDAO orderDAO = new AmsAssetsCheckHeaderDAO(userAccount, headerDTO, conn);
			orderDAO.setDTOClassName(AmsAssetsCheckHeaderDTO.class.getName());
			AmsAssetsCheckHeaderDTO srcDTO = (AmsAssetsCheckHeaderDTO) orderDAO.getDataByPrimaryKey();
			TransferRoadValidator validator = new TransferRoadValidator();
			if (!validator.canExecuteAction(srcDTO, headerDTO)) {
				prodMessage(AssetsMessageKeys.INVALID_OPERATE);
				message.addParameterValue(srcDTO.getStatusName());
				message.addParameterValue(headerDTO.getStatusName());
				message.setIsError(true);
//			} else if (hasPreviousOrder()) {
//				hasPreviousOrder = true;
			} else {
				autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				ArchiveHeaderModel modelProducer = (ArchiveHeaderModel) sqlProducer;
				SQLModel sqlModel = modelProducer.getChkOrderArchiveModel(); //����״̬���Ϊ�鵵
				DBOperator.updateRecord(sqlModel, conn);
//				sqlModel = modelProducer.getArcNotScanBarcodesModel();//�ݲ����Ǹ����
//				DBOperator.updateRecord(sqlModel, conn);
				if (chkLines != null && !chkLines.isEmpty()) {
					ArchiveLineDAO chkLineDAO = new ArchiveLineDAO(userAccount, null, conn);
					chkLineDAO.setServletConfig(servletConfig);
					chkLineDAO.setOrderItems(orderDAO.getOrderBarcodes(false)); //���ô��̵��豸
					chkLineDAO.setLocItems(getLocBarcodes()); //���ñ��̵�ص������е��豸
					chkLineDAO.setOrderHeader(headerDTO);
					AmsAssetsCheckLineDTO chkLineDTO = null;
					for (int i = 0; i < chkLines.getSize(); i++) {
						chkLineDTO = (AmsAssetsCheckLineDTO) chkLines.getDTO(i);
						chkLineDTO.setHeaderId(headerDTO.getHeaderId());
						chkLineDAO.setDTOParameter(chkLineDTO);
						chkLineDAO.archiveChkLine();
					}
				}
				operateResult = true;
			}
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (QueryException ex) {
			ex.printLog();
		} finally {
			try {
				if (operateResult) {
					conn.commit();
					prodMessage(AssetsMessageKeys.ARCH_ORDER_SUCCESS);
				} else {
					conn.rollback();
					if (hasPreviousOrder) {
						prodMessage(AssetsMessageKeys.HAS_PREVIOUS_ORDER);
						message.addParameterValue(headerDTO.getObjectLocation());
					} else if (message == null ||
							StrUtil.isEmpty(message.getMessageValue())) {
						prodMessage(AssetsMessageKeys.ARCH_ORDER_FAILURE);
					}
				}
				conn.setAutoCommit(autoCommit);
				message.setIsError(!operateResult);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
			}
		}
		return operateResult;
	}
	
	/**
	 * �˻��̵㹤��
	 * @throws DataHandleException
	 */
	public void getBackOrder() throws DataHandleException {
		ArchiveHeaderModel modelProducer = (ArchiveHeaderModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getUpdateBackOrderModel();
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * ���ܣ��жϴ��鵵�������ڵص��Ƿ�������δ�鵵����
	 * @return boolean
	 * @throws QueryException
	 */
	private boolean hasPreviousOrder() throws QueryException {
		ArchiveHeaderModel modelProducer = (ArchiveHeaderModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getHasPreviousOrderModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.executeQuery();
		return simp.hasResult();
	}

	/**
	 * ���ܣ���ϵͳ�д��ڵ�PDA�ύ���豸�в����ڵ�����ȫ���鵵����;��(�ݲ����Ǹ����)
	 * @throws DataHandleException
	 */
	private void arcNotScanedBarcodes() throws DataHandleException {
		ArchiveHeaderModel modelProducer = (ArchiveHeaderModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getArcNotScanBarcodesModel();
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * ���ܣ���������ͷ��Ϣ������Ϣ��Excel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File exportArchiveData() throws DataTransException {
		File file = null;
		try {
			setDTOClassName(AmsAssetsCheckHeaderDTO.class.getName());
			setCalPattern(LINE_PATTERN);
			AmsAssetsCheckHeaderDTO orderHeader = (AmsAssetsCheckHeaderDTO)getDataByPrimaryKey();
			AmsAssetsCheckLineDTO checkLine = new AmsAssetsCheckLineDTO();
			checkLine.setHeaderId(orderHeader.getHeaderId());
			AmsAssetsCheckLineDAO lineDAO = new AmsAssetsCheckLineDAO(userAccount, checkLine, conn);
			lineDAO.setCalPattern(LINE_PATTERN);
			lineDAO.setDTOClassName(AmsAssetsCheckLineDTO.class.getName());
			DTOSet chkLines = (DTOSet) lineDAO.getDataByForeignKey("headerId");
			HSSFWorkbook wb = exportOrderHeader(orderHeader);
			wb = exportLineTitle(wb);
			wb = exportLineData(wb, chkLines);
			String fileName = "����" + orderHeader.getTransNo() + "�̵���Ϣ.xls";
			String filePath = com.sino.config.SinoConfig.getExportHOME();//WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			file = new File(filePath);
			file.createNewFile();
			OutputStream out = new FileOutputStream(file);
			wb.write(out);
			out.close();
		} catch (FileNotFoundException ex) {
			Logger.logError(ex);
			throw new DataTransException(ex);
		} catch (IOException ex) {
			Logger.logError(ex);
			throw new DataTransException(ex);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		} catch (ReflectException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}

	/**
	 * ���ܣ������̵㹤��ͷ����
	 * @param orderHeader AmsAssetsCheckHeaderDTO
	 * @return HSSFWorkbook
	 * @throws CalendarException
	 */
	private HSSFWorkbook exportOrderHeader(AmsAssetsCheckHeaderDTO orderHeader) throws
			CalendarException {
		HSSFWorkbook wb = new HSSFWorkbook();
		String sheetName = "����" + orderHeader.getTransNo() + "�̵���Ϣ";
		String dataTitle = "����" + orderHeader.getTransNo() + "ϵͳ�������̵����ݶԱ���Ϣ";
		HSSFSheet sheet = wb.createSheet(sheetName);

		HSSFCellStyle csField = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setFontName("Courier New");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		csField.setFont(font);
		csField.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		csField.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		csField.setBorderTop(HSSFCellStyle.BORDER_THIN);
		csField.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		csField.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		csField.setBorderRight(HSSFCellStyle.BORDER_THIN);

		int rowIndex = 0;
		HSSFRow xlsRow = null;
		HSSFRichTextString cellValue = null;
		HSSFCell xlsCell = null;
		for (; rowIndex < 3; rowIndex++) {
			xlsRow = sheet.createRow(rowIndex);
			for (short j = 0; j < 13; j++) {
				xlsCell = xlsRow.createCell(j);
				xlsCell.setCellStyle(csField);
			}
		}
		Region region = new Region(0, (short) 0, 2, (short) 12);
		sheet.addMergedRegion(region); //ָ���ϲ�����
		xlsRow = sheet.getRow(0);
		xlsCell = xlsRow.getCell((short) 0);
		xlsCell.setCellValue(new HSSFRichTextString(dataTitle));
		xlsRow = sheet.createRow(rowIndex++);
		HSSFCellStyle csValue = wb.createCellStyle();
		csValue.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		csValue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		csValue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		csValue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		csValue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		csValue.setBorderRight(HSSFCellStyle.BORDER_THIN);

		short cellIndex = 0;

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("�̵㵥�ţ�");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getTransNo());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("����������");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getTaskDesc());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("�̵㲿�ţ�");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getCheckDeptName());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("�̵�ص㣺");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getObjectLocation());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("��ʼ���ڣ�");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getStartTime().
				getCalendarValue());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("ִ������(��)��");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(String.valueOf(orderHeader.getImplementDays()));
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell((short) 12);
		xlsCell.setCellStyle(csField);
		cellValue = new HSSFRichTextString("----");
		xlsCell.setCellValue(cellValue);

		xlsRow = sheet.createRow(rowIndex++);
		cellIndex = 0;

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("ִ���ˣ�");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getImplementUser());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("����״̬��");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getStatusName());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("����ʱ�䣺");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getCreationDate().
				getCalendarValue());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("�����ˣ�");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getCreatedUser());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("�·�ʱ�䣺");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getDistributeDate().
				getCalendarValue());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("�·��ˣ�");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getDistributeUser());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell((short) 12);
		xlsCell.setCellStyle(csField);
		cellValue = new HSSFRichTextString("----");
		xlsCell.setCellValue(cellValue);

		xlsRow = sheet.createRow(rowIndex++);
		cellIndex = 0;
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("����ʱ�䣺");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getDownloadDate().
				getCalendarValue());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("�����ˣ�");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getDownloadUser());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("�ϴ�ʱ�䣺");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getUploadDate().
				getCalendarValue());
		xlsCell.setCellValue(cellValue);

		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csField);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString("�ϴ��ˣ�");
		xlsCell.setCellValue(cellValue);
		xlsCell = xlsRow.createCell(cellIndex++);
		xlsCell.setCellStyle(csValue);
		xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
		cellValue = new HSSFRichTextString(orderHeader.getUploadUser());
		xlsCell.setCellValue(cellValue);
		for (short i = cellIndex; i < 13; i++) {
			xlsCell = xlsRow.createCell((short) i);
			xlsCell.setCellStyle(csField);
			cellValue = new HSSFRichTextString("----");
			xlsCell.setCellValue(cellValue);
		}
		return wb;
	}

	/**
	 * ���ܣ������̵㹤��������
	 * @param wb HSSFWorkbook
	 * @return HSSFWorkbook
	 */
	private HSSFWorkbook exportLineTitle(HSSFWorkbook wb) {
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowIndex = 6;
		HSSFCellStyle csField = wb.createCellStyle();
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setFontName("Courier New");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		csField.setFont(font);
		csField.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		csField.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		csField.setBorderTop(HSSFCellStyle.BORDER_THIN);
		csField.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		csField.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		csField.setBorderRight(HSSFCellStyle.BORDER_THIN);
		HSSFRow xlsRow = sheet.createRow(rowIndex++);
		HSSFCell xlsCell = null;
		HSSFRichTextString cellValue = null;
		for (short i = 0; i < 13; i++) {
			xlsCell = xlsRow.createCell(i);
			xlsCell.setCellStyle(csField);
		}
		Region region = new Region(6, (short) 0, 6, (short) 12);
		sheet.addMergedRegion(region); //ָ���ϲ�����
		xlsRow = sheet.getRow(6);
		xlsCell = xlsRow.getCell((short) 0);
		xlsCell.setCellValue(new HSSFRichTextString("�̵㹤��������"));

		xlsRow = sheet.createRow(rowIndex++);
		for (short i = 0; i < 13; i++) {
			xlsCell = xlsRow.createCell(i);
			xlsCell.setCellStyle(csField);
		}
		xlsRow = sheet.createRow(rowIndex++);
		String[] fieldDesc = {"", "רҵ", "����", "�ͺ�", "������", "���β���", "רҵ", "����",
				"�ͺ�", "������", "���β���", "ϵͳ", "ɨ��"};
		for (short i = 0; i < 13; i++) {
			xlsCell = xlsRow.createCell(i);
			xlsCell.setCellStyle(csField);
			cellValue = new HSSFRichTextString(fieldDesc[i]);
			xlsCell.setCellValue(cellValue);
		}
		region = new Region(7, (short) 0, 8, (short) 0);
		sheet.addMergedRegion(region); //ָ���ϲ�����
		region = new Region(7, (short) 1, 7, (short) 5);
		sheet.addMergedRegion(region); //ָ���ϲ�����
		region = new Region(7, (short) 6, 7, (short) 10);
		sheet.addMergedRegion(region); //ָ���ϲ�����
		region = new Region(7, (short) 11, 7, (short) 12);
		sheet.addMergedRegion(region); //ָ���ϲ�����

		xlsRow = sheet.getRow(7);
		cellValue = new HSSFRichTextString("��ǩ��");
		xlsCell = xlsRow.getCell((short) 0);
		xlsCell.setCellValue(cellValue);
		cellValue = new HSSFRichTextString("ϵͳ����");
		xlsCell = xlsRow.getCell((short) 1);
		xlsCell.setCellValue(cellValue);
		cellValue = new HSSFRichTextString("ɨ������");
		xlsCell = xlsRow.getCell((short) 6);
		xlsCell.setCellValue(cellValue);
		cellValue = new HSSFRichTextString("״̬");
		xlsCell = xlsRow.getCell((short) 11);
		xlsCell.setCellValue(cellValue);
		return wb;
	}

	/**
	 * ���ܣ������̵㹤��������
	 * @param wb       HSSFWorkbook
	 * @param chkLines DTOSet
	 * @return HSSFWorkbook
	 * @throws ReflectException
	 */
	private HSSFWorkbook exportLineData(HSSFWorkbook wb, DTOSet chkLines) throws
			ReflectException {
		int rowIndex = 9;
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowCount = chkLines.getSize();
		HSSFCellStyle csValue = wb.createCellStyle();
		csValue.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		csValue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		csValue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		csValue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		csValue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		csValue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		AmsAssetsCheckLineDTO chkLineDTO = null;
		HSSFRow xlsRow = null;
		HSSFCell xlsCell = null;
		String fieldValue = "";
		HSSFRichTextString cellValue = null;
		String[] fields = {"barcode", "itemCategoryName", "itemName",
				"itemSpec", "responsibilityUserName",
				"responsibilityDeptName", "scanItemCategoryName",
				"scanItemName", "scanItemSpec",
				"scanResponsibilityUserName",
				"scanResponsibilityDeptName", "systemStatusName",
				"scanStatusName"};
		for (int i = rowIndex; i < rowCount + rowIndex; i++) {
			chkLineDTO = (AmsAssetsCheckLineDTO) chkLines.getDTO(i - rowIndex);
			xlsRow = sheet.createRow(i);
			for (short j = 0; j < fields.length; j++) {
				xlsCell = xlsRow.createCell(j);
				fieldValue = String.valueOf(ReflectionUtil.getProperty(chkLineDTO, fields[j]));
				cellValue = new HSSFRichTextString(fieldValue);
				xlsCell.setCellStyle(csValue);
				xlsCell.setCellValue(cellValue);
			}
		}
		return wb;
	}

	/**
	 * ���ܣ���ȡ�̵�ص��µı�ǩ��(���ڹ鵵ʱ)
	 * @return List
	 * @throws QueryException
	 */
	public DTOSet getLocBarcodes() throws QueryException {
		ArchiveHeaderModel modelProducer = (ArchiveHeaderModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getLocBarcodesModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(AmsAssetsCheckLineDTO.class.getName());
		simp.executeQuery();
		return simp.getDTOSet();
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		try {
			ArchiveHeaderModel modelProducer = (ArchiveHeaderModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "���鵵�̵㹤��";
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
		fieldMap.put("BATCH_NO", "��������");
		fieldMap.put("TASK_DESC", "��������");
		fieldMap.put("TRANS_NO", "�������");
		fieldMap.put("GROUPNAME", "�������");
		fieldMap.put("CREATED_USER", "�µ���");
		fieldMap.put("CREATION_DATE", "��������");
		fieldMap.put("START_TIME", "��ʼ����");
		fieldMap.put("IMPLEMENT_DAYS", "��������");
		fieldMap.put("IMPLEMENT_USER", "ִ����");
		fieldMap.put("LOCATION_CODE", "�ص����");
		fieldMap.put("CHECK_LOCATION", "����λ��");
		fieldMap.put("CHECK_CATEGORY_NAME", "ɨ���豸רҵ");
		fieldMap.put("COMPANY_NAME", "��˾����");
		fieldMap.put("ORDER_STATUS", "����״̬");
		return fieldMap;
	}
}
