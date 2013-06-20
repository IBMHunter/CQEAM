package com.sino.rds.check.service;

import com.sino.base.log.Logger;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.rds.appbase.service.RDSBaseService;
import com.sino.rds.check.dao.DataModelCheckDAO;
import com.sino.rds.check.dao.DataSourceCheckDAO;
import com.sino.rds.check.dao.ReportCheckDAO;
import com.sino.rds.share.constant.RDSDictionaryList;
import com.sino.rds.share.form.DBConnectionFrm;
import com.sino.rds.share.form.DataModelFrm;
import com.sino.rds.share.form.ReportDefineFrm;

import java.sql.Connection;
import java.sql.SQLException;

public class ReportRuntimeCheckService extends RDSBaseService {

    public ReportRuntimeCheckService(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    public void checkRuntimeReport() {
        try {
            ReportDefineFrm frm = (ReportDefineFrm) dtoParameter;
            ReportCheckDAO reportCheckDAO = new ReportCheckDAO(userAccount, frm, conn);
            ReportDefineFrm report = reportCheckDAO.searchDTOByPrimaryKey();
            if (report != null) {//�ҵ�����
                String checkedSttus = report.getCheckedSttus();
                if (!checkedSttus.equals(RDSDictionaryList.CHECK_STATUS_SUCCESS)) {
                    String modelId = report.getModelId();
                    if (modelId.length() == 0) {//ģ��IDΪ��

                    } else {//ģ��ID��Ϊ�գ�����ģ��
                        checkDataModel(report);
                    }
                }
            } else {//������ָ������ı���

            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
    }

    private void checkDataModel(ReportDefineFrm report) {
        try {
            DataModelFrm modelFrm = new DataModelFrm();
            modelFrm.setModelId(report.getModelId());
            DataModelCheckDAO modelCheckDAO = new DataModelCheckDAO(userAccount, modelFrm, conn);
            DataModelFrm dataModel = modelCheckDAO.searchDTOByPrimaryKey();
            if (dataModel != null) { //�ҵ�ģ��
                String connectionId = dataModel.getConnectionId();
                if (connectionId.length() == 0) {//����ԴIDΪ��

                } else {//����ԴID��Ϊ�գ���������Դ

                }
            } else { //������ָ��ID��ģ��

            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
    }

    private void checkDataSource(DataModelFrm dataModel) {
        try {
            DBConnectionFrm connFrm = new DBConnectionFrm();
            connFrm.setConnectionId(dataModel.getConnectionId());
            DataSourceCheckDAO dsCheckDAO = new DataSourceCheckDAO(userAccount, connFrm, conn);
            DBConnectionFrm dataSource = dsCheckDAO.searchDTOByPrimaryKey();
            if (dataSource != null) {//�ҵ�����Դ
                checkNetCommunication(dataSource);
            } else {//������ָ��ID������Դ

            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
    }

    private void checkNetCommunication(DBConnectionFrm dataSource) {
        Connection ds = null;
        try {
            ds = dataSource.getDBConnection();
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            if (ds != null) {
                try {
                    ds.close();
                } catch (SQLException ex) {
                    Logger.logError("�رյ�����Դ������ʧ��");
                }
            }
        }
    }

    private void checkReport(){

    }
}