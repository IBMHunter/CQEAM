package com.sino.ams.newasset.dao;


import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsRcvHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsRcvLineDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.model.AmsAssetsRcvLineModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: AmsAssetsRcvLineDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsRcvLineDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class AmsAssetsRcvLineDAO extends AMSBaseDAO {
	private AmsAssetsRcvHeaderDTO rcvHeader = null;

	/**
	 * ���ܣ��ʲ����������б�(���ڲ��ż�͹�˾���ʲ�����) AMS_ASSETS_RCV_LINE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsRcvLineDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsAssetsRcvLineDAO(SfUserDTO userAccount, AmsAssetsRcvLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsRcvLineDTO dto = (AmsAssetsRcvLineDTO) dtoParameter;
		sqlProducer = new AmsAssetsRcvLineModel((SfUserDTO) userAccount, dto);
	}

	public void setRcvHeader(AmsAssetsRcvHeaderDTO rcvHeader) {
		this.rcvHeader = rcvHeader;
	}

	/**
	 * ���ܣ����ݽ��շ�������Ϣ���ɵ������յ�����Ϣ
	 * @param assignLines DTOSet
	 * @throws DataHandleException
	 */
	public void createRcvLines(DTOSet assignLines) throws DataHandleException {
		try {
			AmsAssetsRcvLineModel modelProducer = (AmsAssetsRcvLineModel) sqlProducer;
			int lineCount = assignLines.getSize();
			AmsAssetsTransLineDTO assignLine = null;
			AmsAssetsRcvLineDTO rcvLine = null;
			SQLModel sqlModel = null;
			for (int i = 0; i < lineCount; i++) {
				assignLine = (AmsAssetsTransLineDTO) assignLines.getDTO(i);
				rcvLine = new AmsAssetsRcvLineDTO();
				rcvLine.setReceiveHeaderId(StrUtil.strToInt(rcvHeader.getReceiveHeaderId()));
				rcvLine.setTransLineId(assignLine.getLineId());
				rcvLine.setCurrCreationDate();
				rcvLine.setCreatedBy(userAccount.getUserId());
				setDTOParameter(rcvLine);
				sqlModel = modelProducer.getDataCreateModel();
				DBOperator.updateRecord(sqlModel, conn);
			}
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}
}
