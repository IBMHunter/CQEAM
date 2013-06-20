package com.sino.sinoflow.dao;


import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.query.WebPageView;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.datatrans.*;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;
import com.sino.base.constant.WorldConstant;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.sinoflow.dto.DeptGroupDTO;
import com.sino.sinoflow.dto.DeptGroupLineDTO;
import com.sino.sinoflow.dto.SfGroupDTO;
import com.sino.sinoflow.model.DeptGroupModel;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;


/**
 * <p>Title: SfGroupDAO</p>
 * <p>Description:�����Զ����ɷ������SfGroupDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author Hing
 * @version 1.0
 */


public class DeptGroupDAO extends BaseDAO {

//	private SfUserBaseDTO sfUser = null;
    private int pageSize = 20;
    private boolean countPages = true;

	/**
	 * ���ܣ�������� SF_GROUP ���ݷ��ʲ㹹�캯��
	 * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter SfGroupDTO ���β���������
	 * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public DeptGroupDAO(SfUserBaseDTO userAccount, DeptGroupDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
//		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) { 
		DeptGroupDTO dtoPara = (DeptGroupDTO)dtoParameter;
		super.sqlProducer = new DeptGroupModel((SfUserBaseDTO)userAccount, dtoPara);
	}

    public DeptGroupDTO getDeptGroupData() throws QueryException {
        DeptGroupDTO dtoPara = (DeptGroupDTO)dtoParameter;
        SQLModel sqlModel = (new DeptGroupModel((SfUserBaseDTO)userAccount, dtoPara)).getDeptGroupDataModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.setDTOClassName(DeptGroupDTO.class.getName());
        simpleQuery.executeQuery();
        return (DeptGroupDTO)simpleQuery.getFirstDTO();
    }

    public void produceMatchData(HttpServletRequest req) throws QueryException {
        SQLModel sqlModel = ((DeptGroupModel)sqlProducer).getDeptGroupMatchModel();
        WebPageView pageView = new WebPageView(req, conn);
        if (!StrUtil.isEmpty(dtoClassName)) {
            pageView.setDTOClassName(dtoClassName);
        }
        if (pageSize > 0) {
            pageView.setPageSize(pageSize);
        }
        pageView.setCountPages(countPages);
        pageView.setWebCheckProp(getWebCheckProp());
        pageView.setCalPattern(getCalPattern());
        pageView.produceWebData(sqlModel);
    }

    public void createGroup(DTOSet deptLines) throws DataHandleException {
        if(deptLines == null)
            return;
        for(int i = 0; i < deptLines.getSize(); i++) {
            DeptGroupLineDTO line = (DeptGroupLineDTO)deptLines.getDTO(i);
            if(line.getIsCheck().equals("0")) {
                continue;
            }
            SQLModel sqlModel = new DeptGroupModel((SfUserBaseDTO)userAccount, (DeptGroupDTO)dtoParameter).updateDept(line);
            DBOperator.updateRecord(sqlModel, conn);
            SfGroupDTO groupDto = new SfGroupDTO();
            groupDto.setGroupName(line.getGroupName());
            groupDto.setGroupDesc(line.getDeptName());
            groupDto.setEnabled("Y");
            groupDto.setProjectName(line.getProjectName());
            SfGroupDAO groupDAO = new SfGroupDAO((SfUserBaseDTO)userAccount, groupDto, conn);
            int groupId = Integer.parseInt(groupDAO.createData2());
            sqlModel = new DeptGroupModel((SfUserBaseDTO)userAccount, (DeptGroupDTO)dtoParameter).insertGroupMatch(line.getDeptId(), groupId);
            DBOperator.updateRecord(sqlModel, conn);
        }
    }

    public void updateGroup(DTOSet deptLines) throws DataHandleException {
        if(deptLines == null)
            return;
        for(int i = 0; i < deptLines.getSize(); i++) {
            DeptGroupLineDTO line = (DeptGroupLineDTO)deptLines.getDTO(i);
            if(line.getIsCheck().equals("0")) {
                continue;
            }
            SQLModel sqlModel = new DeptGroupModel((SfUserBaseDTO)userAccount, (DeptGroupDTO)dtoParameter).updateDept(line);
            DBOperator.updateRecord(sqlModel, conn);
            // curGroupName Ϊ���������, groupName Ϊ���������, �������Ϊ������Ʊ�������
            if(!line.getCurGroupName().equals(line.getGroupName())) {
                sqlModel = new DeptGroupModel((SfUserBaseDTO)userAccount, (DeptGroupDTO)dtoParameter).updateGroupName(line);
                DBOperator.updateRecord(sqlModel, conn);
                sqlModel = new DeptGroupModel((SfUserBaseDTO)userAccount, (DeptGroupDTO)dtoParameter).updateAuthority(line);
                DBOperator.updateRecord(sqlModel, conn);
                sqlModel = new DeptGroupModel((SfUserBaseDTO)userAccount, (DeptGroupDTO)dtoParameter).updateActInfo(line);
                DBOperator.updateRecord(sqlModel, conn);
                sqlModel = new DeptGroupModel((SfUserBaseDTO)userAccount, (DeptGroupDTO)dtoParameter).updateActLog(line);
                DBOperator.updateRecord(sqlModel, conn);
            }
        }
    }

    public File exportFile() throws DataTransException, SQLModelException {           //����
        File file = null;
        SQLModel sqlModel = sqlProducer.getPageQueryModel();
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setSourceConn(conn);
        String fileName = "���������ƥ���嵥.xls";
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);
        DataRange range = new DataRange();
        rule.setDataRange(range);

        Map fieldMap = new HashMap();
        fieldMap.put("PROJECT_NAME", "��Ŀ����");
        fieldMap.put("DEPT_NAME", "��������");
        fieldMap.put("GROUP_NAME", "�������");
        fieldMap.put("PARENT_NAME", "�ϼ�����");
        fieldMap.put("SECOND_DEPT", "��������");
        fieldMap.put("SPECIALITY_DEPT", "רҵ����");

        rule.setFieldMap(fieldMap);

        CustomTransData custData = new CustomTransData();
        custData.setReportTitle("���������ƥ���嵥");
        custData.setReportPerson(((SfUserBaseDTO)userAccount).getUsername());
        custData.setNeedReportDate(true);
        rule.setCustData(custData);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        file = (File) transfer.getTransResult();
        return file;
    }

    public File exportMatchFile() throws DataTransException {           //����
        File file = null;
        SQLModel sqlModel = ((DeptGroupModel)sqlProducer).getDeptGroupMatchModel();
        TransRule rule = new TransRule();
        rule.setDataSource(sqlModel);
        rule.setSourceConn(conn);
        String fileName = "�������δƥ���嵥.xls";
        String filePath = WorldConstant.USER_HOME;
        filePath += WorldConstant.FILE_SEPARATOR;
        filePath += fileName;
        rule.setTarFile(filePath);
        DataRange range = new DataRange();
        rule.setDataRange(range);

        Map fieldMap = new HashMap();
        fieldMap.put("PROJECT_NAME", "��Ŀ����");
        fieldMap.put("DEPT_NAME", "��������");
        fieldMap.put("GROUP_NAME", "�������");
        fieldMap.put("PARENT_NAME", "�ϼ�����");
        fieldMap.put("SECOND_DEPT", "��������");
        fieldMap.put("SPECIALITY_DEPT", "רҵ����");

        rule.setFieldMap(fieldMap);

        CustomTransData custData = new CustomTransData();
        custData.setReportTitle("�������δƥ���嵥");
        custData.setReportPerson(((SfUserBaseDTO)userAccount).getUsername());
        custData.setNeedReportDate(true);
        rule.setCustData(custData);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        file = (File) transfer.getTransResult();
        return file;
    }
}