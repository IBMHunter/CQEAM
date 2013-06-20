package com.sino.ams.instrument.dao;

//import com.f1j.data.DataRange;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.instrument.dto.AmsInstrumentHDTO;
import com.sino.ams.instrument.dto.AmsInstrumentLDTO;
import com.sino.ams.instrument.model.AmsInstrVendorRetModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2008-2-25
 * Time: 11:26:56
 * To change this template use File | Settings | File Templates.
 */
public class AmsInstrVendorRetDAO extends AMSBaseDAO {
    private AmsInstrumentHDTO dto = null;

    public AmsInstrVendorRetDAO(SfUserDTO userAccount, AmsInstrumentHDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        this.dto = (AmsInstrumentHDTO) super.dtoParameter;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsInstrumentHDTO dtoPara = (AmsInstrumentHDTO) dtoParameter;
        super.sqlProducer = new AmsInstrVendorRetModel((SfUserDTO) userAccount, dtoPara);
    }

    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("����ͷ��(EAM)");
    }

    public void updateData() throws DataHandleException {
        super.updateData();
        getMessage().addParameterValue("����ͷ��(EAM)");
    }

    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("����ͷ��(EAM)");
    }

    public boolean saveData(DTOSet lineSet, String[] barcode) throws SQLException {
        boolean success = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String transId = dto.getTransId();
            if (transId.equals("")) {
                SeqProducer seq = new SeqProducer(conn);
                transId = StrUtil.nullToString(seq.getStrNextSeq("AMS_INSTRU_TRANS_H_S"));
                dto.setTransId(transId);
                createData();
                AmsInstrVendorRetModel model = new AmsInstrVendorRetModel(userAccount, null);
                for (int i = 0; i < barcode.length; i++) {
                    String code = barcode[i];
                    DBOperator.updateRecord(model.insertLData(code, transId), conn);
                }
            } else {
                updateData();
                deleteLines(transId);
            }
            saveLines(lineSet);
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
   //--���ɷ�����
    public boolean returnData(DTOSet lineSet, String[] barcode, String transId, String transStatus) throws SQLException {
        boolean success = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            dto.setConfirmDate(CalendarUtil.getCurrDate());
            AmsInstrVendorRetModel model = new AmsInstrVendorRetModel(userAccount, null);
            DBOperator.updateRecord(model.updateHModel(transId, transStatus), conn);    //�޸ı�  AMS_INSTRU_TRANS_H
            for (int i = 0; i < barcode.length; i++) {
                String code = barcode[i];
                DBOperator.updateRecord(model.updateInfoModel(code), conn);            //AMS_INSTRUMENT_INFO
                DBOperator.updateRecord(model.deleteByBarcodeNoModel(code), conn);      //������ AMS_INSTRUMENT_RESERVED
            }
            updateAddress(barcode);
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

    /**
     * ����: �ύ������
     * @param lineSet
     * @param barcode
     * @return
     * @throws SQLException
     */
    public boolean submitData(DTOSet lineSet, String[] barcode) throws SQLException {
        boolean success = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String transId = dto.getTransId();
            dto.setReturnDate(CalendarUtil.getCurrDate());
            OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), "INS-VRET");
            dto.setTransNo(ong.getOrderNum());
            if (transId.equals("")) {
                SeqProducer seq = new SeqProducer(conn);
                transId = StrUtil.nullToString(seq.getStrNextSeq("AMS_INSTRU_TRANS_H_S"));
                dto.setTransId(transId);
                createData();   //����ͷ��
                AmsInstrVendorRetModel model = new AmsInstrVendorRetModel(userAccount, null);
                for (int i = 0; i < barcode.length; i++) {      //���б������������
                    String code = barcode[i];
                    DBOperator.updateRecord(model.insertLData(code, transId), conn);
                   // ------
                    DBOperator.updateRecord(model.updateItemInfo(code), conn);    // ����ets_item_info�ģ��Ǿ߳����˱�Ϊ�գ���ʾ�ڿ⣩
                }
            } else {
                updateData();     //�޸�ͷ��
                deleteLines(transId);    //ɾ���б�
                AmsInstrVendorRetModel model = new AmsInstrVendorRetModel(userAccount, null);
                for (int i = 0; i < barcode.length; i++) {
                    String code = barcode[i];
                    DBOperator.updateRecord(model.insertLData(code, transId), conn);      //�����б����
                }
            }
            saveLines(lineSet);    //ͷ����в������

            /* if (dto.getTransType().equals(DictConstant.BJRK)) {
                addToItemInfo(lineSet);
            }*/

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

    private void updateAddress(String[] barcode) throws SQLException {
        CallableStatement cStmt = null;
        String sqlStr = "{call AMS_ITEM_TRANS.INSTRU_RETURN(?,?,?)}";
        try {
            cStmt = conn.prepareCall(sqlStr);
            for (int i = 0; i < barcode.length; i++) {
                cStmt.setString(1, barcode[i]);
                cStmt.setString(2, StrUtil.nullToString(userAccount.getOrganizationId()));
                cStmt.setString(3, StrUtil.nullToString(userAccount.getUserId()));
                cStmt.execute();
            }
        }
        finally {
            DBManager.closeDBStatement(cStmt);
        }
    }

    public void deleteLines(String transId) throws DataHandleException {
        AmsInstrVendorRetModel model = new AmsInstrVendorRetModel(userAccount, null);
        DBOperator.updateRecord(model.getDeleteByTransIdModel(transId), conn);
    }

    public void saveLines(DTOSet lineSet) throws DataHandleException {
        if (lineSet != null && !lineSet.isEmpty()) {
            AmsInstrumentBorrowDAO lineDAO = new AmsInstrumentBorrowDAO(userAccount, null, conn);
            for (int i = 0; i < lineSet.getSize(); i++) {
                AmsInstrumentHDTO lineData = (AmsInstrumentHDTO) lineSet.getDTO(i);
                lineData.setTransId(dto.getTransId());
                lineDAO.setDTOParameter(lineData);
                lineDAO.createData();
            }
        }
    }

    public DTOSet getLines() throws QueryException {
        AmsInstrVendorRetModel model = new AmsInstrVendorRetModel(userAccount, null);
        SimpleQuery sq = new SimpleQuery(model.getByTransIdModel(dto.getTransId()), conn);
        sq.setDTOClassName(AmsInstrumentLDTO.class.getName());
        sq.executeQuery();

        return sq.getDTOSet();
    }

    public File exportFile() throws DataTransException {
        File file = null;
        try {
            AmsInstrumentHDTO itemDTO = (AmsInstrumentHDTO) dtoParameter;
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);

            String fileName = "�Ǿ߷�������ͳ�Ʊ�.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("TRANS_NO", "���õ��ݺ�");
            fieldMap.put("BNAME", "������");
            fieldMap.put("RETURN_DATE", "��������");
            fieldMap.put("QNAME", "ȷ����");
            fieldMap.put("CONFIRM_DATE", "ȷ��ʱ��");
            fieldMap.put("TRANS_STATUS", "����״̬");
            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(fileName);
            custData.setReportPerson(userAccount.getUsername());
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
