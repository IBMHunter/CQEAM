package com.sino.rds.appbase.form;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.calen.SimpleDate;
import com.sino.base.calen.SimpleTime;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ReflectException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.validate.CalendarValidator;
import com.sino.rds.foundation.web.component.WebOptions;
import com.sino.rds.foundation.web.component.WebRadio;
import org.apache.struts.action.ActionForm;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

public class RDSBaseFrm extends ActionForm implements DTO, CalendarConstant {
    private String act = "";
    private boolean controlSubmit = true;   //�Ƿ�����ظ��ύ�Ŀ���
    private SimpleCalendar startDate = null; //��������DTO�̳У�������Զ�д�Լ������ڷ�������Ҫ����ҳ����Ҫ��������ѯ����ʱ
    private SimpleCalendar endDate = null; //��������DTO�̳У�������Զ�д�Լ������ڷ�������Ҫ����ҳ����Ҫ��������ѯ����ʱ
    private SimpleCalendar creationDate = null; //��������DTO�̳У�������Զ�д�Լ������ڷ���
    private SimpleCalendar lastUpdateDate = null; //��������DTO�̳У�������Զ�д�Լ������ڷ���
    private String createdBy = "";
    private String lastUpdateBy = "";
    private String lastUpdateUser = "";
    private String createdUser = "";
    private String contextPath = "";
    private String calPattern = "";
    private String lookUpId = "";
    private String lookUpCode = "";
    private String enabled = "";
    private String enabledName = "";
    private WebRadio enabledRadio = null;
    private WebOptions enabledOptions = null;

    private Locale locale = null;
    protected String primaryKeyName = "";
    private String dataSaved = "N";//�����Ƿ񱣴����

    public RDSBaseFrm() {
        super();
        this.creationDate = new SimpleCalendar();
        this.startDate = new SimpleCalendar();
        this.endDate = new SimpleCalendar();
        this.lastUpdateDate = new SimpleCalendar();
        this.calPattern = CAL_PATT_14;
    }


    /**
     * ���ܣ���ָ���ֶ�����Ϊ��ǰ����ֵ
     *
     * @param dtoProp String
     */
    public void setCurrCalendar(String dtoProp) {
        Field field = ReflectionUtil.getField(this.getClass(), dtoProp);
        try {
            if (field != null) {
                SimpleCalendar cal = new SimpleCalendar();
                cal.setCalendarValue(System.currentTimeMillis());
                if (field.getType().getName().equals(SimpleCalendar.class.getName())) {
                    field.setAccessible(true);
                    field.set(this, cal);
                }
            }
        } catch (IllegalAccessException ex) {
            Logger.logError(ex);
        } catch (IllegalArgumentException ex) {
            Logger.logError(ex);
        } catch (CalendarException ex) {
            ex.printLog();
        }
    }

    /**
     * ���ܣ����ô�������
     *
     * @param creationDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setCreationDate(String creationDate) throws CalendarException {
        this.creationDate.setCalendarValue(creationDate);
    }

    /**
     * ���ܣ���ȡ��������
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getCreationDate() throws CalendarException {
        creationDate.setCalPattern(getCalPattern());
        return this.creationDate;
    }


    /**
     * ���ܣ������ϴθ�������
     *
     * @param lastUpdateDate SimpleCalendar
     * @throws CalendarException ����ֵ������ȷ�����ڻ����ǻ����ⲻ��ʶ��ĸ�ʽʱ�׳����쳣
     */
    public void setLastUpdateDate(String lastUpdateDate) throws CalendarException {
        this.lastUpdateDate.setCalendarValue(lastUpdateDate);
    }

    /**
     * ���ܣ���ȡ�ϴθ�������
     *
     * @return SimpleCalendar
     * @throws CalendarException ���õ�������ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getLastUpdateDate() throws CalendarException {
        lastUpdateDate.setCalPattern(getCalPattern());
        return this.lastUpdateDate;
    }

    /**
     * ���ܣ��������������õ�ǰ����ֵ�� ��ȥӦ���еķ���������
     */
    public void setCurrCreationDate() {
        setCurrCalendar("creationDate");
    }

    /**
     * ���ܣ����ÿ�ʼ����
     *
     * @param startDate String
     * @throws CalendarException
     */
    public void setStartDate(String startDate) throws CalendarException {
        this.startDate.setCalendarValue(startDate);
    }

