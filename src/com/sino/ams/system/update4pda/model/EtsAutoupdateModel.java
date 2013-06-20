package com.sino.ams.system.update4pda.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.update4pda.dto.EtsAutoupdateDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EtsAutoupdateModel</p>
 * <p>Description:�����Զ�����SQL��������EtsAutoupdateModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author aidy
 * @version 1.0
 */


public class EtsAutoupdateModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�PDA����汾��(EAM) ETS_AUTOUPDATE ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsAutoupdateDTO ���β���������
     */
    public EtsAutoupdateModel(SfUserDTO userAccount, EtsAutoupdateDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ�����PDA����汾��(EAM) ETS_AUTOUPDATE���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */


    /**
     * ���ܣ�����Զ�����PDA����汾��(EAM) ETS_AUTOUPDATE���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel(EtsAutoupdateDTO etsAutoupdate) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "UPDATE ETS_AUTOUPDATE"
                + " SET"
                + " VERSION = ?,"
                //+ " DESCRIPTION = GETDATE(),"
                + " FILESIZE = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_BY = ?"
                + " WHERE"
                + " MODULE = ?";
        sqlArgs.add(etsAutoupdate.getVersion());
        //sqlArgs.add(String.valueOf(etsAutoupdate.getFilesize()));
        sqlArgs.add(etsAutoupdate.getFilesize());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(etsAutoupdate.getModule());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ�����Զ�����PDA����汾��(EAM) ETS_AUTOUPDATE������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getDataByModuleModel(String Module) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
/*        String sqlStr = "SELECT "
                + " VERSION"
                + " FROM"
                + " ETS_AUTOUPDATE"
                + " WHERE"
                + " MODULE = ?";
        sqlArgs.add(Module);*/
        
        String sqlStr = 
        "SELECT MODULE, VERSION, DESCRIPTION, FILESIZE \n" +
        "  FROM ETS_AUTOUPDATE \n" +
        " WHERE MODULE = ?";
        sqlArgs.add(Module);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = 
            "SELECT MODULE, VERSION, DESCRIPTION, FILESIZE \n" +
            "  FROM ETS_AUTOUPDATE \n" ;
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            return sqlModel;
    }
    
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = 
            "SELECT MODULE, VERSION, DESCRIPTION, FILESIZE \n" +
            "  FROM ETS_AUTOUPDATE \n" ;
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
            return sqlModel;
    }
}