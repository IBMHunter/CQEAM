package com.sino.rds.design.report.service;

import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.db.DataTypeConstant;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.rds.appbase.RDSConstantConfigManager;
import com.sino.rds.appbase.service.RDSBaseService;
import com.sino.rds.design.datamodel.service.DataModelParseService;
import com.sino.rds.design.datamodel.service.DataModelProcessService;
import com.sino.rds.design.report.dao.ReportDefineProcessDAO;
import com.sino.rds.design.report.dao.ReportParameterProcessDAO;
import com.sino.rds.foundation.exception.WebException;
import com.sino.rds.foundation.web.component.WebOption;
import com.sino.rds.foundation.web.component.WebOptions;
import com.sino.rds.share.constant.RDSDictionaryList;
import com.sino.rds.share.form.*;
import com.sino.rds.share.util.RDSOptionCreateService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReportParameterProcessService extends RDSBaseService {

    private ReportParameterProcessDAO parameterDAO = null;
    private ReportDefineProcessDAO reportDAO = null;

    public ReportParameterProcessService(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        reportDAO = new ReportDefineProcessDAO(userAccount, null, conn);
        parameterDAO = new ReportParameterProcessDAO(userAccount, null, conn);
    }

    /**
     * ���ܣ���������ģ��
     *
     * @throws com.sino.base.exception.DataHandleException
     *          ��������ģ�ͳ���ʱ�׳����쳣
     */
    public void saveReportParameter() throws DataHandleException {
        ReportParameterProcessFrm frm = (ReportParameterProcessFrm) dtoParameter;
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            List<ReportParameterFrm> dataFields = frm.getParameterFields();
            if (dataFields != null && !dataFields.isEmpty()) {
                for (ReportParameterFrm field : dataFields) {
                    if (field.getFieldName().equals("")) {
                        continue;
                    }
                    field.setReportId(frm.getPrimaryKey());
                    if (field.isPrimaryKeyEmpty()) {
                        field.setPrimaryKey(parameterDAO.getNextPrimaryKey());
                        parameterDAO.setDTOParameter(field);
                        parameterDAO.createData();
                    } else {
                        parameterDAO.setDTOParameter(field);
                        parameterDAO.updateData();
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
                    prodMessage("SAVE_REPORT_PARAMETER_SUCCESS");
                } else {
                    conn.rollback();
                    prodMessage("SAVE_REPORT_PARAMETER_FAILURE");
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
        ReportParameterProcessFrm dto = (ReportParameterProcessFrm) dtoParameter;
        ReportDefineFrm report = new ReportDefineFrm();
        report.setReportId(dto.getReportId());
        reportDAO.setDTOParameter(report);
        reportDAO.updateSQLModifiedFlag();
    }

    public String deleteReportParameterByIds() {
        StringBuilder xmlResponse = new StringBuilder();
        xmlResponse.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
        boolean operateResult = false;
        String content = "ɾ�������ֶ�ʧ��";
        try {
            ReportParameterProcessFrm frm = (ReportParameterProcessFrm) dtoParameter;
            ReportParameterFrm parameter = new ReportParameterFrm();
            parameter.setParameterIds(frm.getParameterIds());
            parameterDAO.setDTOParameter(parameter);
            parameterDAO.deleteReportParameterByIds();
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

    public ReportParameterProcessFrm searchDataByPrimaryKey() throws QueryException {
        ReportParameterProcessFrm frm = null;
        try {
            ReportParameterProcessFrm dto = (ReportParameterProcessFrm) dtoParameter;
            if (dto.isPrimaryKeyEmpty()) {
                frm = dto;
            } else {
                ReportDefineFrm report = new ReportDefineFrm();
                report.setReportId(dto.getReportId());
                reportDAO.setDTOParameter(report);
                reportDAO.setDTOClassName(ReportDefineFrm.class.getName());
                report = reportDAO.searchDTOByPrimaryKey();
                if (report != null) {
                    frm = new ReportParameterProcessFrm();
                    frm.setReport(report);
                    ReportParameterFrm viewFrm = new ReportParameterFrm();
                    viewFrm.setReportId(report.getReportId());
                    parameterDAO.setDTOParameter(viewFrm);
                    parameterDAO.setDTOClassName(ReportParameterFrm.class.getName());
                    List<ReportParameterFrm> dataFields = parameterDAO.searchListByForeignKey("reportId");
                    frm.setParameterFields(dataFields);
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


    private List<ReportParameterFrm> parseSQLParameters(ReportDefineFrm report) throws QueryException {
        List<ReportParameterFrm> parameters = null;
        try {
            DataModelProcessService processService = new DataModelProcessService(userAccount, report, conn);
            DataModelFrm modelFrm = processService.searchDataByPrimaryKey();
            String modelSql = modelFrm.getModelSql();
            if (modelSql.indexOf("{") > 0) {
                parameters = new ArrayList<ReportParameterFrm>();
                DataModelParseService parseService = new DataModelParseService(userAccount, modelFrm, conn);
                List<ModelFieldFrm> modelFields = parseService.getModelFields();
                int containNumber = StrUtil.containNum(modelSql, "{");
                for (int i = 0; i < containNumber; i++) {
                    int existTime = i + 1;
                    String parameterStr = StrUtil.getBetwStr(modelSql, "{", existTime, "}", existTime);
                    parameterStr = parameterStr.trim();
                    ReportParameterFrm parameter = constructParameter(modelFields, parameterStr, modelSql);
                    addParameter(parameters, parameter);
                }
            }
        } catch (ReflectException ex) {
            ex.printLog();
            throw new QueryException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new QueryException(ex.getMessage());
        }
        return parameters;
    }

    private ReportParameterFrm constructParameter(List<ModelFieldFrm> modelFields, String parameterStr, String modelSql) throws ReflectException {
        ReportParameterFrm parameter = null;
        String fieldName = parameterStr.toUpperCase().trim();
        int index = fieldName.indexOf(RDSDictionaryList.SESSION_PREFIX);
        boolean sessionVariable = (index == 0);
        if (sessionVariable) {//session����
            fieldName = getFieldBeforeSessionVariable(fieldName, modelSql);
        } else {//��session����
            parameter = constructParameterByModel(modelFields, fieldName);
            if (parameter == null) {
                fieldName = StrUtil.trim(fieldName, "START_", true);
                fieldName = StrUtil.trim(fieldName, "END_", true);
            }
        }
        parameter = constructParameterByModel(modelFields, fieldName);
        if (parameter != null) {
            if (sessionVariable) {
                parameter.setInputType(RDSDictionaryList.INPUT_TYPE_SESSION);
            } else {//��������
                if (parameter.getFieldType().equals(DataTypeConstant.DATE)) {
                    parameter.setInputType(RDSDictionaryList.INPUT_TYPE_CALENDAR);
                    if (modelSql.indexOf("START_" + fieldName) > 0 && modelSql.indexOf("END_" + fieldName) > 0) {
                        parameter.setCalendarType(RDSDictionaryList.CALENDAR_TYPE_INTERVAL);
                    } else if (modelSql.indexOf("START_" + fieldName) > 0 && modelSql.indexOf("END_" + fieldName) < 0) {
                        parameter.setCalendarType(RDSDictionaryList.CALENDAR_TYPE_START);
                    } else if (modelSql.indexOf("START_" + fieldName) < 0 && modelSql.indexOf("END_" + fieldName) > 0) {
                        parameter.setCalendarType(RDSDictionaryList.CALENDAR_TYPE_END);
                    }
                }
            }
        }
        return parameter;
    }

    /**
     * ����:�����ֶ�����ȡģ���ֶζ���
     *
     * @param modelFields ģ���ֶζ���
     * @param fieldName   �ֶ�����
     * @return ��������
     * @throws ReflectException ��������������ʱ�׳����쳣
     */
    private ReportParameterFrm constructParameterByModel(List<ModelFieldFrm> modelFields, String fieldName) throws ReflectException {
        ReportParameterFrm parameter = null;
        if (modelFields != null) {
            ModelFieldFrm outField = null;
            for (ModelFieldFrm field : modelFields) {
                if (field.getFieldName().equals(fieldName)) {
                    outField = field;
                }
            }
            if (outField != null) {
                parameter = new ReportParameterFrm();
                ReflectionUtil.copyData(outField, parameter);
            }
        }
        return parameter;
    }

    private void addParameter(List<ReportParameterFrm> parameters, ReportParameterFrm parameter) {
        if (parameter != null) {
            for (ReportParameterFrm frm : parameters) {
                if (frm.getFieldName().equals(parameter.getFieldName())) {
                    return;
                }
            }
            parameters.add(parameter);
        }
    }

    private String getFieldBeforeSessionVariable(String parameterStr, String modelSql) {
        String fieldName = "";
        return fieldName;
    }


    /**
     * ���ܣ�����Web���
     *
     * @throws com.sino.rds.foundation.exception.WebException
     *
     */

    private void produceWebComponent() throws WebException {
        ReportParameterProcessFrm frm = (ReportParameterProcessFrm) dtoParameter;
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
    private void produceFieldsOptions(ReportParameterProcessFrm frm, RDSOptionCreateService optionCreator) throws WebException {
        try {
            if (!frm.getPrimaryKey().equals("")) {
                List<ReportParameterFrm> fields = parameterDAO.getAvailableFieldsByReportId();
                WebOptions options = optionCreator.getFieldsOptions(fields);
                frm.setAvailableFieldsOptions(options);

                fields = parameterDAO.getAlreadyFieldsByReportId();
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
    private void produceLineOptions(ReportParameterProcessFrm frm, RDSOptionCreateService optionCreator) throws WebException {
        List<ReportParameterFrm> dataFields = frm.getParameterFields();
        WebOptions options = null;
        if (dataFields != null && !dataFields.isEmpty()) {
            for (ReportParameterFrm field : dataFields) {
                options = RDSOptionCreateService.getEnableOptions(field.getEnabled());
                field.setEnabledOptions(options);


                processInputTypeOption(field, optionCreator);

                String code = RDSDictionaryList.H_ALIGN;
                options = optionCreator.getDictionaryOptions(code, field.getFieldAlign());
                field.setFieldAlignOptions(options);

                code = RDSDictionaryList.NULL_ABLE;
                options = optionCreator.getDictionaryOptions(code, field.getNullAble());
                field.setNullAbleOptions(options);

                options = optionCreator.getLovOptions(field.getLovId());
                field.setLovOptions(options);

                options = optionCreator.getLookUpOptions(field.getLookUpId());
                field.setLookUpOptions(options);

                code = RDSDictionaryList.CALENDAR_TYPE;
                options = optionCreator.getDictionaryOptions(code, field.getCalendarType());
                field.setCalendarTypeOptions(options);
            }
        }
    }

    private void processInputTypeOption(ReportParameterFrm field, RDSOptionCreateService optionCreator) throws WebException{
        String code = RDSDictionaryList.INPUT_TYPE;
        WebOptions options = optionCreator.getDictionaryOptions(code, field.getInputType());
        String fieldName = field.getFieldName();
        fieldName = StrUtil.getJavaField(fieldName);
        if(!RDSConstantConfigManager.contains(fieldName)){
            options.removeOption(RDSDictionaryList.INPUT_TYPE_SESSION);
        }
        field.setInputTypeOptions(options);
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
    private void produceReferencedOptions(ReportParameterProcessFrm frm, RDSOptionCreateService optionCreator) throws WebException {
        WebOptions options = RDSOptionCreateService.getEnableOptions(frm.getEnabled());
        frm.setEnabledOptions(options);

        String code = RDSDictionaryList.INPUT_TYPE;
        options = optionCreator.getDictionaryOptions(code, "");
        frm.setInputTypeOptions(options);

        code = RDSDictionaryList.H_ALIGN;
        options = optionCreator.getDictionaryOptions(code, "");
        frm.setFieldAlignOptions(options);

        code = RDSDictionaryList.NULL_ABLE;
        options = optionCreator.getDictionaryOptions(code, "");
        frm.setNullAbleOptions(options);

        options = optionCreator.getLovOptions("");
        frm.setLovOptions(options);

        options = optionCreator.getLookUpOptions("");
        frm.setLookUpOptions(options);

        code = RDSDictionaryList.CALENDAR_TYPE;
        options = optionCreator.getDictionaryOptions(code, "");
        frm.setCalendarTypeOptions(options);
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
        ReportParameterProcessFrm frm = (ReportParameterProcessFrm) dtoParameter;
        ReportParameterFrm parameter = new ReportParameterFrm();
        parameter.setReportId(frm.getPrimaryKey());
        parameter.setFieldIds(frm.getFieldIds());
        parameterDAO.setDTOParameter(parameter);
        List<ReportParameterFrm> fields = parameterDAO.getSelectedFields();
        if (fields != null && !fields.isEmpty()) {
            xmlStr.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
            xmlStr.append(WorldConstant.ENTER_CHAR);
            xmlStr.append(WorldConstant.TAB_CHAR);
            xmlStr.append("<lines>");
            String[] fieldNames = {
                    "parameterId", "fieldId", "fieldName", "fieldDesc"
            };
            for (ReportParameterFrm field : fields) {
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

    public String hasReportParameterField() {
        String hasReportParameter = "N";
        try {
            ReportParameterProcessFrm frm = (ReportParameterProcessFrm) dtoParameter;
            ReportParameterFrm searchParameter = new ReportParameterFrm();
            searchParameter.setReportId(frm.getReportId());
            parameterDAO.setDTOParameter(searchParameter);
            if(parameterDAO.hasReportParameterField()){
                hasReportParameter = "Y";
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return hasReportParameter;
    }
}