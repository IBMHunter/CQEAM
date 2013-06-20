package com.sino.ams.print.dao;


import java.sql.Connection;
import java.sql.SQLException;

import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.StrUtil;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.print.dto.AmsBarcodePrintDTO;
import com.sino.ams.print.model.AmsBarcodePrintModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.flow.bean.FlowAction;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsBarcodePrintDAO</p>
 * <p>Description:�����Զ����ɷ������AmsBarcodePrintDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class AmsBarcodePrintDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ������ӡ��Ϣ�� AMS_BARCODE_PRINT ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsBarcodePrintDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsBarcodePrintDAO(SfUserDTO userAccount, AmsBarcodePrintDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		AmsBarcodePrintDTO dtoPara = (AmsBarcodePrintDTO)dtoParameter;
		super.sqlProducer = new AmsBarcodePrintModel((SfUserDTO)userAccount, dtoPara);
	}

     /**
     * �ύ
     * @param
     * @return success
     * @throws java.sql.SQLException
      */
    public boolean submit(FlowDTO flowDTO) throws SQLException {
        boolean success = true;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);

            AmsBarcodePrintDTO dtoPri = (AmsBarcodePrintDTO)dtoParameter;
            dtoPri.setApplyDate(CalendarUtil.getCurrDate());
            FlowAction fa =null;
                OrderNumGenerator ong = new OrderNumGenerator(conn, sfUser.getCompanyCode(), "3");
                dtoPri.setBatchNo(ong.getOrderNum());
//                dtoPri.setBatchNo("2521-3-20080100016");
                SeqProducer seq = new SeqProducer(conn);
                String Id =  String.valueOf(seq.getStrNextSeq("AMS_BARCODE_PRINT_S"));
                dtoPri.setId(Id);
                createData();

                flowDTO.setApplyId(dtoPri.getId());
                flowDTO.setApplyNo(dtoPri.getBatchNo());
                flowDTO.setActivity(FlowConstant.FLOW_CODE_NEXT);
                fa = new FlowAction(conn, flowDTO);
                fa.flow();

//             userAccount.toString();


            conn.commit();
            prodMessage("SAVE_SUCCESS");
            success = true;
        } catch (SQLException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (DataHandleException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (QueryException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } catch (CalendarException e) {
            conn.rollback();
            success = false;
            Logger.logError(e);
            prodMessage("SAVE_FAILURE");
        } finally {
            conn.setAutoCommit(autoCommit);
        }
        return success;
    }


      /**
     * ����ͨ��
     * @param flowDTO FlowDTO
     * @return success
     * @throws SQLException
     */
    public boolean approveOrder(FlowDTO flowDTO,String ApproveUser,String sectionRigth) throws SQLException {
        boolean success = false;
        boolean autoCommit = false;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            AmsBarcodePrintDTO tempDTO = (AmsBarcodePrintDTO) getDTOParameter();
            if(sectionRigth.equals("1")){     //��ǩ������
            tempDTO.setApproveUser(StrUtil.strToInt(ApproveUser));
            tempDTO.setApproveDate(CalendarUtil.getCurrDate());
//            setDTOParameter(tempDTO);                                                           //����dto
            super.updateData();
            }else if(sectionRigth.equals("2")){  //��ǩ��ӡ��
             tempDTO.setPrintUser(StrUtil.strToInt(ApproveUser));
             tempDTO.setPrintDate(CalendarUtil.getCurrDate());
             super.updateData();
            }else if(sectionRigth.equals("3")){  //��ǩ������
             tempDTO.setStatus(2);   //״̬:�����
              super.updateData();
            }else if(sectionRigth.equals("4")){  //��ǩ�����ˣ��˻أ�
             tempDTO.setStatus(1);      //״̬:���˻�
               super.updateData();
            }


            //���̴���
            FlowAction fa = new FlowAction(conn, flowDTO);
            fa.flow();
            conn.commit();
            success = true;
        } catch (SQLException e) {
            prodMessage("SQL_ERROR");
            Logger.logError(e);
            conn.rollback();
        } catch (QueryException e) {
            prodMessage("SQL_ERROR");
            Logger.logError(e);
            conn.rollback();
        } catch (DataHandleException e) {
            prodMessage("SQL_ERROR");
            Logger.logError(e);
            conn.rollback();
        } catch (CalendarException e) {
            prodMessage("SQL_ERROR");
            Logger.logError(e);
            conn.rollback();
        } finally {
            conn.setAutoCommit(autoCommit);
        }
        return success;
    }


      /**
     * �˻�
     * @param flowDTO FlowDTO
     * @throws SQLException
     */
    public void reject(FlowDTO flowDTO) throws SQLException {
        try {
            conn.setAutoCommit(false);
            //ҵ����
//             AmsBarcodePrintDTO tmpDTO = (AmsBarcodePrintDTO) getDTOParameter();
//            tmpDTO.setEnabled("N");                                                          //���� ENABLED
//            setDTOParameter(tmpDTO);                                                           //����dto
//            super.updateData();
            //���̴���

            //
            FlowAction fb = new FlowAction(conn, flowDTO);
            fb.reject2Begin();
            fb.reject();
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}