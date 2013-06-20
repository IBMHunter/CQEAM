package com.sino.hn.todo.xfire.service;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.mochasoft.todo.beans.Close;
import com.mochasoft.todo.beans.Open;
import com.mochasoft.todo.service.ITodoService;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.hn.todo.dto.OaResponseDTO;
import com.sino.hn.todo.log.OaTodoLogService;
import com.sino.hn.todo.service.IOaTodoService;
import com.sino.hn.todo.xfire.client.TodoClient;
import com.sino.hn.todo.xfire.util.ConvertUtil;
import com.sino.sinoflow.todo.constant.HNOAConstant;
import com.sino.sinoflow.todo.dto.OaTodoDTO;
import com.sino.sinoflow.todo.util.DateUtil;

/**
 * 
 * @ϵͳ����:
 * @��������: �ӿ�����ҵ��
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Dec 1, 2011
 */
public class OaTodoService implements IOaTodoService {
//	TodoService todoService;
	ITodoService todoService;
	OaResponseDTO responseDTO = null;
	Connection conn = null;

	OaTodoLogService logService = null;

	public OaTodoService() {
		this.init();
	}

	public void init() {
		logService = new OaTodoLogService();
		responseDTO = new OaResponseDTO();
	}

	public void clear() {
		responseDTO = new OaResponseDTO();
	}

