package eden.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OtherUtils {

	public static Date strToDate(String strDate) {

		SimpleDateFormat formatter = new SimpleDateFormat("MM-DD-YYYY");
		Date formatedDate = null;
		try {
			formatedDate = formatter.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return formatedDate;
	}
	
	public static Date timeToDate(String strTime) {

		SimpleDateFormat formatter = new SimpleDateFormat("MM-DD-YYYY HH:MM:SS");
		strTime = "09-12-2015" + strTime;
		Date formatedDate = null;
		try {
			formatedDate = formatter.parse(strTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return formatedDate;
	}

}
