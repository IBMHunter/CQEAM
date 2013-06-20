package com.sino.ams.yj.dao;

import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.WorldConstant;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.yj.dto.AmsYjDrillDTO;
import com.sino.ams.yj.model.AmsYjDrillModel;

/**
 * <p>Title: AmsYjDrillDAO</p>
 * <p>Description:�����Զ����ɷ������AmsYjDrillDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ���������ά��
 */


public class AmsYjDrillDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ�Ӧ����������� AMS_YJ_DRILL ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsYjDrillDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsYjDrillDAO(SfUserDTO userAccount, AmsYjDrillDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		AmsYjDrillDTO dtoPara = (AmsYjDrillDTO)dtoParameter;
		super.sqlProducer = new AmsYjDrillModel((SfUserDTO)userAccount, dtoPara);
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

                String fileName = "Ӧ�����������Ϣ.xls";
                String filePath = WorldConstant.USER_HOME;
                filePath += WorldConstant.FILE_SEPARATOR;
                filePath += fileName;
                rule.setTarFile(filePath);

                DataRange range = new DataRange();
                rule.setDataRange(range);

                Map fieldMap = new HashMap();
                fieldMap.put("ORGANIZATION_NAME", "��˾����");
                fieldMap.put("DRILL_ID", "���");
                fieldMap.put("DRILL_NAME", "��������");
                fieldMap.put("DRILL_TYPE", "��������");
                fieldMap.put("DRILL_NATURE", "��������");
                fieldMap.put("DRILL_DATE", "����ʱ��");
                fieldMap.put("DRILL_ADDRESS", "�����ص�");
                fieldMap.put("PEOPLE_QUALITY", "��������");
                fieldMap.put("EQUIPMENT_QUANTITY", "����װ������");
                fieldMap.put("PLAN", "����Ԥ��");
                fieldMap.put("QUESTION", "������������");
                fieldMap.put("IS_PERFECT", "�Ƿ���Ҫ����Ԥ��");
                fieldMap.put("PLAN_DATE", "����Ԥ���ƻ�ʱ��");
                fieldMap.put("REMARK", "��ע");

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