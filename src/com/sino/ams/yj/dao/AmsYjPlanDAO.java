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
import com.sino.ams.yj.dto.AmsYjPlanDTO;
import com.sino.ams.yj.model.AmsYjPlanModel;

/**
 * <p>Title: AmsYjPlanDAO</p>
 * <p>Description:�����Զ����ɷ������AmsYjPlanDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ��Ԥ����ϵά��
 */


public class AmsYjPlanDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ�Ӧ��Ԥ����ϵ�� AMS_YJ_PLAN ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsYjPlanDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsYjPlanDAO(SfUserDTO userAccount, AmsYjPlanDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		AmsYjPlanDTO dtoPara = (AmsYjPlanDTO)dtoParameter;
		super.sqlProducer = new AmsYjPlanModel((SfUserDTO)userAccount, dtoPara);
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

                String fileName = "Ӧ��Ԥ����ϵ��Ϣ.xls";
                String filePath = WorldConstant.USER_HOME;
                filePath += WorldConstant.FILE_SEPARATOR;
                filePath += fileName;
                rule.setTarFile(filePath);

                DataRange range = new DataRange();
                rule.setDataRange(range);

                Map fieldMap = new HashMap();
                fieldMap.put("ORGANIZATION_NAME", "��˾����");
                fieldMap.put("PLAN_ID", "���");
                fieldMap.put("PLAN_NAME", "Ԥ������");
                fieldMap.put("PLAN_LEVEL", "Ԥ������");
                fieldMap.put("PRO_CITY", "ʡ�����");
                fieldMap.put("PLAN_NO", "Ԥ�����");
                fieldMap.put("PLAN_TYPE", "Ԥ�����");
                fieldMap.put("PRINT_DATE", "ӡ��ʱ��");
                fieldMap.put("KNOW_POST", "֪����Χ(ְλ/��λ)");
                fieldMap.put("QUANTITY", "֪���˵�����");
                fieldMap.put("LEADER_POST", "Ԥ�����������˵ĸ�λ/ְλ");
                fieldMap.put("IS_DRILL", "��Ԥ���Ƿ�������");
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

    public File exportPlan() throws DataTransException {
        File file = null;

        DataTransfer transfer = null;
        SQLModel sqlModel = ((AmsYjPlanModel) sqlProducer).getPlanStat();
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setCalPattern(CalendarConstant.LINE_PATTERN);
        rule.setSourceConn(conn);

        String fileName = "Ӧ��Ԥ����ϵͳ��.xls";
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);

        DataRange range = new DataRange();
        rule.setDataRange(range);

        Map fieldMap = new HashMap();
        fieldMap.put("ORGANIZATION_NAME", "��˾����");
        fieldMap.put("ZT", "����Ԥ��");
        fieldMap.put("ZX", "ר��Ԥ��");
        fieldMap.put("YJ", "Ӧ������");

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

        return file;
    }
}