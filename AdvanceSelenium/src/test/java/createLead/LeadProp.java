package createLead;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExcelUtility;
import genericUtility.PropertiesFileUtility;
import net.bytebuddy.asm.Advice.Enter;



public class LeadProp {


	public static void main(String[] args) throws InterruptedException, IOException {
		
	//Open The Browser and login into the application	
		
	System.setProperty("webdriver.edge.driver", "C:\\Users\\sipun\\Downloads\\edgedriver_win64\\msedgedriver.exe");
	/*WebDriver driver = new EdgeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	System.out.println("1-Edge browser opned");*/
		
	//Get data from properties file from general utility
	PropertiesFileUtility prop = new PropertiesFileUtility();
	String BROWSER = prop.togetDataFromPropertiesFile("browser");
	String URL = prop.togetDataFromPropertiesFile("url");
	String USERNAME = prop.togetDataFromPropertiesFile("username");
	String PASSWORD = prop.togetDataFromPropertiesFile("password");
	
	WebDriver driver=null;
	if(BROWSER.equals("edge")) 
	{
		driver=new EdgeDriver();
	}
	else if(BROWSER.equals("chrome"))
	{
		driver= new ChromeDriver();
	}
	else if(BROWSER.equals("firefox"))
	{
		driver= new FirefoxDriver();
	}
	
	
	//driver.get("http://49.249.28.218:8098/");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(URL);
	driver.findElement(By.id("username")).sendKeys(USERNAME);
	driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
	
	//Read data from excel file
	ExcelUtility excel = new ExcelUtility();
	
	String LEAD_NAME = excel.toReadDataFromExcelFile("createLead", 1, 0);
	String COMPANY = excel.toReadDataFromExcelFile("createLead", 1, 1);
	String LEADSOURCE = excel.toReadDataFromExcelFile("createLead", 1, 2);
	String INDUSTRY = excel.toReadDataFromExcelFile("createLead", 1, 3);
	String PHONE = excel.toReadDataFromExcelFile("createLead", 1, 6);
	String LEAD_STATUS = excel.toReadDataFromExcelFile("createLead", 1, 9);
	
	
	
	
	
	
	//Create Lead
	driver.findElement(By.xpath("//button[text()='Sign In']")).click();
	driver.findElement(By.xpath("//a[contains(text(),'Leads')]")).click();
	driver.findElement(By.xpath("//span[contains(text(),'Create Lead')]")).click();
	driver.findElement(By.name("name")).sendKeys(LEAD_NAME);
	driver.findElement(By.name("leadSource")).sendKeys(LEADSOURCE);
	driver.findElement(By.name("company")).sendKeys(COMPANY);
	driver.findElement(By.name("industry")).sendKeys(INDUSTRY);
	driver.findElement(By.name("phone")).sendKeys(PHONE);
	driver.findElement(By.xpath("//input[@name='leadStatus']")).sendKeys(LEAD_STATUS);
	
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
	WebElement search = driver.findElement(By.id("search-input"));
	Thread.sleep(5000);
	search.sendKeys("CAM00001");
	
	WebElement campaign = 	driver.findElement(By.xpath("//button[@class='select-btn']"));
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	wait.until(ExpectedConditions.visibilityOf(campaign));
	campaign.click();
	driver.switchTo().window(parent);
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	
	
	
	System.out.println("2-All the new lead details are being  Filled");
	
	
	//Validate lead is added or not
	WebElement message = driver.findElement(By.xpath("//div[@role='alert']"));
	//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	wait.until(ExpectedConditions.visibilityOf(message));
	String sms= message.getText();
	if(sms.contains(LEAD_NAME)) {
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
