package com.sino.ams.net.statistic.dao;


import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.net.statistic.dto.EquipStatDTO;
import com.sino.ams.net.statistic.model.EquipStatModel;
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
 * <p>Title: EquipStatDAO</p>
 * <p>Description:�����Զ����ɷ������EquipStatDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author V-yuanshuai
 * @version 1.0
 */


public class EquipStatDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�EQUIP_STAT ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EquipStatDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EquipStatDAO(SfUserDTO userAccount, EquipStatDTO dtoParameter, Connection conn) {
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
        EquipStatDTO dtoPara = (EquipStatDTO) dtoParameter;
        super.sqlProducer = new EquipStatModel((SfUserDTO) userAccount, dtoPara);
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
            EquipStatDTO dtoPara = (EquipStatDTO) dtoParameter;
            String qryType = dtoPara.getQryType();
            String fileName = "";
            Map fieldMap = new HashMap();
            if (qryType.equals(WebAttrConstant.BY_LOCUS)) {
                fileName = "�豸������--���ص�.xls";
                fieldMap.put("ORGANIZATION_NAME", "��˾");
                fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
                fieldMap.put("WORKORDER_OBJECT_LOCATION", "���ڵص�");
                fieldMap.put("ITEM_CATEGORY", "�豸���");
                fieldMap.put("ITEM_NAME", "�豸����");
                fieldMap.put("ITEM_SPEC", "����ͺ�");
                fieldMap.put("CNT", "������");
            } else if (qryType.equals(WebAttrConstant.BY_CATEGORY)) {
                fileName = "�豸������--������.xls";
                fieldMap.put("ORGANIZATION_NAME", "��˾");
                fieldMap.put("ITEM_CATEGORY", "�豸���");
                fieldMap.put("ITEM_NAME", "�豸����");
                fieldMap.put("ITEM_SPEC", "����ͺ�");
                fieldMap.put("CNT", "������");
            } else if (qryType.equals(WebAttrConstant.BY_VENDOR)) {
                fileName = "�豸������--������.xls";
                fieldMap.put("VENDOR_NAME", "��Ӧ��");
                fieldMap.put("ITEM_NAME", "�豸����");
                fieldMap.put("ITEM_SPEC", "����ͺ�");
                fieldMap.put("CNT", "������");
            }   else if (qryType.equals(WebAttrConstant.BY_NAME)) {
                fileName = "ȫʡͳ��--���ص�.xls";
//                fieldMap.put("ORGANIZATION_NAME", "��˾");
                fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
//                fieldMap.put("WORKORDER_OBJECT_LOCATION", "���ڵص�");
//                fieldMap.put("ITEM_CATEGORY", "�豸���");
                fieldMap.put("ITEM_NAME", "�豸����");
                fieldMap.put("ITEM_SPEC", "����ͺ�");
                fieldMap.put("CNT", "������");
            } else if (qryType.equals(WebAttrConstant.BY_CATEGORY+ "2")) {
                fileName = "ȫʡͳ��--��״̬.xls";
                fieldMap.put("ORGANIZATION_NAME", "��˾");
                fieldMap.put("ITEM_CATEGORY", "�豸���");
                fieldMap.put("ITEM_NAME", "�豸����");
                fieldMap.put("ITEM_SPEC", "����ͺ�");
                fieldMap.put("CNT", "������");
            } else if (qryType.equals(WebAttrConstant.BY_VENDOR + "2")) {
                fileName = "ȫʡͳ��--������.xls";
                fieldMap.put("VENDOR_NAME", "��Ӧ��");
                fieldMap.put("ITEM_NAME", "�豸����");
                fieldMap.put("ITEM_SPEC", "����ͺ�");
                fieldMap.put("CNT", "������");
            }

            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

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