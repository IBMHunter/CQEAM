package com.sino.ams.match.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.match.model.ManualCtMatchModel;
import com.sino.base.data.Row;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ��ʿ��
 * @version 0.1
 *          Date: 2008-12-08
 */
public class ManualCtMatchDAO extends AMSBaseDAO {
	
	public ManualCtMatchDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	private int matchedItemCount = 0;   //����ƥ��ɹ�������

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        super.sqlProducer = new ManualCtMatchModel(userAccount, dtoParameter);
    }


    public boolean saveItemMatch(String[] systemIds, String assetId) throws SQLException {
        boolean success = false;
        boolean autoCommit = false;
        CallableStatement cStmt = null;
        String sqlStr = "{call ETS_ITEM_CT_MATCH_PKG.SAVE_ITEM_CT_MATCH(?,?,?,?,?)}";
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            cStmt = conn.prepareCall(sqlStr);
            for (int i = 0; i < systemIds.length; i++) {
                cStmt.setString(1, systemIds[i].trim());
                cStmt.setString(2, assetId.trim());
                cStmt.setString(3, "".trim());
                cStmt.setString(4, "9".trim());
                cStmt.setString(5, StrUtil.nullToString(userAccount.getUserId()));
                cStmt.execute();
                conn.commit();
                success = true;
            }
        } catch (SQLException e) {
            Logger.logError(e);
            conn.rollback();
            prodMessage("SAVE_FAILURE");
        } finally {
            DBManager.closeDBStatement(cStmt);
            conn.setAutoCommit(autoCommit);
        }
        return success;
    }

    /**
     * ����ƥ��
     * @param systemid �豸ID
     * @param assetId  �ʲ�ID
     * @param type     ƥ�䷽ʽ
     * @return success
     * @throws SQLException
     */
    public synchronized boolean matchByType(String systemid, String assetId, int type) throws SQLException {
        boolean success = false;
        String sqlStr = "";
        switch (type) {
            case 1:
                sqlStr = "{call ETS_ITEM_CT_MATCH_PKG.MATCH_BY_LOCATION(?,?,?,?,?,?)}";
                break;
            case 2:
                sqlStr = "{call ETS_ITEM_CT_MATCH_PKG.MATCH_BY_COUNTY(?,?,?,?,?,?)}";
                break;
            case 3:
                sqlStr = "{call ETS_ITEM_CT_MATCH_PKG.MATCH_BY_CITY(?,?,?,?,?,?)}";
                break;
            default:
                Logger.logError("Manual match type Error !");
        }
        if (!sqlStr.equals("")) {
            boolean autoCommit = false;
            CallableStatement cStmt = null;
            try {
                autoCommit = conn.getAutoCommit();
                conn.setAutoCommit(false);
                cStmt = conn.prepareCall(sqlStr);
                cStmt.setString(1, systemid);
                cStmt.setString(2, assetId);
                cStmt.setString(3, null);        //BATCH_ID  ����������Զ�����һ�����к�
                cStmt.setInt(4, userAccount.getUserId());
                cStmt.setInt(5, userAccount.getOrganizationId());
                cStmt.registerOutParameter(6, Types.NUMERIC);
                cStmt.execute();
                this.matchedItemCount = cStmt.getInt(6);
                conn.commit();
                success = true;
            } catch (SQLException e) {
                Logger.logError(e);
                conn.rollback();
                prodMessage("SAVE_FAILURE");
            } finally {
                DBManager.closeDBStatement(cStmt);
                conn.setAutoCommit(autoCommit);
            }
        }
        return success;
    }

    public int getMatchedItemCount() {
        return matchedItemCount;
    }

    /**
     * ���ĳ������ƥ���??
     * @param batchId ����
     * @return success
     * @throws SQLException
     */
    public int unMatchBatch(String batchId) throws SQLException {
        int count = 0;
        boolean autoCommit = false;
        CallableStatement cStmt = null;
        String sqlStr = "{call ETS_ITEM_CT_MATCH_PKG.UNMATCH_BATCH(?,?,?)}";
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            cStmt = conn.prepareCall(sqlStr);
            cStmt.setString(1, batchId);
            cStmt.setInt(2, userAccount.getUserId());
            cStmt.registerOutParameter(3, Types.NUMERIC);
            cStmt.execute();
            count = cStmt.getInt(3);   //�����ʾ���ƥ�������
            conn.commit();
        } catch (SQLException e) {
            Logger.logError(e);
            conn.rollback();
            prodMessage("SAVE_FAILURE");
            count = -1;
        } finally {
            DBManager.closeDBStatement(cStmt);
            conn.setAutoCommit(autoCommit);
        }
        return count;
    }

    /**
     * ��������ƥ����ʷ��¼�鿴ҳ���е�����ƥ��
     * @param batchId   ����
     * @param matchType ƥ�䷽ʽ
     * @return success
     * @throws SQLException
     */
    public synchronized boolean batchMatch(String batchId, String matchType) throws SQLException {
        boolean success = false;
        String systemId = "";
        String assetId = "";
        //����batchId�ҵ�����һ���뱾�μ�¼��ƥ���systemId��assetId
        String sqlStr = "SELECT EIM.SYSTEMID, EIM.ASSET_ID\n" +
                "  FROM ETS_FA_MATCH_LOG EFML, ETS_ITEM_MATCH EIM\n" +
                " WHERE EFML.ID = EIM.BATCHID\n" +
                "   AND EIM.BATCHID =?" +
                "   AND ROWNUM = 1";
        SQLModel sqlModel = new SQLModel();
        List list = new ArrayList();
        list.add(batchId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(list);

        try {
            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            Row row = sq.getFirstRow();
            systemId = String.valueOf(row.getValue("SYSTEMID"));
            assetId = String.valueOf(row.getValue("ASSET_ID"));

            if (matchType.equals("matchByLocation")) {
                success = matchByType(systemId, assetId, 0);
            } else if (matchType.equals("matchByCounty")) {
                success = matchByType(systemId, assetId, 1);
            } else if (matchType.equals("matchByCity")) {
                success = matchByType(systemId, assetId, 2);
            }
        } catch (QueryException e) {
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (ContainerException e) {
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        }
        return success;
    }
}
