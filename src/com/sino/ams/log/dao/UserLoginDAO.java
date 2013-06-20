package com.sino.ams.log.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.sino.base.constant.message.MsgKeyConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;
import com.sino.ams.log.model.UserLoginModel;
import com.sino.ams.newasset.dao.AmsAssetsPriviDAO;
import com.sino.ams.newasset.dao.AmsAssetsTransConfigDAO;
import com.sino.ams.system.user.dao.SfUserRightDAO;
import com.sino.ams.system.user.dto.SfGroupDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.dto.SfUserRightDTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.dao.BaseLoginDAO;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */

public class UserLoginDAO extends BaseLoginDAO {
    private UserLoginModel userLoginModel = null;
    private SfUserDTO amsUser = null;

    public UserLoginDAO(BaseUserDTO userAccount, Connection conn) {
        super(userAccount, conn);
        userLoginModel = new UserLoginModel(userAccount);
        this.conn = conn;
    }

    /**
     * ���ܣ������û��Ƿ��PDA��¼
     * @param fromPDA boolean
     */
    public void setFromPDA(boolean fromPDA) {
        userLoginModel.setFromPDA(fromPDA);
    }

    /**
     * ���ܣ���ȡ��¼�û�����ϸ��Ϣ
     * @return BaseUserDTO
     */
    public BaseUserDTO getUserAccount() {
        return amsUser;
    }

    /**
     * ���ܣ��жϵ�¼�û��Ƿ�Ϸ��û�
     * @return boolean
     * @throws QueryException
     */
    public boolean isValidUser() throws QueryException {
    	SQLModel sqlModel = userLoginModel.getUserLoginModel();
        return isValidUser( sqlModel , false  );
    }
    
    /**
     * ���ܣ��жϵ�¼�û��Ƿ�Ϸ��û�
     * @return boolean
     * @throws QueryException
     */
    public boolean isValidUser( SQLModel sqlModel , boolean isThrow ) throws QueryException {
//        SQLModel sqlModel = userLoginModel.getUserLoginModel();
        SimpleQuery simp = new SimpleQuery(sqlModel, conn);
        simp.setDTOClassName(SfUserDTO.class.getName());
        simp.executeQuery();
        if (simp.hasResult()) {
            userAccount = (BaseUserDTO) simp.getFirstDTO();
            
            amsUser = (SfUserDTO) userAccount;
            
            if( amsUser.getEnabled().equals( "N" ) ){
            	prodMessage(MsgKeyConstant.LOGIN_ERROR);
            	message.setMessageValue( "�û��ѱ�ʧЧ" );
            	message.setIsError(true);
            	return false;
            }
//            if (String.valueOf(amsUser.getOrganizationId()).equals(servletConfig.getProvinceOrgId())) {
            if (amsUser.getOrganizationId() == servletConfig.getProvinceOrgId()) {
                amsUser.setProvinceUser(true);
            } else {
                amsUser.setProvinceUser(false);
            }
            enhanceUser();
            enrichUserAccount();//��������𡢽�ɫ��Ϣ
            getAddrConfig();//����ص�������Ϣ
            setAssetsProperty();//�����ʲ�����Ա��ɫ
            setAssetsTransConfigs();//�����ʲ�����������Ϣ
            setTmpInvProperty();//������;����Ϣ
            isValidUser = true;
        } else {
        	if( isThrow ){
        		throw new QueryException( "�ʲ�ϵͳû������û����������" );
        	}else{
        		prodMessage(MsgKeyConstant.LOGIN_ERROR);
        		message.setIsError(true);
        	}
        }
        return isValidUser;
    }

    /**
     * ���ܣ������û�����𣬽�ɫ��Ϣ
     * @throws QueryException
     */
    private void enrichUserAccount() throws QueryException {
        SfUserRightDTO sfUserRight = new SfUserRightDTO();
        sfUserRight.setUserId(amsUser.getUserId());

        List lsRoleName = new ArrayList();
        List lsRoleId = new ArrayList();
        SfUserRightDAO userRightDAO = new SfUserRightDAO(amsUser, sfUserRight, conn);
        userRightDAO.setDTOClassName(SfUserRightDTO.class.getName());
        DTOSet userRights = (DTOSet) userRightDAO.getDataByForeignKey("userId");
        try {
            if (userRights != null && !userRights.isEmpty()) {
                amsUser.setUserRights(userRights);
                SfUserRightDTO currUserRight = (SfUserRightDTO) userRights.getDTO(0);
                int currGroupId = currUserRight.getGroupId();
                amsUser.setCurrGroupId(currGroupId);
                amsUser.setCurrGroupName(currUserRight.getGroupName());
                SfUserRightDTO tmpUserRight = null;
                SfGroupDTO sfGroup = null;
                DTOSet sfGroups = new DTOSet();
                Vector vector = new Vector();
                for (int i = 0; i < userRights.getSize(); i++) {
                    tmpUserRight = (SfUserRightDTO) userRights.getDTO(i);
                    int tmpGroupId = tmpUserRight.getGroupId();
                    if (!vector.contains(tmpGroupId)) {
                        sfGroup = new SfGroupDTO();
                        sfGroup.setGroupId(tmpGroupId);
                        sfGroup.setGroupname(tmpUserRight.getGroupName());
                        sfGroup.setGroupCode(tmpUserRight.getGroupCode());
                        sfGroup.setGroupProp(tmpUserRight.getGroupProp());
                        sfGroup.setDeptId(tmpUserRight.getDeptId());
                        sfGroup.setDeptName(tmpUserRight.getDeptName());
                        sfGroups.addDTO(sfGroup);
                        vector.add(tmpGroupId);
                    }

                    if(!lsRoleId.contains(tmpUserRight.getRoleId())){
                        lsRoleId.add(tmpUserRight.getRoleId());
                    }
                    if (!lsRoleName.contains(tmpUserRight.getRoleName())) {
                        lsRoleName.add(tmpUserRight.getRoleName());
                    }
                }

                if (lsRoleName.contains(servletConfig.getSysAdminRole())) {
                    amsUser.setSysAdmin(true);
                }
                if (lsRoleName.contains(servletConfig.getCityAdminRole())) {
                    amsUser.setCityAdmin(true);
                }
                amsUser.setCurrRoleIds(lsRoleId);
                amsUser.setUserGroups(sfGroups);
            }
        } catch (DTOException ex) {
            ex.printLog();
            throw new QueryException(ex);
        }
    }

