package com.sino.ams.workorder.dao;


import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderBatchDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.dto.ZeroTurnHeaderDTO;
import com.sino.ams.workorder.dto.ZeroTurnLineDTO;
import com.sino.ams.workorder.model.ZeroTurnModel;
import com.sino.ams.workorder.util.WorkOrderUtil;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.flow.constant.FlowConstant;
import com.sino.flow.dto.FlowDTO;
import com.sino.framework.dto.BaseUserDTO;

public class ZeroTurnHeaderDAO extends AMSProcedureBaseDAO {

	
	public ZeroTurnHeaderDAO(SfUserDTO userAccount, ZeroTurnHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}
	
	
	@Override
	protected void initSQLProducer(BaseUserDTO arg0, DTO arg1) {
		 ZeroTurnHeaderDTO dtoPara = (ZeroTurnHeaderDTO) dtoParameter;
	     sqlProducer = new ZeroTurnModel((SfUserDTO) userAccount, dtoPara);
	}
	
	//��֤����������Ƿ���ȷ
	public boolean validateImport(DTOSet  dtoSet) throws QueryException{
		boolean flag=true;
		int count=0;
		DecimalFormat df = new DecimalFormat("#.##");
	    
		if (dtoSet!=null) {
			if (!dtoSet.isEmpty()) {
				for (int i = 0; i < dtoSet.getSize(); i++) {
					ZeroTurnLineDTO  lineDTO=(ZeroTurnLineDTO) dtoSet.getDTO(i);
					boolean isEt=true;
					String errorMsg="";
					
					String companyCode=lineDTO.getCompanyCode();//��˾���루���䣩
					lineDTO.setCompanyCode(companyCode);
					if(companyCode.equals("1000")){
						lineDTO.setIsTd("N");
					}else {
						lineDTO.setIsTd("Y");
					}
					isEt=checkExist(companyCode);
					if (!isEt) {
						errorMsg+="��˾����������룬����Ϊ��;<br>";
					}else
					if (getOrgId(companyCode)==0) {
						errorMsg+="��˾���벻��ȷ;<br>";
					}else {
						lineDTO.setCompanyCodeTWO(getOrgId(companyCode));
					}
					String procureCode = lineDTO.getProcureCode();// �ɹ����ţ����䣩
					procureCode = df.format(Double.parseDouble(procureCode));
					isEt=checkExist(procureCode);
					if (!isEt) {
						errorMsg+="�ɹ����ű������룬����Ϊ��;<br>";
					}else {
						lineDTO.setProcureCode(procureCode);
					}
					
					String assetsDescription = lineDTO.getAssetsDescription();// �ʲ�����
					isEt=checkExist(assetsDescription);
					if (!isEt) {
						errorMsg+="�ʲ����Ʊ������룬����Ϊ��;<br>";
					}
					
					String itemSpec = lineDTO.getItemSpec();// ����ͺţ����䣩
					isEt=checkExist(itemSpec);
					if (!isEt) {
						errorMsg+="����ͺű������룬����Ϊ��;<br>";
					}
					
					String manufacturerName = lineDTO.getManufacturerName();// ���̣����䣩
					isEt=checkExist(manufacturerName);
					if (isEt) {
//						errorMsg+="���̱������룬����Ϊ��;<br>";
						lineDTO.setManufacturerIdTWO(getManufactuerId(manufacturerName));
						lineDTO.setManufacturerId(getManufactuerId(manufacturerName));
					}
//					else{
//						lineDTO.setManufacturerIdTWO(getManufactuerId(manufacturerName));
//					}
					
					
					String contentCode=lineDTO.getContentCode();//�ʲ�Ŀ¼�����䣩
					isEt=checkExist(contentCode);
					if (!isEt) {
						errorMsg+="�ʲ�Ŀ¼�������룬����Ϊ��;<br>";
					}else {
						lineDTO.setContentName(getContentName(contentCode));
						lineDTO.setUnitOfMeasure(getUnitOfMeasure(contentCode));
					}
					
					String itemQty = lineDTO.getItemQty();// ���������䣩
					isEt=checkExist(itemQty);
					if (!isEt) {
						errorMsg+="�����������룬����Ϊ��;<br>";
					}
					
					String year = lineDTO.getYears();// ʹ�����ޣ����䣩
//					Double b=Double.parseDouble(a);
//					int c=(int) (b*100/100);
//					lineDTO.setYears(year);
//					isEt=checkExist(years);
//					if (!isEt) {
//						errorMsg+="ʹ�����ޱ������룬����Ϊ��;<br>";
//					}
					
					String price = lineDTO.getPrice();// �����䣩
					isEt=checkExist(price);
					if (!isEt) {
						errorMsg+="���������룬����Ϊ��;<br>";
					}
					
					// String startDate = null; // �������ڣ���ѡ��
					
					String opeId = lineDTO.getOpeId();// ҵ��ƽ̨�����䣩
					isEt=checkExist(opeId);
					if (!isEt) {
						errorMsg+="ҵ��ƽ̨�������룬����Ϊ��;<br>";
					} else
					if (getYwpt(opeId)==null||getYwpt(opeId)==null){
						errorMsg+="ҵ��ƽ̨����ȷ;<br>";
					}else {
						lineDTO.setOpeIdTWO(getYwpt(opeId));
					}
					
					String nleId = lineDTO.getNleId();// ������Σ����䣩
					isEt=checkExist(nleId);
					if (!isEt) {
						errorMsg+="�����α������룬����Ϊ��;<br>";
					} else if (getWlcc(nleId)==null||getWlcc(nleId)==null){
						errorMsg+="�����β���ȷ;<br>";
					}else {
						lineDTO.setNleIdTWO(getWlcc(nleId));
					}
					
					String isBulid = lineDTO.getIsBulid();// �Ƿ񹲽��豸�����䣩
					isEt=checkExist(isBulid);
					if (!isEt) {
						errorMsg+="�Ƿ񹲽��豸�������룬����Ϊ��;<br>";
					} 
					
					String responsibilityUser = lineDTO.getResponsibilityUser();// �����˱�ţ����䣩
				    responsibilityUser = df.format(Double.parseDouble(responsibilityUser));
					isEt=checkExist(responsibilityUser);
					if (!isEt) {
						errorMsg+="�ʲ�������Ա����ű������룬����Ϊ��;<br>";
					}else { 
						if (getPers(responsibilityUser)==""||getPers(responsibilityUser)==null) {
							 errorMsg+="�ʲ�������Ա����Ų���ȷ;<br>";
						}else {
						lineDTO.setResponsibilityUser(responsibilityUser);
						lineDTO.setResponsibilityUserTWO(getPers(responsibilityUser));
						lineDTO.setResponsibilityDept(getDeptName(responsibilityUser));
						lineDTO.setResponsibilityDeptTWO(getDeptCode(getDeptName(responsibilityUser)));
						}
					}
					
					String costCenterCode = lineDTO.getCostCenterCode();// �ɱ����ģ����䣩
					costCenterCode = df.format(Double.parseDouble(costCenterCode));
					isEt=checkExist(costCenterCode);
					if (!isEt) {
						errorMsg+="�ɱ����ı������룬����Ϊ��;<br>";
					} else
					if (!getCbzx(getDeptName(responsibilityUser)).equals(costCenterCode)) {
						errorMsg+="�ɱ����������β��Ų���Ӧ;<br>";
					}else {
						lineDTO.setCostCenterCode(costCenterCode);
					}
					
					String objectNo = lineDTO.getObjectNo();// �ص��ţ����䣩
					isEt=checkExist(objectNo);
					if (!isEt) {
						errorMsg+="�ص��ű������룬����Ϊ��;<br>";
					}else
					if (getAddressId(objectNo,companyCode)==""||getAddressId(objectNo,companyCode)==null) {
						errorMsg+="�ص��Ų���ȷ;<br>";
					}else {
						lineDTO.setAddressIdTWO(getAddressId(objectNo,companyCode));
						lineDTO.setAddressId(getAddressId(objectNo,companyCode));
					}
					String workorderObjectName = lineDTO.getWorkorderObjectName();// �ص����ƣ���ѡ��
//					String queryObjectName=this.getAddressName(objectNo);//�����ʲ�Ŀ¼��ѯ�ʲ�Ŀ¼����
					isEt=checkExist(workorderObjectName);
					if (!isEt) {
//						if (!workorderObjectName.trim().equals(queryObjectName.trim())) {
							errorMsg+="�ص����Ʋ���Ϊ��;<br>";
//						}
					}
					
					
					String responsibilityName=lineDTO.getResponsibilityName();//����������
					isEt=checkExist(responsibilityName);
					if (!isEt) {
						errorMsg+="�ʲ������������������룬����Ϊ��;<br>";
					}
					
					String procureType = lineDTO.getProcureType();
					isEt=checkExist(procureType);
					if (!isEt) {
						errorMsg+="�빺���������룬����Ϊ��;<br>";
					}
					
					String receiver = lineDTO.getReceiver();
					isEt=checkExist(receiver);
					if (!isEt) {
						errorMsg+="�ջ��˱������룬����Ϊ��;<br>";
					}
					
					String receiverContact = lineDTO.getReceiverContact();
					receiverContact = df.format(Double.parseDouble(receiverContact));
					isEt=checkExist(receiverContact);
					if (!isEt) {
						errorMsg+="�ջ�����ϵ��ʽ�������룬����Ϊ��;<br>";
					}else {
						lineDTO.setReceiverContact(receiverContact);
					}
					
//					String misProcureCode = lineDTO.getMisProcureCode();
//					isEt=checkExist(misProcureCode);
//					if (!isEt) {
//						errorMsg+="��������ű������룬����Ϊ��;<br>";
//					}
					
					
//					
//					
//					String contentName=lineDTO.getContentName();//�ʲ�Ŀ¼���ƣ���ѡ��
//					String queryName=this.getContentName(contentCode);//�����ʲ�Ŀ¼��ѯ�ʲ�Ŀ¼����
//					if (!contentName.equals("")) {
//						if (!contentName.trim().equals(queryName.trim())) {
//							errorMsg+="�ʲ�Ŀ¼��Ӧ���ʲ�Ŀ¼���Ʋ���ȷ;<br>";
//						}
//					}
					
//					String barCode=lineDTO.getBarcode();//�ʲ���ǩ
//					if (!barCode.equals("")) {
//						if (!isUniqueBarCode(barCode)) {
//							errorMsg+="��ǩ��"+barCode+"�Ѵ���;<br>";
//						}
//					}
					
					String unitOfMeasure = getUnitOfMeasure(contentCode);// ������λ�����䣩
					lineDTO.setUnitOfMeasure(unitOfMeasure);
					 
//					String manufacturerName = lineDTO.getManufacturerName();// ���̣����䣩
//					isEt=checkExist(manufacturerName);
//					if (!isEt) {
//						errorMsg+="���̱������룬����Ϊ��;<br>";
//					}else{
//						lineDTO.setManufacturerIdTWO(getManufactuerId(manufacturerName));
//					}
					 
					
					 
//					String responsibilityDept = lineDTO.getResponsibilityDept();// ���β��ţ����䣩
//					isEt=checkExist(responsibilityDept);
//					if (!isEt) {
//						errorMsg+="���β��ű������룬����Ϊ��;<br>";
//					}else
//					if (getDeptCode(responsibilityDept)==""||getDeptCode(responsibilityDept)==null) {
//						errorMsg+="���β��Ų���ȷ;<br>";
//					}else {
//						lineDTO.setResponsibilityDeptTWO(getDeptCode(responsibilityDept));
//					}
						
					
					// String specialityDept = lineDTO.;// רҵ���ţ���ѡ��
//					 if (lineDTO.getSpecialityDept()==null||lineDTO.getSpecialityDept()=="") {
//						 
//					}else {
//						if (getZYDetp(lineDTO.getSpecialityDept())==null||getZYDetp(lineDTO.getSpecialityDept())==null){
//							errorMsg+="רҵ���Ų���ȷ;<br>";
//						}else {
//							lineDTO.setSpecialityDeptTWO(getZYDetp(lineDTO.getSpecialityDept()));
//						}
//					}
//					
					
					
					
					// String isShare = lineDTO.;// �Ƿ����豸����ѡ��
					
					 //String lneId = lineDTO.;// �߼�����Ԫ�أ���ѡ��
					//
					
					 //String cexId = lineDTO.;// Ͷ�ʷ��ࣨ��ѡ��
//					if (lineDTO.getCexId()==null||lineDTO.getCexId()=="") {
//						
//					}else {
//						if (getTzfl(lineDTO.getCexId())==null||getTzfl(lineDTO.getCexId())==null){
//							errorMsg+="Ͷ�ʷ��಻��ȷ;<br>";
//						}else {
//							lineDTO.setCexIdTWO(getTzfl(lineDTO.getCexId()));
//						}
//					}
					
//					String assetKey1=lineDTO.getAssetKey1();
//					isEt=checkExist(assetKey1);
//					if (!isEt) {
//						errorMsg+="ҵ�����������룬����Ϊ��;<br>";
//					}
//					String assetKey2=lineDTO.getAssetKey2();
//					isEt=checkExist(assetKey2);
//					if (!isEt) {
//						errorMsg+="�ܹ�˾����������룬����Ϊ��;<br>";
//					}
//					String assetKey3=lineDTO.getAssetKey3();
//					isEt=checkExist(assetKey3);
//					if (!isEt) {
//						errorMsg+="�ܹ�˾���ñ������룬����Ϊ��;<br>";
//					}
//					String assetType=lineDTO.getAssetType();
//					isEt=checkExist(assetType);
//					if (!isEt) {
//						errorMsg+="�ʲ����ͱ������룬����Ϊ��;<br>";
//					}
//					String isDeprn=lineDTO.getIsDeprn();
//					isEt=checkExist(isDeprn);
//					if (!isEt) {
//						errorMsg+="�Ƿ��۾ɱ������룬����Ϊ��;<br>";
//					}
//					String isAdjust=lineDTO.getIsAdjust();
//					isEt=checkExist(isAdjust);
//					if (!isEt) {
//						errorMsg+="�Ƿ�̯�������������룬����Ϊ��;<br>";
//					}
//					String attribute4=lineDTO.getAttribute4();
//					isEt=checkExist(attribute4);
//					if (!isEt) {
//						errorMsg+="����״̬�������룬����Ϊ��;<br>";
//					}
//					String attribute5=lineDTO.getAttribute5();
//					isEt=checkExist(attribute5);
//					if (!isEt) {
//						errorMsg+="�ʲ���Դ�������룬����Ϊ��;<br>";
//					}
					lineDTO.setItemCode(getItemCode(contentCode,assetsDescription,itemSpec,itemQty,getOrgId(companyCode)));
					lineDTO.setCenterName(getCountyName(costCenterCode,companyCode));
					lineDTO.setpUserName(getUserName(responsibilityUser));
					if (!errorMsg.equals("")) {
						count++;
					}
					
					lineDTO.setErrorMessage(errorMsg);
				}
			}
		}
		if (count>0) {
			flag=false;
		}
		return flag;
	}
	
