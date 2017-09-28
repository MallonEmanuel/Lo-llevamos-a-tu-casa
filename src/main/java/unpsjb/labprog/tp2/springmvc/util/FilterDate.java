package unpsjb.labprog.tp2.springmvc.util;

import java.util.Date;

public class FilterDate {
	
	private Date beginDate;
	private Date endDate;
	
	public FilterDate(){
		
	}
	
	public FilterDate(Date beginDate, Date endDate) {
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	
}
