package com.sino.soa.td.srv.assetretire.srv;

import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.util.XMLGregorianCalendarUtil;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ImportAssetRetirmentSrvInputItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ErrorItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ResponseItem;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.SBFIFATDImportAssetRetirmentSrv;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.SBFIFAImportAssetRetirmentSrv;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ImportAssetRetirmentSrvRequest;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ImportAssetRetirmentSrvResponse;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.MsgHeader;
//import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ImportAssetRetirmentSrvCollection;
import com.sino.soa.td.eip.fi.fa.sb_fi_fa_importassetretirmentsrv.ImportAssetRetirmentSrvInputCollection;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.namespace.QName;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Date:2011-09-18
 * User:wangzp
 * DSC: �ʲ����Ͻӿ� ʵ����_TD
 */

public final class TDImportAssetRetirmentSrv {

    private List<ImportAssetRetirmentSrvInputItem> srvInputItems = null;
    private SrvReturnMessage returnMessage=new SrvReturnMessage();
    private List<ResponseItem> responseItemList = null;
    private List<ErrorItem> errorItemList = null;
    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/SB_FI_FA_ImportAssetRetirmentSrv", "SB_FI_FA_TDImportAssetRetirmentSrv");

    public TDImportAssetRetirmentSrv() {
    }

    public void setSrvInputItems(List<ImportAssetRetirmentSrvInputItem> srvInputItems) {
        this.srvInputItems = srvInputItems;
    }

    public SrvReturnMessage getReturnMessage() {
        return returnMessage;
    }

    public List<ResponseItem> getResponseItemList() {
        return responseItemList;
    }

    public List<ErrorItem> getErrorItemList() {
        return errorItemList;
    }

    public void excute() {
        URL wsdlURL = SBFIFATDImportAssetRetirmentSrv.WSDL_LOCATION;

        SBFIFATDImportAssetRetirmentSrv ss = new SBFIFATDImportAssetRetirmentSrv(wsdlURL, SERVICE_NAME);
        SBFIFAImportAssetRetirmentSrv port = ss.getSBFIFAImportAssetRetirmentSrvPort();
        Client client = ClientProxy.getClient(port);
        HTTPConduit http = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(1000000000);//����ʱ��
        httpClientPolicy.setReceiveTimeout(1000000000);//����ʱ��
        httpClientPolicy.setAllowChunking(false);
        http.setClient(httpClientPolicy);

        {
            System.out.println("Invoking process...");
            ImportAssetRetirmentSrvRequest _process_payload = null;
            _process_payload = new ImportAssetRetirmentSrvRequest();
            MsgHeader msgHeader = new MsgHeader();
            _process_payload.setMsgHeader(msgHeader);
            ImportAssetRetirmentSrvInputCollection collection = new ImportAssetRetirmentSrvInputCollection();

            if (srvInputItems != null) {
                for (int i = 0; i < srvInputItems.size(); i++) {
                    ImportAssetRetirmentSrvInputItem inputItem = srvInputItems.get(i);
                    collection.getImportAssetRetirmentSrvInputItem().add(inputItem);
                }
            }
            //�������ݼ���request��
            _process_payload.setImportAssetRetirmentSrvInputCollection(collection);   

            ImportAssetRetirmentSrvResponse _process__return = port.process(_process_payload);
            System.out.println("process.result=" + _process__return.getErrorFlag() + "||" + _process__return.getErrorMessage());
            returnMessage.setErrorFlag(StrUtil.nullToString(_process__return.getErrorFlag()));
            returnMessage.setErrorMessage(_process__return.getErrorMessage());

            if (_process__return.getErrorFlag().equals("Y")) {
                responseItemList = _process__return.getResponseCollecion().getResponseItem();
                System.out.println("���Y: "+responseItemList);
            } else {
                errorItemList = _process__return.getErrorCollection().getErrorItem();
                int s1= errorItemList.size();
                for(int i=0;i<s1;i++){
                	System.out.println("���N: "+errorItemList.get(0).getERRORMESSAGE());
                }
            }

        }
    }
    public String toString() {
        String s = null;
        
        return s;
    }  
    
    public static void main(String[] args) throws CalendarException, DatatypeConfigurationException {
        TDImportAssetRetirmentSrv srv = new TDImportAssetRetirmentSrv();
        List<ImportAssetRetirmentSrvInputItem> inputItems = new ArrayList<ImportAssetRetirmentSrvInputItem>();

        ImportAssetRetirmentSrvInputItem inputItem = null;
        inputItem = new ImportAssetRetirmentSrvInputItem();
        inputItem.setPRIKEY("4110-10001522");
        inputItem.setBOOKTYPECODE("SXMC_FA_4110");
        inputItem.setTAGNUMBER("4110-10001522");
//            inputItem.setDESCRIPTION("");
        inputItem.setDATERRETIRED(XMLGregorianCalendarUtil.getXMLGregorianCalendar("2011-05-04"));
//            inputItem.setRETIREMENTPRORATE("");
        inputItem.setRETIREMENTTYPECODE("����");//NORMAL��TRANSFER
        inputItem.setCURRENTCOST(new BigDecimal(1869.51).setScale(2,BigDecimal.ROUND_HALF_UP));
        inputItem.setRETIREMENTCOST(new BigDecimal(609).setScale(2,BigDecimal.ROUND_HALF_UP));
//            inputItem.setUNITSRETIRED(new BigDecimal(1));
//        inputItem.setCREATEDBY(new BigDecimal(3919));
        inputItem.setEMPLOYEENUMBER("41000663");
        inputItems.add(inputItem);

        srv.setSrvInputItems(inputItems);
        srv.excute();
    }

}