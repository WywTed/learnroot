package com.huhusky.common.utils.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author ShawnPoon
 */
public class DateUtil {
    public static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat STRING_2_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");

    public static String defaultFormat(long time) {
        Date date = new Date(time);
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static String defaultFormat(Date date) {
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static String yyyyMMddHHmmss(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    public static String yyyyMMddHHmm_(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }

    public static String yyyyMMdd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static String yyyyMM(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.format(date);
    }

    public static Date defaultFormat(String date) throws ParseException {
        date = date.replace(" ", "");
        return STRING_2_DATE_FORMAT.parse(date);
    }

    public static Date yyyyMMddHHmm(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        return sdf.parse(date);
    }

    public static Date yyyyMMdd(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.parse(date);
    }

    public static Date yyyyMM(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        return sdf.parse(date);
    }

    public static Date yyyyMMddHHmmss(String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.parse(date);
    }

    /**
     * 获取距今某天的首尾时间戳
     *
     * @param day
     * @return
     */
    public static long[] getBeginEndOfDay(int day) {
        long[] beDay = new long[2];
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        long start = cal.getTimeInMillis();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        long end = cal.getTimeInMillis();
        beDay[0] = start;
        beDay[1] = end;
        return beDay;
    }

    public static Long timeZero(Object date) {
        Date newDate = new Date();
        if (date instanceof Date) {
            newDate = (Date) date;
        } else if (date instanceof Long) {
            newDate = new Date((Long) date);
        }
        Long time = newDate.getTime() / (1000 * 3600 * 24) * (1000 * 3600 * 24) - TimeZone.getDefault().getRawOffset();
        return time;
    }

    public static Long timeTwelve(Object date) {
        Long twelve = timeZero(date) + 24 * 60 * 60 * 1000 - 1;
        return twelve;
    }

    public static Long todayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    public static Long todayEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    public static Long dayEndtime(long dayStamp){
        Calendar dayEnd = Calendar.getInstance();
        dayEnd.setTimeInMillis(dayStamp);
        dayEnd.set(Calendar.HOUR_OF_DAY, 23);
        dayEnd.set(Calendar.MINUTE, 59);
        dayEnd.set(Calendar.SECOND, 59);
        dayEnd.set(Calendar.MILLISECOND, 999);
        return dayEnd.getTimeInMillis();
    }

    public static Long monthStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.DAY_OF_MONTH, 1);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    public static Long monthEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
        return cal.getTimeInMillis();
    }

    public static Long monthStartTime(long timestamp) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTimeInMillis(timestamp);
        todayStart.set(Calendar.DAY_OF_MONTH, 1);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTimeInMillis();
    }

    public static Long monthEndTime(long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
        return cal.getTimeInMillis();
    }

    public static int delayTime(Calendar currentDate, int hourOfDay, int minuteOfHour, int secondOfMinute) {
        long currentTime = currentDate.getTimeInMillis();
        currentDate.set(Calendar.YEAR, currentDate.get(Calendar.YEAR));
        currentDate.set(Calendar.MONTH, currentDate.get(Calendar.MONTH));
        currentDate.set(Calendar.DAY_OF_MONTH, currentDate.get(Calendar.DAY_OF_MONTH));
        currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        currentDate.set(Calendar.MINUTE, minuteOfHour);
        currentDate.set(Calendar.SECOND, secondOfMinute);
        if ((currentTime - currentDate.getTimeInMillis() > 0)) {
            currentDate.add(Calendar.DATE, 1);
        }
        return (int) (currentDate.getTimeInMillis() - currentTime);
    }

    public static String getDateByTimestamp(Long stamp){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(stamp);
        return sdf.format(date);
    }

    public static String getMonthByTimestamp(Long stamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date date = new Date(stamp);
        return sdf.format(date);
    }


    // 获取上个月  yyyyMMdd
    public static String getLastMonth(String dateStr) throws ParseException {
        Date date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return new SimpleDateFormat("yyyyMM").format(cal.getTime());
    }
}
