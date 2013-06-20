package com.sino.ams.net.reportforms.dao;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import com.sino.ams.net.reportforms.dto.SitusStatisticsDTO;
import com.sino.ams.net.reportforms.model.SitusStatisticsModel;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.constant.WorldConstant;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.datatrans.CustomTransData;
import com.sino.base.db.datatrans.DataRange;
import com.sino.base.db.datatrans.DataTransfer;
import com.sino.base.db.datatrans.TransRule;
import com.sino.base.db.datatrans.TransferFactory;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DataTransException;
import com.sino.base.exception.SQLModelException;
import com.sino.framework.dao.BaseDAO;
import com.sino.framework.dto.BaseUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: Zyun
 * Date: 2007-11-12
 * Time: 10:30:26
 * To change this template use File | Settings | File Templates.
 */
public class SitusStatisticsDAO  extends BaseDAO {
//     private HttpServletRequest request = null;
//    private Connection conn = null;
//    private SitusStatisticsModel situsModel = null;
//    private SQLModel sqlModel = null;
    private SfUserDTO sfUser = null;


//    public SitusStatisticsDAO(SfUserDTO userAccount, Connection conn) {
//        this.conn = conn;
//        this.situsModel = new SitusStatisticsModel();
//        this.sqlModel = new SQLModel();
//    }

        public SitusStatisticsDAO(SfUserDTO userAccount, SitusStatisticsDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
		sfUser = userAccount;
	}

//    public void setParameter(SitusStatisticsDTO projectDTO) {
//        situsModel.setDtoParameter(projectDTO);
//    }
//
//    public void clearParameter() {
//        situsModel.setDtoParameter(new SitusStatisticsDTO());
//    }
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		SitusStatisticsDTO dtoPara = (SitusStatisticsDTO) dtoParameter;
		super.sqlProducer = new SitusStatisticsModel( (SfUserDTO) userAccount, dtoPara);
	}
//    /**
//     * ͳ�Ƴ����й���
//     * @throws QueryException
//     */
//    public void produceProjects() throws QueryException {
//        sqlModel = situsModel.getProjectsModel();
//        WebPageView wpv = new WebPageView(request, conn);
//        wpv.setCalPattern(CalendarConstant.LINE_PATTERN);
//        wpv.produceWebData(sqlModel);
//    }


    /**
	 * ���ܣ�����Excel�ļ���
	 * @return File
	 * @throws com.sino.base.exception.DataTransException
	 */
	public File exportFile() throws DataTransException {
		File file = null;
		try {
			SitusStatisticsDTO SitusDTO = (SitusStatisticsDTO) dtoParameter;
			SQLModel sqlModel = sqlProducer.getPageQueryModel();
			TransRule rule = new TransRule();
			rule.setDataSource(sqlModel);
            rule.setCalPattern(CalendarConstant.LINE_PATTERN);
            rule.setSourceConn(conn);
			String fileName =  "����ͳ�Ʊ�.xls";
			String filePath = WorldConstant.USER_HOME;
			filePath += WorldConstant.FILE_SEPARATOR;
			filePath += fileName;
			rule.setTarFile(filePath);
			DataRange range = new DataRange();
			rule.setDataRange(range);

			Map fieldMap = new HashMap();
			fieldMap.put("WORKORDER_OBJECT_CODE", "�ص���");
			fieldMap.put("WORKORDER_OBJECT_NAME", "�ص���");
			fieldMap.put("TMPT_A", "�½�������");
			fieldMap.put("TMPT_B", "���ݹ�����");
			fieldMap.put("TMPT_C", "ά�޹�����");
			fieldMap.put("TMPT_D", "��Ǩ������");
			fieldMap.put("TMPT_E", "Ѳ�칤����");
			fieldMap.put("TMPT_F", "���ݹ�����");
			fieldMap.put("TMPT_G", "�滻������");
//			fieldMap.put("TMPT_B", "��������");

			rule.setFieldMap(fieldMap);

			CustomTransData custData = new CustomTransData();
			custData.setReportTitle("����ͳ�Ʊ���");
			custData.setReportPerson(sfUser.getUsername());
			custData.setNeedReportDate(true);
			rule.setCustData(custData);
			/*rule.setSheetSize(1000);*/
			//���÷�ҳ��ʾ
			TransferFactory factory = new TransferFactory();
			DataTransfer transfer = factory.getTransfer(rule);
			transfer.transData();
			file = (File) transfer.getTransResult();
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataTransException(ex);
		}
		return file;
	}
}
