
package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_inquiryassettagnumbersrv;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;

/**
 * This class was generated by Apache CXF 2.1.4
 * Tue Oct 18 10:01:20 CST 2011
 * Generated source version: 2.1.4
 * 
 */

public final class SBFIFAInquiryAssetTagNumberSrv_SBFIFAInquiryAssetTagNumberSrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://mss.cmcc.com/SB_FI_FA_InquiryAssetTagNumberSrv", "SB_FI_FA_InquiryAssetTagNumberSrv");

    private SBFIFAInquiryAssetTagNumberSrv_SBFIFAInquiryAssetTagNumberSrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBFIFAInquiryAssetTagNumberSrv_Service.WSDL_LOCATION;
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
      
        SBFIFAInquiryAssetTagNumberSrv_Service ss = new SBFIFAInquiryAssetTagNumberSrv_Service(wsdlURL, SERVICE_NAME);
        SBFIFAInquiryAssetTagNumberSrv port = ss.getSBFIFAInquiryAssetTagNumberSrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.mis.eip.fi.fa.sb_fi_fa_inquiryassettagnumbersrv.SBFIFAInquiryAssetTagNumberSrvRequest _process_payload = null;
        com.sino.soa.mis.eip.fi.fa.sb_fi_fa_inquiryassettagnumbersrv.SBFIFAInquiryAssetTagNumberSrvResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}
