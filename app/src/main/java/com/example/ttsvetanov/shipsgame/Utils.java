package com.example.ttsvetanov.shipsgame;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ttsvetanov on 02.03.17.
 */

public class Utils {
    public static String fromDateToString(Date date) {
        if (date != null) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            try {
                return formatter.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "";
    }
}
