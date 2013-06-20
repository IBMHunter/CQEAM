package com.sino.ams.newasset.scrap.service;

import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.newasset.bean.AssetsOptProducer;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.dto.AmsAssetsTransHeaderDTO;
import com.sino.ams.newasset.dto.AmsAssetsTransLineDTO;
import com.sino.ams.newasset.scrap.constant.ScrapAppConstant;
import com.sino.ams.newasset.scrap.dao.AmsAssetsTransHeaderDAO;
import com.sino.ams.newasset.scrap.dto.TransDTO;
import com.sino.ams.newasset.service.AssetsBaseService;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class TransService extends AssetsBaseService {
    static String ORDER_TITLE = "���ϵ�";
    private TransDTO transDTO = null;
    private AmsAssetsTransHeaderDTO headerDTO = null;

    private DTOSet lines = null;

    protected AmsAssetsTransHeaderDAO headerDAO = null;

    protected String msg = null;

    public TransService(SfUserDTO user, TransDTO dto, Connection conn) {
        super(user, dto, conn);
        this.init(user, dto, conn);
    }


    /**
     * ����
     *
     * @param
     * @return
     */
    public boolean doCancel() {
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            headerDTO.setTransStatus(AssetsDictConstant.CANCELED);
            super.deleteReserved(headerDTO.getTransId());
            operateResult = super.cancelProcedure();
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    this.msg = "����ʧ��";
                    conn.rollback();
                } else {
                    this.msg = "�����ɹ�";
                    conn.commit();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
        return operateResult;
    }

    /**
     * ����
     *
     * @param
     * @return
     */
    public boolean doSave() {
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            headerDTO.setTransStatus(AssetsDictConstant.SAVE_TEMP);
            this.saveHeader();
            this.saveLines();
            operateResult = super.processProcedure(false);
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } catch (CalendarException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    this.msg = "����ʧ��";
                    conn.rollback();
                } else {
                    this.msg = "����ɹ�";
                    conn.commit();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
        return operateResult;
    }

    /**
     * ����
     *
     * @param
     * @return
     */
    public boolean doSubmit() {
        boolean operateResult = false;
        boolean autoCommit = true;
        try {
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            String sfAtt3 = headerDTO.getSf_task_attribute3();
            String flowCode = headerDTO.getFlowCode();
            this.msg = ORDER_TITLE + "�ύ";

            if (sfAtt3.equals(ScrapAppConstant.ATT3_FILL_DATA)) {
                headerDTO.setTransStatus(AssetsDictConstant.IN_PROCESS);
                this.saveHeader();
                this.saveLines();
            } else if (sfAtt3.equals(ScrapAppConstant.ATT3_APPROVING)) {
                if (flowCode.equals("A1")) {
                    headerDTO.setTransStatus(AssetsDictConstant.APPROVED);
                } else { // ����
                    super.deleteReserved(headerDTO.getTransId());
                    headerDTO.setTransStatus(AssetsDictConstant.CANCELED);
                }
                headerDTO.setTransStatus(AssetsDictConstant.COMPLETED);
            }
            operateResult = super.processProcedure(true);
        } catch (SQLException ex) {
            Logger.logError(ex);
        } catch (DataHandleException ex) {
            Logger.logError(ex);
        } catch (CalendarException ex) {
            Logger.logError(ex);
        } finally {
            try {
                if (!operateResult) {
                    this.msg += "ʧ��";
                    conn.rollback();
                } else {
                    this.msg = ORDER_TITLE + "��" + headerDTO.getTransNo() + "��"
                            + "�ύ�ɹ�";
                    conn.commit();
                }
                conn.setAutoCommit(autoCommit);
            } catch (SQLException ex) {
                Logger.logError(ex);
            }
        }
        return operateResult;
    }

    /**
     * ����ͷ
     *
     * @throws DataHandleException
     * @throws SQLException
     */
    public void saveHeader() throws DataHandleException, SQLException {
        if (headerDTO.getTransNo().equals(AssetsWebAttributes.ORDER_AUTO_PROD)) {
            if (StrUtil.isEmpty(headerDTO.getTransId())) {
                SeqProducer seqProducer = new SeqProducer(conn);
                String transId = seqProducer.getGUID();
                headerDTO.setTransId(transId);
            }
            String companyCode = userAccount.getCompanyCode(); // ���ǲ��ø÷��������¿������Ӿ���Ϊû��Ҫ
            String transType = headerDTO.getTransType();
            OrderNumGenerator numberProducer = new OrderNumGenerator(conn,
                    companyCode, transType);
            headerDTO.setTransNo(numberProducer.getOrderNum());
//			headerDAO.createHeader(headerDTO);
        } else {
//			headerDAO.updateHeader(headerDTO);
        }
    }

    /**
     * ������
     *
     * @throws DataHandleException
     * @throws CalendarException
     */
    public void saveLines() throws DataHandleException, CalendarException {
        String transId = headerDTO.getTransId();
        if (!StrUtil.isEmpty(transId)) {
            super.deleteReserved(transId);
//			headerDAO.deleteLine(transId);
        }
        AmsAssetsTransLineDTO line = null;
        String lineId = null;
        SeqProducer seqProducer = new SeqProducer(conn);
        for (int i = 0; i < lines.getSize(); i++) {
            line = (AmsAssetsTransLineDTO) lines.getDTO(i);
            lineId = seqProducer.getGUID();
            line.setLineId(lineId);
            line.setTransId(headerDTO.getTransId());
//			headerDAO.createLine(line);
            super.createReserved(transId, line.getBarcode());
        }
    }

    /**
     * ��ʼ��ͷ��Ϣ
     *
     * @param dto
     * @return
     */
    private AmsAssetsTransHeaderDTO initHeaderData(AmsAssetsTransHeaderDTO dto) {
        dto.setTransNo(AssetsWebAttributes.ORDER_AUTO_PROD); // ���õ��ݺ�
        dto.setCreatedBy(userAccount.getUserId()); // ���ô�����
        dto.setCreated(userAccount.getUsername()); // ���ô�����
        dto.setFromOrganizationId(userAccount.getOrganizationId());
        dto.setFromCompanyName(userAccount.getCompany());
        dto.setTransTypeValue(ScrapAppConstant.TRANS_TYPE_NAME);
        dto.setTransType(ScrapAppConstant.TRANS_TYPE);

        dto.setCurrCreationDate();
        dto.setEmail(userAccount.getEmail());
        dto.setPhoneNumber(userAccount.getMobilePhone());
        return dto;
    }

    /**
     * ȡ��ϸ����
     *
     * @throws QueryException
     */
    public void prodData() throws QueryException {
        headerDTO = this.setFlowIdToDTO(headerDTO);
        prodHeader();
        prodLines();
    }

    /**
     * ȡͷ��Ϣ
     *
     * @throws QueryException
     */
    private void prodHeader() throws QueryException {
        headerDAO.setDTOClassName(AmsAssetsTransHeaderDTO.class.getName());
        headerDAO.setCalPattern(CalendarConstant.LINE_PATTERN);

        AmsAssetsTransHeaderDTO tmpDTO = (AmsAssetsTransHeaderDTO) headerDTO.clone();

        headerDTO = (AmsAssetsTransHeaderDTO) headerDAO.getDataByPrimaryKey();
        // ���½�ʱ��
        if (null == headerDTO || StrUtil.isEmpty(headerDTO.getTransId())) {
            headerDTO = new AmsAssetsTransHeaderDTO();
            headerDTO = initHeaderData(headerDTO);
        }
        headerDTO.setSf_task_attribute3(tmpDTO.getSf_task_attribute3());

        transDTO.setHeaderDTO(headerDTO);
    }

    /**
     * ȡ����Ϣ
     *
     * @throws QueryException
     */
    private void prodLines() throws QueryException {
//		lines = headerDAO.getLinesData(headerDTO.getTransId());
        transDTO.setLines(lines);
    }

    public TransDTO getForm() throws QueryException {
        AssetsOptProducer optProducer = new AssetsOptProducer(userAccount, conn);
        if ("".equals(transDTO.getHeaderDTO().getEmergentLevel())) {
            transDTO.getHeaderDTO().setEmergentLevel("0");
        }
        String emergentLevelOption = optProducer.getAmsEmergentLevel(headerDTO
                .getEmergentLevel());
        headerDTO.setEmergentLevelOption(emergentLevelOption);
        transDTO.setHeaderDTO(headerDTO);
        transDTO.setLines(lines);
        return transDTO;
    }

    public void setForm(TransDTO transDTO) {
        this.transDTO = transDTO;
        this.lines = transDTO.getLines();
        this.headerDTO = transDTO.getHeaderDTO();
        this.headerDAO = new AmsAssetsTransHeaderDAO(userAccount, headerDTO, conn);
    }


    /**
     * ���ܣ�׼����������,��Ӧ��ʵ��
     */
    protected void prepareProcedureData(){
        flowDTO.setApp_dataID(headerDTO.getTransId()); // Ӧ��ID
        flowDTO.setPrimaryKey(headerDTO.getTransId()); // Ӧ��ID
        flowDTO.setOrderNo(headerDTO.getTransNo()); // Ӧ�õĵ��ݱ��
        flowDTO.setOrderName(ScrapAppConstant.PROC_NAME); // Ӧ�õĵ��ݱ��
    }


    /**
     * �������б���ĵ���ID���ý�DTO
     *
     * @param dtoParameter
     * @return
     */
    private AmsAssetsTransHeaderDTO setFlowIdToDTO(AmsAssetsTransHeaderDTO dtoParameter) {
        if (StrUtil.isEmpty(dtoParameter.getTransId())) {
            dtoParameter.setTransId(StrUtil.nullToString(dtoParameter
                    .getApp_dataID()));
        }
        return dtoParameter;
    }

    public File exportFile() throws DataTransException {
        return headerDAO.exportFile();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    /**
     * ��ʼ��
     *
     * @param user
     * @param dto
     * @param conn
     */
    private void init(SfUserDTO user, TransDTO dto, Connection conn) {
        this.transDTO = dto;

        this.lines = transDTO.getLines();
        this.headerDTO = transDTO.getHeaderDTO();
        this.headerDAO = new AmsAssetsTransHeaderDAO(user, headerDTO, conn);
    }
}
