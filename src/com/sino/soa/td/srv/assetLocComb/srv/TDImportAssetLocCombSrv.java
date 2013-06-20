package com.sino.soa.td.srv.assetLocComb.srv;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.td.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ImportVSetValueInfoSrvInputItem;
import com.sino.soa.td.srv.valueinfo.srv.SBSYSYTdInquiryVSetValueInfoSrv;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.BImportAssetLocCombSrvInputCollection;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.BImportAssetLocCombSrvInputItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.BImportAssetLocCombSrvRequest;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.BImportAssetLocCombSrvResponse;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.ErrorItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.MsgHeader;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.ResponseItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.SBFIFABImportAssetLocCombSrv;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.SBFIFATDBImportAssetLocCombSrv;
import com.sino.soa.td.srv.valueinfo.srv.SBSYSYTdImportVSetValueInfoSrv;

/**
 * date��2011-09-16
 * user��wangzhipeng
 * function���ʲ��ص������������_TD
 */
public final class TDImportAssetLocCombSrv {

	public TDImportAssetLocCombSrv(){
		
	}
	private List<BImportAssetLocCombSrvInputItem> srvInputItems = null;
    private SrvReturnMessage returnMessage=new SrvReturnMessage();
    private List<ResponseItem> responseItemList = null;
    private List<ErrorItem> errorItemList = null; 
    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/SB_FI_FA_BImportAssetLocCombSrv", "SB_FI_FA_TDBImportAssetLocCombSrv");
	//�ӿ�ʵ��
	 public void excute() {
		 URL wsdlURL = SBFIFATDBImportAssetLocCombSrv.WSDL_LOCATION;
		 SBFIFATDBImportAssetLocCombSrv ss = new SBFIFATDBImportAssetLocCombSrv(wsdlURL, SERVICE_NAME);
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
	    	 for(int i=0;i<responseItemList.size();i++){
	    		String request_id = responseItemList.get(i).getREQUESTID();
	    		String record = responseItemList.get(i).getRECORDNUMBER();
	    		 System.out.println(" "+request_id+" "+record);
	    	 }
	    	 System.out.println("���OK: "+ responseItemList);
	     }else{
             returnMessage.setErrorFlag(_process__return.getErrorFlag());
             returnMessage.setErrorMessage(_process__return.getErrorMessage()); 
	    	 errorItemList = _process__return.getErrorCollection().getErrorItem();
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
		
	
	public static void main(String args[]){
		
		/**
		 * ����ʱ�����ȵ���ֵ�������ܵ���ص� (��ֵ�����û��flexvalue ֵ�� ���ܵ���ص�)
		 */
		SBSYSYTdImportVSetValueInfoSrv srv1 = new SBSYSYTdImportVSetValueInfoSrv();
		 List<ImportVSetValueInfoSrvInputItem> inputItems1 = new ArrayList<ImportVSetValueInfoSrvInputItem>();  //ֵ��
	        ImportVSetValueInfoSrvInputItem inputItem = new ImportVSetValueInfoSrvInputItem();
	        inputItem.setPRIKEY("TEST002");
	        inputItem.setFLEXVALUESETNAME("CMCC_FA_LOC_2");      //ֵ����
	        inputItem.setVALIDATIONTYPE("I");
	        inputItem.setFLEXVALUE("JZ53762");            //ֵ��ֵ code
	        inputItem.setDESCRIPTION("JZ53762 ");         //name �����仪ɽ��ѩ��XAM208
	        inputItem.setENABLEDFLAG("Y");
	        inputItem.setSUMMARYFLAG("N");
	        inputItem.setPARENTFLEXVALUE("890000");             //�ص��һ��  230000
	        inputItem.setEMPLOYEENUMBER("80000000");
            inputItems1.add(inputItem);
        srv1.setImportVSetValueInfoSrvInputItems(inputItems1);     //setImportVSetValueInfoSrvInputItems();
        srv1.execute();
        
        
		TDImportAssetLocCombSrv srv = new TDImportAssetLocCombSrv();	
		List<BImportAssetLocCombSrvInputItem> List1 =new ArrayList<BImportAssetLocCombSrvInputItem>();
		BImportAssetLocCombSrvInputItem  item= new BImportAssetLocCombSrvInputItem();
		 item.setPRIKEY("111");
		 item.setSEGMENT1("890000");
		 item.setSEGMENT2("JZ53762");
		 item.setSEGMENT3("000");
		 item.setENABLEDFLAG("Y");
//		 item.setCREATEDBY(new BigDecimal(2750)); //�Ƶ���ID 2750
		 item.setEMPLOYEENUMBER("81000000"); //�Ƶ���
		 item.setRESPONSIBILITYID(new BigDecimal(50462)); //����ְ��ID50462
		 item.setRESPONSIBILITYNAME("");//����ְ������tem
		 List1.add(item);
		srv.setSrvInputItems(List1);
		srv.excute();

	} 	
		
}
