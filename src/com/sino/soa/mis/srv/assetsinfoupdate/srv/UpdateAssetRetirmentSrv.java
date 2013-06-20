package com.sino.soa.mis.srv.assetsinfoupdate.srv;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import com.sino.base.util.StrUtil;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.*;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
* User: wangzp
* Date: 2011-09-26
* Function:�ʲ�������Ϣ�޸�_�ӿ�ʵ����
 */

public final class UpdateAssetRetirmentSrv {

    private SrvReturnMessage returnMessage = new SrvReturnMessage();
    private List<ErrorItem> errorItemList = null;
    private List<UpdateAssetRetirmentSrvInputItem> srvInputItems=  null ;
    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/SB_FI_FA_UpdateAssetRetirmentSrv", "SB_FI_FA_UpdateAssetRetirmentSrv");

    public UpdateAssetRetirmentSrv() {
    }

    public void excute() {
        URL wsdlURL = SBFIFAUpdateAssetRetirmentSrv_Service.WSDL_LOCATION;

        SBFIFAUpdateAssetRetirmentSrv_Service ss = new SBFIFAUpdateAssetRetirmentSrv_Service(wsdlURL, SERVICE_NAME);
        SBFIFAUpdateAssetRetirmentSrv port = ss.getSBFIFAUpdateAssetRetirmentSrvPort();
        Client client = ClientProxy.getClient(port);
        HTTPConduit http = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(1000000000);//����ʱ��
        httpClientPolicy.setReceiveTimeout(1000000000);//����ʱ��
        httpClientPolicy.setAllowChunking(false);
        http.setClient(httpClientPolicy);

        System.out.println("Invoking process...");
        UpdateAssetRetirmentSrvRequest _process_payload = null;
        _process_payload = new UpdateAssetRetirmentSrvRequest();  //����request
        MsgHeader msgHeader = new MsgHeader();
        _process_payload.setMsgHeader(msgHeader);
        UpdateAssetRetirmentSrvInputCollection collection =  new UpdateAssetRetirmentSrvInputCollection();
        if (srvInputItems != null) {
            for (int i = 0; i < srvInputItems.size(); i++) {
            	UpdateAssetRetirmentSrvInputItem inputItem = srvInputItems.get(i);
                collection.getUpdateAssetRetirmentSrvInputItem().add(inputItem);
            }
        }
        //�������ݼ���request��
        _process_payload.setUpdateAssetRetirmentSrvInputCollection(collection); 
                            
        UpdateAssetRetirmentSrvResponse _process__return = port.process(_process_payload);

        returnMessage.setErrorFlag(StrUtil.nullToString(_process__return.getErrorFlag()));
        returnMessage.setErrorMessage(_process__return.getErrorMessage());
        System.out.println("eee=="+returnMessage.getErrorFlag());
        if (returnMessage.getErrorFlag().equalsIgnoreCase("N")) {
            List<ErrorItem> items = _process__return.getErrorCollection().getErrorItem();
            errorItemList = items;
            if( null != items && items.size() > 0 ){
            	System.out.println("���N:"+items.get(0).getENTITYNAME()+" "+items.get(0).getERRORMESSAGE());
            }else{
            	System.out.println("���N");
            }
            
        }else{
        	ResponseCollecion   responseItemList = _process__return.getResponseCollecion();
        	List<ResponseItem> list = responseItemList.getResponseItem();
        	System.out.println("�ʲ���Ϣ���³ɹ�: ");
        	for(int i=0;i<list.size();i++){
        		 System.out.println("���ݱ��= "+list.get(i).getRECORDNUMBER()+"  ERP���ص�����ID= "+list.get(i).getREQUESTID());
        		
        	}
           
        }
        System.out.println("Invoking end...");
    }

	public List<UpdateAssetRetirmentSrvInputItem> getSrvInputItems() {
		return srvInputItems;
	}

	public void setSrvInputItems(
			List<UpdateAssetRetirmentSrvInputItem> srvInputItems) {
		this.srvInputItems = srvInputItems;
	}
    /**
     * @return the returnMessage
     */
    public SrvReturnMessage getReturnMessage() {
        return returnMessage;
    }

    /**
     * @param returnMessage the returnMessage to set
     */
    public void setReturnMessage(SrvReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }

    public List<ErrorItem> getErrorItemList() {
        return errorItemList;
    }

    public void setErrorItemList(List<ErrorItem> errorItemList) {
        this.errorItemList = errorItemList;
    }

    public static void main(String args[]) {
        UpdateAssetRetirmentSrv srv = new UpdateAssetRetirmentSrv();
        List<UpdateAssetRetirmentSrvInputItem> list1=  new  ArrayList<UpdateAssetRetirmentSrvInputItem>();
        UpdateAssetRetirmentSrvInputItem dto = new UpdateAssetRetirmentSrvInputItem();
        dto.setPRIKEY("10000");
        dto.setBOOKTYPECODE("SXMC_FA_4110");      //�ʲ��ʲ�
        dto.setTAGNUMBER("4110-10001426");  //��ǩ    (�������)
        dto.setDESCRIPTION("�ʼǱ�����");          // �ʲ�����
        dto.setMANUFACTURERNAME("DELL");     //��������   --
        dto.setSERIALNUMBER("0001");      //���          --
        dto.setMODELNUMBER("DELL01");       //����ͺ�      --
        dto.setATTRIBUTE8("arr8");
        dto.setATTRIBUTE9("arr9");
        dto.setATTRIBUTE10("arr10");
        dto.setATTRIBUTE11("arr11");
//        dto.setCREATEDBY(new BigDecimal(0));    //�Ƶ���ID
        dto.setEMPLOYEENUMBER("34514624");    //�Ƶ���Ա������
        dto.setRESPONSIBILITYID(new BigDecimal(50630));  //����ְ��ID 82
        list1.add(dto);
        srv.setSrvInputItems(list1);
        srv.excute();
    }


}
