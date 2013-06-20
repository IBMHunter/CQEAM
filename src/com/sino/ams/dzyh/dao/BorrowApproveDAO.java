package com.sino.ams.dzyh.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.ams.dzyh.constant.LvecMessageKeys;
import com.sino.ams.dzyh.dto.EamYbBorrowLogDTO;
import com.sino.ams.dzyh.model.BorrowApproveModel;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.StrException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.util.StrUtil;
import com.sino.base.web.WebRadio;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: BorrowApproveDAO</p>
 * <p>Description:�����Զ����ɷ������EamYbBorrowLogDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class BorrowApproveDAO extends AMSBaseDAO {

	/**
	 * ���ܣ������Ǳ������־ EAM_YB_BORROW_LOG ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamYbBorrowLogDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public BorrowApproveDAO(BaseUserDTO userAccount, EamYbBorrowLogDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		EamYbBorrowLogDTO dto = (EamYbBorrowLogDTO)dtoParameter;
		sqlProducer = new BorrowApproveModel(userAccount, dto);
	}

	public Object getDataByPrimaryKey() throws QueryException {
		Object data = super.getDataByPrimaryKey();
		try {
			if (!StrUtil.isEmpty(dtoClassName)) {
				EamYbBorrowLogDTO dto = (EamYbBorrowLogDTO) data;
				dto.setStatusRadio(getSatusRadio(dto));
				dto.setApproveUserId(userAccount.getUserId());
				dto.setApproveUser(userAccount.getUsername());
				dto.setCurrCalendar("approveDate");
				data = dto;
			}
		} catch (StrException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return data;
	}

	/**
	 * ���ܣ�����������ѡ��ť
	 * @param dto EamYbBorrowLogDTO
	 * @return String
	 * @throws StrException
	 */
	private String getSatusRadio(EamYbBorrowLogDTO dto) throws StrException {
		StringBuffer approveRadio = new StringBuffer();
		WebRadio radio = new WebRadio("status");
		radio.addValueCaption(LvecDicts.YB_BR_STATUS1_APPROVE, "ͨ��");
		radio.addValueCaption(LvecDicts.YB_BR_STATUS1_NAPPRO, "��ͨ��");
		radio.setCheckedValue(dto.getStatus());
		approveRadio.append(radio);
		return approveRadio.toString();
	}


	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		BorrowApproveModel modelProducer = null;
		try {
			modelProducer = (BorrowApproveModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "�������Ǳ��豸�������������";
			String fileName = reportTitle + ".xls";
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			Map fieldMap = getFieldMap();
			rule.setFieldMap(fieldMap);
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(reportTitle);
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			rule.setCalPattern(LINE_PATTERN);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		} catch (DataTransException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
		}
		return file;
	}

	private Map getFieldMap(){
		Map fieldMap = new HashMap();
		fieldMap.put("TASK_NAME", "��������");
		fieldMap.put("START_DATE", "��ʼ����");
		fieldMap.put("END_DATE", "��������");
		fieldMap.put("TASK_STATUS_VALUE", "����״̬");
		fieldMap.put("CREATION_DATE", "��������");
		return fieldMap;
	}
	/**
	 * ���ܣ����������Ǳ��������
	 * @return boolean
	 */
	public boolean approveBorrowApply(){
		boolean operateResult = false;
		try {
			BorrowApproveModel modelProducer = (BorrowApproveModel)sqlProducer;
			SQLModel sqlModel = modelProducer.getBorrowApproveModel();
			DBOperator.updateRecord(sqlModel, conn);
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} finally{
			if(operateResult){
				prodMessage(LvecMessageKeys.BORROW_APPLY_APPROVE_SUCCESS);
			} else {
				prodMessage(LvecMessageKeys.BORROW_APPLY_APPROVE_FAILURE);
				message.setIsError(true);
			}
			EamYbBorrowLogDTO dto = (EamYbBorrowLogDTO) dtoParameter;
			message.addParameterValue(dto.getBarcode());
			message.setNeedClose(true);
		}
		return operateResult;
	}
}
