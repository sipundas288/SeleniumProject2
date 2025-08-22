package impPropAndExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
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

public class CreateCampaignWithStatus1{
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//Properties file
				FileInputStream fis = new FileInputStream("./src\\test\\resources\\commondata.properties");
				Properties pro= new Properties();
				pro.load(fis);
				String BROWSER = pro.getProperty("browser");
				String URL = pro.getProperty("url");
				String USERNAME = pro.getProperty("username");
				String PASSWORD = pro.getProperty("password");
				
				//Excel file
				FileInputStream exc=new FileInputStream("./src\\test\\resources\\PropertiesFile.xlsx");
				Workbook wb=WorkbookFactory.create(exc);
				Sheet sh = wb.getSheet("createCampaign");
				String CAMPAIGN_NAME = sh.getRow(1).getCell(2).getStringCellValue();
				String TARGET_SIZE = sh.getRow(1).getCell(3).getStringCellValue();
				String STASTUS = sh.getRow(1).getCell(4).getStringCellValue();				
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Create Campaign')]")).click();
		driver.findElement(By.xpath("//input[@name='campaignName']")).sendKeys("Addi");
		driver.findElement(By.name("campaignStatus")).sendKeys(STASTUS);
		WebElement size = driver.findElement(By.name("targetSize"));
		size.clear();
		size.sendKeys("2");
		driver.findElement(By.xpath("//button[contains(text(),'Create Campaign')]")).click();
		WebElement confmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(confmsg));
		String sms = confmsg.getText();
		
		if(sms.contains("Addi")) {
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


	//If campaign is not created then change the campaign name and execute again

}
