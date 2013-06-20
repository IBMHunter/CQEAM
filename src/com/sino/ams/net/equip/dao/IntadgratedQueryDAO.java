package com.sino.ams.net.equip.dao;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.net.equip.dto.IntadgratedQueryDTO;
import com.sino.ams.net.equip.dto.IntegratedDTO;
import com.sino.ams.net.equip.model.IntadgratedQueryModel;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsAssetsCommQueryDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsCommQueryDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class IntadgratedQueryDAO extends AMSBaseDAO {
	private DTOSet queryFields = null;
	private int queryFieldCount = 0;
	private DTOSet displayFields = null;
	private int displayFieldCount = 0;

	private int headerDivTopPx = 20;
	private int dataDivTopPx = 20;
	private int dataDivHeight = 400;
	private int rowHeight = 23;
	private int tdWidth = 150;
	private int tableWidth = 150;
	/**
	 * ���ܣ��ʲ��ۺϲ�ѯ���� AMS_ASSETS_COMM_QUERY ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsCommQueryDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public IntadgratedQueryDAO(SfUserDTO userAccount, IntadgratedQueryDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		init();
	}

	/**
	 * ����Ƿ��Ѿ��������ۺϲ�ѯ���ֶ�
	 * @return boolean
	 */
	public boolean hasCustomizedFields() {
		return (queryFieldCount > 0 && displayFieldCount > 0);
	}
	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		IntadgratedQueryDTO dtoPara = (IntadgratedQueryDTO)dtoParameter;
		super.sqlProducer = new IntadgratedQueryModel((SfUserDTO)userAccount, dtoPara);
	}

	private void init(){
		try {
			IntegratedDAO setDAO = new IntegratedDAO(userAccount, null, conn);
			queryFields = setDAO.getCheckedFields(AssetsDictConstant.FIELD_FOR_QUERY);
			displayFields = setDAO.getCheckedFields(AssetsDictConstant.FIELD_FOR_DISPL);
			if(queryFields != null && !queryFields.isEmpty()){
				queryFieldCount = queryFields.getSize();
				int rowCount = (queryFieldCount / 3);
				if (queryFieldCount % 3 != 0) {
					rowCount++;
				}
				rowCount++;
				headerDivTopPx = rowHeight * (rowCount + 1);
				dataDivTopPx = headerDivTopPx + rowHeight;
				dataDivHeight = 590 - dataDivTopPx;
			}
			if(displayFields != null && !displayFields.isEmpty()){
				displayFieldCount = displayFields.getSize();
				tableWidth = tdWidth * displayFieldCount;
				if(tableWidth < 800){
					tableWidth = 800;
				}
			}
		} catch (QueryException ex) {
			ex.printLog();
		}
	}


    /**
     * ���ܣ���ȡ��ѯ����
     * @return String
     * @throws QueryException
     */
   public String getQueryParas() throws QueryException {
		StringBuffer queryParas = new StringBuffer();
		try {
			if (queryFieldCount > 0) {
				IntegratedDTO field = null;
				String fieldName = "";
				String fieldDesc = "";
				Object fieldValue = "";
				IntadgratedQueryDTO dto = (IntadgratedQueryDTO) dtoParameter;
				queryParas.append("<table border=\"0\" width=\"100%\" cellspacing=\"1\" style=\"TABLE-LAYOUT:fixed;word-break:break-all\">");
				for (int i = 0; i < queryFieldCount; i++) {
					if (i % 3 == 0) {
						if(i > 0){
							queryParas.append("</tr>");
							queryParas.append(WorldConstant.ENTER_CHAR);
						}
						queryParas.append(WorldConstant.TAB_CHAR);
						queryParas.append("<tr>");
					}
					field = (IntegratedDTO) queryFields.getDTO(i);
					fieldDesc = field.getFieldDesc();
					fieldName = field.getFieldName();
					fieldName = StrUtil.getJavaField(fieldName);
					fieldValue = ReflectionUtil.getProperty(dto, fieldName);
					queryParas.append(WorldConstant.ENTER_CHAR);
					queryParas.append(WorldConstant.TAB_CHAR);
					queryParas.append(WorldConstant.TAB_CHAR);
					queryParas.append("<td width=\"17%\" height=\"");
					queryParas.append(rowHeight);
					queryParas.append("\" align=\"right\">");
					queryParas.append(fieldDesc);
					queryParas.append("��</td>");
					queryParas.append(WorldConstant.ENTER_CHAR);
					queryParas.append(WorldConstant.TAB_CHAR);
					queryParas.append(WorldConstant.TAB_CHAR);
					queryParas.append("<td><input type=\"text\" name=\"");
					queryParas.append(fieldName);
					queryParas.append("\" value=\"");
					queryParas.append(fieldValue);
					queryParas.append("\" style=\"width:100%; border: 1px solid #226E9B\"></td>");
				}
				queryParas.append("</table>");
			}
		} catch (ReflectException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return queryParas.toString();
	}
	/**
	 * ���ܣ���ȡͨ�ò�ѯ�Զ���SQL
	 * @return SQLModel
	 * @throws QueryException
	 */
	public SQLModel getCommonQueryModel() throws QueryException {
		SQLModel sqlModel = new SQLModel();
		try {
			String sqlStr = "SELECT ";
			List sqlArgs = new ArrayList();
			if (displayFields != null && !displayFields.isEmpty()) {
				IntegratedDTO field = null;
				for (int i = 0; i < displayFieldCount; i++) {
					field = (IntegratedDTO) displayFields.getDTO(i);
					sqlStr += field.getFieldName();
					if (i < displayFieldCount - 1) {
						sqlStr += ", ";
					}
				}
				sqlStr += " FROM ETS_ITEM_INFO_V";
				if (queryFields != null && !queryFields.isEmpty()) {
					Object fieldValue = null;
					IntadgratedQueryDTO dto = (IntadgratedQueryDTO) dtoParameter;
					for (int i = 0; i < queryFieldCount; i++) {
						field = (IntegratedDTO) queryFields.getDTO(i);
						String fieldName = field.getFieldName();
						if(i == 0){
							sqlStr += " WHERE";
						} else {
							sqlStr += " AND";
						}
						sqlStr += " ( " + SyBaseSQLUtil.isNull() + "  OR " + fieldName + " LIKE dbo.NVL(?, " + fieldName + "))";
						fieldName = StrUtil.getJavaField(fieldName);
						fieldValue = ReflectionUtil.getProperty(dto, fieldName);
						sqlArgs.add(fieldValue);
						sqlArgs.add(fieldValue);
					}
				}
				sqlModel.setSqlStr(sqlStr);
				sqlModel.setArgs(sqlArgs);
			}
		} catch (ReflectException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return sqlModel;
	}

	public DTOSet getDisplayFields() {
		return displayFields;
	}

	public String getHeaderDivTopPx() {
		return String.valueOf(headerDivTopPx) + "px";
	}

	public String getDataDivTopPx() {
		return String.valueOf(dataDivTopPx) + "px";
	}

	public String getTdWidthPx(){
		return String.valueOf(tdWidth) + "px";
	}

	public String getTableWidthPx(){
		return String.valueOf(tableWidth) + "px";
	}

	public String getDataDiveightPx(){
		return String.valueOf(dataDivHeight) + "px";
	}
}
