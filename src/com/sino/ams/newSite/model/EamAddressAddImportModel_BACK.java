package com.sino.ams.newSite.model;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.newSite.dto.EamAddressAddLDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;
import com.sino.config.SinoConfig;
import com.sino.framework.dto.BaseUserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ���� :wangzhipeng
 * @version ����ʱ�䣺Apr 18, 2011 3:46:55 PM
 *          ��˵��:�ص���Ϣ����
 */
public class EamAddressAddImportModel_BACK extends AMSSQLProducer {

    private SfUserDTO user = null;

    public EamAddressAddImportModel_BACK(BaseUserDTO userAccount, DTO dtoParameter) {
        super(userAccount, dtoParameter);
        this.user = (SfUserDTO) userAccount;
    }

    /**
     * ���ܣ�ɾ���ӿڱ�����ݡ�
     * @return SQLModel �������ݸ�����SQLModel
     * @throws com.sino.base.exception.SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     *                           ����������:Trans_Id
     */
    public SQLModel deleteImportModel(String tranId) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "DELETE FROM"
                + " EAM_ADDRESS_ADD_L"
                + " WHERE"
                + " TRANS_ID = ?";

        sqlArgs.add(tranId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����뵽�ӿڱ� EAM_ADDRESS_ADD_L
     * @return SQLModel �������ݲ�����SQLModel
     * @throws com.sino.base.exception.SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel insertImportModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EamAddressAddLDTO dto = (EamAddressAddLDTO) dtoParameter;
        String sqlStr =
                "INSERT INTO EAM_ADDRESS_ADD_L\n" +
                        "  (LINE_ID,\n" +
                        "   TRANS_ID,\n" +
                        "   WORKORDER_OBJECT_CODE,\n" +
                        "   WORKORDER_OBJECT_NAME,\n" +
                        "   OBJECT_CATEGORY,\n" +
                        "   COUNTY_CODE,\n" +
                        "   AREA_TYPE,\n" +
                        "   CITY,\n" +
                        "   COUNTY,\n" +
                        "   REMARK,\n" +
                        "   ADDR_MAINTAIN_TYPE,\n" +
                        "	BTS_NO,\n" +
                        "   ORGANIZATION_ID) VALUES\n" +
                        "  (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ?)";

        sqlArgs.add(dto.getLineId());
        sqlArgs.add(dto.getTransId());
        sqlArgs.add(dto.getWorkorderObjectCode());
        sqlArgs.add(dto.getWorkorderObjectName());
        sqlArgs.add(dto.getObjectCategory());
        sqlArgs.add(dto.getCountyCode());
        sqlArgs.add(dto.getAreaType());
        sqlArgs.add(dto.getCity());
        sqlArgs.add(dto.getCounty());
        sqlArgs.add(dto.getRemark());
        sqlArgs.add(dto.getAddrMaintainType());
        sqlArgs.add(dto.getBtsNo());
        if (StrUtil.isEmpty(user.getOrganizationId())) {
            sqlArgs.add(0);
        } else {
            sqlArgs.add(user.getOrganizationId());
        }
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * У�飺�жϴ˼�¼���Ƿ���ڴ���ļ�¼
     */
    public SQLModel hasErrorModel(String tranId) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EamAddressAddLDTO eoDTO = (EamAddressAddLDTO) dtoParameter;
        String sqlStr =
                "SELECT 1\n" +
                        "  FROM EAM_ADDRESS_ADD_L A\n" +
                        " WHERE A.TRANS_ID = ?\n " +
                        "  AND A.ORGANIZATION_ID=?\n" +
                        "   AND  " + SyBaseSQLUtil.isNotNull("A.ERROR_MESSAGE") + " ";
        sqlArgs.add(tranId);
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * У�飺�жϴ��������Ƿ���ڼ�¼
     */
    @SuppressWarnings("unchecked")
    public SQLModel hasLineModel(String tranId) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        EamAddressAddLDTO eoDTO = (EamAddressAddLDTO) dtoParameter;
        String sqlStr =
                "SELECT 1\n" +
                        "  FROM EAM_ADDRESS_ADD_L A\n" +
                        " WHERE A.TRANS_ID = ?\n " +
                        "  AND A.ORGANIZATION_ID=?\n";

        sqlArgs.add(tranId);
        sqlArgs.add(userAccount.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ��ȡ���ش���
     * @param strCode
     * @return
     * @throws com.sino.base.exception.SQLModelException
     */
    public SQLModel getCountyCode(String strCode) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT TOP 1 COUNTY_CODE FROM AMS_COUNTY WHERE COUNTY_NAME=?";
        sqlArgs.add(strCode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * У����ڴ�����Ϣ��ͬʱ������ʱ��
     */
    public SQLModel insertEmtyErrorData(String codeName, String objCategory, String countyCode, String codeError)
            throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                " UPDATE EAM_ADDRESS_ADD_L\n" +
                        "   SET ERROR_MESSAGE = ERROR_MESSAGE || ? \n" +
                        " WHERE WORKORDER_OBJECT_CODE = ?";
        sqlArgs.add(codeError);
        sqlArgs.add(codeName);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����ص��Ƿ���ڵ�SQL
     * @param code ���ص����   ��  companyCode����˾����
     * @return SQLModel
     * @throws com.sino.base.exception.SQLModelException
     */
    public SQLModel noBarModel(String code, String companyCode) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1\n" +
                "  FROM ETS_OBJECT      EO,\n" +
                "       ETS_OU_CITY_MAP EOCM\n" +
                " WHERE EO.ORGANIZATION_ID = EOCM.ORGANIZATION_ID\n" +
                "       AND EO.WORKORDER_OBJECT_CODE = ?\n";
        sqlArgs.add(code);

        //����Ҫ��
        String provinceCode = SinoConfig.getProvinceCode();
        if(provinceCode.equals(DictConstant.PROVINCE_CODE_NM)){
        	sqlStr += " AND EOCM.COMPANY_CODE = ?";
        	sqlArgs.add(companyCode);
        }

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * У�鵼������Ƿ��ظ�
     * workorderCode:�ص����
     */
    public SQLModel doubleModel(String workorderCode, int organizationId, String transId) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1\n" +
                "  FROM (SELECT COUNT(*) UM\n" +
                "          FROM EAM_ADDRESS_ADD_L AOI\n" +
                "         WHERE AOI.WORKORDER_OBJECT_CODE = ?\n" +
                "           AND AOI.TRANS_ID = ?\n" +
                "         GROUP BY AOI.WORKORDER_OBJECT_CODE) ACC\n" +
                " WHERE ACC.UM > 1";
        sqlArgs.add(workorderCode);
        sqlArgs.add(transId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel hasObjectModel(String workorderCode, int organizationId) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        //EtsObjectDTO eoDTO = (EtsObjectDTO) dtoParameter;
        String sqlStr =
                "SELECT *\n" +
                        "  FROM ETS_OBJECT\n" +
                        " WHERE WORKORDER_OBJECT_CODE = ?\n" +
                        "   AND ORGANIZATION_ID = ?";

        sqlArgs.add(workorderCode);
        sqlArgs.add(organizationId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    public SQLModel hasItemModel(String workorderCode, int organizationId) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        //EtsObjectDTO eoDTO = (EtsObjectDTO) dtoParameter;
        String sqlStr =
                "SELECT BARCODE\n" +
                "  FROM ETS_ITEM_INFO\n" +
                " WHERE EXISTS (SELECT *\n" +
                "          FROM ETS_OBJECT EO, AMS_OBJECT_ADDRESS AOA\n" +
                "         WHERE EO.WORKORDER_OBJECT_NO = AOA.OBJECT_NO\n" +
                "           AND AOA.ADDRESS_ID = ETS_ITEM_INFO.ADDRESS_ID\n" +
                "           AND EO.WORKORDER_OBJECT_CODE = ?\n" +
                "           AND EO.ORGANIZATION_ID = ?)";

        sqlArgs.add(workorderCode);
        sqlArgs.add(organizationId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ����ص�Ϊ�յļ�¼
     */
    public SQLModel getImportErrorLogModel(String code, String codeNameError) throws
            SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " UPDATE EAM_ADDRESS_ADD_L\n" +
                "   SET ERROR_MESSAGE = ERROR_MESSAGE || ? \n" +
                " WHERE WORKORDER_OBJECT_CODE = ?";
        sqlArgs.add(codeNameError);
        sqlArgs.add(code);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ��õ�����
     * @param dictCode
     */
    public SQLModel isExistAreaType(String dictCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT EFV.CODE, EFV.VALUE FROM \n" +
                "ETS_FLEX_VALUES EFV, ETS_FLEX_VALUE_SET EFVS WHERE EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID AND EFV.ENABLED = 'Y' " +
                "AND EFVS.CODE='ADDR_AREA_TYPE' AND EFV.CODE = ?";
        sqlArgs.add(dictCode);
        sqlModel.setArgs(sqlArgs);
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }

    /**
     * У�����ش���Ϸ���
     */
    public SQLModel IsExistCountyCode(String countyCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                " SELECT A.COUNTY_CODE,\n" +
                        " A.COUNTY_NAME\n" +
                        "  FROM ETS_COUNTY A\n" +
                        " WHERE A.COUNTY_CODE = ?";
        sqlArgs.add(countyCode);
        sqlModel.setArgs(sqlArgs);
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;

    }

    /**
     * ���ܣ�����ص�רҵSQL
     * @param obCategory String
     * @return SQLModel
     * @throws com.sino.base.exception.SQLModelException
     */
    public SQLModel OCModel(String obCategory) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " 1"
                + " FROM"
                + " ETS_FLEX_VALUES EFV,"
                + " ETS_FLEX_VALUE_SET EFVS"
                + " WHERE"
                + " EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
                + " AND EFVS.CODE = 'OBJECT_CATEGORY'"
                + " AND EFV.ATTRIBUTE2=?";
        sqlArgs.add(obCategory);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * EAM_ADDRESS_ADD_L���ش���ļ�¼
     */
    public SQLModel getImportErrorModel(String transId) throws SQLModelException {

        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT EAM.LINE_ID,\n" +
                        "       EAM.TRANS_ID,\n" +
                        "       EAM.WORKORDER_OBJECT_CODE,\n" +
                        "       EAM.WORKORDER_OBJECT_NAME,\n" +
                        "       EAM.OBJECT_CATEGORY,\n" +
                        "       EAM.COUNTY_CODE,\n" +
                        "       EAM.AREA_TYPE,\n" +
                        "       EAM.CITY,\n" +
                        "       EAM.COUNTY,\n" +
                        "       EAM.ORGANIZATION_ID,\n" +
                        "		 EAM.BTS_NO,\n" +
                        "       EAM.ERROR_MESSAGE\n" +
                        "  FROM EAM_ADDRESS_ADD_L EAM\n" +
                        " WHERE EAM.TRANS_ID = ?\n" +
                        "   AND  " + SyBaseSQLUtil.isNotNull("EAM.ERROR_MESSAGE") + " ";

        sqlArgs.add(transId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * EAM_ADDRESS_ADD_L ��ȡ���������ļ�¼
     * ERROR_MESSAGE  ��Ϊ��
     */
    public SQLModel getQueryImportModel(String transId) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT EAM.LINE_ID,\n" +
                        "       EAM.TRANS_ID,\n" +
                        "       EAM.WORKORDER_OBJECT_CODE,\n" +
                        "       EAM.WORKORDER_OBJECT_NAME,\n" +
                        "       EAM.OBJECT_CATEGORY,\n" +
                        "       EAM.COUNTY_CODE,\n" +
                        "       EAM.AREA_TYPE,\n" +
                        "       EAM.CITY,\n" +
                        "       EAM.COUNTY,\n" +
                        "       EAM.ORGANIZATION_ID,\n" +
                        "		 EAM.BTS_NO,\n" +
                        "       EAM.ERROR_MESSAGE\n" +
                        "  FROM EAM_ADDRESS_ADD_L EAM\n" +
                        " WHERE EAM.TRANS_ID = ?\n" +
                        "   AND EAM.ERROR_MESSAGE " + SyBaseSQLUtil.isNullNoParam() + " ";

        sqlArgs.add(transId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ��ȡ��ʱ���е����м�¼ EAM_ADDRESS_ADD_L
     * ������transId
     */
    @SuppressWarnings("unchecked")
    public SQLModel getAllQueryImportModel(String transId) throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr =
                "SELECT EAM.LINE_ID,\n" +
                        "       EAM.TRANS_ID,\n" +
                        "       EAM.WORKORDER_OBJECT_CODE,\n" +
                        "       EAM.WORKORDER_OBJECT_NAME,\n" +
                        "       EAM.OBJECT_CATEGORY,\n" +
                        "       EAM.COUNTY_CODE,\n" +
                        "       EAM.AREA_TYPE,\n" +
                        "       EAM.CITY,\n" +
                        "       EAM.COUNTY,\n" +
                        "       EAM.ORGANIZATION_ID,\n" +
                        "       EAM.ERROR_MESSAGE\n" +
                        "  FROM EAM_ADDRESS_ADD_L EAM\n" +
                        " WHERE EAM.TRANS_ID = ?\n";
        sqlArgs.add(transId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������жϻ�վ��Ӫҵ������Ƿ���ڵ�SQL
     * @return SQLModel
     */
    public SQLModel getBtsNoEsistModel(String btsNo) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " 1"
                + " FROM"
                + " ETS_OBJECT EO"
                + " WHERE"
                + " EO.BTS_NO = ?";
        sqlArgs.add(btsNo);

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * ���ܣ�У�鵼��ص������Ƿ��ظ�
     * @param workorderObjectName
     * @return
     */
    public SQLModel getIsExistWorkorderObjectNameModel(String workorderObjectName) {
    	SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " 1"
                + " FROM"
                + " ETS_OBJECT EO"
                + " WHERE"
                + " EO.WORKORDER_OBJECT_NAME = ?";
        sqlArgs.add(workorderObjectName);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
