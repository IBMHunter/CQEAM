package com.sino.ams.match.amselementmatch.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.match.amselementmatch.dto.AmsElementMatchDTO;
import com.sino.ams.match.amselementmatch.model.AmsElementMatchModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.framework.dto.BaseUserDTO;

public class AmsElementMatchDAO extends AMSBaseDAO {
	/**
	 * 
	 * Title: 			SinoApplication
	 * @param userAccount		SfUserDTO  ����ϵͳ�����ղ����û�����
	 * @param dtoParameter		LabelDTO   װ�ڱ��β��������ݶ���
	 * @param conn		Connection  ���ݿ�����,�е����ߴ���
	 * Function			�ʲ�Ŀ¼������Ԫ�ض�Ӧ��ϵά�� ���ݷ��ʲ㹹�캯��
	 * @author 			����
	 * @version 		0.1
	 * @Date			Apr 30, 2009
	 */
	public AmsElementMatchDAO(SfUserDTO userAccount, AmsElementMatchDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		amsElementMatchModel = new AmsElementMatchModel((SfUserDTO) userAccount, dtoParameter);
	}
	
	private AmsElementMatchModel amsElementMatchModel = null;	

	/**
	 * 
	 * Title: 			SinoApplication
	 * @param userAccount		SfUserDTO  ����ϵͳ�����ղ����û�����
	 * @param dtoParameter		LabelDTO   װ�ڱ��β��������ݶ���
	 * @param conn		Connection  ���ݿ�����,�е����ߴ���
	 * Function			SQL������BaseSQLProducer�ĳ�ʼ����
	 * @author 			����
	 * @version 		0.1
	 * @Date			Apr 26, 2009
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		AmsElementMatchDTO dtoPara = (AmsElementMatchDTO)dtoParameter;
		sqlProducer = new AmsElementMatchModel(userAccount, dtoPara);
	}
	
	
	/**
	 * ���ܣ�����Excel�ļ���
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 *
	 */
	public File exportFile() throws DataTransException {     
		File file = null;
		String accessType = ((AmsElementMatchDTO)dtoParameter).getAccessType();
		AmsElementMatchModel  amsElementMatchModel =(AmsElementMatchModel)sqlProducer;
			SQLModel sqlModel = amsElementMatchModel.getPageQueryModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setCalPattern(CalendarConstant.LINE_PATTERN);
			rule.setSourceConn(conn);
			String fileName = "";
			if("lne".equals(accessType)){
				fileName = "�ʲ�Ŀ¼���߼�����Ԫ�ض�Ӧ��ϵ.xls";
			}else if("cex".equals(accessType)){
				fileName = "�ʲ�Ŀ¼��Ͷ�ʷ����Ӧ��ϵ.xls";
			}else if("ope".equals(accessType)){
				fileName = "�ʲ�Ŀ¼��ҵ��ƽ̨��Ӧ��ϵ.xls";
			}else if("nle".equals(accessType)){
				fileName = "�ʲ�Ŀ¼�������ζ�Ӧ��ϵ.xls";
			}
			
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			CustomTransData custData = new CustomTransData();
			
			if("lne".equals(accessType)){
				fieldMap.put("CONTENT_ID", "�ʲ�Ŀ¼ID");
				fieldMap.put("CONTENT_CODE", "�ʲ�Ŀ¼����");
				fieldMap.put("CONTENT_NAME", "�ʲ�Ŀ¼����");
				fieldMap.put("AMS_LNE_ID", "�߼�����Ԫ�ر��");
				fieldMap.put("NET_CATEGORY1", "����רҵ1");
				fieldMap.put("NET_CATEGORY2", "����רҵ2");
				fieldMap.put("NET_UNIT_CODE", "��Ԫ����");
				fieldMap.put("LOG_NET_ELE", "�߼�����Ԫ��");
				
				custData.setReportTitle("�ʲ�Ŀ¼���߼�����Ԫ�ض�Ӧ��ϵά��");
			}else if("cex".equals(accessType)){
				fieldMap.put("CONTENT_ID", "�ʲ�Ŀ¼ID");
				fieldMap.put("CONTENT_CODE", "�ʲ�Ŀ¼����");
				fieldMap.put("CONTENT_NAME", "�ʲ�Ŀ¼����");
				fieldMap.put("AMS_CEX_ID", "Ͷ�ʷ�������ID");
				fieldMap.put("INVEST_CATEGORY1", "Ͷ�ʴ���");
				fieldMap.put("INVEST_CATEGORY2", "Ͷ������");
				fieldMap.put("INVEST_CAT_CODE", "Ͷ�ʷ������");
				fieldMap.put("INVEST_CAT_NAME", "Ͷ�ʷ�������");
				
				custData.setReportTitle("�ʲ�Ŀ¼��Ͷ�ʷ����Ӧ��ϵά��");
			}else if("ope".equals(accessType)){
				fieldMap.put("CONTENT_ID", "�ʲ�Ŀ¼ID");
				fieldMap.put("CONTENT_CODE", "�ʲ�Ŀ¼����");
				fieldMap.put("CONTENT_NAME", "�ʲ�Ŀ¼����");
				fieldMap.put("AMS_OPE_ID", "ҵ��ƽ̨����ID");
				fieldMap.put("OPE_CODE", "ҵ��ƽ̨����");
				fieldMap.put("OPE_NAME", "ҵ��ƽ̨����");
				
				custData.setReportTitle("�ʲ�Ŀ¼��ҵ��ƽ̨��Ӧ��ϵά��");
			}else if("nle".equals(accessType)){
				fieldMap.put("CONTENT_ID", "�ʲ�Ŀ¼ID");
				fieldMap.put("CONTENT_CODE", "�ʲ�Ŀ¼����");
				fieldMap.put("CONTENT_NAME", "�ʲ�Ŀ¼����");
				fieldMap.put("LNE_CODE", "�����α���");
				fieldMap.put("LNE_NAME", "����������");
				
				custData.setReportTitle("�ʲ�Ŀ¼�����������Զ�Ӧ��ϵά��");
			}
			
			rule.setFieldMap(fieldMap);

			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		return file;
	}
	
	/**
	 * ���ܣ�ɾ��������¼
	 * @param dtos DTOSet
	 * @return boolean
	 * @throws DataHandleException
	 * @throws SQLException 
	 */
	public boolean deleteResponsibility(DTOSet dtos) throws DataHandleException, SQLException {
        boolean operateResult = false;
        if (dtos != null && dtos.getSize() > 0) {
            operateResult = true;
            int dtoCount = dtos.getSize();
            AmsElementMatchModel modelProducer = (AmsElementMatchModel) sqlProducer;
            conn.setAutoCommit(false);
            for (int i = 0; i < dtoCount; i++) {
                try{
                	AmsElementMatchDTO dto = (AmsElementMatchDTO) dtos.getDTO(i);
                	SQLModel sqlModel = modelProducer.deleteModel(dto);
                	DBOperator.updateRecord(sqlModel, conn);
                	conn.commit();
                } catch(Exception e){
                    e.printStackTrace();
                    conn.rollback();
                }
            }
            if(conn != null){
            	conn.close();
            }
        }
        return operateResult;
    }
	
}
