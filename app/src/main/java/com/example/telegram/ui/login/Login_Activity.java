package com.example.telegram.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.telegram.R;
import com.example.telegram.domain.preference.UserPrefernceManager;
import com.example.telegram.ui.home.HomeActivity;

public class Login_Activity extends AppCompatActivity {
    UserPrefernceManager prefernceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefernceManager = new UserPrefernceManager(getApplicationContext());
        if (prefernceManager.getKey() == null){
            setContentView(R.layout.activity_login);
        }else {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }
}