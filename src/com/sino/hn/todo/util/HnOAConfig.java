package com.sino.hn.todo.util;

import java.util.ResourceBundle;

import com.sino.base.util.StrUtil;
import com.sino.config.SinoConfig;
import com.sino.sinoflow.todo.constant.HNOAConstant;

/**
 * 
 * @ϵͳ����:
 * @��������: ����OA��������
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Dec 1, 2011
 */
public class HnOAConfig extends SinoConfig {
	public static String getTodo_url() {
		return ResourceBundle.getBundle("config.HnOa").getString("todo_url");
	}
	
//	//ʡ��� HN��
//	public static String getProvinceSimpleName() {
//		return ResourceBundle.getBundle("config.HnOa").getString("PROVINCE_SIMPLE_NAME");
//	}

	public static String getTodo_username() {
		return ResourceBundle.getBundle("config.HnOa").getString(
				"todo_username");
	}

	public static String getTodo_password() {
		return ResourceBundle.getBundle("config.HnOa").getString(
				"todo_password");
	}

	public static String getEam_url() {
		return ResourceBundle.getBundle("config.HnOa").getString("eam_url");
	}

	public static long getOaThreadSleepTime() {
		String time = ResourceBundle.getBundle("config.HnOa").getString(
				"OA_TODO_THREAD_SLEEP_TIME");
		if (StrUtil.isEmpty(time)) {
			return HNOAConstant.OA_TODO_THREAD_SLEEP_TIME;
		} else {
			return Long.parseLong(time);
		}
	}
	
	public static boolean startOatodo(){
		String startOatodo = ResourceBundle.getBundle("config.HnOa").getString("START_OATODO");
		return Boolean.parseBoolean( startOatodo );
	}

}
