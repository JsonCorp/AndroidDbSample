package com.json.client.dbsample.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class LogUtils {
    private Activity mActivity;
    private TextView mTextView;
    private String TAG;
    private int count = 0;

    public LogUtils(Activity activity, TextView view, String tag) {
        mActivity = activity;
        mTextView = view;
        TAG = tag;
    }

    public void i(final String text) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.append(Integer.toString(count++));
                mTextView.append(":");
                mTextView.append(text);
                mTextView.append("\n\n");
                mTextView.invalidate();
            }
        });
        Log.i(TAG, text);
    }

    public void e(final String text) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.append(Integer.toString(count++));
                mTextView.append(":");
                mTextView.append(text);
                mTextView.append("\n\n");
                mTextView.invalidate();
            }
        });

        Log.e(TAG, text);
    }

    public void e(String text, Exception e) {
        Log.e(TAG, text + e.toString());
    }
}
