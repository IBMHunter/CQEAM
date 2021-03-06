
/*
 * 
 */

package com.sino.soa.td.eip.fi.fa.sb_fi_fa_inquiryassetperiodstatussrv;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

import com.sino.soa.common.SOAConstant;

/**
 * This class was generated by Apache CXF 2.1.4
 * Tue Sep 06 19:21:39 CST 2011
 * Generated source version: 2.1.4
 * date:2011-09-08
 * DSC:查询资产会计状态_TD(服务)
 */


@WebServiceClient(name = "SB_FI_FA_TDInquiryAssetPeriodStatusSrv", 
                  wsdlLocation = "http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDInquiryAssetPeriodStatusSrv/1.0/SB_FI_FA_TDInquiryAssetPeriodStatusSrv?wsdl",
                  targetNamespace = "http://eip.zte.com/common/fa/SB_FI_FA_InquiryAssetPeriodStatusSrv") 
public class SBFIFATDInquiryAssetPeriodStatusSrv extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://eip.zte.com/common/fa/SB_FI_FA_InquiryAssetPeriodStatusSrv", "SB_FI_FA_TDInquiryAssetPeriodStatusSrv");
    public final static QName SBFIFAInquiryAssetPeriodStatusSrvPort = new QName("http://eip.zte.com/common/fa/SB_FI_FA_InquiryAssetPeriodStatusSrv", "SB_FI_FA_InquiryAssetPeriodStatusSrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDInquiryAssetPeriodStatusSrv/1.0/SB_FI_FA_TDInquiryAssetPeriodStatusSrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from "+"http://"+ SOAConstant.SERVER_NAME+":"+SOAConstant.SERVER_PORT+"/orabpel/td/SB_FI_FA_TDInquiryAssetPeriodStatusSrv/1.0/SB_FI_FA_TDInquiryAssetPeriodStatusSrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SBFIFATDInquiryAssetPeriodStatusSrv(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBFIFATDInquiryAssetPeriodStatusSrv(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBFIFATDInquiryAssetPeriodStatusSrv() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SBFIFAInquiryAssetPeriodStatusSrv
     */
    @WebEndpoint(name = "SB_FI_FA_InquiryAssetPeriodStatusSrvPort")
    public SBFIFAInquiryAssetPeriodStatusSrv getSBFIFAInquiryAssetPeriodStatusSrvPort() {
        return super.getPort(SBFIFAInquiryAssetPeriodStatusSrvPort, SBFIFAInquiryAssetPeriodStatusSrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBFIFAInquiryAssetPeriodStatusSrv
     */
    @WebEndpoint(name = "SB_FI_FA_InquiryAssetPeriodStatusSrvPort")
    public SBFIFAInquiryAssetPeriodStatusSrv getSBFIFAInquiryAssetPeriodStatusSrvPort(WebServiceFeature... features) {
        return super.getPort(SBFIFAInquiryAssetPeriodStatusSrvPort, SBFIFAInquiryAssetPeriodStatusSrv.class, features);
    }

}
