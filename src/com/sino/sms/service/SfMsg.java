package com.sino.sms.service;

import java.sql.Connection;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.DTOException;
import com.sino.base.exception.QueryException;
import com.sino.base.util.StrUtil;
import com.sino.sms.constant.SMSConstant;
import com.sino.sms.dto.SfMsgDefineDTO;
import com.sino.sms.model.SfMSGModel;

/**
 * User: zhoujs
 * Date: 2008-4-7
 * Time: 15:47:29
 * Function:
 */
public class SfMsg {
    SfMSGModel sfMSGModell = null;

    public SfMsg() {
        sfMSGModell = new SfMSGModel();
    }

    public void sendMsg(Connection conn) {

    }

    /**
     * �ռ������
     * @param conn
     * @return
     * @throws QueryException
     * @throws ContainerException
     * @throws DTOException
     */
    public DTOSet getInBoxMsg(Connection conn) throws QueryException, ContainerException, DTOException {
        DTOSet msgDtoSet = new DTOSet();
        SQLModel sqlModel = sfMSGModell.getInBoxModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            RowSet rs = simpleQuery.getSearchResult();
            Row row = null;
            for (int i = 0; i < rs.getSize(); i++) {
                row = rs.getRow(i);
                SfMsgDefineDTO sfMsgDTO = new SfMsgDefineDTO();
                sfMsgDTO.setCellPhone(row.getStrValue("MOVETEL"));
                sfMsgDTO.setMail(row.getStrValue("MAIL"));
                sfMsgDTO.setMsgContent(row.getStrValue("USERNAME") + ":�����ʲ�ʵ�����ϵͳ�ռ�������" + row.getStrValue("NUM") + "�����죬������ʲ�ʵ�����ϵͳ�ռ�����д���");
                msgDtoSet.addDTO(sfMsgDTO);
            }
        }
        return msgDtoSet;
    }

    /**
     * ���鵵����
     * @param conn
     * @return
     */
    public DTOSet getArchieveMsg(Connection conn) throws QueryException, ContainerException, DTOException {
        DTOSet msgDTOSet = new DTOSet();
        SQLModel sqlModel = new SQLModel();
        sqlModel = sfMSGModell.getArchieveModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            RowSet rs = simpleQuery.getSearchResult();
            Row row = null;
            for (int i = 0; i < rs.getSize(); i++) {
                row = rs.getRow(i);
                SfMsgDefineDTO sfMsgDTO = new SfMsgDefineDTO();
                String userPhone = row.getStrValue("MOVETEL");
                String orderCount = row.getStrValue("NUM");
                sfMsgDTO.setCellPhone(userPhone);
                sfMsgDTO.setMail(row.getStrValue("MAIL"));
                sfMsgDTO.setMsgContent(row.getStrValue("USERNAME") + "������" + orderCount + "��������Ҫ�鵵��������ʲ�ʵ�����ϵͳ�еģ������鵵�����д���");
                msgDTOSet.addDTO(sfMsgDTO);
            }
        }
        return msgDTOSet;
    }

    /**
     * �̵㹤��-���鵵����
     * @param conn
     * @return
     */
    public DTOSet getCheckOrderArchieveMsg(Connection conn) throws QueryException, ContainerException, DTOException {
        DTOSet msgDTOSet = new DTOSet();
        SQLModel sqlModel = new SQLModel();
        sqlModel = sfMSGModell.getCheckOrderArchieveModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            RowSet rs = simpleQuery.getSearchResult();
            Row row = null;
            for (int i = 0; i < rs.getSize(); i++) {
                row = rs.getRow(i);
                SfMsgDefineDTO sfMsgDTO = new SfMsgDefineDTO();
                String userPhone = row.getStrValue("MOVETEL");
                String mail = row.getStrValue("MAIL");
                String orderCount = row.getStrValue("NUM");
                sfMsgDTO.setCellPhone(userPhone);
                sfMsgDTO.setMail(mail);
                sfMsgDTO.setMsgContent(row.getStrValue("USERNAME") + "������" + orderCount + "��������Ҫ�鵵��������ʲ�ʵ�����ϵͳ�еģ��̵㹤���鵵�����д���");
                msgDTOSet.addDTO(sfMsgDTO);
            }
        }
        return msgDTOSet;
    }

    /**
     * ���ݷ����ȡ��Ҫ���͵���Ϣ
     * @param conn
     * @return
     * @throws QueryException
     * @throws ContainerException
     * @throws DTOException
     */
    public DTOSet getDefineMsg(Connection conn) throws QueryException, ContainerException, DTOException {
        DTOSet msgDTOSet = new DTOSet();
        SQLModel sqlModel = new SQLModel();
        sqlModel = sfMSGModell.getDefineModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            RowSet rs = simpleQuery.getSearchResult();
            Row row = null;
            String lastMsgCode = "";
            String lastUserId = "";
            String lastUserName = "";
            String lastPhone = "";
            String lastMail="";
            int count = 0;
            for (int i = 0; i < rs.getSize(); i++) {
                row = rs.getRow(i);
                SfMsgDefineDTO sfMsgDTO = new SfMsgDefineDTO();
                boolean sendFlag = String.valueOf(row.getValue("SEND_FLAG")).equals("1");
                if (sendFlag) {
                    if (lastMsgCode.equals("")) {
                        lastMsgCode = row.getStrValue("MSG_CODE");
                        lastUserId = row.getStrValue("USER_ID");
                        lastUserName = row.getStrValue("USERNAME");
                        lastPhone = row.getStrValue("MOVETEL");
                        lastMail = row.getStrValue("MAIL");
                        count++;
                        if (i == rs.getSize() - 1) {
                            sfMsgDTO.setCellPhone(lastPhone);
                            sfMsgDTO.setMail(lastMail);
                            sfMsgDTO.setMsgContent(getMsgContent(lastUserName, lastMsgCode, count));
                            msgDTOSet.addDTO(sfMsgDTO);
                        }
                        continue;
                    }
                    if (row.getStrValue("MSG_CODE").equals(lastMsgCode) && row.getStrValue("USER_ID").equals(lastUserId)) {
                        count++;
                        lastMsgCode = row.getStrValue("MSG_CODE");
                        lastUserId = row.getStrValue("USER_ID");
                        lastUserName = row.getStrValue("USERNAME");
                        lastPhone = row.getStrValue("MOVETEL");
                        lastMail = row.getStrValue("MAIL");
                        if (i == rs.getSize() - 1) {
                            sfMsgDTO.setCellPhone(lastPhone);
                            sfMsgDTO.setMail(lastMail);
                            sfMsgDTO.setMsgContent(getMsgContent(lastUserName, lastMsgCode, count));
                            msgDTOSet.addDTO(sfMsgDTO);
                        }
                    } else {
                        count = 1;
                        sfMsgDTO.setCellPhone(lastPhone);
                        sfMsgDTO.setMail(lastMail);
                        sfMsgDTO.setMsgContent(getMsgContent(lastUserName, lastMsgCode, count));
                        msgDTOSet.addDTO(sfMsgDTO);

                        lastMsgCode = row.getStrValue("MSG_CODE");
                        lastUserId = row.getStrValue("USER_ID");
                        lastUserName = row.getStrValue("USERNAME");
                        lastPhone = row.getStrValue("MOVETEL");
                        lastMail = row.getStrValue("MAIL");

                    }


                }

//                if (sendFlag) {
//                    sfMsgDTO.setCellPhone(row.getStrValue("MOVETEL"));
//                    sfMsgDTO.setMsgContent(row.getStrValue("MSG_CONTENT"));
//                    msgDTOSet.addDTO(sfMsgDTO);
//                }
            }
        }
        return msgDTOSet;
    }

    /**
     * ���ݵ������ѣ���ǰһ���£�
     * @param conn
     * @return
     * @throws QueryException
     * @throws ContainerException
     * @throws DTOException
     */
    public DTOSet getRentMindMsg(Connection conn) throws QueryException, ContainerException, DTOException {
        DTOSet msgDTOSet = new DTOSet();
        String username = "";
        SQLModel sqlModel = new SQLModel();
        sqlModel = sfMSGModell.getRendMindModel();
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            RowSet rs = simpleQuery.getSearchResult();
            Row row = null;
            for (int i = 0; i < rs.getSize(); i++) {
                row = rs.getRow(i);
                SfMsgDefineDTO sfMsgDTO = new SfMsgDefineDTO();
                String userPhones = row.getStrValue("USER_PHONES");
                String[] phoneArr = StrUtil.splitStr(userPhones, ";");
                if (phoneArr != null && phoneArr.length > 0) {
                    for (int j = 0; j < phoneArr.length; j++) {
                        String phone = phoneArr[j];
                        sfMsgDTO.setCellPhone(phone);
                        username = getUserName(phone, conn);
                        sfMsgDTO.setMsgContent("����:" + username + "����(���룺" + row.getStrValue("BARCODE") + ")���ڿ쵽����ֹ���ڣ�" + row.getStrValue("END_DATE") + "��");
                        msgDTOSet.addDTO(sfMsgDTO);
                    }
                }
            }
        }
        return msgDTOSet;
    }

    public DTOSet getSendMsgInfo() {
        DTOSet msgDTOSet = new DTOSet();
        return msgDTOSet;
    }


    public String getUserName(String phone, Connection conn) throws QueryException, ContainerException {
        String name = "";
        SQLModel sqlModel = new SQLModel();
        sqlModel = sfMSGModell.getNameModel(phone);
        SimpleQuery simpleQuery = new SimpleQuery(sqlModel, conn);
        simpleQuery.executeQuery();
        if (simpleQuery.hasResult()) {
            Row row = simpleQuery.getFirstRow();
            name = row.getStrValue("USERNAME");
        }
        return name;
    }

    public String getMsgContent(String userName, String msgCode, int count) {
        String msgContent = "";
        if (msgCode.equals(SMSConstant.ORDER_DIS)) {//��������
            msgContent = "����" + userName + "������" + count + "��������Ҫ��������ʹ��PDA���ء�";
        } else if (msgCode.equals(SMSConstant.SPARE_ALLOT)) {
            msgContent = "����" + userName + "������" + count + "����������Ҫ�����밲�Ź�˾�����ֿ���е�����";
        } else if (msgCode.equals(SMSConstant.SPARE_RECIEVE)) {
            msgContent = "����" + userName + "������" + count + "�����յ���Ҫ������ע����ա�";
        } else if (msgCode.equals(SMSConstant.ASSET_DIS)) {
            msgContent = "����" + userName + "������" + count + "���̵���Ҫ������ʹ��PDA���ء�";
        } else if (msgCode.equals(SMSConstant.ASSET_CONFIRM)) {
            msgContent = "����" + userName + "�������µ��ʲ���Ҫȷ�ϣ��뼰ʱ����";
        }
        return msgContent;
    }

}
