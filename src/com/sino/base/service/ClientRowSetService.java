package com.sino.base.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.Request2DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.log.Logger;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

/**
 * ����:���ݿͶ��ύ�Ĳ����Ҷ�Ӧ��SQLModel�� ��ִ��SQLModel�������JSON��ʽ���ؿͻ���
 *
 */
public class ClientRowSetService extends BaseServlet {

	private static final long serialVersionUID = -4019652176512931838L;
	private final String SF_USER_DTO = "com.sino.sinoflow.user.dto.SfUserBaseDTO";

	/**
	 * ���ܣ������󲢽������Ľ�����ؿͻ���
	 *
	 * @throws ServletException,
	 *             IOException
	 */
	public void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=GBK");

		/* ���տͻ��˴��������� */
		String modelClassName = request.getParameter("modelClassName");
		/* ���տͻ��˴����ķ����� */
		String methodName = request.getParameter("methodName");
		/*
		 * ���տͻ��˴����ķ����Ĳ����б� ע:��������ΪString���ͣ���֧����������,������������.
		 */
		String methodParameterName[] = request
				.getParameterValues("methodParameterName");
		SQLModel sqlModel = null;
		try {
			sqlModel = getSQLMoudel(modelClassName, methodName,
					methodParameterName, request);
		} catch (InvocationTargetException e) {
			Logger.logError(e);
		} catch (NoSuchMethodException e) {
			Logger.logError(e);
		} catch (IllegalAccessException e) {
			Logger.logError(e);
		} catch (InstantiationException e) {
			Logger.logError(e);
		} catch (ClassNotFoundException e) {
			Logger.logError(e);
		} catch (DTOException e) {
			Logger.logError(e);
		}
		PrintWriter out = response.getWriter();
		out.println(getData(sqlModel, request).toString());
		out.close();
	}
 
	/*
	 * ���ܣ����ݿͻ��˴����Ĳ������Ҷ�Ӧ��SQLMoudel
	 */
	private SQLModel getSQLMoudel(String className, String methodName,
			String[] methodParameterName, HttpServletRequest request)
			throws InvocationTargetException, NoSuchMethodException,
			IllegalAccessException, InstantiationException,
			ClassNotFoundException, DTOException {

		SfUserBaseDTO userAccount = (SfUserBaseDTO) SessionUtil.getUserAccount(request);

		Request2DTO req2DTO = new Request2DTO();
		Class objClass = Class.forName(className);
		Object obj = null;
		Constructor[] cons = objClass.getConstructors();

		/* �жϹ��췽�����������Ƿ�Ϊ2����һ����SfUserDTO���ڶ���:�Ƿ�Ϊ�ͻ��˴���Model�����DTO */
		for(int i=0;i<cons.length;i++){
			Constructor c = cons[i];
			Class[] consClass = c.getParameterTypes();
			if (consClass.length == 2
					&& consClass[0].getName().equals(SF_USER_DTO)) {

				String argsName = consClass[1].getName();
				argsName = argsName.substring(argsName.lastIndexOf(".") + 1);
				argsName = argsName.substring(0, argsName.length() - 3)
						+ "Model";

				if (argsName.equals(className.substring(className
						.lastIndexOf(".") + 1))) {
					req2DTO.setDTOClassName(consClass[1].getName());
					obj = c.newInstance(new Object[] { userAccount,
							req2DTO.getDTO(request)});
				}
			} else {
				obj = objClass.newInstance();
			}
		}

		Method method = null;
		if (methodParameterName != null && methodParameterName.length > 0) {
			Class[] cla = new Class[methodParameterName.length];
			for (int i = 0; i < methodParameterName.length; i++) {
				cla[i] = String.class;
			}
			method = objClass.getDeclaredMethod(methodName, cla);
		} else {
			method = objClass.getDeclaredMethod(methodName, null);
		}
		SQLModel sqlModel = (SQLModel) method.invoke(obj,
				(Object[]) methodParameterName);
		return sqlModel;

	}

	/**
	 * ���ܣ�����SQLModelȥ��ѯ�����,������ѯ����RowSet��װΪ����Ӧ��JSONArray ����
	 */
	private JSONArray getData(SQLModel sqlModel, HttpServletRequest req) {

		SimpleQuery simp = null;
		Connection conn = null;
		JSONArray jsonArray = new JSONArray();
		try {
			conn = getDBConnection(req);
			simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				RowSet rowSet = simp.getSearchResult();
				for (int i = 0; i < rowSet.getSize(); i++) {
					Row row = rowSet.getRow(i);
					JSONObject jsonObject = new JSONObject();
					for (int j = 0; j < row.getFieldCount(); j++) {
						jsonObject.put(row.getFieldNames().get(j).toString(),
								row.getValue(j));
					}
					jsonArray.put(jsonObject);
				}
			}
		} catch (Exception e) {
			Logger.logError(e);
		} finally {
			DBManager.closeDBConnection(conn);
		}

		return jsonArray;
	}
}


