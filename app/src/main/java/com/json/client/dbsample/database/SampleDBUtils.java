package com.json.client.dbsample.database;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.json.client.dbsample.utils.Utils;

public class SampleDBUtils {
    private String LOG_TAG = "SampleDBUtils: ";
    private final Uri PROVIDER_URI = Uri.parse("content://com.json.client.dbsample.database.SampleDBProvider");
    private Context mContext;
    private Utils mUtils;

    public SampleDBUtils(Context context) {
        mContext = context;
        mUtils = new Utils(mContext);
    }

    public void insert(String loginId, String message, String dateTime) {
        Log.i(LOG_TAG, "insert: IN");

        ContentValues addRowValue = new ContentValues();

        addRowValue.put("login_id", loginId);
        addRowValue.put("message", message);
        addRowValue.put("data_time", mUtils.getDateTime());

        Uri uri = mContext.getContentResolver().insert(PROVIDER_URI, addRowValue);

        Log.i(LOG_TAG, "insert: uri = " + uri);
    }

    public void search() {


    }

    public void delete(int oldDate) {

    }

}
