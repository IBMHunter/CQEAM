package com.sino.foundation.config;

import com.sino.base.log.Logger;


/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>@todo����ʱλ�ڸð��£�����ʵ����Ŀ����֤֮�󣬽����������⣬��ȡ��Ŀǰ���������ù���</p>
 * <p>Copyright: ����˼ŵ����Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class PostProcessorContainer<T extends ConfigLoadedResult> extends Thread {

    private ConfigPostProcessor<T> postProcessor = null;
    private T configResult = null;
    private long delayTime = 0L;//����������к��ô���������ѡ����Ч����ʾ���ô������ӳ������ĺ�����

    public PostProcessorContainer(ConfigPostProcessor<T> postProcessor, T configResult, long delayTime){
        this.postProcessor = postProcessor;
        this.configResult = configResult;
        this.delayTime = delayTime;
    }

    public void run() {
        try {
            sleep(delayTime * 1000);
            postProcessor.postProcessConfig(configResult);
        } catch (Throwable ex) {
            Logger.logError(ex);
        }
    }
}

