package com.sino.ams.system.dict.dao;


import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.system.dict.dto.EtsFlexValuesDTO;
import com.sino.ams.system.dict.model.EtsFlexValuesModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.db.DBActionConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsFlexValuesDAO</p>
 * <p>Description:�����Զ����ɷ������EtsFlexValuesDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class EtsFlexValuesDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ��ֵ�����(EAM) ETS_FLEX_VALUES ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsFlexValuesDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EtsFlexValuesDAO(SfUserDTO userAccount, EtsFlexValuesDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsFlexValuesDTO dtoPara = (EtsFlexValuesDTO) dtoParameter;
        super.sqlProducer = new EtsFlexValuesModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ������ֵ�����(EAM)��ETS_FLEX_VALUES�����ݡ�
     * @return boolean
     */
    public void createData() {
        saveData(DBActionConstant.INSERT);
    }

    /**
     * ���ܣ������ֵ�����(EAM)��ETS_FLEX_VALUES�����ݡ�
     * @return boolean
     */
    public void updateData() {
        saveData(DBActionConstant.UPDATE);
    }

    /**
     * ���ܣ������ֵ�����(EAM)��ETS_FLEX_VALUES�����ݡ�
     * @param dbAction String
     * @return boolean
     */
    public boolean saveData(String dbAction) {
        boolean operateResult = false;
        try {
            String tableName = "ETS_FLEX_VALUES";
//            DataUniqueChecker dataChecker = new DataUniqueChecker(tableName, dtoParameter, conn);
//            dataChecker.setDBAction(dbAction);
//            dataChecker.setServletConfig(servletConfig);
//            if (dataChecker.isDataValid()) {
                if (dbAction.equals(DBActionConstant.INSERT)) {
                    super.createData();
                } else {
                    super.updateData();
                }
                getMessage().addParameterValue("�ֵ�");
//            } else {
//                EtsFlexValuesDTO dtoPara = (EtsFlexValuesDTO) dtoParameter;
//                prodMessage(MsgKeyConstant.UNIQUE_ERROR_2);
//                message.addParameterValue(dtoPara.getValue());
//                message.addParameterValue(dtoPara.getFlexValueSetName());
//                message.setIsError(true);
//            }
        } 
//        catch (ValidateException ex) {
//            ex.printLog();
//            prodMessage(MsgKeyConstant.COMMON_ERROR);
//            message.setIsError(true);
//        } 
        catch (DataHandleException e) {
            e.printStackTrace();
        }
        return operateResult;
    }

    /**
     * ���ܣ�ɾ���ֵ�����(EAM)��ETS_FLEX_VALUES�����ݡ�
     * @return boolean
     */
    public void deleteData() throws DataHandleException {
        super.deleteData();
        getMessage().addParameterValue("�ֵ�");
	}
    /**
     * ���ܣ�����Excel�ļ���
     * @return File
     * @throws DataTransException
     */
    public File exportFile() throws DataTransException {
        File file = null;
        try {

            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);
            
            String title = "ϵͳ�ֵ�ά��";
            String fileName = "ϵͳ�ֵ�ά��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("DICT_TYPE_CODE", "�������");
            fieldMap.put("DICT_TYPE_NAME", "��������");
            fieldMap.put("CODE", "�ֵ����");
            fieldMap.put("VALUE", "�ֵ�ֵ");
            fieldMap.put("DESCRIPTION", "�ֵ�����");
            fieldMap.put("ENABLED", "�Ƿ���Ч");
            fieldMap.put("IS_INNER", "�Ƿ�����");
            fieldMap.put("MAINTAIN_FLAG", "�ɷ�ά��");
            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(title);
            custData.setReportPerson(sfUser.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            /*rule.setSheetSize(1000);*/
            //���÷�ҳ��ʾ
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
}
