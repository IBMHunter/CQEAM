package com.sino.soa.mis.srv.assetLoc2Comb.dao;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sino.base.constant.WorldConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.datatrans.*;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.QueryException;
import com.sino.base.util.StrUtil;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.config.SinoConfig;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.soa.common.SrvReturnMessage;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.BImportAssetLocCombSrvInputItem;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.ErrorItem;
import com.sino.soa.mis.eip.fi.fa.sb_fi_fa_bimportassetloccombsrv.ResponseItem;
import com.sino.soa.mis.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ImportVSetValueInfoSrvInputItem;
import com.sino.soa.mis.srv.assetLoc2Comb.dto.SrvAssetLoc2CombDTO;
import com.sino.soa.mis.srv.assetLoc2Comb.model.SrvAssetLoc2CombModel;
import com.sino.soa.mis.srv.assetLocComb.srv.ImportAssetLocCombSrv;
import com.sino.soa.mis.srv.valueinfo.srv.SBSYSYImportVSetValueInfoSrv;
import com.sino.soa.td.srv.valueinfo.srv.SBSYSYTdImportVSetValueInfoSrv;
import com.sino.soa.util.SynLogUtil;
import com.sino.soa.util.dto.EtsMisfaTransactionRespDTO;
import com.sino.soa.util.dto.EtsMisfaUpdateBatchDTO;
import com.sino.soa.util.dto.MisLocDTO;

//�ӿ������

/**
 * date��2011-12-23
 * user��wangzhipeng
 * function���ʲ��ص�ڶ���ͬ��
 */
public class SrvAssetLoc2CombDAO extends AMSBaseDAO {

    private ResponseItem responseItem = null;
    private ErrorItem errorItem = null;
    private SrvReturnMessage returnMessage = null;
    //private SrvReturnMessage returnMessage1=null;

    public SrvAssetLoc2CombDAO(SfUserDTO userAccount, SrvAssetLoc2CombDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        SrvAssetLoc2CombDTO dtoPara = (SrvAssetLoc2CombDTO) dtoParameter;
        super.sqlProducer = new SrvAssetLoc2CombModel((SfUserDTO) userAccount, dtoPara);
    }

