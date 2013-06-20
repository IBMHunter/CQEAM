package com.sino.ams.newSite.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newSite.dto.EamAddressAddLDTO;
import com.sino.ams.newSite.model.EamAddressAddImportModel_BACK;
import com.sino.ams.system.object.dao.ImportObjectDAO;
import com.sino.ams.system.object.model.ImportObjectModel;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.Connection;

/**
 * @author ���� :wangzhipeng
 * @version ����ʱ�䣺Apr 18, 2011 3:42:18 PM
 *          ��˵�����ص���Ϣ���� DAO
 */
public class EamAddressAddImportDAO_BACK extends AMSBaseDAO {


    public EamAddressAddImportDAO_BACK(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);

    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        super.sqlProducer = new EamAddressAddImportModel_BACK(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�ɾ���ӿڱ�����ݡ�
     * @throws com.sino.base.exception.SQLModelException
     */
    public void deleteImportModel(String transId) throws SQLModelException, QueryException, DataHandleException {
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.deleteImportModel(transId);
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ����ӿڱ����ݵ���Ч�ԡ�
     * �ص�����Ψһ�ԣ��ص����Ƶ�Ψһ�ԣ��ص�רҵ����Ĵ����ԣ��ص����ش���Ĵ�����
     * 0://�ص���� onNetDtlDTO.setWorkorderObjectCode(strValue);
     * 1://�ص����� onNetDtlDTO.setWorkorderObjectName(strValue);
     * 2://�ص�רҵ onNetDtlDTO.setObjectCategory(strValue);
     * 3://�������� onNetDtlDTO.setCountyCode(strValue);  ���ش���
     * 4://������� onNetDtlDTO.setAreaType(strValue);
     * 5://�� onNetDtlDTO.setCity(strValue);
     * 6://���� onNetDtlDTO.setCounty(strValue);
     * 7://ά������ onNetDtlDTO.setAddrMaintainType(strValue);
     */
    public void doVerifyData(DTOSet dtoSet) throws SQLModelException, QueryException {
        if (dtoSet != null && dtoSet.getSize() > 0) {
        	ImportObjectDAO objectDAO = new ImportObjectDAO(userAccount, null, conn);
            String workorderObjectCode = "";
            for (int i = 0; i < dtoSet.getSize(); i++) {
                EamAddressAddLDTO eoDTO = (EamAddressAddLDTO) dtoSet.getDTO(i);
                workorderObjectCode = eoDTO.getWorkorderObjectCode();
                String excelLineId = eoDTO.getExcelLineId();
                String msg = "��" + excelLineId + "�У�";
                if (StrUtil.isEmpty(workorderObjectCode)) {
                    insertEmtyErrorData(workorderObjectCode, eoDTO.getObjectCategory(), eoDTO.getCountyCode(), msg + "�ص���벻��Ϊ��");
                } else if (doubleModel(workorderObjectCode, eoDTO.getOrganizationId(), eoDTO.getTransId())) {
                    insertEmtyErrorData(workorderObjectCode, eoDTO.getObjectCategory(), eoDTO.getCountyCode(), msg + "����ص��������ظ�");
                } else if ((workorderObjectCode.indexOf(".") == -1)) {
                    insertEmtyErrorData(workorderObjectCode, eoDTO.getObjectCategory(), eoDTO.getCountyCode(), msg + "�ص�����ʽ����ȷ");
                } else if (workorderObjectCode.substring(workorderObjectCode.indexOf(".") + 1).substring(0, (workorderObjectCode.substring(workorderObjectCode.indexOf(".") + 1).indexOf("."))).length() != 14) {
                    insertEmtyErrorData(workorderObjectCode, eoDTO.getObjectCategory(), eoDTO.getCountyCode(), msg + "�ص�����м�ı��������14λ��");
                } else if (eoDTO.getAddrMaintainType().trim().equals("ʧЧ")) {
                    if (!existsObject(workorderObjectCode, userAccount.getOrganizationId())) {
                        verifyDisableData(workorderObjectCode, "�ص��´���ʧЧ�ص㣬����ʧЧ");
                    } else if (!existsItem(workorderObjectCode, userAccount.getOrganizationId())){
                        verifyDisableData(workorderObjectCode, "�ص��´����豸������ʧЧ");
                    }
                } else if (eoDTO.getAddrMaintainType().equals("����") && efficientData(workorderObjectCode,userAccount.getCompanyCode())) {        //�жϵص�����Ƿ�Ϸ�
	                insertEmtyErrorData(workorderObjectCode, eoDTO.getObjectCategory(), eoDTO.getCountyCode(), msg+"�ص�����Ѵ���");

//����ʤע�ͣ�������
//	            } else if (!objectDAO.isObjectNameValid(eoDTO.getCountyCode(), eoDTO.getWorkorderObjectName())) {
//	            	insertEmtyErrorData(workorderObjectCode, eoDTO.getObjectCategory(), eoDTO.getCountyCode(), msg+"�ص�������һ������������Ӧ��������һ��");

                } else if (!("����").equals(eoDTO.getAddrMaintainType()) && (!efficientData(workorderObjectCode,userAccount.getCompanyCode()))) {
	            	insertEmtyErrorData(workorderObjectCode, eoDTO.getObjectCategory(), eoDTO.getCountyCode(), msg+"�ص���벻����");
	            }
                if (StrUtil.isEmpty(eoDTO.getWorkorderObjectName())) {
                    logImportError(workorderObjectCode, msg + "�ص���������Ϊ��");
                } else if (eoDTO.getAddrMaintainType().equals("����") && isExistWorkorderObjectName(eoDTO.getWorkorderObjectName())) {
                	logImportError(workorderObjectCode, msg + "�ص������Ѿ�����");
                }
                                
                if (StrUtil.isEmpty(eoDTO.getCountyCode())) {
                    logImportError(eoDTO.getCountyCode(), msg + "����������벻��Ϊ��");
                } else if(!StrUtil.isNumber(String.valueOf(eoDTO.getCountyCode()))) {
                	logImportError(eoDTO.getCountyCode(), msg + "�������������");
                } else if(!hasCountyModel(String.valueOf(eoDTO.getCountyCode()))) {
                	logImportError(eoDTO.getCountyCode(), msg + "�������򲻴���");
                } else if (StrUtil.isNotEmpty(workorderObjectCode) && !eoDTO.getWorkorderObjectCode().substring(0,6).equals(eoDTO.getCountyCode())) {
                	logImportError(eoDTO.getCountyCode(), msg + "����������ص�����һ�β���ͬ");
                }
                if (StrUtil.isEmpty(eoDTO.getObjectCategory())) {
                    logImportError(eoDTO.getWorkorderObjectCode(), msg + "רҵ���벻��Ϊ��");
                } else if (StrUtil.isNotEmpty(workorderObjectCode) && eoDTO.getWorkorderObjectCode().indexOf(eoDTO.getObjectCategory()) <= 0) {
                	logImportError(eoDTO.getWorkorderObjectCode(), msg + "רҵ������ص�����е�רҵ���벻ƥ��");
                }
                if (eoDTO.getObjectCategory().equals("JZ") || eoDTO.getObjectCategory().equals("YY")) {
                    String btsNo = eoDTO.getBtsNo();
                    if(!btsNo.equals("")){
                        int index = btsNo.indexOf(".");
                        if(index > -1){
                            btsNo = btsNo.substring(0, index);
                        }
                        if (existBtsNo(btsNo)) {
                            logImportError(eoDTO.getObjectCategory(), msg + "��վ��Ӫҵ����Ų����ظ���");
                        }
                    }
                }
                if (StrUtil.isEmpty(eoDTO.getAreaType())) {
                    logImportError(eoDTO.getWorkorderObjectCode(), msg + "����������Ϊ��");
                } else if (!isExistAreaType(eoDTO.getAreaType())) {     //�ж���������Ƿ����
                    logImportError(eoDTO.getWorkorderObjectCode(), msg + "�������򲻴���");
                }
                if (!isExistObjCatgory(eoDTO.getObjectCategory())) {      //�жϵص�רҵ�Ĵ�����
                    logImportError(eoDTO.getWorkorderObjectCode(), msg + "�ص�רҵ���벻����");
                }
                if (StrUtil.isEmpty(eoDTO.getCity())) {
                	logImportError(eoDTO.getWorkorderObjectCode(), msg + "���в���Ϊ��");
                } else if (!isHasCounty(eoDTO.getCity())) {
                    logImportError(eoDTO.getWorkorderObjectCode(), msg + "���в�����");
                }
                if (StrUtil.isEmpty(eoDTO.getCounty())) {
                	logImportError(eoDTO.getWorkorderObjectCode(), msg + "���ز���Ϊ��");
                } else if (!isHasCounty(eoDTO.getCounty())) {
                    logImportError(eoDTO.getWorkorderObjectCode(), msg + "���ز�����");
                }                
                if (StrUtil.isEmpty(eoDTO.getAddrMaintainType())) {
                    logImportError(eoDTO.getWorkorderObjectCode(), msg + "ά�����Ͳ���Ϊ��");
                }
            }
        }
    }










































    /**
     * ���ܣ�����ĵص�����Ϊ��,�ظ����Ѵ��ڵ���Ϣ(������Ϣ)
     */
    public void insertEmtyErrorData(String codeName, String objCategory, String countyCode, String codeError) throws SQLModelException {
        try {
            EamAddressAddImportModel_BACK onNetModel = (EamAddressAddImportModel_BACK) sqlProducer;
            SQLModel sqlModel = onNetModel.insertEmtyErrorData(codeName, objCategory, countyCode, codeError);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (DataHandleException ex) {
            ex.printLog();
        }
    }

    /**
     * ���ܣ�����ĵص����Ƶ�Ϊ�յ���Ϣ�� �ص�רҵ ,�������� ,�������
     */
    private void logImportError(String code, String codeNameError) throws SQLModelException {
        try {
            EamAddressAddImportModel_BACK onNetModel = (EamAddressAddImportModel_BACK) sqlProducer;
            SQLModel sqlModel = onNetModel.getImportErrorLogModel(code, codeNameError);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (DataHandleException ex) {
            ex.printLog();
        }
    }

    public void verifyDisableData(String code, String codeNameError) throws SQLModelException {
        try {
            EamAddressAddImportModel_BACK onNetModel = (EamAddressAddImportModel_BACK) sqlProducer;
            SQLModel sqlModel = onNetModel.getImportErrorLogModel(code, codeNameError);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (DataHandleException ex) {
            ex.printLog();
        }
    }

    /**
     * �ص�����Ƿ����
     * ���ܣ�У����ETS_OBJECT�Ƿ� ture��ʾ����ڣ�false��ʾ�����ڡ�
     * barcode ���ص����       companyCode����˾����
     * @throws com.sino.base.exception.SQLModelException
     */
    public boolean efficientData(String barcode, String companyCode) throws SQLModelException, QueryException {
        boolean hasBarcode = true;
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.noBarModel(barcode, companyCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (!simpleQuery.hasResult()) {
            hasBarcode = false;
        }
        return hasBarcode;
    }

    /**
     * У��ص�����Ƿ��ظ�
     * ���ܣ�У����ETS_OBJECT ���Ƿ����error,ture ��ʾ�����д�false ��ʾ�����޴�
     * @throws com.sino.base.exception.SQLModelException
     */
    public boolean doubleModel(String barcode, int orgsId, String transId) throws SQLModelException, QueryException {
        boolean hasError = false;
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.doubleModel(barcode, orgsId, transId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasError = true;
        }
        return hasError;
    }
    
    public boolean existsObject(String objectCode, int orgId) throws SQLModelException, QueryException {
        boolean hasObject = false;
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.hasObjectModel(objectCode, orgId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasObject = true;
        }
        return hasObject;
    }

    public boolean existsItem(String objectCode, int orgId) throws SQLModelException, QueryException {
        boolean hasObject = false;
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.hasItemModel(objectCode, orgId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasObject = true;
        }
        return hasObject;
    }

    /**
     * ����:����վ��Ӫҵ������Ƿ����
     * @return boolean
     */
    public boolean existBtsNo(String btsNo) {
        boolean exist = false;
        try {
            EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
            SQLModel sqlModel = eoModel.getBtsNoEsistModel(btsNo);
            SimpleQuery simQuery = new SimpleQuery(sqlModel, conn);
            simQuery.executeQuery();
            exist = simQuery.hasResult();
        } catch (QueryException ex) {
            ex.printLog();
        }
        return exist;
    }
    
    /**
     * ����:���ص������Ƿ����
     * @return boolean
     */
    public boolean isExistWorkorderObjectName(String workorderObjectName) {
    	boolean exist = false;
        try {
            EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
            SQLModel sqlModel = eoModel.getIsExistWorkorderObjectNameModel(workorderObjectName);
            SimpleQuery simQuery = new SimpleQuery(sqlModel, conn);
            simQuery.executeQuery();
            exist = simQuery.hasResult();
        } catch (QueryException ex) {
            ex.printLog();
        }
        return exist;
    }

    /**
     * ���ش���Ϸ���У��
     */
    public boolean isExistCountyCode(String countyCode) throws SQLModelException, QueryException {
        boolean hasError = false;
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.IsExistCountyCode(countyCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasError = true;
        }
        return hasError;
    }

    /**
     * ���ܣ��ص�רҵ�Ϸ���У��
     * @throws com.sino.base.exception.SQLModelException
     */
    public boolean isExistObjCatgory(String category) throws SQLModelException, QueryException {
        boolean hasBarcode = true;
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.OCModel(category);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (!simpleQuery.hasResult()) {
            hasBarcode = false;
        }
        return hasBarcode;
    }

    /**
     * ���ܣ���������У��
     */
    public boolean isExistAreaType(String dictCode) throws QueryException {
        boolean hasAreaType = true;
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.isExistAreaType(dictCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (!simpleQuery.hasResult()) {
            hasAreaType = false;
        }
        return hasAreaType;
    }


    /**
     * ���ܣ��������ݴӽӿڱ���ʽ��
     * @throws com.sino.base.exception.SQLModelException
     */
    public void getImpOnNetModel() throws SQLModelException, QueryException, DataHandleException {
        ImportObjectModel eoModel = (ImportObjectModel) sqlProducer;
        SQLModel sqlModel = eoModel.getImpOnNetModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ�ɾ���ӿڱ�����ݡ�
     * @throws com.sino.base.exception.SQLModelException
     */
    public void deleteImportModel() throws SQLModelException, QueryException, DataHandleException {
        ImportObjectModel eoModel = (ImportObjectModel) sqlProducer;
        SQLModel sqlModel = eoModel.deleteImportModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ�����������Ϣ
     * У����EAM_ADDRESS_ADD_L�Ƿ����error, ture ��ʾ�����д�false ��ʾ�����޴�
     * @throws com.sino.base.exception.SQLModelException
     */
    public boolean importHasError(String tranId) throws SQLModelException, QueryException {
        boolean hasError = false;
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.hasErrorModel(tranId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasError = true;
        }
        return hasError;
    }

    /**
     * ����:�ж��Ƿ���ڴ������µļ�¼
     */
    public boolean importExist(String tranId) throws SQLModelException, QueryException {
        boolean hasError = false;
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.hasLineModel(tranId);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            hasError = true;
        }
        return hasError;
    }

    /**
     * ����:�ж��Ƿ���ڴ������µļ�¼
     */
    public boolean isHasCounty(String strCode) throws SQLModelException, QueryException {
        boolean isHas = false;
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.getCountyCode(strCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            isHas = true;
        }
        return isHas;
    }

    /**
     * EAM_ADDRESS_ADD_L  ����У�������ļ�¼
     */
    public DTOSet getImport(String tranId) throws QueryException, SQLModelException {
        EamAddressAddImportModel_BACK eoModel = (EamAddressAddImportModel_BACK) sqlProducer;
        SQLModel sqlModel = eoModel.getQueryImportModel(tranId);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.setDTOClassName(EamAddressAddLDTO.class.getName());
        sq.executeQuery();
        return sq.getDTOSet();
    }

    /**
     * ���ܣ�У�鵼���countyCode�Ƿ����,ture ��ʾ������ȷ(����ou)��false ��ʾ�����д�
     * @throws com.sino.base.exception.SQLModelException
     */
     public boolean hasCountyModel(String countyCode) throws SQLModelException, QueryException {
    	 boolean hasError=false;
        ImportObjectModel eoModel = new ImportObjectModel(userAccount, null);
        SQLModel sqlModel = eoModel.hasCountyModel(countyCode);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           hasError = true;
        }
        return hasError;
    }
}
