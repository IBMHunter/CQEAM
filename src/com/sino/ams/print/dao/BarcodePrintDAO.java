package com.sino.ams.print.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.print.dto.BarcodeReceiveDTO;
import com.sino.ams.print.model.BarcodePrintModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

public class BarcodePrintDAO extends AMSBaseDAO {

	/**
	 *
	 * Title: 			SinoApplication
	 * @param userAccount		SfUserDTO  ����ϵͳ�����ղ����û�����
	 * @param dtoParameter		LabelDTO   װ�ڱ��β��������ݶ���
	 * @param conn		Connection  ���ݿ�����,�е����ߴ���
	 * Description:		Java Enterprise Edition Ӧ�ÿ���
	 * Copyright:		�����Ȩ����Copyright (c)2009~2022��
	 * Copyright: 		����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�
	 * Copyright: 		������Ȩ����˼ŵ����Ϣ�������޹�˾��һ����Χ��ʹ��
	 * Company: 		����˼ŵ����Ϣ�������޹�˾
	 * Function			ETS_ROLL_CALL_BARCODE ���ݷ��ʲ㹹�캯��
	 * @author 			����
	 * @version 		0.1
	 * @Date			Apr 26, 2009
	 */
	public BarcodePrintDAO(SfUserDTO userAccount, BarcodeReceiveDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		barcodePrintModel = new BarcodePrintModel((SfUserDTO) userAccount, dtoParameter);
	}

	private BarcodePrintModel barcodePrintModel = null;


	/**
	 *
	 * Title: 			SinoApplication
	 * @param userAccount		SfUserDTO  ����ϵͳ�����ղ����û�����
	 * @param dtoParameter		LabelDTO   װ�ڱ��β��������ݶ���
	 * @param conn		Connection  ���ݿ�����,�е����ߴ���
	 * Description:		Java Enterprise Edition Ӧ�ÿ���
	 * Copyright:		�����Ȩ����Copyright (c)2009~2022��
	 * Copyright: 		����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�
	 * Copyright: 		������Ȩ����˼ŵ����Ϣ�������޹�˾��һ����Χ��ʹ��
	 * Company: 		����˼ŵ����Ϣ�������޹�˾
	 * Function			SQL������BaseSQLProducer�ĳ�ʼ����
	 * @author 			����
	 * @version 		0.1
	 * @Date			Apr 26, 2009
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		BarcodeReceiveDTO dtoPara = (BarcodeReceiveDTO)dtoParameter;
		sqlProducer = new BarcodePrintModel(userAccount, dtoPara);
	}

	public boolean getBarcodeIsExist() throws QueryException{
		SQLModel sqlModel = barcodePrintModel.getBarcodeIsExist();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        boolean exist = simpleQuery.hasResult();;
        return exist;
	}

    public boolean getBarcodeIsExistInPrint() throws QueryException{
		SQLModel sqlModel = barcodePrintModel.getBarcodeIsExistInPrint();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        boolean exist = simpleQuery.hasResult();;
        return exist;
	}


}