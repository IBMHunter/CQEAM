package com.sino.ams.newSite.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.sino.ams.newSite.dto.EamAddressAddLDTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DTOException;

/** 
 *
 * @version ����ʱ�䣺Apr 18, 2011 4:27:34 PM 
 * ��˵��:����Excel�ļ�������DOTSet���� 
 *
 */
public class ReadAddressSecondExcel {
	   private HSSFWorkbook book = null;

		 private int startRowNum = 0;
		 private int numberOfColumn = 0;

		  public void setFileName(String fileName) throws IOException {
			 FileInputStream fileIn = new FileInputStream(fileName);
			 POIFSFileSystem fs = new POIFSFileSystem(fileIn);
			 book = new HSSFWorkbook(fs);
		 }

		 public void setStartRowNum(int startRowNum) {
			 this.startRowNum = startRowNum;
		 }

		 public void setNumberOfColumn(int numberOfColumn) {
			 this.numberOfColumn = numberOfColumn;
		 }

		 //��ȡָ��sheet
		 public DTOSet readXls(int indexOfSheet) throws DTOException {
			 DTOSet orderDTOSet = new DTOSet();
			 if (book != null) {
				 int numberOfSheet = book.getNumberOfSheets();
				 if (indexOfSheet < numberOfSheet) {
					 HSSFSheet hssfSheet = book.getSheetAt(indexOfSheet);
					 HSSFRow hssfRow = null;
					 HSSFCell hssfCell = null;
					 int row = hssfSheet.getLastRowNum();
					 for (int i = startRowNum; i <= row; i++) {
						 hssfRow = hssfSheet.getRow(i);
						 if (hssfRow != null) {
							 numberOfColumn = numberOfColumn == 0 ? hssfRow.getLastCellNum() : numberOfColumn;
							 EamAddressAddLDTO onNetDtlDTO = new EamAddressAddLDTO();
							 String excelLineId=String.valueOf(i+1);
							 onNetDtlDTO.setExcelLineId(excelLineId);
							 for (int k = 0; k <= numberOfColumn; k++) {
								 hssfCell = hssfRow.getCell((short) k);
								 String strValue = getStringValue(hssfCell);
								 onNetDtlDTO = fillOrderDtlDTO(k, strValue, onNetDtlDTO);
							 }
	                         orderDTOSet.addDTO(onNetDtlDTO);
	                     }
					 }
				 }

			 }
			 return orderDTOSet;
		 }

		 private EamAddressAddLDTO fillOrderDtlDTO(int indexk, String strValue, EamAddressAddLDTO onNetDtlDTO) {
			 if(strValue != null){
				 strValue = strValue.trim();
			 }
	         switch (indexk) {
				 case 0://�ص�ڶ��δ���
					 onNetDtlDTO.setWorkorderObjectCode(strValue);
					 break;
				 case 1://�ص�ڶ�������
					 onNetDtlDTO.setWorkorderObjectName(strValue);   
					 break;
				 case 2: //��������
					 onNetDtlDTO.setAreaType(strValue);
					 break;
				 case 3://����
					 onNetDtlDTO.setCity(strValue);
					 break;
	             case 4://����
					 onNetDtlDTO.setCounty(strValue);
					 break;
	             case 5://רҵ����
					 onNetDtlDTO.setObjectCategory(strValue);
					 break;
	             case 6://��վ��Ӫҵ�����
	            	 onNetDtlDTO.setBtsNo(strValue);
	            	 break;	             				 
	             case 7://ά������
	            	 onNetDtlDTO.setAddrMaintainType(strValue);
	             case 8://�Ƿ���
	            	 onNetDtlDTO.setShareType(strValue.substring(0,1));
	             default:
			 }
	         return onNetDtlDTO;
		 }

		 private String getStringValue(HSSFCell cell) {
			 String strValue = "";
			 if (cell != null) {
				 if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
					 strValue = "";
				 } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
					 strValue = String.valueOf(cell.getNumericCellValue());
				 } else {
					 strValue = cell.getRichStringCellValue().toString();
				 }
			 }
			 return strValue;
		 }
}
