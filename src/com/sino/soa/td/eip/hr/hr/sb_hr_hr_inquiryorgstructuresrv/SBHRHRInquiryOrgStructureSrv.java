package com.sino.soa.td.eip.hr.hr.sb_hr_hr_inquiryorgstructuresrv;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.1.4
 * Sun Oct 16 21:35:33 CST 2011
 * Generated source version: 2.1.4
 * 
 */
 
@WebService(targetNamespace = "http://eip.zte.com/hr/SB_HR_HR_InquiryOrgStructureSrv", name = "SB_HR_HR_InquiryOrgStructureSrv")
@XmlSeeAlso({ObjectFactory.class, com.sino.soa.td.eip.hr.hr.sb_hr_hr_inquiryorgstructuresrv.msgheader.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface SBHRHRInquiryOrgStructureSrv {

    @WebResult(name = "InquiryOrgStructureSrvResponse", targetNamespace = "http://eip.zte.com/hr/SB_HR_HR_InquiryOrgStructureSrv", partName = "payload")
    @WebMethod(action = "process")
    public InquiryOrgStructureSrvResponse process(
        @WebParam(partName = "payload", name = "InquiryOrgStructureSrvRequest", targetNamespace = "http://eip.zte.com/hr/SB_HR_HR_InquiryOrgStructureSrv")
        InquiryOrgStructureSrvRequest payload
    );
}