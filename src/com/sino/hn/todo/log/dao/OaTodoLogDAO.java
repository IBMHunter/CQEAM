package com.sino.hn.todo.log.dao;

import java.sql.Connection;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.hn.todo.log.dto.OaTodoLogDTO;
import com.sino.hn.todo.log.model.OaTodoLogModel;

/**
 * 
 * @ϵͳ����: 
 * @��������: 
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Nov 30, 2011
 */
public class OaTodoLogDAO extends BaseDAO {
	private OaTodoLogDTO oaTodoLogForm = null;
	private OaTodoLogModel oaTodoLogModel = null;

	public OaTodoLogDAO(BaseUserDTO userAccount, DTO dtoParameter,
			Connection conn) {
		super(userAccount, dtoParameter, conn);
		this.initSQLProducer(userAccount, dtoParameter);
	}

	@Override
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		oaTodoLogForm = (OaTodoLogDTO) dtoParameter;
		oaTodoLogModel = new OaTodoLogModel((SfUserDTO) userAccount,
				oaTodoLogForm);
		sqlProducer = oaTodoLogModel;
	}

	public OaTodoLogDTO getOaTodoLogForm() {
		return oaTodoLogForm;
	}

	public void setOaTodoLogForm(OaTodoLogDTO oaTodoLogForm) {
		this.oaTodoLogForm = oaTodoLogForm;
		initSQLProducer( null , oaTodoLogForm );
	}

}
