package implemantationOfPropAndExcel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Example1 {

	public static void main(String[] args) {
	//System.setProperty("webdriver.edge.driver", "C:\\Users\\sipun\\Downloads\\edgedriver_win64\\msedgedriver.exe");	
	WebDriver driver = new EdgeDriver();
	driver.manage().window().maximize();
	}

}
