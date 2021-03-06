package Util;

import javafx.util.StringConverter;

import java.security.MessageDigest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author qiaojiyuan
 * @date 2021/1/29
 */
public class StringUtil {

    public static final DateTimeFormatter SIMPLE_DATE__M_D_FORMAT = DateTimeFormatter.ofPattern("MM-dd");
    public static final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter SIMPLE_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm;ss");
    public static final String PATTERN_MONEY = "^([1-9]\\d{0,9}|0)(\\.\\d{1,2})?$";
    private static final String slat = "u89u9j8&HYUG8@98.h0980h%798G";

    public static String encrypt(String dataStr) {
        try {
            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes("UTF8"));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    public static int countSize(String str) {
        return countSize(str, 25, 5);
    }

    public static int countSize(String str, int q, int b) {
        int result = 0;
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            if (Character.toString(aChar).getBytes().length > 1) {
                result += q;
            } else {
                result += b;
            }
        }
        return result;
    }

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date stringToDate(LocalDate date) {
        return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate stringToLocalDate(String date, DateTimeFormatter format) {
        return dateToLocalDate(stringToDate(date, format));
    }

    public static Date stringToDate(String date) {
        return stringToDate(date, SIMPLE_DATE_FORMAT);
    }

    public static Date stringToDate(String date, DateTimeFormatter format) {
        return Date.from(LocalDate.parse(date, format).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date stringToDateTime(String date) {
        return stringToDate(date, SIMPLE_DATE_TIME_FORMAT);
    }

    public static Date stringToDateTime(String date, DateTimeFormatter format) {
        return Date.from(LocalDate.parse(date, format).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static String dateToString(Date date) {
        return dateToString(date, SIMPLE_DATE_FORMAT);
    }

    public static String localDateToString(LocalDate date) {
        return localDateToString(date, SIMPLE_DATE_FORMAT);
    }

    public static String localDateToString(LocalDate date, DateTimeFormatter dateFormat) {
        return date.format(dateFormat);
    }

    public static String dateToString(Date date, DateTimeFormatter format) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(format);
    }

    public static String dateTimeToString(Date date) {
        return dateTimeToString(date, SIMPLE_DATE_TIME_FORMAT);
    }

    public static String dateTimeToString(Date date, DateTimeFormatter format) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(format);
    }

    public static StringConverter dateStringConverter () {
        return new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return SIMPLE_DATE_FORMAT.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, SIMPLE_DATE_FORMAT);
                } else {
                    return null;
                }
            }
        };
    }

    public static StringConverter dateTimeStringConverter () {
        return new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return SIMPLE_DATE_TIME_FORMAT.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, SIMPLE_DATE_TIME_FORMAT);
                } else {
                    return null;
                }
            }
        };
    }

    public static boolean isEmpty(String str) {
        return str == null || ("").equals(str);
    }

    public static boolean isEmpty(Object obj) {
        return obj == null;
    }

    public static boolean isMoney(String str) {
        return !isEmpty(str) && Pattern.matches(PATTERN_MONEY, str);
    }
}
