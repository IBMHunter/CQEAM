package com.sino.ams.spare.dao;

import java.io.File;
import java.sql.Connection;
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
import com.sino.ams.spare.dto.SpareOrderDTO;
import com.sino.ams.spare.model.SpareOrderModel;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-12-02
 * Time: 00:00:00
 * To change this template use File | Settings | File Templates.
 */
public class SpareOrderDAO extends AMSBaseDAO {
      private SfUserDTO sfUser = null;

    public SpareOrderDAO(SfUserDTO userAccount, SpareOrderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SpareOrderDTO dtoPara = (SpareOrderDTO) dtoParameter;
        super.sqlProducer = new SpareOrderModel((SfUserDTO) userAccount, dtoPara);
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
            String fileName = "��������ͳ��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("TRANS_NO", "���ݺ�");
            fieldMap.put("CREATED_USER", "������");
            fieldMap.put("CREATION_DATE", "��������");
            fieldMap.put("TRANS_STATUS_NAME", "����״̬");
            fieldMap.put("TRANS_TYPE_NAME", "��������");

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("��������ͳ��");
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
