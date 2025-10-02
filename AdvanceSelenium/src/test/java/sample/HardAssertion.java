package sample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HardAssertion {
	@Test
	public void demo() {
		String expectedtitle = ("Demo Web Shop");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.youtube.com/");
		String actualtitle = driver.getTitle();
		Assert.assertEquals(actualtitle, expectedtitle);
		System.out.println("step1");
		System.out.println("step2");
	}

}
