package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class Login {
	
	WebDriver driver;
	
	public Login(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
		
	}

	@FindBy(id="username")
	private WebElement UN;
	
	@FindBy(id="inputPassword")
	private WebElement PWD;
	
	@FindBy(xpath=("//button[text()='Sign In']"))
	private WebElement loginBtn;

	public WebElement getUN() {
		return UN;
	}

	public WebElement getPWD() {
		return PWD;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

}
