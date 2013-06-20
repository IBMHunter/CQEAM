package com.sino.flow.designer;

/**
 * �ڴ˴���������˵����
 * �������ڣ�(00-12-27 9:25:14)
 * @author��Administrator
 */

import sun.io.ByteToCharConverter;

import com.sino.base.log.Logger;

public class PubString {
    public String test = "";

    /**
     * String ������ע�⡣
     */
    public PubString() {
        super();
    }

    public String chrtoimg(char ch) {
        String retstr;
        retstr = "<img src=" + "\"" + "/images/home/num/num" + String.valueOf(ch) + ".gif" + "\"" + ">";
        return retstr;
    }

    /**
     * �ڴ˴����뷽��˵����
     * �������ڣ�(2000-06-06 10:27:01)
     *
     * @param FieldName  java.lang.String
     * @param FieldValue java.lang.String
     * @return java.lang.String
     */
    /*
   C/c----------  �����ַ�(����Сд)����С��
   T------------  �ַ�(����Сд)����
   n------------  >=����
   N------------  <=����
   M------------   =����
   d------------  >=����
   D------------  <=����
   l------------  >=�ַ�������
   L------------  <=�ַ�������
    */
    public String CreateSubSqlCondition(String SqlStr, String FieldName, char FieldType, String FieldValue, String relate) {
        String RetStr = "";
        if (FieldValue.equalsIgnoreCase("����")) {
            FieldValue = "";
        }
        if (!FieldValue.equalsIgnoreCase("")) {
            switch (FieldType) {
                case 'C':
                    RetStr = "(LOWER(" + FieldName.toUpperCase() + ") LIKE '%" + FieldValue.toLowerCase() + "%' )";
                    break;
                case 'c':
                    RetStr = "(LOWER(" + FieldName.toUpperCase() + ") LIKE '%" + FieldValue.toLowerCase() + "%' )";
                    break;
                case 'T':
                    RetStr = "(" + FieldName.toUpperCase() + " = '" + FieldValue + "' )";
                    break;
                case 'd':
                    RetStr = "(" + FieldName.toUpperCase() + ">=TO_DATE('" + FieldValue + "','YYYY-MM-DD'))";
                    break;
                case 'D':
                    RetStr = "(" + FieldName.toUpperCase() + "<=TO_DATE('" + FieldValue + "','YYYY-MM-DD'))";
                    break;
                case 'l':
                    RetStr = "(TO_NUMBER(" + FieldName.toUpperCase() + ")>=" + formatdate(FieldValue) + ")";
                    break;
                case 'L':
                    RetStr = "(TO_NUMBER(" + FieldName.toUpperCase() + ")<=" + formatdate(FieldValue) + ")";
                    break;
                case 'n':
                    RetStr = "(" + FieldName.toUpperCase() + ">=" + FieldValue + ")";
                    break;
                case 'N':
                    RetStr = "(" + FieldName.toUpperCase() + "<=" + FieldValue + ")";
                    break;
                case 'M':
                    RetStr = "(" + FieldName.toUpperCase() + "=" + FieldValue + ")";
                    break;
            }
        } else {
            RetStr = "";
        }
        if ((RetStr != "") & (SqlStr != "")) {
            RetStr = SqlStr + " " + relate + " " + RetStr;
        } else if ((RetStr == "") & (SqlStr != "")) {
            RetStr = SqlStr;
        }
        return RetStr;
    }

    /**
     * ���ܣ��ַ����������ַ���Ϊnullʱ����"",���򷵻�ȥ��ǰ��ո���ַ�����
     * �������ڣ�(2000-06-15 20:19:42)
     *
     * @param instr java.lang.String
     * @return java.lang.String
     */
    public static String DealSqlValue(String instr) {
        String retstr = "";
        if ((instr != null) & (instr != "")) {
            retstr = instr.trim();
        }
        return retstr;
    }

    /**
     * ���ܣ���ʽ���ַ�����ָ�����ȣ�����ַ�������С��ָ��������ǰ�油"0"��
     * �������ڣ�(2001-3-16 15:11:26)
     *
     * @param oristr      java.lang.String
     * @param TotalLength int
     * @return java.lang.String
     */
    public String formatStr(String oristr, int TotalLength) {
        String initstr = "";
        String retstr = "";
        int i;
        for (i = 0; i < TotalLength; i++) {
            initstr = initstr + "0";
        }
        if (TotalLength > oristr.length()) {
            retstr = initstr.substring(0, TotalLength - oristr.length()) + oristr;
        } else {
            retstr = oristr;
        }
        return retstr;
    }

