package com.sino.ams.ct.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.ct.dto.EtsItemInfoDTO;
import com.sino.ams.ct.model.EtsItemInfoModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
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
 * <p>Title: EtsItemInfoDAO</p>
 * <p>Description:�����Զ����ɷ������EtsItemInfoDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ��ʿ��
 * @version 1.0
 */
public class EtsItemInfoDAO extends AMSBaseDAO {
	
	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���ǩ����Ϣ(EAM) ETS_ITEM_INFO ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsItemInfoDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsItemInfoDAO(SfUserDTO userAccount, EtsItemInfoDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		EtsItemInfoDTO dtoPara = (EtsItemInfoDTO)dtoParameter;
		super.sqlProducer = new EtsItemInfoModel((SfUserDTO)userAccount, dtoPara);
	}

	public File exportFile() throws DataTransException {
		
        File file = null;
        try {
            EtsItemInfoDTO etsItemInfoDto = (EtsItemInfoDTO) dtoParameter;
            if (etsItemInfoDto.getQryType().equals(WebAttrConstant.BY_DAIWEI)) {
                SQLModel sqlModel = sqlProducer.getPageQueryModel();
                TransRule rule = new TransRule();
                rule.setDataSource(sqlModel);
                rule.setCalPattern(CalendarConstant.LINE_PATTERN);
                rule.setSourceConn(conn);
                String fileName = "��ͨ�豸��Ϣ.xls";
                String filePath = WorldConstant.USER_HOME;
                filePath += WorldConstant.FILE_SEPARATOR;
                filePath += fileName;
                rule.setTarFile(filePath);
                DataRange range = new DataRange();
                rule.setDataRange(range);

                Map fieldMap = new HashMap();
//            fieldMap.put("ORG_NAME", "��˾");
                fieldMap.put("BARCODE", "����");
                fieldMap.put("ITEM_NAME", "�豸����");
                fieldMap.put("ITEM_SPEC", "����ͺ�");
                fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���");
                fieldMap.put("WORKORDER_OBJECT_LOCATION", "���ڵص�");
                fieldMap.put("NAME", "��ά��˾");

                rule.setFieldMap(fieldMap);

                CustomTransData custData = new CustomTransData();
                custData.setReportTitle("��ͨ�豸��Ϣ");
                custData.setReportPerson(sfUser.getUsername());
                custData.setNeedReportDate(true);
                rule.setCustData(custData);
                TransferFactory factory = new TransferFactory();
                DataTransfer transfer = factory.getTransfer(rule);
                transfer.transData();
                file = (File) transfer.getTransResult();
            } else {
            	
                SQLModel sqlModel = sqlProducer.getPageQueryModel();
                TransRule rule = new TransRule();
                rule.setDataSource(sqlModel);
                rule.setCalPattern(CalendarConstant.LINE_PATTERN);
                rule.setSourceConn(conn);
                String fileName = "��ͨ�豸��Ϣ.xls";
                String filePath = WorldConstant.USER_HOME;
                filePath += WorldConstant.FILE_SEPARATOR;
                filePath += fileName;
                rule.setTarFile(filePath);
                DataRange range = new DataRange();
                rule.setDataRange(range);
                
                Map fieldMap = new HashMap();
                //fieldMap.put("ORG_NAME", "��˾");
                fieldMap.put("BARCODE", "����");
                fieldMap.put("ITEM_NAME", "�豸����");
                fieldMap.put("ITEM_SPEC", "����ͺ�");
                fieldMap.put("ITEM_CATEGORY", "�豸����");
                fieldMap.put("START_DATE", "��������");
                fieldMap.put("PROJECT_NAME", "��������");
                fieldMap.put("WORKORDER_OBJECT_NAME", "�����ص�");
                fieldMap.put("COUNTY_NAME", "��������");

                rule.setFieldMap(fieldMap);
                
                CustomTransData custData = new CustomTransData();
                custData.setReportTitle("��ͨ�豸��Ϣ");
                custData.setReportPerson(sfUser.getUsername());
                custData.setNeedReportDate(true);
                rule.setCustData(custData);
                TransferFactory factory = new TransferFactory();
                DataTransfer transfer = factory.getTransfer(rule);
                transfer.transData();
                file = (File) transfer.getTransResult();
            }
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataTransException(ex);
        }
        return file;
    }
	
	
}
