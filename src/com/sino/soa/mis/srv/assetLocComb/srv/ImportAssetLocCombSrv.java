package com.sino.soa.mis.srv.assetLocComb.srv;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;

import com.sino.base.exception.CalendarException;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.*;
import com.sino.soa.mis.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ImportVSetValueInfoSrvInputItem;
import com.sino.soa.mis.srv.valueinfo.srv.SBSYSYImportVSetValueInfoSrv;
import com.sino.soa.util.XMLGregorianCalendarUtil;

/**
 * date��2011-09-16
 * user��wangzhipeng
 * function���ʲ��ص������������
 */

public final class ImportAssetLocCombSrv {

    private List<BImportAssetLocCombSrvInputItem> srvInputItems = null;
    private SrvReturnMessage returnMessage=new SrvReturnMessage();
    private List<ResponseItem> responseItemList = null;
    private List<ErrorItem> errorItemList = null; 
    
	private static final QName SERVICE_NAME = new QName("http://eip.zte.com/SB_FI_FA_BImportAssetLocCombSrv", "SB_FI_FA_BImportAssetLocCombSrv");
	public ImportAssetLocCombSrv(){	}
	//�ӿ�ʵ��
	 public void excute() {
		 URL wsdlURL = SBFIFABImportAssetLocCombSrv_Service.WSDL_LOCATION;
		 SBFIFABImportAssetLocCombSrv_Service ss = new SBFIFABImportAssetLocCombSrv_Service(wsdlURL, SERVICE_NAME);
	     SBFIFABImportAssetLocCombSrv port = ss.getSBFIFABImportAssetLocCombSrvPort();  
	       
	     System.out.println("SOAִ��begin.....");
	     BImportAssetLocCombSrvRequest _process_payload=  null;
	     _process_payload=  new  BImportAssetLocCombSrvRequest();   //request���ڴ���
	     BImportAssetLocCombSrvInputCollection colle= new BImportAssetLocCombSrvInputCollection();
	  
	     MsgHeader msgHeader =new MsgHeader();
	     _process_payload.setMsgHeader(msgHeader);
	     if(srvInputItems!=null){
	    	 int list1= srvInputItems.size();
		     for(int i=0;i<list1;i++){
		    	 BImportAssetLocCombSrvInputItem inputitem = srvInputItems.get(i);
                 colle.getBImportAssetLocCombSrvInputItem().add(inputitem);
		     }
	     }
	     //�������ݼ��ϵ�request��
	     _process_payload.setBImportAssetLocCombSrvInputCollection(colle);
	     
	     BImportAssetLocCombSrvResponse _process__return = port.process(_process_payload);   //���ؽ��
	     System.out.println("SOAд��ص���:"+_process__return.getErrorFlag()+" "+_process__return.getErrorMessage());
	     if(_process__return.getErrorFlag().equals("Y")){
             returnMessage.setErrorFlag(_process__return.getErrorFlag());
             returnMessage.setErrorMessage(_process__return.getErrorMessage());
	    	 responseItemList = _process__return.getResponseCollecion().getResponseItem();
	    	 _process__return.getResponseCollecion();    //
	    	 for(int i=0;i<responseItemList.size();i++){
	    		String request_id = responseItemList.get(i).getREQUESTID();
	    		String record = responseItemList.get(i).getRECORDNUMBER();
	    		 System.out.println(" "+request_id+" "+record);
	    	 }
	    	 System.out.println("���OK: "+ responseItemList);
	     }else{
	    	 errorItemList = _process__return.getErrorCollection().getErrorItem();
	    	 _process__return.getErrorCollection();
	    	 int s1= errorItemList.size();
             for(int i=0;i<s1;i++){
             	System.out.println("���N: "+errorItemList.get(0).getERRORMESSAGE());
             }
	     }
	 }
	
		public List<BImportAssetLocCombSrvInputItem> getSrvInputItems() {
			return srvInputItems;
		}

		public void setSrvInputItems(List<BImportAssetLocCombSrvInputItem> srvInputItems) {
			this.srvInputItems = srvInputItems;
		}

		public SrvReturnMessage getReturnMessage() {
			return returnMessage;
		}

		public void setReturnMessage(SrvReturnMessage returnMessage) {
			this.returnMessage = returnMessage;
		}

		public List<ResponseItem> getResponseItemList() {
			return responseItemList;
		}

		public void setResponseItemList(List<ResponseItem> responseItemList) {
			this.responseItemList = responseItemList;
		}

		public List<ErrorItem> getErrorItemList() {
			return errorItemList;
		}

		public void setErrorItemList(List<ErrorItem> errorItemList) {
			this.errorItemList = errorItemList;
		}
		
	public static void main(String args[]) throws CalendarException, DatatypeConfigurationException{
		/**
		 * ����ʱ�����ȵ���ֵ�������ܵ���ص� (��ֵ�����û��flexvalue ֵ�� ���ܵ���ص�)
		 */
		 SBSYSYImportVSetValueInfoSrv srv = new SBSYSYImportVSetValueInfoSrv();
	        List<ImportVSetValueInfoSrvInputItem> inputItems = new ArrayList<ImportVSetValueInfoSrvInputItem>();
	        ImportVSetValueInfoSrvInputItem inputItem = new ImportVSetValueInfoSrvInputItem();
	        inputItem.setPRIKEY("TEST002");
	        inputItem.setFLEXVALUESETNAME("CMCC_FA_LOC_2");      //ֵ����
	        inputItem.setVALIDATIONTYPE("I");
	        inputItem.setFLEXVALUE("2521JZ00061994");            //ֵ��ֵ code
	        inputItem.setDESCRIPTION("2521JZ00061994 ");         //name �����仪ɽ��ѩ��XAM208
	        inputItem.setENABLEDFLAG("Y");
	        inputItem.setSUMMARYFLAG("N");
	        inputItem.setPARENTFLEXVALUE("230000");             //�ص��һ��  230000
	        inputItem.setCREATEDBY(new BigDecimal(2750));
        inputItems.add(inputItem);
        srv.setImportVSetValueInfoSrvInputItems(inputItems);
        srv.excute();

		ImportAssetLocCombSrv srv1 = new ImportAssetLocCombSrv();
		List<BImportAssetLocCombSrvInputItem> List1 =new ArrayList<BImportAssetLocCombSrvInputItem>();
		BImportAssetLocCombSrvInputItem  item= new BImportAssetLocCombSrvInputItem();
		 item.setPRIKEY("TEST002");
		 item.setSEGMENT1("230000");     // 230000
		 item.setSEGMENT2("2521JZ00061994");  //4123XLDLSQ0606
		 item.setSEGMENT3("000");     //000
		 item.setENABLEDFLAG("Y");
//		 item.setCREATEDBY(new BigDecimal(2750)); //�Ƶ���ID 2750
		item.setEMPLOYEENUMBER("88000059");      //�Ƶ���Ա������
		 item.setRESPONSIBILITYID(new BigDecimal(50462)); //����ְ��ID50462
		 item.setRESPONSIBILITYNAME("");//����ְ������tem
		 item.setSTARTDATEACTIVE(XMLGregorianCalendarUtil.getXMLGregorianCalendar(""));  //2000-01-01
		 item.setENDDATEACTIVE(XMLGregorianCalendarUtil.getXMLGregorianCalendar(""));
		 List1.add(item);
		srv1.setSrvInputItems(List1);
		srv1.excute();
		
	} 	
		
}
