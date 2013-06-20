package com.sino.ams.newasset.dao;


import java.sql.Connection;

import com.sino.base.constant.WorldConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.newasset.dto.AmsMisEmployeeDTO;
import com.sino.ams.newasset.model.AmsMisEmployeeModel;

/**
 * <p>Title: AmsMisEmployeeDAO</p>
 * <p>Description:�����Զ����ɷ������AmsMisEmployeeDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class AmsMisEmployeeDAO {

    private Connection conn = null;
    private AmsMisEmployeeModel modelProducer = null;

    /**
     * ���ܣ�MISԱ���� AMS_MIS_EMPLOYEE ���ݷ��ʲ㹹�캯��
     * @param orgId String ��֯id
     * @param conn  Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsMisEmployeeDAO(int orgId, Connection conn) {
        this.conn = conn;
        this.modelProducer = new AmsMisEmployeeModel(orgId);
    }

    /**
     * ���ܣ��жϴ������֯�Ƿ�Ϸ�
     * @return boolean
     * @throws QueryException
     */
    public boolean isValudOrgId() throws QueryException {
        SQLModel sqlModel = modelProducer.getIsOrgValidModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

    /**
     * ���ܣ���ȡmisԱ����mis���Ź�ϵxml
     * @param lastUpdateDate �ϴθ�������
     * @return StringBuffer
     * @throws QueryException
     */
    public StringBuffer getPersonDeptXML(String lastUpdateDate) throws
            QueryException {
        StringBuffer personDeptXML = new StringBuffer();
        try {
            SQLModel sqlModel = modelProducer.getPersonDept4PDAModel(lastUpdateDate);
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.setDTOClassName(AmsMisEmployeeDTO.class.getName());
            simp.executeQuery();
            personDeptXML.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
            personDeptXML.append(WorldConstant.ENTER_CHAR);
            personDeptXML.append(WorldConstant.TAB_CHAR);
            personDeptXML.append("<items>");
            if (simp.hasResult()) {
                DTOSet persons = simp.getDTOSet();
                int personCount = persons.getSize();
                AmsMisEmployeeDTO person = null;
                for (int i = 0; i < personCount; i++) {
                    personDeptXML.append(WorldConstant.ENTER_CHAR);
                    personDeptXML.append(WorldConstant.TAB_CHAR);
                    personDeptXML.append(WorldConstant.TAB_CHAR);
                    person = (AmsMisEmployeeDTO) persons.getDTO(i);
                    personDeptXML.append("<item groupname=\"");
                    personDeptXML.append(person.getDeptName());
                    personDeptXML.append("\" groupid=\"");
                    personDeptXML.append(person.getDeptCode());
                    personDeptXML.append("\" username=\"");
                    personDeptXML.append(person.getUserName());
                    personDeptXML.append("\" userid=\"");
                    personDeptXML.append(person.getEmployeeId());
                    personDeptXML.append("\" Enabled =\"");
                    personDeptXML.append(person.getEnabled());
                    personDeptXML.append("\"/>");
                }
            }
            personDeptXML.append(WorldConstant.ENTER_CHAR);
            personDeptXML.append(WorldConstant.TAB_CHAR);
            personDeptXML.append("</items>");
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return personDeptXML;
    }
}
