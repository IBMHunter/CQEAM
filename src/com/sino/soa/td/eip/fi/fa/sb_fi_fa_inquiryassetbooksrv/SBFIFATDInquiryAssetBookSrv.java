
/*
 * 
 */

package com.sino.soa.td.eip.fi.fa.sb_fi_fa_inquiryassetbooksrv;

import com.sino.soa.common.SOAConstant;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.1.4
 * Tue Sep 06 15:08:12 CST 2011
 * Generated source version: 2.1.4
 * 
 */


@WebServiceClient(name = "SB_FI_FA_TDInquiryAssetBookSrv", 
                  wsdlLocation = "http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDInquiryAssetBookSrv/1.0/SB_FI_FA_TDInquiryAssetBookSrv?wsdl",
                  targetNamespace = "http://eip.zte.com/common/fa/SB_FI_FA_InquiryAssetBookSrv") 
public class SBFIFATDInquiryAssetBookSrv extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://eip.zte.com/common/fa/SB_FI_FA_InquiryAssetBookSrv", "SB_FI_FA_TDInquiryAssetBookSrv");
    public final static QName SBFIFAInquiryAssetBookSrvPort = new QName("http://eip.zte.com/common/fa/SB_FI_FA_InquiryAssetBookSrv", "SB_FI_FA_InquiryAssetBookSrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDInquiryAssetBookSrv/1.0/SB_FI_FA_TDInquiryAssetBookSrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDInquiryAssetBookSrv/1.0/SB_FI_FA_TDInquiryAssetBookSrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SBFIFATDInquiryAssetBookSrv(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBFIFATDInquiryAssetBookSrv(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBFIFATDInquiryAssetBookSrv() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SBFIFAInquiryAssetBookSrv
     */
    @WebEndpoint(name = "SB_FI_FA_InquiryAssetBookSrvPort")
    public SBFIFAInquiryAssetBookSrv getSBFIFAInquiryAssetBookSrvPort() {
        return super.getPort(SBFIFAInquiryAssetBookSrvPort, SBFIFAInquiryAssetBookSrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBFIFAInquiryAssetBookSrv
     */
    @WebEndpoint(name = "SB_FI_FA_InquiryAssetBookSrvPort")
    public SBFIFAInquiryAssetBookSrv getSBFIFAInquiryAssetBookSrvPort(WebServiceFeature... features) {
        return super.getPort(SBFIFAInquiryAssetBookSrvPort, SBFIFAInquiryAssetBookSrv.class, features);
    }

}