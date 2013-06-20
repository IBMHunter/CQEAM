package com.sino.ams.newasset.report.dao;


import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.report.model.DonateAssetsReportModel;
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
 * @author:		����
 * Date:		2009-6-2
 * Function:	�����ʲ�ͳ��DAO
 *
 */
public class DonateAssetsReportDAO extends AMSBaseDAO {

    private SfUserDTO sfUser = null;
    

    /**
     * ���ܣ������ʲ�ͳ��  ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsHouseInfoDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public DonateAssetsReportDAO(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
    	AmsAssetsAddressVDTO dtoPara = (AmsAssetsAddressVDTO) dtoParameter;
        super.sqlProducer = new DonateAssetsReportModel((SfUserDTO) userAccount, dtoPara);
    }

      public File exportFile() throws DataTransException {
        File file = null;
        AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
        try {
        	SQLModel sqlModel = null;
        	 String fileName = "�����ʲ�ͳ��";
            sqlModel = sqlProducer.getPageQueryModel(); 
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setSourceConn(conn);
           
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath + ".xls");

            DataRange range = new DataRange();
            rule.setDataRange(range);
            Map fieldMap = new HashMap();
            
            fieldMap.put("COMPANY", "������˾");
            fieldMap.put("DEPT_NAME", "���β���");
            
            fieldMap.put("BARCODE", "��ǩ��");
            fieldMap.put("ITEM_NAME", "�ʲ�����");
            fieldMap.put("ITEM_SPEC", "����ͺ�");
//            fieldMap.put("DEPRN_RESERVE", "�ۼ��۾�");
//            fieldMap.put("NET_BOOK_VALUE", "��ֵ");
//            fieldMap.put("LIMIT_VALUE", "����");
//            fieldMap.put("IMPAIRMENT_RESERVE", "�ۼƼ�ֵ׼��");
//            fieldMap.put("PTD_DEPRN", "�����۾�");
            fieldMap.put("ITEM_QTY", "�ʲ�����");
            fieldMap.put("COST", "��ֵ");
            fieldMap.put("ASSET_ID", "�ʲ����");
            
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
