package com.sino.ams.system.place.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.*;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.system.place.dto.PlaceInfoDTO;
import com.sino.ams.system.place.model.PlaceInfoModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class PlaceInfoDAO extends AMSBaseDAO {
	public PlaceInfoDAO(BaseUserDTO userAccount, PlaceInfoDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		commonObjectModel = new PlaceInfoModel((SfUserDTO) userAccount, dtoParameter);
	}
	

	private PlaceInfoModel commonObjectModel = null;
	SfUserDTO user=null;

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		PlaceInfoDTO dto = (PlaceInfoDTO)dtoParameter;
		sqlProducer = new PlaceInfoModel(user, dto);
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		try {
			PlaceInfoModel modelProducer = (PlaceInfoModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "�ص�����";
			String fileName = reportTitle + ".xls";
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
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
		} catch (DataTransException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		}
		return file;
	}

//	public void insertToData(String[] workorderObjectNos) { //ִ��ͬ����TDEAM
//        EtsObjectDTO dtoPara = (EtsObjectDTO) dtoParameter;
//        try {
//        	PlaceInfoModel etsObjectModel = (PlaceInfoModel) sqlProducer;
//            SQLModel sqlModel = etsObjectModel.getInsertToModel(workorderObjectNos);
//            DBOperator.updateRecord(sqlModel, conn);
//            if (userAccount.getIsTd().equals("Y")) {
//                syntoEAMLocus();     //TDEAMͬ����EAM
//            } else {
//                syntoTDLocus();          //EAMͬ����TDEAM
//            }
//
//
//            prodMessage(CustMessageKey.ENABLE_SUCCESS);
//            getMessage().addParameterValue(dtoPara.getCategoryName());
//        } catch (DataHandleException ex) {
//            prodMessage(CustMessageKey.ENABLE_FAILURE);
//            getMessage().addParameterValue(dtoPara.getCategoryName());
//            ex.printLog();
//        }
//    }
	
	public void syntoTDLocus() {
        CallableStatement cs = null;
        String callStr = "{CALL dbo.ESOP_SYN_LOCATION_TOTD ?}";
        try {
            cs = conn.prepareCall(callStr);
            cs.setInt(1, userAccount.getOrganizationId());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeDBStatement(cs);
        }
    }

    public void syntoEAMLocus() {
        CallableStatement cs = null;
        String callStr = "{CALL dbo.ESOP_SYN_LOCATION_TOEAM ?}";
        try {
            cs = conn.prepareCall(callStr);
            cs.setInt(1, userAccount.getOrganizationId());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBManager.closeDBStatement(cs);
        }
    }
//    public String getNextWorkorderObjectCode(String provinceCode) throws QueryException {
//        String maxObjectCode = "";
//        try {
//        	PlaceInfoModel modelProducer = (PlaceInfoModel) sqlProducer;
//            SQLModel sqlModel = modelProducer.getNextObjectCodeModel(provinceCode);
//            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
//            simpleQuery.executeQuery();
//            if (simpleQuery.hasResult()) {
//                Row row = simpleQuery.getFirstRow();
//                maxObjectCode = row.getStrValue("OBJECT_CODE");
//            }
//        } catch (ContainerException ex) {
//            ex.printLog();
//            throw new QueryException(ex);
//        }
//        return maxObjectCode;
//    }
    
	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("COMPANY", "��˾����");
		fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
		fieldMap.put("WORKORDER_OBJECT_NAME", "�ص�����");
		fieldMap.put("COUNTY_NAME", "��������");
		fieldMap.put("OBJECT_CATEGORY_NAME", "�ص�רҵ");
		fieldMap.put("IS_TD", "�Ƿ�TD�ص�");
		fieldMap.put("CREATION_USER", "������");
		fieldMap.put("CREATION_DATE", "��������");
		fieldMap.put("DISABLE_DATE", "ʧЧ����");
		fieldMap.put("UPDATED_USER", "�ϴθ�����");
		fieldMap.put("VALUE", "��������");
		return fieldMap;
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.WebFileDownException
	 */
	public File getExportFile2() throws WebFileDownException {
		File file = null;
		try {
			PlaceInfoModel modelProducer = (PlaceInfoModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "�ص�ǩͳ�Ʊ���";
			String fileName = reportTitle + ".xls";
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			Map fieldMap= getFieldMap2();
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
		} catch (DataTransException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		}
		return file;
	}
	

	private Map getFieldMap2(){
		Map fieldMap = new HashMap();
		
		 fieldMap.put("WORKORDER_OBJECT_NO", "���");
         fieldMap.put("WORKORDER_OBJECT_CODE", "��ַ����");
         fieldMap.put("WORKORDER_OBJECT_NAME", "�ص�����");
         fieldMap.put("B", "��������");
         fieldMap.put("C", "�ص����");
         fieldMap.put("D", "��������");
         fieldMap.put("E", "��������");
         fieldMap.put("F", "�ص�����");
         fieldMap.put("COMPANY", "��˾����");
		return fieldMap;
	}
	/**
	 * ����:���ص��Ƿ����
	 * @return boolean
	 */
//	public boolean existObject(){
//		boolean exist = false;
//		try {
//			PlaceInfoModel modelProducer = (PlaceInfoModel) sqlProducer;
//			SQLModel sqlModel = modelProducer.getObjectEsistModel();
//			SimpleQuery simQuery = new SimpleQuery(sqlModel, conn);
//			simQuery.executeQuery();
//			exist = simQuery.hasResult();
//		} catch (QueryException ex) {
//			ex.printLog();
//		}
//		return exist;
//	}
	
	/**
	 * ����:����վ��Ӫҵ������Ƿ����
	 * @return boolean
	 */
//	public boolean existBtsNo(){
//		boolean exist = false;
//		try {
//			PlaceInfoModel modelProducer = (PlaceInfoModel) sqlProducer;
//			SQLModel sqlModel = modelProducer.getBtsNoEsistModel();
//			SimpleQuery simQuery = new SimpleQuery(sqlModel, conn);
//			simQuery.executeQuery();
//			exist = simQuery.hasResult();
//		} catch (QueryException ex) {
//			ex.printLog();
//		}
//		return exist;
//	}
	/**
	 * ����:����ص�
	 * @return boolean
	 */
	public boolean saveObject() {
		boolean operateResult = false;
		PlaceInfoDTO dto = (PlaceInfoDTO) dtoParameter;
		int objectNo = dto.getFlexValueId();
		try {
//			if (objectNo.equals("")) {
//				createObject();
//			} else {
				super.updateData();
//			}
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			if(operateResult){
					prodMessage(CustMessageKey.OBJECT_UPDATE_SUCCESS);
			} else {
					prodMessage(CustMessageKey.OBJECT_UPDATE_FAILURE);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ������ص�
	 * @throws DataHandleException
	 */
//	private void createObject() throws DataHandleException{
//		boolean operateResult = false;
//		boolean autoCommit = true;
//		EtsObjectDTO dto = (EtsObjectDTO) dtoParameter;
//		try {
//			autoCommit = conn.getAutoCommit();
//			conn.setAutoCommit(false);
//			SeqProducer seqProducer = new SeqProducer(conn);
//			String objectNo = seqProducer.getGUID();
//			dto.setRemark("�ص�ͳһά�����򴴽�");
//			dto.setWorkorderObjectNo(objectNo);
//			setDTOParameter(dto);
//			createData();
//			AmsObjectAddressDTO dtoAddress = new AmsObjectAddressDTO();
//			dtoAddress.setObjectNo(dto.getWorkorderObjectNo());
//			dtoAddress.setBoxNo("0000");
//			dtoAddress.setNetUnit("0000");
//			dtoAddress.setAddressNo(dto.getWorkorderObjectNo() + ".0000.0000");
//			dtoAddress.setRemark("�ص�ͳһά�����򴴽�");
//			AmsObjectAddressDAO objectDAO = new AmsObjectAddressDAO(userAccount, dtoAddress, conn);
//			objectDAO.createData();
//			operateResult = true;
//		} catch (SQLException ex) {
//			Logger.logError(ex);
//		} finally{
//			try {
//				if (operateResult) {
//					conn.commit();
//				} else {
//					conn.rollback();
//					dto.setWorkorderObjectNo("");
//				}
//				conn.setAutoCommit(autoCommit);
//			} catch (SQLException ex) {
//				Logger.logError(ex);
//				throw new DataHandleException(ex);
//			}
//		}
//	}

	/**
	 * ���ܣ�ʧЧ�ص�
	 * @param objectNos String[]
	 * @return boolean
	 */
//	public boolean disableObjects(String[] objectNos){
//		boolean operateResult = false;
//		boolean autoCommit = true;
//		try {
//			if (objectNos != null && objectNos.length > 0) {
//				autoCommit = conn.getAutoCommit();
//				conn.setAutoCommit(false);
//				int objectCount = objectNos.length;
//				EtsObjectDTO dto = null;
//				CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
//				SQLModel sqlModel = null;
//				for (int i = 0; i < objectCount; i++) {
//					dto = new EtsObjectDTO();
//					dto.setWorkorderObjectNo(objectNos[i]);
//					setDTOParameter(dto);
//					sqlModel = modelProducer.getDisableModel();
//					DBOperator.updateRecord(sqlModel, conn);
//				}
//				operateResult = true;
//			} else {
//				operateResult = true;
//			}
//		} catch (DataHandleException ex) {
//			ex.printLog();
//		} catch (SQLException ex) {
//			Logger.logError(ex);
//		} finally{
//			try {
//				if (operateResult) {
//					conn.commit();
//					prodMessage(CustMessageKey.OBJECT_DISABLE_SUCCESS);
//				} else {
//					conn.rollback();
//					prodMessage(CustMessageKey.OBJECT_DISABLE_FAILURE);
//				}
//				conn.setAutoCommit(autoCommit);
//			} catch (SQLException ex1) {
//				Logger.logError(ex1);
//			}
//		}
//		return operateResult;
//	}

//	public boolean enableObjects(String[] objectNos){
//		boolean operateResult = false;
//		boolean autoCommit = true;
//		try {
//			if (objectNos != null && objectNos.length > 0) {
//				autoCommit = conn.getAutoCommit();
//				conn.setAutoCommit(false);
//				int objectCount = objectNos.length;
//				EtsObjectDTO dto = null;
//				CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
//				SQLModel sqlModel = null;
//				for (int i = 0; i < objectCount; i++) {
//					dto = new EtsObjectDTO();
//					dto.setWorkorderObjectNo(objectNos[i]);
//					setDTOParameter(dto);
//					sqlModel = modelProducer.getEnableModel();
//					DBOperator.updateRecord(sqlModel, conn);
//				}
//				operateResult = true;
//			} else {
//				operateResult = true;
//			}
//		} catch (DataHandleException ex) {
//			ex.printLog();
//		} catch (SQLException ex) {
//			Logger.logError(ex);
//		} finally{
//			try {
//				if (operateResult) {
//					conn.commit();
//					prodMessage(CustMessageKey.OBJECT_ENABLE_SUCCESS);
//				} else {
//					conn.rollback();
//					prodMessage(CustMessageKey.OBJECT_ENABLE_FAILURE);
//				}
//				conn.setAutoCommit(autoCommit);
//			} catch (SQLException ex1) {
//				Logger.logError(ex1);
//			}
//		}
//		return operateResult;
//	}
	
//	public String getAddress(String addressName) throws QueryException, ContainerException{
//		SQLModel sqlModel = commonObjectModel.getAddress(addressName);
//        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
//        simpleQuery.executeQuery();
//        RowSet rows = simpleQuery.getSearchResult();
//        Row row = null;
//        
//        StringBuffer result = new StringBuffer();
//        if (rows != null && rows.getSize() > 0) {
//        	result.append("<div id=\"search_suggest\"><font color = '#blue'>&nbsp;&nbsp;����������ӵĵص����ƣ���ȷ���Ƿ������õص㣡</font>");
//        	for (int i = 0; i < rows.getSize(); i++) {
//        		row = rows.getRow(i);
//        		result.append("<div onmouseover=\"javascript:suggestOver(this);\" style = 'width:100%'");
//        		result.append(" onmouseout=\"javascript:suggestOut(this);\" ");
//        		result.append(" onclick=\"javascript:setSearch(this.innerHTML);\" ");
//        		result.append(" class=\"suggest_link\">" + row.getValue("WORKORDER_OBJECT_NAME") + "</div>");
//        	}
//        	result.append("<div align = 'right'><a href=''  onclick = 'do_Cancle();'><font color='blue' size='3'>�ر�</font></a></div>");
//        	result.append("</div>");
//        }
//        return result.toString();
//	}
	
//	private String getIsTD() throws QueryException {
//        String isTd = "";
//        try {
//        	PlaceInfoModel modelProducer = (PlaceInfoModel) sqlProducer;
//            SQLModel sqlModel = modelProducer.geIsTDByOrgIdModel();
//            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
//            simpleQuery.executeQuery();
//            if (simpleQuery.hasResult()) {
//                Row row = simpleQuery.getFirstRow();
//                isTd = row.getStrValue("IS_TD");
//            }
//        } catch (ContainerException ex) {
//            ex.printLog();
//            throw new QueryException(ex);
//        }
//        return isTd;
//    }
	
//	public String getOrgChangeResponse() throws QueryException{
//        String resContent = "";
//        EtsObjectDTO dto = (EtsObjectDTO)dtoParameter;
//        AssetsOptProducer optProducer = new AssetsOptProducer(userAccount, conn);
//        resContent = optProducer.getAreaOptions(dto.getOrganizationId(), "");
//        String isTd = getIsTD();
//        if(isTd.equals("")){
//            isTd = "EMPTY_CONTENT";
//        }
//        resContent += "&";
//        resContent += isTd;
//        return resContent;
//    }
	
//	public String getMatchORG() throws QueryException, ContainerException {
//		PlaceInfoModel modelProducer = (PlaceInfoModel) sqlProducer;
//        SQLModel sqlModel = modelProducer.getMatchORG();
//        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
//        simpleQuery.executeQuery();
//        String matchORG = "";
//        RowSet rows = simpleQuery.getSearchResult();
//        if (rows != null && rows.getSize() > 0) {
//            matchORG = simpleQuery.getFirstRow().getStrValue("COMPANY_CODE");
//        }
//        return matchORG;
//    }
	
//	public String getLocation(String location) throws QueryException, ContainerException{
//		SQLModel sqlModel = commonObjectModel.getLocation(location);
//		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
//		simpleQuery.executeQuery();
//		RowSet rows = simpleQuery.getSearchResult();
//		Row row = null;
//		
//		StringBuffer result = new StringBuffer();
//		if (rows != null && rows.getSize() > 0) {
//			result.append("<div id=\"search_suggest\"><font color = '#blue'>&nbsp;&nbsp;����������ӵ���������ȷ���Ƿ�������������</font>");
//			for (int i = 0; i < rows.getSize(); i++) {
//				row = rows.getRow(i);
//				result.append("<div onmouseover=\"javascript:suggestOver(this);\" style = 'width:100%'");
//				result.append(" onmouseout=\"javascript:suggestOut(this);\" ");
//				result.append(" onclick=\"javascript:setSearch(this.innerHTML);\" ");
//				result.append(" class=\"suggest_link\">" + row.getValue("LOCATION") + "</div>");
//			}
//			result.append("<div align = 'right'><a href=''  onclick = 'do_Cancle();'><font color='blue' size='3'>�ر�</font></a></div>");
//			result.append("</div>");
//		}
//		return result.toString();
//	}
}
