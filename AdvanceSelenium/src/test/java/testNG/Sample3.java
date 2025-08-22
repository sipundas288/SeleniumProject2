package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Sample3 {
	@Test(invocationCount = 5, threadPoolSize = 2)
	public void facebook() {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com");
	}

}
