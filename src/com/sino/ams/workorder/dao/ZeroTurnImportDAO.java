package com.sino.ams.workorder.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.object.model.ImportObjectModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.ZeroImportDTO;
import com.sino.ams.workorder.model.ZeroTurnImportModel;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

public class ZeroTurnImportDAO  extends AMSBaseDAO {
	private SfUserDTO sfUser = null;
	private ZeroTurnImportModel modelProducer = null;
    private SimpleQuery simpleQuery = null;
    public ZeroTurnImportDAO(SfUserDTO userAccount, ZeroImportDTO dtoParameter,
			Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser =  userAccount;
	}
	@Override
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		ZeroImportDTO dtoPara = (ZeroImportDTO) dtoParameter;
        super.sqlProducer = new ZeroTurnImportModel( userAccount, dtoPara);
		
	}

	/**
     * ���ܣ���������
     *
     * @param dtoSet ��Excel������������
     * @return true��ʾ����ɹ���false��ʾ����ʧ��
     */
    public boolean importObject(DTOSet dtoSet) {
        boolean dataValid = true;
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            modelProducer = (ZeroTurnImportModel) sqlProducer;
            simpleQuery = new SimpleQuery(null, conn);
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            deleteTmpTableData();
            import2TmpTable(dtoSet);
//            if (isAllObjectValid(dtoSet)) {
//                submitOrderDtl(dtoSet);
//            } else {
//                dataValid = false;
//            }
            operateResult = true;
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
        return dataValid;
    }
    /**
     * ���ܣ�ɾ���ӿڱ�����ݡ�
     *
     * @throws DataHandleException ɾ���ӿڱ�����ݳ���ʱ�׳����ݴ����쳣
     * @throws SQLModelException 
     */
    private void deleteTmpTableData() throws DataHandleException, SQLModelException {
        SQLModel sqlModel = modelProducer.deleteImportModel();
        DBOperator.updateRecord(sqlModel, conn);
    }
    /**
     * ���ܣ���Excel�����������ݵ��뵽��ʱ��AMS_OBJECT_IMPORT
     *
     * @param dtoSet DTOSet Excel������������
     * @throws DataHandleException ��Excel�����������ݵ��뵽��ʱ��AMS_OBJECT_IMPORT����ʱ�׳����쳣
     */
    private void import2TmpTable(DTOSet dtoSet) throws DataHandleException {
       
            if (dtoSet != null && dtoSet.getSize() > 0) {
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    ZeroImportDTO zeroDTO = (ZeroImportDTO) dtoSet.getDTO(i);
                    modelProducer.setDTOParameter(zeroDTO);
                    SQLModel sqlModel;
					try {
						sqlModel = modelProducer.insertImportModel(zeroDTO);
						 DBOperator.updateRecord(sqlModel, conn);
					} catch (SQLModelException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                   
                }
            }
    }
    public int validata() {
    	int flag=0;
    	CallableStatement cs = null;
	          String callStr = "{CALL dbo.ZERO_TURN_REIMBURSE(?)}";
	          try {
				cs = conn.prepareCall(callStr);
				cs.registerOutParameter(1, java.sql.Types.INTEGER);
				cs.execute();
				flag=cs.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBManager.closeDBStatement(cs);
			}
	          
		return flag;
	}
    
}
