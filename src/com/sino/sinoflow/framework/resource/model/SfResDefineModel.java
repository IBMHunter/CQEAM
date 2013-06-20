package com.sino.sinoflow.framework.resource.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sino.base.db.sql.model.SQLModel;
import com.sino.sinoflow.appbase.model.SFSQLProducer;
import com.sino.sinoflow.constant.WebAttrConstant;
import com.sino.sinoflow.framework.resource.dto.SfResDefineDTO;
import com.sino.sinoflow.user.dto.SfUserBaseDTO;


/**
 * <p>Title: SfResDefineModel</p>
 * <p>Description:�����Զ�����SQL��������SfResDefineModel�����������Ҫ�����޸�</p>
 * <p>Copyright: Copyright (c) {year}</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author mshtang
 * @version 1.0
 */


public class SfResDefineModel extends SFSQLProducer {

    /**
     * ���ܣ�SF_RES_DEFINE ���ݿ�SQL����㹹�캯��
     * @param userAccount SfUserBaseDTO ����ϵͳ�����ղ����û�����
     * @param dtoParameter SfResDefineDTO ���β���������
     */
    public SfResDefineModel(SfUserBaseDTO userAccount, SfResDefineDTO dtoParameter) {
        super(userAccount, dtoParameter);
        if( null != userAccount ){
        	dtoParameter.setCreatedBy(userAccount.getUserId());
        }
    }
 

