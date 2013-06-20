package com.sino.soa.mis.srv.inquiryassetlocation.srv;

import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.util.StrUtil;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.mis.srv.inquiryassetlocation.dto.SrvAssetLocationDTO;
import com.sino.soa.util.XMLGregorianCalendarUtil;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_pageinquiryassetlocationsrv.*;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
/**
 * User: wangzhipeng
 * Date: 2011-09-08 9:53:23
 * Function:�ӿڷ��񷵻���Ϣ-��ѯ�ʲ��ص�
 */

public final class SrvAssetLocationSrv {
    private SrvReturnMessage returnMessage = new SrvReturnMessage();
    private DTOSet ds = new DTOSet();
    private String segment1 = "";      //��������
    private String segment2 = "";      //�ص����
    private String segment3 = "";      //ʹ��״̬
    private String locationCombinationCode = "";     //�ʲ��ص�������
    private String locationCombinationName = "";     //�ʲ��ص�����
    private String enabledFlag = "";                 //�Ƿ�����
    private XMLGregorianCalendar lastUpDate = null;       //�����¿�ʼ����  
    private XMLGregorianCalendar endLastUpDate = null;    //�����½�������
    private static final QName SERVICE_NAME = new QName("http://eip.zte.com/fi/SB_FI_FA_PageInquiryAssetLocationSrv", "SB_FI_FA_PageInquiryAssetLocationSrv");

    public SrvAssetLocationSrv() {
    }

    public void excute() throws Exception {
        URL wsdlURL = SBFIFAPageInquiryAssetLocationSrv_Service.WSDL_LOCATION;

        SBFIFAPageInquiryAssetLocationSrv_Service ss = new SBFIFAPageInquiryAssetLocationSrv_Service(wsdlURL, SERVICE_NAME);
        SBFIFAPageInquiryAssetLocationSrv port = ss.getSBFIFAPageInquiryAssetLocationSrvPort();

        {
            System.out.println("SB_FI_FA_PageInquiryAssetLocationSrv Invoking process...");
            PageInquiryAssetLocationSrvRequest _process_payload = null;

            _process_payload = new PageInquiryAssetLocationSrvRequest();
            MsgHeader msgHeader = new MsgHeader();
	            msgHeader.setSOURCESYSTEMID("EAM");
	            msgHeader.setSOURCESYSTEMNAME("EAM");
	            msgHeader.setUSERID("IBM");
	            msgHeader.setUSERNAME("IBM");
	            msgHeader.setPAGESIZE(new BigDecimal(1000));
	            msgHeader.setCURRENTPAGE(new BigDecimal(1));
	            msgHeader.setTOTALRECORD(new BigDecimal(-1));
            _process_payload.setSEGMENT1(this.segment1);
            _process_payload.setSEGMENT2(this.segment2);
            _process_payload.setSEGMENT3(this.segment3);
//            _process_payload.setLOCATIONCOMBINATIONCODE(this.locationCombinationCode);
//            _process_payload.setLOCATIONCOMBINATIONNAME(this.locationCombinationName);
//            _process_payload.setENABLEDFLAG(this.enabledFlag);
            _process_payload.setSTARTLASTUPDATEDATE(this.lastUpDate);
            _process_payload.setENDLASTUPDATEDATE(this.endLastUpDate);
            _process_payload.setMsgHeader(msgHeader);
            PageInquiryAssetLocationSrvResponse _process__return = port.process(_process_payload);
            System.out.println("process.result=" + "=" + _process__return.getErrorFlag() + "||" + _process__return.getErrorMessage());
            returnMessage.setErrorFlag(StrUtil.nullToString(_process__return.getErrorFlag()));
            returnMessage.setErrorMessage(_process__return.getErrorMessage());
            System.out.println("�ܼ�¼��="+_process__return.getTOTALRECORD()+" ��ҳ��:"+_process__return.getTOTALPAGE()+" ÿҳ��¼��="+_process__return.getPAGESIZE());
            if (returnMessage.getErrorFlag().equalsIgnoreCase("Y")) {
                BigDecimal totalRecord = _process__return.getTOTALRECORD();
                BigDecimal pageSize = new BigDecimal(1000);
                int totalPage = _process__return.getTOTALPAGE().intValue();
                for (int i = 1; i < totalPage + 1; i++) {
                    msgHeader = new MsgHeader();
    	            msgHeader.setSOURCESYSTEMID("EAM");
    	            msgHeader.setSOURCESYSTEMNAME("EAM");
    	            msgHeader.setUSERID("IBM");
    	            msgHeader.setUSERNAME("IBM");
                    msgHeader.setPAGESIZE(pageSize);
                    msgHeader.setTOTALRECORD(totalRecord);
                    msgHeader.setCURRENTPAGE(new BigDecimal(i));
                    _process_payload.setSEGMENT1(this.segment1);
                    _process_payload.setSEGMENT2(this.segment2);
                    _process_payload.setSEGMENT3(this.segment3);
                    _process_payload.setSTARTLASTUPDATEDATE(this.lastUpDate);
                    _process_payload.setENDLASTUPDATEDATE(this.endLastUpDate);
                    _process_payload.setMsgHeader(msgHeader);
                    _process__return = port.process(_process_payload);       
            if (_process__return.getErrorFlag().equalsIgnoreCase("Y")) {
                List<PageInquiryAssetLocationSrvOutputItem> items = _process__return.getPageInquiryAssetLocationSrvOutputCollection().getPageInquiryAssetLocationSrvOutputItem();
                for (int k = 0; k < items.size(); k++) {
                    PageInquiryAssetLocationSrvOutputItem item = items.get(k);
                    SrvAssetLocationDTO srvAssetLocationDTO = new SrvAssetLocationDTO();
                        srvAssetLocationDTO.setLocationId(StrUtil.nullToString(item.getLOCATIONID())) ;     //�ʲ��ص�ID
                        srvAssetLocationDTO.setLocationCombinationCode(item.getLOCATIONCOMBINATIONCODE());  //�ʲ��ص�������
	                    String temp = item.getLOCATIONCOMBINATIONNAME();                                    //�ʲ��ص�����
	                    String s = "";
	                    if (temp != null && !temp.equals("") && temp.indexOf("ȱʡ") != -1) {
	                        s = temp.substring(0, temp.indexOf("ȱʡ"));
	                        s = s + "000";
	                        srvAssetLocationDTO.setLocationCombinationName(s);
	                    } else {
	                        srvAssetLocationDTO.setLocationCombinationName(item.getLOCATIONCOMBINATIONNAME());
                        }
	                    srvAssetLocationDTO.setEnabledFlag(item.getENABLEDFLAG());                                      //�Ƿ�����
	                    srvAssetLocationDTO.setStartDateActive(StrUtil.nullToString(item.getSTARTDATEACTIVE())) ;            //����������
	                    srvAssetLocationDTO.setEndDateActive(StrUtil.nullToString(item.getENDDATEACTIVE()));          //����������
	                    srvAssetLocationDTO.setLastUpdateDate(StrUtil.nullToString(item.getLASTUPDATEDATE()));           //������ʱ��

                    ds.addDTO(srvAssetLocationDTO);
                }
              }
            }
          }
        }
    }
    public String toString() {
        String s = null;
        for (int i = 0; i < ds.getSize(); i++) {
        	SrvAssetLocationDTO dto = (SrvAssetLocationDTO) ds.getDTO(i);
            s += "dto: locationID= "+dto.getLocationId()+" �ʲ��ص�������=" + dto.getLocationCombinationCode() +" �ʲ��ص�����="+dto.getLocationCombinationName()+" "+dto.getEnabledFlag()+"\n";
        }
        return s;
    }  
     
