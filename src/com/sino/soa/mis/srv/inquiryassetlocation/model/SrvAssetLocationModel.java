package com.sino.soa.mis.srv.inquiryassetlocation.model;


import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.util.IntegerUtil;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.soa.mis.srv.inquiryassetlocation.dto.SrvAssetLocationDTO;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>Title: SrvAssetCategoryModel</p>
 * <p>Description:�����Զ�����SQL��������SrvAssetCategoryModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author wzp
 * @version 1.0
 *          DES:ͬ���ʲ��ص�
 */


public class SrvAssetLocationModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ��ʲ������� SRV_ASSET_CATEGORY ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SrvAssetCategoryDTO ���β���������
     */
    public SrvAssetLocationModel(SfUserDTO userAccount, SrvAssetLocationDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ������ʲ������� SRV_ASSET_CATEGORY���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() throws SQLModelException, RuntimeException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SrvAssetLocationDTO srvAssetCategory = (SrvAssetLocationDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " ETS_FA_ASSETS_LOCATION("
                + " BOOK_TYPE_CODE,"
                + " ASSETS_LOCATION,"
                + " ORG_ID,"
                + " ASSETS_LOCATION_CODE,"
                + " ENABLED_FLAG,"
                + " CREATED_BY,"
                + " CREATION_DATE,"
                + " LAST_UPDATE_BY,"
                + " LAST_UPDATE_DATE"
                + ") VALUES ("
                + " ?, ?, ?, ?, ?, ?, GETDATE(), ?, GETDATE())";

        sqlArgs.add(srvAssetCategory.getBookTypeCode());   //
        sqlArgs.add(srvAssetCategory.getLocationCombinationName());
        sqlArgs.add(IntegerUtil.parseInt(srvAssetCategory.getOrgId()));
        sqlArgs.add(srvAssetCategory.getLocationCombinationCode());
        sqlArgs.add(srvAssetCategory.getEnabledFlag());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(sfUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������ʲ������� SRV_ASSET_CATEGORY���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel �������ݸ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataUpdateModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SrvAssetLocationDTO srvAssetCategory = (SrvAssetLocationDTO) dtoParameter;
        String tmp = srvAssetCategory.getSegment1() + "." + srvAssetCategory.getSegment2() + "." + srvAssetCategory.getSegment3();
        String sqlStr = "UPDATE ETS_FA_ASSETS_LOCATION"
                + " SET"
                //+ " BOOK_TYPE_CODE=?,"
                + " ASSETS_LOCATION=?,"
                //+ " ORG_ID=?,"
                + " ASSETS_LOCATION_CODE=?,"
                + " ENABLED_FLAG=?,"
                + " LAST_UPDATE_BY=?,"
                + " LAST_UPDATE_DATE=GETDATE()"
                + " WHERE ASSETS_LOCATION_CODE=?";
        //sqlArgs.add(srvAssetCategory.getBookTypeCode());
        sqlArgs.add(srvAssetCategory.getLocationCombinationName());
        //sqlArgs.add(srvAssetCategory.getOrgId());
        sqlArgs.add(srvAssetCategory.getLocationCombinationCode());
        sqlArgs.add(srvAssetCategory.getEnabledFlag());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(srvAssetCategory.getLocationCombinationCode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ����:�ж�MIS�̶��ʲ��ص�����Ƿ���ڵص�������ϴ���
     *
     * @return
     */
    public SQLModel getEcouInforModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SrvAssetLocationDTO srvAssetCategory = (SrvAssetLocationDTO) dtoParameter;
        String sqlStr = "SELECT"
                + "	ACD.ASSETS_LOCATION_CODE "
                + "	FROM ETS_FA_ASSETS_LOCATION ACD WHERE ACD.ASSETS_LOCATION_CODE=?";
        sqlModel.setSqlStr(sqlStr);
        sqlArgs.add(srvAssetCategory.getLocationCombinationCode());
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ��ж��Ƿ����д�TD��ַ
     *
     * @return
     */
    public SQLModel isExistTdAssetsLocation() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SrvAssetLocationDTO srvAssetCategory = (SrvAssetLocationDTO) dtoParameter;
        String sqlStr = "SELECT"
                + "	ACD.ASSETS_LOCATION_CODE "
                + "	FROM ETS_FA_ASSETS_LOCATION_TD ACD WHERE ACD.ASSETS_LOCATION_CODE=?";
        sqlModel.setSqlStr(sqlStr);
        sqlArgs.add(srvAssetCategory.getLocationCombinationCode());
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����ݵص����ڶ��λ�ȡOU��֯ID���ʲ��˲�����
     * @param objectCode
     * @return
     */
    public SQLModel getEcomCodeModel(String objectCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT \n" +
                "    EOCM.ORGANIZATION_ID, \n" +
                "    EOCM.BOOK_TYPE_CODE\n" +
                " FROM \n" +
                "    ETS_OU_CITY_MAP EOCM\n" +
                "WHERE \n" +
                "    RIGHT(EOCM.BOOK_TYPE_CODE, 4) = ?";
        int index = objectCode.indexOf(".");
        objectCode = objectCode.substring(index + 1);
        objectCode = objectCode.substring(0, 4);
        sqlArgs.add(objectCode);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����OU��֯��Ϣ���� SRV_OU_ORGANIZATIONҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SrvAssetLocationDTO srvOuOrganization = (SrvAssetLocationDTO) dtoParameter;
        String sqlStr = " SELECT EFA.BOOK_TYPE_CODE,                               "
                + "        EFA.ASSETS_LOCATION,                              "
                + "        EFA.CREATION_DATE,                                "
                + "        EFA.CREATED_BY,                                   "
                + "        EFA.LAST_UPDATE_DATE,                             "
                + "        EFA.LAST_UPDATE_BY,                               "
                + "        EFA.ORG_ID,                                       "
                + "        EFA.ASSETS_LOCATION_CODE,                         "
                + "        EFA.ENABLED_FLAG                                  "
                + "   FROM ETS_FA_ASSETS_LOCATION EFA, ETS_OU_CITY_MAP EO    "
                + "  WHERE EO.BOOK_TYPE_CODE = EFA.BOOK_TYPE_CODE            "
                + "        AND (? IS NULL OR  EO.ORGANIZATION_ID LIKE ? )                  ";
        sqlArgs.add(srvOuOrganization.getOrganizationId());
        sqlArgs.add(srvOuOrganization.getOrganizationId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


}