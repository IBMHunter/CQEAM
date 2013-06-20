package com.sino.ams.match.amsMisLocMatch.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.match.amsMisLocMatch.dto.AmsMisLocMatchDTO;
import com.sino.ams.match.amsMisLocMatch.model.AmsMisLocMatchModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2007-11-21
 * Time: 19:44:03
 * To change this template use File | Settings | File Templates.
 */
public class AmsMisLocMatchDAO extends BaseDAO {
     private SfUserDTO sfUser = null;

    /**
     * ���ܣ����޷���(EAM) AMS_HOUSE_INFO ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsHouseInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsMisLocMatchDAO(SfUserDTO userAccount, AmsMisLocMatchDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsMisLocMatchDTO dtoPara = (AmsMisLocMatchDTO) dtoParameter;
        super.sqlProducer = new AmsMisLocMatchModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ��������޷���(EAM)��AMS_HOUSE_INFO�����ݡ�
     *
     * @return boolean
     */
    public void doMatch() throws DataHandleException {          //do_save����
//        boolean operateResult = false;
        boolean autoCommit = true;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsMisLocMatchDTO dtopara = (AmsMisLocMatchDTO) dtoParameter;
            AmsMisLocMatchModel AMLocMatchModel =new AmsMisLocMatchModel((SfUserDTO) userAccount, dtopara);
            DBOperator.updateRecord(AMLocMatchModel.matchETSMISLoc(),conn);

//            operateResult = true;
            conn.commit();
            hasError = false;
            getMessage().addParameterValue("�����ʲ�");
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } catch (SQLModelException e) {
            e.printStackTrace();
        } finally {
            if (hasError) {
                try {
                    conn.rollback();
                    conn.setAutoCommit(autoCommit);
                } catch (SQLException ex) {
                    Logger.logError(ex);
                    prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
                }
            }
        }
//        return operateResult;
    }


  /**
     * ���ܣ����н��ƥ��Ĳ�����
     * @return boolean
     */
    public void unMatch(String[] subCheck) throws DataHandleException {          //do_save����
//        boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsMisLocMatchDTO dtopara = (AmsMisLocMatchDTO) dtoParameter;
            for(int i=0; i<subCheck.length;i++){
               String[] loc= StrUtil.splitStr(subCheck[i],"@@@");
                String etsNO=loc[0];
				String misLocation=loc[1];
                dtopara.setWorkorderObjectNo(etsNO);
                dtopara.setAssetsLocation(misLocation);
               AmsMisLocMatchModel AMLocMatchModel =new AmsMisLocMatchModel((SfUserDTO) userAccount, dtopara);
               DBOperator.updateRecord(AMLocMatchModel.getDelMatchModel(),conn);
            }
//            operateResult = true;
            conn.commit();
            hasError = false;
            getMessage().addParameterValue("�����ʲ�");
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } finally {
            if (hasError) {
                try {
                    conn.rollback();
                    conn.setAutoCommit(autoCommit);
                } catch (SQLException ex) {
                    Logger.logError(ex);
                    prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
                }
            }
        }
//        return operateResult;
    }
    /**
     * ���ܣ�
     * @return boolean
     */
    public void updateData() throws DataHandleException {                     //�޸�����
        AmsMisLocMatchDTO dtopara = (AmsMisLocMatchDTO) dtoParameter;
        super.updateData();
        getMessage().addParameterValue("�ʲ�");
    }


    public void updateEIIData() throws DataHandleException {
        AmsMisLocMatchModel rentModel = (AmsMisLocMatchModel)sqlProducer;
        SQLModel sqlModel = rentModel.getupdataEIIModel();
        DBOperator.updateRecord(sqlModel,conn);
    }
}
