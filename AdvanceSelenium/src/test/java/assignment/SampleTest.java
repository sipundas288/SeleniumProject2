package assignment;

import org.testng.annotations.Test;

public class SampleTest {
	@Test
	public void loginTest() {
		String username = System.getProperty("username");
		String url1 = System.getProperty("url");
		String password1 = System.getProperty("password");
		System.out.println(username);
		System.out.println(password1);
		System.out.println(url1);
	}

}
