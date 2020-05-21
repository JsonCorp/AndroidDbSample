package com.json.client.dbsample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SampleDBManager extends SQLiteOpenHelper {
    static final String DB_NAME = "sample_data_db";
    static final String DB_TABLE_SAMPLE_DATA = "sample_data";
    static final int DB_VERSION = 1;

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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DB_TABLE_SAMPLE_DATA + "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "login_id TEXT,"+
                "message TEXT," +
                "data_time TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch(oldVersion) {
            case 1:

                break;
        }
    }

    public long insert(ContentValues addRowValue){
        return getWritableDatabase().insert(DB_TABLE_SAMPLE_DATA,null,addRowValue);
    }

    public int insertAll(ContentValues[] values){
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();

        for(ContentValues contentValues : values){
            db.insert(DB_TABLE_SAMPLE_DATA,null,contentValues);
        }

        db.setTransactionSuccessful();
        db.endTransaction();

        return values.length;
    }

    public Cursor query(String [] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return getReadableDatabase().query(DB_TABLE_SAMPLE_DATA,columns,selection,selectionArgs,groupBy,having,orderBy);
    }

    public int update(ContentValues updateRowValue,String whereClause, String[] whereArgs){
        return getWritableDatabase().update(DB_TABLE_SAMPLE_DATA,updateRowValue,whereClause,whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs){
        return getWritableDatabase().delete(DB_TABLE_SAMPLE_DATA,whereClause,whereArgs);
    }
}
