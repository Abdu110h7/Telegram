package com.example.telegram.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.telegram.R;
import com.example.telegram.domain.model.Post_Model;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    List<Post_Model> list;

    public PostAdapter(List<Post_Model> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        // holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView personName;
        TextView time;
        CircleImageView avatar;
        TextView lastMessage;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.name);
            avatar = itemView.findViewById(R.id.circleImageView);
            time = itemView.findViewById(R.id.textView);
            lastMessage = itemView.findViewById(R.id.lastMessage);
        }
    }
}
