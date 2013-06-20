package com.sino.pda;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.file.FileProcessor;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.config.SinoConfig;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.constant.AmsOrderConstant;
import com.sino.ams.constant.DictConstant;
import com.sino.ams.system.fixing.dto.EtsItemInfoDTO;
import com.sino.ams.system.item.assistant.SystemItemUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.workorder.dto.EtsWorkorderBatchDTO;
import com.sino.ams.workorder.dto.EtsWorkorderDTO;
import com.sino.ams.workorder.model.WorkorderModel;
import com.sino.ams.workorder.util.WorkOrderUtil;
import com.sino.pda.model.PDAOrderUtilModel;
import com.sino.pda.util.XmlUtil;
import org.jdom.Element;

/**
 * User: zhoujs
 * Date: 2008-1-29
 * Time: 18:24:38
 * Function:����PDA��������
 */
public class PDAOrderUtil {

	private static List netWorders = new ArrayList();//���Ʊ�������Ĺ���Ϊ�����๤��
    private static List itemOrders=new ArrayList();
	static{
		netWorders.add("�½�");
		netWorders.add("����");
		netWorders.add("Ѳ��");
		netWorders.add("ά��");
		netWorders.add("��Ǩ");
		netWorders.add("����");
		netWorders.add("�滻");
		netWorders.add("����");
		netWorders.add("����");

        itemOrders.add("����");
        itemOrders.add("Ѳ��");
//        itemOrders.add("�㹺");
	}

	/**
	 * PDA����Ѳ�칤��
	 * @param conn
	 * @param xml
	 * @return String
	 * @throws QueryException
	 * @throws ContainerException
	 * @throws DataHandleException
	 */
	public String createWorkorder(Connection conn, XmlUtil xml) throws QueryException, ContainerException, DataHandleException {

		boolean operatorResult = false;
		String workorderNo = "";
		List allOrderNos = xml.getAllRootChildren();
		String groupId = xml.getElementAttrValue(xml.getRootElement(), "GroupID");
		String orderTypeDesc = xml.getElementAttrValue(xml.getRootElement(), "type");
		String orderType = PDAUtil.getOrderType(conn, orderTypeDesc);

		if (orderType.equals("")) {
			Logger.logError(" can't get workorder type info from system[ orderTypeDesc=" + orderTypeDesc + "]");
			operatorResult = false;
		} else {
			String createUser = "", objectNo = "", archived_by = "", scan_category = "",costCenterCode="",projectId="";
			int count = allOrderNos.size();
			for (int j = 0; j < count; j++) {
				Element xmlWO = xml.getNthElement(allOrderNos, j);
				if (xml.getElementName(xmlWO).equals("created_by")) {
					createUser = xml.getElementAttrValue(xmlWO, "name");
				}
				if (xml.getElementName(xmlWO).equals("location")) {
					objectNo = xml.getElementAttrValue(xmlWO, "object_no");
				}
				if (xml.getElementName(xmlWO).equals("archived_by")) {
					archived_by = xml.getElementAttrValue(xmlWO, "user_id");
				}
				if (xml.getElementName(xmlWO).equals("scan_category")) {
					scan_category = xml.getElementAttrValue(xmlWO, "scan_category");
				}
				if (xml.getElementName(xmlWO).equals("costCode")) {  //ȡ�ɱ�����
					costCenterCode = xml.getElementAttrValue(xmlWO, "costCode");
				}
                if (xml.getElementName(xmlWO).equals("projectId")) {  //��Ŀ���
					projectId = xml.getElementAttrValue(xmlWO, "projectId");
				}
			}
			scan_category = StrUtil.isEmpty(scan_category) ? AmsOrderConstant.scanAllItemCategory : scan_category;


			SfUserDTO userAccount = PDAUtil.getUserInfo(conn, createUser);
			int userId = userAccount.getUserId();
			int organizationId = userAccount.getOrganizationId();

			boolean validateResult = true;
			if (userId ==0) {
				Logger.logInfo("can't get userinfo:" + createUser + " orderTypeDesc=" + orderTypeDesc + " objectNo=" + objectNo);
				validateResult = false;
			}
			if (StrUtil.isEmpty(objectNo)) {
				Logger.logError("�Ƿ��Ĺ�������ص�");
				validateResult = false;
			}
			//����Ƿ����û����ɵĹ��� --�ݲ����
			if (hasRecord(conn, objectNo, groupId, scan_category)) {
				Logger.logError("��ǰ�������û����ɵ�Ѳ�칤��");
				workorderNo = AmsOrderConstant.doubleOrder;
				validateResult = false;
			}
			 if (exceedMaxCount(conn, objectNo)) {
	                Logger.logError("�õص��豸��������ϵͳ�趨���ֵ"+ SinoConfig.getMaxItemCount()+"���������Ѳ��");
	                workorderNo = AmsOrderConstant.EXCEED_ORDER;
	                validateResult = false;
	            }
			//check group and location
//			if (!validLocation(conn, objectNo, groupId)) {
//				Logger.logError("��ǰ�û������������ǰ����ص��Ѳ�칤��");
//				workorderNo = "";
//				validateResult = false;
//			}

			if (validateResult) {
				String batchNo = WorkOrderUtil.getWorkorderBatchNo(conn);
				String orderNo = WorkOrderUtil.getWorkorderNo(batchNo, conn);

				String batchName = "PDA��������-" + getObjectCode(conn, objectNo);

				EtsWorkorderBatchDTO batchDTO = new EtsWorkorderBatchDTO();
				batchDTO.setWorkorderBatch(batchNo);
				batchDTO.setWorkorderBatchName(batchName);
				batchDTO.setWorkorderType(orderType);
				batchDTO.setCreatedBy(userId);
				batchDTO.setDistributeGroupId(StrUtil.strToInt(groupId));

				String cateDesc = getCatDescByGroup(groupId, conn);

				EtsWorkorderDTO orderDTO = new EtsWorkorderDTO();
				orderDTO.setWorkorderBatch(batchNo);
				orderDTO.setWorkorderNo(orderNo);
				orderDTO.setWorkorderType(orderType);
				orderDTO.setWorkorderObjectNo(objectNo);
				orderDTO.setGroupId(StrUtil.strToInt(groupId));
				orderDTO.setCreatedBy(userId);
				orderDTO.setOrganizationId(organizationId);
				orderDTO.setRemark(batchName);
				orderDTO.setDistributeGroup(StrUtil.strToInt(groupId));
				orderDTO.setAttribute4(cateDesc);
				orderDTO.setCheckoverBy(StrUtil.strToInt(archived_by));
				orderDTO.setImplementBy(userId);
				orderDTO.setAttribute7(scan_category);
				orderDTO.setCostCenterCode(costCenterCode);
                orderDTO.setPrjId(projectId);

				operatorResult = createOrder(conn, batchDTO, orderDTO);
				if (operatorResult) {
					workorderNo = orderDTO.getWorkorderNo();
				}
			}
		}

		return workorderNo;
	}

