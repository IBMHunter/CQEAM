package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.model.YlAssetsQueryModel;
import com.sino.base.db.report.xls.SQLReporter;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAMS</p>
 * <p>Description: Ԥ���ʲ���ѯ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007 - 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ���
 * @version 0.1
 *          Date: 2008-7-2
 */
public class YlAssetsQueryDAO extends AMSBaseDAO {
    public YlAssetsQueryDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        this.sqlProducer = new YlAssetsQueryModel(userAccount, dtoParameter);
    }

    public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            SQLReporter reporter = new SQLReporter(sqlModel, conn);


            Map fieldMap = new HashMap();
            fieldMap.put("ORGANIZATION_NAME", "��˾����");
            fieldMap.put("ASSET_NUMBER", "�ʲ����");
            fieldMap.put("TAG_NUMBER", "�ʲ���ǩ");
            fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
            fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
            fieldMap.put("FA_CATEGORY_CODE", "�ʲ����");
            fieldMap.put("LIFE_IN_YEARS", "�۾�����");
            fieldMap.put("DATE_PLACED_IN_SERVICE", "�ʲ���������");
            fieldMap.put("ASSIGNED_TO_NAME", "������");

            reporter.setFieldMap(fieldMap);
            reporter.setFileName("Ԥ���ʲ�.xls");
            reporter.setReportUser(userAccount.getUsername());
            reporter.setNeedReportData(true);
            file = reporter.exportFile();
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataTransException(ex);
        }
        return file;
    }
}
