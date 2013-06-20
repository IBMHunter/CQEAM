package com.sino.ams.spare.repair.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;

import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.spare.repair.model.AmsItemRepairModel;
import com.sino.ams.system.item.dto.EtsSystemItemDTO;
import com.sino.ams.system.user.dto.SfUserDTO;


/**
 * <p>Title: AmsWorkPlanDAO</p>
 * <p>Description:�����Զ����ɷ������AmsItemRepairDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author jiachuanchuan
 * @version 1.0
 */


public class AmsItemRepairDAO extends BaseDAO {

    private AmsItemRepairModel amsRepairModel = null;
    private SfUserDTO sfUser = null;
    private SQLModel sModel = null;

    /**
     * ���ܣ��豸ά�޴������ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsWorkPlanDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsItemRepairDAO(SfUserDTO userAccount, EtsSystemItemDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
        sModel = new SQLModel();
        initSQLProducer(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsSystemItemDTO dtoPara = (EtsSystemItemDTO) dtoParameter;
        super.sqlProducer = new AmsItemRepairModel((SfUserDTO) userAccount, dtoPara);
        amsRepairModel = (AmsItemRepairModel) sqlProducer;
    }

   public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "�豸������-������.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
//            fieldMap.put("ORG_NAME", "��˾");
            fieldMap.put("ITEM_NAME", "�豸����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
            fieldMap.put("QUANTITY", "���޴���");

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("�豸������-������");
            custData.setReportPerson(sfUser.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
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
     public File vendorFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "�豸������--������.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
//            fieldMap.put("ORG_NAME", "��˾");
            fieldMap.put("VENDOR_NAME", "����");
            fieldMap.put("ITEM_NAME", "�豸����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
            fieldMap.put("QUANTITY", "���޴���");

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("�豸������--������");
            custData.setReportPerson(sfUser.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
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

}