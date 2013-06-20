package com.sino.ams.system.house.dao;


import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.system.fixing.dto.EtsItemInfoDTO;
import com.sino.ams.system.house.dto.AmsHouseInfoDTO;
import com.sino.ams.system.house.dto.AmsHouseUsesDTO;
import com.sino.ams.system.house.dto.AmsItemFilesDTO;
import com.sino.ams.system.house.model.AmsHouseInfoModel;
import com.sino.ams.system.house.model.AmsItemFilesModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MsgKeyConstant;
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
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.base.web.request.upload.UploadFile;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsHouseInfoDAO</p>
 * <p>Description:�����Զ����ɷ������AmsHouseInfoDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class AmsHouseInfoDAO extends AMSBaseDAO {

    private SfUserDTO sfUser = null;
    private int orderLength;

    /**
     * ���ܣ����޷���(EAM) AMS_HOUSE_INFO ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsHouseInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsHouseInfoDAO(SfUserDTO userAccount, AmsHouseInfoDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsHouseInfoDTO dtoPara = (AmsHouseInfoDTO) dtoParameter;
        super.sqlProducer = new AmsHouseInfoModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ��������޷���(EAM)��AMS_HOUSE_INFO�����ݡ�
     * @return boolean
     */
    public boolean createData(EtsItemInfoDTO itemInfoDTO, String systemId, String[] filePaths, DTOSet lineSet) throws DataHandleException, QueryException, SQLModelException {          //do_save����
        boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsHouseInfoDTO houseDTO = (AmsHouseInfoDTO) dtoParameter;
            String barcode = houseDTO.getBarcode();
            itemInfoDTO.setItemCode(houseDTO.getItemCode());          //�������
//            itemInfoDTO.setBarcode(getOrderNum());
            itemInfoDTO.setBarcode(houseDTO.getBarcode());         //���Barcode
            itemInfoDTO.setAddressId( houseDTO.getAddressId() );        //��� address_Id
//            itemInfoDTO.setAttribute1(DictConstant.HOUSE);

//            itemInfoDTO.setBarcode(getBarcode1(houseDTO));         //��üӹ���Barcode
            EtsItemInfoDAO itemInfoDAO = new EtsItemInfoDAO(sfUser, itemInfoDTO, conn);


            boolean hasBarcode = hasBarcode(houseDTO.getBarcode());
            if (hasBarcode) {                                       //����ڱ�ETS_ITEM_INFO����Barcode����������޸ģ�������в������
                itemInfoDAO.updateAttribute1(houseDTO.getBarcode());             //����Barcode�Ա�ETS_ITEM_INFO�����޸Ĳ��������� ��ATTRIBUTE1����ֵ��Ϊ"HOUSE"  ���� Barcode


            } else {
                itemInfoDAO.createData();                   //�Ա� ETS_ITEM_INFO ���в������
            }

//             itemInfoDAO.createData();

//            AmsHouseInfoDTO houseDTO = (AmsHouseInfoDTO)dtoParameter;
//            houseDTO.setBarcode(itemInfoDTO.getBarcode());     //������ ETS_ITEM_INFO ��ͬ��barcode
//            setDTOParameter(houseDTO);                            //����dto

            super.createData();                             //�Ա� AMS_HOUSE_INFO ���в������
            saveUses(lineSet);
            if (houseDTO.getIsRent().equals("Y")) {                                          //�жϱ�  AMS_HOUSE_INFO �е��ֶ� ��IS_RENT����ֵ ��1����=��Y���Ա� AMS_RENT_INFO ���в������

                creatRentData();                              //�����AMS_RENT_INFO�ֶ� barcode �������� RENT_ID �Զ�����
            }

            //---�Ա�AMS_ITEM_FILES���в���
            if (!StrUtil.isEmpty(filePaths)) {
                for (int i = 0; i < filePaths.length; i++) {
                    AmsItemFilesDTO fileDTO = new AmsItemFilesDTO();
                    fileDTO.setBarcode(barcode);
//                 String filedp[]= filePaths[i].split("$");
                    String filedp[] = StrUtil.splitStr(filePaths[i], "$");
                    fileDTO.setFileDesc(filedp[0]);
                    fileDTO.setFilePath(filedp[1]);
//                 System.out.println("--------------------------------------------------");
//                 System.out.println("filedp[0]&filedp[1]="+filedp[0]+"&"+filedp[1]);
                    AmsItemFilesDAO amsItemFilesDAO = new AmsItemFilesDAO(userAccount, fileDTO, conn);
                    amsItemFilesDAO.setDTOClassName(AmsItemFilesDTO.class.getName());
                    amsItemFilesDAO.createData();
//                forwardURL="/servlet/com.sino.ams.system.house.servlet.AmsItemFilesServlet?barcode=UPLOAD_ACTION&fileDesc="+filePath[i];
                }

            }


            operateResult = true;
            conn.commit();
            hasError = false;
            prodMessage(MsgKeyConstant.CREATE_DATA_SUCCESS);
            getMessage().addParameterValue("������Ϣ");
        } catch (SQLException ex) {
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

    public void saveUses(DTOSet lineSet) throws DataHandleException, QueryException {
        AmsHouseInfoDTO houseDTO = (AmsHouseInfoDTO) dtoParameter;
        if (lineSet != null && !lineSet.isEmpty()) {
            for (int i = 0; i < lineSet.getSize(); i++) {
                AmsHouseUsesDTO lineData = (AmsHouseUsesDTO) lineSet.getDTO(i);
                lineData.setBarcode(houseDTO.getBarcode());
                if (!(lineData.getArea().equals("") || lineData.getUsage().equals(""))) {
                    AmsHouseInfoModel model = new AmsHouseInfoModel(sfUser, houseDTO);
                    SQLModel sqlModel = model.insertUsesInfo(lineData);
                    DBOperator.updateRecord(sqlModel, conn);
                }

            }
        }
    }

    public DTOSet getUses() throws QueryException {
        AmsHouseInfoModel model = (AmsHouseInfoModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(model.getUsesInfo(), conn);
        sq.setDTOClassName(AmsHouseUsesDTO.class.getName());
        sq.executeQuery();
        return sq.getDTOSet();
    }

    public void upAttribute1Data(String systemId) { //ִ��ETS_ITEM_INFO ��� ATTRIBUTE1�ֶε��޸Ĳ���
        try {
            AmsHouseInfoModel amsHouseInfoModel = (AmsHouseInfoModel) sqlProducer;
            SQLModel sqlModel = amsHouseInfoModel.getAttribute1Model(systemId);
            DBOperator.updateRecord(sqlModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("���޷�������");
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("���޷�������");
            ex.printLog();
        }
    }

    /**
     * ���ܣ��������޷���(EAM)��AMS_HOUSE_INFO�����ݡ�
     * @return boolean
     */
    public boolean updateData(EtsItemInfoDTO itemInfoDTO, AmsItemFilesDTO fileDTO, String[] filePaths, DTOSet lineSet) throws DataHandleException, SQLModelException, QueryException {                     //�޸�����

//        boolean operateResult = super.updateData();

//        getMessage().addParameterValue("���޷�������");
//		return operateResult;
        boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsHouseInfoDTO houseDTO = (AmsHouseInfoDTO) dtoParameter;
            AmsHouseInfoModel model = new AmsHouseInfoModel(sfUser, houseDTO);
            SQLModel sqlModel1 = model.deleteUsesInfo();
            DBOperator.updateRecord(sqlModel1, conn);

            sqlModel1 = model.getDataDeleteModel();
            DBOperator.updateRecord(sqlModel1, conn);
            String barcode = houseDTO.getBarcode();
            if (houseDTO.getIsRent().equals("Y")) {
                DBOperator.updateRecord(model.getAttribute1Model(barcode), conn);
            } else if (houseDTO.getIsRent().equals("N")) {
                DBOperator.updateRecord(model.getAttribute1NotModel(barcode), conn);
            }
            sqlModel1 = model.getDataCreateModel();
            DBOperator.updateRecord(sqlModel1, conn);
//            houseDTO.setBarcode(getBarcode());
//            setDTOParameter(houseDTO);

//            super.updateData();            //�Ա�AMS_HOUSE_INFO�����޸Ĳ���
//            itemInfoDTO.setBarcode(houseDTO.getBarcode());       // ��ǩ��
//            itemInfoDTO.setItemCode(houseDTO.getItemCode());          //�������
//            itemInfoDTO.setSystemid(houseDTO.getSystemId());          // ������SYSTEMID�����޸ģ�
//            EtsItemInfoDAO itemInfoDAO = new EtsItemInfoDAO(sfUser,itemInfoDTO,conn);
//            itemInfoDAO.updateData();              //�Ա�ETS_ITEM_INFO�����޸Ĳ���

            saveUses(lineSet);
            if (houseDTO.getIsRent().equals("Y")) {        //�� AMS_RENT_INFO ��RENT_ID �� �޸�  ��ɾ�� ������
                deleteRentData();
                creatRentData();

            } else {
                deleteRentData();             //�Ա�  AMS_RENT_INFO ����ɾ������
            }

            //--------------�Ա� AMS_ITEM_FILES  �����޸Ĳ���   ��ɾ��������
            AmsItemFilesModel sqlModel = new AmsItemFilesModel(sfUser, fileDTO);
            DBOperator.updateRecord(sqlModel.getDeleteModel(barcode), conn);
            if (!StrUtil.isEmpty(filePaths)) {
                for (int i = 0; i < filePaths.length; i++) {
//                 AmsItemFilesDTO fileDTO= new AmsItemFilesDTO();
                    fileDTO.setBarcode(barcode);
//                 String filedp[]= filePaths[i].split("$");
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
     * ���ܣ�ɾ�����޷���(EAM)��AMS_HOUSE_INFO�����ݡ�
     * @return boolean
     */
    public void deleteData() throws DataHandleException {
//		boolean operateResult = super.deleteData();
        super.deleteData();
        getMessage().addParameterValue("���޷�������");
//		return operateResult;
    }


    private int getBarcode() throws SQLException {
        SeqProducer seqProducer = new SeqProducer(conn);
        return seqProducer.getStrNextSeq("AMS_HOUSE_INFO_S");
    }


    private String getBarcode1(AmsHouseInfoDTO houseDTO) throws SQLException {
        String barcode1 = sfUser.getCompanyCode();
        String barcode2 = barcode1 + '-';
        String barcode3 = houseDTO.getBarcode();
        String barcode4 = barcode2 + barcode3;
        return barcode4;
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
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
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
            fieldMap.put("HOUSE_CERTIFICATE_NO", "��Ȩ֤��");
            fieldMap.put("COUNTY_NAME", "��������");
            fieldMap.put("HOUSE_ADDRESS", "���ڵ�ַ");
            fieldMap.put("IS_RENT", "�Ƿ�����");
            fieldMap.put("RENT_PERSON", "������");
            fieldMap.put("RENT_DATE", "��������");
            fieldMap.put("RENTAL", "���");
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


    public boolean uploadFiles(UploadFile[] files) {
        boolean operateResult = false;
//        getMessage().addParameterValue("");
        return operateResult;
    }


    public void disabledData(String[] systemIds) { //ִ������ʧЧ����
        try {
            AmsHouseInfoModel amsHouseInfoModel = (AmsHouseInfoModel) sqlProducer;
            SQLModel sqlModel = amsHouseInfoModel.getDisableModel(systemIds);
            DBOperator.updateRecord(sqlModel, conn);
            prodMessage(CustMessageKey.DISABLE_SUCCESS);
            getMessage().addParameterValue("���޷�������");
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.DISABLE_FAILURE);
            getMessage().addParameterValue("���޷�������");
            ex.printLog();
        }
    }

    public void efficientData(String[] systemIds) { //ִ��������ЧЧ����
        try {
            AmsHouseInfoModel amsHouseInfoModel = (AmsHouseInfoModel) sqlProducer;
            SQLModel sqlModel = amsHouseInfoModel.getEnableModel(systemIds);
            DBOperator.updateRecord(sqlModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("���޷�������");
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("���޷�������");
            ex.printLog();
        }
    }


    public void creatRentData() throws SQLModelException { //ִ��AMS_RENT_INFO �����������
        try {
            AmsHouseInfoModel amsHouseInfoModel = (AmsHouseInfoModel) sqlProducer;
            SQLModel sqlModel = amsHouseInfoModel.doCreatRentData();
            DBOperator.updateRecord(sqlModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("���޷�������");
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("���޷�������");
            ex.printLog();
        }
    }

    public void deleteRentData() throws SQLModelException { //ִ��AMS_RENT_INFO �����������
        try {
            AmsHouseInfoModel amsHouseInfoModel = (AmsHouseInfoModel) sqlProducer;
            SQLModel sqlModel = amsHouseInfoModel.doDeleteRentData();
            DBOperator.updateRecord(sqlModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("���޷�������");
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("���޷�������");
            ex.printLog();
        }
    }


    public void updateRentData() throws SQLModelException { //ִ��AMS_RENT_INFO ����޸Ĳ���
        try {
            AmsHouseInfoModel amsHouseInfoModel = (AmsHouseInfoModel) sqlProducer;
            SQLModel sqlModel = amsHouseInfoModel.doUpdateRentData();
            DBOperator.updateRecord(sqlModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("���޷�������");
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("���޷�������");
            ex.printLog();
        }
    }

    public boolean verifyBarcode(String barcode) throws QueryException { //ִ��У��Barcode����
        boolean success = false;
//        try {
        AmsHouseInfoModel amsHouseInfoModel = (AmsHouseInfoModel) sqlProducer;
        SQLModel sqlModel = amsHouseInfoModel.getVerifyBarcodeModel(barcode);

        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();

//                  DBOperator.updateRecord(sqlModel, conn);
//			prodMessage(CustMessageKey.DISABLE_SUCCESS);
//			getMessage().addParameterValue("���޷�������");
//             success = true;
//        } catch (DataHandleException ex) {
//			prodMessage(CustMessageKey.DISABLE_FAILURE);
//			getMessage().addParameterValue("���޷�������");
//			ex.printLog();

//        } catch (QueryException ex) {
//            prodMessage(CustMessageKey.DISABLE_FAILURE);
//            getMessage().addParameterValue("���޷�������");
//            ex.printLog();
//        }
        return success;
    }

    public boolean hasBarcode(String barcode) throws QueryException { //ִ��У��Barcode����
        boolean success = false;
        AmsHouseInfoModel amsHouseInfoModel = (AmsHouseInfoModel) sqlProducer;
        SQLModel sqlModel = amsHouseInfoModel.getVerifyBarcodeModel(barcode);

        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();

        return success;
    }

//      public boolean verifyProjectSegment1() throws QueryException {
//        boolean success = false;
//        sqlModel = projectModel.getVerifyProjectSegment1Model();
//        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
//        sq.setDTOClassName(ProjectDTO.class.getName());
//        sq.executeQuery();
//        ProjectDTO projectDTO = (ProjectDTO) sq.getFirstDTO();
//        if (!StrUtil.isEmpty(projectDTO)) {
//            success = true;
//        }
//        return success;
//    }
//
//       public boolean hasManager() throws QueryException {
//          boolean hasManager = false;
//          sqlModel = projectMaintenceModel.getHasPriviledgeModel();
//          SimpleQuery sq = new SimpleQuery(sqlModel, conn);
//          sq.executeQuery();
//          hasManager = sq.hasResult();
//          return  hasManager;
//      }
    /**
     * ���ܣ�����Barcode���ݺš�
     *
     * @return ORDER_NUMBER
     * @throws
     */
    public String getOrderNum() {
        String no = null;
        String companyCode = "";
        companyCode = sfUser.getCompanyCode();
        CallableStatement cst = null;
        String type = null; //����ʲô���͵ı��
        String sqlStr = "{CALL dbo.AONP_GET_ORDER_NO(?, ?, ?, ?)}";
        try {
            cst = conn.prepareCall(sqlStr);
            cst.setString(1, companyCode);
            cst.setString(2, type);
            cst.setInt(3, orderLength);
            cst.registerOutParameter(4, Types.VARCHAR);
            conn.setAutoCommit(true);            
            cst.execute();
            no = cst.getString(4);
        } catch (SQLException e) {
            Logger.logError(e);

        } finally {
            DBManager.closeDBStatement(cst);
        }
        return no;
    }
}