    /**
     * EAM�ص�ͬ��
     *
     * @param objectNos EO������
     * @throws QueryException
     * @throws ContainerException
     * @throws SQLException
     */
    public String synLocComb(String[] objects, String isTd) throws QueryException, ContainerException, SQLException, DataHandleException {
        SrvAssetLoc2CombModel synLocModel = (SrvAssetLoc2CombModel) sqlProducer;
        EtsMisfaUpdateBatchDTO updateBatchDTO = new EtsMisfaUpdateBatchDTO();
        SeqProducer seqProducer = new SeqProducer(conn);
        SynLogUtil synLogUtil = new SynLogUtil();
        String isRespExist = "YES";
        try {
            EtsMisfaTransactionRespDTO respDTO = synLogUtil.getMisfaResp(userAccount.getOrganizationId(), userAccount.getEmployeeNumber(), conn);
            if (respDTO == null) {
                isRespExist = "NO";
                return isRespExist;
            } else {
                if (!isTd.equals("Y")) {     //MIS�û�
                    List<ImportVSetValueInfoSrvInputItem> inputItems1 = new ArrayList<ImportVSetValueInfoSrvInputItem>();
                    ImportVSetValueInfoSrvInputItem inputItem1 = null;
                    String batchSeq = seqProducer.getGUID();
                    MisLocDTO locDTO = null;
                    List<MisLocDTO> list = new ArrayList<MisLocDTO>();
                    List<String> primaryKeys = new ArrayList<String>();
                    for (int i = 0; i < objects.length; i++) {
                        SeqProducer seq = new SeqProducer(conn);
                        String priKey = seq.getGUID();
                        String object = objects[i];
                        String[] objectCodeName = object.split(",");
                        String objectCode = objectCodeName[0];
                        String objectName = objectCodeName[1];
                        //��־
                        locDTO = new MisLocDTO();
                        locDTO.setCode(objectCode);
                        locDTO.setName(objectName);
                        locDTO.setKey(priKey);
                        list.add(locDTO);
                        //������־ �� ETS_FA_NEW_LOC
//                    DBOperator.updateRecord(synLocModel.getInsertSynLogModel(objectCode,batchSeq,userAccount, priKey),conn);//����ʤע�ͣ���Ҫ����־��¼�ŵ�����SOA����֮��
                        //ֵ��
                        inputItem1 = new ImportVSetValueInfoSrvInputItem();
                        inputItem1.setPRIKEY(priKey);
                        inputItem1.setFLEXVALUESETNAME(SinoConfig.getFlexValueSetNameMis());
                        inputItem1.setVALIDATIONTYPE("I");
                        inputItem1.setFLEXVALUE(objectCode);
                        inputItem1.setDESCRIPTION(objectName);
                        inputItem1.setENABLEDFLAG("Y");
                        inputItem1.setSUMMARYFLAG("N");
                        inputItem1.setCREATEDBY(respDTO.getUserId());
//        	        inputItem1.setEMPLOYEENUMBER(respDTO.getEmployeeNumber());
                        inputItems1.add(inputItem1);

                        primaryKeys.add(priKey);//����ʤ����
                    }
                    //����ֵ��
                    SBSYSYImportVSetValueInfoSrv srv1 = new SBSYSYImportVSetValueInfoSrv();
                    srv1.setImportVSetValueInfoSrvInputItems(inputItems1);
                    srv1.excute();

                    for (MisLocDTO locDTO1 : list) {//����ʤ���ӣ���Ҫ����־��¼�ŵ�����SOA����֮��
                        SQLModel sqlModel = synLocModel.getInsertSynLogModel(locDTO1.getCode(), batchSeq, userAccount, locDTO1.getKey());
                        DBOperator.updateRecord(sqlModel, conn);
                    }
                    
                    if (srv1.getReturnMessage().getErrorFlag().equalsIgnoreCase("Y")) {
                        returnMessage = srv1.getReturnMessage();
                        updateBatchDTO.setBatchId(batchSeq);
                        updateBatchDTO.setOrganizationId(userAccount.getOrganizationId());
                        updateBatchDTO.setCreatedBy(userAccount.getUserId());
                        updateBatchDTO.setTransType("SYNLOC2");
//                    updateBatchDTO.setTransStatus(2);
                        updateBatchDTO.setTransStatus(1);//����ʤ�޸ģ�1��ʾͬ���ɹ�
                        updateBatchDTO.setErrmsg("");
                        updateBatchDTO.setRemark("���ι�ͬ��'�ص��2����Ϣ'" + objects.length + "��,ȫ��ͬ���ɹ�!");
                        synLogUtil.createMisUpdateBatch(updateBatchDTO, conn);
                        DBOperator.updateBatchRecords(synLocModel.getUpdateSynLogModel(batchSeq, list), conn);

                        primaryKeys.clear();//����ʤ����
                    } else {
                        returnMessage = srv1.getReturnMessage();
                        updateBatchDTO.setBatchId(batchSeq);
                        updateBatchDTO.setOrganizationId(userAccount.getOrganizationId());
                        updateBatchDTO.setCreatedBy(userAccount.getUserId());
                        updateBatchDTO.setTransType("SYNLOC2");
                        updateBatchDTO.setTransStatus(2);
                        updateBatchDTO.setErrmsg(returnMessage.getErrorMessage());
                        updateBatchDTO.setRemark("���ι�ͬ��'�ص��2����Ϣ'" + objects.length + "��,����ص��Ӧ��ֵ��ʧ��,ʧ��ԭ��"+returnMessage.getErrorMessage());
                        synLogUtil.createMisUpdateBatch(updateBatchDTO, conn);
                        List<com.sino.soa.mis.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ErrorItem> errorlist1 = srv1.getErrorItemList();
                        if (errorlist1.size() > 0) {
                            for (int i = 0; i < errorlist1.size(); i++) {
                                com.sino.soa.mis.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ErrorItem item1 = errorlist1.get(i);
                                String priKey = item1.getRECORDNUMBER();
                                String msg = item1.getERRORMESSAGE();
                                DBOperator.updateRecord(synLocModel.getUpdateSynErrorLogModel3(priKey, msg), conn);

                                primaryKeys.remove(priKey);
                            }
                            if(!primaryKeys.isEmpty()){//���ͬ��ʧ�ܵļ�¼������ͬ����¼����������־��¼��������ͬ��ʧ�ܵļ�¼����ͬ���ɹ��ļ�¼û�и���״̬���򵱵ص��ٴη����仯��Ҫͬ��ʱ��ѯ������������ʤ���ӱ�����
                                List<SQLModel> sqlModels = synLocModel.getSynLocationSuccessModel(batchSeq, primaryKeys);
                                DBOperator.updateBatchRecords(sqlModels, conn);
                                primaryKeys.clear();
                            }
                        }  else {
                            primaryKeys.clear();
                        }
                    }
                } else {   //TD�û�
                    List<com.sino.soa.td.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ImportVSetValueInfoSrvInputItem> inputItems1 = new ArrayList<com.sino.soa.td.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ImportVSetValueInfoSrvInputItem>();
                    com.sino.soa.td.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ImportVSetValueInfoSrvInputItem inputItem1 = null;
                    String batchSeq = seqProducer.getGUID();   //����   
                    MisLocDTO locDTO = null;
                    List<MisLocDTO> list = new ArrayList<MisLocDTO>();
                    List<String> primaryKeys = new ArrayList<String>();
                    for (int i = 0; i < objects.length; i++) {
                        SeqProducer seq = new SeqProducer(conn);
                        String priKey = seq.getGUID();
                        String object = objects[i];
                        String[] objectCodeName = object.split(",");
                        String objectCode = objectCodeName[0];
                        String objectName = objectCodeName[1];
                        //��־
                        locDTO = new MisLocDTO();
                        locDTO.setCode(objectCode);
                        locDTO.setName(objectName);
                        locDTO.setKey(priKey);
                        list.add(locDTO);
                        //������־ �� ETS_FA_NEW_LOC
//                        DBOperator.updateRecord(synLocModel.getInsertSynLogModel(objectCode, batchSeq, userAccount, priKey), conn);//����ʤע�ͣ���־��¼�ŵ�����SOA����֮��
                        //ֵ��
                        inputItem1 = new com.sino.soa.td.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ImportVSetValueInfoSrvInputItem();
                        inputItem1.setPRIKEY(priKey);
                        inputItem1.setFLEXVALUESETNAME(SinoConfig.getFlexValueSetNameTD());
                        inputItem1.setVALIDATIONTYPE("I");
                        inputItem1.setFLEXVALUE(objectCode);
                        inputItem1.setDESCRIPTION(objectName);
                        inputItem1.setENABLEDFLAG("Y");
                        inputItem1.setSUMMARYFLAG("N");
                        inputItem1.setCREATEDBY(respDTO.getUserId());
//        	        inputItem1.setEMPLOYEENUMBER(respDTO.getEmployeeNumber());
                        inputItems1.add(inputItem1);

                        primaryKeys.add(priKey);
                    }
                    //����ֵ��
                    SBSYSYTdImportVSetValueInfoSrv srv1 = new SBSYSYTdImportVSetValueInfoSrv();
                    srv1.setImportVSetValueInfoSrvInputItems(inputItems1);
                    srv1.execute();

                    for (MisLocDTO locDTO1 : list) {//����ʤ���ӣ���Ҫ����־��¼�ŵ�����SOA����֮��
                        SQLModel sqlModel = synLocModel.getInsertSynLogModel(locDTO1.getCode(), batchSeq, userAccount, locDTO1.getKey());
                        DBOperator.updateRecord(sqlModel, conn);
                    }

                    if (srv1.getReturnMessage().getErrorFlag().equalsIgnoreCase("Y")) { //����ֵ���ɹ�
                        returnMessage = srv1.getReturnMessage();
                        updateBatchDTO.setBatchId(batchSeq);
                        updateBatchDTO.setOrganizationId(userAccount.getOrganizationId());
                        updateBatchDTO.setCreatedBy(userAccount.getUserId());
                        updateBatchDTO.setTransType("SYNLOC2_TD");
//                    updateBatchDTO.setTransStatus(2); //����ʤע��
                        updateBatchDTO.setTransStatus(1);//����ʤ�޸ģ�1��ʾͬ���ɹ�
                        updateBatchDTO.setErrmsg("");
                        updateBatchDTO.setRemark("���ι�ͬ��'�ص��2����Ϣ'" + objects.length + "��,ȫ��ͬ���ɹ�!");
                        synLogUtil.createMisUpdateBatch(updateBatchDTO, conn);
                        DBOperator.updateBatchRecords(synLocModel.getUpdateSynLogModel(batchSeq, list), conn);

                        primaryKeys.clear();
                    } else {
                        returnMessage = srv1.getReturnMessage();
                        updateBatchDTO.setBatchId(batchSeq);
                        updateBatchDTO.setOrganizationId(userAccount.getOrganizationId());
                        updateBatchDTO.setCreatedBy(userAccount.getUserId());
                        updateBatchDTO.setTransType("SYNLOC2_TD");
                        updateBatchDTO.setTransStatus(2);
                        updateBatchDTO.setErrmsg(returnMessage.getErrorMessage());
                        updateBatchDTO.setRemark("���ι�ͬ��'�ص��2����Ϣ'" + objects.length + "��,����ص��Ӧ��ֵ��ʧ��,ʧ��ԭ��"+returnMessage.getErrorMessage());
                        synLogUtil.createMisUpdateBatch(updateBatchDTO, conn);
                        List<com.sino.soa.td.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ErrorItem> errorlist1 = srv1.getErrorItemList();
                        if (errorlist1.size() > 0) {
                            for (int i = 0; i < errorlist1.size(); i++) {
                                com.sino.soa.td.eip.sy.sy.sb_sy_sy_importvsetvalueinfosrv.ErrorItem item1 = errorlist1.get(i);
                                String priKey = item1.getRECORDNUMBER();
                                String msg = item1.getERRORMESSAGE();
                                DBOperator.updateRecord(synLocModel.getUpdateSynErrorLogModel3(priKey, msg), conn);

                                primaryKeys.remove(priKey);
                            }
                            if(!primaryKeys.isEmpty()){//���ͬ��ʧ�ܵļ�¼������ͬ����¼����������־��¼��������ͬ��ʧ�ܵļ�¼����ͬ���ɹ��ļ�¼û�и���״̬���򵱵ص��ٴη����仯��Ҫͬ��ʱ��ѯ������������ʤ���ӱ�����
                                List<SQLModel> sqlModels = synLocModel.getSynLocationSuccessModel(batchSeq, primaryKeys);
                                DBOperator.updateBatchRecords(sqlModels, conn);
                                primaryKeys.clear();
                            }
                        } else {
                            primaryKeys.clear();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return isRespExist;
    }

    public File getExportFile() throws DataTransException {
        SrvAssetLoc2CombModel modelProducer = (SrvAssetLoc2CombModel) sqlProducer;
        SQLModel sqlModel = modelProducer.getPageQueryModel();
        String reportTitle = "EAMϵͳ�����ص�";
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
        rule.setCalPattern(CAL_PATT_50);
        TransferFactory factory = new TransferFactory();
        DataTransfer transfer = factory.getTransfer(rule);
        transfer.transData();
        return (File) transfer.getTransResult();
    }

    private Map getFieldMap() {
        Map fieldMap = new HashMap();
        fieldMap.put("COMPANY_NAME", "��˾");
        fieldMap.put("LOC2_CODE", "�ص����");
        fieldMap.put("LOC2_DESC", "�ص�����");
        fieldMap.put("OLD_LOC_DESC", "ԭ�ص�����");
        fieldMap.put("WORKORDER_CATEGORY", "�ص�����");
        fieldMap.put("AREA_TYPE_NAEM", "��������");
        fieldMap.put("LOC_TYPE", "ͬ������");
        return fieldMap;
    }

    public ResponseItem getResponseItem() {
        return responseItem;
    }

    public void setResponseItem(ResponseItem responseItem) {
        this.responseItem = responseItem;
    }

    public ErrorItem getErrorItem() {
        return errorItem;
    }

    public void setErrorItem(ErrorItem errorItem) {
        this.errorItem = errorItem;
    }

    public SrvReturnMessage getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(SrvReturnMessage returnMessage) {
        this.returnMessage = returnMessage;
    }


}