    public static void main(String[] args) throws Exception {
        SrvAssetLocationSrv srv = new SrvAssetLocationSrv();
        srv.setSegment1("890000");    //�������� 
        srv.setLastUpDate("2011-07-10");
       // srv.setEndLastUpDate("2010-01-01");
        //srv.setLastUpDate("2011-09-20");
       // srv.setEndLastUpDate("2011-10-27");     //��������������     setEndLastUpDate ��ֵ��Ч Ĭ�����˳��10��
        srv.excute();
        System.out.println(srv.toString());
    }

    /**
     * @return the returnMessage
     */
    public SrvReturnMessage getReturnMessage() {
        return returnMessage;
    }

    /**
     * @return the ds
     */
    public DTOSet getDs() {
        return ds;
    }

    /**
     * @param segment1 the segment1 to set
     */
    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    /**
     * @param segment2 the segment2 to set
     */
    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }


    /**
     * @param segment3 the segment3 to set
     */
    public void setSegment3(String segment3) {
        this.segment3 = segment3;
    }


    /**
     * @param locationCombinationCode the locationCombinationCode to set
     */
    public void setLocationCombinationCode(String locationCombinationCode) {
        this.locationCombinationCode = locationCombinationCode;
    }

    /**
     * @param locationCombinationName the locationCombinationName to set
     */
    public void setLocationCombinationName(String locationCombinationName) {
        this.locationCombinationName = locationCombinationName;
    }

    /**
     * @param enabledFlag the enabledFlag to set
     */
    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    /**
     * @param lastUpDate the lastUpDate to set
     */
    public void setLastUpDate(String lastUpDate) throws CalendarException, DatatypeConfigurationException {
        this.lastUpDate = XMLGregorianCalendarUtil.getXMLGregorianCalendar(lastUpDate);
    }

    /**
     * @param endLastUpDate the endLastUpDate to set
     */
    public void setEndLastUpDate(String endLastUpDate) throws CalendarException, DatatypeConfigurationException {
        this.endLastUpDate = XMLGregorianCalendarUtil.getXMLGregorianCalendar(endLastUpDate); 
    }

 
}