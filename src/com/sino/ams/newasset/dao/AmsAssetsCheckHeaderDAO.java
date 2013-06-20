package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO;
import com.sino.ams.newasset.model.AmsAssetsCheckHeaderModel;
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
import com.sino.sms.bean.MessageSaver;
import com.sino.sms.constant.SMSConstant;
import com.sino.sms.dto.SfMsgDefineDTO;

/**
 * <p>Title: AmsAssetsCheckHeaderDAO</p>
 * <p>Description:�����Զ����ɷ������AmsAssetsCheckHeaderDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */


public class AmsAssetsCheckHeaderDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��ʲ��̵�ͷ��(EAM) AMS_ASSETS_CHECK_HEADER ���ݷ��ʲ㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsAssetsCheckHeaderDTO ���β���������
	 * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsAssetsCheckHeaderDAO(BaseUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO)dtoParameter;
		sqlProducer = new AmsAssetsCheckHeaderModel((SfUserDTO)userAccount, dto);
	}


	/**
	 * ����:���ݹ�����IDɾ��������Ϣ
	 * @throws DataHandleException
	 */
	public void deleteByBatchId() throws DataHandleException {
		AmsAssetsCheckHeaderModel modelProducer = (AmsAssetsCheckHeaderModel)sqlProducer;
		SQLModel sqlModel = modelProducer.getDeleteByForeignKeyModel("batchId");
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * ���ܣ������̵㹤����Ϣ���������̵㹤���Ĺ�����
	 * @throws DataHandleException
	 */
	public void saveCheckHeader() throws DataHandleException {
		AmsAssetsCheckHeaderModel modelProducer = (AmsAssetsCheckHeaderModel) sqlProducer;
		try {
			boolean isNewData = false;
			AmsAssetsCheckHeaderDTO headerDTO = (AmsAssetsCheckHeaderDTO) dtoParameter;
			String headerId = headerDTO.getHeaderId();
			if (StrUtil.isEmpty(headerId)) {
				SeqProducer seqProducer = new SeqProducer(conn);
				headerId = seqProducer.getGUID();
				headerDTO.setHeaderId(headerId);
				if (headerDTO.getTransNo().equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
					String companyCode = userAccount.getCompanyCode();
					String orderType = headerDTO.getOrderType();
					OrderNumGenerator numberProducer = new OrderNumGenerator(conn, companyCode, orderType);
					headerDTO.setTransNo(numberProducer.getOrderNum());
				}
				headerDTO.setCreatedBy(userAccount.getUserId());
				headerDTO.setOrganizationId(userAccount.getOrganizationId());
				isNewData = true;
			}
			setDTOParameter(headerDTO);
			if (isNewData) {
				createData();
			} else {
				updateData();
			}
			AmsAssetsCheckLineDTO lineDTO = new AmsAssetsCheckLineDTO();
			lineDTO.setHeaderId(headerDTO.getHeaderId());
			AmsAssetsCheckLineDAO lineDAO = new AmsAssetsCheckLineDAO(userAccount, lineDTO, conn);
			lineDAO.DeleteByForeignKey("headerId");
			setDTOParameter(headerDTO);
			SQLModel sqlModel = modelProducer.getLocAssetsSaveModel();
			DBOperator.updateRecord(sqlModel, conn);
		} catch (Throwable ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex.getMessage());
		}
	}

	/**
	 * ���ܣ����õص��Ƿ���δ�鵵����
	 * @return boolean
	 * @throws QueryException
	 */
	public boolean hasPreviousOrder() throws QueryException {
		AmsAssetsCheckHeaderModel modelProducer = (AmsAssetsCheckHeaderModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getHasPreviousOrderModel();
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.executeQuery();
		return simp.hasResult();
	}
	/**
	 * ���ܣ������ֻ����Ÿ�����ִ����
	 * @throws DataHandleException
	 */
	private void saveMessage() throws DataHandleException {
		AmsAssetsCheckHeaderDTO headerDTO = (AmsAssetsCheckHeaderDTO) dtoParameter;
		int userId = headerDTO.getImplementBy();
		String orderNum = headerDTO.getTransNo();
		String userName = headerDTO.getImplementUser();
		String userTel = "";
		try {
			MessageSaver msgSaver = new MessageSaver(conn);
			SQLModel sqlModel = new SQLModel();
			List strArg = new ArrayList();
			String strSql =
					"SELECT DISTINCT SU.MOVETEL FROM SF_USER SU WHERE SU.USER_ID = ?";
			strArg.add(userId);
			sqlModel.setSqlStr(strSql);
			sqlModel.setArgs(strArg);
			SimpleQuery sq = new SimpleQuery(sqlModel, conn);
			sq.executeQuery();
			userTel = String.valueOf(sq.getFirstRow().getValue("MOVETEL"));
			SfMsgDefineDTO msgDefineDTO = new SfMsgDefineDTO();
			msgDefineDTO.setMsgCategoryId(SMSConstant.ASSET_DIS_ID);
			msgDefineDTO.setCreatedBy(userAccount.getUserId());
			msgDefineDTO.setCellPhone(userTel);
			msgDefineDTO.setApplyNumber(orderNum);
			msgDefineDTO.setUserId(String.valueOf(userId));
			msgDefineDTO.setMsgContent(userName + "��������" + orderNum +
									   "��Ҫ��������ʹ��PDA���ء�");
			msgSaver.saveMsg(msgDefineDTO);
		} catch (QueryException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (ContainerException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (Exception ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ��·��̵㹤��
	 * @throws DataHandleException
	 */
	public void distributeChkOrder() throws DataHandleException {
		AmsAssetsCheckHeaderModel modelProducer = (AmsAssetsCheckHeaderModel)
												  sqlProducer;
		SQLModel sqlModel = modelProducer.getDistributeModel();
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * ���ܣ��ύ�̵㹤����������
	 * @return boolean
	 */
	public boolean submitOrder() {
		boolean operateResult = false;
		return operateResult;
	}

	/**
	 * ���ܣ���ȡ�̵㹤���µı�ǩ��
	 * @param includeAdded boolean �Ƿ����PDAɨ��󣬹����ϴ��¼�����豸��
	 * @return List
	 * @throws QueryException
	 */
	public DTOSet getOrderBarcodes(boolean includeAdded) throws QueryException {
		AmsAssetsCheckHeaderModel modelProducer = (AmsAssetsCheckHeaderModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getOrderBarcodesModel(includeAdded);
		SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		simp.setDTOClassName(AmsAssetsCheckLineDTO.class.getName());
		simp.executeQuery();
		return simp.getDTOSet();
	}

	/**
	 * ���ܣ����ݸ�������ȡ���ݡ�
	 * @param foreignKey String
	 * @return Object DTOSet����RowSet����ȡ������Ƿ�����DTOClassName��������ǿ��ת����
	 * @throws QueryException
	 */
	public Object getDataByForeignKey(String foreignKey) throws QueryException {
		Object retDatas = null;
		try {
			AmsAssetsCheckHeaderModel modelProducer = (AmsAssetsCheckHeaderModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getDataByForeignKeyModel(foreignKey);
			SimpleQuery splq = new SimpleQuery(sqlModel, conn);
			splq.setCalPattern(getCalPattern());
			if (!StrUtil.isEmpty(dtoClassName)) {
				splq.setDTOClassName(dtoClassName);
			}
			splq.executeQuery();
			if (!StrUtil.isEmpty(dtoClassName)) {
				retDatas = splq.getDTOSet();
				if(foreignKey.endsWith("batchId")){//�����ID��������ݻ�ȡ���н�һ���Ĳ���
					DTOSet dtos = (DTOSet)retDatas;
					if(dtos != null && !dtos.isEmpty()){
						AmsAssetsCheckHeaderDTO chkOrder = null;
						AssetsOptProducer optProducer = new AssetsOptProducer(userAccount, conn);
						String chkCategoryOpt = "";
						for(int i = 0; i < dtos.getSize(); i++){
							chkOrder = (AmsAssetsCheckHeaderDTO)dtos.getDTO(i);
							chkCategoryOpt = optProducer.getChkCategoryOption(chkOrder.getCheckCategory());
							chkOrder.setCheckCategoryOpt(chkCategoryOpt);
						}
					}
				}
			} else {
				retDatas = splq.getSearchResult();
				if(foreignKey.endsWith("batchId")){//�����ID��������ݻ�ȡ���н�һ���Ĳ���
					RowSet rows = (RowSet)retDatas;
					if(rows != null && !rows.isEmpty()){
						Row row = null;
						AssetsOptProducer optProducer = new AssetsOptProducer(userAccount, conn);
						String chkCategoryOpt = "";
						for(int i = 0; i < rows.getSize(); i++){
							row = rows.getRow(i);
							chkCategoryOpt = optProducer.getChkCategoryOption(row.getStrValue("CHECK_CATEGORY"));
							row.addField("CHECK_CATEGORY_OPT", chkCategoryOpt);
							rows.set(i, row);
						}
					}
				}
			}
		} catch (ContainerException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return retDatas;
	}


	/**
	 * ���ܣ�������ѯSQL�ʲ�����
	 * @return String ���ص���Excel�ļ�
	 * @throws com.sino.base.exception.WebFileDownException
	 */
	public File getExportFile() throws WebFileDownException {
		File file = null;
		AmsAssetsCheckHeaderModel modelProducer = null;
		try {
			modelProducer = (AmsAssetsCheckHeaderModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getPageQueryModel();
			String reportTitle = "�̵㹤����ѯ����";
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
		fieldMap.put("TRANS_NO", "�������");
		fieldMap.put("COMPANY_NAME", "�̵㹫˾");
		fieldMap.put("GROUPNAME", "�µ����");
		fieldMap.put("LOCATION_CODE", "�ص����");
		fieldMap.put("CHECK_LOCATION", "����λ��");
		fieldMap.put("CHECK_CATEGORY_NAME", "ɨ���豸רҵ");
		fieldMap.put("START_TIME", "��ʼ����");
		fieldMap.put("IMPLEMENT_USER", "ִ����");
		fieldMap.put("IMPLEMENT_DAYS", "��������");
		fieldMap.put("ARCHIVED_USER", "�鵵��");
		fieldMap.put("ARCHIVED_DATE", "�鵵����");
		fieldMap.put("ORDER_STATUS", "����״̬");
		return fieldMap;
	}
}
