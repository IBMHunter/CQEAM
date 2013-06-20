package com.sino.ams.dzyh.dao;


import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.dzyh.dto.EamDhBillLDTO;
import com.sino.ams.dzyh.model.EamDhBillLModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EamDhBillLDAO</p>
 * <p>Description:�����Զ����ɷ������EamDhBillLDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����
 * @version 1.0
 */


public class EamDhBillLDAO extends AMSBaseDAO {

	private EamDhBillLDTO edblDto;

	/**
	 * ���ܣ���ṹ����-L(EAM) EAM_DH_BILL_L ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EamDhBillLDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EamDhBillLDAO(SfUserDTO userAccount, EamDhBillLDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		edblDto = null;
		edblDto = dtoParameter;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		EamDhBillLDTO dtoPara = (EamDhBillLDTO)dtoParameter;
		super.sqlProducer = new EamDhBillLModel((SfUserDTO)userAccount, dtoPara);
	}
	
	public void createData() throws DataHandleException {
	    super.createData();
	    getMessage().addParameterValue("�����ֵ�׺��б�(EAM)");
	}
	
	/**
	 * �ύ����
	 * @param lineSet
	 * @return
	 * @throws SQLException
	 * @throws SQLModelException
	 */
	public boolean submitSaveData(DTOSet lineSet) throws SQLException, SQLModelException {
	    boolean success;
	    success = false;
	    boolean autoCommit = true;
	    try {
	        autoCommit = conn.getAutoCommit();
	        conn.setAutoCommit(false);
	        String headId = edblDto.getBillHeaderId();
	        edblDto.setCreationDate(CalendarUtil.getCurrDate());
	        OrderNumGenerator ong = new OrderNumGenerator(conn, userAccount.getCompanyCode(), "DZYH");
	        System.out.println("\n=====================" + ong.getOrderNum());
	        edblDto.setBillNo(ong.getOrderNum());
	        if(headId.equals(""))
	        {
	            SeqProducer seq = new SeqProducer(conn);
	            //TODO 
	            headId =  "" + seq.getStrNextSeq("EAM_DH_BILL_H");
	            edblDto.setBillHeaderId(headId);
	            edblDto.setBillStatus("1");
	            saveHeaders(edblDto);
	            System.out.println("=======saveHeaders===---------------->>>>>>>>>>>>>" + lineSet.getSize());
	            createData();
	        } else {
	            edblDto.setBillStatus("1");
	            updateData();
	            deleteLines(headId);
	        }
	        saveLines(lineSet);
	        System.out.println("=======saveLines===---------------->>>>>>>>>>>>>" + lineSet.getSize());
	        saveEIILines(lineSet);
	        System.out.println("=======saveEIILines===--------------->>>>>>>>>>>>>" + lineSet.getSize());
	        success = true;
	    }
	    catch(SQLException e) {
	        Logger.logError(e);
	    }
	    catch(DataHandleException e) {
	        e.printLog();
	    }
	    catch(CalendarException e) {
	        e.printLog();
	    }
	    finally {
	        if(success) {
	            prodMessage("SPARE_SAVE_SUCCESS");
	            conn.commit();
	        } else {
	            prodMessage("SPARE_SAVE_FAILURE");
	            conn.rollback();
	        }
	        conn.setAutoCommit(autoCommit);
	    }
	    return success;
	}
	
	/**
	 * ����ͷ��Ϣ
	 * @param lineData
	 * @throws DataHandleException
	 * @throws SQLModelException
	 */
	public void saveHeaders(EamDhBillLDTO lineData) throws DataHandleException, SQLModelException
	{
	    EamDhBillLDAO lineDAO = new EamDhBillLDAO(userAccount, null, conn);
	    EamDhBillLModel model = new EamDhBillLModel(userAccount, null);
	    lineData.setBillHeaderId(edblDto.getBillHeaderId());
	    lineDAO.setDTOParameter(lineData);
	    DBOperator.updateRecord(model.getDataCreateHeaderModel(lineData), conn);
	}
	
	/**
	 * ��������Ϣ
	 * @param lineSet
	 * @throws DataHandleException
	 */
	public void saveLines(DTOSet lineSet) throws DataHandleException {
	    if(lineSet != null && !lineSet.isEmpty()) {
	        EamDhBillLDAO edblDao = new EamDhBillLDAO(userAccount, null, conn);
	        for(int i = 0; i < lineSet.getSize(); i++) {
	            EamDhBillLDTO lineData = (EamDhBillLDTO)lineSet.getDTO(i);
	            lineData.setBillHeaderId(edblDto.getBillHeaderId());
	            edblDao.setDTOParameter(lineData);
	            edblDao.createData();
	        }
	
	    }
	}
	
	/**
	 * �h������Ϣ
	 * @param billHeaderId
	 * @throws DataHandleException
	 */
	public void deleteLines(String billHeaderId) throws DataHandleException {
	    EamDhBillLModel model = new EamDhBillLModel(userAccount, null);
	    DBOperator.updateRecord(model.getDeleteByBillHeaderIdModel(billHeaderId), conn);
	}
	
	/**
	 * ������д��ETS_ITEM_INFO
	 * @param lineSet
	 * @throws DataHandleException
	 * @throws SQLModelException
	 */
	public void saveEIILines(DTOSet lineSet) throws DataHandleException, SQLModelException {
	    if(lineSet != null && !lineSet.isEmpty()) {
	        EamDhBillLDAO lineDAO = new EamDhBillLDAO(userAccount, null, conn);
	        EamDhBillLModel model = new EamDhBillLModel(userAccount, null);
	        for(int i = 0; i < lineSet.getSize(); i++) {
	            EamDhBillLDTO lineData = (EamDhBillLDTO)lineSet.getDTO(i);
	            lineData.setBillHeaderId(edblDto.getBillHeaderId());
	            lineDAO.setDTOParameter(lineData);
	            DBOperator.updateRecord(model.getCreateEIIModel(lineData), conn);
	        }
	
	    }
	}
	
}