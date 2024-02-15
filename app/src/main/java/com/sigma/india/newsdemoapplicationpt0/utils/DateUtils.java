package com.sigma.india.newsdemoapplicationpt0.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
    private static final String DATE_FORMAT_PATTERN = "EEE, MMM d, yyyy";

    public static Date parseDate(String dateString) {
        if(dateString == null) return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault());
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}

