package com.example.telegram.ui.home.message;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.telegram.Chat.ChatActivity;
import com.example.telegram.R;
import com.example.telegram.domain.model.Chat_Model;
import com.example.telegram.domain.model.LastMessageModel;
import com.example.telegram.domain.model.UserData;
import com.example.telegram.domain.preference.UserPrefernceManager;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LastMessageAdapter extends RecyclerView.Adapter<LastMessageAdapter.LastViewHolder> {

    String[] color = {"#F1A882", "#F0DDAF", "#99BBD2", "#F8EBDC", "#E7D8C2"};

    List<LastMessageModel> list;
    int countMessage = 0;
    public LastMessageAdapter(List<LastMessageModel> list, int count) {
        this.list = list;
        countMessage = count;
    }

    @NonNull
    @Override
    public LastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
       return new LastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastViewHolder holder, int position) {
        holder.bind(list.get(position), position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LastViewHolder extends RecyclerView.ViewHolder {

        TextView personName, txtAvatar, messageCount, phoneNumber;
        CircleImageView avatar;

        public LastViewHolder(@NonNull View itemView) {
            super(itemView);

            personName = itemView.findViewById(R.id.textView5);
            txtAvatar = itemView.findViewById(R.id.txtAvatar);
            avatar = itemView.findViewById(R.id.circleImageView2);
            messageCount = itemView.findViewById(R.id.counttext);
            phoneNumber = itemView.findViewById(R.id.phome_Number);
        }


        public void bind(LastMessageModel userData, int pos){
            if (countMessage == 0){
                messageCount.setVisibility(View.GONE);
            }else {
                messageCount.setVisibility(View.VISIBLE);
                messageCount.setText(String.valueOf(countMessage));
            }
            personName.setText(userData.getName());
            ShapeDrawable ovalDrawable = new ShapeDrawable(new OvalShape());
            ovalDrawable.getPaint().setColor(Color.parseColor(color[pos% color.length]));
            txtAvatar.setBackground(ovalDrawable);
            txtAvatar.setText(getUserNameLetter(userData.getName()));
            phoneNumber.setText(userData.getMessage());
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
                    Intent intent = new Intent(itemView.getContext(), ChatActivity.class);
//                    intent.putExtra("userData",userData);
                    intent.putExtra("color",color[pos% color.length]);
                    itemView.getContext().startActivity(intent);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), ChatActivity.class);
                    intent.putExtra("userData",list.get(pos).getData());
                    intent.putExtra("color",color[pos% color.length]);
                    itemView.getContext().startActivity(intent);
                }
            });
        }

    }

    public String getUserNameLetter(String name){
        return  name.substring(0,2);
    }

}
