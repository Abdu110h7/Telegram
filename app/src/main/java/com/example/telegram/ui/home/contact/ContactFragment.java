package com.example.telegram.ui.home.contact;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.telegram.R;
import com.example.telegram.domain.model.UserData;
import com.example.telegram.domain.preference.UserPrefernceManager;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {
    RecyclerView contactRecyclerView;
    ContacAdapter contacAdapter;
    DatabaseReference reference;
    List<UserData> list;
    UserPrefernceManager prefernceManager;
    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false);
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

                contacAdapter = new ContacAdapter(list);
                contactRecyclerView.setAdapter(contacAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initViews(View view) {
        contactRecyclerView = view.findViewById(R.id.contactReci);

    }
}