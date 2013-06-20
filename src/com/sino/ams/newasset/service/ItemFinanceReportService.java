package com.sino.ams.newasset.service;

import com.sino.ams.appbase.service.AMSBaseService;
import com.sino.ams.newasset.dao.ItemFinanceReportDAO;
import com.sino.ams.newasset.dto.ItemFinanceReportDTO;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.Connection;


public class ItemFinanceReportService extends AMSBaseService {

    public ItemFinanceReportService(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    public DTOSet getItemFinanceReport() throws QueryException {
//==========================����Ŀǰ���ݿ��ʲ��������ݲ�׼ȷ�����²�ѯ�����ʵ��̨�˲�ѯ��һ�£���ʱע�͵�������ٿ��ţ�����ʤ��ע������ɾ���öδ���===============================
//        ItemFinanceReportDAO reportDAO = new ItemFinanceReportDAO(userAccount, dtoParameter, conn);
//        DTOSet report = reportDAO.getFinanceDictionaryList();
//        DTOSet itemReport = reportDAO.getItemFinanceReport();
//        if (itemReport != null && !itemReport.isEmpty()) {
//            int dictCount = report.getSize();
//            int reportCount = itemReport.getSize();
//            for (int i = 0; i < reportCount; i++) {
//                ItemFinanceReportDTO dto = (ItemFinanceReportDTO) itemReport.getDTO(i);
//                for (int j = 0; j < dictCount; j++) {
//                    ItemFinanceReportDTO reportDTO = (ItemFinanceReportDTO) report.getDTO(j);
//                    if(reportDTO.getFinanceProp().equals(dto.getFinanceProp())){
//                        reportDTO.setItemCount(dto.getItemCount());
//                        break;
//                    }
//                }
//            }
//        }
//        return report;
//==========================����Ŀǰ���ݿ��ʲ��������ݲ�׼ȷ�����²�ѯ�����ʵ��̨�˲�ѯ��һ�£���ʱע�͵�������ٿ��ţ�����ʤ��ע������ɾ���öδ���===============================
        return new DTOSet();
    }

}
