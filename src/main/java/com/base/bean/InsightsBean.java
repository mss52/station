package com.base.bean;

public class InsightsBean {

	double totalFillAmount;
	long totalFillCount;
	long month;
	long dayOfMonth;
	
	public double getTotalFillAmount() {
		return totalFillAmount;
	}
	public void setTotalFillAmount(double totalFillAmount) {
		this.totalFillAmount = totalFillAmount;
	}
	public long getTotalFillCount() {
		return totalFillCount;
	}
	public void setTotalFillCount(long totalFillCount) {
		this.totalFillCount = totalFillCount;
	}
	public long getMonth() {
		return month;
	}
	public void setMonth(long month) {
		this.month = month;
	}
	public long getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(long dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

}
