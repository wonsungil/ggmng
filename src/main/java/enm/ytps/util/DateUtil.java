package enm.ytps.util;

import com.google.api.ads.admanager.axis.v202005.Date;
import com.google.api.ads.admanager.axis.v202005.DateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    public static LocalDateTime convertGoogleDateTimeToLocalDateTime(DateTime dateTime) {
        if (dateTime==null) {
            return null;
        }

        return LocalDateTime.of(
                dateTime.getDate().getYear()
                , dateTime.getDate().getMonth()
                , dateTime.getDate().getDay()
                , dateTime.getHour()
                , dateTime.getMinute()
                , dateTime.getSecond());
    }

    public static DateTime convertLocalDateTimeToGoogleDateTime(LocalDateTime dateTime) {
        return new DateTime(
                new Date(dateTime.getYear(), dateTime.getMonth().getValue(), dateTime.getDayOfMonth())
                , dateTime.getHour()
                , dateTime.getMinute()
                , dateTime.getSecond()
                , "Asia/Seoul");
    }

    public static String convertGoogleDateTimeToStrDate(DateTime dateTime) {
        if (dateTime == null) {
            return "";
        }
        return convertGoogleDateTimeToLocalDateTime(dateTime)
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public static DateTime convertStrDateToGoogleDateTime(String strDate) {
        return convertLocalDateTimeToGoogleDateTime(
                LocalDateTime.parse(strDate, DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
    }

    public static Date convertStrDateToGoogleDate(String strDate) {

        if (strDate == null) {
            return null;
        }

        strDate = strDate.replace("-", "");

        DateTimeFormatter formatter = null;
        if (strDate.length() == 8) {
            formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        } else {
            formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        }

        return convertLocalDateTimeToGoogleDateTime(LocalDate.parse(strDate, formatter).atStartOfDay()).getDate();
    }
}
