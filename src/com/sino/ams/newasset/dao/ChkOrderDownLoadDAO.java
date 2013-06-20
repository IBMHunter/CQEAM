package com.sino.ams.newasset.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.log.Logger;
import com.sino.base.util.ReflectionUtil;
import com.sino.base.util.StrUtil;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.newasset.bean.OrderXMLAssistant;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.dto.AmsAssetsCheckHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsCheckLineDTO;
import com.sino.ams.newasset.model.ChkOrderDownLoadModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.pda.dao.OrderDownLoadInterface;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class ChkOrderDownLoadDAO extends AMSBaseDAO implements OrderDownLoadInterface {
    private StringBuffer orderXml = null;
    private AmsAssetsCheckLineDTO chkLine = null;
    private boolean xmlProduced = false;

    private static Map orderMap = OrderXMLAssistant.getChkOrderMap();
    private static Map itemMap = OrderXMLAssistant.getChkItemMap();
    private static String orderPrimaryKey = OrderXMLAssistant.getOrderPrimaryKey();

    public ChkOrderDownLoadDAO(SfUserDTO userAccount, AmsAssetsCheckHeaderDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }


    /**
     * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
     * @param userAccount  BaseUserDTO
     * @param dtoParameter DTO
     */
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        AmsAssetsCheckHeaderDTO dto = (AmsAssetsCheckHeaderDTO) dtoParameter;
        sqlProducer = new ChkOrderDownLoadModel((SfUserDTO) userAccount, dto);
    }


    /**
     * ���ܣ������̵㹤���������ݣ����������ݿ�״̬
     * <B>�������ʲ��̵㡢�����̵㡢�����Ǳ��̵�</B>
     * <B>�����Զ��ύģʽ�����ٽ���������ơ���ֹsybase���ݿ�����</B>
     */
    private void consructXml() {
//        boolean autoCommit = true;
//        boolean operateResult = false;
        ChkOrderDownLoadModel modelProducer = (ChkOrderDownLoadModel) sqlProducer;
        try {
            orderXml = new StringBuffer();
//            autoCommit = conn.getAutoCommit();
//            conn.setAutoCommit(false);
            SQLModel sqlModel = modelProducer.getAllChkOrdersModel();
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.setDTOClassName(AmsAssetsCheckHeaderDTO.class.getName());
            simp.setCalPattern(LINE_PATTERN);
            simp.executeQuery();
            DTOSet chkOrders = simp.getDTOSet();
//			startRootXml();//��������ά�����ϲ����أ�����Ҫ�ٴ��ṩ���ڵ���Ϣ
            if (chkOrders != null && !chkOrders.isEmpty()) {
                int orderCount = chkOrders.getSize();
                AmsAssetsCheckHeaderDTO headerDTO = null;
                String orderTypeName = "";
                for (int i = 0; i < orderCount; i++) {
                    headerDTO = (AmsAssetsCheckHeaderDTO) chkOrders.getDTO(i);
                    headerDTO.setOrderStatus(AssetsDictConstant.CHK_STATUS_DOWNLOADED);
                    orderTypeName = headerDTO.getOrderTypeName();
                    if (headerDTO.getOrderType().equals(AssetsDictConstant.ASS_CHK)) {
                        orderTypeName = AssetsDictConstant.ASS_CHK_PAD;
                    }
                    headerDTO.setOrderType(orderTypeName);
                    setDTOParameter(headerDTO);
                    updateOrderStatus();
                    startChkOrderXml();
                    sqlModel = modelProducer.getOrderAssetsModel();
                    simp.setSql(sqlModel);
                    simp.setDTOClassName(AmsAssetsCheckLineDTO.class.getName());
                    simp.executeQuery();
                    DTOSet chkAssets = simp.getDTOSet();
                    if (chkAssets != null && !chkAssets.isEmpty()) {
                        int itemCount = chkAssets.getSize();
                        for (int j = 0; j < itemCount; j++) {
                            chkLine = (AmsAssetsCheckLineDTO) chkAssets.getDTO(j);
                            constructAssetXml((j == (itemCount - 1)));
                        }
                    }
                    endChkOrderXml();
                }
            } else {
                constructNoDataXml();
            }
//			endRootXml();//��������ά�����ϲ����أ�����Ҫ�ٴ��ṩ���ڵ���Ϣ
//            operateResult = true;
        } catch (DataHandleException ex) {
            ex.printLog();
        } catch (QueryException ex) {
            ex.printLog();
//        } catch (SQLException ex) {
//            Logger.logError(ex);
        } catch (ReflectException ex) {
            Logger.logError(ex);
//        } finally {
//            try {
//                if (operateResult) {
//                    conn.commit();
//                } else {
//                    conn.rollback();
//                }
//                conn.setAutoCommit(autoCommit);
//            } catch (SQLException ex1) {
//                Logger.logError(ex1);
//            }
        }
    }

    /**
     * ���ܣ�����xml��ʼ���ڵ�
     */
    private void startRootXml() {
        orderXml.append("<?xml version=\"1.0\" encoding=\"GB2312\" ?> ");
        orderXml.append(WorldConstant.ENTER_CHAR);
        orderXml.append(WorldConstant.TAB_CHAR);
        orderXml.append("<");
        orderXml.append(OrderXMLAssistant.getRootName());
        orderXml.append(">");
        orderXml.append(WorldConstant.ENTER_CHAR);
        orderXml.append(WorldConstant.TAB_CHAR);
        orderXml.append(WorldConstant.TAB_CHAR);
    }

    /**
     * ���ܣ������̵㹤���ڵ���ʼxml
     * @throws ReflectException
     */
    private void startChkOrderXml() throws ReflectException {
        AmsAssetsCheckHeaderDTO chkOrder = (AmsAssetsCheckHeaderDTO) dtoParameter;
        orderXml.append("<");
        orderXml.append(OrderXMLAssistant.getOrderName());
        Iterator pdaKeys = orderMap.keySet().iterator();
        String pdaField = "";
        String serverField = "";
        String nodeValue = "";
        while (pdaKeys.hasNext()) {
            pdaField = (String) pdaKeys.next();
            serverField = (String) orderMap.get(pdaField);
            nodeValue = String.valueOf(ReflectionUtil.getProperty(chkOrder, serverField));
            orderXml.append(" ");
            orderXml.append(pdaField);
            orderXml.append("=\"");
            orderXml.append(StrUtil.xmlFormat(nodeValue));
            orderXml.append("\"");
        }
        orderXml.append(">");
        orderXml.append(WorldConstant.ENTER_CHAR);
        orderXml.append(WorldConstant.TAB_CHAR);
        orderXml.append(WorldConstant.TAB_CHAR);
        orderXml.append(WorldConstant.TAB_CHAR);
    }

    /**
     * ���ܣ������̵��ʲ�xml��Ϣ
     * @param isLastAssets boolean
     * @throws ReflectException
     */
    private void constructAssetXml(boolean isLastAssets) throws ReflectException {
        orderXml.append("<");
        orderXml.append(OrderXMLAssistant.getItemName());
        Iterator pdaKeys = itemMap.keySet().iterator();
        String pdaField = "";
        String serverField = "";
        String nodeValue = "";
        while (pdaKeys.hasNext()) {
            pdaField = (String) pdaKeys.next();
            serverField = (String) itemMap.get(pdaField);
            nodeValue = String.valueOf(ReflectionUtil.getProperty(chkLine, serverField));
            nodeValue = StrUtil.xmlFormat(nodeValue);
            if (pdaField.equals("status")) {
                nodeValue = AssetsDictConstant.SACN_NO;
            }
            orderXml.append(" ");
            orderXml.append(pdaField);
            orderXml.append("=\"");
            orderXml.append(nodeValue);
            orderXml.append("\"");
        }
        orderXml.append("/>");
        orderXml.append(WorldConstant.ENTER_CHAR);
        orderXml.append(WorldConstant.TAB_CHAR);
        orderXml.append(WorldConstant.TAB_CHAR);
        if (!isLastAssets) {
            orderXml.append(WorldConstant.TAB_CHAR);
        }
    }

    /**
     * ���ܣ������̵㹤���ڵ����xml
     */
    private void endChkOrderXml() {
        orderXml.append("</");
        orderXml.append(OrderXMLAssistant.getOrderName());
        orderXml.append(">");
        orderXml.append(WorldConstant.ENTER_CHAR);
        orderXml.append(WorldConstant.TAB_CHAR);
        orderXml.append(WorldConstant.TAB_CHAR);
    }

    /**
     * ���ܣ��������ڵ�
     */
    private void endRootXml() {
        orderXml.append("</");
        orderXml.append(OrderXMLAssistant.getRootName());
        orderXml.append(">");
    }

    /**
     * ���ܣ�����������xml��ʾ
     */
    private void constructNoDataXml() {
//		orderXml.append("there's no data");
        orderXml.append(WorldConstant.ENTER_CHAR);
        orderXml.append(WorldConstant.TAB_CHAR);
    }

    /**
     * ���ܣ�����ѭ�������е��̵㹤��״̬
     * @throws DataHandleException
     */
    private void updateOrderStatus() throws DataHandleException {
        ChkOrderDownLoadModel modelProducer = (ChkOrderDownLoadModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getDownloadChkOrdersModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ���ܣ���ȡ���ص�xml�ļ�
     * @return StringBuffer
     * @throws QueryException
     */
    public StringBuffer getOrderXml() throws QueryException {
        if (!xmlProduced) {
//			AmsAssetsCheckHeaderDTO objDTO = (AmsAssetsCheckHeaderDTO) dtoParameter;
//			AmsAssetsCheckHeaderDAO orderDAO = new AmsAssetsCheckHeaderDAO(userAccount, objDTO, conn);
//			orderDAO.setDTOClassName(AmsAssetsCheckHeaderDTO.class.getName());
//			AmsAssetsCheckHeaderDTO srcDTO = (AmsAssetsCheckHeaderDTO) orderDAO.getDataByPrimaryKey();
//			TransferRoadValidator validator = new TransferRoadValidator();
//			if(!validator.canExecuteAction(srcDTO, objDTO)){
//				prodMessage(AssetsMessageKeys.INVALID_OPERATE);
//				message.addParameterValue(srcDTO.getStatusName());
//				message.addParameterValue(objDTO.getStatusName());
//			} else {
//				consructXml();
//			}
            consructXml();
            xmlProduced = true;
        }
        return orderXml;
    }
}
