package com.sino.ams.system.manydimensions.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.match.amselementmatch.dto.AmsElementMatchDTO;
import com.sino.ams.system.manydimensions.model.NleModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * User: ����
 * Date: 2009-6-16
 * Time: 17:32:55
 * Function:		����������ά��
 */
public class NleDAO extends AMSBaseDAO {

	private NleModel nleModel = null;

    public NleDAO(SfUserDTO userAccount, AmsElementMatchDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        nleModel = new NleModel((SfUserDTO) userAccount, dtoParameter);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
    	AmsElementMatchDTO dtoPara = (AmsElementMatchDTO) dtoParameter;
        sqlProducer = new NleModel(userAccount, dtoPara);
    }
    
    /**
	 * ����:��������α��������Ƿ����
	 * @return boolean
	 */
	public boolean existObject(){
		boolean exist = false;
		try {
			SQLModel sqlModel = nleModel.getObjectEsistModel();
			SimpleQuery simQuery = new SimpleQuery(sqlModel, conn);
			simQuery.executeQuery();
			exist = simQuery.hasResult();
		} catch (QueryException ex) {
			ex.printLog();
		}
		return exist;
	}
    
    
    /**
	 * ���ܣ�����Excel�ļ���
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 *
	 */
	public File exportFile() throws DataTransException {     
			File file = null;
			SQLModel sqlModel = nleModel.getPageQueryModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setCalPattern(CalendarConstant.LINE_PATTERN);
			rule.setSourceConn(conn);
			String fileName = "����������ά��.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("ROWNUM", "���");
			fieldMap.put("LNE_CODE", "�����α���");
			fieldMap.put("LNE_NAME", "����������");

			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle("����������ά��");
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
			return file;
	}
 
}
