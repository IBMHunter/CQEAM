package com.sino.ams.newSite.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.newSite.dto.EamAddressAddLDTO;
import com.sino.ams.newSite.model.EamAddressAddImportModel;
import com.sino.ams.system.user.dao.EtsOuCityMapDAO;
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
public class EamAddressAddImportDAO extends AMSBaseDAO {

    private EamAddressAddImportModel modelProducer = null;
    private SimpleQuery simpleQuery = null;
    private EamAddressAddLDTO lineDTO = null;
    private DTOSet dataLines = null;

    public EamAddressAddImportDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        super.sqlProducer = new EamAddressAddImportModel(userAccount, dtoParameter);
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
            modelProducer = (EamAddressAddImportModel) sqlProducer;
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
                    	String objectName = lineDTO.getWorkorderObjectName();
                    	int startIndex = objectName.indexOf(".");
                        int endIndex = objectName.lastIndexOf(".");
                        if (startIndex > 0 && endIndex == lineDTO.getWorkorderObjectName().length() - 4 && endIndex > startIndex) {
                        	StringBuffer sbObjectName = new StringBuffer(objectName);
                        	//ȥ���ڶ��εص�����ǰ��ո�
                        	lineDTO.setWorkorderObjectName(sbObjectName.replace(startIndex + 1, endIndex,objectName.substring(startIndex + 1,endIndex).trim()).toString());
                        }                        
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
     * 1�����ص���룻
     * 2�����ص�������
     * 3�����������룻
     * 4���������������
     * 5�����������أ�
     * 6�����ص�רҵ��
     * 7�����ص���(�����ڻ�վ��Ż�Ӫҵ�����)
     *
     * @return true��ʾ���м��Ϸ���false��ʾ���ݷǷ���������г��ִ���
     */
    private boolean isDataValid() {
        boolean isObjectValid = false;
        try {
            if (isMaintainTypeValid()) {
                checkObjectCode();
                checkObjectCodeSelfDuplicate();
                checkAreaCode();
                checkObjectName();
                checkObjectNameSelfDuplicate();
                checkObjectLoc2SelfDuplicate();
                checkAreaType();
                checkCityAndCounty();
                checkObjectCategory();
                checkBTSOrBizHall();
                checkBTSNOSelfDuplicate();
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
        String provinceCode = SinoConfig.getProvinceCode();
        String locationCode = lineDTO.getWorkorderObjectCode();
        EtsOuCityMapDAO etsOuCityMapDAO = new EtsOuCityMapDAO(userAccount, null, conn);
		String companyCode = etsOuCityMapDAO.getCompanyCode(userAccount.getOrganizationId());
        if (StrUtil.isEmpty(locationCode)) {
            errorMessage = "�ص���벻��Ϊ��";
            checkResult = false;
        } else if (locationCode.indexOf(" ")!=-1) {
        	errorMessage = "�ص�����в����пո�";
            checkResult = false;
        }
        if (checkResult) {
            int startIndex = locationCode.indexOf(".");
            int endIndex = locationCode.lastIndexOf(".");
            if (startIndex == 6 && endIndex == 21 && locationCode.length() == 25) {
                locationCode = locationCode.substring(0, startIndex);
                if (lineDTO.getCountyCode().length() > 0 || lineDTO.isAddLocation()) {
                    if (!locationCode.equals(lineDTO.getCountyCode())) {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "����ص���롰"
                                + lineDTO.getWorkorderObjectCode()
                                + "����һ�Ρ�"
                                + locationCode
                                + "����������롰"
                                + lineDTO.getCountyCode()
                                + "����һ��";
                        checkResult = false;
                    }
//                    if (!StrUtil.isNumber(lineDTO.getWorkorderObjectCode().substring(13,endIndex))) {
//                    	errorMessage += "����ص���롰"
//		                            + lineDTO.getWorkorderObjectCode()
//		                            + "���ڶ��Ρ�"
//		                            + lineDTO.getWorkorderObjectCode().substring(7, endIndex)
//		                            + "���λӦ��Ϊ����";
//                        checkResult = false;
//                    }
                }
                if (!lineDTO.getWorkorderObjectCode().substring(7, 11).equals(companyCode)) {
                	errorMessage += "����ص���롰"
                        + lineDTO.getWorkorderObjectCode()
                        + "���ڶ��Ρ�"
                        + lineDTO.getWorkorderObjectCode().substring(7, endIndex)
                        + "��ǰ��λ��"
                        + lineDTO.getWorkorderObjectCode().substring(7, 11)
                        + "���빫˾���롰"
                        + companyCode
                        + "����һ��";
                	checkResult = false;
                }
            } else {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage += "����ص���롰"
                        + lineDTO.getWorkorderObjectCode()
                        + "����ʽ����ȷ��������������뿪ʼ���Ұ��������㣬����Ϊ25���ַ�";
                checkResult = false;
            }
        }
        if (checkResult) {
        	if (lineDTO.isAddLocation()) {
            	if (isObjectCodeExistInOrder("WORKORDER_OBJECT_CODE")) {
            		if (errorMessage.length() > 0) {
                        errorMessage += ",";
                    }
            		errorMessage += "�ص���롰"
                        + lineDTO.getWorkorderObjectCode()
                        + "��������������";
            	}
        	}
        	
            if (isObjectCodeExist()) { 
            	if (lineDTO.isAddLocation()) {
                    if (provinceCode.equals(DictConstant.PROVINCE_CODE_NM)) {//���������
                        if (getObjectCodeOrganizationId() == userAccount.getOrganizationId()) {
                            if (errorMessage.length() > 0) {
                                errorMessage += ",";
                            }
                            errorMessage += "����ص���롰"
                                    + lineDTO.getWorkorderObjectCode()
                                    + "�������ݿ����Ѵ���";
                        }
                    } else {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "����ص���롰"
                                + lineDTO.getWorkorderObjectCode()
                                + "�������ݿ����Ѵ���";
                    }
                } else if (lineDTO.isDisableLocation()) {
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
        setErrorMessage("�ص����", errorMessage);
    }


    /**
     * ���ܣ������������Ƿ�Ϸ�
     *
     * @throws QueryException ������ݳ���ʱ�׳���ѯ�쳣
     */
    private void checkAreaCode() throws QueryException {
        boolean checkResult = true;
        String errorMessage = "";
        String areaCode = lineDTO.getCountyCode();
        String provinceCode = SinoConfig.getProvinceCode();
        if (lineDTO.isAddLocation()) {
            if (StrUtil.isEmpty(areaCode)) {
                errorMessage += "����������Ϊ��";
                checkResult = false;
            }
        }
        if (areaCode.length() > 0) {
            if (areaCode.length() != 6) {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage += "����������롰"
                        + areaCode
                        + "��λ������6λ";
                checkResult = false;
            } else if (!StrUtil.isNumber(areaCode)) {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage += "����������벻�ǡ�"
                        + areaCode
                        + "������";
                checkResult = false;
            } 
//            else {
//                if (!provinceCode.equals(DictConstant.PROVINCE_CODE_JIN)) {
//                    String preAreaCode = areaCode.substring(0, 4);
//                    if (!preAreaCode.equals(userAccount.getCompanyCode())) {
//                        if (errorMessage.length() > 0) {
//                            errorMessage += ",";
//                        }
//                        errorMessage += "����������롰"
//                                + areaCode
//                                + "���Ƿ�����ע�⣬������������ʼ�ڹ�˾���롰"
//                                + userAccount.getCompanyCode()
//                                + "��������ܵ�����������˾�ĵص�";
//                        checkResult = false;
//                    }
//                }
//            }
            if (checkResult) {
                if (!isAreaCodeExist()) {      //�ж���������Ĵ�����
                    if (!provinceCode.equals(DictConstant.PROVINCE_CODE_JIN)) {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "����������롰"
                                + areaCode
                                + "�������ڹ�˾�򲻴���";
                    } else {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "����������롰"
                                + areaCode
                                + "��������";
                    }
                }
            }
        }
        setErrorMessage("�������", errorMessage);
    }

    /**
     * ���ܣ����ص������Ƿ�Ϸ�����Ҫ������¹�����
     * 1���ص������Ƿ�ǿգ�
     * 2���ص������Ƿ��ظ�(Excel�����߼���δ����)��
     * 3���ص������Ƿ������ݿ��ظ�(���߼���δ����)��
     *
     * @throws QueryException ������ݳ���ʱ�׳���ѯ�쳣
     */
    private void checkObjectName() throws QueryException {
        boolean checkResult = true;
        String errorMessage = "";
        String objectName = lineDTO.getWorkorderObjectName();
        int startIndex = objectName.indexOf(".");
        int endIndex = objectName.lastIndexOf(".");
        if (!lineDTO.isDisableLocation()) {
            if (StrUtil.isEmpty(objectName)) {
                errorMessage += "�ص���������Ϊ��";
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
            String areaCode = lineDTO.getCountyCode();
            if (lineDTO.isAddLocation() || areaCode.length() > 0) {
                if (startIndex > -1 && endIndex > startIndex) {
                    objectName = objectName.substring(0, startIndex);
                    String location = getFirstSegmentDescByAreaCode();
                    if (!objectName.equals(location)) {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "����ĵص�������"
                                + lineDTO.getWorkorderObjectName()
                                + "���ĵ�һ�Ρ�"
                                + objectName
                                + "����������롰"
                                + lineDTO.getCountyCode()
                                + "����Ӧ��������"
                                + location
                                + "����һ��";
                        checkResult = false;
                    }
                }
            }
            if (checkResult) {
//                if (startIndex > 0 && endIndex == lineDTO.getWorkorderObjectName().length() - 3 && endIndex > startIndex) {
            	if (startIndex > 0 && endIndex > startIndex) {
                    String city = lineDTO.getCity();
                    city += lineDTO.getCounty();
                    objectName = lineDTO.getWorkorderObjectName().substring(startIndex + 1);
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
                        } else if (isObjectCodeExistInOrder("WORKORDER_OBJECT_NAME")) {
                    		if (errorMessage.length() > 0) {
                                errorMessage += ",";
                            }
                    		errorMessage += "�ص�������"
                                + lineDTO.getWorkorderObjectName()
                                + "����������������";
                    	}
                    }
                } else {
                    if (errorMessage.length() > 0) {
                        errorMessage += ",";
                    }
                    errorMessage += "�ص�������"
                            + lineDTO.getWorkorderObjectName()
                            + "�����淶";
                    checkResult = false;
                }
            }
            if (checkResult) {
                String locCode = getLocCode2ByLocDesc();
                String locDesc = getLocDesc2ByLocCode();
                objectName = lineDTO.getWorkorderObjectName();
                objectName = objectName.substring(startIndex + 1, endIndex);
                String locationCode = lineDTO.getWorkorderObjectCode();
                startIndex = locationCode.indexOf(".");
                endIndex = locationCode.lastIndexOf(".");
                locationCode = locationCode.substring(startIndex + 1, endIndex);
                if (locCode.length() > 0) {
                	if (!locCode.equals(locationCode)) {
                        if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "�ص�������"
                                + lineDTO.getWorkorderObjectName()
                                + "���ĵڶ��Ρ�"
                                + objectName
                                + "����ϵͳ�ж�Ӧ�ı���Ϊ��"
                                + locCode
                                + "��,���㵼��ı��롰"
                                + lineDTO.getWorkorderObjectCode()
                                + "���ĵڶ��Ρ�"
                                + locationCode
                                + "����һ��";
                    }
                }
                if (locDesc.length() > 0 && lineDTO.isAddLocation()) {
                	if(!locDesc.equals(objectName)) {
                   	 if (errorMessage.length() > 0) {
                            errorMessage += ",";
                        }
                        errorMessage += "�ص���롰"
                                + lineDTO.getWorkorderObjectCode()
                                + "���ĵڶ��Ρ�"
                                + locationCode
                                + "����ϵͳ�ж�Ӧ������Ϊ��"
                                + locDesc
                                + "��,���㵼���������"
                                + lineDTO.getWorkorderObjectName()
                                + "���ĵڶ��Ρ�"
                                + objectName
                                + "����һ��";
                   } 
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
     * ����:У������ص������ͬ����������ͬ������ص�������ͬ���벻ͬ
     */
    private void checkObjectLoc2SelfDuplicate() {
    	if (!lineDTO.isDisableLocation() && lineDTO.getWorkorderObjectCode().length() > 0 && lineDTO.getWorkorderObjectName().length() > 0) {
    		String objectCode = lineDTO.getWorkorderObjectCode();
    		String objectName = lineDTO.getWorkorderObjectName();
            int codeStartIndex = objectCode.indexOf(".");
            int codeEndIndex = objectCode.lastIndexOf(".");
    		int nameStartIndex = objectName.indexOf(".");
            int nameEndIndex = objectName.lastIndexOf(".");
            String loc2Code = objectCode.substring(7, codeEndIndex);
            String loc2Name = objectName.substring(nameStartIndex + 1, nameEndIndex);
            int dataCount = dataLines.getSize();
            String errorMessage = "";
            String codeDuplicateLine = "";
            String nameDuplicateLine = "";
            
        	if (codeStartIndex == 6 && codeEndIndex == 21 && objectCode.length() == 25) {
        		for (int i = 0; i < dataCount; i++) {
                    EamAddressAddLDTO dto = (EamAddressAddLDTO) dataLines.getDTO(i);
                    if (lineDTO.getLineId().equals(dto.getLineId())) {
                        continue;
                    }
                    if (StrUtil.isNotEmpty(dto.getWorkorderObjectCode())) {
                    	if (loc2Code.equals(dto.getWorkorderObjectCode().substring(7,codeEndIndex)) && 
                        		!loc2Name.equals(dto.getWorkorderObjectName().substring(dto.getWorkorderObjectName().indexOf(".") + 1, dto.getWorkorderObjectName().lastIndexOf(".")))) {
                        	codeDuplicateLine += String.valueOf(i + 1) + ", ";
                        }
                        if (!loc2Code.equals(dto.getWorkorderObjectCode().substring(7,codeEndIndex)) && 
                        		loc2Name.equals(dto.getWorkorderObjectName().substring(dto.getWorkorderObjectName().indexOf(".") + 1, dto.getWorkorderObjectName().lastIndexOf(".")))) {
                        	nameDuplicateLine += String.valueOf(i + 1) + ", ";
                        }
                    }
                }        		
        	}
            
            if (codeDuplicateLine.length() > 0) {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage = "����ص���롰"
                        + loc2Code
                        + "����Ӧ������ص��������"
                        + codeDuplicateLine.substring(0, codeDuplicateLine.length() - 2)
                        + "������ص�����Ӧ������ص���������ͬ";
                setErrorMessage("�ص�����", errorMessage);
            }
            
            if (nameDuplicateLine.length() > 0) {
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                errorMessage = "����ص�������"
                        + loc2Name
                        + "����Ӧ������ص�������"
                        + nameDuplicateLine.substring(0, nameDuplicateLine.length() - 2)
                        + "������ص�������Ӧ������ص���벻��ͬ";
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
            if (lineDTO.getWorkorderObjectCode().indexOf(lineDTO.getObjectCategory()) != 11) { //��֤רҵ�����Ƿ��ڵص�����д���
                if (errorMessage.length() > 0) {
                    errorMessage += ",";
                }
                String objectCategory2 = lineDTO.getWorkorderObjectCode();
                if(objectCategory2.length() > 13){
                    objectCategory2 = objectCategory2.substring(11, 13);
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
                    int startIndex = locationCode.indexOf(".");
                    int endIndex = locationCode.lastIndexOf(".");
                    if (startIndex == 6 && endIndex == 21 && locationCode.length() == 25) {
                        locationCode = locationCode.substring(startIndex + 1, endIndex);
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
     * ���ܣ�У����ETS_OBJECT�Ƿ����objectCode
     *
     * @return ture��ʾ���ڣ�false��ʾ�����ڡ�
     */
    private boolean isObjectCodeExistInOrder(String str) {
        boolean hasBarcode = false;
        try {
            SQLModel sqlModel = modelProducer.getObjectCodeExistInOrderModel(str);
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
     * ���ܣ���鵼�����������Ƿ���ڡ�
     *
     * @return ture ��ʾ������ȷ(����ou)��false ��ʾ�����д�
     * @throws QueryException ��鵼�����������Ƿ���ڳ���ʱ�׳���ѯ�쳣
     */
    private boolean isAreaCodeExist() throws QueryException {
        SQLModel sqlModel = modelProducer.getAreaCodeExistModel();
        simpleQuery.setSql(sqlModel);
        simpleQuery.executeQuery();
        return simpleQuery.hasResult();
    }

    /**
     * ���ܣ�У�鵼��ĵص������ĵ�һ���Ƿ��뵼������������Ӧ������һ��
     *
     * @return true��ʾ�ص�Ϸ���false��ʾ�ص�Ƿ�
     * @throws QueryException
     */
    private String getFirstSegmentDescByAreaCode() throws QueryException {
        String location = "";
        try {
            SQLModel sqlModel = modelProducer.getAreaCodeExistModel();
            simpleQuery.setSql(sqlModel);
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                Row row = simpleQuery.getFirstRow();
                location = row.getStrValue("DESCRIPTION");
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new QueryException(ex.getMessage());
        }
        return location;
    }

    private String getLocCode2ByLocDesc() throws QueryException {
        String locCode2 = "";
        try {
            SQLModel sqlModel = modelProducer.getLocCode2ByDescModel();
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

    private String getLocDesc2ByLocCode() throws QueryException {
        String locDesc2 = "";
        try {
            SQLModel sqlModel = modelProducer.getLocDesc2ByDescModel();
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
