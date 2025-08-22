package testNG;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Sample1 {

	@Test(priority = 0)
	
	public void create() {
		
		Reporter.log("create",true);
		
	}
	@Test(priority = 1)
	
	public void update() {
		Reporter.log("update",true);
	}
	@Test(priority = 2)
	public void delete() {
		Reporter.log("delete",true);
		
	}
		

	}


