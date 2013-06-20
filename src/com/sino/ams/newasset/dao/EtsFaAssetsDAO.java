package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.model.EtsFaAssetsModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: EtsFaAssetsDAO</p>
 * <p>Description:�����Զ����ɷ������EtsFaAssetsDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class EtsFaAssetsDAO extends AMSBaseDAO {

    /**
     * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsAddressVDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EtsFaAssetsDAO(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsAddressVDTO dtoPara = (AmsAssetsAddressVDTO) dtoParameter;
        sqlProducer = new EtsFaAssetsModel((SfUserDTO) userAccount, dtoPara);
    }

    public String checkData() throws QueryException, ContainerException {
        String isTD = "N";
        EtsFaAssetsModel assetsModel = (EtsFaAssetsModel) sqlProducer;
        SQLModel sqlModel = assetsModel.getIsTDModel();
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        if (sq.hasResult()) {
            isTD = sq.getFirstRow().getStrValue(0);
        }
        return isTD;
    }

    /**
     * ���ܣ�����ѡ����ʲ���������
     * @param barcodes String[]
     * @return String ���ص���Excel�ļ�
     * @throws DataTransException
     */
    public File exportCheckedAssets(String[] barcodes) throws DataTransException {
        File file = null;
        EtsFaAssetsModel assetsModel = (EtsFaAssetsModel) sqlProducer;
        SQLModel sqlModel = assetsModel.getExpCheckedAssetsModel(barcodes);
        file = getExportFile(sqlModel);
        return file;
    }

    /**
     * ���ܣ�������ѯSQL�ʲ�����
     * @return String ���ص���Excel�ļ�
     * @throws DataTransException
     */
    public File exportQueryAssets() throws DataTransException {
        EtsFaAssetsModel assetsModel = (EtsFaAssetsModel) sqlProducer;
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
        } else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_DEPART)) { //�����ʲ�
            fieldMap = getAssetsMap();
        } else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_COMPAN)) { //��˾�ʲ�
            fieldMap = getAssetsMap();
        } else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_PROVIN)) { //ȫʡ�ʲ�
            fieldMap = getAssetsMap();
        } else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CONFIRM)) { //��ȷ���ʲ�
            fieldMap = getConfirmAssetsMap();
        } else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_TRANSFER)) { //�����ʲ�
            fieldMap = getTransferAssetsMap();
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
     * ���ܣ���ȡ��ȷ���ʲ��ĵ�����ͷ
     * @return Map
     */
    private static Map getConfirmAssetsMap() {
        return getTransferAssetsMap();
    }

    /**
     * ���ܣ���ȡ�����ʲ��ĵ�����ͷ
     * @return Map
     */
    private static Map getTransferAssetsMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("TRANS_NO", "��������");
        fieldMap.put("BARCODE", "�ʲ���ǩ");
        fieldMap.put("ASSET_NUMBER", "�ʲ����");
        fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
        fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
        fieldMap.put("COST", "�ʲ�ԭֵ");
        fieldMap.put("DEPRN_COST", "�ۼ��۾�");
        fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
        fieldMap.put("CURRENT_UNITS", "����");
        fieldMap.put("OLD_LOCATION_NAME", "ԭ�ص�");
        fieldMap.put("OLD_RESPONSIBILITY_USER_NAME", "ԭ������");
        fieldMap.put("OLD_RESPONSIBILITY_DEPT_NAME", "ԭ���β���");
        fieldMap.put("ASSIGNED_TO_LOCATION_NAME", "����ص�");
        fieldMap.put("RESPONSIBILITY_USER_NAME", "��������");
        fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���벿��");
        fieldMap.put("REMARK", "ժҪ");
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
        } else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_DEPART)) { //�����ʲ�����
            reportTitle.append(companyName);
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append(deptName);
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append("�ʲ�");
        } else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_COMPAN)) { //��˾�ʲ�����
            reportTitle.append(companyName);
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append(deptName);
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append("�ʲ�");
        } else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_PROVIN)) { //ȫʡ�ʲ�����
            reportTitle.append(servletConfig.getProCompanyName());
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append(AssetsWebAttributes.ASSETS_PROVIN);
            reportTitle.append(companyName);
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append(deptName);
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append("�ʲ�");
        } else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_CONFIRM)) { //��ȷ���ʲ�����
            reportTitle.append(companyName);
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append(userAccount.getUsername());
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append("��ȷ���ʲ�");
        } else if (treeCategory.equals(AssetsWebAttributes.ASSETS_TREE_TRANSFER)) { //�����ʲ�����
            reportTitle.append(companyName);
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append(deptName);
            reportTitle.append(WorldConstant.EMPTY_SPACE);
            reportTitle.append("�����ʲ�");
        }
        return reportTitle.toString();
	}
}
