package com.sino.pda;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.sino.ams.workorder.dto.EtsWorkorderDtlDTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DTOException;
import com.sino.base.util.StrUtil;

/**
 * User: zhoujs
 * Date: 2007-12-19
 * Time: 12:01:43
 * Function: ����Excelģ���ȡ
 */
public class XlsOrderReader {
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
                int col = hssfSheet.getPhysicalNumberOfRows();

                for (int i = startRowNum; i <= row; i++) {
                    hssfRow = hssfSheet.getRow(i);
                    if (hssfRow != null) {
                        numberOfColumn = numberOfColumn == 0 ? hssfRow.getLastCellNum() : numberOfColumn;
                        EtsWorkorderDtlDTO orderDtlDTO = new EtsWorkorderDtlDTO();
                        for (int k = 0; k < numberOfColumn; k++) {
                            hssfCell = hssfRow.getCell((short) k);
                            String strValue = getStringValue(hssfCell);
                            orderDtlDTO = fillOrderDtlDTO(k, strValue, orderDtlDTO);
                        }
                        orderDTOSet.addDTO(orderDtlDTO);
                    }
                }
            }

        }
        return orderDTOSet;
    }

    private EtsWorkorderDtlDTO fillOrderDtlDTO(int indexk, String strValue, EtsWorkorderDtlDTO orderDtlDTO) {
        switch (indexk) {
            case 0: //������
                orderDtlDTO.setWorkorderNo(strValue);
                break;
            case 1://�ʲ���ǩ
                orderDtlDTO.setBarcode(strValue);
                break;
            case 2: //�豸״̬
                orderDtlDTO.setItemStatus(StrUtil.strToInt(strValue));
                break;
            case 3://�豸����
                orderDtlDTO.setItemQty(StrUtil.strToInt(strValue));
                break;
            case 4: //�豸����
                orderDtlDTO.setItemCode(strValue);
                break;
//            case 5://�ص�
//                orderDtlDTO.setAddressId(strValue);
//                break;
//            case 6: //����
//                orderDtlDTO.setBoxNo(strValue);
//                break;
//            case 7: //��Ԫ
//                orderDtlDTO.setNetUnit(strValue);
//                break;
            case 5: //���豸��ǩ
                orderDtlDTO.setParentBarcode(strValue);
                break;
            case 6:  //��ע
                orderDtlDTO.setRemark(strValue);
                break;
            default:
        }

        return orderDtlDTO;
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
