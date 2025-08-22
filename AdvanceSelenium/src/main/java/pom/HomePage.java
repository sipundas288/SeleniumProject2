package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
		
		WebDriver driver;
		public HomePage(WebDriver driver) {
			PageFactory.initElements(driver, this);
			this.driver=driver;
		}
	    @FindBy(xpath = "//a[text()='Campaigns']")
	    private WebElement Campaigns;
	    @FindBy(xpath = "//a[contains(text(),'Products')]")
		private WebElement Product;
		@FindBy(xpath=("//div[@class='user-icon']"))
		private WebElement UserIcon;
		@FindBy(xpath = ("//a[text()='Leads']"))
		private WebElement Leads;
		@FindBy(xpath = "//button[@aria-label='close']") 
		 private WebElement Closemsg;
		@FindBy(xpath=("//div[@class='dropdown-item logout']"))
		private WebElement LogOut;
		public WebElement getCampaigns() {
			return Campaigns;
		}
		public WebElement getProduct() {
			return Product;
		}
		public WebElement getClosemsg() {
			return Closemsg;
		}
		public WebElement getUserIcon() {
			return UserIcon;
		}
		public WebElement getLeads() {
			return Leads;
		}
		public WebElement getLogOut() {
			return LogOut;
		}
		
	

}
