package com.sino.ams.yj.ensure.dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

import com.sino.base.dto.DTO;
import com.sino.base.util.StrUtil;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.DataTransException;
import com.sino.base.log.Logger;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.*;
import com.sino.base.constant.WorldConstant;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.yj.ensure.dto.AmsYjCommunicateEnsureDTO;
import com.sino.ams.yj.ensure.model.AmsYjCommunicateEnsureModel;


/**
 * <p>Title: AmsYjCommunicateEnsureDAO</p>
 * <p>Description:�����Զ����ɷ������AmsYjCommunicateEnsureDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ��ͨ�ű������
 */


public class AmsYjCommunicateEnsureDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�Ӧ��ͨ�ű����¼���Ϣ�� AMS_YJ_COMMUNICATE_ENSURE ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjCommunicateEnsureDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsYjCommunicateEnsureDAO(SfUserDTO userAccount, AmsYjCommunicateEnsureDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsYjCommunicateEnsureDTO dtoPara = (AmsYjCommunicateEnsureDTO) dtoParameter;
        super.sqlProducer = new AmsYjCommunicateEnsureModel((SfUserDTO) userAccount, dtoPara);
    }

    public boolean saveDate(boolean isNew) {
        boolean flag = false;
        try {
            if (isNew) {
                createData();
            } else {
                updateData();
            }
            flag = true;
        } catch (DataHandleException e) {
            Logger.logError(e);
        }
        return flag;
    }

    public void deleteAllData(String ids) throws DataHandleException {
        AmsYjCommunicateEnsureModel ensureModel = (AmsYjCommunicateEnsureModel) sqlProducer;
        SQLModel sqlModel = ensureModel.getDeleteAllData(ids);
        DBOperator.updateRecord(sqlModel, conn);
    }

    public File getExportFile() throws DataTransException, SQLModelException {
        AmsYjCommunicateEnsureModel modelProducer = (AmsYjCommunicateEnsureModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getExportModel();
        String reportTitle = "Ӧ��ͨ�ű����¼�ͳ�Ʊ�";
        String fileName = reportTitle + ".xls";
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setSourceConn(conn);
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);
        DataRange range = new DataRange();
        rule.setDataRange(range);
        rule.setFieldMap(getFieldMap());
        CustomTransData custData = new CustomTransData();
        custData.setReportTitle(reportTitle);
        custData.setReportPerson(sfUser.getUsername());
        custData.setNeedReportDate(true);
        rule.setCustData(custData);
        rule.setCalPattern(LINE_PATTERN);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        return (File) transfer.getTransResult();
    }

    private Map getFieldMap() {
        Map fieldMap = new HashMap();
        
        fieldMap.put("DEPT_NAME", "��λ");
        fieldMap.put("EVENT_1", "���ξ����¼���");
        fieldMap.put("EVENT_2", "�ڼ��ձ�����");
        fieldMap.put("EVENT_3", "��Ȼ�ֺ���");
        fieldMap.put("EVENT_4", "�¹�������");
        fieldMap.put("EVENT_5", "���������¼���");
        fieldMap.put("EVENT_6", "��ᰲȫ�¼���");
        fieldMap.put("ITEM_1", "Ͷ������");
        fieldMap.put("ITEM_2", "Ͷ���˴�");
        fieldMap.put("ITEM_3", "Ӧ��������");
        fieldMap.put("ITEM_4", "Ӧ��������");
        fieldMap.put("ITEM_5", "Ӧ��ͨ���豸��");
        fieldMap.put("ITEM_6", "Ӧ��ͨ���豸�״�");

        return fieldMap;
    }

     public File getExpFile() throws DataTransException {
        File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			String reportTitle = "Ӧ��ͨ�ű����¼���Ϣ��";
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
			Map fieldMap = new HashMap();
			fieldMap.put("COMMUNICATE_ID", "���");
			fieldMap.put("DEPT_NAME", "��λ");
			fieldMap.put("ENSURE_NAME", "ͨ�ű�������");
			fieldMap.put("EVENT_TYPE", "�¼�����");
			fieldMap.put("ENSURE_DATE_FROM", "����ʱ���");
			fieldMap.put("ENSURE_DATE_TO", "����ʱ�䵽");
			fieldMap.put("ENSURE_LOCATION", "���ϵص�");
			fieldMap.put("MANPOWER_QTY", "Ͷ������");
			fieldMap.put("MANPOWER_TIMES", "Ͷ���˴�");
			fieldMap.put("COMVAN_QTY", "Ӧ��������");
			fieldMap.put("COMVAN_TIMES", "Ӧ��������");
			fieldMap.put("EQUIPMENT_QTY", "Ӧ��ͨ���豸��");
			fieldMap.put("EQUIPMENT_UNIT", "Ӧ��ͨ���豸�״�");
			fieldMap.put("BLOCK_DEGREE", "ͨ����ϳ̶�");
			fieldMap.put("LOSS_CONDITION", "��ʧ���");
			fieldMap.put("ENSURE_MEASURE", "Ӧ�����ϴ�ʩ");
			fieldMap.put("RECOVER_SITUATION", "ͨ�Żָ������ʱ��");
			fieldMap.put("GOVERNMENT_EVALUATE", "�ط�������������");
			fieldMap.put("REASON_AFFECT", "�¼�ԭ��Ӱ�췶Χ");
			fieldMap.put("QUESTION", "���ڵ�����");
			fieldMap.put("GUARD_MEASURE", "δ��������ʩ");

			rule.setFieldMap(fieldMap);
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(reportTitle);
			custData.setReportPerson(sfUser.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			rule.setCalPattern(LINE_PATTERN);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
    }
     
     /**
      * �ַ���ת��
      */
     public String appendIntComvanId(String[] srcArr, String linkStr) {
         String retStr = "";
         if (srcArr != null && srcArr.length > 0) {
             boolean hasProcessed = false;
             for (int i = 0; i <= srcArr.length - 1; i++) {
             	retStr += "CONVERT(FLOAT, "+srcArr[i]+")"+ linkStr;
                 hasProcessed = true;
             }
             if (hasProcessed) {
                 retStr = retStr.substring(0, retStr.length() - linkStr.length());
             }
         }
         return retStr;
     }  
     
     /**
      * ȡ��ǰӦ�����͵������
      * @return
      */
 	public int getYjManagerMax(String yj_type) {
     	int isSyn= 0;
         CallableStatement cStmt = null;
         String sqlStr = "{call dbo.YJ_MANAGE_GET_MAX(?,?)}";
         try {
 			cStmt = conn.prepareCall(sqlStr);
 			cStmt.setString(1,yj_type);
 			cStmt.registerOutParameter(2,java.sql.Types.INTEGER);
             cStmt.execute();
             isSyn= cStmt.getInt(2);
             System.out.println("yyyyyy====="+isSyn);
 		} catch (SQLException e) {
 			e.printStackTrace();
 			isSyn= -1;
 		} finally {
             DBManager.closeDBStatement(cStmt);
         }
         return isSyn;
     }
     
 
     
     
}