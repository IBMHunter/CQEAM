package com.sino.ams.yj.bean;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.yj.resource.dto.AmsYjCommunicateResourceDTO;
import com.sino.ams.yj.constant.YjLookUpConstant;
import com.sino.ams.yj.dto.AmsYjItemDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.lookup.LookUpModel;
import com.sino.base.lookup.LookUpProp;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.dto.ServletConfigDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class YjLookUpModel extends LookUpModel {
    private SfUserDTO user = null;
    private String proCode = "";

    public YjLookUpModel(BaseUserDTO userAccount, DTO dtoParameter, LookUpProp lookProp) {
        super(userAccount, dtoParameter, lookProp);
        this.user = (SfUserDTO) userAccount;

    }

    public void setServletConfig(ServletConfigDTO servletConfig) {
        super.setServletConfig(servletConfig);
        this.proCode = servletConfig.getProvinceCode();
    }

    /**
     * ���ܣ������ѯSQL���ɾ�����ҪLookUp������Ӧ��ʵ��
     */
    protected void produceSQLModel() {
        sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "";
        String lookUpName = lookProp.getLookUpName();
        if (lookUpName.equals(YjLookUpConstant.LOOK_UP_RESOURCE)) {  //ѡ��ս����Դ
            AmsYjCommunicateResourceDTO dto = (AmsYjCommunicateResourceDTO) dtoParameter;
            sqlStr = "SELECT \n" +
                    "       AYCR.RESOURCE_ID,\n" +
                    "       AYCR.DEPT_NAME,\n" +
                    "       AYCR.EQUIPMENT_NAME,\n" +
                    "       AYCR.RESOURCE_QTY,\n" +
                    "       AYCR.LOCATION,\n" +
                    "       AYCR.MODEL\n" +
                    "  FROM AMS_YJ_COMMUNICATE_RESOURCE AYCR\n" +
                    " WHERE ("+SyBaseSQLUtil.isNull()+" OR AYCR.EQUIPMENT_NAME = ?)\n" +
                    "   AND ("+SyBaseSQLUtil.isNull()+" OR AYCR.MODEL = ?)\n" +
                    "   AND ("+SyBaseSQLUtil.isNull()+" OR AYCR.DEPT_NAME = ?)\n" +
                    "   AND ("+SyBaseSQLUtil.isNull()+" OR AYCR.LOCATION = ?)\n" +
                    "   AND (? =-1 OR AYCR.ORGANIZATION_ID = ?)";

            sqlArgs.add(dto.getEquipmentName());
            sqlArgs.add(dto.getEquipmentName());
            sqlArgs.add(dto.getModel());
            sqlArgs.add(dto.getModel());
            sqlArgs.add(dto.getDeptName());
            sqlArgs.add(dto.getDeptName());
            sqlArgs.add(dto.getLocation());
            sqlArgs.add(dto.getLocation());
            sqlArgs.add(dto.getOrganizationId());
            sqlArgs.add(dto.getOrganizationId());

        }if (lookUpName.equals(YjLookUpConstant.LOOK_UP_EQUIPMENT)) {//ѡ��װ������
            AmsYjItemDTO dto = (AmsYjItemDTO) dtoParameter;
            sqlStr = "SELECT AYI.ITEM_CODE,AYI.ITEM_NAME\n" +
                    "  FROM AMS_YJ_ITEM AYI \n" +
                    " WHERE ("+SyBaseSQLUtil.isNull()+" OR AYI.ITEM_NAME LIKE ?)";
            sqlArgs.add(dto.getItemName());
            sqlArgs.add(dto.getItemName());
        }

        sqlModel.setArgs(sqlArgs);
        sqlModel.setSqlStr(sqlStr);
    }
}