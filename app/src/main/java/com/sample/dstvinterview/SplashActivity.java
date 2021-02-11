package com.sample.dstvinterview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.sample.dstvinterview.ui.login.LoginActivity;
import com.sample.dstvinterview.util.Constants;
import com.sample.dstvinterview.util.SharedPreferenceManagers;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferenceManagers sharedPreferenceManagers = new SharedPreferenceManagers(getApplicationContext());
        Intent intent;
        if (TextUtils.isEmpty(sharedPreferenceManagers.getString(Constants.USER_ID_KEY))) {
            intent = new Intent(this, LoginActivity.class);

        } else  {
            intent = new Intent(this, HomeActivity.class);
        }
        startActivity(intent);
    }
}