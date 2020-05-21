package com.json.client.dbsample.utils;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {
    private String LOG_TAG = "Utils: ";
    private Context mContext;

    public Utils(Context context) {
        mContext = context;
    }

    public String getDateTime() {
        String dateTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        dateTime = sdf.format(new Date());

        Log.i(LOG_TAG, "getDateTime: dateTime = " + dateTime);
        return dateTime;
    }
}
