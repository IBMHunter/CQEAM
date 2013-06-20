package com.sino.ams.system.object.dao;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.object.model.ImportItemModel;
import com.sino.ams.system.object.dto.EtsItemDTO;
import com.sino.ams.onnet.dto.AmsItemOnNetDTO;
import com.sino.ams.newasset.dao.AmsItemInfoHistoryDAO;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.util.StrUtil;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.datatrans.*;
import com.sino.base.data.RowSet;
import com.sino.base.data.Row;
import com.sino.base.constant.WorldConstant;

import java.sql.Connection;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA. User: su Date: 2009-4-26 Time: 10:44:18 To change this template use
 * File | Settings | File Templates.
 */
public class ImportItemDAO extends AMSBaseDAO {

	private SfUserDTO sfUser = null;

	/**
	 * ���ܣ��ص㵼�� AMS_OBJECT_IMPORT ���ݷ��ʲ㹹�캯��
	 * 
	 * @param userAccount
	 *            SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter
	 *            AmsItemOnNetDTO ���β���������
	 * @param conn
	 *            Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public ImportItemDAO(SfUserDTO userAccount, AmsItemOnNetDTO dtoParameter,
			Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * 
	 * @param userAccount
	 *            BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter
	 *            DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EtsItemDTO dtoPara = (EtsItemDTO) dtoParameter;
		super.sqlProducer = new ImportItemModel((SfUserDTO) userAccount,
				dtoPara);
	}

	/**
	 * ���ܣ�ɾ���ӿڱ�����ݡ�
	 * 
	 * @throws SQLModelException
	 */
	public void deleteImportModel() throws SQLModelException, QueryException,
			DataHandleException {
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.deleteImportModel();
		DBOperator.updateRecord(sqlModel, conn);
	}

	/**
	 * �����Ľ�����֤
	 * 
	 * @param dtoSet
	 * @throws SQLModelException
	 * @throws QueryException
	 * @throws ContainerException
	 */
	public void doVerifyData_insert(DTOSet dtoSet) throws SQLModelException,
			QueryException, ContainerException {
		if (dtoSet != null && dtoSet.getSize() > 0) {
			for (int i = 0; i < dtoSet.getSize(); i++) {
				EtsItemDTO itemDTO = (EtsItemDTO) dtoSet.getDTO(i);
				if (!validateBarcode(itemDTO.getBarcode())) {
					doVerifyData_insert(itemDTO);
				} else {
					insertImprotErrorData(itemDTO.getBarcode(), "��"
							+ itemDTO.getExcelLineId() + "��: " + "������ϵͳ���Ѵ���");
				}
			}
		}
	}

