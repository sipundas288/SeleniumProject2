package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class StealeElementReferenceWithoutPom {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.instagram.com");
		WebElement un = driver.findElement(By.name("username"));
		WebElement pwd = driver.findElement(By.name("password"));
		un.sendKeys("admin");
		pwd.sendKeys("pwd");
		driver.navigate().refresh();
		un.sendKeys("admin");
		pwd.sendKeys("pwd");
		
	
	}

}
