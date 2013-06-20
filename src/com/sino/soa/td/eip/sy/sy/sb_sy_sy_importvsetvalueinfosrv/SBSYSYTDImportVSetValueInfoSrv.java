
/*
 * 
 */

package com.sino.soa.td.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv;

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
 * Wed Sep 14 15:37:06 CST 2011
 * Generated source version: 2.1.4
 * 
 */


@WebServiceClient(name = "SB_SY_SY_TDImportVSetValueInfoSrv", 
                  wsdlLocation = "http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_SY_SY_TDImportVSetValueInfoSrv/1.0/SB_SY_SY_TDImportVSetValueInfoSrv?wsdl",
                  targetNamespace = "http://xmlns.oracle.com/SB_SY_SY_ImportVSetValueInfoSrv") 
public class SBSYSYTDImportVSetValueInfoSrv extends Service {

    public final static URL WSDL_LOCATION;
    public final static QName SERVICE = new QName("http://xmlns.oracle.com/SB_SY_SY_ImportVSetValueInfoSrv", "SB_SY_SY_TDImportVSetValueInfoSrv");
    public final static QName SBSYSYImportVSetValueInfoSrvPort = new QName("http://xmlns.oracle.com/SB_SY_SY_ImportVSetValueInfoSrv", "SB_SY_SY_ImportVSetValueInfoSrvPort");
    static {
        URL url = null;
        try {
            url = new URL("http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_SY_SY_TDImportVSetValueInfoSrv/1.0/SB_SY_SY_TDImportVSetValueInfoSrv?wsdl");
        } catch (MalformedURLException e) {
            System.err.println("Can not initialize the default wsdl from http://"+ SOAConstant.SERVER_NAME+":"+ SOAConstant.SERVER_PORT+"/orabpel/td/SB_SY_SY_TDImportVSetValueInfoSrv/1.0/SB_SY_SY_TDImportVSetValueInfoSrv?wsdl");
            // e.printStackTrace();
        }
        WSDL_LOCATION = url;
    }

    public SBSYSYTDImportVSetValueInfoSrv(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SBSYSYTDImportVSetValueInfoSrv(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SBSYSYTDImportVSetValueInfoSrv() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     * 
     * @return
     *     returns SBSYSYImportVSetValueInfoSrv
     */
    @WebEndpoint(name = "SB_SY_SY_ImportVSetValueInfoSrvPort")
    public SBSYSYImportVSetValueInfoSrv getSBSYSYImportVSetValueInfoSrvPort() {
        return super.getPort(SBSYSYImportVSetValueInfoSrvPort, SBSYSYImportVSetValueInfoSrv.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SBSYSYImportVSetValueInfoSrv
     */
    @WebEndpoint(name = "SB_SY_SY_ImportVSetValueInfoSrvPort")
    public SBSYSYImportVSetValueInfoSrv getSBSYSYImportVSetValueInfoSrvPort(WebServiceFeature... features) {
        return super.getPort(SBSYSYImportVSetValueInfoSrvPort, SBSYSYImportVSetValueInfoSrv.class, features);
    }

}