	/**
	 * PDA���ع���
	 * @param conn         ���ݿ�����
	 * @param sfUser       �û�DTO
	 * @param m_force      �Ƿ�ǿ�����������嵥
	 * @param provinceCode ʡ���루ɽ��-41��
	 * @return String
	 */
	public String getAllWorkorder(Connection conn, SfUserDTO sfUser, String m_force, String provinceCode) {
		SQLModel sqlModel = new SQLModel();

		OrderModel orderModel = new OrderModel();
		WorkorderModel workorderModel = new WorkorderModel();
		boolean forceDownload = StrUtil.nullToString(m_force).equalsIgnoreCase("Y");
		forceDownload = true;

		StringBuffer returnStr = new StringBuffer("");
		try {
			sqlModel = orderModel.getOrdersModel(sfUser, forceDownload);
			SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
			simpleQuery.executeQuery();
			boolean isSuccess = true;
			if (simpleQuery.hasResult()) {
				RowSet rs = simpleQuery.getSearchResult();
				Row row = null;
				for (int i = 0; i < rs.getSize(); i++) {
					row = rs.getRow(i);
					String prjId=row.getStrValue("PRJ_ID");//���̱���
					String workorderNo = row.getStrValue("WORKORDER_NO");
					String objectNo = row.getStrValue("WORKORDER_OBJECT_NO");
					String orderTypeDesc = row.getStrValue("WORKORDER_TYPE_DESC");
					returnStr.append("<workorder id=\"").append(workorderNo).append("\"");
					returnStr.append(" type=\"").append(orderTypeDesc).append("\"");
					returnStr.append(" object_no=\"").append(objectNo).append("\"");
					returnStr.append(" location=\"").append(PDAUtil.xmlFormat(row.getStrValue("WORKORDER_OBJECT_LOCATION"))).append("\"");
					returnStr.append(" creation_date=\"").append(row.getStrValue("CREATION_DATE")).append("\"");
					returnStr.append(" creator=\"").append(row.getStrValue("CREATE_USER")).append("\"");
					returnStr.append(" start_date=\"").append(row.getStrValue("START_DATE") != null && !row.getStrValue("START_DATE").equals("") ? row.getStrValue("START_DATE").substring(0, 10) : row.getStrValue("START_DATE")).append("\"");
					returnStr.append(" period=\"").append(row.getStrValue("IMPLEMENT_DAYS")).append("\"");
					returnStr.append(" deadline_date=\"").append(row.getStrValue("DEADLINE_DATE")).append("\"");
					returnStr.append(" AssignDate=\"").append(row.getStrValue("DISTRIBUTE_DATE")).append("\"");
					returnStr.append(" signedDate=\"").append(row.getStrValue("DOWNLOAD_DATE")).append("\"");


					if (StrUtil.isEmpty(row.getStrValue("IMPLEMENT_USER"))) {
						returnStr.append(" IsGroup=\"Y\"");
					} else {
						returnStr.append(" IsGroup=\"N\"");
					}
					returnStr.append(" GroupID=\"").append(row.getStrValue("GROUP_ID")).append("\"");
					returnStr.append(" projectname=\"").append(PDAUtil.xmlFormat(row.getStrValue("PRJ_NAME"))).append("\"");
					returnStr.append(" category=\"").append(row.getStrValue("CATEGORY")).append("\"");
					returnStr.append(" inspect_range=\"").append(row.getStrValue("INSPECT_RANGE")).append("\"");
					returnStr.append(" costCode=\"").append(row.getStrValue("COST_CENTER_CODE")).append("\"");
//                    returnStr.append(" new_object_no=\"").append(PDAUtil.xmlFormat(row.getStrValue("NEW_OBJECT_NO"))).append("\"");
//                    returnStr.append(" new_location=\"").append(PDAUtil.xmlFormat(row.getStrValue("NEWOBJNAME"))).append("\"");

					returnStr.append(" > \n");

					returnStr = printAllItemInfoByWo(sfUser, workorderNo, returnStr, conn, orderTypeDesc, provinceCode,prjId);
					returnStr = printAllConfigInfoByWo(workorderNo, objectNo, orderTypeDesc, returnStr, conn,prjId);
//
					returnStr.append("</workorder>\n");
//                    isGroup = true;
//                    if (!isGroup) {
					List sqlModList = new ArrayList();

					//���¹���״̬
					sqlModel = workorderModel.getUpdateOrderStatusModel(workorderNo, sfUser, String.valueOf (DictConstant.WOR_STATUS_DOWNLOAD));
					sqlModList.add(sqlModel);
					//���¹�������
					sqlModel = workorderModel.getUpdateOrderProcessModel(workorderNo, DictConstant.WORKORDER_NODE_DOWNLOADED, true);
					sqlModList.add(sqlModel);

					isSuccess = DBOperator.updateBatchRecords(sqlModList, conn);
					if (!isSuccess) {
						Logger.logError("���¹�����Ϣʧ�ܣ�");
					}
//                    }
				}
			}

			if (!isSuccess) {
				returnStr = new StringBuffer("");
			}
		} catch (DataHandleException e) {
			Logger.logError("��ȡ������Ϣʧ�ܣ�" + e.toString());
		} catch (QueryException e) {
			e.printStackTrace();
			Logger.logError("��ȡ������Ϣʧ�ܣ�" + e.toString());
		} catch (ContainerException e) {
			Logger.logError("��ȡ������Ϣʧ�ܣ�" + e.toString());
			e.printStackTrace();
		}

		return returnStr.toString();
	}

