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
import com.sino.ams.yj.dto.AmsYjUserDTO;
import com.sino.ams.yj.model.AmsYjUserModel;


/**
 * <p>Title: AmsYjUserDAO</p>
 * <p>Description:�����Զ����ɷ������AmsYjUserDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ������-Ӧ��������Աά��
 */


public class AmsYjUserDAO extends BaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ�Ӧ��ͨ�ű��϶���� AMS_YJ_USER ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter AmsYjUserDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public AmsYjUserDAO(SfUserDTO userAccount, AmsYjUserDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		AmsYjUserDTO dtoPara = (AmsYjUserDTO)dtoParameter;
		super.sqlProducer = new AmsYjUserModel((SfUserDTO)userAccount, dtoPara);
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

                String fileName = "Ӧ��������Ա��Ϣ.xls";
                String filePath = WorldConstant.USER_HOME;
                filePath += WorldConstant.FILE_SEPARATOR;
                filePath += fileName;
                rule.setTarFile(filePath);

                DataRange range = new DataRange();
                rule.setDataRange(range);

                Map fieldMap = new HashMap();
                fieldMap.put("ORGANIZATION_NAME", "��˾����");
                fieldMap.put("TEAM_ID", "�����");
                fieldMap.put("TEAM_NAME", "��������");
                fieldMap.put("RESPONSIBILITY_USER", "��ҵ������");
                fieldMap.put("TEL1", "��ҵ�������ֻ�");
                fieldMap.put("SITUATION", "�������������ص�");
                fieldMap.put("USER_NAME", "����");
                fieldMap.put("TEL", "�ֻ�");
                fieldMap.put("POST", "ְ��");
                fieldMap.put("CATEGORY", "רҵ");
                fieldMap.put("ATTRIBUTE", "����");
                fieldMap.put("REMARK", "��ע");
                fieldMap.put("CREATE_USER", "������");
                fieldMap.put("CREATION_DATE", "��������");
                fieldMap.put("LAST_UPDATE_USER", "������");
                fieldMap.put("LAST_UPDATE_DATE", "��������");

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