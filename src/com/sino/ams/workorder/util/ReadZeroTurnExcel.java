package com.sino.ams.workorder.util;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.sino.ams.workorder.dto.ZeroTurnLineDTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DTOException;

//�����㹺����
public class ReadZeroTurnExcel {
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

	// ��ȡָ��sheet
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
						numberOfColumn = numberOfColumn == 0 ? hssfRow
								.getLastCellNum() : numberOfColumn;
						ZeroTurnLineDTO lineDTO = new ZeroTurnLineDTO();
						String excelLineId = String.valueOf(i + 1);
						lineDTO.setExcelLineId(excelLineId);
						for (int k = 0; k <= numberOfColumn; k++) {
							hssfCell = hssfRow.getCell((short) k);
							String strValue = getStringValue(hssfCell);
							lineDTO = fillLineDTO(k, strValue, lineDTO);
						}
						boolean flag = true;
						if (isExist(lineDTO)) {
							orderDTOSet.addDTO(lineDTO);
						}
					}
				}
			}

		}
		return orderDTOSet;
	}

	private ZeroTurnLineDTO fillLineDTO(int indexk, String strValue,
			ZeroTurnLineDTO lineDTO) {
		if (strValue != null) {
			strValue = strValue.trim();
		}
		switch (indexk) {
		case 0:// ���������
			strValue = noIntType(strValue);
			lineDTO.setMisProcureCode(strValue);
			break;
		case 1:// �ɹ����ţ����䣩
			lineDTO.setProcureCode(strValue);
			break;
		case 2:
			// ��˾����(����)
			strValue = noIntType(strValue);
			lineDTO.setCompanyCode(strValue);
			break;
		case 3:// �ʲ�����
			lineDTO.setAssetsDescription(strValue);
			break;
		case 4:
			// ����ͺţ����䣩
			lineDTO.setItemSpec(strValue);
			break;
		case 5:
			// ���̣����䣩
			lineDTO.setManufacturerName(strValue);
			break;
		case 6:// �ʲ�Ŀ¼�����䣩
			lineDTO.setContentCode(strValue);
			break;
		case 7:// ���������䣩
			strValue = noIntType(strValue);
			lineDTO.setItemQty(strValue);
			break;
		case 8:// ʹ�����ޣ����䣩
			strValue = noIntType(strValue);
			lineDTO.setYears(strValue);
			break;
		case 9:
			// �����䣩
			//strValue = noIntType(strValue);
			lineDTO.setPrice(strValue);
			break;
		case 10:
			// �������ڣ���ѡ��
			lineDTO.setStartDate(strValue);
			break;
		case 11:
			// ҵ��ƽ̨����ѡ��
			lineDTO.setOpeId(strValue);
			break;
		case 12:
			// ������Σ���ѡ��
			lineDTO.setNleId(strValue);
			break;
		case 13:
			// �Ƿ񹲽��豸����ѡ��
			lineDTO.setIsBulid(strValue);
			break;
		case 14:
			// �ɱ����ģ����䣩
			strValue = noIntType(strValue);
			lineDTO.setCostCenterCode(strValue);
			break;
		case 15:
			// �ص����ƣ���ѡ��
			lineDTO.setWorkorderObjectName(strValue);
			break;
		case 16:
			// �ص��ţ����䣩
			lineDTO.setObjectNo(strValue);
			break;
		case 17:
			// �����˱�ţ����䣩
			strValue = noIntType(strValue);
			lineDTO.setResponsibilityUser(strValue);
			break;
		case 18:
			// ���������������䣩
			lineDTO.setResponsibilityName(strValue);
			break;
		case 19:// �빺��𣨱��䣩
			lineDTO.setProcureType(strValue);
			break;
		case 20:// �ջ��ˣ����䣩
			lineDTO.setReceiver(strValue);
			break;
		case 21:// �ջ�����ϵ��ʽ
			strValue = noIntType(strValue);
			lineDTO.setReceiverContact(strValue);
			break;
		case 22:// ҵ�����
			lineDTO.setAssetKey1(strValue);
			break;
		case 23:// �ܹ�˾����
			lineDTO.setAssetKey2(strValue);
			break;
		case 24:// �ܹ�˾����
			lineDTO.setAssetKey3(strValue);
			break;
		case 25:// �ʲ�����
			lineDTO.setAssetType(strValue);
			break;
		case 26:// �Ƿ��۾�
			lineDTO.setIsDeprn(strValue);
			break;
		case 27:// �Ƿ�̯������
			lineDTO.setIsAdjust(strValue);
			break;
		case 28:// ����״̬
			lineDTO.setAttribute4(strValue);
			break;
		case 29:// �ʲ���Դ
			strValue = noIntType(strValue);
			lineDTO.setAttribute5(strValue);
			break;
		case 30:// Ԥ�Ƶ�������
			lineDTO.setExpectedDate(strValue);
			break;

		default:
		}
		return lineDTO;
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

	// ȥ������EXCEL�еĿհ���
	public boolean isExist(ZeroTurnLineDTO lineDTO) {
		boolean flag = true;
		if (lineDTO.getAssetsDescription().equals("")
				&& lineDTO.getBarcode().equals("")
				&& lineDTO.getCexId().equals("")
				&& lineDTO.getCompanyCode().equals("")
				&& lineDTO.getComputeDays().equals("")
				&& lineDTO.getContentName().equals("")
				&& lineDTO.getCostCenterCode().equals("")
				&& lineDTO.getIsBulid().equals("")
				&& lineDTO.getIsShare().equals("")
				&& lineDTO.getItemQty().equals("")
				&& lineDTO.getItemSpec().equals("")
				&& lineDTO.getLneId().equals("")
				&& lineDTO.getManufacturerName().equals("")
				&& lineDTO.getNleId().equals("")
				&& lineDTO.getObjectNo().equals("")
				&& lineDTO.getOpeId().equals("")
				&& lineDTO.getPrice().equals("")
				&& lineDTO.getProcureCode().equals("")
				&& lineDTO.getRemark().equals("")
				&& lineDTO.getResponsibilityDept().equals("")
				&& lineDTO.getResponsibilityUser().equals("")
				&& lineDTO.getSpecialityDept().equals("")
				&& lineDTO.getStartDate().equals("")
				&& lineDTO.getUnitOfMeasure().equals("")
				&& lineDTO.getWorkorderObjectName().equals("")
				&& lineDTO.getYears().equals("")) {
			flag = false;
		}
		return flag;
	}

	// ȥ���������ַ����е�.0��ʽ
	public String noIntType(String strValue) {
		String newStr = strValue;
		if (!strValue.equals("")) {
			if (strValue.length() > 0) {
				int index = strValue.indexOf(".0");
				if (index >= 0) {
					newStr = strValue.substring(0, index);
				}
			}
		}
		return newStr;
	}

//	 public static void main(String[] args) {
//	 String value="3910-70000029,3910-70000030,3910-70000031,";
//	 String os="";
//	 String [] arg=value.split(",");
//	 for (int i = 0; i < arg.length; i++) {
//		 String a=arg[i];
//		if (i!=arg.length-1) {
//			os+="'"+a+"',";
//		}else {
//			os+="'"+a+"'";
//		}
//	}
//	 System.out.println(os);
//	 }
}
