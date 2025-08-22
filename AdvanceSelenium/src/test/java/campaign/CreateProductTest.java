package campaign;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtility.ExcelUtility;
import genericUtility.JavaUtility;
import genericUtility.WebDriverUtility;
import pom.CreateProduct;
import pom.HomePage;
import testNG.BaseClass;

public class CreateProductTest extends BaseClass {
	@Test
	public void CreateProduct() throws IOException, InterruptedException {
		JavaUtility jUtility = new JavaUtility();
		WebDriverUtility webdUtility = new WebDriverUtility();
		//ReadDataFromGenericExcelFiles
				ExcelUtility excUtility = new ExcelUtility();
				String PRODUCT_NAME = excUtility.toReadDataFromExcelFile("createProduct", 1, 0);
				String SELECT_CATEGORY = excUtility.toReadDataFromExcelFile("createProduct", 1, 1);
				String QUANTITY = excUtility.toReadDataFromExcelFile("createProduct", 1, 2);
				String PRICE = excUtility.toReadDataFromExcelFile("createProduct", 1, 3);
				String SELECT_VENDOR = excUtility.toReadDataFromExcelFile("createProduct", 1, 4);
				String productname = PRODUCT_NAME+jUtility.getRandomNumber();
				//FromHome
				HomePage hPage = new HomePage(driver);
				hPage.getProduct().click();
				
				//CreateProduct
				CreateProduct cProduct = new CreateProduct(driver);
				cProduct.getAddProduct().click();
				cProduct.getProductName().sendKeys(productname);
				WebElement selProduct = cProduct.getProductCategory();
				webdUtility.select(selProduct, SELECT_CATEGORY);
				
				cProduct.getQuantity().clear();
				cProduct.getQuantity().sendKeys(QUANTITY);
				cProduct.getPrice().clear();
				cProduct.getPrice().sendKeys(PRICE);
				webdUtility.select(SELECT_VENDOR, cProduct.getVendor());
				cProduct.getAdd().click();
				
				
				//Validation
				WebElement toastmsg = cProduct.getToastMessage();
				webdUtility.waitForVisibilityOfElement(driver, toastmsg);
				String sms = toastmsg.getText();
				Assert.assertEquals(sms,"Product "+productname+" Successfully Added");
				//hPage.getClosemsg().click();
				//driver.findElement(By.xpath("//button[@arialabel='close']")).click();

	
		
	}

}
