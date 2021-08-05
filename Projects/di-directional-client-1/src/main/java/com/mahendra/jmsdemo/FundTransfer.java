package com.mahendra.jmsdemo;

public class FundTransfer {
	private String fromAccount;
	private String fromBank;
	private String toAccount;
	private double amount;
	public FundTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getFromBank() {
		return fromBank;
	}
	public void setFromBank(String fromBank) {
		this.fromBank = fromBank;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public FundTransfer(String fromAccount, String fromBank, String toAccount, double amount) {
		super();
		this.fromAccount = fromAccount;
		this.fromBank = fromBank;
		this.toAccount = toAccount;
		this.amount = amount;
	}
}