	/**
	 * ���ʹ���/�Ѱ�(һ��һ������) ��ʽһ :�����ͣ�ֻҪ���أ��Ͳ����¼����־����
	 * 
	 * @param open
	 * @return
	 */
	public boolean saveSender(OaTodoDTO todoDTO) {
		boolean isSuccess = false; // ���ͳɹ�
		boolean isAccess = false;
		try {
			Logger.logInfo( " start sender " + todoDTO.getTitle() );
			responseDTO.setBeginSendTime(DateUtil.getCurDateTimeStr());
			if (todoDTO.getTodoType().trim().equals(
					HNOAConstant.OA_TODO_TYPE_OPEN)) {
				Open open = ConvertUtil.getOpenFromDTO(todoDTO);
				isAccess = saveOpens(open); // �Խӳɹ��������Ƿ���ͨ
			} else if (todoDTO.getTodoType().trim().equals(
					HNOAConstant.OA_TODO_TYPE_CLOSE)) {
				Close close = ConvertUtil.getCloseFromDTO(todoDTO);
				isAccess = saveCloses(close); // �Խӳɹ��������Ƿ���ͨ
			}

			if (isAccess) {
//				OaResponseDTO resDTO = this.parseResponseXML(responseDTO
//						.getRetXML());
				isSuccess = TodoClient.isSuccess( responseDTO.getRetXML() );
//				isSuccess = resDTO.isSuccess();
				responseDTO.setResultCode( TodoClient.getResultCode() );
				responseDTO.setResultDesc( TodoClient.getMessage() );
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			responseDTO.setEamMsg(StrUtil.nullToString(responseDTO.getEamMsg())
					+ e.getMessage());
		} catch (Throwable e) {
			e.printStackTrace();
			responseDTO.setEamMsg(StrUtil.nullToString(responseDTO.getEamMsg())
					+ e.getMessage());
		} finally {
			responseDTO.setEndSendTime(DateUtil.getCurDateTimeStr());
			Logger.logInfo( " end sender " + todoDTO.getTitle() );
			responseDTO.setAccess(isAccess);
			responseDTO.setSuccess(isSuccess);

			logService.saveLog(todoDTO, responseDTO);

			return isSuccess;
		}
	}

	/**
	 * ���ʹ���/�Ѱ�(һ��һ������) ��ʽһ :�����ͣ�ֻҪ���أ��Ͳ����¼����־����
	 * 
	 * @param open
	 * @return
	 * @throws SQLException
	 */
	public boolean saveSender2(OaTodoDTO todoDTO) throws SQLException {
		boolean isSuccess = false; // ���ͳɹ�
		boolean isAccess = false;
		try {
			logService.saveLogInTrans(todoDTO, conn);
			if (todoDTO.getTodoType().equals(HNOAConstant.OA_TODO_TYPE_OPEN)) {
				Open open = ConvertUtil.getOpenFromDTO(todoDTO);
				isAccess = saveOpens(open); // �Խӳɹ��������Ƿ���ͨ
			} else if (todoDTO.getTodoType().equals(
					HNOAConstant.OA_TODO_TYPE_CLOSE)) {
				Close close = ConvertUtil.getCloseFromDTO(todoDTO);
				isAccess = saveCloses(close); // �Խӳɹ��������Ƿ���ͨ
			}
			if (isAccess) {
				OaResponseDTO resDTO = this.parseResponseXML(responseDTO
						.getRetXML());
				isSuccess = resDTO.isSuccess();
				responseDTO.setResultCode(resDTO.getResultCode());
				responseDTO.setResultDesc(resDTO.getResultDesc());
			}
		} catch (DocumentException e) {
			responseDTO.setEamMsg(StrUtil.nullToString(responseDTO.getEamMsg())
					+ e.getMessage());
		} catch (DataHandleException e) {
			responseDTO.setEamMsg(StrUtil.nullToString(responseDTO.getEamMsg())
					+ e.getMessage());
		} catch (IllegalAccessException e) {
			responseDTO.setEamMsg(StrUtil.nullToString(responseDTO.getEamMsg())
					+ e.getMessage());
		} catch (InvocationTargetException e) {
			responseDTO.setEamMsg(StrUtil.nullToString(responseDTO.getEamMsg())
					+ e.getMessage());
		} finally {
			responseDTO.setAccess(isAccess);
			responseDTO.setSuccess(isSuccess);
			if (isSuccess) {
				conn.commit();
			} else {
				conn.rollback();
			}
			return isSuccess;
		}
	}

	/**
	 * ���ʹ���(һ��һ������)
	 * 
	 * @param open
	 * @return
	 */
	public boolean saveOpen(OaTodoDTO todoDTO) {
		boolean isSuccess = false; // ���ͳɹ�
		boolean isAccess = false;
		try {
			Open open = ConvertUtil.getOpenFromDTO(todoDTO);
			isAccess = saveOpens(open); // �Խӳɹ��������Ƿ���ͨ
			if (isAccess) {
				OaResponseDTO resDTO = this.parseResponseXML(responseDTO
						.getRetXML());
				isSuccess = resDTO.isSuccess();
				responseDTO.setResultCode(resDTO.getResultCode());
				responseDTO.setResultDesc(resDTO.getResultDesc());
			}
		} catch (DocumentException e) {
			responseDTO.setEamMsg(StrUtil.nullToString(responseDTO.getEamMsg())
					+ e.getMessage());
		} finally {
			responseDTO.setAccess(isAccess);
			responseDTO.setSuccess(isSuccess);
			return isSuccess;
		}
	}

	/**
	 * ���ʹ���(һ��һ������)
	 * 
	 * @param open
	 * @return
	 */
	public boolean saveOpen(Open open) {
		boolean isSuccess = false; // ���ͳɹ�
		boolean isAccess = false;
		try {
			isAccess = saveOpens(open); // �Խӳɹ��������Ƿ���ͨ
			if (isAccess) {
				OaResponseDTO resDTO = this.parseResponseXML(responseDTO
						.getRetXML());
				isSuccess = resDTO.isSuccess();
				responseDTO.setResultCode(resDTO.getResultCode());
				responseDTO.setResultDesc(resDTO.getResultDesc());
			}
		} catch (DocumentException e) {
			responseDTO.setEamMsg(StrUtil.nullToString(responseDTO.getEamMsg())
					+ e.getMessage());
		} finally {
			responseDTO.setAccess(isAccess);
			responseDTO.setSuccess(isSuccess);
			return isSuccess;
		}
	}

	/**
	 * �����Ѱ�(һ��һ������)
	 * 
	 * @param close
	 * @return
	 */
	public boolean saveClose(Close close) {
		boolean isSuccess = false; // ���ͳɹ�
		boolean isAccess = false;
		try {
			isAccess = saveCloses(close); // �Խӳɹ��������Ƿ���ͨ
			if (isAccess) {
				OaResponseDTO resDTO = this.parseResponseXML(responseDTO
						.getRetXML());
				isSuccess = resDTO.isSuccess();
				responseDTO.setResultCode(resDTO.getResultCode());
				responseDTO.setResultDesc(resDTO.getResultDesc());
			}
		} catch (DocumentException e) {
			responseDTO.setEamMsg(StrUtil.nullToString(responseDTO.getEamMsg())
					+ e.getMessage());
		} finally {
			responseDTO.setAccess(isAccess);
			responseDTO.setSuccess(isSuccess);
			return isSuccess;
		}
	}

	/**
	 * OA�˱����Լ�������Ϣ����
	 * 
	 * @param open
	 */
	protected boolean saveOpens(Open open) {
		boolean isSuccess = false; // ��¼�Ƿ�ɹ�������Ϣ���Խ��Ƿ�ɹ�
		List<Open> opens = new ArrayList<Open>();
		opens.add(open);
		try {
			String retXML = saveOpens( opens );
			responseDTO.setRetXML(retXML);
			isSuccess = true;
		} catch (Throwable ex) {
			Logger.logError( ex );
			responseDTO.setEamMsg(ex.getMessage());
		} finally {
			if (StrUtil.isEmpty(responseDTO.getRetXML())
					&& StrUtil.isEmpty(responseDTO.getEamMsg())) {
				responseDTO.setEamMsg("û�з�����Ϣ�����ܳ�ʱ��!");
				isSuccess = false;
			}
			return isSuccess;
		}
	}

	/**
	 * OA�˱����Լ�������Ϣ����
	 * 
	 * @param open
	 */
	protected boolean saveCloses(Close close) {
		boolean isSuccess = false; // ��¼�Ƿ�ɹ�������Ϣ���Խ��Ƿ�ɹ�
		
		List<Close> closes = new ArrayList<Close>();
		closes.add(close);
		try {
			String retXML = saveCloses(closes);
			responseDTO.setRetXML(retXML);
			isSuccess = true;
		} catch (Throwable ex) {
			responseDTO.setEamMsg(ex.getMessage());
		} finally {
			if (StrUtil.isEmpty(responseDTO.getRetXML())
					&& StrUtil.isEmpty(responseDTO.getEamMsg())) {
				responseDTO.setEamMsg("û�з�����Ϣ�����ܳ�ʱ��!");
				isSuccess = false;
			}
			return isSuccess;
		}
	}

	/**
	 * ��������XML���
	 * 
	 * @param retXML
	 * @return
	 * @throws DocumentException
	 */
	private OaResponseDTO parseResponseXML(String retXML)
			throws DocumentException {
		OaResponseDTO resDTO = null;
		String resultCode = "";
		String resultDesc = "";
		boolean isSuccess = false;
		SAXReader saxReadr = new SAXReader();// �õ�SAXReader����
		Document doc = saxReadr.read(retXML);
		Element root = doc.getRootElement();
		Element resultCodeEle = root.element("resultCode");
		resultCode = resultCodeEle.getText();
		if (resultCode.equals(HNOAConstant.RESULT_CODE_FAILURE)) {
			Element resultDescEle = root.element("resultDesc");
			resultDesc = resultDescEle.getText();
			isSuccess = false;
		} else {
			isSuccess = true;
		}
		resDTO = new OaResponseDTO();
		resDTO.setResultCode(resultCode);
		resDTO.setResultDesc(resultDesc);
		resDTO.setSuccess(isSuccess);
		return resDTO;
	}

	/**
	 * OA�˱���
	 * 
	 * @param opens
	 * @return
	 * @throws Throwable
	 */
	protected String saveCloses(List<Close> closes) throws Throwable {
		prodService();
		return todoService.saveClose(closes);
	}

	/**
	 * OA�˱���
	 * 
	 * @param opens
	 * @return
	 * @throws Throwable
	 */
	protected String saveOpens( List<Open> opens ) throws Throwable {
		prodService();
		return todoService.saveOpen( opens );
	}
	
	protected void prodService() throws Throwable{
		todoService = TodoClient.getClient();
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public OaResponseDTO getResponseDTO() {
		return responseDTO;
	}

}
