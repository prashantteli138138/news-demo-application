package com.sigma.india.newsdemoapplicationpt0;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.sigma.india.newsdemoapplicationpt0.utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

public class DateUtilsTest {

    private static final String DATE_STRING = "Tue, Feb 14, 2024";
    private static final Date EXPECTED_DATE;

    static {
        try {
            EXPECTED_DATE = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
                    .parse(DATE_STRING);
        } catch (ParseException e) {
            throw new RuntimeException(e); // Handle exception during initialization
        }
    }

    @Test
    public void testParseDate_ValidDateString_ReturnsParsedDate() throws ParseException {
        Date parsedDate = DateUtils.parseDate(DATE_STRING);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        assertEquals(formatter.format(EXPECTED_DATE), formatter.format(parsedDate));
    }

    @Test
    public void testParseDate_InvalidDateString_ReturnsNull() {
        Date parsedDate = DateUtils.parseDate("Invalid Date String");
        assertNull(parsedDate);
    }

    @Test
    public void testParseDate_NullDateString_ReturnsNull() {
        Date parsedDate = DateUtils.parseDate(null);
        assertNull(parsedDate);
    }

}
