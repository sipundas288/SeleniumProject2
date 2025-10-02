package createLead;

import java.io.IOException;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import genericUtility.ExcelUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import pom.CreateLead;
import pom.HomePage;
import pom.Login;




public class LeadGenericWithTestNG {


	public static void main(String[] args) throws InterruptedException, IOException {
		
	//Open The Browser and login into the application	
		
	System.setProperty("webdriver.edge.driver", "C:\\Users\\sipun\\Downloads\\edgedriver_win64\\msedgedriver.exe");
	
		
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
	
	
	
	
	driver.manage().window().maximize();
	WebDriverUtility webutil = new WebDriverUtility();
	webutil.waitforPageLoad(driver);
	driver.get(URL);
	
	Login login = new Login(driver);
	login.getUN().sendKeys(USERNAME);
	login.getPWD().sendKeys(PASSWORD);
	login.getLoginBtn().click();
	System.out.println("1-Edge browser opned");
	
	
	
	//Read data from excel file
	ExcelUtility excel = new ExcelUtility();
	
	String LEAD_NAME = excel.toReadDataFromExcelFile("createLead", 1, 0);
	String COMPANY = excel.toReadDataFromExcelFile("createLead", 1, 1);
	String LEADSOURCE = excel.toReadDataFromExcelFile("createLead", 1, 2);
	String INDUSTRY = excel.toReadDataFromExcelFile("createLead", 1, 3);
	String PHONE = excel.toReadDataFromExcelFile("createLead", 1, 6);
	String LEAD_STATUS = excel.toReadDataFromExcelFile("createLead", 1, 9);
	String CAMPAIGN = excel.toReadDataFromExcelFile("CreateLead", 1, 17);
	
	//Create Lead
	CreateLead creLead = new CreateLead(driver);
	
	creLead.getClickCreateLead().click();
	creLead.getAddNewCreateLead().click();
	creLead.getName().sendKeys(LEAD_NAME);
	creLead.getLeadSource().sendKeys(LEADSOURCE);
	creLead.getCompany().sendKeys(COMPANY);
	creLead.getIndustry().sendKeys(INDUSTRY);
	creLead.getPhone().sendKeys(PHONE);
	creLead.getLeadStatus().sendKeys(LEAD_STATUS);
	creLead.getAddCmpaign().click();
	
	String parent = driver.getWindowHandle();
	Set<String> ids = driver.getWindowHandles();
	ids.remove(parent);
	for(String child : ids)
	{
		driver.switchTo().window(child);
	}
	
	creLead.getSearchCampaign().sendKeys(CAMPAIGN);
	WebElement campaign = creLead.getSelectCampaign();
	webutil.waitForVisibilityOfElement(driver, campaign);
	campaign.click();
	driver.switchTo().window(parent);
	creLead.getSubmitCreateLead().click();
	
	
	
	System.out.println("2-All the new lead details are being  Filled");
	
	
	//Validate lead is added or not
	WebElement message = driver.findElement(By.xpath("//div[@role='alert']"));
	
	webutil.waitForVisibilityOfElement(driver, message);
	String sms= message.getText();
	
	if(sms.contains(LEAD_NAME)) {
		System.out.println("3-Lead added successfully");
		
	}
	else {
		System.out.println("3-Lead is not added please check again");
	
	creLead.getClose().click();
	
	
	
	//Logout from the application
	
	HomePage home = new HomePage(driver);
	home.getUserIcon().click();
	home.getLogOut().click();
	
	System.out.println("4-logout from the application");

	}

}
