package com.sino.soa.td.srv.PageInquiryAssetDistribution.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.SQLModelException;
import com.sino.config.SinoConfig;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.soa.mis.srv.PageInquiryAssetDistribution.dto.InquiryAssetDistributionDTO;

/**
 * <p>Title: SrvAssetBookModel</p>
 * <p>Description:�����Զ�����SQL��������SrvAssetBookModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * user:wangzp
 * function:��ѯ�ʲ���������Ϣ����ҳ��_TD
 */

public class TDPageInquiryAssetDistributionlModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ��ʲ��˲����� SRV_ASSET_BOOK ���ݿ�SQL����㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SrvAssetBookDTO ���β���������
     */
    public TDPageInquiryAssetDistributionlModel(SfUserDTO userAccount, InquiryAssetDistributionDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ������ʲ���������Ϣ
     * @return SQLModel �������ݲ�����SQLModel
     * @throws SQLModelException ���������쳣ʱת��Ϊ���쳣�׳�
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        InquiryAssetDistributionDTO pageRetiredAssetDTO = (InquiryAssetDistributionDTO) dtoParameter;
        String sqlStr = "INSERT INTO "
            + " ZTE_TD_ASSET_DISTRIBUTION("     //����TD��
            + " ASSET_ID ,"
            + " DISTRIBUTION_ID,"
            + " DEPARTMENT,"
            + " LOCATION,"
            + " STATUS,"
            
            + " LOCATION_ID,"
            + " CODE_COMBINATION,"
            + " DISTRIBUTION_UNIT,"
            + "	ASSIGNED_NAME,"
            + " ASSIGNED_TO ,"
            
            + " ASSIGNED_NUMBER,"
            + " EXPENSE_CODE_COMBINATION_ID,"
            + " BOOK_TYPE_CODE,"
            + " LAST_UPDATE_DATE"
            + ") VALUES ("
            + " CONVERT(FLOAT, ?), CONVERT(FLOAT, ?), ?, ?, ?,   CONVERT(FLOAT, ?), ?, CONVERT(FLOAT, ?), ?, CONVERT(FLOAT, ?),   ?, ?, ?, ?)";

      sqlArgs.add(pageRetiredAssetDTO.getAssetId());
      sqlArgs.add(pageRetiredAssetDTO.getDistributionId());
      sqlArgs.add(pageRetiredAssetDTO.getDepartment());
      sqlArgs.add(pageRetiredAssetDTO.getLocation());
      sqlArgs.add(pageRetiredAssetDTO.getStatus());
      sqlArgs.add(pageRetiredAssetDTO.getLocationId());
      sqlArgs.add(pageRetiredAssetDTO.getCodeCombination());
      sqlArgs.add(pageRetiredAssetDTO.getDistributionUnit());
      sqlArgs.add(pageRetiredAssetDTO.getAssignedName());
      sqlArgs.add(pageRetiredAssetDTO.getAssignedTo());
      sqlArgs.add(pageRetiredAssetDTO.getAssetNumber());
      sqlArgs.add(pageRetiredAssetDTO.getExpenseCodeCombinationId());
      sqlArgs.add(pageRetiredAssetDTO.getBookTypeCode());
      sqlArgs.add(pageRetiredAssetDTO.getLastUpdateDate().subSequence(0, 10));
            		
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ������ʲ���������Ϣ��
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        InquiryAssetDistributionDTO pageRetiredAssetDTO = (InquiryAssetDistributionDTO) dtoParameter;
        String sqlStr = "UPDATE "
            + " ZTE_TD_ASSET_DISTRIBUTION "
            + " SET DISTRIBUTION_ID = CONVERT(FLOAT, ?),"
            + " DEPARTMENT = ?,"
            + " LOCATION = ?,"
            + " STATUS = ?,"
            + " LOCATION_ID = CONVERT(FLOAT, ?),"
            + " CODE_COMBINATION = ?,"
            + " DISTRIBUTION_UNIT = CONVERT(FLOAT, ?),"
            + "	ASSIGNED_NAME = ?,"
            + " ASSIGNED_TO = CONVERT(FLOAT, ?) ,"
            + " ASSIGNED_NUMBER = ?,"
            + " EXPENSE_CODE_COMBINATION_ID = ?,"
            + " BOOK_TYPE_CODE = ?,"
            + " LAST_UPDATE_DATE = ?"    //CONVERT(DATETIME, ?,)
            + " WHERE ASSET_ID=CONVERT(FLOAT, ?)";

		    sqlArgs.add(pageRetiredAssetDTO.getDistributionId());
		    sqlArgs.add(pageRetiredAssetDTO.getDepartment());
		    sqlArgs.add(pageRetiredAssetDTO.getLocation());
		    sqlArgs.add(pageRetiredAssetDTO.getStatus());
		    sqlArgs.add(pageRetiredAssetDTO.getLocationId());
		    sqlArgs.add(pageRetiredAssetDTO.getCodeCombination());
		    sqlArgs.add(pageRetiredAssetDTO.getDistributionUnit());
		    sqlArgs.add(pageRetiredAssetDTO.getAssignedName());
		    sqlArgs.add(pageRetiredAssetDTO.getAssignedTo());
		    sqlArgs.add(pageRetiredAssetDTO.getAssetNumber());
		    sqlArgs.add(pageRetiredAssetDTO.getExpenseCodeCombinationId());
		    sqlArgs.add(pageRetiredAssetDTO.getBookTypeCode());
		    sqlArgs.add(pageRetiredAssetDTO.getLastUpdateDate().subSequence(0, 10));
		    sqlArgs.add(pageRetiredAssetDTO.getAssetId());
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
        InquiryAssetDistributionDTO retiredAssetDTO = (InquiryAssetDistributionDTO) dtoParameter;
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
        InquiryAssetDistributionDTO srvVendorInfoDTO = (InquiryAssetDistributionDTO) dtoParameter;
        SQLModel sqlModel = new SQLModel();
        String sqlStr = "UPDATE ETS_ITEM_INFO EII"
                + "   SET EII.ITEM_STATUS = 'DISCARDED',"
                + "	     EII.DISABLE_DATE= SYSDATE"
                + "	 WHERE EII.BARCODE = ?";
        sqlModel.setSqlStr(sqlStr);
       // sqlArgs.add(srvVendorInfoDTO.getTagNumber());
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
	}
}