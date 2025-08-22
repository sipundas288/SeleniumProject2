package genericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class JavaUtility {
	
	public int getRandomNumber() {
		
		Random random=new Random();
		int randomcount = random.nextInt(1000);
		return randomcount;
	}
	
	public String getCurrentData() {
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		String currentDate = sim.format(date);
		return currentDate;
	}
	public String togetRequirdDate(int day) {
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(cal.DAY_OF_MONTH,day);
		String reqdate = sim.format(cal.getTime());	
		return reqdate;
	}
	


}

