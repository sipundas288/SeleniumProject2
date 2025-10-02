package campaign;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import genericUtility.ExcelUtility;
import genericUtility.JavaUtility;
import pom.CreateCampaign;
import pom.HomePage;
import testNG.BaseClass;
@Listeners(Listener.ListenerImplimentaion.class)
public class CampaignTest extends BaseClass{
	@Test(groups="smoke")
	public void CreateCampaignwithMandatory() throws IOException, InterruptedException {
		JavaUtility jUtility = new JavaUtility();
		//ReadDataFromGenericExcelFiles
		ExcelUtility excUtility = new ExcelUtility();
		String CAMPAIGN_NAME = excUtility.toReadDataFromExcelFile("createCampaign", 1, 2);
		String TARGET_SIZE = excUtility.toReadDataFromExcelFile("createCampaign", 1, 3);
		//String campname = CAMPAIGN_NAME+jUtility.getRandomNumber();
				
		//FromHome
		HomePage hPage = new HomePage(driver);
		hPage.getCampaigns().click();
		
		//FromCreateCampaign
		CreateCampaign cCampaign = new pom.CreateCampaign(driver);
		cCampaign.getCreateCampaign().click();
		cCampaign.getCampaignName().sendKeys(CAMPAIGN_NAME);
		cCampaign.getTargetSize().clear();
		cCampaign.getTargetSize().sendKeys(TARGET_SIZE);
		cCampaign.getClickCreateC().click();
		
		
		//hPage.getCampaigns().click();
		
		//validation
		WebElement confmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(confmsg));
		String sms = confmsg.getText();
		
		Assert.assertEquals(sms,"Campaign "+CAMPAIGN_NAME+" Successfully Added");
		hPage.getClosemsg().click();
					
	}
	@Test(groups="regression")
	public void CreateCampaignWithStatus() throws IOException {
		JavaUtility jUtility = new JavaUtility();
		//ReadDataFromGenericExcelFiles
		ExcelUtility excUtility = new ExcelUtility();
		String CAMPAIGN_NAME = excUtility.toReadDataFromExcelFile("createCampaign", 1, 2);
		
		String TARGET_SIZE = excUtility.toReadDataFromExcelFile("createCampaign", 1, 3);
		String STATUS = excUtility.toReadDataFromExcelFile("CreateCampaign", 1, 4);
		//String campname = CAMPAIGN_NAME+jUtility.getRandomNumber();
		
	
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
		
		//validation
				WebElement confmsg = driver.findElement(By.xpath("//div[@role='alert']"));
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
				wait.until(ExpectedConditions.visibilityOf(confmsg));
				String sms = confmsg.getText();
				
				Assert.assertEquals(sms,"Campaign "+CAMPAIGN_NAME+" Successfully Added");
				//driver.findElement(By.xpath("//button[@arialabel='close']")).click();
				hPage.getClosemsg().click();

	
	}
	@Test(groups="smoke")
	public void CreateCampaignWithExpectedDate() throws IOException {
		
		JavaUtility jUtility = new JavaUtility();
		//ReadDataFromGenericExcelFiles
		ExcelUtility excUtility = new ExcelUtility();
		String CAMPAIGN_NAME = excUtility.toReadDataFromExcelFile("createCampaign", 1, 2);
		String TARGET_SIZE = excUtility.toReadDataFromExcelFile("createCampaign", 1, 3);
		//String campname = CAMPAIGN_NAME+jUtility.getRandomNumber();
		
	
		//FromHome
		HomePage hPage = new HomePage(driver);
		hPage.getCampaigns().click();
		//FromCraateCampaign
		CreateCampaign cCampaign = new pom.CreateCampaign(driver);
		cCampaign.getCreateCampaign().click();
		cCampaign.getCampaignName().sendKeys(CAMPAIGN_NAME);
		cCampaign.getTargetSize().clear();
		cCampaign.getTargetSize().sendKeys(TARGET_SIZE);
		cCampaign.getWithDate().sendKeys(jUtility.togetRequirdDate(30));
		cCampaign.getClickCreateC().click();
		//validation
		WebElement confmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(confmsg));
		String sms = confmsg.getText();
		
		Assert.assertEquals(sms,"Campaign "+CAMPAIGN_NAME+" Successfully Added");
		hPage.getClosemsg().click();
	}
	}

		
	
		
		
	