    /**
     * ���ܣ���ȡ�ص�����
     * @throws QueryException
     */
    private void getAddrConfig() throws QueryException {
        SQLModel sqlModel = userLoginModel.getAddrConfigDataModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();

        if (simpleQuery.hasResult()) {
            RowSet rows = simpleQuery.getSearchResult();
            for (int i = 0; i < rows.getSize(); i++) {
                Row row = rows.getRow(i);
                try {
                    String id = row.getStrValue("CONFIG_ID");
                    String checkedFlag = row.getStrValue("CHECKED_FLAG");
                    if (id.equals("1")) {
                        amsUser.setIsToAddr(checkedFlag.equals("Y"));
                    }
                    if (id.equals("2")) {
                        amsUser.setIsToNetUnit(checkedFlag.equals("Y"));
                    }
                    if (id.equals("3")) {
                        amsUser.setIsToBox(checkedFlag.equals("Y"));
                    }
                } catch (ContainerException e) {
                    e.printStackTrace();
                    throw new QueryException();
                }
            }
        }
    }

    /**
     * ���ܣ������û����ʲ�����Ȩ��
     * @throws QueryException
     */
    private void setAssetsProperty() throws QueryException {
        AmsAssetsPriviDAO priviDAO = new AmsAssetsPriviDAO(amsUser, null, conn);
        priviDAO.setServletConfig(servletConfig);
        DTOSet priviAssetsDtp = priviDAO.getPriviDepts();
        String mtlMgrPropps = priviDAO.getMtlMgrProps();
        amsUser.setMtlMgrProps(mtlMgrPropps);
        if (priviAssetsDtp == null || priviAssetsDtp.isEmpty()) {
            amsUser.setDptAssetsManager(false);
        } else {
            amsUser.setDptAssetsManager(true);
            amsUser.setPriviDeptCodes(priviAssetsDtp);
        }
        if (mtlMgrPropps.equals("")) {
            amsUser.setMtlAssetsManager(false);
        } else {
            amsUser.setMtlAssetsManager(true);
        }
        amsUser.setComAssetsManager(priviDAO.isCompanyManager());
        amsUser.setProvAssetsManager(priviDAO.isProvinceManager());
//        amsUser.setDptMgr(priviDAO.isDptMgr());
    }

    /**
     * ���ܣ�������;����Ϣ
     * @throws QueryException
     */
    private void setTmpInvProperty() throws QueryException {
        try {
            userLoginModel.setDTOParameter(amsUser);
            SQLModel sqlModel = userLoginModel.getTmpInvAddressModel();
            SimpleQuery simp = new SimpleQuery(sqlModel, conn);
            simp.executeQuery();
            if (simp.hasResult()) {
                Row row = simp.getFirstRow();
                amsUser.setTmpAddressId(row.getStrValue("ADDRESS_ID"));
                amsUser.setTmpInvCode(row.getStrValue("WORKORDER_OBJECT_NO"));
            }
        } catch (ContainerException ex) {
            ex.printLog();
            throw new QueryException(ex);
            }
    }

    /**
     * ���ܣ������ʲ�����������Ϣ
     * @throws QueryException
     */
    private void setAssetsTransConfigs() throws QueryException {
        AmsAssetsTransConfigDAO configDAO = new AmsAssetsTransConfigDAO(amsUser, null, conn);
        configDAO.setServletConfig(servletConfig);
        DTOSet transConfigs = configDAO.getTransConfigs();
        amsUser.setTransConfigs(transConfigs);
    }

        private  void enhanceUser() throws QueryException {
        SQLModel sqlModel=new SQLModel();
        sqlModel=userLoginModel.getEmployeeIdModel(amsUser.getEmployeeNumber(),amsUser.getOrganizationId());
        SimpleQuery sq=new SimpleQuery(sqlModel,conn);
        sq.executeQuery();
        if(sq.hasResult()){
            try {
                amsUser.setEmployeeId(sq.getSearchResult().getRow(0).getStrValue("EMPLOYEE_ID"));
            } catch (ContainerException e) {
                Logger.logError(e);
            }
        }

    }
}
