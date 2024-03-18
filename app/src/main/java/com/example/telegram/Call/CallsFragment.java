package com.example.telegram.Call;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.telegram.Call.CallActivity;
import com.example.telegram.R;
import com.example.telegram.domain.model.UserData;
import com.example.telegram.domain.preference.UserPrefernceManager;
import com.example.telegram.ui.home.contact.ContacAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CallsFragment extends Fragment {
    DatabaseReference reference;
    List<UserData> list;
    UserPrefernceManager prefernceManager;
    Call_Adapter call_adapter;
    RecyclerView postReci;
    public CallsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calls, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        prefernceManager = new UserPrefernceManager(requireContext());
        reference = FirebaseDatabase.getInstance().getReference("users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot ds: snapshot.getChildren()){
                    UserData data = ds. getValue(UserData.class);
                    if (data != null && !data.getKey().equals(prefernceManager.getKey())) {
                        list.add(data);

                    }
                }

                call_adapter = new Call_Adapter(list);
                postReci.setAdapter(call_adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initViews(View view) {
        postReci  = view.findViewById(R.id.postreci);
    }

}