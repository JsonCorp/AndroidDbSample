package com.json.client.dbsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.json.client.dbsample.database.SampleDBUtils;
import com.json.client.dbsample.utils.LogUtils;
import com.json.client.dbsample.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private String LOG_TAG = "MainActivity: ";
    private Context mContext;
    private LogUtils mLogUtils;
    private Utils mUtils;
    private SampleDBUtils mDbUtils;

    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = getApplicationContext();

        mMessageEditText = findViewById(R.id.main_message_edit_text);
        Button addButton = findViewById(R.id.main_add_button);
        Button searchButton = findViewById(R.id.main_search_button);
        Button deleteButton = findViewById(R.id.main_delete_button);
        TextView logView = findViewById(R.id.main_log_text_view);
        logView.setMovementMethod(new ScrollingMovementMethod());

        addButton.setOnClickListener(View -> addDbData());
        searchButton.setOnClickListener(View -> searchDbData());
        deleteButton.setOnClickListener(View -> deleteDbData());

        mLogUtils = new LogUtils(this, logView, LOG_TAG);
        mUtils = new Utils(mContext);
        mDbUtils = new SampleDBUtils(mContext);
    }

    private void addDbData() {
        String message = mMessageEditText.getText().toString();
        if (message.isEmpty()) {
            showToast("메시지 입력 후 저장");
            return;
        }

        mDbUtils.insert("TEST", message, mUtils.getDateTime());
        mLogUtils.i("addDbData : " + message);
    }

    private void searchDbData() {

    }

    private void deleteDbData() {

    }


    private void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }
}
