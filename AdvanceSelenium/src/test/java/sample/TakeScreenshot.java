package sample;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class TakeScreenshot {
	@Test
	public void takeSS() throws IOException {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com");
		Date d = new Date();
		String newdate = d.toString().replace(" ", " ").replace(":", "_");
		
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File temp =ts.getScreenshotAs(OutputType.FILE);
		File per=new File("./Screenshot/takeSS_"+newdate+".png");
		FileHandler.copy(temp, per);
	}

}
