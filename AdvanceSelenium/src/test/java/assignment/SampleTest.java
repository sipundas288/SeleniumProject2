package assignment;

import org.testng.annotations.Test;

public class SampleTest {
	@Test
	public void loginTest() {
		String username = System.getProperty("username");
		System.out.println(username);
	}

}
