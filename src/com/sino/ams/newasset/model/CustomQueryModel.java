package com.sino.ams.newasset.model;

import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;

/**
 * <p>Title: AssetsCommQueryModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsCommQueryModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */
public class CustomQueryModel extends AMSSQLProducer {

    /**
     * ���ܣ��ʲ��ۺϲ�ѯ���� AMS_ASSETS_COMM_QUERY ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsCommQueryDTO ���β���������
     */
    public CustomQueryModel(SfUserDTO userAccount,
                            AmsAssetsAddressVDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����ҳ�淭ҳ��ѯʱ����Ҫ��SQLModel
     * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ��</B>
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        return null;
    }
}
