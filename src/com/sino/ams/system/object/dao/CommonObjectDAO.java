package com.sino.ams.system.object.dao;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.object.AmsObjectAddressDTO;
import com.sino.ams.system.object.model.CommonObjectModel;
import com.sino.ams.system.user.dao.EtsOuCityMapDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
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
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class CommonObjectDAO extends AMSBaseDAO {
	public CommonObjectDAO(BaseUserDTO userAccount, EtsObjectDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		commonObjectModel = new CommonObjectModel((SfUserDTO) userAccount, dtoParameter);
	}
	

	private CommonObjectModel commonObjectModel = null;

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfUserDTO user = (SfUserDTO)userAccount;
		EtsObjectDTO dto = (EtsObjectDTO)dtoParameter;
		sqlProducer = new CommonObjectModel(user, dto);
	}

	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		try {
			CommonObjectModel modelProducer = (CommonObjectModel)sqlProducer;
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

	public void insertToData(String[] workorderObjectNos) { //ִ��ͬ����TDEAM
        EtsObjectDTO dtoPara = (EtsObjectDTO) dtoParameter;
        try {
            CommonObjectModel etsObjectModel = (CommonObjectModel) sqlProducer;
            SQLModel sqlModel = etsObjectModel.getInsertToModel(workorderObjectNos);
            DBOperator.updateRecord(sqlModel, conn);
            if (userAccount.getIsTd().equals("Y")) {
                syntoEAMLocus();     //TDEAMͬ����EAM
            } else {
                syntoTDLocus();          //EAMͬ����TDEAM
            }


            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue(dtoPara.getCategoryName());
        } catch (DataHandleException ex) {
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue(dtoPara.getCategoryName());
            ex.printLog();
        }
    }
	
	public void transferToData(String[] workorderObjectNos) { //ִ�еص㴫��
		boolean operateResult = false;
        boolean autoCommit = true;
        int count = 0;
		EtsObjectDTO dtoPara = (EtsObjectDTO) dtoParameter;
        try {
            CommonObjectModel etsObjectModel = (CommonObjectModel) sqlProducer;            
            List<SQLModel> sqlModleList=new ArrayList<SQLModel>();
            int organizationId = getMatchOrgId(userAccount.getOrganizationId());
            String isTd = "Y";
            if (userAccount.getIsTd().equals("Y")) {
            	isTd = "N";
            }
            dtoPara.setOrganizationId(organizationId);
            dtoPara.setIsTd(isTd);
            
            for (int i=0;i<workorderObjectNos.length;i++) {
            	dtoPara.setWorkorderObjectNo(workorderObjectNos[i]);
            	if (!existMatchObjectCode()) {            		
            		sqlModleList.add(etsObjectModel.getTransferToModel());
            		count++;
            	}
            }
            operateResult=DBOperator.updateBatchRecords(sqlModleList, conn);
            
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (operateResult && count!=0) {
                	prodMessage(CustMessageKey.TRANSFER_SUCCESS);
                    conn.commit();
                } else {
                	prodMessage(CustMessageKey.TRANSFER_FAILURE);
                    getMessage().addParameterValue("�����Ѵ��͹�");
                    conn.rollback();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
    }
	
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
    
    public String getNextWorkorderObjectCode(String provinceCode) throws QueryException {
        String maxObjectCode = "";
        try {
            CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getNextObjectCodeModel(provinceCode);
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                Row row = simpleQuery.getFirstRow();
                maxObjectCode = row.getStrValue("OBJECT_CODE");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return maxObjectCode;
    }
    
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
			CommonObjectModel modelProducer = (CommonObjectModel)sqlProducer;
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
	public boolean existObject(){
		boolean exist = false;
		try {
			CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getObjectEsistModel();
			SimpleQuery simQuery = new SimpleQuery(sqlModel, conn);
			simQuery.executeQuery();
			exist = simQuery.hasResult();
		} catch (QueryException ex) {
			ex.printLog();
		}
		return exist;
	}
	
	/**
	 * ����:����վ��Ӫҵ������Ƿ����
	 * @return boolean
	 */
	public boolean existBtsNo(){
		boolean exist = false;
		try {
			CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getBtsNoEsistModel();
			SimpleQuery simQuery = new SimpleQuery(sqlModel, conn);
			simQuery.executeQuery();
			exist = simQuery.hasResult();
		} catch (QueryException ex) {
			ex.printLog();
		}
		return exist;
	}
	
	/**
	 * ���ܣ����ص������Ƿ����
	 * @return
	 */
	public boolean existWorkorderObjectName(){
		boolean exist = false;
		try {
			CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getWorkorderObjectNameEsistModel();
			SimpleQuery simQuery = new SimpleQuery(sqlModel, conn);
			simQuery.executeQuery();
			exist = simQuery.hasResult();
		} catch (QueryException ex) {
			ex.printLog();
		}
		return exist;
	}

	/**
	 * ����:����ص�
	 * @return boolean
	 * @throws ContainerException 
	 * @throws QueryException 
	 * @throws SQLModelException 
	 */
	public boolean saveObject() throws SQLModelException, QueryException, ContainerException {
		boolean operateResult = false;
		EtsObjectDTO dto = (EtsObjectDTO) dtoParameter;
		String objectNo = dto.getWorkorderObjectNo();
		try {
			if (objectNo.equals("")) {
				createObject();
			} else {				
				super.updateData();
				
				//�޸�����ص�
				DBOperator.updateRecord(commonObjectModel.updateEtsObjectLocInfo(), conn);
				//�޸�ETS_OBJECT���з���Ҫ��ĵص�
				DBOperator.updateRecord(commonObjectModel.updateEtsObjectInfo(), conn);				
			}
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			if(operateResult){
				if(objectNo.equals("")){
					prodMessage(CustMessageKey.OBJECT_CREATE_SUCCESS);
				} else {
					prodMessage(CustMessageKey.OBJECT_UPDATE_SUCCESS);
				}
			} else {
				if(objectNo.equals("")){
					prodMessage(CustMessageKey.OBJECT_CREATE_FAILURE);
				} else {
					prodMessage(CustMessageKey.OBJECT_UPDATE_FAILURE);
				}
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ������ص�
	 * @throws DataHandleException
	 * @throws ContainerException 
	 * @throws QueryException 
	 * @throws SQLModelException 
	 * @throws ContainerException 
	 * @throws QueryException 
	 * @throws SQLModelException 
	 */
	private void createObject() throws DataHandleException, SQLModelException, QueryException, ContainerException{
		boolean operateResult = false;
		boolean autoCommit = true;
		EtsObjectDTO dto = (EtsObjectDTO) dtoParameter;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			SeqProducer seqProducer = new SeqProducer(conn);
			String objectNo = seqProducer.getGUID();
			dto.setRemark("�ص�ͳһά�����򴴽�");
			dto.setWorkorderObjectNo(objectNo);
			
			String countyCode = getAreaCountyCode(dto.getCountyCode());
        	if (StrUtil.isNotEmpty(countyCode)) {
        		dto.setCountyCode(countyCode);
        	}  
			
			setDTOParameter(dto);
			createData();
			
			//����������ص����¼ETS_OBJECT_LOC2��
			//if (!userAccount.getIsTt().equals("Y")) {
				if (StrUtil.isEmpty(dto.getLoc2Code()) && !getLoc2CodeIsExists(dto.getWorkorderObjectCode())) {
					CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
					EtsOuCityMapDAO etsOuCityMapDAO = new EtsOuCityMapDAO(userAccount, null, conn);
					dto.setCompanyCode(etsOuCityMapDAO.getCompanyCodeByOrgId(dto.getOrganizationId()));
					SQLModel sqlModel = modelProducer.createDoEtsObjectLoc(dto);
					DBOperator.updateRecord(sqlModel, conn);
				}
			//}
			
			AmsObjectAddressDTO dtoAddress = new AmsObjectAddressDTO();
			dtoAddress.setObjectNo(dto.getWorkorderObjectNo());
			dtoAddress.setBoxNo("0000");
			dtoAddress.setNetUnit("0000");
			dtoAddress.setAddressNo(dto.getWorkorderObjectNo() + ".0000.0000");
			dtoAddress.setRemark("�ص�ͳһά�����򴴽�");
			AmsObjectAddressDAO objectDAO = new AmsObjectAddressDAO(userAccount, dtoAddress, conn);
			objectDAO.createData();
			operateResult = true;
		} catch (SQLException ex) {
			Logger.logError(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
				} else {
					conn.rollback();
					dto.setWorkorderObjectNo("");
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
				throw new DataHandleException(ex);
			}
		}
	}

	/**
	 * ���ܣ�ʧЧ�ص�
	 * @param objectNos String[]
	 * @return boolean
	 */
	public boolean disableObjects(String[] objectNos){
		boolean operateResult = false;
		boolean autoCommit = true;
		try {
			if (objectNos != null && objectNos.length > 0) {
				autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				int objectCount = objectNos.length;
				EtsObjectDTO dto = null;
				CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
				SQLModel sqlModel = null;
				for (int i = 0; i < objectCount; i++) {
					dto = new EtsObjectDTO();
					dto.setWorkorderObjectNo(objectNos[i]);
					setDTOParameter(dto);
					sqlModel = modelProducer.getDisableModel();
					DBOperator.updateRecord(sqlModel, conn);
				}
				operateResult = true;
			} else {
				operateResult = true;
			}
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage(CustMessageKey.OBJECT_DISABLE_SUCCESS);
				} else {
					conn.rollback();
					prodMessage(CustMessageKey.OBJECT_DISABLE_FAILURE);
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
			}
		}
		return operateResult;
	}

	public boolean enableObjects(String[] objectNos){
		boolean operateResult = false;
		boolean autoCommit = true;
		try {
			if (objectNos != null && objectNos.length > 0) {
				autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				int objectCount = objectNos.length;
				EtsObjectDTO dto = null;
				CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
				SQLModel sqlModel = null;
				for (int i = 0; i < objectCount; i++) {
					dto = new EtsObjectDTO();
					dto.setWorkorderObjectNo(objectNos[i]);
					setDTOParameter(dto);
					sqlModel = modelProducer.getEnableModel();
					DBOperator.updateRecord(sqlModel, conn);
				}
				operateResult = true;
			} else {
				operateResult = true;
			}
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
					prodMessage(CustMessageKey.OBJECT_ENABLE_SUCCESS);
				} else {
					conn.rollback();
					prodMessage(CustMessageKey.OBJECT_ENABLE_FAILURE);
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
			}
		}
		return operateResult;
	}
	
	public String getAddress(String addressName) throws QueryException, ContainerException{
		SQLModel sqlModel = commonObjectModel.getAddress(addressName);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rows = simpleQuery.getSearchResult();
        Row row = null;
        
        StringBuffer result = new StringBuffer();
        if (rows != null && rows.getSize() > 0) {
        	result.append("<div id=\"search_suggest\"><font color = '#blue'>&nbsp;&nbsp;����������ӵĵص����ƣ���ȷ���Ƿ������õص㣡</font>");
        	for (int i = 0; i < rows.getSize(); i++) {
        		row = rows.getRow(i);
        		result.append("<div onmouseover=\"javascript:suggestOver(this);\" style = 'width:100%'");
        		result.append(" onmouseout=\"javascript:suggestOut(this);\" ");
        		result.append(" onclick=\"javascript:setSearch(this.innerHTML);\" ");
        		result.append(" class=\"suggest_link\">" + row.getValue("WORKORDER_OBJECT_NAME") + "</div>");
        	}
        	result.append("<div align = 'right'><a href=''  onclick = 'do_Cancle();'><font color='blue' size='3'>�ر�</font></a></div>");
        	result.append("</div>");
        }
        return result.toString();
	}
	
	private String getIsTD() throws QueryException {
        String isTd = "";
        try {
            CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
            SQLModel sqlModel = modelProducer.geIsTDByOrgIdModel();
            SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                Row row = simpleQuery.getFirstRow();
                isTd = row.getStrValue("IS_TD");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return isTd;
    }
	
	public String getOrgChangeResponse() throws QueryException{
        String resContent = "";
        EtsObjectDTO dto = (EtsObjectDTO)dtoParameter;
        AssetsOptProducer optProducer = new AssetsOptProducer(userAccount, conn);
//          if(servletConfig.getProvinceCode().equals(AssetsDictConstant.PROVINCE_CODE_NM)){
//                resContent = optProducer.getNMAreaOptions(dto.getOrganizationId(), "");
//            }else{
              resContent = optProducer.getAreaOptions(dto.getOrganizationId(), "");
//            }
        String isTd = getIsTD();
        if(isTd.equals("")){
            isTd = "EMPTY_CONTENT";
        }
        resContent += "&";
        resContent += isTd;
        return resContent;
    }
	
	public String getMatchORG() throws QueryException, ContainerException {
        CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getMatchORG();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        String matchORG = "";
        RowSet rows = simpleQuery.getSearchResult();
        if (rows != null && rows.getSize() > 0) {
            matchORG = simpleQuery.getFirstRow().getStrValue("COMPANY_CODE");
        }
        return matchORG;
    }
	
	public String getLocation(String location) throws QueryException, ContainerException{
		SQLModel sqlModel = commonObjectModel.getLocation(location);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		RowSet rows = simpleQuery.getSearchResult();
		Row row = null;
		
		StringBuffer result = new StringBuffer();
		if (rows != null && rows.getSize() > 0) {
			result.append("<div id=\"search_suggest\"><font color = '#blue'>&nbsp;&nbsp;����������ӵ���������ȷ���Ƿ�������������</font>");
			for (int i = 0; i < rows.getSize(); i++) {
				row = rows.getRow(i);
				result.append("<div onmouseover=\"javascript:suggestOver(this);\" style = 'width:100%'");
				result.append(" onmouseout=\"javascript:suggestOut(this);\" ");
				result.append(" onclick=\"javascript:setSearch(this.innerHTML);\" ");
				result.append(" class=\"suggest_link\">" + row.getValue("LOCATION") + "</div>");
			}
			result.append("<div align = 'right'><a href=''  onclick = 'do_Cancle();'><font color='blue' size='3'>�ر�</font></a></div>");
			result.append("</div>");
		}
		return result.toString();
	}
	
	/**
     * ��ȡ�������
     * @param str
     * @return
     * @throws SQLModelException
     * @throws QueryException
     * @throws ContainerException
     */
    public String getAreaCountyCode(String str) throws SQLModelException, QueryException, ContainerException {
    	CommonObjectModel model = (CommonObjectModel) sqlProducer;
        SQLModel sqlModel = model.getAreaCountyCode(str);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        String code = "";
        if (simpleQuery.hasResult()) {
            code = simpleQuery.getSearchResult().getRow(0).getStrValue("COUNTY_CODE");
        }
        return code;
    }
    
    public boolean addObject() throws DataHandleException, SQLModelException, QueryException, ContainerException{
		boolean operateResult = false;
		boolean autoCommit = true;
		EtsObjectDTO dto = (EtsObjectDTO) dtoParameter;
		EtsObjectDTO objectDTO = getEtsObjectLocData();
		
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			SeqProducer seqProducer = new SeqProducer(conn);
			String objectNo = seqProducer.getGUID();
			dto.setRemark("������ϵص㴴��");
			dto.setWorkorderObjectNo(objectNo);
			dto.setWorkorderObjectName(java.net.URLDecoder.decode(dto.getWorkorderObjectName(),"UTF-8"));
			dto.setObjCategoryOption(objectDTO.getObjectCategory());
			dto.setObjectCategoryName(objectDTO.getObjectCategoryName());
			dto.setAreaType(objectDTO.getAreaType());
			dto.setCity(objectDTO.getCity());
			dto.setCounty(objectDTO.getCounty());
			dto.setBtsNo(objectDTO.getBtsNo());
			dto.setLatitudeLongitude(objectDTO.getLatitudeLongitude());
			dto.setAuxiliaryInfo(objectDTO.getAuxiliaryInfo());
			dto.setRemark(objectDTO.getRemark());
			setDTOParameter(dto);
			createData();
			
			AmsObjectAddressDTO dtoAddress = new AmsObjectAddressDTO();
			dtoAddress.setObjectNo(dto.getWorkorderObjectNo());
			dtoAddress.setBoxNo("0000");
			dtoAddress.setNetUnit("0000");
			dtoAddress.setAddressNo(dto.getWorkorderObjectNo() + ".0000.0000");
			dtoAddress.setRemark("������ϵص㴴��");
			AmsObjectAddressDAO objectDAO = new AmsObjectAddressDAO(userAccount, dtoAddress, conn);
			objectDAO.createData();
			operateResult = true;
			
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (UnsupportedEncodingException ex) {
			Logger.logError(ex);
		} finally{
			try {
				if (operateResult) {
					conn.commit();
				} else {
					conn.rollback();
					dto.setWorkorderObjectNo("");
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
				throw new DataHandleException(ex);
			}
		}
		return operateResult;
	}
    
    /**
     * ��ȡ�ص�ڶ��δ���
     * @return
     * @throws QueryException
     */
    public EtsObjectDTO getEtsObjectLocData() throws QueryException {
    	CommonObjectModel model = (CommonObjectModel) sqlProducer;
    	SQLModel sqlModel = model.getEtsObjectLoc2();
    	SimpleQuery simp = new SimpleQuery(sqlModel, conn);
    	simp.setDTOClassName(EtsObjectDTO.class.getName());
    	simp.executeQuery();
    	EtsObjectDTO etsObjectDTO = (EtsObjectDTO) simp.getDTOSet().getDTO(0);
        return etsObjectDTO;
    }
    
    public EtsObjectDTO getEtsObjectLocInfo() throws QueryException {
    	CommonObjectModel model = (CommonObjectModel) sqlProducer;
    	SQLModel sqlModel = model.getEtsObjectLocInfo();
    	SimpleQuery simp = new SimpleQuery(sqlModel, conn);
    	simp.setDTOClassName(EtsObjectDTO.class.getName());
    	simp.executeQuery();
    	EtsObjectDTO etsObjectDTO = (EtsObjectDTO) simp.getDTOSet().getDTO(0);
        return etsObjectDTO;
    }
    
    public String getWorkorderObjectNo() throws SQLModelException, QueryException, ContainerException {
    	CommonObjectModel model = (CommonObjectModel) sqlProducer;
        SQLModel sqlModel = model.getWorkorderObjectNo();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        String objectNo = "";
        if (simpleQuery.hasResult()) {
        	Row row = simpleQuery.getFirstRow();
        	objectNo = row.getStrValue("WORKORDER_OBJECT_NO") + ";" + row.getStrValue("ADDRESS_ID");
        }
        return objectNo;
    }
    
    /**
     * �жϵص�ڶ��δ����Ƿ����
     * @param barcode
     * @param companyCode
     * @return
     * @throws SQLModelException
     * @throws QueryException
     */
    public boolean getLoc2CodeIsExists(String barcode) throws SQLModelException, QueryException {
        boolean hasLocCode = true;
        CommonObjectModel eoModel = (CommonObjectModel) sqlProducer;
        SQLModel sqlModel = eoModel.getLoc2CodeIsExistsModel(barcode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (!simpleQuery.hasResult()) {
        	hasLocCode = false;
        }
        return hasLocCode;
    }
    
    /**
     * ��ȡƥ���orgizationId
     * @return
     * @throws QueryException
     * @throws ContainerException
     */
    private int getMatchOrgId(int orgId) throws QueryException, ContainerException {
        CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getMatchOrgIdModel(orgId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        int matchOrgId = 0;
        if (simpleQuery.hasResult()) {
        	matchOrgId = Integer.parseInt(simpleQuery.getFirstRow().getStrValue("MATCH_ORGANIZATION_ID"));
        }
        return matchOrgId;
    }

    /**
	 * ����:���ƥ��ص�����Ƿ����
	 * @return boolean
	 */
	public boolean existMatchObjectCode(){
		boolean exist = false;
		try {
			CommonObjectModel modelProducer = (CommonObjectModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getMatchObjectCodeExistModel();
			SimpleQuery simQuery = new SimpleQuery(sqlModel, conn);
			simQuery.executeQuery();
			exist = simQuery.hasResult();
		} catch (QueryException ex) {
			ex.printLog();
		}
		return exist;
	}
}