    /**
     * ���ܣ���ȡ��ʼ����
     *
     * @return SimpleCalendar
     * @throws CalendarException
     */
    public SimpleCalendar getStartDate() throws CalendarException {
        startDate.setCalPattern(getCalPattern());
        return startDate;
    }

    /**
     * ���ܣ����ý�������
     *
     * @param endDate String
     * @throws CalendarException �����ڲ��Ϸ�ʱ�׳����쳣
     */
    public void setEndDate(String endDate) throws CalendarException {
        this.endDate.setCalendarValue(endDate);
    }

    /**
     * ���ܣ���ȡ��������
     *
     * @return SimpleCalendar
     * @throws CalendarException �����ڸ�ʽ���Ϸ�ʱ�׳����쳣
     */
    public SimpleCalendar getEndDate() throws CalendarException {
        endDate.setCalPattern(getCalPattern());
        return endDate;
    }

    /**
     * ���ܣ������ѯ�����������ڵ����һ����������������ȥӦ����ÿ����ѯSQL�Լ������������
     *
     * @return SimpleCalendar
     */
    public SimpleCalendar getSQLEndDate() {
        return getLastValueOfDate("endDate");
    }


    /**
     * ���ܣ������ѯ�����������ڵ����һ����������������ȥӦ����ÿ����ѯSQL�Լ������������
     *
     * @return SimpleCalendar
     */
    public SimpleCalendar getSQLToDate() {
        return getLastValueOfDate("toDate");
    }


