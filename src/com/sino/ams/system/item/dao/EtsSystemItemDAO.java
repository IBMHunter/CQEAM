package com.sino.ams.system.item.dao;


import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.item.assistant.SysItemDataHelper;
import com.sino.ams.system.item.dto.EtsSysitemDistributeDTO;
import com.sino.ams.system.item.dto.EtsSystemItemDTO;
import com.sino.ams.system.item.model.EtsSystemItemModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.db.DBActionConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.util.DataUniqueChecker;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.ValidateException;
import com.sino.base.log.Logger;
import com.sino.base.util.ConvertUtil;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsSystemItemDAO</p>
 * <p>Description:�����Զ����ɷ������EtsSystemItemDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Zyun
 * @version 1.0
 */


public class EtsSystemItemDAO extends AMSBaseDAO {

    /**
	 * ���ܣ��豸�����(EAM) ETS_SYSTEM_ITEM ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsSystemItemDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public EtsSystemItemDAO(SfUserDTO userAccount, EtsSystemItemDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}


    /**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
		EtsSystemItemDTO dtoPara = (EtsSystemItemDTO)dtoParameter;
		super.sqlProducer = new EtsSystemItemModel((SfUserDTO)userAccount, dtoPara);
	}

	/**
	 * ���ܣ������豸�����(EAM)��ETS_SYSTEM_ITEM�����ݡ�
	 * @return boolean
	 */
	public boolean createData(String[] orgIds){
        boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try {
			String tableName = "ETS_SYSTEM_ITEM";
			DataUniqueChecker datChecker = new DataUniqueChecker(tableName, dtoParameter, conn);
			datChecker.setDBAction(DBActionConstant.INSERT);
            datChecker.setServletConfig(servletConfig);
//            boolean isValid = datChecker.isDataValid();
//            if(!isValid){
//                prodMessage(MsgKeyConstant.UNIQUE_ERROR);
//                getMessage().addParameterValue("�豸����");
//            } else {
                autoCommit = conn.getAutoCommit();
                conn.setAutoCommit(false);
                EtsSystemItemDTO tmpDTO = (EtsSystemItemDTO)getDTOParameter();
                tmpDTO.setItemCode(getNextItemCode());          //��ȡItemCode
                setDTOParameter(tmpDTO);                        //����dto
                super.createData();                             //��������
                DTOSet distrDatas = SysItemDataHelper.getDistriDatas(tmpDTO, orgIds);     //��ȡ
                EtsSysitemDistributeDTO tmpDTO2 = (EtsSysitemDistributeDTO)distrDatas.getDTO(0);
                EtsSysitemDistributeDAO dao = new EtsSysitemDistributeDAO(userAccount,tmpDTO2,conn);
                dao.createDistriDatas(distrDatas);                                                  //����itemcode���в������
                operateResult = true;
                conn.commit();
                hasError = false;
                getMessage().addParameterValue("�豸�����");
//            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } catch (DTOException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } catch (DataHandleException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.COMMON_ERROR);
        } catch (ValidateException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.COMMON_ERROR);
        } finally{
            try {
                if(hasError){
                    conn.rollback();                      //�ع�
                }
                conn.setAutoCommit(autoCommit);          //�ָ���ǰ״̬
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
	}

    private String getNextItemCode() throws SQLException {
        SeqProducer seqProducer = new SeqProducer(conn);
        return seqProducer.getGUID();//ConvertUtil.int2String( seqProducer.getStrNextSeq("ETS_SYSTEM_ITEM") );
    }


    /**
	 * ���ܣ������豸�����(EAM)��ETS_SYSTEM_ITEM�����ݣ���
     * @param  orgIds
	 * @return boolean
	 */
	public boolean updateData(String[] orgIds,String itemCode){
        boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try{
         	String tableName = "ETS_SYSTEM_ITEM";
			DataUniqueChecker datChecker = new DataUniqueChecker(tableName, dtoParameter, conn);
			datChecker.setDBAction(DBActionConstant.UPDATE);
            datChecker.setServletConfig(servletConfig);
//            boolean isValid = datChecker.isDataValid();
//            if(!isValid){
//                prodMessage(MsgKeyConstant.UNIQUE_ERROR);
//                getMessage().addParameterValue("�豸����");
//            } else {

                autoCommit = conn.getAutoCommit();
                conn.setAutoCommit(false);
                super.updateData();                                                              //ETS_SYSTEM_ITEM���޸Ĳ���

        //        EtsSysitemDistributeDTO DistrDTO = (EtsSysitemDistributeDTO)getDTOParameter();
        //        EtsSysitemDistributeDAO dao2 = new EtsSysitemDistributeDAO(SfUser,DistrDTO,conn);
        //        dao2.deleteData();                                                               //ETS_SYSITEM_DISTRIBUTEɾ������

                EtsSystemItemDTO tmpDTO = (EtsSystemItemDTO)getDTOParameter();                   // ��ȡ���ε�����

                DTOSet distrDatas = SysItemDataHelper.getDistriDatas(tmpDTO, orgIds);
                EtsSysitemDistributeDTO tmpDTO2 = new EtsSysitemDistributeDTO();
                EtsSysitemDistributeDAO dao = new EtsSysitemDistributeDAO(userAccount,tmpDTO2,conn);
                dao.deleteData(itemCode);
                dao.createDistriDatas(distrDatas);                                                  //����itemcode���в������
                operateResult = true;

                conn.commit();
                hasError = false;
                getMessage().addParameterValue("�豸�����");
//            }
        }catch(SQLException ex){
          Logger.logError(ex);
          prodMessage(MsgKeyConstant.SQL_ERROR);
        } catch (DTOException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.SQL_ERROR);
        } catch (DataHandleException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.COMMON_ERROR);
         } catch (ValidateException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.COMMON_ERROR);
        }finally{
           try{
              if(hasError){
                  conn.rollback();
              }
              conn.setAutoCommit(autoCommit);
           }catch(SQLException ex){
               Logger.logError(ex);
               prodMessage(MsgKeyConstant.SQL_ERROR);
           }
        }
		return operateResult;
	}

	/**
	 * ���ܣ�ʹѡ�е� ETS_SYSTEM_ITEM �е�����ʧЧ��ʹ��ETS_SYSTEM_ITEM�е�ENABLEDΪ��N����
     * @param  itemCode
	 * @return boolean
	 */
	public boolean deleteData(String itemCode){
		boolean operateResult = false;
        boolean autoCommit = false;
        boolean hasError = true;
        try{
        autoCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);
        EtsSystemItemDTO tmpDTO = (EtsSystemItemDTO)getDTOParameter();
        tmpDTO.setEnabled("N");                                                          //���� ENABLED
        setDTOParameter(tmpDTO);                                                           //����dto
        super.updateData();                                                              //ETS_SYSTEM_ITEM���޸Ĳ���
        EtsSysitemDistributeDTO tmpDTO2 = new EtsSysitemDistributeDTO();
        EtsSysitemDistributeDAO dao = new EtsSysitemDistributeDAO(userAccount,tmpDTO2,conn);
        dao.deleteData(itemCode);
        operateResult = true;
        conn.commit();
        hasError = false;
        getMessage().addParameterValue("�豸�����");
        }catch(SQLException ex){
          Logger.logError(ex);
          prodMessage(MsgKeyConstant.SQL_ERROR);
        } catch (DataHandleException ex) {
            ex.printLog();
            prodMessage(MsgKeyConstant.COMMON_ERROR);
        }finally{
           try{
              if(hasError){
                  conn.rollback();
              }
              conn.setAutoCommit(autoCommit);
           }catch(SQLException ex){
               Logger.logError(ex);
               prodMessage(MsgKeyConstant.SQL_ERROR);
           }
        }
		return operateResult;
	}

}
