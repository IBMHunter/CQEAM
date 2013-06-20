package com.sino.ams.newasset.rent.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.rent.model.AssetsQueryModel;
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
import com.sino.framework.dto.BaseUserDTO;

public class AssetsQueryDAO extends AMSBaseDAO {
	private SfUserDTO sfUser = null;

    /**
     * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsAddressVDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AssetsQueryDAO(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsAddressVDTO dtoPara = (AmsAssetsAddressVDTO) dtoParameter;
        sqlProducer = new AssetsQueryModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ�������ѯSQL�ʲ�����
     * @return String ���ص���Excel�ļ�
     * @throws DataTransException
     * @throws SQLModelException 
     */
    public File exportQueryAssets() throws DataTransException, SQLModelException {
    	AssetsQueryModel assetsModel = (AssetsQueryModel) sqlProducer;
        SQLModel sqlModel = assetsModel.getPageQueryModel();
        return getExportFile(sqlModel);
    }

    /**
     * ���ܣ�����SQL�����ļ�
     * @param sqlModel SQLModel
     * @return File
     * @throws DataTransException
     */
    private File getExportFile(SQLModel sqlModel) throws DataTransException {
        File file = null;
        String reportTitle = getFormatTitle();
        String fileName = reportTitle + ".xls";
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setSourceConn(conn);
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
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
//		rule.setSheetSize(2000);
        rule.setCalPattern(LINE_PATTERN);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        file = (File) transfer.getTransResult();
        return file;
    }

    private Map getFieldMap() {
        Map fieldMap = null;
        AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
        String treeCategory = dto.getTreeCategory();
        if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_PERSON)) { //�����ʲ�
            fieldMap = getAssetsMap();
        }
        return fieldMap;
    }

    /**
     * ���ܣ���ȡ�����ʲ��ĵ�����ͷ
     * @return Map
     */
    private static Map getAssetsMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("BARCODE", "�ʲ���ǩ");
        fieldMap.put("ASSET_NUMBER", "�ʲ����");
        fieldMap.put("FA_CATEGORY1", "�ʲ����һ");
        fieldMap.put("FA_CATEGORY2", "�ʲ�����");
        fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
        fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
        fieldMap.put("UNIT_OF_MEASURE", "������λ");
        fieldMap.put("ITEM_STATUS", "�ʲ�״̬");
        fieldMap.put("CURRENT_UNITS", "����");
        fieldMap.put("COST", "�ʲ�ԭֵ");
        fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
        fieldMap.put("LIFE_IN_YEARS", "��������");
        fieldMap.put("DEPRECIATION", "�ۼ��۾�");
        fieldMap.put("DEPRN_COST", "�ʲ���ֵ");
        fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
        fieldMap.put("DEPRECIATION_ACCOUNT", "�۾��˻�����");
        fieldMap.put("BOOK_TYPE_CODE", "�ʲ��˲�");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
        fieldMap.put("WORKORDER_OBJECT_NAME", "���ڵص�");
        fieldMap.put("PROJECT_NAME", "��Ŀ����");
        fieldMap.put("RESPONSIBILITY_USER_NAME", "������");
        fieldMap.put("DEPT_NAME", "���β���");
        fieldMap.put("MAINTAIN_USER_NAME", "������");
        fieldMap.put("ITEM_STATUS_NAME", "�ʲ�״̬");
        fieldMap.put("EMPLOYEE_NUMBER", "Ա���ű�");
        fieldMap.put("ASSETS_CREATE_DATE", "�ʲ���������");
        fieldMap.put("DEPRECIATION_ACCOUNT_NAME", "�۾��˻�����");
        return fieldMap;
    }

    /**
     * ���ܣ����쵼��Excel�ı���
     * @return String
     */
    private String getFormatTitle() {
        StringBuffer reportTitle = new StringBuffer();
        AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
        String treeCategory = dto.getTreeCategory();
        String companyName = dto.getCompanyName();
        String deptName = dto.getDeptName();
        String userName = userAccount.getUsername();
        if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_PERSON)) { //�����ʲ�����
            reportTitle.append(userAccount.getCompany());
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append(userName);
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append("�ʲ�");
        }
        return reportTitle.toString();
	}
    
    public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "����δ�����ʲ�.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);
            //"�ʲ�����", "�ʲ��ͺ�", "��ǩ��", "�ʲ�״̬","�ص����","�ص�����","������","Ա����","���β���","ʹ����"
            Map fieldMap = new HashMap();
            fieldMap.put("ITEM_NAME", "�ʲ�����");
            fieldMap.put("ITEM_SPEC", "�ʲ��ͺ�");
            fieldMap.put("BARCODE", "��ǩ��");
            fieldMap.put("ITEM_STATUS", "�ʲ�״̬");
            fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
            fieldMap.put("WORKORDER_OBJECT_NAME", "�ص�����");
            fieldMap.put("RESPONSIBILITY_USER_NAME", "������");
            fieldMap.put("EMPLOYEE_NUMBER", "Ա����");
            fieldMap.put("DEPT_NAME", "���β���");
            fieldMap.put("MAINTAIN_USER_NAME", "ʹ����");
            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("����δ�����ʲ�");
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
