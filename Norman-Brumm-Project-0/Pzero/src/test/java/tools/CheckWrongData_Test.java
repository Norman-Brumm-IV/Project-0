package tools;

import org.junit.Assert;
import org.junit.Test;

public class CheckWrongData_Test {
	private String rsbf = "result should be false";
	private String rsbt = "result should be true";
	
	@Test
	public void checkIsEmpty() {
		Assert.assertEquals(false, CheckWrongData.isEmptyString("1","22","333","444"));
		Assert.assertEquals(rsbf, true, CheckWrongData.isEmptyString("1","22","333","444", null));
		Assert.assertEquals(rsbf, true, CheckWrongData.isEmptyString("1","22","333",""));
		Assert.assertEquals(rsbf, true, CheckWrongData.isEmptyString("1","22","","444"));
		Assert.assertEquals(rsbf, true, CheckWrongData.isEmptyString("1","","333","444"));
		Assert.assertEquals(rsbf, true, CheckWrongData.isEmptyString("","22","333","444"));
	}
	
	@Test
	public void checkssnAndAccountNumber() {
		Assert.assertEquals(rsbf, false, CheckWrongData.checkSSNAndAccountNumber("123456789", "564789213"));
		Assert.assertEquals(rsbt, true, CheckWrongData.checkSSNAndAccountNumber("12345678"));
		Assert.assertEquals(rsbt, true, CheckWrongData.checkSSNAndAccountNumber("123456789", "andflasuc"));
		Assert.assertEquals(rsbt, true, CheckWrongData.checkSSNAndAccountNumber("123456789", null));
		Assert.assertEquals(rsbt, true, CheckWrongData.checkSSNAndAccountNumber("12345678910"));
	}
	
	@Test
	public void checksCanParse() {
		Assert.assertEquals(rsbt, true, CheckWrongData.canParseAsInt("123"));
		Assert.assertEquals(rsbf, false, CheckWrongData.canParseAsInt("asf"));
		Assert.assertEquals(rsbf, false, CheckWrongData.canParseAsInt("25a4"));
		Assert.assertEquals(rsbf, false, CheckWrongData.canParseAsInt("123", null));
	}
}
