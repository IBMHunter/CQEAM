package com.sino.base.util;

import java.sql.Connection;

import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.exception.ContainerException;
import com.sino.base.exception.QueryException;
import com.sino.base.log.Logger;

/**
 * <p>Title: SinoApplication</p>
 * <p>Description: Java Enterprise Edition ƽ̨Ӧ�ÿ����������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2003~2008��
 * <p>Copyright: ����ʹ�õ��ĵ���������������л����񹲺͹���ط����Լ��л����񹲺͹��������ع��ʹ�Լ����Ȩ��ԭ�������С�</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 0.1
 */
public class MzTree2 {
    protected String sqlStr = "";
    protected SQLModel sqModel = null;
    protected String rootText = "";   //���ڵ������
    protected String iconPath = "/images/mzTree/"; //http://www.meizz.com/Icons/TreeView/";   //ͼƬ��·��
    protected String url = "";   //URL
    protected String target = "";   //���ڵ������
    protected String transParaName = "";   //Ҫ����Ŀ�괰�ڵı���������
    protected String paramStr = "";   //Ҫ����Ŀ�괰�ڵı����ĸ�ʽ ��flexValueSetId=ID1&flexValueSetName=TEXT1

    protected int levels = 2;      //�����ж��ٲ㣬���ڵڶ������ڵ������,��ֵ������Ӧ��>=2 ,

    protected String treeStr = "";

    protected Connection conn = null;


    public MzTree2(Connection conn) {
        this.conn = conn;
    }


    protected void produceTitle() {  	
        treeStr += "var tree = new MzTreeView(\"tree\");\n";
        treeStr += "with(tree){\n";
        treeStr += "setIconPath(\"" + iconPath + "\");\n";
        if (!url.equals("")) {
            treeStr += "setURL(\"" + url + "\");\n";
        }
        if (!target.equals("")) {
            treeStr += "setTarget(\"" + target + "\");\n";
        }
        treeStr += "\nnodes[\"" + 0 + "_ROOTNODE\"]=\"text:" + rootText;
        treeStr += "\"";
    }

    protected void produceBottom() {
        treeStr += "}\n" +
                "document.write(tree.toString());";
    }

    /**
     * ���ø÷���������
     */
    public void produceTree() {
        produceTitle();
        produceNodes();
        produceBottom();

    }

    /**
     * ���ø÷���������
     */
    public void produceTree2() {
        produceTitle();
        produceNodes2();
        produceBottom();

    }

    /**
     * ���ø÷���������
     */
    public void produceTree3() {
        produceTitle();
        produceNodes3();
        produceBottom();

    }

    public void produceTree4() {
        produceTitle();
        produceNodes4();
        produceBottom();

    }

