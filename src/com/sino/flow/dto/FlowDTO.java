package com.sino.flow.dto;

import com.sino.ams.bean.SyBaseSQLUtil;
import com.sino.base.SinoBaseObject;
import com.sino.base.dto.DTO;
import com.sino.flow.constant.FlowConstant;

/**
 * Created by wwb.
 * User: V-wangwenbin
 * Date: 2007-11-9
 * Time: 14:48:00
 * FlowAction�����DTO
 */
public class FlowDTO extends SinoBaseObject implements DTO {
    private String applyId = "";//Ӧ��ID   ����Ϊ��
    private String applyNo = "";//Ӧ�ñ��  ����Ϊ��
    private String actId = "";//���̼�¼ID   ��һ�ο���Ϊ�գ�
    private String activity = "";//���̵���������ͬ�⣩Ϊ9�����򣨾ܾ���Ϊ10�� ����Ϊ��
    private String sessionCurTaskId = "";//�ͻ��˱���ĵ�ǰ�ڵ㣬������תʱ������Ϊ��
    private int sessionUserId = SyBaseSQLUtil.NULL_INT_VALUE; //��ǰ�û�������һ���������ˣ��п����Ǵ����� ����Ϊ��
    private String sessionUserName = "";//��ǰ�û�����      ����Ϊ�գ���Ҫ���ڶ���֪ͨ
    private String approveContent = "";//��������� ��ò�Ϊ��
    private String toTaskId = "";//Ŀ�Ľڵ�    ������򣬲���Ϊ��
    private String toUserIds = "";//Ŀ���ˣ�����Ϊ���� ����÷ֺŷָ�   ������򣬲���Ϊ��
    private String procId = "";//����Ϊ��
    private String prevTaskId = "";//���һ��һ���˻أ�Ӧ���˵��Ľڵ㣬����Ϊ��
    private String prevUserId = "";//���һ��һ���˻أ�Ӧ���˵����û�  ����Ϊ�գ����Ϊ�գ�ϵͳ���Զ���
    private String curTaskId = "";//��ǰ�ڵ㣬����Ϊ��
    private String beginTaskId = "";//һ�����̵Ŀ�ʼ�ڵ㣬����Ϊ��
    private String beginUserId = "";//һ�����̵������ˣ�����Ϊ��
    private String procName = "";
    private String sectionRight = "";
    
    protected String primaryKey = "";
    protected String orderNo = "";
    protected String orderName = "";

    public FlowDTO() {
    }

    public FlowDTO(String applyId, String applyNo, String actId, String activity, String sessionCurTaskId, int sessionUserId, String sessionUserName, String approveContent, String toTaskId, String toUserIds, String procId) {
        this.applyId = applyId;
        this.applyNo = applyNo;
        this.actId = actId;
        this.activity = activity;
        this.sessionUserId = sessionUserId;
        this.approveContent = approveContent;
        this.toTaskId = toTaskId;
        this.toUserIds = toUserIds;
        this.sessionUserName = sessionUserName;
        this.procId = procId;
        this.sessionCurTaskId = sessionCurTaskId;
    }

    public String getApplyId() {
        return applyId;
    }

    public String getProcId() {
        return procId;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getActId() {
        return actId;
    }

    public void setActId(String actId) {
        this.actId = actId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getSessionUserId() {
        return sessionUserId;
    }

    public void setSessionUserId(int sessionUserId) {
        this.sessionUserId = sessionUserId;
    }

    public String getApproveContent() {
        return approveContent;
    }

    public void setApproveContent(String approveContent) {
        this.approveContent = approveContent;
    }

    public String getToTaskId() {
        return toTaskId;
    }

    public void setToTaskId(String toTaskId) {
        this.toTaskId = toTaskId;
    }

    public String getToUserIds() {
        return toUserIds;
    }

    public void setToUserIds(String toUserIds) {
        this.toUserIds = toUserIds;
    }

    public String getSessionUserName() {
        return sessionUserName;
    }

    public void setSessionUserName(String sessionUserName) {
        this.sessionUserName = sessionUserName;
    }

    public String getPrevTaskId() {
        return prevTaskId;
    }

    public void setPrevTaskId(String prevTaskId) {
        this.prevTaskId = prevTaskId;
    }

    public String getPrevUserId() {
        return prevUserId;
    }

    public void setPrevUserId(String prevUserId) {
        this.prevUserId = prevUserId;
    }

    public String getCurTaskId() {
        return curTaskId;
    }

    public void setCurTaskId(String curTaskId) {
        this.curTaskId = curTaskId;
    }

    public String getBeginTaskId() {
        return beginTaskId;
    }

    public void setBeginTaskId(String beginTaskId) {
        this.beginTaskId = beginTaskId;
    }

    public String getBeginUserId() {
        return beginUserId;
    }

    public void setBeginUserId(String beginUserId) {
        this.beginUserId = beginUserId;
    }

    public String getSessionCurTaskId() {
        return sessionCurTaskId;
    }

    public void setSessionCurTaskId(String sessionCurTaskId) {
        this.sessionCurTaskId = sessionCurTaskId;
    }

    //��DTO�ǲ���DTO��DTO����Щ�����ǲ���Ϊ�յģ����Ϊ�գ�����û�취��ת��
    public String validate() {
        String retValue = "";
        if (this.activity == null || this.activity.equals("")) {
            retValue = "activity";
        } else if (this.activity.equals(FlowConstant.FLOW_CODE_NEXT)) {
            //��һ���ڵ�ID����Ϊ�գ����Ϊ�գ��޷�Ťת
            if (this.toTaskId == null || this.toTaskId.equals("")) {
                retValue = "toTaskId";
            }

        } else if ( this.sessionUserId > 0 ) {
            retValue = "sessionUserId";
        } else if (this.sessionCurTaskId == null || this.sessionCurTaskId.equals("")) {
            retValue = "sessionCurTaskId";// ���������Ҫȷ�����̱�û���ظ��ύ
        }
        return retValue;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public String getSectionRight() {
        return sectionRight;
    }

    public void setSectionRight(String sectionRight) {
        this.sectionRight = sectionRight;
    }

	public String getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
}
