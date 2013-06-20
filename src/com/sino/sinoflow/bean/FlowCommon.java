package com.sino.sinoflow.bean;

public interface FlowCommon {

	/**
	 * ���ܣ�����������Ϣ
	 *
	 * @param isSubmit boolean
	 * @return boolean
	 */
	public abstract boolean processProcedure(boolean isSubmit);

	/**
	 * ������������
	 *
	 * @return
	 */
	public abstract boolean processDel();

	/**
	 * ȡ������
	 *
	 * @return
	 */
	public abstract boolean processCancel();

	/**
	 * �˻�ʱ���õķ���
	 *
	 * @return boolean
	 */
	public abstract boolean reject();

	public abstract String getFlowCode() throws Exception;

}