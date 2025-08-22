package genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void waitforPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	public void waitForVisibilityOfElement(WebDriver driver,WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver, String nameorid) {
		driver.switchTo().frame(nameorid);
	}
	public void switchToFrame(WebDriver driver, WebElement Framelement) {
		driver.switchTo().frame(Framelement);
	}
	public void select(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}
	public void select(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	public void select(String text, WebElement element) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}
	
		public void switchToAlertAndAccept(WebDriver driver) {
			driver.switchTo().alert().accept();
		}
		public void switchToAlertAndDismiss(WebDriver driver) {
			driver.switchTo().alert().dismiss();
		}
		public String switchToAlertAndGetText(WebDriver driver) {
		String text = driver.switchTo().alert().getText();
			return text;
		}
		public void switchToAlertAndSendkeys(WebDriver driver, String text) {
			driver.switchTo().alert().sendKeys(text);
		}
		
		public void mouseHoverOnWebelement(WebDriver driver, WebElement element) {
		 Actions act = new Actions(driver);
		 act.moveToElement(element).click().perform();
		 
		}
		public void clickOnWebelement(WebDriver driver, WebElement element) {
			 Actions act = new Actions(driver);
			 act.moveToElement(element).click().perform();
			 
			}
		public void switchToWindow(WebDriver driver) {
			String parent = driver.getWindowHandle();
			Set<String> allWindowId = driver.getWindowHandles();
			allWindowId.remove(parent);
			for(String id:allWindowId) {
				driver.switchTo().window(id);
			}
			}
			
			public void doubleClickOnWebelement(WebDriver driver, WebElement element) {
				 Actions act = new Actions(driver);
				 act.doubleClick(element).perform();
			}
			public void passInput(WebDriver driver, WebElement element, String text) {
				 Actions act = new Actions(driver);
				 act.click(element).sendKeys(text).perform();
			}
			public void rightClickOnWebelement(WebDriver driver, WebElement element) {
				 Actions act = new Actions(driver);
				 act.contextClick(element).perform();
			}
			public void takeScreenshot(WebDriver driver, WebElement element, String filename) throws IOException {
			TakesScreenshot ts = (TakesScreenshot)driver;
			File temp = ts.getScreenshotAs(OutputType.FILE);
			File perm = new File("./Screenshots/"+filename+".png");
			FileHandler.copy(temp, perm);
			}
//			public void javaScriptExecutor(WebDriver driver, WebElement element) {
//				JavascriptExecutor js = (JavascriptExecutor)driver;
//				js.executeScript("arguments[0].scrollIntoView(true)", element);
//				
//			}
			public void javaScriptExecutor(WebDriver driver, int x, int y) {
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("window.scrollBy("+x+","+y+")");
			}
			
			
}
