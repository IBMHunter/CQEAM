package com.sino.sinoflow.dao;

import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.data.RowSet;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.db.datatrans.*;
import com.sino.base.constant.WorldConstant;
import com.sino.sinoflow.dto.TaskLookUpDTO;
import com.sino.sinoflow.model.TaskLookUpModel;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.dao.BaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.io.File;

/**
 *
 *  ��Ŀ���ƣ�cms
 *  ����ʱ�䣺����03:56:41
 *  author��������
 *  ��ͬ����ά��
 *
 */
public class TaskDao extends BaseDAO {
    protected SfUserDTO userAccount = null;
    private Object dataSource = null;

	public TaskDao(BaseUserDTO userAccount, TaskLookUpDTO dtoParameter,
			Connection conn) {
		super(userAccount, dtoParameter, conn);
        this.userAccount = (SfUserDTO) userAccount;
	}

	@Override
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		TaskLookUpDTO dto = (TaskLookUpDTO)dtoParameter;
		sqlProducer = new TaskLookUpModel( (SfUserDTO) userAccount, dto);

	}
	
	 /**
     * ���õ����ֶ�
     * @return Map
     */
    public Map getExportMap() {
        Map fieldMap = new HashMap();  
        fieldMap.put("COMPANY_NAME", "��˾");
        fieldMap.put("PROCEDURE_NAME", "������������");
        fieldMap.put("TASK_NAME", "�ڵ�");
        fieldMap.put("PROCESS_TIME", "ƽ������ʱ��");
        return fieldMap;
    }

    public void setDataSource(RowSet rows) {
        this.dataSource = rows;
    }

    public void setDataSource(DTOSet dtos) {
        this.dataSource = dtos;
    }

    public void setDataSource(List forms) {
        try {
            if (forms != null && !forms.isEmpty()) {
                DTOSet dtos = new DTOSet();
                Iterator iterator = forms.iterator();
                while (iterator.hasNext()) {
                    dtos.addDTO((DTO) iterator.next());
                }
                this.dataSource = dtos;
            }
        } catch (DTOException ex) {
            ex.printLog();
        }
    }

    /**
     * ��ȡ�����ļ�
     *
     * @param reportTitle �����ļ���
     * @return File
     */
    public File getExportFile2(String reportTitle) throws DataTransException, SQLModelException {
        String fileName = reportTitle + ".xls";
        TransRule rule = new TransRule();
        if (dataSource != null) {
            if (dataSource instanceof DTOSet) {
                rule.setDataSource((DTOSet) dataSource);
            } else if (dataSource instanceof RowSet) {
                rule.setDataSource((RowSet) dataSource);
            }
        } else {
            rule.setDataSource(sqlProducer.getExportModel());
            rule.setSourceConn(conn);
        }
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);
        DataRange range = new DataRange();
        rule.setDataRange(range);
        Map map = getExportMap();
        if (map == null) {
            throw new DataTransException("����EXCEL���ֶ�δ����");
        }
        rule.setFieldMap(map);
        CustomTransData custData = new CustomTransData();
        //custData.setReportTitle(reportTitle);
        //custData.setReportPerson(userAccount.getUsername());
        custData.setNeedReportDate(false);
        rule.setCustData(custData);
        rule.setCalPattern(LINE_PATTERN);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        return (File) transfer.getTransResult();
    }

     /**
     * ��ȡ�����ļ�
     *
     * @param reportTitle �����ļ���
     * @return File
     */
    public File getExportFile(String reportTitle) throws DataTransException, SQLModelException {
        String fileName = reportTitle + ".xls";
        TransRule rule = new TransRule();
        if (dataSource != null) {
            if (dataSource instanceof DTOSet) {
                rule.setDataSource((DTOSet) dataSource);
            } else if (dataSource instanceof RowSet) {
                rule.setDataSource((RowSet) dataSource);
            }
        } else {
            rule.setDataSource(sqlProducer.getExportModel());
            rule.setSourceConn(conn);
        }
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);
        DataRange range = new DataRange();
        rule.setDataRange(range);
        Map map = getExportMap();
        if (map == null) {
            throw new DataTransException("����EXCEL���ֶ�δ����");
        }
        rule.setFieldMap(map);
        CustomTransData custData = new CustomTransData();
        custData.setReportTitle(reportTitle);
        custData.setReportPerson(userAccount.getUsername());
        custData.setNeedReportDate(false);
        rule.setCustData(custData);
        rule.setCalPattern(LINE_PATTERN);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        return (File) transfer.getTransResult();
    }
	public Object getDataSource() {
		return dataSource;
	}    
}
