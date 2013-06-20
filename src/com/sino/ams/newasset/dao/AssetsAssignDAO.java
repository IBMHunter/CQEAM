package com.sino.ams.newasset.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.model.AssetsAssignModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class AssetsAssignDAO extends AMSBaseDAO {


    public AssetsAssignDAO(SfUserDTO userAccount,
                           AmsAssetsAddressVDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
     * @param userAccount BaseUserDTO
     * @param dtoParameter DTO
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
        sqlProducer = new AssetsAssignModel((SfUserDTO) userAccount, dto);
    }

    /**
     * ���ܣ������ʲ��ķ��䡣
     * @param assignDatas DTOSet
     * @return boolean
     */
    public boolean assignAssets(DTOSet assignDatas) {
        boolean operateResult = false;
        boolean autoCommit = true;
        AmsAssetsAddressVDTO dto = null;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            int assetsCount = assignDatas.getSize();
            AssetsAssignModel modelProducer = (AssetsAssignModel) sqlProducer;
            SQLModel sqlModel = null;
            for (int i = 0; i < assetsCount; i++) {
                dto = (AmsAssetsAddressVDTO) assignDatas.getDTO(i);
                modelProducer.setDTOParameter(dto);
                sqlModel = modelProducer.getAssetsAssignModel();
                DBOperator.updateRecord(sqlModel, conn); //�����ʲ�
            }
            operateResult = true;
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (SQLModelException ex) {
            ex.printLog();
        } catch (SQLException ex) {
            Logger.logError(ex);
        } finally {
            String assProp = dto.getAssProp();
            String paraValue = "";
            if (assProp.equals(AssetsWebAttributes.ASSIGN_COST_CENTER)) {
                paraValue = "����";
            } else if (assProp.equals(AssetsWebAttributes.
                                      ASSIGN_RESPONSIBLE_USER)) {
                paraValue = "������";
            } else if (assProp.equals(AssetsWebAttributes.ASSIGN_MAINTAIN_USER)) {
                paraValue = "ʹ����";
            }
            try {
                if (!operateResult) {
                    conn.rollback();
                    prodMessage(AssetsMessageKeys.ASN_ASSETS_FAILURE);
                } else {
                    conn.commit();
                    prodMessage(AssetsMessageKeys.ASN_ASSETS_SUCCESS);
                }
                message.addParameterValue(paraValue);
                message.setIsError(!operateResult);
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }
}
