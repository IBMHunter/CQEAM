package com.sino.ams.bean;

import java.util.List;
import java.util.UUID;

import com.sino.base.calen.SimpleCalendar;
 
/**
 * 
 * @ϵͳ����: �ʲ�����
 * @��������: Sybase ���ݿ����Գ��ù�����
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: May 27, 2011
 */
public class SyBaseSQLUtil {
	
	public static int ORG_ID = 86;
	
	public static String NEW_ID_FUNCTION = " SELECT NEWID() ";
	
	//Ĭ�ϵ�Int ��ʼֵ
	public static int NULL_INT_VALUE = -1;

	//�õ���ǰ����
	public static String getCurDate(){
		return "GETDATE()";
	}
	//��ȡ���ݿ��û�
	public static String getDBOwner(){
		return "dbo.";
	}
	
	//��ѯ������ String ���� SQL
	public static String nullStringParam(){
		return "  ? IS NULL  OR ? = '' ";
	}
	//��ѯ������ INT ���� SQL
	public static String nullIntParam(){
		return " ? IS NULL  OR ? = " + NULL_INT_VALUE ;
	}
	
	//��ѯ������ SimpleCalendar ���� SQL
	public static String nullSimpleCalendarParam(){  
		return "  ? IS NULL  OR ? = '' ";
	}
	
	//�滻 ? IS NULL 
	public static String isNull(){
		return " ? = '' ";
	}
	
	//�滻 IS NULL 
	public static String isNullNoParam(){
		return "=NULL ";
	}
	
	//�滻 SF.XXX �ֶ��� IS NOT NULL ����
	public static String isNotNull( String column ){
//		return " ( " + column + " != '' ) ";
		return " ( " + column + " IS NOT NULL AND " + column + " != NULL ) ";
	}
	
	//��ѯ������ String ����  
	public static void nullStringParamArgs(List sqlArgs , String param){
		sqlArgs.add( param );	
		sqlArgs.add( param );
		sqlArgs.add( param );
	}
	//��ѯ������ SimpleCalendar ���� 
	public static void nullSimpleCalendarParamArgs(List sqlArgs , SimpleCalendar param){
		sqlArgs.add( param );	
		sqlArgs.add( param );
		sqlArgs.add( param );
	}
	//��ѯ������ INT ���� 
	public static void nullIntParamArgs(List sqlArgs , int param){
		sqlArgs.add( param );	
		sqlArgs.add( param ); 
		sqlArgs.add( param ); 
	}
}
