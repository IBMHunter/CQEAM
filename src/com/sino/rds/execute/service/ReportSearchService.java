package com.sino.rds.execute.service;

import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.rds.appbase.service.RDSBaseService;
import com.sino.rds.execute.dao.ReportSearchDAO;
import com.sino.rds.foundation.exception.WebException;
import com.sino.rds.foundation.web.component.WebOptions;
import com.sino.rds.foundation.web.component.WebRadio;
import com.sino.rds.share.constant.RDSActionConstant;
import com.sino.rds.share.constant.RDSDictionaryList;
import com.sino.rds.share.form.ReportDefineFrm;
import com.sino.rds.share.util.RDSOptionCreateService;
import com.sino.rds.share.util.RDSRadioCreator;

import java.sql.Connection;


public class ReportSearchService extends RDSBaseService {

    public ReportSearchService(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        ReportSearchDAO searchDAO = new ReportSearchDAO(userAccount, dtoParameter, conn);
        setPrimaryDAO(searchDAO);
    }

    /**
     * ���ܣ�����Web���
     *
     * @throws com.sino.rds.foundation.exception.WebException ����ҳ��Web�������ʱ�׳����쳣
     */
    public void produceWebComponent() throws WebException {
        ReportDefineFrm frm = (ReportDefineFrm) dtoParameter;
        prodEnabledRadio(frm);
        prodReportTypeOptions(frm);
        prodDataModelOptions(frm);
    }

    private void prodEnabledRadio(ReportDefineFrm frm) throws WebException {
        WebRadio radio = RDSRadioCreator.getEnableRadio(frm.getEnabled());
        String enabled = frm.getEnabled();
        String act = frm.getAct();
        if (enabled.equals("") && act.equals(RDSActionConstant.DETAIL_ACTION)) {
            enabled = RDSDictionaryList.TRUE_VALUE;
        }
        radio.setCheckedValue(enabled);
        frm.setEnabledRadio(radio);
    }

    private void prodReportTypeOptions(ReportDefineFrm frm) throws WebException {
        RDSOptionCreateService optionCreator = new RDSOptionCreateService(userAccount, conn);
        WebOptions options = optionCreator.getDictionaryOptions(RDSDictionaryList.REPORT_TYPE, frm.getReportType());
        frm.setReportTypeOptions(options);
    }

    private void prodDataModelOptions(ReportDefineFrm frm) throws WebException {
        RDSOptionCreateService optionCreator = new RDSOptionCreateService(userAccount, conn);
        WebOptions options = optionCreator.getAllDataModelOptions(frm.getModelId());
        frm.setModelOptions(options);
    }
}