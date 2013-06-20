package com.sino.flow.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wwb.
 * User: demo
 * Date: 2007-4-9
 * Time: 9:24:54
 * �������������ͻ��˴�����������
 */
public class ClientReader {
    //�ӿͻ��˶�ȡ���ݣ���JSON�ĸ�ʽ����
    public String readToJSON(HttpServletRequest req) throws IOException {
        StringBuffer json = new StringBuffer();
        String line = null;
        BufferedReader br = req.getReader();
        while ((line = br.readLine()) != null) {
            json.append(line);
        }
        return unescape(json.toString());
    }

    /**
     * ���� ˵������������֤ ���۲���src�Ƿ񾭹�escape()���룬���ܵõ���ȷ�ġ����롱���
     *
     * @param src
     */
    private static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src.substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src.substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }

}
