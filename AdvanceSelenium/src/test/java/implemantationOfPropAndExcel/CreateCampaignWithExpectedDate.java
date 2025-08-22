package implemantationOfPropAndExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;

public class CreateCampaignWithExpectedDate {

public static void main(String[] args) throws IOException {
	
		JavaUtility javau = new JavaUtility();
		WebDriverUtility webUtility = new WebDriverUtility();
		
		FileInputStream fis=new FileInputStream("./src\\test\\resources\\commondata.properties");
		Properties prop=new Properties();
		prop.load(fis);
		String BROWSER = prop.getProperty("browser");
		String URL = prop.getProperty("url");
		String USERNAME = prop.getProperty("username");
		String PASSWORD = prop.getProperty("password");
	
//	PropertiesFileUtility plib = new PropertiesFileUtility();
//	String BROWSER = plib.togetDataFromPropertiesFile("browser");
//	String URL = plib.togetDataFromPropertiesFile("url");
//	String USERNAME = plib.togetDataFromPropertiesFile("username");
//	String PASSWORD = plib.togetDataFromPropertiesFile("password");
		
		FileInputStream fis1 = new FileInputStream("C:\\Users\\sipun\\OneDrive\\Desktop\\PropertiesFile.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		Sheet sh=wb.getSheet("createCampaign");
		String CAMPAIGN_NAME = sh.getRow(1).getCell(2).getStringCellValue();
		String TARGET_SIZE = sh.getRow(1).getCell(3).getStringCellValue();
		
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
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		
		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Create Campaign')]")).click();
		driver.findElement(By.xpath("//input[@name='campaignName']")).sendKeys(CAMPAIGN_NAME);
		WebElement size = driver.findElement(By.name("targetSize"));
		size.clear();
		size.sendKeys(TARGET_SIZE);
		
//		Date date = new Date();
//		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
//		String date1 = sim.format(date);
//		Calendar cal = sim.getCalendar();
//		cal.add(cal.DAY_OF_MONTH,30);
//		String reqdate = sim.format(cal.getTime());
		//driver.findElement(By.name("expectedCloseDate")).sendKeys(javau.togetRequirdDate(30));
		webUtility.waitforPageLoad(driver);
		driver.findElement(By.xpath("//button[contains(text(),'Create Campaign')]")).click();
		WebElement confmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.visibilityOf(confmsg));
	
		String sms = confmsg.getText();
		
		if(sms.contains(CAMPAIGN_NAME)) {
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
