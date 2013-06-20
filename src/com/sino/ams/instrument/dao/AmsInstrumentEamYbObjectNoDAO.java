package com.sino.ams.instrument.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.instrument.model.AmsInstrumentEamYbObjectNoModel;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.RowSet;
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
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

public class AmsInstrumentEamYbObjectNoDAO extends AMSBaseDAO {

	public AmsInstrumentEamYbObjectNoDAO(SfUserDTO userAccount, EtsObjectDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

    /**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EtsObjectDTO dtoPara = (EtsObjectDTO) dtoParameter;
		super.sqlProducer = new AmsInstrumentEamYbObjectNoModel( (SfUserDTO) userAccount, dtoPara);
	}

	/**
	 * ���ܣ�����ObjectCategory��� categoryName
	 * @return String
	 * @throws QueryException
	 */
	public String getCategoryName() throws QueryException {
        String categoryName = "";
        EtsObjectDTO etsObjectDTO = (EtsObjectDTO) dtoParameter;
        AmsInstrumentEamYbObjectNoModel eomodel = new AmsInstrumentEamYbObjectNoModel(userAccount, etsObjectDTO);
        SQLModel sModel = eomodel.getCategoryNameModel();
        SimpleQuery sQuery = new SimpleQuery(sModel, conn);
        sQuery.executeQuery();
        if (sQuery.hasResult()) {
            RowSet row = sQuery.getSearchResult();
            try {
                categoryName = (String) row.getRow(0).getValue("VALUE");
            } catch (ContainerException e) {
                e.printStackTrace();
                throw new QueryException();
            }
        }
        return categoryName;
    }

    /**
	 * ���ܣ������ʲ��ص��(EAM)��ETS_OBJECT�����ݡ�
	 * @return boolean
	 */
	public void createData() throws DataHandleException{
		EtsObjectDTO dtoPara = (EtsObjectDTO) dtoParameter;
	    super.createData();
		getMessage().addParameterValue(dtoPara.getCategoryName());
//		return operateResult;
	}

    /**
	 * ���ܣ�ִ�е���ʧЧ������
	 * @return boolean
	 */
	public void deleteData() throws DataHandleException{
		 super.deleteData();
		getMessage().addParameterValue("�ص���Ϣ");
//		return operateResult;
	}

    /**
	 * ���ܣ������ʲ��ص��(EAM)��ETS_OBJECT�����ݡ�
	 * @return boolean
	 */
	public void updateData() throws DataHandleException{
		EtsObjectDTO dtoPara = (EtsObjectDTO) dtoParameter;
		 super.updateData();
		getMessage().addParameterValue(dtoPara.getCategoryName());
//		return operateResult;
	}

	public void disabledData(String[] workorderObjectNos){ //ִ������ʧЧ����
		EtsObjectDTO dtoPara = (EtsObjectDTO) dtoParameter;
		try {
			AmsInstrumentEamYbObjectNoModel etsObjectModel = (AmsInstrumentEamYbObjectNoModel) sqlProducer;
			SQLModel sqlModel = etsObjectModel.getDisabledModel(workorderObjectNos);
			DBOperator.updateRecord(sqlModel, conn);
			prodMessage(CustMessageKey.DISABLE_SUCCESS);
			getMessage().addParameterValue(dtoPara.getCategoryName());
		} catch (DataHandleException ex) {
			prodMessage(CustMessageKey.DISABLE_FAILURE);
			getMessage().addParameterValue(dtoPara.getCategoryName());
			ex.printLog();
		}
	}

	public void efficientData(String[] workorderObjectNos){ //ִ��������ЧЧ����
		EtsObjectDTO dtoPara = (EtsObjectDTO) dtoParameter;
		try {
			AmsInstrumentEamYbObjectNoModel etsObjectModel = (AmsInstrumentEamYbObjectNoModel) sqlProducer;
			SQLModel sqlModel = etsObjectModel.getEfficientModel(workorderObjectNos);
			DBOperator.updateRecord(sqlModel, conn);
			prodMessage(CustMessageKey.ENABLE_SUCCESS);
			getMessage().addParameterValue(dtoPara.getCategoryName());
		} catch (DataHandleException ex) {
			prodMessage(CustMessageKey.ENABLE_FAILURE);
			getMessage().addParameterValue(dtoPara.getCategoryName());
			ex.printLog();
		}
	}

	public void inureData() throws DataHandleException { //ִ����Ч����
		AmsInstrumentEamYbObjectNoModel etsObjectModel = (AmsInstrumentEamYbObjectNoModel) sqlProducer;
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
			EtsObjectDTO etsObjectDTO = (EtsObjectDTO) dtoParameter;
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
			String fileName = etsObjectDTO.getCategoryName() + "ͳ�Ʊ�.xls";
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
			custData.setReportPerson(userAccount.getUsername());
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

        public boolean doVerifyWorkNo(String workorderObjectCode) throws QueryException { //ִ��У��workorderObjectCode����
        boolean success = false;
        AmsInstrumentEamYbObjectNoModel etsObjectModel = (AmsInstrumentEamYbObjectNoModel) sqlProducer;
        SQLModel sqlModel = etsObjectModel.getVerifyWorkNoModel(workorderObjectCode);

        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();

        return success;
    }

        public boolean doVerifyWorkBarcode(String[] workorderObjectNos) throws QueryException { //ִ��У��õص��Ƿ�����豸
        boolean success = false;
        AmsInstrumentEamYbObjectNoModel etsObjectModel = (AmsInstrumentEamYbObjectNoModel) sqlProducer;
        SQLModel sqlModel = etsObjectModel.getVerifyBarcode(workorderObjectNos);

        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        success = sq.hasResult();

        return success;
    }


  public String  createData2() throws DataHandleException{                         //do _save �� ����
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
        return String.valueOf(operateResult);

    }

    private String getNextWorkorderObjectNo() throws SQLException {
        SeqProducer seqProducer = new SeqProducer(conn);
        return  String.valueOf(seqProducer.getStrNextSeq("ETS_OBJECT_S"));
    }

    public void inAOAData() throws DataHandleException { //ִ�в���AOA�����
    	AmsInstrumentEamYbObjectNoModel etsObjectModel = (AmsInstrumentEamYbObjectNoModel) sqlProducer;
		SQLModel sqlModel = etsObjectModel.getAOACreateModel();
		DBOperator.updateRecord(sqlModel, conn);
	}

}
