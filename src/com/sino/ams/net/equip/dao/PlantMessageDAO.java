package com.sino.ams.net.equip.dao;

import java.sql.Connection;

import com.sino.ams.net.equip.dto.PlantMessageDTO;
import com.sino.ams.net.equip.model.PlantMessageModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.dto.DTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Owner
 * Date: 2008-2-21
 * Time: 16:00:31
 * To change this template use File | Settings | File Templates.
 */
public class PlantMessageDAO extends BaseDAO {


    private SfUserDTO sfUser;

    {
        sfUser = null;
    }

    /**
     * ���ܣ����캯����
     *
     * @param userAccount  UserAccountDTO �û���Ϣ
     * @param dtoParameter DTO ���������ݿ⽻��ʱ��Ҫ�Ĳ�����
     * @param conn         Connection ���ݿ�����
     */
    public PlantMessageDAO(SfUserDTO userAccount, PlantMessageDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
         sfUser = userAccount;
    }
    /**
    * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
    *
    * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
    * @param dtoParameter DTO ���β���������
    */

     protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
       PlantMessageDTO dtoPara = (PlantMessageDTO) dtoParameter;
       super.sqlProducer = new PlantMessageModel((SfUserDTO) userAccount, dtoPara);



    }
}
