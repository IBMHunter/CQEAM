package com.sino.ams.expand.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Types;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

import com.sino.base.dto.DTO;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.conn.DBManager;
import com.sino.framework.dto.BaseUserDTO;

import com.sino.ams.system.house.dto.AmsItemFilesDTO;
import com.sino.ams.system.house.model.AmsItemFilesModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.expand.dto.EtsItemLandInfoDTO;
import com.sino.ams.expand.model.EtsItemLandInfoModel;
import com.sino.ams.appbase.dao.AMSBaseDAO;


/**
 * <p>Title: EtsItemLandInfoDAO</p>
 * <p>Description:�����Զ����ɷ������EtsItemLandInfoDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����
 * @version 1.0
 */


public class EtsItemLandInfoDAO extends AMSBaseDAO {

    /**
     * ���ܣ����������ʲ�(AMS) AMS_LAND_INFO ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemLandInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EtsItemLandInfoDAO(SfUserDTO userAccount, EtsItemLandInfoDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsItemLandInfoDTO dtoPara = (EtsItemLandInfoDTO) dtoParameter;
        super.sqlProducer = new EtsItemLandInfoModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ�ɾ�����������ʲ�(AMS)��AMS_LAND_INFO�����ݡ�
     *
     * @return boolean
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("�����ʲ�(AMS)");
//		return operateResult;
    }

    /**
     * ���ܣ��޸����������ʲ�(AMS)��AMS_LAND_INFO�����ݡ�
     *
     * @return boolean
     */
    public boolean updateData(AmsItemFilesDTO fileDTO, String[] filePaths) throws DataHandleException {
        boolean operateResult = false;
        boolean autoCommit = true;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            EtsItemLandInfoDTO landDTO = (EtsItemLandInfoDTO) dtoParameter;
            String barcode = landDTO.getBarcode();

            EtsItemLandInfoModel model = new EtsItemLandInfoModel(userAccount, landDTO);

//            SQLModel landModel= model.getDeleteLandModel();
//            DBOperator.updateRecord(landModel, conn);
//
//            landModel = model.getCreateLandModel();
//            DBOperator.updateRecord(landModel, conn);
            
            SQLModel landModel = model.getUpdateLandModel();
            DBOperator.updateRecord(landModel, conn);

            //super.updateData();            //�Ա�AMS_LAND_INFO�����޸Ĳ���
            if (landDTO.getIsRent().equals("Y")) {        //�� AMS_RENT_INFO ��RENT_ID �� �޸�  ��ɾ�� ������
//                deleteRentData();
//                creatRentData();
            	  updateRentData();
//                 DBOperator.updateRecord( model.getAttribute1Model(barcode),conn  );
            } else {
                deleteRentData();          //�Ա�  AMS_RENT_INFO ����ɾ������
//                 DBOperator.updateRecord( model.getAttribute1NotModel(barcode),conn  );
            }
            //--------------�Ա� AMS_ITEM_FILES  �����޸Ĳ���   ��ɾ��������
            AmsItemFilesModel sqlModel = new AmsItemFilesModel(userAccount, fileDTO);
            DBOperator.updateRecord(sqlModel.getDeleteModel(barcode), conn);
            if (!StrUtil.isEmpty(filePaths)) {
                for (int i = 0; i < filePaths.length; i++) {
                    fileDTO.setBarcode(barcode);
                    String filedp[] = StrUtil.splitStr(filePaths[i], "$");
                    fileDTO.setFileDesc(filedp[0]);
                    fileDTO.setFilePath(filedp[1]);
                    DBOperator.updateRecord(sqlModel.getCreateModel(), conn);
                }
            }
            operateResult = true;
            conn.commit();
            hasError = false;
            prodMessage(MsgKeyConstant.UPDATE_DATA_SUCCESS);
            getMessage().addParameterValue("���ع�����Ϣ");
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } catch (SQLModelException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } finally {
            try {
                if (hasError) {
                    conn.rollback();                      //�ع�
                }
                conn.setAutoCommit(autoCommit);          //�ָ���ǰ״̬
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    public void creatRentData() throws SQLModelException { //ִ��AMS_RENT_INFO �����������
        try {
        	EtsItemLandInfoModel amsLandInfoModel = (EtsItemLandInfoModel) sqlProducer;
            SQLModel sqlModel = amsLandInfoModel.doCreatRentData();
            DBOperator.updateRecord(sqlModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("��������");
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("��������");
            ex.printLog();
        }
    }

    public void updateRentData() throws SQLModelException { //ִ��AMS_RENT_INFO �����������
        try {
        	EtsItemLandInfoModel amsLandInfoModel = (EtsItemLandInfoModel) sqlProducer;
            SQLModel sqlModel = amsLandInfoModel.doUpdateRentData();
            DBOperator.updateRecord(sqlModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("��������");
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("��������");
            ex.printLog();
        }
    }
    
    public void deleteRentData() throws SQLModelException { //ִ��AMS_RENT_INFO ���ɾ������
        try {
        	EtsItemLandInfoModel landInfoModel = (EtsItemLandInfoModel) sqlProducer;
            SQLModel sqlModel = landInfoModel.doDeleteRentData();
            DBOperator.updateRecord(sqlModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("��������");
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("��������");
            ex.printLog();
        }
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
            SQLModel sqlModel = sqlProducer.getPageQueryModel();     //��ò�ѯ��sql
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "������Ϣ��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();
            fieldMap.put("BARCODE", "��������");
            fieldMap.put("LAND_AREA", "�������");
            fieldMap.put("AREA_UNIT", "�ػ���λ");
            fieldMap.put("LAND_CERTFICATE_NO", "����֤��");
            fieldMap.put("COUNTY_NAME", "����");
            fieldMap.put("IS_RENT", "�Ƿ�����");
            fieldMap.put("RENT_PERSON", "������");
            fieldMap.put("RENT_DATE", "��������");
            fieldMap.put("END_DATE", "��������");
            rule.setFieldMap(fieldMap);
            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("������Ϣ��");
            custData.setReportPerson(userAccount.getUsername());
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


    public boolean verifyBarcode(String barcode) throws QueryException { //ִ��У��Barcode����
        boolean success = false;
        EtsItemLandInfoModel amsLandInfoModel = (EtsItemLandInfoModel) sqlProducer;
        SQLModel sqlModel = amsLandInfoModel.getVerifyBarcodeModel(barcode);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();
        return success;
    }


    /**
     * ���ܣ�����Barcode���ݺš�
     *
     * @return ORDER_NUMBER
     * @throws
     */
    public String getOrderNum() {
        String no = null;
        String companyCode = "";
        companyCode = userAccount.getCompanyCode();
        CallableStatement cst = null;
        String sqlStr = "begin ? := AMS_BARCODE_PKG.GETASSETBARCODE(?); end;";
        try {
            cst = conn.prepareCall(sqlStr);
            cst.registerOutParameter(1, Types.VARCHAR);
            cst.setString(2, companyCode);
            cst.execute();
            no = cst.getString(1);
        } catch (SQLException e) {
            Logger.logError(e);
        } finally {
            DBManager.closeDBStatement(cst);
        }
        return no;
    }

    public boolean hasBarcode(String barcode) throws QueryException { //ִ��У��Barcode����
        boolean success = false;
        EtsItemLandInfoModel amsLandInfoModel = (EtsItemLandInfoModel) sqlProducer;
        SQLModel sqlModel = amsLandInfoModel.getVerifyBarcodeModel(barcode);

        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();

        return success;
    }
}