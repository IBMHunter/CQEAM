package com.sino.ams.print.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.print.dto.BarcodeReceiveDTO;
import com.sino.ams.print.model.BarcodeReceiveModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

public class BarcodeReceiveDAO extends AMSBaseDAO {
	
	/**
	 * 
	 * Title: 			SinoApplication
	 * @param userAccount		SfUserDTO  ����ϵͳ�����ղ����û�����
	 * @param dtoParameter		LabelDTO   װ�ڱ��β��������ݶ���
	 * @param conn		Connection  ���ݿ�����,�е����ߴ���
	 * Description:		Java Enterprise Edition Ӧ�ÿ���
	 * Copyright: 		����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�
	 * Copyright: 		������Ȩ����˼ŵ����Ϣ�������޹�˾��һ����Χ��ʹ��
	 * Company: 		����˼ŵ����Ϣ�������޹�˾
	 * Function			ETS_ROLL_CALL_BARCODE ���ݷ��ʲ㹹�캯��
	 */
	public BarcodeReceiveDAO(SfUserDTO userAccount, BarcodeReceiveDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		barcodReceiveModel = new BarcodeReceiveModel((SfUserDTO) userAccount, dtoParameter);
	}
	
	private BarcodeReceiveModel barcodReceiveModel = null;
	
    private final static String[] fieldNames = {
/*    	"barcode", "barcodePrintNum", "barcodeReceiveId", "erbiId", "impError", "impUserId", 
    	"organization", "organizationId", "printDate", "printUser", "printUserName", "receiveDate", 
    	"receiveDept", "receiveDeptName", "receiveRemark", "receiveUser", "receiveUserName", "remark"  */   
    	"barcode", "organization", "receiveDeptName", "receiveUserName", "receiveDate", "printDate", "printUserName", "receiveRemark"
    	};
	

	/**
	 * 
	 * Title: 			SinoApplication
	 * @param userAccount		SfUserDTO  ����ϵͳ�����ղ����û�����
	 * @param dtoParameter		LabelDTO   װ�ڱ��β��������ݶ���
	 * @param conn		Connection  ���ݿ�����,�е����ߴ���
	 * Description:		Java Enterprise Edition Ӧ�ÿ���
	 * Copyright: 		����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�
	 * Copyright: 		������Ȩ����˼ŵ����Ϣ�������޹�˾��һ����Χ��ʹ��
	 * Company: 		����˼ŵ����Ϣ�������޹�˾
	 * Function			SQL������BaseSQLProducer�ĳ�ʼ����
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		BarcodeReceiveDTO dtoPara = (BarcodeReceiveDTO)dtoParameter;
		sqlProducer = new BarcodeReceiveModel(userAccount, dtoPara);
	}
	

	 /**
	 * ���ܣ�����Excel�ļ���
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 *
	 */
	public File exportFile(String excelType) throws DataTransException {     
			File file = null;
			BarcodeReceiveModel  barcodeReceiveModel =(BarcodeReceiveModel)sqlProducer;
			SQLModel sqlModel = barcodeReceiveModel.getPageQueryModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setCalPattern(CalendarConstant.LINE_PATTERN);
			rule.setSourceConn(conn);
			String reportTitle = "��ǩ����ά��";
			if (!StrUtil.isNotEmpty(excelType)) {
				excelType = "xls";
			}
			String fileName = reportTitle + "." + excelType;
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			//fieldMap.put("FROM_BARCODE", "��ʼ��ǩ");
			//fieldMap.put("TO_BARCODE", "������ǩ");
			//fieldMap.put("BARCODE_QTY", "��ǩ����");
			fieldMap.put("BARCODE", "��ǩ��");
			fieldMap.put("ASSETS_TYPE", "��ǩ����");
			fieldMap.put("RECEIVE_DEPT_NNAME", "���ò���");
			fieldMap.put("RECEIVE_USER_NAME", "������");
			fieldMap.put("RECEIVE_DATE", "��������");
			fieldMap.put("PRINT_USER_NAME", "��ӡ��");
			fieldMap.put("PRINT_DATE", "��ӡ����");
			fieldMap.put("COMPANY", "��ӡ����");
			fieldMap.put("BARCODE_PRINT_NUM", "��ӡ����");
			fieldMap.put("RECEIVE_REMARK", "����ԭ��");

			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle("��ǩ�����ɼ���ǩ��ӡ");
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
			return file;
	}

	
	
