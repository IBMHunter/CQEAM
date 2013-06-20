package com.sino.ams.expand.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.sino.base.config.ConfigLoader;
import com.sino.base.config.UploadConfig;
import com.sino.base.constant.calen.DateConstant;
import com.sino.base.data.Row;
import com.sino.base.data.RowSet;
import com.sino.base.db.query.SimpleQuery;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.base.db.util.DBOperator;
import com.sino.base.db.util.SeqProducer;
import com.sino.base.dto.DTOSet;
import com.sino.base.exception.*;
import com.sino.base.util.CalendarUtil;
import com.sino.base.util.StrUtil;
import com.sino.base.web.request.download.WebFileDownload;

import com.sino.ams.expand.dto.UploadFileDTO;
import com.sino.ams.expand.model.UploadFileModel;
import com.sino.ams.system.user.dto.SfUserDTO;

/**
 * Created by IntelliJ IDEA.
 * User: srf
 * Date: 2008-3-12
 * Time: 16:40:04
 * To change this template use File | Settings | File Templates.
 */
public class UploadFileDAO {
    private Connection conn;
    private UploadConfig uploadConfig = null;
    private HttpServletRequest req;
    private UploadFileDTO upDto;
    private SfUserDTO sfDto;

    public UploadFileDAO(Connection conn, HttpServletRequest req,
                         UploadFileDTO upDto, SfUserDTO sfDto) throws
            ConfigException {
        this.req = req;
        this.conn = conn;
        this.upDto = upDto;
        this.sfDto = sfDto;
        this.uploadConfig = ConfigLoader.loadUploadConfig();
    }

    /**
     * ��������������
     * ��������Ϊ����+���кţ�
     */
    public String getFileName() throws SQLException {
        SeqProducer sp = new SeqProducer(conn);
        String seq = Integer.toString(sp.getStrNextSeq("AMS_ASSETS_TRANS_HEADER_S"));
        String sta_date = CalendarUtil.getCurrDate(DateConstant.STA_PATTERN);
        String year_month = sta_date.substring(0, 6);
        return year_month + "-" + seq;
    }

    //�����Ϣ�������ݿ�
    public void save2DB(UploadFileDTO dto, String userId) throws
            DataHandleException {
        UploadFileModel model = new UploadFileModel(sfDto, dto);
        SQLModel sm = model.insert(userId);
        DBOperator.updateRecord(sm, conn);
    }

    //�鸽����Ϣ
    public RowSet getFile(String transId) throws
            QueryException {
        UploadFileModel model = new UploadFileModel(sfDto, upDto);
        SQLModel sm = model.getFileModel(transId);
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.executeQuery();
        return sq.getSearchResult();
    }

    //ɾ������
    public void deleteFiles(String[] fileIds) throws SQLException,
            QueryException, ContainerException, DataHandleException {
        if (fileIds != null && fileIds.length > 0) {
            for (int i = 0; i < fileIds.length; i++) {
                try {
                    conn.setAutoCommit(false);
                    String fileId = fileIds[i];
                    //ȡ·��
                    String path = getPathById(fileId);
                    //ɾ���ļ�
                    File file = new File(path);
                    file.delete();
                    //�����ݿ�ɾ����¼
                    deleteFromDB(fileId);
                    conn.commit();
                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                } catch (QueryException e) {
                    conn.rollback();
                    throw e;
                } catch (ContainerException e) {
                    conn.rollback();
                    throw e;
                } catch (DataHandleException e) {
                    conn.rollback();
                    throw e;
                } finally {
                    conn.setAutoCommit(true);
                }
            }
        }
    }

    //ȡ·��
    private String getPathById(String fileId) throws QueryException,
            ContainerException {
        String path = "";
        UploadFileModel model = new UploadFileModel(sfDto, upDto);
        SQLModel sm = model.getPathModel(fileId);
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.executeQuery();
        RowSet rs = sq.getSearchResult();
        if (rs != null && rs.getSize() > 0) {
            Row row = rs.getRow(0);
            path = StrUtil.nullToString(row.getValue("FILE_PATH"));
        }
        return path;
    }

    //�����ݿ���ɾ����¼
    private void deleteFromDB(String fileId) throws DataHandleException {
        UploadFileModel model = new UploadFileModel(sfDto, upDto);
        SQLModel sm = model.deleteFromDB(fileId);
        DBOperator.updateRecord(sm, conn);
    }

