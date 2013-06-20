package com.sino.soa.mis.srv.assetsinfoupdate.dao;

import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.QueryException;
import com.sino.soa.mis.srv.assetsinfoupdate.dto.SrvAssetsUpdateDTO;
import com.sino.soa.mis.srv.assetsinfoupdate.model.SrvAssetsUpdateModel;

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
public class SrvAssetsUpdateDAO {
	
	 /**
	  * ����:��ȡ�ʲ������Ϣ����
	  * @param assetid
	  * @param conn
	  * @return
	  * @throws QueryException
	  */
    public SrvAssetsUpdateDTO getAssetsDtoBydId(String assetid,Connection conn)throws QueryException{
    	SrvAssetsUpdateDTO dto = null;
    	SrvAssetsUpdateModel assetsModel = new SrvAssetsUpdateModel(null, null);
    	SQLModel sqlModel = assetsModel.getAssetsUpdateDTO(assetid);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.setDTOClassName(SrvAssetsUpdateDTO.class.getName());
        sq.executeQuery();
        if (sq.hasResult()) {
            dto = (SrvAssetsUpdateDTO) sq.getFirstDTO();
        }

        return dto;
    }

}

