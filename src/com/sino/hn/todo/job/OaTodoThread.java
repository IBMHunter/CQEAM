package com.sino.hn.todo.job;


import com.sino.base.log.Logger;
import com.sino.hn.todo.service.EamToOaService;
import com.sino.hn.todo.util.HnOAConfig;

/**
 * 
 * 
 * @ϵͳ����:  
 * @��������: OA�����Լ��Ѱ�����ѭ������
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sangjun
 * @����ʱ��: Dec 7, 2011
 */
public class OaTodoThread implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		EamToOaService service = new EamToOaService();
		while( true ){
			Logger.logInfo( "start loop" );
			if( JobControl.todoStart ){
				JobControl.setTodoStart( false );
				service.sendOatodo();
			}
			if( JobControl.todoDeleteStart  ){
				JobControl.setTodoDeleteStart( false );
				service.sendOatodoDele();
			}
			Logger.logInfo( "end loop" );
			
			try {
				Thread.sleep( HnOAConfig.getOaThreadSleepTime() );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		OaTodoThread myJob = new OaTodoThread();
		Thread thread = new Thread( myJob );
		thread.start();
	}

}
