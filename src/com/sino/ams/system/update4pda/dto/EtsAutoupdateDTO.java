package com.sino.ams.system.update4pda.dto;

import com.sino.base.calen.SimpleCalendar;
import com.sino.base.dto.CheckBoxDTO;

/**
 * <p>Title: PDA����汾��(EAM) EtsAutoupdate</p>
 * <p>Description: �����Զ�����DTO���ݴ������</p>
 * <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
 * <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
 *
 * @author aidy
 * @version 1.0
 */

public class EtsAutoupdateDTO extends CheckBoxDTO {

    private String module = "";
    private String version = "";
    private String description = "";
    private int filesize = 0;
    private SimpleCalendar creationDate = null;
    private int createdBy ;
    private SimpleCalendar lastUpdateDate = null;
    private int lastUpdateBy ;

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }

    public SimpleCalendar getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(SimpleCalendar creationDate) {
        this.creationDate = creationDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public SimpleCalendar getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(SimpleCalendar lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(int lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }


}