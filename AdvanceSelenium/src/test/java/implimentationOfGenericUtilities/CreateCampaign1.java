package implimentationOfGenericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import genericUtility.WebDriverUtility;
import pom.CreateCampaign;
import pom.HomePage;
import pom.Login;

public class CreateCampaign1 {

	public static void main(String[] args) throws IOException {
		
		
		//Properties file
//		FileInputStream fis = new FileInputStream("./src\\test\\resources\\commondata.properties");
//		Properties pro= new Properties();
//		pro.load(fis);
//		String BROWSER = pro.getProperty("browser");
//		String URL = pro.getProperty("url");
//		String USERNAME = pro.getProperty("username");
//		String PASSWORD = pro.getProperty("password");
		
		//ReadDataFromGenericUtilityPropertiesFile
		PropertiesFileUtility proUtility = new PropertiesFileUtility();
		String BROWSER = proUtility.togetDataFromPropertiesFile("browser");
		String URL = proUtility.togetDataFromPropertiesFile("url");
		String USERNAME = proUtility.togetDataFromPropertiesFile("username");
		String PASSWORD = proUtility.togetDataFromPropertiesFile("password");
		
		
//		//Excel file
//		FileInputStream exc=new FileInputStream("./src\\test\\resources\\PropertiesFile.xlsx");
//		Workbook wb=WorkbookFactory.create(exc);
//		Sheet sh = wb.getSheet("createCampaign");
//		String CAMPAIGN_NAME = sh.getRow(1).getCell(2).getStringCellValue();
//		String TARGET_SIZE = sh.getRow(1).getCell(3).getStringCellValue();		
		
		//ReadDataFromGenericExcelFiles
		ExcelUtility excUtility = new ExcelUtility();
		String CAMPAIGN_NAME = excUtility.toReadDataFromExcelFile("createCampaign", 1, 2);
		String TARGET_SIZE = excUtility.toReadDataFromExcelFile("createCampaign", 1, 3);
		
		//Open Browser
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
		
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		//implicitlyWaity
		WebDriverUtility webdUtility = new WebDriverUtility();
		webdUtility.waitforPageLoad(driver);
		
		driver.get(URL);
		
//		driver.findElement(By.id("username")).sendKeys(USERNAME);
//		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
//		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
	
		//Login
		Login signin = new Login(driver);
		signin.getUN().sendKeys(USERNAME);
		signin.getPWD().sendKeys(PASSWORD);
		signin.getLoginBtn().click();
		
		//driver.findElement(By.xpath("//span[contains(text(),'Create Campaign')]")).click();
//		driver.findElement(By.xpath("//input[@name='campaignName']")).sendKeys(CAMPAIGN_NAME);
//		WebElement size = driver.findElement(By.name("targetSize"));
//		size.clear();
//		size.sendKeys(TARGET_SIZE);
//		driver.findElement(By.xpath("//button[contains(text(),'Create Campaign')]")).click();
//		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		
		//Locate Elements
		//FromHome
		HomePage hPage = new HomePage(driver);
		hPage.getCampaigns().click();
		//FromCraateCampaign
		CreateCampaign cCampaign = new pom.CreateCampaign(driver);
		cCampaign.getCreateCampaign().click();
		cCampaign.getCampaignName().sendKeys(CAMPAIGN_NAME);
		cCampaign.getTargetSize().clear();
		cCampaign.getTargetSize().sendKeys(TARGET_SIZE);
		cCampaign.getClickCreateC().click();
		WebElement toastmsg = cCampaign.getToastMessage();
		
		
		
		
		
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.visibilityOf(confmsg));
		
		
		//ExplecitlyEait
		webdUtility.waitForVisibilityOfElement(driver, toastmsg);
		String sms = toastmsg.getText();
		
		if(sms.contains(CAMPAIGN_NAME)) {
			System.out.println("campaign created");
		}
		else
		{
			System.out.println("campaign not created");
		}
		//WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		WebElement icon = hPage.getUserIcon();
		//Actions act = new Actions(driver);
		//act.moveToElement(icon).click().perform();
		//Action
		webdUtility.mouseHoverOnWebelement(driver, icon);
		//driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();
		hPage.getLogOut().click();
	}

}
