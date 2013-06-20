package com.sino.soa.td.srv.assetsinfoupdate.dao;

import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.QueryException;
import com.sino.soa.td.srv.assetsinfoupdate.dto.TDSrvAssetsUpdateDTO;
import com.sino.soa.td.srv.assetsinfoupdate.model.TDSrvAssetsUpdateModel;
import java.sql.Connection;

/**
 * <p>Title: SrvAssetsUpdateDAO</p>
 * <p>Description:�ʲ�������Ϣ�޸ġ�SrvAssetsUpdateDAO��</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-26
 * Function:�ʲ�������Ϣ�޸�
 */
public class TDSrvAssetsUpdateDAO {
	
	 /**
	  * ����:��ȡ�ʲ������Ϣ����
	  * @param assetid
	  * @param conn
	  * @return
	  * @throws QueryException
	  */
    public TDSrvAssetsUpdateDTO getAssetsDtoBydId(String assetid,Connection conn)throws QueryException{
    	TDSrvAssetsUpdateDTO dto = null;
    	TDSrvAssetsUpdateModel assetsModel = new TDSrvAssetsUpdateModel(null, null);
    	SQLModel sqlModel = assetsModel.getAssetsUpdateDTO(assetid);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.setDTOClassName(TDSrvAssetsUpdateDTO.class.getName());
        sq.executeQuery();
        if (sq.hasResult()) {
            dto = (TDSrvAssetsUpdateDTO) sq.getFirstDTO();
        }

        return dto;
    }

}

