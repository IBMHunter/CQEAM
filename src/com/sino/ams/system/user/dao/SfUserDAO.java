package com.sino.ams.system.user.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.dto.SfUserRightDTO;
import com.sino.ams.system.user.model.SfUserModel;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.util.StrUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����ʤ��Ȩ����Copyright (c) 2003~2007��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Copyright: ������Ȩ����˼ŵ����Ϣ�������޹�˾��һ����Χ��ʹ��</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 0.1
 */


public class SfUserDAO extends BaseDAO {
    private SfUserDTO userAccount = null;

    public SfUserDAO(BaseUserDTO userAccount, SfUserDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO
     * @param dtoParameter DTO
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SfUserDTO sfUser = (SfUserDTO) dtoParameter;
        super.sqlProducer = new SfUserModel(userAccount, sfUser);
    }

    private void prodNextUserId() throws SQLException {

    }

    /**
     * ����û���¼���Ƿ����
     *
     * @param sfUser
     * @return
     * @throws QueryException
     */
    public boolean checkSfUser(SfUserDTO sfUser) throws QueryException {
        boolean hasRecord = false;
        SQLModel sqlModel = getCheckUserModel(sfUser);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasRecord = true;
        }

        return hasRecord;
    }

    public boolean saveData(SfUserDTO sfUser, DTOSet dtoSet) throws DataHandleException {
        boolean operateResult = true;
        try {
            boolean autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            boolean isNew = StrUtil.isEmpty(sfUser.getUserId());
            if (isNew) {
                SeqProducer seqProducer = new SeqProducer(conn);
                sfUser.setUserId(seqProducer.getStrNextSeq("SF_USER_S"));
                createData();
            } else {
                updateData();
            }
            SfUserRightDTO userRightDTO = new SfUserRightDTO();
            userRightDTO.setUserId(sfUser.getUserId());

            SfUserRightDAO sfUserRightDAO = new SfUserRightDAO(userAccount, userRightDTO, conn);
            sfUserRightDAO.deleteData();
            if (dtoSet != null && dtoSet.getSize() > 0) {
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    userRightDTO = (SfUserRightDTO) dtoSet.getDTO(i);
                    if (isNew) {
                        userRightDTO.setUserId(sfUser.getUserId());
                    }
                    sfUserRightDAO.setDTOParameter(userRightDTO);
                    sfUserRightDAO.createData();
                }
            }
            conn.commit();
            conn.setAutoCommit(autoCommit);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operateResult;
    }

    public RowSet getGroupOfOu(String organizationId) throws QueryException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT SG.GROUP_ID, SG.GROUP_NAME\n" +
                "  FROM SF_GROUP SG\n" +
                " WHERE SG.ORGANIZATION_ID = ?\n" +
                "   AND SG.ENABLED = 'Y'";
        sqlArgs.add(organizationId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        return simpleQuery.getSearchResult();
    }

    /**
     * ����û���¼���Ƿ��ظ�
     *
     * @param sfUser SfUserDTO
     * @return SQLModel
     */

    private SQLModel getCheckUserModel(SfUserDTO sfUser) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT * FROM SF_USER SU WHERE UPPER(SU.LOGIN_NAME) = UPPER(?)";
        sqlArgs.add(sfUser.getLoginName().toUpperCase());

        sqlModel.setArgs(sqlArgs);
        sqlModel.setSqlStr(sqlStr);

        return sqlModel;
    }


    public Object getMuxData() throws QueryException {
        return super.getMuxData();
    }
}
