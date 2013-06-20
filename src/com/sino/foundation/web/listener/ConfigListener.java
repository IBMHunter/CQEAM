package com.sino.foundation.web.listener;


import com.sino.base.log.Logger;
import com.sino.foundation.config.ConfigLoader;

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

public class ConfigListener extends Thread {
    private int listenFrequency = 60;

    public void setListenFrequency(int listenFrequency) {
        if(listenFrequency > 0){
            this.listenFrequency = listenFrequency;
        }
    }

    public void run() {
        long listenFqy = listenFrequency * 1000;
        while (true) {
            try {
                sleep(listenFqy);
                ConfigLoader.refreshConfig();
            } catch (Throwable ex) {
                Logger.logError(ex);
            }
        }
    }
}
