package com.sino.ams.system.basepoint.dao;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.system.basepoint.dto.EtsObjectAttributeDTO;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.basepoint.model.EtsObjectModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsObjectDAO</p>
 * <p>Description:�����Զ����ɷ������EtsObjectDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsObjectDAO extends BaseDAO {
    private SfUserDTO sfUser = null;

	/**
	 * ���ܣ��ʲ��ص��(EAM) ETS_OBJECT ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsObjectDAO(SfUserDTO userAccount, EtsObjectDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		EtsObjectDTO dtoPara = (EtsObjectDTO)dtoParameter;
		super.sqlProducer = new EtsObjectModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ������ʲ��ص��(EAM)��ETS_OBJECT�����ݡ�
	 * @param objAttibute EtsObjectAttributeDTO
	 * @return boolean
	 */
	public boolean createData(EtsObjectAttributeDTO objAttibute) throws DataHandleException{                         //do _save �� ����
		boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try {
                autoCommit = conn.getAutoCommit();
                conn.setAutoCommit(false);
                EtsObjectDTO objDTO = (EtsObjectDTO)dtoParameter;
                objDTO.setWorkorderObjectNo(getNextWorkorderObjectNo());
                setDTOParameter(objDTO);
                super.createData();                                       //��������
                inAOAData();
//                EtsObjectAttributeDTO objDTO2 = new EtsObjectAttributeDTO();
//                objDTO2.setObjectNo(objDTO.getWorkorderObjectNo());                          //��ȡ WORKORDER_OBJECT_NO
//
//
                objAttibute.setObjectNo(objDTO.getWorkorderObjectNo());
                EtsObjectAttributeDAO dao = new EtsObjectAttributeDAO(sfUser,objAttibute,conn);
                dao.createData();                                                  //����WORKORDER_OBJECT_NO���в������
                operateResult = true;
                conn.commit();
                hasError = false;
                getMessage().addParameterValue("�ʲ��ص��");
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } finally{
            try {
                if(hasError){
                    conn.rollback();                      //�ع�
                }
                conn.setAutoCommit(autoCommit);          //�ָ���ǰ״̬
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
            }
        }
        return operateResult;

    }



    private String getNextWorkorderObjectNo() throws SQLException {
        SeqProducer seqProducer = new SeqProducer(conn);
        return String.valueOf(seqProducer.getStrNextSeq("ETS_OBJECT_S"));
    }

	/**
	 * ���ܣ������ʲ��ص��(EAM)��ETS_OBJECT�����ݡ�
	 * @param workorderObjectNo String
	 * @param objAttibute EtsObjectAttributeDTO
	 * @return boolean
	 */
	public boolean updateData(String workorderObjectNo,EtsObjectAttributeDTO objAttibute) throws DataHandleException{
	    boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try{
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            super.updateData();                                                              //���޸Ĳ���
//            EtsObjectAttributeDTO objDTO2 = new EtsObjectAttributeDTO();
            objAttibute.setObjectNo(workorderObjectNo);
            EtsObjectAttributeDAO dao = new EtsObjectAttributeDAO(sfUser,objAttibute,conn);
            dao.deleteData();
//            EtsObjectAttributeDAO dao2 = new EtsObjectAttributeDAO(sfUser,objAttibute,conn);
            dao.createData();                                                  //�������
            operateResult = true;
            conn.commit();
            hasError = false;
            getMessage().addParameterValue("�ʲ��ص��");
        }catch(SQLException ex){
          Logger.logError(ex);
          prodMessage(MsgKeyConstant.SQL_ERROR);
        }finally{
           try{
              if(hasError){
                  conn.rollback();
              }
              conn.setAutoCommit(autoCommit);
           }catch(SQLException ex){
               Logger.logError(ex);
               prodMessage(MsgKeyConstant.SQL_ERROR);
           }
        }
		return operateResult;
	}

	/**
	 * ���ܣ��޸��ʲ��ص��(EAM)��ETS_OBJECT�����ݣ�ִ����ϸҳ��ĵ�һʧЧ������
	 * @return boolean
     *
	 */
	public void deleteData() throws DataHandleException{        //����ʧЧ����
//		boolean operateResult = super.deleteData();
		super.deleteData();
		getMessage().addParameterValue("�ʲ��ص�");
//		return operateResult;
	}

  public void  disabledData(String[] workorderObjectNos) throws DataHandleException {        //ִ������ʧЧ����
      EtsObjectModel etsObjectModel = (EtsObjectModel)sqlProducer;
      SQLModel sqlModel = etsObjectModel.getDisabledModel(workorderObjectNos);
      DBOperator.updateRecord(sqlModel, conn);
	}

  public void  efficientData(String[] workorderObjectNos) throws DataHandleException {        //ִ��������ЧЧ����
      EtsObjectModel etsObjectModel = (EtsObjectModel)sqlProducer;
      SQLModel sqlModel = etsObjectModel.getEfficientModel(workorderObjectNos);
      DBOperator.updateRecord(sqlModel, conn);
	}

  public void inureData()throws DataHandleException{                           //ִ����Ч����
     EtsObjectModel etsObjectModel = (EtsObjectModel)sqlProducer;
     SQLModel sqlModel = etsObjectModel.getInureModel();
     DBOperator.updateRecord(sqlModel, conn);
   }

  /**
	 * ���ܣ�����Excel�ļ���
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File exportFile() throws DataTransException {
		File file = null;
		try {
			DataTransfer transfer = null;
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);

			String fileName = "��վ�ص�ͳ�Ʊ�.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);

			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���");
			fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
			fieldMap.put("WORKORDER_OBJECT_LOCATION", "���ڵص�");
			fieldMap.put("ORGANIZATION_ID", "��֯ID");
			fieldMap.put("COUNTY_NAME", "��������");
			fieldMap.put("DISABLE_DATE", "ʧЧ����");
			fieldMap.put("CATEGORY_NAME", "�ص����");
//		fieldMap.put("IS_TEMP_ADDR", "�Ƿ���ʱ�ص�");
			fieldMap.put("CREATION_DATE", "��������");
			fieldMap.put("CREATED_BY", "������");
			fieldMap.put("PROJECT_NAME", "��������");

			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(fileName);
			custData.setReportPerson(sfUser.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			/*rule.setSheetSize(1000);*/
			//���÷�ҳ��ʾ
			TransferFactory factory = new TransferFactory();
			transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}


     public boolean doVerifyWorkNo(String workorderObjectCode) throws QueryException { //ִ��У��workorderObjectCode����
        boolean success = false;
        EtsObjectModel etsObjectModel = (EtsObjectModel) sqlProducer;
        SQLModel sqlModel = etsObjectModel.getVerifyWorkNoModel(workorderObjectCode);

        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();

        return success;
    }
    

	public void inAOAData() throws DataHandleException { //ִ�в���AOA�����
		EtsObjectModel etsObjectModel = (EtsObjectModel) sqlProducer;
		SQLModel sqlModel = etsObjectModel.getAOACreateModel();
		DBOperator.updateRecord(sqlModel, conn);
	}
}
