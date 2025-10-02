package implemantationOfPropAndExcel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCase3 {

	public static void main(String[] args) {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\sipun\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Create Campaign')]")).click();
		driver.findElement(By.xpath("//input[@name='campaignName']")).sendKeys("Aditya1");
		driver.findElement(By.name("campaignStatus")).sendKeys("pass");
		WebElement size = driver.findElement(By.name("targetSize"));
		size.clear();
		size.sendKeys("2");
		driver.findElement(By.xpath("//button[contains(text(),'Create Campaign')]")).click();
		WebElement confmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(confmsg));
		String sms = confmsg.getText();
		
		if(sms.contains("Aditya1")) {
			System.out.println("campaign created");
		}
		else
		{
			System.out.println("campaign not created");
		}
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act = new Actions(driver);
		act.moveToElement(icon).click().perform();
		driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
	}


	

}
