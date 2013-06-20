package com.sino.ams.synchronize.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.synchronize.dto.EamSyschronizeDTO;
import com.sino.ams.synchronize.model.EamNewLocusModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by sunny song
 * User: song
 * Date: 2008-3-11
 * Time: 8:31:36
 * To change this template use File | Settings | File Templates.
 */
public class EamNewLocusDAO extends AMSBaseDAO {

    /**
     * ���ܣ�eAM�����ص�ͬ�� ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsItemMatchDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EamNewLocusDAO(SfUserDTO userAccount, EamSyschronizeDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EamSyschronizeDTO dtoPara = (EamSyschronizeDTO) dtoParameter;
        super.sqlProducer = new EamNewLocusModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * EAM�ص�ͬ��
     *
     * @param systemId String
     */
    public void syschronizeLocus(String systemId) {
        CallableStatement cs = null;
        String callStr = "{CALL AMS_SYN_PKG.SYN_EAM_NEW_PLACE(?, ?, ?, ?)}";
        try {
            cs = conn.prepareCall(callStr);
            cs.setInt(1, userAccount.getOrganizationId());
            cs.setString(2, systemId);
            cs.registerOutParameter(3, Types.NUMERIC);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.execute();

            if(cs.getString(4)!=null)
            {
                 this.message.setMessageValue(cs.getString(4));
                 this.message.setIsError(true);
                 Logger.logError("{CALL AMS_SYN_PKG.SYN_EAM_NEW_PLACE(?, ?, ?, ?)} ERROR:"+cs.getString(4));
                 Logger.logError("(0)"+userAccount.getOrganizationId()+";(1)"+systemId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeDBStatement(cs);
        }
    }

    public void insertSynTmp(String systemId) {
        CallableStatement cs = null;
        String callStr = "{CALL AMS_SYN_PKG.FN_SPLIT(?, ?)}";
        try {
            cs = conn.prepareCall(callStr);
            cs.setString(1, systemId);
            cs.setString(2, ",");
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeDBStatement(cs);
        }
    }

    public List changeString(String Str[]) {
        List al = new ArrayList();
        for (int i = 0; i < Str.length; i++) {
            if (Str[i] != null && !Str[i].equals("")) {
                long aa = Long.parseLong(Str[i]);
                al.add(new Long(aa));
            }
        }
        return al;
    }

     public File getExportFile() throws DataTransException {
	   File file = null;
       EamNewLocusModel modelProducer = (EamNewLocusModel)sqlProducer;
       SQLModel sqlModel = modelProducer.getPageQueryModel();
       String reportTitle = "EAMϵͳ�����ص�";
       String fileName = reportTitle + ".xls";
       String filePath = WorldConstant.USER_HOME;
       filePath += WorldConstant.FILE_SEPARATOR;
       filePath += fileName;
       TransRule rule = new TransRule();
       rule.setDataSource(sqlModel);
       rule.setSourceConn(conn);
       rule.setTarFile(filePath);
       DataRange range = new DataRange();
       rule.setDataRange(range);
       rule.setFieldMap(getFieldMap());
       CustomTransData custData = new CustomTransData();
       custData.setReportTitle(reportTitle);
       custData.setReportPerson(userAccount.getUsername());
       custData.setNeedReportDate(true);
       rule.setCustData(custData);
       rule.setCalPattern(LINE_PATTERN);
       TransferFactory factory = new TransferFactory();
       DataTransfer transfer = factory.getTransfer(rule);
       transfer.transData();
       file = (File) transfer.getTransResult();
       return file;
	}

    private Map getFieldMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("WORKORDER_OBJECT_CODE", "EAM�ص����");
        fieldMap.put("WORKORDER_OBJECT_LOCATION", "EAM�ص�����");
        fieldMap.put("ASSETS_LOCATION_CODE", "ԭMIS�ص����");
        fieldMap.put("ASSETS_LOCATION", "ԭMIS�ص�����");
        fieldMap.put("LAST_UPDATE_DATE", "������ʱ��");
        fieldMap.put("USERNAME", "��������");
        fieldMap.put("WORKORDER_CATEGORY", "�ص����");
        fieldMap.put("CREATION_DATE", "��������");
        return fieldMap;
    }
}
