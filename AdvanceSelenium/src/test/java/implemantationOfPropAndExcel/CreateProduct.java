package implemantationOfPropAndExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import pom.Login;
public class CreateProduct {

	public static void main(String[] args) throws IOException, InterruptedException {
		 JavaUtility javau = new JavaUtility();
		 WebDriverUtility webUtil = new WebDriverUtility();
		//		FileInputStream fis=new FileInputStream("./src\\test\\resources\\commondata.properties");
//		Properties prop=new Properties();
//		prop.load(fis);
//		String BROWSER = prop.getProperty("browser");
//		String URL = prop.getProperty("url");
//		String USERNAME = prop.getProperty("username");
//		String PASSWORD = prop.getProperty("password");
		
		
		PropertiesFileUtility plib = new PropertiesFileUtility();
		String BROWSER = plib.togetDataFromPropertiesFile("browser");
		String URL = plib.togetDataFromPropertiesFile("url");
		String USERNAME = plib.togetDataFromPropertiesFile("username");
		String PASSWORD = plib.togetDataFromPropertiesFile("password");
		
		FileInputStream fis1 = new FileInputStream("C:\\Java_Pavan_Sir\\selenuumwebdriver\\AdvanceSelenium\\src\\test\\resources\\AddProduct.xlsx");
		Workbook wb= WorkbookFactory.create(fis1);
		Sheet sh=wb.getSheet("createCampaign");
		String PRODUCT_NAME = sh.getRow(1).getCell(0).getStringCellValue();
		String SELECT_CATEGORY = sh.getRow(1).getCell(1).getStringCellValue();
		String QUANTITY = sh.getRow(1).getCell(2).getStringCellValue();
		String PRICE_PER_UNIT = sh.getRow(1).getCell(3).getStringCellValue();
		String SELECT_VENDER = sh.getRow(1).getCell(4).getStringCellValue();
		
//		Random rand = new Random();
//        int randomNum = rand.nextInt(1000);

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
		webUtil.waitforPageLoad(driver);
		driver.get(URL);
		
		//Login
				Login signin = new Login(driver);
				signin.getUN().sendKeys(USERNAME);
				signin.getPWD().sendKeys(PASSWORD);
				signin.getLoginBtn().click();
		
//		driver.findElement(By.id("username")).sendKeys(USERNAME);
//		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
//		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
//		driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
				
		//Find Elements
		//AddProduct ap = new AddProduct();
		
		driver.findElement(By.xpath("//span[contains(text(),'Add Product')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("productName")).sendKeys(PRODUCT_NAME+javau.getRandomNumber());
		WebElement selProduct = driver.findElement(By.name("productCategory"));
		webUtil.select(selProduct, SELECT_VENDER);
		WebElement qua = driver.findElement(By.name("quantity"));
		qua.clear();
		qua.sendKeys(QUANTITY);
		WebElement pri = driver.findElement(By.name("price"));
		pri.clear();
		pri.sendKeys(PRICE_PER_UNIT);
		WebElement vendor = driver.findElement(By.name("vendorId"));
		webUtil.select(vendor, SELECT_VENDER);
		
		driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
		WebElement usersms = driver.findElement(By.xpath("//div[(@role='alert')]"));
		webUtil.waitForVisibilityOfElement(driver, usersms);
		String sms = usersms.getText();
		if(sms.contains(PRODUCT_NAME+javau.getRandomNumber()))
		{
			System.out.println("Product Added");
		}
		else
		{
			System.out.println("Product is not added");
		}
		WebElement icon = driver.findElement(By.xpath("//*[name()='svg' and @data-icon='user']"));
		webUtil.clickOnWebelement(driver, icon);
		driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();	

	}

}
