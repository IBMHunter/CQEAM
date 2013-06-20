package com.sino.ams.newSite.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.newSite.dto.EamAddressAddLDTO;
import com.sino.ams.newSite.model.EamAddressSecondAddImportModel;
import com.sino.base.data.Row;
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
 * @author ���� :wangzhipeng
 * @version ����ʱ�䣺Apr 18, 2011 3:42:18 PM
 *          ��˵�����ص���Ϣ���� DAO
 */
public class EamAddressSecondAddImportDAO extends AMSBaseDAO {

    private EamAddressSecondAddImportModel modelProducer = null;
    private SimpleQuery simpleQuery = null;
    private EamAddressAddLDTO lineDTO = null;
    private DTOSet dataLines = null;

    public EamAddressSecondAddImportDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        super.sqlProducer = new EamAddressSecondAddImportModel(userAccount, dtoParameter);
    }

    public void setDTOParameter(DTO dtoParameter) {
        super.setDTOParameter(dtoParameter);
        lineDTO = (EamAddressAddLDTO) dtoParameter;
    }

    /**
     * ���ܣ��������ݡ�
     *
     * @param dtoSet  ��Excel������������
     * @param transId ϵͳ���ɵ���������
     * @return true��ʾ����ɹ�������������У��Ϸ���false��ʾ����ʧ�ܻ��зǷ����ݴ���
     */
    public boolean importObject(DTOSet dtoSet, String transId) {
        boolean dataValid = true;
        try {
            modelProducer = (EamAddressSecondAddImportModel) sqlProducer;
            simpleQuery = new SimpleQuery(null, conn);
            dataLines = dtoSet;

            deleteLineData(transId);
            importLineData(dtoSet, transId);
            dataValid = checkAllObject(dtoSet);
        } catch (Throwable ex) {
            Logger.logError(ex);
            dataValid = false;
        }
        return dataValid;
    }

    /**
     * ���ܣ�ɾ���ӿڱ�����ݡ�
     *
     * @param transId ���̵���ID
     * @throws com.sino.base.exception.DataHandleException
     *          ɾ�����ݳ���ʱ�׳����ݴ����쳣
     */
    private void deleteLineData(String transId) throws DataHandleException {
        EamAddressAddLDTO lineDTO = new EamAddressAddLDTO();
        lineDTO.setTransId(transId);
        setDTOParameter(lineDTO);
        SQLModel sqlModel = modelProducer.deleteImportModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ���Excel�����������ݵ��뵽�б�EAM_ADDRESS_ADD_L
     *
     * @param dtoSet  DTOSet Excel������������
     * @param transId ����ͷID
     * @throws DataHandleException ��Excel�����������ݵ��뵽�б�EAM_ADDRESS_ADD_L����ʱ�׳����쳣
     */
    private void importLineData(DTOSet dtoSet, String transId) throws DataHandleException {
        try {
            if (dtoSet != null && dtoSet.getSize() > 0) {
                for (int i = 0; i < dtoSet.getSize(); i++) {
                    EamAddressAddLDTO lineDTO = (EamAddressAddLDTO) dtoSet.getDTO(i);
                    lineDTO.setTransId(transId);
                    String oldCountyCode = lineDTO.getCountyCode();
                    if (lineDTO.getAddrMaintainType().equals("����")) {
                        String countyCode = getCountyCodeByAreaCode();
                        if (StrUtil.isNotEmpty(countyCode)) {
                            lineDTO.setCountyCode(countyCode);
                        }
                    }
                    if (!lineDTO.getAddrMaintainType().equals("ʧЧ")) {
                    	lineDTO.setWorkorderObjectName(lineDTO.getWorkorderObjectName().trim());
                    }
                    lineDTO.setLineId(getNextGUID());
                    setDTOParameter(lineDTO);
                    createData();//�������ݵ��б�
                    lineDTO.setCountyCode(oldCountyCode);
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
     * @return true��ʾ�������ݺϷ���false��ʾ���ݷǷ���������г��ִ���
     */
    private boolean checkAllObject(DTOSet dtoSet) {
        int invalidCount = 0;
        if (dtoSet != null && !dtoSet.isEmpty()) {
            int dataCount = dtoSet.getSize();
            for (int i = 0; i < dataCount; i++) {
                setDTOParameter(dtoSet.getDTO(i));
                if (!isDataValid()) {
                    invalidCount++;
                }
            }
        }
        return (invalidCount == 0);
    }


    /**
     * ���ܣ����ص��Ƿ�Ϸ�����Ҫ��������У�鹤����
     * 1�����ص�ڶ��α��룻
     * 2�����ص�ڶ���������
     * 3���������������
     * 4�����������أ�
     * 5�����ص�רҵ��
     * 6�����ص���(�����ڻ�վ��Ż�Ӫҵ�����)
     *
     * @return true��ʾ���м��Ϸ���false��ʾ���ݷǷ���������г��ִ���
     */
    private boolean isDataValid() {
        boolean isObjectValid = false;
        try {
            if (isMaintainTypeValid()) {
                checkObjectCode();
                checkObjectCodeSelfDuplicate();
                checkObjectName();
                checkObjectNameSelfDuplicate();
                checkAreaType();
                checkCityAndCounty();
                checkObjectCategory();
                checkBTSOrBizHall();
                checkBTSNOSelfDuplicate();
                checkShareType();
            }
            if (lineDTO.getErrorMessage().length() > 0) {
                logImportError();
            } else {
                isObjectValid = true;
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
        return isObjectValid;
    }

    /**
     * ���ܣ����ص��ά�������Ƿ�Ϸ���
     *
     * @return true��ʾά�����ͺϷ���false��ʾ���Ϸ�
     */
    private boolean isMaintainTypeValid() {
        String maintainType = lineDTO.getAddrMaintainType();
        String errorMessage = "";
        if (maintainType == null || maintainType.trim().length() == 0) {
            errorMessage = "δָ���ص�ά�����ͣ�ϵͳ���ܴ���";
        } else {
            if (!(lineDTO.isAddLocation() || lineDTO.isUpdateLocation() || lineDTO.isDisableLocation())) {
                errorMessage = "ָ���˲�֧�ֵĵص�ά�����ͣ�ϵͳ���ܴ���";
            }
        }
        setErrorMessage("ά������", errorMessage);
        return (errorMessage.length() == 0);
    }

    /**
     * ���ܣ����ص�����Ƿ�Ϸ�����Ҫ������¹�����
     * 1���ص�����Ƿ�ǿգ�
     * 2���ص�����Ƿ��ظ�(Excel)��
     * 3���ص�����Ƿ������ݿ��ظ���
     * <P>ע�⣺���������ƶ��������߼�</P>
     *
     * @throws QueryException ������ݳ���ʱ�׳���ѯ�쳣
     */
    private void checkObjectCode() throws QueryException {

        boolean checkResult = true;
        String errorMessage = "";
        String locationCode = lineDTO.getWorkorderObjectCode();
        
        if (!lineDTO.isAddLocation()) {
        	if (StrUtil.isEmpty(locationCode)) {
                errorMessage = "�ص���벻��Ϊ��";
                checkResult = false;
            }
            if (checkResult) {            
                if (locationCode.length() == 14) {
                    
                } else {
                    if (errorMessage.length() > 0) {
                        errorMessage += ",";
                    }
                    errorMessage += "����ص�ڶ��δ��롰"
                            + lineDTO.getWorkorderObjectCode()
                            + "����ʽ����ȷ�������Թ�˾���뿪ʼ���Ұ����ص�רҵ���룬����Ϊ14���ַ�";
                    checkResult = false;
                }
            }
            if (checkResult) {
                if (isObjectCodeExist()) {
                    if (lineDTO.isDisableLocation()) {
                        if (hasAssetsUnderLocation()) {
                            if (errorMessage.length() > 0) {
                                errorMessage += ",";
                            }
                            errorMessage += "����Ϊ��"
                                    + lineDTO.getWorkorderObjectCode()
                                    + "���ĵص������ʲ�������ʧЧ��";
                        }
                    }
                } else {
                    if (lineDTO.isUpdateLocation()) {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "Ҫ�޸ĵĵص���롰"
                                + lineDTO.getWorkorderObjectCode()
                                + "��������";
                    } else if (lineDTO.isDisableLocation()) {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "ҪʧЧ�ĵص���롰"
                                + lineDTO.getWorkorderObjectCode()
                                + "��������";
                    }
                }
            }
        } else if (StrUtil.isNotEmpty(locationCode)) {
        	errorMessage = "����ʱ�ص�ڶ��δ��벻������";
            checkResult = false;
        }
        setErrorMessage("�ص����", errorMessage);
    }

    /**
     * ���ܣ����ص������Ƿ�Ϸ�����Ҫ������¹�����
     * 1���ص������Ƿ�ǿգ�
     * 2���ص������Ƿ��ظ�(Excel�����߼���δ����)��
     * 3���ص������Ƿ������ݿ��ظ�(���߼���δ����)��
     *
     * @throws QueryException ������ݳ���ʱ�׳���ѯ�쳣
     * @throws ContainerException 
     */
    private void checkObjectName() throws QueryException, ContainerException {
        boolean checkResult = true;
        String errorMessage = "";
        String objectName = lineDTO.getWorkorderObjectName();
        if (!lineDTO.isDisableLocation()) {
            if (StrUtil.isEmpty(objectName)) {
                errorMessage += "�ص���������Ϊ��";
                checkResult = false;
            } 
            
            if (!SinoConfig.getProvinceCode().equals(DictConstant.PROVINCE_CODE_JIN)) { //������ɽ������Ҫ
            	if (objectName.indexOf(".") != -1) {
            		errorMessage += "�ص����������С�.��";
                    checkResult = false;
            	}
            }
            
        }
        if (objectName.length() > 0) {
            if (checkResult) {
                String city = lineDTO.getCity();
                city += lineDTO.getCounty();
                objectName = lineDTO.getWorkorderObjectName();
                boolean needCheck = lineDTO.isAddLocation();//���������
                if (!needCheck) {
                    needCheck = city.length() > 0;
                }
                if (needCheck) {
                    if (!objectName.startsWith(city)) {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "�ص�������"
                                + lineDTO.getWorkorderObjectName()
                                + "������С�"
                                + lineDTO.getCity()
                                + "�����ء�"
                                + lineDTO.getCounty()
                                + "�����Ʋ�һ��";
                        checkResult = false;
                    }
                }
                if (hasLocName() && !getLoc2DescByLoc2Code().equals(lineDTO.getWorkorderObjectName())) {
            		errorMessage += "�ص�������"
                        + lineDTO.getWorkorderObjectName()
                        + "��ϵͳ����";
            		checkResult = false;
            	} else if (hasLocNameInOrder()) {
            		errorMessage += "�ص�������"
                        + lineDTO.getWorkorderObjectName()
                        + "��������������";
            		checkResult = false;
            	}
            }            
        }
        setErrorMessage("�ص�����", errorMessage);
    }

    /**
     * ���ܣ�У�鵼��������У��ص������Ƿ��ظ�
     */
    private void checkObjectNameSelfDuplicate() {
        if (lineDTO.getWorkorderObjectName().length() > 0) {
            int dataCount = dataLines.getSize();
            String errorMessage = "";
            String duplicateLine = "";
            for (int i = 0; i < dataCount; i++) {
                EamAddressAddLDTO dto = (EamAddressAddLDTO) dataLines.getDTO(i);
                if (lineDTO.getLineId().equals(dto.getLineId())) {
                    continue;
                }
                if (lineDTO.getWorkorderObjectName().equals(dto.getWorkorderObjectName())) {
                    duplicateLine += String.valueOf(i + 1) + ", ";
                }
            }
            if (duplicateLine.length() > 0) {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage = "�ص�������"
                        + lineDTO.getWorkorderObjectName()
                        + "�����"
                        + duplicateLine.substring(0, duplicateLine.length() - 2)
                        + "�еص������ظ�";
                setErrorMessage("�ص�����", errorMessage);
            }
        }
    }


    /**
     * ���ܣ�������������Ƿ�Ϸ�
     *
     * @throws QueryException ������ݳ���ʱ�׳���ѯ�쳣
     */
    private void checkAreaType() throws QueryException {
        String errorMessage = "";
        if (lineDTO.isAddLocation()) {
            if (StrUtil.isEmpty(lineDTO.getAreaType())) {
                errorMessage = "������������Ϊ��";
            }
        }
        if (!StrUtil.isEmpty(lineDTO.getAreaType())) {
            if (!isAreaTypeExist()) {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage = "����������"
                        + lineDTO.getAreaType()
                        + "��������";
            }
        }
        setErrorMessage("��������", errorMessage);
    }

    /**
     * ���ܣ������������Ƿ�Ϸ�
     *
     * @throws QueryException ������ݳ���ʱ�׳���ѯ�쳣
     */
    private void checkCityAndCounty() throws QueryException {

        String errorMessage = "";
        String city = lineDTO.getCity();
        String county = lineDTO.getCounty();
        if (lineDTO.isAddLocation()) {
            if (StrUtil.isEmpty(city)) {
                errorMessage = "���в���Ϊ��";
            }
            if (StrUtil.isEmpty(county)) {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage += "���ز���Ϊ��";
            }
        }
        if (city.length() > 0) {
            if (!isCityExist()) {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage += "���С�" + city + "��������";
            }
        }
        if (county.length() > 0) {
            if (!isCountyExist()) {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage += "���ء�" + county + "�������ڣ����߲��ڵ��С�" + city + "����";
            }
        }
        setErrorMessage("����������", errorMessage);
    }

    /**
     * ���ܣ����ص�רҵ�Ƿ�Ϸ�
     */
    private void checkObjectCategory() {
        String errorMessage = "";
        String objectCategory = lineDTO.getObjectCategory();
        if (lineDTO.isAddLocation()) {
            if (StrUtil.isEmpty(objectCategory)) {
                errorMessage = "רҵ���벻��Ϊ��";
            }
        }
        if (StrUtil.isNotEmpty(objectCategory)) {
            if (objectCategory.length() != 2) {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage += "רҵ���롰"
                        + lineDTO.getObjectCategory()
                        + "��λ������ȷ";
            }
            if (!lineDTO.isAddLocation() && StrUtil.isNotEmpty(lineDTO.getWorkorderObjectCode())) {
            	if (lineDTO.getWorkorderObjectCode().indexOf(lineDTO.getObjectCategory()) != 4) { //��֤רҵ�����Ƿ��ڵص�����д���
                    if (errorMessage.length() > 0) {
                        errorMessage += ",";
                    }
                    String objectCategory2 = lineDTO.getWorkorderObjectCode();
                    objectCategory2 = objectCategory2.substring(4, 6);
                    errorMessage += "רҵ���롰"
                            + objectCategory
                            + "����ص���롰"
                            + lineDTO.getWorkorderObjectCode()
                            + "���е�רҵ���롰"
                            + objectCategory2
                            + "����ƥ��";
                }
            }
            
            if (!isObjectCategoryExist()) {      //�жϵص����Ĵ�����
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage += "רҵ���롰"
                        + objectCategory
                        + "��������";
            }
        }
        setErrorMessage("�ص�רҵ", errorMessage);
    }

    /**
     * ���ܣ�����վ��Ż�Ӫҵ������Ƿ�Ϸ�
     *
     * @throws QueryException ����վ��Ӫҵ����ų���ʱ�׳���ѯ�쳣
     */
    private void checkBTSOrBizHall() throws QueryException {
        String errorMessage = "";
        String objectCategory = lineDTO.getObjectCategory();
        if (objectCategory.equalsIgnoreCase("JZ") || objectCategory.equalsIgnoreCase("YY")) {
            String btsNo = lineDTO.getBtsNo();
            if (!btsNo.equals("")) {
                String objectCode = getObjectCodeByBTSNo();//��ȡ��վ��Ŷ�Ӧ�ĵص�ڶ��α��룻
                if (objectCode.length() > 0) {
                    String locationCode = lineDTO.getWorkorderObjectCode();
                    if (!objectCode.equals(locationCode)) {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage = "��վ��š�"
                                + btsNo
                                + "�������ݿ��ж�Ӧ�ĵص����ڶ����ǡ�"
                                + objectCode
                                + "�������㵼��ĵص���롰"
                                + lineDTO.getWorkorderObjectCode()
                                + "���ĵڶ��Ρ�"
                                + locationCode
                                + "��������";
                    }
                }
            } else {//Excelδ�����վ���
                if (lineDTO.isAddLocation()) {
                    if (objectCategory.equalsIgnoreCase("JZ")) {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage = "��վ��Ų���Ϊ��";
                    } else {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage = "Ӫҵ����Ų���Ϊ��";
                    }
                }
            }
            setErrorMessage("��վ��Ӫҵ�����", errorMessage);
        }
    }

    /**
     * ���ܣ�У�鵼��������У���վ�����Ƿ��ظ�
     */
    private void checkBTSNOSelfDuplicate() {
    	String objectCategory = lineDTO.getObjectCategory();
    	if (objectCategory.equalsIgnoreCase("JZ") || objectCategory.equalsIgnoreCase("YY")) {
	        if (lineDTO.getBtsNo().length() > 0) {
	            int dataCount = dataLines.getSize();
	            String errorMessage = "";
	            String duplicateLine = "";
	            for (int i = 0; i < dataCount; i++) {
	                EamAddressAddLDTO dto = (EamAddressAddLDTO) dataLines.getDTO(i);
	                if (lineDTO.getLineId().equals(dto.getLineId())) {
	                    continue;
	                }
	                if (lineDTO.getBtsNo().equals(dto.getBtsNo())) {
	                    duplicateLine += String.valueOf(i + 1) + ", ";
	                }
	            }
	            if (duplicateLine.length() > 0) {
	                if (errorMessage.length() > 0) {
	                    errorMessage += ",";
	                }
	                String btsStr = "Ӫҵ��";
	                if (objectCategory.equals("JZ")) {
	                	btsStr = "��վ";
	                } 	                
	                errorMessage = btsStr + "��š�"
	                        + lineDTO.getBtsNo()
	                        + "�����"
	                        + duplicateLine.substring(0, duplicateLine.length() - 2)
	                        + "��" + btsStr + "����ظ�";
	                setErrorMessage(btsStr + "���", errorMessage);
	            }
	        }
    	}
    }

    /**
     * ���ܣ�У�鵼��������У��Ƿ����Ƿ�Ϸ�
     */
    private void checkShareType() {
    	String shareType = lineDTO.getShareType();
        String errorMessage = "";
        if (StrUtil.isEmpty(shareType)) {
            errorMessage = "�Ƿ�����Ϊ��";
        } else {
        	if(shareType.equals("1") || shareType.equals("2") || shareType.equals("3"))  {
        		
        	} else {
        		errorMessage = "ָ���˲�֧�ֵ��Ƿ������ͣ�ϵͳ���ܴ���";
        	}
        }
        setErrorMessage("�Ƿ���", errorMessage);
    }
    
    /**
     * ���ܣ�������������ȡ�ɱ����ı����к�
     *
     * @return �ɱ����ı����к�
     * @throws QueryException �׳���ѯ�쳣
     */
    private String getCountyCodeByAreaCode() throws QueryException {
        String countyCode = "";
        try {
            SQLModel sqlModel = modelProducer.getCountyCodeByAreaCodeModel();
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
     * ���ܣ�У����ETS_OBJECT�Ƿ����objectCode
     *
     * @return ture��ʾ���ڣ�false��ʾ�����ڡ�
     */
    private boolean isObjectCodeExist() {
        boolean hasBarcode = false;
        try {
            SQLModel sqlModel = modelProducer.getObjectCodeExistModel();
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
     * ����:����վ��Ӫҵ������Ƿ����
     *
     * @return String ����BTS�����ϵͳ�ж�Ӧ�ĵص����
     * @throws QueryException ��ȡ�ص�������ʱ�׳����쳣
     */
    private String getObjectCodeByBTSNo() throws QueryException {
        String objectCode = "";
        try {
            SQLModel sqlModel = modelProducer.getObjectCodeByBTSNoModel();
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                Row row = simpleQuery.getFirstRow();
                objectCode = row.getStrValue("LOC2_CODE");
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return objectCode;
    }

    /**
     * ���ܣ��������Ƿ����
     *
     * @return boolean trueb��ʾ���ڣ�false��ʾ������
     * @throws QueryException ������ݳ���ʱ�׳���ѯ�쳣
     */
    private boolean isCityExist() throws QueryException {
        SQLModel sqlModel = modelProducer.getCityExistModel();
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.hasResult();
    }

    /**
     * ���ܣ���������Ƿ����
     *
     * @return boolean trueb��ʾ���ڣ�false��ʾ������
     * @throws QueryException ������ݳ���ʱ�׳���ѯ�쳣
     */
    private boolean isCountyExist() throws QueryException {
        SQLModel sqlModel = modelProducer.getCountyExistModel();
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.hasResult();
    }


    /**
     * ���ܣ�У����ETS_OBJECT �ĵص�����Ƿ��ظ�
     */
    private void checkObjectCodeSelfDuplicate() {
        if (lineDTO.getWorkorderObjectCode().length() > 0) {
            int dataCount = dataLines.getSize();
            String errorMessage = "";
            String duplicateLine = "";
            for (int i = 0; i < dataCount; i++) {
                EamAddressAddLDTO dto = (EamAddressAddLDTO) dataLines.getDTO(i);
                if (lineDTO.getLineId().equals(dto.getLineId())) {
                    continue;
                }
                if (lineDTO.getWorkorderObjectCode().equals(dto.getWorkorderObjectCode())) {
                    duplicateLine += String.valueOf(i + 1) + ", ";
                }
            }
            if (duplicateLine.length() > 0) {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage = "�ص�������"
                        + duplicateLine.substring(0, duplicateLine.length() - 2)
                        + "�еص�����ظ�";
                setErrorMessage("�ص����", errorMessage);
            }
        }
    }


    /**
     * ���ܣ�У����ETS_OBJECT�Ƿ����objectCode
     *
     * @return ture��ʾ���ڣ�false��ʾ�����ڡ�
     */
    private int getObjectCodeOrganizationId() {
        int existCount = 0;
        try {
            SQLModel sqlModel = modelProducer.getObjectCodeOrganizationIdModel();
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
     * @return true��ʾ���ڣ�false��ʾ������
     */
    private boolean isObjectCategoryExist() {
        boolean hasBarcode = false;
        try {
            SQLModel sqlModel = modelProducer.getObjectCategoryExistModel();
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
     * ���ܣ�У����AMS_OBJECT_IMPORT�Ƿ����AreaType
     *
     * @return ��ture��ʾ����ڣ�false��ʾ�����ڡ�
     */
    private boolean isAreaTypeExist() throws QueryException {
        boolean hasAreaType = true;
        SQLModel sqlModel = modelProducer.getAreaTypeExistModel();
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        if (!simpleQuery.hasResult()) {
            hasAreaType = false;
        }
        return hasAreaType;
    }

    /**
     * ���ܣ���¼�ض���У��ķǷ�������Ϣ
     *
     * @throws DataHandleException ��¼У����Ϣ����ʱ�׳����쳣
     */
    private void logImportError() throws DataHandleException {
        SQLModel sqlModel = modelProducer.getImportErrorLogModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ��жϵص����Ƿ����ʲ�
     *
     * @return true��ʾ���ʲ���false��ʾ���ʲ�
     * @throws QueryException ���ص����Ƿ����ʲ�����ʱ�׳����쳣
     */
    private boolean hasAssetsUnderLocation() throws QueryException {
        SQLModel sqlModel = modelProducer.getLocationAssetsModel();
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.hasResult();
    }
    
    /**
     * ���ܣ�У�鵼��ص�ڶ��������Ƿ����
     *
     * @return true��ʾ���ڣ�false��ʾ������
     * @throws QueryException ���ص����Ƿ����ʲ�����ʱ�׳����쳣
     */
    private boolean hasLocName() throws QueryException {
        SQLModel sqlModel = modelProducer.getIsExistWorkorderObjectNameModel();
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.hasResult();
    }
    
    /**
     * ���ܣ���������ص�����ȡ����ص�����
     * @return ����ص�����
     */
    private String getLoc2DescByLoc2Code() throws QueryException, ContainerException {
    	SQLModel sqlModel = modelProducer.getLoc2DescByLoc2CodeModel();
    	simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.getFirstRow().getStrValue("LOC2_DESC");
    }

    /**
     * ���ܣ�У�鵼��ص�ڶ��������Ƿ���ڵ����У�����״̬Ϊ�ݴ桢������
     *
     * @return true��ʾ���ڣ�false��ʾ������
     * @throws QueryException ���ص����Ƿ����ʲ�����ʱ�׳����쳣
     */
    private boolean hasLocNameInOrder() throws QueryException {
        SQLModel sqlModel = modelProducer.getIsObjectNameInOrderModel();
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.hasResult();
    }
    
    private void setErrorMessage(String errorCode, String errorMessage) {
        if (errorMessage.length() > 0) {
            String message = lineDTO.getErrorMessage();
            if (message.length() > 0) {
                message += "<br>";
            }
            lineDTO.setErrorMessage(message + errorCode + "����" + errorMessage);
        }
    }
}
