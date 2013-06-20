package com.sino.soa.td.srv.InquiryRetiredAssetDetail.model;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.config.SinoConfig;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.soa.mis.srv.InquiryRetiredAssetDetail.dto.PageRetiredAssetDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: SrvAssetBookModel</p>
 * <p>Description:�����Զ�����SQL��������SrvAssetBookModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * user:wangzp
 * function:��ѯ�����ʲ�������Ϣ����ҳ��_TD
 */

public class TDPageRetiredAssetDetailModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ��ʲ��˲����� SRV_ASSET_BOOK ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SrvAssetBookDTO ���β���������
     */
    public TDPageRetiredAssetDetailModel(SfUserDTO userAccount, PageRetiredAssetDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ����ӱ�����Ϣ
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        PageRetiredAssetDTO pageRetiredAssetDTO = (PageRetiredAssetDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
                + " ZTE_TD_RETIREMENT_BASIC_INFO("
                + " RETIREMENT_ID "
                + "  BOOK_TYPE_CODE,"
                + "  ASSET_ID,"
                + " ASSET_NUMBER,"
                + " TAG_NUMBER,"
                + " DATE_PLACED_IN_SERVICE,"
                + " DATE_RETIRED,"
                + " DATE_EFFECTIVE,"
                + "	COST_RETIRED,"
                + " STATUS ,"
                + " UNITS,"
                + " RETIREMENT_TYPE_CODE,"
                + ") VALUES ("
                + " ?, ?, ?, ?, ?, ?, ?, ?, CONVERT(FLOAT, ?), ?, ?,?)";
        sqlArgs.add(pageRetiredAssetDTO.getRetirementId());
        sqlArgs.add(pageRetiredAssetDTO.getBookTypeCode());
        sqlArgs.add(Integer.parseInt(pageRetiredAssetDTO.getAssetId()));
        sqlArgs.add(pageRetiredAssetDTO.getAssetNumber());
        sqlArgs.add(pageRetiredAssetDTO.getTagNumber());
        sqlArgs.add(pageRetiredAssetDTO.getDatePlacedInService());
        sqlArgs.add(pageRetiredAssetDTO.getDateRettred());
        sqlArgs.add(pageRetiredAssetDTO.getDateEffective());
        sqlArgs.add(Integer.parseInt(pageRetiredAssetDTO.getCostRetired()));
        sqlArgs.add(pageRetiredAssetDTO.getStatus());
        sqlArgs.add(Integer.parseInt(pageRetiredAssetDTO.getUnits()));
        sqlArgs.add(pageRetiredAssetDTO.getRetirementTypeCode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ����±�����Ϣ��
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        PageRetiredAssetDTO pageRetiredAssetDTO = (PageRetiredAssetDTO) dtoParameter;
        String sqlStr = "UPDATE "
                + " ZTE_TD_RETIREMENT_BASIC_INFO SET"
                + " BOOK_TYPE_CODE=?,"
                + " ASSET_ID=?,"
                + " ASSET_NUMBER=?,"
                + " TAG_NUMBER=?,"
                + " DATE_PLACED_IN_SERVICE=?,"
                + " DATE_RETIRED=?,"
                + " DATE_EFFECTIVE=?,"
                + "	COST_RETIRED=?,"
                + " STATUS=?,"
                + " UNITS=?,"
                + " RETIREMENT_TYPE_CODE=?"
                + " WHERE ASSET_ID=?";
        sqlArgs.add(pageRetiredAssetDTO.getBookTypeCode());
        sqlArgs.add(Integer.parseInt(pageRetiredAssetDTO.getAssetId()));
        sqlArgs.add(pageRetiredAssetDTO.getAssetNumber());
        sqlArgs.add(pageRetiredAssetDTO.getTagNumber());
        
        sqlArgs.add(pageRetiredAssetDTO.getDatePlacedInService());
        sqlArgs.add(pageRetiredAssetDTO.getDateRettred());
        sqlArgs.add(pageRetiredAssetDTO.getDateEffective());

        sqlArgs.add(Integer.parseInt(pageRetiredAssetDTO.getCostRetired()));
        sqlArgs.add(pageRetiredAssetDTO.getStatus());
        sqlArgs.add(Integer.parseInt(pageRetiredAssetDTO.getUnits()));
        sqlArgs.add(pageRetiredAssetDTO.getRetirementTypeCode());
        sqlArgs.add(Integer.parseInt(pageRetiredAssetDTO.getAssetId()));
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * �ж��Ƿ���ڴ˼�¼
     * ����: �ʲ�ID(ASSET_ID)
     * @return 
     */
    public SQLModel getAssetExistsModel() {
        List sqlArgs = new ArrayList();
        PageRetiredAssetDTO retiredAssetDTO = (PageRetiredAssetDTO) dtoParameter;
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "SELECT"
                + "	ECOM.ASSET_ID"
                + "	FROM ZTE_TD_RETIREMENT_BASIC_INFO ECOM WHERE ECOM.ASSET_ID=?";
        sqlModel.setSqlStr(sqlStr);
        sqlArgs.add(Integer.parseInt(retiredAssetDTO.getAssetId()));
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel geUpdateItemInfoModel() {
        List sqlArgs = new ArrayList();
        PageRetiredAssetDTO srvVendorInfoDTO = (PageRetiredAssetDTO) dtoParameter;
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "UPDATE ETS_ITEM_INFO EII"
                + "   SET EII.ITEM_STATUS = 'DISCARDED',"
                + "	     EII.DISABLE_DATE= SYSDATE"
                + "	 WHERE EII.BARCODE = ?";
        sqlModel.setSqlStr(sqlStr);
        sqlArgs.add(srvVendorInfoDTO.getTagNumber());
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
	}
}