	/**
	 * PDA�ύ����(���ʲ��๤��)
	 * @param conn
	 * @param xml
	 * @param sfUser
	 * @param filePath
	 * @param webPath
	 * @return
	 */
	public boolean submitOrder(Connection conn, XmlUtil xml, SfUserDTO sfUser, String filePath, String webPath) {
		boolean submitResult = true;//������Ҫ��������ʲ���ͬʱ�ɹ�,���ύ����,��Ӧ��Ϊtrue,��ʹû�д����κι���.
		try {
			SQLModel sqlModel = new SQLModel();
			OrderModel orderModel = new OrderModel();
			WorkorderModel workorderModel = new WorkorderModel();
			SystemItemUtil systemItemUtil = new SystemItemUtil();
			boolean isSuccess = false;

			List workorders = xml.getAllRootChildren();
			int workorderCount = workorders.size();
			String workorderNo, workorderTypeDesc, objectNo, loc,scanoverDate;
			for (int i = 0; i < workorderCount; i++) { //����ѭ��
				Element xmlWO = xml.getNthElement(workorders, i);
				workorderNo = xml.getElementAttrValue(xmlWO, "id");
				workorderTypeDesc = xml.getElementAttrValue(xmlWO, "type");
                objectNo = xml.getElementAttrValue(xmlWO, "object_no");
				if (!netWorders.contains(workorderTypeDesc)) { //���������๤���б��У����账��
					continue;
				}
//            if (workorderTypeDesc.equals("�̵�")) {
//                continue;
//            }

				/******************************************
				 *********** backup workorder file*********
				 ******************************************/

				String bkFlName = "No_" + workorderNo + ".xml";
				String bkFile = webPath + System.getProperty("file.separator") + bkFlName;
				File kk = new File(bkFile);
				FileProcessor fp = new FileProcessor();

				fp.copyFile(filePath, bkFile);

				loc = xml.getElementAttrValue(xmlWO, "location");
				scanoverDate = xml.getElementAttrValue(xmlWO, "scanoverDate");
				String workorderType = PDAUtil.getOrderType(conn, workorderTypeDesc);

				//��������Ѿ����ύ�������¸�����
				if (hasSubmit(conn, workorderNo)) {
					continue;
				}

				/**
				 * A:���¹���״̬
				 */

				sqlModel = orderModel.getUpdateUploadOrderModel(workorderNo, scanoverDate);

				isSuccess = DBOperator.updateRecord(sqlModel, conn);

				if (!isSuccess) {
					submitResult = false;
					break;
				}

				/**
				 * B: ���¹���������
				 */
				sqlModel = workorderModel.getUpdateOrderProcessModel(workorderNo, DictConstant.WORKORDER_NODE_UPLODADED, true);

				isSuccess = DBOperator.updateRecord(sqlModel, conn);

				if (!isSuccess) {
					Logger.logError("PDA ���¹���������ʧ�ܡ���");
					break;
				}

				/**
				 * C:д��dtl��
				 */
				List xmlAllBarCodes = xml.getAllChild(xmlWO);

				int itemCount = xmlAllBarCodes.size();

				for (int j = 0; j < itemCount; j++) {

					Element barcodeXml = xml.getNthElement(xmlAllBarCodes, j);
					if (!barcodeXml.getName().equals("item")) {
						break;
					}
					String status = xml.getElementAttrValue(barcodeXml, "status");
					String m_isnew = xml.getElementAttrValue(barcodeXml, "manual");

					EtsItemInfoDTO etsItemInfo = getEtsItemInfo(xml, barcodeXml);

					etsItemInfo.setOrganizationId(sfUser.getOrganizationId());

					etsItemInfo = systemItemUtil.checkSysItem(conn, etsItemInfo);

					List sqlModelList = new ArrayList();
					sqlModel = orderModel.getInsertDtlModel(etsItemInfo, workorderNo, status);
					sqlModelList.add(sqlModel);
                    sqlModel = orderModel.getUpdateDtlModel(workorderNo,objectNo);
                    sqlModelList.add(sqlModel);
					sqlModel = orderModel.getInsertInterfaceModel(etsItemInfo, workorderNo, status);
					sqlModelList.add(sqlModel);
					isSuccess = DBOperator.updateBatchRecords(sqlModelList, conn);
				}
				submitResult = true;
			}
		} catch (Exception ex) {
			Logger.logError(ex);
			submitResult = false;
		}
		return submitResult;
	}


