package assignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Driver;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestCase21071 {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		FileInputStream fis =new  FileInputStream("./src\\test\\java\\dataDrivenTesting\\commondata.properties");
		
		Properties pro= new Properties();
		pro.load(fis);
		
		String BROWSER = pro.getProperty("browser");
		String URL = pro.getProperty("url");
		String USERNAME = pro.getProperty("username");
		String PASSWORD = pro.getProperty("password");
		
		
		
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(),'Create Campaign')]")).click();
		driver.findElement(By.name("campaignName")).sendKeys("Nidhi1");
		WebElement size = driver.findElement(By.name("targetSize"));
		size.clear();
		size.sendKeys("5");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[normalize-space()='Contacts']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Create Contact')]")).click();
		driver.findElement(By.name("organizationName")).sendKeys("NidhiOgr");
		driver.findElement(By.name("title")).sendKeys("Org");
		driver.findElement(By.name("contactName")).sendKeys("NidhiContact");
		driver.findElement(By.name("mobile")).sendKeys("5698745821");
		driver.findElement(By.xpath("//*[name()='svg' and @data-icon='plus']")).click();
		String parent = driver.getWindowHandle();
		Set<String> wid = driver.getWindowHandles();
		wid.remove(parent);
		for(String child:wid)
		{
			driver.switchTo().window(child);
		}
		WebElement sel = driver.findElement(By.id("search-criteria"));
		Select sc= new Select(sel);
		sc.selectByValue("campaignName");
		driver.findElement(By.id("search-input")).sendKeys("Nidhi");
		driver.findElement(By.xpath("//button[@class='select-btn']")).click();
		driver.switchTo().window(parent);
		driver.findElement(By.xpath("//button[contains(text(),'Create Contact')]")).click();
		
		
		
		
		
		
		
	}

}
