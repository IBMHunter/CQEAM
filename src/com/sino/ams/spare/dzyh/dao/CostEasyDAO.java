package com.sino.ams.spare.dzyh.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;

import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.spare.dzyh.dto.CostEasyDTO;
import com.sino.ams.spare.dzyh.model.CostEasyModel;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.switches.model.EtsObjectModel;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2008-4-7
 * Time: 16:32:53
 * Function����ֵ�׺�ά����
 */
public class CostEasyDAO extends AMSBaseDAO {

	private SfUserDTO sfUser = null;

/**
	 * ���ܣ��ʲ��ص��(AMS) ETS_OBJECT ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsObjectDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public CostEasyDAO(SfUserDTO userAccount, CostEasyDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

    /**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		CostEasyDTO dtoPara = (CostEasyDTO) dtoParameter;
		super.sqlProducer = new CostEasyModel( (SfUserDTO) userAccount, dtoPara);
	}

	/**
	 * ���ܣ�����ObjectCategory��� categoryName
	 * @return String
	 * @throws com.sino.base.exception.QueryException
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
                Logger.logError(e);
                throw new QueryException();
            }
        }
        return categoryName;
    }

    /**
	 * ���ܣ������ʲ��ص��(AMS)��ETS_OBJECT�����ݡ�
	 * @return boolean
	 */
	public void createData() throws DataHandleException {
//		CostEasyDTO dtoPara = (CostEasyDTO) dtoParameter;
	    super.createData();
	getMessage().addParameterValue("��ֵ�׺���Ϣ");
//		return operateResult;
	}

    /**
	 * ���ܣ�ִ�е���ʧЧ������
	 * @return boolean
	 */
	public void deleteData() throws DataHandleException{
		 super.deleteData();
		getMessage().addParameterValue("��ֵ�׺���Ϣ");
	}

    /**
	 * ���ܣ������ʲ��ص��(AMS)��ETS_OBJECT�����ݡ�
	 * @return boolean
	 */
	public void updateData() throws DataHandleException{
//		CostEasyDTO dtoPara = (CostEasyDTO) dtoParameter;
		 super.updateData();
        getMessage().addParameterValue("��ֵ�׺���Ϣ");
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
            String fileName = "��ֵ�׺�Ʒ����.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);
            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("BARCODE", "��ֵ�׺�Ʒ��ǩ��");
            fieldMap.put("ITEM_NAME", "��ֵ�׺�Ʒ����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
            fieldMap.put("ITEM_CATE_GORY_DESC", "�豸����");
            fieldMap.put("ITEM_QTY", "����");
            fieldMap.put("WORKORDER_OBJECT_NAME", "�ص�����");

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle("��ֵ�׺�Ʒ����");
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


  public String insertData() throws SQLException, DataHandleException, QueryException {
        String msg = "";
        try {
            conn.setAutoCommit(false);
            CostEasyDTO dto = (CostEasyDTO) dtoParameter;
            CostEasyModel model = new CostEasyModel(sfUser, dto);
//            SimpleQuery sq = new SimpleQuery(model.selectItemInfo(), conn);//��� ��ETS_SYSTEM_ITEM ��Ψһ��
//            sq.executeQuery();
//            if (sq.getSearchResult().getSize() > 0) {
//                DBOperator.updateRecord(model.updateModel(), conn);//����м��Ա� ETS_SYSTEM_ITEM ִ���޸Ĳ���
//            } else {
//                SeqProducer seq = new SeqProducer(conn);
//                String itemCode = seq.getStrNextSeq("ETS_SYSTEM_ITEM_S");
//                dto.setItemCode(itemCode);
//                DBOperator.updateRecord(model.insertIntoItem(), conn);//�Ա� ETS_SYSTEM_ITEM ִ�����Ӳ���
//                SimpleQuery sq1 = new SimpleQuery(model.selectDis(itemCode), conn);//�Ա�ETS_SYSITEM_DISTRIBUTE�����ж�
//                sq1.executeQuery();
//                if (sq1.getSearchResult().getSize() > 0) {
//                } else {
//                    DBOperator.updateRecord(model.insertIntoDis(itemCode), conn);//������Ϣ����ETS_SYSITEM_DISTRIBUTE
//                }
//            }
            createData();//ETS_ITEM_INFO���������Ĳ���
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            Logger.logError(e);
            throw e;
        }
        catch (DataHandleException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                Logger.logError(e1);
            }
            Logger.logError(e);
            throw e;
        }
        return msg;
    }
}
