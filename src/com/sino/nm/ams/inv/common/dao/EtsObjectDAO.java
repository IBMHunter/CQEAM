package com.sino.nm.ams.inv.common.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.nm.ams.inv.common.model.EtsObjectModel;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.specialty.model.OtLocsVindicateModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: EtsObjectDAO</p>
 * <p>Description:�ֿ�ص�����</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yushibo
 * @version 1.0
 */
public class EtsObjectDAO extends AMSBaseDAO {

	EtsObjectModel etsObjectModel = null;
	
	/**
     * ���ܣ��ʲ��ص��(AMS) ETS_OBJECT ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsObjectDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
	public EtsObjectDAO(SfUserDTO userAccount, EtsObjectDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		etsObjectModel = (EtsObjectModel) sqlProducer;
	}

	/**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		super.sqlProducer = new EtsObjectModel((SfUserDTO) userAccount, (EtsObjectDTO) dtoParameter);
	}

	/**
     * ���ܣ�����ص��(AMS)�� AMS_OBJECT_ADDRESS�����ݡ�
     */
    public void createData() throws DataHandleException {
        boolean hasError = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
        	
            super.createData();
            SQLModel sqlModel = etsObjectModel.getAddressCreateModel();
            DBOperator.updateRecord(sqlModel, conn);
            conn.commit();
            hasError = false;
            prodMessage(MsgKeyConstant.CREATE_DATA_SUCCESS);
            getMessage().addParameterValue("�ֿ�");
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } finally {
            try {
                if (hasError) {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
                throw new DataHandleException(ex1);
            }
        }
        getMessage().addParameterValue("AMS����ص��");
    }
    
    /**
     * ���ܣ�����ֿ��(AMS)�� ETS_OBJECT�����ݡ�
     */
    public void createEoData(String countyCodeMis) throws DataHandleException {
        boolean hasError = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            super.createData();
            SeqProducer seqProducer = new SeqProducer(conn);
//            String etsObjectS = seqProducer.getStrNextSeq("ETS_OBJECT_S");
            String etsObjectS = seqProducer.getGUID();
            EtsObjectDTO objDTO = (EtsObjectDTO) dtoParameter;
//        	String countyCodeMis = getCountyCodeMis();// �ɱ����Ĵ���
            objDTO.setCountyName(getName());
			objDTO.setCost_code(countyCodeMis);
			objDTO.setCityName(getCityName());
			objDTO.setAreaName(getAreaName());
			String workorderObjectLocation = objDTO.getCountyName() + "."
					+ objDTO.getCityName() + objDTO.getAreaName() + "."
					+ objDTO.getWorkorderObjectName() + "." + "000";
			objDTO.setWorkorderObjectLocation(workorderObjectLocation);
			setDTOParameter(objDTO);
            SQLModel sqlModel = etsObjectModel.getDataCreateModel(etsObjectS, countyCodeMis);
            DBOperator.updateRecord(sqlModel, conn);
            SQLModel sqlModelAddressId = etsObjectModel.getDataCreateAddressModel(etsObjectS);
            DBOperator.updateRecord(sqlModelAddressId, conn);
            conn.commit();
            hasError = false;
            prodMessage(MsgKeyConstant.CREATE_DATA_SUCCESS);
            getMessage().addParameterValue("�ֿ�");
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        } catch (QueryException e) {
			
			e.printStackTrace();
		} finally {
            try {
                if (hasError) {
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex1) {
                Logger.logError(ex1);
                throw new DataHandleException(ex1);
            }
        }
        getMessage().addParameterValue("AMS����ص��");
    }
    
    /**
     * ���ܣ������ʲ��ص��(AMS)��ETS_OBJECT������
     * @param countyCodeMis
     * @throws DataHandleException
     * @throws QueryException 
     */
    public void updateData(String countyCodeMis) throws DataHandleException, QueryException {
		boolean operateResult = false;
		SQLModel sqlModel = null;
	     EtsObjectDTO objDTO = (EtsObjectDTO) dtoParameter;
	    objDTO.setCountyName(getName());
		objDTO.setCost_code(countyCodeMis);
		objDTO.setCityName(getCityName());
		objDTO.setAreaName(getAreaName());
		String workorderObjectLocation = objDTO.getCountyName() + "."
				+ objDTO.getCityName() + objDTO.getAreaName() + "."
				+ objDTO.getWorkorderObjectName() + "." + "000";
		objDTO.setWorkorderObjectLocation(workorderObjectLocation);
		setDTOParameter(objDTO);
		sqlModel = etsObjectModel.getDataUpdateModel(countyCodeMis);
		if (sqlModel != null && !sqlModel.isEmpty()) {
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		}
	}

