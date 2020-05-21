package com.json.client.dbsample.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.util.Log;

import com.json.client.dbsample.utils.Utils;

import java.util.ArrayList;

import static com.json.client.dbsample.database.SampleDBManager.DB_TABLE_SAMPLE_DATA;

public class SampleDBUtils {
    private String LOG_TAG = "SampleDBUtils: ";
    private final Uri PROVIDER_URI = Uri.parse("content://com.json.client.dbsample.database.SampleDBProvider");
    private Context mContext;
    private Utils mUtils;

    private SampleDBManager mSampleDBManager;

    SampleDBUtils(Context context) {
        mContext = context;
        mUtils = new Utils(mContext);
        mSampleDBManager = SampleDBManager.getInstance(mContext);
    }

    long insert(String loginId, String message, String dateTime, long timestamp) {
        Log.i(LOG_TAG, "insert: IN");

        ContentValues addRowValue = new ContentValues();

        addRowValue.put("login_id", loginId);
        addRowValue.put("message", message);
        addRowValue.put("date_time", dateTime);
        addRowValue.put("timestamp", timestamp);

        long rowID = mSampleDBManager.insert(addRowValue);

        Log.i(LOG_TAG, "insert: rowID = " + rowID);
        return rowID;
    }

    public void search() {
        String selectQuery = "SELECT  * FROM " + DB_TABLE_SAMPLE_DATA;

//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);

    }

    int deleteAll() {
        int result = mSampleDBManager.delete(null, null);
        Log.i(LOG_TAG, "delete: result = " + result);
        return result;
    }

    ArrayList<String> getDbDataList() {
        ArrayList<String> dbDataList = new ArrayList<>();

        String[] columns = new String[]{"message", "date_time"};
        String sortOrder = "date_time asc"; // "필드명 오름차순"
//        String sortOrder = "date_time desc"; // "필드명 내림차순"

        Cursor cursor = mSampleDBManager.query(columns, null, null, null, null, sortOrder);
        if (cursor.moveToFirst()) {
            do {
                String message = cursor.getString(cursor.getColumnIndex("message"));
                String dateTime = cursor.getString(cursor.getColumnIndex("date_time"));

                dbDataList.add(message + " / D : " + dateTime);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return dbDataList;
    }
}
