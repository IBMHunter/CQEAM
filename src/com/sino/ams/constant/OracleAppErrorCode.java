package com.sino.ams.constant;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ���
 * @version 0.1
 *          Date: 2008-2-22
 */
public interface OracleAppErrorCode {

    //ע: ��д�洢����ʱ������Զ����Ӧ���쳣,�ö���Ӧ�����ݿ���AMS_CONSTANT_PKG�еĶ���һ��
    // �뽫�����ڴ�ע��,AMS_CONSTANT_PKG�еĶ�������ǰ�����"-",�ѱ����ORACLE���쳣�����ͻ,���ﲻ��Ҫ��"-"

    int spareNotEnough1 = 20001;      //���޿������޴˲����ŵ��豸
    int spareNotEnough2 = 20002;      //���޿������޴˲����ŵ��豸
    int spareNotEnough3 = 20003;      //�ֿ����Ѵ˲����ŵ��豸��������

    int APPROVE_USER_NOT_FOUND = 20011;  //����������δ�ҵ�

    int QUANTITY_NOT_ENOUGH = 20021;  //��������
}
