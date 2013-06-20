package com.sino.sinoflow.dao;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.util.FlowTaskTool;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.dto.SfApiDTO;
import com.sino.sinoflow.model.SfApiModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

/**
 * Title: SfApiDAO
 * Description:�����Զ����ɷ������SfApiDAO�����������Ҫ�����޸�
 * Copyright: Copyright (c) 2008
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * @author Hing
 * @version 1.0
 */

public class SfApiDAO extends BaseDAO {

	private SfUserBaseDTO sfUser = null;

	/**
	 * ���ܣ��ӿڳ�����Ϣ SF_API ���ݷ��ʲ㹹�캯��
	 * @param userAccount ����ϵͳ�����ղ����û�����
	 * @param dtoParameter ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public SfApiDAO(SfUserBaseDTO userAccount, SfApiDTO dtoParameter,
			Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * 
	 * @param userAccount
	 *            BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter
	 *            DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SfApiDTO dtoPara = (SfApiDTO) dtoParameter;
		super.sqlProducer = new SfApiModel((SfUserBaseDTO) userAccount, dtoPara);
	}

	

	/**
	 * ���ܣ�api �ӿ�����������¼�
	 * @return Map
	 */
	private Map getApiMap() {
		Map map = new HashMap();
		map.put(new Integer(1), "sfqueryopen");
		map.put(new Integer(2), "sfpostopen");
		map.put(new Integer(4), "sfquerysign");
		map.put(new Integer(8), "sfpostsign");
		map.put(new Integer(16), "sfquerycomplete");
		map.put(new Integer(32), "sfgroupreview");
		map.put(new Integer(64), "sfquerycyclereview");
		map.put(new Integer(128), "sfqueryconditionalflow");
		map.put(new Integer(256), "sfquerygroup");
		map.put(new Integer(512), "sfqueryassistflow");
		map.put(new Integer(1024), "sfquerydistribute");
		map.put(new Integer(2048), "sfquerygoback");
		map.put(new Integer(4096), "sfquerysave");
		map.put(new Integer(8192), "sfpostsave");
		map.put(new Integer(8192*2), "sfparallelflow");
		return map;
	}

	public static String getJointStr(int count,Map map) {
		List list = FlowTaskTool.findNum(count);
		if(list.isEmpty()) return "&nbsp;";
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str += map.get(list.get(i))+" + ";
		}
		return str.substring(0,str.length()-2);
	}

	public DTOSet operation(DTOSet ds){
		for (int i = 0; i < ds.getSize(); i++) {
			SfApiDTO sad = (SfApiDTO)ds.getDTO(i);
			String str = getJointStr(sad.getApiControl(),getApiMap());
			sad.setApiControlStr(str);
		}
		return ds;
	}
	
	/**
	 * ���ܣ���ɾ��Ӧ��
	 * @param ids String[]
	 * @throws DataHandleException 
	 */
	public void del(String[] ids) throws DataHandleException {
		DBOperator.updateRecord(((SfApiModel)sqlProducer).del(ids), conn);
	}

    public void enabled(String[] ids) throws DataHandleException {
        DBOperator.updateRecord(((SfApiModel)sqlProducer).enabled(ids), conn);
    }

    public void disabled(String[] ids) throws DataHandleException {
        DBOperator.updateRecord(((SfApiModel)sqlProducer).disabled(ids), conn);
    }
}