package daoModels;

public class UserAccountRow {
	private int ssn;
	private int account;
	
	public UserAccountRow() {};
	public UserAccountRow(int ssn, int account) {
		this.ssn = ssn;
		this.account = account;
	}
	
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
}
