package pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProduct {

	WebDriver driver;
	public CreateProduct(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver=driver;
	}
	
		
		
		
		@FindBy(xpath = "//span[text()='Add Product']")
		private WebElement AddProduct;
		
		@FindBy(name="productName")
		private WebElement ProductName;
		
		@FindBy(name="productCategory")
		private WebElement ProductCategory;
		
		@FindBy(name="quantity")
		private WebElement Quantity;
		
		@FindBy(name="price")
		private WebElement Price;
		
		@FindBy(xpath ="//select[@name='vendorId']")
		private WebElement Vendor;
		
		@FindBy(xpath = "//button[text()='Add']")
		private WebElement Add;
		@FindBy(xpath=("//div[@role='alert']"))
		private WebElement ToastMessage;


		public WebElement getAddProduct() {
			return AddProduct;
		}

		public WebElement getProductName() {
			return ProductName;
		}

		public WebElement getProductCategory() {
			return ProductCategory;
		}

		public WebElement getQuantity() {
			return Quantity;
		}

		public WebElement getPrice() {
			return Price;
		}

		public WebElement getVendor() {
			return Vendor;
		}

		public WebElement getAdd() {
			return Add;
		}

		public WebElement getToastMessage() {
			return ToastMessage;
		}

		public void setToastMessage(WebElement toastMessage) {
			ToastMessage = toastMessage;
		}
		
		

	

}
