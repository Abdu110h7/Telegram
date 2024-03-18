package com.example.telegram.ui.home.story;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.telegram.R;
import com.example.telegram.domain.model.StoryModel;

import java.util.List;

public class StoryViewAdapter extends RecyclerView.Adapter<StoryViewAdapter.StoryViewHolder> {

    List<StoryModel> list;

    public StoryViewAdapter(List<StoryModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story_view,parent,false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(list.get(position).getStoryImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mainImage);
        }
    }
}
