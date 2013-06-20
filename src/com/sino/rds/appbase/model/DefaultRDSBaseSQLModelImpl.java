package com.sino.rds.appbase.model;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.rds.appbase.RDSConstantConfigManager;

public class DefaultRDSBaseSQLModelImpl extends BaseSQLProducer implements RDSBaseSQLModel {//����Ĵ�����Ϊ����ȥ����SQLProducer�ദ�������û�����

    public DefaultRDSBaseSQLModelImpl(BaseUserDTO userAccount, DTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    public SQLModel getDataCreateModel() {
        String errorMsg = "��δʵ��"
                + getClass().getName()
                + "�ķ���getDataCreateModel";
        throw new UnsupportedOperationException(errorMsg);
    }

    public SQLModel getDataUpdateModel()  {
        String errorMsg = "��δʵ��"
                + getClass().getName()
                + "�ķ���getDataUpdateModel";
        throw new UnsupportedOperationException(errorMsg);
    }

    public SQLModel getDataDeleteModel()  {
        String errorMsg = "��δʵ��"
                + getClass().getName()
                + "�ķ���getDataDeleteModel";
        throw new UnsupportedOperationException(errorMsg);
    }

    public SQLModel getDeleteByPrimaryKeyModel() {
        String errorMsg = "��δʵ��"
                + getClass().getName()
                + "�ķ���getDeleteByPrimaryKeyModel";
        throw new UnsupportedOperationException(errorMsg);
    }

    public SQLModel getPrimaryKeyDataModel()  {
        String errorMsg = "��δʵ��"
                + getClass().getName()
                + "�ķ���getPrimaryKeyDataModel";
        throw new UnsupportedOperationException(errorMsg);
    }

    public SQLModel getDataByForeignKeyModel(String foreignKey)  {
        String errorMsg = "��δʵ��"
                + getClass().getName()
                + "�ķ���getDataByForeignKeyModel";
        throw new UnsupportedOperationException(errorMsg);
    }

    public SQLModel getDeleteByForeignKeyModel(String foreignKey)  {
        String errorMsg = "��δʵ��"
                + getClass().getName()
                + "�ķ���getDeleteByForeignKeyModel";
        throw new UnsupportedOperationException(errorMsg);
    }

    public SQLModel getMuxDataModel()  {
        String errorMsg = "��δʵ��"
                + getClass().getName()
                + "�ķ���getMuxDataModel";
        throw new UnsupportedOperationException(errorMsg);
    }


    public SQLModel getPageQueryModel()  {
        String errorMsg = "��δʵ��"
                + getClass().getName()
                + "�ķ���getPageQueryModel";
        throw new UnsupportedOperationException(errorMsg);
    }

    protected String getUserId(){
        return RDSConstantConfigManager.getUserId(userAccount);
    }
}