    /**
     * ���ܣ�����Զ��������ݲ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݲ�����SQLModel
     */
    public SQLModel getDataCreateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
        String sqlStr = "INSERT INTO "
                        + " SF_RES_DEFINE("
                        + " RES_ID,"
                        + " RES_PAR_ID,"
                        + " RES_NAME,"
                        + " RES_URL,"
                        + " SORT_NO,"
                        + " IS_POPUP,"
                        + " PRINCIPAL,"
                        + " ENABLED,"
                        + " IS_INNER,"
                        + " VISIBLE,"
                        + " POPSCRIPT,"
                        + " LEVEL_NUM,"
                        + " BUSINESS_DESC,"
                        + " CREATION_DATE,"
                        + " CREATED_BY"
                        + ") VALUES ("
                        + " dbo.SRP_GET_NEXT_RESID(?), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        sqlArgs.add(dto.getResParId());
        sqlArgs.add(dto.getResParId());
        sqlArgs.add(dto.getResName());
        sqlArgs.add(dto.getResUrl());
        sqlArgs.add(dto.getSortNo());
        sqlArgs.add(dto.getIsPopup());
        sqlArgs.add(dto.getPrincipal());
        sqlArgs.add(dto.getEnabled());
        sqlArgs.add(dto.getIsInner());
        sqlArgs.add(dto.getVisible());
        sqlArgs.add(dto.getPopscript());
        sqlArgs.add(dto.getLevelNum());
        sqlArgs.add(dto.getBusinessDesc());
        sqlArgs.add(dto.getCreationDate());
        sqlArgs.add(dto.getCreatedBy());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ��������ݸ���SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel �������ݸ�����SQLModel
     */
    public SQLModel getDataUpdateModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
        String sqlStr = "UPDATE SF_RES_DEFINE"
                        + " SET"
                        + " RES_ID = ?,"
                        + " RES_PAR_ID = ?,"
                        + " RES_NAME = ?,"
                        + " RES_URL = ?,"
                        + " SORT_NO = ?,"
                        + " IS_POPUP = ?,"
                        + " PRINCIPAL = ?,"
                        + " ENABLED = ?,"
                        + " IS_INNER = ?,"
                        + " VISIBLE = ?,"
                        + " POPSCRIPT = ?,"
                       // + " LEVEL_NUM = ?,"
                        + " BUSINESS_DESC = ?,"
                        + " LAST_UPDATE_DATE = ?,"
                        + " LAST_UPDATE_BY = ?"
                        + " WHERE"
                        + " SYSTEM_ID = ?";
        sqlArgs.add(dto.getResId());
        sqlArgs.add(dto.getResParId());
        sqlArgs.add(dto.getResName());
        sqlArgs.add(dto.getResUrl());
        sqlArgs.add(dto.getSortNo());
        sqlArgs.add(dto.getIsPopup());
        sqlArgs.add(dto.getPrincipal());
        sqlArgs.add(dto.getEnabled());
        sqlArgs.add(dto.getIsInner());
        sqlArgs.add(dto.getVisible());
        sqlArgs.add(dto.getPopscript());
        //sqlArgs.add(dto.getLevelNum());
        sqlArgs.add(dto.getBusinessDesc());
        Date date=new Date();
        sqlArgs.add(String.format("%tY",date)+"-"+String.format("%tm",date)+"-"+String.format("%td",date));
        sqlArgs.add(dto.getLastUpdateBy());
        sqlArgs.add(dto.getSystemId());

        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ���������ɾ��SQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ��������ɾ����SQLModel
     */
    public SQLModel getDataDeleteModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
        String sqlStr = "DELETE FROM"
                        + " SF_RES_DEFINE"
                        + " WHERE"
                        + " SYSTEM_ID = ?";
        sqlArgs.add(dto.getSystemId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����������ϸ��Ϣ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����������ϸ��Ϣ��ѯ��SQLModel
     */
    public SQLModel getPrimaryKeyDataModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
        String sqlStr = "SELECT"
                        + " *"
                        + " FROM"
                        + " SF_RES_DEFINE"
                        + " WHERE"
                        + " SYSTEM_ID = ?";
        sqlArgs.add(dto.getSystemId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����Զ�����ҳ�淭ҳ��ѯSQLModel�������ʵ����Ҫ�޸ġ�
     * @return SQLModel ����ҳ�淭ҳ��ѯSQLModel
     */
    public SQLModel getPageQueryModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
        String sqlStr = "SELECT"
                        + " A.SYSTEM_ID,"
                        + " A.RES_ID,"
                        + " A.RES_PAR_ID,"
                        + " A.RES_NAME,"
                        + " A.RES_URL,"
                        + " ( CASE A.ENABLED WHEN 'Y' THEN '<font color=green>��Ч</font>' ELSE '<font color=red>��Ч</font>' END ) ENABLED,"
                        + " ( CASE A.VISIBLE WHEN 'Y' THEN '<font color=green>�ɼ�</font>' ELSE '<font color=red>���ɼ�</font>' END ) VISIBLE,"
                        + " B.RES_NAME  PAR_NAME"
                        + " FROM"
                        + " SF_RES_DEFINE A"
//                        + " ,SF_RES_DEFINE B"
                        + " LEFT OUTER JOIN SF_RES_DEFINE B ON (A.RES_PAR_ID = B.RES_ID)"
                        + " WHERE"
//                        + " A.RES_PAR_ID *= B.RES_ID AND"
                        + " (? = '' OR A.ENABLED = ?)"
                        + " AND (? = '' OR A.VISIBLE = ?)"
                        + " AND (? = '' OR A.RES_NAME LIKE ?)"
                        + " AND (? = '' OR A.RES_URL LIKE ?)"
                        + " AND (? = '' OR (A.RES_ID LIKE ? OR A.RES_ID = ?))"
//                        + " ORDER BY"
//                        + " A.RES_ID,"
//                        + " A.RES_PAR_ID"
                        ;
        sqlArgs.add(dto.getEnabled());
        sqlArgs.add(dto.getEnabled());
        sqlArgs.add(dto.getVisible());
        sqlArgs.add(dto.getVisible());
        sqlArgs.add(dto.getResName());
        sqlArgs.add(dto.getResName());
        sqlArgs.add(dto.getResUrl());
        sqlArgs.add(dto.getResUrl());
        sqlArgs.add(dto.getResId());
        sqlArgs.add(dto.getResId() + ".%");
        sqlArgs.add(dto.getResId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡURL��Դ�˵���Ŀ�������б�SQL��
     * ����ʾָ����Դ��������Դ
     * @param resourceId ָ����Դ
     * @return SQLModel
     */
    public SQLModel getResourceOptionModel(String resourceId) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT"
                        + " SRD.RES_ID,"
                        + " SRD.RES_NAME"
                        + " FROM"
                        + " SF_RES_DEFINE SRD"
                        + " WHERE"
                        + " (SRD.RES_ID <> ? "
                        + " AND SRD.RES_ID NOT LIKE ?)"
                        + " OR ? = ''"
                        + " ORDER  BY"
                        + " SRD.RES_ID,"
                        + " SRD.RES_PAR_ID";
        sqlArgs.add(resourceId);
        sqlArgs.add(resourceId + ".%");
        sqlArgs.add(resourceId);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�����URL��Դ����SQL����Ŀ������
     * @return SQLModel
     */
    public SQLModel getResourceTreeModel() {
    	SQLModel sqlModel = new SQLModel(); 
    	
    	StringBuffer sb = new StringBuffer();
    	
    	sb.append( " 	SELECT \n ");
    	sb.append( " 	A.RES_ID ID1, \n ");
    	sb.append( " 	A.RES_NAME TEXT1, \n ");
    	sb.append( " 	A2.RES_ID ID2, \n ");
    	sb.append( " 	A2.RES_NAME TEXT2, \n ");
    	sb.append( " 	A3.RES_ID ID3, \n ");
    	sb.append( " 	A3.RES_NAME TEXT3  \n ");
    	sb.append( " FROM \n ");
    	sb.append( " 	SF_RES_DEFINE A, \n ");
    	sb.append( " 	SF_RES_DEFINE A2, \n ");
    	sb.append( " 	SF_RES_DEFINE A3  \n ");
    	sb.append( " WHERE \n ");
    	sb.append( " 	A2.RES_ID *= A3.RES_PAR_ID  \n ");
    	sb.append( " 	AND A2.RES_PAR_ID =* A.RES_ID \n ");
    	sb.append( " 	AND A.RES_PAR_ID = '' \n ");  
//    	sb.append( " UNION  \n ");
//    	sb.append( " SELECT \n ");
//    	sb.append( " 	A.RES_ID ID1, \n ");
//    	sb.append( " 	A.RES_NAME TEXT1, \n ");
//    	sb.append( " 	A2.RES_ID ID2, \n ");
//    	sb.append( " 	A2.RES_NAME TEXT2, \n ");
//    	sb.append( "     '' ID3, \n ");
//    	sb.append( "     '' TEXT3 \n ");
//    	sb.append( " FROM \n ");
//    	sb.append( " 	SF_RES_DEFINE A, \n ");
//    	sb.append( " 	SF_RES_DEFINE A2  \n ");
//    	sb.append( " WHERE  \n ");
//    	sb.append( " 	 A2.RES_PAR_ID = A.RES_ID  \n ");
//    	sb.append( " AND NOT EXISTS( \n ");
//    	sb.append( "     SELECT NULL FROM SF_RES_DEFINE A3 WHERE A2.RES_ID = A3.RES_PAR_ID  \n ");
//    	sb.append( " ) \n ");
//    	sb.append( " AND CHARINDEX( '.', A.RES_ID ) = 0 \n ");
    	sb.append( " ORDER BY \n ");
    	sb.append( " 	A.RES_ID, \n ");
    	sb.append( " 	A2.RES_ID , \n ");
    	sb.append( " 	A3.RES_ID \n ");
    	 
//	    String sqlStr  = " SELECT "
//	      			   + " A.RES_ID ID1, "
//	      			   + " A.RES_NAME TEXT1, "
//	      			   + " A2.RES_ID ID2, "
//	      			   + " A2.RES_NAME TEXT2, "
//	      			   + " A3.RES_ID ID3, "
//	      			   + " A3.RES_NAME TEXT3 "
//	      			   + " FROM "
//	      			   + " SF_RES_DEFINE A, "
//	      			   + " SF_RES_DEFINE A2, "
//	      			   + " SF_RES_DEFINE A3 "
//	      			   + " WHERE "
//	      			   + " A2.RES_ID = A3.RES_PAR_ID   "
//	      			   + " AND A2.RES_PAR_ID = A.RES_ID "
//	      			   + " ORDER BY A.RES_ID, A2.RES_ID , A3.RES_ID ";
	    sqlModel.setSqlStr( sb.toString() ); 
	    return sqlModel; 
//        SQLModel sqlModel = new SQLModel();
//        String sqlStr = " SELECT"
//                        + " A.SYSTEM_ID,"
//                        + " A.RES_ID,"
//                        + " A.RES_PAR_ID,"
//                        + " A.RES_NAME,"
//                        + " A.RES_ID RES_URL,"
//                        + " A.SORT_NO,"
//                        + " 'N' IS_POPUP,"
//                        + " A.POPSCRIPT"
//                        + " FROM"
//                        + " SF_RES_DEFINE A"
//                        + " ORDER BY"
//                        + " A.RES_ID,"
//                        + " A.RES_PAR_ID";
//        sqlModel.setSqlStr(sqlStr);
//        return sqlModel;
    }

    /**
     * ���ܣ���ȡָ������Դ�µ���һ����Դ���SQL
     * @param resourcePid String
     * @return SQLModel
     */
    public SQLModel getAllChildModel(String resourcePid) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT"
                        + " *"
                        + " FROM"
                        + " SF_RES_DEFINE SRD"
                        + " WHERE"
                        + " SRD.RES_ID LIKE ?"
                        + " ORDER BY"
                        + " RES_ID,"
                        + " RES_PAR_ID";
        sqlArgs.add(resourcePid + ".%");
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡָ������Դ�µ���һ����Դ���SQL
     * @param resourcePid String
     * @return SQLModel
     */
    public SQLModel getNewChildModel(String resourcePid) {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = " SELECT"
                        + " dbo.SRP_GET_NEXT_RESID(?) RES_ID";
        sqlArgs.add(resourcePid);
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡָ������Դ�µ���һ����ԴʧЧSQL
     * @return SQLModel
     */
    public SQLModel getPassviateResourceModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
        String sqlStr = "UPDATE SF_RES_DEFINE"
                        + " SET"
                        + " ENABLED = ?"
                        + " WHERE"
                        + " RES_ID = ?"
                        + " OR RES_ID LIKE ?";
        sqlArgs.add(dto.getEnabled());
        sqlArgs.add(dto.getResId());
        sqlArgs.add(dto.getResId() + ".%");
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ���ȡָ����Դ������ֱϵ�ϼ���Ŀ��Դ����ЧSQL��
     * @return SQLModel
     */
    public SQLModel getActivateResourceModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
        String sqlStr = "UPDATE SF_RES_DEFINE"
                        + " SET"
                        + " ENABLED = ?"
                        + " WHERE"
                        + " ? LIKE RES_ID||'%'";
        sqlArgs.add(dto.getEnabled());
        sqlArgs.add(dto.getResId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * ���ܣ�������ԴID��ȡURL��ԴSQL��
     * @return SQLModel
     */
    public SQLModel getResourceByIdModel() {
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
        String sqlStr = "SELECT"
                        + " SRD.*,"
                        + " dbo.SRP_HAS_CHILD(SRD.RES_ID) HAS_CHILD"
                        + " FROM"
                        + " SF_RES_DEFINE SRD"
                        + " WHERE"
                        + " SRD.ENABLED = ?"
                        + " AND SRD.VISIBLE = ?"
                        + " AND SRD.RES_ID = ?";
        sqlArgs.add(WebAttrConstant.TRUE_VALUE);
        sqlArgs.add(WebAttrConstant.TRUE_VALUE);
        sqlArgs.add(dto.getResId());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * ����RES_ID��ȡ
     * @return
     */
    public SQLModel getResourceById(String resId ){
    	SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
        String sqlStr = "SELECT"
                        + " SRD.* " 
                        + " FROM"
                        + " SF_RES_DEFINE SRD"
                        + " WHERE" 
                        + " SRD.RES_ID = ?"; 
        
        sqlArgs.add( resId );
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
    
    /**
     * ����RES_ID��ȡ
     * @return
     */
    public SQLModel getResourceByName(String resName ){
    	SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        SfResDefineDTO dto = (SfResDefineDTO)dtoParameter;
        String sqlStr = "SELECT"
                        + " SRD.* " 
                        + " FROM"
                        + " SF_RES_DEFINE SRD"
                        + " WHERE" 
                        + " SRD.RES_NAME = ?"; 
        
        sqlArgs.add( resName );
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
}
