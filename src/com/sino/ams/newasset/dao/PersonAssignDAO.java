package com.sino.ams.newasset.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.model.PersonAssignModel;
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
public class PersonAssignDAO extends AMSBaseDAO {


    public PersonAssignDAO(SfUserDTO userAccount,
                           AmsAssetsAddressVDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
     * @param userAccount BaseUserDTO
     * @param dtoParameter DTO
     * @todo Implement this com.sino.framework.dao.BaseDAO method
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
        sqlProducer = new PersonAssignModel((SfUserDTO) userAccount, dto);
    }

    /**
     * ���ܣ���ȡ��Ա�����б��
     * @param selectedPerson String
     * @return String
     * @throws QueryException
     */
    public String getPersonOptions(String selectedPerson) throws QueryException {
        PersonAssignModel modelProducer = (PersonAssignModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getPersonOptionsModel();
        DatabaseForWeb optProducer = new DatabaseForWeb(sqlModel, conn);
        return optProducer.getOptionHtml(selectedPerson);
    }
}
