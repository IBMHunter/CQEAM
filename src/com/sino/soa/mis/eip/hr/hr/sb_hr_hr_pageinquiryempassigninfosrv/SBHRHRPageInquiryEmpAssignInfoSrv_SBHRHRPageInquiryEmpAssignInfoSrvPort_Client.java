
package com.sino.soa.mis.eip.hr.hr.sb_hr_hr_pageinquiryempassigninfosrv;

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
 * Wed Sep 07 15:14:51 CST 2011
 * Generated source version: 2.1.4
 * 
 */

public final class SBHRHRPageInquiryEmpAssignInfoSrv_SBHRHRPageInquiryEmpAssignInfoSrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/common/fi/SB_HR_HR_PageInquiryEmpAssignInfoSrv", "SB_HR_HR_PageInquiryEmpAssignInfoSrv");

    private SBHRHRPageInquiryEmpAssignInfoSrv_SBHRHRPageInquiryEmpAssignInfoSrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBHRHRPageInquiryEmpAssignInfoSrv_Service.WSDL_LOCATION;
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
      
        SBHRHRPageInquiryEmpAssignInfoSrv_Service ss = new SBHRHRPageInquiryEmpAssignInfoSrv_Service(wsdlURL, SERVICE_NAME);
        SBHRHRPageInquiryEmpAssignInfoSrv port = ss.getSBHRHRPageInquiryEmpAssignInfoSrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.mis.eip.hr.hr.sb_hr_hr_pageinquiryempassigninfosrv.InquiryEmpAssignInfoSrvRequest _process_payload = null;
        com.sino.soa.mis.eip.hr.hr.sb_hr_hr_pageinquiryempassigninfosrv.InquiryEmpAssignInfoSrvResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}