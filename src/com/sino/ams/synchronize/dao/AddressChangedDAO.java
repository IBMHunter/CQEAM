package com.sino.ams.synchronize.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.synchronize.dto.EamSyschronizeDTO;
import com.sino.ams.synchronize.model.AddressChangedModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2008-11-26
 * Time: 11:51:56
 * To change this template use File | Settings | File Templates.
 */

    public class AddressChangedDAO extends AMSBaseDAO {
        private SfUserDTO sfUser = null;

        /**
         * ���ܣ��ʲ��ص��(EAM) ETS_OBJECT ���ݷ��ʲ㹹�캯��
         * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
         * @param dtoParameter EtsObjectDTO ���β���������
         * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
         */
        public AddressChangedDAO(SfUserDTO userAccount, EamSyschronizeDTO dtoParameter, Connection conn) {
            super(userAccount, dtoParameter, conn);
            sfUser = userAccount;
        }

        /**
         * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
         * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
         * @param dtoParameter DTO ���β���������
         */
        protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
            EamSyschronizeDTO dtoPara = (EamSyschronizeDTO)dtoParameter;
            super.sqlProducer = new AddressChangedModel((SfUserDTO)userAccount, dtoPara);
        }

     /**
         * ���ܣ�����Excel�ļ���
         * @return File
         * @throws com.sino.base.exception.DataTransException
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

                String fileName = "�ص���Ϣ���ͬ��.xls";
                String filePath = WorldConstant.USER_HOME;
                filePath += WorldConstant.FILE_SEPARATOR;
                filePath += fileName;
                rule.setTarFile(filePath);

                DataRange range = new DataRange();
                rule.setDataRange(range);

                Map fieldMap = new HashMap();
                fieldMap.put("WORKORDER_OBJECT_CODE", "�ص����");
                fieldMap.put("WORKORDER_OBJECT_NAME", "EAM�ص�����");
                fieldMap.put("ASSETS_LOCATION", "MIS�ص�����");

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

