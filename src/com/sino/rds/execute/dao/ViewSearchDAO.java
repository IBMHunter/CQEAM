package com.sino.rds.execute.dao;

import com.sino.base.dto.DTO;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.rds.appbase.dao.RDSBaseDAO;

import java.sql.Connection;

public class ViewSearchDAO extends RDSBaseDAO {

    public ViewSearchDAO(BaseUserDTO userAccount, DTO dtoParameter, Connection conn) {
        super(userAccount, dtoParameter, conn);
    }
}