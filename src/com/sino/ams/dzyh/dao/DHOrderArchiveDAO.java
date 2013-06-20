package com.sino.ams.dzyh.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class DHOrderArchiveDAO extends AMSBaseDAO {

	public DHOrderArchiveDAO(BaseUserDTO userAccount, EamDhCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ���������ʵ�֣����ô洢����ʵ��ҵ���߼���
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
	}

	/**
	 * ���ܣ��鵵����(��ֵ�׺ġ������Ǳ��ͺĲ�ɨ)
	 * @param orderArchMaps Map
	 * @throws DataHandleException
	 */
	public void archiveOrders(Map orderArchMaps) throws DataHandleException{
		if(orderArchMaps == null || orderArchMaps.isEmpty()){
			return;
		}
		CallableStatement cs = null;
		int orderCount = orderArchMaps.size();
		int succeedCount = 0;
		String errorMsg = "";
		Exception error = null;
		try {
			String callStr = "{CALL EAM_DHCHK_ARCHIVE_PKG.ARCHIVE_ORDER(?, ?, ?, ?, ?, ?, ?)}";
			cs = conn.prepareCall(callStr);
			int paraIndex = 1;
			Iterator headerIds = orderArchMaps.keySet().iterator();
			String headerId = "";
			String archStatus = "";
			while (headerIds.hasNext()) {
				headerId = (String) headerIds.next();
				archStatus = (String) orderArchMaps.get(headerId);

				cs.setInt(paraIndex++, Integer.parseInt(headerId));
				cs.setString(paraIndex++, archStatus);
				cs.setInt(paraIndex++, userAccount.getUserId() );
				cs.setInt(paraIndex++, userAccount.getOrganizationId() );
				cs.setInt(paraIndex++, servletConfig.getProvinceOrgId() );
				cs.registerOutParameter(paraIndex++, Types.INTEGER);
				cs.registerOutParameter(paraIndex, Types.VARCHAR);
				cs.execute();
				succeedCount += cs.getInt(6);
				errorMsg += cs.getString(7);
				paraIndex = 1;
			}
		} catch (NumberFormatException ex) {
			Logger.logError(ex);
			if(StrUtil.isEmpty(errorMsg)){
				error = new DataHandleException(ex);
			} else {
				error = new DataHandleException(errorMsg);
			}
		} catch (SQLException ex) {
			Logger.logError(ex);
			if(StrUtil.isEmpty(errorMsg)){
				error = new DataHandleException(ex);
			} else {
				error = new DataHandleException(errorMsg);
			}
		} finally {
			DBManager.closeDBStatement(cs);
			if(succeedCount < orderCount){
				if(error == null){
					if(StrUtil.isEmpty(errorMsg)){
						errorMsg = "�����鵵ʧ��";
					}
					error = new DataHandleException(errorMsg);
				}
				throw (DataHandleException)error;
			}
		}
	}
}
