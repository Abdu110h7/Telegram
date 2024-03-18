package com.example.telegram.Call;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.telegram.Chat.ChatActivity;
import com.example.telegram.R;
import com.example.telegram.domain.model.UserData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Call_Adapter extends RecyclerView.Adapter<Call_Adapter.CallViewHolder> {
    String[] color = {"#F1A882", "#F0DDAF", "#99BBD2", "#F8EBDC", "#E7D8C2"};
    List<UserData> list;

    public Call_Adapter(List<UserData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public CallViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_call,parent,false);
        return new CallViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CallViewHolder holder, int position) {
        holder.bind(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CallViewHolder extends RecyclerView.ViewHolder {
        TextView personName, personPhone,txtAvatar;
        CircleImageView avatar;
        public CallViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.textView2);
            personPhone = itemView.findViewById(R.id.textView3);
            txtAvatar = itemView.findViewById(R.id.textAvatar);
            avatar = itemView.findViewById(R.id.avatar);
        }
        public void bind( UserData userData, int pos){
            personPhone.setText(userData.getPhone());
            personName.setText(userData.getName());
            ShapeDrawable ovalDrawable = new ShapeDrawable(new OvalShape());
            ovalDrawable.getPaint().setColor(Color.parseColor(color[pos% color.length]));
            txtAvatar.setBackground(ovalDrawable);
            txtAvatar.setText(getUserNameLetter(userData.getName()));
            if (userData.getAvatarUrl().isEmpty()){
                txtAvatar.setVisibility(View.VISIBLE);
                avatar.setVisibility(View.GONE);
            }else {
                txtAvatar.setVisibility(View.VISIBLE);
                avatar.setVisibility(View.GONE);
                Glide.with(itemView.getContext()).load(userData.getAvatarUrl()).into(avatar);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(itemView.getContext(), CallActivity.class);
//                    intent.putExtra("userData",userData);
//                    intent.putExtra("color",color[pos% color.length]);
//                    itemView.getContext().startActivity(intent);

                    if(ContextCompat.checkSelfPermission(
                            itemView.getContext(),android.Manifest.permission.CALL_PHONE) !=
                            PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions((Activity) itemView.getContext(), new
                                String[]{android.Manifest.permission.CALL_PHONE}, 0);
                    } else {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + userData.getPhone()));
                        itemView.getContext().startActivity(intent);
                    }
                }
            });
        }

    }
    public String getUserNameLetter(String name){
        return  name.substring(0,2);
    }
}
