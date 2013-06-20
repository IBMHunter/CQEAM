package com.sino.ams.system.object.servlet;

import com.sino.ams.system.object.AmsObjectAddressDTO;
import com.sino.ams.system.object.dao.AmsObjectAddressDAO;
import com.sino.ams.system.user.dto.SfUserDTO;
import com.sino.base.db.conn.DBManager;
import com.sino.base.dto.Request2DTO;
import com.sino.base.log.Logger;
import com.sino.framework.security.bean.SessionUtil;
import com.sino.framework.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����CopyrightCopyright (c) 2008</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 *
 * @author ����ʤ
 * @version 1.0
 */
public class AmsObjectAddressServlet extends BaseServlet {

    /**
     * ���е�Servlet������ʵ�ֵķ�����
     *
     * @param req HttpServletRequest
     * @param res HttpServletResponse
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void performTask(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Connection conn = null;
        PrintWriter out = null;
        try {
            SfUserDTO user = (SfUserDTO) SessionUtil.getUserAccount(req);
            Request2DTO req2DTO = new Request2DTO();
            req2DTO.setDTOClassName(AmsObjectAddressDTO.class.getName());
            AmsObjectAddressDTO dto = (AmsObjectAddressDTO) req2DTO.getDTO(req);
            conn = getDBConnection(req);

            AmsObjectAddressDAO addressDAO = new AmsObjectAddressDAO(user, dto, conn);
            res.setContentType("text/plain;charset=GBK");
            out = res.getWriter();
            String addressIds = addressDAO.getAddressIdByObjectNos();
            out.print(addressIds);
        } catch (Throwable ex) {
            Logger.logError(ex);
        } finally {
            DBManager.closeDBConnection(conn);
            if (out != null) {
                out.close();
            }
        }
    }
}