    /**
     * ���ܣ������ʲ��ص��(AMS)��ETS_OBJECT�����ݡ�
     */
    public void updateData() throws DataHandleException {
        EtsObjectDTO objDTO = (EtsObjectDTO) dtoParameter;
    	String countyCodeMis;
		try {
	   objDTO.setCountyName(getName());
	    countyCodeMis = getCountyCodeMis();
		objDTO.setCost_code(countyCodeMis);
		objDTO.setCityName(getCityName());
		objDTO.setAreaName(getAreaName());
		} catch (QueryException e) {
			
			e.printStackTrace();
		}
		String workorderObjectLocation = objDTO.getCountyName() + "."
				+ objDTO.getCityName() + objDTO.getAreaName() + "."
				+ objDTO.getWorkorderObjectName() + "." + "000";
		objDTO.setWorkorderObjectLocation(workorderObjectLocation);
		setDTOParameter(objDTO);
        super.updateData();
        getMessage().addParameterValue("�ʲ��ֿ��");

    }

    private void disabledData(String[] workorderObjectCodes) throws DataHandleException { //ִ������ʧЧ����
        SQLModel sqlModel = etsObjectModel.getDisabledModel(workorderObjectCodes);
        DBOperator.updateRecord(sqlModel, conn);
    }

    public void efficientData(String[] workorderObjectCodes) throws DataHandleException { //ִ��������ЧЧ����
        SQLModel sqlModel = etsObjectModel.getEfficientModel(workorderObjectCodes);
        DBOperator.updateRecord(sqlModel, conn);
        message.setMessageValue("�ֿ���Ч�ɹ���");
    }

