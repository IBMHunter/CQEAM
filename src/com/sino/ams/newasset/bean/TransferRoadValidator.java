package com.sino.ams.newasset.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckBatchDTO;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.base.dto.DTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class TransferRoadValidator {
    private Map chkOrderRoad = null; //�̵㹤��״̬ת������·��
    private Map chkBatchRoad = null; //�̵㹤����״̬ת������·��

    public TransferRoadValidator() {
        initChkOrderPermitRoad();
        initChkBatchPermitRoad();
    }

    /**
     * ���ܣ���ʼ���ʲ��̵㹤��״̬�仯·��
     */
    private void initChkOrderPermitRoad() {
        chkOrderRoad = new HashMap();
        List newTarget = new ArrayList();
        newTarget.add(AssetsDictConstant.CHK_STATUS_IN_PROCESS);
        newTarget.add(AssetsDictConstant.CHK_STATUS_CANCELED);
        newTarget.add(AssetsDictConstant.CHK_STATUS_DISTRUIBUTED);
        chkOrderRoad.put(AssetsDictConstant.CHK_STATUS_SAVE_TEMP, newTarget);

        List processTarget = new ArrayList();
        processTarget.add(AssetsDictConstant.CHK_STATUS_APPROVED);
        processTarget.add(AssetsDictConstant.CHK_STATUS_REJECTED);
        chkOrderRoad.put(AssetsDictConstant.CHK_STATUS_IN_PROCESS,
                         processTarget);

        List rejectTarget = new ArrayList();
        rejectTarget.add(AssetsDictConstant.CHK_STATUS_IN_PROCESS);
        rejectTarget.add(AssetsDictConstant.CHK_STATUS_CANCELED);
        chkOrderRoad.put(AssetsDictConstant.CHK_STATUS_REJECTED, rejectTarget);

        List approvedTarget = new ArrayList();
        approvedTarget.add(AssetsDictConstant.CHK_STATUS_DISTRUIBUTED);
        chkOrderRoad.put(AssetsDictConstant.CHK_STATUS_APPROVED, approvedTarget);

        List dictributeTarget = new ArrayList();
        dictributeTarget.add(AssetsDictConstant.CHK_STATUS_DOWNLOADED);
        chkOrderRoad.put(AssetsDictConstant.CHK_STATUS_DISTRUIBUTED,
                         dictributeTarget);

        List downloadTarget = new ArrayList();
        downloadTarget.add(AssetsDictConstant.CHK_STATUS_UPLOADED);
        chkOrderRoad.put(AssetsDictConstant.CHK_STATUS_DOWNLOADED,
                         downloadTarget);

        List uploadTarget = new ArrayList();
        uploadTarget.add(AssetsDictConstant.CHK_STATUS_ARCHIEVED);
        chkOrderRoad.put(AssetsDictConstant.CHK_STATUS_UPLOADED, uploadTarget);
    }

    /**
     * ���ܣ���ʼ���ʲ��̵㹤��״̬�仯·��
     */
    private void initChkBatchPermitRoad() {
        chkBatchRoad = new HashMap();
        List newTarget = new ArrayList();
        newTarget.add(AssetsDictConstant.CHK_STATUS_IN_PROCESS);
        newTarget.add(AssetsDictConstant.CHK_STATUS_CANCELED);
        newTarget.add(AssetsDictConstant.CHK_STATUS_DISTRUIBUTED);
        chkBatchRoad.put(AssetsDictConstant.CHK_STATUS_SAVE_TEMP, newTarget);

        List processTarget = new ArrayList();
        processTarget.add(AssetsDictConstant.CHK_STATUS_APPROVED);
        processTarget.add(AssetsDictConstant.CHK_STATUS_REJECTED);
        chkBatchRoad.put(AssetsDictConstant.CHK_STATUS_IN_PROCESS,
                         processTarget);

        List rejectTarget = new ArrayList();
        rejectTarget.add(AssetsDictConstant.CHK_STATUS_IN_PROCESS);
        rejectTarget.add(AssetsDictConstant.CHK_STATUS_CANCELED);
        chkBatchRoad.put(AssetsDictConstant.CHK_STATUS_REJECTED, rejectTarget);

        List approvedTarget = new ArrayList();
        approvedTarget.add(AssetsDictConstant.CHK_STATUS_DISTRUIBUTED);
        chkBatchRoad.put(AssetsDictConstant.CHK_STATUS_APPROVED, approvedTarget);
    }

    /**
     * ���ܣ��жϵ����Ƿ�����һ��״̬����һ��״̬
     * @param srcDTO AmsAssetsCheckHeaderDTO
     * @param objDTO AmsAssetsCheckHeaderDTO
     * @return boolean
     */
    public boolean canExecuteAction(DTO srcDTO, DTO objDTO) {
        boolean canExecute = false;
        if (srcDTO != null && objDTO != null) {
            List permitionAction = null;
            String clsName = srcDTO.getClass().getName();
            String srcStatus = "";
            String objStatus = "";
            if (clsName.equals(AmsAssetsCheckHeaderDTO.class.getName())) {
                AmsAssetsCheckHeaderDTO srcData = (AmsAssetsCheckHeaderDTO)
                                                  srcDTO;
                AmsAssetsCheckHeaderDTO objData = (AmsAssetsCheckHeaderDTO)
                                                  objDTO;
                srcStatus = srcData.getOrderStatus();
                objStatus = objData.getOrderStatus();
                permitionAction = (List) chkOrderRoad.get(srcStatus);
            } else if (clsName.equals(AmsAssetsCheckBatchDTO.class.getName())) {
                AmsAssetsCheckBatchDTO srcData = (AmsAssetsCheckBatchDTO)
                                                 srcDTO;
                AmsAssetsCheckBatchDTO objData = (AmsAssetsCheckBatchDTO)
                                                 objDTO;
                srcStatus = srcData.getBatchStatus();
                objStatus = objData.getBatchStatus();
                permitionAction = (List) chkBatchRoad.get(srcStatus);
            }
            if (permitionAction != null) {
                canExecute = permitionAction.contains(objStatus);
            }
        }
        return canExecute;
    }
}
