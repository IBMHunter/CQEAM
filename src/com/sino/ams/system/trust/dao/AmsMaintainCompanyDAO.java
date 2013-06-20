package com.sino.ams.system.trust.dao;


import java.sql.Connection;

import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataHandleException;
import com.sino.base.log.Logger;
import com.sino.base.util.StrUtil;
import com.sino.base.web.request.upload.UploadFile;
import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.trust.dto.AmsMaintainCompanyDTO;
import com.sino.ams.system.trust.model.AmsMaintainCompanyModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.framework.dto.BaseUserDTO;


/**
 * <p>Title: AmsMaintainCompanyDAO</p>
 * <p>Description:�����Զ����ɷ������AmsMaintainCompanyDAO�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author V-yuanshuai
 * @version 1.0
 */


public class AmsMaintainCompanyDAO extends AMSBaseDAO {

    /**
     * ���ܣ���ά��˾��(EAM) AMS_MAINTAIN_COMPANY ���ݷ��ʲ㹹�캯��
     * @param userAccount SfUserDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter AmsMaintainCompanyDTO ���β���������
     * @param conn Connection ���ݿ����ӣ��ɵ����ߴ��롣
     */
    public AmsMaintainCompanyDAO(SfUserDTO userAccount, AmsMaintainCompanyDTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }

    /**
     * ���ܣ�SQL������BaseSQLProducer�ĳ�ʼ����
     * @param userAccount BaseUserDTO ��ϵͳ���ղ����û���
     * @param dtoParameter DTO ���β���������
     */
    protected void initSQLProducer(BaseUserDTO  userAccount, DTO dtoParameter) {
        AmsMaintainCompanyDTO dtoPara = (AmsMaintainCompanyDTO)dtoParameter;
        super.sqlProducer = new AmsMaintainCompanyModel((SfUserDTO)userAccount, dtoPara);
    }

    /**
     * ���ܣ������ά��˾��(EAM)��AMS_MAINTAIN_COMPANY�����ݡ�

     */
    public void createData() throws DataHandleException {
        try {
           // String tableName = "AMS_MAINTAIN_COMPANY";
//            DataUniqueChecker datChecker = new DataUniqueChecker(tableName, dtoParameter, conn);
//            datChecker.setDBAction(DBActionConstant.INSERT);
//            boolean isValid = datChecker.isDataValid();
//            if (isValid) {
                AmsMaintainCompanyDTO company = (AmsMaintainCompanyDTO)super.dtoParameter;
                 
                if( StrUtil.isEmpty( company.getCompanyId() ) ){
                	SeqProducer seq = new SeqProducer(conn);
                    company.setCompanyId( seq.getGUID() );
                }
                
                setDTOParameter(company);
                super.createData();
                getMessage().addParameterValue("��ά��˾");
//            } else {
//                prodMessage(MsgKeyConstant.UNIQUE_ERROR);
//                AmsMaintainCompanyDTO dto = (AmsMaintainCompanyDTO)dtoParameter;
//                String msg = "����Ϊ" + dto.getName() + "�Ĵ�ά��˾";
//                message.addParameterValue(msg);
//            }
        } 
//        catch (ValidateException ex) {
//            ex.printLog();
//            prodMessage(MsgKeyConstant.COMMON_ERROR);
//            message.addParameterValue("��ά��˾");
//        } 
        catch (Exception ex) {
            Logger.logError(ex);
            prodMessage(MsgKeyConstant.SEQ_ERROR);
            message.addParameterValue("AMS_MAINTAIN_COMPANY_S");
        }

    }

    /**
     * ���ܣ����´�ά��˾��(EAM)��AMS_MAINTAIN_COMPANY�����ݡ�

     */
    public void updateData() throws DataHandleException {
       super.updateData();
        getMessage().addParameterValue("��ά��˾");
    }

    /**
     * ���ܣ�ɾ����ά��˾��(EAM)��AMS_MAINTAIN_COMPANY�����ݡ�

     */
    public void deleteData() throws DataHandleException {
       super.deleteData();
        getMessage().addParameterValue("��ά��˾");

    }

    public boolean uploadFiles(UploadFile[] files){
        boolean operateResult = false;
        return operateResult;
    }
}
