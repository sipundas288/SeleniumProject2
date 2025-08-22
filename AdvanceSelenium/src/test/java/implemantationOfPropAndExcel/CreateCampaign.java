package implemantationOfPropAndExcel;
import java.io.IOException;
import java.time.Duration;
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
import pom.Login;

public class CreateCampaign {

	public static void main(String[] args) throws IOException {
	//	Reading data from properties file
		PropertiesFileUtility plib = new PropertiesFileUtility();
		ExcelUtility elib = new ExcelUtility();
		String BROWSER = plib.togetDataFromPropertiesFile("browser");
		String URL = plib.togetDataFromPropertiesFile("url");
		String USERNAME = plib.togetDataFromPropertiesFile("username");
		String PASSWORD = plib.togetDataFromPropertiesFile("password");
		
		//Read the data from excel
		String CAMPAIGN_NAME = elib.toReadDataFromExcelFile("createCampaign", 1, 2);
		String TARGET_SIZE = elib.toReadDataFromExcelFile("createCampaign", 1, 3);

		WebDriver driver = null;
		
		if(BROWSER.equals("edge"))
		{
			driver = new EdgeDriver();
		}
		else if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		
		//Login
		Login signin = new Login(driver);
		signin.getUN().sendKeys(USERNAME);
		signin.getPWD().sendKeys(PASSWORD);
		signin.getLoginBtn().click();
	
		
		
		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
		
		//Locating Element
		pom.CreateCampaign cp = new pom.CreateCampaign(driver);
		cp.getCreateCampaign().click();

		
		//Validation
		WebElement confmsg = cp.getToastMessage();
		
		
		
	
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(confmsg));
		String sms = confmsg.getText();
		
		if(sms.contains(CAMPAIGN_NAME)) {
			System.out.println("campaign is created");
		}
		else
		{
			System.out.println("campaign not created");
		}
		
		//Logout
		
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act = new Actions(driver);
		act.moveToElement(icon).click().perform();
		driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
		
	}


}
