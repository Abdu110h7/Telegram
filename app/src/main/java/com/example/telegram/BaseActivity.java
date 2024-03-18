package com.example.telegram;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.telegram.domain.preference.UserPrefernceManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class BaseActivity extends AppCompatActivity {

    DatabaseReference reference;
    UserPrefernceManager prefernceManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefernceManager = new UserPrefernceManager(this);
        reference = FirebaseDatabase.getInstance().getReference("users").child(prefernceManager.getKey());
    }

    @Override
    protected void onStart() {
        super.onStart();
        reference.child("lastTime").setValue("online");
    }

    @Override
    protected void onResume() {
        super.onResume();
        reference.child("lastTime").setValue("online");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        reference.child("lastTime").setValue(getTime());
    }

    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }
}
