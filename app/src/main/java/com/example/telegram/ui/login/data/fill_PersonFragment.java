package com.example.telegram.ui.login.data;

import android.accounts.AbstractAccountAuthenticator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.FlowExtKt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.telegram.R;
import com.example.telegram.domain.model.UserData;
import com.example.telegram.domain.preference.UserPrefernceManager;
import com.example.telegram.ui.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class fill_PersonFragment extends Fragment {

    TextInputLayout perspnNameEditText;
    FloatingActionButton buttenConfirm;
    FirebaseDatabase database;
    DatabaseReference reference;
    UserPrefernceManager prefernceManager;

    public fill_PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fill__person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        prefernceManager = new UserPrefernceManager(requireContext());
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("users");

        buttenConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = perspnNameEditText.getEditText().getText().toString();
                if (name.isEmpty()){
                    return;
                }
                String phone  = prefernceManager.getPhone();
                String key = reference.push().getKey();

                UserData data = new UserData(name, key, getTime(),"", phone, "online", "");
                writeDtabase(data);
            }
        });
    }

    private void writeDtabase(UserData data) {
        reference.child(data.getKey()).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    prefernceManager.saveKey(data.getKey());
                    prefernceManager.saveKey(data.getKey());
                    goHome();
                }
            }
        });
    }

    private void initViews(View view) {
        perspnNameEditText = view.findViewById(R.id.ism);
        buttenConfirm = view.findViewById(R.id.butten);
    }
    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
        
    }

    private void goHome() {
        Intent intent = new Intent(requireContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}