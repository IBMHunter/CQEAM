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
import com.sino.ams.system.object.dao.ImportDzyhAssetsDAO;
import com.sino.ams.system.object.dto.ImportDzyhAssetsDTO;
import com.sino.ams.system.object.model.ImportDzyhAssetsModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.message.MessageConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTOSet;
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
 * Created by IntelliJ IDEA.
 * User: su
 * Date: 2009-5-27
 * Time: 10:08:26
 * To change this template use File | Settings | File Templates.
 */
public class ImportDzyhAssetsServlet extends BaseServlet {
	private static int startRowNum = 4;
	private static int columnNum = 14;

	public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String forwardURL = "";
		Message message = null;
		Connection conn = null;
		String showMsg = "";
		try {
			conn = DBManager.getDBConnection();
			SfUserDTO userAccount = (SfUserDTO) SessionUtil.getUserAccount(req);
            ImportDzyhAssetsDAO ImObDAO = new ImportDzyhAssetsDAO(userAccount, null, conn);
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
			} else if (upFiles.length != 1 || upFiles[0].getFileName().equals("")) {
				return;
			}
			UploadFile uploadFile = upFiles[0];
			String fileName = uploadFile.getAbsolutePath();
//=========================================================
			boolean autoCommit = conn.getAutoCommit();
			ReadDzyhAssetsInfo xlsUtil = new ReadDzyhAssetsInfo();
			xlsUtil.setFileName(fileName);
			xlsUtil.setNumberOfColumn(columnNum);
			xlsUtil.setStartRowNum(startRowNum);
			DTOSet dtoSet = xlsUtil.readXls(0);

            //清除数据
            ImObDAO.deleteImportModel();
            //导入到接口表AMS_DZYH_ASSETS_IMPORT
            itemImportData(dtoSet, conn, userAccount);
            //检查接口表数据的有效性。
            ImObDAO.doVerifyData(dtoSet);

