package com.sino.ams.spare.bjslcy.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;

import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.spare.allot.dto.AmsBjsAllotHDTO;
import com.sino.ams.spare.bjslcy.model.AmsBjSlDisposeModel;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author TOTTI
 *         Date: 2007-11-28
 */
public class AmsBjSlDisposeDAO extends BaseDAO {
    private SfUserDTO sfUser = null;
    private AmsBjsAllotHDTO dto = null;

    public AmsBjSlDisposeDAO(SfUserDTO userAccount, AmsBjsAllotHDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        this.sfUser = userAccount;
        this.dto = (AmsBjsAllotHDTO) super.dtoParameter;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsBjsAllotHDTO dtoPara = (AmsBjsAllotHDTO) dtoParameter;
        super.sqlProducer = new AmsBjSlDisposeModel((SfUserDTO) userAccount, dtoPara);
    }

    public RowSet getLines() throws QueryException {
        AmsBjSlDisposeModel model = new AmsBjSlDisposeModel(sfUser, null);
        SimpleQuery sq = new SimpleQuery(model.getDataByTransIdModel(dto.getTransId()), conn);
        sq.executeQuery();

        return sq.getSearchResult();
    }

    public RowSet getData(String transId, String itemCode) throws QueryException {
        AmsBjSlDisposeModel model = new AmsBjSlDisposeModel(sfUser, null);
        SimpleQuery sq = new SimpleQuery(model.getDataDiffent(transId, itemCode), conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }

    public File exportFile() throws DataTransException {
        File file = null;
        try {
            AmsBjsAllotHDTO itemDTO = (AmsBjsAllotHDTO) dtoParameter;
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);

            String fileName = "�����������ͳ�Ʊ�.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("TRANS_NO", "�������쵥�ݺ�");
            fieldMap.put("CREATED_USER", "������");
            fieldMap.put("TRANS_STATUS_NAME", "����״̬");
            fieldMap.put("CREATION_DATE", "����ʱ��");
            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(fileName);
            custData.setReportPerson(sfUser.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            /*rule.setSheetSize(1000);*/
            //���÷�ҳ��ʾ
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
