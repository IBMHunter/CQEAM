package com.sino.ams.yj.comvan.dao;


import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

import com.sino.base.dto.DTO;
import com.sino.base.util.StrUtil;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.exception.DataTransException;
import com.sino.base.log.Logger;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.datatrans.*;
import com.sino.base.constant.WorldConstant;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.yj.comvan.dto.AmsYjComvanDTO;
import com.sino.ams.yj.comvan.model.AmsYjComvanModel;


/**
 * <p>Title: AmsYjComvanDAO</p>
 * <p>Description:�����Զ����ɷ������AmsYjComvanDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * User: wangzp
 * Date: 2011-09-20
 * Function:Ӧ��ͨ�ų�
 */


public class AmsYjComvanDAO extends BaseDAO {

    private SfUserDTO sfUser = null;

    /**
     * ���ܣ�Ӧ��ͨ�ų���Ϣ�� AMS_YJ_COMVAN ���ݷ��ʲ㹹�캯��
     * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsYjComvanDTO ���β���������
     * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsYjComvanDAO(SfUserDTO userAccount, AmsYjComvanDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        sfUser = userAccount;
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsYjComvanDTO dtoPara = (AmsYjComvanDTO) dtoParameter;
        super.sqlProducer = new AmsYjComvanModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * isNew:ture ��¼�����ڣ��������   false����¼���ڣ��޸���� 
     * @param isNew
     * @return
     */
    public boolean saveData(boolean isNew) {
        boolean success = false;
        try {
            if (isNew) {
                createData();
            } else {
                updateData();
            }
            success = true;
        } catch (DataHandleException e) {
            Logger.logError(e);
        }
        return success;
    }

    public void deleteAllData(String[] comvanIds) throws DataHandleException {
        AmsYjComvanModel comvanModel=(AmsYjComvanModel)sqlProducer;
        SQLModel sqlModel=comvanModel.getDeleteModel(comvanIds);
        DBOperator.updateRecord(sqlModel,conn);
    }

    public File getExportFile() throws DataTransException {
        File file = null;
		try {
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			String reportTitle = "Ӧ��ͨ�ų���Ϣ����";
			String fileName = reportTitle + ".xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setSourceConn(conn);
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);
			Map fieldMap = new HashMap();
			fieldMap.put("COMVAN_ID", "���");
			fieldMap.put("DEPT_NAME", "������λ");
			fieldMap.put("MANUFACTURER", "������");
			fieldMap.put("MODEL", "�ͺ�");
			fieldMap.put("REFIT_FIRM", "������װ��");
			fieldMap.put("LENGTH", "��������(mm)");
			fieldMap.put("QUALITY", "��������(��)");
			fieldMap.put("ANTENNA_MAST_FORM", "����Φ����ʽ");
			fieldMap.put("HAS_OILENGINE", "�Ƿ����ͻ�");
			fieldMap.put("LICENSE_PLATE", "���г�����");
			fieldMap.put("FRAME_NUMBER", "���ܺ�");
			fieldMap.put("L_W_H", "���������(mm)");
			fieldMap.put("ORIGINAL_COST", "�ʲ�ԭֵ(��Ԫ)");
			fieldMap.put("BTS_MANUFACTURER", "����");
			fieldMap.put("BTS_MODEL", "�ͺ�");
			fieldMap.put("CARRIER_FREQUENCYV_ALLOCATE", "��Ƶ����");
			fieldMap.put("CARRIER_FREQUENCYV_QTY", "����Ƶ��");
			fieldMap.put("INSTALLED_BSC", "�Ƿ�װBSC");
			fieldMap.put("OTHER_GSM_UNIT", "��װ������GSMϵͳ��Ԫ");
			fieldMap.put("TRANS_FORM", "���䷽ʽ");
			fieldMap.put("TRANS_ITEM_MODEL", "�豸�ͺ�");
			fieldMap.put("BANDWIDTH", "����");
			fieldMap.put("HAS_SATELLITE_TRANSMISSIONS", "�Ƿ������Ǵ���");
			fieldMap.put("TYPE_OF_TRAFFIC", "���ṩҵ������");
			fieldMap.put("USE_TIMES", "ʹ�ô���");
			fieldMap.put("USED_TRAFFIC", "ʹ��ʱ�ṩ��ҵ��");
			fieldMap.put("USE_SCENE", "��Ҫʹ�ó������ص�");
			fieldMap.put("REMARK", "����˵��");

			rule.setFieldMap(fieldMap);
			CustomTransData custData = new CustomTransData();
			custData.setReportTitle(reportTitle);
			custData.setReportPerson(sfUser.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			rule.setCalPattern(LINE_PATTERN);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
    }

}