package com.sino.ams.system.note.dao;


import java.sql.Connection;

import com.sino.ams.system.note.dto.AmsRentDeadlineDTO;
import com.sino.ams.system.note.model.AmsRentDeadlineModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.DBActionConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DataUniqueChecker;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ValidateException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsRentDeadlineDAO</p>
 * <p>Description:�����Զ����ɷ������AmsRentDeadlineDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class AmsRentDeadlineDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ���������(EAM) AMS_RENT_DEADLINE ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsRentDeadlineDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsRentDeadlineDAO(SfUserDTO userAccount, AmsRentDeadlineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		AmsRentDeadlineDTO dtoPara = (AmsRentDeadlineDTO)dtoParameter;
		super.sqlProducer = new AmsRentDeadlineModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ�������������(EAM)��AMS_RENT_DEADLINE�����ݡ�
	 * @return boolean
	 */
    public void  createData() {
        boolean operateResult = false;
        try {
            String tableName = "AMS_RENT_DEADLINE";
            DataUniqueChecker datChecker = new DataUniqueChecker(tableName, dtoParameter, conn);
            datChecker.setDBAction(DBActionConstant.INSERT);
            datChecker.setServletConfig(servletConfig);
            boolean isValid = datChecker.isDataValid();
            if (!isValid) {
                prodMessage(MsgKeyConstant.UNIQUE_ERROR);
                getMessage().addParameterValue("����������������");
            } else {
                super.createData();
                operateResult = true;
                getMessage().addParameterValue("����������������");
            }
        } catch (ValidateException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.COMMON_ERROR);
        } catch (DataHandleException ex)  {

        }
       
//        return operateResult;
    }

	/**
	 * ���ܣ�������������(EAM)��AMS_RENT_DEADLINE�����ݡ�
	 * @return boolean
	 */
	public void updateData() throws DataHandleException{
//		boolean operateResult = super.updateData();
		super.updateData();
		getMessage().addParameterValue("����������������");
//		return operateResult;
	}

	/**
	 * ���ܣ�ɾ����������(EAM)��AMS_RENT_DEADLINE�����ݡ�
	 * @return boolean
	 */
	public void deleteData() throws DataHandleException{
//		boolean operateResult = super.deleteData();
		 super.deleteData();
		getMessage().addParameterValue("����������������");
//		return operateResult;
	}

       public boolean verifyBarcode(String barcode) throws QueryException { //ִ��У��Barcode����
        boolean success = false;
//        try {
            AmsRentDeadlineModel amsRentDeadlineModel = (AmsRentDeadlineModel) sqlProducer;
            SQLModel sqlModel = amsRentDeadlineModel.getVerifyBarcodeModel(barcode);

            SimpleQuery sq = new SimpleQuery(sqlModel, conn);
            sq.executeQuery();
            success = sq.hasResult();

        return success;
    }
}