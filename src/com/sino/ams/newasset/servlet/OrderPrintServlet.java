package com.sino.ams.newasset.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import com.f1j.ss.BookModel;
import com.f1j.util.F1Exception;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.bean.AssetsRadioProducer;
import com.sino.ams.newasset.constant.AssetsActionConstant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsURLList;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dao.AmsAssetsCheckLineDAO;
import com.sino.ams.newasset.dao.ApproveContentDAO;
import com.sino.ams.newasset.dao.ArchiveHeaderDAO;
import com.sino.ams.newasset.dao.OrderHeaderPrintDAO;
import com.sino.ams.newasset.dao.OrderLinePrintDAO;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.model.OrderHeaderPrintModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.db.QueryConstant;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.RowSet;
import com.sino.base.dto.DTOSet;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.exception.StrException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.framework.dao.PageQueryDAO;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.framework.sql.BaseSQLProducer;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class OrderPrintServlet extends BaseServlet {
	 private SfUserDTO user = null;
	 private Connection conn = null;
	 ServletOutputStream out = null;
	 String showMsg = "";
	 protected com.f1j.ss.BookModelImpl book = null;
	/**
	 * ���е�Servlet������ʵ�ֵķ�����
	 * @param req HttpServletRequest
	 * @param res HttpServletResponse
	 * @throws ServletException
	 * @throws IOException
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res) throws
			ServletException, IOException {
		String forwardURL = "";
		Message message = SessionUtil.getMessage(req);
		Connection conn = null;
		try {
			SfUserDTO user = (SfUserDTO) getUserAccount(req);
			Request2DTO req2DTO = new Request2DTO();
			req2DTO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
			AmsAssetsTransHeaderDTO dto = (AmsAssetsTransHeaderDTO) req2DTO.getDTO(req);
			dto.setServletConfig(getServletConfig(req));
			String action = dto.getAct();
			dto.setCalPattern(LINE_PATTERN);
			conn = getDBConnection(req);
			String transType = dto.getTransType();
			if (transType.equals(AssetsDictConstant.ASS_RED)) {
				AssetsOptProducer optProducer = new AssetsOptProducer(user,conn);
				String option = optProducer.getTransferOption(dto.getTransferType());
				dto.setTransferTypeOption(option);
				AssetsRadioProducer radioPrd = new AssetsRadioProducer(user);
				req.setAttribute(AssetsWebAttributes.PRINT_RADIO,radioPrd.getOrderPrintRadio(dto.getPrintType()));
			}
			if (action.equals("")) {
				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = AssetsURLList.ORDER_PRINT_QUERY;
			} else if (action.equals(AssetsActionConstant.QUERY_ACTION)) {
				BaseSQLProducer sqlProducer = new OrderHeaderPrintModel(user, dto);
				PageQueryDAO pageDAO = new PageQueryDAO(req, conn, sqlProducer);
				pageDAO.setCalPattern(LINE_PATTERN);
				pageDAO.produceWebData();
				req.setAttribute(QueryConstant.QUERY_DTO, dto);
				forwardURL = AssetsURLList.ORDER_PRINT_QUERY;
			} else if (action.equals(AssetsActionConstant.DETAIL_ACTION)) { //������ϸ��Ϣҳ�棬ҳ���ṩ��ӡ����
				OrderHeaderPrintDAO printDAO = new OrderHeaderPrintDAO(user, dto, conn);
				printDAO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
				AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO)printDAO.getDataByPrimaryKey();
				if (headerDTO == null) {
					message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
					message.setIsError(true);
				} else {
					headerDTO.setPrintType(dto.getPrintType());
					AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
					lineDTO.setTransId(headerDTO.getTransId());
					lineDTO.setTransType(headerDTO.getTransType());
					OrderLinePrintDAO lineDAO = new OrderLinePrintDAO(user, lineDTO, conn);
					lineDAO.setCalPattern(LINE_PATTERN);
					lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
//					lineDAO.setTransType(headerDTO.getTransType());
					lineDAO.setPrintType(headerDTO.getPrintType());
					
					DTOSet ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
					req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
					

					String transId = headerDTO.getTransId();
					String tableName = "AMS_ASSETS_TRANS_HEADER";
					
					RowSet rows = null;
					rows = ApproveContentDAO.getApproveContent(conn, transId, tableName);
					req.setAttribute(AssetsWebAttributes.APPROVE_CONTENT, rows);
				}
				headerDTO.setCalPattern(LINE_PATTERN);
				req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
				forwardURL = AssetsURLList.PRINT_DETAIL_PAGE;
			} else if (action.equals(AssetsActionConstant.EXPORT_ACTION)) { //��˾��������� //����
				
				OrderHeaderPrintDAO printDAO = new OrderHeaderPrintDAO(user, dto, conn);
				printDAO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
				AmsAssetsTransHeaderDTO headerDTO = (AmsAssetsTransHeaderDTO)printDAO.getDataByPrimaryKey();
				DTOSet ds = null;
				if (headerDTO == null) {
					message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
					message.setIsError(true);
				} else {
					
					headerDTO.setPrintType(dto.getPrintType());
					AmsAssetsTransLineDTO lineDTO = new AmsAssetsTransLineDTO();
					lineDTO.setTransId(headerDTO.getTransId());
					lineDTO.setTransType(headerDTO.getTransType());
					OrderLinePrintDAO lineDAO = new OrderLinePrintDAO(user, lineDTO, conn);
					lineDAO.setCalPattern(LINE_PATTERN);
					lineDAO.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
					lineDAO.setPrintType(headerDTO.getPrintType());
					
					ds = (DTOSet) lineDAO.getDataByForeignKey("transId");
					req.setAttribute(AssetsWebAttributes.ORDER_LINE_DATA, ds);
					String transId = headerDTO.getTransId();
					String tableName = "AMS_ASSETS_TRANS_HEADER";
					RowSet rows = null;
					rows = ApproveContentDAO.getApproveContent(conn, transId, tableName);
					req.setAttribute(AssetsWebAttributes.APPROVE_CONTENT, rows);
				}
				headerDTO.setCalPattern(LINE_PATTERN);
				req.setAttribute(AssetsWebAttributes.ORDER_HEAD_DATA, headerDTO);
				//forwardURL = AssetsURLList.PRINT_DETAIL_PAGE;
				////////////////
				File file;
				try {
					file = exportPrintData(headerDTO, ds);
					WebFileDownload fileDown = new WebFileDownload(req, res);
					fileDown.setFilePath(file.getAbsolutePath());
				    fileDown.download();
					file.delete();
				} catch (DataTransException e) {
					e.printStackTrace();
				}catch (WebFileDownException e) {
					e.printStackTrace();
				}
				////////////////
				///---
			    /*res.setContentType("application/vnd.ms-excel");
			    String reportTitle="";
		
			    	
			    if(headerDTO.getTransType().equals(AssetsDictConstant.ASS_RED)
			     &&headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_BTW_COMP) ){//��˾�����
			    	reportTitle="�̶��ʲ������嵥(��˾��)";
			    }else if(!headerDTO.getTransType().equals(AssetsDictConstant.ASS_SUB )
			    	  && !headerDTO.getTransType().equals(AssetsDictConstant.ASS_FREE)
			    	  && !headerDTO.getTransType().equals(AssetsDictConstant.ASS_RED)){//���ϻ���	
			    	reportTitle="�̶��ʲ������嵥";
			    }
                String header = "attachment; filename=" + new String((reportTitle+".xls").getBytes(), "iso8859-1");
                res.setHeader("Content-Disposition", header);
                out = res.getOutputStream(); 
                boolean success = doExport(headerDTO, ds);
                if (success) {
                    out.flush();
                    out.close();
                } else {
                	forwardURL = AssetsURLList.PRINT_DETAIL_PAGE;
                }
                req.setAttribute("showMsg", showMsg);*/
				///---
			} else {
				message = getMessage(AssetsMessageKeys.INVALID_REQ);
				message.setIsError(true);
				forwardURL = MessageConstant.MSG_PRC_SERVLET;
			}
		} catch (StrException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.QUERY_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (PoolPassivateException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.POOL_PASSIVATE_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DTOException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.DTO_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (QueryException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.QUERY_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			forwarder.forwardView(forwardURL);
		}
	}
	
	/**
	 * ���ܣ���������ͷ��Ϣ������Ϣ��Excel�ļ�
	 * @return File
	 * @throws DataTransException
	 */
	public File exportPrintData(AmsAssetsTransHeaderDTO headerDTO, DTOSet lineSet) throws DataTransException {
		File file = null;
		try {
			HSSFWorkbook wb = null;
			String reportTitle="";
			if(headerDTO.getTransType().equals(AssetsDictConstant.ASS_RED)
			 &&headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_BTW_COMP) ){//��˾�����
				reportTitle="�̶��ʲ������嵥(��˾��)";
			}else if(!headerDTO.getTransType().equals(AssetsDictConstant.ASS_SUB )
				  && !headerDTO.getTransType().equals(AssetsDictConstant.ASS_FREE)
				  && !headerDTO.getTransType().equals(AssetsDictConstant.ASS_RED)){//���ϻ���	
				reportTitle="�̶��ʲ������嵥";
			}
			wb = getPrintData(headerDTO, lineSet,reportTitle);
			Date rightNow = new Date();    
	        DateFormat format1 = new SimpleDateFormat("yyyyMMddhhmmss"); 
			String fileName = reportTitle+format1.format(rightNow)+".xls";
			
			String filePath = WorldConstant.USER_HOME;
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
		} 
		return file;
	}

	/**
	 * ���ܣ���˾����������� �̶��ʲ������嵥(��˾��)
	 */
	private HSSFWorkbook getPrintData(AmsAssetsTransHeaderDTO headerDTO, DTOSet lineSet,String reportTitle) throws
			CalendarException {
		
		HSSFWorkbook wb = new HSSFWorkbook();
		String sheetName = headerDTO.getTransNo();
		String dataTitle = reportTitle;
		HSSFSheet sheet = wb.createSheet(sheetName);
        //��ɫ
		HSSFFont font = wb.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setFontName("Courier New");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//��ɫ ͷ ���ж��� �ޱ���
		HSSFCellStyle csFieldT = wb.createCellStyle();
		csFieldT.setFont(font);
		csFieldT.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		csFieldT.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		csFieldT.setBorderTop(HSSFCellStyle.BORDER_NONE);
		csFieldT.setBorderBottom(HSSFCellStyle.BORDER_NONE);
		csFieldT.setBorderLeft(HSSFCellStyle.BORDER_NONE);
		csFieldT.setBorderRight(HSSFCellStyle.BORDER_NONE);
		//��ɫ ��ͷ ���ж��� �б���
		HSSFCellStyle csField = wb.createCellStyle();
		csField.setFont(font);
		csField.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		csField.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		csField.setBorderTop(HSSFCellStyle.BORDER_THIN);
		csField.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		csField.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		csField.setBorderRight(HSSFCellStyle.BORDER_THIN);
		//��ɫ ֵ ������� �ޱ���
		HSSFCellStyle csValue0 = wb.createCellStyle();
		csValue0.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		csValue0.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		csValue0.setBorderTop(HSSFCellStyle.BORDER_NONE);
		csValue0.setBorderBottom(HSSFCellStyle.BORDER_NONE);
		csValue0.setBorderLeft(HSSFCellStyle.BORDER_NONE);
		csValue0.setBorderRight(HSSFCellStyle.BORDER_NONE);
		//��ɫ ֵ ���ж��� �б���
		HSSFCellStyle csValue = wb.createCellStyle();
		csValue.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		csValue.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		csValue.setBorderTop(HSSFCellStyle.BORDER_THIN);
		csValue.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		csValue.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		csValue.setBorderRight(HSSFCellStyle.BORDER_THIN);
		//��ɫ ֵ ������� �б���
		HSSFCellStyle csValueL = wb.createCellStyle();
		csValueL.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		csValueL.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		csValueL.setBorderTop(HSSFCellStyle.BORDER_THIN);
		csValueL.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		csValueL.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		csValueL.setBorderRight(HSSFCellStyle.BORDER_THIN);
		//��ɫ ֵ ���Ҷ��� �б���
		HSSFCellStyle csValueR = wb.createCellStyle();
		csValueR.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
		csValueR.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		csValueR.setBorderTop(HSSFCellStyle.BORDER_THIN);
		csValueR.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		csValueR.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		csValueR.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		Calendar c = Calendar.getInstance();
    	int month = c.get(Calendar.MONTH) + 1;
     	String curDate = c.get(Calendar.YEAR) + "��" + month + "��";
     	
		float TOTAL_COST = 0;
		float TOTAL_DEPRECIATION = 0;
		float TOTAL_NET_ASSET_VALUE = 0;
		float TOTAL_IMPAIR_RESERVE = 0;
		float TOTAL_DEPRN_COST = 0;
		float TOTAL_SCRAP_VALUE = 0;
		
		int rowIndex = 0;
		HSSFRow xlsRow = null;
		HSSFRichTextString cellValue = null;
		HSSFCell xlsCell = null;
		int colspan=0;//�п��
		
		if(headerDTO.getTransType().equals(AssetsDictConstant.ASS_RED)
			     &&headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_BTW_COMP) ){//��˾�����	
			//�����
			colspan=15;//�п��
			for (; rowIndex < 3; rowIndex++) {
				xlsRow = sheet.createRow(rowIndex);
				for (short j = 0; j < colspan; j++) {
					xlsCell = xlsRow.createCell(j);
					xlsCell.setCellStyle(csFieldT);
				}
			}
			Region region = new Region(0, (short) 0, 2, (short) (colspan-1));
			sheet.addMergedRegion(region); //ָ���ϲ�����
			xlsRow = sheet.getRow(0);
			xlsCell = xlsRow.getCell((short) 0);
			xlsCell.setCellValue(new HSSFRichTextString(dataTitle));
			
			//��ͷ��ע
			xlsRow = sheet.createRow(rowIndex++);
			xlsCell = xlsRow.createCell((short)0);
			xlsCell.setCellStyle(csValue0);
			xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellValue = new HSSFRichTextString("������λ��"+headerDTO.getFromCompanyName());
			xlsCell.setCellValue(cellValue);
			region = new Region(xlsRow.getRowNum(), (short) 0, xlsRow.getRowNum(), (short) 2);
			sheet.addMergedRegion(region); //ָ���ϲ�����
			
	        xlsCell = xlsRow.createCell((short)3);
			xlsCell.setCellStyle(csValue0);
			xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellValue = new HSSFRichTextString("���뵥λ��"+headerDTO.getToCompanyName());
			xlsCell.setCellValue(cellValue);
			region = new Region(xlsRow.getRowNum(), (short) 3, xlsRow.getRowNum(), (short) 6);
			sheet.addMergedRegion(region); //ָ���ϲ�����
			
			xlsCell = xlsRow.createCell((short)7);
			xlsCell.setCellStyle(csValue0);
			xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellValue = new HSSFRichTextString("�������ţ�"+headerDTO.getTransNo());
			xlsCell.setCellValue(cellValue);
			region = new Region(xlsRow.getRowNum(), (short) 7, xlsRow.getRowNum(), (short) 10);
			sheet.addMergedRegion(region); //ָ���ϲ�����
				
			xlsCell = xlsRow.createCell((short)11);
			xlsCell.setCellStyle(csValue0);
			xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellValue = new HSSFRichTextString("����ڣ�"+curDate);
			xlsCell.setCellValue(cellValue);
			region = new Region(xlsRow.getRowNum(), (short) 11, xlsRow.getRowNum(), (short) 13);
			sheet.addMergedRegion(region); //ָ���ϲ�����
			
			xlsCell = xlsRow.createCell((short)(colspan-1));
			xlsCell.setCellStyle(csValue0);
			xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellValue = new HSSFRichTextString("(��λ��Ԫ)");
			xlsCell.setCellValue(cellValue);
			//��ͷ
			xlsRow = sheet.createRow(rowIndex++);
			String[] fieldDesc = {"���", "ԭ�ʲ����", "ԭ�ʲ���ǩ", "�ʲ�����", "����ͺ�",
					              "��λ", "����", "��������","�۾�����(��)", "����Ŀ��",
					              "ԭֵ", "�ۼ��۾�", "��ֵ׼��","����","��ֵ"};
			for (short i = 0; i < colspan; i++) {
				xlsCell = xlsRow.createCell(i);
				xlsCell.setCellStyle(csValue);
				cellValue = new HSSFRichTextString(fieldDesc[i]);
				xlsCell.setCellValue(cellValue);
			}
			//������
	        int j=0;
	        AmsAssetsTransLineDTO lineDTO = null; 
	        for (int i = 0; i < lineSet.getSize(); i++) {
				lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				TOTAL_COST += lineDTO.getCost();
				TOTAL_DEPRECIATION += lineDTO.getDepreciation();
				
    			if (!lineDTO.getNetAssetValue().trim().equals("")) {
    				TOTAL_NET_ASSET_VALUE += Float.parseFloat(lineDTO.getNetAssetValue());
    			} else {
    				TOTAL_NET_ASSET_VALUE += Float.parseFloat("0");
    			}
				
				TOTAL_IMPAIR_RESERVE += lineDTO.getImpairReserve();
				TOTAL_DEPRN_COST += lineDTO.getDeprnCost();
				TOTAL_SCRAP_VALUE += Float.parseFloat(lineDTO.getScrapValue());
				j=i+1;
				xlsRow = sheet.createRow(rowIndex++);
				
				xlsCell = xlsRow.createCell((short) 0);
				xlsCell.setCellStyle(csValue);
				cellValue = new HSSFRichTextString(j+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 1);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getAssetNumber());
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 2);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getBarcode());
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 3);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getAssetsDescription());
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 4);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getModelNumber());
				xlsCell.setCellValue(cellValue);
				
				xlsCell = xlsRow.createCell((short) 5);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getUnitOfMeasure());
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 6);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getCurrentUnits()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 7);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getDatePlacedInService()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 8);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getLifeInYears()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 9);
				xlsCell.setCellStyle(csValueL);
				
				cellValue = new HSSFRichTextString(lineDTO.getContentCode());
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 10);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getCost()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 11);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getDepreciation()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 12);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getImpairReserve()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 13);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getDeprnCost()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 14);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getScrapValue());
				xlsCell.setCellValue(cellValue);
	        }	
	        //�ϼ���
	        xlsRow = sheet.createRow(rowIndex++);
	    	xlsCell = xlsRow.createCell((short) 0);
			xlsCell.setCellStyle(csValueL);
			cellValue = new HSSFRichTextString("��  ��");
			xlsCell.setCellValue(cellValue);
			for(int n=1;n<=9;n++){
				xlsCell = xlsRow.createCell((short) n);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString("");
				xlsCell.setCellValue(cellValue);
			}
			region = new Region(xlsRow.getRowNum(), (short) 0, xlsRow.getRowNum(), (short) 9);
			sheet.addMergedRegion(region); //ָ���ϲ�����
			
			xlsCell = xlsRow.createCell((short) 10);
			xlsCell.setCellStyle(csValueR);
			cellValue = new HSSFRichTextString(TOTAL_COST+"");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 11);
			xlsCell.setCellStyle(csValueR);
			cellValue = new HSSFRichTextString(TOTAL_DEPRECIATION+"");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 12);
			xlsCell.setCellStyle(csValueR);
			cellValue = new HSSFRichTextString(TOTAL_IMPAIR_RESERVE+"");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 13);
			xlsCell.setCellStyle(csValueR);
			cellValue = new HSSFRichTextString(TOTAL_DEPRN_COST+"");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 14);
			xlsCell.setCellStyle(csValueR);
			cellValue = new HSSFRichTextString(TOTAL_SCRAP_VALUE+"");
			xlsCell.setCellValue(cellValue);
			//��β��ע
			rowIndex = rowIndex+4;
			xlsRow = sheet.createRow(rowIndex);
	        
			xlsCell = xlsRow.createCell((short) 2);
			xlsCell.setCellStyle(csValue0);
			cellValue = new HSSFRichTextString("������λ��������ǩ�֣�");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 8);
			xlsCell.setCellStyle(csValue0);
			cellValue = new HSSFRichTextString("���뵥λ��������ǩ�֣�");
			xlsCell.setCellValue(cellValue);
			rowIndex = rowIndex+3;
			xlsRow = sheet.createRow(rowIndex);
			xlsCell = xlsRow.createCell((short) 2);
			xlsCell.setCellStyle(csValue0);
			cellValue = new HSSFRichTextString("������λרҵ����ǩ�֣�");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 8);
			xlsCell.setCellStyle(csValue0);
			cellValue = new HSSFRichTextString("���뵥λרҵ����ǩ�֣�");
			xlsCell.setCellValue(cellValue);
			rowIndex = rowIndex+5;
			xlsRow = sheet.createRow(rowIndex);
			xlsCell = xlsRow.createCell((short) 2);
			xlsCell.setCellStyle(csValue0);
			cellValue = new HSSFRichTextString("������λ������ǩ�£�");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 8);
			xlsCell.setCellStyle(csValue0);
			cellValue = new HSSFRichTextString("���뵥λ������ǩ�£�");
			xlsCell.setCellValue(cellValue);
			rowIndex = rowIndex+5;
			xlsRow = sheet.createRow(rowIndex);
			xlsCell = xlsRow.createCell((short) 2);
			xlsCell.setCellStyle(csValue0);
			cellValue = new HSSFRichTextString("������λ���£�");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 8);
			xlsCell.setCellStyle(csValue0);
			cellValue = new HSSFRichTextString("���뵥λ���£�");
			xlsCell.setCellValue(cellValue);
		}else if(!headerDTO.getTransType().equals(AssetsDictConstant.ASS_SUB )
			      && !headerDTO.getTransType().equals(AssetsDictConstant.ASS_FREE)
			      && !headerDTO.getTransType().equals(AssetsDictConstant.ASS_RED)){//���ϻ���	
			//�����
			colspan=19;//�п��
			for (; rowIndex < 3; rowIndex++) {
				xlsRow = sheet.createRow(rowIndex);
				for (short j = 0; j < colspan; j++) {
					xlsCell = xlsRow.createCell(j);
					xlsCell.setCellStyle(csFieldT);
				}
			}
			Region region = new Region(0, (short) 0, 2, (short) (colspan-1));
			sheet.addMergedRegion(region); //ָ���ϲ�����
			xlsRow = sheet.getRow(0);
			xlsCell = xlsRow.getCell((short) 0);
			xlsCell.setCellValue(new HSSFRichTextString(dataTitle));
			//����� ����·�
			xlsRow = sheet.createRow(rowIndex++);
			xlsCell = xlsRow.createCell((short)0);
			xlsCell.setCellStyle(csFieldT);
			xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellValue = new HSSFRichTextString(curDate);
			xlsCell.setCellValue(cellValue);
			region = new Region(xlsRow.getRowNum(), (short) 0, xlsRow.getRowNum(), (short) (colspan-1));
			sheet.addMergedRegion(region); //ָ���ϲ�����
			xlsRow = sheet.createRow(rowIndex++);//��һ��
			region = new Region(xlsRow.getRowNum(), (short) 0, xlsRow.getRowNum(), (short) (colspan-1));
			sheet.addMergedRegion(region); //ָ���ϲ�����
			//��ͷ��ע
			xlsRow = sheet.createRow(rowIndex++);
			xlsCell = xlsRow.createCell((short)0);
			xlsCell.setCellStyle(csValue0);
			xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellValue = new HSSFRichTextString("��λ���ƣ�"+headerDTO.getFromCompanyName());
			xlsCell.setCellValue(cellValue);
			region = new Region(xlsRow.getRowNum(), (short) 0, xlsRow.getRowNum(), (short) 2);
			sheet.addMergedRegion(region); //ָ���ϲ�����
			
			xlsCell = xlsRow.createCell((short)3);
			xlsCell.setCellStyle(csValue0);
			xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellValue = new HSSFRichTextString("���ϵ��ţ�"+headerDTO.getTransNo());
			xlsCell.setCellValue(cellValue);
			region = new Region(xlsRow.getRowNum(), (short) 3, xlsRow.getRowNum(), (short) 6);
			sheet.addMergedRegion(region); //ָ���ϲ�����
			
			xlsCell = xlsRow.createCell((short)(colspan-1));
			xlsCell.setCellStyle(csValue0);
			xlsCell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cellValue = new HSSFRichTextString("(��λ��Ԫ)");
			xlsCell.setCellValue(cellValue);
	        
			//��ͷ
			xlsRow = sheet.createRow(rowIndex++);
			String[] fieldDesc = {"���", "��Ԫ���", "�ʲ����", "�ʲ���ǩ", "�ʲ�����",
					              "����Ŀ��", "�ʲ����ĩ������", "��Ӧ��","����ʽ", "��������",
					              "ʣ���۾�����(��)", "������ð汾", "������ϰ汾","�ʲ�ԭֵ","�ۼ��۾�",
					              "�ʲ���ֵ","��ֵ׼��","�ʲ�����","��ע"};
			for (short i = 0; i < colspan; i++) {
				xlsCell = xlsRow.createCell(i);
				xlsCell.setCellStyle(csValue);
				cellValue = new HSSFRichTextString(fieldDesc[i]);
				xlsCell.setCellValue(cellValue);
			}
			//������
	        int j=0;
	        AmsAssetsTransLineDTO lineDTO = null; 
	        for (int i = 0; i < lineSet.getSize(); i++) {
	        	lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
				TOTAL_COST += lineDTO.getCost();
    			TOTAL_DEPRECIATION += lineDTO.getDepreciation();
    			if (!lineDTO.getNetAssetValue().trim().equals("")) {
    				TOTAL_NET_ASSET_VALUE += Float.parseFloat(lineDTO.getNetAssetValue());
    			} else {
    				TOTAL_NET_ASSET_VALUE += Float.parseFloat("0");
    			}
    			
    			TOTAL_IMPAIR_RESERVE += lineDTO.getImpairReserve();
    			TOTAL_DEPRN_COST += lineDTO.getDeprnCost();
    			j=i+1;
    			
				xlsRow = sheet.createRow(rowIndex++);
				
				xlsCell = xlsRow.createCell((short) 0);
				xlsCell.setCellStyle(csValue);
				cellValue = new HSSFRichTextString(j+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 1);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getNetUnit());
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 2);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getAssetNumber());
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 3);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getBarcode());
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 4);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getAssetsDescription());
				xlsCell.setCellValue(cellValue);
		      
				xlsCell = xlsRow.createCell((short) 5);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getContentCode());
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 6);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getContentName()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 7);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getManufacturerName()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 8);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getModelNumber()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 9);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getDatePlacedInService()+"");
				xlsCell.setCellValue(cellValue);
				
				xlsCell = xlsRow.createCell((short) 10);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getLifeInYears()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 11);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getSoftInuseVersion()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 12);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getSoftDevalueVersion()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 13);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getCost()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 14);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getDepreciation()+"");
				xlsCell.setCellValue(cellValue);
				
				xlsCell = xlsRow.createCell((short) 15);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getNetAssetValue()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 16);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getImpairReserve()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 17);
				xlsCell.setCellStyle(csValueR);
				cellValue = new HSSFRichTextString(lineDTO.getDeprnCost()+"");
				xlsCell.setCellValue(cellValue);
				xlsCell = xlsRow.createCell((short) 18);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString(lineDTO.getRemark()+"");
				xlsCell.setCellValue(cellValue);
			  
	        }	
	       
	        //�ϼ���
	        xlsRow = sheet.createRow(rowIndex++);
	    	xlsCell = xlsRow.createCell((short) 0);
			xlsCell.setCellStyle(csValueL);
			cellValue = new HSSFRichTextString("��  ��");
			xlsCell.setCellValue(cellValue);
			for(int n=1;n<=13;n++){
				xlsCell = xlsRow.createCell((short) n);
				xlsCell.setCellStyle(csValueL);
				cellValue = new HSSFRichTextString("");
				xlsCell.setCellValue(cellValue);
			}
			region = new Region(xlsRow.getRowNum(), (short) 0, xlsRow.getRowNum(), (short) 12);
			sheet.addMergedRegion(region); //ָ���ϲ�����
			
			xlsCell = xlsRow.createCell((short) 13);
			xlsCell.setCellStyle(csValueR);
			cellValue = new HSSFRichTextString(TOTAL_COST+"");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 14);
			xlsCell.setCellStyle(csValueR);
			cellValue = new HSSFRichTextString(TOTAL_DEPRECIATION+"");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 15);
			xlsCell.setCellStyle(csValueR);
			cellValue = new HSSFRichTextString(TOTAL_NET_ASSET_VALUE+"");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 16);
			xlsCell.setCellStyle(csValueR);
			cellValue = new HSSFRichTextString(TOTAL_IMPAIR_RESERVE+"");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 17);
			xlsCell.setCellStyle(csValueR);
			cellValue = new HSSFRichTextString(TOTAL_DEPRN_COST+"");
			xlsCell.setCellValue(cellValue);
			xlsCell = xlsRow.createCell((short) 18);
			xlsCell.setCellStyle(csValueL);
			cellValue = new HSSFRichTextString("");
			xlsCell.setCellValue(cellValue);
		}
		return wb;
	}
	
	/**
     * ������ǩ�б���Excel
     */
    private boolean doExport(AmsAssetsTransHeaderDTO headerDTO, DTOSet lineSet) {
        boolean success = false;
        book = new com.f1j.ss.BookModelImpl();
        
        float TOTAL_COST = 0;
        float TOTAL_DEPRECIATION = 0;
        float TOTAL_NET_ASSET_VALUE = 0;
        float TOTAL_IMPAIR_RESERVE = 0;
        float TOTAL_DEPRN_COST = 0;
        float TOTAL_SCRAP_VALUE = 0;
        
    	Calendar c = Calendar.getInstance();
    	int month = c.get(Calendar.MONTH) + 1;
     	String curDate = c.get(Calendar.YEAR) + "��" + month + "��";
     	
        try {
			book.initWorkbook();
			book.getLock();
			book.setBorder(false);
			
			String reportTitle="";
			int r=0;
			if(headerDTO.getTransType().equals(AssetsDictConstant.ASS_RED)
		     &&headerDTO.getTransferType().equals(AssetsDictConstant.TRANS_BTW_COMP) ){//��˾�����
			    reportTitle="�̶��ʲ������嵥(��˾��)";
				book.setText(0, 7, reportTitle);
				book.setText(2, 1, "������λ��");
		        book.setText(2, 2, headerDTO.getFromCompanyName());
		        book.setText(2, 4, "���뵥λ��");
		        book.setText(2, 5, headerDTO.getToCompanyName());
		        book.setText(2, 8, "�������ţ�");
		        book.setText(2, 9, headerDTO.getTransNo());
		        book.setText(2, 11, "����ڣ�");
		        book.setText(2, 12, curDate);
		        book.setText(2, 14, "(��λ��Ԫ)");
		        
		        
		        book.setText(3, 0, "���");
		        book.setText(3, 1, "ԭ�ʲ����");
		        book.setText(3, 2, "ԭ�ʲ���ǩ");
		        book.setText(3, 3, "�ʲ�����");
		        book.setText(3, 4, "����ͺ�");
		        
		        book.setText(3, 5, "��λ");
		        book.setText(3, 6, "����");
		        book.setText(3, 7, "��������");
		        book.setText(3, 8, "�۾�����(��)");
		        book.setText(3, 9, "����Ŀ��");
		        
		        book.setText(3, 10, "ԭֵ");
		        book.setText(3, 11, "�ۼ��۾�");
		        book.setText(3, 12, "��ֵ׼��");
		        book.setText(3, 13, "����");
		        book.setText(3, 14, "��ֵ");
		        r=4;
		        int j=0;
		        AmsAssetsTransLineDTO lineDTO = null; 
		        for (int i = 0; i < lineSet.getSize(); i++) {
					lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
					TOTAL_COST += lineDTO.getCost();
	    			TOTAL_DEPRECIATION += lineDTO.getDepreciation();
	    			
	    			if (!lineDTO.getNetAssetValue().trim().equals("")) {
	    				TOTAL_NET_ASSET_VALUE += Float.parseFloat(lineDTO.getNetAssetValue());
	    			} else {
	    				TOTAL_NET_ASSET_VALUE += Float.parseFloat("0");
	    			}
	    			
	    			TOTAL_IMPAIR_RESERVE += lineDTO.getImpairReserve();
	    			TOTAL_DEPRN_COST += lineDTO.getDeprnCost();
	    			TOTAL_SCRAP_VALUE += Float.parseFloat(lineDTO.getScrapValue());
	    			j=i+1;
	    			book.setText(r, 0, j+"");
			        book.setText(r, 1, lineDTO.getAssetNumber());
			        book.setText(r, 2, lineDTO.getBarcode());
			        book.setText(r, 3, lineDTO.getAssetsDescription());
			        book.setText(r, 4, lineDTO.getModelNumber());
			        
			        book.setText(r, 5, lineDTO.getUnitOfMeasure());
			        book.setText(r, 6, lineDTO.getCurrentUnits()+"");
			        book.setText(r, 7, lineDTO.getDatePlacedInService()+"");
			        book.setText(r, 8, lineDTO.getLifeInYears()+"");
			        book.setText(r, 9, lineDTO.getContentCode());
			        
			        book.setText(r, 10, lineDTO.getCost()+"");
			        book.setText(r, 11, lineDTO.getDepreciation()+"");
			        book.setText(r, 12, lineDTO.getImpairReserve()+"");
			        book.setText(r, 13, lineDTO.getDeprnCost()+"");
			        book.setText(r, 14, lineDTO.getScrapValue());
			        r=++r;
		        }	
		        
		        book.setText(r, 1, "��  ��");
		        book.setText(r, 10, TOTAL_COST+"");
		        book.setText(r, 11, TOTAL_DEPRECIATION+"");
		        book.setText(r, 12, TOTAL_IMPAIR_RESERVE+"");
		        book.setText(r, 13, TOTAL_DEPRN_COST+"");
		        book.setText(r, 14, TOTAL_SCRAP_VALUE+"");
		       
		        r=r+3;
		        
		        book.setText(r, 2, "������λ��������ǩ�֣�");
		        book.setText(r, 8, "���뵥λ��������ǩ�֣�");
		        r=r+2;
		        book.setText(r, 2, "������λרҵ����ǩ�֣�");
		        book.setText(r, 8, "���뵥λרҵ����ǩ�֣�");
		        r=r+3;
		        book.setText(r, 2, "������λ������ǩ�£�");
		        book.setText(r, 8, "���뵥λ������ǩ�£�");
		        r=r+3;
		        book.setText(r, 2, "������λ���£�");
		        book.setText(r, 8, "���뵥λ���£�");
		        
	        
		    }else if(!headerDTO.getTransType().equals(AssetsDictConstant.ASS_SUB )
			      && !headerDTO.getTransType().equals(AssetsDictConstant.ASS_FREE)
			      && !headerDTO.getTransType().equals(AssetsDictConstant.ASS_RED)){//���ϻ���	
			    reportTitle="�̶��ʲ������嵥";
		    	book.setText(0, 9, reportTitle);
		    	book.setText(1, 9, curDate);
		    	r=3;
				book.setText(r, 1, "��λ���ƣ�");
		        book.setText(r, 2, headerDTO.getFromCompanyName());
		        book.setText(r, 4, "���ϵ��ţ�");
		        book.setText(r, 5, headerDTO.getTransNo());
		        book.setText(r, 18, "(��λ��Ԫ)");
		        
		        r=4;
		        book.setText(r, 0, "���");
		        book.setText(r, 1, "��Ԫ���");
		        book.setText(r, 2, "�ʲ����");
		        book.setText(r, 3, "�ʲ���ǩ");
		        book.setText(r, 4, "�ʲ�����");
		        
		        book.setText(r, 5, "����Ŀ��");
		        book.setText(r, 6, "�ʲ����ĩ������");
		        book.setText(r, 7, "��Ӧ��");
		        book.setText(r, 8, "����ʽ");
		        book.setText(r, 9, "��������");
		        
		        book.setText(r, 10, "ʣ���۾�����(��)");
		        book.setText(r, 11, "������ð汾");
		        book.setText(r, 12, "������ϰ汾");
		        book.setText(r, 13, "�ʲ�ԭֵ");
		        book.setText(r, 14, "�ۼ��۾�");
		        
		        book.setText(r, 15, "�ʲ���ֵ");
		        book.setText(r, 16, "��ֵ׼��");
		        book.setText(r, 17, "�ʲ�����");
		        book.setText(r, 18, "��ע");
		        
		        r=5;
		        int j=0;
		        AmsAssetsTransLineDTO lineDTO = null; 
		        for (int i = 0; i < lineSet.getSize(); i++) {
	    			lineDTO = (AmsAssetsTransLineDTO) lineSet.getDTO(i);
					TOTAL_COST += lineDTO.getCost();
	    			TOTAL_DEPRECIATION += lineDTO.getDepreciation();
	    			
	    			if (!lineDTO.getNetAssetValue().trim().equals("")) {
	    				TOTAL_NET_ASSET_VALUE += Float.parseFloat(lineDTO.getNetAssetValue());
	    			} else {
	    				TOTAL_NET_ASSET_VALUE += Float.parseFloat("0");
	    			}
	    			
	    			TOTAL_IMPAIR_RESERVE += lineDTO.getImpairReserve();
	    			TOTAL_DEPRN_COST += lineDTO.getDeprnCost();
	    			j=i+1;
	    			
	    			book.setText(r, 0, j+"");
			        book.setText(r, 1, lineDTO.getNetUnit());
			        book.setText(r, 2, lineDTO.getAssetNumber());
			        book.setText(r, 3, lineDTO.getBarcode());
			        book.setText(r, 4, lineDTO.getAssetsDescription());
			  
			        book.setText(r, 5, lineDTO.getContentCode());
			        book.setText(r, 6, lineDTO.getContentName()+"");
			        book.setText(r, 7, lineDTO.getManufacturerName()+"");
			        book.setText(r, 8, lineDTO.getModelNumber()+"");
			        book.setText(r, 9, lineDTO.getDatePlacedInService()+"");
      
			        book.setText(r, 10, lineDTO.getLifeInYears()+"");
			        book.setText(r, 11, lineDTO.getSoftInuseVersion()+"");
			        book.setText(r, 12, lineDTO.getSoftDevalueVersion()+"");
			        book.setText(r, 13, lineDTO.getCost()+"");
			        book.setText(r, 14, lineDTO.getDepreciation()+"");
			        
			        book.setText(r, 15, lineDTO.getNetAssetValue()+"");
			        book.setText(r, 16, lineDTO.getImpairReserve()+"");
			        book.setText(r, 17, lineDTO.getDeprnCost()+"");
			        book.setText(r, 18, lineDTO.getRemark());
			        r=++r;
		        }	
		        
		        book.setText(r, 1, "��  ��");
		        book.setText(r, 13, TOTAL_COST+"");
		        book.setText(r, 14, TOTAL_DEPRECIATION+"");
		        book.setText(r, 15, TOTAL_NET_ASSET_VALUE+"");
		        book.setText(r, 16, TOTAL_IMPAIR_RESERVE+"");
		        book.setText(r, 17, TOTAL_DEPRN_COST+"");
		      
		    
		    }
	        
            book.setColWidthAuto(0, 0, r, 2, true);
          //book.setFixedRow(0);
          //book.setFixedCol(1);
            book.recalc();
            book.write(out, new com.f1j.ss.WriteParams(BookModel.eFileExcel97));
            out.close();
        
            success = true;
	    } catch (CalendarException e) {
	    	Logger.logError(e);
            showMsg = e.getMessage();
			
        } catch (IOException e) {
            Logger.logError(e);
            showMsg = e.getMessage();
        } catch (F1Exception e) {
            Logger.logError(e);
            showMsg = e.getMessage();
        }

        return success;
    }
}
