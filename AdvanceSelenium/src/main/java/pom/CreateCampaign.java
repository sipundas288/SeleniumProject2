
package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CreateCampaign {
	WebDriver driver;
	public CreateCampaign(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
		
	}
	
	@FindBy(xpath=("//span[contains(text(),'Create Campaign')]"))
	private WebElement CreateCampaign;
	@FindBy(name=("campaignName"))
	private WebElement CampaignName;
	@FindBy(name=("campaignStatus"))
	private WebElement Status;
	@FindBy(name=("targetSize"))
	private WebElement TargetSize;
	@FindBy(name =("expectedCloseDate"))
	private WebElement WithDate;
	@FindBy(xpath=("//button[@type='submit']"))
	private WebElement ClickCreateC;
	@FindBy(xpath=("//div[@role='alert']"))
	private WebElement ToastMessage;
	
	public WebElement getCreateCampaign() {
		return CreateCampaign;
	}
	
	public WebElement getStatus() {
		return Status;
	}

	public void setStatus(WebElement status) {
		Status = status;
	}

	public WebElement getCampaignName() {
		return CampaignName;
	}
	public WebElement getTargetSize() {
		return TargetSize;
	}
	
	public WebElement getWithDate() {
		return WithDate;
	}
	public WebElement getClickCreateC() {
		return ClickCreateC;
	}
	public WebElement getToastMessage() {
		return ToastMessage;
	}

	
	
	
	
	

}
