package com.sino.ams.system.object.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.basepoint.dto.EtsObjectDTO;
import com.sino.ams.system.object.model.ImportObjectModel;
import com.sino.ams.system.user.dao.EtsOuCityMapDAO;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.config.SinoConfig;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: zjs
 * Date: 2008-6-26
 * Time: 20:16:41
 * Function:�ص���������.
 */
public class ImportObjectDAO extends AMSBaseDAO {
    private ImportObjectModel modelProducer = null;
    private SimpleQuery simpleQuery = null;
    private List<String> countyCodes = null;

    /**
     * ���ܣ��ص㵼�� AMS_OBJECT_IMPORT ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  BaseUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter DTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public ImportObjectDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        super.sqlProducer = new ImportObjectModel(userAccount, dtoParameter);
    }

    /**
     * ���ܣ���������
     *
     * @param dtoSet ��Excel������������
     * @return true��ʾ����ɹ���false��ʾ����ʧ��
     */
    public boolean importObject(DTOSet dtoSet) {
        boolean dataValid = true;
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            modelProducer = (ImportObjectModel) sqlProducer;
            simpleQuery = new SimpleQuery(null, conn);
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            deleteTmpTableData();
            import2TmpTable(dtoSet);
            if (isAllObjectValid(dtoSet)) {
                submitOrderDtl(dtoSet);
            } else {
                dataValid = false;
            }
            operateResult = true;
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            try {
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
        return dataValid;
    }


    /**
     * ���ܣ�ɾ���ӿڱ�����ݡ�
     *
     * @throws DataHandleException ɾ���ӿڱ�����ݳ���ʱ�׳����ݴ����쳣
     */
    private void deleteTmpTableData() throws DataHandleException {
        SQLModel sqlModel = modelProducer.deleteImportModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ���ȡ����Ĵ�����Ϣ
     *
     * @return ����Ĵ�����Ϣ
     * @throws QueryException ��ȡ����Ĵ�����Ϣ����ʱ�׳���ѯ�쳣
     */
    public RowSet getImportError() throws QueryException {
        SQLModel sqlModel = modelProducer.getImportErrorModel();
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.getSearchResult();
    }

    /**
     * ���ܣ���Excel�����������ݵ��뵽��ʱ��AMS_OBJECT_IMPORT
     *
     * @param dtoSet DTOSet Excel������������
     * @throws DataHandleException ��Excel�����������ݵ��뵽��ʱ��AMS_OBJECT_IMPORT����ʱ�׳����쳣
     */
    private void import2TmpTable(DTOSet dtoSet) throws DataHandleException {
        try {
            if (dtoSet != null && dtoSet.getSize() > 0) {
                countyCodes = new ArrayList<String>();
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    EtsObjectDTO eoDTO = (EtsObjectDTO) dtoSet.getDTO(i);
                    String countyCode = getCountyCodeByAreaCode(eoDTO.getCountyCode());
                    if (countyCode.equals("")) {
                        countyCodes.add(eoDTO.getCountyCode());
                    } else {
                        countyCodes.add(countyCode);
                    }
                    modelProducer.setDTOParameter(eoDTO);
                    SQLModel sqlModel = modelProducer.insertImportModel(countyCode);
                    DBOperator.updateRecord(sqlModel, conn);
                }
            }
        } catch (QueryException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        }
    }

    /**
     * ���ܣ�������������Ƿ�Ϸ�
     *
     * @param dtoSet Excel������������
     * @return true��ʾȫ��У��ͨ����false��ʾ�зǷ����ݴ��ڣ��Ҽ�¼������־
     */
    private boolean isAllObjectValid(DTOSet dtoSet) {
        int inValidCount = 0;
        if (dtoSet != null && !dtoSet.isEmpty()) {
            int dataCount = dtoSet.getSize();
            for (int i = 0; i < dataCount; i++) {
                EtsObjectDTO objectDTO = (EtsObjectDTO) dtoSet.getDTO(i);
                if (!isObjectValid(objectDTO)) {
                    inValidCount++;
                }
            }
        }
        return (inValidCount == 0);
    }


    /**
     * ���ܣ������ݴ�ת�Ƶ���ʽ����Ҫ�����������������
     * 1��д���ݵ�ETS_OBJECT��
     * 2��д���ݵ�AMS_OBJECT_ADDRESS��
     * 3���ж��Ƿ���ETS_OBJECT_LOC2����ڣ���������д�ص�ڶ��α�ETS_OBJECT_LOC2
     *
     * @param dtoSet DTOSet Excel������������
     * @throws DataHandleException �����ݴ�ת�Ƶ���ʽ�����ʱ�׳��쳣
     */
    private void submitOrderDtl(DTOSet dtoSet) throws DataHandleException {
        try {
        	SQLModel sqlModel = null;
            for (int i = 0; i < dtoSet.getSize(); i++) {
                EtsObjectDTO eoDTO = (EtsObjectDTO) dtoSet.getDTO(i);

                eoDTO.setWorkorderObjectNo(getNextGUID());
                eoDTO.setOrganizationId(userAccount.getOrganizationId());
                eoDTO.setCityCode(getCityCode(eoDTO.getCounty()));
                eoDTO.setCountyCode2(getCountyCode(eoDTO.getCounty()));
                String oldObjectCategory = eoDTO.getObjectCategory();
                String objectCategoryCode = getObjectCategoryCode(oldObjectCategory, "CODE");
                eoDTO.setObjectCategory(objectCategoryCode);
                String objectCategoryName = getObjectCategoryCode(oldObjectCategory, "DESCRIPTION");
                eoDTO.setObjectCategoryName(objectCategoryName);
                if (userAccount.getIsTt().equals("Y")) {  //��ͨ
                	eoDTO.setIsTd("Y");
                } else {
                	eoDTO.setIsTd(userAccount.getIsTd());
                }                 
                eoDTO.setCountyCode(countyCodes.get(i));//��������ɱ����ı����к�
                modelProducer.setDTOParameter(eoDTO);
                
                if (eoDTO.isAddLocation()) { //����
                	sqlModel = modelProducer.getDataCreateModel();
                    DBOperator.updateRecord(sqlModel, conn);

                    sqlModel = modelProducer.getAOACreateModel();
                    DBOperator.updateRecord(sqlModel, conn);

                    //if (!userAccount.getIsTt().equals("Y")) {
    	                if (!isLocCode2Exists(eoDTO.getWorkorderObjectCode())) { //�ж������ĵص��Ƿ��ڵص�ڶ��δ���ά�����д���
    	                    sqlModel = modelProducer.createDoEtsObjectLoc();
    	                    DBOperator.updateRecord(sqlModel, conn);
    	                }
                    //}    	                
                } else if (eoDTO.isUpdateLocation()) { //�޸�
                	sqlModel = modelProducer.updateEtsObjectInfo(eoDTO);
                    DBOperator.updateRecord(sqlModel, conn);
                    
                    //�޸�����ص���Ϣ
                    sqlModel = modelProducer.updateEtsObjectLocInfo(eoDTO);
                    DBOperator.updateRecord(sqlModel, conn);
                } else { //ʧЧ
                	sqlModel = modelProducer.disabledEtsObject(eoDTO.getWorkorderObjectCode());
                    DBOperator.updateRecord(sqlModel, conn);
                }
                
                
                
            }
        } catch (QueryException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        }
    }

    /**
     * ���ܣ����ص��Ƿ�Ϸ�����Ҫ��������У�鹤����
     * 1�����ص���룻
     * 2�����ص����ƣ�
     * 3�����������룻
     * 4���������������
     * 5�����������أ�
     * 6�����ص�רҵ��
     * 7�����ص���(�����ڻ�վ��Ż�Ӫҵ�����)
     *
     * @param eoDTO �ص����
     * @return true��ʾ���Ϸ���false��ʾ�Ƿ�
     */
    private boolean isObjectValid(EtsObjectDTO eoDTO) {
        boolean checkResult = false;
        try {
        	checkResult = isMaintainTypeValid(eoDTO);
        	if (checkResult) {
	            checkResult = checkObjectCode(eoDTO);
	            if (checkResult) {
	                checkResult = checkAreaCode(eoDTO);
	                if (checkResult) {
	                    checkResult = checkObjectName(eoDTO);
	                    if (checkResult) {
	                        checkResult = checkAreaType(eoDTO);
	                        if (checkResult) {
	                            checkResult = checkCityAndCounty(eoDTO);
	                            if (checkResult) {
	                                checkResult = checkObjectCategory(eoDTO);
	                                if (checkResult) {
	                                    checkResult = checkBTSOrBizHall(eoDTO);
	                                }
	                            }
	                        }
	                    }
	                }
	            }
        	}
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return checkResult;
    }
    
    /**
     * ���ܣ����ص��ά�������Ƿ�Ϸ���
     *
     * @return true��ʾά�����ͺϷ���false��ʾ���Ϸ�
     * @throws DataHandleException 
     */
    private boolean isMaintainTypeValid(EtsObjectDTO eoDTO) {
    	boolean checkResult = true;
        String maintainType = eoDTO.getRemark();
        String errorMessage = "";
        if (maintainType == null || maintainType.trim().length() == 0) {
            errorMessage = "δָ���ص�ά�����ͣ�ϵͳ���ܴ���";
            checkResult = false;
        } else {
            if (!(eoDTO.isAddLocation() || eoDTO.isUpdateLocation() || eoDTO.isDisableLocation())) {
                errorMessage = "ָ���˲�֧�ֵĵص�ά�����ͣ�ϵͳ���ܴ���";
                checkResult = false;
            }
        }
        try {
	        if (!checkResult) {
	            logObjectNameError(eoDTO.getWorkorderObjectCode(), errorMessage);
	        }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return checkResult;
    }

    /**
     * ���ܣ����ص�����Ƿ�Ϸ�����Ҫ������¹�����
     * 1���ص�����Ƿ�ǿգ�
     * 2���ص�����Ƿ��ظ�(Excel)��
     * 3���ص�����Ƿ������ݿ��ظ���
     * <P>ע�⣺���������ƶ��������߼�</P>
     *
     * @param eoDTO �ص����
     * @return true��ʾ���Ϸ���false��ʾ�Ƿ�
     */
    private boolean checkObjectCode(EtsObjectDTO eoDTO) {
        boolean checkResult = true;
        try {
            String errorMessage = "";
            String provinceCode = SinoConfig.getProvinceCode();
            String locationCode = eoDTO.getWorkorderObjectCode();
            EtsOuCityMapDAO etsOuCityMapDAO = new EtsOuCityMapDAO(userAccount, null, conn);
            String companyCode = etsOuCityMapDAO.getCompanyCode(userAccount.getOrganizationId());
            if (StrUtil.isEmpty(locationCode)) {
                errorMessage = "�ص���벻��Ϊ��";
                checkResult = false;
            } else if (locationCode.indexOf(" ")!=-1) {
            	errorMessage = "�ص�����в����пո�";
                checkResult = false;
            } else if (checkObjectCodeSelfDuplicate(locationCode)) {
                errorMessage = "����ص��������ظ�";
                checkResult = false;
            }
            if (checkResult) {
                int startIndex = locationCode.indexOf(".");
                int endIndex = locationCode.lastIndexOf(".");
                if (startIndex == 6 && endIndex == 21 && locationCode.length() == 25) {
                    locationCode = locationCode.substring(0, startIndex);
                    
                    if (eoDTO.getCountyCode().length() > 0 || eoDTO.isAddLocation()) {
	                    if (!locationCode.equals(eoDTO.getCountyCode())) {
	                        errorMessage = "����ص�����һ����������벻һ��";
	                        checkResult = false;
	                    }
                    }
                    if (!eoDTO.getWorkorderObjectCode().substring(7, 11).equals(companyCode)) {
                        errorMessage = "����ص����ڶ���ǰ��λ�빫˾���벻һ��";
                        checkResult = false;
                    }
                    if (!provinceCode.equals(DictConstant.PROVINCE_CODE_JIN)) {
//                    ������ɽ�������ϡ�ɽ��2011-12-28�޸�
                        if (!StrUtil.isNumber(eoDTO.getWorkorderObjectCode().substring(13, endIndex))) {
                            errorMessage = "����ص����ڶ��κ��λӦ��Ϊ����";
                            checkResult = false;
                        }
                    }
                } else {
                    errorMessage = "����ص�����ʽ����ȷ��������������뿪ʼ���Ұ��������㣬����Ϊ25���ַ�";
                    checkResult = false;
                }
            }
            if (checkResult) {
                if (isObjectCodeExist(eoDTO.getWorkorderObjectCode())) {
                    if (eoDTO.isAddLocation()) {
	                	if (provinceCode.equals(DictConstant.PROVINCE_CODE_NM)) {//���������
	                        if (getObjectCodeOrganizationId(eoDTO.getWorkorderObjectCode()) == userAccount.getOrganizationId()) {
	                            errorMessage = "����ص������ϵͳ���Ѵ���";
	                            checkResult = false;
	                        }
	                    } else {
	                        errorMessage = "����ص���������ݿ����Ѵ���";
	                        checkResult = false;
	                    }
                    } else if (eoDTO.isDisableLocation()) {
                    	if (hasAssetsUnderLocation(eoDTO.getWorkorderObjectCode())) {
                            if (errorMessage.length() > 0) {
                                errorMessage += ",";
                            }
                            errorMessage += "�õص���������ʲ�������ʧЧ";
                            checkResult = false;
                        }
                    }
                } else {
                	if (eoDTO.isUpdateLocation()) {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "Ҫ�޸ĵĵص���벻����";
                        checkResult = false;
                    } else if (eoDTO.isDisableLocation()) {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "ҪʧЧ�ĵص���벻����";
                        checkResult = false;
                    }
                }
            }
            if (!checkResult) {
                logObjectCodeError(eoDTO.getLocation(), errorMessage);
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return checkResult;
    }

    /**
     * ���ܣ����ص������Ƿ�Ϸ�����Ҫ������¹�����
     * 1���ص������Ƿ�ǿգ�
     * 2���ص������Ƿ��ظ�(Excel�����߼���δ����)��
     * 3���ص������Ƿ������ݿ��ظ�(���߼���δ����)��
     *
     * @param eoDTO �ص����
     * @return true��ʾ���Ϸ���false��ʾ�Ƿ�
     */
    private boolean checkObjectName(EtsObjectDTO eoDTO) {
        boolean checkResult = true;
        try {
            String errorMessage = "";
            String objectName = eoDTO.getWorkorderObjectName();
            int startIndex = objectName.indexOf(".");
            int endIndex = objectName.lastIndexOf(".");
            if (!eoDTO.isDisableLocation()) {
            	if (StrUtil.isEmpty(objectName)) {
                    errorMessage = "�ص���������Ϊ��";
                    checkResult = false;
                }            	
            	if (!SinoConfig.getProvinceCode().equals(DictConstant.PROVINCE_CODE_JIN)) { //������ɽ������Ҫ
                	if (objectName.substring(startIndex + 1, endIndex).indexOf(".") != -1) {
                		errorMessage += "�ص������еġ�.�����������ܳ�������";
                        checkResult = false;
                	}
                }
            }
            
            if (objectName.length() > 0) {
            	if (!isCoincidentWithAreaName(eoDTO.getCountyCode(), objectName)) {
                    errorMessage = "�ص�������һ������������Ӧ��������һ��";
                    checkResult = false;
                } else {
//                    if (startIndex > 0 && endIndex == objectName.length() - 3 && endIndex > startIndex) {
                	if (startIndex > 0 && endIndex > startIndex) {
                        String city = eoDTO.getCity();
                        city += eoDTO.getCounty();
                        objectName = objectName.substring(startIndex + 1);
                        if (!objectName.startsWith(city)) {
                            errorMessage = "�ص�����������������Ʋ�һ��";
                            checkResult = false;
                        }
                    } else {
                        errorMessage = "�ص��������淶";
                        checkResult = false;
                    }
                }
            	
            	if (checkResult) {
            		objectName = eoDTO.getWorkorderObjectName();
            		objectName = objectName.substring(startIndex + 1, endIndex);
            		String locationCode = eoDTO.getWorkorderObjectCode();
                	startIndex = locationCode.indexOf(".");
                	endIndex = locationCode.lastIndexOf(".");
            		locationCode = locationCode.substring(startIndex + 1, endIndex);
            		String locCode = getLocCode2ByLocDesc(objectName);
                    String locDesc = getLocDesc2ByLocCode(locationCode);
            		
                    if (locCode.length() > 0) {                    	
                		if (!locCode.equals(locationCode)) {
	                			errorMessage += "�ص������ڶ��Ρ�"
		                                 + objectName
		                                 + "����ϵͳ�ж�Ӧ����Ϊ��"
		                                 + locCode
		                                 + "��,���㵼��ı���ĵڶ��β�һ��";
	                			checkResult = false;
                			}
	                 	}
	                 	if (locDesc.length() > 0 && eoDTO.isAddLocation()) {
		                 	if(!locDesc.equals(objectName)) {
		                         errorMessage += "�ص����ڶ��Ρ�"
		                                 + locationCode
		                                 + "����ϵͳ�ж�Ӧ����Ϊ��"
		                                 + locDesc
		                                 + "��,���㵼��������ĵڶ��β�һ��";
		                         checkResult = false;
		                 	} 
	                 }            		
//                    String locDesc = eoDTO.getWorkorderObjectName();
//                    locDesc = locDesc.substring(startIndex + 1, endIndex);
//                    String locCode = getLocCode2ByLocDesc(locDesc);
//                    if (!locCode.equals("")) {
//                        if (!eoDTO.getWorkorderObjectCode().contains(locCode)) {
//                            errorMessage = "����ĵص������ĵڶ�����ϵͳ�ж�Ӧ�ı��Ϊ��" + locCode + "��";
//                            checkResult = false;
//                        }
//                    }
                } 
            	
            	if (checkResult) {
            		if (!eoDTO.isUpdateLocation() && isObjectNameExist(eoDTO.getWorkorderObjectName())) {
            			errorMessage = "����ص����������ݿ����Ѵ���";
                        checkResult = false;
            		}
            	}
            }
            
            if (!checkResult) {
                logObjectNameError(eoDTO.getWorkorderObjectCode(), errorMessage);
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return checkResult;
    }

    /**
     * ���ܣ������������Ƿ�Ϸ�
     *
     * @param eoDTO �ص����
     * @return true��ʾ���Ϸ���false��ʾ�Ƿ�
     */
    private boolean checkAreaCode(EtsObjectDTO eoDTO) {
        boolean checkResult = true;
        try {
            String errorMessage = "";
            String areaCode = eoDTO.getCountyCode();
            String provinceCode = SinoConfig.getProvinceCode();
            if (eoDTO.isAddLocation()) {
            	if (StrUtil.isEmpty(areaCode)) {
                    errorMessage = "����������Ϊ��";
                    checkResult = false;
                }
            }
            if (areaCode.length() > 0) {
	            if (areaCode.length() != 6) {
	                errorMessage = "�����������λ������6λ";
	                checkResult = false;
	            } else if (!StrUtil.isNumber(areaCode)) {
	                errorMessage = "�������������";
	                checkResult = false;
	            }
//            else {
//                if (!provinceCode.equals(DictConstant.PROVINCE_CODE_JIN)) {
//                    String preAreaCode = areaCode.substring(0, 4);
//                    if (!preAreaCode.equals(userAccount.getCompanyCode())) {
//                        errorMessage = "����������������ʼ�ڹ�˾���룬����ܵ�����������˾���������";
//                        checkResult = false;
//                    }
//                }
//            }
		            if (checkResult) {
		                if (!isAreaCodeExist(areaCode)) {      //�ж���������Ĵ�����
		                    if (!provinceCode.equals(DictConstant.PROVINCE_CODE_JIN)) {
		                        errorMessage = "�����������ڹ�˾�򲻴���";
		                    } else {
		                        errorMessage = "�������򲻴���";
		                    }
		                    checkResult = false;
		                }
		            }
	            }
	            if (!checkResult) {
	                logAreaCodeError(eoDTO.getWorkorderObjectCode(), errorMessage);
	            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return checkResult;
    }

    /**
     * ���ܣ�������������Ƿ�Ϸ�
     *
     * @param eoDTO �ص����
     * @return true��ʾ���Ϸ���false��ʾ�Ƿ�
     */
    private boolean checkAreaType(EtsObjectDTO eoDTO) {
        boolean checkResult = false;
        try {
        	if (eoDTO.isAddLocation()) {
        		if (StrUtil.isEmpty(eoDTO.getAreaType())) {
                    logAreaTypeError(eoDTO.getWorkorderObjectCode(), "������������Ϊ��");
                }	
        	}
           
        	if (!StrUtil.isEmpty(eoDTO.getAreaType())) {
	            if (!isAreaTypeExist(eoDTO.getAreaType())) {
	                logAreaTypeError(eoDTO.getWorkorderObjectCode(), "��������������");
	            } else {
	                checkResult = true;
	            }
        	}
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return checkResult;
    }

    /**
     * ���ܣ������������Ƿ�Ϸ�
     *
     * @param eoDTO �ص����
     * @return true��ʾ���Ϸ���false��ʾ�Ƿ�
     */
    private boolean checkCityAndCounty(EtsObjectDTO eoDTO) {
        boolean checkResult = true;
        try {
            boolean checkCityResult = true;
            boolean checkCountyResult = true;
            String errorCityMessage = "";
            String errorCountyMessage = "";
            String city = eoDTO.getCity();
            String county = eoDTO.getCounty();
            
            if (eoDTO.isAddLocation()) {
            	if (StrUtil.isEmpty(city)) {
                    errorCityMessage = "���в���Ϊ��";
                    checkCityResult = false;
                }            	
            	if (StrUtil.isEmpty(county)) {
                    errorCountyMessage = "���ز���Ϊ��";
                    checkCountyResult = false;
                }
            }
            
            if (city.length() > 0) {
                if (!isCityExist(city)) {
                    errorCityMessage = "���С�" + city + "��������";
                    checkCityResult = false;
                }
            }
            if (county.length() >0 && checkCityResult) {
                if (!isCountyExist(county, city)) {
                    errorCountyMessage = "���ء�" + county + "�������ڣ����߲��ڵ��С�" + city + "����";
                    checkCountyResult = false;
                }
            }
            if (!checkCityResult) {
                logCityError(eoDTO.getWorkorderObjectCode(), errorCityMessage);
            }
            if (!checkCountyResult) {
                logCountyError(eoDTO.getWorkorderObjectCode(), errorCountyMessage);
            }
            checkResult = (checkCityResult && checkCountyResult);
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return checkResult;
    }

    /**
     * ���ܣ����ص�רҵ�Ƿ�Ϸ�
     *
     * @param eoDTO �ص����
     * @return true��ʾ���Ϸ���false��ʾ�Ƿ�
     */
    private boolean checkObjectCategory(EtsObjectDTO eoDTO) {
        boolean checkResult = true;
        try {
            String errorMessage = "";
            String objectCategory = eoDTO.getObjectCategory();
            if (eoDTO.isAddLocation()) {
            	if (StrUtil.isEmpty(objectCategory)) {
                    errorMessage = "רҵ���벻��Ϊ��";
                    checkResult = false;
                }
            }
            if (StrUtil.isNotEmpty(objectCategory)) {
            	if (!isObjectCategoryExist(objectCategory)) {      //�жϵص����Ĵ�����
                    errorMessage = "רҵ���벻����";
                    logObjectCategoryError(eoDTO.getWorkorderObjectCode(), eoDTO.getWorkorderObjectName(), eoDTO.getCountyCode(), "רҵ���벻����");
                    checkResult = false;
                } else if (eoDTO.getWorkorderObjectCode().indexOf(eoDTO.getObjectCategory()) <= 0) { //��֤רҵ�����Ƿ��ڵص�����д���
                    errorMessage = "רҵ������ص�����е�רҵ���벻ƥ��";
                    checkResult = false;
                }
            }

            if (!checkResult) {
                logObjectCategoryError(eoDTO.getWorkorderObjectCode(), eoDTO.getWorkorderObjectName(), eoDTO.getCountyCode(), errorMessage);
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return checkResult;
    }

    /**
     * ���ܣ�����վ��Ż�Ӫҵ������Ƿ�Ϸ�
     *
     * @param eoDTO �ص����
     * @return true��ʾ���Ϸ���false��ʾ�Ƿ�
     */
    private boolean checkBTSOrBizHall(EtsObjectDTO eoDTO) {
        boolean checkResult = true;
        try {
            String errorMessage = "";
            String objectCategory = eoDTO.getObjectCategory();
            if (objectCategory.equalsIgnoreCase("JZ") || objectCategory.equalsIgnoreCase("YY")) {
                String btsNo = eoDTO.getBtsNo();
                if (!btsNo.equals("")) {
                    int index = btsNo.indexOf(".");
                    if (index > -1) {
                        btsNo = btsNo.substring(0, index);
                    }
                    if (isBTSOrBizHallNoExist(btsNo)) {
                        if (objectCategory.equalsIgnoreCase("JZ")) {
                            errorMessage = "��վ��š�" + btsNo + "���Ѿ�����";
                        } else {
                            errorMessage = "Ӫҵ����š�" + btsNo + "���Ѿ�����";
                        }
                        checkResult = false;
                    }
                } else {
                	if (eoDTO.isAddLocation()) {
	                    if (objectCategory.equalsIgnoreCase("JZ")) {
	                        errorMessage = "��վ��Ų���Ϊ��";
	                    } else {
	                        errorMessage = "Ӫҵ����Ų���Ϊ��";
	                    }
	                    checkResult = false;
                	}
                }
                if (!checkResult) {
                    logObjectCategoryError(eoDTO.getWorkorderObjectCode(), eoDTO.getWorkorderObjectName(), eoDTO.getCountyCode(), errorMessage);
                }
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return checkResult;
    }

    /**
     * ����:����վ��Ӫҵ������Ƿ����
     *
     * @param btsNo ��վ��Ӫҵ�����
     * @return boolean trueb��ʾ���ڣ�false��ʾ������
     */
    private boolean isBTSOrBizHallNoExist(String btsNo) {
        boolean exist = false;
        try {
            SQLModel sqlModel = modelProducer.getBTSNoExistModel(btsNo);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            exist = simpleQuery.hasResult();
        } catch (QueryException ex) {
            ex.printLog();
        }
        return exist;
    }

    /**
     * ���ܣ��������Ƿ����
     *
     * @param city ��������
     * @return boolean trueb��ʾ���ڣ�false��ʾ������
     * @throws QueryException ������ݳ���ʱ�׳���ѯ�쳣
     */
    private boolean isCityExist(String city) throws QueryException {
        SQLModel sqlModel = modelProducer.getCityExistModel(city);
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.hasResult();
    }

    /**
     * ���ܣ���������Ƿ����
     *
     * @param county ��������
     * @param city   ��������
     * @return boolean trueb��ʾ���ڣ�false��ʾ������
     * @throws QueryException ������ݳ���ʱ�׳���ѯ�쳣
     */
    private boolean isCountyExist(String county, String city) throws QueryException {
        SQLModel sqlModel = modelProducer.getCountyExistModel(city, county);
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.hasResult();
    }

    /**
     * ���ܣ�����ĵص�����Ϊ�յ���Ϣ��
     *
     * @throws DataHandleException
     */
    private void logObjectCodeError(String codeName, String codeError) throws DataHandleException {

        SQLModel sqlModel = modelProducer.getObjectCodeLogModel(codeName, codeError);
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ��������Ϊ�յ���Ϣ��
     *
     * @throws DataHandleException
     */
    private void logCityError(String locationCode, String codeError) throws DataHandleException {

        SQLModel sqlModel = modelProducer.getCityErrorLogModel(locationCode, codeError);
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ���������Ϊ�յ���Ϣ��
     *
     * @throws DataHandleException
     */
    private void logCountyError(String locationCode, String codeError) throws DataHandleException {
        SQLModel sqlModel = modelProducer.getCountyErrorLogModel(locationCode, codeError);
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ����뵼��ص����ƷǷ��Ĵ�����Ϣ��
     *
     * @throws DataHandleException
     */
    private void logObjectNameError(String code, String codeNameError) throws DataHandleException {

        SQLModel sqlModel = modelProducer.getObjectNameLogModel(code, codeNameError);
        DBOperator.updateRecord(sqlModel, conn);
    }


    /**
     * ���ܣ��������������Ϊ�յ���Ϣ��
     *
     * @throws DataHandleException
     */
    private void logAreaTypeError(String locationCode, String areaTypeError) throws DataHandleException {

        SQLModel sqlModel = modelProducer.getAreaTypeLogModel(locationCode, areaTypeError);
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ�����ĵص�רҵ��Ϊ�յ���Ϣ��
     *
     * @throws DataHandleException
     */
    private void logObjectCategoryError(String code, String codeName, String countyCode, String obCateError) throws DataHandleException {

        SQLModel sqlModel = modelProducer.getObjectCategoryLogModel(code, codeName, countyCode, obCateError);
        DBOperator.updateRecord(sqlModel, conn);
    }


    /**
     * ���ܣ�����ĵص����ص�Ϊ�յ���Ϣ��
     *
     * @throws DataHandleException
     */
    private void logAreaCodeError(String code, String countyError) throws DataHandleException {
        SQLModel sqlModel = modelProducer.getAreaCodeLogModel(code, countyError);
        DBOperator.updateRecord(sqlModel, conn);
    }


    /**
     * ���ܣ�У����ETS_OBJECT�Ƿ����objectCode ture��ʾ���ڣ�false��ʾ�����ڡ�
     */
    private boolean isObjectCodeExist(String objectCode) {
        boolean hasBarcode = false;
        try {
            SQLModel sqlModel = modelProducer.getObjectCodeExistModel(objectCode);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            hasBarcode = simpleQuery.hasResult();
        } catch (QueryException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return hasBarcode;
    }
    
    /**
     * ���ܣ�У����ETS_OBJECT�Ƿ����objectName ture��ʾ���ڣ�false��ʾ�����ڡ�
     */
    private boolean isObjectNameExist(String objectName) {
        boolean hasBarcode = false;
        try {
            SQLModel sqlModel = modelProducer.getObjectNameExistModel(objectName);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            hasBarcode = simpleQuery.hasResult();
        } catch (QueryException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У����ETS_OBJECT�Ƿ����objectCode ture��ʾ���ڣ�false��ʾ�����ڡ�
     */
    private int getObjectCodeOrganizationId(String objectCode) {
        int existCount = 0;
        try {
            SQLModel sqlModel = modelProducer.getObjectCodeOrganizationIdModel(objectCode);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                existCount = Integer.parseInt(simpleQuery.getFirstRow().getStrValue("ORGANBIZATION_ID"));
            }
        } catch (QueryException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return existCount;
    }


    /**
     * ���ܣ�У����AMS_OBJECT_IMPORT�Ƿ����barcode ture��ʾ����ڣ�false��ʾ�����ڡ�
     *
     * @throws DataHandleException
     */
    private boolean isObjectCategoryExist(String category) {
        boolean hasBarcode = false;
        try {
            SQLModel sqlModel = modelProducer.OCModel(category);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            hasBarcode = simpleQuery.hasResult();
        } catch (QueryException ex) {
            ex.printLog();
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return hasBarcode;
    }

    /**
     * ���ܣ�У����AMS_OBJECT_IMPORT�Ƿ����AreaType ture��ʾ����ڣ�false��ʾ�����ڡ�
     */
    private boolean isAreaTypeExist(String dictCode) throws QueryException {
        boolean hasAreaType = true;
        SQLModel sqlModel = modelProducer.isExistAreaType(dictCode);
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        if (!simpleQuery.hasResult()) {
            hasAreaType = false;
        }
        return hasAreaType;
    }

    /**
     * ���ܣ�У����AMS_OBJECT_IMPORT�Ƿ����error,ture ��ʾ�����д�false ��ʾ�����޴�
     *
     * @throws DataHandleException
     */
    public boolean importHasError() throws DataHandleException, QueryException {
        boolean hasError = false;
        SQLModel sqlModel = modelProducer.hasErrorModel();
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasError = true;
        }
        return hasError;
    }

    /**
     * ���ܣ�У�鵼���countyCode�Ƿ����,ture ��ʾ������ȷ(����ou)��false ��ʾ�����д�
     *
     * @throws DataHandleException
     */
    private boolean isAreaCodeExist(String areaCode) throws QueryException {
        boolean hasError = false;
        SQLModel sqlModel = modelProducer.hasCountyModel(areaCode);
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasError = true;
        }
        return hasError;
    }

    /**
     * ���ܣ�У�鵼��ĵص������ĵ�һ���Ƿ��뵼������������Ӧ������һ��
     *
     * @param countyCode �������
     * @param objectName �ص���������
     * @return true��ʾ�ص�Ϸ���false��ʾ�ص�Ƿ�
     * @throws QueryException
     */
    private boolean isCoincidentWithAreaName(String countyCode, String objectName) throws QueryException {
        boolean isValid = false;
        try {
            SQLModel sqlModel = modelProducer.hasCountyModel(countyCode);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                Row row = simpleQuery.getFirstRow();
                String location = row.getStrValue("DESCRIPTION");
                int index = objectName.indexOf(".");
                int endIndex = objectName.lastIndexOf(".");
                if (index > -1 && endIndex > index) {
                    objectName = objectName.substring(0, index);
                    if (objectName.equals(location)) {
                        isValid = true;
                    }
                }
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new QueryException(ex.getMessage());
        }
        return isValid;
    }

    private String getLocCode2ByLocDesc(String locDesc) throws QueryException {
        String locCode2 = "";
        try {
            SQLModel sqlModel = modelProducer.getLocCode2ByDescModel(locDesc);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                Row row = simpleQuery.getFirstRow();
                locCode2 = row.getStrValue("LOC2_CODE");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return locCode2;
    }
    
    private String getLocDesc2ByLocCode(String locCode) throws QueryException {
        String locDesc2 = "";
        try {
            SQLModel sqlModel = modelProducer.getLocDesc2ByDescModel(locCode);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                Row row = simpleQuery.getFirstRow();
                locDesc2 = row.getStrValue("LOC2_DESC");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return locDesc2;
    }

    /**
     * ���ܣ��� AMS_OBJECT_IMPORT �Ĳ�ѯsql��
     *
     * @throws QueryException
     */
    public RowSet getQueryImportModel() throws QueryException {
        SQLModel sqlModel = modelProducer.getQueryImportModel();
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.getSearchResult();
    }

    /**
     * ���ܣ�У����ETS_OBJECT �ĵص�����Ƿ��ظ��Ƿ����error,ture ��ʾ�����д�false ��ʾ�����޴�
     *
     * @throws QueryException
     */
    private boolean checkObjectCodeSelfDuplicate(String objectCode) throws QueryException {
        SQLModel sqlModel = modelProducer.getObjectCodeSelfDuplicateModel(objectCode);
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.hasResult();
    }


    /**
     * ���ܣ���ȡ�ɱ����ı����к�
     *
     * @param areaCode �������
     * @return �ɱ����ı����к�
     * @throws QueryException �׳���ѯ�쳣
     */
    private String getCountyCodeByAreaCode(String areaCode) throws QueryException {
        String countyCode = "";
        try {
            SQLModel sqlModel = modelProducer.getAreaCountyCode(areaCode);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                countyCode = simpleQuery.getFirstRow().getStrValue("COUNTY_CODE");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return countyCode;
    }

    /**
     * ���ܣ������������ƻ�ȡ���д���
     *
     * @param county ��������
     * @return ���д���
     * @throws QueryException �����������ƻ�ȡ���д������ʱ�׳���ѯ�쳣
     */
    private String getCityCode(String county) throws QueryException {
        String city = "";
        try {
            SQLModel sqlModel = modelProducer.getCityCodeModel(county);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            RowSet rs = simpleQuery.getSearchResult();
            if (rs != null && rs.getSize() > 0) {
                Row row = rs.getRow(0);
                city = StrUtil.nullToString(row.getValue("CITY_CODE"));
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return city;
    }

    /**
     * ���ܣ������������ƻ�ȡ���ش���
     *
     * @param county ��������
     * @return ���ش���
     * @throws QueryException �����������ƻ�ȡ���ش������ʱ�׳���ѯ�쳣
     */
    private String getCountyCode(String county) throws QueryException {
        String city = "";
        try {
            SQLModel sqlModel = modelProducer.getCountyCodeModel(county);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            RowSet rs = simpleQuery.getSearchResult();
            if (rs != null && rs.getSize() > 0) {
                Row row = rs.getRow(0);
                city = StrUtil.nullToString(row.getValue("COUNTY_CODE"));
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return city;

    }

    /**
     * ���ܣ�����רҵ����(JZ��YY��XL)��ѯEAMϵͳ��רҵ�����רҵ����
     *
     * @param objectCategory Excel�������רҵ����
     * @param objectName     �ֶ����ƣ�����ȷ�����ش��������
     * @return ���ش��������
     * @throws QueryException ��ѯEAMϵͳ��רҵ�����רҵ���Ƴ���ʱ�׳���ѯ�쳣
     */
    private String getObjectCategoryCode(String objectCategory, String objectName) throws QueryException {
        String newId = "";
        try {
            SQLModel sqlModel = modelProducer.getObjectCategoryCode(objectCategory);
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            RowSet rs = simpleQuery.getSearchResult();
            if (rs != null && rs.getSize() > 0) {
                Row row = rs.getRow(0);
                newId = StrUtil.nullToString(row.getValue(objectName));
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return newId;
    }


    /**
     * ���ܣ����ص�ڶ��δ����Ƿ����
     *
     * @param workorderObjectCode �ص����
     * @return true��ʾ�ص����ĵڶ��δ��ڣ�false��ʾ������
     * @throws QueryException ���ص�ڶ��δ����Ƿ���ڳ���ʱ�׳����ݲ�ѯ�쳣
     */
    private boolean isLocCode2Exists(String workorderObjectCode) throws QueryException {
        SQLModel sqlModel = modelProducer.getIsExistsLocCode(workorderObjectCode);
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.hasResult();
    }
    
    /**
     * ���ܣ��жϵص����Ƿ����ʲ�
     *
     * @return true��ʾ���ʲ���false��ʾ���ʲ�
     * @throws QueryException ���ص����Ƿ����ʲ�����ʱ�׳����쳣
     */
    private boolean hasAssetsUnderLocation(String objectCode) throws QueryException {
        SQLModel sqlModel = modelProducer.getLocationAssetsModel(objectCode);
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.hasResult();
    }
}
