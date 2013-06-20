package com.sino.ams.spare.part.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.spare.part.model.ImportSpareCategoryModel;
import com.sino.ams.spare.part.dto.ImportSpareCategoryDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.datatrans.*;
import com.sino.base.util.StrUtil;
import com.sino.base.data.RowSet;
import com.sino.base.data.Row;
import com.sino.base.constant.WorldConstant;

import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: wangzp
 * Date: 2011-12-12
 * Function: �����豸���ർ��
 * To change this template use File | Settings | File Templates.
 */
public class ImportSpareCategoryDAO  extends AMSBaseDAO {
    private SfUserDTO sfUser = null;

    public ImportSpareCategoryDAO(SfUserDTO userAccount, ImportSpareCategoryDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        ImportSpareCategoryDTO dtoPara = (ImportSpareCategoryDTO)dtoParameter;
        super.sqlProducer = new ImportSpareCategoryModel((SfUserDTO)userAccount, dtoPara);
    }
    
     /**
      * ��յ�ǰ�û�����ʱ��
      */
     public void deleteImportModel() throws SQLModelException, QueryException, DataHandleException {
            ImportSpareCategoryModel eoModel = (ImportSpareCategoryModel) sqlProducer;
            SQLModel sqlModel = eoModel.deleteImportModel();
            DBOperator.updateRecord(sqlModel, conn);
    }

     /**
      * ��������У��
      */
    public void doVerifyData(DTOSet dtoSet) throws SQLModelException, QueryException, ContainerException {
        if (dtoSet != null && dtoSet.getSize() > 0) {
            for (int i = 0; i < dtoSet.getSize(); i++) {
                ImportSpareCategoryDTO dto = (ImportSpareCategoryDTO) dtoSet.getDTO(i);
                if (StrUtil.isEmpty(dto.getItemName())) {
                    insertImprotErrorData(dto.getId(), "�豸���Ʋ���Ϊ��");
                } else if (StrUtil.isEmpty(dto.getItemSpec())) {
                    insertImprotErrorData(dto.getId(), "�豸�ͺŲ���Ϊ��");
                } else if (StrUtil.isEmpty(dto.getItemCategory())) {
                    insertImprotErrorData(dto.getId(), "�豸���Ͳ���Ϊ��");
                } else if (StrUtil.isEmpty(dto.getSpareUsage())) {
                    insertImprotErrorData(dto.getId(), "��;����Ϊ��");
                } else if (StrUtil.isEmpty(dto.getVendorId())) {
                    insertImprotErrorData(dto.getId(), "���̲���Ϊ��");
                } else if (StrUtil.isEmpty(dto.getItemUnit())) {
                    insertImprotErrorData(dto.getId(), "��λ����Ϊ��");
                } else if (!validateVendorId(dto.getVendorId())) {
                    insertImprotErrorData(dto.getId(), "����ID������");
                } else if (!validateSameCategory(dto.getItemName(), dto.getItemSpec(), dto.getItemCategory(), dto.getSpareUsage(), dto.getVendorId())) {
                    insertImprotErrorData(dto.getId(), "EXCEL�д�����ͬ����Ϣ");
                } else if (!validateExistCategory(dto.getItemName(), dto.getItemSpec(), dto.getItemCategory(), dto.getSpareUsage(), dto.getVendorId())) {
                    insertImprotErrorData(dto.getId(), "�����豸������ϵͳ���Ѵ���");
                }
            }
        }
    }

    public void insertImprotErrorData(String id,String codeError) throws SQLModelException {
        try {
            ImportSpareCategoryModel onNetModel = (ImportSpareCategoryModel) sqlProducer;
            SQLModel sqlModel = onNetModel.insertImprotErrorData(id,codeError);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (DataHandleException ex) {
            ex.printLog();
        }
    }

    public boolean validateSameCategory(String itemName, String itemSpec, String itemCategory, String spareUsage, String vendorId) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode=true;
        ImportSpareCategoryModel eoModel = (ImportSpareCategoryModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateSameCategory(itemName, itemSpec, itemCategory, spareUsage, vendorId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            int count = Integer.parseInt(StrUtil.nullToString(row.getValue("RET_QTY")));
            if (count > 1) {
                hasBarcode = false;
            }
        }
        return hasBarcode;
    }

    public boolean validateExistCategory(String itemName, String itemSpec, String itemCategory, String spareUsage, String vendorId) throws SQLModelException, QueryException, ContainerException {
        boolean hasBarcode=true;
        ImportSpareCategoryModel eoModel = (ImportSpareCategoryModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateExistCategory(itemName, itemSpec, itemCategory, spareUsage, vendorId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = false;
        }
        return hasBarcode;
    }

    public boolean validateVendorId(String vendorId) throws SQLModelException, QueryException {
        boolean hasBarcode=false;
        ImportSpareCategoryModel eoModel = (ImportSpareCategoryModel) sqlProducer;
        SQLModel sqlModel = eoModel.validateVendorId(vendorId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasBarcode = true;
        }
        return hasBarcode;
     }
    /**
     * �ж�У���������Ƿ���ڴ���
     */
    public boolean importHasError() throws SQLModelException, QueryException {
        boolean hasError=false;
            ImportSpareCategoryModel eoModel = (ImportSpareCategoryModel) sqlProducer;
            SQLModel sqlModel = eoModel.hasErrorModel();
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if(simpleQuery.hasResult()){
               hasError = true;
            }
        return hasError;
    }
    
    /**
     * ��ȡ��ʱ���е����� ����������ǰ�û���
     * @return DTOSet
     */
    public DTOSet getImport() throws QueryException, SQLModelException {
        ImportSpareCategoryModel eoModel = (ImportSpareCategoryModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(eoModel.getQueryImportModel(), conn);
        sq.setDTOClassName(ImportSpareCategoryDTO.class.getName());
        sq.executeQuery();
        return sq.getDTOSet();
    }

    //����
    public File getExportFile() throws DataTransException, SQLModelException {
        ImportSpareCategoryModel modelProducer = (ImportSpareCategoryModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getImportErrorModel();
        String reportTitle = "��ֵ�׺��ʲ����������Ϣ";
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
        fieldMap.put("COMPANY_CODE", "��˾����");
        fieldMap.put("BARCODE", "��ֵ�׺��ʲ���ǩ��");
        fieldMap.put("ITEM_NAME", "�ʲ�����");
        fieldMap.put("ITEM_SPEC", "����ͺ�");
        fieldMap.put("ITEM_UNIT", "��λ");
        fieldMap.put("SPECIALITY_DEPT", "רҵ���Ŵ���");
        fieldMap.put("SPECIALITY_USER", "רҵ�����˱��");
        fieldMap.put("ADDRESS", "�ص�");
        fieldMap.put("RESPONSIBILITY_DEPT", "���β��Ŵ���");
        fieldMap.put("RESPONSIBILITY_USER", "���β��Ź���Ա���");
        fieldMap.put("MAINTAIN_USER", "ʹ��������");
        fieldMap.put("PRICE", "�ɱ�");
        fieldMap.put("IS_TD", "�Ƿ�TD");
        fieldMap.put("DZYH_START_DATE", "��������");
        fieldMap.put("REMARK", "��ע");
        fieldMap.put("ERROR", "������Ϣ");
        return fieldMap;
    }
}