	//�ж��ַ����Ƿ����
	public boolean checkExist(String str){
		boolean isExist=true;
		if (str==null||str.equals("")) {
			isExist=false;
		}
		return isExist;
	}
	
	  /**
     * ���ܣ������㹺����
     * ͷ��Ϣ������Ϣ �ύ������
     */
    public boolean saveOrder(DTOSet lineSet) throws SQLModelException, ContainerException, QueryException {
        boolean result = false;
        boolean autoCommit = true;
        ZeroTurnHeaderDTO dtoPara=(ZeroTurnHeaderDTO)dtoParameter;
        boolean isNewData = dtoPara.getTransNo().equals(AssetsWebAttributes.ORDER_AUTO_PROD);
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            if(isNewData){
	            if (lineSet!=null) {
					if (lineSet.getSize()>0) {
						ZeroTurnLineDTO zdto=(ZeroTurnLineDTO) lineSet.getDTO(0);
						String barcode=zdto.getBarcode();
						if (!barcode.equals("")) {
							saveOrderHeader();
							deleteOrderLines();
							saveOrderLines(lineSet);            
						}
					}
	            }
            }
            result = processProcedure();
        } catch (DataHandleException ex) {
            result = false;
            Logger.logError(ex);
        } catch (Throwable ex) {
            result = false;
            Logger.logError(ex);
        } finally {
            try {
                if (!result) {
                    conn.rollback();
                    prodMessage("ZERO_TURN_FAILURE");
                    if (isNewData) {
                        rollbackData();
                    }
                } else {
                    conn.commit();
                    prodMessage("ZERO_TURN_SUCCESS");
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("����");
                message.setIsError(!result);
            } catch (SQLException ex) {
                result = false;
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return result;
    }
	
	 /**
     * ���ܣ��ύ�ʲ����ݣ������������ϣ�����
     *
     * @param lineSet DTOSet �ʲ�����
     * @return boolean
     */
    public boolean submitOrder(DTOSet lineSet,String orderExecuter, String orderExecuterName,String orderFiler,String orderFilerName) {
        boolean operateResult = false;
        boolean autoCommit = true;
        ZeroTurnHeaderDTO headerDTO=(ZeroTurnHeaderDTO)dtoParameter;
        String trnasStatus=headerDTO.getTransStatus();
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            //���̵�һ���ڵ�
            if (headerDTO.getSf_task_attribute1().equals("from1")&&!trnasStatus.equals("ALR_FILLING")) { 
	            saveOrderHeader();
	            deleteOrderLines();
	            saveOrderLines(lineSet);
	        //���������ڵ�
            }else {
            	//��λ�ʲ�����Ա�ַ�����
            	if (trnasStatus.equals("PRE_ASSETS")) {
            		//�޸�״̬[ҵ�����]
//            		updateZeroHeader();
            		String status="ALR_ISSUED";
            		String statusVal="���·�";
            		updateHeaderStatusByMgr(status,statusVal,orderExecuter,orderExecuterName,orderFiler,orderFilerName);
            		//�޸�ETS_ITEM_INFO״̬
            		//updateEtsItemStatus(lineSet,status);
            		updateLineStatus(status,statusVal);
				 }
            	//�����鵵�˹鵵ȷ��
            	else if(trnasStatus.equals("ALR_ISSUED")){
            		String status="ALR_FILLING";
            		String statusVal="�ѹ鵵";
            		updateHeaderStatus(status,statusVal);
            		//updateEtsItemStatus(lineSet,status);
            		updateLineStatus(status,statusVal);
            		//�ύ�ɹ�����Ա��������
            	}else if(trnasStatus.equals("ALR_FILLING")){
            		String status="TO_END";
            		String statusVal="���㹺";
            		updateHeaderStatus(status,statusVal);
            		//updateEtsItemStatus(lineSet,status);
            		updateLineStatus(status,statusVal);
            	}
            }
            operateResult = processProcedure(true);
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                    prodMessage("ZERO_TURN_FAILURE");
                } else {
                    conn.commit();
                    prodMessage("ZERO_TURN_SUCCESS");
                }
                conn.setAutoCommit(autoCommit);
                message.addParameterValue("�ύ");
                message.setIsError(!operateResult);
            } catch (SQLException ex) {
                Logger.logError(ex);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }
        return operateResult;
    }

