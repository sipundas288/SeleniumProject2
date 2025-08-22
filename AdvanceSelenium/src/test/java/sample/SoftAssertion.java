package sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAssertion {
	@Test
	public void demo() {
		String expectedtitle = ("Demo Web");
		WebDriver driver=new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/");
		String actualtitle = driver.getTitle();
		SoftAssert soft=new SoftAssert();
		
		soft.assertEquals(actualtitle, expectedtitle);
		
		System.out.println("step1");
		System.out.println("step2");
		soft.assertAll();
	}

}
