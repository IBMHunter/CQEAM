
package com.sino.soa.td.eip.fi.fa.sb_fi_fa_bcreateassettransincompanysrv;

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
 * Wed Sep 14 09:43:40 CST 2011
 * Generated source version: 2.1.4
 * 
 */

public final class SBFIFABCreateAssetTransInCompanySrv_SBFIFABCreateAssetTransInCompanySrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/SB_FI_FA_BCreateAssetTransInCompanySrv", "SB_FI_FA_TDBCreateAssetTransInCompanySrv");

    private SBFIFABCreateAssetTransInCompanySrv_SBFIFABCreateAssetTransInCompanySrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBFIFATDBCreateAssetTransInCompanySrv.WSDL_LOCATION;
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
      
        SBFIFATDBCreateAssetTransInCompanySrv ss = new SBFIFATDBCreateAssetTransInCompanySrv(wsdlURL, SERVICE_NAME);
        SBFIFABCreateAssetTransInCompanySrv port = ss.getSBFIFABCreateAssetTransInCompanySrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.td.eip.fi.fa.sb_fi_fa_bcreateassettransincompanysrv.BCreateAssetTransInCompanySrvRequest _process_payload = null;
        com.sino.soa.td.eip.fi.fa.sb_fi_fa_bcreateassettransincompanysrv.BCreateAssetTransInCompanySrvResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}