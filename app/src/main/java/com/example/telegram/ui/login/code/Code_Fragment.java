package com.example.telegram.ui.login.code;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.Telephony;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.telegram.R;
import com.example.telegram.ui.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;


public class Code_Fragment extends Fragment {
    PinView pinView;
    MaterialButton buttenConfirm;
    FirebaseAuth mAuth;
    String verificationId;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    public Code_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_code_, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        mAuth =FirebaseAuth.getInstance();

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                Log.d("tekshirish","onVerificationCompleted" + phoneAuthCredential);
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }
            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.w("tekshirish","OnVerificationFailed", e);
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
                Log.d("tekshirish", "onCodeSent:" + s);
            }
        };

        String phone = getArguments().getString("phone");
        Log.d("tekshirish", "onViewCreated: "+phone);
        if (!phone.isEmpty()){
            PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                    .setPhoneNumber(phone)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(requireActivity())
                    .setCallbacks(mCallbacks)
                    .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
        }

        buttenConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String code = pinView.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void initViews(View view) {
        pinView = view.findViewById(
                R.id.pinView
        );
        buttenConfirm = view.findViewById(R.id.buttonConfirm);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = task.getResult().getUser();

                            String uid = user.getUid();
                            checkUserIsExist(uid);

                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("tekshirish", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }

    private void checkUserIsExist(String uId) {
       DatabaseReference reference =  FirebaseDatabase.getInstance().getReference("users");
       reference.child(uId).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               if (snapshot.exists()){
                   goHome();
               }else {
                   goRegister();
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
    }

    private void goRegister() {
        NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
        navController.navigate(R.id.fill_PersonFragment);
    }

    private void goHome() {
        Intent intent = new Intent(requireContext(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}