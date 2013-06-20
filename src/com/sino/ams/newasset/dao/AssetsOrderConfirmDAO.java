package com.sino.ams.newasset.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.model.AssetsOrderConfirmModel;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.Connection;

/**
 * <p>Title: EtsFaAssetsDAO</p>
 * <p>Description:�����Զ����ɷ������EtsFaAssetsDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */

public class AssetsOrderConfirmDAO extends AMSBaseDAO {

    private boolean assetsConfirm = true;

    /**
     * ���ܣ��̶��ʲ���ǰ��Ϣ(EAM) ETS_FA_ASSETS ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsTransLineDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AssetsOrderConfirmDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }


    public void setAssetsConfirm(boolean assetsConfirm){
        this.assetsConfirm = assetsConfirm;
    }


    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        super.sqlProducer = new AssetsOrderConfirmModel(userAccount, dtoParameter);
    }

    public void confirmOrderAssets() throws DataHandleException {
        try {
            AssetsOrderConfirmModel modelProducer = (AssetsOrderConfirmModel) sqlProducer;
            SQLModel sqlModel = modelProducer.getOrderAssetsModel();
            SimpleQuery searcher = new SimpleQuery(sqlModel, conn);
            searcher.setDTOClassName(AmsAssetsTransLineDTO.class.getName());
            searcher.executeQuery();
            DTOSet orderAssets = searcher.getDTOSet();
            if(orderAssets != null && !orderAssets.isEmpty()){
                AssetsConfirmDAO assignDAO = new AssetsConfirmDAO(userAccount, null, conn);
                assignDAO.setAssetsConfirm(assetsConfirm);
                assignDAO.confirmAssets(orderAssets);
            }
        } catch (QueryException ex) {
            ex.printLog();
            throw new DataHandleException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        }
    }

}
