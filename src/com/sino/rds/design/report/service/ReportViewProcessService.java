package com.sino.rds.design.report.service;

import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.db.DataTypeConstant;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.ArrUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.rds.appbase.service.RDSBaseService;
import com.sino.rds.design.report.dao.ReportCategoryProcessDAO;
import com.sino.rds.design.report.dao.ReportDefineProcessDAO;
import com.sino.rds.design.report.dao.ReportViewProcessDAO;
import com.sino.rds.foundation.exception.WebException;
import com.sino.rds.foundation.web.component.WebOption;
import com.sino.rds.foundation.web.component.WebOptions;
import com.sino.rds.share.constant.RDSDictionaryList;
import com.sino.rds.share.form.ReportCategoryFrm;
import com.sino.rds.share.form.ReportDefineFrm;
import com.sino.rds.share.form.ReportViewFrm;
import com.sino.rds.share.form.ReportViewProcessFrm;
import com.sino.rds.share.util.RDSOptionCreateService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class ReportViewProcessService extends RDSBaseService {

    private ReportDefineProcessDAO reportDAO = null;
    private ReportViewProcessDAO viewDAO = null;

    public ReportViewProcessService(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        reportDAO = new ReportDefineProcessDAO(userAccount, null, conn);
        viewDAO = new ReportViewProcessDAO(userAccount, null, conn);
    }

    /**
     * ���ܣ���������ģ��
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��������ģ�ͳ���ʱ�׳����쳣
     */
    public void saveReportView() throws DataHandleException {
        ReportViewProcessFrm frm = (ReportViewProcessFrm) dtoParameter;
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            List<ReportViewFrm> dataFields = frm.getDataFields();
            if (dataFields != null && !dataFields.isEmpty()) {
                for (ReportViewFrm field : dataFields) {
                    if (field.getFieldName().equals("")) {
                        continue;
                    }
                    field.setReportId(frm.getPrimaryKey());
                    if (field.isPrimaryKeyEmpty()) {
                        field.setPrimaryKey(viewDAO.getNextPrimaryKey());
                        viewDAO.setDTOParameter(field);
                        viewDAO.createData();
                    } else {
                        viewDAO.setDTOParameter(field);
                        viewDAO.updateData();
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
                    prodMessage("SAVE_REPORT_VIEW_SUCCESS");
                } else {
                    conn.rollback();
                    prodMessage("SAVE_REPORT_VIEW_FAILURE");
                    message.setIsError(true);
                }
                conn.setAutoCommit(autoCommit);
            } catch (Throwable ex) {
                Logger.logError(ex);
                prodMessage(ex);
            }
        }
    }

    /**
     * ���ܣ�ɾ�������ֶΣ���AjaxProcessActionͳһ����
     *
     * @return ����ɾ����Ϣ
     */
    public String deleteReportViewByIds() {
        StringBuilder xmlResponse = new StringBuilder();
        xmlResponse.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
        boolean operateResult = false;
        String content = "ɾ�������ֶ�ʧ��";
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            deleteReportCategoryByViewIds();
            deleteReportViewIds();
            updateSQLModifiedFlag();
            operateResult = true;
            content = "ɾ�������ֶγɹ�";
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
                operateResult = false;
            }
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

    private void deleteReportCategoryByViewIds() throws DataHandleException {
        ReportViewProcessFrm frm = (ReportViewProcessFrm) dtoParameter;
        ReportCategoryFrm categoryFrm = new ReportCategoryFrm();
        categoryFrm.setReportId(frm.getReportId());
        categoryFrm.setViewIds(frm.getViewIds());
        ReportCategoryProcessDAO categoryDAO = new ReportCategoryProcessDAO(userAccount, categoryFrm, conn);
        categoryDAO.deleteReportCategoryByViewIds();
    }

    private void deleteReportViewIds() throws DataHandleException {
        ReportViewProcessFrm frm = (ReportViewProcessFrm) dtoParameter;
        ReportViewFrm parameter = new ReportViewFrm();
        parameter.setViewIds(frm.getViewIds());
        parameter.setReportId(frm.getReportId());
        viewDAO.setDTOParameter(parameter);
        viewDAO.deleteReportViewByIds();
    }

    private void updateSQLModifiedFlag() throws DataHandleException {
        try {
            ReportViewProcessFrm frm = (ReportViewProcessFrm) dtoParameter;
            ReportDefineFrm report = new ReportDefineFrm();
            report.setReportId(frm.getReportId());
            reportDAO.setDTOParameter(report);
            report = reportDAO.searchDTOByPrimaryKey();
            if (!report.getReportType().equals(RDSDictionaryList.REPORT_TYPE_LIST)) {
                reportDAO.updateSQLModifiedFlag();
            }
        } catch (QueryException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        }
    }

    public ReportViewProcessFrm searchDataByPrimaryKey() throws QueryException {
        ReportViewProcessFrm frm = null;
        try {
            ReportViewProcessFrm dto = (ReportViewProcessFrm) dtoParameter;
            if (dto.isPrimaryKeyEmpty()) {
                frm = dto;
            } else {
                ReportDefineFrm report = new ReportDefineFrm();
                report.setReportId(dto.getReportId());
                reportDAO.setDTOParameter(report);
                reportDAO.setDTOClassName(ReportDefineFrm.class.getName());
                report = reportDAO.searchDTOByPrimaryKey();
                if (report != null) {
                    frm = new ReportViewProcessFrm();
                    frm.setReport(report);
                    ReportViewFrm viewFrm = new ReportViewFrm();
                    viewFrm.setReportId(report.getReportId());
                    viewDAO.setDTOParameter(viewFrm);
                    viewDAO.setDTOClassName(ReportViewFrm.class.getName());
                    List<ReportViewFrm> dataFields = viewDAO.searchListByForeignKey("reportId");
                    frm.setDataFields(dataFields);
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
        ReportViewProcessFrm frm = (ReportViewProcessFrm) dtoParameter;
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
    private void produceFieldsOptions(ReportViewProcessFrm frm, RDSOptionCreateService optionCreator) throws WebException {
        try {
            if (!frm.getPrimaryKey().equals("")) {
                List<ReportViewFrm> fields = viewDAO.getAvailableFieldsByReportId();
                WebOptions options = optionCreator.getFieldsOptions(fields);
                frm.setAvailableFieldsOptions(options);

                fields = viewDAO.getAlreadyFieldsByReportId();
                options = optionCreator.getFieldsOptions(fields);
                frm.setAlreadyFieldsOptions(options);
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
    private void produceLineOptions(ReportViewProcessFrm frm, RDSOptionCreateService optionCreator) throws WebException {
        List<ReportViewFrm> dataFields = frm.getDataFields();
        WebOptions options = null;
        if (dataFields != null && !dataFields.isEmpty()) {
            for (ReportViewFrm field : dataFields) {
                options = RDSOptionCreateService.getEnableOptions(field.getEnabled());
                field.setEnabledOptions(options);

                String code = RDSDictionaryList.H_ALIGN;
                options = optionCreator.getDictionaryOptions(code, field.getFieldAlign());
                field.setFieldAlignOptions(options);

                String fieldType = field.getFieldType();
                if (fieldType.equals(DataTypeConstant.DATE)) {
                    code = RDSDictionaryList.DATE_PATTERN;
                    options = optionCreator.getDictionaryOptions(code, field.getDataPattern());
                    field.setDataPatternOptions(options);

                } else if (ArrUtil.contains(DataTypeConstant.LIMIT_NUMBER_TYPE, fieldType)) {
                    code = RDSDictionaryList.NUMBER_PATTERN;
                    options = optionCreator.getDictionaryOptions(code, field.getDataPattern());
                    field.setDataPatternOptions(options);

                    code = RDSDictionaryList.COMPUTE_EXPRESSION;
                    options = optionCreator.getDictionaryOptions(code, field.getComputeExpression());
                    field.setComputeExpressionOptions(options);
                } else {
                    field.setDataPatternOptions(getNoNeedOptions());
                    field.setComputeExpressionOptions(getNoNeedOptions());
                }
            }
        }
    }

    /**
     * ���ܣ�������������������
     *
     * @return ������������������
     */
    private WebOptions getNoNeedOptions() {
        WebOptions options = new WebOptions();
        WebOption option = new WebOption();
        option.setLabel("��������");
        options.addOption(option);
        return options;
    }

    /**
     * ���ܣ�Ϊ�ֶ�ѡ����Ҫ�����������ɲο�����
     *
     * @param frm           ����չʾ������
     * @param optionCreator ������������
     * @throws WebException �������������ʱ�׳����쳣
     */
    private void produceReferencedOptions(ReportViewProcessFrm frm, RDSOptionCreateService optionCreator) throws WebException {
        WebOptions options = RDSOptionCreateService.getEnableOptions(frm.getEnabled());
        frm.setEnabledOptions(options);

        String code = RDSDictionaryList.DATE_PATTERN;
        options = optionCreator.getDictionaryOptions(code, "");
        frm.setDatePatternOptions(options);

        code = RDSDictionaryList.NUMBER_PATTERN;
        options = optionCreator.getDictionaryOptions(code, "");
        frm.setNumberPatternOptions(options);

        code = RDSDictionaryList.H_ALIGN;
        options = optionCreator.getDictionaryOptions(code, "");
        frm.setFieldAlignOptions(options);

        code = RDSDictionaryList.COMPUTE_EXPRESSION;
        options = optionCreator.getDictionaryOptions(code, "");
        frm.setComputeExpressionOptions(options);
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
        ReportViewProcessFrm frm = (ReportViewProcessFrm) dtoParameter;
        ReportViewFrm parameter = new ReportViewFrm();
        parameter.setReportId(frm.getPrimaryKey());
        parameter.setFieldIds(frm.getFieldIds());
        viewDAO.setDTOParameter(parameter);
        List<ReportViewFrm> fields = viewDAO.getSelectedFields();
        if (fields != null && !fields.isEmpty()) {
            xmlStr.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
            xmlStr.append(WorldConstant.ENTER_CHAR);
            xmlStr.append(WorldConstant.TAB_CHAR);
            xmlStr.append("<lines>");
            String[] fieldNames = {
                    "viewId", "fieldId", "fieldName", "fieldDesc",
                    "dataPattern", "fieldAlign", "computeExpression",
                    "sortNumber", "enabled", "fieldType"
            };
            for (ReportViewFrm field : fields) {
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

    public String hasReportViewField() {
        String operateResult = "N";
        try {
            ReportViewProcessFrm frm = (ReportViewProcessFrm) dtoParameter;
            ReportViewFrm searchParameter = new ReportViewFrm();
            searchParameter.setReportId(frm.getReportId());
            viewDAO.setDTOParameter(searchParameter);
            if (viewDAO.hasReportViewField()) {
                operateResult = "Y";
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return operateResult;
    }
}