	/**
	 * ���������ݽ�����֤
	 * 
	 * @param dtoSet
	 * @throws SQLModelException
	 * @throws QueryException
	 * @throws ContainerException
	 */
	public void doVerifyData_insert(EtsItemDTO itemDTO)
			throws SQLModelException, QueryException, ContainerException {
		String excelLineId = itemDTO.getExcelLineId();
		String msg = "��" + excelLineId + "��: ";
		boolean flag = true;// Ĭ�������Ƹ��ͺŴ���
		boolean flags = true;
		// ������barcode, ��Ӧ����������
		if (!validateOu(itemDTO.getBookTypeCode())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�ʲ��˲������ڱ���˾�˲�");
		} else if (StrUtil.isEmpty(itemDTO.getNewItemName())
				|| StrUtil.isEmpty(itemDTO.getNewItemSpec())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�����ʲ������ƺ��ͺű�������");
		} else if (!validateItemNS(itemDTO.getNewItemName(), itemDTO
				.getNewItemSpec())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�����ƺ��ͺŲ�����");
			flag = false;
		} else if (StrUtil.isEmpty(itemDTO.getContentCode())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "���ʲ�Ŀ¼��ϴ����������");
		} else if (StrUtil.isEmpty(itemDTO.getContentCode())
				|| !validateContentCode(itemDTO.getContentCode())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "���ʲ�Ŀ¼��ϴ��벻����");
		} else if (StrUtil.isEmpty(itemDTO.getNewObjectCode())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�����ʲ��µص�����������");
		} else if (StrUtil.isEmpty(itemDTO.getBarcode())
				|| !validateObjectCode(itemDTO.getNewObjectCode())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�µص���벻����");
			flags = false;
		} else if (StrUtil.isEmpty(itemDTO.getNewResponsibilityDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�����β��Ŵ����������");
		} else if (!validateNewResDept(itemDTO.getNewResponsibilityDept())
				&& !StrUtil.isEmpty(itemDTO.getNewResponsibilityDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�����β��Ŵ��벻����");
		} else if (StrUtil.isEmpty(itemDTO.getNewEmployeeNumber())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��Ա����ű�������");
		} else if (!validateNewEmployeeNum(itemDTO.getNewEmployeeNumber())
				&& !StrUtil.isEmpty(itemDTO.getNewEmployeeNumber())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��Ա����Ų�����");
		} else if (StrUtil.isEmpty(itemDTO.getNewSpecialityDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��ʵ�ﲿ�Ŵ����������");
		} else if (!validateNewResDept(itemDTO.getNewSpecialityDept())
				&& !StrUtil.isEmpty(itemDTO.getNewSpecialityDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��ʵ�ﲿ�Ŵ��벻����");
		}  else if (StrUtil.isEmpty(itemDTO.getNewMaintainDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��ʹ�ò��Ŵ����������");
		}  else if (!validateNewResDept(itemDTO.getNewMaintainDept()) ) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��ʹ�ò��Ŵ��벻����");
		}  else if (StrUtil.isEmpty(itemDTO.getNewMaintainUser())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��ʹ���˱�������");
		} else if (!validateNewManufactId(itemDTO.getNewManufacturerId())
				&& !StrUtil.isEmpty(itemDTO.getNewManufacturerId())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�³��̴��벻����");
		}  else if (!validateNewLNE(itemDTO.getNewLneId())
				&& !StrUtil.isEmpty(itemDTO.getNewLneId())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "����Ԫ���벻����");
		}  else if (!validateNewCEX(itemDTO.getNewCexId())
				&& !StrUtil.isEmpty(itemDTO.getNewCexId())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��Ͷ�ʷ�����벻����");
		}  else if (!validateNewOPE(itemDTO.getNewOpeId())
				&& !StrUtil.isEmpty(itemDTO.getNewOpeId())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��ҵ��ƽ̨���벻����");
		}  else if (!validateNewNLE(itemDTO.getNewNleId())
				&& !StrUtil.isEmpty(itemDTO.getNewNleId())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�������α��벻����");
		}  else if (!validateOuDept(itemDTO.getBookTypeCode(), itemDTO
				.getNewResponsibilityDept())
				&& !StrUtil.isEmpty(itemDTO.getNewResponsibilityDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "���β��Ŷ�Ӧ��˾����ȷ");
		}  else if (!validateOuDept(itemDTO.getBookTypeCode(), itemDTO
				.getNewMaintainDept())
				&& !StrUtil.isEmpty(itemDTO.getNewMaintainDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "ʹ�ò��Ŷ�Ӧ��˾����ȷ");
		} else if (StrUtil.isEmpty(itemDTO.getNewResponsibilityDept())
				&& !StrUtil.isEmpty(itemDTO.getNewEmployeeNumber())) {
			String deptCode = findDeprCode(itemDTO.getBarcode());
			if (!validateEmployee(deptCode, itemDTO.getNewEmployeeNumber())) {
				insertImprotErrorData(itemDTO.getBarcode(), msg
						+ "Ա����ź�EAM���β��Ų�һ��");
			}
		} else if (!StrUtil.isEmpty(itemDTO.getNewResponsibilityDept())
				&& !StrUtil.isEmpty(itemDTO.getNewEmployeeNumber())) {
			if (!validateEmployee(itemDTO.getNewResponsibilityDept(), itemDTO
					.getNewEmployeeNumber())) {
				insertImprotErrorData(itemDTO.getBarcode(), msg
						+ "��Ա����ź������β��Ų�һ��");
			}
		} else if (!validateShareStatus(itemDTO.getNewShareStatus())
				&& !StrUtil.isEmpty(itemDTO.getNewShareStatus())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "����״̬���벻����");
		} else if (!validateContractStatus(itemDTO.getNewConstructStatus())
				&& !StrUtil.isEmpty(itemDTO.getNewConstructStatus())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "����״̬���벻����");
		}
		// else if (!validateNewResDeptIsNum(itemDTO.getNewResponsibilityDept()) &&
		// !StrUtil.isEmpty(itemDTO.getNewResponsibilityDept())) {
		// insertImprotErrorData(itemDTO.getBarcode(), "�����β��Ŵ������Ϊ����");
		// }

		if (flags && !StrUtil.isEmpty(itemDTO.getNewObjectCode())) {
			String addressId = this.getAddressId(itemDTO.getNewObjectCode(),
					itemDTO.getBookTypeCode());
			if (addressId == null || "".equals(addressId)) {
				insertImprotErrorData(itemDTO.getBarcode(), msg
						+ "�µص�����Ӧ���ʲ��ص�ID������");
			}
		}

		if (flag && !StrUtil.isEmpty(itemDTO.getNewItemName())
				&& !StrUtil.isEmpty(itemDTO.getNewItemSpec())) {// ��ȡITEM_CODE
			String itemCode = this.getItemCode(itemDTO.getNewItemName(),
					itemDTO.getNewItemSpec());
			if (itemCode == null || "".equals(itemCode)) {
				insertImprotErrorData(itemDTO.getBarcode(), msg
						+ "�����ƺ��ͺŶ�Ӧ���豸������벻����");
			}
		}
	}

	/**
	 * ���µĽ�����֤
	 * 
	 * @param dtoSet
	 * @throws SQLModelException
	 * @throws QueryException
	 * @throws ContainerException
	 */
	public void doVerifyData_update(DTOSet dtoSet) throws SQLModelException,
			QueryException, ContainerException {
		if (dtoSet != null && dtoSet.getSize() > 0) {
			for (int i = 0; i < dtoSet.getSize(); i++) {
				EtsItemDTO itemDTO = (EtsItemDTO) dtoSet.getDTO(i);
				if (validateBarcode(itemDTO.getBarcode())) {
					doVerifyData_update(itemDTO);
				} else {
					insertImprotErrorData(itemDTO.getBarcode(), "��"
							+ itemDTO.getExcelLineId() + "��: " + "������ϵͳ�в�����");
				}
			}
		}
	}

	/**
	 * ���µ����ݽ�����֤
	 * 
	 * @param dtoSet
	 * @throws SQLModelException
	 * @throws QueryException
	 * @throws ContainerException
	 */
	public void doVerifyData_update(EtsItemDTO itemDTO)
			throws SQLModelException, QueryException, ContainerException {
		String excelLineId = itemDTO.getExcelLineId();
		String msg = "��" + excelLineId + "��: ";
		// boolean flag = true;// Ĭ�������Ƹ��ͺŴ���
		// boolean flags = true;
		// ����barcode, ��Ӧ�ø�������
		if (!validateOu(itemDTO.getBookTypeCode())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�ʲ��˲������ڱ���˾�˲�");
		} else if (!validateSameBarcode(itemDTO.getBarcode())) {
			// �����д����ظ�������
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�����ظ�");
		} else if (!StrUtil.isEmpty(itemDTO.getContentCode())
				&& !validateContentCode(itemDTO.getContentCode())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "���ʲ�Ŀ¼��ϴ��벻����");
		}
		// else if (!validateBarcodeOu(itemDTO.getBarcode(), itemDTO.getBookTypeCode()))
		// {
		// insertImprotErrorData(itemDTO.getBarcode(), "���벻���ڱ���˾����");
		// }
		else if (!validateItemNS(itemDTO.getNewItemName(), itemDTO
				.getNewItemSpec())
				&& (!StrUtil.isEmpty(itemDTO.getNewItemName()))) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�����ƻ��ͺŲ�����");
		} else if (!validateObjectCode(itemDTO.getNewObjectCode())
				&& !StrUtil.isEmpty(itemDTO.getNewObjectCode())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�µص���벻����");
		} else if (!validateNewResDept(itemDTO.getNewResponsibilityDept())
				&& !StrUtil.isEmpty(itemDTO.getNewResponsibilityDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�����β��Ŵ��벻����");
		} else if (!validateNewEmployeeNum(itemDTO.getNewEmployeeNumber())
				&& !StrUtil.isEmpty(itemDTO.getNewEmployeeNumber())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��Ա����Ų�����");
		} else if (!validateNewResDept(itemDTO.getNewSpecialityDept())
				&& !StrUtil.isEmpty(itemDTO.getNewSpecialityDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��ʵ�ﲿ�Ŵ��벻����");
		} else if (!validateNewResDept(itemDTO.getNewMaintainDept())
				&& !StrUtil.isEmpty(itemDTO.getNewMaintainDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��ʹ�ò��Ŵ��벻����");
		} else if (!validateNewManufactId(itemDTO.getNewManufacturerId())
				&& !StrUtil.isEmpty(itemDTO.getNewManufacturerId())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�³��̴��벻����");
		} else if (!validateNewLNE(itemDTO.getNewLneId())
				&& !StrUtil.isEmpty(itemDTO.getNewLneId())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "����Ԫ���벻����");
		} else if (!validateNewCEX(itemDTO.getNewCexId())
				&& !StrUtil.isEmpty(itemDTO.getNewCexId())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��Ͷ�ʷ�����벻����");
		} else if (!validateNewOPE(itemDTO.getNewOpeId())
				&& !StrUtil.isEmpty(itemDTO.getNewOpeId())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "��ҵ��ƽ̨���벻����");
		} else if (!validateNewNLE(itemDTO.getNewNleId())
				&& !StrUtil.isEmpty(itemDTO.getNewNleId())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "�������α��벻����");
		} else if (!validateOuDept(itemDTO.getBookTypeCode(), itemDTO
				.getNewResponsibilityDept())
				&& !StrUtil.isEmpty(itemDTO.getNewResponsibilityDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "���β��Ŷ�Ӧ��˾����ȷ");
		} else if (!validateOuDept(itemDTO.getBookTypeCode(), itemDTO
				.getNewMaintainDept())
				&& !StrUtil.isEmpty(itemDTO.getNewMaintainDept())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "ʹ�ò��Ŷ�Ӧ��˾����ȷ");
		} else if (StrUtil.isEmpty(itemDTO.getNewResponsibilityDept())
				&& !StrUtil.isEmpty(itemDTO.getNewEmployeeNumber())) {
			String deptCode = findDeprCode(itemDTO.getBarcode());
			if (!validateEmployee(deptCode, itemDTO.getNewEmployeeNumber())) {
				insertImprotErrorData(itemDTO.getBarcode(), msg
						+ "Ա����ź�EAM���β��Ų�һ��");
			}
		} else if (!StrUtil.isEmpty(itemDTO.getNewResponsibilityDept())
				&& !StrUtil.isEmpty(itemDTO.getNewEmployeeNumber())) {
			if (!validateEmployee(itemDTO.getNewResponsibilityDept(), itemDTO
					.getNewEmployeeNumber())) {
				insertImprotErrorData(itemDTO.getBarcode(), msg
						+ "��Ա����ź������β��Ų�һ��");
			}
		} else if (!validateShareStatus(itemDTO.getNewShareStatus())
				&& !StrUtil.isEmpty(itemDTO.getNewShareStatus())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "����״̬���벻����");
		} else if (!validateContractStatus(itemDTO.getNewConstructStatus())
				&& !StrUtil.isEmpty(itemDTO.getNewConstructStatus())) {
			insertImprotErrorData(itemDTO.getBarcode(), msg + "����״̬���벻����");
		}

	}

	/**
	 * ���ܣ����ӿڱ����ݵ���Ч�ԡ�
	 */
	public void doVerifyData(DTOSet dtoSet) throws SQLModelException,
			QueryException, ContainerException {
		if (dtoSet != null && dtoSet.getSize() > 0) {
			for (int i = 0; i < dtoSet.getSize(); i++) {
				EtsItemDTO itemDTO = (EtsItemDTO) dtoSet.getDTO(i);
				// �����ʲ�У��
				if (!validateBarcode(itemDTO.getBarcode())) {
					// ������barcode, ��Ӧ����������
					doVerifyData_insert(itemDTO);
				} else {
					// ����barcode, ��Ӧ�ø�������
					doVerifyData_update(itemDTO);
					// else if (!validateNewResDeptIsNum(itemDTO.getNewResponsibilityDept()) &&
					// !StrUtil.isEmpty(itemDTO.getNewResponsibilityDept())) {
					// insertImprotErrorData(itemDTO.getBarcode(), "�����β��Ŵ������Ϊ����");
					// }
				}
			}
		}
	}

	// /**
	// * ���ܣ�У��AMS_ITEM_IMPORT��NEW_RESPONSIBILITY_DEPT�Ƿ�Ϊ����
	// * @throws SQLModelException
	// */
	// public boolean validateNewResDeptIsNum(String newResDept) throws SQLModelException,
	// QueryException {
	// boolean isNum;
	// try {
	// Integer.parseInt(newResDept);
	// isNum = true;
	// Integer.parseInt(newResDept);
	// } catch(NumberFormatException ex) {
	// isNum = false;
	// }
	// return isNum;
	// }

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��BARCODE�Ƿ������ETS_ITEM_INFO��
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateEmployee(String deptCode, String employeeNumber)
			throws SQLModelException, QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateEmployee(deptCode, employeeNumber);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ���д���β���Ϊ�գ�����EII�����β��ţ�ΪУ���������Ƿ��������β��ţ�
	 * 
	 * @throws SQLModelException
	 */
	public String findDeprCode(String barcode) throws SQLModelException,
			QueryException, ContainerException {
		String deptCode = "";
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.findDeprCode(barcode);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		RowSet rs = simpleQuery.getSearchResult();
		if (rs != null && rs.getSize() > 0) {
			Row row = rs.getRow(0);
			deptCode = StrUtil.nullToString(row
					.getStrValue("RESPONSIBILITY_DEPT"));
		}
		return deptCode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��BARCODE�Ƿ����ڱ���˾BARCODE
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateOuDept(String bookTypeCode, String deptCode)
			throws SQLModelException, QueryException {
		boolean hasBarcode = false;
		String subBookTypeCode = bookTypeCode.substring(8, 12);
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateOuDept(subBookTypeCode, deptCode);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��BARCODE�Ƿ������ETS_ITEM_INFO��
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateBarcode(String barcode) throws SQLModelException,
			QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.isBarcodeModel(barcode);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ����������Ϣ��
	 * 
	 * @throws SQLModelException
	 */
	public void insertImprotErrorData(String barcode, String codeError)
			throws SQLModelException {
		try {
			ImportItemModel onNetModel = (ImportItemModel) sqlProducer;
			SQLModel sqlModel = onNetModel.insertImprotErrorData(barcode,
					codeError);
			DBOperator.updateRecord(sqlModel, conn);
		} catch (DataHandleException ex) {
			ex.printLog();
		}
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��BARCODE�����ظ�
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateSameBarcode(String barcode)
			throws SQLModelException, QueryException, ContainerException {
		boolean hasBarcode = true;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateSameBarcode(barcode);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		RowSet rs = simpleQuery.getSearchResult();
		if (rs != null && rs.getSize() > 0) {
			Row row = rs.getRow(0);
			int count = Integer.parseInt(StrUtil.nullToString(row
					.getStrValue("BAR_QTY")));
			if (count > 1) {
				hasBarcode = false;
			}
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��BARCODE�Ƿ����ڱ���˾BARCODE
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateOu(String bookTypeCode) throws SQLModelException,
			QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateOu(bookTypeCode);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У�� AMS_CONTENT_DIC ��CONTENT_CODE�Ƿ���Ч
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateContentCode(String contentCode)
			throws SQLModelException, QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateContentCode(contentCode);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��BARCODE�Ƿ����ڱ���˾BARCODE
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateBarcodeOu(String barcode, String bookTypeCode)
			throws SQLModelException, QueryException {
		boolean hasBarcode = false;
		String subBarcode = barcode.substring(0, 4);
		String subBookTypeCode = bookTypeCode.substring(8, 12);
		if (subBarcode.equals(subBookTypeCode)) {
			hasBarcode = true;
		}
		// ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		// SQLModel sqlModel = eoModel.isBarcodeOuModel(barcode, bookTypeCode);
		// SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		// simpleQuery.executeQuery();
		// if(simpleQuery.hasResult()){
		// hasBarcode = true;
		// }
		return hasBarcode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��NEW_ITEM_NAME��NEW_ITEM_SPEC�Ƿ���Ч
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateItemNS(String itemName, String itemSpec)
			throws SQLModelException, QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateItemNS(itemName, itemSpec);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��NEW_OBJECT_CODE�Ƿ���Ч
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateObjectCode(String newObjectCode)
			throws SQLModelException, QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateObjectCode(newObjectCode);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��NEW_RESPONSIBILITY_DEPT�Ƿ���Ч
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateNewResDept(String newResDept)
			throws SQLModelException, QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateNewResDept(newResDept);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��NEW_EMPLOYEE_NUMBER�Ƿ���Ч
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateNewEmployeeNum(String newEmployeeNum)
			throws SQLModelException, QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateNewEmployeeNum(newEmployeeNum);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ����:��¼�ʲ���Ϣ�䶯��ʷ
	 * 
	 * @throws DataHandleException
	 */
	public void logBarcodeHistory(EtsItemDTO eoDTO) {
		AmsItemInfoHistoryDTO historyDTO = new AmsItemInfoHistoryDTO();
		historyDTO.setOrderCategory("3");
		historyDTO.setOrderNo("");
		historyDTO.setCreatedBy(userAccount.getUserId());
		historyDTO.setOrderDtlUrl("");
		historyDTO.setBarcode(eoDTO.getBarcode());
		AmsItemInfoHistoryDAO historyDAO = new AmsItemInfoHistoryDAO(
				userAccount, historyDTO, conn);
		historyDAO.recordHistory();
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��NEW_MANUFACTURER_ID�Ƿ���Ч
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateNewManufactId(String NewManufactId)
			throws SQLModelException, QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateNewManufactId(NewManufactId);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��NEW_LNE_ID�Ƿ���Ч
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateNewLNE(String newLneId) throws SQLModelException,
			QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateNewLNE(newLneId);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��CEX_ID�Ƿ���Ч
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateNewCEX(String newCexId) throws SQLModelException,
			QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateNewCEX(newCexId);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��OPE_ID�Ƿ���Ч
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateNewOPE(String newOpeId) throws SQLModelException,
			QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateNewOPE(newOpeId);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У��AMS_ITEM_IMPORT��NLE_ID�Ƿ���Ч
	 * 
	 * @throws SQLModelException
	 */
	public boolean validateNewNLE(String newNleId) throws SQLModelException,
			QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateNewNLE(newNleId);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�У����AMS_ITEM_IMPORT�Ƿ���ڵ������
	 * 
	 * @throws SQLModelException
	 */
	public boolean importHasError() throws SQLModelException, QueryException {
		boolean hasError = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.hasErrorModel();
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasError = true;
		}
		return hasError;
	}

	/**
	 * ���ܣ���AMS_ITEM_IMPORT�л�ȡ����ɹ�������
	 * 
	 * @throws QueryException
	 */
	public DTOSet getImport() throws QueryException, SQLModelException {
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SimpleQuery sq = new SimpleQuery(eoModel.getQueryImportModel(), conn);
		sq.setDTOClassName(EtsItemDTO.class.getName());
		sq.executeQuery();
		return sq.getDTOSet();
	}

	/**
	 * ���ܣ�ͨ��NEW_ITEM_NAME,NEW_ITEM_SPECȡ��ITEM_CODE
	 * 
	 * @throws SQLModelException
	 */
	public String getItemCode(String itemName, String itemSpce)
			throws SQLModelException, QueryException, ContainerException {
		String itemCode = "";
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.getItemCodeModel(itemName, itemSpce);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		RowSet rs = simpleQuery.getSearchResult();
		if (rs != null && rs.getSize() > 0) {
			Row row = rs.getRow(0);
			itemCode = StrUtil.nullToString(row.getStrValue("ITEM_CODE"));
		}
		return itemCode;
	}

	/**
	 * ���ܣ�ͨ��NEW_OBJECT_CODEȡ��ADDRESS_ID
	 * 
	 * @throws SQLModelException
	 */
	public String getAddressId(String objectCode, String bookTypeCode)
			throws SQLModelException, QueryException, ContainerException {
		String addressId = "";
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		// String bookCode = bookTypeCode.substring(8);//�ɸ���ģ������д���˲��ж�OU
		SQLModel sqlModel = eoModel.getAddressIdModel(objectCode);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		RowSet rs = simpleQuery.getSearchResult();
		if (rs != null && rs.getSize() > 0) {
			Row row = rs.getRow(0);
			addressId = StrUtil.nullToString(row.getStrValue("ADDRESS_ID"));
		}
		return addressId;
	}

	/**
	 * ���ܣ�ͨ��NEW_EMPLOYEE_NUMBERȡ��EMPLOYEE_ID
	 * 
	 * @throws SQLModelException
	 */
	public String getEmployeeId(String employeeNumber)
			throws SQLModelException, QueryException, ContainerException {
		String employeeId = "";
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.getEmployeeIdModel(employeeNumber);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		RowSet rs = simpleQuery.getSearchResult();
		if (rs != null && rs.getSize() > 0) {
			Row row = rs.getRow(0);
			employeeId = StrUtil.nullToString(row.getStrValue("EMPLOYEE_ID"));
		}
		return employeeId;
	}

	public boolean validateShareStatus(String NewShareStatus)
			throws SQLModelException, QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel.validateNewShareStatus(NewShareStatus);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	public boolean validateContractStatus(String NewContractStatus)
			throws SQLModelException, QueryException {
		boolean hasBarcode = false;
		ImportItemModel eoModel = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = eoModel
				.validateNewContractStatus(NewContractStatus);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			hasBarcode = true;
		}
		return hasBarcode;
	}

	/**
	 * ���ܣ�������Ϣ����EXCEL
	 */
	public File getExportFile() throws DataTransException, SQLModelException {
		ImportItemModel modelProducer = (ImportItemModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getImportErrorModel();
		String reportTitle = "�ʲ�ʵ���������������Ϣ����";
		String fileName = reportTitle + ".xls";
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

	private Map getFieldMap() {
		Map fieldMap = new HashMap();
		fieldMap.put("BOOK_TYPE_CODE", "�ʲ��ʲ�");
		fieldMap.put("BARCODE", "��ǩ��");
		fieldMap.put("NEW_ITEM_NAME", "���ʲ�����");
		fieldMap.put("NEW_ITEM_SPEC", "�¹���ͺ�");
		fieldMap.put("NEW_OBJECT_CODE", "�µص�������");
		fieldMap.put("NEW_RESPONSIBILITY_DEPT", "�����β��Ŵ���");
		fieldMap.put("NEW_EMPLOYEE_NUMBER", "��������Ա�����");
		fieldMap.put("NEW_SPECIALITY_DEPT", "��ʵ�ﲿ�Ŵ���");
		fieldMap.put("NEW_MAINTAIN_DEPT", "��ʹ�ò��Ŵ���");
		fieldMap.put("NEW_MAINTAIN_USER", "��ʹ��������");
		fieldMap.put("NEW_MANUFACTURER_ID", "�³��̴���");
		fieldMap.put("NEW_LNE_ID", "����Ԫ����");
		fieldMap.put("NEW_CEX_ID", "��Ͷ�ʷ������");
		fieldMap.put("NEW_OPE_ID", "��ҵ��ƽ̨����");
		fieldMap.put("NEW_NLE_ID", "�������α���");
		fieldMap.put("NEW_CONSTRUCT_STATUS", "�¹���״̬");
		fieldMap.put("NEW_SHARE_STATUS", "�¹���״̬");
		fieldMap.put("ERROR", "������Ϣ");
		return fieldMap;
	}
}