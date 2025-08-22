package sample;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenShot {

	public static void main(String[] args) throws IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.instagram.com/");
		//step 1
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		//step 2
		File temp = ts.getScreenshotAs(OutputType.FILE);
		
		//step 3
		File perm = new File("./Screenshots/insta.png");
		
		
		FileHandler.copy(temp, perm);

	}

}
