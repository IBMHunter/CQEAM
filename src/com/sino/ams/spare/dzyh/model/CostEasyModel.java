package com.sino.ams.spare.dzyh.model;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.spare.dzyh.dto.CostEasyDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.sql.BaseSQLProducer;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2008-4-7
 * Time: 16:32:34
 * Function����ֵ�׺�ά����
 */
public class CostEasyModel extends BaseSQLProducer {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ��ʲ��ص��(AMS) ETS_OBJECT ���ݿ�SQL����㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsObjectDTO ���β���������
     */
    public CostEasyModel(SfUserDTO userAccount, CostEasyDTO dtoParameter) {
        super(userAccount, dtoParameter);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�ִ���������ݲ�����
     *
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() throws SQLModelException{
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        CostEasyDTO costDTO = (CostEasyDTO) dtoParameter;
        try {
        String sqlStr = "INSERT INTO ETS_ITEM_INFO\n" +
                "  (SYSTEMID,\n" +
                "   BARCODE,  \n" +
                "   ITEM_QTY,\n" +
                "   REMARK,\n" +
                "   START_DATE,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY,\n" +
                "   DZYH_ADDRESS,\n" +
                "   RESPONSIBILITY_DEPT,\n" +
                "   RESPONSIBILITY_USER,\n" +
                "   SPECIALITY_DEPT,\n" +
                "   SPECIALITY_USER,\n" +
                "   IS_TD,\n" +
                "   PRICE,\n" +
                "   MANUFACTURER_ID,\n" +
                "   MAINTAIN_USER,\n" +
                "   FINANCE_PROP,\n" +
                "   ORGANIZATION_ID,\n" +
                "   ITEM_CODE)\n" +
                "VALUES\n" +
                "  (ETS_ITEM_INFO_S.NEXTVAL,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   SYSDATE,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?,\n" +
                "   ?)";
        sqlArgs.add(costDTO.getBarcode());
        sqlArgs.add(costDTO.getItemQty());
        sqlArgs.add(costDTO.getRemark());
        sqlArgs.add(costDTO.getStartDate());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(costDTO.getWorkorderObjectName());
        sqlArgs.add(costDTO.getResponsibilityDept());
        sqlArgs.add(costDTO.getResponsibilityUser());
        sqlArgs.add(costDTO.getSpecialityDept());
        sqlArgs.add(costDTO.getSpecialityUser());    
        sqlArgs.add(costDTO.getIsTD());
        sqlArgs.add(costDTO.getPrice());
        sqlArgs.add(costDTO.getManufacturerId());
        sqlArgs.add(costDTO.getMaintainUser());
        sqlArgs.add("DZYH");
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(costDTO.getItemCode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        } catch (CalendarException e) {
            e.printLog();
            throw new SQLModelException(e);
        }
        return sqlModel;
    }

    /**
     * ���ܣ�ִ�������޸Ĳ�����
     *
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        CostEasyDTO costDTO = (CostEasyDTO) dtoParameter;
        String sqlStr = "UPDATE " +
                "    ETS_ITEM_INFO\n" +
                "   SET BARCODE       = ?,\n" +
                "       ITEM_QTY         = ?,\n" +
                "       REMARK           = ?,\n" +
                "       MANUFACTURER_ID        = ?,\n" +
                "       LAST_UPDATE_DATE = SYSDATE,\n" +
                "       LAST_UPDATE_BY   = ?,\n" +
                "       DZYH_ADDRESS       = ? \n" +
                " WHERE SYSTEMID = ?";

        sqlArgs.add(costDTO.getBarcode());
        sqlArgs.add(costDTO.getItemQty());
        sqlArgs.add(costDTO.getRemark());
        sqlArgs.add(costDTO.getManufacturerId());
        sqlArgs.add(sfUser.getUserId());
        sqlArgs.add(costDTO.getWorkorderObjectName());
        sqlArgs.add(costDTO.getSystemid());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�ִ�е���ʧЧ������
     *
     * @return SQLModel ��������ʧЧ��SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        CostEasyDTO etsObject = (CostEasyDTO) dtoParameter;
        String sqlStr = "UPDATE "
                + " ETS_OBJECT"
                + " SET"
                + " DISABLE_DATE = SYSDATE"
                + " WHERE"
                + " WORKORDER_OBJECT_NO = ?";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�ִ����ϸ�����õ�SQLMODEL��
     *
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() { //����ϸ
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        CostEasyDTO csdto = (CostEasyDTO) dtoParameter;
        String sqlStr = "SELECT " +
                "  EII.SYSTEMID,\n" +
                "  AMS_PUB_PKG.GET_FLEX_VALUE('DZYH', 'ITEM_TYPE') ITEM_CATEGORY_DESC," +
                "  AM.MANUFACTURER_CODE,\n" +
                "  AM.MANUFACTURER_NAME,\n" +
                "  ESI.ITEM_NAME,\n" +
                "  EII.ITEM_QTY,\n" +
                "  EII.BARCODE,\n" +
                "  EII.ITEM_STATUS,\n" +
                "  EII.ATTRIBUTE3 INSTRU_USAGE,\n" +
                "  ESI.ITEM_CATEGORY,\n" +
                "  EII.MAINTAIN_USER  USER_ID2, \n" +      //--��ǰʹ����
                "  EII.RESPONSIBILITY_USER USER_ID,\n" +      //--������
                "  AMS_PUB_PKG.GET_USER_NAME(EII.RESPONSIBILITY_USER) RESPONSIBILITY_NAME,\n" +
                " EII.MAINTAIN_USER MAINTAIN_USER," +
                "  EII.RESPONSIBILITY_DEPT DEPT_ID,\n" +
                "       AMS_PUB_PKG.GET_GROUP_NAME(EII.RESPONSIBILITY_DEPT) RESPONSIBILITY_DEPT,\n" +
                "       EII.MAINTAIN_DEPT ,\n" +
                "       AMS_PUB_PKG.GET_GROUP_NAME(EII.MAINTAIN_DEPT) MAINTAIN_DEPT_NAME,\n" +
//                "       EII.ADDRESS_ID,\n" +
//                "       AMS_PUB_PKG.GET_WORKORDER_OBJECT_NAME(EII.ADDRESS_ID) WORKORDER_OBJECT_NAME,\n" +
                "  EII.DZYH_ADDRESS WORKORDER_OBJECT_NAME," +
                "  ESI.ITEM_CODE," +
                "  EII.ATTRIBUTE2 UNIT_PRICE,\n" +
                "  EMPV.VENDOR_NAME,\n" +
                "  EMPV.VENDOR_ID,\n" +
                "  EII.REMARK,\n" +
                "  ESI.ITEM_SPEC\n" +
                " FROM" +
                " ETS_ITEM_INFO EII, " +
                " ETS_SYSTEM_ITEM ESI, " +
                " ETS_MIS_PO_VENDORS EMPV,\n" +
                " AMS_MANUFACTURER AM\n" +
                " WHERE " +
                " ESI.ITEM_CODE = EII.ITEM_CODE\n" +
                " AND EII.MANUFACTURER_ID = AM.MANUFACTURER_ID(+)\n" +
                " AND EMPV.VENDOR_ID(+) = ESI.VENDOR_ID\n" +
                " AND EII.SYSTEMID = ?";
        sqlArgs.add(csdto.getSystemid());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    /**
     * ���ܣ���ѯ�õ�SQLMOL��
     *
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() { //��ѯ
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        CostEasyDTO csdto = (CostEasyDTO) dtoParameter;
        String sqlStr = "SELECT EII.SYSTEMID,\n" +
                "       EII.BARCODE ,  \n" +
                "       EII.ITEM_CODE,\n" +
                "       ESI.ITEM_NAME,\n" +
                "       ESI.ITEM_SPEC,\n" +
                "       AMS_PUB_PKG.GET_FLEX_VALUE(ESI.ITEM_CATEGORY, 'ITEM_TYPE') ITEM_CATE_GORY_DESC,\n" +
                "       EII.ITEM_QTY,\n" +
                "       EMPV.VENDOR_NAME,\n" +
                "       ESI.MASTER_ORGANIZATION_ID,\n" +
                "       ESI.ITEM_CATEGORY,\n" +
//                "       EO.WORKORDER_OBJECT_NAME\n" +
                "       EII.DZYH_ADDRESS WORKORDER_OBJECT_NAME"+
                "  FROM ETS_ITEM_INFO       EII,\n" +
                "       ETS_SYSTEM_ITEM     ESI,\n" +
                "       ETS_MIS_PO_VENDORS  EMPV\n" +
                " WHERE EII.ITEM_CODE = ESI.ITEM_CODE\n" +
                "   AND EMPV.VENDOR_ID(+) = ESI.VENDOR_ID\n" +
                "   AND EII.FINANCE_PROP = 'DZYH'" +
                " AND    (? IS NULL OR ESI.ITEM_NAME LIKE ?)\n" +
                " AND    (? IS NULL OR EII.BARCODE LIKE ?)\n" +
                " AND    (? IS NULL OR EII.MANUFACTURER_ID = ?)" +
                " AND (? IS NULL OR ESI.ITEM_SPEC LIKE ?)";
        sqlArgs.add(csdto.getItemName());
        sqlArgs.add(csdto.getItemName());
        sqlArgs.add(csdto.getBarcode());
        sqlArgs.add(csdto.getBarcode());
        sqlArgs.add(csdto.getManufacturerId());
        sqlArgs.add(csdto.getManufacturerId());
        sqlArgs.add(csdto.getItemSpec());
        sqlArgs.add(csdto.getItemSpec());
        if ((!sfUser.isProvinceUser()) && (!sfUser.isSysAdmin())) {
            sqlStr += "AND EII.ORGANIZATION_ID = ?";
            sqlArgs.add(sfUser.getOrganizationId());
        }
        sqlStr+= " ORDER BY EII.CREATION_DATE DESC";
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    public SQLModel selectItemInfo() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        CostEasyDTO amsInstrumentInfo = (CostEasyDTO) dtoParameter;
        String sqlStr = "SELECT 1\n" +
                "  FROM ETS_SYSTEM_ITEM ESI\n" +
                " WHERE ESI.ITEM_NAME =?\n" +
                "   AND ESI.ITEM_SPEC =?\n" +
                "   AND ESI.ITEM_CATEGORY =?";
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlArgs.add(amsInstrumentInfo.getItemCategory());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    public SQLModel updateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        CostEasyDTO costDTO = (CostEasyDTO) dtoParameter;
        String sqlStr = "UPDATE ETS_SYSTEM_ITEM  SET  VENDOR_ID=?  \n" +
                " WHERE ITEM_NAME =?\n" +
                "   AND ITEM_SPEC =?\n" +
                "   AND ITEM_CATEGORY = ?";

        sqlArgs.add(costDTO.getVendorId());
        sqlArgs.add(costDTO.getItemName());
        sqlArgs.add(costDTO.getItemSpec());
        sqlArgs.add(costDTO.getItemCategory());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    public SQLModel selectDis(String itemCode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT 1\n" +
                "  FROM ETS_SYSITEM_DISTRIBUTE ESD\n" +
                " WHERE ESD.ITEM_CODE = ?\n" +
                "   AND ESD.ORGANIZATION_ID = ?";
        sqlArgs.add(itemCode);
        sqlArgs.add(sfUser.getOrganizationId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    public SQLModel insertIntoItem() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        CostEasyDTO amsInstrumentInfo = (CostEasyDTO) dtoParameter;
        String sqlStr = "INSERT INTO ETS_SYSTEM_ITEM\n" +
                "  (ITEM_CODE,\n" +
                "   ITEM_NAME,\n" +
                "   ITEM_SPEC,\n" +
                "   ITEM_CATEGORY,\n" +
                "   ITEM_UNIT,\n" +
                "   VENDOR_ID,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY,\n" +
                "   IS_TMP_CODE)\n" +
                "VALUES\n" +
                "  (?, ?, ?, ?, '��', ?, SYSDATE, ?, 'Y')";
        sqlArgs.add(amsInstrumentInfo.getItemCode());
        sqlArgs.add(amsInstrumentInfo.getItemName());
        sqlArgs.add(amsInstrumentInfo.getItemSpec());
        sqlArgs.add(amsInstrumentInfo.getItemCategory());
        sqlArgs.add(amsInstrumentInfo.getVendorId());
        sqlArgs.add(sfUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }


    public SQLModel insertIntoDis(String itemcode) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        CostEasyDTO amsInstrumentInfo = (CostEasyDTO) dtoParameter;
        String sqlStr = "INSERT INTO ETS_SYSITEM_DISTRIBUTE\n" +
                "  (SYSTEM_ID,\n" +
                "   ITEM_CODE,\n" +
                "   ORGANIZATION_ID,\n" +
                "   CREATION_DATE,\n" +
                "   CREATED_BY,\n" +
                "   IS_TMP)\n" +
                "VALUES\n" +
                "  (ETS_SYSITEM_DISTRIBUTE_S.NEXTVAL, ?, ?, SYSDATE, ?, 'Y')";
        sqlArgs.add(itemcode);
        sqlArgs.add(sfUser.getOrganizationId());
        sqlArgs.add(sfUser.getUserId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
