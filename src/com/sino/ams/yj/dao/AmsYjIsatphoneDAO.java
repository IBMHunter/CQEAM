package com.sino.ams.yj.dao;


import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.WorldConstant;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.yj.dto.AmsYjIsatphoneDTO;
import com.sino.ams.yj.model.AmsYjIsatphoneModel;


/**
 * <p>Title: AmsYjIsatphoneDAO</p>
 * <p>Description:�����Զ����ɷ������AmsYjIsatphoneDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-���ǵ绰ά��
 */


public class AmsYjIsatphoneDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ����ǵ绰��Ϣ�� AMS_YJ_ISATPHONE ���ݷ��ʲ㹹�캯��
     *
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjIsatphoneDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsYjIsatphoneDAO(SfUserDTO userAccount, AmsYjIsatphoneDTO dtoParameter, Connection conn) {
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
        AmsYjIsatphoneDTO dtoPara = (AmsYjIsatphoneDTO) dtoParameter;
        super.sqlProducer = new AmsYjIsatphoneModel((SfUserDTO) userAccount, dtoPara);
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

            String fileName = "���ǵ绰��Ϣ.xls";
            String filePath = WorldConstant.USER_HOME;
            filePath += WorldConstant.FILE_SEPARATOR;
            filePath += fileName;
            rule.setTarFile(filePath);

            DataRange range = new DataRange();
            rule.setDataRange(range);

            Map fieldMap = new HashMap();
            fieldMap.put("ORGANIZATION_NAME", "��˾����");
            fieldMap.put("ISATPHONE_ID", "���");
            fieldMap.put("ISATPHONE_NAME", "������������");
            fieldMap.put("ISATPHONE_TYPE", "���");
            fieldMap.put("ISATPHONE_MODEL", "�ͺ�");
            fieldMap.put("ISATPHONE_ADDRESS", "�洢�ص��λ");
            fieldMap.put("TEL", "�绰����");
            fieldMap.put("BUYING_TIME", "����ʱ��");
            fieldMap.put("COST", "�ʲ�ԭֵ(��Ԫ)");
            fieldMap.put("USED_NUMBER", "ʹ�ô������꣩");
//            fieldMap.put("CREATE_USER", "������");
//            fieldMap.put("CREATION_DATE", "��������");
//            fieldMap.put("LAST_UPDATE_USER", "������");
//            fieldMap.put("LAST_UPDATE_DATE", "��������");

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