package com.sino.ams.system.house.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.match.dao.BarcodeMatchDAO;
import com.sino.ams.match.dto.BarcodeMatchDTO;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dto.EtsFaAssetsDTO;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.fixing.dto.EtsItemInfoDTO;
import com.sino.ams.system.house.business.SystemItemHouse;
import com.sino.ams.system.house.dto.AmsHouseInfoDTO;
import com.sino.ams.system.house.dto.AmsHouseUsesDTO;
import com.sino.ams.system.house.dto.AmsItemFilesDTO;
import com.sino.ams.system.house.model.AmsHouseInfoModel;
import com.sino.ams.system.house.model.AmsItemFilesModel;
import com.sino.ams.system.house.model.GetMisHousInfoModel;
import com.sino.ams.system.house.model.RentHouseModel;
import com.sino.ams.system.switches.dao.EtsObjectDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.Row;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.GridQuery;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2008-5-28
 * Time: 11:11:53
 * ���ܣ�����������Ϣ��mis����
 */
public class GetMisHousInfoDAO extends AMSBaseDAO {
    private GetMisHousInfoModel modelProducer = null;

    /**
     * ���ܣ����������ʲ�(EAM) AMS_LAND_INFO ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsHouseInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public GetMisHousInfoDAO(SfUserDTO userAccount, AmsHouseInfoDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        modelProducer = (GetMisHousInfoModel) sqlProducer;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsHouseInfoDTO dtoPara = (AmsHouseInfoDTO) dtoParameter;
        super.sqlProducer = new GetMisHousInfoModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ��������������ʲ�(EAM)��AMS_LAND_INFO�����ݡ�
     * @return boolean
     */
    public boolean createData(EtsItemInfoDTO itemInfoDTO, String[] filePaths) throws DataHandleException {
        boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsHouseInfoDTO landDTO = (AmsHouseInfoDTO) dtoParameter;
            String barcode = landDTO.getBarcode();
            itemInfoDTO.setItemCode(landDTO.getItemCode()); //�������
            itemInfoDTO.setBarcode(landDTO.getBarcode()); //���Barcode
            EtsItemInfoDAO itemInfoDAO = new EtsItemInfoDAO(userAccount, itemInfoDTO, conn);
            boolean hasBarcode = hasBarcode(landDTO.getBarcode());
            if (hasBarcode) { //����ڱ�ETS_ITEM_INFO����Barcode����������޸ģ�������в������
                itemInfoDAO.updateAttribute1(landDTO.getBarcode()); //����Barcode�Ա�ETS_ITEM_INFO�����޸Ĳ��������� ��ATTRIBUTE1����ֵ��Ϊ"RENT"  ���� Barcode
            } else {
                itemInfoDAO.createData(); //�Ա� ETS_ITEM_INFO ���в������
            }
            super.createData(); //�Ա� AMS_LAND_INFO ���в������
            if (landDTO.getIsRent().equals("Y")) { //�жϱ�  AMS_LAND_INFO �е��ֶ� ��IS_RENT����ֵ ��1����=��Y���Ա� AMS_RENT_INFO ���в������
                creatRentData(); //�����AMS_RENT_INFO�ֶ� barcode �������� RENT_ID �Զ�����
            }
            //---�Ա�AMS_ITEM_FILES���в���
            if (!StrUtil.isEmpty(filePaths)) {
                for (int i = 0; i < filePaths.length; i++) {
                    AmsItemFilesDTO fileDTO = new AmsItemFilesDTO();
                    fileDTO.setBarcode(barcode);
                    String filedp[] = StrUtil.splitStr(filePaths[i], "$");
                    fileDTO.setFileDesc(filedp[0]);
                    fileDTO.setFilePath(filedp[1]);
                    AmsItemFilesDAO amsItemFilesDAO = new AmsItemFilesDAO(userAccount, fileDTO, conn);
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
                    conn.rollback(); //�ع�
                }
                conn.setAutoCommit(autoCommit); //�ָ���ǰ״̬
            } catch (SQLException e) {
                Logger.logError(e);
                prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }


    /**
     * ���ܣ��޸����ݡ�
     * @return boolean
     */
    public boolean updateData(EtsItemInfoDTO itemInfoDTO, AmsItemFilesDTO fileDTO, String[] filePaths, DTOSet lineSet) throws
            DataHandleException {
        boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsHouseInfoDTO HLDTO = (AmsHouseInfoDTO) dtoParameter;
            AmsHouseInfoModel model = new AmsHouseInfoModel(userAccount, HLDTO);
            SQLModel sqlModel1 = model.deleteUsesInfo();
            DBOperator.updateRecord(sqlModel1, conn);

            String barcode = HLDTO.getBarcode();
            super.updateData(); //�Ա�AMS_house_INFO�����޸Ĳ���

            itemInfoDTO.setBarcode(HLDTO.getBarcode()); // ��ǩ��
            itemInfoDTO.setItemCode(HLDTO.getItemCode()); //�������
            itemInfoDTO.setSystemid( HLDTO.getSystemId() ); // ������SYSTEMID�����޸ģ�

            if (!HLDTO.getTemp().equals("Y")) {
                GetMisHousInfoModel updateEII = new GetMisHousInfoModel(userAccount, HLDTO);
                SQLModel eiiSqlModel = updateEII.getEiiData(HLDTO.getBarcode());
                DBOperator.updateRecord(eiiSqlModel, conn);
            }

//            EtsItemInfoDAO itemInfoDAO = new EtsItemInfoDAO(userAccount,itemInfoDTO,conn);
//            itemInfoDAO.updateData();              //�Ա�ETS_ITEM_INFO�����޸Ĳ���


//            if (landDTO.getIsRent().equals("Y")) {        //�� AMS_RENT_INFO ��RENT_ID �� �޸�  ��ɾ�� ������
//                deleteRentData();
//                creatRentData();
//            } else {
//                deleteRentData();          //�Ա�  AMS_RENT_INFO ����ɾ������
//            }
            saveUses(lineSet);
            //--------------�Ա� AMS_ITEM_FILES  �����޸Ĳ���   ��ɾ��������
            AmsItemFilesModel sqlModel = new AmsItemFilesModel(userAccount, fileDTO);
            DBOperator.updateRecord(sqlModel.getDeleteModel(barcode), conn);
            if (!StrUtil.isEmpty(filePaths)) {
            	SeqProducer seq = new SeqProducer(conn);
                for (int i = 0; i < filePaths.length; i++) {
                    fileDTO.setBarcode(barcode);
                    String filedp[] = StrUtil.splitStr(filePaths[i], "$");
                    fileDTO.setFileDesc(filedp[0]);
                    fileDTO.setFilePath(filedp[1]);
                    fileDTO.setSystemId(seq.getGUID());
                    DBOperator.updateRecord(sqlModel.getCreateModel(), conn);
                }
            }
            operateResult = true;
            conn.commit();
            hasError = false;
            prodMessage(MsgKeyConstant.UPDATE_DATA_SUCCESS);
            getMessage().addParameterValue("δ������Ϣ");
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
//        } catch (SQLModelException ex) {
//            Logger.logError(ex);
//            prodMessage(MsgKeyConstant.SQL_ERROR);
        } catch (QueryException e) {
            Logger.logError(e);
//            prodMessage(MsgKeyConstant.SQL_ERROR);
        } finally {
            try {
                if (hasError) {
                    conn.rollback(); //�ع�
                }
                conn.setAutoCommit(autoCommit); //�ָ���ǰ״̬
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }


    /**
     * ���ܣ��޸����ݡ�
     * @return boolean
     */
    public boolean deleteNullData() throws DataHandleException {
        boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsHouseInfoDTO HLDTO = (AmsHouseInfoDTO) dtoParameter;
            AmsHouseInfoModel model = new AmsHouseInfoModel(userAccount, HLDTO);
            SQLModel sqlModel1 = model.deleteUsesInfo();
            DBOperator.updateRecord(sqlModel1, conn);

            String barcode = HLDTO.getBarcode();

//            super.updateData();            //�Ա�AMS_house_INFO�����޸Ĳ���

            GetMisHousInfoModel updateEII = new GetMisHousInfoModel(userAccount, HLDTO);
            SQLModel nullSqlModel = updateEII.updateNull(HLDTO.getBarcode());
            DBOperator.updateRecord(nullSqlModel, conn);

//            itemInfoDTO.setBarcode(HLDTO.getBarcode());       // ��ǩ��
//            itemInfoDTO.setItemCode(HLDTO.getItemCode());          //�������
//            itemInfoDTO.setSystemid(HLDTO.getSystemId());          // ������SYSTEMID�����޸ģ�

            //--------------�Ա� AMS_ITEM_FILES  �����޸Ĳ���   ��ɾ��������
            AmsItemFilesModel sqlModel = new AmsItemFilesModel(userAccount, null);
            DBOperator.updateRecord(sqlModel.getDeleteModel(barcode), conn);
            operateResult = true;
            conn.commit();
            hasError = false;
            prodMessage(MsgKeyConstant.UPDATE_DATA_SUCCESS);
            getMessage().addParameterValue("δ������Ϣ");
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } finally {
            try {
                if (hasError) {
                    conn.rollback(); //�ع�
                }
                conn.setAutoCommit(autoCommit); //�ָ���ǰ״̬
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    public boolean isTempSave(String barcode) throws QueryException {
        boolean has = false;
        GetMisHousInfoModel tsqlModel = new GetMisHousInfoModel(userAccount, null);
        SQLModel temModel = tsqlModel.isTempDate(barcode);
        SimpleQuery sqt = new SimpleQuery(temModel, conn);
        sqt.executeQuery();
        if (sqt.hasResult()) {
            has = true;
        }
//         isTempDate
        return has;
    }


    /**
     * ���ܣ�ɾ�����������ʲ�(EAM)��AMS_LAND_INFO�����ݡ�
     * @return boolean
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("�����ʲ�(EAM)");
//		return operateResult;
    }


    /**
     * ���ܣ�����Excel�ļ���
     * @return File
     * @throws com.sino.base.exception.DataTransException
     *
     */
    public File exportFile() throws DataTransException {
        File file = null;
        try {
//            SQLModel sqlModel = sqlProducer.getPageQueryModel();     //��ò�ѯ��sql
            AmsHouseInfoDTO HLDTO = (AmsHouseInfoDTO) dtoParameter;
            GetMisHousInfoModel updateEII = new GetMisHousInfoModel(userAccount, HLDTO);
            SQLModel sqlModel = updateEII.getHouseLandModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "�Ѵ�����������Ϣ��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();
            fieldMap.put("BARCODE", "����");
            fieldMap.put("ITEM_NAME", "����");
            fieldMap.put("ITEM_SPEC", "�ͺ�");
            fieldMap.put("HOUSE_ADDRESS", "�ص�");
            fieldMap.put("LAND_CERTFICATE_NO", "����֤��");
            fieldMap.put("HOUSE_AREA", "�������");
            fieldMap.put("OCCUPY_AREA", "ռ�����");
            fieldMap.put("OFFICE_USAGE", "��;");
            fieldMap.put("OFFICE_TYPE", "����");
            fieldMap.put("ATTRIBUTE2", "�Ƿ���");
            rule.setFieldMap(fieldMap);
            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("�Ѵ�����������Ϣ��");
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

    /**
     * ���ܣ�����Excel�ļ���
     * @return File
     * @throws com.sino.base.exception.DataTransException
     *
     */
    public File exportFileM() throws DataTransException {
        File file = null;
        try {
//            SQLModel sqlModel = sqlProducer.getPageQueryModel();     //��ò�ѯ��sql
            AmsHouseInfoDTO HLDTO = (AmsHouseInfoDTO) dtoParameter;
            GetMisHousInfoModel updateEII = new GetMisHousInfoModel(userAccount, HLDTO);
            SQLModel sqlModel = updateEII.getDispositionModel();
                       
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "����������Ϣ��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();
            fieldMap.put("BARCODE", "����");
            fieldMap.put("ITEM_NAME", "����");
            fieldMap.put("ITEM_SPEC", "�ͺ�");
            fieldMap.put("HOUSE_ADDRESS", "�ص�");
            fieldMap.put("HOUSE_AREA", "�������");
            fieldMap.put("OCCUPY_AREA", "ռ�����");
            fieldMap.put("OFFICE_USAGE", "��;");
            fieldMap.put("OFFICE_TYPE", "����");
            fieldMap.put("HOUSE_CERTIFICATE_NO", "����֤��");
            fieldMap.put("LAND_CERTFICATE_NO", "����֤��");
            fieldMap.put("ATTRIBUTE2", "�Ƿ���");
            rule.setFieldMap(fieldMap);
            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("����������Ϣ��");
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


    /**
     * ���ܣ�����δ�����Excel�ļ���
     * @return File
     * @throws com.sino.base.exception.DataTransException
     *
     */
    public File exportMisFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel(); //��ò�ѯ��sql
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "δ���뷿��������Ϣ��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();
            fieldMap.put("TAG_NUMBER", "����");
            fieldMap.put("ASSETS_DESCRIPTION", "����");
            fieldMap.put("MODEL_NUMBER", "�ͺ�");
            fieldMap.put("ASSIGNED_TO_NAME", "������");
            fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
            fieldMap.put("ASSETS_LOCATION", "�ص�");
            rule.setFieldMap(fieldMap);
            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("��Ϣ��");
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
        GetMisHousInfoModel getMisHousInfoModel = (GetMisHousInfoModel) sqlProducer;
        SQLModel sqlModel = getMisHousInfoModel.getVerifyBarcodeModel(barcode);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();
        return success;
    }


    public void creatRentData() throws SQLModelException { //ִ��AMS_RENT_INFO �����������
        try {
            GetMisHousInfoModel getMisHousInfoModel = (GetMisHousInfoModel) sqlProducer;
            SQLModel sqlModel = getMisHousInfoModel.doCreatRentData();
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
            GetMisHousInfoModel getMisHousInfoModel = (GetMisHousInfoModel) sqlProducer;
            SQLModel sqlModel = getMisHousInfoModel.doDeleteRentData();
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
     * ���ܣ�����Barcode���ݺš�
     * @return ORDER_NUMBER
     * @throws
     */

    public boolean hasBarcode(String barcode) throws QueryException { //ִ��У��Barcode����
        boolean success = false;
        GetMisHousInfoModel getMisHousInfoModel = (GetMisHousInfoModel) sqlProducer;
        SQLModel sqlModel = getMisHousInfoModel.getVerifyBarcodeModel(barcode);

        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();

        return success;
    }


    /**
     * ���ܣ�����mis�ķ��ݺ����ص���Ϣ
     * @return boolean
     */
    public boolean getMisInfo() throws DataHandleException {
        boolean operateResult = false;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            SQLModel sqlModel = modelProducer.getPageQueryModel();
            GridQuery sq = new GridQuery(sqlModel, conn);
            sq.setDTOClassName(EtsFaAssetsDTO.class.getName());
            sq.setPageSize(2000);
            sq.executeQuery();
            while (sq.nextPage()) {
                DTOSet dtos = sq.getDTOSet();
                importEAMhouse(dtos);
            }
            operateResult = true;
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (QueryException ex) {
            ex.printLog();
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                    prodMessage(AssetsMessageKeys.READ_HOUSE_SUCCESS);
                } else {
                    conn.rollback(); //�ع�
                    prodMessage(AssetsMessageKeys.READ_HOUSE_FAILURE);
                }
                message.setIsError(!operateResult);
                conn.setAutoCommit(autoCommit); //�ָ���ǰ״̬
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    /**
     * �ύ����
     * @param dtoSet
     * @return
     * @throws QueryException
     * @throws DataHandleException
     */
    private boolean importEAMhouse(DTOSet dtoSet) throws DataHandleException {
        boolean operatorResult = false;
        try {
            if (dtoSet != null && dtoSet.getSize() > 0) {
                EtsFaAssetsDTO faDTO = null;
                EtsItemInfoDTO itemDTO = null;
                AmsHouseInfoDTO houseDTO = null;
                SystemItemHouse systemItemUtil = new SystemItemHouse();
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    faDTO = (EtsFaAssetsDTO) dtoSet.getDTO(i);
                    itemDTO = new EtsItemInfoDTO();
                    itemDTO.setBarcode(faDTO.getTagNumber());
                    itemDTO.setItemName(faDTO.getAssetsDescription());
                    itemDTO.setItemSpec(faDTO.getModelNumber());
                    itemDTO.setItemQty(StrUtil.nullToString(faDTO.getCurrentUnits()));
                    itemDTO.setStartDate(faDTO.getDatePlacedInService().toString());
                    itemDTO.setItemCategory("HOUSE");
                    itemDTO.setAssetId(faDTO.getAssetId());
                    itemDTO.setOrganizationId(faDTO.getOrganizationId());
                    itemDTO.setFinanceProp("ASSETS");
                    itemDTO = systemItemUtil.checkSysItem(conn, itemDTO);
                    appendProjIdData(itemDTO, faDTO);
                    appendUserData(itemDTO, faDTO);
                    appendAddressData(itemDTO, faDTO);
                    if (StrUtil.isEmpty( itemDTO.getAddressId() )) {
                        addAddressData(faDTO);
                        appendAddressData(itemDTO, faDTO);
                    }
                    if (StrUtil.isEmpty( itemDTO.getAddressId() )) {
                        continue;
                    }
                    houseDTO = new AmsHouseInfoDTO();
                    houseDTO.setBarcode(faDTO.getTagNumber());
                    houseDTO.setHouseAddress(faDTO.getAssetsLocation());
                    houseDTO.setHremark("house��Ϣ����");
                    itemDTO.setSystemId(getNextSystemId());
                    itemDTO.setRemark("house��Ϣ����");
                    if (!isExist(faDTO)) {
                        insertEtsItemInfo(itemDTO); // IN  ams_item_info
                    }
                    insertHouesInfo(houseDTO); // IN ams_house_info
                    matchData(itemDTO, faDTO);                                   //����ƥ�����
                }
            }
            operatorResult = true;
        } catch (DataHandleException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (ContainerException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        } catch (QueryException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        }
        return operatorResult;
    }

    /**
     * ���ܣ�����ets_item_info
     * @throws QueryException
     * @throws DataHandleException
     */
    private void insertEtsItemInfo(EtsItemInfoDTO etsItemInfo) throws DataHandleException {
        SQLModel sqlModel = null;
        try {
            sqlModel = modelProducer.getItemCreateModel(etsItemInfo);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        }
    }

    /**
     * ���ܣ�����ETS_PA_PROJECTS_ALL
     * @throws QueryException
     * @throws DataHandleException
     */
    private void insertProjectInfo(EtsItemInfoDTO itemDTO, EtsFaAssetsDTO faDTO) throws DataHandleException {
        SQLModel sqlModel = null;
        try {
            sqlModel = modelProducer.getProjectCreateModel(itemDTO, faDTO);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        }
    }

    /**
     * ���ܣ�����ams_house_info
     * @throws QueryException
     * @throws DataHandleException
     */
    private void insertHouesInfo(AmsHouseInfoDTO houseDTO) throws DataHandleException {
        SQLModel sqlModel = modelProducer.insertHouse(houseDTO);
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ����MIS��Ա����Ϣ
     * @throws QueryException
     * @throws ContainerException
     */
    private void appendUserData(EtsItemInfoDTO itemDTO, EtsFaAssetsDTO faDTO) throws QueryException {
        try {
            SQLModel sqlModel = modelProducer.getMISemployee(faDTO.getAssignedToNumber());
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            if (sq.hasResult()) {
                Row row = sq.getFirstRow();
                itemDTO.setResponsibilityDept(row.getStrValue("DEPT_CODE"));
                itemDTO.setResponsibilityUser(row.getStrValue("EMPLOYEE_ID"));
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
    }

    /**
     * ���ܣ����MIS��Ա����Ϣ
     * @throws QueryException
     * @throws ContainerException
     */
    private void appendAddressData(EtsItemInfoDTO itemDTO, EtsFaAssetsDTO faDTO) throws QueryException {
        try {
            SQLModel sqlModel = modelProducer.getLocId(faDTO.getAssetsLocationCode(), faDTO.getOrganizationId());
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            if (sq.hasResult()) {
                Row row = sq.getFirstRow();
                itemDTO.setAddressId((String)row.getValue("ADDRESS_ID"));
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
    }

    /**
     * ���ܣ����ProjectId
     * @throws QueryException
     * @throws ContainerException
     */
    private void appendProjIdData(EtsItemInfoDTO itemDTO, EtsFaAssetsDTO faDTO) throws QueryException {
        try {
            SQLModel sqlModel = modelProducer.getProId(faDTO.getProjectid());
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            if (sq.hasResult()) {
                Row row = sq.getFirstRow();
                itemDTO.setProjectid((String)row.getValue("PROJECT_ID"));
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
    }

    /**
     * ����:�ж�barcode�Ĵ����ԡ�
     * @return
     */

    private boolean isExist(EtsFaAssetsDTO faDTO) throws QueryException {
        boolean hasResule = false;
        SQLModel sqlModel = modelProducer.hasBarcode(faDTO.getTagNumber());
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        if (sq.hasResult()) {
            hasResule = true;
        }
        return hasResule;
    }

    /**
     * ����:�ж���Ŀid�Ĵ����ԡ�
     * @return
     */

    private boolean isProjectId(EtsFaAssetsDTO faDTO) throws QueryException {
        boolean hasResule = false;
        SQLModel sqlModel = modelProducer.hasProjId(faDTO.getProjectid());
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        if (sq.hasResult()) {
            hasResule = true;
        }
        return hasResule;
    }

    public void saveUses(DTOSet lineSet) throws DataHandleException, QueryException {
        AmsHouseInfoDTO houseDTO = (AmsHouseInfoDTO) dtoParameter;
        if (lineSet != null && !lineSet.isEmpty()) {
            for (int i = 0; i < lineSet.getSize(); i++) {
                AmsHouseUsesDTO lineData = (AmsHouseUsesDTO) lineSet.getDTO(i);
                lineData.setBarcode(houseDTO.getBarcode());
                if (!(lineData.getArea().equals("") || lineData.getUsage().equals(""))) {
                    GetMisHousInfoModel model = new GetMisHousInfoModel(userAccount, houseDTO);
                    SQLModel sqlModel = model.insertUsesInfo(lineData);
                    DBOperator.updateRecord(sqlModel, conn);
                }

            }
        }
    }

    public DTOSet getUses() throws QueryException {
        GetMisHousInfoModel model = (GetMisHousInfoModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(model.getUsesInfo(), conn);
        sq.setDTOClassName(AmsHouseUsesDTO.class.getName());
        sq.executeQuery();
        return sq.getDTOSet();
    }


    private String getNextSystemId() throws SQLException {
        SeqProducer seqProducer = new SeqProducer(conn);
        return seqProducer.getGUID();
    }


    /**
     * ���ܣ�����ص���Ϣ
     * @throws DataHandleException
     */
    private void addAddressData(EtsFaAssetsDTO faDTO) throws DataHandleException, QueryException {
        try {
            EtsObjectDTO obDTO = new EtsObjectDTO();
            obDTO.setWorkorderObjectCode(faDTO.getAssetsLocationCode());
            obDTO.setWorkorderObjectName(faDTO.getAssetsLocation());
            obDTO.setWorkorderObjectLocation(faDTO.getAssetsLocation());
            obDTO.setOrganizationId(faDTO.getOrganizationId());
            obDTO.setObjectCategory("81");
            SQLModel sqlModel = modelProducer.getCount(faDTO.getDepreciationAccount().substring(5, 11));
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            if (sq.hasResult()) {
                Row row = sq.getFirstRow();
                obDTO.setCountyCode(row.getStrValue("COUNTY_CODE"));
            }
            if (obDTO.getWorkorderObjectName().equals("")) {
                return;
            }
         
            EtsObjectDAO etsObjectDAO = new EtsObjectDAO(userAccount, obDTO, conn);
            etsObjectDAO.createData2();
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
    }

    /**
     * ���ܣ�����ص���Ϣ
     * @throws DataHandleException
     */
    private void matchData(EtsItemInfoDTO itemDTO, EtsFaAssetsDTO faDTO) throws DataHandleException {
        String[] systemids = new String[]{itemDTO.getSystemId() + "@@@" + faDTO.getAssetId()};
        BarcodeMatchDTO baDTO = new BarcodeMatchDTO();
        BarcodeMatchDAO barcodeMatchDAO = new BarcodeMatchDAO(userAccount, baDTO, conn);
        barcodeMatchDAO.doMatch(systemids);
    }

    /**
     * ����:�ж��Ƿ�ȫʡ���޹���Ա;
     * @return
     */
    public String isProvince() throws QueryException {
        String isProvince = "N";
        SQLModel sqlModel = null;
        RentHouseModel sqlProducer = new RentHouseModel(userAccount, null);
        sqlModel = sqlProducer.isProvince();
        SimpleQuery spl = new SimpleQuery(sqlModel, conn);
        spl.executeQuery();
        if (spl.hasResult()) {
            isProvince = "Y";
        }
        return isProvince;
    }

    public void efficientData(String[] barcodes) { //ִ��������ЧЧ����
//		EtsObjectDTO dtoPara = (EtsObjectDTO) dtoParameter;
        AmsHouseInfoDTO landDTO = (AmsHouseInfoDTO) dtoParameter;
        try {
            SQLModel sqlModel = modelProducer.UpdateBarsModel(barcodes);
            DBOperator.updateRecord(sqlModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("����");
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("����");
			ex.printLog();
		}
	}
}
