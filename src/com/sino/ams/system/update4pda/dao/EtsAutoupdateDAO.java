package com.sino.ams.system.update4pda.dao;


import java.sql.Connection;

import com.sino.ams.system.update4pda.dto.EtsAutoupdateDTO;
import com.sino.ams.system.update4pda.model.EtsAutoupdateModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: EtsAutoupdateDAO</p>
 * <p>Description:�����Զ����ɷ������EtsAutoupdateDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author aidy
 * @version 1.0
 */


public class EtsAutoupdateDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�PDA����汾��(EAM) ETS_AUTOUPDATE ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EtsAutoupdateDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public EtsAutoupdateDAO(SfUserDTO userAccount, EtsAutoupdateDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    public EtsAutoupdateDTO getDataByModule(String Module) throws QueryException {
        EtsAutoupdateDTO dto = null;
        EtsAutoupdateModel modelProducer = (EtsAutoupdateModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getDataByModuleModel(Module);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.setDTOClassName(EtsAutoupdateDTO.class.getName());
        sq.executeQuery();
        DTOSet ds = sq.getDTOSet();
        dto = (EtsAutoupdateDTO) ds.getDTO(0);
        if (sq.hasResult()) {
            dto = (EtsAutoupdateDTO) sq.getFirstDTO();
        }
        return dto;

    }

    public void setDataByModule(EtsAutoupdateDTO dtoparameter) throws SQLModelException, DataHandleException {


            EtsAutoupdateModel etsObjectModel = (EtsAutoupdateModel) sqlProducer;
            SQLModel sqlModel = etsObjectModel.getDataUpdateModel(dtoparameter);
            DBOperator.updateRecord(sqlModel, conn);
        }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     *
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        EtsAutoupdateDTO dtoPara = (EtsAutoupdateDTO) dtoParameter;
        super.sqlProducer = new EtsAutoupdateModel((SfUserDTO) userAccount, dtoPara);
    }

}