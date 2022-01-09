package com.sreetej.mebank;

public class CustomerTransactions {
	
	//transactionId, fromAccountId, toAccountId, createdAt, amount, transactionType, relatedTransaction
	private String transactionId;
	@Override
	public String toString() {
		return "CustomerTransactions [transactionId=" + transactionId + ", fromAccountId=" + fromAccountId
				+ ", toAccountId=" + toAccountId + ", createdAt=" + createdAt + ", amount=" + amount
				+ ", transactionType=" + transactionType + ", relatedTransaction=" + relatedTransaction + "]";
	}
	private String fromAccountId;
	private String toAccountId;
	private String createdAt;
	private String amount;
	private String transactionType;
	private String relatedTransaction;
	public String getTransactionId() {
		return transactionId;
	}
	public String getFromAccountId() {
		return fromAccountId;
	}
	public String getToAccountId() {
		return toAccountId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public String getAmount() {
		return amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public String getRelatedTransaction() {
		return relatedTransaction;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}
	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public void setRelatedTransaction(String relatedTransaction) {
		this.relatedTransaction = relatedTransaction;
	}
	

}
