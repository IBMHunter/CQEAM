package com.sino.ams.dzyh.constant;

import com.sino.ams.constant.CustMessageKey;
import com.sino.base.constant.message.MsgKeyConstant;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public interface LvecMessageKeys extends CustMessageKey, MsgKeyConstant {
	/*=============================����ά������=================================*/
	String TASK_CREATE_SUCCESS = "TASK_CREATE_SUCCESS";
	String TASK_CREATE_FAILURE = "TASK_CREATE_FAILURE";
	String TASK_SUBMIT_SUCCESS = "TASK_SUBMIT_SUCCESS";
	String TASK_SUBMIT_FAILURE = "TASK_SUBMIT_FAILURE";
	String TASK_UPDATE_SUCCESS = "TASK_UPDATE_SUCCESS";
	String TASK_UPDATE_FAILURE = "TASK_UPDATE_FAILURE";
	String TASK_CANCEL_SUCCESS = "TASK_CANCEL_SUCCESS";
	String TASK_CANCEL_FAILURE = "TASK_CANCEL_FAILURE";
	String TASK_MULCEL_SUCCESS = "TASK_MULCEL_SUCCESS";
	String TASK_MULCEL_FAILURE = "TASK_MULCEL_FAILURE";
	String TASK_MULSUB_SUCCESS = "TASK_MULSUB_SUCCESS";
	String TASK_MULSUB_FAILURE = "TASK_MULSUB_FAILURE";
	String TASK_DUPDEFINE = "TASK_DUPDEFINE";


	/*=============================����ά������=================================*/
	String ORDER_SAVE_SUCCESS = "ORDER_SAVE_SUCCESS";
	String ORDER_SAVE_FAILURE = "ORDER_SAVE_FAILURE";
	String ORDER_DISTRI_SUCCESS = "ORDER_DISTRI_SUCCESS";
	String ORDER_DISTRI_FAILURE = "ORDER_DISTRI_FAILURE";

	String BATCH_CANCEL_SUCCESS = "BATCH_CANCEL_SUCCESS";
	String BATCH_CANCEL_FAILURE = "BATCH_CANCEL_FAILURE";
	String BATCH_MULCEL_SUCCESS = "BATCH_MULCEL_SUCCESS";
	String BATCH_MULCEL_FAILURE = "BATCH_MULCEL_FAILURE";

	String BARCIODE_NO_CHG_LOG = "BARCIODE_NO_CHG_LOG";
	String INSTR_CONFIRM_SUCCESS = "INSTR_CONFIRM_SUCCESS";
	String INSTR_CONFIRM_FAILURE = "INSTR_CONFIRM_FAILURE";
	String NO_EMPLOYEE_NUMBER = "NO_EMPLOYEE_NUMBER";


	/*=============================�����Ǳ����벿��=================================*/
	String BORROW_APPLY_SAVE_SUCCESS = "BORROW_APPLY_SAVE_SUCCESS";
	String BORROW_APPLY_SAVE_FAILURE = "BORROW_APPLY_SAVE_FAILURE";
	String BORROW_APPLY_SUBMIT_SUCCESS = "BORROW_APPLY_SUBMIT_SUCCESS";
	String BORROW_APPLY_SUBMIT_FAILURE = "BORROW_APPLY_SUBMIT_FAILURE";

	String BORROW_APPLY_CANCEL_SUCCESS = "BORROW_APPLY_CANCEL_SUCCESS";
	String BORROW_APPLY_CANCEL_FAILURE = "BORROW_APPLY_CANCEL_FAILURE";
	String BORROW_APPLY_APPROVE_SUCCESS = "BORROW_APPLY_APPROVE_SUCCESS";
	String BORROW_APPLY_APPROVE_FAILURE = "BORROW_APPLY_APPROVE_FAILURE";
	String ITEM_APPLY_NOT_AVAIABLE = "ITEM_APPLY_NOT_AVAIABLE";
}
