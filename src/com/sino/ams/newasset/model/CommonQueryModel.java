package com.sino.ams.newasset.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.ams.appbase.model.AMSSQLProducer;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.dto.AmsMisDeptDTO;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * <p>Title: AssetsCommQueryModel</p>
 * <p>Description:�����Զ�����SQL��������AmsAssetsCommQueryModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */
public class CommonQueryModel extends AMSSQLProducer {

    /**
     * ���ܣ��ʲ��ۺϲ�ѯ���� AMS_ASSETS_COMM_QUERY ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsCommQueryDTO ���β���������
     */
    public CommonQueryModel(SfUserDTO userAccount,
                            AmsAssetsAddressVDTO dtoParameter) {
        super(userAccount, dtoParameter);
    }

    /**
     * ���ܣ�����ҳ�淭ҳ��ѯʱ����Ҫ��SQLModel
     * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ��</B>
     * @return SQLModel
     * @throws SQLModelException
     */
    public SQLModel getPageQueryModel() throws SQLModelException {
        SQLModel sqlModel = new SQLModel();
        try {
            List sqlArgs = new ArrayList();
            AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
            String sqlStr = "SELECT "
                            + " AAAV.BARCODE,"
                            + " AAAV.ASSET_NUMBER,"
                            + " AAAV.FA_CATEGORY1,"
                            + " AAAV.FA_CATEGORY2,"
                            + " AAAV.SEGMENT1,"
                            + " AAAV.SEGMENT2,"
                            + " AAAV.FA_CATEGORY_CODE,"
                            + " AAAV.ASSETS_DESCRIPTION,"
                            + " AAAV.MODEL_NUMBER,"
                            + " AAAV.ITEM_CATEGORY_NAME,"
                            + " AAAV.ITEM_NAME,"
                            + " AAAV.ITEM_SPEC,"
                            + " AAAV.UNIT_OF_MEASURE,"
                            + " AAAV.CURRENT_UNITS,"
                            + " AAAV.COST,"
                            + " AAAV.LIFE_IN_YEARS,"
                            + " AAAV.DATE_PLACED_IN_SERVICE,"
                            + " AAAV.DEPRN_COST,"
                            + " AAAV.DEPRECIATION,"
                            + " AAAV.DEPRECIATION_ACCOUNT,"
                            + " AAAV.SCRAP_VALUE,"
                            + " AAAV.BOOK_TYPE_CODE,"
                            + " AAAV.PROJECT_NUMBER,"
                            + " AAAV.PROJECT_NAME,"
                            + " AAAV.VENDOR_NUMBER,"
                            + " AAAV.VENDOR_NAME,"
                            + " AAAV.ITEM_STATUS_NAME,"
                            + " AAAV.DEPT_NAME,"
                            + " AAAV.RESPONSIBILITY_USER_NAME,"
                            + " AAAV.EMPLOYEE_NUMBER,"
                            + " AAAV.MAINTAIN_USER_NAME,"
                            + " AAAV.WORKORDER_OBJECT_CODE,"
                            + " AAAV.WORKORDER_OBJECT_NAME,"
                            + " AAAV.WORKORDER_OBJECT_LOCATION,"
                            + " AAAV.COUNTY_NAME,"
                            + " AAAV.COMPANY"
                            + " FROM"
                            + " AMS_ASSETS_ADDRESS_V AAAV"
                            + " WHERE"
                            +
                            " AAAV.ASSET_NUMBER LIKE dbo.NVL(?, AAAV.ASSET_NUMBER)"
                            +
                    " AND AAAV.ASSETS_DESCRIPTION LIKE dbo.NVL(?, AAAV.ASSETS_DESCRIPTION)"
                            + " AND AAAV.BARCODE LIKE dbo.NVL(?, AAAV.BARCODE)"
                            +
                    " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.MODEL_NUMBER LIKE dbo.NVL(?, AAAV.MODEL_NUMBER))"
                            +
                    " AND AAAV.FA_CATEGORY_CODE >= dbo.NVL(?, AAAV.FA_CATEGORY_CODE)"
                            +
                    " AND AAAV.FA_CATEGORY_CODE <= dbo.NVL(?, AAAV.FA_CATEGORY_CODE)"
                            + " AND AAAV.COST >= CONVERT(DECIMAL, dbo.NVL(NULL, CONVERT(VARCHAR, AAAV.COST)))"
                            + " AND AAAV.COST <= CONVERT(DECIMAL, dbo.NVL(NULL, CONVERT(VARCHAR, AAAV.COST)))"
                            +
                    " AND AAAV.BOOK_TYPE_CODE = dbo.NVL(?, AAAV.BOOK_TYPE_CODE)"
                            +
                    " AND AAAV.DATE_PLACED_IN_SERVICE >= ISNULL(?, AAAV.DATE_PLACED_IN_SERVICE)"
                            +
                    " AND AAAV.DATE_PLACED_IN_SERVICE <= ISNULL(?, AAAV.DATE_PLACED_IN_SERVICE)"
                            + " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.RESPONSIBILITY_USER_NAME LIKE dbo.NVL(?, AAAV.RESPONSIBILITY_USER_NAME))"
                            +
                    " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.EMPLOYEE_NUMBER LIKE dbo.NVL(?, AAAV.EMPLOYEE_NUMBER))"
                            + " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.DEPRECIATION_ACCOUNT LIKE dbo.NVL(?, AAAV.DEPRECIATION_ACCOUNT))"
                            +
                    " AND AAAV.WORKORDER_OBJECT_NAME LIKE dbo.NVL(?, AAAV.WORKORDER_OBJECT_NAME)"
                            +
                    " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.VENDOR_NAME LIKE dbo.NVL(?, AAAV.VENDOR_NAME))"
                            +
                    " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.VENDOR_NUMBER LIKE dbo.NVL(?, AAAV.VENDOR_NUMBER))"
                            +
                    " AND ( " + SyBaseSQLUtil.isNull() + "  OR AAAV.PROJECT_NUMBER LIKE dbo.NVL(?, AAAV.PROJECT_NUMBER))"
                            + " AND  " + SyBaseSQLUtil.isNotNull("AAAV.ASSET_ID") + " ";
            sqlArgs.add(dto.getAssetNumber());
            sqlArgs.add(dto.getAssetsDescription());
            sqlArgs.add(dto.getBarcode());
            sqlArgs.add(dto.getModelNumber());
            sqlArgs.add(dto.getModelNumber());
            sqlArgs.add(dto.getStartCategoryCode());
            sqlArgs.add(dto.getEndCategoryCode());
            sqlArgs.add(dto.getStartCost());
            sqlArgs.add(dto.getEndCost());
            sqlArgs.add(dto.getBookTypeCode());
            sqlArgs.add(dto.getStartDate());
            sqlArgs.add(dto.getSQLEndDate());
            sqlArgs.add(dto.getResponsibilityUserName());
            sqlArgs.add(dto.getResponsibilityUserName());
            sqlArgs.add(dto.getEmployeeNumber());
            sqlArgs.add(dto.getEmployeeNumber());
            sqlArgs.add(dto.getDepreciationAccount());
            sqlArgs.add(dto.getDepreciationAccount());
            sqlArgs.add(dto.getWorkorderObjectName());
            sqlArgs.add(dto.getVendorName());
            sqlArgs.add(dto.getVendorName());
            sqlArgs.add(dto.getVendorNumber());
            sqlArgs.add(dto.getVendorNumber());
            sqlArgs.add(dto.getProjectNumber());
            sqlArgs.add(dto.getProjectNumber());
            if (!userAccount.isProvAssetsManager()) {
                sqlStr += " AND AAAV.ORGANIZATION_ID = ?";
                sqlArgs.add(userAccount.getOrganizationId());
                String mtlMgrProps = userAccount.getMtlMgrProps();
                if (!userAccount.isComAssetsManager()) {
                    if (userAccount.isDptAssetsManager()) {
                        DTOSet depts = userAccount.getPriviDeptCodes();
                        if (depts != null && !depts.isEmpty()) {
                            AmsMisDeptDTO dept = null;
                            String deptCodes = "'";
                            for (int i = 0; i < depts.getSize(); i++) {
                                dept = (AmsMisDeptDTO) depts.getDTO(i);
                                deptCodes += dept.getDeptCode() + "', '";
                            }
                            deptCodes += "'";
                            sqlStr += " AND AAAV.DEPT_CODE IN (" + deptCodes +
                                    ")";
                        }
                    } else {
                        sqlStr += " AND AAAV.RESPONSIBILITY_USER = ?";
                        sqlArgs.add(userAccount.getEmployeeId());
                    }
                }
            }
            sqlModel.setSqlStr(sqlStr);
            sqlModel.setArgs(sqlArgs);
        } catch (CalendarException ex) {
            ex.printLog();
            throw new SQLModelException(ex);
        }
        return sqlModel;
    }
}
