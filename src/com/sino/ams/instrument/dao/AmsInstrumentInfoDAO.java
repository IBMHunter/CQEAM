package com.sino.ams.instrument.dao;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.instrument.dto.AmsInstrumentInfoDTO;
import com.sino.ams.instrument.model.AmsInstrumentInfoModel;
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
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsInstrumentInfoDAO</p>
 * <p>Description:�����Զ����ɷ������AmsInstrumentInfoDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class AmsInstrumentInfoDAO extends AMSBaseDAO {
    AmsInstrumentInfoModel  modelProducer = null;
    /**
     * ���ܣ������Ǳ����(EAM) AMS_INSTRUMENT_INFO ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsInstrumentInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsInstrumentInfoDAO(SfUserDTO userAccount, AmsInstrumentInfoDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        modelProducer = (AmsInstrumentInfoModel)sqlProducer;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsInstrumentInfoDTO dtoPara = (AmsInstrumentInfoDTO) dtoParameter;
        super.sqlProducer = new AmsInstrumentInfoModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ����������Ǳ����(EAM)��AMS_INSTRUMENT_INFO�����ݡ�
     * @return boolean
     */
    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("�����Ǳ����(EAM)");
    }

    public String getAddressId() throws QueryException {
        String addresId = "";
        AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
        AmsInstrumentInfoModel model = new AmsInstrumentInfoModel(userAccount, amsInstrumentInfo);
        SimpleQuery sq = new SimpleQuery(model.selectAddressId(), conn);
        sq.executeQuery();
        if (sq.hasResult()) {
            RowSet row = sq.getSearchResult();
            try {
                addresId = (String) row.getRow(0).getValue("ADDRESS_ID");
            } catch (ContainerException e) {
                e.printStackTrace();
                throw new QueryException();
            }
        }
        return addresId;
    }
   //�޸Ĳ���
    public void update(Connection conn) throws SQLException, DataHandleException {
        try {
            conn.setAutoCommit(false);
            AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
            AmsInstrumentInfoModel model = new AmsInstrumentInfoModel(userAccount, amsInstrumentInfo);
            DBOperator.updateRecord(model.update(), conn);
            DBOperator.updateRecord(model.updateIfo(), conn);
            conn.commit();
        }
        catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
//            e.printStackTrace();
            Logger.logError(e);
            throw e;
        }
        catch (DataHandleException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            Logger.logError(e);
            throw e;
        }
    }


    public String insertData(Connection conn, String itemcode1,String addressId) throws SQLException, DataHandleException, QueryException {
        String mes = "";
        try {
            conn.setAutoCommit(false);
            AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
            AmsInstrumentInfoModel model = new AmsInstrumentInfoModel(userAccount, amsInstrumentInfo);
            SimpleQuery sq = new SimpleQuery(model.selectItemInfo(), conn);  //���ITEM�Ļ�����Ϣ
            sq.executeQuery();

            SimpleQuery s = new SimpleQuery(model.selectInfo(), conn);   //���������Ϣ
            s.executeQuery();

            /*SimpleQuery siq = new SimpleQuery(model.selectNO(), conn);    //���BARCODENO
            siq.executeQuery();*/


            if (s.getSearchResult().getSize() > 0) {
                mes = "���Ǿ������У�";
            } else {
                if (sq.getSearchResult().getSize() > 0) {
                    DBOperator.updateRecord(model.updateItem(), conn);                          //ETS_SYSTEM_ITEM  (���Ĺ�Ӧ��id)
                    DBOperator.updateRecord(model.insertDataNo(itemcode1,addressId), conn);     // ����ETS_ITEM_INFO  (����addressId)
                    DBOperator.updateRecord(model.getDataCreateModel(), conn);                  //����AMS_INSTRUMENT_INFO
                } else {
                    SeqProducer seq = new SeqProducer(conn);
                    String itemCode = StrUtil.nullToString(seq.getStrNextSeq("ETS_SYSTEM_ITEM_S"));
                    DBOperator.updateRecord(model.insertDataBase(itemCode), conn);            //ETS_SYSTEM_ITEM
                    DBOperator.updateRecord(model.insertDistrbute(itemCode), conn);           //ETS_SYSITEM_DISTRIBUTE (������Լ�)
                    DBOperator.updateRecord(model.insertDataNo(itemCode,addressId), conn);    //ETS_ITEM_INFO ������addressId��
                    DBOperator.updateRecord(model.getDataCreateModel(), conn);                //AMS_INSTRUMENT_INFO ���Ǿ���;��
                }

            }
            conn.commit();
        }
        catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
//            e.printStackTrace();
            Logger.logError(e);
            throw e;
        }
        catch (DataHandleException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            Logger.logError(e);
            throw e;
        }
        return mes;
    }

    /**
     * ���ܣ����������Ǳ����(EAM)��AMS_INSTRUMENT_INFO�����ݡ�
     * @return boolean
     */
    public void updateData() throws DataHandleException {
        super.updateData();
        getMessage().addParameterValue("�����Ǳ����(EAM)");
    }

    /**
     * ���ܣ�ɾ�������Ǳ����(EAM)��AMS_INSTRUMENT_INFO�����ݡ�
     * @return boolean
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("�����Ǳ����(EAM)");
    }

    public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);

            String fileName = "�����Ǳ�̨��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();
            fieldMap.put("ORGNIZATION_NAME", "��˾");
            fieldMap.put("BARCODE", "����");
            fieldMap.put("ITEM_NAME", "�����Ǳ�����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
            fieldMap.put("VENDOR_NAME", "�Ǳ���");
            fieldMap.put("MAINTAIN_DEPT_NAME", "ʹ�ò���");
            fieldMap.put("ITEM_QTY", "����");
            fieldMap.put("ATTRIBUTE3", "��;");
            fieldMap.put("OBJECT_NAME", "ʹ�õص�");
            fieldMap.put("MAINTAIN_USER", "ʹ����Ա");
            fieldMap.put("REMARK", "�Ǳ�����");
            fieldMap.put("ITEM_STATUS", "�Ǳ�״̬");
            fieldMap.put("RESPONSIBILITY_NAME", "������");
            fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���β���");
            fieldMap.put("ATTRIBUTE2", "����");
            fieldMap.put("TOTAL_PR", "�ܼ�");

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("�����Ǳ�̨��");
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

    /**
     * ���ܣ������Ǳ������˲�ѯ�ĵ���
     * @return
     * @throws DataTransException
     */
    public File exportRes() throws DataTransException {
        File file = null;
//        try {
            SQLModel sqlModel = modelProducer.getQueryRespModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);
            String fileName = "�����Ǳ�̨��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();
            fieldMap.put("ORGNIZATION_NAME", "��˾");
            fieldMap.put("BARCODE", "����");
            fieldMap.put("ITEM_NAME", "�����Ǳ�����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
            fieldMap.put("VENDOR_NAME", "�Ǳ���");
            fieldMap.put("MAINTAIN_DEPT_NAME", "ʹ�ò���");
            fieldMap.put("ITEM_QTY", "����");
            fieldMap.put("ATTRIBUTE3", "��;");
            fieldMap.put("OBJECT_NAME", "ʹ�õص�");
            fieldMap.put("MAINTAIN_USER", "ʹ����Ա");
            fieldMap.put("REMARK", "�Ǳ�����");
            fieldMap.put("ITEM_STATUS", "�Ǳ�״̬");
            fieldMap.put("RESPONSIBILITY_NAME", "������");
            fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���β���");
            fieldMap.put("ATTRIBUTE2", "����");
            fieldMap.put("TOTAL_PR", "�ܼ�");
            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("�����Ǳ�̨��");
            custData.setReportPerson(userAccount.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            /*rule.setSheetSize(1000);*/
            //���÷�ҳ��ʾ
            TransferFactory factory = new TransferFactory();
            DataTransfer transfer = factory.getTransfer(rule);
            transfer.transData();
            file = (File) transfer.getTransResult();
//        } catch (SQLModelException ex) {
//            ex.printLog();
//            throw new DataTransException(ex);
//        }
        return file;
    }


      //��Ҫ�Ƕ��Ǿ���;����ά��
  public String creatData(Connection conn, String itemcode1,String addressId) throws SQLException, DataHandleException, QueryException {
        String mes = "";
        try {
            conn.setAutoCommit(false);
            AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
            AmsInstrumentInfoModel model = new AmsInstrumentInfoModel(userAccount, amsInstrumentInfo);
//            SimpleQuery sq = new SimpleQuery(model.selectItemInfo(), conn);  //���ITEM�Ļ�����Ϣ
//            sq.executeQuery();

//            SimpleQuery s = new SimpleQuery(model.selectInfo(), conn);   //���������Ϣ
//            s.executeQuery();

            /*SimpleQuery siq = new SimpleQuery(model.selectNO(), conn);    //���BARCODENO
            siq.executeQuery();*/


//            if (s.getSearchResult().getSize() > 0) {
//                mes = "���Ǿ������У�";
//            } else {
//                if (sq.getSearchResult().getSize() > 0) {
//                    DBOperator.updateRecord(model.updateItem(), conn);                          //ETS_SYSTEM_ITEM
//                    DBOperator.updateRecord(model.insertDataNo(itemcode1,addressId), conn);     // ����ETS_ITEM_INFO
//                    DBOperator.updateRecord(model.getDataCreateModel(), conn);                  //����AMS_INSTRUMENT_INFO
//                } else {
                    SeqProducer seq = new SeqProducer(conn);
//                    String itemCode = seq.getStrNextSeq("ETS_SYSTEM_ITEM_S");
//                    DBOperator.updateRecord(model.insertDataBase(itemCode), conn);            //ETS_SYSTEM_ITEM��VENDOR_ID  INSTRUMENT��
//                    DBOperator.updateRecord(model.insertDistrbute(itemCode), conn);           //ETS_SYSITEM_DISTRIBUTE (������Լ�)
                    DBOperator.updateRecord(model.insertDataNo(itemcode1,addressId), conn);    //ETS_ITEM_INFO ������addressId ,ATTRIBUTE3��
//                    DBOperator.updateRecord(model.getDataCreateModel(), conn);                //AMS_INSTRUMENT_INFO ���Ǿ���;��
//                    DBOperator.updateRecord(model.getDataCreateModel(), conn);                //AMS_INSTRUMENT_INFO ���Ǿ���;��
//                }

//            }
            conn.commit();
        }
        catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            e.printStackTrace();
            throw e;
        }
        catch (DataHandleException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            Logger.logError(e);
            throw e;
        }
        return mes;
    }