    public void download(String fileId, HttpServletResponse res) throws
            QueryException, WebFileDownException {
        WebFileDownload down = new WebFileDownload(req, res);
        UploadFileDTO dto = getFileById(fileId);
        if (dto == null) {
            throw new WebFileDownException("���������ڣ������Ѿ���ɾ����");
        }
        down.setFileName(dto.getFileDesc());
        down.setFilePath(dto.getFilePath());
        down.download();
    }

    public UploadFileDTO getFileById(String fileId) throws QueryException {
        UploadFileModel model = new UploadFileModel(sfDto, upDto);
        SQLModel sm = model.getFileById(fileId);
        SimpleQuery sq = new SimpleQuery(sm, conn);
        sq.setDTOClassName(UploadFileDTO.class.getName());
        sq.executeQuery();
        return (UploadFileDTO) sq.getFirstDTO();
    }

    //�޸ĸ�������
    public void updateDesc(String fileId, String desc) throws
            DataHandleException, Exception {
        UploadFileModel model = new UploadFileModel(sfDto, upDto);
        SQLModel sm = model.updateDesc(fileId, desc);
        DBOperator.updateRecord(sm, conn);
    }

    private void save2DB(DTOSet ds, String transId, String fileType,
                         String userId, String orderType) throws SQLException,
            DataHandleException {
        if (ds != null && ds.getSize() > 0) {
            for (int i = 0; i < ds.getSize(); i++) {
                UploadFileDTO dto = (UploadFileDTO) ds.getDTO(i);
                if (!dto.getFileDesc().equals("") &&
                    !dto.getFilePath().equals("")) {
                    dto.setBarcode(transId);
//                    dto.setFileType(fileType);
//                    dto.setOrderType(orderType);
                    save2DB(dto, userId);
                }
            }
        }
    }

    public String[] upload(String userId) throws FileUploadException,
            SQLException {
        String transId = "";
        String fileType = "";
        String orderType = "";
        DiskFileItemFactory factory = new DiskFileItemFactory();
        long size = uploadConfig.getSingleSize();
        int s = (int) (size * 1024); //��λΪB
        factory.setSizeThreshold(s);
        //��ʱ�����ռ�
        String savePath = uploadConfig.getSavePath();
        String tmpPath = savePath + File.separator + "tmp";
        File tmpDir = new File(tmpPath);
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
        factory.setRepository(new File(tmpPath));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        //�ļ���С����
        upload.setSizeMax(uploadConfig.getTotalSize() * 1024); //����ļ��ϴ����ܴ�С
        upload.setFileSizeMax(s); //�����ļ���С
        // Parse the request

        List
        /* FileItem */ items = null;

        items = upload.parseRequest(req);
        try {
            conn.setAutoCommit(false);
            if (items != null) {
                DTOSet ds = new DTOSet();
                Iterator iter = items.iterator();
                /*
                 *���ܱ�֤��file����file��ǰ�õ�ֵ�����Ҫ���е�ѭ����ɺ󣬲��ܴ浽���ݿ���
                 *�����п��ܻ�û�õ�ͷID���ȱ�Ҫ��Ϣ�Ϳ�ʼ�����ݿ�
                 */
                while (iter.hasNext()) {
                    UploadFileDTO dto = new UploadFileDTO();
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString("GBK");
                        if (name.equals("transId")) {
                            transId = value;
                        } else if (name.equals("fileType")) {
                            fileType = value;
                        } else if (name.equals("transType")) {
                            orderType = value;
                        }

                    } else {
                        String fieldName = item.getFieldName();
                        String fileName = item.getName();
                        String fileValue = item.getString();
                        if (fileName == null || fileName.equals("")) {
                            continue;
                        }

                        fileName = FilenameUtils.getName(fileName);
                        long sizeInBytes = item.getSize();
                        String fileext = FilenameUtils.getExtension(fileName);
                        String reName = getFileName();
                        String targetPath = savePath + File.separator + reName +
                                            "." + fileext;
                        File dirFile = new File(savePath);
                        if (!dirFile.exists()) {
                            dirFile.mkdirs();
                        }

                        File targetfile = new File(targetPath);
                        //save file
                        item.write(targetfile);
                        //remove temp file
                        item.delete();
                        dto.setFileDesc(fileName);
                        dto.setFilePath(targetPath);
                        ds.addDTO(dto);
                    }
                }
                save2DB(ds, transId, fileType, userId, orderType);
            }

            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw ex;
            }

        } finally {
            conn.setAutoCommit(true);
        }
        return new String[] {transId, fileType};
    }
}
