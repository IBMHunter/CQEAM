package com.sino.ams.match.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.match.model.MatchResultModel;
import com.sino.ams.system.user.dto.EtsOuCityMapDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-12-25
 * Time: 9:19:05
 * To change this template use File | Settings | File Templates.
 */
public class MatchResultDAO extends AMSBaseDAO {

    public MatchResultDAO(SfUserDTO userAccount, EtsOuCityMapDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
     *
     * @param userAccount  BaseUserDTO
     * @param dtoParameter DTO
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SfUserDTO user = (SfUserDTO) userAccount;
        EtsOuCityMapDTO dto = (EtsOuCityMapDTO) dtoParameter;
        sqlProducer = new MatchResultModel(user, dto);
    }


    /**
     * ���ܣ���ȡ�����̵��豸��ϸExcel�ļ�
     *
     * @return File
     * @throws com.sino.base.exception.DataTransException
     *
     */
    public File getExportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            String reportTitle = "ƥ���ر���";
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
            Map fieldMap = new HashMap();
            fieldMap.put("COMPANY", "��˾����");
            fieldMap.put("MIS_COUNT", "MIS�ʲ�����");
            fieldMap.put("MATCH_COUNT", "��ƥ���ʲ�����");
            fieldMap.put("NO_MATCH_COUNT", "δƥ���ʲ�����");
            fieldMap.put("SYN_COUNT", "��ͬ���ʲ�����");
            fieldMap.put("NO_SYN_COUNT", "δͬ���ʲ�����");
            fieldMap.put("MATCH_RATE", "��ƥ���ʲ��ٷֱ�");
            fieldMap.put("SYN_RATE", "��ͬ���ʲ��ٷֱ�");
            rule.setFieldMap(fieldMap);
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
}

