package pom;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Dependson {
	@Test
	public void createAcc() {
		Reporter.log("createAcc",true);
	}
	@Test(dependsOnMethods = "createAcc")
	public void updateAcc() {
		Reporter.log("updateAcc",true);
	}
	@Test(dependsOnMethods = {"createAcc","updateAcc"})
	public void deleteAcc() {
		Reporter.log("updateAcc",true);
	}

}
