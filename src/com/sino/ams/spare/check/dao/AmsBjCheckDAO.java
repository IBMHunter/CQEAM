package com.sino.ams.spare.check.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;

import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.spare.check.dto.AmsItemCheckHeaderDTO;
import com.sino.ams.spare.check.dto.AmsItemCheckLineDTO;
import com.sino.ams.spare.check.model.AmsBjCheckModel;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author TOTTI
 *         Date: 2007-11-27
 */
public class AmsBjCheckDAO extends AMSBaseDAO {
    private SfUserDTO sfUser = null;
    private AmsItemCheckHeaderDTO dto = null;

    public AmsBjCheckDAO(SfUserDTO userAccount, AmsItemCheckHeaderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        this.sfUser = userAccount;
        this.dto = (AmsItemCheckHeaderDTO) super.dtoParameter;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsItemCheckHeaderDTO dtoPara = (AmsItemCheckHeaderDTO) dtoParameter;
        super.sqlProducer = new AmsBjCheckModel((SfUserDTO) userAccount, dtoPara);
    }

    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("����ͷ��(AMS)");
    }

    public void updateData() throws DataHandleException {
        super.updateData();
        getMessage().addParameterValue("����ͷ��(AMS)");
    }

    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("����ͷ��(AMS)");
    }

    public boolean saveData(DTOSet lineSet, String[] barcode) throws SQLException {
        boolean success = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String transId = dto.getHeaderId();
            if (transId.equals("")) {
                SeqProducer seq = new SeqProducer(conn);
//                transId = seq.getStrNextSeq("AMS_INSTRU_TRANS_H_S");
                transId = seq.getGUID();
                dto.setHeaderId(transId);
                createData();
                AmsBjCheckModel model = new AmsBjCheckModel(sfUser, null);
                for (int i = 0; i < barcode.length; i++) {
                    String code = barcode[i];
                    DBOperator.updateRecord(model.insertLData(code, transId), conn);
                }
            } else {
                updateData();
                deleteLines(transId);
            }
            conn.commit();
            prodMessage("ASSETS_TRANSFER_SUCCESS");
            success = true;
        } catch (SQLException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SPARE_SAVE_FAILURE");
        } catch (DataHandleException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SPARE_SAVE_FAILURE");
        } finally {
            conn.setAutoCommit(autoCommit);
        }

        return success;
    }

    public boolean submitData(DTOSet lineSet) throws SQLException {
        boolean success = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            dto.setCreationDate(CalendarUtil.getCurrDate());
            OrderNumGenerator ong = new OrderNumGenerator(conn, sfUser.getCompanyCode(), "BJCHK");
            dto.setTransNo(ong.getOrderNum());
            dto.setOrderStatus("10");
            createData();


            conn.commit();
            prodMessage("SPARE_SAVE_SUCCESS");
            success = true;
        } catch (SQLException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SPARE_SAVE_FAILURE");
        } catch (DataHandleException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SPARE_SAVE_FAILURE");
        } catch (CalendarException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SPARE_SAVE_FAILURE");
        } finally {
            conn.setAutoCommit(autoCommit);
        }
        return success;
    }

    public void deleteLines(String transId) throws DataHandleException {
        AmsBjCheckModel model = new AmsBjCheckModel(sfUser, null);
        DBOperator.updateRecord(model.getDeleteByTransIdModel(transId), conn);
    }

    public RowSet getItem(String checkLocation, String headerId) throws QueryException {
        AmsBjCheckModel model = new AmsBjCheckModel(sfUser, null);
        SimpleQuery sq = new SimpleQuery(model.getByAddressId(checkLocation, headerId), conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }

    public DTOSet getLines(String headerId) throws QueryException {
        AmsBjCheckModel model = new AmsBjCheckModel(sfUser, null);
        SimpleQuery sq = new SimpleQuery(model.getByTransIdModel(headerId), conn);
        sq.setDTOClassName(AmsItemCheckLineDTO.class.getName());
        sq.executeQuery();

        return sq.getDTOSet();
    }

    public File exportFile(String checkLocation, String headerId) throws DataTransException {
        File file = null;

//            AmsItemCheckHeaderDTO itemDTO = (AmsItemCheckHeaderDTO) dtoParameter;
        SQLModel sqlModel = new SQLModel();
        AmsBjCheckModel model = new AmsBjCheckModel(sfUser, null);
        sqlModel = model.getByAddressId(checkLocation, headerId);
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setSourceConn(conn);

        String fileName = "�����̵�ͳ�Ʊ�.xls";
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);

        DataRange range = new DataRange();
        rule.setDataRange(range);

        Map fieldMap = new HashMap();
        fieldMap.put("BARCODE", "��������");
        fieldMap.put("ITEM_NAME", "��������");
        fieldMap.put("ITEM_SPEC", "����ͺ�");
        fieldMap.put("SYS_STATUS", "ϵͳ״̬");
        fieldMap.put("CHECK_STATUS", "�̵�״̬");
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

        return file;
    }
}
