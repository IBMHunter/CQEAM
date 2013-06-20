package com.sino.ams.system.project.dao;


import java.sql.Connection;

import com.sino.ams.system.project.dto.EtsPaProjectsAllDTO;
import com.sino.ams.system.project.model.EtsPaProjectsAllModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.DBActionConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.util.DataUniqueChecker;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.ValidateException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsPaProjectsAllDAO</p>
 * <p>Description:�����Զ����ɷ������EtsPaProjectsAllDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsPaProjectsAllDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ���Ŀά����(EAM) ETS_PA_PROJECTS_ALL ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsPaProjectsAllDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EtsPaProjectsAllDAO(SfUserDTO userAccount, EtsPaProjectsAllDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsPaProjectsAllDTO dtoPara = (EtsPaProjectsAllDTO) dtoParameter;
        super.sqlProducer = new EtsPaProjectsAllModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ�������Ŀά����(EAM)��ETS_PA_PROJECTS_ALL�����ݡ�
     * @return boolean
     */
    public void createData() throws DataHandleException {
        boolean operateResult = false;
//        try {
//            String tableName = "ETS_PA_PROJECTS_ALL";
//            DataUniqueChecker datChecker = new DataUniqueChecker(tableName, dtoParameter, conn);
//            datChecker.setDBAction(DBActionConstant.INSERT);
//            datChecker.setServletConfig(servletConfig);
//            boolean isValid = datChecker.isDataValid();
//        	  boolean isValid = false;
//            if (!isValid) {
//                prodMessage(MsgKeyConstant.UNIQUE_ERROR);
//                getMessage().addParameterValue(datChecker.getInValidData());
//            } else {
                super.createData();
                getMessage().addParameterValue("��Ŀ");
//            }
//        } catch (ValidateException ex) {
//            ex.printLog();
//            prodMessage(MsgKeyConstant.COMMON_ERROR);
//        }
    }

    /**
     * ���ܣ�������Ŀά����(EAM)��ETS_PA_PROJECTS_ALL�����ݡ�
     * @return boolean
     */
    public void updateData() throws DataHandleException {
        boolean operateResult = false;
        try {
            String tableName = "ETS_PA_PROJECTS_ALL";
            DataUniqueChecker datChecker = new DataUniqueChecker(tableName, dtoParameter, conn);
            datChecker.setDBAction(DBActionConstant.UPDATE);
            datChecker.setServletConfig(servletConfig);
//            boolean isValid = datChecker.isDataValid();
//            if (!isValid) {
//                prodMessage(MsgKeyConstant.UNIQUE_ERROR);
//                getMessage().addParameterValue(datChecker.getInValidData());
//            } else {
                super.updateData();
                operateResult = true;
                getMessage().addParameterValue("��Ŀ");
//            }
        } catch (ValidateException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.COMMON_ERROR);
        }
    }

    /**
     * ���ܣ�ɾ����Ŀά����(EAM)��ETS_PA_PROJECTS_ALL�����ݡ�
     * @return boolean
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("��Ŀά��");
    }

}
