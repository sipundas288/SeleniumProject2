package implimentationOfGenericUtilities;

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

import genericUtility.ExcelUtility;
import genericUtility.PropertiesFileUtility;
import genericUtility.WebDriverUtility;
import pom.CreateCampaign;
import pom.HomePage;
import pom.Login;

public class CreateCampaignWithStatus1{
	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		//ReadDataFromGenericUtilityPropertiesFile
				PropertiesFileUtility proUtility = new PropertiesFileUtility();
				String BROWSER = proUtility.togetDataFromPropertiesFile("browser");
				String URL = proUtility.togetDataFromPropertiesFile("url");
				String USERNAME = proUtility.togetDataFromPropertiesFile("username");
				String PASSWORD = proUtility.togetDataFromPropertiesFile("password");
				
				
		
				//ReadDataFromGenericExcelFiles
				ExcelUtility excUtility = new ExcelUtility();
				String CAMPAIGN_NAME = excUtility.toReadDataFromExcelFile("createCampaign", 1, 2);
				String TARGET_SIZE = excUtility.toReadDataFromExcelFile("createCampaign", 1, 3);
				String STATUS = excUtility.toReadDataFromExcelFile("CreateCampaign", 1, 4);
				
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
				hPage.getCampaigns().click();
				//FromCraateCampaign
				CreateCampaign cCampaign = new pom.CreateCampaign(driver);
				cCampaign.getCreateCampaign().click();
				cCampaign.getCampaignName().sendKeys(CAMPAIGN_NAME);
				cCampaign.getStatus().sendKeys(STATUS);
				cCampaign.getTargetSize().clear();
				cCampaign.getTargetSize().sendKeys(TARGET_SIZE);
				cCampaign.getClickCreateC().click();
				WebElement toastmsg = cCampaign.getToastMessage();
				webdUtility.waitForVisibilityOfElement(driver, toastmsg);
				String sms = toastmsg.getText();
		
		if(sms.contains("Addi")) {
			System.out.println("campaign created");
		}
		else
		{
			System.out.println("campaign not created");
		}
		WebElement icon = hPage.getUserIcon();
		webdUtility.mouseHoverOnWebelement(driver, icon);
		hPage.getLogOut().click();
	}


	//If campaign is not created then change the campaign name and execute again

}