	/**
	 * ��ȡ�����ص��µĵ�ǰ�豸
	 * @param sfUser       SfUserDTO
	 * @param orderNo      String
	 * @param strBuff      StringBuffer
	 * @param conn         Connection
	 * @param orderType    String
	 * @param provinceCode String
	 * @return StringBuffer
	 */
	private StringBuffer printAllItemInfoByWo(SfUserDTO sfUser, String orderNo, StringBuffer strBuff, Connection conn, String orderType, String provinceCode,String prjId) {

//		if ((orderType.equals("Ѳ��"))) {
		if (itemOrders.contains(orderType)) {
			OrderModel orderModel = new OrderModel();
			SQLModel sqlModel = new SQLModel();
			String sql = "";

			/**
			 * �޸�Ŀ�ģ���������Ѳ�칤��ʱ��Ҫ�õ�ets_workorder��attribute7��ֵ
			 */
			try {

				String itemCategory = getItemCategoryOfOrder(conn, orderNo);

				String costCenterCode = getCostCenterOfOrder(conn, orderNo);


				if (provinceCode.equals("41")) {//ɽ�����ղ������ض�Ӧ���豸�嵥��������û�ж�Ӧ��MIS���ţ�����Ը�����
					if (StrUtil.isEmpty(costCenterCode)) {
						sqlModel = orderModel.getItemsModel(orderNo, itemCategory, sfUser.getOrganizationId());
//                        if (orderType.equals("����")&&!prjId.equals(DictConstant.ZERO_PRJ_ID)) {
//                            sqlModel = orderModel.getOrderItemsModel(orderNo, itemCategory, sfUser.getOrganizationId());
//                        }else if(orderType.equals("����")&&prjId.equals(DictConstant.ZERO_PRJ_ID)){
//                        	
//                        }
                        if (orderType.equals("����")) {
                            sqlModel = orderModel.getOrderItemsModel(orderNo, itemCategory, sfUser.getOrganizationId());
                        }
					} else {
						sqlModel = orderModel.getCostCenterItemsModel(orderNo, itemCategory, sfUser.getOrganizationId(), costCenterCode);
						if (orderType.equals("����")&&prjId.equals(DictConstant.ZERO_PRJ_ID)) {
                            sqlModel = orderModel.getZeroCostCenterItemsModel(orderNo, itemCategory, sfUser.getOrganizationId(), costCenterCode);
                        }
					}
				} else {
					sqlModel = orderModel.getItemsModel(orderNo, itemCategory, sfUser.getOrganizationId());
                    if(orderType.equals("����")&&!prjId.equals(DictConstant.ZERO_PRJ_ID)){
                        sqlModel=orderModel.getOrderItemsModel(orderNo, itemCategory, sfUser.getOrganizationId());
                    }
                    if (orderType.equals("����")&&prjId.equals(DictConstant.ZERO_PRJ_ID)) {
                        sqlModel = orderModel.getZeroCostCenterItemsModel(orderNo, itemCategory, sfUser.getOrganizationId(), costCenterCode);
                    }
				}

				Logger.logInfo("���ع���--�豸��Ϣ�� " + sql);
				SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
				simpleQuery.executeQuery();
				if (simpleQuery.hasResult()) {
					RowSet rowSet = simpleQuery.getSearchResult();
					Row row = null;
					for (int i = 0; i < rowSet.getSize(); i++) {
						row = rowSet.getRow(i);
						strBuff.append("<item code=\"").append(row.getStrValue("BARCODE")).append("\"");
						strBuff.append(" name=\"").append(PDAUtil.xmlFormat(row.getStrValue("ITEM_NAME"))).append("\"");
						strBuff.append(" type=\"").append(PDAUtil.xmlFormat(row.getStrValue("ITEM_SPEC"))).append("\"");
						strBuff.append(" status=\"6\"");
						strBuff.append(" quantity=\"").append(row.getStrValue("ITEM_QTY")).append("\"");
						strBuff.append(" item_code=\"").append(row.getStrValue("ITEM_CODE")).append("\"");
						strBuff.append(" category=\"").append(row.getStrValue("ITEM_CATEGORY")).append("\"");
						strBuff.append(" vendor_barcode=\"").append(row.getStrValue("VENDOR_BARCODE")).append("\"");
						strBuff.append(" start_date=\"").append(row.getStrValue("START_DATE") != null && !row.getStrValue("START_DATE").equals("") ? row.getStrValue("START_DATE").substring(0, 10) : row.getStrValue("START_DATE")).append("\"");
						strBuff.append(" address_id=\"").append(row.getStrValue("ADDRESS_ID")).append("\"");
						strBuff.append(" assign_groupid=\"").append(row.getStrValue("ASSIGN_GROUPID")).append("\"");// ���β���
						strBuff.append(" assign_userid=\"").append(row.getStrValue("ASSIGN_USERID")).append("\"");//������
						strBuff.append(" box_no=\"").append(row.getStrValue("BOX_NO")).append("\"");
						strBuff.append(" net_unit=\"").append(row.getStrValue("NET_UNIT")).append("\"");
						strBuff.append(" parent_barcode=\"").append(row.getStrValue("PARENT_BARCODE")).append("\"");
						strBuff.append(" username=\"").append(row.getStrValue("USERNAME")).append("\"");//ʹ����
						strBuff.append(" contentCode=\"").append(row.getStrValue("CONTENT_CODE")).append("\"");//ʹ����
						strBuff.append(" contentName=\"").append(row.getStrValue("CONTENT_NAME")).append("\"");//ʹ����
						strBuff.append(" shareStatus=\"").append(row.getStrValue("SHARE_STATUS")).append("\"");//ʹ����
						strBuff.append(" constructStatus=\"").append(row.getStrValue("CONSTRUCT_STATUS")).append("\"");//ʹ����
						strBuff.append(" lneId=\"").append(row.getStrValue("LNE_ID")).append("\"");//ʹ����
						strBuff.append(" cexId=\"").append(row.getStrValue("CEX_ID")).append("\"");//ʹ����
						strBuff.append(" opeId=\"").append(row.getStrValue("OPE_ID")).append("\"");//ʹ����
						strBuff.append(" nleId=\"").append(row.getStrValue("NLE_ID")).append("\"");//ʹ����

						strBuff.append("/> \n");
					}
				}
			} catch (QueryException e) {
				Logger.logError("��ȡ����Ѳ��ص��豸��Ϣ����" + e.toString());
			} catch (ContainerException e) {
				Logger.logError("��ȡ����Ѳ��ص��豸��Ϣ����" + e.toString());
			}
		}
		return strBuff;
	}

