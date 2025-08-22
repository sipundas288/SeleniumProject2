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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import genericUtility.ExcelUtility;
import genericUtility.JavaUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import pom.CreateProduct;
import pom.HomePage;
import pom.Login;
public class CreateProduct1 {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//ReadDataFromGenericUtilityPropertiesFile
		PropertiesFileUtility proUtility = new PropertiesFileUtility();
		String BROWSER = proUtility.togetDataFromPropertiesFile("browser");
		String URL = proUtility.togetDataFromPropertiesFile("url");
		String USERNAME = proUtility.togetDataFromPropertiesFile("username");
		String PASSWORD = proUtility.togetDataFromPropertiesFile("password");
		
		//ReadDataFromGenericExcelFiles
		ExcelUtility excUtility = new ExcelUtility();
		String PRODUCT_NAME = excUtility.toReadDataFromExcelFile("createProduct", 1, 0);
		String SELECT_CATEGORY = excUtility.toReadDataFromExcelFile("createProduct", 1, 1);
		String QUANTITY = excUtility.toReadDataFromExcelFile("createProduct", 1, 2);
		String PRICE = excUtility.toReadDataFromExcelFile("createProduct", 1, 3);
		String SELECT_VENDOR = excUtility.toReadDataFromExcelFile("createProduct", 1, 4);


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
				
				
				WebDriverUtility webdUtility = new WebDriverUtility();
				webdUtility.waitforPageLoad(driver);
				driver.get(URL);
				//Login
				Login signin = new Login(driver);
				signin.getUN().sendKeys(USERNAME);
				signin.getPWD().sendKeys(PASSWORD);
				signin.getLoginBtn().click();
				//FromHome
				HomePage hPage = new HomePage(driver);
				hPage.getProduct().click();
				JavaUtility jUtility = new JavaUtility();
				CreateProduct cProduct = new CreateProduct(driver);
				cProduct.getAddProduct().click();
				cProduct.getProductName().sendKeys(PRODUCT_NAME+jUtility.getRandomNumber());
				WebElement selProduct = cProduct.getProductCategory();
				webdUtility.select(selProduct, SELECT_CATEGORY);
				
				cProduct.getQuantity().clear();
				cProduct.getQuantity().sendKeys(QUANTITY);
				cProduct.getPrice().clear();
				cProduct.getPrice().sendKeys(PRICE);
				webdUtility.select(SELECT_VENDOR, cProduct.getVendor());
				cProduct.getAdd().click();
				
				WebElement toastmsg = cProduct.getToastMessage();
				webdUtility.waitForVisibilityOfElement(driver, toastmsg);
				String sms = toastmsg.getText();
		if(sms.contains(PRODUCT_NAME))
		{
			System.out.println(PRODUCT_NAME);
		}
		else
		{
			System.out.println("Product is not added");
		}
		WebElement icon = hPage.getUserIcon();
		webdUtility.mouseHoverOnWebelement(driver, icon);
		hPage.getLogOut().click();

	}

}
