package com.sino.framework.sql;

import com.sino.base.SinoBaseObject;
import com.sino.base.constant.calen.CalendarConstant;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTO;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.SQLModelException;
import com.sino.base.log.Logger;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.security.dto.ServletConfigDTO;
/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public abstract class BaseSQLProducer extends SinoBaseObject implements CalendarConstant{
    protected DTO dtoParameter = null;
    protected BaseUserDTO userAccount = null;
	protected ServletConfigDTO servletConfig = null;

    public BaseSQLProducer(BaseUserDTO userAccount, DTO dtoParameter) {
        this.userAccount = userAccount;
        this.setDTOParameter(dtoParameter);
    }

	/**
	 * ���ܣ����ó�ʼ�����ò���
	 * @param servletConfig ServletConfigDTO
	 */
	public void setServletConfig(ServletConfigDTO servletConfig) {
		if(servletConfig != null){
			this.servletConfig = servletConfig;
		}
	}

    /**
     * ���ܣ������µĲ��������Խ����µ�SQL���졣
     * @param dtoParameter DTO
     */
    public void setDTOParameter(DTO dtoParameter){
        this.dtoParameter = dtoParameter;
    }

    /**
     * ���ܣ���ȡ�û���Ϣ��
     * @return BaseUserDTO �����Ӧ��Ӧ�������ص��û���Ϣת������Ӧ�ļ̳��ࡣ
     */
    public BaseUserDTO getUserAccount(){
        return userAccount;
    }

    /**
     * ���ܣ���������������SQLModel
     * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
     * @return SQLModel
	 * @throws SQLModelException
     */
    public SQLModel getDataCreateModel() throws SQLModelException{
        return null;
    }

    /**
     * ���ܣ����������޸ĵ�SQLModel
     * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
     * @return SQLModel
	 * @throws SQLModelException
     */
    public SQLModel getDataUpdateModel() throws SQLModelException{
        return null;
    }

    /**
     * ���ܣ���������ɾ����SQLModel
     * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
     * @return SQLModel
	 * @throws SQLModelException
     */
    public SQLModel getDataDeleteModel() throws SQLModelException{
        return null;
    }

	/**
	 * ���ܣ����ظ���������������ɾ����SQLModel
	 * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getDeleteByPrimaryKeyModel() throws SQLModelException{
		return null;
    }

    /**
     * ���ܣ�����������ϸ��Ϣ��SQLModel
     * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
     * @return SQLModel
	 * @throws SQLModelException
     */
    public SQLModel getPrimaryKeyDataModel() throws SQLModelException{
        return null;
    }

    /**
     * ���ܣ����������ȡ����
     * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
     * @param foreignKey ����ֶ����ƶ�Ӧ��DTO���ԡ�
     * @return SQLModel
	 * @throws SQLModelException
     */
    public SQLModel getDataByForeignKeyModel(String foreignKey) throws SQLModelException{
        return null;
    }

	/**
	 * ���ܣ��������ɾ������
	 * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
	 * @param foreignKey ����ֶ����ƶ�Ӧ��DTO���ԡ�
	 * @return SQLModel
	 * @throws SQLModelException
	 */
	public SQLModel getDeleteByForeignKeyModel(String foreignKey) throws SQLModelException{
		return null;
	}

    /**
     * ���ܣ����ض������ݵ�SQLModel
     * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ�֡��̳�����Ҫ��dtoParameter����SQLModel��</B>
     * @return SQLModel
	 * @throws SQLModelException
     */
    public SQLModel getMuxDataModel() throws SQLModelException{
        return null;
    }

    /**
     * ���ܣ�����ҳ�淭ҳ��ѯʱ����Ҫ��SQLModel
     * <B>Ĭ��Ϊ��ʵ�֡����ɾ���Ӧ��ѡ���Ƿ���Ҫʵ��</B>
     * @return SQLModel
	 * @throws SQLModelException
     */
    public SQLModel getPageQueryModel() throws SQLModelException{
        return null;
    }


    /**
     * ���ܣ�������Ĳ���ֵ��ա�
     * <B>ʹ�ó��ϣ��������ݽ�����Ӧ��������Ҫ�����µĲ�ѯ������ʱ����Ҫԭ����������ʱ���Ե��ô˷���</B>
     * @throws DTOException
     */
    public void clearDTOParameter() throws DTOException{
        try {
            String dtoName = DTO.class.getName();
            dtoParameter = (DTO) Class.forName(dtoName).newInstance();
        } catch (ClassNotFoundException ex) {
            Logger.logError(ex);
            throw new DTOException(ex);
        } catch (IllegalAccessException ex) {
            Logger.logError(ex);
            throw new DTOException(ex);
        } catch (InstantiationException ex) {
            Logger.logError(ex);
            throw new DTOException(ex);
        }
    }
    
    public SQLModel getExportModel() throws SQLModelException{
        SQLModel sm = null;
        try {
            sm =  getPageQueryModel();
        } catch (SQLModelException e) {
            Logger.logError(e);
        }
        return sm;
    }
}
