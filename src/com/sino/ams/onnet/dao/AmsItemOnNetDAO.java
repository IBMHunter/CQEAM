package com.sino.ams.onnet.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.onnet.dto.AmsItemOnNetDTO;
import com.sino.ams.onnet.model.AmsItemOnNetModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.data.RowSet;
import com.sino.base.data.Row;
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
import com.sino.base.util.StrUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-12-02
 * Time: 00:00:00
 * To change this template use File | Settings | File Templates.
 */

public class AmsItemOnNetDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	public AmsItemOnNetDAO(SfUserDTO userAccount, AmsItemOnNetDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		AmsItemOnNetDTO dtoPara = (AmsItemOnNetDTO)dtoParameter;
		super.sqlProducer = new AmsItemOnNetModel((SfUserDTO)userAccount, dtoPara);
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

              String fileName = "�豸��������.xls";
              String filePath = WorldConstant.USER_HOME;
              filePath += WorldConstant.FILE_SEPARATOR;
              filePath += fileName;
              rule.setTarFile(filePath);

              DataRange range = new DataRange();
              rule.setDataRange(range);

              Map fieldMap = new HashMap();
              fieldMap.put("ORGNIZATION_NAME", "��˾");
              fieldMap.put("PART_NO", "������");
              fieldMap.put("ITEM_NAME", "�豸����");
              fieldMap.put("ITEM_SPEC", "����ͺ�");
              fieldMap.put("","");
              fieldMap.put("VENDOR_NAME", "����");
              fieldMap.put("QUANTITY", "����");

              rule.setFieldMap(fieldMap);

              CustomTransData custData = new CustomTransData();
              custData.setReportTitle("�豸��������");
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

    /**
     * ���ܣ����ӿڱ����ݵ���Ч�ԡ�
     */
    public void doVerifyData(DTOSet dtoSet) throws SQLModelException, QueryException {
        if (dtoSet != null && dtoSet.getSize() > 0) {
            for (int i = 0; i < dtoSet.getSize(); i++) {
                AmsItemOnNetDTO onNetDTO = (AmsItemOnNetDTO) dtoSet.getDTO(i);
               if (!efficientData(onNetDTO.getPartNo())) {
                    insertErrorData(onNetDTO.getPartNo(), "�����ŷ��಻����", "", "");
                } else if(doubleModel(onNetDTO.getPartNo(),onNetDTO.getOrganizationId())){
                   insertErrorData(onNetDTO.getPartNo(), "���벿���������ظ�", "", "");
                }
                if (!StrUtil.isNumber(StrUtil.nullToString(onNetDTO.getQuantity()))) {
                    insertErrorData(onNetDTO.getPartNo(), "", "", "��������������");
                }
                if (!StrUtil.isNumber(StrUtil.nullToString(onNetDTO.getOrganizationId()))) {
                    insertErrorData(onNetDTO.getPartNo(), "", "OU������", "");
                }else if(!hasOrgIdModel(onNetDTO.getOrganizationId())){
                    insertErrorData(onNetDTO.getPartNo(), "", "OU������", "");
                }
            }
        }
    }

    /**
     * ���ܣ�����ӿڱ������Ϣ��
     * @throws SQLModelException
     */
     public void insertErrorData(String barcode,String partError,String ouError,String qtyError) throws SQLModelException {
        try {
            AmsItemOnNetModel onNetModel = (AmsItemOnNetModel) sqlProducer;
            if(!StrUtil.isEmpty(partError)){
            SQLModel sqlModel = onNetModel.insertPartErrorModel( barcode, partError);
             DBOperator.updateRecord(sqlModel, conn);
            }
            if(!StrUtil.isEmpty(ouError)){
              SQLModel sqlModel = onNetModel.insertOUErrorModel( barcode, ouError);
              DBOperator.updateRecord(sqlModel, conn);
            }
            if(!StrUtil.isEmpty(qtyError)){
              SQLModel sqlModel = onNetModel.insertQtyErrorModel( barcode, qtyError);
              DBOperator.updateRecord(sqlModel, conn);
            }

        } catch (DataHandleException ex) {
            ex.printLog();
        }
    }


     /**
     * ���ܣ�У����ets_SPARE_CATEGORY�Ƿ����barcode ture��ʾ����ڣ�false��ʾ�����ڡ�
     * @throws SQLModelException
     */
     public boolean efficientData(String barcode) throws SQLModelException, QueryException {
        boolean hasBarcode=true;
            AmsItemOnNetModel onNetModel = (AmsItemOnNetModel) sqlProducer;
            SQLModel sqlModel = onNetModel.noBarModel(barcode);
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if(!simpleQuery.hasResult()){
               hasBarcode = false;
            }
        return hasBarcode;
    }

     /**
     * ���ܣ�У����ets_SPARE_CATEGORY�Ƿ����error,ture ��ʾ�����д�false ��ʾ�����޴�
     * @throws SQLModelException
     */
     public boolean importHasError() throws SQLModelException, QueryException {
        boolean hasError=false;
            AmsItemOnNetModel onNetModel = (AmsItemOnNetModel) sqlProducer;
            SQLModel sqlModel = onNetModel.hasErrorModel();
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if(simpleQuery.hasResult()){
               hasError = true;
            }
        return hasError;
    }

    /**
     * ���ܣ�У�鵼���org_id�Ƿ����,ture ��ʾ������ȷ(����ou)��false ��ʾ�����д�
     * @throws SQLModelException
     */
     public boolean hasOrgIdModel(int organizationId) throws SQLModelException, QueryException {
        boolean hasError=false;
            AmsItemOnNetModel onNetModel = (AmsItemOnNetModel) sqlProducer;
            SQLModel sqlModel = onNetModel.hasOrgIdModel(organizationId);
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if(simpleQuery.hasResult()){
               hasError = true;
            }
        return hasError;
    }


   /**
    * ���ܣ��� AMS_ON_NET_IMPORT �Ĳ�ѯsql��
    * @throws SQLModelException
    */
    public RowSet getQueryImportModel() throws SQLModelException, QueryException {
       RowSet rows = new RowSet();
           AmsItemOnNetModel onNetModel = (AmsItemOnNetModel) sqlProducer;
           SQLModel sqlModel = onNetModel.getQueryImportModel();
           SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
           simpleQuery.executeQuery();
           if(simpleQuery.hasResult()){
              rows = simpleQuery.getSearchResult();
           }
       return rows;
   }

   /**
     * ���ܣ��������ݴӽӿڱ���ʽ��
     * @throws SQLModelException
     */
     public void getImpOnNetModel() throws SQLModelException, QueryException, DataHandleException {
            AmsItemOnNetModel onNetModel = (AmsItemOnNetModel) sqlProducer;
            SQLModel sqlModel = onNetModel.getImpOnNetModel();
            DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ�ɾ���ӿڱ�����ݡ�
     * @throws SQLModelException
     */
     public void deleteImportModel() throws SQLModelException, QueryException, DataHandleException {
            AmsItemOnNetModel onNetModel = (AmsItemOnNetModel) sqlProducer;
            SQLModel sqlModel = onNetModel.deleteImportModel();
            DBOperator.updateRecord(sqlModel, conn);
    }


    /**
     * ���ܣ�У����ets_SPARE_CATEGORY�Ƿ����error,ture ��ʾ�����д�false ��ʾ�����޴�
     * @throws SQLModelException
     */
     public boolean doubleModel(String barcode,int orgsId) throws SQLModelException, QueryException {
        boolean hasError=false;
            AmsItemOnNetModel onNetModel = (AmsItemOnNetModel) sqlProducer;
            SQLModel sqlModel = onNetModel.doubleModel(barcode,orgsId);
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if(simpleQuery.hasResult()){
               hasError = true;
            }
        return hasError;
    }

    /**
     * @throws QueryException
     */
   public DTOSet getImport() throws QueryException, SQLModelException {
        AmsItemOnNetModel onNetModel = (AmsItemOnNetModel) sqlProducer;
        SimpleQuery sq = new SimpleQuery(onNetModel.getQueryImportModel(), conn);
        sq.setDTOClassName(AmsItemOnNetDTO.class.getName());
        sq.executeQuery();
        return sq.getDTOSet();
    }
   /**
    * ͳ������
    * @throws ContainerException 
    */
   public String getCount() throws SQLModelException, QueryException, ContainerException {
       String count = "0";
       AmsItemOnNetModel onNetModel = (AmsItemOnNetModel) sqlProducer;
       SQLModel sqlModel = onNetModel.getCount();
       SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
       simpleQuery.executeQuery();
       RowSet rs = simpleQuery.getSearchResult();
       if(simpleQuery.hasResult()){
          Row row = rs.getRow(0);
          count = StrUtil.nullToString(row.getValue("TATOL_COUNT"));
       }
       return count;
   } 
   
}