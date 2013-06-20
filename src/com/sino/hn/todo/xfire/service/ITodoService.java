package com.sino.hn.todo.xfire.service;

import java.util.List;

public interface ITodoService {
/**
 * ���ʹ�����Ϣ
 * 
 * ������list����ѹ��Openʵ����
 * ���أ��ɹ�xml=<?xml version="1.0" encoding="UTF-8"?>
		 * 				<result>
		 *					<resultCode>1</resultCode>
		 *				</result>
	    *		ʧ��xml=<?xml version="1.0" encoding="UTF-8"?>
		 * 				<result>
		 *					<resultCode>-1</resultCode>
		 *					<resultDesc>������Ϣ��ϸ</resultDesc>
		 *				</result>
 */

	public String saveOpen(List list);
	/**
	 * �����Ѱ���Ϣ
	 * 
	 * ������list����ѹ��Closeʵ����
	 * ���أ��ɹ�xml=<?xml version="1.0" encoding="UTF-8"?>
		 * 				<result>
		 *					<resultCode>1</resultCode>
		 *				</result>
	    *		ʧ��xml=<?xml version="1.0" encoding="UTF-8"?>
		 * 				<result>
		 *					<resultCode>-1</resultCode>
		 *					<resultDesc>������Ϣ��ϸ</resultDesc>
		 *				</result>
	 */
	public String saveClose(List list);
	/**
	 * ����ע����Ϣ
	 * 
	 * ������list����ѹ��Cancelʵ����
	 * ���أ��ɹ�xml=<?xml version="1.0" encoding="UTF-8"?>
		 * 				<result>
		 *					<resultCode>1</resultCode>
		 *				</result>
	    *		ʧ��xml=<?xml version="1.0" encoding="UTF-8"?>
		 * 				<result>
		 *					<resultCode>-1</resultCode>
		 *					<resultDesc>������Ϣ��ϸ</resultDesc>
		 *				</result>
	 */
	public String saveCancel(List list);
	/**
	 * ��Ч����״̬�ӿ�
	 * @param state ���³ɶ��� 
				1 ����
				2 �Ѱ�
				3 ����
				4 ����
				5 �ݸ�
				6 ע��
	 * @param title �������
	 * @param list �û���������
	 * ���أ��ɹ�xml=<?xml version="1.0" encoding="UTF-8"?>
		 * 				<result>
		 *					<resultCode>1</resultCode>
		 *				</result>
	    *		ʧ��xml=<?xml version="1.0" encoding="UTF-8"?>
		 * 				<result>
		 *					<resultCode>-1</resultCode>
		 *					<resultDesc>������Ϣ��ϸ</resultDesc>
		 *				</result>
	 */
	public String updateTodo(String state,String title,List list);
	/**
	 * ��Чɾ���ӿ�
	 * @param title �������
	 * @param list �û���������
	 * ���أ��ɹ�xml=<?xml version="1.0" encoding="UTF-8"?>
		 * 				<result>
		 *					<resultCode>1</resultCode>
		 *				</result>
	    *		ʧ��xml=<?xml version="1.0" encoding="UTF-8"?>
		 * 				<result>
		 *					<resultCode>-1</resultCode>
		 *					<resultDesc>������Ϣ��ϸ</resultDesc>
		 *				</result>
	 */
	public String deleteTodo(String title,List list);
	
}
