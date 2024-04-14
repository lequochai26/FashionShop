package org.lequochai.fashionshop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
//    Static fields:
    public static final String DEFAULT_FORMAT = "dd/MM/yyyy HH:mm:ss";

//    Static methods:
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String format(Date date) {
        return format(date, DEFAULT_FORMAT);
    }

//    Constructors:
    private DateFormatter() {

    }
}