	/**
	 * 
	 * Function:		����ҳ���ύ��Ou��ŵõ���OU�µ����в��ű�š�����
	 * @param organizationId
	 * @return
	 * @throws QueryException
	 */
	public RowSet getDeptByOu(int organizationId) throws QueryException{
		SQLModel sqlModel = barcodReceiveModel.getDeptByOu(organizationId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        return simpleQuery.getSearchResult();
	}
	
	/**
	 * 
	 * Function:		ͨ��OU�鵽��OU��Ӧ�Ĺ�˾���
	 * @return			��˾���
	 * @throws QueryException 
	 * @throws ContainerException 
	 */
	public String getCompanyCodeByOrganization(int organizationId) throws QueryException, ContainerException{
		SQLModel sqlModel = barcodReceiveModel.getCompanyCodeByOrganization(organizationId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        Row row = simpleQuery.getFirstRow();
        return row.getValue(0).toString();
	}
	
    public void deleteImportModel() throws SQLModelException, QueryException, DataHandleException {
    	BarcodeReceiveModel beModel = (BarcodeReceiveModel) sqlProducer;
        SQLModel sqlModel = beModel.deleteImportModel();
        DBOperator.updateRecord(sqlModel, conn);
    }
    
    /**
     * ���ܣ����뵽�ӿڱ�
     *
     * @param dtoSet DTOSet
     * @return boolean
     * @throws DataHandleException
     * @throws SQLModelException
     */
    public boolean itemImportData(DTOSet dtoSet) throws DataHandleException {
        boolean operateResult = false;
        boolean autoCommit = true;
        PreparedStatement pstmt = null;
        try {
            if (dtoSet != null && dtoSet.getSize() > 0) {
                autoCommit = conn.getAutoCommit();
                conn.setAutoCommit(false);
                BarcodeReceiveModel modelProducer = (BarcodeReceiveModel) sqlProducer;
                SQLModel sqlModel = modelProducer.insertImportModel();
                pstmt = conn.prepareCall(sqlModel.getSqlStr());
                int fieldCount = fieldNames.length;
                for (int i = 0; i < dtoSet.getSize(); i++) {
                	BarcodeReceiveDTO brDTO = (BarcodeReceiveDTO) dtoSet.getDTO(i);
                    for (int j = 0; j < fieldCount; j++) {
                        String fieldName = fieldNames[j];
                        String fieldValue = StrUtil.nullToString(ReflectionUtil.getProperty(brDTO, fieldName));
                        pstmt.setString(j + 1, fieldValue);
                    }
                    //pstmt.setInt(fieldCount + 1, Integer.parseInt(userAccount.getUserId()));
                    pstmt.setInt(fieldCount + 1, userAccount.getUserId());
                    pstmt.addBatch();
                    if (i % 100 == 0 && i > 0) {
                        pstmt.executeBatch();
                    }
                }
                pstmt.executeBatch();
            }
            operateResult = true;
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            try {
                DBManager.closeDBStatement(pstmt);
                if (operateResult) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
        return operateResult;
    }
    
    public String doVerifyData() throws DataHandleException {
        CallableStatement cs = null;
        String errorMsg = "";
        try {
            String callStr = "{CALL dbo.RECEIVE_BARCODE_CHECK_DATA(?, ?)}";
            cs = conn.prepareCall(callStr);
            //cs.setInt(1, Integer.parseInt(userAccount.getUserId()));
            int a = userAccount.getUserId();
            cs.setInt(1, userAccount.getUserId());
            cs.registerOutParameter(2, Types.VARCHAR);
            conn.setAutoCommit(true);  
            cs.execute();
            errorMsg = cs.getString(2);           
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            DBManager.closeDBStatement(cs);
        }
        return errorMsg;
    }
    
    public String submitOrderDtl() throws DataHandleException {

        CallableStatement cs = null;
        String errorMsg = "";
        try {
        	boolean autoCommit = conn.getAutoCommit();
            String callStr = "{CALL dbo.RECEIVE_BARCODE_TRANS_DATA(?, ?)}";
            cs = conn.prepareCall(callStr);
            //cs.setInt(1, Integer.parseInt(userAccount.getUserId()));
            cs.setInt(1, userAccount.getUserId());
            cs.registerOutParameter(2, Types.VARCHAR);  
            cs.execute();
            errorMsg = cs.getString(2);  
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            DBManager.closeDBStatement(cs);
        }
        return errorMsg;
    }
    
    public RowSet getImportErrors() throws QueryException {
    	BarcodeReceiveModel modelProducer = (BarcodeReceiveModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getImportErrorModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        return simpleQuery.getSearchResult();
    }
    
}
