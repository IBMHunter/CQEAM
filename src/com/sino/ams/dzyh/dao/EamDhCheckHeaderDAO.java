package com.sino.ams.dzyh.dao;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.dzyh.constant.LvecDicts;
import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.ams.dzyh.model.EamDhCheckHeaderModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EamDhCheckHeaderDAO</p>
 * <p>Description:�����Զ����ɷ������EamDhCheckHeaderDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */

public class EamDhCheckHeaderDAO extends AMSBaseDAO {

	/**
	 * ���ܣ���ֵ�׺��̵㹤��ͷ�� EAM_DH_CHECK_HEADER ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhCheckHeaderDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamDhCheckHeaderDAO(BaseUserDTO userAccount, EamDhCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO)dtoParameter;
		sqlProducer = new EamDhCheckHeaderModel((SfUserDTO)userAccount, dto);
	}

	/**
	 * ���ܣ����湤��(���ݴ湤�����·�����)
	 * @throws DataHandleException
	 */
	public void saveOrder() throws DataHandleException {
		EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO)dtoParameter;
		try {
			if (dto.getHeaderId().equals("")) {
				SeqProducer seqPrd = new SeqProducer(conn);
				//TODO
				dto.setHeaderId(seqPrd.getStrNextSeq("EAM_DH_CHECK_HEADER") + "" );
				String companyCode = userAccount.getCompanyCode();
				String orderType = dto.getOrderType();
				OrderNumGenerator numPrd = new OrderNumGenerator(conn, companyCode, orderType);
				dto.setOrderNo(numPrd.getOrderNum());
			}
			createData();
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ����ݹ�����ID��������
	 * @param batchIds String[]
	 * @throws DataHandleException
	 */
	public void cancelOrders(String[] batchIds) throws DataHandleException {
		EamDhCheckHeaderModel modelProducer = (EamDhCheckHeaderModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getOrderCancelByBatchIdsModel(batchIds);
		DBOperator.updateRecord(sqlModel, conn);
	}


	public Object getDataByForeignKey(String foreigeKey) throws QueryException {
		Object data = super.getDataByForeignKey(foreigeKey);
		EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO) dtoParameter;
		if(dto.getOrderType().equals(LvecDicts.ORD_TYPE1_YQYB)){//����������Ǳ����Ҫ���������ȷ�Ϸ�ʽ����
			if (!StrUtil.isEmpty(dtoClassName)) {
				if (data != null) {
					DTOSet dtos = (DTOSet) data;
					data = prdCheckToolsOpt(dtos);
				}
			}
		}
		return data;
	}

	/**
	 * ���ܣ�Ϊ��������������������
	 * @param orders DTOSet
	 * @return DTOSet
	 * @throws QueryException
	 */
	private DTOSet prdCheckToolsOpt(DTOSet orders) throws QueryException {
		try {
			EamDhCheckHeaderModel modelProducer = (EamDhCheckHeaderModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getCheckToolsModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.executeQuery();
			if (simp.hasResult()) {
				RowSet options = simp.getSearchResult();
				int optionCount = options.getSize();
				int dataCount = orders.getSize();
				StringBuffer cfmOpt = null;
				EamDhCheckHeaderDTO orderHeader = null;
				String checkTools = "";
				String optCode = "";
				String optLabel = "";
				String selProp = "";
				for (int i = 0; i < dataCount; i++) {
					cfmOpt = new StringBuffer();
					orderHeader = (EamDhCheckHeaderDTO) orders.getDTO(i);
					checkTools = orderHeader.getCheckTools();
					cfmOpt.append("<option value=\"\">--��ѡ��--</option>");
					for (int j = 0; j < optionCount; j++) {
						Row option = options.getRow(j);
						optCode = option.getStrValue("CODE");
						optLabel = option.getStrValue("VALUE");
						if (checkTools.equals(optCode)) {
							selProp = " selected";
							orderHeader.setCheckToolsValue(optLabel);
						} else {
							selProp = "";
						}
						cfmOpt.append("<option value=\"");
						cfmOpt.append(optCode);
						cfmOpt.append("\"");
						cfmOpt.append(selProp);
						cfmOpt.append(">");
						cfmOpt.append(optLabel);
						cfmOpt.append("</option>");
					}
					orderHeader.setCheckToolsOpt(cfmOpt.toString());
				}
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return orders;
	}


	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		EamDhCheckHeaderModel modelProducer = null;
		try {
			modelProducer = (EamDhCheckHeaderModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "����ִ�����";
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
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new WebFileDownException(ex);
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
		fieldMap.put("BATCH_NO", "��������");
		fieldMap.put("ORDER_NO", "�������");
		fieldMap.put("LOCATION_NAME", "�����ص�");
		fieldMap.put("START_TIME", "��ʼ����");
		fieldMap.put("IMPLEMENT_DAYS", "ִ������");
		fieldMap.put("ORDER_TYPE_VALUE", "��������");
		fieldMap.put("CHECK_TOOLS_VALUE", "ȷ�Ϸ�ʽ");
		fieldMap.put("ORDER_STATUS_VALUE", "����״̬");
		fieldMap.put("DISTRIBUTE_DATE", "�·�����");
		fieldMap.put("DISTRIBUTE_USER", "�·���");

		fieldMap.put("IMPLEMENT_USER", "ִ����");
		fieldMap.put("DOWNLOAD_DATE", "��������");
		fieldMap.put("DOWNLOAD_USER", "������");
		fieldMap.put("SCANOVER_DATE", "ɨ���������");
		fieldMap.put("SCANOVER_USER", "ɨ�������");
		fieldMap.put("UPLOAD_DATE", "��������");
		fieldMap.put("UPLOAD_USER", "������");
		fieldMap.put("ARCHIVED_DATE", "�鵵����");
		fieldMap.put("ARCHIVED_USER", "�鵵��");

		return fieldMap;
	}
}
