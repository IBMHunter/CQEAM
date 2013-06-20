package com.sino.ams.newasset.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.newasset.dto.AmsAssetsAddressVDTO;
import com.sino.ams.newasset.dto.AmsItemCorrectLogDTO;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.model.ItemMaintainModel3;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.datatrans.*;
import com.sino.base.dto.DTO;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.constant.WorldConstant;
import com.sino.base.data.RowSet;
import com.sino.base.data.Row;
import com.sino.framework.dto.BaseUserDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: T_suhuipeng
 * Date: 2011-3-14
 * Time: 13:48:55
 * To change this template use File | Settings | File Templates.
 */
public class ItemMaintainDAO3 extends AMSBaseDAO {
	private AmsItemCorrectLogDAO logDAO = null;
	private SimpleQuery simp = null;

	public ItemMaintainDAO3(SfUserDTO userAccount, AmsAssetsAddressVDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		logDAO = new AmsItemCorrectLogDAO(userAccount, null, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO)dtoParameter;
		sqlProducer = new ItemMaintainModel3((SfUserDTO)userAccount, dto);
	}

	public boolean updateItems(String[] barcodeNos) {
		boolean operateResult = false;
		boolean autoCommit = true;
		String barcodes = "";
		try {
			AmsAssetsAddressVDTO dto = (AmsAssetsAddressVDTO) dtoParameter;
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String currTime = CalendarUtil.getCurrCalendar(CAL_PATT_45);
			String remark = userAccount.getUsername()
							+ "��"
							+ currTime
							+ "ͨ���豸̨��ά������";
			for (int i = 0; i < barcodeNos.length; i++) {
				barcodes += barcodeNos[i] + ",";
				dto.setBarcode(barcodeNos[i]);
				dto.setRemark(remark);
				setDTOParameter(dto);
				logDAO.setDTOParameter(getLogDTO());
				updateData();
				logDAO.createData();
			}
			operateResult = true;
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (QueryException ex) {
			ex.printLog();
		} finally{
			try {
				if (operateResult) {
					prodMessage(AssetsMessageKeys.ITEM_UPDATE_SUCCESS);
					conn.commit();
				} else {
					prodMessage(AssetsMessageKeys.ITEM_UPDATE_FAILURE);
					conn.rollback();
				}
				message.setIsError(!operateResult);
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex1) {
				Logger.logError(ex1);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ�����̨��ά����־
	 * @return AmsItemCorrectLogDTO
	 * @throws QueryException
	 */
	private AmsItemCorrectLogDTO getLogDTO() throws QueryException{
		AmsItemCorrectLogDTO logDTO = null;
		try {
			ItemMaintainModel3 modelProducer = (ItemMaintainModel3) sqlProducer;
			SQLModel sqlModel = modelProducer.getPrimaryKeyDataModel();
			if (simp == null) {
				simp = new SimpleQuery(sqlModel, conn);
				simp.setDTOClassName(AmsAssetsAddressVDTO.class.getName());
			} else {
				simp.setSql(sqlModel);
			}
			simp.executeQuery();
			if (simp.hasResult()) {
				AmsAssetsAddressVDTO oldDTO = (AmsAssetsAddressVDTO) simp.getFirstDTO();
				AmsAssetsAddressVDTO newDTO = (AmsAssetsAddressVDTO)dtoParameter;
				logDTO = new AmsItemCorrectLogDTO();
				logDTO.setBarcode(newDTO.getBarcode());
				String correctContent = "";
				String[] fieldNames = {"ITEM_CODE", "ITEM_NAME", "ITEM_SPEC", "WORKORDER_OBJECT_CODE",
									  "WORKORDER_OBJECT_NAME", "RESPONSIBILITY_USER_NAME","RESPONSIBILITY_DEPT_NAME",
									  "MAINTAIN_USER", "MAINTAIN_DEPT_NAME"};
				String[] fieldDescs = {"�豸�������", "�豸����", "�豸�ͺ�", "�ص����", "�ص�����",
									  "������","���β���",
									  "ʹ����", "ʹ�ò���"};
				String fieldName = "";
				String javaField = "";
				String oldValue = "";
				String newValue = "";
				for (int i = 0; i < fieldNames.length; i++) {
					fieldName = fieldNames[i];
					javaField = StrUtil.getJavaField(fieldName);
					oldValue = String.valueOf(ReflectionUtil.getProperty(oldDTO, javaField));
					if(oldValue.equals("")){
						oldValue = "��";
					}
					newValue = String.valueOf(ReflectionUtil.getProperty(newDTO, javaField));
					newDTO.getMaintainDept();
					if (!oldValue.equals(newValue) && !newValue.equals("")) {
						correctContent += fieldDescs[i]
							+ ":"
							+ oldValue
							+ "-->>"
							+ newValue;
						correctContent += WorldConstant.ENTER_CHAR;
					}
				}
				logDTO.setCorrectContent(correctContent);
			}
		} catch (ReflectException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return logDTO;
	}

	public File getExportFile(String excelType) throws DataTransException, SQLModelException {
		ItemMaintainModel3 modelProducer = (ItemMaintainModel3) sqlProducer;
		SQLModel sqlModel = modelProducer.getPageQueryModel();
		String reportTitle = "ʵ��̨�ʲ�ѯ";
		if (!StrUtil.isNotEmpty(excelType)) {
			excelType = "xls";
		}
		String fileName = reportTitle + "." + excelType;
		TransRule rule = new TransRule();
		rule.setDataSource(sqlModel);
		rule.setSourceConn(conn);
		String filePath = WorldConstant.USER_HOME;
		filePath += WorldConstant.FILE_SEPARATOR;
		filePath += fileName;
		rule.setTarFile(filePath);
		DataRange range = new DataRange();
		rule.setDataRange(range);
		rule.setFieldMap(getFieldMap());
		CustomTransData custData = new CustomTransData();
		custData.setReportTitle(reportTitle);
		custData.setReportPerson(userAccount.getUsername());
		custData.setNeedReportDate(true);
		rule.setCustData(custData);
		rule.setCalPattern(LINE_PATTERN);
		TransferFactory factory = new TransferFactory();
		DataTransfer transfer = factory.getTransfer(rule);
		transfer.transData();
		return (File) transfer.getTransResult();
	}

	private Map getFieldMap(){
        Map fieldMap = new HashMap();
        fieldMap.put("BARCODE", "��ǩ��");
        fieldMap.put("ITEM_NAME", "�豸����");
        fieldMap.put("ITEM_SPEC", "����ͺ�");
        fieldMap.put("ITEM_UNIT", "������λ");
        fieldMap.put("ITEM_QTY", "ԭʼ����");
        fieldMap.put("ACTUAL_QTY", "ʵ������");
        fieldMap.put("FINANCE_PROP_VALUE", "�ʲ�����");
        fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���");
        fieldMap.put("WORKORDER_OBJECT_NAME", "�ص�����");
        fieldMap.put("CONTENT_CODE", "Ŀ¼����");
        fieldMap.put("CONTENT_NAME", "Ŀ¼����");
        fieldMap.put("CITY", "������");
        fieldMap.put("COUNTY", "������");
        fieldMap.put("AREA_TYPE_NAME", "��������");
        fieldMap.put("RESPONSIBILITY_USER_NAME", "������");
        fieldMap.put("EMPLOYEE_NUMBER", "Ա�����");
        fieldMap.put("DEPT_CODE", "���β��Ŵ���");
        fieldMap.put("RESPONSIBILITY_DEPT_NAME", "���β���");
        fieldMap.put("MAINTAIN_USER", "ʹ����");
        fieldMap.put("MAINTAIN_DEPT_NAME", "ʹ�ò���");
        fieldMap.put("SPECIALITY_DEPT_NAME", "ʵ�ﲿ��");
        fieldMap.put("PROJECT_NAME", "��Ŀ����");
        fieldMap.put("PROJECT_NUMBER", "��Ŀ���");
        fieldMap.put("IS_SHARE", "�Ƿ���");
        fieldMap.put("CONSTRUCT_STATUS", "�Ƿ񹲽�");
        fieldMap.put("LOG_NET_ELE", "�߼�����Ԫ��");
        fieldMap.put("INVEST_CAT_NAME", "Ͷ�ʷ���");
        fieldMap.put("OPE_NAME", "ҵ��ƽ̨");
        fieldMap.put("LNE_NAME", "������");
        fieldMap.put("LIFE_IN_YEARS", "�ʲ�ʹ������");
        fieldMap.put("DATE_PLACED_IN_SERVICE", "�ʲ���������");
        fieldMap.put("ASSETS_CREATE_DATE", "�ʲ���������");
        fieldMap.put("ORIGINAL_COST", "�ʲ�ԭֵ");
        fieldMap.put("SCRAP_VALUE", "�ʲ���ֵ");
        fieldMap.put("IMPAIR_RESERVE", "�ʲ���ֵ׼��");
        fieldMap.put("DEPRN_RESERVE", "�ʲ��ۼ��۾�");
        fieldMap.put("DEPRN_COST", "�ʲ�����");
        fieldMap.put("SEGMENT2", "�ʲ�������");
        fieldMap.put("FA_CATEGORY2", "�ʲ����");
        fieldMap.put("REMARK", "��ע");
        fieldMap.put("REMARK1", "��עһ");
        fieldMap.put("REMARK2", "��ע��");
        fieldMap.put("ITEM_STATUS_VALUE", "�ʲ�״̬");
        return fieldMap;
	}

    public int getCompanyId(String company) throws SQLModelException, QueryException, ContainerException {
        int employeeId = 0;
        ItemMaintainModel3 eoModel = (ItemMaintainModel3) sqlProducer;
        SQLModel sqlModel = eoModel.getCompanyIdModel(company);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            employeeId = Integer.parseInt(row.getStrValue("ORGANIZATION_ID"));
        }
        return employeeId;
    }

    public String getDeptCode(String deptName) throws SQLModelException, QueryException, ContainerException {
        String employeeId = "";
        ItemMaintainModel3 eoModel = (ItemMaintainModel3) sqlProducer;
        SQLModel sqlModel = eoModel.getDeptCodeModel(deptName);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            employeeId = StrUtil.nullToString(row.getStrValue("DEPT_CODE"));
        }
        return employeeId;
    }

    public boolean inItPageQuery(String deptName) throws QueryException {
        boolean isNewPageQuery;
        if (deptName.indexOf("+") > 0) {
            isNewPageQuery = false;
        } else {
            boolean isNewQuery = isNewQuery(deptName);
            if (isNewQuery) {
                isNewPageQuery = true;
            } else {
                isNewPageQuery = false;
            }
        }
        return isNewPageQuery;
    }

    public boolean isNewQuery(String deptName) throws QueryException {
        boolean isNewQuery = false;
        ItemMaintainModel3 eoModel = (ItemMaintainModel3) sqlProducer;
        SQLModel sqlModel = eoModel.isNewQueryModel(deptName);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if(simpleQuery.hasResult()){
           isNewQuery = true;
        }
        return isNewQuery;
    }

    public Map getFincePropCount (AmsAssetsAddressVDTO dto) throws QueryException, ContainerException{
        Map map = new HashMap();
        ItemMaintainModel3 modelProducer = (ItemMaintainModel3) sqlProducer;
        SQLModel sqlModel = modelProducer.getFincePropCount(dto);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        RowSet rs = simpleQuery.getSearchResult();
        for (int i = 0; i< rs.getSize(); i++) {
            Row row = rs.getRow(i);
            map.put(row.getStrValue("CODE"),row.getStrValue("CNT"));
        }
        return map;
    }
}

