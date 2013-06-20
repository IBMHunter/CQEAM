package com.sino.soa.td.srv.assetsinfoupdate.model;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.soa.util.dto.EtsMisfaUpdateBatchDTO;
import com.sino.soa.util.dto.EtsMisfaUpdateLogDTO;
import com.sino.soa.util.dto.SynLogDTO;
import java.util.ArrayList;
import java.util.List;
/**
 * <p>Title: EtsAutoSynLogModel</p>
 * <p>Description:�����Զ�����SQL����������ȡETS_ITEM_INFO��Ķ�Ӧ���ԡ����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>v
 * @author wangzp
 * @version 1.0
 */

public class TDSynAttributeModel {

	   /**
     * ����:Ͷ�ʷ���ID
     * ��ȡ֧�����豸����
     * @param 
     * @return SQLModel
     */
    public SQLModel getCexType(String cexId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT SN_CODE,\n" +
        		        " SN_NAME \n" +
        		        " FROM AMS_CEX \n" +
        		        " WHERE AMS_CEX_ID=? " ;
        
        sqlArgs.add(cexId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }
	
    /**
     * ��ȡҵ��ƽ̨���룬�����α��� 
     * @param 
     * @return SQLModel
     */
    public SQLModel getOpeModel(String  opeId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "select AO.OPE_CODE,AO.OPE_NAME from AMS_OPE AO WHERE AO.AMS_OPE_ID= ?";
       sqlArgs.add(opeId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        return sqlModel;
    }
	
	
}