    protected void produceNodes() {
        try {
            RowSet rows = getSearchResult();
            Row row = null;
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
                String id = String.valueOf(row.getValue("ID"));
                String pid = String.valueOf(row.getValue("PARENT_ID"));
                pid = pid.equals("") ? "ROOTNODE" : pid;
                String text = String.valueOf(row.getValue("TEXT"));
                String url = String.valueOf(row.getValue("URL"));
                treeStr += "\nnodes[\"" + pid + "_" + id + "\"]=\"";
                if (text != null && !text.trim().equals("")) {
                    treeStr += "text:" + text + ";";
                }
                if (!url.equals("")) {
                    treeStr += "url:" + url + ";";
                }
                treeStr += "\"";
            }
        } catch (ContainerException e) {
            Logger.logError(e);
            treeStr = "������ʱ����";
        }
    }

    protected RowSet getSearchResult() {
        SimpleQuery sq = null;
        RowSet rows = null;
        try {
            if (!sqlStr.equals("")) {
                sqModel = new SQLModel();
                sqModel.setSqlStr(sqlStr);
            }
            sq = new SimpleQuery(sqModel, conn);
            sq.executeQuery();
            rows = sq.getSearchResult();
        } catch (QueryException e) {
            Logger.logError(e);
            treeStr = "������ʱ����";
        }
        return rows;
    }

    public String getTreeStr() {
        return treeStr;
    }

    /**
     * �÷�������������ID,PARENT_ID��ϵ�����Ľڵ�
     */
    protected void produceNodes2() {

        RowSet rows = getSearchResult();
        Row row = null;
        String id = "";
        String text = "";
        String lastId = "";
        try {
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
                for (int j = 1; j <= levels; j++) {

                    if (j == 1) {
                        id = String.valueOf(row.getValue("ID" + j));
                        text = String.valueOf(row.getValue("TEXT" + j));
                        treeStr += "\nnodes[\"ROOTNODE_" + id + "\"]=\"text:" + text;
                        if (!transParaName.equals("")) {
                            treeStr += ";data:" + transParaName + "=" + id;
                        }
                        treeStr += "\"";
                    } else {
                        lastId = String.valueOf(row.getValue("ID" + (j - 1)));
                        id = String.valueOf(row.getValue("ID" + j));
                        text = String.valueOf(row.getValue("TEXT" + j));
                        if (text != null && !text.trim().equals("")) {   //���ı�Ϊ��ʱ����Ӹýڵ�
                            treeStr += "\nnodes[\"" + lastId + "_" + id + "\"]=\"text:" + text;
                            if (j == levels && !transParaName.equals("")) {
                                treeStr += ";data:" + transParaName + "=" + id;
                            }
                            treeStr += "\"";
                        }
                    }
                }
            }
        } catch (ContainerException e) {
            Logger.logError(e);
            treeStr = "������ʱ����,�������־�ļ���";
        }
    }

    /**
     * ��produceNodes2����������Ҫ������ڵ���ܴ������Ĺ��ܡ�
     * �÷�������������ID,PARENT_ID��ϵ�����Ľڵ�
     */
    protected void produceNodes3() {

        RowSet rows = getSearchResult();
        Row row = null;
        String id = "";
        String text = "";
        String lastId = "";
        try {
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
                for (int j = 1; j <= levels; j++) {

                    if (j == 1) {
                        id = String.valueOf(row.getValue("ID" + j));
                        text = String.valueOf(row.getValue("TEXT" + j));
                        treeStr += "\nnodes[\"ROOTNODE_" + id + "\"]=\"text:" + text;
                        if (!transParaName.equals("")) {
                            treeStr += ";data:" + transParaName + "=" + id;
                        }
                        treeStr += "\"";
                    } else {
                        lastId = String.valueOf(row.getValue("ID" + (j - 1)));
                        id = String.valueOf(row.getValue("ID" + j));
                        text = String.valueOf(row.getValue("TEXT" + j));
                        if (text != null && !text.trim().equals("")) {   //���ı�Ϊ��ʱ����Ӹýڵ�
                            treeStr += "\nnodes[\"" + lastId + "_" + id + "\"]=\"text:" + text;
                            if (!transParaName.equals("")) {
                                treeStr += ";data:" + transParaName + "=" + id;
                            }
                            treeStr += "\"";
                        }
                    }
                }
            }
        } catch (ContainerException e) {
            Logger.logError(e);
            treeStr = "������ʱ����,�������־�ļ���";
        }
    }

    /**
     * �÷����ɴ��ݶ������,��������������,��ʽ��:flexValueSetId=ID1&flexValueSetName=TEXT1
     */
    protected void produceNodes4() {

        RowSet rows = getSearchResult();
        Row row = null;
        String id = "";
        String text = "";
        String lastId = "";
        try {
            for (int i = 0; i < rows.getSize(); i++) {
                row = rows.getRow(i);
                for (int j = 1; j <= levels; j++) {

                    if (j == 1) {
                        lastId = "ROOTNODE";
                    } else {
                        lastId = String.valueOf(row.getValue("ID" + (j - 1)));
                    }
                    id = String.valueOf(row.getValue("ID" + j));
                    text = String.valueOf(row.getValue("TEXT" + j));
                    if (text != null && !text.trim().equals("")) {   //���ı�Ϊ��ʱ����Ӹýڵ�
                        treeStr += "\nnodes[\"" + lastId + "_" + id + "\"]=\"text:" + text;
                        treeStr += ";data:";
                        if (!transParaName.equals("")) {
                            treeStr += transParaName + "=" + id;
                        }
                        if (!paramStr.equals("")) {
                            String tempArr[] = paramStr.split("&");
                            for (int k = 0; k < tempArr.length; k++) {
                                String param = tempArr[k];
                                String paraArr[] = param.split("=");
                                String paramName = paraArr[0];
                                String paramField = paraArr[1];
                                treeStr += "&" + paramName + "=" + String.valueOf(row.getValue(paramField));
                            }
                        }
                        treeStr += "\"";
                    }
                }
            }
        } catch (ContainerException e) {
            Logger.logError(e);
            treeStr = "������ʱ����,�������־�ļ���";
        }
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public void setSqModel(SQLModel sqModel) {
        this.sqModel = sqModel;
    }


    public void setRootText(String rootText) {
        this.rootText = rootText;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }


    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public void setTarget(String target) {
        this.target = target;
    }


    public void setTransParaName(String transParaName) {
        this.transParaName = transParaName;
    }

    public void setParamStr(String paramStr) {
        this.paramStr = paramStr;
    }

    public void setProperties() {

    }

//    public static void main(String[] args) throws PoolException {
//        Connection conn = DBManager.getDBConnection();
//        MzTree mzTree = new MzTree(conn);
//        SQLModel sqlModel = new SQLModel();
////
//        String sqlStr2 = "SELECT aoa.address_id id1,\n" +
//                "       eo.workorder_object_name text1,\n" +
//                "       aoa.address_no id2,\n" +
//                "       aoa.address_no text2,\n" +
//                "       eii.barcode id3,\n" +
//                "       eii.barcode text3\n" +
//                "  FROM ets_object eo, ams_object_address aoa, ets_item_info eii\n" +
//                " WHERE eo.workorder_object_no = aoa.object_no\n" +
//                "   AND aoa.address_id = eii.address_id\n" +
//                "   AND eii.is_parent = 'Y'";
//        sqlModel.setSqlStr(sqlStr2);
//        mzTree.setSqModel(sqlModel);
//        mzTree.setLevels(3);
//        mzTree.setRootText("�ص�");
//        mzTree.setTransParaName("barcode");
//        mzTree.produceTree2();
//        System.out.println("mzTree = " + mzTree.getTreeStr());
//        String sqlStr = "select t.res_id id,t.res_par_id parent_id,t.res_name text,t.res_url url from sf_res_define t order by t.res_id";
//        mzTree.setSqlStr(sqlStr);
//        mzTree.setRootText("ʵ�����");
//        mzTree.produceTree();
//        System.out.println("mzTree2 = " + mzTree.getTreeStr());
//        DBManager.closeDBConnection(conn);
//        System.exit(0);
//    }
}
