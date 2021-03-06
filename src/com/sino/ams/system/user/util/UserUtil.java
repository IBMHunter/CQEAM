package com.sino.ams.system.user.util;

import java.sql.Connection;
import java.util.*;

import com.sino.base.constant.WorldConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.QueryException;
import com.sino.base.util.StrUtil;
import com.sino.ams.system.user.dao.SfUserRightDAO;
import com.sino.ams.system.user.dto.SfGroupDTO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.ams.system.user.dto.SfUserRightDTO;
import com.sino.framework.security.dto.ServletConfigDTO;

/**
 * User: zhoujs
 * Date: 2007-9-24
 * Time: 11:50:29
 * Function: 用户维护辅助
 */
public class UserUtil {
	private SfUserDTO userAccount = null;
	private ServletConfigDTO servletConfig = null;

	public UserUtil(SfUserDTO userAccount) {
		this.userAccount = userAccount;
	}

/**
	 * 取用户所在OU下所有组别(用户维护用)
	 * @param sfUser SfUserDTO
	 * @param conn Connection
	 * @return Map
	 * @throws QueryException
	 * @throws ContainerException
	 */
	public Map getGroupMap(SfUserDTO sfUser, Connection conn) throws QueryException, ContainerException {

		Map mp = new Hashtable();
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		StringBuffer groupSB = new StringBuffer();

		String organizationId = "";
		String curOrganizationId = "";

		String sqlStr = "SELECT GROUP_ID, GROUPNAME, ORGANIZATION_ID FROM SF_GROUP WHERE ENABLED='Y' ";
		if (! (sfUser.isSysAdmin() || sfUser.isProvinceUser())) {
			sqlStr += " AND ORGANIZATION_ID=? ";
			sqlArgs.add(sfUser.getOrganizationId());
		}
		sqlStr += " ORDER BY ORGANIZATION_ID,GROUPNAME";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			RowSet rows = simpleQuery.getSearchResult();
			if (rows != null && !rows.isEmpty()) {
				Row row = null;
				for (int i = 0; i < rows.getSize(); i++) {
					row = rows.getRow(i);
					curOrganizationId = row.getStrValue("ORGANIZATION_ID");

					if (!curOrganizationId.equals(organizationId)) {
						if (!StrUtil.isEmpty(organizationId) && groupSB != null) {
							if (sfUser.isSysAdmin()) {
							}
							if (groupSB.length() > 0) {
								if (groupSB.toString().endsWith(WorldConstant.SPLITOR)) {
									groupSB.deleteCharAt(groupSB.length() - 1);
								}
							}
							if (!StrUtil.isEmpty(organizationId)) {
								mp.put(organizationId, groupSB.toString());
							}

						}
						organizationId = curOrganizationId;
						groupSB = new StringBuffer();
					}
					groupSB.append(row.getStrValue("GROUP_ID"));
					groupSB.append(",");
					groupSB.append(row.getStrValue("GROUPNAME"));
					groupSB.append(WorldConstant.SPLITOR);

					if (i == rows.getSize() - 1) {
						if (sfUser.isSysAdmin()) {
							//groupSB.append(groupCodeofNv+",*;");
						}
						if (groupSB.toString().endsWith(WorldConstant.SPLITOR)) {
							groupSB.deleteCharAt(groupSB.length() - 1);
						}

						mp.put(curOrganizationId, groupSB.toString());
					}
				}
			}
		}

		return mp;
	}

/**
	 * 获取角色字符串(多选用)
	 * 格式:roleId,rolename;roleId,rolename
	 * 示例：0,管理员;1:系统用户
	 * @param conn Connection
	 * @return String
	 * @throws QueryException
	 * @throws ContainerException
	 */
	public String getRoles(Connection conn) throws QueryException, ContainerException {
		StringBuffer roleSB = new StringBuffer();
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "SELECT ROLE_ID, ROLENAME FROM SF_ROLE SR";
		if (!userAccount.isSysAdmin()) {
			sqlStr += " WHERE SR.ROLE_NAME <> ?";
			sqlArgs.add(servletConfig.getSysAdminRole());
		}
        sqlStr += " ORDER BY SR.ROLE_NAME";
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
		simpleQuery.executeQuery();
		if (simpleQuery.hasResult()) {
			RowSet rows = simpleQuery.getSearchResult();
			if (rows != null && !rows.isEmpty()) {
				Row row = null;
				for (int i = 0; i < rows.getSize(); i++) {
					row = rows.getRow(i);
					roleSB.append(row.getStrValue("ROLE_ID"));
					roleSB.append(",");
					roleSB.append(row.getStrValue("ROLENAME"));
					roleSB.append(WorldConstant.SPLITOR);
				}
			}
		}
		if (roleSB.length() > 0) {
			if (roleSB.toString().endsWith(";")) {
				roleSB.deleteCharAt(roleSB.length() - 1);
			}
		}

		return roleSB.toString();
	}

	public String getUserRight(SfUserDTO sfUserDTO) {
		StringBuffer userRightSB = new StringBuffer();
		DTOSet dtoSets = sfUserDTO.getUserRight();
		SfUserRightDTO userRightDTO = null;

		if (dtoSets != null && dtoSets.getSize() > 0) {
			for (int i = 0; i < dtoSets.getSize(); i++) {
				userRightDTO = (SfUserRightDTO) dtoSets.getDTO(i);
				userRightSB.append(userRightDTO.getGroupId());
				userRightSB.append(",");
				userRightSB.append(userRightDTO.getRoleId());
				userRightSB.append(WorldConstant.SPLITOR);
			}
		}
		if (userRightSB.length() > 0) {
			if (userRightSB.toString().endsWith(WorldConstant.SPLITOR)) {
				userRightSB.deleteCharAt(userRightSB.length() - 1);
			}
		}

		return userRightSB.toString();
	}

