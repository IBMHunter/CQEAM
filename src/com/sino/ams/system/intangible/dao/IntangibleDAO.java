package com.sino.ams.system.intangible.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.intangible.dto.IntangibleDTO;
import com.sino.ams.system.intangible.model.IntangibleModel;
import com.sino.ams.system.switches.model.EtsObjectModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
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


public class IntangibleDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

/**
	 * ���ܣ��ʲ��ص��(EAM) ETS_OBJECT ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public IntangibleDAO(SfUserDTO userAccount, IntangibleDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

    /**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		IntangibleDTO dtoPara = (IntangibleDTO) dtoParameter;
		super.sqlProducer = new IntangibleModel( (SfUserDTO) userAccount, dtoPara);
	}

	/**
	 * ���ܣ�����ObjectCategory��� categoryName
	 * @return String
	 * @throws QueryException
	 */
	public String getCategoryName() throws QueryException {
        String categoryName = "";
        EtsObjectDTO etsObjectDTO = (EtsObjectDTO) dtoParameter;
        EtsObjectModel eomodel = new EtsObjectModel(sfUser, etsObjectDTO);
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
			EtsObjectModel etsObjectModel = (EtsObjectModel) sqlProducer;
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
			EtsObjectModel etsObjectModel = (EtsObjectModel) sqlProducer;
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
		EtsObjectModel etsObjectModel = (EtsObjectModel) sqlProducer;
		SQLModel sqlModel = etsObjectModel.getInureModel();
		DBOperator.updateRecord(sqlModel, conn);
	}

   public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            String fileName = "�����ʲ�.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("COMPANY", "��˾");
            fieldMap.put("ASSET_NUMBER", "�ʲ����");
            fieldMap.put("BARCODE", "�ʲ�����");
            fieldMap.put("ITEM_NAME", "�ʲ�����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
            fieldMap.put("LIFE_IN_YEARS", "̯������");
            fieldMap.put("DEPRN_COST", "�۾ɽ��");
            fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
            fieldMap.put("VENDOR_NAME", "��Ӧ��");

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("�����ʲ�");
            custData.setReportPerson(sfUser.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
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
