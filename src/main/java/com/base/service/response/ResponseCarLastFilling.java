package com.base.service.response;

import com.base.lib.db.model.ModelCarFillingHistory;

public class ResponseCarLastFilling {

	private ModelCarFillingHistory last;
	private Long allowedAfterDayCount;
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
	
	
}
