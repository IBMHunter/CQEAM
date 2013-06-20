package com.sino.ams.newasset.dao;


import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.dto.AmsAssetsPriviDTO;
import com.sino.ams.newasset.model.ContentPriviModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsAssetsChkLogDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsChkLogDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class ContentPriviDAO extends AMSBaseDAO {

    /**
     * ���ܣ��ʲ��̵��¼ AMS_ASSETS_CHK_LOG ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsChkLogDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public ContentPriviDAO(SfUserDTO userAccount,
                           AmsAssetsPriviDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsPriviDTO dtoPara = (AmsAssetsPriviDTO) dtoParameter;
        super.sqlProducer = new ContentPriviModel((SfUserDTO) userAccount,
                                                  dtoPara);
    }


    /**
     * ���ܣ�����רҵ�ʲ�Ȩ��
     * @param priviDTOs DTOSet
     * @return boolean
     */
    public boolean savePrivi(DTOSet priviDTOs) {
        boolean operateResult = false;
        boolean autoCommit = false;
        try {
            int dataCount = priviDTOs.getSize();
            AmsAssetsPriviDTO dto = null;
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            ContentPriviModel modelProducer = (ContentPriviModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getDataDeleteModel();
            DBOperator.updateRecord(sqlModel, conn);
            for (int i = 0; i < dataCount; i++) {
                dto = (AmsAssetsPriviDTO) priviDTOs.getDTO(i);
                setDTOParameter(dto);
                sqlModel = modelProducer.getDataCreateModel();
                DBOperator.updateRecord(sqlModel, conn);
            }
            operateResult = true;
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (SQLException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                    prodMessage(AssetsMessageKeys.ASS_PRIVI_SAVE_FAILURE);
                } else {
                    conn.commit();
                    prodMessage(AssetsMessageKeys.ASS_PRIVI_SAVE_SUCCESS);
                }
                message.setIsError(!operateResult);
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
                message.setIsError(true);
            }
        }
        return operateResult;
    }
}
