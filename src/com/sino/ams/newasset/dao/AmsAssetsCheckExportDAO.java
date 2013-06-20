package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsCJYCDTO;
import com.sino.ams.newasset.model.AmsAssetsCheckExportModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: yuyao
 * Date: 2008-11-14
 * Time: 11:14:46
 * To change this template use File | Settings | File Templates.
 */
public class AmsAssetsCheckExportDAO extends AMSBaseDAO {
      SfUserDTO sfUser=null;
    public AmsAssetsCheckExportDAO(SfUserDTO userAccount, AmsAssetsCJYCDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        this.sfUser=userAccount;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsCJYCDTO dtoPara = (AmsAssetsCJYCDTO) dtoParameter;
        super.sqlProducer = new AmsAssetsCheckExportModel((SfUserDTO) userAccount, dtoPara);
   }
       public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);
           String fileName = "�ʲ��̵�ͳ��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

             Map fieldMap = new HashMap();
            fieldMap.put("ASSET_ID", "�ʲ����");
            fieldMap.put("TAG_NUMBER", "�ʲ���ǩ");
            fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
            fieldMap.put("MODEL_NUMBER", "����ͺ�");
            fieldMap.put("UNIT_OF_MEASURE", "������λ");
            fieldMap.put("DATE_PLACED_IN_SERVICE", "��������");
            fieldMap.put("LIFE_IN_YEARS", "�۾�����");
            fieldMap.put("COST", "�ʲ�ԭֵ");
            fieldMap.put("DEPRN_RESERVE", "�ۼ��۾�");
            fieldMap.put("DEPRN_COST", "�ʲ���ֵ");
            fieldMap.put("CURRENT_UNITS", "��������");
            fieldMap.put("NOW_COUNT", "ʵ������");
            fieldMap.put("DEPT_NAME", "���β���");
            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(fileName);
            custData.setReportPerson(sfUser.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            /*rule.setSheetSize(1000);*/
            //���÷�ҳ��ʾ
            TransferFactory factory = new TransferFactory();
            DataTransfer transfer = factory.getTransfer(rule);
            transfer.transData();
            file = (File) transfer.getTransResult();
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataTransException(ex);
        }
        return file;
        }
}
