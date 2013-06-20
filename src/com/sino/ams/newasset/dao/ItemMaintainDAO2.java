package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.dto.AmsItemCorrectLogDTO;
import com.sino.ams.newasset.model.ItemMaintainModel2;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 *
 * <p>Title: SinoEAM</p>
 * <p>Description: �����ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author suhp
 */
public class ItemMaintainDAO2 extends AMSBaseDAO {
    private AmsItemCorrectLogDAO logDAO = null;
    private SimpleQuery simp = null;

    public ItemMaintainDAO2(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter,Connection conn) {
        super(userAccount, dtoParameter, conn);
        logDAO = new AmsItemCorrectLogDAO(userAccount, null, conn);
    }

    /**
     * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
     * @param userAccount BaseUserDTO
     * @param dtoParameter DTO
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO)dtoParameter;
        sqlProducer = new ItemMaintainModel2((SfUserDTO)userAccount, dto);
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
                            + "ͨ���豸̨��ά������";
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
        } finally{
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
    private AmsItemCorrectLogDTO getLogDTO() throws QueryException{
        AmsItemCorrectLogDTO logDTO = null;
        try {
            ItemMaintainModel2 modelProducer = (ItemMaintainModel2) sqlProducer;
            SQLModel sqlModel = modelProducer.getPrimaryKeyDataModel();
            if (simp == null) {
                simp = new SimpleQuery(sqlModel, conn);
                simp.setDTOClassName(AmsAssetsAddressVDTO.class.getName());
            } else {
                simp.setSql(sqlModel);
            }
            simp.executeQuery();
            if (simp.hasResult()) {
                AmsAssetsAddressVDTO oldDTO = (AmsAssetsAddressVDTO) simp.getFirstDTO();
                AmsAssetsAddressVDTO newDTO = (AmsAssetsAddressVDTO)dtoParameter;
                logDTO = new AmsItemCorrectLogDTO();
                logDTO.setBarcode(newDTO.getBarcode());
                String correctContent = "";
                String[] fieldNames = {"ITEM_CODE", "ITEM_NAME", "ITEM_SPEC", "WORKORDER_OBJECT_CODE",
                                      "WORKORDER_OBJECT_NAME", "RESPONSIBILITY_USER_NAME","RESPONSIBILITY_DEPT_NAME",
                                      "MAINTAIN_USER", "MAINTAIN_DEPT_NAME"};
                String[] fieldDescs = {"�豸�������", "�豸����", "�豸�ͺ�", "�ص����", "�ص�����",
                                      "������","���β���",
                                      "ʹ����", "ʹ�ò���"};
                String fieldName = "";
                String javaField = "";
                String oldValue = "";
                String newValue = "";
                for (int i = 0; i < fieldNames.length; i++) {
                    fieldName = fieldNames[i];
                    javaField = StrUtil.getJavaField(fieldName);
                    oldValue = String.valueOf(ReflectionUtil.getProperty(oldDTO, javaField));
                    if(oldValue.equals("")){
                        oldValue = "��";
                    }
                    newValue = String.valueOf(ReflectionUtil.getProperty(newDTO, javaField));
                    newDTO.getMaintainDept();
                    if (!oldValue.equals(newValue) && !newValue.equals("")) {
                        correctContent += fieldDescs[i]
                            + ":"
                            + oldValue
                            + "-->>"
                            + newValue;
                        correctContent += WorldConstant.ENTER_CHAR;
                    }
                }
                logDTO.setCorrectContent(correctContent);
            }
        } catch (ReflectException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return logDTO;
    }

    public File getExportFile() throws DataTransException {
        ItemMaintainModel2 modelProducer = (ItemMaintainModel2) sqlProducer;
        SQLModel sqlModel = modelProducer.getPageQueryModel();
        String reportTitle = "̨�˲�ѯ�����豸";
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
        rule.setFieldMap(getFieldMap());
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

    private Map getFieldMap(){
        Map fieldMap = new HashMap();
        fieldMap.put("BARCODE", "��ǩ��");
        fieldMap.put("ITEM_CATEGORY_NAME", "�豸����");
        fieldMap.put("ITEM_NAME", "�豸����");
        fieldMap.put("ITEM_SPEC", "�豸�ͺ�");
        fieldMap.put("ITEM_UNIT", "������λ");
        fieldMap.put("YEARS", "ʹ������");
        fieldMap.put("START_DATE", "��������");
        fieldMap.put("ITEM_STATUS_NAME", "�豸״̬");
        fieldMap.put("FINANCE_PROP_NAME", "�ʲ�����");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
        fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
        fieldMap.put("WORKORDER_OBJECT_LOCATION", "�ص�λ��");
        fieldMap.put("COUNTY_NAME", "��������");
        fieldMap.put("RESPONSIBILITY_USER_NAME", "����������");
        fieldMap.put("EMPLOYEE_NUMBER", "������Ա����");
        fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���β���");
        fieldMap.put("MAINTAIN_USER", "ʹ����");
        fieldMap.put("MAINTAIN_DEPT_NAME", "ʹ�ò���");
        fieldMap.put("PROJECT_NAME", "��Ŀ����");
        fieldMap.put("PROJECT_NUMBER", "��Ŀ���");
        fieldMap.put("MANUFACTURER_NAME", "��������");
        fieldMap.put("MANUFACTURER_CODE", "���̴���");
        fieldMap.put("CONTENT_NAME", "�ʲ�Ŀ¼����");
		fieldMap.put("CONTENT_CODE", "�ʲ�Ŀ¼����");
        fieldMap.put("SHARE_STATUS_NAME", "������״̬");
		fieldMap.put("POWER", "�����");
        fieldMap.put("LOG_NET_ELE", "�߼�����Ԫ��");
        fieldMap.put("INVEST_CAT_NAME", "Ͷ�ʷ���");
        fieldMap.put("OPE_NAME", "ҵ��ƽ̨");
        fieldMap.put("LNE_NAME", "������");     
        return fieldMap;
    }

    public Map getFincePropCount (AmsAssetsAddressVDTO dto) throws QueryException, ContainerException{
        Map map = new HashMap();
        ItemMaintainModel2 modelProducer = (ItemMaintainModel2) sqlProducer;
        SQLModel sqlModel = modelProducer.getFincePropCount(dto);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        for (int i = 0; i< rs.getSize(); i++) {
            Row row = rs.getRow(i);
            map.put(row.getValue("CODE"),row.getValue("CNT"));
        }
        return map;
    }
}
