package daoModels;

public class CustomersRow{
	private int ssn;
	private String name;
	
	public CustomersRow() {};
	public CustomersRow(int ssn, String name) {
		this.ssn = ssn;
		this.name = name;
	}
	
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
