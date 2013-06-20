package com.sino.ams.dzyh.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EamDhCheckLineDTO;
import com.sino.ams.dzyh.model.DHLineDownloadModel;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class DHLineDownloadDAO extends AMSBaseDAO {

	public DHLineDownloadDAO(BaseUserDTO userAccount, EamDhCheckLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EamDhCheckLineDTO dto = (EamDhCheckLineDTO)dtoParameter;
		sqlProducer = new DHLineDownloadModel(userAccount, dto);
	}


	/**
	 * ���ܣ���ȡ���ص��ʲ�
	 * @return DTOSet
	 * @throws QueryException
	 */
	public DTOSet getDownloadAssets() throws QueryException {
		DHLineDownloadModel modelProducer = (DHLineDownloadModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getDownloadAssetsModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setSql(sqlModel);
		simp.setDTOClassName(EamDhCheckLineDTO.class.getName());
		simp.executeQuery();
		return simp.getDTOSet();
	}
}
