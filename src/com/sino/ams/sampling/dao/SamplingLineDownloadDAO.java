package com.sino.ams.sampling.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.sampling.dto.AmsAssetsSamplingLineDTO;
import com.sino.ams.sampling.model.SamplingLineDownloadModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class SamplingLineDownloadDAO extends AMSBaseDAO {

	public SamplingLineDownloadDAO(SfUserDTO userAccount, AmsAssetsSamplingLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 *
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 * @todo Implement this com.sino.framework.dao.BaseDAO method
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsSamplingLineDTO dto = (AmsAssetsSamplingLineDTO)dtoParameter;
		super.sqlProducer = new SamplingLineDownloadModel((SfUserDTO)userAccount, dto);
	}

	/**
	 * ���ܣ���ȡ���ص��ʲ�
	 * @return DTOSet
	 * @throws QueryException
	 */
	public DTOSet getDownloadAssets() throws QueryException {
		SamplingLineDownloadModel modelProducer = (SamplingLineDownloadModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getDownloadAssetsModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setSql(sqlModel);
		simp.setDTOClassName(AmsAssetsSamplingLineDTO.class.getName());
		simp.executeQuery();
		return simp.getDTOSet();
	}
}
