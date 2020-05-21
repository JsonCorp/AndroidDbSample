package com.json.client.dbsample.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SampleDBProvider extends ContentProvider {
    private SampleDBManager mSampleDBManager;
    private Context mContext;
    @Override
    public boolean onCreate() {
        mContext = getContext();
        mSampleDBManager = SampleDBManager.getInstance(mContext);

        return mSampleDBManager != null;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return mSampleDBManager.query(projection,selection,selectionArgs,null, null,sortOrder);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowID = mSampleDBManager.insert(values);
        if (rowID > 0) {
            Uri successUri = ContentUris.withAppendedId(uri, rowID);
            if (mContext != null) {
                mContext.getContentResolver().notifyChange(successUri, null);
            }
            return successUri;
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return mSampleDBManager.delete(selection,selectionArgs);
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return mSampleDBManager.update(values,selection,selectionArgs);
    }
}
