package com.sino.ams.print.model;

import java.util.ArrayList;
import java.util.List;

import com.sino.ams.print.dto.BarcodeReceiveDTO;
import com.sino.base.db.sql.model.SQLModel;
import com.sino.framework.dto.BaseUserDTO;
import com.sino.framework.sql.BaseSQLProducer;

public class BarcodePrintModel extends BaseSQLProducer {

	public BarcodePrintModel(BaseUserDTO userAccount, BarcodeReceiveDTO dtoParameter) {
		super(userAccount, dtoParameter);
	}

    /**
     * Function:        �ж�EII�����Ƿ�����û�����ı�ǩ��
     * @Author��         ����
     * @Date��           2009-11-03
     */
    public SQLModel getBarcodeIsExist(){
        BarcodeReceiveDTO dto = (BarcodeReceiveDTO) super.dtoParameter;
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT EII.BARCODE FROM ETS_ITEM_INFO EII WHERE EII.BARCODE = ?";
        sqlArgs.add(dto.getFromBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }

    /**
     * Function:        �ж�ETS_BARCODE_PRINT�����Ƿ��и�����ļ�¼
     * @Author��         ����
     * @Date��           2009-11-03
     */
    public SQLModel getBarcodeIsExistInPrint(){
        BarcodeReceiveDTO dto = (BarcodeReceiveDTO) super.dtoParameter;
        SQLModel sqlModel = new SQLModel();
        List sqlArgs = new ArrayList();
        String sqlStr = "SELECT EBP.BARCODE FROM ETS_BARCODE_PRINT EBP WHERE EBP.BARCODE = ?";
        sqlArgs.add(dto.getFromBarcode());
        sqlModel.setSqlStr(sqlStr);
        sqlModel.setArgs(sqlArgs);
        return sqlModel;
    }
	
	/**
	 * Function			������ǩ��ӡȷ��
	 * @return boolean	SQLModel
	 */
	public SQLModel getDataCreateModel(){
		BarcodeReceiveDTO dto = (BarcodeReceiveDTO) super.dtoParameter;
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		String sqlStr = "INSERT INTO ETS_BARCODE_PRINT"
								+	"(BARCODE,"
								+	"BARCODE_PRINT_NUM)"
								+	"VALUES"
								+	"(?, "    //��ǩ��
								+	"1)";
		sqlArgs.add(dto.getFromBarcode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}

	/**
	 * Function:			�õ�ѡ���ı�ǩ��ӡȷ��ά����¼
	 * @return SQLModel 	ɾ����SQLModel
	 */
	public SQLModel getPrimaryKeyDataModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList();
		BarcodeReceiveDTO dto = (BarcodeReceiveDTO) super.dtoParameter;
		String sqlStr =  "SELECT EBP.BARCODE FROM_BARCODE, EBP.BARCODE_PRINT_NUM\n" +
                        "  FROM ETS_BARCODE_PRINT EBP\n" +
                        " WHERE EBP.BARCODE = ?";
		sqlArgs.add(dto.getFromBarcode());

		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}


    /**
	 * Function��		�޸�ָ����ǩ��ӡȷ�ϼ�¼��
	 * @return SQLModel	�޸���SQLModel
	 */
	public SQLModel getDataUpdateModel(){
		SQLModel sqlModel = new SQLModel();
		List sqlArgs = new ArrayList(0);
		BarcodeReceiveDTO dto = (BarcodeReceiveDTO)super.dtoParameter;
		String sqlStr = "UPDATE ETS_BARCODE_PRINT"
								+ " SET"
								+ " BARCODE_PRINT_NUM = BARCODE_PRINT_NUM+1"
								+ " WHERE"
								+ "  	BARCODE = ?";

		sqlArgs.add(dto.getFromBarcode());
		sqlModel.setSqlStr(sqlStr);
		sqlModel.setArgs(sqlArgs);
		return sqlModel;
	}
	
}