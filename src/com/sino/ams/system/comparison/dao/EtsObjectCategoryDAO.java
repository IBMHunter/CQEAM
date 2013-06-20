package com.sino.ams.system.comparison.dao;

import java.sql.*;

import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.comparison.dto.EtsObjectCategoryDTO;
import com.sino.ams.system.comparison.model.EtsObjectCategoryModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>
 * Title: EtsObjectCategoryDAO
 * </p>
 * <p>
 * Description:�����Զ����ɷ������EtsObjectCategoryDAO�����������Ҫ�����޸�
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: ����˼ŵ����Ϣ�������޹�˾
 * </p>
 * 
 * @author Zyun
 * @version 1.0
 */

public class EtsObjectCategoryDAO extends AMSBaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���������ʹ��רҵ������һ��רҵ���Զ��ļ���רҵ��������(EAM) ETS_OBJECT_CATEGORY ���ݷ��ʲ㹹�캯��
	 * 
	 * @param userAccount
	 *            SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter
	 *            EtsObjectCategoryDTO ���β���������
	 * @param conn
	 *            Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsObjectCategoryDAO(SfUserDTO userAccount,
			EtsObjectCategoryDTO dtoParameter, Connection conn) {
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
		EtsObjectCategoryDTO dtoPara = (EtsObjectCategoryDTO) dtoParameter;
		super.sqlProducer = new EtsObjectCategoryModel((SfUserDTO) userAccount,
				dtoPara);
	}

	/**
	 * ���ܣ������ʲ��ص��(EAM)��ETS_OBJECT�����ݡ�
	 * 
	 * @return boolean
	 * @param codes
	 */
	public void createData(String objectCategory, String[] codes)
			throws DataHandleException {
		for (int i = 0; i < codes.length; i++) {
			EtsObjectCategoryDTO dtoPara = (EtsObjectCategoryDTO) dtoParameter;
			dtoPara.setSearchCategory(codes[i]);
			super.createData();
		}
		// EtsObjectCategoryDTO dtoPara = (EtsObjectCategoryDTO) dtoParameter;
		// String objectCategory = dtoPara.getObjectCategory();
		// try {
		// DTOSet dtos = getDistriDatas(objectCategory,codes);
		//
		// } catch (DTOException e) {
		// e.printStackTrace();
		// }
		// super.createData();

		// getMessage().addParameterValue();
		// return operateResult;
	}

	/**
	 * ���ܣ�ִ�е���ʧЧ������
	 * 
	 * @return boolean
	 */
	public void deleteData() throws DataHandleException {
		super.deleteData();
		getMessage().addParameterValue("�ص���Ϣ");
		// return operateResult;
	}

	/**
	 * ���ܣ������ʲ��ص��(EAM)��ETS_OBJECT�����ݡ�
	 * 
	 * @return boolean
	 */
	public String updateData(String objectCategory, String[] codes)
			throws DataHandleException {
		String operateResult = "";
		boolean autoCommit = false;
		boolean hasError = true;
		try {

			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			EtsObjectCategoryModel model = (EtsObjectCategoryModel) sqlProducer;
			DBOperator.updateRecord(model.getDataDeleteModel(objectCategory),
					conn);
			for (int i = 0; i < codes.length; i++) {
				DBOperator.updateRecord(model.getDataCreateModel(
						objectCategory, codes[i]), conn);
			}
			operateResult = "Y";
			conn.commit();
			hasError = false;
			getMessage().addParameterValue("רҵ��");
		} catch (SQLException ex) {
			Logger.logError(ex);
			prodMessage(MsgKeyConstant.SQL_ERROR);
		} catch (DataHandleException ex) {
			ex.printLog();
			prodMessage(MsgKeyConstant.COMMON_ERROR);
		} catch (SQLModelException e) {
			e.printStackTrace();
		} finally {
			try {
				if (hasError) {
					operateResult = "N";
					conn.rollback();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
				prodMessage(MsgKeyConstant.SQL_ERROR);
			}
		}
		return operateResult;
	}

	public static DTOSet getDistriDatas(String objectCategory, String[] codes)
			throws DTOException {
		DTOSet distrDatas = new DTOSet();
		if (codes != null) {
			EtsObjectCategoryDTO distrData = null;
			// System.out.println("itemData.getItemCode = " +
			// itemData.getItemCode());
			for (int i = 0; i < codes.length; i++) {
				distrData = new EtsObjectCategoryDTO();
				distrData.setObjectCategory(objectCategory);
				distrData.setSearchCategory(codes[i]);
				distrDatas.addDTO(distrData);
			}
		}
		return distrDatas;
	}

	public DTO getDataByPrimaryKey() throws QueryException {
		DTO dto = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			EtsObjectCategoryModel modelProducer = (EtsObjectCategoryModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPrimaryKeyDataModel();
			String sqlStr = sqlModel.getSqlStr();
			st = conn.createStatement();
			rs = st.executeQuery(sqlStr);
			if(rs.next()){
				dto = new EtsObjectCategoryDTO();
				ResultSetMetaData rsmd = rs.getMetaData();
				int colCount = rsmd.getColumnCount();
				for(int i = 1; i <= colCount; i++){
					String columnName = rsmd.getColumnName(i);
					Object fieldValue = rs.getObject(i);
					String fieldName = StrUtil.getJavaField(columnName);
					ReflectionUtil.setProperty(dto, fieldName, fieldValue);
				}
			}
		} catch (Throwable ex) {
			Logger.logError(ex);
			throw new QueryException(ex.getMessage());
		} finally {
			DBManager.closeDBResultSet(rs);
			DBManager.closeDBStatement(st);
		}
		return dto;
	}
}