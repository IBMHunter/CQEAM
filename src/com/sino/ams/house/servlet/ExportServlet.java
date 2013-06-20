package com.sino.ams.house.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.PoolException;
import com.sino.ams.house.dao.AmshouseinfoDAO;
import com.sino.ams.house.dto.AmshouseinfoDTO;
import com.sino.framework.servlet.BaseServlet;
import org.apache.poi.hssf.usermodel.*;

/**
 * ��������ͳ�Ʊ�����
 * 
 * @author kouzh
 * 
 */
public class ExportServlet extends BaseServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sino.base.PubServlet#performTask(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	public void performTask(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Connection conn = null;
		String nextPage = "";
		try {
			conn = DBManager.getDBConnection();
			this.export(req, res, conn);
			return;
		} catch (PoolException e) {
			req.setAttribute("ERROR_MSG", "ȡ���ӳ���");
			nextPage = "/flow/errorPage.jsp";
			req.getRequestDispatcher(nextPage).forward(req, res);
			e.printLog();
		} finally {
			DBManager.closeDBConnection(conn);
		}
	}

	/**
	 * 
	 * ��������������Ϣͳ�Ʊ���
	 * 
	 */
	public void export(HttpServletRequest req, HttpServletResponse res,
			Connection conn) {
		InputStream ins = null;// ����������
		HSSFWorkbook mcBook = null;// ��д����������
		HSSFSheet mcSheet = null;// //��д���������
		String exportFileName = "houseStat.xls";// ��������
		int size = 0;
		try {
			this.beforeExport(req, res, exportFileName);// ������Ӧͷ��Ϣ
			ins = new FileInputStream(this.getFile(req, exportFileName));// ��ȡģ��XLS�ļ��Ա�����д��
			mcBook = new HSSFWorkbook(ins); // ����һ������������

			// ���ñ������������ĵ�Ԫ����ʽ
			HSSFFont fontHead = mcBook.createFont();
			HSSFCellStyle cellStyle = mcBook.createCellStyle();
			cellStyle.setBorderBottom((short) 1);
			cellStyle.setBorderLeft((short) 1);
			cellStyle.setBorderRight((short) 1);
			cellStyle.setBorderTop((short) 1);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			fontHead.setFontHeightInPoints((short) 11);// �ֺ�
			cellStyle.setFont(fontHead);

			// ���ñ����л������ĵ�Ԫ����ʽ
			HSSFFont fontHead1 = mcBook.createFont();
			HSSFCellStyle cellStyle1 = mcBook.createCellStyle();
			cellStyle1.setBorderBottom((short) 1);
			cellStyle1.setBorderLeft((short) 1);
			cellStyle1.setBorderRight((short) 1);
			cellStyle1.setBorderTop((short) 1);
			cellStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			fontHead1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ����
			fontHead1.setFontHeightInPoints((short) 11);// �ֺ�
			cellStyle1.setFont(fontHead1);
			// ������Ϣ����
			long assetNum = 0;
			long houseCertificateNum = 0;
			double occupyArea = 0.0;
			double houseArea = 0.0;
			double cost = 0.0;
			double deprnReserve = 0.0;
			double netAssetValue = 0.0;
			Map map = new HashMap();
			AmshouseinfoDTO dto1 = new AmshouseinfoDTO();

			/** ***************** ��ȡ��������վ����ͳ����Ϣ**************** */
			AmshouseinfoDAO dao = new AmshouseinfoDAO(conn, req);
			DTOSet set = dao.getAmslandInfo(conn);
			if (set != null && !set.isEmpty()) {// �����վ����ͳ����Ϣ��Ϊ����д�뵽EXCELģ���У��Թ�����
				size = set.getSize();
				mcSheet = mcBook.getSheetAt(4);// �õ������������:��վ����
				// ��֯���ݲ�д��
				int rowIndex = 2;
				HSSFRow row = null;
				HSSFCell cell = null;
				AmshouseinfoDTO dto = null;
				for (int i = 0; i < size; i++) {
					dto = (AmshouseinfoDTO) set.getDTO(i);
					dto.setLandnum(dto.getAssetnum());// ��վ��������
					map.put(dto.getCompany(), dto);
					row = mcSheet.createRow(rowIndex);
					cell = row.createCell((short) 0);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(i + 1);// ���
					cell = row.createCell((short) 1);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCompany());// ��˾
					cell = row.createCell((short) 2);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getAssetnum());// �ʲ�����
					assetNum += dto.getAssetnum();
					cell = row.createCell((short) 3);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseCertificateNum());// ����Ȩ֤����
					houseCertificateNum += dto.getHouseCertificateNum();
					cell = row.createCell((short) 4);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getOccupyArea());// �������
					occupyArea += dto.getOccupyArea();
					cell = row.createCell((short) 5);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCost());// �ʲ�ԭֵ
					cost += dto.getCost();
					cell = row.createCell((short) 6);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getDeprnReserve());// �ۼ��۾�
					deprnReserve += dto.getDeprnReserve();
					cell = row.createCell((short) 7);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getNetAssetValue());// �ʲ���ֵ
					netAssetValue += dto.getNetAssetValue();
					rowIndex = rowIndex + 1;
				}
				row = mcSheet.createRow(rowIndex);
				cell = row.createCell((short) 1);
				cell.setCellStyle(cellStyle1);
				cell.setCellValue("�ϼ�");
				cell = row.createCell((short) 2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(assetNum);// �ʲ������ϼ�
				cell = row.createCell((short) 3);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseCertificateNum);// ����Ȩ֤�����ϼ�
				cell = row.createCell((short) 4);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(occupyArea);// ��������ϼ�
				cell = row.createCell((short) 5);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(cost);// �ʲ�ԭֵ�ϼ�
				cell = row.createCell((short) 6);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(deprnReserve);// �ۼ��۾ɺϼ�
				cell = row.createCell((short) 7);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(netAssetValue);// �ʲ���ֵ�ϼ�
				assetNum = 0;
				houseCertificateNum = 0;
				occupyArea = 0.0;
				houseArea = 0.0;
				cost = 0.0;
				deprnReserve = 0.0;
				netAssetValue = 0.0;
			}
			/** *********************** end **************************** */

            /** **************��ȡ��������վ���ݱ���ͳ����Ϣ****************** */
			DTOSet set1 = dao.getAmshouseInfo(conn);
			if (set1 != null && !set1.isEmpty()) {// �����վ���ݱ���ͳ����Ϣ��Ϊ����д�뵽EXCELģ���У��Թ�����
				size = set1.getSize();
				mcSheet = mcBook.getSheetAt(3);// �õ����ĸ�������:��վ����
				// ��֯���ݲ�д��
				int rowIndex = 2;
				HSSFRow row = null;
				HSSFCell cell = null;
				AmshouseinfoDTO dto = null;
				for (int i = 0; i < size; i++) {
					dto = (AmshouseinfoDTO) set1.getDTO(i);
					if (!map.containsKey(dto.getCompany())) {
						dto1.setCompany(dto.getCompany());// ��˾
						dto1.setAssetnum(dto.getAssetnum());// �ʲ�����
						dto1.setLandnum(0);// ��վ��������
						dto1.setHousenum(dto.getAssetnum());// ��վ��������
						dto1.setHouseCertificateNum(dto.getHouseCertificateNum());// ����Ȩ֤����
						dto1.setHouseArea(dto.getHouseArea());// �������
						dto1.setOccupyArea(0);// �������
						dto1.setCost(dto.getCost());// �ʲ�ԭֵ
						dto1.setDeprnReserve(dto.getDeprnReserve());// �ۼ��۾�
						dto1.setNetAssetValue(dto.getNetAssetValue());// �ʲ���ֵ
						map.put(dto.getCompany(), dto1);
					} else {
						dto1 = (AmshouseinfoDTO) map.get(dto.getCompany());
						dto1.setAssetnum(dto1.getAssetnum() + dto.getAssetnum());// �ʲ�����
						dto1.setHousenum(dto.getAssetnum());// ��վ��������
						dto1.setHouseCertificateNum(dto1.getHouseCertificateNum() + dto.getHouseCertificateNum());// ����Ȩ֤����
						dto1.setHouseArea(dto.getHouseArea());// �������
						dto1.setCost(dto1.getCost() + dto.getCost());// �ʲ�ԭֵ
						dto1.setDeprnReserve(dto1.getDeprnReserve() + dto.getDeprnReserve());// �ۼ��۾�
						dto1.setNetAssetValue(dto1.getNetAssetValue() + dto.getNetAssetValue());// �ʲ���ֵ
						map.put(dto.getCompany(), dto1);
					}
					row = mcSheet.createRow(rowIndex);
					cell = row.createCell((short) 0);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(i + 1);// ���
					cell = row.createCell((short) 1);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCompany());// ��˾
					cell = row.createCell((short) 2);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getAssetnum());// �ʲ�����
					assetNum += dto.getAssetnum();
					cell = row.createCell((short) 3);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseCertificateNum());// ����Ȩ֤����
					houseCertificateNum += dto.getHouseCertificateNum();
					cell = row.createCell((short) 4);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseArea());// �������
					houseArea += dto.getHouseArea();
					cell = row.createCell((short) 5);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCost());// �ʲ�ԭֵ
					cost += dto.getCost();
					cell = row.createCell((short) 6);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getDeprnReserve());// �ۼ��۾�
					deprnReserve += dto.getDeprnReserve();
					cell = row.createCell((short) 7);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getNetAssetValue());// �ʲ���ֵ
					netAssetValue += dto.getNetAssetValue();
					rowIndex = rowIndex + 1;
				}
				row = mcSheet.createRow(rowIndex);
				cell = row.createCell((short) 1);
				cell.setCellStyle(cellStyle1);
				cell.setCellValue("�ϼ�");
				cell = row.createCell((short) 2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(assetNum);// �ʲ������ϼ�
				cell = row.createCell((short) 3);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseCertificateNum);// ����Ȩ֤�����ϼ�
				cell = row.createCell((short) 4);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseArea);// ��������ϼ�
				cell = row.createCell((short) 5);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(cost);// �ʲ�ԭֵ�ϼ�
				cell = row.createCell((short) 6);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(deprnReserve);// �ۼ��۾ɺϼ�
				cell = row.createCell((short) 7);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(netAssetValue);// �ʲ���ֵ�ϼ�
				assetNum = 0;
				houseCertificateNum = 0;
				occupyArea = 0.0;
				houseArea = 0.0;
				cost = 0.0;
				deprnReserve = 0.0;
				netAssetValue = 0.0;
			}
			/** ********************** end ***************************** */

			/** ****************** ��ȡ�������칫���ر���ͳ����Ϣ************* */
			DTOSet set2 = dao.getAmsofficelandInfo(conn);
			if (set2 != null && !set2.isEmpty()) {// ����칫���ر���ͳ����Ϣ��Ϊ����д�뵽EXCELģ���У��Թ�����
				size = set2.getSize();
				mcSheet = mcBook.getSheetAt(2);// �õ�������������:�칫����
				// ��֯���ݲ�д��
				int rowIndex = 2;
				HSSFRow row = null;
				HSSFCell cell = null;
				AmshouseinfoDTO dto = null;
				for (int i = 0; i < size; i++) {
					dto = (AmshouseinfoDTO) set2.getDTO(i);
					if (!map.containsKey(dto.getCompany())) {
						dto1.setCompany(dto.getCompany());// ��˾
						dto1.setAssetnum(dto.getAssetnum());// �ʲ�����
						dto1.setLandnum(0);// ��վ��������
						dto1.setHousenum(0);// ��վ��������
						dto1.setOfficeLandnum(dto.getAssetnum());// �칫Ӫҵ��������
						dto1.setHouseCertificateNum(dto.getHouseCertificateNum());// ����Ȩ֤����
						dto1.setHouseArea(0);// �������
						dto1.setOccupyArea(dto.getOccupyArea());// �������
						dto1.setCost(dto.getCost());// �ʲ�ԭֵ
						dto1.setDeprnReserve(dto.getDeprnReserve());// �ۼ��۾�
						dto1.setNetAssetValue(dto.getNetAssetValue());// �ʲ���ֵ
						map.put(dto.getCompany(), dto1);
					} else {
						dto1 = (AmshouseinfoDTO) map.get(dto.getCompany());
						dto1.setAssetnum(dto1.getAssetnum() + dto.getAssetnum());// �ʲ�����
						dto1.setOfficeLandnum(dto.getAssetnum());// �칫Ӫҵ��������
						dto1.setHouseCertificateNum(dto1.getHouseCertificateNum() + dto.getHouseCertificateNum());// ����Ȩ֤����
						dto1.setOccupyArea(dto1.getOccupyArea() + dto.getOccupyArea());// �������
						dto1.setCost(dto1.getCost() + dto.getCost());// �ʲ�ԭֵ
						dto1.setDeprnReserve(dto1.getDeprnReserve() + dto.getDeprnReserve());// �ۼ��۾�
						dto1.setNetAssetValue(dto1.getNetAssetValue() + dto.getNetAssetValue());// �ʲ���ֵ
						map.put(dto.getCompany(), dto1);
					}
					row = mcSheet.createRow(rowIndex);
					cell = row.createCell((short) 0);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(i + 1);// ���
					cell = row.createCell((short) 1);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCompany());// ��˾
					cell = row.createCell((short) 2);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getAssetnum());// �ʲ�����
					assetNum += dto.getAssetnum();
					cell = row.createCell((short) 3);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseCertificateNum());// ����Ȩ֤����
					houseCertificateNum += dto.getHouseCertificateNum();
					cell = row.createCell((short) 4);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getOccupyArea());// �������
					occupyArea += dto.getOccupyArea();
					cell = row.createCell((short) 5);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCost());// �ʲ�ԭֵ
					cost += dto.getCost();
					cell = row.createCell((short) 6);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getDeprnReserve());// �ۼ��۾�
					deprnReserve += dto.getDeprnReserve();
					cell = row.createCell((short) 7);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getNetAssetValue());// �ʲ���ֵ
					netAssetValue += dto.getNetAssetValue();
					rowIndex = rowIndex + 1;
				}
				row = mcSheet.createRow(rowIndex);
				cell = row.createCell((short) 1);
				cell.setCellStyle(cellStyle1);
				cell.setCellValue("�ϼ�");
				cell = row.createCell((short) 2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(assetNum);// �ʲ������ϼ�
				cell = row.createCell((short) 3);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseCertificateNum);// ����Ȩ֤�����ϼ�
				cell = row.createCell((short) 4);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(occupyArea);// ��������ϼ�
				cell = row.createCell((short) 5);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(cost);// �ʲ�ԭֵ�ϼ�
				cell = row.createCell((short) 6);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(deprnReserve);// �ۼ��۾ɺϼ�
				cell = row.createCell((short) 7);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(netAssetValue);// �ʲ���ֵ�ϼ�
				assetNum = 0;
				houseCertificateNum = 0;
				occupyArea = 0.0;
				houseArea = 0.0;
				cost = 0.0;
				deprnReserve = 0.0;
				netAssetValue = 0.0;
			}
			/** ********************** end ************************ */

			/** ************ ��ȡ�������칫���ݱ���ͳ����Ϣ ************* */
			DTOSet set3 = dao.getAmsofficehouseInfo(conn);
			if (set3 != null && !set3.isEmpty()) {// ����칫���ݱ���ͳ����Ϣ��Ϊ����д�뵽EXCELģ���У��Թ�����
				size = set3.getSize();
				mcSheet = mcBook.getSheetAt(1);// �õ��ڶ���������:�칫����
				// ��֯���ݲ�д��
				int rowIndex = 2;
				HSSFRow row = null;
				HSSFCell cell = null;
				AmshouseinfoDTO dto = null;
				for (int i = 0; i < size; i++) {
					dto = (AmshouseinfoDTO) set3.getDTO(i);
					if (!map.containsKey(dto.getCompany())) {
						dto1.setCompany(dto.getCompany());// ��˾
						dto1.setAssetnum(dto.getAssetnum());// �ʲ�����
						dto1.setLandnum(0);// ��վ��������
						dto1.setHousenum(0);// ��վ��������
						dto1.setOfficeLandnum(0);// �칫Ӫҵ��������
						dto1.setOfficeHousenum(dto.getAssetnum());// �칫Ӫҵ��������
						dto1.setHouseCertificateNum(dto.getHouseCertificateNum());// ����Ȩ֤����
						dto1.setHouseArea(dto.getHouseArea());// �������
						dto1.setOccupyArea(0);// �������
						dto1.setCost(dto.getCost());// �ʲ�ԭֵ
						dto1.setDeprnReserve(dto.getDeprnReserve());// �ۼ��۾�
						dto1.setNetAssetValue(dto.getNetAssetValue());// �ʲ���ֵ
						map.put(dto.getCompany(), dto1);
					} else {
						dto1 = (AmshouseinfoDTO) map.get(dto.getCompany());
						dto1.setAssetnum(dto1.getAssetnum() + dto.getAssetnum());// �ʲ�����
						dto1.setOfficeHousenum(dto.getAssetnum());// �칫Ӫҵ��������
						dto1.setHouseCertificateNum(dto1.getHouseCertificateNum() + dto.getHouseCertificateNum());// ����Ȩ֤����
						dto1.setHouseArea(dto1.getHouseArea() + dto.getHouseArea());// �������
						dto1.setCost(dto1.getCost() + dto.getCost());// �ʲ�ԭֵ
						dto1.setDeprnReserve(dto1.getDeprnReserve() + dto.getDeprnReserve());// �ۼ��۾�
						dto1.setNetAssetValue(dto1.getNetAssetValue() + dto.getNetAssetValue());// �ʲ���ֵ
						map.put(dto.getCompany(), dto1);
					}
					row = mcSheet.createRow(rowIndex);
					cell = row.createCell((short) 0);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(i + 1);// ���
					cell = row.createCell((short) 1);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCompany());// ��˾
					cell = row.createCell((short) 2);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getAssetnum());// �ʲ�����
					assetNum += dto.getAssetnum();
					cell = row.createCell((short) 3);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseCertificateNum());// ����Ȩ֤����
					houseCertificateNum += dto.getHouseCertificateNum();
					cell = row.createCell((short) 4);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseArea());// �������
					houseArea += dto.getHouseArea();
					cell = row.createCell((short) 5);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCost());// �ʲ�ԭֵ
					cost += dto.getCost();
					cell = row.createCell((short) 6);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getDeprnReserve());// �ۼ��۾�
					deprnReserve += dto.getDeprnReserve();
					cell = row.createCell((short) 7);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getNetAssetValue());// �ʲ���ֵ
					netAssetValue += dto.getNetAssetValue();
					rowIndex = rowIndex + 1;
				}
				row = mcSheet.createRow(rowIndex);
				cell = row.createCell((short) 1);
				cell.setCellStyle(cellStyle1);
				cell.setCellValue("�ϼ�");
				cell = row.createCell((short) 2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(assetNum);// �ʲ������ϼ�
				cell = row.createCell((short) 3);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseCertificateNum);// ����Ȩ֤�����ϼ�
				cell = row.createCell((short) 4);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseArea);// ��������ϼ�
				cell = row.createCell((short) 5);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(cost);// �ʲ�ԭֵ�ϼ�
				cell = row.createCell((short) 6);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(deprnReserve);// �ۼ��۾ɺϼ�
				cell = row.createCell((short) 7);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(netAssetValue);// �ʲ���ֵ�ϼ�
				assetNum = 0;
				houseCertificateNum = 0;
				occupyArea = 0.0;
				houseArea = 0.0;
				cost = 0.0;
				deprnReserve = 0.0;
				netAssetValue = 0.0;
			}
			/** ********************** end ********************* */

            /** ************ ��ȡ�������칫���غ�һ����ͳ����Ϣ ************* 
            long houseLandCertificateNum = 0;
            long landCertificateNum = 0;
            DTOSet set4 = dao.getHouseLandInfo(conn);
			if (set4 != null && !set4.isEmpty()) {// ����칫���ݱ���ͳ����Ϣ��Ϊ����д�뵽EXCELģ���У��Թ�����
				size = set4.getSize();
				mcSheet = mcBook.getSheetAt(3);// �õ����ĸ�������:�칫���غ�һ
				// ��֯���ݲ�д��
				int rowIndex = 2;
				HSSFRow row = null;
				HSSFCell cell = null;
				AmshouseinfoDTO dto = null;
				for (int i = 0; i < size; i++) {
					dto = (AmshouseinfoDTO) set4.getDTO(i);
					if (!map.containsKey(dto.getCompany())) {
						dto1.setCompany(dto.getCompany());// ��˾
						dto1.setAssetnum(dto.getAssetnum());// �ʲ�����
						dto1.setLandnum(0);// ��վ��������
						dto1.setHousenum(0);// ��վ��������
						dto1.setOfficeLandnum(0);// �칫Ӫҵ��������
						dto1.setOfficeHousenum(0);// �칫Ӫҵ��������
                        dto1.setHouseLandNum(0);// ��վ���غ�һ����
                        dto1.setOfficeHouseLandNum(dto.getAssetnum());// �칫���غ�һ����
                        dto1.setHouseCertificateNum(dto.getHouseLandCertificateNum());// ����Ȩ֤����
						dto1.setHouseArea(dto.getHouseArea());// �������
						dto1.setOccupyArea(dto.getOccupyArea());// �������
						dto1.setCost(dto.getCost());// �ʲ�ԭֵ
						dto1.setDeprnReserve(dto.getDeprnReserve());// �ۼ��۾�
						dto1.setNetAssetValue(dto.getNetAssetValue());// �ʲ���ֵ
						map.put(dto.getCompany(), dto1);
					} else {
						dto1 = (AmshouseinfoDTO) map.get(dto.getCompany());//��˾
						dto1.setAssetnum(dto1.getAssetnum() + dto.getAssetnum());// �ʲ�����
						dto1.setOfficeHouseLandNum(dto.getAssetnum());// �칫���غ�һ����
						dto1.setHouseCertificateNum(dto1.getHouseCertificateNum() + dto.getHouseLandCertificateNum());// ����Ȩ֤����
						dto1.setHouseArea(dto1.getHouseArea() + dto.getHouseArea());// �������
                        dto1.setOccupyArea(dto1.getOccupyArea() + dto.getOccupyArea());// �������
                        dto1.setCost(dto1.getCost() + dto.getCost());// �ʲ�ԭֵ
						dto1.setDeprnReserve(dto1.getDeprnReserve() + dto.getDeprnReserve());// �ۼ��۾�
						dto1.setNetAssetValue(dto1.getNetAssetValue() + dto.getNetAssetValue());// �ʲ���ֵ
						map.put(dto.getCompany(), dto1);
					}
					row = mcSheet.createRow(rowIndex);
					cell = row.createCell((short) 0);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(i + 1);// ���

                    cell = row.createCell((short) 1);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCompany());// ��˾

                    cell = row.createCell((short) 2);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getAssetnum());// �ʲ�����
					assetNum += dto.getAssetnum();

                    cell = row.createCell((short) 3);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseLandCertificateNum());// ����Ȩ֤����
					houseLandCertificateNum += dto.getHouseLandCertificateNum();

                    cell = row.createCell((short) 4);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseCertificateNum());// ���ݰ���Ȩ֤����
					houseCertificateNum += dto.getHouseCertificateNum();

                    cell = row.createCell((short) 5);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseArea());// �������
					houseArea += dto.getHouseArea();

                    cell = row.createCell((short) 6);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getLandCertificateNum());// ���ذ���Ȩ֤����
					landCertificateNum += dto.getLandCertificateNum();

                    cell = row.createCell((short) 7);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getOccupyArea());// �������
					occupyArea += dto.getOccupyArea();

                    cell = row.createCell((short) 8);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCost());// �ʲ�ԭֵ
					cost += dto.getCost();

                    cell = row.createCell((short) 9);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getDeprnReserve());// �ۼ��۾�
					deprnReserve += dto.getDeprnReserve();

                    cell = row.createCell((short) 10);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getNetAssetValue());// �ʲ���ֵ
					netAssetValue += dto.getNetAssetValue();

                    rowIndex = rowIndex + 1;
				}
				row = mcSheet.createRow(rowIndex);
				cell = row.createCell((short) 1);
				cell.setCellStyle(cellStyle1);
				cell.setCellValue("�ϼ�");
				cell = row.createCell((short) 2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(assetNum);// �ʲ������ϼ�
				cell = row.createCell((short) 3);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseLandCertificateNum);// ����Ȩ֤�����ϼ�
                cell = row.createCell((short) 4);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseCertificateNum);// ���ݰ���Ȩ֤�����ϼ�
                cell = row.createCell((short) 5);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseArea);// ��������ϼ�
                cell = row.createCell((short) 6);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(landCertificateNum);// ���ذ���Ȩ֤�����ϼ�
                cell = row.createCell((short) 7);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(occupyArea);// ��������ϼ�
                cell = row.createCell((short) 8);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(cost);// �ʲ�ԭֵ�ϼ�
				cell = row.createCell((short) 9);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(deprnReserve);// �ۼ��۾ɺϼ�
				cell = row.createCell((short) 10);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(netAssetValue);// �ʲ���ֵ�ϼ�
                assetNum = 0;
                houseLandCertificateNum = 0;
                houseCertificateNum = 0;
                houseArea = 0.0;
                landCertificateNum = 0;
                occupyArea = 0.0;
				cost = 0.0;
				deprnReserve = 0.0;
				netAssetValue = 0.0;
            }
            */
			/** ********************** end ********************* */

            /** ************ ��ȡ��������վ���غ�һ����ͳ����Ϣ ************* 
//            long houseLandCertificateNum = 0;
//            long landCertificateNum = 0;
            DTOSet set5 = dao.getBtsHouseLandInfo(conn);
			if (set5 != null && !set5.isEmpty()) {
				size = set5.getSize();
				mcSheet = mcBook.getSheetAt(6);// �õ����߸�������:��վ���غ�һ
				// ��֯���ݲ�д��
				int rowIndex = 2;
				HSSFRow row = null;
				HSSFCell cell = null;
				AmshouseinfoDTO dto = null;
				for (int i = 0; i < size; i++) {
					dto = (AmshouseinfoDTO) set5.getDTO(i);
					if (!map.containsKey(dto.getCompany())) {
						dto1.setCompany(dto.getCompany());// ��˾
						dto1.setAssetnum(dto.getAssetnum());// �ʲ�����
						dto1.setLandnum(0);// ��վ��������
						dto1.setHousenum(0);// ��վ��������
						dto1.setOfficeLandnum(0);// �칫Ӫҵ��������
						dto1.setOfficeHousenum(0);// �칫Ӫҵ��������
                        dto1.setOfficeHouseLandNum(0);// �칫���غ�һ����
                        dto1.setHouseLandNum(dto.getAssetnum());// ��վ���غ�һ����
                        dto1.setHouseCertificateNum(dto.getHouseLandCertificateNum());// ����Ȩ֤����
						dto1.setHouseArea(dto.getHouseArea());// �������
						dto1.setOccupyArea(dto.getOccupyArea());// �������
						dto1.setCost(dto.getCost());// �ʲ�ԭֵ
						dto1.setDeprnReserve(dto.getDeprnReserve());// �ۼ��۾�
						dto1.setNetAssetValue(dto.getNetAssetValue());// �ʲ���ֵ
						map.put(dto.getCompany(), dto1);
					} else {
						dto1 = (AmshouseinfoDTO) map.get(dto.getCompany());//��˾
						dto1.setAssetnum(dto1.getAssetnum() + dto.getAssetnum());// �ʲ�����
						dto1.setHouseLandNum(dto.getAssetnum());// ��վ���غ�һ����
						dto1.setHouseCertificateNum(dto1.getHouseCertificateNum() + dto.getHouseLandCertificateNum());// ����Ȩ֤����
						dto1.setHouseArea(dto1.getHouseArea() + dto.getHouseArea());// �������
                        dto1.setOccupyArea(dto1.getOccupyArea() + dto.getOccupyArea());// �������
                        dto1.setCost(dto1.getCost() + dto.getCost());// �ʲ�ԭֵ
						dto1.setDeprnReserve(dto1.getDeprnReserve() + dto.getDeprnReserve());// �ۼ��۾�
						dto1.setNetAssetValue(dto1.getNetAssetValue() + dto.getNetAssetValue());// �ʲ���ֵ
						map.put(dto.getCompany(), dto1);
					}
					row = mcSheet.createRow(rowIndex);
					cell = row.createCell((short) 0);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(i + 1);// ���

                    cell = row.createCell((short) 1);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCompany());// ��˾

                    cell = row.createCell((short) 2);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getAssetnum());// �ʲ�����
					assetNum += dto.getAssetnum();

                    cell = row.createCell((short) 3);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseLandCertificateNum());// ����Ȩ֤����
					houseLandCertificateNum += dto.getHouseLandCertificateNum();

                    cell = row.createCell((short) 4);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseCertificateNum());// ���ݰ���Ȩ֤����
					houseCertificateNum += dto.getHouseCertificateNum();

                    cell = row.createCell((short) 5);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseArea());// �������
					houseArea += dto.getHouseArea();

                    cell = row.createCell((short) 6);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getLandCertificateNum());// ���ذ���Ȩ֤����
					landCertificateNum += dto.getLandCertificateNum();

                    cell = row.createCell((short) 7);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getOccupyArea());// �������
					occupyArea += dto.getOccupyArea();

                    cell = row.createCell((short) 8);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCost());// �ʲ�ԭֵ
					cost += dto.getCost();

                    cell = row.createCell((short) 9);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getDeprnReserve());// �ۼ��۾�
					deprnReserve += dto.getDeprnReserve();

                    cell = row.createCell((short) 10);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getNetAssetValue());// �ʲ���ֵ
					netAssetValue += dto.getNetAssetValue();

                    rowIndex = rowIndex + 1;
				}
				row = mcSheet.createRow(rowIndex);
				cell = row.createCell((short) 1);
				cell.setCellStyle(cellStyle1);
				cell.setCellValue("�ϼ�");
				cell = row.createCell((short) 2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(assetNum);// �ʲ������ϼ�
				cell = row.createCell((short) 3);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseLandCertificateNum);// ����Ȩ֤�����ϼ�
                cell = row.createCell((short) 4);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseCertificateNum);// ���ݰ���Ȩ֤�����ϼ�
                cell = row.createCell((short) 5);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseArea);// ��������ϼ�
                cell = row.createCell((short) 6);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(landCertificateNum);// ���ذ���Ȩ֤�����ϼ�
                cell = row.createCell((short) 7);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(occupyArea);// ��������ϼ�
                cell = row.createCell((short) 8);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(cost);// �ʲ�ԭֵ�ϼ�
				cell = row.createCell((short) 9);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(deprnReserve);// �ۼ��۾ɺϼ�
				cell = row.createCell((short) 10);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(netAssetValue);// �ʲ���ֵ�ϼ�
                assetNum = 0;
                houseLandCertificateNum = 0;
                houseCertificateNum = 0;
                houseArea = 0.0;
                landCertificateNum = 0;
                occupyArea = 0.0;
				cost = 0.0;
				deprnReserve = 0.0;
				netAssetValue = 0.0;
            }*/
			/** ********************** end ********************* */

            /** *********** ��ȡ�������������ػ���ͳ����Ϣ ********** */
			Set entrySet = map.keySet();
			if (entrySet != null && !entrySet.isEmpty()) {
				mcSheet = mcBook.getSheetAt(0);// �õ���һ��������:����
				int rowIndex = 2;
				Iterator it = entrySet.iterator();
				int i = 0;
				HSSFRow row = null;
				HSSFCell cell = null;
				AmshouseinfoDTO dto = null;
				long officeHousenum = 0;// �칫Ӫҵ��������
				long officeLandnum = 0;// �칫Ӫҵ��������
                long officeHouseLandnum = 0;//�칫Ӫҵ���غ�һ����
                long landnum = 0;// ��վ��������
				long housenum = 0;// ��վ��������
                long houseLandNum = 0;//��վ���غ�һ����

                while (it.hasNext()) {
					// ��֯���ݲ�д��
					dto = (AmshouseinfoDTO) map.get(it.next());
					row = mcSheet.createRow(rowIndex);
					cell = row.createCell((short) 0);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(++i);// ���

                    cell = row.createCell((short) 1);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCompany());// ��˾

                    cell = row.createCell((short) 2);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getAssetnum());// �ʲ�����
					assetNum += dto.getAssetnum();

                    cell = row.createCell((short) 3);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getOfficeHousenum());// �칫Ӫҵ��������
					officeHousenum += dto.getOfficeHousenum();

                    cell = row.createCell((short) 4);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getOfficeLandnum());// �칫Ӫҵ��������
					officeLandnum += dto.getOfficeLandnum();

                    cell = row.createCell((short) 5);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getOfficeHouseLandNum());// �칫Ӫҵ���غ�һ����
					officeHouseLandnum += dto.getOfficeHouseLandNum();

                    cell = row.createCell((short) 6);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHousenum());// ��վ��������
					housenum += dto.getHousenum();

                    cell = row.createCell((short) 7);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getLandnum());// ��վ��������
					landnum += dto.getLandnum();

                    cell = row.createCell((short) 8);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseLandNum());// ��վ���غ�һ����
					houseLandNum += dto.getHouseLandNum();

                    cell = row.createCell((short) 9);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseArea());// �������
					houseArea += dto.getHouseArea();

                    cell = row.createCell((short) 10);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getOccupyArea());// �������
					occupyArea += dto.getOccupyArea();

                    cell = row.createCell((short) 11);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getHouseCertificateNum());// ����Ȩ֤����
					houseCertificateNum += dto.getHouseCertificateNum();

                    cell = row.createCell((short) 12);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getCost());// �ʲ�ԭֵ
					cost += dto.getCost();

                    cell = row.createCell((short) 13);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getDeprnReserve());// �ۼ��۾�
					deprnReserve += dto.getDeprnReserve();

                    cell = row.createCell((short) 14);
					cell.setCellStyle(cellStyle);
					cell.setCellValue(dto.getNetAssetValue());// �ʲ���ֵ
					netAssetValue += dto.getNetAssetValue();

                    rowIndex = rowIndex + 1;
				}
				row = mcSheet.createRow(rowIndex);
				cell = row.createCell((short) 1);
				cell.setCellStyle(cellStyle1);
				cell.setCellValue("�ϼ�");
				cell = row.createCell((short) 2);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(assetNum);// �ʲ������ϼ�
				cell = row.createCell((short) 3);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(officeHousenum);// �칫Ӫҵ��������
				cell = row.createCell((short) 4);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(officeLandnum);// �칫Ӫҵ��������
				cell = row.createCell((short) 5);
				cell.setCellStyle(cellStyle);
                cell.setCellValue(officeHouseLandnum);// �칫Ӫҵ���غ�һ����
				cell = row.createCell((short) 6);
				cell.setCellStyle(cellStyle);
                cell.setCellValue(housenum);// ��վ��������
				cell = row.createCell((short) 7);
				cell.setCellStyle(cellStyle);
                cell.setCellValue(landnum);// ��վ��������
                cell = row.createCell((short) 8);
				cell.setCellStyle(cellStyle);
                cell.setCellValue(houseLandNum);// ��վ���غ�һ����
                cell = row.createCell((short) 9);
				cell.setCellStyle(cellStyle);
                cell.setCellValue(houseArea);// ��������ϼ�
				cell = row.createCell((short) 10);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(occupyArea);// �������
				cell = row.createCell((short) 11);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(houseCertificateNum);// ����Ȩ֤�����ϼ�
				cell = row.createCell((short) 12);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(cost);// �ʲ�ԭֵ�ϼ�
				cell = row.createCell((short) 13);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(deprnReserve);// �ۼ��۾ɺϼ�
				cell = row.createCell((short) 14);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(netAssetValue);// �ʲ���ֵ�ϼ�
			}
			mcBook.write(res.getOutputStream());// д������
			// ��Դ�ͷ�
			this.afterExport(res, ins, mcBook, mcSheet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void beforeExport(HttpServletRequest request,
			HttpServletResponse response, String exportFileName) {
		if (exportFileName == null || "".equals(exportFileName)) {
			exportFileName = request.getParameter("exportTmpName") + ".xls";// ���û�е���������ȡ����ģ������
		}
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename=\""
				+ exportFileName + "\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
	}

	protected void afterExport(HttpServletResponse response, InputStream ins,
			HSSFWorkbook wbook, HSSFSheet wsheet) throws Exception {
		wbook = null;
		wsheet = null;
		if (ins != null)
			ins.close();
		if (response != null) {
			if (response.getOutputStream() != null)
				response.getOutputStream().flush();
			if (response.getOutputStream() != null)
				response.getOutputStream().close();
		}
	}

	protected String getFile(HttpServletRequest request, String filename)
			throws Exception {
		if (filename == null || "".equals(filename))
			return "";
		else
			return request.getRealPath("/") + "/document/" + filename;
	}
}