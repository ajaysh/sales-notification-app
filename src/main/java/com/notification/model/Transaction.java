package com.notification.model;

public class Transaction {
	private int notificationNumber;
	private boolean isAdjustmentNotification;
	private String logMessage;
	
	private long count;
	private Double sellingPrice;
	

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}


public Double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	public boolean isAdjustmentNotification() {
		return isAdjustmentNotification;
	}

	public void setAdjustmentNotification(boolean isAdjustmentNotification) {
		this.isAdjustmentNotification = isAdjustmentNotification;
	}


	public Transaction(int notificationNumber, boolean isAdjustmentNotification,
			long count, Double sellingPrice) {
		super();
		this.notificationNumber = notificationNumber;
		this.isAdjustmentNotification = isAdjustmentNotification;
		this.count = count;
		this.sellingPrice = sellingPrice;
	}
	
	
}
