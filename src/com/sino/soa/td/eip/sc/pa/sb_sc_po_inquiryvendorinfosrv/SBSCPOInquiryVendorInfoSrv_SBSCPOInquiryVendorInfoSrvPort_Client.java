
package com.sino.soa.td.eip.sc.pa.sb_sc_po_inquiryvendorinfosrv;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Wed Oct 12 21:09:08 CST 2011
 * Generated source version: 2.1.4
 * 
 */

public final class SBSCPOInquiryVendorInfoSrv_SBSCPOInquiryVendorInfoSrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://xmlns.oracle.com/SB_SC_PO_InquiryVendorInfoSrv", "SB_SC_PO_TDInquiryVendorInfoSrv");

    private SBSCPOInquiryVendorInfoSrv_SBSCPOInquiryVendorInfoSrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBSCPOTDInquiryVendorInfoSrv.WSDL_LOCATION;
        if (args.length > 0) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        SBSCPOTDInquiryVendorInfoSrv ss = new SBSCPOTDInquiryVendorInfoSrv(wsdlURL, SERVICE_NAME);
        SBSCPOInquiryVendorInfoSrv port = ss.getSBSCPOInquiryVendorInfoSrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.td.eip.sc.pa.sb_sc_po_inquiryvendorinfosrv.InquiryVendorInfoSrvRequest _process_payload = null;
        com.sino.soa.td.eip.sc.pa.sb_sc_po_inquiryvendorinfosrv.InquiryVendorInfoSrvResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}