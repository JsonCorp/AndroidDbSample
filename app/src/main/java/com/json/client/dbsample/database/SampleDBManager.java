package com.json.client.dbsample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SampleDBManager extends SQLiteOpenHelper {
    private String LOG_TAG = "SampleDBManager: ";
    static final String DB_NAME = "sample_data_db";
    static final String DB_TABLE_SAMPLE_DATA = "sample_data";
    static final int DB_VERSION = 1;
    static SampleDBUtils mSampleDBUtils;

    private static SampleDBManager mSampleDBManager = null;

    private SampleDBManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static SampleDBManager getInstance(Context context) {
        if (mSampleDBManager == null) {
            mSampleDBManager = new SampleDBManager(context, DB_NAME, null, DB_VERSION);
        }

        return mSampleDBManager;
    }

    public void init(Context context) {
        if (mSampleDBUtils == null) {
            mSampleDBUtils = new SampleDBUtils(context);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DB_TABLE_SAMPLE_DATA + "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login_id TEXT," +
                "message TEXT," +
                "date_time TEXT," +
                "timestamp Long);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:

                break;
        }
    }

    long insert(ContentValues addRowValue) {
        return getWritableDatabase().insert(DB_TABLE_SAMPLE_DATA, null, addRowValue);
    }

    public long insert(String loginId, String message, String dateTime, long timestamp) {
        return mSampleDBUtils.insert(loginId, message, dateTime, timestamp);
    }

    int insertAll(ContentValues[] values) {
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        for (ContentValues contentValues : values) {
            db.insert(DB_TABLE_SAMPLE_DATA, null, contentValues);
        }

        db.setTransactionSuccessful();
        db.endTransaction();

        return values.length;
    }

    Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        return getReadableDatabase().query(DB_TABLE_SAMPLE_DATA, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    int update(ContentValues updateRowValue, String whereClause, String[] whereArgs) {
        return getWritableDatabase().update(DB_TABLE_SAMPLE_DATA, updateRowValue, whereClause, whereArgs);
    }

    int delete(String whereClause, String[] whereArgs) {
        return getWritableDatabase().delete(DB_TABLE_SAMPLE_DATA, whereClause, whereArgs);
    }

    public int deleteAll() {
        return mSampleDBUtils.deleteAll();
    }

    public ArrayList<String> getDbDataList() {
        return mSampleDBUtils.getDbDataList();
    }
}
