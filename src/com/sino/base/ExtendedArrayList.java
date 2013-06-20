package com.sino.base;

import java.util.ArrayList;

import com.sino.base.log.Logger;

/**
 * <p>Title: </p>
 * <p>Description: ��Ҫ�������ڻ�ȡԪ��ʱ,���Ԫ���ǿ�(index������ǰlist��size),��������һ��Ԫ��,������Ԫ�س�ʼ��Ϊһ�����ʵ��</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007 - 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ���
 * @version 0.1
 *          Date: 2009-2-10
 */
public class ExtendedArrayList extends ArrayList {
    private Class itemClass;

    public ExtendedArrayList(Class itemClass) {
        this.itemClass = itemClass;
    }

    public Object get(int index) {
        while (index >= size()) {
            try {
                add(itemClass.newInstance());
            } catch (InstantiationException e) {
                Logger.logError(e);
            } catch (IllegalAccessException e) {
                Logger.logError(e);
            }
        }
        return super.get(index);
    }
}
