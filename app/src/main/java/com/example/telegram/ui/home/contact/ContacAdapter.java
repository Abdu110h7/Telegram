package com.example.telegram.ui.home.contact;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.telegram.Chat.ChatActivity;
import com.example.telegram.R;
import com.example.telegram.domain.model.UserData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContacAdapter extends RecyclerView.Adapter<ContacAdapter.ContactViewHolder> {
    String[] color = {"#F1A882", "#F0DDAF", "#99BBD2", "#F8EBDC", "#E7D8C2"};
    List<UserData> list;

    public ContacAdapter(List<UserData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.bind(list.get(position), position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ContactViewHolder extends  RecyclerView.ViewHolder {
        TextView personName, personPhone,txtAvatar;
        CircleImageView avatar;
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.textView5);
            personPhone = itemView.findViewById(R.id.phome_Number);
            txtAvatar = itemView.findViewById(R.id.txtAvatar);
            avatar = itemView.findViewById(R.id.circleImageView2);
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
                    Intent intent = new Intent(itemView.getContext(), ChatActivity.class);
                    intent.putExtra("userData",userData);
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
