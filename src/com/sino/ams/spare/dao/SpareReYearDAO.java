package com.sino.ams.spare.dao;

import java.io.File;
import java.sql.Connection;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;

import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.spare.dto.AmsItemTransLDTO;
import com.sino.ams.spare.model.SpareReYearModel;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2008-3-23
 * Time: 16:47:38
 * Function; ����������ͳ��_��
 */
public class SpareReYearDAO extends AMSBaseDAO {
     private SfUserDTO sfUser = null;
     private String searchYear;
     private String searchMonth;

    public SpareReYearDAO(SfUserDTO userAccount, AmsItemTransLDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
        AmsItemTransLDTO Situsdto = (AmsItemTransLDTO) dtoParameter;
        searchYear =  Situsdto.getSearchYear();
        searchMonth = Situsdto.getSearchMonth();        
        if(searchYear.equals("")) {
            Calendar c = Calendar.getInstance();
            searchYear=String.valueOf(c.get(Calendar.YEAR));
        }
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsItemTransLDTO dtoPara = (AmsItemTransLDTO) dtoParameter;
        super.sqlProducer = new SpareReYearModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ�����Excel�ļ���
     *
     * @return File
     * @throws com.sino.base.exception.DataTransException
     *
     */
    public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "����������ͳ��_����.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("SPARE_USAGE", "�豸����");
            fieldMap.put("VENDOR_NAME", "����");
            fieldMap.put("REP_NUM", "������");
            fieldMap.put("TOTAL_NUM", "������");
            fieldMap.put("RAT_NUM", "������");

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            String title;
            if(searchMonth.equals("")) {
                title = searchYear + "�걸��������ͳ��";
            } else {
                title = searchYear + "��" + searchMonth + "�±���������ͳ��";
            }
            custData.setReportTitle(title);
            custData.setReportPerson(sfUser.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
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
