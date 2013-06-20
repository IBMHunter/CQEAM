package com.sino.ams.newasset.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsMisDeptDTO;
import com.sino.ams.newasset.model.DeptAssignModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.base.web.DatabaseForWeb;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class DeptAssignDAO extends AMSBaseDAO {


    public DeptAssignDAO(SfUserDTO userAccount, AmsMisDeptDTO dtoParameter,
                         Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
     * @param userAccount BaseUserDTO
     * @param dtoParameter DTO
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsMisDeptDTO dto = (AmsMisDeptDTO) dtoParameter;
        sqlProducer = new DeptAssignModel((SfUserDTO) userAccount, dto);
    }

    /**
     * ���ܣ���ȡ���������б��
     * @param selectedDept String
     * @return String
     * @throws QueryException
     */
    public String getDeptOptions(String selectedDept) throws QueryException {
        DeptAssignModel modelProducer = (DeptAssignModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getDeptOptionsModel();
        DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
        return optProducer.getOptionHtml(selectedDept);
    }
}
