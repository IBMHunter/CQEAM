package com.sino.ams.system.user.dao;

import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.dto.AmsSynRightDTO;
import com.sino.ams.system.user.model.AmsSynRightModel;
import com.sino.ams.constant.CustMessageKey;
import com.sino.base.dto.DTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.exception.DataHandleException;
import com.sino.base.util.StrUtil;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: yuyao
 * Date: 2009-6-12
 * Time: 11:19:50
 * To change this template use File | Settings | File Templates.
 */
public class AmsSynRightDAO extends BaseDAO {

    /**
     * ���ܣ�SF_GROUP ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SfGroupDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsSynRightDAO(SfUserDTO userAccount, AmsSynRightDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsSynRightDTO dtoPara = (AmsSynRightDTO) dtoParameter;
        super.sqlProducer = new AmsSynRightModel((SfUserDTO) userAccount, dtoPara);
    }

    public boolean updateByUser(String organizationIds[]) {
        boolean operateResult = false;
        try {
            AmsSynRightDTO dtouser = (AmsSynRightDTO) dtoParameter;
            AmsSynRightModel model = (AmsSynRightModel) sqlProducer;
            SQLModel sqlModel = model.deletByUser();
            DBOperator.updateRecord(sqlModel, conn);
            for (int i = 0; i < organizationIds.length; i++) {
                AmsSynRightDTO dto = new AmsSynRightDTO();
                dto.setUserId(dtouser.getUserId());
                dto.setOrganizationId(StrUtil.strToInt(organizationIds[i]));
                SQLModel sm = model.insertByUser(dto);
                DBOperator.updateRecord(sm, conn);
            }
            operateResult = true;
            prodMessage(CustMessageKey.PRIVI_SAVE_SUCCESS);
        } catch (DataHandleException ex) {
            ex.printLog();
            prodMessage(CustMessageKey.RRIVI_SAVE_FAILURE);
        }
        return operateResult;
    }
}