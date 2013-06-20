package com.sino.ams.newasset.lose.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.ams.newasset.lose.dto.LoseHeaderDTO;
import com.sino.ams.newasset.lose.dto.LoseLineDTO;
import com.sino.ams.newasset.lose.model.LoseModel;
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
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * 
 * @ϵͳ����: ��ʧ
 * @��������: 
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Jul 14, 2011
 */
public class LoseDAO extends AMSProcedureBaseDAO{

	LoseHeaderDTO headerDTO = null;
	LoseModel loseModel = null;
	public LoseDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn); 
		this.initSQLProducer(userAccount, dtoParameter); 
	}

	@Override
	protected void initSQLProducer(BaseUserDTO arg0, DTO arg1) {
		// TODO Auto-generated method stub
		headerDTO = (LoseHeaderDTO) dtoParameter;
		loseModel = new LoseModel((SfUserDTO)userAccount, headerDTO );
		sqlProducer = loseModel ;
	}
	
	public DTOSet getLinesData( String headerId ) throws QueryException {
		if( !StrUtil.isEmpty( headerId )) {
	        SQLModel sqlModel = loseModel.getLinesModel( headerId );
	        SimpleQuery splq = new SimpleQuery(sqlModel, conn);
	        splq.setCalPattern(getCalPattern());
	        splq.setDTOClassName( LoseLineDTO.class.getName());
	        splq.executeQuery();
	        return splq.getDTOSet();
		}else{
			return new DTOSet();
		}
    }
	
	public void createHeader( LoseHeaderDTO header ) throws DataHandleException{
		SQLModel sqlModel = loseModel.createHeaderModel( header );
		DBOperator.updateRecord( sqlModel , conn );
	}
	
	public void createLine( LoseLineDTO line ) throws DataHandleException, CalendarException{
		if( !StrUtil.isEmpty( line.getBarcode() ) ){
			SQLModel sqlModel = loseModel.createLineModel( line );
			DBOperator.updateRecord( sqlModel , conn );
		}
	}
	//����ʱ����ͷ
	public void updateHeader( LoseHeaderDTO header ) throws DataHandleException{
		SQLModel sqlModel = loseModel.updateHeaderModel( header );
		DBOperator.updateRecord( sqlModel , conn );
	}
	//�����и���ͷ��Ϣ��״̬
	public void updateHeaderStatus( LoseHeaderDTO header ) throws DataHandleException{
		SQLModel sqlModel = loseModel.updateHeaderStatusModel( header );
		DBOperator.updateRecord( sqlModel , conn );
	}
	
	public void deleteLine( String headerId ) throws DataHandleException{
		SQLModel sqlModel = loseModel.deleteLinesModel(headerId);
		DBOperator.updateRecord( sqlModel , conn );
	}
	
	public File exportFile() throws DataTransException{
		File file = null;
        try {
            DataTransfer transfer = null;
            
            SQLModel sqlModel = loseModel.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);            
            String fileName = "��ʧ���ݱ�.xls" ;
            
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap(); 
             
            fieldMap.put("TRANS_NO", "��ʧ����");
            fieldMap.put("TRANS_STATUS_DESC", "����״̬");
            fieldMap.put("FROM_COMPANY_NAME", "��˾����");
            fieldMap.put("CREATED", "������");
            fieldMap.put("CREATION_DATE", "��������"); 

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(fileName);
            custData.setReportPerson( this.userAccount.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
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
