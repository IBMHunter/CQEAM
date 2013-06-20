package com.sino.ams.dzyh.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.ams.dzyh.dto.EamDhCheckLineDTO;
import com.sino.ams.dzyh.model.DHOrderUploadModel;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class DHOrderHeaderUploadDAO extends AMSBaseDAO {

	public DHOrderHeaderUploadDAO(BaseUserDTO userAccount, EamDhCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO)dtoParameter;
		sqlProducer = new DHOrderUploadModel(userAccount, dto);
	}

	/**
	 * ���ܣ��鵵����ͷ��Ϣ
	 * @throws DataHandleException
	 */
	public void uploadOrderHeader() throws DataHandleException {
		try {
			DHOrderUploadModel modelProducer = (DHOrderUploadModel) sqlProducer;
			EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO) dtoParameter;
			dto.setOrderStatus(LvecDicts.CHK_STATUS_UPLOADED);
			if ( dto.getScanoverBy() > -1 ) {
				dto.setScanoverBy(userAccount.getUserId());
				dto.setCurrCalendar("scanoverDate");
			}
			dto.setUploadBy(userAccount.getUserId());
			dto.setCurrCalendar("uploadDate");
			setDTOParameter(dto);
			SQLModel sqlModel = modelProducer.getOrderUploadModel(); //���µ���Ϊ������״̬
			DBOperator.updateRecord(sqlModel, conn);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}


	/**
	 * ���ܣ���ȡ��ǰ�����µ��豸
	 * @param includePDA boolean
	 * @return DTOSet
	 * @throws QueryException
	 */
	public DTOSet getOrderBarcodes(boolean includePDA) throws QueryException {
		DHOrderUploadModel modelProducer = (DHOrderUploadModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getOrderBarcodesModel(includePDA);
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(EamDhCheckLineDTO.class.getName());
		simp.executeQuery();
		return simp.getDTOSet();
	}
}
