package com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryassetcategorysrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Tue Sep 06 13:33:18 CST 2011
 * Generated source version: 2.1.4
 * 
 */
 
@WebService(targetNamespace = "http://eip.zte.com/fi/SB_FI_FA_PageInquiryAssetCategorySrv", name = "SB_FI_FA_PageInquiryAssetCategorySrv")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBFIFAPageInquiryAssetCategorySrv {

    @WebResult(name = "PageInquiryAssetCategorySrvResponse", targetNamespace = "http://eip.zte.com/fi/SB_FI_FA_PageInquiryAssetCategorySrv", partName = "payload")
    @WebMethod(action = "process")
    public PageInquiryAssetCategorySrvResponse process(
        @WebParam(partName = "payload", name = "PageInquiryAssetCategorySrvRequest", targetNamespace = "http://eip.zte.com/fi/SB_FI_FA_PageInquiryAssetCategorySrv")
        PageInquiryAssetCategorySrvRequest payload
    );
}
