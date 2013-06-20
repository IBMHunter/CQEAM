package com.sino.ams.newasset.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsCJYCDTO;
import com.sino.ams.newasset.model.AmsAssetsQJCJYCModel;
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
 * User: srf
 * Date: 2008-3-17
 * Time: 18:11:36
 * To change this template use File | Settings | File Templates.
 */
public class AmsAssetsQJCJYCDAO extends AMSBaseDAO {
    SfUserDTO sfUser=null;
    public AmsAssetsQJCJYCDAO(SfUserDTO userAccount, AmsAssetsCJYCDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        this.sfUser=userAccount;
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsCJYCDTO dtoPara = (AmsAssetsCJYCDTO) dtoParameter;
        super.sqlProducer = new AmsAssetsQJCJYCModel((SfUserDTO) userAccount, dtoPara);
   }
    public File exportFile() throws DataTransException {
        File file = null;
        try {
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);

            String fileName = "�ʲ����������۾�Ԥ�ⱨ��.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("FA_CAT_NAME_1", "Ӧ������");
            fieldMap.put("FA_CAT_NAME_2", "����Ŀ¼");
            fieldMap.put("FA_CAT_NAME_3", "����");
            fieldMap.put("DEPRN_COST_1", "1��");
            fieldMap.put("DEPRN_COST_2", "2��");
            fieldMap.put("DEPRN_COST_3", "3��");
            fieldMap.put("DEPRN_COST_4", "4��");
            fieldMap.put("DEPRN_COST_5", "5��");
            fieldMap.put("DEPRN_COST_6", "6��");
            fieldMap.put("DEPRN_COST_7", "7��");
            fieldMap.put("DEPRN_COST_8", "8��");
            fieldMap.put("DEPRN_COST_9", "9��");
            fieldMap.put("DEPRN_COST_10", "10��");
            fieldMap.put("DEPRN_COST_11", "11��");
            fieldMap.put("DEPRN_COST_12", "12��");
            fieldMap.put("DEPRN_COST_13", "13��");
            fieldMap.put("DEPRN_COST_14", "14��");
            fieldMap.put("DEPRN_COST_15", "15��");
            fieldMap.put("DEPRN_COST_16", "16��");
            fieldMap.put("DEPRN_COST_17", "17��");
            fieldMap.put("DEPRN_COST_18", "18��");
            fieldMap.put("DEPRN_COST_19", "19��");
            fieldMap.put("DEPRN_COST_20", "20��");
            fieldMap.put("DEPRN_COST_21", "21��");
            fieldMap.put("DEPRN_COST_22", "22��");
            fieldMap.put("DEPRN_COST_23", "23��");
            fieldMap.put("DEPRN_COST_24", "24��");
            fieldMap.put("DEPRN_COST_25", "25��");
            fieldMap.put("DEPRN_COST_26", "26��");
            fieldMap.put("DEPRN_COST_27", "27��");
            fieldMap.put("DEPRN_COST_28", "28��");
            fieldMap.put("DEPRN_COST_29", "29��");
            fieldMap.put("DEPRN_COST_30", "30��");
            fieldMap.put("DEPRN_COST_31", "31��");
            fieldMap.put("DEPRN_COST_32", "32��");
            fieldMap.put("DEPRN_COST_33", "33��");
            fieldMap.put("DEPRN_COST_34", "34��");
            fieldMap.put("DEPRN_COST_35", "35��");
            fieldMap.put("DEPRN_COST_36", "36��");
            fieldMap.put("DEPRN_COST_37", "37��");
            fieldMap.put("DEPRN_COST_38", "38��");
            fieldMap.put("DEPRN_COST_39", "39��");
            fieldMap.put("DEPRN_COST_40", "40��");
            fieldMap.put("DEPRN_COST_41", "41��");
            fieldMap.put("DEPRN_COST_42", "42��");
            fieldMap.put("DEPRN_COST_43", "43��");
            fieldMap.put("DEPRN_COST_44", "44��");
            fieldMap.put("DEPRN_COST_45", "45��");
            fieldMap.put("DEPRN_COST_46", "46��");
            fieldMap.put("DEPRN_COST_47", "47��");
            fieldMap.put("DEPRN_COST_48", "48��");
            fieldMap.put("DEPRN_COST_49", "49��");
            fieldMap.put("DEPRN_COST_50", "50��");
            fieldMap.put("DEPRN_COST_51", "51��");
            fieldMap.put("DEPRN_COST_52", "52��");
            fieldMap.put("DEPRN_COST_53", "53��");
            fieldMap.put("DEPRN_COST_54", "54��");
            fieldMap.put("DEPRN_COST_55", "55��");
            fieldMap.put("DEPRN_COST_56", "56��");
            fieldMap.put("DEPRN_COST_57", "57��");
            fieldMap.put("DEPRN_COST_58", "58��");
            fieldMap.put("DEPRN_COST_59", "59��");
            fieldMap.put("DEPRN_COST_60", "60��");
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
