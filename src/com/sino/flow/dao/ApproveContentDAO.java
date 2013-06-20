package com.sino.flow.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sino.base.constant.WorldConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.flow.constant.ReqAttributeList;
import com.sino.flow.model.ApproveContentModel;

/**
 * Created by wwb.
 * User: demo
 * Date: 2006-12-21
 * Time: 12:03:43
 */
public class ApproveContentDAO {
    private Connection conn;
    private HttpServletRequest req;


    public ApproveContentDAO(Connection conn, HttpServletRequest req) {
        this.conn = conn;
        this.req = req;
    }

	/**
	 * ���ܣ���ѯӦ�õ�������������ؽ���� ������Ӧ��ID����������¼
	 * @param applyId String
	 * @param appTableName String
	 * @throws QueryException
	 */
    public void getApproveContent(String applyId, String appTableName) throws QueryException {
        SimpleQuery sq = new SimpleQuery(ApproveContentModel.getContentModel(applyId, appTableName), conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        req.setAttribute(ReqAttributeList.APPROVE_CONTENT_DATA,rs);
    }

	/**
	 * ���ܣ���ȡ�����������������������
	 * @param actId String
	 * @throws QueryException
	 */
    public void getApproveContent(String actId) throws QueryException {
        SimpleQuery sq = new SimpleQuery(ApproveContentModel.getContentModel(actId), conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        req.setAttribute(ReqAttributeList.APPROVE_CONTENT_DATA,rs);
    }

	/**
	 * ���ܣ��������������Ϊexcel�ļ�.��������������
	 * @param actId String
	 * @return
	 * @throws DataTransException
	 */
	public File getExportFile(String actId) throws DataTransException {
		String reportTitle = "������ת���";
		String fileName = reportTitle + ".xls";
		TransRule rule = new TransRule();
		SQLModel sqlModel = ApproveContentModel.getContentModel(actId);
		rule.setDataSource(sqlModel);
		rule.setSourceConn(conn);
		String filePath = WorldConstant.USER_HOME;
		filePath += WorldConstant.FILE_SEPARATOR;
		filePath += fileName;
		rule.setTarFile(filePath);
		DataRange range = new DataRange();
		rule.setDataRange(range);
		Map fieldMap = new HashMap();
		fieldMap.put("USER_NAME", "������");
		fieldMap.put("APPROVE_TIME", "����ʱ��");
		fieldMap.put("APPROVE_CONTENT", "�������");
		rule.setFieldMap(fieldMap);
		TransferFactory factory = new TransferFactory();
		DataTransfer transfer = factory.getTransfer(rule);
		transfer.transData();
		return (File) transfer.getTransResult();
	}


	/**
	 * ���ܣ��������������Ϊexcel�ļ�
	 * @param applyId String
	 * @param appTableName String
	 * @return
	 * @throws DataTransException
	 */
	public File getExportFile(String applyId, String appTableName) throws DataTransException {
		String reportTitle = "������ת���";
		String fileName = reportTitle + ".xls";
		TransRule rule = new TransRule();
		SQLModel sqlModel = ApproveContentModel.getContentModel(applyId, appTableName);
		rule.setDataSource(sqlModel);
		rule.setSourceConn(conn);
		String filePath = WorldConstant.USER_HOME;
		filePath += WorldConstant.FILE_SEPARATOR;
		filePath += fileName;
		rule.setTarFile(filePath);
		DataRange range = new DataRange();
		rule.setDataRange(range);
		Map fieldMap = new HashMap();
		fieldMap.put("USER_NAME", "������");
		fieldMap.put("APPROVE_TIME", "����ʱ��");
		fieldMap.put("APPROVE_CONTENT", "�������");
		rule.setFieldMap(fieldMap);
		TransferFactory factory = new TransferFactory();
		DataTransfer transfer = factory.getTransfer(rule);
		transfer.transData();
		return (File) transfer.getTransResult();
	}
}