    /**
     * ���ܣ����浥��ͷ��Ϣ
     *
     * @return String
     * @throws DataHandleException
     */
    private void saveOrderHeader() throws DataHandleException {
        try {
            ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) dtoParameter;
            String transNo = headerDTO.getTransNo();
           // headerDTO.setFromPerson(userAccount.getEmployeeNumber());
            String act = headerDTO.getAct();
            if (transNo.equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
                String transId = headerDTO.getTransId();
                //if (StrUtil.isEmpty(transId)) {
                    SeqProducer seq = new SeqProducer(conn);
                    transId = StrUtil.nullToString(seq.getGUID());
                    headerDTO.setTransId(transId);
                //}
                String companyCode = userAccount.getCompanyCode(); //���ǲ��ø÷��������¿������Ӿ���Ϊû��Ҫ
                //String transType = headerDTO.getTransType();
                
                OrderNumGenerator numberProducer = new OrderNumGenerator(conn, companyCode, "ZERO-TURN");
                headerDTO.setTransNo(numberProducer.getOrderNum());
                setDTOParameter(headerDTO);
                createData();
            } else {
                updateData();
                //deleteLines();
                //deleteReserveAssets();
            }
        } catch (SQLException ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex);
        }
    }
    
    
    /**
     * ���ܣ����浥������Ϣ
     *
     * @param lineSet DTOSet
     * @throws DataHandleException
     * @throws QueryException 
     */
    private void saveOrderLines(DTOSet lineSet) throws
            DataHandleException {
        if (lineSet != null && !lineSet.isEmpty()) {
            ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) dtoParameter;
            ZeroTurnLineDAO lineDAO = new ZeroTurnLineDAO(userAccount, null, conn);
            String transId=headerDTO.getTransId();
            for (int i = 0; i < lineSet.getSize(); i++) {
                ZeroTurnLineDTO lineData = (ZeroTurnLineDTO) lineSet.getDTO(i);
                String costCenterCode=lineData.getCostCenterCode();
                if(costCenterCode.equals("")){
                	try {
						costCenterCode=this.getCostCenterCode(lineData.getRecord());
					} catch (QueryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	lineData.setCostCenterCode(costCenterCode);
                }
                lineData.setTransId(transId);
                lineDAO.setDTOParameter(lineData);
                lineDAO.createData();
            }
        }
    }
    
    /**
     * ��ȡ������Ϣ
     */
    public DTOSet getLineData() throws QueryException {
        ZeroTurnHeaderDTO dto = (ZeroTurnHeaderDTO) dtoParameter;
        ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
        SQLModel sqlModel = model.getLineDataModel(dto);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(ZeroTurnLineDTO.class.getName());
        simp.executeQuery();
        return simp.getDTOSet();
    }
    public DTOSet getAllExcelData() throws QueryException {
        ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
        SQLModel sqlModel = model.getAllExcelData();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(ZeroTurnLineDTO.class.getName());
        simp.executeQuery();
        return simp.getDTOSet();
    }
    
    public String getObjectNo(int userId,String code) throws QueryException{
        ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
        String s="";
        SQLModel sqlModel = model.getObjectNo(userId,code);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
    }
    
    protected void prepareProcedureData() {
        ZeroTurnHeaderDTO dtoPara = (ZeroTurnHeaderDTO) dtoParameter;
        dtoPara.setPrimaryKey(dtoPara.getTransId());
        dtoPara.setOrderNo(dtoPara.getTransNo());
        dtoPara.setOrderName(dtoPara.getTransType());
    }
    
    
    public boolean isUniqueBarCode(String barCode){
    	boolean flag=false;
         try {
			ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
			SQLModel sqlModel = model.getCountBarCode(barCode);
			SimpleQuery qeuryQuery = new SimpleQuery(sqlModel, conn);
			qeuryQuery.executeQuery();
			RowSet rs = qeuryQuery.getSearchResult();
			int n = Integer.parseInt(rs.getRowValues().get(0).toString().substring(1, 2));
			if (n==0) {
				flag=true;
			}
		} catch (QueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
    }
    
    public void updateZeroHeader(){
   	 try {
   		 ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
   		 SQLModel updateModel = model.updateHeader();
		 DBOperator.updateRecord(updateModel, conn);
		} catch (DataHandleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
    
    public void updateHeaderStatus(String status,String val){
    	 try {
       		 ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       		 SQLModel updateModel = model.updateZeroHeader(status,val);
    		 DBOperator.updateRecord(updateModel, conn);
    		} catch (DataHandleException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
    
    public void updateHeaderStatusByMgr(String status,String val, String orderExecuter,String orderExecuterName,String orderFiler,String orderFilerName){
   	 try {
      		 ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
      		 SQLModel updateModel = model.updateZeroHeaderByMgr(status, val,orderExecuter,orderExecuterName,orderFiler,orderFilerName);
   		     DBOperator.updateRecord(updateModel, conn);
   		} catch (DataHandleException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   }
    
    public void updateLineStatus(String status,String statusVal){
    	 try {
       		 ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       		 SQLModel updateModel = model.updateZeroLineStatus(status,statusVal);
    		 DBOperator.updateRecord(updateModel, conn);
    		} catch (DataHandleException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
    
   public void updateEtsItemStatus(DTOSet lineSet,String status){
	   try {
			ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
			for (int i = 0; i < lineSet.getSize(); i++) {
				ZeroTurnLineDTO lineDTO=(ZeroTurnLineDTO) lineSet.getDTO(i);
				String barcode=lineDTO.getBarcode();
				SQLModel updateModel = model.updateEtsItemStatus(barcode,status);
				DBOperator.updateRecord(updateModel, conn);
			}
	   } catch (DataHandleException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   public void insertEtsItem(DTOSet lineSet){
//	   boolean fg = true;
	   try {
			ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
			for (int i = 0; i < lineSet.getSize(); i++) {
				ZeroTurnLineDTO lineDTO=(ZeroTurnLineDTO) lineSet.getDTO(i);
				lineDTO.setCreatedBy(userAccount.getUserId());
				lineDTO.setOrganizationId(userAccount.getOrganizationId());
				SQLModel updateModel = model.insertEtsItemInfo(lineDTO);
				DBOperator.updateRecord(updateModel, conn);
			}
	   } catch (DataHandleException e) {
//			fg=false;
		e.printStackTrace();
	   }
//	   return fg;
   }
   private void rollbackData() {
       ZeroTurnHeaderDTO dtoPara = (ZeroTurnHeaderDTO) dtoParameter;
       dtoPara.setTransId("");
       dtoPara.setTransNo(AssetsWebAttributes.ORDER_AUTO_PROD);
   }
   
   public void deleteOrderLines(){
       try {
			ZeroTurnHeaderDTO dto = (ZeroTurnHeaderDTO) dtoParameter;
			ZeroTurnModel modelProducer = (ZeroTurnModel) sqlProducer;
			modelProducer.setDTOParameter(dto);
			SQLModel sqlModel = modelProducer.deleteImportModel();
			DBOperator.updateRecord(sqlModel, conn);
       } catch (DataHandleException e) {
		// TODO Auto-generated catch block
    	   e.printStackTrace();
       }
   }
   
   public void rejectOrder() {
       boolean operateResult = rejectProcedure();
       if (operateResult) {
           prodMessage(AssetsMessageKeys.REJECT_ORDER_SUCCESS);
       } else {
           prodMessage(AssetsMessageKeys.REJECT_ORDER_FAILURE);
           message.setIsError(true);
       }
       ZeroTurnHeaderDTO dto = (ZeroTurnHeaderDTO) dtoParameter;
       message.addParameterValue(dto.getTransType());
       message.addParameterValue(dto.getTransNo());
   }
   
   /**
    * ���ܣ������ݴ�ĵ���
    *
    * @return boolean
    */
   public boolean cancelOrder() {
       boolean operateResult = false;
       boolean autoCommit = true;
       ZeroTurnHeaderDTO headerDTO = (ZeroTurnHeaderDTO) dtoParameter;
       try {
           autoCommit = conn.getAutoCommit();
           conn.setAutoCommit(false);
           String status=DictConstant.CANCELED;
           String ststusType="����";
           ZeroTurnModel modelProducer = (ZeroTurnModel) sqlProducer;
           SQLModel sqlModel = modelProducer.updateZeroHeader(status,ststusType);
           DBOperator.updateRecord(sqlModel, conn);
           
           SQLModel sql2=modelProducer.updateZeroLineStatus(status, ststusType);
           DBOperator.updateRecord(sql2, conn);
           
           operateResult = cancelProcedure();
       } catch (Throwable ex) {
           Logger.logError(ex);
       } finally {
           try {
               if (operateResult) {
                   conn.commit();
                   prodMessage(AssetsMessageKeys.ORDER_CANCEL_SUCCESS);
               } else {
                   conn.rollback();
                   prodMessage(AssetsMessageKeys.ORDER_CANCEL_FAILURE);
               }
               conn.setAutoCommit(autoCommit);
               message.addParameterValue(headerDTO.getTransType());
               message.setIsError(!operateResult);
           } catch (SQLException ex1) {
               Logger.logError(ex1);
               prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
           }
       }
       return operateResult;
   }
   
//    SQLModel sqlModel = model.getInserBatchModel(dto);
//    SQLModel sqlModel1 = model.getCreateWorkorderDataModel(dto);
//    SQLModel sqlModel2 = model.getInsertDtlModel(dto);
//	        isExsitVallueSet();
//		DBOperator.updateRecord(sqlModel, conn);
//		DBOperator.updateRecord(sqlModel1, conn);
//		DBOperator.updateRecord(sqlModel2, conn);
   public boolean subIn(DTOSet lineSet,SfUserDTO user,int groupId ,String groupName,String orderExecuter,String orderFiler){
	   	boolean autoCommit=false;
	   	boolean operateResult=false;
	   	ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
	    ZeroTurnHeaderDTO headerDTO=(ZeroTurnHeaderDTO)dtoParameter;
	    String trnasStatus=headerDTO.getTransStatus();
	    String transId=headerDTO.getTransId();
	    String computeDays=headerDTO.getComputeTims();//ִ������
	   	try {
	       	autoCommit = conn.getAutoCommit();
	        conn.setAutoCommit(false);
	        if (trnasStatus.equals("PRE_ASSETS")&&!computeDays.equals("")) {
	        	isExsitSegment();//����㹺����
	        	operateResult=createTmpData(lineSet, user,groupId,groupName, headerDTO, orderExecuter, orderFiler,transId);
			}else{
				operateResult=true;
			}
		} catch (DataHandleException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (QueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (operateResult) {
					conn.commit();
			        autoCommit=true;
			    } else {
			        conn.rollback();
			    }
			} catch (SQLException e) {
					e.printStackTrace();
			}
		}
   	return autoCommit;
   }
   
   
   public void isExsitSegment() throws DataHandleException, QueryException{
	   ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
	   String segment="Z9999999";
	   SQLModel existModel = model.existsProjectModel(segment);
	   SimpleQuery qeuryQuery = new SimpleQuery(existModel, conn);
	   qeuryQuery.executeQuery();
	   RowSet rs = qeuryQuery.getSearchResult();
	   int n = Integer.parseInt(rs.getRowValues().get(0).toString().substring(1, 2));
	   if (n==0) {
		    SQLModel inModel = model.insertEtsProjects(segment);
			DBOperator.updateRecord(inModel, conn);
	   }
   }
   
   public void isExsitVallueSet() throws DataHandleException, QueryException{
	   ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
	   String segment="ZERO_TURN";
	   SQLModel existModel = model.existEtsValSet(segment);
	   SimpleQuery qeuryQuery = new SimpleQuery(existModel, conn);
	   qeuryQuery.executeQuery();
	   RowSet rs = qeuryQuery.getSearchResult();
	   int n = Integer.parseInt(rs.getRowValues().get(0).toString().substring(1, 2));
	   if (n==0) {
		    SQLModel inModel = model.insertEtsValSet(segment);
			DBOperator.updateRecord(inModel, conn);
	   }
	   
	   SQLModel extModel = model.existEtsVal(segment);
	   SimpleQuery qeuryQuery2 = new SimpleQuery(extModel, conn);
	   qeuryQuery2.executeQuery();
	   RowSet rs2 = qeuryQuery2.getSearchResult();
	   int n2 = Integer.parseInt(rs2.getRowValues().get(0).toString().substring(1, 2));
	   if (n2==0) {
		    SQLModel inModel = model.insertEtsVal(segment);
			DBOperator.updateRecord(inModel, conn);
	   }
   }
   
   /**
    * ����������������ʱ��
    * @param workorderObjectNos
    * @param workorderDTO
    * @param sfUser
    * @return
    * @throws DataHandleException
    */
   public boolean createTmpData(DTOSet orderLines, SfUserDTO sfUser,int groupId,String groupName,ZeroTurnHeaderDTO headerDTO,String orderExecuter,String orderFiler,String transId) throws DataHandleException {
       boolean operatorResult = true;
		try {
			ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
			SQLModel sqlModel = null;
			List sqlModList = new ArrayList();
			EtsWorkorderDTO workorderDTO=new EtsWorkorderDTO();
			workorderDTO.setWorkorderBatch(WorkOrderUtil.getWorkorderBatchNo(conn));                           //��������
			for (int i = 0; i < orderLines.getSize(); i++) {
				ZeroTurnLineDTO dto=(ZeroTurnLineDTO) orderLines.getDTO(i);
				String record=dto.getRecord();//��ǩ��
				String costCode=this.getCostCenterCode(record);
//				String costCenterCode=dto.getCostCenterCode();                                                 //�ɱ�����
				String objectCode=dto.getObjectNo();
				String workorderNo = WorkOrderUtil.getWorkorderNo(workorderDTO.getWorkorderBatch(), conn);     //������
				workorderDTO.setWorkorderNo(workorderNo);
				workorderDTO.setCostCenterCode(costCode);                                                //�ɱ�����
			    String objeNo="";
			    objeNo=this.getObjectNo(sfUser.getOrganizationId(), objectCode);
				workorderDTO.setWorkorderObjectNo(objeNo);                                                     //��������
				workorderDTO.setImplementDays(Integer.parseInt(headerDTO.getComputeTims()));                   //ʵʩ����
				workorderDTO.setGroupId(groupId);                                                              //��������/�ӵ�����
				workorderDTO.setGroupName(groupName);                                                          
				workorderDTO.setImplementBy(Integer.parseInt(orderExecuter));                                  //����ִ����
				workorderDTO.setCheckoverBy(Integer.parseInt(orderFiler));
				workorderDTO.setPrjId("d87500c590d34fd3882dd1a73757ef0d");                                     //����ID
				workorderDTO.setResponsibilityUser(dto.getResponsibilityUser());                               //������
				workorderDTO.setOrganizationId(sfUser.getOrganizationId());                                    //��֯
				workorderDTO.setWorkorderFlag(DictConstant.WOR_STATUS_NEW);                                    //����״̬
//				workorderDTO.setRemark(remark);                                                                //��ע
				workorderDTO.setCreatedBy(sfUser.getUserId());                                                 //������
				workorderDTO.setCreationDate(dto.getCreateDate());                                             //��������
				workorderDTO.setDistributeGroup(groupId);
				workorderDTO.setWorkorderType("18");
//				workorderDTO.setWorkorderType("19");
				
				SQLModel itemModel = model.insertWorkItem(workorderDTO.getWorkorderBatch(), workorderNo, record,transId);
				DBOperator.updateRecord(itemModel, conn);
				 
				sqlModel = model.getInsertWorkorderDataModel(workorderDTO);
				sqlModList.add(sqlModel);
			}
				operatorResult = DBOperator.updateBatchRecords(sqlModList, conn);
				
				FlowDTO flowDTO = new FlowDTO();
				flowDTO.setProcName("�㹺ת�������·�����");
				flowDTO.setSessionUserId(-1);
				EtsWorkorderBatchDTO workorderBatch = new EtsWorkorderBatchDTO();
				SeqProducer sp = new SeqProducer(conn);
	            workorderBatch.setSystemid(sp.getGUID());
	            workorderBatch.setWorkorderBatch(workorderDTO.getWorkorderBatch());
	            workorderBatch.setPrjId(DictConstant.ZERO_PRJ_ID); 
//	            workorderBatch.setPrjId("d87500c590d34fd3882dd1a73757ef0d"); 
	            workorderBatch.setWorkorderType("18");
//	            workorderBatch.setWorkorderType("19");
	            workorderBatch.setStatus(0);
	            workorderBatch.setArchflag(0);
	            workorderBatch.setDistributeGroupId(groupId);
	            workorderBatch.setCreatedBy(sfUser.getUserId());
	            SQLModel inSql=model.getInserBatchModel(workorderBatch, sfUser);
	            DBOperator.updateRecord(inSql, conn);
	            
	            flowDTO.setActivity(FlowConstant.FLOW_CODE_NEXT);
	            WorkOrderUtil workOrderUtil = new WorkOrderUtil();
	            workOrderUtil.saveBatchExtends(conn, workorderBatch);
	            String  workorderNode = "PROCESS3";
	            boolean isNew=true;
	            boolean isFlowOver=true;
	            workOrderUtil.saveOrderStatus(conn, workorderBatch.getWorkorderBatch(), workorderNode);
	            workOrderUtil.saveWorkorderPorcess(conn, workorderBatch.getWorkorderBatch(), workorderNode, isNew, false, isFlowOver);
				 
				 
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (CalendarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (QueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       return operatorResult;
   }
   
   public String getContentName(String contentCode) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getContentName(contentCode);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   
   public String getAddressId(String objectNo,String companyCode) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getAddressId(objectNo,companyCode);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   
   public String getAddressName(String objectCode) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getAddressName(objectCode);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   public String getZYDetp(String objectCode) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getZYDetp(objectCode);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   public String getWlys(String objectCode) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getWlys(objectCode);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   public String getWlcc(String objectCode) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getWlcc(objectCode);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
//   public String getYwpt(String objectCode) throws QueryException{
//       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
//       String s="";
//       SQLModel sqlModel = model.getYwpt(objectCode);
//       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
//       simp.executeQuery();
//		RowSet rs = simp.getSearchResult();
//		if (rs.getSize()==1) {
//			s =rs.getRowValues().get(0).toString();
//			s=s.substring(1, s.length()-1);
//		}
//		return s;
//   }
   public String getCbzx(String objectCode) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getCbzx(objectCode);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   public String getTzfl(String objectCode) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getTzfl(objectCode);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   public String getYwpt(String objectCode) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getYwpt(objectCode);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   public String getEfvCode(String contentCode) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getEfvCode(contentCode);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   
   public String getItemCode(String contentCode,String assetsDescription,String itemSpec,String unitOfMeasure,int orgnizationId  ) throws QueryException{
	   String s="";
	   String efvCode=this.getEfvCode(contentCode);
	   s=queryItemCode(efvCode, assetsDescription, itemSpec, unitOfMeasure, orgnizationId);
	   return s;
   }
   
   public String queryItemCode(String contentCode,String assetsDescription,String itemSpec,String unitOfMeasure,int orgnizationId ){
       CallableStatement cs = null;
       String s="";
	      try {
	          String callStr = "{CALL SYN_FA_EFA_SYSTEM_ITEM(?, ?, ?, ?, ?, ?)}";
	          cs = conn.prepareCall(callStr);
	          cs.setString(1,contentCode);
	          cs.registerOutParameter(2, java.sql.Types.VARCHAR); 
	          cs.setString(3,assetsDescription);
	          cs.setString(4,itemSpec);
	          cs.setString(5,unitOfMeasure);
	          cs.setInt(6,orgnizationId);
	          cs.execute();
	          s=cs.getString(2);	          
	      } catch (SQLException ex) {
	          Logger.logError(ex);
	      } catch (Throwable ex) {
	          Logger.logError(ex);
	      } finally {
	          DBManager.closeDBStatement(cs);
	      }
	      return s;
  }

   public String getManufactuerId(String manufacturerName){
       CallableStatement cs = null;
       String s="";
	      try {
	          String callStr = "{CALL dbo.SYN_FA_EFA_MANUFACTURER(?, ?)}";
	          cs = conn.prepareCall(callStr);
	          cs.registerOutParameter(1, java.sql.Types.VARCHAR); 
	          cs.setString(2,manufacturerName);
	          cs.execute();
	          s=cs.getString(1);	          
	      } catch (SQLException ex) {
	          Logger.logError(ex);
	      } catch (Throwable ex) {
	          Logger.logError(ex);
	      } finally {
	          DBManager.closeDBStatement(cs);
	      }
	      return s;
  }
   
   public String getDeptCode(String responsibilityDept) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getRespDetp(responsibilityDept);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   
   public String getDeptName(String userId) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getDetpName(userId);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   
   public int getOrgId(String responsibilityDept) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       int s1=0;
       SQLModel sqlModel = model.checkOrgId(responsibilityDept);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
			s1=Integer.parseInt(s);
		}
		return s1;
   }
   public String getPers(String responsibilityDept) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getPers(responsibilityDept);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   public String getUserName(String responsibilityDept) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getUserName(responsibilityDept);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   
//   public String getManufacturerId(String manufacturerName) throws QueryException{
//       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
//       String s="";
//       SQLModel sqlModel = model.getManufacturerId(manufacturerName);
//       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
//       simp.executeQuery();
//		RowSet rs = simp.getSearchResult();
//		if (rs.getSize()==1) {
//			s =rs.getRowValues().get(0).toString();
//			s=s.substring(1, s.length()-1);
//		}
//		return s;
//   }
   public String getCountyName(String countyId,String code) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getcountyName(countyId,code);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   //barcode �������
   public String getBarcode(String companyCode) throws QueryException{
	   String bc="";
	   String barcode="";
	   ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       SQLModel sqlModel = model.getBarcode(companyCode);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
	   RowSet rs = simp.getSearchResult();
	   if (rs.getSize()==1) {
			bc =rs.getRowValues().get(0).toString();
			bc=bc.substring(1, bc.length()-1);
			barcode=returnStr(bc);
	   }
	   return barcode;
   }
   
   public static String returnStr(String barcode){
	   String rStr = "";
	   String firStr=barcode.substring(0, 7);
	   int index=barcode.indexOf("-");
	   String str=barcode.substring(index+3);
	   Integer codeValue=Integer.parseInt(str);
	   codeValue++;
	   rStr=firStr+String.format("%06d", codeValue); 
	   return rStr;
   }
   
   
   public DTOSet getZeroTurnDto() throws QueryException {
       ZeroTurnHeaderDTO dto = (ZeroTurnHeaderDTO) dtoParameter;
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       SQLModel sqlModel = model.getZeroTurnDto();
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.setDTOClassName(ZeroTurnLineDTO.class.getName());
       simp.executeQuery();
       return simp.getDTOSet();
   }
   
   public String getCostCenterCode(String record) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getCostCenterCode(record);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   public String getUnitOfMeasure(String zcml) throws QueryException{
       ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       String s="";
       SQLModel sqlModel = model.getUnitOfMeasure(zcml);
       SimpleQuery simp = new SimpleQuery(sqlModel, conn);
       simp.executeQuery();
		RowSet rs = simp.getSearchResult();
		if (rs.getSize()==1) {
			s =rs.getRowValues().get(0).toString();
			s=s.substring(1, s.length()-1);
		}
		return s;
   }
   public String validateFilter(String transId) throws QueryException{
	   String s="";
	   ZeroTurnModel model = (ZeroTurnModel) sqlProducer;
       SQLModel queryModel = new SQLModel();
       queryModel = model.isFilter(transId);
       SimpleQuery qeuryQuery = new SimpleQuery(queryModel, conn);
       qeuryQuery.executeQuery();
       RowSet rs = qeuryQuery.getSearchResult();
       int n = Integer.parseInt(rs.getRowValues().get(0).toString().substring(1, 2));
       if(n==0){
    	   s="0";
       }else {
    	   s="1";
       }
	   return s;
   }
   public File getExportFile(String barcode) throws DataTransException, SQLModelException {
       File file = null;
       ZeroTurnModel ztm = new ZeroTurnModel(userAccount, (ZeroTurnHeaderDTO) dtoParameter);
	   SQLModel sqlModel = ztm.getExport(barcode);
	   String reportTitle = "";

	   reportTitle = "�㹺ת������";
	   String fileName = reportTitle + ".xls";
	   String filePath = WorldConstant.USER_HOME;
	   filePath += WorldConstant.FILE_SEPARATOR;
	   filePath += fileName;
	   TransRule rule = new TransRule();
	   rule.setDataSource(sqlModel);
	   rule.setSourceConn(conn);
	   rule.setTarFile(filePath);
	   DataRange range = new DataRange();
	   rule.setDataRange(range);
	   Map fieldMap = getFieldMap();


	   rule.setFieldMap(fieldMap);
	   CustomTransData custData = new CustomTransData();
	   custData.setReportTitle(reportTitle);
	   custData.setReportPerson(userAccount.getUsername());
	   custData.setNeedReportDate(true);
	   rule.setCustData(custData);
	   rule.setCalPattern(LINE_PATTERN);
	   TransferFactory factory = new TransferFactory();
	   DataTransfer transfer = factory.getTransfer(rule);
	   transfer.transData();
	   file = (File) transfer.getTransResult();
       return file;
   }

   private Map getFieldMap() {
       Map fieldMap = new HashMap();
       fieldMap.put("MIS_PROCURE_CODE", "���������");
       fieldMap.put("PROCURE_CODE", "MIS�빺������");
       fieldMap.put("BARCODE", "��ǩ��");
       fieldMap.put("COMPANY_CODE", "��˾����");
       fieldMap.put("ASSETS_DESCRIPTION", "�ʲ�����");
       fieldMap.put("ITEM_SPEC", "����ͺ�");
       fieldMap.put("MANUFACTURER_NAME", "����");
       fieldMap.put("CONTENT_CODE", "�ʲ����");
       fieldMap.put("ITEM_QTY", "����");
       fieldMap.put("YEARS", "ʹ������");
       fieldMap.put("PRICE", "�ʲ�ԭֵ");
       fieldMap.put("START_DATE", "��������");
       fieldMap.put("OPE_ID", "ҵ��ƽ̨");
       fieldMap.put("NLE_ID", "������");
       fieldMap.put("IS_BULID", "�Ƿ񹲽�");
       fieldMap.put("COST_CENTER_CODE", "�ɱ�����");
       fieldMap.put("WORKORDER_OBJECT_NAME", "�ʲ��ص�����");
       fieldMap.put("OBJECT_NO", "�ʲ����ڵص����");
       fieldMap.put("RESPONSIBILITY_USER", "�ʲ�������Ա�����");
       fieldMap.put("RESPONSIBILITY_NAME", "�ʲ�����������");
       fieldMap.put("PROCURE_TYPE", "�빺���");
       fieldMap.put("RECEIVER", "�ջ���");
       fieldMap.put("RECEIVER_CONTACT", "�ջ�����ϵ��ʽ");
       fieldMap.put("EXPECTED_DATE", "Ԥ�Ƶ�������");

       return fieldMap;
   }
   public static void main(String[] args) {
	String a="3.0";
	Double b=Double.parseDouble(a);
	int c=(int) (b*10/10);
//	DecimalFormat df = new DecimalFormat("#.##");
//
//    String account = df.format(Double.parseDouble(a));

	System.out.println(c);
}
   
}
