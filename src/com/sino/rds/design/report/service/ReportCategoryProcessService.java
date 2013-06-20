package com.sino.rds.design.report.service;

import com.sino.base.constant.WorldConstant;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.rds.appbase.service.RDSBaseService;
import com.sino.rds.design.report.dao.ReportCategoryProcessDAO;
import com.sino.rds.design.report.dao.ReportDefineProcessDAO;
import com.sino.rds.foundation.exception.WebException;
import com.sino.rds.foundation.web.component.WebOptions;
import com.sino.rds.share.constant.RDSDictionaryList;
import com.sino.rds.share.form.ReportCategoryFrm;
import com.sino.rds.share.form.ReportCategoryProcessFrm;
import com.sino.rds.share.form.ReportDefineFrm;
import com.sino.rds.share.util.RDSOptionCreateService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class ReportCategoryProcessService extends RDSBaseService {

    private ReportDefineProcessDAO reportDAO = null;
    private ReportCategoryProcessDAO categoryDAO = null;

    public ReportCategoryProcessService(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        reportDAO = new ReportDefineProcessDAO(userAccount, null, conn);
        categoryDAO = new ReportCategoryProcessDAO(userAccount, null, conn);
    }

    /**
     * ���ܣ���������ģ��
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��������ģ�ͳ���ʱ�׳����쳣
     */
    public void saveReportCategory() throws DataHandleException {
        ReportCategoryProcessFrm frm = (ReportCategoryProcessFrm) dtoParameter;
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            List<ReportCategoryFrm> dataFields = frm.getCategoryFields();
            if (dataFields != null && !dataFields.isEmpty()) {
                for (ReportCategoryFrm field : dataFields) {
                    if(field.getFieldName().equals("")){
                        continue;
                    }
                    field.setReportId(frm.getPrimaryKey());
                    if (field.isPrimaryKeyEmpty()) {
                        field.setPrimaryKey(categoryDAO.getNextPrimaryKey());
                        categoryDAO.setDTOParameter(field);
                        categoryDAO.createData();
                    } else {
                        categoryDAO.setDTOParameter(field);
                        categoryDAO.updateData();
                    }
                }
                updateSQLModifiedFlag();
            }
            operateResult = true;
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                    prodMessage("SAVE_REPORT_CATEGORY_SUCCESS");
                } else {
                    conn.rollback();
                    prodMessage("SAVE_REPORT_CATEGORY_FAILURE");
                    message.setIsError(true);
                }
                conn.setAutoCommit(autoCommit);
            } catch (Throwable ex) {
                Logger.logError(ex);
                prodMessage(ex);
            }
        }
    }


    private void updateSQLModifiedFlag() throws DataHandleException{
        ReportCategoryProcessFrm frm = (ReportCategoryProcessFrm) dtoParameter;
        ReportDefineFrm report = new ReportDefineFrm();
        report.setReportId(frm.getReportId());
        reportDAO.setDTOParameter(report);
        reportDAO.updateSQLModifiedFlag();
    }

    public String deleteReportCategoryByIds() {
        StringBuilder xmlResponse = new StringBuilder();
        xmlResponse.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
        boolean operateResult = false;
        String content = "ɾ�������ֶ�ʧ��";
        try {
            ReportCategoryProcessFrm frm = (ReportCategoryProcessFrm) dtoParameter;
            ReportCategoryFrm parameter = new ReportCategoryFrm();
            parameter.setCategoryIds(frm.getCategoryIds());
            categoryDAO.setDTOParameter(parameter);
            categoryDAO.deleteReportCategoryByIds();
            operateResult = true;
            content = "ɾ�������ֶγɹ�";
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            xmlResponse.append("<message result=\"");
            xmlResponse.append(operateResult);
            xmlResponse.append("\">");
            xmlResponse.append("<content>");
            xmlResponse.append(content);
            xmlResponse.append("</content>");
            xmlResponse.append("</message>");
        }
        return xmlResponse.toString();
    }

    public ReportCategoryProcessFrm searchDataByPrimaryKey() throws QueryException {
        ReportCategoryProcessFrm frm = null;
        try {
            ReportCategoryProcessFrm dto = (ReportCategoryProcessFrm) dtoParameter;
            if (dto.isPrimaryKeyEmpty()) {
                frm = dto;
            } else {
                ReportDefineFrm report = new ReportDefineFrm();
                report.setReportId(dto.getReportId());
                reportDAO.setDTOParameter(report);
                reportDAO.setDTOClassName(ReportDefineFrm.class.getName());
                report = reportDAO.searchDTOByPrimaryKey();
                if (report != null) {
                    frm = new ReportCategoryProcessFrm();
                    frm.setReport(report);
                    ReportCategoryFrm viewFrm = new ReportCategoryFrm();
                    viewFrm.setReportId(report.getReportId());
                    categoryDAO.setDTOParameter(viewFrm);
                    categoryDAO.setDTOClassName(ReportCategoryFrm.class.getName());
                    categoryDAO.setCalPattern(LINE_PATTERN);
                    List<ReportCategoryFrm> dataFields = categoryDAO.searchListByForeignKey("reportId");
                    frm.setCategoryFields(dataFields);
                    setDTOParameter(frm);
                }
            }
            if (frm != null) {
                produceWebComponent();
            }
        } catch (WebException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return frm;
    }


    /**
     * ���ܣ�����Web���
     *
     * @throws com.sino.rds.foundation.exception.WebException
     *
     */
    private void produceWebComponent() throws WebException {
        ReportCategoryProcessFrm frm = (ReportCategoryProcessFrm) dtoParameter;
        RDSOptionCreateService optionCreator = new RDSOptionCreateService(userAccount, conn);
        produceFieldsOptions(frm, optionCreator);
        produceLineOptions(frm, optionCreator);
        produceReferencedOptions(frm, optionCreator);
    }

    /**
     * ���ܣ����ɿ�ѡ�ֶκ���ѡ�ֶε�ѡ��
     *
     * @param frm           ����չʾ������
     * @param optionCreator ������������
     * @throws WebException �������������ʱ�׳����쳣
     */
    private void produceFieldsOptions(ReportCategoryProcessFrm frm, RDSOptionCreateService optionCreator) throws WebException {
        try {
            if (!frm.getPrimaryKey().equals("")) {
                List<ReportCategoryFrm> fields = categoryDAO.getAvailableFieldsByReportId();
                WebOptions options = optionCreator.getFieldsOptions(fields);
                frm.setAvailableFieldsOptions(options);

                fields = categoryDAO.getAboveFieldsByReportId();
                options = optionCreator.getFieldsOptions(fields);
                frm.setAboveFieldsOptions(options);

                fields = categoryDAO.getLeftFieldsByReportId();
                options = optionCreator.getFieldsOptions(fields);
                frm.setLeftFieldsOptions(options);
            }
        } catch (QueryException ex) {
            ex.printLog();
            throw new WebException(ex);
        }
    }


    /**
     * ���ܣ�Ϊ����Ϣ����������ѡ��
     *
     * @param frm           ����չʾ������
     * @param optionCreator ������������
     * @throws WebException �������������ʱ�׳����쳣
     */
    private void produceLineOptions(ReportCategoryProcessFrm frm, RDSOptionCreateService optionCreator) throws WebException {
        List<ReportCategoryFrm> dataFields = frm.getCategoryFields();
        WebOptions options = null;
        if (dataFields != null && !dataFields.isEmpty()) {
            for (ReportCategoryFrm field : dataFields) {
                options = RDSOptionCreateService.getEnableOptions(field.getEnabled());
                field.setEnabledOptions(options);

                String code = RDSDictionaryList.H_ALIGN;
                options = optionCreator.getDictionaryOptions(code, field.getFieldAlign());
                field.setFieldAlignOptions(options);

                code = RDSDictionaryList.DISPLAY_FLAG;
                options = optionCreator.getDictionaryOptions(code, field.getDisplayFlag());
                field.setDisplayFlagOptions(options);
            }
        }
    }

    /**
     * ���ܣ�Ϊ�ֶ�ѡ����Ҫ�����������ɲο�����
     *
     * @param frm           ����չʾ������
     * @param optionCreator ������������
     * @throws WebException �������������ʱ�׳����쳣
     */
    private void produceReferencedOptions(ReportCategoryProcessFrm frm, RDSOptionCreateService optionCreator) throws WebException {
        WebOptions options = RDSOptionCreateService.getEnableOptions(frm.getEnabled());
        frm.setEnabledOptions(options);

        String code = RDSDictionaryList.DISPLAY_FLAG;
        options = optionCreator.getDictionaryOptions(code, "Y");
        frm.setDisplayFlagOptions(options);
    }


    /**
     * ���ܣ���ȡ����ѡ�е��ֶεĸ�������ԡ�����String��ʽ���ء�ǰ̨��Ajax������
     *
     * @return ѡ���ֶι��ɵ�DTO����(Javascript)
     * @throws com.sino.base.exception.QueryException
     *          ��ȡѡ���ֶ���Ϣ����ʱ�׳����쳣
     */
    public String getSelectedFieldsXML() throws QueryException {
        StringBuilder xmlStr = new StringBuilder();
        ReportCategoryProcessFrm frm = (ReportCategoryProcessFrm) dtoParameter;
        ReportCategoryFrm parameter = new ReportCategoryFrm();
        parameter.setReportId(frm.getPrimaryKey());
        parameter.setFieldIds(frm.getFieldIds());
        categoryDAO.setDTOParameter(parameter);
        List<ReportCategoryFrm> fields = categoryDAO.getSelectedFields();
        if (fields != null && !fields.isEmpty()) {
            xmlStr.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
            xmlStr.append(WorldConstant.ENTER_CHAR);
            xmlStr.append(WorldConstant.TAB_CHAR);
            xmlStr.append("<lines>");
            String[] fieldNames = {
                    "categoryId", "fieldId", "fieldName",
                    "fieldDesc", "displayFlag","enabled"
            };
            for (ReportCategoryFrm field : fields) {
                xmlStr.append(WorldConstant.ENTER_CHAR);
                xmlStr.append(WorldConstant.TAB_CHAR);
                xmlStr.append(WorldConstant.TAB_CHAR);
                xmlStr.append("<line>");
                for (String fieldName : fieldNames) {
                    String fieldValue = StrUtil.nullToString(field.getProperty(fieldName));
                    xmlStr.append(WorldConstant.ENTER_CHAR);
                    xmlStr.append(WorldConstant.TAB_CHAR);
                    xmlStr.append(WorldConstant.TAB_CHAR);
                    xmlStr.append(WorldConstant.TAB_CHAR);
                    xmlStr.append("<");
                    xmlStr.append(fieldName);
                    xmlStr.append(">");
                    xmlStr.append(fieldValue);
                    xmlStr.append("</");
                    xmlStr.append(fieldName);
                    xmlStr.append(">");
                }
                xmlStr.append(WorldConstant.ENTER_CHAR);
                xmlStr.append(WorldConstant.TAB_CHAR);
                xmlStr.append(WorldConstant.TAB_CHAR);
                xmlStr.append("</line>");
            }
            xmlStr.append(WorldConstant.ENTER_CHAR);
            xmlStr.append(WorldConstant.TAB_CHAR);
            xmlStr.append("</lines>");
        }
        return xmlStr.toString();
    }


    public String hasReportCategoryField() {
        String hasReportParameter = "N";
        try {
            ReportCategoryProcessFrm frm = (ReportCategoryProcessFrm) dtoParameter;
            ReportCategoryFrm searchParameter = new ReportCategoryFrm();
            searchParameter.setReportId(frm.getReportId());
            categoryDAO.setDTOParameter(searchParameter);
            if(categoryDAO.hasReportCategoryField()){
                hasReportParameter = "Y";
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return hasReportParameter;
    }
}