	private StringBuffer printAllConfigInfoByWo(String workorderNo, String objNo, String orderType, StringBuffer m_sb, Connection conn,String prjId) {
		OrderModel orderModel = new OrderModel();
		String conDesc = "", newConDesc = "", workorderType = "", itemCategory = "";
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sql;

		try {
			SimpleQuery simpleQuery;

			//get config description infomation
			sql = "SELECT EW.ATTRIBUTE5 NEWCONDESC,EOA.ATTRIBUTE65 CONDESC\n" +
					"  FROM ETS_OBJECT_ATTRIBUTE EOA, ETS_WORKORDER EW, ETS_OBJECT EO\n" +
					" WHERE EO.WORKORDER_OBJECT_NO = EW.WORKORDER_OBJECT_NO\n" +
					"   AND EO.WORKORDER_OBJECT_NO *= EOA.OBJECT_NO\n" +
					" AND EW.WORKORDER_NO=?";
			sqlArgs.add(workorderNo);
			sqlModel.setSqlStr(sql);
			sqlModel.setArgs(sqlArgs);

			simpleQuery = new SimpleQuery(sqlModel, conn);
			simpleQuery.executeQuery();

			if (simpleQuery.hasResult()) {
				Row row = simpleQuery.getSearchResult().getRow(0);
				conDesc = row.getStrValue("CONDESC");
				newConDesc = row.getStrValue("NEWCONDESC");
			}

			//get config information

			sqlModel = orderModel.getNewConfigModel(workorderNo);
//			if (prjId.equals(DictConstant.ZERO_PRJ_ID)) {
//				sqlModel = orderModel.getNewConfigModelByPrjId(workorderNo,prjId);
//			}

			simpleQuery = new SimpleQuery(sqlModel, conn);
			simpleQuery.executeQuery();

			m_sb.append("<new_config description=\"").append(PDAUtil.xmlFormat(newConDesc)).append("\">\n");

			if (simpleQuery.hasResult()) {
				RowSet rowSet = simpleQuery.getSearchResult();
				Row row = null;
				for (int i = 0; i < rowSet.getSize(); i++) {
					row = rowSet.getRow(i);
					m_sb.append("<item name=\"").append(PDAUtil.xmlFormat(row.getStrValue("ITEM_NAME"))).append("\"");
					m_sb.append(" type=\"").append(PDAUtil.xmlFormat(row.getStrValue("ITEM_SPEC"))).append("\"");
					m_sb.append(" qty=\"").append(row.getStrValue("ITEM_QTY")).append("\"");
					m_sb.append("/>\n");
				}
			}
			m_sb.append("</new_config>\n");

			/**
			 * �жϣ�������������Ѳ�칤��ʱ������attribute7�·���վ������Ϣ
			 * �޸��ˣ�wangwenbin
			 * ��2006-5-11�޸�
			 */
			if (orderType.equals("Ѳ�칤��")) {
				String attr_sql = "SELECT EW.ATTRIBUTE7  FROM ETS_WORKORDER EW WHERE EW.WORKORDER_NO=?";
				sqlArgs.clear();
				sqlArgs.add(workorderNo);
				sqlModel.setSqlStr(attr_sql);
				sqlModel.setArgs(sqlArgs);
				simpleQuery = new SimpleQuery(sqlModel, conn);
				simpleQuery.executeQuery();
				if (simpleQuery.hasResult()) {
					Row row = simpleQuery.getSearchResult().getRow(0);
					itemCategory = row.getStrValue("ATTRIBUTE7");
					if (StrUtil.isEmpty(itemCategory)) {
						itemCategory = AmsOrderConstant.scanAllItemCategory;
					}
				}
				if (itemCategory.equalsIgnoreCase("ALL")) {
					itemCategory = AmsOrderConstant.scanAllItemCategory;
				}
			}


			Logger.logInfo("���ع���--<old_config>��" + sql);

			sqlModel = orderModel.getCurrentConfigModel(objNo, itemCategory);
			if (prjId.equals(DictConstant.ZERO_PRJ_ID)) {
				sqlModel = orderModel.getCurrentConfigModelByPrjId(objNo, itemCategory, prjId);
		    }
			simpleQuery = new SimpleQuery(sqlModel, conn);
			simpleQuery.executeQuery();


			m_sb.append("<old_config description=\"").append(PDAUtil.xmlFormat(conDesc)).append("\">\n");
			if (simpleQuery.hasResult()) {
				RowSet rowSet = simpleQuery.getSearchResult();
				Row row;
				for (int i = 0; i < rowSet.getSize(); i++) {
					row = rowSet.getRow(i);
					m_sb.append("<item name=\"").append(PDAUtil.xmlFormat(row.getStrValue("ITEM_NAME"))).append("\"");
					m_sb.append(" type=\"").append(PDAUtil.xmlFormat(row.getStrValue("ITEM_SPEC"))).append("\"");
					m_sb.append(" qty=\"").append(row.getStrValue("NCOUNT")).append("\"");
					m_sb.append("/> \n");
				}
			}
			m_sb.append("</old_config>\n");

		} catch (QueryException e) {
			Logger.logError("��ȡ�����ص�������Ϣ����" + e.toString());
		} catch (ContainerException e) {
			Logger.logError("��ȡ�����ص�������Ϣ����" + e.toString());
		}
		return m_sb;
	}

