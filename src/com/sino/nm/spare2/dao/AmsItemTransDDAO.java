package com.sino.nm.spare2.dao;


import com.sino.nm.spare2.dto.AmsItemTransDDTO;
import com.sino.nm.spare2.model.AmsItemTransDModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * <p>Title: AmsItemTransDDAO</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Herui
 * @version 1.0
 */


public class AmsItemTransDDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�����ҵ����ϸ��(AMS) AMS_ITEM_TRANS_D ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsItemTransDDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsItemTransDDAO(SfUserDTO userAccount, AmsItemTransDDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsItemTransDDTO dtoPara = (AmsItemTransDDTO) dtoParameter;
        super.sqlProducer = new AmsItemTransDModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ����뱸��ҵ����ϸ��(AMS)��AMS_ITEM_TRANS_D�����ݡ�
     */
    public void createData() throws DataHandleException {
        super.createData();
        getMessage().addParameterValue("����ҵ����ϸ��(AMS)");
    }

    /**
     * ���ܣ����±���ҵ����ϸ��(AMS)��AMS_ITEM_TRANS_D�����ݡ�
     */
    public void updateData() throws DataHandleException {
        super.updateData();
        getMessage().addParameterValue("����ҵ����ϸ��(AMS)");
    }

    /**
     * ���ܣ�ɾ������ҵ����ϸ��(AMS)��AMS_ITEM_TRANS_D�����ݡ�
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("����ҵ����ϸ��(AMS)");
    }

    /**
     * ������ϸ
     * @param lines DTOSet
     * @throws SQLException
     */
    public void writeDetails(DTOSet lines) throws SQLException {
        CallableStatement cStmt = null;
        String sqlStr = "{call dbo.AIT_SAVE_DETAIL(?,?,?,?,?,?,?,?,?)}";
        try {
            cStmt = conn.prepareCall(sqlStr);
            for (int i = 0; i < lines.getSize(); i++) {
                AmsItemTransDDTO dto = (AmsItemTransDDTO) lines.getDTO(i);
                //��������>0ʱ�Ŵ���
                if (dto.getQuantity() >0 && dto.getQuantity()>0 && dto.getQuantity() > 0) {
                    cStmt.setString(1, ((AmsItemTransDDTO) dtoParameter).getTransId());
                    cStmt.setString(2, ((AmsItemTransDDTO) dtoParameter).getLineId());
                    cStmt.setString(3, dto.getDetailId());
                    cStmt.setInt(4, dto.getOrganizationId());
                    cStmt.setString(5, dto.getObjectNo());
                    cStmt.setString(6, ((AmsItemTransDDTO) dtoParameter).getItemCode());
                    cStmt.setInt(7, dto.getQuantity());
                    cStmt.setInt(8, dto.getConfirmQuantity());
                    cStmt.setInt(9, dto.getCurOnhandQty());
                    cStmt.execute();
                }
            }
            prodMessage("UPDATE_DATA_SUCCESS");
            message.addParameterValue("������Ϣ");
        } finally {
            DBManager.closeDBStatement(cStmt);
        }
    }

}