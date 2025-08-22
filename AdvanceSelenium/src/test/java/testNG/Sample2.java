package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Sample2 {
	@Test(invocationCount = 5)
	public void insta() {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.instagram.com");
		driver.quit();
	}
}
