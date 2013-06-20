package com.sino.sinoflow.user.dao;


import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.prematch.model.AmsPaAssetsModel;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;
import com.sino.sinoflow.user.dto.SfUserChgLogDTO;
import com.sino.sinoflow.user.model.SfUserChgLogModel;


/**
 * <p>Title: SfGroupDAO</p>
 * <p>Description:�����Զ����ɷ������SfGroupDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author mshtang
 * @version 1.0
 */


public class SfUserChgLogDAO extends BaseDAO {
    SfUserChgLogDTO sfUserChgLogDTO = new SfUserChgLogDTO();
    SfUserBaseDTO userAccount;

    /**
     * ���ܣ�SF_GROUP ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserBaseDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SfGroupDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public SfUserChgLogDAO(SfUserBaseDTO userAccount, SfUserChgLogDTO dtoParameter, Connection conn) {

        super(userAccount, dtoParameter, conn);
        this.initSQLProducer(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        this.userAccount = (SfUserBaseDTO) userAccount;
        sfUserChgLogDTO = (SfUserChgLogDTO) dtoParameter;
        super.sqlProducer = new SfUserChgLogModel((SfUserBaseDTO) userAccount, sfUserChgLogDTO);
    }

    public File getExportFile() throws DataTransException {
        File file = null;
        try {
            SfUserChgLogModel modelProducer = (SfUserChgLogModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getPageQueryModel();
            String reportTitle = "�û���Ϣ�����¼";
            String fileName = reportTitle + ".xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);
            rule.setFieldMap(getFieldMap());
            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(reportTitle);
            custData.setReportPerson(userAccount.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            rule.setCalPattern(LINE_PATTERN);
            TransferFactory factory = new TransferFactory();
            DataTransfer transfer = factory.getTransfer(rule);
            transfer.transData();
            file = (File) transfer.getTransResult();
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataTransException(ex);
        }
        return file;
    }

    /**
     * ���ܣ���ȡ�û������¼�嵥�����ֶ�ӳ��
     *
     * @return Map
     */
    private Map getFieldMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("USER_ID", "�û�ID");
        fieldMap.put("USER_NAME", "�û�����");
        fieldMap.put("CHG_TYPE", "�������");
        fieldMap.put("OPERATOR", "������ԱID");
        fieldMap.put("OPERATOR_NAME", "������Ա");
        fieldMap.put("OPERATOR_DATE", "��������");
//        fieldMap.put("LOG_FROM", "���ǰ");
//        fieldMap.put("LOG_TO", "�����");
        fieldMap.put("REMARK", "˵��");
        return fieldMap;
    }
}
