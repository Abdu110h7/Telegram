package com.example.telegram.Chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.telegram.BaseActivity;
import com.example.telegram.R;
import com.example.telegram.domain.model.Chat_Model;
import com.example.telegram.domain.model.UserData;
import com.example.telegram.domain.preference.UserPrefernceManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends BaseActivity implements TextWatcher {

    TextView personName;
    UserData data;
    CircleImageView avatar;
    ImageView buttenMicrophone, buttenSend;
    TextView txtAvatar;
    RecyclerView chatRecyclerView;
    UserPrefernceManager prefernceManager;
    EditText typingEdit;
    DatabaseReference ChatReference,  lastRefrence;
    ChatAdapter chatAdapter ;
    String frienndKey,myKey, chatKey;
    List<Chat_Model> chatModelList;
    TextView people_name;
    DatabaseReference userRefernece;
    TextView lastTimeTxt;
    String[] color = {"#F1A882", "#F0DDAF", "#99BBD2", "#F8EBDC", "#E7D8C2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initiViews();
        data = getIntent().getParcelableExtra("userData");
        prefernceManager = new UserPrefernceManager(this);
        ChatReference = FirebaseDatabase.getInstance().getReference("chats");
        userRefernece = FirebaseDatabase.getInstance().getReference("users");
        lastRefrence  = FirebaseDatabase.getInstance().getReference("lastMessages");
        setPersonData(data);
        frienndKey = data.getKey();
        myKey = prefernceManager.getKey();
        userRefernece.child(frienndKey).child("lastTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String time = snapshot.getValue(String.class);
                if (time.equals("online")){
                    lastTimeTxt.setText("online");
                }else {
                    lastTimeTxt.setText(getLastTime(time));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (frienndKey.compareTo(myKey) > 0){
            chatKey = frienndKey + myKey;
        }else {
            chatKey = myKey + frienndKey;
        }
        ChatReference.child(chatKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatModelList = new ArrayList<>();
                for (DataSnapshot ds :snapshot.getChildren()){
                    Chat_Model model = ds .getValue(Chat_Model.class);
                    chatModelList.add(model);
                }
                chatRecyclerView.setAdapter(new ChatAdapter(chatModelList,prefernceManager.getKey()));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        buttenSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String messageKey = ChatReference.push().getKey();
        String  message = typingEdit.getText().toString();
        if (message.isEmpty()){
            return;
        }
        Chat_Model chatModel = new Chat_Model(chatKey, messageKey,message,myKey,frienndKey,getTime(), false);
        ChatReference.child(chatKey).child(messageKey).setValue(chatModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    lastRefrence.child(chatKey).setValue(chatModel);
                    typingEdit.setText("");

                }
            }
        });
    }

    private void setPersonData(UserData data) {
        personName.setText(data.getName());
        typingEdit.addTextChangedListener(this);
        ShapeDrawable ovalDrawable = new ShapeDrawable(new OvalShape());
        String color = getIntent().getStringExtra("color");
        ovalDrawable.getPaint().setColor(Color.parseColor(color));
        txtAvatar.setBackground(ovalDrawable);
        txtAvatar.setText(getUserNameLetter(data.getName()));
        if (data.getAvatarUrl().isEmpty()){
            txtAvatar.setVisibility(View.VISIBLE);
            avatar.setVisibility(View.GONE);
        }else {
            txtAvatar.setVisibility(View.VISIBLE);
            avatar.setVisibility(View.GONE);
            Glide.with(this).load(data.getAvatarUrl()).into(avatar);
        }
    }


    private void initiViews() {
        personName = findViewById(R.id.person_Name);
        avatar = findViewById(R.id.circleImageView2);
        txtAvatar = findViewById(R.id.txtAvatar);
        buttenMicrophone = findViewById(R.id.buttenMicrofon);
        buttenSend = findViewById(R.id.butten_send);
        typingEdit = findViewById(R.id.typingEdit);
        chatRecyclerView = findViewById(R.id.recyclerView);
        people_name = findViewById(R.id.people_name);
        lastTimeTxt = findViewById(R.id.lastTime);


    }
    public String getUserNameLetter(String name){
        return  name.substring(0,2);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().length() > 0){
            buttenMicrophone.setVisibility(View.GONE);
            buttenSend.setVisibility(View.VISIBLE);
        }else {
            buttenMicrophone.setVisibility(View.VISIBLE);
            buttenSend.setVisibility(View.GONE);
        }
    }

    public String getTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());
        String currentDateandTime = sdf.format(new Date());
        return currentDateandTime;
    }
    public String getLastTime(String time){
        String[] a = time.split("_");
        return a[1].substring(0,5);
    }
}