package com.sino.sinoflow.user.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.sinoflow.user.dto.PersonOrderDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����ʤ��Ȩ����Copyright (c) 2003~2007��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Copyright: ������Ȩ����˼ŵ����Ϣ�������޹�˾��һ����Χ��ʹ��</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */


public class PersonOrderModel extends AMSSQLProducer {
    /**
     * ���ܣ��û�ά��Model���캯��
     * @param userAccount  BaseUserDTO ����ִ�е�ǰ�������û�
     * @param dtoParameter SfUserBaseDTO ����ǰ����������
     */
    public PersonOrderModel(SfUserBaseDTO userAccount, PersonOrderDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }


    /**
     * ���ܣ�����Զ�����ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        PersonOrderDTO dto = (PersonOrderDTO) dtoParameter;
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =

                "SELECT\n" +
                        "  SAA.SFACT_PROC_NAME,\n" +
                        "  SAA.SFACT_APPL_COLUMN_1,\n" +
                        "  SAA.SFACT_APPL_ID,\n" +
                        "  SAA.SFACT_CREATE_DT,\n" +
                        "  SU.USERNAME,\n" +
                        "  COUNT(1) CNT\n" +
                        "FROM\n" +
                        "  SF_ACT_ARCHIVE SAA ,\n" +
                        "  SF_USER SU\n" +
                        "WHERE\n" +
                        "  SAA.SFACT_COMPOSE_USER=SU.LOGIN_NAME\n" +
                        "  AND (?='' OR SAA.SFACT_PROC_NAME=?)\n" +
                        "  AND (?='' OR SAA.SFACT_APPL_COLUMN_1=?)\n" +
                        "  AND (SAA.SFACT_COMPLETE_USER=? OR SAA.SFACT_SIGN_USER=? OR SAA.SFACT_PICK_USER=?)\n" +
                        "GROUP BY\n" +
                        "  SAA.SFACT_PROC_NAME,\n" +
                        "  SAA.SFACT_APPL_COLUMN_1,\n" +
                        "  SAA.SFACT_APPL_ID,\n" +
                        "  SFACT_CREATE_DT,\n" +
                        "  SAA.SFACT_COMPOSE_USER,\n" +
                        "  SU.USERNAME\n"+
                        " ORDER BY SAA.SFACT_PROC_NAME,SAA.SFACT_APPL_COLUMN_1";

        sqlArgs.add(dto.getTransName());
        sqlArgs.add(dto.getTransName());
        sqlArgs.add(dto.getTransNo());
        sqlArgs.add(dto.getTransNo());
        sqlArgs.add(userAccount.getLoginName());
        sqlArgs.add(userAccount.getLoginName());
        sqlArgs.add(userAccount.getLoginName());
        

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
