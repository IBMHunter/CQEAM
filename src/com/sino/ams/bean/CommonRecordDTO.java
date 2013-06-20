package com.sino.ams.bean;

import com.sino.ams.appbase.dto.AMSBaseDTO;
import com.sino.base.calen.SimpleCalendar;
import com.sino.base.calen.SimpleDate;
import com.sino.base.calen.SimpleTime;
import com.sino.base.dto.CalendarUtililyDTO;
import com.sino.base.exception.CalendarException;
import com.sino.base.exception.ReflectException;
import com.sino.base.exception.TimeException;
import com.sino.base.util.ReflectionUtil;

/**
 * <p>Title: SinoEAM</p>
 * <p>Description: ɽ���ƶ�ʵ���ʲ�����ϵͳ</p>
 * <p>Copyright: ����˼ŵ����Ϣ�������޹�˾��Ȩ����Copyright (c) 2007</p>
 * <p>Company: ����˼ŵ����Ϣ�������޹�˾</p>
 * @author ����ʤ
 * @version 1.0
 */
public class CommonRecordDTO extends AMSBaseDTO {
	private int createdBy = 0;
	private int lastUpdateBy = 0;
	private SimpleCalendar accountingPeriod = null;//����ڼ�
	private String month = "";
    private String year = "";
    private String periodName = "";//����ڼ�
    private String lastMonthPeriodName = "";//���»���ڼ�
    private String lastYearPeriodName = "";//ȥ��ͬ�ڻ���ڼ�
    private String lastFourYearPeriodName = "";//4��ǰ12�·ݻ���ڼ�
    private String lastThreeYearPeriodName = "";//3��ǰ12�·ݻ���ڼ�
    private String lastTwoYearPeriodName = "";//2��ǰ12�·ݻ���ڼ�
    private String lastOneYearPeriodName = "";//1��ǰ12�·ݻ���ڼ�
    private String periodNameByHisRep = "";// ETS_ITEM_INFO_HIS_REP����ĵ�ǰ�����
    private String lastMonthPeriodNameByHisRep = "";// ����ETS_ITEM_INFO_HIS_REP��������»����
    private String lastYearPeriodNameByHisRep = "";// ����ETS_ITEM_INFO_HIS_REP�����ȥ��ͬ�ڻ����
    private String lastFourYearPeriodNameByHisRep = "";//����ETS_ITEM_INFO_HIS_REP����4��ǰ12�·ݻ���ڼ�
    private String lastThreeYearPeriodNameByHisRep = "";//����ETS_ITEM_INFO_HIS_REP����3��ǰ12�·ݻ���ڼ�
    private String lastTwoYearPeriodNameByHisRep = "";//����ETS_ITEM_INFO_HIS_REP����2��ǰ12�·ݻ���ڼ�
    private String lastOneYearPeriodNameByHisRep = "";//����ETS_ITEM_INFO_HIS_REP����1��ǰ12�·ݻ���ڼ�
    
    private String lastYear = "";//ȥ�����
	private String lastFourYear = "";	//4��ǰ���
	private String lastThreeYear = "";	//3��ǰ���
	private String lastTwoYear = "";	//2��ǰ���
    
	public String getLastFourYear() {
		return lastFourYear;
	}

	public void setLastFourYear(String lastFourYear) {
		this.lastFourYear = lastFourYear;
	}

	public String getLastThreeYear() {
		return lastThreeYear;
	}

	public void setLastThreeYear(String lastThreeYear) {
		this.lastThreeYear = lastThreeYear;
	}

	public String getLastTwoYear() {
		return lastTwoYear;
	}

	public void setLastTwoYear(String lastTwoYear) {
		this.lastTwoYear = lastTwoYear;
	}

	public String getLastYear() {
		return lastYear;
	}

	public void setLastYear(String lastYear) {
		this.lastYear = lastYear;
	}

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	/**
     * 
     * Function:		��ȡ����ڼ�
     * @return			SimpleCalendar
     * @throws 			CalendarException 
     * @Version 		0.1
     * @Date:   		May 31, 2009
     */
	public SimpleCalendar getAccountingPeriod() throws CalendarException {
		accountingPeriod.setCalPattern(getCalPattern());
		return accountingPeriod;
	}

