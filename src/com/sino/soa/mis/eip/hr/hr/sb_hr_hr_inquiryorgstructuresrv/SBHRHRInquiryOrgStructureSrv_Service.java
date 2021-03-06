
/*
 * 
 */

package com.sino.soa.mis.eip.hr.hr.sb_hr_hr_inquiryorgstructuresrv;

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
 * Sun Oct 16 18:20:54 CST 2011
 * Generated source version: 2.1.4
 * 
 */


@WebServiceClient(name = "SB_HR_HR_InquiryOrgStructureSrv", 
                  wsdlLocation = "http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/hr/SB_HR_HR_InquiryOrgStructureSrv/2.0/SB_HR_HR_InquiryOrgStructureSrv?wsdl",
                  targetNamespace = "http://eip.zte.com/hr/SB_HR_HR_InquiryOrgStructureSrv") 
public class SBHRHRInquiryOrgStructureSrv_Service extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://eip.zte.com/hr/SB_HR_HR_InquiryOrgStructureSrv", "SB_HR_HR_InquiryOrgStructureSrv");
    public final static QName SBHRHRInquiryOrgStructureSrvPort = new QName("http://eip.zte.com/hr/SB_HR_HR_InquiryOrgStructureSrv", "SB_HR_HR_InquiryOrgStructureSrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/hr/SB_HR_HR_InquiryOrgStructureSrv/2.0/SB_HR_HR_InquiryOrgStructureSrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/hr/SB_HR_HR_InquiryOrgStructureSrv/2.0/SB_HR_HR_InquiryOrgStructureSrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SBHRHRInquiryOrgStructureSrv_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBHRHRInquiryOrgStructureSrv_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBHRHRInquiryOrgStructureSrv_Service() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SBHRHRInquiryOrgStructureSrv
     */
    @WebEndpoint(name = "SB_HR_HR_InquiryOrgStructureSrvPort")
    public SBHRHRInquiryOrgStructureSrv getSBHRHRInquiryOrgStructureSrvPort() {
        return super.getPort(SBHRHRInquiryOrgStructureSrvPort, SBHRHRInquiryOrgStructureSrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBHRHRInquiryOrgStructureSrv
     */
    @WebEndpoint(name = "SB_HR_HR_InquiryOrgStructureSrvPort")
    public SBHRHRInquiryOrgStructureSrv getSBHRHRInquiryOrgStructureSrvPort(WebServiceFeature... features) {
        return super.getPort(SBHRHRInquiryOrgStructureSrvPort, SBHRHRInquiryOrgStructureSrv.class, features);
    }

}