    public void inureData() throws DataHandleException { //ִ����Ч����
        SQLModel sqlModel = etsObjectModel.getInureModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    public void deleteData() throws DataHandleException { //ִ��ʧЧ����
        super.deleteData();
        getMessage().addParameterValue("�ʲ��ص�");
    }

    public String checkCategory(String objCategory) throws QueryException, ContainerException { //���ͬ��֯���Ƿ�����ͬ���Ͳֿ�
        SQLModel sqlModel = etsObjectModel.getTypeObjHasBeenModel(objCategory);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        if (!sq.hasResult()) {
            return "";
        } else {
            Row row = sq.getFirstRow();
            return row.getStrValue(0);
        }
    }

    public boolean checkCode(String objCode) throws QueryException { //���ͬ��֯���Ƿ�����ͬ���Ͳֿ�
        SQLModel sqlModel = etsObjectModel.getCodeHasBeenModel(objCode);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.hasResult();
    }
    
    public boolean checkFields(String objectCategory, String businessCategory) throws QueryException { //������ҳ����һ����˾ֻ������һ�������Ǳ�������
        SQLModel sqlModel = etsObjectModel.getFieldsHasBeenModel(objectCategory, businessCategory);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.hasResult();
    }
    
    public boolean checkFields(String workorderObjectCode, String objectCategory, String businessCategory) throws QueryException { //���޸�ҳ����һ����˾ֻ������һ�������Ǳ�������
        SQLModel sqlModel = etsObjectModel.getFieldsHasBeenModel(workorderObjectCode, objectCategory, businessCategory);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.hasResult();
    }

    /**
     * ʧЧʱ���жϸòֿ��Ƿ����豸
     * @param workorderObjectCode �ֿ����
     * @return haveItems
     */
    private boolean isInvHaveItems(String workorderObjectCode) throws QueryException {
        SimpleQuery sq = new SimpleQuery(etsObjectModel.getItemsByObjectModel(workorderObjectCode), conn);
        sq.executeQuery();
        return sq.hasResult();
    }

    /**
     * ʧЧ
     * @param workorderObjectCodes ѡ�еĲֿ����
     * @throws DataHandleException
     * @throws QueryException
     */
    public void do_disable(String[] workorderObjectCodes) throws DataHandleException, QueryException {
        if (workorderObjectCodes != null) {
            boolean haveItems = false;
            String workorderObjectCode = "";
            for (int i = 0; i < workorderObjectCodes.length; i++) {
                workorderObjectCode = workorderObjectCodes[i];
                if (workorderObjectCode.indexOf("BJ") > -1) haveItems = isInvHaveItems(workorderObjectCode);
                if (haveItems) {
                    break;
                }
            }
            //�жϸòֿ��Ƿ����豸
            if (haveItems) {
                message.setMessageValue("��ѡ������вֿ�[" + workorderObjectCode + "]�»����豸���޷�ʧЧ�òֿ⣬������ѡ��");
                message.setIsError(true);
            } else {
                disabledData(workorderObjectCodes);
                message.setMessageValue("�ֿ�ʧЧ�ɹ���");
            }
        }
    }
    /**
	 * ���ܣ�����countyCode��ȡcountyCodeMis��ֵ
	 * 
	 * @return SQLModel
	 */
	private String getCountyCodeMis() throws QueryException {
		String countyCodeMis = "";
		EtsObjectDTO etsObjectDTO = (EtsObjectDTO) dtoParameter;
		EtsObjectModel etsObjectModel = new EtsObjectModel(userAccount,
				etsObjectDTO);
		String countyCode = etsObjectDTO.getCountyCode();
		SQLModel sqlModel = etsObjectModel.getCountyCodeMis(countyCode);
		SimpleQuery sqlQuery = new SimpleQuery(sqlModel, conn);
		sqlQuery.executeQuery();
		if (sqlQuery.hasResult()) {
			RowSet row = sqlQuery.getSearchResult();
			try {
				countyCodeMis = (String) row.getRow(0).getValue(
						"COUNTY_CODE_MIS");
			} catch (ContainerException e) {
				e.printStackTrace();
				throw new QueryException();
			}
		}
		return countyCodeMis;
	}
	
	/**
	 * ���ܣ���ȡcity_name��ֵ
	 * 
	 * @return SQLModel
	 */
	private String getCityName() throws QueryException {
		String countName = "";
		EtsObjectDTO objDTO = (EtsObjectDTO) dtoParameter;
		EtsObjectModel etsObjectModel = (EtsObjectModel) sqlProducer;
		SQLModel sqlModel = etsObjectModel.getCityNameModel(objDTO.getCityId());
		SimpleQuery sqlQuery = new SimpleQuery(sqlModel, conn);
		sqlQuery.executeQuery();
		if (sqlQuery.hasResult()) {
			RowSet row = sqlQuery.getSearchResult();
			try {
				countName = (String) row.getRow(0).getValue("CITY_NAME");
			} catch (ContainerException e) {
				e.printStackTrace();
				throw new QueryException();
			}
		}
		return countName;
	}
	/**
	 * ���ܣ���ȡarea_name��ֵ
	 * 
	 * @return SQLModel
	 */

	private String getAreaName() throws QueryException {
		String countName = "";
		EtsObjectDTO objDTO = (EtsObjectDTO) dtoParameter;
		EtsObjectModel etsObjectModel = (EtsObjectModel) sqlProducer;
		SQLModel sqlModel = etsObjectModel.getAreaNameModel(objDTO.getAreaId());
		SimpleQuery sqlQuery = new SimpleQuery(sqlModel, conn);
		sqlQuery.executeQuery();
		if (sqlQuery.hasResult()) {
			RowSet row = sqlQuery.getSearchResult();
			try {
				countName = (String) row.getRow(0).getValue("AREA_NAME");
			} catch (ContainerException e) {
				e.printStackTrace();
				throw new QueryException();
			}
		}
		return countName;
	}
	   private String getName()throws QueryException{
		      String countName="";
		         EtsObjectDTO objDTO = (EtsObjectDTO)dtoParameter;
		         EtsObjectModel etsObjectModel = (EtsObjectModel) sqlProducer;
		            SQLModel sqlModel = etsObjectModel.getNameModel(objDTO.getCountyCode());
		              SimpleQuery sqlQuery = new SimpleQuery(sqlModel, conn);
		        sqlQuery.executeQuery();
		        if (sqlQuery.hasResult()) {
		            RowSet row = sqlQuery.getSearchResult();
		            try {
		            	countName = (String) row.getRow(0).getValue("COUNTY_NAME");
		            } catch (ContainerException e) {
		                e.printStackTrace();
		                throw new QueryException();
		            }
		        }
		       return countName;
		   }
}
