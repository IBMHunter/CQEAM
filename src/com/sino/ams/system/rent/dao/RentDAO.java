package com.sino.ams.system.rent.dao;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.rent.dto.RentDTO;
import com.sino.ams.system.rent.model.RentModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
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
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsHouseInfoDAO</p>
 * <p>Description:�����Զ����ɷ������AmsHouseInfoDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author Zyun
 * @version 1.0
 */


public class RentDAO extends AMSBaseDAO {

    private SfUserDTO sfUser = null;
    

    /**
     * ���ܣ����޷���(EAM) AMS_HOUSE_INFO ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsHouseInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public RentDAO(SfUserDTO userAccount, RentDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        RentDTO dtoPara = (RentDTO) dtoParameter;
        super.sqlProducer = new RentModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ��������޷���(EAM)��AMS_HOUSE_INFO�����ݡ�
     *
     * @return boolean
     */
    public void createData() throws DataHandleException {          //do_save����
//        boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            RentDTO dtopara = (RentDTO) dtoParameter;
            RentModel sqlModel = new RentModel((SfUserDTO) userAccount, dtopara);
//            updateEIIData();                              //�Ա�ETS_ITEM_INFO�����޸Ĳ��� 1��EII.ATTRIBUTE1 = 'RENT'

//             DBOperator.updateRecord(sqlModel.insertDataNo(),conn);  //�Ա�   eii ������������
//            super.createData();
            DBOperator.updateRecord(sqlModel.insertAmsRentInfoHistory(),conn) ;
            if(!dtopara.getHistoryId().equals("")){
            	DBOperator.updateRecord(sqlModel.disabledAmsRentInfoHistory(),conn) ;
            }
            if(!dtopara.getRentId().equals("")){
            	DBOperator.updateRecord(sqlModel.updateAmsRentInfo(),conn) ;
            } else {
            	super.createData();
            }
            DBOperator.updateRecord(sqlModel.getUpdteEII(), conn);
            //�Ա�   AMS_RENT_INFO ������������
//            operateResult = true;
            conn.commit();
            hasError = false;
            prodMessage(MsgKeyConstant.UPDATE_DATA_SUCCESS);
          	getMessage().addParameterValue("�����ʲ�");
            
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        }catch(SQLModelException e){
              Logger.logError(e);
              prodMessage(MsgKeyConstant.SQL_ERROR);
    } catch (CalendarException e) {
			e.printStackTrace();
		} finally {
            if (hasError) {
                try {
                    conn.rollback();
                    conn.setAutoCommit(autoCommit);
                } catch (SQLException ex) {
                    Logger.logError(ex);
                    prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
                }
            }
        }
//        return operateResult;
    }

    /**
     * ���ܣ��������޷���(EAM)��AMS_HOUSE_INFO�����ݡ�
     *
     * @return boolean
     */
    public void updateData() throws DataHandleException {                     //�޸�����
        RentDTO dtopara = (RentDTO) dtoParameter;
        super.updateData();
        prodMessage(MsgKeyConstant.UPDATE_DATA_SUCCESS);
        getMessage().addParameterValue("�����ʲ�");
    }


    //�޸����޵��������
    public void updateDatT(Connection conn) throws SQLException, DataHandleException, SQLModelException {
        try {
            conn.setAutoCommit(false);
            RentDTO rentDTO = (RentDTO) dtoParameter;
            RentModel model = new RentModel(sfUser, rentDTO);
            DBOperator.updateRecord(model.getUpdteEII(), conn);
            DBOperator.updateRecord(model.getDataUpdateModel(), conn);
            if(!rentDTO.getHistoryId().equals("")){
            	DBOperator.updateRecord(model.updateAmsRentInfoHistoryRemark(), conn);
            }else {
            	DBOperator.updateRecord(model.insertAmsRentInfoHistory(), conn);
            }
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
        } catch (CalendarException e) {
			e.printStackTrace();
		}
    }


    public void updateEIIData() throws DataHandleException {
        RentModel rentModel = (RentModel)sqlProducer;
        SQLModel sqlModel = rentModel.getupdataEIIModel();
        DBOperator.updateRecord(sqlModel,conn);
    }

      public File exportFile() throws DataTransException {
        File file = null;
        RentDTO rentDTO = (RentDTO) dtoParameter;
        try {
        	SQLModel sqlModel = null;
        	 String fileName = "";
             if("AMS_RENT_INFO_HISTORY".equals(rentDTO.getAccessType())){
             	fileName = "�����ʲ���ʷ��Ϣ";
             	sqlModel = sqlProducer.getDataByForeignKeyModel("barcode");
             }else{
             	fileName = "��Ӫ���޵����ʲ�";
             	sqlModel = sqlProducer.getPageQueryModel();
             }
             
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);
            
           
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath + ".xls");

            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();

            if("AMS_RENT_INFO_HISTORY".equals(rentDTO.getAccessType())){
	            fieldMap.put("BARCODE", "�����ʲ���ǩ��");
	            fieldMap.put("ITEM_NAME", "�ʲ�����");
	            fieldMap.put("ITEM_SPEC", "����ͺ�");
	            
	            fieldMap.put("CONTENT_NAME", "�ʲ��������");
	            fieldMap.put("RENT_DATE", "��ʼ����");
	            fieldMap.put("END_DATE", "����ʱ��");
	            fieldMap.put("RENT_PERSON", "���ⷽ");
	            
	            fieldMap.put("TENANCY", "����(��)");
	            fieldMap.put("RENTAL", "�����");
	            fieldMap.put("YEAR_RENTAL", "�����(Ԫ)");
	            fieldMap.put("MONTH_REANTAL", "�����");
	            fieldMap.put("REMARK", "��ע");
            }else {
	            fieldMap.put("ROWNUM", "���");
	            fieldMap.put("COMPANY", "��˾OU");
	            fieldMap.put("BARCODE", "�����ʲ���ǩ��");
	            fieldMap.put("ITEM_NAME", "�ʲ�����");
	            fieldMap.put("ITEM_SPEC", "����ͺ�");
	            fieldMap.put("ITEM_UNIT", "��λ");
	            
	            fieldMap.put("MANUFACTURER_NAME", "������������");
	            fieldMap.put("POWER", "�����");
	            fieldMap.put("OTHER_INFO", "�豸����");
	            fieldMap.put("CONTENT_CODE", "�ʲ����������");
	            fieldMap.put("CONTENT_NAME", "�ʲ��������");
	            fieldMap.put("RESPONSIBILITY_USER", "�����˱��");
	            
	            fieldMap.put("USER_NAME", "����������");
	            fieldMap.put("OBJECT_NAME", "�ʲ��ص�");
	            fieldMap.put("MAINTAIN_USER", "ʹ����");
	            fieldMap.put("MAINTAIN_DEPT", "ʹ�ò���");
	            fieldMap.put("RENT_DATE", "��ʼ����");
	            fieldMap.put("END_DATE", "����ʱ��");
	            fieldMap.put("RENT_PERSON", "���ⷽ");
	            
	            fieldMap.put("TENANCY", "����(��)");
	            fieldMap.put("YEAR_RENTAL", "�����(Ԫ)");
	            fieldMap.put("MONTH_REANTAL", "�����");	            
	            fieldMap.put("MONTH_REANTAL", "�����");
//	            fieldMap.put("LAST_YEAR_RATE", "��ȥ��ͬ��������");
            }
            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(fileName);
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
      
      public RowSet getRentAssetsByReport() throws SQLModelException, QueryException{
    	  RentModel rentModel = (RentModel)sqlProducer;
          SQLModel sqlModel = rentModel.getPageQueryModel();
	      SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
	      simpleQuery.executeQuery();
	      return simpleQuery.getSearchResult();
      }
}
