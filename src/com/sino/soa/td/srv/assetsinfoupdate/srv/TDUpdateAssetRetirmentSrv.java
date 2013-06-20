package com.sino.soa.td.srv.assetsinfoupdate.srv;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import com.sino.base.util.StrUtil;
import com.sino.soa.common.SrvReturnMessage;
import javax.xml.namespace.QName;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.ResponseItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.UpdateAssetRetirmentSrvInputItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.SBFIFATDUpdateAssetRetirmentSrv;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.SBFIFAUpdateAssetRetirmentSrv;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.UpdateAssetRetirmentSrvRequest;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.UpdateAssetRetirmentSrvResponse;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.ErrorItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.UpdateAssetRetirmentSrvInputCollection;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.ResponseCollecion;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.MsgHeader;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
//import com.sino.soa.td.eip.fi.fa.sb_fi_fa_updateassetretirmentsrv.SBFIFAUpdateAssetRetirmentSrv_SBFIFAUpdateAssetRetirmentSrvPort_Client;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
* User: wangzp
* Date: 2011-09-26
* Function:�ʲ�������Ϣ�޸�_�ӿ�ʵ����
 */

public final class TDUpdateAssetRetirmentSrv {

    private SrvReturnMessage returnMessage = new SrvReturnMessage();
    private List<ErrorItem> errorItemList = null;
    private List<UpdateAssetRetirmentSrvInputItem> srvInputItems=  null ;
    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/SB_FI_FA_UpdateAssetRetirmentSrv", "SB_FI_FA_TDUpdateAssetRetirmentSrv");

    public TDUpdateAssetRetirmentSrv() {
    }

    public void excute() {
        URL wsdlURL = SBFIFATDUpdateAssetRetirmentSrv.WSDL_LOCATION;

        SBFIFATDUpdateAssetRetirmentSrv ss = new SBFIFATDUpdateAssetRetirmentSrv(wsdlURL, SERVICE_NAME);
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
            System.out.println("���N:"+items.get(0).getENTITYNAME()+" "+items.get(0).getERRORMESSAGE());
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
        TDUpdateAssetRetirmentSrv srv = new TDUpdateAssetRetirmentSrv();
        List<UpdateAssetRetirmentSrvInputItem> list1=  new  ArrayList<UpdateAssetRetirmentSrvInputItem>();
        UpdateAssetRetirmentSrvInputItem dto = new UpdateAssetRetirmentSrvInputItem();
        dto.setPRIKEY("4110-10001522");
        dto.setBOOKTYPECODE("SXMC_FA_8110");            //�ʲ��ʲ�
        dto.setTAGNUMBER("00000001");                  //��ǩ    (�������)
        dto.setDESCRIPTION("�����������ն�11");          // �ʲ�����
        dto.setMANUFACTURERNAME("����");     //��������
        dto.setSERIALNUMBER("123456");      //���
        dto.setMODELNUMBER("DELL22");       //����ͺ�
        dto.setATTRIBUTE8("att1");
        dto.setATTRIBUTE9("att2");
        dto.setATTRIBUTE10("att3");
        dto.setATTRIBUTE11("att4");
//        dto.setCREATEDBY(new BigDecimal(1069));    //�Ƶ���ID
        dto.setEMPLOYEENUMBER("41000237");    //�Ƶ���Ա������
        dto.setRESPONSIBILITYID(new BigDecimal(50897));  //����ְ��ID
        
        list1.add(dto);
       srv.setSrvInputItems(list1);
        
        srv.excute();

    }


}