    /**
     * ���ܣ���ȡָ���ֶ�����ֵ�����ʱ��
     *
     * @param fieldName String
     * @return SimpleCalendar
     */
    public SimpleCalendar getLastValueOfDate(String fieldName) {
        SimpleCalendar sqlEndCal = null;
        try {
            Object fieldValue = getProperty(fieldName);
            if (fieldValue instanceof SimpleCalendar) {
                sqlEndCal = (SimpleCalendar) fieldValue;
                if (!sqlEndCal.toString().equals("")) {
                    SimpleDate date = sqlEndCal.getSimpleDate();
                    SimpleTime time = SimpleTime.getEndTime();
                    sqlEndCal = new SimpleCalendar(date, time);
                }
            }
            if (sqlEndCal == null) {
                sqlEndCal = new SimpleCalendar();
            }
        } catch (CalendarException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return sqlEndCal;
    }


    /**
     * ����:��ȡDTOָ�����Ƶ��ֶε�ֵ
     *
     * @param fieldName �ֶ�����
     * @return �ֶ�ֵ,���DTO����ָ���ֶ�,�򷵻�null��
     */
    public Object getProperty(String fieldName) {
        Object fieldValue = null;
        try {
            fieldValue = ReflectionUtil.getProperty(this, fieldName);
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return fieldValue;
    }

    /**
     * ���ܣ�����������ʽ
     *
     * @param calPattern String
     */
    public void setCalPattern(String calPattern) {
        try {
            CalendarValidator.validatePattern(calPattern);
            this.calPattern = calPattern.toUpperCase();
        } catch (CalendarException ex) {
            ex.printLog();
        }
    }

    /**
     * ���ܣ���ȡ������ʽ
     *
     * @return String �������ù���������ʽ��û������ʱ����Ĭ�ϸ�ʽ��CalendarConstant.CAL_PATT_14
     */
    public String getCalPattern() {
        return calPattern;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getLookUpCode() {
        return lookUpCode;
    }

    public void setLookUpCode(String lookUpCode) {
        this.lookUpCode = lookUpCode;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public boolean isControlSubmit() {
        return controlSubmit;
    }

    public void setControlSubmit(boolean controlSubmit) {
        this.controlSubmit = controlSubmit;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getEnabledName() {
        return enabledName;
    }

    public void setEnabledName(String enabledName) {
        this.enabledName = enabledName;
    }

    public WebRadio getEnabledRadio() {
        return enabledRadio;
    }

    public void setEnabledRadio(WebRadio enabledRadio) {
        this.enabledRadio = enabledRadio;
    }

    public WebOptions getEnabledOptions() {
        return enabledOptions;
    }

    public void setEnabledOptions(WebOptions enabledOptions) {
        this.enabledOptions = enabledOptions;
    }

    public String getLookUpId() {
        return lookUpId;
    }

    public void setLookUpId(String lookUpId) {
        this.lookUpId = lookUpId;
    }

    public String getDataSaved() {
        return dataSaved;
    }

    public void setDataSaved(String dataSaved) {
        this.dataSaved = dataSaved;
    }

    public void setPrimaryKey(String primaryKeyValue) {
        try {
            if (ReflectionUtil.hasProperty(this.getClass(), primaryKeyName)) {
                ReflectionUtil.setProperty(this, primaryKeyName, primaryKeyValue);
            }
        } catch (ReflectException ex) {
            ex.printLog();
        }
    }

    public void clearPrimaryKey() {
        try {
            if (ReflectionUtil.hasProperty(this.getClass(), primaryKeyName)) {
                ReflectionUtil.setProperty(this, primaryKeyName, "");
            }
        } catch (ReflectException ex) {
            ex.printLog();
        }
    }

    public String getPrimaryKey() {
        Object primaryKey = "";
        try {
            if (ReflectionUtil.hasProperty(this.getClass(), primaryKeyName)) {
                primaryKey = ReflectionUtil.getProperty(this, primaryKeyName);
                primaryKey = StrUtil.nullToString(primaryKey);
            }
        } catch (ReflectException ex) {
            ex.printLog();
        }
        return primaryKey.toString();
    }

    /**
     * ���ܣ�����ֵ�Ƿ�Ϊ��
     *
     * @return
     */
    public boolean isPrimaryKeyEmpty() {
        return (StrUtil.isEmpty(getPrimaryKey()));
    }

    public String toJSONString() {
        return toJSONString(null);
    }

    public String toJSONString(List<String> ignoreFields) {
        String headerJSON = "({";
        List<String> fieldNames = ReflectionUtil.getProperties(getClass());
        int fieldCount = fieldNames.size();
        boolean hasIgnore = (ignoreFields != null && !ignoreFields.isEmpty());
        int addTimes = 0;
        for (int i = 0; i < fieldCount; i++) {
            String fieldName = fieldNames.get(i);
            if (addTimes > 0) {
                headerJSON += ",";
            }
            if (hasIgnore) {
                if (ignoreFields.contains(fieldName)) {
                    continue;
                }
            }
            addTimes++;
            headerJSON += "\"" + fieldName + "\":\"" + getProperty(fieldName) + "\"";
        }
        headerJSON += "})";
        return headerJSON;
    }

    public String lines2JSONString(String fieldName) {
        String lineJSON = "";
        try {
            Object fieldValue = getProperty(fieldName);
            if (fieldValue != null) {
                if (fieldValue instanceof List) {
                    List lines = (List) fieldValue;
                    lineJSON = list2JSONString(lines);
                } else if (fieldValue instanceof DTOSet) {
                    DTOSet lines = (DTOSet) fieldValue;
                    lineJSON = dtos2JSONString(lines);
                }
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return lineJSON;
    }

    public static String dtos2JSONString(DTOSet dtos) {
        String lineJSON = "[";
        try {
            if (dtos != null && !dtos.isEmpty()) {
                int lineCount = dtos.getSize();
                List<String> fieldNames = ReflectionUtil.getProperties(dtos.getDTO(0).getClass());
                int fieldCount = fieldNames.size();
                for (int i = 0; i < lineCount; i++) {
                    DTO line = dtos.getDTO(i);
                    if (i > 0) {
                        lineJSON += ",";
                    }
                    lineJSON += "{";
                    for (int j = 0; j < fieldCount; j++) {
                        String field = fieldNames.get(j);
                        if (j > 0) {
                            lineJSON += ",";
                        }
                        lineJSON += "\"" + field + "\":\"" + ReflectionUtil.getProperty(line, field) + "\"";
                    }
                    lineJSON += "}";
                }
            }
        } catch (ReflectException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        lineJSON += "]";
        return lineJSON;
    }

    public static String list2JSONString(List listValue) {
        String lineJSON = "[";
        try {
            if (listValue != null && !listValue.isEmpty()) {
                int lineCount = listValue.size();
                List<String> fieldNames = ReflectionUtil.getProperties(listValue.get(0).getClass());
                int fieldCount = fieldNames.size();
                for (int i = 0; i < lineCount; i++) {
                    Object line = listValue.get(i);
                    if (i > 0) {
                        lineJSON += ",";
                    }
                    lineJSON += "{";
                    for (int j = 0; j < fieldCount; j++) {
                        String field = fieldNames.get(j);
                        if (j > 0) {
                            lineJSON += ",";
                        }
                        lineJSON += "\"" + field + "\":\"" + ReflectionUtil.getProperty(line, field) + "\"";
                    }
                    lineJSON += "}";
                }
            }
        } catch (ReflectException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        lineJSON += "]";
        return lineJSON;
    }
}