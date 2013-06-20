package com.sino.sinoflow.dto;

import com.sino.base.dto.CheckBoxDTO;

import java.util.List;

/**
* <p>Title: ������� SfGroup</p>
* <p>Description: �����Զ�����DTO���ݴ������</p>
* <p>Copyright: ����˼ŵ����Ϣ�Ƽ����޹�˾ Copyright (c) 2006</p>
* <p>Company: ����˼ŵ����Ϣ�Ƽ����޹�˾</p>
* @author ����ʤ
* @version 1.0
*/

public class DeptGroupDTO extends CheckBoxDTO{

    private String sfProject = "";
    private String project = "";
    private String group = "";
    private String dept = "";

    private List<DeptGroupLineDTO> lines = null;

    public DeptGroupDTO() {
		super();

	}

    public void setSfProject(String sfProject) {
        this.sfProject = sfProject;
    }

    public String getSfProject() {
        return this.sfProject;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProject() {
        return this.project;
    }

    public void setGroup(String group){
		this.group = group;
	}

	public String getGroup() {
		return this.group;
	}

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDept() {
        return this.dept;
    }

    public List<DeptGroupLineDTO> getLines() {
        return lines;
    }

    public void setLines(List<DeptGroupLineDTO> lines) {
        this.lines = lines;
    }

}