	/**
	 * Function:		���û���ڼ�
	 * @param 			accountingPeriod
	 * @throws 			CalendarException
	 * @Version 		0.1
	 * @Date:   		May 31, 2009
	 */
	public void setAccountingPeriod(String accountingPeriod) throws CalendarException {
		this.accountingPeriod.setCalendarValue(accountingPeriod);
	}
	public CommonRecordDTO() {
		this.accountingPeriod = new SimpleCalendar();
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public void setLastUpdateBy(int lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public int getLastUpdateBy() {
		return lastUpdateBy;
	}


	/**
	 * ���ܣ���ȡָ���ֶ�����ֵ�����ʱ��
	 * @param fieldName String
	 * @return SimpleCalendar
	 */
	public SimpleCalendar getLastValueOfDate(String fieldName){
		SimpleCalendar sqlEndCal = null;
		try {
			Object fieldValue = ReflectionUtil.getProperty(this, fieldName);
			if (fieldValue instanceof SimpleCalendar) {
				sqlEndCal = (SimpleCalendar) fieldValue;
				SimpleDate date = sqlEndCal.getSimpleDate();
				if (!date.getDateValue().equals("")) {
					SimpleTime time = SimpleTime.getEndTime();
					sqlEndCal = new SimpleCalendar(date, time);
				}
			}
			if (sqlEndCal == null) {
				sqlEndCal = new SimpleCalendar();
			}
		} catch (ReflectException ex) {
			ex.printLog();
		} catch (TimeException ex) {
			ex.printLog();
		}
		return sqlEndCal;
	}
	
	public String getLastFourYearPeriodName() {
		return lastFourYearPeriodName;
	}

	public void setLastFourYearPeriodName(String lastFourYearPeriodName) {
		this.lastFourYearPeriodName = lastFourYearPeriodName;
	}

	public String getLastMonthPeriodName() {
		return lastMonthPeriodName;
	}

	public void setLastMonthPeriodName(String lastMonthPeriodName) {
		this.lastMonthPeriodName = lastMonthPeriodName;
	}

	public String getLastOneYearPeriodName() {
		return lastOneYearPeriodName;
	}

	public void setLastOneYearPeriodName(String lastOneYearPeriodName) {
		this.lastOneYearPeriodName = lastOneYearPeriodName;
	}

	public String getLastThreeYearPeriodName() {
		return lastThreeYearPeriodName;
	}

	public void setLastThreeYearPeriodName(String lastThreeYearPeriodName) {
		this.lastThreeYearPeriodName = lastThreeYearPeriodName;
	}

	public String getLastTwoYearPeriodName() {
		return lastTwoYearPeriodName;
	}

	public void setLastTwoYearPeriodName(String lastTwoYearPeriodName) {
		this.lastTwoYearPeriodName = lastTwoYearPeriodName;
	}

	public String getLastYearPeriodName() {
		return lastYearPeriodName;
	}

	public void setLastYearPeriodName(String lastYearPeriodName) {
		this.lastYearPeriodName = lastYearPeriodName;
	}

	public String getPeriodNameByHisRep() {
		return periodNameByHisRep;
	}

	public void setPeriodNameByHisRep(String periodNameByHisRep) {
		this.periodNameByHisRep = periodNameByHisRep;
	}

	public String getLastFourYearPeriodNameByHisRep() {
		return lastFourYearPeriodNameByHisRep;
	}

	public void setLastFourYearPeriodNameByHisRep(
			String lastFourYearPeriodNameByHisRep) {
		this.lastFourYearPeriodNameByHisRep = lastFourYearPeriodNameByHisRep;
	}

	public String getLastMonthPeriodNameByHisRep() {
		return lastMonthPeriodNameByHisRep;
	}

	public void setLastMonthPeriodNameByHisRep(String lastMonthPeriodNameByHisRep) {
		this.lastMonthPeriodNameByHisRep = lastMonthPeriodNameByHisRep;
	}

	public String getLastOneYearPeriodNameByHisRep() {
		return lastOneYearPeriodNameByHisRep;
	}

	public void setLastOneYearPeriodNameByHisRep(
			String lastOneYearPeriodNameByHisRep) {
		this.lastOneYearPeriodNameByHisRep = lastOneYearPeriodNameByHisRep;
	}

	public String getLastThreeYearPeriodNameByHisRep() {
		return lastThreeYearPeriodNameByHisRep;
	}

	public void setLastThreeYearPeriodNameByHisRep(
			String lastThreeYearPeriodNameByHisRep) {
		this.lastThreeYearPeriodNameByHisRep = lastThreeYearPeriodNameByHisRep;
	}

	public String getLastTwoYearPeriodNameByHisRep() {
		return lastTwoYearPeriodNameByHisRep;
	}

	public void setLastTwoYearPeriodNameByHisRep(
			String lastTwoYearPeriodNameByHisRep) {
		this.lastTwoYearPeriodNameByHisRep = lastTwoYearPeriodNameByHisRep;
	}

	public String getLastYearPeriodNameByHisRep() {
		return lastYearPeriodNameByHisRep;
	}

	public void setLastYearPeriodNameByHisRep(String lastYearPeriodNameByHisRep) {
		this.lastYearPeriodNameByHisRep = lastYearPeriodNameByHisRep;
	}
	
}
