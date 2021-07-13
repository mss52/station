package com.base.service.response;

import java.util.Date;

import com.base.lib.db.model.ModelCarFillingHistory;

public class ResponseCarLastFilling {

	private ModelCarFillingHistory last;
	
	private Long allowedAfterDayCount;
	
	private boolean canFill;
	
	private Date nextFillDate;
	
	public ModelCarFillingHistory getLast() {
		return last;
	}
	public void setLast(ModelCarFillingHistory last) {
		this.last = last;
	}
	public Long getAllowedAfterDayCount() {
		return allowedAfterDayCount;
	}
	public void setAllowedAfterDayCount(Long allowedAfterDayCount) {
		this.allowedAfterDayCount = allowedAfterDayCount;
	}
	public boolean isCanFill() {
		return canFill;
	}
	public void setCanFill(boolean canFill) {
		this.canFill = canFill;
	}
	public Date getNextFillDate() {
		return nextFillDate;
	}
	public void setNextFillDate(Date nextFillDate) {
		this.nextFillDate = nextFillDate;
	}
}
