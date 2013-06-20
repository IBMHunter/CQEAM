package com.sino.ams.newasset.assetsharing.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import com.sino.ams.newasset.devalue.model.DevalueAssetsHeaderModel;
import com.sino.base.constant.web.WebActionConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.base.web.DatabaseForWeb;
import com.sino.ams.appbase.dao.AMSProcedureBaseDAO;
import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.newasset.assetsharing.constant.AssetSharingConstant;
import com.sino.ams.newasset.assetsharing.dto.AssetSharingHeaderDTO;
import com.sino.ams.newasset.assetsharing.dto.AssetSharingLineDTO;
import com.sino.ams.newasset.assetsharing.model.AssetSharingModel;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsMessageKeys;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dao.AmsAssetsReservedDAO;
import com.sino.ams.newasset.dao.AmsItemInfoHistoryDAO;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsItemInfoHistoryDTO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * �ʲ�����  DAO
 *
 * @author xiaohua
 */
public class AssetSharingDAO extends AMSProcedureBaseDAO {
    private AssetSharingHeaderDTO headerDTO = null;

    public AssetSharingDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        headerDTO = (AssetSharingHeaderDTO) dtoParameter;
    }

    @Override
    protected void initSQLProducer(BaseUserDTO arg0, DTO arg1) {
        super.sqlProducer = new AssetSharingModel(arg0, arg1);
    }

    /**
     * ���̱���/�ύ����
     *
     * @throws DataHandleException
     */
    public void saveOrder(DTOSet lines) throws DataHandleException {
        boolean operateResult = false;
        String transType = headerDTO.getTransType();
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            this.saveHeaderForm();
            this.saveLineForm(lines);
            //sj add ���̽�����ձ�����
            if (headerDTO.getTransStatus().equals(AssetsDictConstant.APPROVED)) {
                updateItemInfoShareStaus(lines);
                this.deleteReserved(headerDTO.getTransId());
                this.saveItemInfoHistory(lines);
            }
            if (headerDTO.getTransStatus().equals(AssetsDictConstant.CANCELED)) {
                this.deleteReserved(headerDTO.getTransId());
            }
            operateResult = processProcedure();
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            try {
                if (!operateResult) {
                    conn.rollback();
                    if (transType.equals(AssetsDictConstant.ASS_SHARE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SHARE_FAILURE);
                    }
                } else {
                    conn.commit();
                    if (transType.equals(AssetsDictConstant.ASS_SHARE)) {
                        prodMessage(AssetsMessageKeys.ASSETS_SHARE_SUCCESS);
                    }
                }
                conn.setAutoCommit(autoCommit);
                if (headerDTO.getAct().equals(WebActionConstant.SUBMIT_ACTION)) {
                    message.addParameterValue("�ύ");
                } else {
                    message.addParameterValue("����");
                }
                message.setIsError(!operateResult);
            } catch (Throwable e) {
                Logger.logError(e);
                prodMessage(AssetsMessageKeys.ROLL_BACK_ERROR);
            }
        }

    }

    /**
     * ���ܣ�׼����������,��Ӧ��ʵ��
     */
    protected void prepareProcedureData() {
        headerDTO.setPrimaryKey(headerDTO.getTransId());
        headerDTO.setOrderNo(headerDTO.getTransNo());
        headerDTO.setOrderName(headerDTO.getTransTypeValue());
    }

    /**
     * ��ѯ��ϸ/�鿴����
     *
     * @throws QueryException
     */
    public void prodForm() throws QueryException {
        try {
            this.getOrderHeader();
            headerDTO.setLines(this.queryOrderLines(headerDTO.getTransId()));
            this.setShareOpt();
        } catch (QueryException e) {
            Logger.logError(e);
            throw e;
        }
    }

    /**
     * ���̳���
     *
     * @throws DataHandleException
     * @throws QueryException
     * @throws SQLModelException
     * @throws ContainerException
     * @throws ParseException
     */
    public void cacelFlow() throws Exception {
        boolean isSuccess = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            this.updateHeaderStatus();
            this.updateReserve();
            isSuccess = cancelProcedure();
        } catch (DataHandleException e) {
            Logger.logError(e);
            setOprateMsg(AssetSharingConstant.OPERATE_FAILURE_VALUE, true);
            throw e;
        } finally {
            if (isSuccess) {
                conn.commit();
                setOprateMsg(AssetSharingConstant.OPERATE_SUCCESS_VALUE, false);
            } else {
                conn.rollback();
                setOprateMsg(AssetSharingConstant.OPERATE_FAILURE_VALUE, false);
            }
            conn.setAutoCommit(autoCommit);
        }
    }

    private void setOprateMsg(String msg, boolean isError) {
        message.setMessageValue(msg);
        message.setIsError(isError);
    }

    /**
     * �����˻�
     *
     * @throws DataHandleException
     */
    public void rejectFlow() throws DataHandleException {
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            updateHeaderStatus();
            operateResult = super.rejectProcedure();
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        } finally {
            try {
                if (operateResult) {
                    conn.commit();
                    prodMessage(AssetsMessageKeys.REJECT_ORDER_SUCCESS);
                } else {
                    conn.rollback();
                    prodMessage(AssetsMessageKeys.REJECT_ORDER_FAILURE);
                    message.setIsError(true);
                }
                conn.setAutoCommit(autoCommit);
                AssetSharingHeaderDTO dto = (AssetSharingHeaderDTO) dtoParameter;
                message.addParameterValue(dto.getTransTypeValue());
                message.addParameterValue(dto.getTransNo());
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
    }


    /**
     * ���� ����
     *
     * @throws DataHandleException
     */
    private void saveLineForm(DTOSet lines) throws DataHandleException {
        try {
            if (lines == null) {
                return;
            } else {
                AssetSharingModel modelProducer = (AssetSharingModel) sqlProducer;
                SQLModel sqlModel = modelProducer.deleteLinesModel(headerDTO.getTransId());
                // ɾ��ԭ��
                DBOperator.updateRecord(sqlModel, conn);
                //SJ add
                this.deleteReserved(headerDTO.getTransId());
                SeqProducer seq = new SeqProducer(conn);

                for (int i = 0; i < lines.getSize(); i++) {
                    AssetSharingLineDTO line = (AssetSharingLineDTO) lines.getDTO(i);
                    if (line.getBarcode().equals("")) {
                        continue;
                    }
                    line.setLineId((seq.getGUID()));
                    line.setTransId(headerDTO.getTransId());
                    sqlModel = modelProducer.saveLineModel(line);
                    DBOperator.updateRecord(sqlModel, conn);
                    //SJ add
                    this.createReserved(headerDTO.getTransId(), line.getBarcode());
                }
            }
        } catch (DataHandleException e) {
            Logger.logError(e);
            throw e;
        }

    }

    /**
     * ���� ��ͷ
     *
     * @throws DataHandleException
     */
    private void saveHeaderForm() throws DataHandleException {
        try {
            SQLModel sqlModel = null;

            AssetSharingModel modelProducer = (AssetSharingModel) sqlProducer;
            if (headerDTO.getSf_task_attribute3().equals("FILL_DATA")) {
                if (StrUtil.isEmpty(headerDTO.getTransNo()) || headerDTO.getTransNo().equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
                    OrderNumGenerator orderNumProd = new OrderNumGenerator(conn, userAccount.getCompanyCode(), headerDTO.getTransType());
                    headerDTO.setTransNo(orderNumProd.getOrderNum());
                    if (headerDTO.getTransId().equals("")) {//
                        SeqProducer seq = new SeqProducer(conn);
                        headerDTO.setTransId(seq.getGUID());
                    }
                    headerDTO.setTransStatus(AssetsDictConstant.IN_PROCESS);
                    sqlModel = modelProducer.saveHeaderModel();
                    DBOperator.updateRecord(sqlModel, conn);
                } else {
                    updateHeader();
                }
            } else {//
                if (headerDTO.getSf_task_attribute4().equals(AssetSharingConstant.ASSET_SHARE_EDITABLE)) {// ����˸���Ӧ������
                    updateHeader();
                } else {
                    if (headerDTO.getSf_end().equals("1")) {// ���̽�����־
                        headerDTO.setTransStatus(AssetsDictConstant.APPROVED);
                        sqlProducer.setDTOParameter(headerDTO);
                        sqlModel = modelProducer.getOrderCompleteModel();//��ȡ�����������SQL
                        DBOperator.updateRecord(sqlModel, conn);
                    } else {
                        headerDTO.setTransStatus(AssetsDictConstant.IN_PROCESS);
                        sqlProducer.setDTOParameter(headerDTO);
                        sqlModel = modelProducer.updateHeaderStatus();// �޸ı�״̬
                        DBOperator.updateRecord(sqlModel, conn);
                    }
                }
            }
        } catch (Throwable ex) {
            Logger.logError(ex);
            throw new DataHandleException(ex.getMessage());
        }
    }

    /**
     * ��ѯ����Ϣ
     *
     * @param headerId
     * @return
     * @throws QueryException
     */
    private List<AssetSharingLineDTO> queryOrderLines(String headerId) throws QueryException {
        AssetSharingModel modelProducer = (AssetSharingModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getLinesModel(headerId);
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.setDTOClassName(AssetSharingLineDTO.class.getName());
        sq.setCalPattern(LINE_PATTERN);
        sq.executeQuery();
        return sq.getListResult();
    }

    /**
     * ��ѯ ��ͷ��Ϣ
     *
     * @throws QueryException
     */
    private void getOrderHeader() throws QueryException {
        AssetSharingModel modelProducer = (AssetSharingModel)sqlProducer;
        SQLModel sqlModel = modelProducer.getHeaderModel();
        SimpleQuery sq = new SimpleQuery(sqlModel, conn);
        sq.setDTOClassName(AssetSharingHeaderDTO.class.getName());
        sq.executeQuery();
        if(sq.hasResult()){
            setHeaderMes((AssetSharingHeaderDTO) sq.getFirstDTO());
        }
    }

    private void setHeaderMes(AssetSharingHeaderDTO dto) throws QueryException {
        if (dto == null) {
            return;
        }
        this.headerDTO.setTransId(dto.getTransId());
        this.headerDTO.setCreatedBy(dto.getCreatedBy());
        headerDTO.setCurrUserName(dto.getCurrUserName());
        try {
            headerDTO.setCreationDate(dto.getCreationDate().getSimpleDate().getDateValue());
        } catch (Throwable e) {
            Logger.logError(e);
            throw new QueryException(e.getMessage());
        }
        headerDTO.setTransNo(dto.getTransNo());
        headerDTO.setTransType(dto.getTransType());
        headerDTO.setTransStatus(dto.getTransStatus());
        headerDTO.setFromOrganizationId(dto.getFromOrganizationId());
        headerDTO.setCompany(dto.getCompany());
        headerDTO.setDeptName(dto.getDeptName());
        headerDTO.setFromDept(dto.getFromDept());
        headerDTO.setCheckUser(dto.getCheckUser());
        headerDTO.setCheckUserName(dto.getCheckUserName());
        headerDTO.setEmail(dto.getEmail());
        headerDTO.setMobilePhone(dto.getMobilePhone());
        headerDTO.setRemark(dto.getRemark());
        AssetsOptProducer optProducer = new AssetsOptProducer(userAccount, conn);
        headerDTO.setEmergentLevelOption(optProducer.getAmsEmergentLevel(dto.getEmergentLevel()));
    }

    /**
     * ���� ��ͷ
     *
     * @throws DataHandleException
     */
    private void updateHeader() throws DataHandleException {
        try {
            DBOperator.updateRecord(((AssetSharingModel) sqlProducer)
                    .updateHeaderModel(""), conn);
        } catch (DataHandleException e) {
            Logger.logError(e);
            throw e;
        }
    }

    /**
     * ���� ��ͷ״̬
     *
     * @throws DataHandleException
     */
    private void updateHeaderStatus() throws DataHandleException {
        AssetSharingModel modelProducer = (AssetSharingModel) sqlProducer;
        SQLModel sqlModel = modelProducer.updateHeaderStatus();
        DBOperator.updateRecord(sqlModel, conn);
    }

    private void updateReserve() throws DataHandleException {
        AssetSharingModel modelProducer = (AssetSharingModel) sqlProducer;
        SQLModel sqlModel = modelProducer.deleteReserveModel();
        DBOperator.updateRecord(sqlModel, conn);
    }

    /**
     * ����  ����ѡ��
     *
     * @throws QueryException
     */
    private void setShareOpt() throws QueryException {
        AssetsOptProducer opt = new AssetsOptProducer(userAccount, conn);
        DatabaseForWeb dfw = opt.getAssetsShareOptions();

        String specialityDept = StrUtil.nullToString(headerDTO.getSpecialityDept());
        String specialityDeptOptions = "";
        specialityDeptOptions = opt.getSpecialAsssetsDeptOption(specialityDept);
        headerDTO.setSpecialityDeptOption(specialityDeptOptions);

        headerDTO.setShareStatusOpt(dfw.getOptionHtml("", true));
        List<AssetSharingLineDTO> lines = headerDTO.getLines();
        if (lines != null && lines.size() > 0) {
            for (int i = 0; i < lines.size(); i++) {
                AssetSharingLineDTO line = lines.get(i);
                line.setShareStatusOpt(dfw.getOptionHtml(line.getShareStatus(), true));
            }
        }
    }

    /**
     * �������̳�ʼ����
     *
     * @throws QueryException
     * @throws CalendarException
     */
    public void setInitInfo() throws QueryException, CalendarException {
        headerDTO.setCreatedBy(userAccount.getUserId());
        headerDTO.setCurrUserName(userAccount.getUsername());
        headerDTO.setCompany(userAccount.getCompany());
        headerDTO.setFromOrganizationId(userAccount.getOrganizationId());
        //headerDTO.setFromDept(userAccount.getDeptCode());
        //headerDTO.setDeptName(userAccount.getDeptName());
        headerDTO.setMobilePhone(userAccount.getMobilePhone());
        headerDTO.setEmail(userAccount.getEmail());
        //headerDTO.setGroupId(userAccount.getCurrGroupId() + "");
        headerDTO.setTransNo(AssetSharingConstant.Trans_No);
        headerDTO.setCurrCreationDate();
        this.setShareOpt();

    }


    /**
     * ���ܣ������� --
     *
     * @throws DataHandleException
     */
    public void createReserved(String transId, String barcode)
            throws DataHandleException {
        AmsAssetsReservedDAO amsAssetsReservedDAO = new AmsAssetsReservedDAO(userAccount, null, conn);
        amsAssetsReservedDAO.createReserved(transId, barcode);
    }

    /**
     * ���ܣ�
     *
     * @throws DataHandleException
     */
    public void deleteReserved(String transId)
            throws DataHandleException {
        AmsAssetsReservedDAO amsAssetsReservedDAO = new AmsAssetsReservedDAO(userAccount, null, conn);
        amsAssetsReservedDAO.deleteReserved(transId);
    }

    private void saveItemInfoHistory(DTOSet lines) {
        String orderURL = AssetSharingConstant.ASSET_SHARE_SERVLET;
        orderURL += "?act=VIEW_ACTION";
        orderURL += "&transId=" + headerDTO.getTransId();
        AmsItemInfoHistoryDAO historyDAO = new AmsItemInfoHistoryDAO(userAccount, null, conn);
        for (int i = 0; i < lines.getSize(); i++) {
            AssetSharingLineDTO line = (AssetSharingLineDTO) lines.getDTO(i);
            AmsItemInfoHistoryDTO historyDTO = new AmsItemInfoHistoryDTO();

            historyDTO.setOrderCategory("3");
            historyDTO.setOrderDtlUrl(orderURL);
            historyDTO.setRemark("�ʲ�����");
            historyDTO.setBarcode(line.getBarcode());
            historyDTO.setCreatedBy(userAccount.getUserId());
            historyDTO.setOrderNo(headerDTO.getTransNo());

            historyDAO.setDTOParameter(historyDTO);
            historyDAO.recordHistory();
        }

    }

    private void updateItemInfoShareStaus(DTOSet lines) throws DataHandleException {
        AssetSharingModel sharingModel = (AssetSharingModel) sqlProducer;
        for (int i = 0; i < lines.getSize(); i++) {
            AssetSharingLineDTO lineDTO = (AssetSharingLineDTO) lines.getDTO(i);
            SQLModel sqlModel = sharingModel.updateItemStatusModel(lineDTO.getBarcode(), lineDTO.getShareStatus());
            DBOperator.updateRecord(sqlModel, conn);
        }
    }

    //�ж��Ƿ�ʵ�ﲿ��
    public boolean isSpecialGroup(int fromGroup) throws QueryException {
    	AssetSharingModel modelProducer = (AssetSharingModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getIsSpecialGroupModel(fromGroup);
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.executeQuery();
        return simp.hasResult();
    }

}
