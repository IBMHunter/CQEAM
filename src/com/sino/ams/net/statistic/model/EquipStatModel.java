package com.sino.ams.net.statistic.model;


import java.util.ArrayList;
import java.util.List;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.net.statistic.dto.EquipStatDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.sql.BaseSQLProducer;


/**
 * <p>Title: EquipStatModel</p>
 * <p>Description:�����Զ�����SQL��������EquipStatModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author V-yuanshuai
 * @version 1.0
 */


public class EquipStatModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;


    /**
     * ���ܣ�EQUIP_STAT ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EquipStatDTO ���β���������
     */
    public EquipStatModel(SfUserDTO userAccount, EquipStatDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;

    }

    /**
     * ���ܣ�����Զ�����EQUIP_STATҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "";
        EquipStatDTO equipStat = (EquipStatDTO) dtoParameter;
        String qryType = equipStat.getQryType();
        if (qryType.equals(WebAttrConstant.BY_LOCUS)) {     //�豸������--���ص�
            sqlStr = "SELECT " +
                    "         EM.COMPANY ORGANIZATION_NAME, " +
                    "         EO.WORKORDER_OBJECT_NAME, " +
                    "         EO.WORKORDER_OBJECT_LOCATION, " +
                    "         ESI.ITEM_CATEGORY, " +
                    "         ESI.ITEM_NAME, " +
                    "         ESI.ITEM_SPEC, " +
                    "         COUNT(1)  CNT  \n " +
                    " FROM     ETS_ITEM_INFO      EII, " +
                    "          AMS_OBJECT_ADDRESS AOA, " +
                    "          ETS_OBJECT         EO, " +
                    "          ETS_OU_CITY_MAP    EM, " +
                    "          ETS_SYSTEM_ITEM    ESI \n" +
                    " WHERE " +
                    "          EII.ITEM_CODE = ESI.ITEM_CODE " +
                    " AND      EII.ADDRESS_ID = AOA.ADDRESS_ID " +
                    " AND      AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO " +
                    " AND      EII.ORGANIZATION_ID = EM.ORGANIZATION_ID " +
                    " AND    ( " + SyBaseSQLUtil.isNull() + "  OR  EII.ORGANIZATION_ID =��?) " +
                    " AND    ( " + SyBaseSQLUtil.isNull() + "  OR  EO.COUNTY_CODE = ?) " +
                    " AND    ( " + SyBaseSQLUtil.isNull() + "  OR  EO.OBJECT_CATEGORY = ?) " +
                    " AND    ( " + SyBaseSQLUtil.isNull() + "  OR EO.WORKORDER_OBJECT_NAME LIKE ?) " +
                    " AND    ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?) \n" +
                    " AND    ( " + SyBaseSQLUtil.isNull() + "  OR EII.FINANCE_PROP = ?) \n" +
                    " GROUP BY " +
                    "         EM.COMPANY, " +
                    "         EO.WORKORDER_OBJECT_NAME, " +
                    "         EO.WORKORDER_OBJECT_LOCATION, " +
                    "         ESI.ITEM_CATEGORY, " +
                    "         ESI.ITEM_NAME, " +
                    "         ESI.ITEM_SPEC ";

            sqlArgs.add(equipStat.getOrganizationId());
            sqlArgs.add(equipStat.getOrganizationId());
            sqlArgs.add(equipStat.getCountyCode());
            sqlArgs.add(equipStat.getCountyCode());
            sqlArgs.add(equipStat.getObjectCategory());
            sqlArgs.add(equipStat.getObjectCategory());
            sqlArgs.add(equipStat.getWorkorderObjectName());
            sqlArgs.add(equipStat.getWorkorderObjectName());
            sqlArgs.add(equipStat.getItemSpec());
            sqlArgs.add(equipStat.getItemSpec());
            sqlArgs.add(equipStat.getFinaceProp());
            sqlArgs.add(equipStat.getFinaceProp());
        } else if (qryType.equals(WebAttrConstant.BY_CATEGORY)) {      //�豸������--��״̬
            sqlStr = " SELECT" +
                    " EM.COMPANY ORGANIZATION_NAME,    \n" +
//                    " ESI.ITEM_CATEGORY,    \n" +
                    " AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY, 'ITEM_TYPE') ITEM_CATEGORY,\n" +
                    " AMS_PUB_PKG.GET_FLEX_VALUE(EII.ITEM_STATUS, 'ITEM_STATUS') ITEM_STATUS,\n" +
                    " ESI.ITEM_NAME,    \n" +
                    " ESI.ITEM_SPEC,    \n" +
                    " COUNT(EII.SYSTEMID) CNT  \n" +
                    " FROM   ETS_ITEM_INFO   EII,    \n" +
                    " ETS_SYSTEM_ITEM ESI,    \n" +
                    " ETS_OU_CITY_MAP EM     \n" +
                    " WHERE  EII.ORGANIZATION_ID = EM.ORGANIZATION_ID    \n" +
                    " AND    EII.ITEM_CODE = ESI.ITEM_CODE    \n" +
                    " AND    ( " + SyBaseSQLUtil.isNull() + "  OR  EII.ORGANIZATION_ID =��?) " +
                    " AND   ( " + SyBaseSQLUtil.isNull() + "  OR  ESI.ITEM_CATEGORY = ?)    \n" +
                    " AND   ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?) \n" +
                    " AND   ( " + SyBaseSQLUtil.isNull() + "  OR  EII.FINANCE_PROP = ?) \n" +
                    " AND   ( " + SyBaseSQLUtil.isNull() + "  OR  EII.ITEM_STATUS = ?)   \n" +
                    " GROUP  BY ESI.ITEM_NAME,    \n" +
                    " ESI.ITEM_SPEC,  \n" +
                    " ESI.ITEM_CATEGORY,  \n" +
                    " EII.ITEM_STATUS,  \n" +
                    " EM.COMPANY  ";
            sqlArgs.add(equipStat.getOrganizationId());
            sqlArgs.add(equipStat.getOrganizationId());
            sqlArgs.add(equipStat.getItemCategory());
            sqlArgs.add(equipStat.getItemCategory());
            sqlArgs.add(equipStat.getItemSpec());
            sqlArgs.add(equipStat.getItemSpec());
            sqlArgs.add(equipStat.getFinaceProp());
            sqlArgs.add(equipStat.getFinaceProp());
            sqlArgs.add(equipStat.getItemStatus());
            sqlArgs.add(equipStat.getItemStatus());

        } else if (qryType.equals(WebAttrConstant.BY_VENDOR)) {     //�豸������--������
            sqlStr = "SELECT EMV.VENDOR_NAME,\n" +
                    "    ESI.ITEM_NAME,\n" +
                    "    ESI.ITEM_SPEC,\n" +
                    "    EII.ITEM_STATUS ," +
                    "    COUNT(*) CNT \n" +
                    " FROM   ETS_ITEM_INFO      EII,\n" +
                    "    ETS_SYSTEM_ITEM    ESI,\n" +
                    "    ETS_MIS_PO_VENDORS EMV \n" +
                    " WHERE  ESI.VENDOR_ID *= EMV.VENDOR_ID\n" +
                    " AND    EII.ITEM_CODE = ESI.ITEM_CODE\n" +
                   " AND    ( " + SyBaseSQLUtil.isNull() + "  OR  EII.ORGANIZATION_ID =��?) " +
                    " AND   ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?) \n" +
                    " AND   ( " + SyBaseSQLUtil.isNull() + "  OR EII.ITEM_STATUS = ?)\n" +
                    " AND   ( " + SyBaseSQLUtil.isNull() + "  OR  ESI.VENDOR_ID = ?)\n" +
                    " GROUP  BY EMV.VENDOR_NAME,\n" +
                    "     ESI.ITEM_NAME,\n" +
                    "     ESI.ITEM_SPEC,\n" +
                    "     EII.ITEM_STATUS";

            sqlArgs.add(equipStat.getOrganizationId());
            sqlArgs.add(equipStat.getOrganizationId());
            sqlArgs.add(equipStat.getItemSpec());
            sqlArgs.add(equipStat.getItemSpec());
            sqlArgs.add(equipStat.getItemStatus());
            sqlArgs.add(equipStat.getItemStatus());
            sqlArgs.add(equipStat.getVendorId());
            sqlArgs.add(equipStat.getVendorId());
        } else if (qryType.equals(WebAttrConstant.BY_CATEGORY + "2")) {      //ȫʡͳ��--��״̬
            sqlStr = " SELECT" +
                    " EM.COMPANY ORGANIZATION_NAME,    \n" +
                    " ESI.ITEM_CATEGORY,    \n" +
                    " ESI.ITEM_NAME,    \n" +
                    " ESI.ITEM_SPEC,    \n" +
                    " COUNT(EII.SYSTEMID) CNT  \n" +
                    " FROM   " +
                    " ETS_ITEM_INFO   EII,    \n" +
                    " ETS_SYSTEM_ITEM ESI,    \n" +
                    " ETS_OU_CITY_MAP EM ,    \n" +
                    " AMS_OBJECT_ADDRESS AOA, " +
                    " ETS_OBJECT EO" +
                    " WHERE   EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                    " AND    AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO " +
                    " AND    EII.ORGANIZATION_ID = EM.ORGANIZATION_ID \n" +
                    " AND    EII.ITEM_CODE = ESI.ITEM_CODE \n" +
                    " AND    EO.OBJECT_CATEGORY > ? " +
                    " AND    EO.OBJECT_CATEGORY < ? " +
                    " AND    EO.OBJECT_CATEGORY <>74 " +
                   " AND    ( " + SyBaseSQLUtil.isNull() + "  OR  EII.ORGANIZATION_ID =��?) " +
                    " AND    ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_CATEGORY = ?)    \n" +
                    " AND    ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?) \n" +
                    " AND    ( " + SyBaseSQLUtil.isNull() + "  OR EII.FINANCE_PROP = ?) \n" +
                    " AND    ( " + SyBaseSQLUtil.isNull() + "  OR EII.ITEM_STATUS = ?)   \n" +
                    " GROUP  BY ESI.ITEM_NAME,    \n" +
                    " ESI.ITEM_SPEC,    \n" +
                    " ESI.ITEM_CATEGORY,    \n" +
                    " EM.COMPANY   ";
            sqlArgs.add(WebAttrConstant.INV_CATEGORY);       sqlArgs.add(DictConstant.NETADDR_OTHERS);
            sqlArgs.add(equipStat.getOrganizationId());
            sqlArgs.add(equipStat.getOrganizationId());
            sqlArgs.add(equipStat.getItemCategory());
            sqlArgs.add(equipStat.getItemCategory());
            sqlArgs.add(equipStat.getItemSpec());
            sqlArgs.add(equipStat.getItemSpec());
            sqlArgs.add(equipStat.getFinaceProp());
            sqlArgs.add(equipStat.getFinaceProp());
            sqlArgs.add(equipStat.getItemStatus());
            sqlArgs.add(equipStat.getItemStatus());

        } else if (qryType.equals(WebAttrConstant.BY_NAME)) {       //ȫʡͳ��--���ص�
            String materialAttr = equipStat.getMaterialAttr();
            if (materialAttr.equals("1")) {    //����������
                sqlStr = " SELECT " +
                        " ESI.ITEM_NAME,\n" +
                        " ESI.ITEM_SPEC,\n" +
                        " AIS.QUANTITY CNT,\n" +
                        " EO.WORKORDER_OBJECT_NAME\n" +
                        " FROM " +
                        "       AMS_INV_STORAGE AIS,\n" +
                        "       ETS_SYSTEM_ITEM ESI,\n" +
                        "       ETS_OBJECT EO \n" +
                        " WHERE" +
                        "     AIS.ITEM_CODE = ESI.ITEM_CODE \n" +
                        " AND AIS.INV_CODE = EO.WORKORDER_OBJECT_NO \n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AIS.INV_CODE = ?) \n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?) \n" +
                        " AND ( " + SyBaseSQLUtil.isNull() + "  OR AIS.ORGANIZATION_ID = ?) " +  //����
                        " AND EO.OBJECT_CATEGORY > ? "+
                        " AND EO.OBJECT_CATEGORY < ? "+
                         " AND    EO.OBJECT_CATEGORY <>74 " ;
                sqlArgs.add(equipStat.getWorkorderObjectCode());
                sqlArgs.add(equipStat.getWorkorderObjectCode());
                sqlArgs.add(equipStat.getItemSpec());
                sqlArgs.add(equipStat.getItemSpec());
                sqlArgs.add(equipStat.getOrganizationId());
                sqlArgs.add(equipStat.getOrganizationId());
                sqlArgs.add(WebAttrConstant.INV_CATEGORY);
                sqlArgs.add(DictConstant.NETADDR_OTHERS);
            } else if (materialAttr.equals("2")) {  //��������
                sqlStr = "SELECT  " +
                        "     ESI.ITEM_NAME,\n" +
                        "     ESI.ITEM_SPEC,\n" +
                        "     EO.WORKORDER_OBJECT_NAME,\n" +
                        "     COUNT(*) CNT\n" +
                        " FROM " +
                        "       ETS_ITEM_INFO EII,\n" +
                        "       ETS_SYSTEM_ITEM ESI,\n" +
                        "       AMS_OBJECT_ADDRESS AOA,\n" +
                        "       ETS_OBJECT EO ," +
                        "       ETS_OU_CITY_MAP EM\n" +
                        " WHERE" +
                        "       EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                        " AND   AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO " +
                        " AND   EII.ORGANIZATION_ID = EM.ORGANIZATION_ID    \n" +
                        " AND   EO.OBJECT_CATEGORY > ? " +
                        " AND   EO.OBJECT_CATEGORY < ? " +
                         " AND    EO.OBJECT_CATEGORY <>74 " +
                        " AND   EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                        " AND   AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
                        " AND   EII.ITEM_CODE = ESI.ITEM_CODE\n" +
                       " AND    ( " + SyBaseSQLUtil.isNull() + "  OR  EII.ORGANIZATION_ID =��?) " +
                        " AND  ( " + SyBaseSQLUtil.isNull() + "  OR EO.WORKORDER_OBJECT_CODE = ? ) \n" +
                        " AND  ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?) \n" +
                        " AND  ( " + SyBaseSQLUtil.isNull() + "  OR EII.FINANCE_PROP = ?)\n" +
                        " GROUP BY " +
                        " ESI.ITEM_NAME,ESI.ITEM_SPEC,EO.WORKORDER_OBJECT_NAME";

                sqlArgs.add(WebAttrConstant.INV_CATEGORY);       sqlArgs.add(DictConstant.NETADDR_OTHERS);
                sqlArgs.add(equipStat.getOrganizationId());
                sqlArgs.add(equipStat.getOrganizationId());
                sqlArgs.add(equipStat.getWorkorderObjectCode());
                sqlArgs.add(equipStat.getWorkorderObjectCode());
                sqlArgs.add(equipStat.getItemSpec());
                sqlArgs.add(equipStat.getItemSpec());
                sqlArgs.add(equipStat.getFinaceProp());
                sqlArgs.add(equipStat.getFinaceProp());
            }

        } else if (qryType.equals(WebAttrConstant.BY_VENDOR + "2")) {      //ȫʡͳ��--������
            sqlStr = "SELECT EMV.VENDOR_NAME,\n" +
                    "    ESI.ITEM_NAME,\n" +
                    "    ESI.ITEM_SPEC,\n" +
                    "    EII.ITEM_STATUS ," +
                    "    COUNT(*) CNT \n" +
                    " FROM  " +
                    "    ETS_ITEM_INFO  EII,\n" +
                    "    ETS_SYSTEM_ITEM    ESI,\n" +
                    "    ETS_MIS_PO_VENDORS EMV, \n" +
                    "    AMS_OBJECT_ADDRESS AOA, " +
                    "    ETS_OBJECT EO" +
                    " WHERE  ESI.VENDOR_ID *= EMV.VENDOR_ID \n" +
                    " AND    EII.ITEM_CODE = ESI.ITEM_CODE\n" +
                    " AND���� EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                    " AND    AOA.OBJECT_NO = EO.WORKORDER_OBJECT_NO " +
                   " AND    ( " + SyBaseSQLUtil.isNull() + "  OR  EII.ORGANIZATION_ID =��?) " +
                    " AND   ( " + SyBaseSQLUtil.isNull() + "  OR ESI.ITEM_SPEC LIKE ?) \n" +
                    " AND   ( " + SyBaseSQLUtil.isNull() + "  OR EII.ITEM_STATUS = ?)\n" +
                    " AND   ( " + SyBaseSQLUtil.isNull() + "  OR ESI.VENDOR_ID = ?)\n" +
                    " AND EO.OBJECT_CATEGORY > ? " +
                    " AND EO.OBJECT_CATEGORY < ? " +
                     " AND    EO.OBJECT_CATEGORY <>74 " +
                    " GROUP  BY EMV.VENDOR_NAME,\n" +
                    "     ESI.ITEM_NAME,\n" +
                    "     ESI.ITEM_SPEC,\n" +
                    "     EII.ITEM_STATUS";

            sqlArgs.add(equipStat.getOrganizationId());
            sqlArgs.add(equipStat.getOrganizationId());
            sqlArgs.add(equipStat.getItemSpec());
            sqlArgs.add(equipStat.getItemSpec());
            sqlArgs.add(equipStat.getItemStatus());
            sqlArgs.add(equipStat.getItemStatus());
            sqlArgs.add(equipStat.getVendorId());
            sqlArgs.add(equipStat.getVendorId());
            sqlArgs.add(WebAttrConstant.INV_CATEGORY);
                sqlArgs.add(DictConstant.NETADDR_OTHERS);
        }

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

}