//�޸������Ǳ���������
    public void updateDat(Connection conn) throws SQLException, DataHandleException {
        try {
            conn.setAutoCommit(false);
            AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
            AmsInstrumentInfoModel model = new AmsInstrumentInfoModel(userAccount, amsInstrumentInfo);
//            DBOperator.updateRecord(model.update(), conn);                   //�޸�  ETS_SYSTEM_ITEM��VENDOR_ID��ITEM_NAME��ITEM_SPEC��
            DBOperator.updateRecord(model.getUpusageModel(), conn);           // ETS_ITEM_INFO.ATTRIBUTE3 �����Ǿ���;
            conn.commit();
        }
        catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            Logger.logError(e);
            throw e;
        }
        catch (DataHandleException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            Logger.logError(e);
            throw e;
        }
    }


  public File exportFile2() throws DataTransException {
        File file = null;
//        try {

//            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            AmsInstrumentInfoDTO amsInstrumentInfo = (AmsInstrumentInfoDTO) dtoParameter;
            AmsInstrumentInfoModel model = new AmsInstrumentInfoModel(userAccount, amsInstrumentInfo);
            SQLModel sqlModel = model.getSQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);
            String fileName = "�����Ǳ�ͳ�Ʊ�.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();
            fieldMap.put("BARCODE", "��������");
            fieldMap.put("ITEM_NAME", "��������");
            fieldMap.put("ITEM_SPEC", "��������");
            fieldMap.put("MAINTAIN_NAME", "������");
            fieldMap.put("RESPONSIBILITY_NAME", "������");
            fieldMap.put("VENDOR_NAME", "��Ӧ��");
            fieldMap.put("BORROW_DATE", "��������");
            fieldMap.put("DAYS", "��������");
//            fieldMap.put("CREATION_DATE", "����ʱ��");
            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("�����Ǳ���ñ���");
            custData.setReportPerson(userAccount.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            /*rule.setSheetSize(1000);*/
            //���÷�ҳ��ʾ
            TransferFactory factory = new TransferFactory();
            DataTransfer transfer = factory.getTransfer(rule);
            transfer.transData();
            file = (File) transfer.getTransResult();
//        } catch (SQLModelException ex) {
//            ex.printLog();
//            throw new DataTransException(ex);
//        }
        return file;
    }
}