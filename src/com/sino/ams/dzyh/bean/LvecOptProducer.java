package com.sino.ams.dzyh.bean;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.dto.SfUserRightDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.base.web.DatabaseForWeb;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class LvecOptProducer extends AssetsOptProducer {

	public LvecOptProducer(SfUserDTO userAccount, Connection conn) {
		super(userAccount, conn);
	}

	/**
	 * ���ܣ����칤����״̬�����б��
	 * @param selectedValue String
	 * @return String
	 * @throws QueryException
	 */
	public String getBatchStatusOpt(String selectedValue) throws QueryException {
		SQLModel sqlModel = LvecOptSQLProducer.getBatchStatusModel();
		DatabaseForWeb db2web = new DatabaseForWeb(sqlModel, conn);
		return db2web.getOptionHtml(selectedValue, true);
	}

	/**
	 * ���ܣ����칤��״̬�����б��
	 * @param selectedValue String
	 * @return String
	 * @throws QueryException
	 */
	public String getOrderStatusOpt(String selectedValue) throws QueryException {
		SQLModel sqlModel = LvecOptSQLProducer.getOrderStatusModel();
		DatabaseForWeb db2web = new DatabaseForWeb(sqlModel, conn);
		return db2web.getOptionHtml(selectedValue, true);
	}


	/**
	 * ���ܣ����칤��ִ�в��������б��
	 * @param dto EamDhCheckHeaderDTO
	 * @return String
	 * @throws QueryException
	 */
	public String getImpDeptOpt(EamDhCheckHeaderDTO dto) throws QueryException {
		SQLModel sqlModel = LvecOptSQLProducer.getImpDeptModel(userAccount, dto);
		DatabaseForWeb db2web = new DatabaseForWeb(sqlModel, conn);
		return db2web.getOptionHtml(dto.getImpDeptCode(), true);
	}

	/**
	 * ���ܣ����칤��ִ�в��������б��
	 * @param selectedValue String
	 * @return String
	 * @throws QueryException
	 */
	public String getOrderTypeOpt(String selectedValue) throws QueryException {
		SQLModel sqlModel = LvecOptSQLProducer.getOrderTypeModel();
		DatabaseForWeb db2web = new DatabaseForWeb(sqlModel, conn);
		return db2web.getOptionHtml(selectedValue, true);
	}

	/**
	 * ���ܣ������û����������
	 * @param selectedValue String
	 * @return String
	 */
	public String getUserGroupOption(int selectedValue){
		StringBuffer groupOption = new StringBuffer();
		DTOSet userRights = userAccount.getUserRight();
		int dtoCount = userRights.getSize();
		SfUserRightDTO userRightDTO = null;
		Map groups = new HashMap();
		int groupId ;
		String groupName = "";
		groupOption.append("<option value=\"\">--��ѡ��--</option>");
		String selectProp = "";
		for(int i = 0; i < dtoCount; i++){
			userRightDTO = (SfUserRightDTO)userRights.getDTO(i);
			groupId = userRightDTO.getGroupId();
			groupName = userRightDTO.getGroupName();
			if(!groups.containsKey(groupId)){
				if(groupId == selectedValue ){
					selectProp = " selected";
				} else {
					selectProp = "";
				}
				groups.put(groupId, groupName);
				groupOption.append("<option value=\"");
				groupOption.append(groupId);
				groupOption.append("\"");
				groupOption.append(selectProp);
				groupOption.append(">");
				groupOption.append(groupName);
				groupOption.append("</option>");
			}
		}
		return groupOption.toString();
	}
}
