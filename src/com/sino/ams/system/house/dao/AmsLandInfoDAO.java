package com.sino.ams.system.house.dao;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.system.fixing.dto.EtsItemInfoDTO;
import com.sino.ams.system.house.dto.AmsItemFilesDTO;
import com.sino.ams.system.house.dto.AmsLandInfoDTO;
import com.sino.ams.system.house.model.AmsItemFilesModel;
import com.sino.ams.system.house.model.AmsLandInfoModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsLandInfoDAO</p>
 * <p>Description:�����Զ����ɷ������AmsLandInfoDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author Zyun
 * @version 1.0
 */


public class AmsLandInfoDAO extends AMSBaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ����������ʲ�(EAM) AMS_LAND_INFO ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsLandInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsLandInfoDAO(SfUserDTO userAccount, AmsLandInfoDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsLandInfoDTO dtoPara = (AmsLandInfoDTO) dtoParameter;
        super.sqlProducer = new AmsLandInfoModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ��������������ʲ�(EAM)��AMS_LAND_INFO�����ݡ�
     *
     * @return boolean
     */
    public boolean createData(EtsItemInfoDTO itemInfoDTO ,String[] filePaths) throws DataHandleException {
        boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsLandInfoDTO landDTO = (AmsLandInfoDTO) dtoParameter;
             String barcode = landDTO.getBarcode();
            itemInfoDTO.setItemCode(landDTO.getItemCode());          //�������
            itemInfoDTO.setBarcode(landDTO.getBarcode());         //���Barcode
            EtsItemInfoDAO itemInfoDAO = new EtsItemInfoDAO(sfUser, itemInfoDTO, conn);
            boolean hasBarcode = hasBarcode(landDTO.getBarcode());
            if (hasBarcode) {                                       //����ڱ�ETS_ITEM_INFO����Barcode����������޸ģ�������в������
                itemInfoDAO.updateAttribute1(landDTO.getBarcode());             //����Barcode�Ա�ETS_ITEM_INFO�����޸Ĳ��������� ��ATTRIBUTE1����ֵ��Ϊ"RENT"  ���� Barcode
            } else {
                itemInfoDAO.createData();                   //�Ա� ETS_ITEM_INFO ���в������
            }
            super.createData();                             //�Ա� AMS_LAND_INFO ���в������
            if (landDTO.getIsRent().equals("Y")) {                                          //�жϱ�  AMS_LAND_INFO �е��ֶ� ��IS_RENT����ֵ ��1����=��Y���Ա� AMS_RENT_INFO ���в������
                creatRentData();                              //�����AMS_RENT_INFO�ֶ� barcode �������� RENT_ID �Զ�����
            }
              //---�Ա�AMS_ITEM_FILES���в���
            if(!StrUtil.isEmpty(filePaths)) {
             for(int i=0;i<filePaths.length;i++){
                 AmsItemFilesDTO fileDTO= new AmsItemFilesDTO();
                 fileDTO.setBarcode(barcode);
                 String filedp[]=  StrUtil.splitStr(filePaths[i],"$");
                 fileDTO.setFileDesc(filedp[0]);
                 fileDTO.setFilePath(filedp[1]);
                 AmsItemFilesDAO amsItemFilesDAO = new AmsItemFilesDAO(userAccount,fileDTO,conn);
                 amsItemFilesDAO.setDTOClassName(AmsItemFilesDTO.class.getName());
                 amsItemFilesDAO.createData();
                }
            }
            operateResult = true;
            conn.commit();
            hasError = false;
            prodMessage(MsgKeyConstant.CREATE_DATA_SUCCESS);
            getMessage().addParameterValue("��������");
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } catch (SQLModelException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } catch (QueryException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
            try {
                if (hasError) {
                    conn.rollback();                      //�ع�
                }
                conn.setAutoCommit(autoCommit);          //�ָ���ǰ״̬
            } catch (SQLException e) {
                Logger.logError(e);
                prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    /**
     * ���ܣ��޸����������ʲ�(EAM)��AMS_LAND_INFO�����ݡ�
     *
     * @return boolean
     */
    public boolean updateData(EtsItemInfoDTO itemInfoDTO, AmsItemFilesDTO fileDTO, String[] filePaths) throws DataHandleException {
        boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsLandInfoDTO landDTO = (AmsLandInfoDTO) dtoParameter;
            String barcode = landDTO.getBarcode();

            AmsLandInfoModel model = new AmsLandInfoModel(sfUser, landDTO);

            SQLModel landModel= model.getDataDeleteModel();
            DBOperator.updateRecord(landModel, conn);

            landModel = model.getDataCreateModel();
            DBOperator.updateRecord(landModel, conn);

            //super.updateData();            //�Ա�AMS_LAND_INFO�����޸Ĳ���
            if (landDTO.getIsRent().equals("Y")) {        //�� AMS_RENT_INFO ��RENT_ID �� �޸�  ��ɾ�� ������
                deleteRentData();
                creatRentData();
                 DBOperator.updateRecord( model.getAttribute1Model(barcode),conn  );
            } else {
                deleteRentData();          //�Ա�  AMS_RENT_INFO ����ɾ������
                 DBOperator.updateRecord( model.getAttribute1NotModel(barcode),conn  );
            }
            //--------------�Ա� AMS_ITEM_FILES  �����޸Ĳ���   ��ɾ��������
            AmsItemFilesModel sqlModel = new AmsItemFilesModel(sfUser, fileDTO);
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
            getMessage().addParameterValue("������Ϣ");
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

    /**
     * ���ܣ�ɾ�����������ʲ�(EAM)��AMS_LAND_INFO�����ݡ�
     *
     * @return boolean
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("�����ʲ�(EAM)");
//		return operateResult;
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


    public boolean verifyBarcode(String barcode) throws QueryException { //ִ��У��Barcode����
        boolean success = false;
        AmsLandInfoModel amsLandInfoModel = (AmsLandInfoModel) sqlProducer;
        SQLModel sqlModel = amsLandInfoModel.getVerifyBarcodeModel(barcode);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();
        return success;
    }


    public void creatRentData() throws SQLModelException { //ִ��AMS_RENT_INFO �����������
        try {
            AmsLandInfoModel amsLandInfoModel = (AmsLandInfoModel) sqlProducer;
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

    public void deleteRentData() throws SQLModelException { //ִ��AMS_RENT_INFO �����������
        try {
            AmsLandInfoModel amsLandInfoModel = (AmsLandInfoModel) sqlProducer;
            SQLModel sqlModel = amsLandInfoModel.doDeleteRentData();
            DBOperator.updateRecord(sqlModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("��������");
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("��������");
            ex.printLog();
        }
    }


    public boolean hasBarcode(String barcode) throws QueryException { //ִ��У��Barcode����
        boolean success = false;
        AmsLandInfoModel amsLandInfoModel = (AmsLandInfoModel) sqlProducer;
        SQLModel sqlModel = amsLandInfoModel.getVerifyBarcodeModel(barcode);

        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();

        return success;
    }
}