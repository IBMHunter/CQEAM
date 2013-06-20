package com.sino.ams.synchronize.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.synchronize.dto.EamSyschronizeDTO;
import com.sino.ams.synchronize.model.TransStatusQueryModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-3-16
 * Time: 13:15:49
 * To change this template use File | Settings | File Templates.
 */
public class TransStatusDAO extends AMSBaseDAO {

    public SfUserDTO sfUser = null;

    /**
     * ���ܣ�eAM�����ص�ͬ�� ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemMatchDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public TransStatusDAO(SfUserDTO userAccount, EamSyschronizeDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EamSyschronizeDTO dtoPara = (EamSyschronizeDTO) dtoParameter;
        super.sqlProducer = new TransStatusQueryModel((SfUserDTO) userAccount, dtoPara);
    }

    public File getExportFile() throws DataTransException {
        TransStatusQueryModel modelProducer = (TransStatusQueryModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getPageQueryModel();
        String reportTitle = "������״̬��ѯ";
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
        custData.setReportPerson(userAccount.getUsername());
        custData.setNeedReportDate(true);
        rule.setCustData(custData);
        rule.setCalPattern(LINE_PATTERN);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        return (File) transfer.getTransResult();
    }

    private Map getFieldMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("BATCH_ID", "����������");
        fieldMap.put("REMARK", "ͬ�����");
        fieldMap.put("TRANS_STATUS", "����״̬");
        fieldMap.put("START_DATE", "��ʼʱ��");
        fieldMap.put("END_DATE", "����ʱ��");
        fieldMap.put("ERRMSG", "������Ϣ");
        return fieldMap;
    }

    public RowSet getLog(String logId) throws QueryException {
        TransStatusQueryModel modelProducer = (TransStatusQueryModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(modelProducer.getLogInf(logId), conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        return rs;
    }
}
