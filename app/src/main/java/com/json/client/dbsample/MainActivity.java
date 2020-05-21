package com.json.client.dbsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.json.client.dbsample.database.SampleDBManager;
import com.json.client.dbsample.utils.LogUtils;
import com.json.client.dbsample.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String LOG_TAG = "MainActivity: ";
    private Context mContext;
    private LogUtils mLogUtils;
    private Utils mUtils;
    private SampleDBManager mSampleDBManager;

    private EditText mMessageEditText;
    TextView mLogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        mMessageEditText = findViewById(R.id.main_message_edit_text);
        Button addButton = findViewById(R.id.main_add_button);
        Button searchButton = findViewById(R.id.main_search_button);
        Button deleteButton = findViewById(R.id.main_delete_button);
        Button clearButton = findViewById(R.id.main_clear_button);
        mLogView = findViewById(R.id.main_log_text_view);
        mLogView.setMovementMethod(new ScrollingMovementMethod());

        addButton.setOnClickListener(View -> addDbData());
        searchButton.setOnClickListener(View -> searchDbData());
        deleteButton.setOnClickListener(View -> deleteDbData());
        clearButton.setOnClickListener(View -> clearData());
        mLogUtils = new LogUtils(this, mLogView, LOG_TAG);
        mUtils = new Utils(mContext);
        mSampleDBManager = SampleDBManager.getInstance(mContext);
        mSampleDBManager.init(mContext);
    }

    private void clearData() {
        mLogView.setText("");
    }

    private void addDbData() {
        String message = mMessageEditText.getText().toString();
        if (message.isEmpty()) {
            showToast("메시지 입력 후 저장");
            return;
        }

        String dateTime = mUtils.getDateTime();
        long timestamp = System.currentTimeMillis();

        long rowId = mSampleDBManager.insert("TEST", message, dateTime, timestamp);
        mLogUtils.i("addDbData : " + message + " / dateTime : " + dateTime + " / rowId : " + rowId);
    }

    private void searchDbData() {
        ArrayList<String> dbDataList = mSampleDBManager.getDbDataList();

        for (String item : dbDataList) {
            mLogUtils.i("searchDbData : " + item);
        }
    }

    private void deleteDbData() {
        int result = mSampleDBManager.deleteAll();
        mLogUtils.i("deleteDbData : result : " + result);
    }


    private void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