            ImportDzyhAssetsModel onNetModel = new ImportDzyhAssetsModel(userAccount, null);
			if (ImObDAO.importHasError()) {
				SQLModel sqlModel = onNetModel.getImportErrorModel();
				SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
				simpleQuery.executeQuery();
				RowSet rows = simpleQuery.getSearchResult();
				req.setAttribute(WebAttrConstant.ETS_SPARE_DTO, rows);
                forwardURL = "/system/object/importDzyhError.jsp";
            } else {
                DTOSet dtoSetTemp = ImObDAO.getImport();
				submitOrderDtl(dtoSetTemp, conn, userAccount);
                forwardURL = "/system/object/importDzyhAssets.jsp";
				showMsg = "低值易耗资产导入成功！";
			}
            conn.commit();
			conn.setAutoCommit(autoCommit);
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
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (DTOException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.DTO_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (SQLException ex) {
			Logger.logError(ex);
			message = getMessage(MsgKeyConstant.SQL_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
        } catch (DataHandleException ex) {
			ex.printLog();
            message = getMessage(MsgKeyConstant.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (QueryException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.QUERY_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (ContainerException ex) {
			ex.printLog();
            message = getMessage(MsgKeyConstant.COMMON_ERROR);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (UploadException ex) {
			ex.printLog();
            message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
            message.setIsError(true);
            forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (FileSizeException ex) {
			ex.printLog();
			message = getMessage(MsgKeyConstant.DATA_NOT_EXIST);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (SQLModelException ex) {
			ex.printLog();
            message = getMessage(MsgKeyConstant.QUERY_ERROR);
			message.setIsError(true);
			forwardURL = MessageConstant.MSG_PRC_SERVLET;
		} catch (WebFileDownException ex) {
			ex.printLog();
			message = getMessage(AssetsMessageKeys.COMMON_ERROR);
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

    /**
	 * 功能：插入到接口表
	 * @param dtoSet DTOSet
	 * @param conn Connection
	 * @param userAccount SfUserDTO
	 * @return boolean
	 * @throws DataHandleException
	 * @throws SQLModelException
	 */
	private boolean itemImportData(DTOSet dtoSet, Connection conn, SfUserDTO userAccount) throws  DataHandleException, SQLModelException {
		boolean operatorResult = false;
        if (dtoSet != null && dtoSet.getSize() > 0) {
			SQLModel sqlModel = new SQLModel();
			ImportDzyhAssetsModel modelProducer = new ImportDzyhAssetsModel(userAccount, null);
			for (int i = 0; i < dtoSet.getSize(); i++) {
				ImportDzyhAssetsDTO eoDTO = (ImportDzyhAssetsDTO) dtoSet.getDTO(i);
                modelProducer.setDTOParameter(eoDTO);
				sqlModel = modelProducer.insertImportModel();
				DBOperator.updateRecord(sqlModel, conn);
			}
		}
        operatorResult = true;
		return operatorResult;
	}


    /**
	 * 功能：导入到表ets_item_info提交工单
	 * @param dtoSet DTOSet
	 * @param conn Connection
	 * @param userAccount SfUserDTO
	 * @return boolean
	 * @throws DataHandleException
	 */
	private boolean submitOrderDtl(DTOSet dtoSet, Connection conn, SfUserDTO userAccount) throws DataHandleException {
		boolean operatorResult = false;
		try {
			if (dtoSet != null && dtoSet.getSize() > 0) {
				SQLModel sqlModel = new SQLModel();
				ImportDzyhAssetsModel modelProducer = new ImportDzyhAssetsModel(userAccount, null);
                for (int i = 0; i < dtoSet.getSize(); i++) {
					ImportDzyhAssetsDTO eoDTO = (ImportDzyhAssetsDTO) dtoSet.getDTO(i);
                    //查找并获取ITEM_CODE，没有则新增
                    ImportDzyhAssetsDAO ImObDAO = new ImportDzyhAssetsDAO(userAccount, null, conn);
                    if (!StrUtil.isEmpty(eoDTO.getItemName()) && !StrUtil.isEmpty(eoDTO.getItemSpec())) {
                        String itemCode = ImObDAO.getItemCode(eoDTO.getItemName(), eoDTO.getItemSpec());
                        if (itemCode == null || itemCode.equals("")) {
                            itemCode = StrUtil.nullToString(getNextItemCode(conn));
                            ImObDAO.insertSystemItem(itemCode, eoDTO.getItemName(), eoDTO.getItemSpec(), eoDTO.getItemUnit());
                            ImObDAO.insertDistribute(itemCode, conn);
                        }
                        eoDTO.setItemCode(itemCode);
                    }
                    //获取公司专业责任人EMPLOYEE_ID
                    if (!StrUtil.isEmpty(eoDTO.getSpecialityUser())) {
                        String employeeId = ImObDAO.getEmployeeId(eoDTO.getSpecialityUser());
                        eoDTO.setSpecialEmployeeId(employeeId);
                    }
                    //获取责任部门管理员EMPLOYEE_ID
                    if (!StrUtil.isEmpty(eoDTO.getResponsibilityUser())) {
                        String employeeId = ImObDAO.getEmployeeId(eoDTO.getResponsibilityUser());
                        eoDTO.setResponsEmployeeId(employeeId);
                    }
                    //获取责任部门编号
                    if (!StrUtil.isEmpty(eoDTO.getResponsibilityUser())) {
                        String deptCode = ImObDAO.getDeptCode(eoDTO.getResponsibilityUser());
                        eoDTO.setResponsibilityDept(deptCode);
                    }
                    if (eoDTO.getTd().equals("")) {
                        eoDTO.setTd("N");                       
                    }
                    eoDTO.setSystemid(StrUtil.nullToString(getNextSystemId(conn)));
                    modelProducer.setDTOParameter(eoDTO);

                    sqlModel = modelProducer.getDataCreateModel();
					DBOperator.updateRecord(sqlModel, conn);
				}
			}
			operatorResult = true;
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (ContainerException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} catch (QueryException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		} catch (SQLException ex) {
			Logger.logError(ex);
			throw new DataHandleException(ex);
		}
		return operatorResult;
	}

    private int getNextSystemId(Connection conn) throws SQLException {
		SeqProducer seqProducer = new SeqProducer(conn);
		return seqProducer.getStrNextSeq("ETS_ITEM_INFO_S");
	}

    private int getNextItemCode(Connection conn) throws SQLException {
		SeqProducer seqProducer = new SeqProducer(conn);
		return seqProducer.getStrNextSeq("ETS_SYSTEM_ITEM_S");
	}
}
