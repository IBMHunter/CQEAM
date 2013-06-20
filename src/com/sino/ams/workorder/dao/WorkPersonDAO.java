package com.sino.ams.workorder.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.WorkPersonModel;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.db.DBActionConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DataUniqueChecker;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.ValidateException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2008-1-18
 * Time: 10:04:33
 * To change this template use File | Settings | File Templates.
 */
public class WorkPersonDAO extends AMSBaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�����(EAM) ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsRentDeadlineDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public WorkPersonDAO(SfUserDTO userAccount, EtsWorkorderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsWorkorderDTO dtoPara = (EtsWorkorderDTO) dtoParameter;
        super.sqlProducer = new WorkPersonModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ�����������ݡ�
     * @return boolean
     */
    public void createData() {
        boolean operateResult = false;
        try {
            String tableName = "";
            DataUniqueChecker datChecker = new DataUniqueChecker(tableName, dtoParameter, conn);
            datChecker.setDBAction(DBActionConstant.INSERT);
            datChecker.setServletConfig(servletConfig);
            boolean isValid = datChecker.isDataValid();
            if (!isValid) {
                prodMessage(MsgKeyConstant.UNIQUE_ERROR);
                getMessage().addParameterValue("");
            } else {
                super.createData();
                operateResult = true;
                getMessage().addParameterValue("");
            }
        } catch (ValidateException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.COMMON_ERROR);
        } catch (DataHandleException ex) {
        }
//        return operateResult;
    }

    /**
     * ���ܣ�����(EAM)�������ݡ�
     * @return boolean
     */
    public void updateData() throws DataHandleException {
//		boolean operateResult = super.updateData();
        super.updateData();
        getMessage().addParameterValue("");
//		return operateResult;
    }

    /**
     * ���ܣ�ɾ�������ݡ�
     * @return boolean
     */
    public void deleteData() throws DataHandleException {
//		boolean operateResult = super.deleteData();
        super.deleteData();
        getMessage().addParameterValue("");
//		return operateResult;
    }

    public boolean verifyBarcode(String barcode) throws QueryException { //ִ��У��Barcode����
        boolean success = false;
        WorkPersonModel amsRentDeadlineModel = (WorkPersonModel) sqlProducer;
        SQLModel sqlModel = amsRentDeadlineModel.getVerifyBarcodeModel(barcode);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();
        return success;
    }

    public File getExportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            String reportTitle = "";

            reportTitle = "���˹�����ѯ";
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
            Map fieldMap = getFieldMap();


            rule.setFieldMap(fieldMap);
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
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataTransException(ex);
        }
        return file;
    }

    private Map getFieldMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("ORG_NAME", "��˾����");
        fieldMap.put("WORKORDER_NO", "������");
        fieldMap.put("WORKORDER_FLAG_DESC", "����״̬");
        fieldMap.put("ATTRIBUTE4", "����רҵ");
        fieldMap.put("WORKORDER_TYPE_DESC", "��������");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���");
        fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
        fieldMap.put("START_DATE", "��ʼ����");
        fieldMap.put("IMPLEMENT_DAYS", "ʵʩ����(��)");
        fieldMap.put("IMPLEMENT_USER", "ִ����");
        fieldMap.put("UPLOAD_DATE", "ʵ���������");
        fieldMap.put("DIFF", "����");
        fieldMap.put("OVERTIME", "��ʱ");

        return fieldMap;
    }

}