    /**
     * ���ܣ��ַ�����GBKת����Unicode��
     *
     * @param s
     */
    public String GB2Unicode(String s) //(***)��Ϊ��������
    {
        if (s == null) {
            return s;
        }
        test = "begin,";
        char[] orig = s.toCharArray();//ת��Char����
        byte[] dest = new byte[orig.length];
        for (int i = 0; i < orig.length; i++) dest[i] = (byte) (orig[i] & 0xFF); //ȥ���ֽڵ�0ת��GB��
        test = test + "1";
        try {
            test = test + "2";
            ByteToCharConverter toChar = ByteToCharConverter.getConverter("GBK");
            test = test + "3";
            //��GB��תΪUnicode
            return new String(toChar.convertAll(dest));
        } catch (Exception e) {
            test = test + "err:" + e.toString();
            return s;
        }
    }

    /**
     * ����:���ַ���ת����gb2312�룮
     *
     * @param str
     */
    public String iso2GB(String str) {
        String retStr = "";
        try {
            if (str != null && str != "") {
                retStr = new String(str.getBytes("iso-8859-1"), "gb2312");
            } else {
                retStr = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
        return retStr;
    }

    /**
     * ���ܣ��ַ���ת��Ϊ������
     * �������ڣ�(2001-1-12 16:29:34)
     *
     * @param str java.lang.String
     * @return int
     */
    public static int StrToInt(String str) {
        int retint = 0;
        String tmpstr = "";
        try {
            tmpstr = DealSqlValue(str);
            if (tmpstr.compareTo("") != 0) {
                if (tmpstr.indexOf(".") > 0) {
                    tmpstr = tmpstr.substring(0, tmpstr.indexOf("."));
                }
                retint = Integer.valueOf(tmpstr).intValue();
            }
        } catch (Exception e) {
            Logger.logError("str to int err=" + e.toString());
        }
        return retint;
    }

    /**
     * ���ܣ����ַ����е�ָ���ַ������ɣ¡�
     * �������ڣ�(2001-1-17 16:31:02)
     *
     * @param instr   java.lang.String
     */
    public String TrimStr(String instr, char nullch, char chgch) {
        char[] ch;
        ch = instr.toCharArray();
        String retstr = "";
        for (int i = 0; i < instr.length(); i++) {
            if (ch[i] != nullch) {
                retstr = retstr + String.valueOf(ch[i]);
            } else {
                retstr = retstr + String.valueOf(chgch);
            }
        }
        return retstr;
    }

    /**
     * ���ܣ���ʽ�������ַ��������֡�
     * �������ڣ�(2001-8-30 17:11:43)
     *
     * @param instr java.lang.String
     * @return java.lang.String
     */
    public String formatdate(String instr) {
        char[] ch;
        String retstr = "";
        ch = instr.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] != '-') {
                retstr = retstr + String.valueOf(ch[i]);
            }
        }
        return retstr;
    }

    /**
     * �������ڣ�(2000-06-02 09:50:44)
     *
     * @return
     */

