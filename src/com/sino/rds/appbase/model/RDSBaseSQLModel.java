package com.sino.rds.appbase.model;

import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.sql.model.SQLModel;

public interface RDSBaseSQLModel extends CalendarConstant {

    /**
     * ���ܣ��������ݲ���SQL
     * @return �������ݲ���SQLģ�Ͷ���
     */
    SQLModel getDataCreateModel();

    /**
     * ���ܣ��������ݸ���SQL
     * @return �������ݸ���SQLģ�Ͷ���
     */
    SQLModel getDataUpdateModel();

    /**
     * ���ܣ���������ɾ��SQL
     * @return ��������ɾ��SQLģ�Ͷ���
     */
    SQLModel getDataDeleteModel();

    /**
     * ���ܣ������������ɾ������SQL
     * @return ���ظ�������ɾ������SQLģ�Ͷ���
     */
    SQLModel getDeleteByPrimaryKeyModel();

    /**
     * ���ܣ��������������ѯ����SQL
     * @return ���ظ���������ѯ����SQLģ�Ͷ���
     */
    SQLModel getPrimaryKeyDataModel();

    /**
     * ���ܣ�������������ѯ���ݵ�SQL
     * @param foreignKey ������ơ��˴���DTO������
     * @return ���ظ��������ѯ���ݵ�SQL
     */
    SQLModel getDataByForeignKeyModel(String foreignKey);

    /**
     * ���ܣ�����������ɾ�����ݵ�SQL
     * @param foreignKey ������ơ��˴���DTO������
     * @return ���ظ������ɾ�����ݵ�SQL
     */
    SQLModel getDeleteByForeignKeyModel(String foreignKey);

    /**
     * ���ܣ�����������������ѯ����SQL
     * @return ���ظ������������ѯ����SQLģ�Ͷ���
     */
    SQLModel getMuxDataModel();

    /**
     * ���ܣ��������ҳ������������ѯ���ݵ�SQL����Ҫ����ҳ��ķ�ҳ��ѯ����ʱҲ��Ϊ���ݵ���SQLʹ��
     * @return ���ع������ҳ������������ѯ���ݵ�SQLģ�Ͷ���
     */
    SQLModel getPageQueryModel();
}
