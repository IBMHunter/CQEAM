package com.sino.ams.system.object.servlet;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.system.object.dao.ImportItemDAO;
import com.sino.ams.system.object.dto.EtsItemDTO;
import com.sino.ams.system.object.model.ImportItemModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.FileSizeException;
import com.sino.base.exception.PoolPassivateException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.UploadException;
import com.sino.base.exception.WebFileDownException;
import com.sino.base.log.Logger;
import com.sino.base.message.Message;
import com.sino.base.util.StrUtil;
import com.sino.base.web.ServletForwarder;
import com.sino.base.web.request.download.WebFileDownload;
import com.sino.base.web.request.upload.RequestParser;
import com.sino.base.web.request.upload.UploadFile;
import com.sino.base.web.request.upload.UploadFileSaver;
import com.sino.base.web.request.upload.UploadRow;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;
import com.sino.pda.PDAUtil;

/**
 * Created by IntelliJ IDEA. User: su Date: 2009-4-26 Time: 10:27:05 Function:Excelʵ���ʲ�������������.
 */
public class ImportItemServlet extends BaseServlet {
	private static int startRowNum = 1;

	private static int columnNum = 35;

	public void performTask(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String forwardURL = "";
		Message message = null;
		Connection conn = null;
		String showMsg = "";
		try {
			conn = DBManager.getDBConnection();
			SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(req);
			ImportItemDAO ImObDAO = new ImportItemDAO(userAccount, null, conn);
			String action = req.getParameter("act");
			
			if (action == null) {
				Logger.logInfo("Excel submit servlet begin....");
				RequestParser reqPar = new RequestParser();
				reqPar.transData(req);
				UploadFile[] upFiles = null;
				UploadRow uploadRow;
				String conFilePath = PDAUtil.getCurUploadFilePath(conn);
				UploadFileSaver uploadFileSaver = reqPar.getFileSaver();
				uploadFileSaver.saveFiles(conFilePath);
				uploadRow = uploadFileSaver.getRow();
				upFiles = uploadRow.getFiles();
				if (upFiles == null) {
					return;
				} else if (upFiles.length != 1
						|| upFiles[0].getFileName().equals("")) {
					return;
				}
				UploadFile uploadFile = upFiles[0];
				String fileName = uploadFile.getAbsolutePath();
				// =========================================================
				// boolean autoCommit = conn.getAutoCommit();
				ReadItemInfo xlsUtil = new ReadItemInfo();
				xlsUtil.setFileName(fileName);
				xlsUtil.setNumberOfColumn(columnNum);
				xlsUtil.setStartRowNum(startRowNum);
				DTOSet dtoSet = xlsUtil.readXls(0);

				// // �������
				// ImObDAO.deleteImportModel();
				// // ���뵽�ӿڱ�ams_item_import
				// itemImportData(dtoSet, conn, userAccount);
				// // ���ӿڱ����ݵ���Ч�ԡ�
				// ImObDAO.doVerifyData(dtoSet);
				boolean isSuccess = this.prodImport(dtoSet, ImObDAO,
						userAccount, conn);
				if (!isSuccess) {
					ImportItemModel onNetModel = new ImportItemModel(
							userAccount, null);
					SQLModel sqlModel = onNetModel.getImportErrorModel();
					SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
					simpleQuery.executeQuery();
					RowSet rows = simpleQuery.getSearchResult();
					req.setAttribute(WebAttrConstant.ETS_SPARE_DTO, rows);
					forwardURL = "/system/object/importItemError.jsp";
				} else {
					DTOSet dtoSetTemp = ImObDAO.getImport();
					submitOrderDtl(dtoSetTemp, conn, userAccount);
					forwardURL = "/system/object/importItem.jsp";
					showMsg = "ʵ��������µ���ɹ���";
				}
				// conn.commit();
				// conn.setAutoCommit(autoCommit);
			} else {
				File file = ImObDAO.getExportFile();
				WebFileDownload fileDown = new WebFileDownload(req, res);
				fileDown.setFilePath(file.getAbsolutePath());
				fileDown.download();
				file.delete();
			}
		} catch (PoolPassivateException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.POOL_PASSIVATE_ERROR);
			message.setMessageValue( ex.getMessage() );
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DTOException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.DTO_ERROR);
			message.setMessageValue( ex.getMessage() );
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (SQLException ex) {
			Logger.logError(ex);
			message = getMessage(MsgKeyConstant.SQL_ERROR);
			message.setMessageValue( ex.getMessage() );
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DataHandleException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setMessageValue( ex.getMessage() );
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (QueryException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.QUERY_ERROR);
			message.setMessageValue( ex.getMessage() );
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (ContainerException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setMessageValue( ex.getMessage() );
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (UploadException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
			message.setMessageValue( ex.getMessage() );
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (FileSizeException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
			message.setMessageValue( ex.getMessage() );
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (SQLModelException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.QUERY_ERROR);
			message.setMessageValue( ex.getMessage() );
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (WebFileDownException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.COMMON_ERROR);
			message.setMessageValue( ex.getMessage() );
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (Throwable ex) {
			Logger.logError(ex);
			message = getMessage(MsgKeyConstant.COMMON_ERROR);
			message.setMessageValue( ex.getMessage() );
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} finally {
			DBManager.closeDBConnection(conn);
			setHandleMessage(req, message);
			ServletForwarder forwarder = new ServletForwarder(req, res);
			if (showMsg.equals("")) {
				forwarder.forwardView(forwardURL);
			} else {
				forwarder.forwardView(forwardURL, showMsg);
			}
		}
	}

	public boolean prodImport(DTOSet dtoSet, ImportItemDAO ImObDAO,
			SfUserDTO userAccount, Connection conn) throws SQLModelException,
			QueryException, DataHandleException, ContainerException,
			SQLException {
		boolean autoCommit = conn.getAutoCommit();
		boolean isSuccess = false;
		ImObDAO.deleteImportModel();
		conn.commit();
		// ���뵽�ӿڱ�ams_item_import
		conn.setAutoCommit(false);
		isSuccess = itemImportData(dtoSet, conn, userAccount);
		if (isSuccess) {
			conn.commit();
		} else {
			conn.rollback();
		}
		conn.setAutoCommit(autoCommit);

		// conn.setAutoCommit( false );
		// ���ӿڱ����ݵ���Ч�ԡ�
		// ImObDAO.doVerifyData(dtoSet);

		if (isSuccess) {
			ImObDAO.doVerifyData_update(dtoSet);
			conn.commit();
			if (ImObDAO.importHasError()) {
				isSuccess = false;
			} else {
				isSuccess = true;
			}
		}
		return isSuccess;
	}

	/**
	 * ���ܣ����뵽�ӿڱ�
	 * 
	 * @param dtoSet
	 *            DTOSet
	 * @param conn
	 *            Connection
	 * @param userAccount
	 *            SfUserDTO
	 * @return boolean
	 * @throws DataHandleException
	 * @throws SQLModelException
	 * @throws SQLException
	 */
	private boolean itemImportData(DTOSet dtoSet, Connection conn,
			SfUserDTO userAccount) throws DataHandleException,
			SQLModelException, SQLException {
		boolean operatorResult = false;
		if (dtoSet != null && dtoSet.getSize() > 0) {
			SQLModel sqlModel = new SQLModel();
			ImportItemModel modelProducer = new ImportItemModel(userAccount,
					null);
			for (int i = 0; i < dtoSet.getSize(); i++) {
				EtsItemDTO eoDTO = (EtsItemDTO) dtoSet.getDTO(i);
				modelProducer.setDTOParameter(eoDTO);
				sqlModel = modelProducer.insertImportModel();
				try {
					DBOperator.updateRecord(sqlModel, conn);
				} catch (DataHandleException ex) {
					String errmsg = ex.getMessage();
					operatorResult = false;
					if (errmsg.indexOf("unique index") > -1) {
						throw new DataHandleException("��" + (i+1) + "�����ݴ����ظ�");
					} else {
						throw new DataHandleException("��" + (i+1) + "�����ݴ��ڴ���:"
								+ ex.getMessage());
					}
				} catch (Exception ex) {
					String errmsg = ex.getMessage();
					operatorResult = false;
					if (errmsg.indexOf("unique index") > -1) {
						throw new DataHandleException("��" + i + "�����ݴ����ظ�");
					} else {
						throw new DataHandleException("��" + i + "�����ݴ��ڴ���:"
								+ ex.getMessage());
					}
				}
			}
		}
		operatorResult = true;
		return operatorResult;

	}

	/**
	 * ���ܣ����뵽��ets_item_info�ύ����
	 * 
	 * @param dtoSet
	 *            DTOSet
	 * @param conn
	 *            Connection
	 * @param userAccount
	 *            SfUserDTO
	 * @return boolean
	 * @throws DataHandleException
	 * @throws CalendarException
	 */
	private boolean submitOrderDtl(DTOSet dtoSet, Connection conn,
			SfUserDTO userAccount) throws DataHandleException {
		boolean operatorResult = false;
		try {
			if (dtoSet != null && dtoSet.getSize() > 0) {
				SQLModel sqlModel = new SQLModel();
				ImportItemModel modelProducer = new ImportItemModel(
						userAccount, null);

				for (int i = 0; i < dtoSet.getSize(); i++) {
					EtsItemDTO eoDTO = (EtsItemDTO) dtoSet.getDTO(i);
					ImportItemDAO ImObDAO = new ImportItemDAO(userAccount,
							null, conn);

					if (!StrUtil.isEmpty(eoDTO.getNewItemName())
							&& !StrUtil.isEmpty(eoDTO.getNewItemSpec())) {// ��ȡITEM_CODE
						String itemCode = ImObDAO.getItemCode(eoDTO
								.getNewItemName(), eoDTO.getNewItemSpec());
						eoDTO.setItemCode(itemCode);
					}
					if (!StrUtil.isEmpty(eoDTO.getNewObjectCode())) {// ��ȡADDRESS_ID
						String addressId = ImObDAO.getAddressId(eoDTO
								.getNewObjectCode(), eoDTO.getBookTypeCode());
						eoDTO.setAddressId(addressId);
					}
					if (!StrUtil.isEmpty(eoDTO.getNewEmployeeNumber())) {// ��ȡEMPLOYEE_ID
						String employeeId = ImObDAO.getEmployeeId(eoDTO
								.getNewEmployeeNumber());
						eoDTO.setEmployeeId(employeeId);
					}
					if (ImObDAO.validateBarcode(eoDTO.getBarcode())) {
						modelProducer.setDTOParameter(eoDTO);
						sqlModel = modelProducer.getDataUpdateModel();
					} else {
						eoDTO.setFinanceProp("UNKNOW");
						eoDTO.setRemark("ʵ��������µ��������ʲ�");
						eoDTO.setItemQty(1);
						modelProducer.setDTOParameter(eoDTO);
						sqlModel = modelProducer.getDataInsertModel();
					}
					DBOperator.updateRecord(sqlModel, conn);
				}
			}
			operatorResult = true;
			// ����䶯��ʷ
			if (dtoSet != null && dtoSet.getSize() > 0) {
				for (int i = 0; i < dtoSet.getSize(); i++) {
					EtsItemDTO eoDTO = (EtsItemDTO) dtoSet.getDTO(i);
					ImportItemDAO ImObDAO = new ImportItemDAO(userAccount,
							null, conn);
					ImObDAO.logBarcodeHistory(eoDTO);
				}

			}
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (ContainerException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} catch (QueryException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		}
		return operatorResult;
	}
}