/**
	 * 将用户权限转换成DTOSets
	 * @param userRightStr String
	 * @param userId String
	 * @return DTOSet
	 * @throws DTOException
	 */
	public DTOSet convertStr2DTOSet(String userRightStr, String userId) throws DTOException {
		DTOSet dtoSet = new DTOSet();
		if (!StrUtil.isEmpty(userRightStr)) {
			String[][] rightArr = StrUtil.splitStr(userRightStr, ";", ",");
			SfUserRightDTO userRightDTO = null;
			for (int i = 0; i < rightArr.length; i++) {
				String[] userRight = rightArr[i];
				userRightDTO = new SfUserRightDTO();
				userRightDTO.setUserId(Integer.valueOf(userId));
				userRightDTO.setGroupId( StrUtil.strToInt( userRight[0] ) );
				userRightDTO.setRoleId(userRight[1]);
				dtoSet.addDTO(userRightDTO);
			}
		}
		return dtoSet;
	}

/**
	 * 取指定用户权限 (DTOSet)
	 * @param sfUserDTO SfUserDTO
	 * @param conn Connection
	 * @return DTOSet
	 * @throws QueryException
	 */
	public DTOSet getDTOSet(SfUserDTO sfUserDTO, Connection conn) throws QueryException {
		DTOSet dtoSet = new DTOSet();

		SfUserRightDTO userRightDTO = new SfUserRightDTO();
		userRightDTO.setUserId(sfUserDTO.getUserId());

		SfUserRightDAO userRightDAO = new SfUserRightDAO(sfUserDTO, userRightDTO, conn);
		userRightDAO.setDTOClassName(SfUserRightDTO.class.getName());
		dtoSet = (DTOSet) userRightDAO.getDataByForeignKey("userId");

		return dtoSet;
	}

	/**
	 * 获取用户组别字符产
	 * @return String
	 */
	public String getUserGroupIds() {
		String groupIds = "";
		DTOSet sfGroupSets = userAccount.getUserGroups();
		if (sfGroupSets.getSize() > 0) {
			SfGroupDTO sfGroup = null;
			for (int i = 0; i < sfGroupSets.getSize(); i++) {
				sfGroup = (SfGroupDTO) sfGroupSets.getDTO(i);
				if (i == sfGroupSets.getSize() - 1) {
					groupIds += sfGroup.getGroupId();
				} else {
					groupIds += sfGroup.getGroupId()+",";
				}
			}
		}
		return groupIds;
	}

	public static void enrichUserAccount(SfUserDTO amsUser, Connection conn) throws QueryException {
		SfUserRightDTO sfUserRight = new SfUserRightDTO();
		sfUserRight.setUserId(amsUser.getUserId());

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
				List currRoleIds = new ArrayList();
				String roleName = "";
				for (int i = 0; i < userRights.getSize(); i++) {
					tmpUserRight = (SfUserRightDTO) userRights.getDTO(i);
					int tmpGroupId = tmpUserRight.getGroupId();
					if (!vector.contains(tmpGroupId)) {
						sfGroup = new SfGroupDTO();
						sfGroup.setGroupId(tmpGroupId);
						sfGroup.setGroupname(tmpUserRight.getGroupName());
						sfGroup.setGroupCode(tmpUserRight.getGroupCode());
						sfGroups.addDTO(sfGroup);
						vector.add(tmpGroupId);
					}
					if (currGroupId == tmpUserRight.getGroupId() ) {
						currRoleIds.add(tmpUserRight.getRoleId());
					}
//                    roleName = tmpUserRight.getRolename();
				}
				amsUser.setCurrRoleIds(currRoleIds);
				amsUser.setUserGroups(sfGroups);
			}
		} catch (DTOException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
	}

	public ServletConfigDTO getServletConfig() {
		return servletConfig;
	}

	public void setServletConfig(ServletConfigDTO servletConfig) {
		this.servletConfig = servletConfig;
	}

}
