package com.example.telegram;

import android.app.Application;

import com.example.telegram.domain.preference.UserPrefernceManager;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }
    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }
}
