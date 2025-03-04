package group.rxcloud.capa.addons.serializer.baiji.value;

import group.rxcloud.capa.addons.serializer.baiji.exception.ParseRuntimeException;
import group.rxcloud.capa.addons.serializer.baiji.value.parser.StandardDateFormatTimeZoneParser;
import group.rxcloud.capa.addons.serializer.baiji.value.parser.ValueParser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public final class DateValues {

    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String STANDARD_SIMPLE_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String STANDARD_DATE_FORMAT_WITHOUT_TIMEZONE = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String STANDARD_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    private static final String DEFAULT_DATE_FORMAT = STANDARD_DATE_FORMAT;
    private static final ValueParser<TimeZone> DEFAULT_TIME_ZONE_PARSER = StandardDateFormatTimeZoneParser.INSTANCE;

    public static DateFormat newDateFormat() {
        return newDateFormat(null, null);
    }

    public static DateFormat newDateFormat(String format) {
        return newDateFormat(format, null);
    }

    public static DateFormat newDateFormat(String format, TimeZone timeZone) {
        if (StringValues.isNullOrWhitespace(format))
            format = DEFAULT_DATE_FORMAT;

        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        if (timeZone != null)
            dateFormat.setTimeZone(timeZone);

        return dateFormat;
    }

    public static GregorianCalendar toGregorianCalendar(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static Date toDate(GregorianCalendar calendar) {
        return calendar.getTime();
    }

    public static String toString(Date date) {
        return toString(date, null);
    }

    public static String toString(Date date, String format) {
        return toString(date, format, null);
    }

    public static String toString(Date date, String format, TimeZone timeZone) {
        return newDateFormat(format, timeZone).format(date);
    }

    public static Date parse(String date) {
        return parse(date, null);
    }

    public static Date parse(String date, String format) {
        return parse(date, format, null);
    }

    public static Date parse(String date, String format, TimeZone timeZone) {
        try {
            if (StringValues.isNullOrWhitespace(date))
                return null;

            return newDateFormat(format, timeZone).parse(date);
        } catch (ParseException ex) {
            throw new ParseRuntimeException(ex);
        }
    }

    public static String toString(Calendar date) {
        return toString(date, null);
    }

    public static String toString(Calendar date, String format) {
        if (date == null)
            return null;

        return newDateFormat(format, date.getTimeZone()).format(date.getTime());
    }

    public static GregorianCalendar parseCalendar(String date) {
        return parseCalendar(date, null, DEFAULT_TIME_ZONE_PARSER);
    }

    public static GregorianCalendar parseCalendar(String date, String format, ValueParser<TimeZone> timeZoneParser) {
        GregorianCalendar calendar = timeZoneParser == null ? new GregorianCalendar() : new GregorianCalendar(timeZoneParser.parse(date));
        calendar.setTime(parse(date, format, calendar.getTimeZone()));
        return calendar;
    }

    private DateValues() {

    }
}