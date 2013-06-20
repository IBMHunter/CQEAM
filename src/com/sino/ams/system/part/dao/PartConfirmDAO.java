package com.sino.ams.system.part.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.constant.CustMessageKey;
import com.sino.ams.system.part.dto.PartConfirmDTO;
import com.sino.ams.system.part.model.PartConfirmModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.RowSet;
import com.sino.base.db.conn.DBManager;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2007-12-28
 * Time: 9:34:10
 * To change this template use File | Settings | File Templates.
 */
public class PartConfirmDAO extends AMSBaseDAO {

	/**
	 * ���ܣ��豸�����(EAM) ETS_SYSTEM_ITEM ���ݷ��ʲ㹹�캯��
	 * @param userAccount  SfUserDTO ����ϵͳ�����ղ����û�����
	 * @param dtoParameter EtsSystemItemDTO ���β���������
	 * @param conn         Connection ���ݿ����ӣ��ɵ����ߴ��롣
	 */
	public PartConfirmDAO(SfUserDTO userAccount, PartConfirmDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		initSQLProducer(userAccount, dtoParameter);
	}


	/**
	 * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
	 * @param userAccount  BaseUserDTO ��ϵͳ���ղ����û���
	 * @param dtoParameter DTO ���β���������
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		PartConfirmDTO dtoPara = (PartConfirmDTO) dtoParameter;
		super.sqlProducer = new PartConfirmModel((SfUserDTO) userAccount, dtoPara);
	}

	/**
	 * ���ܣ������豸�����(EAM)��ETS_SYSTEM_ITEM�����ݡ�
	 * @param orgIds String[]
	 * @param itemCodes String[]
	 * @return boolean
	 */
	public boolean createData(String[] orgIds, String[] itemCodes) {
		boolean operateResult = false;
		boolean autoCommit = false;
		boolean hasError = true;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			PartConfirmDTO tmpDTO = (PartConfirmDTO) getDTOParameter();

			tmpDTO.setItemCode(getNextItemCode());          //��ȡItemCode
			setDTOParameter(tmpDTO);                        //����dto
//                super.createData();                             //��������

//                DTOSet distrDatas = getDatas(itemCodes);     //��ȡ

//                EtsSysitemDistributeDTO tmpDTO2 = (EtsSysitemDistributeDTO)distrDatas.getDTO(0);
//                EtsSysitemDistributeDAO dao = new EtsSysitemDistributeDAO(SfUser,tmpDTO2,conn);
//
//                dao.deleteItemCodes(itemCodes);
//                dao.createDistriDatas(distrDatas);                                                  //����itemcode���в������
			operateResult = true;
			conn.commit();
			hasError = false;
			getMessage().addParameterValue("�豸�����");
		} catch (SQLException ex) {
			Logger.logError(ex);
			prodMessage(MsgKeyConstant.SQL_ERROR);
//        } catch (DTOException ex) {
//            ex.printLog();
//            prodMessage(MsgKeyConstant.SQL_ERROR);
//        } catch (DataHandleException ex) {
//            ex.printLog();
//            prodMessage(MsgKeyConstant.COMMON_ERROR);
		} finally {
			try {
				if (hasError) {
					conn.rollback();                      //�ع�
				}
				conn.setAutoCommit(autoCommit);          //�ָ���ǰ״̬
			} catch (SQLException ex) {
				Logger.logError(ex);
				prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
			}
		}
		return operateResult;
	}

	private String getNextItemCode() throws SQLException {
		SeqProducer seqProducer = new SeqProducer(conn);
		return StrUtil.nullToString(seqProducer.getStrNextSeq("ETS_SYSTEM_ITEM_S"));
	}


	/**
	 * ���ܣ������豸�����(EAM)��ETS_SYSTEM_ITEM�����ݣ���
	 * @param orgIds String[]
	 * @param itemCode String
	 * @return boolean
	 */
	public boolean updateData(String[] orgIds, String itemCode) {
		boolean operateResult = false;
		boolean autoCommit = false;
		boolean hasError = true;
		try {

			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
//                super.updateData();                                                              //ETS_SYSTEM_ITEM���޸Ĳ���

//                EtsSysitemDistributeDTO DistrDTO = (EtsSysitemDistributeDTO)getDTOParameter();
//                EtsSysitemDistributeDAO dao2 = new EtsSysitemDistributeDAO(SfUser,DistrDTO,conn);
//                dao2.deleteData();                                                               //ETS_SYSITEM_DISTRIBUTEɾ������

			PartConfirmDTO tmpDTO = (PartConfirmDTO) getDTOParameter();                   // ��ȡ���ε�����

//                DTOSet distrDatas = SysItemDataHelper.getDistriDatas(tmpDTO, orgIds);
//                EtsSysitemDistributeDTO tmpDTO2 = new EtsSysitemDistributeDTO();
//                EtsSysitemDistributeDAO dao = new EtsSysitemDistributeDAO(SfUser,tmpDTO2,conn);
//                dao.deleteData(itemCode);
//                dao.createDistriDatas(distrDatas);                                                  //����itemcode���в������
			operateResult = true;

			conn.commit();
			hasError = false;
			getMessage().addParameterValue("�豸�����");
		} catch (SQLException ex) {
			Logger.logError(ex);
			prodMessage(MsgKeyConstant.SQL_ERROR);
//        } catch (DTOException ex) {
//            ex.printLog();
//            prodMessage(MsgKeyConstant.SQL_ERROR);
//        } catch (DataHandleException ex) {
//            ex.printLog();
//            prodMessage(MsgKeyConstant.COMMON_ERROR);

		} finally {
			try {
				if (hasError) {
					conn.rollback();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
				prodMessage(MsgKeyConstant.SQL_ERROR);
			}
		}
		return operateResult;
	}

	/**
	 * ���ܣ�ʹѡ�е� ETS_SYSTEM_ITEM �е�����ʧЧ��ʹ��ETS_SYSTEM_ITEM�е�ENABLEDΪ��N����
	 * @param itemCode String
	 * @return boolean
	 */
	public boolean deleteData(String itemCode) {
		boolean operateResult = false;
		boolean autoCommit = false;
		boolean hasError = true;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			PartConfirmDTO tmpDTO = (PartConfirmDTO) getDTOParameter();
			tmpDTO.setEnabled("N");                                                          //���� ENABLED
			setDTOParameter(tmpDTO);                                                           //����dto
			super.updateData();                                                              //ETS_SYSTEM_ITEM���޸Ĳ���
//        EtsSysitemDistributeDTO tmpDTO2 = new EtsSysitemDistributeDTO();
//        EtsSysitemDistributeDAO dao = new EtsSysitemDistributeDAO(SfUser,tmpDTO2,conn);
//        dao.deleteData(itemCode);
			operateResult = true;
			conn.commit();
			hasError = false;
			getMessage().addParameterValue("�豸�����");
		} catch (SQLException ex) {
			Logger.logError(ex);
			prodMessage(MsgKeyConstant.SQL_ERROR);
		} catch (DataHandleException ex) {
			ex.printLog();
			prodMessage(MsgKeyConstant.COMMON_ERROR);
		} finally {
			try {
				if (hasError) {
					conn.rollback();
				}
				conn.setAutoCommit(autoCommit);
			} catch (SQLException ex) {
				Logger.logError(ex);
				prodMessage(MsgKeyConstant.SQL_ERROR);
			}
		}
		return operateResult;
	}


	public DTOSet getDatas(String[] itemCodes) throws DTOException, QueryException, CalendarException {
		DTOSet distrDatas = new DTOSet();
		PartConfirmDTO dto = null;
//            System.out.println("itemData.getItemCode = " + itemData.getItemCode());
		for (int i = 0; i < itemCodes.length; i++) {
			dto = new PartConfirmDTO();
			dto.setItemCode(itemCodes[i]);

			String[] a = getItem(itemCodes[i]);
//                    for(int j=0; j<a.length; j++){
			dto.setOldItemCode(a[0]);
			dto.setOldItemName(a[1]);
			dto.setOldItemSpec(a[2]);
			dto.setOldItemCategory(a[3]);
			dto.setOldItemUnit(a[4]);
			dto.setVendorId(a[5]);
			dto.setSubmitUser(a[6]);
			dto.setSubmitDate(a[7]);
//                    }
			distrDatas.addDTO(dto);
		}
		return distrDatas;
	}


	/**
	 * ���ܣ�����ObjectCategory��� categoryName
	 * @param itemCode String
	 * @return String[]
	 * @throws QueryException
	 */
	public String[] getItem(String itemCode) throws QueryException {
		String itemName = "";
		String itemSpec = "";
		String createdBy = "";
		String itemCategory = "";
		String itemUnit = "";
		String vendorId = "";
		String creationDate = "";
		String[] a = new String[8];
		PartConfirmDTO partConfirmDTO = (PartConfirmDTO) dtoParameter;
		PartConfirmModel eomodel = new PartConfirmModel((SfUserDTO) userAccount, partConfirmDTO);
		SQLModel sModel = eomodel.getItemSpecNameModel(itemCode);
		SimpleQuery sQuery = new SimpleQuery(sModel, conn);
		sQuery.executeQuery();
		if (sQuery.hasResult()) {
			RowSet row = sQuery.getSearchResult();
			try {
				itemCode = (String) row.getRow(0).getValue("ITEM_CODE");
				itemName = (String) row.getRow(0).getValue("ITEM_NAME");
				itemSpec = (String) row.getRow(0).getValue("ITEM_SPEC");
				itemCategory = (String) row.getRow(0).getValue("ITEM_CATEGORY");
				itemUnit = (String) row.getRow(0).getValue("ITEM_UNIT");
				vendorId = (String) row.getRow(0).getValue("VENDOR_ID");
				createdBy = (String) row.getRow(0).getValue("CREATED_BY");
				creationDate = (String) row.getRow(0).getValue("CREATION_DATE");
				a[0] = itemCode;
				a[1] = itemName;
				a[2] = itemSpec;
				a[3] = itemCategory;
				a[4] = itemUnit;
				a[5] = vendorId;
				a[6] = createdBy;
				a[7] = creationDate;

//               for (int i=0;i<6;i++){
//                a[i] = row.getRow(0).getValue(i).toString();
//               }
			} catch (ContainerException e) {
				e.printStackTrace();
				throw new QueryException();
			}
		}
		return a;
	}


	//
	public void inDTOs(DTOSet dtos) throws DataHandleException, SQLModelException {
		if (dtos != null && dtos.getSize() > 0) {
			int dtoCount = dtos.getSize();
			for (int i = 0; i < dtoCount; i++) {
				PartConfirmDTO dto = (PartConfirmDTO) dtos.getDTO(i);
//                delDTO(dto);
				PartConfirmModel model = (PartConfirmModel) sqlProducer;

				SQLModel sqlModel = model.insertIntoDis(dto);
				DBOperator.updateRecord(sqlModel, conn);
			}
		}
	}


	/**
	 * ���ܣ�ȷ���豸�����(EAM)��ETS_SYSTEM_ITEM�����ݡ�
	 * 1. ��¼���ε�ȷ����Ϣ����  ETS_SYSTEM_ITEM_CHECK
	 * 2. ������� ETS_SYSTEM_ITEM  ��item_code��Ϊ��ʽ���豸( IS_TMP_CODE ��Ϊ��N��)
	 * 3. ������� ETS_SYSITEM_DISTRIBUTE  ��IS_TMP ��Ϊ��N��
	 * @param itemCodes String[]
	 * @return boolean
	 */
	public boolean confirm(String[] itemCodes) {        //1��ԭ����ȷ
		boolean operateResult = false;
		boolean autoCommit = true;
		CallableStatement cStmt = null;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			PartConfirmModel model = (PartConfirmModel)sqlProducer;
			DBOperator.updateRecord(model.confirmModel(itemCodes), conn);
			DBOperator.updateRecord(model.distriModel(itemCodes), conn);
			String sqlStr = "{CALL dbo.ASC_CONFIRM_SYSITEM_DISTRIBUTE ?}";
			cStmt = conn.prepareCall(sqlStr);
			for (int i = 0; i < itemCodes.length; i++) {//zzj add,�����Զ�����������֯����
				cStmt.setString(1, itemCodes[i]);
//				cStmt.registerOutParameter(2, Types.VARCHAR);
				cStmt.execute();
				//zzj add over
				DBOperator.updateRecord(model.insertIntoCheck(itemCodes[i], "Y", "�ظ����", ""), conn);
			}
			getMessage().addParameterValue("�豸����");
			operateResult = true;
		} catch (SQLException ex) {
			Logger.logError(ex);
		} catch (DataHandleException ex) {
			ex.printLog();
		} catch (SQLModelException e) {
			Logger.logError(e);
		} finally {
			try {
				if (!operateResult) {
					conn.rollback();                      //�ع�
					prodMessage(CustMessageKey.ITEM_CONFIRM_FAILURE);
				} else {
					conn.commit();
					prodMessage(CustMessageKey.ITEM_CONFIRM_SUCCESS);
				}
				getMessage().setIsError(!operateResult);
				conn.setAutoCommit(autoCommit);          //�ָ���ǰ״̬
				DBManager.closeDBStatement(cStmt);
			} catch (SQLException ex) {
				Logger.logError(ex);
				prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
			}
		}
		return operateResult;
	}


	/**
	 * ���ܣ������豸�����(EAM)��ETS_SYSTEM_ITEM�����ݡ�
	 * @param Ids String[]
	 * @param itemCode String
	 */
	public void distriData(String[] Ids, String itemCode) {         //4��
		String operateResult = "N";
		boolean autoCommit = false;
		boolean hasError = true;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			PartConfirmDTO tmpDTO = (PartConfirmDTO) getDTOParameter();
			PartConfirmModel model = new PartConfirmModel((SfUserDTO) userAccount, tmpDTO);
			DBOperator.updateRecord(model.distriModel4(Ids), conn);          //����м��Ա� ETS_SYSiTEM_DISTRIBUTE ȷ�ϲ���
			operateResult = "Y";
			conn.commit();
			hasError = false;
			getMessage().addParameterValue("");
		} catch (SQLException ex) {
			Logger.logError(ex);
			prodMessage(MsgKeyConstant.SQL_ERROR);
//        } catch (DTOException ex) {
//            ex.printLog();
//            prodMessage(MsgKeyConstant.SQL_ERROR);
		} catch (DataHandleException ex) {
			ex.printLog();
			prodMessage(MsgKeyConstant.COMMON_ERROR);
		} finally {
			try {
				if (hasError) {
					conn.rollback();                      //�ع�
				}
				conn.setAutoCommit(autoCommit);          //�ָ���ǰ״̬
			} catch (SQLException ex) {
				Logger.logError(ex);
				prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
			}
		}
