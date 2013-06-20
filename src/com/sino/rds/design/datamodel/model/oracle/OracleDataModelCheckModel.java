package com.sino.rds.design.datamodel.model.oracle;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.rds.appbase.model.DefaultRDSBaseSQLModelImpl;
import com.sino.rds.design.datamodel.model.DataModelCheckModel;
import com.sino.rds.share.form.DataModelFrm;

public class OracleDataModelCheckModel extends DefaultRDSBaseSQLModelImpl implements DataModelCheckModel {

    public OracleDataModelCheckModel(BaseUserDTO userAccount, DTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ���ȡSQL��ѯ�ﴴ����ͼ��SQLModel����
     *
     * @return ��ȡSQL��ѯ�ﴴ����ͼ��SQLModel����
     */
    public SQLModel getModelCheckModel() {
        SQLModel sqlModel = new SQLModel();
        DataModelFrm frm = (DataModelFrm) dtoParameter;
        String sqlStr = "SELECT 1 FROM (" + frm.getModelSql() + ")  WHERE ROWNUM = 1";
        sqlModel.setSqlStr(sqlStr);
        return sqlModel;
    }
}
