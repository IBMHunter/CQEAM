package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.dto.AmsItemCorrectLogDTO;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.ams.newasset.model.ItemMaintainModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.dto.ServletConfigDTO;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ItemMaintainDAO extends AMSBaseDAO {
    private AmsItemCorrectLogDAO logDAO = null;
    private SimpleQuery simp = null;

    public ItemMaintainDAO(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        logDAO = new AmsItemCorrectLogDAO(userAccount, null, conn);
    }

    /**
     * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
     * @param userAccount  BaseUserDTO
     * @param dtoParameter DTO
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
        sqlProducer = new ItemMaintainModel((SfUserDTO) userAccount, dto);
    }

    public boolean updateItems(String[] barcodeNos) {
        boolean operateResult = false;
        boolean autoCommit = true;
        String barcodes = "";
        try {
            AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String currTime = CalendarUtil.getCurrCalendar(CAL_PATT_45);
            String remark = userAccount.getUsername()
                    + "��"
                    + currTime
                    + "ͨ��ʵ��̨��ά������";
            for (int i = 0; i < barcodeNos.length; i++) {
                barcodes += barcodeNos[i] + ",";
                dto.setBarcode(barcodeNos[i]);
                dto.setRemark(remark);
                setDTOParameter(dto);
                logDAO.setDTOParameter(getLogDTO());
                updateData();
                logDAO.createData();
            }
            operateResult = true;
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (QueryException ex) {
            ex.printLog();
        } finally {
            try {
                if (operateResult) {
                    prodMessage(AssetsMessageKeys.ITEM_UPDATE_SUCCESS);
                    conn.commit();
                } else {
                    prodMessage(AssetsMessageKeys.ITEM_UPDATE_FAILURE);
                    conn.rollback();
                }
                message.setIsError(!operateResult);
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
            }
        }
        return operateResult;
    }

    /**
     * ���ܣ�����̨��ά����־
     * @return AmsItemCorrectLogDTO
     * @throws QueryException
     */
    private AmsItemCorrectLogDTO getLogDTO() throws QueryException {
        AmsItemCorrectLogDTO logDTO = new AmsItemCorrectLogDTO();
        try {
            ItemMaintainModel modelProducer = (ItemMaintainModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getPrimaryKeyDataModel();
            if (simp == null) {
                simp = new SimpleQuery(sqlModel, conn);
                simp.setDTOClassName(AmsAssetsAddressVDTO.class.getName());
                simp.setCalPattern(LINE_PATTERN);
            } else {
                simp.setSql(sqlModel);
            }
            simp.executeQuery();
            if (simp.hasResult()) {
                AmsAssetsAddressVDTO oldDTO = (AmsAssetsAddressVDTO) simp.getFirstDTO();
                AmsAssetsAddressVDTO newDTO = (AmsAssetsAddressVDTO) dtoParameter;
                logDTO = new AmsItemCorrectLogDTO();
                logDTO.setBarcode(newDTO.getBarcode());
                StringBuffer correctContent = new StringBuffer();
                String[] fieldNames = {"ITEM_CODE", "ITEM_CATEGORY", "ITEM_CATEGORY_NAME", "ITEM_NAME",
                        "ITEM_SPEC", "ADDRESS_ID", "WORKORDER_OBJECT_CODE", "WORKORDER_OBJECT_NAME",
                        "RESPONSIBILITY_USER", "EMPLOYEE_NUMBER", "RESPONSIBILITY_USER_NAME", "RESPONSIBILITY_DEPT",
                        "RESPONSIBILITY_DEPT_NAME", "MAINTAIN_USER", "MAINTAIN_DEPT_NAME", "START_DATE"};
                String[] fieldDescs = {"�豸�������", "�豸רҵ����", "�豸רҵ����", "�豸����",
                        "�豸�ͺ�", "�ص�ID", "�ص����", "�ص�����",
                        "������Ա��ID", "������Ա�����", "����������", "���β��Ŵ���",
                        "���β�������", "ʹ����", "ʹ�ò���", "��������"};
                String fieldName = "";
                String javaField = "";
                String oldValue = "";
                String newValue = "";
                for (int i = 0; i < fieldNames.length; i++) {
                    fieldName = fieldNames[i];
                    javaField = StrUtil.getJavaField(fieldName);
                    oldValue = String.valueOf(ReflectionUtil.getProperty(oldDTO, javaField));
                    if (oldValue.equals("")) {
                        oldValue = "��";
                    }
                    newValue = String.valueOf(ReflectionUtil.getProperty(newDTO, javaField));
                    if (!oldValue.equals(newValue) && !newValue.equals("")) {
                        correctContent.append(fieldDescs[i]);
                        correctContent.append(":");
                        correctContent.append(oldValue);
                        correctContent.append("-->>");
                        correctContent.append(newValue);
                        correctContent.append(WorldConstant.ENTER_CHAR);
                    }
                }
                logDTO.setCorrectContent(correctContent.toString());
            }
        } catch (ReflectException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return logDTO;
    }

    public File getExportFile(ServletConfigDTO servletConfig,String excelType) throws DataTransException, SQLModelException {
        ItemMaintainModel modelProducer = (ItemMaintainModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getPageQueryModel();
        String reportTitle = "ʵ��̨��ά��";
        if (!StrUtil.isNotEmpty(excelType)) {
			excelType = "xls";
		}
		String fileName = reportTitle + "." + excelType;
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setSourceConn(conn);
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);
        DataRange range = new DataRange();
        rule.setDataRange(range);
        rule.setFieldMap(getFieldMap(servletConfig));
        CustomTransData custData = new CustomTransData();
        custData.setReportTitle(reportTitle);
        custData.setReportPerson(userAccount.getUsername());
        custData.setNeedReportDate(true);
        rule.setCustData(custData);
        rule.setCalPattern(LINE_PATTERN);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        return (File) transfer.getTransResult();
    }

    private Map getFieldMap(ServletConfigDTO servletConfig) {
        Map fieldMap = new HashMap();
        fieldMap.put("BARCODE", "��ǩ��");
        fieldMap.put("ITEM_CATEGORY_NAME", "�豸רҵ");
        fieldMap.put("ITEM_NAME", "�豸����");
        fieldMap.put("ITEM_SPEC", "����ͺ�");
        fieldMap.put("ITEM_UNIT", "������λ");
        fieldMap.put("ACTUAL_QTY", "ʵ������");
        fieldMap.put("FINANCE_PROP_VALUE", "�ʲ�����");
        fieldMap.put("ITEM_STATUS_NAME", "�豸״̬");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���");
        fieldMap.put("WORKORDER_OBJECT_NAME", "�ص�����");
//        fieldMap.put("CITY", "������");
//        fieldMap.put("COUNTY", "������");
//        fieldMap.put("AREA_TYPE_NAME", "��������");
        fieldMap.put("RESPONSIBILITY_USER_NAME", "������");
        fieldMap.put("EMPLOYEE_NUMBER", "Ա�����");
        fieldMap.put("DEPT_CODE", "���β��Ŵ���");
        fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���β���");
        fieldMap.put("MAINTAIN_USER", "ʹ����");
        fieldMap.put("MAINTAIN_DEPT_NAME", "ʹ�ò���");
        fieldMap.put("SPECIALITY_DEPT_NAME", "ʵ�ﲿ��");
        fieldMap.put("PROJECT_NAME", "��Ŀ����");
        fieldMap.put("PROJECT_NUMBER", "��Ŀ���");
        fieldMap.put("MANUFACTURER_NAME", "��������");
        fieldMap.put("CONTENT_CODE", "�ʲ�Ŀ¼����");
        fieldMap.put("CONTENT_NAME", "�ʲ�Ŀ¼����");
        fieldMap.put("IS_SHARE", "�Ƿ���");
        fieldMap.put("CONSTRUCT_STATUS", "�Ƿ񹲽�");
        fieldMap.put("LOG_NET_ELE", "�߼�����Ԫ��");
        fieldMap.put("INVEST_CAT_NAME", "Ͷ�ʷ���");
        fieldMap.put("OPE_NAME", "ҵ��ƽ̨");
        fieldMap.put("LNE_NAME", "������");
        fieldMap.put("LIFE_IN_YEARS", "�ʲ�ʹ������");
        fieldMap.put("DATE_PLACED_IN_SERVICE", "�ʲ���������");
        fieldMap.put("ASSETS_CREATE_DATE", "�ʲ���������");
        fieldMap.put("ORIGINAL_COST", "�ʲ�ԭֵ");
        fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
        fieldMap.put("IMPAIR_RESERVE", "�ʲ���ֵ׼��");
        fieldMap.put("DEPRN_RESERVE", "�ʲ��ۼ��۾�");
        fieldMap.put("DEPRN_COST", "�ʲ�����");
        fieldMap.put("SEGMENT2", "�ʲ�������");
        fieldMap.put("FA_CATEGORY2", "�ʲ����");
        fieldMap.put("REMARK", "��ע");
        fieldMap.put("REMARK1", "��עһ");
        fieldMap.put("REMARK2", "��ע��");
        return fieldMap;
    }

    public int checkItemStatus(String[] barcodes, String itemStatus) throws QueryException, SQLModelException {
        int count = 0;
        ItemMaintainModel modelProducer = (ItemMaintainModel) sqlProducer;
        for (int i=0; i<barcodes.length; i++) {
            String barcode = barcodes[i];
            SQLModel sqlModel = modelProducer.getCheckItemStatusModel(barcode, itemStatus);
            SimpleQuery simp = new SimpleQuery(sqlModel,conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                count++;
            }
        }
        return count;
    }

    public void logItemChgHistory(String [] barcodes){
        AmsItemInfoHistoryDAO historyDAO = new AmsItemInfoHistoryDAO(userAccount,null,conn);
        for (int i = 0; i < barcodes.length; i++) {
            String barcode = barcodes[i];

            AmsItemInfoHistoryDTO historyDTO=new AmsItemInfoHistoryDTO();
            historyDTO.setOrderCategory("3");
            historyDTO.setOrderNo("ʵ��̨��ά��");
            historyDTO.setCreatedBy(userAccount.getUserId());
            historyDTO.setBarcode(barcode);

            historyDAO.setDTOParameter(historyDTO);
            historyDAO.recordHistory();
        }


    }
}
