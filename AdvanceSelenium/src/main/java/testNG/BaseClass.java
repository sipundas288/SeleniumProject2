package testNG;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import pom.HomePage;
import pom.Login;

public class BaseClass {
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	PropertiesFileUtility proUtil=new PropertiesFileUtility();
	WebDriverUtility webUtility=new WebDriverUtility();
	
	@BeforeSuite(groups={"smoke","regression"})
	public void beforeSute() {
		System.out.println("establich database connectivity");
	}
	@BeforeTest(groups={"smoke","regression"})
	public void beforeTest() {
		System.out.println("precondition");
	}
	//@Parameters("BROWSER")
	@BeforeClass(groups={"smoke","regression"})
	//String browser
	public void beforeClass() throws IOException {
		//String BROWSER=browser;

		String BROWSER = proUtil.togetDataFromPropertiesFile("browser");
		if(BROWSER.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver", "C:\\Users\\sipun\\Downloads\\edgedriver_win64\\msedgedriver.exe");	
			driver = new EdgeDriver();
		}
		else if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")) {
			
			driver = new FirefoxDriver();
		}
		sdriver=driver;//passing driver ref to static driver variable
		
		driver.manage().window().maximize();
		webUtility.waitforPageLoad(driver);
	}
	@BeforeMethod(groups={"smoke","regression"})
	public void beforeMethod() throws IOException {
		PropertiesFileUtility proUtil = new PropertiesFileUtility();
		String BROWSER = proUtil.togetDataFromPropertiesFile("browser");
		String URL = proUtil.togetDataFromPropertiesFile("url");
		String USERNAME = proUtil.togetDataFromPropertiesFile("username");
		String PASSWORD = proUtil.togetDataFromPropertiesFile("password");
		Login signin = new Login(driver);
		driver.get(URL);
		signin.getUN().sendKeys(USERNAME);
		signin.getPWD().sendKeys(PASSWORD);
		signin.getLoginBtn().click();
		System.out.println("login done");
	}
	@AfterMethod(groups={"smoke","regression"})
	public void afterMethod() {
		HomePage hPage = new HomePage(driver);
		WebElement icon1 = hPage.getUserIcon();
		webUtility.clickOnWebelement(driver, icon1);
		hPage.getLogOut().click();
	}
	@AfterClass(groups={"smoke","regression"})
	public void afterClass(){
		driver.quit();
		
	}
	@AfterTest(groups={"smoke","regression"})
	public void afterTest() {
		System.out.println("post-codition");	
	}
	@AfterSuite(groups={"smoke","regression"})
	public void afterSuit(){
		System.out.println("close Dbconnection");
	}
	

}
