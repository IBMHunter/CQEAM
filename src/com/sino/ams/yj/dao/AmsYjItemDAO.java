package com.sino.ams.yj.dao;

import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.WorldConstant;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.yj.dto.AmsYjItemDTO;
import com.sino.ams.yj.model.AmsYjItemModel;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.spare.model.SpareVendorModel;

/**
 * <p>Title: AmsYjItemDAO</p>
 * <p>Description:�����Զ����ɷ������AmsYjItemDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ������װ������ά��
 */

public class AmsYjItemDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ�Ӧ�������豸�����ֵ�� AMS_YJ_ITEM ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsYjItemDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsYjItemDAO(SfUserDTO userAccount, AmsYjItemDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		AmsYjItemDTO dtoPara = (AmsYjItemDTO)dtoParameter;
		super.sqlProducer = new AmsYjItemModel((SfUserDTO)userAccount, dtoPara);
	}


    /**
     * ���ܣ���Чѡ���豸
     * @param itemCode String
     */
    public void enableItem(String itemCode) {
        try {
            AmsYjItemModel a = (AmsYjItemModel) sqlProducer;
            SQLModel sModel = a.getEnableModel(itemCode);
            DBOperator.updateRecord(sModel, conn);
            prodMessage(CustMessageKey.ENABLE_SUCCESS);
            getMessage().addParameterValue("�豸");
        } catch (DataHandleException ex) {
            ex.printLog();
            prodMessage(CustMessageKey.ENABLE_FAILURE);
            getMessage().addParameterValue("�豸");
        }
    }
     public boolean doVerify(String itemName) throws QueryException {
       boolean success = false;
       AmsYjItemModel aModel = (AmsYjItemModel) sqlProducer;
       SQLModel sqlModel = aModel.doVerify(itemName);
       SimpleQuery sq = new SimpleQuery(sqlModel, conn);
       sq.executeQuery();
       success = sq.hasResult();
       return success;
   }

    /**
         * ���ܣ�����Excel�ļ���
         * @return File
         * @throws com.sino.base.exception.DataTransException
         */
        public File exportFile() throws DataTransException {
            File file = null;
            try {
                DataTransfer transfer = null;
                SQLModel sqlModel = sqlProducer.getPageQueryModel();
                TransRule rule = new TransRule();
                rule.setDataSource(sqlModel);
                rule.setCalPattern(CalendarConstant.LINE_PATTERN);
                rule.setSourceConn(conn);

                String fileName = "Ӧ������װ������.xls";
                String filePath = WorldConstant.USER_HOME;
                filePath += WorldConstant.FILE_SEPARATOR;
                filePath += fileName;
                rule.setTarFile(filePath);

                DataRange range = new DataRange();
                rule.setDataRange(range);

                Map fieldMap = new HashMap();
                fieldMap.put("ITEM_CODE", "ID��");
                fieldMap.put("ITEM_NAME", "װ������");
                fieldMap.put("ITEM_CATEGORY", "�ֵ�����");
                fieldMap.put("CREATE_USER", "������");
                fieldMap.put("CREATION_DATE", "��������");
                fieldMap.put("LAST_UPDATE_USER", "������");
                fieldMap.put("LAST_UPDATE_DATE", "��������");
                fieldMap.put("DISABLE_DATE", "ʧЧ����");

                rule.setFieldMap(fieldMap);

                CustomTransData custData = new CustomTransData();
                custData.setReportTitle(fileName);
                custData.setReportPerson(sfUser.getUsername());
                custData.setNeedReportDate(true);
                rule.setCustData(custData);
                /*rule.setSheetSize(1000);*/
                //���÷�ҳ��ʾ
                TransferFactory factory = new TransferFactory();
                transfer = factory.getTransfer(rule);
                transfer.transData();
                file = (File) transfer.getTransResult();
            } catch (SQLModelException ex) {
                ex.printLog();
                throw new DataTransException(ex);
            }
            return file;
        }

}