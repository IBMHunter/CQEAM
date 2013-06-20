package com.sino.ams.system.object.servlet;

import java.io.FileInputStream;
import java.io.IOException;

import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DTOException;
import com.sino.base.util.StrUtil;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Created by IntelliJ IDEA.
 * User: zjs
 * Date: 2008-6-26
 * Time: 20:13:58
 * Function:�ص���������.
 */
public class ReadObjectInfo {
    private HSSFWorkbook book = null;

    private int startRowNum = 0;
    private int numberOfColumn = 0;

    public void setFileName(String fileName) throws IOException {
        FileInputStream fileIn = new FileInputStream(fileName);
        POIFSFileSystem fs = new POIFSFileSystem(fileIn);
        book = new HSSFWorkbook(fs);
//        book=new HSSFWorkbook(fileIn);
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
                        EtsObjectDTO onNetDtlDTO = new EtsObjectDTO();
                        onNetDtlDTO.setExcelLineId(String.valueOf(i+1));
                        for (int k = 0; k <= numberOfColumn; k++) {
                            hssfCell = hssfRow.getCell((short) k);
                            String strValue = getStringValue(hssfCell);
                            onNetDtlDTO = fillOrderDtlDTO(k, strValue, onNetDtlDTO);
                        }
//                         if (StrUtil.isNotEmpty(onNetDtlDTO.getWorkorderObjectCode())) {
//                             orderDTOSet.addDTO(onNetDtlDTO);
//                         }
                        orderDTOSet.addDTO(onNetDtlDTO);
                    }
                }
            }

        }
        return orderDTOSet;
    }

    private EtsObjectDTO fillOrderDtlDTO(int indexk, String strValue, EtsObjectDTO onNetDtlDTO) {
        strValue = StrUtil.nullToString(strValue);
        strValue = strValue.trim();
//----����ص�ǰ�汾----
//		 switch (indexk) {
//			 case 0: //�ص����
//				 onNetDtlDTO.setWorkorderObjectCode(strValue);
//				 break;
//			 case 1://�ص����ƺ�����
//				 onNetDtlDTO.setWorkorderObjectName(strValue);
//				 break;
//			 case 2: //�ص�רҵ
//				 if(strValue.indexOf(".") > -1){
//					 strValue = strValue.substring(0, strValue.indexOf("."));
//				 }
//				 onNetDtlDTO.setObjectCategory(strValue);
//				 break;
//			 case 3://�ص�����
//				 if(strValue.indexOf(".") > -1){
//					 strValue = strValue.substring(0, strValue.indexOf("."));
//				 }
//				 onNetDtlDTO.setCountyCode(strValue);
//				 break;
//             case 4://��������
//				 if(strValue.indexOf(".") > -1){
//					 strValue = strValue.substring(0, strValue.indexOf("."));
//				 }
//				 onNetDtlDTO.setAreaType(strValue);
//				 break;
//             case 5://�Ƿ�TD
//				 if(strValue.indexOf(".") > -1){
//					 strValue = strValue.substring(0, strValue.indexOf("."));
//				 }
//				 onNetDtlDTO.setIsTd(strValue);
//				 break;
//             case 6://��˾����
//				 if(strValue.indexOf(".") > -1){
//					 strValue = strValue.substring(0, strValue.indexOf("."));
//				 }
//				 onNetDtlDTO.setCompanyCode(strValue);
//				 break;
//             default:
//		 }

/*        switch (indexk) {
	        case 0: //�ص����
	            onNetDtlDTO.setLocationCode(strValue);
	            break;
	        case 1://����
	            onNetDtlDTO.setCity(strValue);
	            break;
	        case 2://����
	            onNetDtlDTO.setCounty(strValue);
	            break;
	        case 3://�ص�����
	            onNetDtlDTO.setLocation(strValue);
	            break;
	        case 4: //רҵ����
	            onNetDtlDTO.setObjectCategory(strValue);
	            break;
	        case 5://�ɱ����Ĵ���
	            onNetDtlDTO.setCountyCode(strValue);
	            break;
	        case 6://��������
	            onNetDtlDTO.setAreaType(strValue);
	            break;
	        case 7://�Ƿ�TD
	            onNetDtlDTO.setIsTd(strValue);
	            break;
	        case 8://��˾����
	            if (strValue.endsWith(".0")) {
	                strValue = strValue.substring(0, strValue.indexOf("."));
	            }
	            onNetDtlDTO.setCompanyCode(strValue);
	            break;
	        default:
	    }
	    return onNetDtlDTO;*/
        
        //2011.10.27�����ģ��							
        //LOCATION_CODE0	CITY1	COUNTY2	"WORKORDER_OBJECT_NAME or WORKORDER_OBJECT_LOCATION or LOCATION"3	
        //COUNTY_CODE4	OBJECT_CATEGORY5	BTS_NO6	AREA_TYPE7	REMARK8
        //�ص����0	����1	����2	���ڵص�����3	��������4	רҵ����5	��վ��Ӫҵ�����6	��������7	ά������8

        switch (indexk) {
            case 0: //�ص����
                onNetDtlDTO.setLocationCode(strValue);
                onNetDtlDTO.setWorkorderObjectCode(strValue);
                break;
            case 1://����
                onNetDtlDTO.setCity(strValue);
                break;
            case 2://����
                onNetDtlDTO.setCounty(strValue);
                break;
            case 3://�ص�����
                onNetDtlDTO.setLocation(strValue);
                onNetDtlDTO.setWorkorderObjectName(strValue);
                break;
            case 4: //�������� Ҳ�ǳɱ����Ĵ���?
                onNetDtlDTO.setCountyCode(strValue);
                break;
            case 5://רҵ����
                onNetDtlDTO.setObjectCategory(strValue);
                break;
            case 6://��վ��Ӫҵ�����
                onNetDtlDTO.setBtsNo(strValue);
                break;
            case 7://��������
                onNetDtlDTO.setAreaType(strValue);
                break;
            case 8://ά������
                onNetDtlDTO.setRemark(strValue);
                break;
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
