package hardCode;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class CreateLead {


	public static void main(String[] args) throws InterruptedException {
		
	//Open The Browser and login into the application	
		
	System.setProperty("webdriver.edge.driver", "C:\\Users\\sipun\\Downloads\\edgedriver_win64\\msedgedriver.exe");
	WebDriver driver = new EdgeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	System.out.println("1-Edge browser opned");
	
	
	
	//Create Lead
	driver.get("http://49.249.28.218:8098/");
	driver.findElement(By.id("username")).sendKeys("rmgyantra");
	driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
	driver.findElement(By.xpath("//button[text()='Sign In']")).click();
	driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
	driver.findElement(By.xpath("//span[contains(text(),'Create Lead')]")).click();
	driver.findElement(By.name("name")).sendKeys("HelloDas");
	driver.findElement(By.name("leadSource")).sendKeys("Sdass");
	driver.findElement(By.name("company")).sendKeys("AbcSoftware");
	driver.findElement(By.name("industry")).sendKeys("IT");
	driver.findElement(By.name("phone")).sendKeys("4586798545");
	driver.findElement(By.xpath("//input[@name='leadStatus']")).sendKeys("Pass");
	
	//driver.findElement(by.)
	driver.findElement(By.xpath("//*[name()='svg' and @data-icon='plus']")).click();
	
	//driver.findElement(By.xpath("//div[@class='form-group']//div//button")).click();
	
	//Actions act = new Actions(driver);
	//act.moveToElement(addcampaign).click().perform();
	String parent = driver.getWindowHandle();
	Set<String> ids = driver.getWindowHandles();
	ids.remove(parent);
	for(String child : ids)
	{
		driver.switchTo().window(child);
	}
	Thread.sleep(3000);
	driver.findElement(By.id("search-input")).sendKeys("CAM00002");
	Thread.sleep(2000);
	driver.findElement(By.xpath("//button[@onclick=\"selectCampaign('CAM00002', 'Roshal')\"]")).click();
	Thread.sleep(2000);
	driver.switchTo().window(parent);
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	
	System.out.println("2-All the new lead details are being  Filled");
	
	
	//Validate lead is added or not
	WebElement message = driver.findElement(By.xpath("//div[@role='alert']"));
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.visibilityOf(message));
	String sms= message.getText();
	if(sms.contains("HelloDas")) {
		System.out.println("3-Lead added successfully");
		
	}
	else {
		System.out.println("3-Lead is not added please check again");
	}
	driver.findElement(By.xpath("//button[@type='button' and @aria-label='close']")).click();
	
	
	
	//Logout from the application
	WebElement usericon = driver.findElement(By.xpath("//div[@class='user-icon']"));
	Actions act= new Actions(driver);
	act.moveToElement(usericon).perform();
	driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
	System.out.println("4-logout from the application");
	
	
	
	
	
	
	
	
	
	

	}

}
