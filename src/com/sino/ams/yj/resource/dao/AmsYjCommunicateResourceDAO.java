package com.sino.ams.yj.resource.dao;

import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import com.sino.base.dto.DTO;
import com.sino.base.util.StrUtil;
import com.sino.base.util.ArrUtil;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.datatrans.*;
import com.sino.base.constant.WorldConstant;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.yj.resource.dto.AmsYjCommunicateResourceDTO;
import com.sino.ams.yj.resource.model.AmsYjCommunicateResourceModel;

/**
 * <p>Title: AmsYjCommunicateResourceDAO</p>
 * <p>Description:�����Զ����ɷ������AmsYjCommunicateResourceDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-ս��Ӧ��ͨ����Դ
 */

public class AmsYjCommunicateResourceDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�ս��Ӧ��ͨ����Դ AMS_YJ_COMMUNICATE_RESOURCE ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjCommunicateResourceDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsYjCommunicateResourceDAO(SfUserDTO userAccount, AmsYjCommunicateResourceDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsYjCommunicateResourceDTO dtoPara = (AmsYjCommunicateResourceDTO) dtoParameter;
        super.sqlProducer = new AmsYjCommunicateResourceModel((SfUserDTO) userAccount, dtoPara);
    }

    public boolean saveData(boolean isNew) {
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

    public boolean deleteAllData(String ids) {
        boolean flag = false;
        AmsYjCommunicateResourceModel resourceModel=(AmsYjCommunicateResourceModel)sqlProducer;
        SQLModel sqlModel=resourceModel.getDeleteDataModel(ids);
        try {
            DBOperator.updateRecord(sqlModel,conn);
            flag=true;
        } catch (DataHandleException e) {
            Logger.logError(e);
        }

        return flag;
    }

    public File getExportFile() throws DataTransException {
        File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			String reportTitle = "ս��Ӧ��ͨ����Դ��Ϣ��";
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
			fieldMap.put("RESOURCE_ID", "���");
			fieldMap.put("DEPT_NAME", "��λ���� ");
			fieldMap.put("EQUIPMENT_NAME", "װ������");
			fieldMap.put("RESOURCE_QTY", "����");
			fieldMap.put("LOCATION", "�÷�λ��");
			fieldMap.put("MODEL", "���");
			fieldMap.put("NORMAL_STATUS", "��������");
			fieldMap.put("CHARGE_DEPT", "���ܲ���");
			fieldMap.put("CHARGER", "������");
			fieldMap.put("CHARGER_MOBILE", "�ֻ�");
			fieldMap.put("KEEPER", "������");
			fieldMap.put("KEEPER_MOBILE", "�ֻ�");
			fieldMap.put("REMARK", "��ע");

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
     * ͳ������
     * @return
     * @throws DataTransException
     * @throws SQLModelException
     */
    public File getExpFile() throws DataTransException, SQLModelException {
           AmsYjCommunicateResourceModel modelProducer = (AmsYjCommunicateResourceModel) sqlProducer;
           SQLModel sqlModel = modelProducer.getExportModel();
           String reportTitle = "ս��Ӧ��ͨ����Դͳ�Ʊ�";
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

           fieldMap.put("COMPANY", "��˾����");
           fieldMap.put("EQUIPMENT_NAME", "װ������");
           fieldMap.put("QTY", "����");
      
           return fieldMap;
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

}