package assignment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Domy {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		String date1 = sim.format(date);
		System.out.println(date1);
		Calendar cal = sim.getCalendar();
		cal.add(cal.DAY_OF_MONTH,30);
		String req = sim.format(cal.getTime());
		System.out.println(req);
		
	}

}
