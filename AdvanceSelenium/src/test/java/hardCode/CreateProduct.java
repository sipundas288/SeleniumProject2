package hardCode;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class CreateProduct {

	public static void main(String[] args) {
		System.setProperty("webdriver.edge.driver", "C:\\Users\\sipun\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8098/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Add Product')]")).click();
		driver.findElement(By.name("productName")).sendKeys("product123");
		WebElement selProduct = driver.findElement(By.name("productCategory"));
		Select sel = new Select(selProduct);
		sel.selectByValue("Electronics");
		WebElement qua = driver.findElement(By.name("quantity"));
		qua.clear();
		qua.sendKeys("2");
		WebElement pri = driver.findElement(By.name("price"));
		pri.clear();
		pri.sendKeys("10");
		WebElement vendor = driver.findElement(By.name("vendorId"));
		Select sel1 = new Select(vendor);
		sel1.selectByVisibleText("Vendor_68300 - (Electronics)");
		driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
		WebElement usersms = driver.findElement(By.xpath("//div[(@role='alert')]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(usersms));
		String sms = usersms.getText();
		if(sms.contains("product123"))
		{
			System.out.println("Product Added");
		}
		else
		{
			System.out.println("Product is not added");
		}
		Actions act = new Actions(driver);
		WebElement icon = driver.findElement(By.xpath("//*[name()='svg' and @data-icon='user']"));
		act.moveToElement(icon).click().perform();
		driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();	

	}

}
