package com.example.bear.programmerdaily.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bear.programmerdaily.R;

public class AddAppItemActivity extends Activity {
    private EditText addAppName;
    private EditText addAppKey;
    private EditText addAppDeveloper;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_app_info_act);
        addAppName = findViewById(R.id.add_app_name);
        addAppKey = findViewById(R.id.add_app_key);
        addAppDeveloper = findViewById(R.id.add_app_developer);
    }

    public void save(View view) {
        String appName = addAppName.getText().toString();
        String appKey = addAppKey.getText().toString();
        String appDeveloper = addAppDeveloper.getText().toString();
        if (TextUtils.isEmpty(appName)) {
            Toast.makeText(this,"请输入名称！",Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(appKey)) {
            Toast.makeText(this,"请输入关键字！",Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(appDeveloper)) {
            Toast.makeText(this,"请输入开发者名称！",Toast.LENGTH_SHORT).show();
        } else {
            Intent replyIntent = new Intent();
            replyIntent.putExtra("appName",appName);
            replyIntent.putExtra("appKey",appKey);
            replyIntent.putExtra("appDeveloper",appDeveloper);
            setResult(RESULT_OK,replyIntent);
            finish();
        }
    }
}
