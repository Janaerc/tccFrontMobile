package backend;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String BACKEND_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";


    public static Date formatDateForBackend(Date date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(BACKEND_DATE_FORMAT, Locale.getDefault());
        String formattedDate = dateFormat.format(date);
        return dateFormat.parse(formattedDate);
    }
}
