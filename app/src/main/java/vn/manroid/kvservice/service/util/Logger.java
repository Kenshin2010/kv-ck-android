package vn.manroid.kvservice.service.util;

import android.util.Log;

/**
 * Created by Mr.Sen on 10/23/2017.
 */

public class Logger {
    private static final String LOGGER_NAME = "Antsoft";

    public static void d(String log) {
        Log.d(LOGGER_NAME, log);
    }

    public static void w(String log) {
        Log.w(LOGGER_NAME, log);
    }

    public static void e(String log) {
    }
}