//         operateResult;
	}

	/**
	 * ���ܣ��滻�豸�����(EAM)��ETS_SYSTEM_ITEM������ʱ��
	 * @param itemCodes [0]:oldItemCode;[1]newItemCode
	 * @return boolean
	 */
	public boolean replaceItemCode(String oldCode, String newCode, String newCategory, String newName, String newSpec) {                 // 2
		boolean operateResult = false;
		boolean autoCommit = false;
		CallableStatement cStmt = null;
		try {
			autoCommit = conn.getAutoCommit();
			conn.setAutoCommit(false);
			String sqlStr = "";
			if(!StrUtil.isEmpty(newCode)){
				sqlStr = "{CALL dbo.ASC_CONFIRM_SYSITEM ?, ?, ? }";
				cStmt = conn.prepareCall(sqlStr);
				cStmt.setString(1, oldCode);
				cStmt.setString(2, newCode);
				cStmt.setInt(3, userAccount.getUserId());
				cStmt.execute();
			} else {
				sqlStr = "{CALL dbo.ASC_CONFIRM_SYSITEM_NEWITEM ?, ?, ?, ?, ? }";
				cStmt = conn.prepareCall(sqlStr);
				cStmt.setString(1, oldCode);
				cStmt.setString(2, newCategory);
				cStmt.setString(3, newName);
				cStmt.setString(4, newSpec);
				cStmt.setInt(5, userAccount.getUserId());
				cStmt.execute();
			}
			operateResult = true;
		} catch (SQLException ex) {
			Logger.logError(ex);
		} finally {
			try {
				if (!operateResult) {
					conn.rollback();                      //�ع�
					prodMessage(CustMessageKey.ITEM_REPLACE_FAILURE);
				} else {
					conn.commit();
					prodMessage(CustMessageKey.ITEM_REPLACE_SUCCESS);
				}
				getMessage().setIsError(!operateResult);
				conn.setAutoCommit(autoCommit);
				DBManager.closeDBStatement(cStmt);
			} catch (SQLException ex) {
				Logger.logError(ex);
				prodMessage(MsgKeyConstant.ROLL_BACK_ERROR);
			}
		}
		return operateResult;
	}

	 /**
	 * ���ܣ�����Excel�ļ���
	 *
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 *
	 */
	public File exportFile2() throws DataTransException {
		File file = null;
//        try {
			PartConfirmModel  partConfirmModel =(PartConfirmModel)sqlProducer;
			SQLModel sqlModel = partConfirmModel.getReasonRateModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setCalPattern(CalendarConstant.LINE_PATTERN);
			rule.setSourceConn(conn);
			String fileName = "ȷ��ԭ��ͳ�Ʊ�.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("COMPANY", "��˾");
			fieldMap.put("MATCH_REASON", "ԭ��");
			fieldMap.put("NUM", "��Ŀ");

			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle("ȷ��ԭ��ͳ�Ʊ���");
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
//        } catch (SQLModelException ex) {
//            ex.printLog();
//            throw new DataTransException(ex);
//        }
		return file;
	}

	   /**
	 * ���ܣ�����Excel�ļ���
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 *
	 */
	public File exportFile1() throws DataTransException {     //
		File file = null;
			PartConfirmModel  partConfirmModel =(PartConfirmModel)sqlProducer;
			SQLModel sqlModel = partConfirmModel.getFalseRateModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
			rule.setCalPattern(CalendarConstant.LINE_PATTERN);
			rule.setSourceConn(conn);
			String fileName = "ȷ�ϴ���ͳ�Ʊ�.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("USER_NAME", "��Ա");
			fieldMap.put("COMPANY_NAME", "����");
			fieldMap.put("ERROR_NUM", "�������");
			fieldMap.put("CORRECT_NUM", "��ȷ����");
			fieldMap.put("RATE", "������");

			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle("ȷ�ϴ���ͳ�Ʊ���");
			custData.setReportPerson(userAccount.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		return file;
	}
}
