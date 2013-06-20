package com.sino.ams.system.comparison.dao;


import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.system.comparison.dto.EtsObjectCatGroupDTO;
import com.sino.ams.system.comparison.model.EtsObjectCatGroupModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsObjectCatGroupDAO</p>
 * <p>Description:�����Զ����ɷ������EtsObjectCatGroupDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsObjectCatGroupDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ����-רҵ�ص������ձ�(EAM) ETS_OBJECT_CAT_GROUP ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectCatGroupDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsObjectCatGroupDAO(SfUserDTO userAccount, EtsObjectCatGroupDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EtsObjectCatGroupDTO dtoPara = (EtsObjectCatGroupDTO)dtoParameter;
		super.sqlProducer = new EtsObjectCatGroupModel((SfUserDTO)userAccount, dtoPara);
	}



    /**
	 * ���ܣ������ʲ��ص��(EAM)��ETS_OBJECT�����ݡ�
	 * @return boolean
	 */
    public String updateData2(String groupId, String[] codes) throws DataHandleException {
        String operateResult = "";
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            EtsObjectCatGroupDTO tmpDTO = (EtsObjectCatGroupDTO) getDTOParameter();                   // ��ȡ���ε�����
            EtsObjectCatGroupModel model = new EtsObjectCatGroupModel((SfUserDTO) userAccount, tmpDTO);
            DBOperator.updateRecord(model.getDataDeleteModel(groupId), conn);      //ɾ������
            for (int i = 0; i < codes.length; i++) {
                DBOperator.updateRecord(model.getDataCreateModel(groupId, codes[i]), conn);    //���Ӳ���
            }
//            createData(objectCategory, codes);
            operateResult = "Y";
            conn.commit();
            hasError = false;
            getMessage().addParameterValue("רҵ��");
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } catch (DataHandleException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.COMMON_ERROR);
        } catch (SQLModelException e) {
            e.printStackTrace();
        } finally {
            try {
                if (hasError) {
                    operateResult ="N";
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(MsgKeyConstant.SQL_ERROR);
            }
        }
        return operateResult;
    }



}