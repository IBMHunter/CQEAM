package com.sino.ams.spare.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.spare.dto.SpareHistoryDTO;
import com.sino.ams.spare.model.SpareHistoryModel;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.datatrans.*;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.WorldConstant;

import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-12-02
 * Time: 00:00:00
 * To change this template use File | Settings | File Templates.
 */
public class SpareHistoryDAO extends AMSBaseDAO {
      private SfUserDTO sfUser = null;

    public SpareHistoryDAO(SfUserDTO userAccount, SpareHistoryDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SpareHistoryDTO dtoPara = (SpareHistoryDTO) dtoParameter;
        super.sqlProducer = new SpareHistoryModel((SfUserDTO) userAccount, dtoPara);
    }

    public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "����ҵ�񵥾���ʷ��Ϣ.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();
            fieldMap.put("TRANS_NO", "���ݺ�");
            fieldMap.put("TRANS_TYPE_NAME", "��������");
            fieldMap.put("BARCODE", "ID��");
            fieldMap.put("ITEM_NAME", "�豸����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
            fieldMap.put("ITEM_CATEGORY", "�豸����");
            fieldMap.put("SPARE_USAGE", "��;");
            fieldMap.put("VENDOR_NAME", "����");
            fieldMap.put("QUANTITY", "��������");
            fieldMap.put("FROM_OBJECT_NAME", "��Դ�ֿ�");
            fieldMap.put("TO_OBJECT_NAME", "Ŀ�Ĳֿ�");
            fieldMap.put("FROM_COMPANY", "��Դ��˾");
            fieldMap.put("TO_COMPANY", "Ŀ�Ĺ�˾");
            fieldMap.put("CREATED_USER", "������");
            fieldMap.put("CREATION_DATE", "��������");
            rule.setFieldMap(fieldMap);
            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("����ҵ�񵥾���ʷ��Ϣ");
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
