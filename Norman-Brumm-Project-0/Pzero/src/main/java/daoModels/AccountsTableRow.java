package daoModels;

public class AccountsTableRow {
	private int account;
	private double amountInAccount;

	public AccountsTableRow() {}
	public AccountsTableRow(int account, double amount) {
		this.account = account;
		amountInAccount = amount;
	}
	
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public double getAmountInAccount() {
		return amountInAccount;
	}
	public void setAmountInAccount(double amountInAccount) {
		this.amountInAccount = amountInAccount;
	}
	public void setBoth(int account, double d) {
		this.account = account;
		this.amountInAccount = d;
	}

}
