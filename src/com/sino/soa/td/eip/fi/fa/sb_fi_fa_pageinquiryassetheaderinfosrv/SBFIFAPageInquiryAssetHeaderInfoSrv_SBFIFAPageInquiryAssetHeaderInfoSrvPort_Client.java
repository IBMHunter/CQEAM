
package com.sino.soa.td.eip.fi.fa.sb_fi_fa_pageinquiryassetheaderinfosrv;

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
 * Fri Oct 14 14:43:02 CST 2011
 * Generated source version: 2.1.4
 * 
 */

public final class SBFIFAPageInquiryAssetHeaderInfoSrv_SBFIFAPageInquiryAssetHeaderInfoSrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/fi/SB_FI_FA_PageInquiryAssetHeaderInfoSrv", "SB_FI_FA_TDPageInquiryAssetHeaderInfoSrv");

    private SBFIFAPageInquiryAssetHeaderInfoSrv_SBFIFAPageInquiryAssetHeaderInfoSrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBFIFATDPageInquiryAssetHeaderInfoSrv.WSDL_LOCATION;
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
      
        SBFIFATDPageInquiryAssetHeaderInfoSrv ss = new SBFIFATDPageInquiryAssetHeaderInfoSrv(wsdlURL, SERVICE_NAME);
        SBFIFAPageInquiryAssetHeaderInfoSrv port = ss.getSBFIFAPageInquiryAssetHeaderInfoSrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.td.eip.fi.fa.sb_fi_fa_pageinquiryassetheaderinfosrv.PageInquiryAssetHeaderInfoSrvRequest _process_payload = null;
        com.sino.soa.td.eip.fi.fa.sb_fi_fa_pageinquiryassetheaderinfosrv.PageInquiryAssetHeaderInfoSrvResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}
