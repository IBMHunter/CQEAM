package com.sino.ams.newasset.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.newasset.model.CheckDataQueryModel;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.WorldConstant;

import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-6-2
 * Time: 11:33:02
 * To change this template use File | Settings | File Templates.
 */
public class CheckDataQueryDAO extends AMSBaseDAO {
    private SfUserDTO sfUser = null;

	/**
	 * ���ܣ��ʲ��ص��(AMS) ETS_OBJECT ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public CheckDataQueryDAO(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		AmsAssetsCheckHeaderDTO dtoPara = (AmsAssetsCheckHeaderDTO)dtoParameter;
        sqlProducer = new CheckDataQueryModel((SfUserDTO)userAccount, dtoPara);
    }

 /**
	 * ���ܣ�����Excel�ļ���
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File exportFile() throws DataTransException {
		File file = null;
		try {
			DataTransfer transfer = null;
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
			String fileName = "�����̵��ʲ���Ϣ��ѯ.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			Map fieldMap = new HashMap();
			fieldMap.put("TRANS_NO", "�������");
			fieldMap.put("BARCODE", "��ǩ��");
			fieldMap.put("COMPANY_NAME", "�̵㹫˾");
			fieldMap.put("GROUPNAME", "�µ����");
            fieldMap.put("LOCATION_CODE", "�ص����");
            fieldMap.put("CHECK_LOCATION", "�̵�ص�");
			fieldMap.put("START_TIME", "��ʼ����");
			fieldMap.put("IMPLEMENT_USER", "ִ����");
            fieldMap.put("ARCHIVED_USER", "�鵵��");
            fieldMap.put("IMPLEMENT_DAYS", "ִ������(��)");
            fieldMap.put("ORDER_STATUS", "����״̬");

            rule.setFieldMap(fieldMap);
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(fileName);
			custData.setReportPerson(sfUser.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			/*rule.setSheetSize(1000);*/
			//���÷�ҳ��ʾ
			TransferFactory factory = new TransferFactory();
			transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}

}
