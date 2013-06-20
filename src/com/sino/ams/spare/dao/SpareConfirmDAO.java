package com.sino.ams.spare.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.spare.dto.AmsSpareCategoryDTO;
import com.sino.ams.spare.model.SpareConfirmModel;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.log.Logger;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.exception.*;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-12-02
 * Time: 00:00:00
 * To change this template use File | Settings | File Templates.
 */
public class SpareConfirmDAO extends AMSBaseDAO {

	public SpareConfirmDAO(SfUserDTO userAccount, AmsSpareCategoryDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		initSQLProducer(userAccount, dtoParameter);
	}

	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsSpareCategoryDTO dtoPara = (AmsSpareCategoryDTO) dtoParameter;
		super.sqlProducer = new SpareConfirmModel((SfUserDTO) userAccount, dtoPara);
	}

    public String getReplaceCategory(String fromBarcode, String toBarcode) {
        String ret = "Y";
        boolean hasError = true;
		boolean autoCommit = false;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
            //1���ҵ���ͬ�ı����豸����(AMS_SPARE_INFO);
            DTOSet dtoSet =getSameCategory(fromBarcode, toBarcode);
            //2���ҵ���ͬ�ı����豸���ཫTO�������ӵ�FROM����(AMS_SPARE_INFO);
            updateCategoryQuantity(fromBarcode, toBarcode);
            //3���Ҳ�����ͬ���豸������TO�滻��(AMS_SPARE_INFO);
            replaceCategory(fromBarcode, toBarcode);
            //4��ɾ����ͬ���豸����(FROM);(AMS_SPARE_INFO)
            if (dtoSet != null && dtoSet.getSize() > 0) {
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    AmsSpareCategoryDTO categoryDTO = (AmsSpareCategoryDTO) dtoSet.getDTO(i);
                    deleteSameCategory(categoryDTO.getSpareId());
                }
            }
            //5��ɾ���滻ԴĿ���豸������Ϣ(AMS_SPARE_CATEGORY);
            deleteFromCategory(fromBarcode);
            conn.commit();
			hasError = false;
		} catch (SQLException e) {
			Logger.logError(e);
		} catch (DataHandleException e) {
			e.printStackTrace();
		} catch (QueryException e) {
			Logger.logError(e);
		} catch (SQLModelException e) {
			Logger.logError(e);
		} finally {
			try {
				if (hasError) {
					ret = "N";
					conn.rollback();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException e) {
				Logger.logError(e);
			}
		}
		return ret;
    }
    
    /**
     * �ҵ���barcode��Ӧ�ı����豸����
     * @param fromBarcode Դbarcode  �滻barcode
     */
    public DTOSet getSameCategory(String fromBarcode, String toBarcode) throws DataHandleException, QueryException, SQLModelException {
		SpareConfirmModel modelProducer = (SpareConfirmModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(modelProducer.getSameCategoryModel(fromBarcode, toBarcode), conn);
        sq.setDTOClassName(AmsSpareCategoryDTO.class.getName());
        sq.executeQuery();
        return sq.getDTOSet();
	}

    /**
     * �ҵ���ͬ�ı����豸���ཫTO�������ӵ�FROM����(AMS_SPARE_INFO);
     * @param fromBarcode
     * @param toBarcode
     */
    public void updateCategoryQuantity(String fromBarcode, String toBarcode) throws DataHandleException {
		SpareConfirmModel modelProducer = (SpareConfirmModel) sqlProducer;
		SQLModel sqlModel = modelProducer.updateCategoryQuantity(fromBarcode, toBarcode);
		DBOperator.updateRecord(sqlModel, conn);
	}
    
    /**
     * �Ҳ�����ͬ���豸������TO�滻��(AMS_SPARE_INFO);
     * @param fromBarcode
     * @param toBarcode
     */
    public void replaceCategory(String fromBarcode, String toBarcode) throws DataHandleException {
		SpareConfirmModel modelProducer = (SpareConfirmModel) sqlProducer;
		SQLModel sqlModel = modelProducer.replaceCategory(fromBarcode, toBarcode);
		DBOperator.updateRecord(sqlModel, conn);
	}
    
    /**
     * ɾ����ͬ���豸����(��Դ)
     * @param spareId  AMS_SPARE_INFO(������Ϣ��)
     * @throws DataHandleException
     */
    public void deleteSameCategory(String spareId) throws DataHandleException {
		SpareConfirmModel modelProducer = (SpareConfirmModel) sqlProducer;
		SQLModel sqlModel = modelProducer.deleteSameCategory(spareId);
		DBOperator.updateRecord(sqlModel, conn);
	}
    
    /**
     * �ĵ����滻���豸����״̬
     * @param barcode   ENABLED='N'
     * @throws DataHandleException
     */
    public void deleteFromCategory(String barcode) throws DataHandleException {
		SpareConfirmModel modelProducer = (SpareConfirmModel) sqlProducer;
		SQLModel sqlModel = modelProducer.deleteFromCategory(barcode);
		DBOperator.updateRecord(sqlModel, conn);
	}
}
