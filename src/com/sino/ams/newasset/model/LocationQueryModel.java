package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;

/**
 * <p>Title: OrderQueryModel</p>
 * <p>Description:�����Զ�����SQL��������OrderQueryModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class LocationQueryModel extends AMSSQLProducer {

    /**
     * ���ܣ��ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADER ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsObjectDTO ���β���������
     */
    public LocationQueryModel(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }


    /**
     * ���ܣ�����������ϸ��Ϣ��SQLModel
     * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel getPrimaryKeyDataModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                + " EO.WORKORDER_OBJECT_NO,"
                + " EO.WORKORDER_OBJECT_CODE,"
                + " EO.WORKORDER_OBJECT_NAME,"
                + " EO.WORKORDER_OBJECT_LOCATION,"
                + " EO.COUNTY_CODE,"
                + " EC.COUNTY_NAME,"
                + " EOCM.COMPANY,"
                + " EFV.VALUE OBJECT_CATEGORY_NAME"
                + " FROM"
                + " ETS_OBJECT         EO,"
                + " ETS_COUNTY         EC,"
                + " ETS_OU_CITY_MAP    EOCM,"
                + " ETS_FLEX_VALUE_SET EFVS,"
                + " ETS_FLEX_VALUES    EFV"
                + " WHERE"
                + " EO.COUNTY_CODE *= EC.COUNTY_CODE"
                + " AND EO.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                + " AND EC.COMPANY_CODE = EOCM.COMPANY_CODE"
                + " AND CONVERT(VARCHAR,EO.OBJECT_CATEGORY) = EFV.CODE"
                + " AND EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
                + " AND EFVS.CODE = ?"
                + " AND EO.WORKORDER_OBJECT_NO = ?";
        sqlArgs.add(AssetsDictConstant.OBJECT_CATEGORY);
        sqlArgs.add(dto.getWorkorderObjectNo());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }

    /**
     * ���ܣ�����Զ������ʲ�ҵ��ͷ��(EAM)--ȡ��ԭ�� AMS_ASSETS_TRANS_HEADERҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
        String sqlStr = "SELECT"
                + " EOCM.COMPANY COMPANY_NAME,"
                + " EOCM.ORGANIZATION_ID,"
                + " EC.COUNTY_NAME,"
                + " EO.WORKORDER_OBJECT_NO,"
                + " EO.WORKORDER_OBJECT_CODE,"
                + " EO.WORKORDER_OBJECT_NAME,"
                + " EO.WORKORDER_OBJECT_LOCATION,"
                + " EO.CREATION_DATE,"
                + " EFV.VALUE OBJECT_CATEGORY_NAME"
                + " FROM"
                + " ETS_OBJECT         EO,"
                + " ETS_COUNTY         EC,"
                + " ETS_OU_CITY_MAP    EOCM,"
                + " ETS_FLEX_VALUE_SET EFVS,"
                + " ETS_FLEX_VALUES    EFV"
                + " WHERE"
                + " EO.ORGANIZATION_ID = EOCM.ORGANIZATION_ID"
                + " AND EO.COUNTY_CODE = EC.COUNTY_CODE"
                + " AND EC.COMPANY_CODE = EOCM.COMPANY_CODE"
                + " AND (EO.DISABLE_DATE " + SyBaseSQLUtil.isNullNoParam() + "  OR EO.DISABLE_DATE > GETDATE() OR EO.DISABLE_DATE IS NULL)"
                + " AND CONVERT(VARCHAR,EO.OBJECT_CATEGORY) = EFV.CODE"
                + " AND EFV.FLEX_VALUE_SET_ID = EFVS.FLEX_VALUE_SET_ID"
                + " AND EFVS.CODE = ?"
                + " AND EO.WORKORDER_OBJECT_CODE LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_CODE)"
                + " AND (EO.WORKORDER_OBJECT_NAME LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_NAME) OR EO.WORKORDER_OBJECT_LOCATION LIKE dbo.NVL(?, EO.WORKORDER_OBJECT_LOCATION))"
                + " AND EC.COUNTY_NAME LIKE dbo.NVL(?, EC.COUNTY_NAME)"
                + " AND EOCM.COMPANY = ?";
        sqlArgs.add(AssetsDictConstant.OBJECT_CATEGORY);
        sqlArgs.add(dto.getWorkorderObjectCode());
        sqlArgs.add(dto.getWorkorderObjectName());
        sqlArgs.add(dto.getWorkorderObjectName());
        sqlArgs.add(dto.getCountyName());
        sqlArgs.add(dto.getCompanyName());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
	}
}
