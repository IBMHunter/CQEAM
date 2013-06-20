package com.sino.base;

import java.io.IOException;
import java.io.Writer;

import com.sino.base.exception.ReflectException;
import com.sino.base.log.Logger;
import com.sino.base.util.*;
/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class SinoBaseObject extends Object {

    public SinoBaseObject() {
        super();
    }

    /**
     *
     * @return String
     */
    public String toString() {
        return SystemUtil.toString(this);
    }


    public Object clone(){
        SinoBaseObject tarObj = null;
        String className = "";
        try {
            className = this.getClass().getName();
            tarObj = (SinoBaseObject) Class.forName(className).newInstance();
            ReflectionUtil.copyData(this, tarObj);
        } catch (ClassNotFoundException ex) {
            Logger.logError(ex);
        } catch (IllegalAccessException ex) {
            Logger.logError(ex);
        } catch (InstantiationException ex) {
            Logger.logError(ex);
        } catch (ReflectException ex) {
            ex.printLog();
        }
        return tarObj;
    }


	/**
	 * ���ܣ���DTO�����ӡ������̨
	 */
	public void println(){
		System.out.println(this);
	}


	/**
	 * ���ܣ���DTO�����ӡ��ָ���������
	 * @param writer Writer
	 */
	public void print(Writer writer){
		try {
			writer.write(toString());
		} catch (IOException ex) {
			Logger.logError(ex);
		}
	}

	/**
	 * ���ܣ���ĳ�����Դ�ӡ������̨��
	 * @param fieldName String
	 */
	public void println(String fieldName){
		if(StrUtil.isEmpty(fieldName)){
			System.out.println("you printed fieldName is empty, and there's no value binding it");
		} else {
			try{
				boolean hasProp = ReflectionUtil.hasProperty(this.getClass(), fieldName);
				if (hasProp) {
					Object value = ReflectionUtil.getProperty(this, fieldName);
					System.out.println(this.getClass().getName() + ": " + fieldName + " = " + value);
				} else {
					System.out.println(this.getClass().getName() + " does not contain a field named " + fieldName); ;
				}
			}catch(ReflectException ex){
				ex.printLog();
			}
		}
	}

	/**
	 * ���ܣ����������¼����־�ļ�
	 */
	public void log(){
		Logger.logInfo(this);
	}
}
