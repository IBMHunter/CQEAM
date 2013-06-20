package com.sino.ams.others.cabel.dao;


import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.others.cabel.dto.AmsCabelInfoDTO;
import com.sino.ams.others.cabel.model.AmsCabelInfoModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsCabelInfoDAO</p>
 * <p>Description:�����Զ����ɷ������AmsCabelInfoDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author V-yuanshuai
 * @version 1.0
 */


public class AmsCabelInfoDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ��������豸��չ��Ϣ(EAM) AMS_CABEL_INFO ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsCabelInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsCabelInfoDAO(SfUserDTO userAccount, AmsCabelInfoDTO dtoParameter, Connection conn) {
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
        AmsCabelInfoDTO dtoPara = (AmsCabelInfoDTO) dtoParameter;
        super.sqlProducer = new AmsCabelInfoModel((SfUserDTO) userAccount, dtoPara);
    }


    /**
     * ���ܣ���� AMS_CABEL_INFO���� BARCODE��Ϣ�Ƿ����
     */
/*    public boolean checkBarcodeExist(String barcode) throws QueryException {
        boolean hasBeen;
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getCheckBarcodeExistModel(barcode);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        hasBeen = sq.hasResult();
        return hasBeen;
    }
    */
    //-----------------------------------------------------------------------------------------
    //--check
    public boolean checkInACI() throws QueryException {
        boolean hasBeen;
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getCheckInACIModel();
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        hasBeen = sq.hasResult();
        return hasBeen;
    }

    public boolean checkInESI() throws QueryException {
        boolean hasBeen;
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getCheckInESIModel();
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        hasBeen = sq.hasResult();
        return hasBeen;
    }

    public boolean checkInESD() throws QueryException {
        boolean hasBeen;
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getCheckInESDModel();
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        hasBeen = sq.hasResult();
        return hasBeen;
    }

    public boolean checkInEII() throws QueryException {
        boolean hasBeen;
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getCheckInEIIModel();
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        hasBeen = sq.hasResult();
        return hasBeen;
    }

    public boolean checkSameTypeInEII() throws QueryException {
        boolean hasBeen;
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getCheckSameTypeInEIIModel();
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        hasBeen = sq.hasResult();
        return hasBeen;
    }

    //-----------------------------------------------------------------------------------------
    //--create
    public void createInESD() throws DataHandleException {
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getCreateInESDModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    public void createInESI() throws DataHandleException {
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getCreateInESIModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    public void createInAASI() throws DataHandleException {
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getCreateInAASIModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    public void createInEII() throws DataHandleException {
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getCreateInEIIModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    public void updateInEII() throws DataHandleException {
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getUpdateInEIIModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    //-----------------------------------------------------------------------------------------
    //--getItemCode
    public String getItemCodeInESI() throws QueryException, ContainerException {
        AmsCabelInfoModel modelClass = (AmsCabelInfoModel) sqlProducer;
        SQLModel sqlModel = modelClass.getItemCodeInESIModel();
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        return rs.getRow(0).getStrValue(0);
    }

    //-----------------------------------------------------------------------------------------
    //--submit
    public void submit() throws DataHandleException {
        boolean hasError = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            //���&&�����豸������Ϣ
            if (!checkInESI()) {                       //�������豸������Ϣ
                createInESI();                         //�����豸������Ϣ
                String itemCode = getItemCodeInESI();
                AmsCabelInfoDTO amsCabelInfoDTO = (AmsCabelInfoDTO) dtoParameter;
                amsCabelInfoDTO.setItemCode(itemCode);
                setDTOParameter(amsCabelInfoDTO);      //����DTO��ItemCode������
                createInAASI();                        //���������豸��������
                createInESD();                         //�����豸���������Ϣ
            } else if (!checkInESD()) {               //�����豸������Ϣ,�����ڱ�����
                createInESD();                         //�����豸������Ϣ
            }
            //���&&�����豸��Ϣ
            if (!checkInACI()) {                          //������������Ϣ
                if (!checkInEII()) {                      //������������Ϣ
                    createInEII();                        //����������Ϣ
                } else if (!checkSameTypeInEII()) {      //���������豸���ͺŲ�һ��
                    updateInEII();                        //����������Ϣ
                }
                createData();                             //����������Ϣ
            } else {                                                     //���������Ϣ����
                updateData();                             //  ����������Ϣ
            }
            conn.commit();
            hasError = false;
        } catch (SQLException e) {
            Logger.logError(e);
        } catch (QueryException e) {
            Logger.logError(e);
        } catch (ContainerException e) {
            Logger.logError(e);
        } finally {
            try {
                if (hasError) {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            }
            catch (SQLException e) {
                Logger.logError(e);
            }
        }

    }
}