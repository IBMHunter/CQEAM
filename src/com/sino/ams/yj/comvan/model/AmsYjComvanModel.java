package com.sino.ams.yj.comvan.model;

import java.util.*;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.base.math.AdvancedNumber;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.*;
import com.sino.base.util.ArrUtil;
import com.sino.base.util.StrUtil;
import com.sino.ams.yj.comvan.dto.AmsYjComvanDTO;

/**
 * <p>Title: AmsYjComvanModel</p>
 * <p>Description:�����Զ�����SQL��������AmsYjComvanModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ��ͨ�ų�
 */

public class AmsYjComvanModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�Ӧ��ͨ�ų���Ϣ�� AMS_YJ_COMVAN ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjComvanDTO ���β���������
     */
    public AmsYjComvanModel(SfUserDTO userAccount, AmsYjComvanDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ų���Ϣ�� AMS_YJ_COMVAN���ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjComvanDTO amsYjComvan = (AmsYjComvanDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " AMS_YJ_COMVAN(" 
                + " COMVAN_ID,"
                + " DEPT_NAME,"
                + " MANUFACTURER,"
                + " MODEL,"
                + " REFIT_FIRM,"
                + " LENGTH,"
                + " QUALITY,"
                + " ANTENNA_MAST_FORM,"
                + " HAS_OILENGINE,"
                + " LICENSE_PLATE,"
                + " FRAME_NUMBER,"
                + " L_W_H,"
                + " ORIGINAL_COST,"
                + " BTS_MANUFACTURER,"
                + " BTS_MODEL,"
                + " CARRIER_FREQUENCYV_ALLOCATE,"
                + " CARRIER_FREQUENCYV_QTY,"
                + " INSTALLED_BSC,"
                + " OTHER_GSM_UNIT,"
                + " TRANS_FORM,"
                + " TRANS_ITEM_MODEL,"
                + " HAS_SATELLITE_TRANSMISSIONS,"
                + " TYPE_OF_TRAFFIC,"
                + " USE_TIMES,"
                + " USED_TRAFFIC,"
                + " USE_SCENE,"
                + " REMARK,"
                + " ORGANIZATION_ID,"
                + " RESOURCE_ID,"
                + " CREATION_DATE," 
                + " CREATE_USER,"   
                + " BANDWIDTH"
                + ") VALUES ("
                + " CONVERT(FLOAT, ?),?, ?, ?, ?, CONVERT(FLOAT, ?), CONVERT(FLOAT, ?), ?, ?, ?, ?, ?, " 
                + " CONVERT(FLOAT, ?), ?, ?, ?, CONVERT(FLOAT, ?), ?, ?, ?, ?, ?, ?, CONVERT(FLOAT, ?)," 
                + " ?, ?, ?, ?,CONVERT(FLOAT, ?),GETDATE(),CONVERT(FLOAT, ?),? )";

        sqlArgs.add(amsYjComvan.getComvanId());
        sqlArgs.add(amsYjComvan.getDeptName());
        sqlArgs.add(amsYjComvan.getManufacturer());
        sqlArgs.add(amsYjComvan.getModel());
        sqlArgs.add(amsYjComvan.getRefitFirm());
        sqlArgs.add(amsYjComvan.getLength());
        sqlArgs.add(amsYjComvan.getQuality());
        sqlArgs.add(amsYjComvan.getAntennaMastForm());
        sqlArgs.add(amsYjComvan.getHasOilengine());
        sqlArgs.add(amsYjComvan.getLicensePlate());
        sqlArgs.add(amsYjComvan.getFrameNumber());
        sqlArgs.add(amsYjComvan.getLWH());
        sqlArgs.add(amsYjComvan.getOriginalCost());	
        sqlArgs.add(amsYjComvan.getBtsManufacturer());
        sqlArgs.add(amsYjComvan.getBtsModel());
        sqlArgs.add(amsYjComvan.getCarrierFrequencyvAllocate());     
        sqlArgs.add(amsYjComvan.getCarrierFrequencyvQty());
        sqlArgs.add(amsYjComvan.getInstalledBsc());
        sqlArgs.add(amsYjComvan.getOtherGsmUnit());
        sqlArgs.add(amsYjComvan.getTransForm());
        sqlArgs.add(amsYjComvan.getTransItemModel());
        sqlArgs.add(amsYjComvan.getHasSatelliteTransmissions());
        sqlArgs.add(amsYjComvan.getTypeOfTraffic());
        sqlArgs.add(amsYjComvan.getUseTimes());
        sqlArgs.add(amsYjComvan.getUsedTraffic());
        sqlArgs.add(amsYjComvan.getUseScene());
        sqlArgs.add(amsYjComvan.getRemark());
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(amsYjComvan.getResourceId());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsYjComvan.getBandwidth());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ų���Ϣ�� AMS_YJ_COMVAN���ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjComvanDTO amsYjComvan = (AmsYjComvanDTO) dtoParameter;
        String sqlStr = "UPDATE AMS_YJ_COMVAN"
                + " SET"
                + " DEPT_NAME = ?,"
                + " MANUFACTURER = ?,"
                + " MODEL = ?,"
                + " REFIT_FIRM = ?,"
                + " LENGTH = CONVERT(FLOAT, ?),"
                + " QUALITY = CONVERT(FLOAT, ?),"
                + " ANTENNA_MAST_FORM = ?,"
                + " HAS_OILENGINE = ?,"
                + " LICENSE_PLATE = ?,"
                + " FRAME_NUMBER = ?,"
                + " L_W_H = ?,"
                + " ORIGINAL_COST = CONVERT(FLOAT, ?),"
                + " BTS_MANUFACTURER = ?,"
                + " BTS_MODEL = ?,"
                + " CARRIER_FREQUENCYV_ALLOCATE = ?,"
                + " CARRIER_FREQUENCYV_QTY = CONVERT(FLOAT, ?),"
                + " INSTALLED_BSC = ?,"
                + " OTHER_GSM_UNIT = ?,"
                + " TRANS_FORM = ?,"
                + " TRANS_ITEM_MODEL = ?,"
                + " HAS_SATELLITE_TRANSMISSIONS = ?,"
                + " TYPE_OF_TRAFFIC = ?,"
                + " USE_TIMES = CONVERT(FLOAT, ?),"
                + " USED_TRAFFIC = ?,"
                + " USE_SCENE = ?,"
                + " REMARK = ?,"
                + " RESOURCE_ID = CONVERT(FLOAT, ?),"
                + " BANDWIDTH = ?,"
                + " LAST_UPDATE_DATE = GETDATE(),"
                + " LAST_UPDATE_USER = CONVERT(FLOAT, ?)"
                + " WHERE  COMVAN_ID= CONVERT(FLOAT, ?)";
        sqlArgs.add(amsYjComvan.getDeptName());
        sqlArgs.add(amsYjComvan.getManufacturer());
        sqlArgs.add(amsYjComvan.getModel());
        sqlArgs.add(amsYjComvan.getRefitFirm());
        sqlArgs.add(amsYjComvan.getLength());
        sqlArgs.add(amsYjComvan.getQuality());
        sqlArgs.add(amsYjComvan.getAntennaMastForm());
        sqlArgs.add(amsYjComvan.getHasOilengine());
        sqlArgs.add(amsYjComvan.getLicensePlate());
        sqlArgs.add(amsYjComvan.getFrameNumber());
        sqlArgs.add(amsYjComvan.getLWH());
        sqlArgs.add(amsYjComvan.getOriginalCost());
        sqlArgs.add(amsYjComvan.getBtsManufacturer());
        sqlArgs.add(amsYjComvan.getBtsModel());
        sqlArgs.add(amsYjComvan.getCarrierFrequencyvAllocate());
        sqlArgs.add(amsYjComvan.getCarrierFrequencyvQty());
        sqlArgs.add(amsYjComvan.getInstalledBsc());
        sqlArgs.add(amsYjComvan.getOtherGsmUnit());
        sqlArgs.add(amsYjComvan.getTransForm());
        sqlArgs.add(amsYjComvan.getTransItemModel());
        sqlArgs.add(amsYjComvan.getHasSatelliteTransmissions());
        sqlArgs.add(amsYjComvan.getTypeOfTraffic());
        sqlArgs.add(amsYjComvan.getUseTimes());
        sqlArgs.add(amsYjComvan.getUsedTraffic());
        sqlArgs.add(amsYjComvan.getUseScene());
        sqlArgs.add(amsYjComvan.getRemark());
        sqlArgs.add(amsYjComvan.getResourceId());
        sqlArgs.add(amsYjComvan.getBandwidth());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(amsYjComvan.getComvanId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ų���Ϣ�� AMS_YJ_COMVAN����ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjComvanDTO amsYjComvan = (AmsYjComvanDTO) dtoParameter;
        String sqlStr = "DELETE FROM"
                + " AMS_YJ_COMVAN";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ų���Ϣ�� AMS_YJ_COMVAN������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjComvanDTO amsYjComvan = (AmsYjComvanDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " AYC.COMVAN_ID,"
                + " AYC.DEPT_NAME,"
                + " AYC.MANUFACTURER,"
                + " AYC.MODEL,"
                + " AYC.REFIT_FIRM,"
                + " AYC.LENGTH,"
                + " AYC.QUALITY,"
                + " AYC.ANTENNA_MAST_FORM,"
                + " AYC.HAS_OILENGINE,"
                + " AYC.LICENSE_PLATE,"
                + " AYC.FRAME_NUMBER,"
                + " AYC.L_W_H,"
                + " AYC.ORIGINAL_COST,"
                + " AYC.BTS_MANUFACTURER,"
                + " AYC.BTS_MODEL,"
                + " AYC.CARRIER_FREQUENCYV_ALLOCATE,"
                + " AYC.CARRIER_FREQUENCYV_QTY,"
                + " AYC.INSTALLED_BSC,"
                + " AYC.OTHER_GSM_UNIT,"
                + " AYC.TRANS_FORM,"
                + " AYC.TRANS_ITEM_MODEL,"
                + " AYC.BANDWIDTH,"
                + " AYC.HAS_SATELLITE_TRANSMISSIONS,"
                + " AYC.TYPE_OF_TRAFFIC,"
                + " AYC.USE_TIMES,"
                + " AYC.USED_TRAFFIC,"
                + " AYC.ORGANIZATION_ID,"
                + " AYC.USE_SCENE,"
                + " AYC.REMARK,"
                + " AYCR.RESOURCE_ID,"
                + " AYCR.EQUIPMENT_NAME"
                + " FROM AMS_YJ_COMVAN AYC,AMS_YJ_COMMUNICATE_RESOURCE AYCR"
                + " WHERE AYC.RESOURCE_ID *= AYCR.RESOURCE_ID"
                + " AND COMVAN_ID=CONVERT(FLOAT, ?)";

        sqlArgs.add(amsYjComvan.getComvanId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ų���Ϣ�� AMS_YJ_COMVAN����������Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ���ض���������Ϣ��ѯ��SQLModel
     */
    public SQLModel getMuxDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjComvanDTO amsYjComvan = (AmsYjComvanDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " COMVAN_ID,"
                + " DEPT_NAME,"
                + " MANUFACTURER,"
                + " MODEL,"
                + " REFIT_FIRM,"
                + " LENGTH,"
                + " QUALITY,"
                + " ANTENNA_MAST_FORM,"
                + " HAS_OILENGINE,"
                + " LICENSE_PLATE,"
                + " FRAME_NUMBER,"
                + " L_W_H,"
                + " ORIGINAL_COST,"
                + " BTS_MANUFACTURER,"
                + " BTS_MODEL,"
                + " CARRIER_FREQUENCYV_ALLOCATE,"
                + " CARRIER_FREQUENCYV_QTY,"
                + " INSTALLED_BSC,"
                + " OTHER_GSM_UNIT,"
                + " TRANS_FORM,"
                + " TRANS_ITEM_MODEL,"
                + " HAS_SATELLITE_TRANSMISSIONS,"
                + " TYPE_OF_TRAFFIC,"
                + " USE_TIMES,"
                + " USED_TRAFFIC,"
                + " USE_SCENE,"
                + " REMARK"
                + " FROM"
                + " AMS_YJ_COMVAN"
                + " WHERE"
                + " ("+ SyBaseSQLUtil.isNull() +" OR COMVAN_ID = ?)"
                + " AND ("+ SyBaseSQLUtil.isNull() +" OR DEPT_NAME LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR MANUFACTURER LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR MODEL LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR REFIT_FIRM LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR LENGTH LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR QUALITY LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR ANTENNA_MAST_FORM LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR HAS_OILENGINE LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR LICENSE_PLATE LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR FRAME_NUMBER LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR L_W_H LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR ORIGINAL_COST LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR BTS_MANUFACTURER LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR BTS_MODEL LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR CARRIER_FREQUENCYV_ALLOCATE LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR CARRIER_FREQUENCYV_QTY LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR INSTALLED_BSC LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR OTHER_GSM_UNIT LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR TRANS_FORM LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR TRANS_ITEM_MODEL LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR HAS_SATELLITE_TRANSMISSIONS LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR TYPE_OF_TRAFFIC LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR USE_TIMES LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR USED_TRAFFIC LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR USE_SCENE LIKE ?)"
//                + " AND ("+ SyBaseSQLUtil.isNull() +" OR REMARK LIKE ?)" 
                +		"";
        sqlArgs.add(amsYjComvan.getComvanId());
        sqlArgs.add(amsYjComvan.getComvanId());
        sqlArgs.add(amsYjComvan.getDeptName());
        sqlArgs.add(amsYjComvan.getDeptName());
       /* sqlArgs.add(amsYjComvan.getManufacturer());
        sqlArgs.add(amsYjComvan.getManufacturer());
        sqlArgs.add(amsYjComvan.getModel());
        sqlArgs.add(amsYjComvan.getModel());
        sqlArgs.add(amsYjComvan.getRefitFirm());
        sqlArgs.add(amsYjComvan.getRefitFirm());
        sqlArgs.add(amsYjComvan.getLength());
        sqlArgs.add(amsYjComvan.getLength());
        sqlArgs.add(amsYjComvan.getQuality());
        sqlArgs.add(amsYjComvan.getQuality());
        sqlArgs.add(amsYjComvan.getAntennaMastForm());
        sqlArgs.add(amsYjComvan.getAntennaMastForm());
        sqlArgs.add(amsYjComvan.getHasOilengine());
        sqlArgs.add(amsYjComvan.getHasOilengine());
        sqlArgs.add(amsYjComvan.getLicensePlate());
        sqlArgs.add(amsYjComvan.getLicensePlate());
        sqlArgs.add(amsYjComvan.getFrameNumber());
        sqlArgs.add(amsYjComvan.getFrameNumber());
        sqlArgs.add(amsYjComvan.getLWH());
        sqlArgs.add(amsYjComvan.getLWH());
        sqlArgs.add(amsYjComvan.getOriginalCost());
        sqlArgs.add(amsYjComvan.getOriginalCost());
        sqlArgs.add(amsYjComvan.getBtsManufacturer());
        sqlArgs.add(amsYjComvan.getBtsManufacturer());
        sqlArgs.add(amsYjComvan.getBtsModel());
        sqlArgs.add(amsYjComvan.getBtsModel());
        sqlArgs.add(amsYjComvan.getCarrierFrequencyvAllocate());
        sqlArgs.add(amsYjComvan.getCarrierFrequencyvAllocate());
        sqlArgs.add(amsYjComvan.getCarrierFrequencyvQty());
        sqlArgs.add(amsYjComvan.getCarrierFrequencyvQty());
        sqlArgs.add(amsYjComvan.getInstalledBsc());
        sqlArgs.add(amsYjComvan.getInstalledBsc());
        sqlArgs.add(amsYjComvan.getOtherGsmUnit());
        sqlArgs.add(amsYjComvan.getOtherGsmUnit());
        sqlArgs.add(amsYjComvan.getTransForm());
        sqlArgs.add(amsYjComvan.getTransForm());
        sqlArgs.add(amsYjComvan.getTransItemModel());
        sqlArgs.add(amsYjComvan.getTransItemModel());
        sqlArgs.add(amsYjComvan.getHasSatelliteTransmissions());
        sqlArgs.add(amsYjComvan.getHasSatelliteTransmissions());
        sqlArgs.add(amsYjComvan.getTypeOfTraffic());
        sqlArgs.add(amsYjComvan.getTypeOfTraffic());
        sqlArgs.add(amsYjComvan.getUseTimes());
        sqlArgs.add(amsYjComvan.getUseTimes());
        sqlArgs.add(amsYjComvan.getUsedTraffic());
        sqlArgs.add(amsYjComvan.getUsedTraffic());
        sqlArgs.add(amsYjComvan.getUseScene());
        sqlArgs.add(amsYjComvan.getUseScene());
        sqlArgs.add(amsYjComvan.getRemark());
        sqlArgs.add(amsYjComvan.getRemark());*/

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����Ӧ��ͨ�ų���Ϣ�� AMS_YJ_COMVANҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        AmsYjComvanDTO amsYjComvan = (AmsYjComvanDTO) dtoParameter;
        String sqlStr = "SELECT "
                + " CONVERT(VARCHAR, COMVAN_ID) COMVAN_ID,"
                + " DEPT_NAME,"
                + " MANUFACTURER,"
                + " MODEL,"
                + " REFIT_FIRM,"
                + " LENGTH,"
                + " QUALITY,"
                + " ANTENNA_MAST_FORM,"
                + " HAS_OILENGINE,"
                + " LICENSE_PLATE,"
                + " FRAME_NUMBER,"
                + " L_W_H,"
                + " ORIGINAL_COST,"
                + " BTS_MANUFACTURER,"
                + " BTS_MODEL,"
                + " CARRIER_FREQUENCYV_ALLOCATE,"
                + " CARRIER_FREQUENCYV_QTY,"
                + " INSTALLED_BSC,"
                + " OTHER_GSM_UNIT,"
                + " TRANS_FORM,"
                + " TRANS_ITEM_MODEL,"
                + " BANDWIDTH,"
                + " HAS_SATELLITE_TRANSMISSIONS,"
                + " TYPE_OF_TRAFFIC,"
                + " USE_TIMES,"
                + " USED_TRAFFIC,"
                + " USE_SCENE,"
                + " REMARK,"
                + " ORGANIZATION_ID"
                + " FROM"
                + " AMS_YJ_COMVAN"
                + " WHERE"
                + "  ("+ SyBaseSQLUtil.isNull() +" OR DEPT_NAME LIKE ?)"
                + " AND (? = -1 OR ORGANIZATION_ID = ?)";
        
	        if(!amsYjComvan.getComvanId().equals("")){
	        	sqlStr += " AND COMVAN_ID = CONVERT(FLOAT,?))";
	        }else{
	        	sqlStr += " AND CONVERT(FLOAT,?) = 0 ";
	        }
         
        sqlArgs.add(amsYjComvan.getDeptName());
        sqlArgs.add(amsYjComvan.getDeptName());
        sqlArgs.add(amsYjComvan.getOrganizationId());
        sqlArgs.add(amsYjComvan.getOrganizationId());
        sqlArgs.add(amsYjComvan.getComvanId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel getDeleteModel(String[] comvanIds) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String ComvanIds = appendIntComvanId(comvanIds, ",");
        String sqlStr = "DELETE FROM"
                + " AMS_YJ_COMVAN WHERE COMVAN_ID IN  (" + ComvanIds + ")";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    private String appendIntComvanId(String[] srcArr, String linkStr) {
        String retStr = "";
        if (srcArr != null && srcArr.length > 0) {
            boolean hasProcessed = false;
            for (int i = 0; i <= srcArr.length - 1; i++) {
            	retStr += "CONVERT(FLOAT, "+srcArr[i]+")"+ linkStr;
                hasProcessed = true;
            }
            if (hasProcessed) {
                retStr = retStr.substring(0, retStr.length() - linkStr.length());
            }
        }
        return retStr;
    }
    
}