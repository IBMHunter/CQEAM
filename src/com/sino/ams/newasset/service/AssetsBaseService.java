package com.sino.ams.newasset.service;

import com.sino.ams.appbase.dto.AMSFlowDTO;
import com.sino.ams.appbase.service.AMSBaseService;
import com.sino.ams.newasset.dao.AmsAssetsReservedDAO;
import com.sino.ams.newasset.dao.AmsItemInfoHistoryDAO;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.bean.FlowCommonUtil;
import com.sino.sinoflow.flowinterface.AppFlowBaseDTO;

import java.sql.Connection;

/**
 * @ϵͳ����: �ʲ�ҵ�������
 * @��������: �˻����ְ������ʲ�������������ʲ������ʷ����
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾�� 1.0
 * @��������: sj
 * @����ʱ��: Sep 3, 2011
 */
public abstract class AssetsBaseService extends AMSBaseService {
    protected AMSFlowDTO flowDTO = null;

    public AssetsBaseService(BaseUserDTO user, AMSFlowDTO dto, Connection conn) {
        super(user, dto, conn);
        setDTOParameter(dto);
    }

    public void setDTOParameter(DTO dtoParameter){
        super.setDTOParameter(dtoParameter);
        if(dtoParameter instanceof AMSFlowDTO){
            this.flowDTO = (AMSFlowDTO)dtoParameter;
        }
    }


    /**
     * �ݴ�
     *
     * @return
     */
    public abstract boolean doSave();

    /**
     * �����ύ
     *
     * @return
     */
    public abstract boolean doSubmit();

    /**
     * ��ѯ��ϸ
     *
     * @throws QueryException
     */
    public abstract void prodData() throws QueryException;

    /**
     * ���ܣ������� --
     *
     * @throws DataHandleException
     */
    public void createReserved(String transId, String barcode) throws DataHandleException {
        AmsAssetsReservedDAO reservedDAO = new AmsAssetsReservedDAO(userAccount, null, conn);
        reservedDAO.createReserved(transId, barcode);
    }

    /**
     * ���ܣ�
     *
     * @throws DataHandleException
     */
    public void deleteReserved(String transId) throws DataHandleException {
        AmsAssetsReservedDAO reservedDAO = new AmsAssetsReservedDAO(userAccount, null, conn);
        reservedDAO.deleteReserved(transId);
    }


    /**
     * �����ʷ��¼
     *
     * @param dto
     * @throws DataHandleException
     */
    public void saveItemInfoHistory(AmsItemInfoHistoryDTO dto) throws DataHandleException {
        AmsItemInfoHistoryDAO amsItemInfoHistoryDAO = new AmsItemInfoHistoryDAO(userAccount, dto, conn);
        amsItemInfoHistoryDAO.recordHistory();
    }

    /**
     * �����ύ
     *
     * @return
     */
    public boolean cancelProcedure() {
        FlowCommonUtil flowCommonUtil = new FlowCommonUtil(flowDTO, conn); // ���̹�����
        return flowCommonUtil.processDel();
    }

    /**
     * ���ܣ��������̵���
     *
     * @param isSubmit true��ʾ�ύ��false��ʾ�ݴ�
     * @return true��ʾ�ɹ���false��ʾʧ��
     */
    protected boolean processProcedure(boolean isSubmit) {
        boolean operateResult = false;
        if (dtoParameter != null && dtoParameter instanceof AppFlowBaseDTO) {
            prepareProcedureData();
            AppFlowBaseDTO frm = (AppFlowBaseDTO) dtoParameter;
            FlowCommonUtil fcu = new FlowCommonUtil(frm, conn);
            operateResult = fcu.processProcedure(isSubmit);
        }
        return operateResult;
    }

    /**
     * <p>ע�⣺������������ʹ�ã�������ȷ����������ύ���õ���SUBMIT_ACTION</p>
     * <p>ע�⣺������֮������protected����ȷ�����������е��ã���������servlet�е���</p>
     * ���ܣ��������̵���
     *
     * @return true��ʾ�ɹ���false��ʾʧ��
     */
    protected boolean processProcedure() {
        boolean operateResult = false;
        if (dtoParameter != null && dtoParameter instanceof AppFlowBaseDTO) {
            prepareProcedureData();
            AppFlowBaseDTO frm = (AppFlowBaseDTO) dtoParameter;
            FlowCommonUtil fcu = new FlowCommonUtil(frm, conn);
            String act = frm.getAct();
            boolean isSubmit = act.equals(WebActionConstant.SUBMIT_ACTION);
            operateResult = fcu.processProcedure(isSubmit);
        }
        return operateResult;
    }

    /**
     * ���ܣ��˻����̵���
     *
     * @return true��ʾ�ɹ���false��ʾʧ��
     */
    public boolean rejectProcedure() {
        if (dtoParameter != null && dtoParameter instanceof AppFlowBaseDTO) {
            AppFlowBaseDTO frm = (AppFlowBaseDTO) dtoParameter;
            FlowCommonUtil fcu = new FlowCommonUtil(frm, conn);
            return fcu.reject();
        }
        return false;
    }


    /**
     * ���ܣ�׼����������,��Ӧ��ʵ��
     */
    protected abstract void prepareProcedureData();
}
