
package com.sino.soa.td.eip.fi.fa.sb_fi_fa_transassetdeprecationsrv;

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
 * Wed Oct 12 16:37:32 CST 2011
 * Generated source version: 2.1.4
 * 
 */

public final class SBFIFATransAssetDeprecationSrv_SBFIFATransAssetDeprecationSrvPort_Client {

    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/fi/SB_FI_FA_TransAssetDeprecationSrv", "SB_FI_FA_TDTransAssetDeprecationSrv");

    private SBFIFATransAssetDeprecationSrv_SBFIFATransAssetDeprecationSrvPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = SBFIFATDTransAssetDeprecationSrv.WSDL_LOCATION;
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
      
        SBFIFATDTransAssetDeprecationSrv ss = new SBFIFATDTransAssetDeprecationSrv(wsdlURL, SERVICE_NAME);
        SBFIFATransAssetDeprecationSrv port = ss.getSBFIFATransAssetDeprecationSrvPort();  
        
        {
        System.out.println("Invoking process...");
        com.sino.soa.td.eip.fi.fa.sb_fi_fa_transassetdeprecationsrv.SBFIFATransAssetDeprecationSrvProcessRequest _process_payload = null;
        com.sino.soa.td.eip.fi.fa.sb_fi_fa_transassetdeprecationsrv.SBFIFATransAssetDeprecationSrvProcessResponse _process__return = port.process(_process_payload);
        System.out.println("process.result=" + _process__return);


        }

        System.exit(0);
    }

}
