package com.sino.ams.spare.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;

import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowDTO;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.spare.dto.AmsItemAllocateDDTO;
import com.sino.ams.spare.dto.AmsItemTransHDTO;
import com.sino.ams.spare.model.BjdbModel;
import com.sino.ams.spare.pda.SpareTransaction;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.util.BillUtil;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ���
 * @version 0.1
 *          Date: 2007-10-26
 */
public class BjdbApproveDAO extends BaseDAO {
    private SfUserDTO sfUser = null;
    private AmsItemTransHDTO amsItemTransH = null;

    /**
     * ���ܣ����캯����
     * @param userAccount  UserAccountDTO �û���Ϣ
     * @param dtoParameter DTO ���������ݿ⽻��ʱ��Ҫ�Ĳ�����
     * @param conn         Connection ���ݿ�����
     */
    public BjdbApproveDAO(SfUserDTO userAccount, AmsItemTransHDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        this.sfUser = userAccount;
        this.amsItemTransH = dtoParameter;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        super.sqlProducer = new BjdbModel((SfUserDTO) userAccount, (AmsItemTransHDTO) dtoParameter);
    }


    public RowSet getLines() throws QueryException, SQLModelException {
        SQLModel sqlModel = sqlProducer.getDataByForeignKeyModel(amsItemTransH.getTransId());
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }

    public boolean approveOrder(DTOSet details, HttpServletRequest req) throws SQLException {
        boolean success = false;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String sectionRight = req.getParameter("sectionRight");
            String bill_type = StrUtil.nullToString(req.getAttribute("bill_type"));
            // ����
            if ("OUT".equals(sectionRight) || bill_type.equals(SpareTransaction.BILL_TYPE_BJDB_OUT)) {
                saveDetails(details);
                //д������
//                writeReservQty(details);
                //���������ڲֿ��Ϊ��;��
//                updateAddressIdOnWay(details);
                //����2����,�������������
                processSpareInfo(details);
            }/*else if(sectionRight.equals("RECEIVE")){   //���� ��Ϊһ��������Act
                //,�����������ڲֿ��Ϊ��������

                updateAddressIdOnNormal();
                updateStatus(DictConstant.COMPLETED); //�ݲ��ı䵥��״̬,���պ�Ÿı�״̬
            }*/

            //���̴���
            FlowDTO fDto = FlowAction.getDTOFromReq(req);
            fDto.setActivity("9");
            fDto.setApproveContent(FlowConstant.APPROVE_CONTENT_AGREE);
            FlowAction fb = new FlowAction(conn, fDto);
            if (fb.isFlowToEnd()) {
                //���һ���ڵ�,���޸ĵ���״̬
                BillUtil.updateStatus("AMS_ITEM_ALLOCATE_H", "TRANS_ID", amsItemTransH.getTransId(), "TRANS_STATUS", DictConstant.COMPLETED, conn);
            }
            fb.flow();
            conn.commit();
            success = true;
        } catch (SQLException e) {
            prodMessage("SQL_ERROR");
            Logger.logError(e);
            conn.rollback();
        } catch (DataHandleException e) {
            prodMessage("SQL_ERROR");
            Logger.logError(e);
            conn.rollback();
        } catch (QueryException e) {
            prodMessage("SQL_ERROR");
            Logger.logError(e);
            conn.rollback();
        } catch (ContainerException e) {
            prodMessage("SQL_ERROR");
            Logger.logError(e);
            conn.rollback();
        } finally {
            conn.setAutoCommit(autoCommit);
        }
        return success;
    }

    /**
     * ����������Ϣ
     * @param details DTOSet
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    public void saveDetails(DTOSet details) throws DataHandleException {
        if (details != null && !details.isEmpty()) {
            AmsItemAllocateDDAO allocateDDAO = new AmsItemAllocateDDAO(sfUser, null, conn);
            for (int i = 0; i < details.getSize(); i++) {
                AmsItemAllocateDDTO data = (AmsItemAllocateDDTO) details.getDTO(i);
                data.setTransId(amsItemTransH.getTransId());
                allocateDDAO.setDTOParameter(data);
                allocateDDAO.createData();
            }
        }
    }

    /**
     * �����������ڲֿ�Ϊ��;��
     * @param dtoSet DTOSet
     * @throws SQLException
     */
    private void updateAddressIdOnWay(DTOSet dtoSet) throws SQLException {
        if (dtoSet != null && !dtoSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call AMS_ITEM_TRANS.ITEM_ON_THE_WAY(?,?,?)}";
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    AmsItemAllocateDDTO lineData = (AmsItemAllocateDDTO) dtoSet.getDTO(i);
                    cStmt.setString(1, lineData.getBarcode());
                    cStmt.setInt(2, this.amsItemTransH.getToOrganizationId());
                    cStmt.setInt(3, this.sfUser.getUserId());
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }

    /**
     * �˻�
     * @param flowDTO FlowDTO
     * @throws SQLException
     */
    public void reject(FlowDTO flowDTO) throws SQLException {
        try {
            conn.setAutoCommit(false);
            //ҵ����
            updateStatus(DictConstant.REJECTED);
            //���̴���
          /*  if(flowDTO.getApproveContent().equals("")){
                    flowDTO.setApproveContent("��ͬ��");
            }*/
            FlowAction fb = new FlowAction(conn, flowDTO);
            fb.reject2Begin();
//            fb.reject();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } catch (DataHandleException e) {
            conn.rollback();
            throw new SQLException(e.getMessage());
        } finally {
            conn.setAutoCommit(true);
        }
    }
     /**
     * ���µ���״̬
     * @param status ״̬
     * @throws DataHandleException
     */
    public void updateStatus(String status) throws DataHandleException {
        String sqlStr = " UPDATE AMS_ITEM_ALLOCATE_H\n" +
                " SET TRANS_STATUS = ?" +
                " WHERE TRANS_ID = ?";
        List list = new ArrayList();
        list.add(status);
        list.add(amsItemTransH.getTransId());
        SQLModel sqlModel = new SQLModel();
        sqlModel.setArgs(list);
        sqlModel.setSqlStr(sqlStr);
        DBOperator.updateRecord(sqlModel, conn);
    }
    /**
     * ����2:����,�������������
     * @param dtoSet DTOSet
     * @throws SQLException
     */
    private void processSpareInfo(DTOSet dtoSet) throws SQLException {
        if (dtoSet != null && !dtoSet.isEmpty()) {
            CallableStatement cStmt = null;
            String sqlStr = "{call AMS_INV_TRANS.DB_OUT_INV(?,?,?,?,?,?,?)}";
            try {
                cStmt = conn.prepareCall(sqlStr);
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    AmsItemAllocateDDTO lineData = (AmsItemAllocateDDTO) dtoSet.getDTO(i);
                    cStmt.setString(1, lineData.getBarcode());
                    cStmt.setString(2, lineData.getItemCode());
                    cStmt.setString(3, lineData.getObjectNo());
                    cStmt.setInt(4, this.sfUser.getOrganizationId());
                    cStmt.setInt(5, lineData.getAllocateQty());
                    cStmt.setInt(6, this.sfUser.getUserId());
                    cStmt.setString(7, this.amsItemTransH.getTransId());
                    cStmt.execute();
                }
            } finally {
                DBManager.closeDBStatement(cStmt);
            }
        }
    }
}
