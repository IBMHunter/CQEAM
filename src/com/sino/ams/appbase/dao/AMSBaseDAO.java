package com.sino.ams.appbase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public abstract class AMSBaseDAO extends BaseDAO {//����Ĵ�����Ϊ����ȥ����DAO�ദ�������û�����
	protected SfUserDTO userAccount = null;

	public AMSBaseDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		this.userAccount = (SfUserDTO)userAccount;
	}


    /**
     * ���ܣ���ȡ������DTO����
     *
     * @param sqlModel ��ѯSQL
     * @param cls      �������DTO��
     * @param <T>      DTO��ʵ����
     * @return DTO��ʵ����ʵ��
     * @throws com.sino.base.exception.QueryException ��ѯ����ʱ�׳����쳣
     */
    public <T extends DTO> T searchDTOByModel(SQLModel sqlModel, Class cls) throws QueryException {
        setDTOClassName(cls.getName());
        return (T) getDataBySQLModelKey(sqlModel);
    }

    /**
     * ���ܣ�����������ѯ������DTO����
     *
     * @param <T> DTO��ʵ����
     * @return DTO��ʵ����ʵ��
     * @throws QueryException ��ѯ����ʱ�׳����쳣
     */
    public <T extends DTO> T searchDTOByPrimaryKey() throws QueryException {
        T data = null;
        try {
            data = (T) searchDTOByModel(sqlProducer.getPrimaryKeyDataModel(), dtoParameter.getClass());
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return data;
    }

    /**
     * ���ܣ���ѯDTO�����б�
     *
     * @param sqlModel ��ѯSQL
     * @param cls      �������DTO��
     * @return DTO�����б�
     * @throws QueryException ��ѯ����ʱ�׳����쳣
     */
    public <T extends DTO> List<T> searchListByModel(SQLModel sqlModel, Class cls) throws QueryException {
        SimpleQuery splq = new SimpleQuery(sqlModel, conn);
        splq.setCalPattern(getCalPattern());
        splq.setDTOClassName(cls.getName());
        splq.executeQuery();
        return splq.getListResult();
    }

    /**
     * ���ܣ���ѯDTO�����б�
     * @param foreignKey ����ֶ�����
     * @return DTO�����б�
     * @throws QueryException ��ѯ����ʱ�׳����쳣
     */
    public <T extends DTO> List<T> searchListByForeignKey(String foreignKey) throws QueryException {
        List<T> listData = null;
        try {
            SQLModel sqlModel = sqlProducer.getDataByForeignKeyModel(foreignKey);
            listData = searchListByModel(sqlModel, dtoParameter.getClass());
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return listData;
    }

    /**
     * ���ܣ���ѯDTO�����б�
     *
     * @param sqlModel ��ѯSQL
     * @return DTO�����б�
     * @throws QueryException ��ѯ����ʱ�׳����쳣
     */
    public DTOSet searchDTOSetByModel(SQLModel sqlModel) throws QueryException {
        return searchDTOSetByModel(sqlModel, dtoParameter.getClass());
    }

    /**
     * ���ܣ���ѯDTO�����б�
     *
     * @param sqlModel ��ѯSQL
     * @param cls      �������DTO��
     * @return DTO�����б�
     * @throws QueryException ��ѯ����ʱ�׳����쳣
     */
    public DTOSet searchDTOSetByModel(SQLModel sqlModel, Class cls) throws QueryException {
        SimpleQuery splq = new SimpleQuery(sqlModel, conn);
        splq.setCalPattern(getCalPattern());
        splq.setDTOClassName(cls.getName());
        splq.executeQuery();
        return splq.getDTOSet();
    }

    /**
     * ���ܣ���ѯDTO�����б�
     * @param foreignKey ����ֶ�����
     * @return DTO�����б�
     * @throws QueryException ��ѯ����ʱ�׳����쳣
     */
    public DTOSet searchDTOSetByForeignKey(String foreignKey) throws QueryException {
        DTOSet dtos = null;
        try {
            SQLModel sqlModel = sqlProducer.getDataByForeignKeyModel(foreignKey);
            dtos = searchDTOSetByModel(sqlModel, dtoParameter.getClass());
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
        return dtos;
    }

    /**
     * ���ܣ�ִ��SQL��䣨���롢���¡�ɾ����
     *
     * @param sqlModel SQL���롢���¡�ɾ�����
     * @throws com.sino.base.exception.DataHandleException ���������ݿ�ִ�д���ʱ�׳����쳣
     */
    public void executeUpdate(SQLModel sqlModel) throws DataHandleException {
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ��������ִ��ɾ��
     * @param foreignKey ����ֶ�����
     * @throws com.sino.base.exception.DataHandleException ���������ݿ�ִ�д���ʱ�׳����쳣
     */
    public void deleteByForeignKey(String foreignKey) throws DataHandleException {
        try {
            SQLModel sqlModel = sqlProducer.getDeleteByForeignKeyModel(foreignKey);
            DBOperator.updateRecord(sqlModel, conn);
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataHandleException();
        }
    }

    /**
     * ���ܣ�ִ�в�ѯ��䣬�ж��Ƿ��в�ѯ���
     *
     * @param sqlModel SQL��ѯ���
     * @return true��ʾ�в�ѯ�����false��ʾû�в�ѯ���
     * @throws QueryException  ���������ݿ��ѯ����ʱ�׳����쳣
     */
    public boolean hasSearchResult(SQLModel sqlModel) throws QueryException {
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.hasResult();
    }

    public String getNextGUID(){
        SeqProducer seqProducer = new SeqProducer(conn);
        return seqProducer.getGUID();        
    }
}
