package com.sino.ams.system.item.dao;

import java.sql.Connection;

import com.sino.ams.system.fixing.dto.EtsItemInfoDTO;
import com.sino.ams.system.item.model.ItemRelationModel;
import com.sino.ams.system.item.model.SetSubItemsModel;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: </p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾ Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ���
 * @version 0.1
 *          Date: 2007-11-22
 */
public class ItemRelationDAO extends BaseDAO {
    /**
     * ���ܣ����캯����
     * @param userAccount  UserAccountDTO �û���Ϣ
     * @param dtoParameter DTO ���������ݿ⽻��ʱ��Ҫ�Ĳ�����
     * @param conn         Connection ���ݿ�����
     */
    public ItemRelationDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        super.sqlProducer = new ItemRelationModel(userAccount, dtoParameter);
    }

    /**
     * ����barcode��ѯ�豸���ƺ��ͺ�
     * @return EtsItemInfoDTO
     * @throws QueryException
     */
    public EtsItemInfoDTO getItemInfoByBarcode() throws QueryException {
        SimpleQuery sq = new SimpleQuery(((ItemRelationModel) sqlProducer).getItemInfoByBarcodeModel(), conn);
        sq.setDTOClassName(EtsItemInfoDTO.class.getName());
        sq.executeQuery();
        return (EtsItemInfoDTO) sq.getFirstDTO();
    }

    /**
     * �����豸�������豸
     * @param barcodes �ӱ�ǩ��
     * @return success
     */
    public boolean addSubItems(String[] barcodes) {
        return updateSubItems(barcodes, "ADD");
    }

    /**
     * �����豸ɾ�����豸
     * @param barcodes �ӱ�ǩ��
     * @return success
     */
    public boolean deleteSubItems(String[] barcodes) {
        return updateSubItems(barcodes, "DELETE");
    }

    /**
     * ��������� PARENT_BARCODE �ֶ�
     * @param barcodes �ӱ�ǩ��
     * @param flag     ���,�����ӻ���ɾ��
     * @return success
     */
    private boolean updateSubItems(String[] barcodes, String flag) {
        boolean success = false;
        SetSubItemsModel model ;
        EtsItemInfoDTO itemInfo = new EtsItemInfoDTO();
        try {
            for (int i = 0; i < barcodes.length; i++) {
                itemInfo.setBarcode(barcodes[i]);
                if (flag.equals("ADD")) {
                    itemInfo.setParentBarcode(((EtsItemInfoDTO) dtoParameter).getParentBarcode());
                }
                model = new SetSubItemsModel(userAccount, itemInfo);
                DBOperator.updateRecord(model.getDataUpdateModel(), conn);
            }
            success = true;
        } catch (DataHandleException e) {
            e.printLog();
            prodMessage("SAVE_SUCCESS");
        }
        return success;
    }
}
