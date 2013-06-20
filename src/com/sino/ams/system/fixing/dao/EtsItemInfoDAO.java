package com.sino.ams.system.fixing.dao;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.system.fixing.dto.EtsItemInfoDTO;
import com.sino.ams.system.fixing.model.EtsItemInfoModel;
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
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: EtsItemInfoDAO</p>
 * <p>Description:�����Զ����ɷ������EtsItemInfoDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author yuyao
 * @version 1.0
 */


public class EtsItemInfoDAO extends BaseDAO {

    private SfUserDTO sfUser = null;


    /**
     * ���ܣ���ǩ����Ϣ(EAM) ETS_ITEM_INFO ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EtsItemInfoDAO(SfUserDTO userAccount, EtsItemInfoDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsItemInfoDTO dtoPara = (EtsItemInfoDTO) dtoParameter;
        super.sqlProducer = new EtsItemInfoModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ������ǩ����Ϣ(EAM)��ETS_ITEM_INFO�����ݡ�
     */
    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("��ǩ����Ϣ(EAM)");
    }

    /**
     * ���ܣ����±�ǩ����Ϣ(EAM)��ETS_ITEM_INFO�����ݡ�
     */
    public void updateData() throws DataHandleException {
        super.updateData();
        getMessage().addParameterValue("��ǩ����Ϣ(EAM)");
    }

    /**
     * ���ܣ�ɾ����ǩ����Ϣ(EAM)��ETS_ITEM_INFO�����ݡ�
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("��ǩ����Ϣ(EAM)");
    }

    public String insertData() throws SQLException, DataHandleException, QueryException {
        String msg = "";
        try {
            conn.setAutoCommit(false);
            EtsItemInfoDTO dto = (EtsItemInfoDTO) dtoParameter;
            EtsItemInfoModel model = new EtsItemInfoModel(sfUser, dto);
            SimpleQuery sq = new SimpleQuery(model.selectItemInfo(), conn);  //���ITEM�Ļ�����Ϣ
            sq.executeQuery();

            if (sq.hasResult()) {
                DBOperator.updateRecord(model.updateModel(), conn);
            } else {
                SeqProducer seq = new SeqProducer(conn);
                String itemCode = StrUtil.nullToString(seq.getStrNextSeq("ETS_SYSTEM_ITEM"));
                dto.setItemCode(itemCode);
                DBOperator.updateRecord(model.insertIntoItem(), conn);
                SimpleQuery sq1 = new SimpleQuery(model.selectDis(itemCode), conn);
                sq1.executeQuery();
                if (!sq1.hasResult()) {
                    DBOperator.updateRecord(model.insertIntoDis(itemCode), conn);
                }
            }
            createData();
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            e.printStackTrace();
            throw e;
        } catch (DataHandleException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            Logger.logError(e);
            throw e;
        } catch (QueryException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            Logger.logError(e);
            throw e;
        }
        return msg;
    }

    public String simpleQueryData(String itemCategory) throws QueryException {
        String itemCategroyDesc = "";
        EtsItemInfoDTO etsItemInfoDTO = (EtsItemInfoDTO) dtoParameter;
        EtsItemInfoModel a = new EtsItemInfoModel(sfUser, etsItemInfoDTO);
        SQLModel sModel = a.getItemCategoryModel(itemCategory);
        SimpleQuery sQuery = new SimpleQuery(sModel, conn);
        sQuery.executeQuery();
        if (sQuery.hasResult()) {
            RowSet row = sQuery.getSearchResult();
            try {
                itemCategroyDesc = (String) row.getRow(0).getValue("ITEM_CATEGORY_DESC");
            } catch (ContainerException e) {
                e.printStackTrace();
                throw new QueryException();
            }
        }

        return itemCategroyDesc;
    }

    public String getCode() throws QueryException {
        String companyCode = "";
        EtsItemInfoDTO etsItemInfoDTO = (EtsItemInfoDTO) dtoParameter;
        EtsItemInfoModel model1 = new EtsItemInfoModel(sfUser, etsItemInfoDTO);
        SQLModel sModel = model1.getCode();
        SimpleQuery sQuery = new SimpleQuery(sModel, conn);
        sQuery.executeQuery();
        if (sQuery.hasResult()) {
            RowSet row = sQuery.getSearchResult();
            try {
                companyCode = (String) row.getRow(0).getValue("COMPANY_CODE");
            } catch (ContainerException e) {
                e.printStackTrace();
                throw new QueryException();
            }
        }
        return companyCode;
    }

    /**
     * ���ܣ�ʧЧѡ���豸
     * @param systemIds String[]
     */
    public void disableItem(String systemIds) {
        try {
            EtsItemInfoModel a = (EtsItemInfoModel) sqlProducer;
            SQLModel sModel = a.getDisableModel(systemIds);
            DBOperator.updateRecord(sModel, conn);
            prodMessage(CustMessageKey.DISABLE_SUCCESS);
            getMessage().addParameterValue("�豸");
        } catch (DataHandleException ex) {
            ex.printLog();
            prodMessage(CustMessageKey.DISABLE_FAILURE);
            getMessage().addParameterValue("�豸");
        }
    }

    /**
     * ���ܣ���Чѡ���豸
     * @param systemIds String[]
     */
    public void enableItem(String systemIds) {
        try {
            EtsItemInfoModel a = (EtsItemInfoModel) sqlProducer;
            SQLModel sModel = a.getEnableModel(systemIds);
            DBOperator.updateRecord(sModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("�豸");
        } catch (DataHandleException ex) {
            ex.printLog();
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("�豸");
        }
    }

    /**
     * ���ܣ�����Excel�ļ���
     * @return File
     * @throws DataTransException
     */
    public File exportFile() throws DataTransException {
        File file = null;
        try {
            EtsItemInfoDTO itemDTO = (EtsItemInfoDTO) dtoParameter;
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);

            String fileName = itemDTO.getItemCategoryDesc() + "ͳ�Ʊ�.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("BARCODE1", "��ǩ��");
            fieldMap.put("ITEM_QTY", "�豸����");
            fieldMap.put("DISABLE_DATE", "ʧЧ����");
            fieldMap.put("VENDOR_NAME", "��Ӧ��");
            fieldMap.put("ITEM_NAME", "�豸����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
            fieldMap.put("ITEM_CATE_GORY_DESC", "�豸���");
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
