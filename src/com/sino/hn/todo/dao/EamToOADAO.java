package com.sino.hn.todo.dao;

import java.sql.Connection;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.hn.todo.model.EamToOAModel;
import com.sino.sinoflow.todo.dao.OaTodoDAO;
import com.sino.sinoflow.todo.dto.OaTodoDTO;

/**
 * 
 * @ϵͳ����: 
 * @��������: ��ȡ��Ҫ���͵�����
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Dec 1, 2011
 */
public class EamToOADAO extends OaTodoDAO {
	EamToOAModel eamToOAModel = null;

	public EamToOADAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		this.initSQLProducer(userAccount, dtoParameter);
	}

	@Override
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		oaTodoForm = (OaTodoDTO) dtoParameter;
		eamToOAModel = new EamToOAModel((SfUserDTO) userAccount, oaTodoForm);
		super.sqlProducer = eamToOAModel;
	}

	/**
	 * ��ȡ��Ҫ���͵�����
	 * @return
	 * @throws QueryException
	 */
	public DTOSet getTodoDataSet() throws QueryException {
		SimpleQuery query = new SimpleQuery(eamToOAModel.getEamToOASQLModel(),
				conn);
		query.setDTOClassName(OaTodoDTO.class.getName());
		query.executeQuery();
		if (query.hasResult()) {
			return query.getDTOSet();
		} else {
			return null;
		}
	}

}
