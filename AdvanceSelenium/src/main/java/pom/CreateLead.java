package pom;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateLead {
	WebDriver driver;
	public CreateLead(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}	
		@FindBy(xpath ="//a[contains(text(),'Leads')]")
		private WebElement ClickCreateLead;
		
		@FindBy(xpath = "//span[contains(text(),'Create Lead')]")
		private WebElement AddNewCreateLead;
		
		public WebElement getClickCreateLead() {
			return ClickCreateLead;
		}

		public WebElement getAddNewCreateLead() {
			return AddNewCreateLead;
		}
		@FindBy(name="name")
		private WebElement Name;
		
		@FindBy(name="leadSource")
		private WebElement LeadSource;
		
		@FindBy(name="company")
		private WebElement Company;
		
		@FindBy(name="industry")
		private WebElement Industry;
		
		@FindBy(name="phone")
		private WebElement Phone;
		
		@FindBy(xpath = "//input[@name='leadStatus']")
		private WebElement LeadStatus;
		
		@FindBy(xpath = "//*[name()='svg' and @data-icon='plus']")
		private WebElement AddCmpaign;
		
		@FindBy(id="search-input")
		private WebElement SearchCampaign;
		
		@FindBy(xpath = "//button[@class='select-btn']")
		private WebElement SelectCampaign;
		
		@FindBy(xpath = "//button[@type='submit']")
		private WebElement SubmitCreateLead;
		
		@FindBy(xpath = "//button[@type='button' and @aria-label='close']")
		private WebElement Close;
		
		public WebElement getClose() {
			return Close;
		}

		public WebDriver getDriver() {
			return driver;
		}

		public WebElement getName() {
			return Name;
		}

		public WebElement getLeadSource() {
			return LeadSource;
		}

		public WebElement getCompany() {
			return Company;
		}

		public WebElement getIndustry() {
			return Industry;
		}

		public WebElement getPhone() {
			return Phone;
		}

		public WebElement getLeadStatus() {
			return LeadStatus;
		}

		public WebElement getAddCmpaign() {
			return AddCmpaign;
		}

		public WebElement getSearchCampaign() {
			return SearchCampaign;
		}

		public WebElement getSelectCampaign() {
			return SelectCampaign;
		}

		public WebElement getSubmitCreateLead() {
			return SubmitCreateLead;
		}

		
	

}
