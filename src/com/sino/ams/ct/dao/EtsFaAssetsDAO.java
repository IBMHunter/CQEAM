package com.sino.ams.ct.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.ct.dto.EtsFaAssetsDTO;
import com.sino.ams.ct.dto.EtsItemInfoDTO;
import com.sino.ams.ct.model.EtsFaAssetsModel;
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

public class EtsFaAssetsDAO extends AMSBaseDAO {

	private SfUserDTO sfUser = null;
	
	/**
	 * ���ܣ���ͨ�ʲ�������Ϣ(EAM) ETS_FA_ASSETS ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsFaAssetsDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsFaAssetsDAO(SfUserDTO userAccount, EtsFaAssetsDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}
	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EtsFaAssetsDTO dtoPara = (EtsFaAssetsDTO)dtoParameter;
		super.sqlProducer = new EtsFaAssetsModel((SfUserDTO)userAccount, dtoPara);
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
                String fileName = "��ͨ�ʲ�������Ϣ.xls";
                String filePath = WorldConstant.USER_HOME;
                filePath += WorldConstant.FILE_SEPARATOR;
                filePath += fileName;
                rule.setTarFile(filePath);
                DataRange range = new DataRange();
                rule.setDataRange(range);

                Map fieldMap = new HashMap();
                //fieldMap.put("ORG_NAME", "��˾");
                fieldMap.put("TAG_NUMBER", "�ʲ���ǩ��");
                fieldMap.put("ASSET_NUMBER", "�ʲ����");
                fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
                fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
                fieldMap.put("ORIGINAL_COST", "�ʲ�ԭֵ");
                fieldMap.put("DATEPLACED_IN_SERVICE", "��������");
                fieldMap.put("DEPRN_COST", "��ֵ");
                fieldMap.put("RESPONSIBILITY_USER", "������");
                fieldMap.put("RESPONSIBILITY_DEPT", "���β���");
                fieldMap.put("RETIRE_DATE", "��������");

                rule.setFieldMap(fieldMap);

                CustomTransData custData = new CustomTransData();
                custData.setReportTitle("��ͨ�ʲ�������Ϣ");
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
                String fileName = "��ͨ�ʲ�������Ϣ.xls";
                String filePath = WorldConstant.USER_HOME;
                filePath += WorldConstant.FILE_SEPARATOR;
                filePath += fileName;
                rule.setTarFile(filePath);
                DataRange range = new DataRange();
                rule.setDataRange(range);
                
                Map fieldMap = new HashMap();
                //fieldMap.put("ORG_NAME", "��˾");
                fieldMap.put("TAG_NUMBER", "�ʲ���ǩ��");
                fieldMap.put("ASSET_NUMBER", "�ʲ����");
                fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
                fieldMap.put("MODEL_NUMBER", "�ʲ��ͺ�");
                fieldMap.put("ORIGINAL_COST", "�ʲ�ԭֵ");
                fieldMap.put("DATEPLACED_IN_SERVICE", "��������");
                fieldMap.put("DEPRN_COST", "��ֵ");
                fieldMap.put("RESPONSIBILITY_USER", "������");
                fieldMap.put("RESPONSIBILITY_DEPT", "���β���");
                fieldMap.put("RETIRE_DATE", "��������");

                rule.setFieldMap(fieldMap);
                
                CustomTransData custData = new CustomTransData();
                custData.setReportTitle("��ͨ�ʲ�������Ϣ");
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
