package com.sino.ams.apd.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.sino.ams.apd.dto.AmsAssetsCheckByYrLineDTO;
import com.sino.ams.apd.dto.AmsAssetsCheckOrderDTO;
import com.sino.ams.apd.model.AmsAssetsCheckByYrLineModel;
import com.sino.ams.apd.model.AmsAssetsCheckByYrModel;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

public class AmsAssetsCheckByYrLineDAO extends AMSBaseDAO{

	public AmsAssetsCheckByYrLineDAO(SfUserDTO userAccount, AmsAssetsCheckByYrLineDTO dtoParameter,
			Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	@Override
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsCheckByYrLineDTO dtoPara = (AmsAssetsCheckByYrLineDTO) dtoParameter;
		sqlProducer = new AmsAssetsCheckByYrLineModel((SfUserDTO) userAccount,dtoPara);
	}
	
	//�����û���Ϣ��ȡ�����
	public AmsAssetsCheckByYrLineDTO getTraskUserModel(Connection conn) throws QueryException {
		   DTOSet dtoSet=new DTOSet();
		   AmsAssetsCheckByYrLineDTO lineDTO=new AmsAssetsCheckByYrLineDTO();
		   AmsAssetsCheckByYrLineModel modelProducer = (AmsAssetsCheckByYrLineModel) sqlProducer;
		   SQLModel sqlModel = modelProducer.getTraskUserModel();
	       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
		   simp.setDTOClassName(AmsAssetsCheckByYrLineDTO.class.getName());
		   simp.executeQuery();
		   dtoSet=simp.getDTOSet();
		   if(dtoSet.getSize()>0){
			   lineDTO=(AmsAssetsCheckByYrLineDTO) dtoSet.getDTO(0);
		   }
		return lineDTO;
	}
	
	
	  public void saveWorkTask(DTOSet deptLines){
	        boolean autoCommit = true;
	    	try {
	    		autoCommit = conn.getAutoCommit();
	            conn.setAutoCommit(false);
	            preparePd();
	    	    saveHeader();//�޸��б���Ϣ���̵㹤��״̬���̵��׼�ա�
	    	    saveLine(deptLines);
	    	 } catch (DataHandleException e) {
				e.printStackTrace();
			} catch (CalendarException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
	            try {
//	                if (!operateResult) {
//	                    conn.rollback();
//	                    prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_FAILURE);
//	                } else {
	                    conn.commit();
	                    prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_SUCCESS);
//	                }
	                conn.setAutoCommit(autoCommit);
	                message.addParameterValue("�̵����񱣴�");
//	                message.setIsError(!operateResult);
	            } catch (SQLException ex) {
	                Logger.logError(ex);
	                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
	            }
	        }
	    	
	    	
	    }
	  
	  
	    
	    public void saveHeader() throws CalendarException, DataHandleException{
	    	AmsAssetsCheckByYrLineDTO dtoPara = (AmsAssetsCheckByYrLineDTO) dtoParameter;
	        dtoPara.setTransStatus("SAVE_ORDER");
	        dtoPara.setTransStatusValue("�����̵㹤��");
	        
	        AmsAssetsCheckByYrLineModel model = (AmsAssetsCheckByYrLineModel) sqlProducer;
	        SQLModel sqlModel = new SQLModel();
	        sqlModel=model.updateLineModel(dtoPara);
		    DBOperator.updateRecord(sqlModel, conn);
	    }
	    
	    public void saveLine(DTOSet deptLines) throws DataHandleException{
	    	AmsAssetsCheckByYrLineDTO dtoPara = (AmsAssetsCheckByYrLineDTO) dtoParameter;
	    	String transId = dtoPara.getLineId();
		   	if(deptLines == null){
		         return;
			}else {
		        for(int i = 0; i < deptLines.getSize(); i++) {
		        	AmsAssetsCheckOrderDTO  line = (AmsAssetsCheckOrderDTO)deptLines.getDTO(i);
		        	line.setTransId(transId);
		        	AmsAssetsCheckByYrLineModel model = (AmsAssetsCheckByYrLineModel) sqlProducer;
		            SQLModel sqlModel = new SQLModel();
		            sqlModel=model.getWorkOrderInsert(line);
				    DBOperator.updateRecord(sqlModel, conn);
		        }
			}
	    }
	    
	   //ͬ���ӿ�
       //����
	    public void preparePd() throws DataHandleException, CalendarException{
		    AmsAssetsCheckByYrLineDTO dtoPara = (AmsAssetsCheckByYrLineDTO) dtoParameter;
		    AmsAssetsCheckByYrLineModel model = (AmsAssetsCheckByYrLineModel) sqlProducer;
		    //ͬ���ӿ�
	        SQLModel sqlModel = new SQLModel();
	        sqlModel=model.getEtsItemInfoPh();
	        DBOperator.updateRecord(sqlModel, conn);
	    }
	    
	    
		  public void sendWorkTask(DTOSet deptLines){
		        boolean autoCommit = true;
		    	try {
		    		autoCommit = conn.getAutoCommit();
		            conn.setAutoCommit(false);
		            preparePd();
		            updateHeader();//�޸��б���Ϣ���̵㹤��״̬���̵��׼�ա�
		    	    //saveLine(deptLines);
		    	 } catch (DataHandleException e) {
					e.printStackTrace();
				} catch (CalendarException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
		            try {
//		                if (!operateResult) {
//		                    conn.rollback();
//		                    prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_FAILURE);
//		                } else {
		                    conn.commit();
		                    prodMessage(AssetsMessageKeys.ASSETS_TRANSFER_SUCCESS);
//		                }
		                conn.setAutoCommit(autoCommit);
		                message.addParameterValue("�̵������·�");
//		                message.setIsError(!operateResult);
		            } catch (SQLException ex) {
		                Logger.logError(ex);
		                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
		            }
		        }
		    	
		    	
		    }
		  
		  public void updateHeader() throws CalendarException, DataHandleException{
		    	AmsAssetsCheckByYrLineDTO dtoPara = (AmsAssetsCheckByYrLineDTO) dtoParameter;
		        dtoPara.setTransStatus("SEND_ORDER");
		        dtoPara.setTransStatusValue("�·��̵㹤��");
		        
		        AmsAssetsCheckByYrLineModel model = (AmsAssetsCheckByYrLineModel) sqlProducer;
		        SQLModel sqlModel = new SQLModel();
		        sqlModel=model.updateLineModelStatus(dtoPara);
			    DBOperator.updateRecord(sqlModel, conn);
		    }
}
