package com.sino.ams.yj.dao;


import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.WorldConstant;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.yj.dto.AmsYjTeamDTO;
import com.sino.ams.yj.ensure.model.AmsYjCommunicateEnsureModel;
import com.sino.ams.yj.model.AmsYjTeamModel;
import com.sino.ams.yj.model.AmsYjItemModel;
import com.sino.ams.constant.CustMessageKey;


/**
 * <p>Title: AmsYjTeamDAO</p>
 * <p>Description:�����Զ����ɷ������AmsYjTeamDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ�����϶���
 */


public class AmsYjTeamDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�Ӧ��ͨ�ű��϶���� AMS_YJ_TEAM ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjTeamDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsYjTeamDAO(SfUserDTO userAccount, AmsYjTeamDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsYjTeamDTO dtoPara = (AmsYjTeamDTO) dtoParameter;
        super.sqlProducer = new AmsYjTeamModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ�����Excel�ļ���
     * @return File
     * @throws com.sino.base.exception.DataTransException
     *
     */
    public File exportFile() throws DataTransException {
        File file = null;
        try {
            DataTransfer transfer = null;
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);

            String fileName = "Ӧ�����϶�����Ϣ.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("ORGANIZATION_NAME", "��˾����");
            fieldMap.put("TEAM_ID", "�����");
            fieldMap.put("TEAM_NAME", "��������");
            fieldMap.put("RESPONSIBILITY_USER", "��ҵ������");
            fieldMap.put("TEL", "�ֻ�");
            fieldMap.put("QUANTITY", "��������");
            fieldMap.put("SITUATION", "�������������ص�");
            fieldMap.put("CREATE_USER", "������");
            fieldMap.put("CREATION_DATE", "��������");
            fieldMap.put("LAST_UPDATE_USER", "������");
            fieldMap.put("LAST_UPDATE_DATE", "��������");
            fieldMap.put("DISABLE_DATE", "ʧЧ����");

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(fileName);
            custData.setReportPerson(sfUser.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            /*rule.setSheetSize(1000);*/
            //���÷�ҳ��ʾ
            TransferFactory factory = new TransferFactory();
            transfer = factory.getTransfer(rule);
            transfer.transData();
            file = (File) transfer.getTransResult();
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataTransException(ex);
        }
        return file;
    }

    /**
     * ��������ͳ��
     * @return SQLModel sqlModel = ((AmsYjTeamModel) sqlProducer).getTeamStat();
     * @throws DataTransException
     */
    public File exportTeam() throws DataTransException {
//        File file = null;
//        DataTransfer transfer = null;
//        SQLModel sqlModel = ((AmsYjTeamModel) sqlProducer).getTeamStat();
//        TransRule rule = new TransRule();
//        rule.setDataSource(sqlModel);
//        rule.setCalPattern(CalendarConstant.LINE_PATTERN);
//        rule.setSourceConn(conn);
//
//        String fileName = "Ӧ�����϶���ͳ��.xls";
//        String filePath = WorldConstant.USER_HOME;
//        filePath += WorldConstant.FILE_SEPARATOR;
//        filePath += fileName;
//        rule.setTarFile(filePath);
//
//        DataRange range = new DataRange();
//        rule.setDataRange(range);
//
//        Map fieldMap = new HashMap();
//        fieldMap.put("ORGANIZATION_NAME", "��˾����");
//        fieldMap.put("TEAM_COUNT", "������");
//        fieldMap.put("USER_COUNT", "��������");
//
//        rule.setFieldMap(fieldMap);
//
//        CustomTransData custData = new CustomTransData();
//        custData.setReportTitle(fileName);
//        custData.setReportPerson(sfUser.getUsername());
//        custData.setNeedReportDate(true);
//        rule.setCustData(custData);
//        /*rule.setSheetSize(1000);*/
//        //���÷�ҳ��ʾ
//        TransferFactory factory = new TransferFactory();
//        transfer = factory.getTransfer(rule);
//        transfer.transData();
//        file = (File) transfer.getTransResult();
//
//        return file;
        
        AmsYjTeamModel modelProducer = (AmsYjTeamModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getTeamStat();
        String reportTitle = "Ӧ�����϶���ͳ��";
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
        custData.setReportPerson(sfUser.getUsername());
        custData.setNeedReportDate(true);
        rule.setCustData(custData);
        rule.setCalPattern(LINE_PATTERN);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        return (File) transfer.getTransResult();
        
    }

    /**
     * ͳ����Ŀ
     * @return
     */
    private Map getFieldMap() {
        Map fieldMap = new HashMap();  
        fieldMap.put("ORGANIZATION_NAME", "��˾����");
        fieldMap.put("TEAM_COUNT", "������");
        fieldMap.put("USER_COUNT", "��������");
        return fieldMap;
    }
    
    
    /**
     * ���ܣ���Чѡ������
     * @param teamId String
     */
    public void enableItem(String teamId) {
        try {
            AmsYjTeamModel a = (AmsYjTeamModel) sqlProducer;
            SQLModel sModel = a.getEnableModel(teamId);
            DBOperator.updateRecord(sModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("����");
        } catch (DataHandleException ex) {
            ex.printLog();
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("����");
        }
    }

    public boolean doVerify(String teamName) throws QueryException {
        boolean success = false;
        AmsYjTeamModel aModel = (AmsYjTeamModel) sqlProducer;
        SQLModel sqlModel = aModel.doVerify(teamName);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();
        return success;
   }
}