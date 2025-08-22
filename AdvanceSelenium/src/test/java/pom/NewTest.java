package pom;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewTest {
	@Test
	public void Tc001() {
		Reporter.log("testcase done, true");
	}
	@BeforeClass
	public void beforeMethod() {
			Reporter.log("login",true);
	}
	@AfterClass
	public void afterClass() {
		Reporter.log("logout",true);
	}
	@BeforeClass
	public void beforeClass() {
		Reporter.log("logout",true);
	}

}
