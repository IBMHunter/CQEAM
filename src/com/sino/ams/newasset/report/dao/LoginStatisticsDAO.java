package com.sino.ams.newasset.report.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.report.model.LoginStatisticsModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.db.DataTransConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Function:        �û���¼���ͳ�Ʊ���
 * Author��          ����
 * Date:            2009-10-27
 */
public class LoginStatisticsDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�EQUIP_STAT ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsAssetsCheckHeaderDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public LoginStatisticsDAO(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
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
        AmsAssetsCheckHeaderDTO dtoPara = (AmsAssetsCheckHeaderDTO) dtoParameter;
        super.sqlProducer = new LoginStatisticsModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ���ܣ�����Excel�ļ���
     *
     * @return File
     * @throws com.sino.base.exception.DataTransException
     *
     */
    public File exportFile() throws DataTransException {
        File file = null;
        try {
            DataTransfer transfer = null;
            SQLModel sqlModel = sqlProducer.getPageQueryModel();
            TransRule rule = new TransRule();
            rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
            Map fieldMap = new HashMap();
            String fileName = "�û���¼���ͳ�Ʊ���";
            fieldMap.put("COMPANY", "��˾����");
            fieldMap.put("FINANCIAL_COUNT", "������");
            fieldMap.put("NETWORK_COUNT", "���粿��");
            fieldMap.put("MARKET_COUNT", "�г�����");
            fieldMap.put("COMPREHENSIVE_COUNT", "�ۺϲ���");
            fieldMap.put("COUNTY_COUNT", "�ع�˾");
            fieldMap.put("SUM_COUNT", "�ϼ�");

            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName + DataTransConstant.SF_XLS;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);
            rule.setFieldMap(fieldMap);
            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(fileName);
            custData.setReportPerson(sfUser.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            TransferFactory factory = new TransferFactory();
            transfer = factory.getTransfer(rule);
            transfer.transData();
            file = (File) transfer.getTransResult();
        } catch (SQLModelException ex) {
            ex.printLog();
            throw new DataTransException(ex);
        }
        return file;
    }

    /**
	 * Function:		�õ��ֵ���ж����������������
	 * @param dicValue	�ֵ���ж�����������Ŵ���
	 * @return			SQLModel
	 * @author  		����
	 * @Date:   		Oct 29, 2009
	 */
	public RowSet getDeptCategoryByDic(String dicValue) throws QueryException{
        LoginStatisticsModel model = (LoginStatisticsModel)this.sqlProducer;
		SQLModel sqlModel = model.getDeptCategoryByDic(dicValue);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        return simpleQuery.getSearchResult();
	}

}