package com.sino.ams.newasset.urgenttrans.service;

import com.sino.ams.bean.OrderNumGenerator;
import com.sino.ams.newasset.constant.AssetsDictConstant;
import com.sino.ams.newasset.constant.AssetsWebAttributes;
import com.sino.ams.newasset.service.AssetsBaseService;
import com.sino.ams.newasset.urgenttrans.constant.UrgentAppConstant;
import com.sino.ams.newasset.urgenttrans.dao.UrgentDAO;
import com.sino.ams.newasset.urgenttrans.dto.UrgentDTO;
import com.sino.ams.newasset.urgenttrans.dto.UrgentHeaderDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @ϵͳ����: ��������������
 * @��������:
 * @�޸���ʷ: ��ʼ�汾1.0
 * @��˾����: ����˼ŵ����Ϣ�������޹�˾
 * @��ǰ�汾��1.0
 * @��������: sj
 * @����ʱ��: Jul 14, 2011
 */
public class UrgentBaseService extends AssetsBaseService {
    static String ORDER_TITLE = "����������";
    protected UrgentDTO leaseDTO = null;
    protected UrgentHeaderDTO headerDTO = null;

    protected DTOSet lines = null;

    protected UrgentDAO headerDAO = null;
    protected String msg = null;

    public UrgentBaseService(SfUserDTO user, UrgentDTO dto, Connection conn) {
        super(user, dto, conn);
        this.init(user, dto, conn);
    }

    /**
     * ����
     *
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
     * @return
     */
    public boolean doSubmit() {
        return true;
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
            OrderNumGenerator numberProducer = new OrderNumGenerator(conn, companyCode, transType);
            headerDTO.setTransNo(numberProducer.getOrderNum());
            headerDAO.createHeader(headerDTO);
        } else {
            headerDAO.updateHeader(headerDTO);
        }
    }

    /**
     * ������
     *
     * @throws DataHandleException
     */
    public void saveLines() throws DataHandleException {
    }

    /**
     * ��ʼ��ͷ��Ϣ
     *
     * @param dto
     * @return
     */
    private UrgentHeaderDTO initHeaderData(UrgentHeaderDTO dto) {
        dto.setTransNo(AssetsWebAttributes.ORDER_AUTO_PROD); // ���õ��ݺ�
        dto.setCreatedBy(userAccount.getUserId()); // ���ô�����
        dto.setCreated(userAccount.getUsername()); // ���ô�����
        dto.setFromOrganizationId(userAccount.getOrganizationId());
        dto.setFromCompanyName(userAccount.getCompany());
        dto.setTransTypeValue(UrgentAppConstant.TRANS_TYPE_NAME);
        dto.setTransType(UrgentAppConstant.TRANS_TYPE);
        dto.setTransStatusName("����");
        dto.setCurrCreationDate();

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
    protected void prodHeader() throws QueryException {
        headerDAO.setDTOClassName(UrgentHeaderDTO.class.getName());
        headerDAO.setCalPattern(CalendarConstant.LINE_PATTERN);

        UrgentHeaderDTO tmpDTO = (UrgentHeaderDTO) headerDTO.clone();

        headerDTO = (UrgentHeaderDTO) headerDAO.getDataByPrimaryKey();
        // ���½�ʱ��
        if (null == headerDTO || StrUtil.isEmpty(headerDTO.getTransId())) {
            headerDTO = new UrgentHeaderDTO();
            headerDTO = initHeaderData(headerDTO);
        }
        headerDTO.setSf_task_attribute3(tmpDTO.getSf_task_attribute3());

        leaseDTO.setHeaderDTO(headerDTO);
    }

    /**
     * ȡ����Ϣ
     *
     * @throws QueryException
     */
    protected void prodLines() throws QueryException {
        lines = headerDAO.getLinesData(headerDTO.getTransId());
        leaseDTO.setLines(lines);
    }

    public UrgentDTO getForm() {
        leaseDTO.setHeaderDTO(headerDTO);
        leaseDTO.setLines(lines);
        return leaseDTO;
    }

    public void setForm(UrgentDTO leaseDTO) {
        this.leaseDTO = leaseDTO;
        this.lines = leaseDTO.getLines();
        this.headerDTO = leaseDTO.getHeaderDTO();
        this.headerDAO = new UrgentDAO(userAccount, headerDTO, conn);
    }


    /**
     * ���ܣ�׼����������,��Ӧ��ʵ��
     */
    protected void prepareProcedureData(){
        flowDTO.setApp_dataID(headerDTO.getTransId());
        flowDTO.setPrimaryKey(headerDTO.getTransId());
        flowDTO.setOrderNo(headerDTO.getTransNo());
        flowDTO.setOrderName(ORDER_TITLE);
    }


    /**
     * ��ʼ��
     *
     * @param user
     * @param dto
     * @param conn
     */
    private void init(SfUserDTO user, UrgentDTO dto, Connection conn) {
        this.userAccount = user;
        this.conn = conn;
        this.leaseDTO = dto;

        this.lines = leaseDTO.getLines();
        this.headerDTO = leaseDTO.getHeaderDTO();
        this.headerDAO = new UrgentDAO(user, headerDTO, conn);
    }

    /**
     * �������б���ĵ���ID���ý�DTO
     *
     * @param dtoParameter
     * @return
     */
    protected UrgentHeaderDTO setFlowIdToDTO(UrgentHeaderDTO dtoParameter) {
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
}