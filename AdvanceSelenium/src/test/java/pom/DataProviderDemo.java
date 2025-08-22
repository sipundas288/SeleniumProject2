package pom;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class DataProviderDemo {
	@Test(dataProvider = "loginDetails")
	public void login(String username, String password) {
	System.out.println(username+"=="+ password);	
		
	}
	@DataProvider
	public Object [][] loginDetails(){
	Object[][] objarr=new Object[3][2];
	objarr[0][0]="amruta";
	objarr[0][1]="a123";
	objarr[1][0]="divya";
	objarr[1][1]="di123";
	objarr[2][0]="sipun";
	objarr[2][1]="s123";
	
	return objarr;
	}
}
