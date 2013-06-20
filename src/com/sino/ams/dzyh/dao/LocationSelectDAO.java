package com.sino.ams.dzyh.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.dzyh.dto.EamDhCheckHeaderDTO;
import com.sino.ams.dzyh.model.LocationSelectModel;
import com.sino.base.constant.WorldConstant;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.QueryException;
import com.sino.base.exception.ReflectException;
import com.sino.base.util.ReflectionUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class LocationSelectDAO extends AMSBaseDAO {

	private static List fields = new ArrayList();

	static {
		fields.add("checkLocation");
		fields.add("locationName");
		fields.add("impDeptName");
		fields.add("impDeptCode");
		fields.add("implementBy");
		fields.add("implementUser");
		fields.add("orderType");
		fields.add("orderTypeValue");
	}

	public LocationSelectDAO(BaseUserDTO userAccount, EamDhCheckHeaderDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		EamDhCheckHeaderDTO dto = (EamDhCheckHeaderDTO) dtoParameter;
		sqlProducer = new LocationSelectModel(userAccount, dto);
	}

	/**
	 * ���ܣ����ع����ص�XML����
	 * @return StringBuffer
	 * @throws QueryException
	 */
	public StringBuffer getLocationXml() throws QueryException {
		StringBuffer locXML = new StringBuffer();
		try {
			LocationSelectModel modelProducer = (LocationSelectModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getLocationsModel();
			SimpleQuery simp = new SimpleQuery(sqlModel, conn);
			simp.setDTOClassName(EamDhCheckHeaderDTO.class.getName());
			simp.executeQuery();
			DTOSet locations = simp.getDTOSet();
			if (locations != null && !locations.isEmpty()) {
				int locationCount = locations.getSize();
				locXML.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
				locXML.append(WorldConstant.ENTER_CHAR);
				locXML.append(WorldConstant.TAB_CHAR);
				locXML.append("<locations>");
				int fieldCount = fields.size();
				EamDhCheckHeaderDTO location = null;
				String fieldName = "";
				Object fieldValue = null;
				for (int i = 0; i < locationCount; i++) {
					location = (EamDhCheckHeaderDTO) locations.getDTO(i);
					locXML.append(WorldConstant.ENTER_CHAR);
					locXML.append(WorldConstant.TAB_CHAR);
					locXML.append(WorldConstant.TAB_CHAR);
					locXML.append("<location ");
					for (int j = 0; j < fieldCount; j++) {
						fieldName = (String) fields.get(j);
						fieldValue = ReflectionUtil.getProperty(location, fieldName);
						locXML.append(fieldName);
						locXML.append(" =\"");
						locXML.append(fieldValue);
						if (j == fieldCount - 1) {
							locXML.append("\"");
						} else {
							locXML.append("\" ");
						}
					}
					locXML.append("/>");
				}
				locXML.append(WorldConstant.ENTER_CHAR);
				locXML.append(WorldConstant.TAB_CHAR);
				locXML.append("</locations>");
			}
		} catch (ReflectException ex) {
			ex.printLog();
			throw new QueryException(ex);
		}
		return locXML;
	}
}
