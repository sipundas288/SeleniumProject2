package impPropAndExcel;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
public class CreateProduct {

	public static void main(String[] args) throws IOException {
		//Properties file
				FileInputStream fis = new FileInputStream("./src\\test\\resources\\commondata.properties");
				Properties pro= new Properties();
				pro.load(fis);
				String BROWSER = pro.getProperty("browser");
				String URL = pro.getProperty("url");
				String USERNAME = pro.getProperty("username");
				String PASSWORD = pro.getProperty("password");
				
				//Excel file
				FileInputStream exc=new FileInputStream("./src\\test\\resources\\PropertiesFile.xlsx");
				Workbook wb=WorkbookFactory.create(exc);
				Sheet sh = wb.getSheet("createProduct");
				String PRODUCT_NAME = sh.getRow(1).getCell(0).getStringCellValue();
				String SELECT_CATEGORY = sh.getRow(1).getCell(1).getStringCellValue();
				String QUANTITY = sh.getRow(1).getCell(2).getStringCellValue();
				String PRICE = sh.getRow(1).getCell(3).getStringCellValue();
				String SELECT_VENDOR = sh.getRow(1).getCell(4).getStringCellValue();
				
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[contains(text(),'Sign In')]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Products')]")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Add Product')]")).click();
		driver.findElement(By.name("productName")).sendKeys(PRODUCT_NAME);
		WebElement selProduct = driver.findElement(By.name("productCategory"));
		Select sel = new Select(selProduct);
		sel.selectByValue(SELECT_CATEGORY);
		WebElement qua = driver.findElement(By.name("quantity"));
		qua.clear();
		qua.sendKeys(QUANTITY);
		WebElement pri = driver.findElement(By.name("price"));
		pri.clear();
		pri.sendKeys(PRICE);
		WebElement vendor = driver.findElement(By.name("vendorId"));
		Select sel1 = new Select(vendor);
		sel1.selectByVisibleText(SELECT_VENDOR);
		driver.findElement(By.xpath("//button[contains(text(),'Add')]")).click();
		WebElement usersms = driver.findElement(By.xpath("//div[(@role='alert')]"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(usersms));
		String sms = usersms.getText();
		if(sms.contains(PRODUCT_NAME))
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
