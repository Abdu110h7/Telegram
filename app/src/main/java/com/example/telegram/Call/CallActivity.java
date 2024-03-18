package com.example.telegram.Call;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.telegram.R;
import com.example.telegram.domain.model.UserData;

public class CallActivity extends AppCompatActivity {

    UserData userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        Intent intent = getIntent();
        UserData userData = (UserData) intent.getSerializableExtra("userData");
        int color = intent.getIntExtra("color", 0);

    }
}