package com.sino.ams.newasset.report.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.newasset.report.dto.AssetsInDataReportDTO;
import com.sino.ams.newasset.report.model.AssetsInDataReportModel;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.data.RowSet;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.DataTransException;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.datatrans.*;
import com.sino.base.constant.WorldConstant;

import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-5-16
 * Time: 11:26:47
 * To change this template use File | Settings | File Templates.
 */
public class AssetsInDataReportDAO extends AMSBaseDAO {

	public AssetsInDataReportDAO(SfUserDTO userAccount, AssetsInDataReportDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 *
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		AssetsInDataReportDTO dto = (AssetsInDataReportDTO) dtoParameter;
		sqlProducer = new AssetsInDataReportModel(user, dto);
	}

    /**
	 * ���ܣ�����ģ����ʲ�ģ������׼ȷ��/�̶��ʲ��ر���/�̶��ʲ���ת��
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File getExportFile(AssetsInDataReportDTO dto) throws DataTransException {
		File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
            String reportTitle = "";
            if (dto.getManagerGuideType().equals("TRUN_RATE")) {
                reportTitle = "ת���ʱ���";
            } else if (dto.getManagerGuideType().equals("IN_TIME_RATE")) {
                reportTitle = "���߷��������ϱ���ʱ�ʱ���";
            } else if (dto.getManagerGuideType().equals("NICETY_RATE")) {
                reportTitle = "ת����Ϣ׼ȷ�ʱ���";
            } else if (dto.getManagerGuideType().equals("CHECK_RATE")) {
                reportTitle = "�����������ʱ���";
            } else if (dto.getManagerGuideType().equals("MATCH_CASE_RATE")) {
                reportTitle = "����̵��ʲ���ʵ����ʱ���";
            } else if (dto.getManagerGuideType().equals("COP_RATE")) {
                reportTitle = "�ճ�Ѳ���ʲ��̵�����ʱ���";
            } else if (dto.getManagerGuideType().equals("COP_MATCH_RATE")) {
                reportTitle = "�ճ�Ѳ���ʲ��̵���ʵ����ʱ���";
            } else if (dto.getManagerGuideType().equals("ACCOUNTING_ACCURATE")) {
                reportTitle = "�ʲ�����׼ȷ�ʱ���";
            }
			String fileName = reportTitle + ".xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
            rule.setFieldMap(getFieldMap(dto));
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
			throw new DataTransException(ex);
		}
		return file;
	}

    private Map getFieldMap(AssetsInDataReportDTO dto){
		Map fieldMap = new HashMap();
        if (dto.getManagerGuideType().equals("TRUN_RATE")) {
            fieldMap.put("COMPANY", "��˾");
            fieldMap.put("PERIOD", "����ڼ�");
            fieldMap.put("PROJECT_TRUN_ASSETS", "�������ڹ���ת�ʶ�");
            fieldMap.put("PROJECT_SUM_ASSETS", "�����ۼ�Ͷ���ܶ�");
            fieldMap.put("ASSETS_RATE", "ת����");
        } else if(dto.getManagerGuideType().equals("IN_TIME_RATE")) {
            fieldMap.put("PERIOD", "����ڼ�");
            fieldMap.put("NO_TIMELY_REPORT_NUM", "δ��ʱ�ϱ�����");
            fieldMap.put("ASSETSMENT_REPORT_NUM", "������Ӧ�ϱ�����");
            fieldMap.put("ASSETS_RATE", "���߷��������ϱ���ʱ��");
        } else if (dto.getManagerGuideType().equals("NICETY_RATE")) {
            fieldMap.put("PERIOD", "����ڼ�");
            fieldMap.put("ASSETSMENT_FALSE_NUM", "�������ڷ�����ת���ʲ���׼ȷ������");
            fieldMap.put("ASSETSMENT_ASSETS_SUM", "��������ת���ʲ�����");
            fieldMap.put("ASSETS_RATE", "ת����Ϣ׼ȷ��");
        } else if (dto.getManagerGuideType().equals("CHECK_RATE")) {
            fieldMap.put("COMPANY", "��˾");
            fieldMap.put("PERIOD", "����ڼ�");
            fieldMap.put("COMPLETE_CHECK_NUM", "����̵����񹤵�����");
            fieldMap.put("PLAN_CHECK_NUM", "�ƻ��涨�ĳ���̵����񹤵�����");
            fieldMap.put("ASSETS_RATE", "������������");
        } else if (dto.getManagerGuideType().equals("MATCH_CASE_RATE")) {
            fieldMap.put("COMPANY", "��˾");
            fieldMap.put("PERIOD", "����ڼ�");
            fieldMap.put("ACCOUNT_MATCH_CASE", "�������ʵ������ʲ�����");
            fieldMap.put("CHECK_ASSETS_SUM", "����ʲ�������");
            fieldMap.put("ASSETS_RATE", "����̵��ʲ���ʵ�����");
        } else if (dto.getManagerGuideType().equals("COP_RATE")) {
            fieldMap.put("COMPANY", "��˾");
            fieldMap.put("PERIOD", "����ڼ�");
            fieldMap.put("ASSETS_COP_NUM", "����ɵ��ճ�Ѳ���̵�Ĺ�����");
            fieldMap.put("ASSETS_COM_SUM", "�ƻ����ճ�Ѳ���̵㹤������");
            fieldMap.put("ASSETS_RATE", "�ճ�Ѳ���ʲ��̵������");
        } else if (dto.getManagerGuideType().equals("COP_MATCH_RATE")) {
            fieldMap.put("COMPANY", "��˾");
            fieldMap.put("PERIOD", "����ڼ�");
            fieldMap.put("ASSETS_MATCH_CASE", "�̵�����ʵ������ʲ�����");
            fieldMap.put("ASSETS_CHECK_SUM", "�̵��ʲ�������");
            fieldMap.put("ASSETS_RATE", "�ճ�Ѳ���ʲ��̵���ʵ�����");
        } else if (dto.getManagerGuideType().equals("ACCOUNTING_ACCURATE")) {
            fieldMap.put("COMPANY", "��˾");
            fieldMap.put("PERIOD", "����ڼ�");
            fieldMap.put("ACCURATE_ERROR_NUMBER", "�ʲ�������صĲ�����");
        }
        return fieldMap;
	}
}
