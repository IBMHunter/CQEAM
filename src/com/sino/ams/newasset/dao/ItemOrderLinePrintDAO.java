package com.sino.ams.newasset.dao;

import java.sql.Connection;

import com.sino.base.dto.DTO;

import com.sino.framework.dto.BaseUserDTO;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.model.ItemOrderLinePrintModel;
import com.sino.ams.system.user.dto.SfUserDTO;
public class ItemOrderLinePrintDAO extends AMSBaseDAO {

    /**
     * ���ܣ�AMS_ASSETS_TRANS_LINE ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransLineDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public ItemOrderLinePrintDAO(SfUserDTO userAccount, AmsAssetsTransLineDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsTransLineDTO dto = (AmsAssetsTransLineDTO) dtoParameter;
        sqlProducer = new ItemOrderLinePrintModel((SfUserDTO) userAccount, dto);
    }
//
//    public void setTransType(String transType) {
//        OrderLinePrintModel modelProducer = (OrderLinePrintModel) sqlProducer;
//        modelProducer.setTransType(transType);
//    }

    /**
     * ���ܣ����ô�ӡ���ͣ�������ӡ���ǵ����ӡ(�����ڵ�����)
     * @param printType String
     */
    public void setPrintType(String printType) {
        ItemOrderLinePrintModel modelProducer = (ItemOrderLinePrintModel) sqlProducer;
        modelProducer.setPrintType(printType);
    }
}
