package com.sino.ams.net.statistic.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.net.statistic.dto.MisDiffDTO;
import com.sino.ams.net.statistic.model.MisDiffModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-4-11
 * Time: 1:56:56
 * To change this template use File | Settings | File Templates.
 */
public class MisDiffDAO extends BaseDAO {
     SfUserDTO sfUser = null;
     MisDiffDTO dto = null;

    public MisDiffDAO(SfUserDTO userAccount, MisDiffDTO dtoParameter, Connection conn) {
          super(userAccount, dtoParameter, conn);
          this.sfUser = userAccount;
          this.dto = (MisDiffDTO) super.dtoParameter;
      }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        MisDiffDTO dtoPara = (MisDiffDTO) dtoParameter;
        super.sqlProducer = new MisDiffModel((SfUserDTO) userAccount, dtoPara);
    }

     public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "�����״��MIS���챨��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("COMPANY", "��˾");
            fieldMap.put("QUANTITY", "MIS����");
            fieldMap.put("MIS_ITEM_CODE", "���ϱ���");
            fieldMap.put("ITEM_NAME", "�ʲ�����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
            fieldMap.put("EAM_QUANTITY", "EAM����");
            fieldMap.put("WORKORDER_OBJECT_NAME", "�ص�");
            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("�����״��MIS���챨��");
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
