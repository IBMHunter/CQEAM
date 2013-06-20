package com.sino.rds.appbase.dao;

import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.sql.BaseSQLProducer;
import com.sino.rds.appbase.RDSConstantConfigManager;
import com.sino.rds.appbase.form.RDSBaseFrm;
import com.sino.rds.appbase.model.DefaultRDSBaseSQLModelImpl;
import com.sino.rds.foundation.db.structure.ConnParser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract class RDSBaseDAO extends BaseDAO implements CalendarConstant {
    protected ConnParser parser = null;

    public RDSBaseDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        initConnParser();
    }

    private void initConnParser(){
        parser = new ConnParser(conn);
    }

    public void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        try {
            initConnParser();
            Class cls = getClass();
            String clsName = cls.getSimpleName();
            String packageName = cls.getPackage().getName();
            clsName = clsName.substring(0, clsName.length() - 3);
            clsName += "Model";
            packageName = packageName.substring(0, packageName.lastIndexOf(".") + 1);
            packageName += "model.";
            if (isOracleDBProduct()) {
                packageName += "oracle.";
                clsName = "Oracle" + clsName;
            } else if (isSybaseDBProduct()) {//SyBase���ݿ�
                packageName += "sybase.";
                clsName = "Sybase" + clsName;
            }
            clsName = packageName + clsName;
            Object[] modelParameters = {userAccount, dtoParameter};
            sqlProducer = (DefaultRDSBaseSQLModelImpl) ReflectionUtil.getInstance(clsName, modelParameters);
        } catch (ReflectException ex) {
            ex.printLog();
            throw new RuntimeException(ex);
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new RuntimeException(ex);
        }
    }

    public Map getExportMap() {
        return null;
    }

    /**
     * ���ܣ���ȡ������DTO����
     *
     * @param sqlModel ��ѯSQL
     * @param cls      �������DTO��
     * @param <T>      DTO��ʵ����
     * @return DTO��ʵ����ʵ��
     * @throws com.sino.base.exception.QueryException
     *          ��ѯ����ʱ�׳����쳣
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
            if (data instanceof RDSBaseFrm) {
                RDSBaseFrm frm = (RDSBaseFrm) data;
                frm.setDataSaved("Y");
            }
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
     *
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
     * ���ܣ���ѯRow�����б�
     *
     * @param sqlModel ��ѯSQL
     * @return RowSet�����б�
     * @throws QueryException ��ѯ����ʱ�׳����쳣
     */
    public RowSet searchRowSetByModel(SQLModel sqlModel) throws QueryException {
        SimpleQuery splq = new SimpleQuery(sqlModel, conn);
        splq.setCalPattern(getCalPattern());
        splq.executeQuery();
        return splq.getSearchResult();
    }

    /**
     * ���ܣ���ȡ��һ���к�
     *
     * @param sequenceName ���к�����
     * @return ��һ����ֵ
     * @throws java.sql.SQLException �������г���ʱ�׳����쳣
     */
    public String getStrNextSeq(String sequenceName) throws SQLException {
        String seqNumber = "";
        SeqProducer sqlProducer = new SeqProducer(conn);
        if (parser.isOracleDBProduct()) {//Oracle���ݿ�
            seqNumber = String.valueOf(sqlProducer.getStrNextSeq(sequenceName));
        } else if (parser.isSybaseDBProduct()) {//SyBase���ݿ�
            seqNumber = sqlProducer.getGUID();
        }
        return seqNumber;
    }

    /**
     * ���ܣ�ִ��SQL��䣨���롢���¡�ɾ����
     *
     * @param sqlModel
     * @throws com.sino.base.exception.DataHandleException
     *
     */
    public void executeUpdate(SQLModel sqlModel) throws DataHandleException {
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ�ִ�в�ѯ��䣬�ж��Ƿ��в�ѯ���
     *
     * @param sqlModel
     * @return true��ʾ�в�ѯ�����false��ʾû�в�ѯ���
     * @throws QueryException
     */
    public boolean hasSearchResult(SQLModel sqlModel) throws QueryException {
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        return sq.hasResult();
    }

    protected String getUserId() {
        return RDSConstantConfigManager.getUserId(userAccount);
    }

    protected boolean isOracleDBProduct(){
        return parser.isOracleDBProduct();
    }

    protected boolean isSybaseDBProduct(){
        return parser.isSybaseDBProduct();
    }

    public BaseSQLProducer getSQLProducer(){
        return sqlProducer;
    }
}