	/**
	 * ��鹤���Ƿ��Ѿ����ύ
	 * @param conn        Connection
	 * @param workorderNo String
	 * @return boolean
	 */
	private boolean hasSubmit(Connection conn, String workorderNo) {
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();

		String sqlStr = "SELECT COUNT(1) NCOUNT\n" +
				"  FROM ETS_WORKORDER EW\n" +
				" WHERE EW.WORKORDER_NO = ?\n" +
				"   AND WORKORDER_FLAG=?";
		sqlArgs.add(workorderNo);
		sqlArgs.add(DictConstant.WOR_STATUS_DOWNLOAD);
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		String count = "";

		try {
			SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
			simpleQuery.executeQuery();

			if (simpleQuery.hasResult()) {
				Row row = simpleQuery.getSearchResult().getRow(0);
				count = row.getStrValue("NCOUNT");
			}

		} catch (QueryException e) {
			Logger.logError(e);
			e.printStackTrace();
		} catch (ContainerException e) {
			Logger.logError(e);
			e.printStackTrace();
		}


		return count.equals("0");
	}


	private String getObjectCode(Connection conn, String objectNo) throws QueryException, ContainerException {

		String objectCode = "";
		String sql = "SELECT EO.WORKORDER_OBJECT_CODE\n" +
				"  FROM ETS_OBJECT EO\n" +
				" WHERE EO.WORKORDER_OBJECT_NO = ?";
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		sqlArgs.add(objectNo);
		sqlModel.setSqlStr(sql);
		sqlModel.setArgs(sqlArgs);

		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			objectCode = simpleQuery.getFirstRow().getStrValue("WORKORDER_OBJECT_CODE");
		}

