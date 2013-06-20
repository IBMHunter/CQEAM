package com.sino.ams.newasset.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.bean.CustomQryFields;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dto.AmsAssetsCommQueryDTO;
import com.sino.ams.newasset.model.CustomQuerySetModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsAssetsCommQueryDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsCommQueryDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class CustomQuerySetDAO extends AMSBaseDAO {


    /**
     * ���ܣ��ʲ��ۺϲ�ѯ���� AMS_ASSETS_COMM_QUERY ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsCommQueryDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public CustomQuerySetDAO(SfUserDTO userAccount,
                             AmsAssetsCommQueryDTO dtoParameter,
                             Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsCommQueryDTO dtoPara = (AmsAssetsCommQueryDTO) dtoParameter;
        super.sqlProducer = new CustomQuerySetModel((SfUserDTO) userAccount,
                dtoPara);
    }

    /**
     * ���ܣ���ȡ�û��Զ�����ֶ�
     * @param fieldUsage String �ֶ���;:���ڲ�ѯ������ʾ
     * @return DTOSet
     * @throws QueryException
     */
    public DTOSet getCheckedFields(String fieldUsage) throws QueryException {
        DTOSet checkedFields = new DTOSet();
        CustomQuerySetModel modelProducer = (CustomQuerySetModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getFieldsByUserIdModel(fieldUsage);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(AmsAssetsCommQueryDTO.class.getName());
        simp.executeQuery();
        checkedFields = simp.getDTOSet();
        return checkedFields;
    }

    /**
     * ���ܣ���ȡͨ�ò�ѯ���д�ѡ�ֶ��б�
     * @return String
     * @throws QueryException
     */
    public String getAllQueryFields() throws QueryException {
        StringBuffer fieldsOption = new StringBuffer();
        DTOSet fields = getCheckedFields(AssetsDictConstant.FIELD_FOR_QUERY);
        Object fieldName = "";
        Object fieldComment = "";
        Map fieldMap = CustomQryFields.getFieldsMap();
        Iterator fieldNames = fieldMap.keySet().iterator();
        while (fieldNames.hasNext()) {
            fieldName = fieldNames.next();
            if (fields != null && fields.contains("fieldName", fieldName)) {
                continue;
            }
            fieldComment = fieldMap.get(fieldName);
            fieldsOption.append("<option value=\"");
            fieldsOption.append(fieldName);
            fieldsOption.append("\">");
            fieldsOption.append(fieldComment);
            fieldsOption.append("</option>");
            fieldsOption.append(WorldConstant.ENTER_CHAR);
        }
        return fieldsOption.toString();
    }


    /**
     * ���ܣ���ȡͨ�ò�ѯ���û�ѡ�񱣴���ֶ�
     * @return String
     * @throws QueryException
     */
    public String getChkQueryFields() throws QueryException {
        StringBuffer fieldsOption = new StringBuffer();
        DTOSet fields = getCheckedFields(AssetsDictConstant.FIELD_FOR_QUERY);
        Object fieldName = "";
        Object fieldComment = "";
        Map fieldMap = CustomQryFields.getFieldsMap();
        if (fields != null) {
            AmsAssetsCommQueryDTO field = null;
            for (int i = 0; i < fields.getSize(); i++) {
                field = (AmsAssetsCommQueryDTO) fields.getDTO(i);
                fieldName = field.getFieldName();
                fieldComment = fieldMap.get(fieldName);
                fieldsOption.append("<option value=\"");
                fieldsOption.append(fieldName);
                fieldsOption.append("\">");
                fieldsOption.append(fieldComment);
                fieldsOption.append("</option>");
                fieldsOption.append(WorldConstant.ENTER_CHAR);
            }
        }
        return fieldsOption.toString();
    }

    /**
     * ���ܣ���ȡͨ�ò�ѯ���д�ѡ�ֶ��б�
     * @return String
     * @throws QueryException
     */
    public String getAllDisplayFields() throws QueryException {
        StringBuffer fieldsOption = new StringBuffer();
        DTOSet fields = getCheckedFields(AssetsDictConstant.FIELD_FOR_DISPL);
        Object fieldName = "";
        Object fieldComment = "";
        Map fieldMap = CustomQryFields.getFieldsMap();
        Iterator fieldNames = fieldMap.keySet().iterator();
        while (fieldNames.hasNext()) {
            fieldName = fieldNames.next();
            if (fields != null && fields.contains("fieldName", fieldName)) {
                continue;
            }
            fieldComment = fieldMap.get(fieldName);
            fieldsOption.append("<option value=\"");
            fieldsOption.append(fieldName);
            fieldsOption.append("\">");
            fieldsOption.append(fieldComment);
            fieldsOption.append("</option>");
            fieldsOption.append(WorldConstant.ENTER_CHAR);
        }
        return fieldsOption.toString();
    }


    /**
     * ���ܣ���ȡͨ�ò�ѯ���û�ѡ�񱣴���ֶ�
     * @return String
     * @throws QueryException
     */
    public String getChkDisplayFields() throws QueryException {
        StringBuffer fieldsOption = new StringBuffer();
        DTOSet fields = getCheckedFields(AssetsDictConstant.FIELD_FOR_DISPL);
        Object fieldName = "";
        Object fieldComment = "";
        Map fieldMap = CustomQryFields.getFieldsMap();
        if (fields != null) {
            AmsAssetsCommQueryDTO field = null;
            for (int i = 0; i < fields.getSize(); i++) {
                field = (AmsAssetsCommQueryDTO) fields.getDTO(i);
                fieldName = field.getFieldName();
                fieldComment = fieldMap.get(fieldName);
                fieldsOption.append("<option value=\"");
                fieldsOption.append(fieldName);
                fieldsOption.append("\">");
                fieldsOption.append(fieldComment);
                fieldsOption.append("</option>");
                fieldsOption.append(WorldConstant.ENTER_CHAR);
            }
        }
        return fieldsOption.toString();
    }

    /**
     * ���ܣ������Զ����ֶ�
     * @param chkQueryFields String[] ѡ�еĲ�ѯ�����ֶ�
     * @param chkDisplayFields String[] ѡ�е���ʾ�ֶ�
     * @return boolean
     */
    public boolean saveCustomizeFields(String[] chkQueryFields,
                                       String[] chkDisplayFields) {
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            CustomQuerySetModel modelProducer = (CustomQuerySetModel)
                                                sqlProducer;
            SQLModel sqlModel = modelProducer.getDeleteFieldsByUserIdModel();
            DBOperator.updateRecord(sqlModel, conn);
            saveCustomizedFields(chkQueryFields,
                                 AssetsDictConstant.FIELD_FOR_QUERY);
            saveCustomizedFields(chkDisplayFields,
                                 AssetsDictConstant.FIELD_FOR_DISPL);
            operateResult = true;
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (SQLException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                    prodMessage(AssetsMessageKeys.COMM_QRY_CUST_SUCCESS);
                } else {
                    conn.rollback();
                    prodMessage(AssetsMessageKeys.COMM_QRY_CUST_SUCCESS);
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
                prodMessage(AssetsMessageKeys.SQL_ERROR);
            }
        }
        return operateResult;
    }

    /**
     * ���ܣ������Զ�����ֶ�
     * @param fields String[]
     * @param fieldUsage String
     * @throws DataHandleException
     */
    private void saveCustomizedFields(String[] fields, String fieldUsage) throws
            DataHandleException {
        String field = "";
        int index = -1;
        for (int i = 0; i < fields.length; i++) {
            field = fields[i];
            index = field.indexOf(";");
            AmsAssetsCommQueryDTO dto = new AmsAssetsCommQueryDTO();
            dto.setFieldName(field.substring(0, index));
            dto.setFieldDesc(field.substring(index + 1));
            dto.setUserId(StrUtil.nullToString(userAccount.getUserId()));
            dto.setFieldUsage(fieldUsage);
            dto.setSortNo(i);
            setDTOParameter(dto);
            createData();
        }
    }
}
