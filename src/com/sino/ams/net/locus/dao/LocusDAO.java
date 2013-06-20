package com.sino.ams.net.locus.dao;


import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.net.locus.dto.LocusDTO;
import com.sino.ams.net.locus.model.LocusModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: LocusDAO</p>
 * <p>Description:�����Զ����ɷ������LocusDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author V-yuanshuai
 * @version 1.0
 */


public class LocusDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�LOCUS ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter LocusDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public LocusDAO(SfUserDTO userAccount, LocusDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        LocusDTO dtoPara = (LocusDTO) dtoParameter;
        super.sqlProducer = new LocusModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ�����Excel�ļ���
     *
     * @return File
     * @throws com.sino.base.exception.DataTransException
     *
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

            String fileName = "�ص�ͳ�Ʊ�.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���");
            fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
            fieldMap.put("WORKORDER_OBJECT_LOCATION", "���ڵص�");
            fieldMap.put("ORGANIZATION_ID", "��֯ID");
            fieldMap.put("COUNTY_NAME", "��������");
            fieldMap.put("ISALL", "Ѳ��ģʽ");
            fieldMap.put("DISABLE_DATE", "ʧЧ����");

//		fieldMap.put("IS_TEMP_ADDR", "�Ƿ���ʱ�ص�");
//			fieldMap.put("CREATION_DATE", "��������");
//			fieldMap.put("CREATED_BY", "������");
//			fieldMap.put("PROJECT_NAME", "��������");

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