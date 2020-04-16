import java.util.Calendar;
import java.util.GregorianCalendar;

public class GregCal {
	public static void main(String[] args) {
		GregorianCalendar today = new GregorianCalendar();
		GregorianCalendar birthday = new GregorianCalendar(2002, Calendar.FEBRUARY, 14);
		
		String days[] = {
				"Sunday",
				"Monday",
				"Tuesday",
				"Wednesday",
				"Thursday",
				"Friday",
				"Saturday"
		};
		
		String months[] = {
				"January",
				"Febuary",
				"March",
				"April",
				"May",
				"June",
				"July",
				"August",
				"September",
				"October",
				"November",
				"December"
		};
						
		today.add(GregorianCalendar.DATE, 100);
		String todayWeekday = days[today.get(Calendar.DAY_OF_WEEK) - 1];
		String todayMonth = months[today.get(Calendar.MONTH)];
		
		String birthdayWeekdayPre = "Weekday of my Birthday: " +
		days[birthday.get(Calendar.DAY_OF_WEEK) - 1];
		
		birthday.add(GregorianCalendar.DATE, 10000);
		String birthdayWeekday = days[birthday.get(Calendar.DAY_OF_WEEK) - 1];
		String birthdayMonth = months[birthday.get(Calendar.MONTH)];
		
		String hundred = "100 days from now: " +
		todayWeekday + ", " +
		todayMonth + " " +
		today.get(Calendar.DATE) + ", " +
		today.get(Calendar.YEAR);
		
		String tenKBirthday = "10,000 after my Birthday: " +
		birthdayWeekday + ", " + birthdayMonth + " " +
		birthday.get(Calendar.DATE) + ", " + birthday.get(Calendar.YEAR);
		
		System.out.println(hundred);
		System.out.println(birthdayWeekdayPre);
		System.out.println(tenKBirthday);
	}
}