		return objectCode;
	}
	private boolean exceedMaxCount(Connection conn, String objectNo) {
        boolean isValidate = false;
        SQLModel sqlModel = new SQLModel();
        String sqlStr =
                "SELECT COUNT(1)\n" +
                        "  FROM AMS_OBJECT_ADDRESS AOA, ETS_ITEM_INFO EII\n" +
                        " WHERE EII.ADDRESS_ID = AOA.ADDRESS_ID\n" +
                        "   AND AOA.OBJECT_NO = ?";

        List sqlArgs = new ArrayList();
        sqlArgs.add(objectNo);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);

        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        try {
            simpleQuery.executeQuery();
            if (simpleQuery.hasResult()) {
                int count = Integer.parseInt(String.valueOf(simpleQuery.getFirstRow().getStrValue(0)));
                isValidate = count > SinoConfig.getMaxItemCount();
            }
        } catch (QueryException e) {
            e.printLog();
        } catch (ContainerException e) {
            e.printLog();
        }

        return isValidate;
    }
	private boolean hasRecord(Connection conn, String objectNo, String groupId, String scan_category) throws QueryException, ContainerException {
		//10-������11-���·�,12-�����أ�13-�����,14-�Ѻ�ʵ,15-��ȡ��

		String sql = "", catSql = "", category = "";
		boolean flag = false;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();

		sql = "SELECT EW.*\n" +
				"  FROM ETS_WORKORDER EW, ETS_OBJECT EO\n" +
				" WHERE EW.WORKORDER_OBJECT_NO = EO.WORKORDER_OBJECT_NO\n" +
				"   AND EW.WORKORDER_TYPE = '12'\n" +
				"   AND EW.WORKORDER_FLAG IN ('11','12','13')\n" +
				"   AND EW.WORKORDER_OBJECT_NO = ?\n" +
				"   AND ( " + SyBaseSQLUtil.isNull() + "  OR EW.ATTRIBUTE7=?)";

		sqlArgs.clear();
		sqlArgs.add(objectNo);
		sqlArgs.add(scan_category);
		sqlArgs.add(scan_category);

		sqlModel.setSqlStr(sql);
		sqlModel.setArgs(sqlArgs);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		flag = simpleQuery.hasResult();

		return flag;
	}

	private boolean validLocation(Connection conn, String objectNo, String groupId) throws QueryException {
		String sql = "SELECT *\n" +
				"  FROM ETS_OBJECT EO, ETS_OBJECT_CAT_GROUP EOCG\n" +
				" WHERE EO.OBJECT_CATEGORY = EOCG.OBJECT_CATEGORY\n" +
				"   AND EO.WORKORDER_OBJECT_NO = ?\n" +
				"   AND EOCG.GROUP_ID = ?";
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		sqlArgs.add(objectNo);
		sqlArgs.add(groupId);
		sqlModel.setSqlStr(sql);
		sqlModel.setArgs(sqlArgs);

		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();

//        return simpleQuery.hasResult();//todo ���
		return true;
	}

	/**
	 * ȡ������Ӧ��רҵ,
     * ��������ȥ�����רҵ�Ĺ�ϵ
	 * @param groupId
	 * @param conn
	 * @return String
	 */
	private String getCatDescByGroup(String groupId, Connection conn) {
		String catDesc = "", cat = "";
//		String sql = "SELECT SG.CATEGORY FROM SF_GROUP SG WHERE SG.GROUP_ID = ?";
//		SQLModel sqlModel = new SQLModel();
//		List sqlArgs = new ArrayList();
//		sqlArgs.add(groupId);
//		sqlModel.setSqlStr(sql);
//		sqlModel.setArgs(sqlArgs);
//
//		try {
//			SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
//			simpleQuery.executeQuery();
//			if (simpleQuery.hasResult()) {
//				cat = simpleQuery.getFirstRow().getStrValue("CATEGORY");
//			}
//			if (cat.equals("BTS")) {
//				catDesc = "��վרҵ";
//			} else if (cat.equals("EXCHG")) {
//				catDesc = "����רҵ";
//			} else if (cat.equals("BSC")) {
//				catDesc = "���רҵ";
//			} else if (cat.equals("TRANS")) {
//				catDesc = "����רҵ";
//			} else if (cat.equals("DATA")) {
//				catDesc = "����רҵ";
//			} else if (cat.equals("ELEC")) {
//				catDesc = "����רҵ";
//			} else if (cat.equals("NETOP")) {
//				catDesc = "����רҵ";
//			} else if (cat.equals("OTHERS")) {
//				catDesc = "����רҵ";
//			}
//		} catch (Exception e) {
//			Logger.logError(e.toString());
//		}
		return catDesc;
	}


	/**
	 * PDA�������������������������������ȣ�
	 * @param conn
	 * @param workorderBatchDTO
	 * @param workorderDTO
	 * @return
	 * @throws DataHandleException
	 */
	private boolean createOrder(Connection conn, EtsWorkorderBatchDTO workorderBatchDTO, EtsWorkorderDTO workorderDTO) throws DataHandleException {
		boolean flag = false;
		PDAOrderUtilModel pdaOrderUtilModel = new PDAOrderUtilModel();
		List sqlModList = new ArrayList();

		sqlModList.add(pdaOrderUtilModel.getInserBatchModel(workorderBatchDTO));
		sqlModList.add(pdaOrderUtilModel.getInsertOrderModel(workorderDTO));
		sqlModList.add(pdaOrderUtilModel.getAddProcessModel(workorderBatchDTO.getWorkorderBatch(), workorderDTO.getWorkorderNo()));

		flag = DBOperator.updateBatchRecords(sqlModList, conn);

		return flag;
	}

	/**
	 * ȡɨ����
	 * @param xml
	 * @param barcodeXml
	 * @return
	 */
	private EtsItemInfoDTO getEtsItemInfo(XmlUtil xml, Element barcodeXml) {

		String qty = xml.getElementAttrValue(barcodeXml, "quantity");
		if (StrUtil.isEmpty(qty)) qty = "1";

		EtsItemInfoDTO etsItemInfoDTO = new EtsItemInfoDTO();
		etsItemInfoDTO.setBarcode(xml.getElementAttrValue(barcodeXml, "code"));
		etsItemInfoDTO.setItemCode(xml.getElementAttrValue(barcodeXml, "item_code"));
		etsItemInfoDTO.setItemName(xml.getElementAttrValue(barcodeXml, "name"));
		etsItemInfoDTO.setItemSpec(xml.getElementAttrValue(barcodeXml, "type"));
		etsItemInfoDTO.setItemCategory(xml.getElementAttrValue(barcodeXml, "category"));
		etsItemInfoDTO.setItemQty(qty);
		etsItemInfoDTO.setAddressId(xml.getElementAttrValue(barcodeXml, "address_id"));
		etsItemInfoDTO.setBoxNo(xml.getElementAttrValue(barcodeXml, "box_no"));
		etsItemInfoDTO.setNetUnit(xml.getElementAttrValue(barcodeXml, "net_unit"));
		etsItemInfoDTO.setParentBarcode(xml.getElementAttrValue(barcodeXml, "parent_code"));
		etsItemInfoDTO.setResponsibilityDept(xml.getElementAttrValue(barcodeXml, "assign_groupid"));
		etsItemInfoDTO.setResponsibilityUser(xml.getElementAttrValue(barcodeXml, "assign_userid"));
		etsItemInfoDTO.setMaintainUser(xml.getElementAttrValue(barcodeXml, "username"));
		etsItemInfoDTO.setManufacturerId(xml.getElementAttrValue(barcodeXml, "manufacturerId")); //����
		etsItemInfoDTO.setShare(xml.getElementAttrValue(barcodeXml, "isShare"));
		etsItemInfoDTO.setPower(xml.getElementAttrValue(barcodeXml, "power"));

		return etsItemInfoDTO;
	}

	/**
	 * * ȡ������attribute7��ɨ��רҵ��
	 * @param conn        ���ݿ�����
	 * @param workorderNo ������
	 * @return String
	 * @throws QueryException
	 * @throws ContainerException
	 */
	public String getItemCategoryOfOrder(Connection conn, String workorderNo) throws QueryException, ContainerException {
		String itemCategory = "";
		PDAOrderUtilModel pdaOrderUtilModel = new PDAOrderUtilModel();
		SQLModel sqlModel = pdaOrderUtilModel.getAttribute7(workorderNo);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			Row row = simpleQuery.getFirstRow();
			itemCategory = row.getStrValue("ATTRIBUTE7");
		}
		if (itemCategory.equalsIgnoreCase("ALL")) {
			itemCategory = AmsOrderConstant.scanAllItemCategory;
		}
		return itemCategory;
	}

	/**
	 * ȡ�����ĳɱ����Ĵ���
	 * @param conn
	 * @param workorderNo
	 * @return
	 * @throws QueryException
	 * @throws ContainerException
	 */
	public String getCostCenterOfOrder(Connection conn, String workorderNo) throws QueryException, ContainerException {
		String costCenterCode = "";
		PDAOrderUtilModel pdaOrderUtilModel = new PDAOrderUtilModel();
		SQLModel sqlModel = pdaOrderUtilModel.getCostCenter(workorderNo);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			Row row = simpleQuery.getFirstRow();
			costCenterCode = row.getStrValue("COST_CENTER_CODE");
		}

		return costCenterCode;
	}

	/**
	 * ��ѯ�ù����·�����Ƿ��ж�Ӧ��MIS����
	 * @param conn        ���ݿ�����
	 * @param workorderNo ������
	 * @return boolean
	 * @throws QueryException
	 */
	public boolean hasMatchedMISDetp(Connection conn, String workorderNo) throws QueryException {
		PDAOrderUtilModel pdaOrderUtilModel = new PDAOrderUtilModel();
		SQLModel sqlModel = pdaOrderUtilModel.getExistsMisGroupModel(workorderNo);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		return simpleQuery.hasResult();
	}
}
