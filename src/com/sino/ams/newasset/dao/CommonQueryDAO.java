package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.model.CommonQueryModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: �й��ƶ��ʲ�ʵ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class CommonQueryDAO extends AMSBaseDAO {

    public CommonQueryDAO(SfUserDTO userAccount,
                          AmsAssetsAddressVDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
     * @param userAccount BaseUserDTO
     * @param dtoParameter DTO
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsAddressVDTO dtoPara = (AmsAssetsAddressVDTO) dtoParameter;
        super.sqlProducer = new CommonQueryModel((SfUserDTO) userAccount,
                                                 dtoPara);
    }

    public File getExportFile() throws DataTransException {
        File file = null;
        try {
            CommonQueryModel assetsModel = (CommonQueryModel) sqlProducer;
            SQLModel sqlModel = assetsModel.getPageQueryModel();
            String reportTitle = "�ۺϲ�ѯ�����ʲ�";
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
            Map fieldMap = getFieldMap();
            rule.setFieldMap(fieldMap);
            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(reportTitle);
            custData.setReportPerson(userAccount.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
//			rule.setSheetSize(2000);
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

    private Map getFieldMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("BARCODE", "�ʲ���ǩ");
        fieldMap.put("ASSET_NUMBER", "�ʲ����");
        fieldMap.put("FA_CATEGORY1", "�ʲ����һ");
        fieldMap.put("FA_CATEGORY2", "�ʲ�����");
        fieldMap.put("SEGMENT1", "������һ");
        fieldMap.put("SEGMENT2", "�������");
        fieldMap.put("FA_CATEGORY_CODE", "������");
        fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
        fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
        fieldMap.put("ITEM_CATEGORY_NAME", "�豸����");
        fieldMap.put("ITEM_NAME", "�豸����");
        fieldMap.put("ITEM_SPEC", "�豸�ͺ�");
        fieldMap.put("UNIT_OF_MEASURE", "������λ");
        fieldMap.put("CURRENT_UNITS", "����");
        fieldMap.put("COST", "�ʲ�ԭֵ");
        fieldMap.put("LIFE_IN_YEARS", "��������");
        fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
        fieldMap.put("DEPRN_COST", "�ʲ���ֵ");
        fieldMap.put("DEPRECIATION", "�ۼ��۾�");
        fieldMap.put("DEPRECIATION_ACCOUNT", "�۾��˻�");
        fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
        fieldMap.put("BOOK_TYPE_CODE", "�ʲ��˲�");
        fieldMap.put("PROJECT_NUMBER", "��Ŀ���");
        fieldMap.put("PROJECT_NAME", "��Ŀ����");
        fieldMap.put("VENDOR_NUMBER", "���ұ��");
        fieldMap.put("VENDOR_NAME", "��������");
        fieldMap.put("ITEM_STATUS_NAME", "�ʲ�״̬");
        fieldMap.put("DEPT_NAME", "���β���");
        fieldMap.put("RESPONSIBILITY_USER_NAME", "������");
        fieldMap.put("EMPLOYEE_NUMBER", "������Ա����");
        fieldMap.put("MAINTAIN_USER_NAME", "������");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
        fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
        fieldMap.put("WORKORDER_OBJECT_LOCATION", "���ڵص�");
        fieldMap.put("COUNTY_NAME", "��������");
        fieldMap.put("COMPANY", "������˾");
        return fieldMap;
    }
}
