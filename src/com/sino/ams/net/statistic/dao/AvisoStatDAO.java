package com.sino.ams.net.statistic.dao;

import java.io.File;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.constant.WebAttrConstant;
import com.sino.ams.net.statistic.dto.AvisoStatDTO;
import com.sino.ams.net.statistic.model.AvisoStatModel;
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
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: V-yuanshuai
 * Date: 2007-11-14
 * Time: 15:27:04
 * To change this template use File | Settings | File Templates.
 */
public class AvisoStatDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�EQUIP_STAT ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter EquipStatDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AvisoStatDAO(SfUserDTO userAccount, AvisoStatDTO dtoParameter, Connection conn) {
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
        AvisoStatDTO dtoPara = (AvisoStatDTO) dtoParameter;
        super.sqlProducer = new AvisoStatModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * ����:��ȡʱ����
     */
    public void setTimeDistance() throws QueryException, ParseException, ContainerException {
        AvisoStatModel modelClass = (AvisoStatModel) sqlProducer;
        SQLModel sqlModel = modelClass.getTimeDistance();
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateA[] = new Date[7];
        for (int i = 0; i < 7; i++) {
            dateA[i] = sdf.parse(rs.getRow(0).getValue(i).toString());
        }
        int n = 6;
        for (int i = 5; i > -1; i--) {
            if (dateA[6].before(dateA[i])) {
                dateA[i] = dateA[6];
                n -= 1;
            }
        }
        Date firDayOfWeek[] = new Date[n];
        Date lasDayOfWeek[] = new Date[n];
        for (int i = 0; i < n; i++) {
            firDayOfWeek[i] = dateA[i];
            lasDayOfWeek[i] = dateA[i + 1];
        }
        AvisoStatDTO aviso = (AvisoStatDTO) dtoParameter;
        aviso.setFirDayOfWeek(firDayOfWeek);
        aviso.setLasDayOfWeek(lasDayOfWeek);
        aviso.setWeekCount(n);
        setDTOParameter(aviso);
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
            AvisoStatDTO dtoPara = (AvisoStatDTO) dtoParameter;
            String qryType = dtoPara.getQryType();
            String fileName = "";
            Map fieldMap = new HashMap();
            if (qryType.equals(WebAttrConstant.BY_CHECK)) {
                fileName = "Ѳ�칤��ͳ��";
                fieldMap.put("ORGANIZATION_NAME", "��˾����");
                fieldMap.put("E1", "1��");
                fieldMap.put("E2", "2��");
                fieldMap.put("E3", "3��");
                fieldMap.put("E4", "4��");
                fieldMap.put("E5", "5��");
                fieldMap.put("EL6", "6�μ�����");
                fieldMap.put("CNT", "��վ����");

            } else if (qryType.equals(WebAttrConstant.BY_MONTH)) {
                fileName = "����ҵ���¶�ͳ��";
                fieldMap.put("ORGANIZATION_NAME", "��˾����");
                fieldMap.put("MONTH_NO", "�·�");
                fieldMap.put("WEEK_NO", "�ܴ�");
                fieldMap.put("T1", "����");
                fieldMap.put("T2", "Ѳ��");
                fieldMap.put("T3", "ά��");
                fieldMap.put("T4", "��Ǩ");
                fieldMap.put("T5", "����");
                fieldMap.put("T6", "����");
                fieldMap.put("SUM", "�ϼ�");
            } else if (qryType.equals(WebAttrConstant.BY_YEAR)) {
                fileName = "����ҵ�����ͳ��";
                fieldMap.put("ORGANIZATION_NAME", "��˾����");
                fieldMap.put("T1", "����");
                fieldMap.put("T2", "Ѳ��");
                fieldMap.put("T3", "ά��");
                fieldMap.put("T4", "��Ǩ");
                fieldMap.put("T5", "����");
                fieldMap.put("T6", "����");
                fieldMap.put("SUM", "�ϼ�");
            } else if (qryType.equals(WebAttrConstant.BY_TIME)) {
                fileName = "����ʱ���ֲ�ͳ�Ʊ���";
                fieldMap.put("ORGANIZATION_NAME", "��˾����");
                fieldMap.put("T1", "8�㵽18��");
                fieldMap.put("T2", "18�㵽0��");
                fieldMap.put("T3", "0�㵽8��");
      //          fieldMap.put("SUM", "�ϼ�");
            }

            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName + DataTransConstant.SF_XLS;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

//		fieldMap.put("IS_TEMP_ADDR", "�Ƿ���ʱ�ص�");
//			fieldMap.put("CREATION_DATE", "��������");
//			fieldMap.put("CREATED_BY", "������");
//			fieldMap.put("PROJECT_NAME", "��������");

            rule.setFieldMap(fieldMap);

            CustomTransData custData = new CustomTransData();
            custData.setReportTitle(fileName);
            custData.setReportPerson(sfUser.getUsername());
            custData.setNeedReportDate(true);
            rule.setCustData(custData);
            /*rule.setSheetSize(1000);*/
            //���÷�ҳ��ʾ
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
}
