package com.sino.ams.sampling.dao;

import java.sql.Connection;

import com.sino.ams.appbase.dao.AMSBaseDAO;
import com.sino.ams.sampling.constant.SamplingDicts;
import com.sino.ams.sampling.dto.AmsAssetsSamplingLineDTO;
import com.sino.ams.sampling.model.OrderLineUploadModel;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.dto.DTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.DataHandleException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.util.StrUtil;
import com.sino.framework.dto.BaseUserDTO;

/**
 * <p>Title: SinoAMS</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class OrderLineUploadDAO extends AMSBaseDAO {

	public OrderLineUploadDAO(BaseUserDTO userAccount, AmsAssetsSamplingLineDTO dtoParameter, Connection conn) {
		super(userAccount, dtoParameter, conn);
	}

	/**
	 * ���ܣ�SQL������baseSQLProducer�ĳ�ʼ���������DAO�̳�ʱ��ʼ�������SQL��������
	 * @param userAccount BaseUserDTO
	 * @param dtoParameter DTO
	 */
	protected void initSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
		AmsAssetsSamplingLineDTO dto = (AmsAssetsSamplingLineDTO)dtoParameter;
		sqlProducer = new OrderLineUploadModel(userAccount, dto);
	}

	/**
	 * ���ܣ������豸��
	 * <B>����豸�����ڸù����·�ʱ�������У���ֱ�Ӹ����������ֶ���Ϣ</B>
	 * <B>����豸�������ڸù����·�ʱ�������У����뵽�ù���������Ϣ�У���������SYSTEM_STATUSֵΪN��BATCH_IDֵΪ��</B>
	 * @param itemExist boolean ��ǰ�����豸�Ƿ�����ڸù����·�ʱ��������
	 * @throws DataHandleException
	 */
	public void uploadOrderLine(boolean itemExist) throws DataHandleException {
		try {
			AmsAssetsSamplingLineDTO dto = (AmsAssetsSamplingLineDTO)dtoParameter;

			dto.setScanItemCode(dto.getItemCode());
			dto.setScanItemCategory(dto.getItemCategory());
			dto.setScanItemName(dto.getItemName());
			dto.setScanItemSpec(dto.getItemSpec());
			dto.setScanResponsibilityUser(dto.getResponsibilityUser());
			dto.setScanResponsibilityDept(dto.getResponsibilityDept());
			dto.setScanMaintainUser(dto.getMaintainUser());
			dto.setScanStartDate(dto.getStartDate().toString()); //��������


			if(itemExist){//ɨ���豸Ϊ�������·��豸
				if (StrUtil.isEmpty(dto.getItemCode())) { //��ʾ�����ӵ��豸���࣬��Ҫ����רҵ�����ƣ��ͺŵ�
					dto.setRemark("PDA�����豸����");
				}
			} else {//ɨ���豸���Ǳ������·��豸
				dto.setSystemStatus(SamplingDicts.STATUS_NO);
				if (StrUtil.isEmpty(dto.getItemCode())) { //��ʾ�����ӵ��豸���࣬��Ҫ����רҵ�����ƣ��ͺŵ�
					dto.setRemark("PDA���������룬�������豸����");
				} else {
					dto.setRemark("PDA����������");
				}
			}
			setDTOParameter(dto);
			OrderLineUploadModel modelProducer = (OrderLineUploadModel) sqlProducer;
			SQLModel sqlModel = modelProducer.getLineUploadModel(itemExist);
			DBOperator.updateRecord(sqlModel, conn);
		} catch (SQLModelException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		} catch (CalendarException ex) {
			ex.printLog();
			throw new DataHandleException(ex);
		}
	}

	/**
	 * ���ܣ�����PDAδɨ�赽���豸��ɨ��״̬Ϊ��N����
	 * <B>�÷�����Ҫ�ڶ�ɨ�赽���豸������ٽ��д���</B>
	 * @throws DataHandleException
	 */
	public void updateLeftBarcodes() throws DataHandleException {
		OrderLineUploadModel modelProducer = (OrderLineUploadModel) sqlProducer;
		SQLModel sqlModel = modelProducer.getLeftBarcodesUpdateModel();
		DBOperator.updateRecord(sqlModel, conn);
	}
}