//    public MDateTime getCurDateTime() {
//        Calendar dar;
//        MDateTime dt = new MDateTime();
//
//        dar = Calendar.getInstance();
//        dt.year = dar.get(Calendar.YEAR);
//        dt.month = dar.get(Calendar.MONTH) + 1;
//        dt.day = dar.get(Calendar.DAY_OF_MONTH);
//        dt.hour = dar.get(Calendar.HOUR_OF_DAY);
//        dt.minutes = dar.get(Calendar.MINUTE);
//        dt.second = dar.get(Calendar.SECOND);
//        dt.week = dar.get(Calendar.DAY_OF_WEEK);
//        return dt;
//    }

    /**
     * ���ܣ���ʼ�����顣
     * �������ڣ�(2001-8-17 11:37:24)
     *
     * @param num
     */
    public static String[] initStrArr(int num) {
        String[] rstr;
        rstr = new String[num];
        int i = 0;
        for (i = 0; i < num; i++) {
            rstr[i] = "";
        }
        return rstr;
    }

    /**
     * ����:ɾ���ַ�����ָ���ַ���
     * �������ڣ�(2001-1-17 16:31:02)
     *
     * @param instr
     * @param nullch
     */
    public String delstr(String instr, char nullch) {
        char[] ch;
        ch = instr.toCharArray();
        String retstr = "";
        for (int i = 0; i < instr.length(); i++) {
            if (ch[i] != nullch) {
                retstr = retstr + String.valueOf(ch[i]);
            }
        }
        return retstr;
    }

    /**
     * ���ܣ�ȡ����������������֡�
     * �������ڣ�(01-1-4 18:48:16)
     *
     * @param innum  int
     * @param modnum int
     * @return int
     */
    public long GetModNum(long innum, long modnum) {
        long retint = 0;
        retint = Integer.valueOf(String.valueOf(innum / modnum)).intValue();
        return retint;
    }

    /**
     * ���ܣ���ʼ���������飮
     *
     * @param num ���鳤��
     */
    public static int[] initintArr(int num) {
        int[] rstr;
        rstr = new int[num];
        int i = 0;
        for (i = 0; i < num; i++) {
            rstr[i] = 0;
        }
        return rstr;
    }

    /**
     * ���ܣ���ȡ��ǰʱ�䡣
     * �������ڣ�(2001-12-13 16:50:21)
     *
     * @return java.lang.String
     */
//    public String getCurrentDateTime() {
//        MDateTime dt = new MDateTime();
//        dt = getCurDateTime();
//        String retstr = "";
//        retstr = formatStr(String.valueOf(dt.year), 4) + "-"
//                + formatStr(String.valueOf(dt.month), 2) + "-" + formatStr(String.valueOf(dt.day), 2)
//                + " " + formatStr(String.valueOf(dt.hour), 2) + ":"
//                + formatStr(String.valueOf(dt.minutes), 2) + ":" + formatStr(String.valueOf(dt.second), 2);
//        return retstr;
//    }

    /**
     * ���ܣ�������ʽ����ָ��С��λ��������
     * �������ڣ�(2002-3-20 9:13:33)
     *
     * @return double
     *         indouble :
     *         pointnum:С������λ��
     */
    public double formatDouble(double indouble, int pointnum) {

        long m = 1, leftint = 0;
        long tmpint = 0;
        double tmpd = 0.5, retd = 0;
        leftint = StrToInt(String.valueOf(indouble));
        indouble = indouble - leftint;
        for (int i = 0; i < pointnum; i++) {
            m = m * 10;
            tmpd = tmpd / 10;
        }
        tmpint = StrToLong(String.valueOf((indouble + tmpd) * m));
        retd = Double.valueOf(String.valueOf(Double.valueOf(String.valueOf(tmpint)).doubleValue() / m)).doubleValue();
        retd = leftint + retd;
        return retd;
    }

    /**
     * �ڴ˴����뷽��˵����
     * �������ڣ�(2001-1-12 16:29:34)
     *
     * @param str java.lang.String
     * @return int
     */
    public long StrToLong(String str) {
        long retint = 0;
        String tmpstr = "";
        try {
            tmpstr = DealSqlValue(str);
            if (tmpstr.compareTo("") != 0) {
                if (tmpstr.indexOf(".") > 0) {
                    tmpstr = tmpstr.substring(0, tmpstr.indexOf("."));
                }
                retint = Long.valueOf(tmpstr).longValue();
            }
        } catch (Exception e) {
            Logger.logError("str to long err=" + e.toString());
        }
        return retint;
    }

    public static int getIndexOFObject(Object[] array, Object item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    public static int getIndexOFStringIgnoreCase(String[] array, String item) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equalsIgnoreCase(item)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * ����sql����where����
     *
     * @param sql
     * @param fieldNm
     * @param fieldValue
     */
    public String dealSqlCondition(String sql, String fieldNm, String fieldValue) {
        String retValue = "";
        retValue = sql;
        if (fieldValue != null && !fieldValue.equals("")) {
            retValue += " and " + fieldNm + " like '" + fieldValue + "%'";
        }
        return retValue;
    }

}
