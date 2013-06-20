package com.sino.ams.newasset.servlet;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dao.ApproveContentDAO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.data.RowSet;
import com.sino.base.exception.QueryException;
import com.sino.base.message.Message;
import com.sino.framework.servlet.BaseServlet;

/**
 * 
 * @ϵͳ����: 
 * @��������: �ʲ���������ת�������
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Nov 1, 2011
 */
public abstract class AssetsTransFlowBaseServlet extends BaseServlet {
	
	/**
	 * @�������� ���������б�
	 * @param req
	 * @param conn
	 * @param loseDTO
	 * @throws QueryException
	 */
	public void doApproveContent(HttpServletRequest req, Connection conn,
			String transId ) throws QueryException {
		String tableName = "AMS_ASSETS_TRANS_HEADER";
		RowSet rows = null;
		rows = ApproveContentDAO.getApproveContent(conn,  transId , tableName);
		req.setAttribute(AssetsWebAttributes.APPROVE_CONTENT, rows);
	}
	
	public void setHandleMessage( HttpServletRequest req,  Message message){
		req.setAttribute(MessageConstant.MESSAGE_DATA, message );
	}
}
