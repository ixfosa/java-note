package com.ixfosa.format;


import org.springframework.format.Formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ixfosa on 2021/4/12 15:27
 */
public class StringToDateFormatter implements Formatter<Date> {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        return dateFormat.parse(s);
    }

    @Override
    public String print(Date date, Locale locale) {
        return dateFormat.format(date);
    }
}
