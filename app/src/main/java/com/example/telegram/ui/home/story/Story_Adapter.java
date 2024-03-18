package com.example.telegram.ui.home.story;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.telegram.R;
import com.example.telegram.domain.model.StoryModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Story_Adapter extends RecyclerView.Adapter<Story_Adapter.StoryViewHolder> {

    List<StoryModel> list;
    ClickStory clickStory;

    public Story_Adapter(List<StoryModel> list, ClickStory clickStory) {
        this.list = list;
        this.clickStory = clickStory;
    }

    @NonNull
    @Override
    public StoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story,parent,false);
        return new StoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryViewHolder holder, int position) {

        if (position == 0){
            holder.storyAdd.setVisibility(View.VISIBLE);
            holder.storyImage.setVisibility(View.GONE);
            holder.storyName.setText("Your Story");
        }else {
            holder.storyAdd.setVisibility(View.GONE);
            holder.storyImage.setVisibility(View.VISIBLE);
            holder.bind(list.get(position - 1));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.getAdapterPosition() == 0) {
                    clickStory.buttenAddStory();
                }else {
                     clickStory.showStory(holder.getAdapterPosition(), list);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public class StoryViewHolder extends RecyclerView.ViewHolder {
        TextView storyName;
        CircleImageView storyImage;
        FrameLayout storyFarme;
        ImageView storyAdd;
        public StoryViewHolder(@NonNull View itemView) {
            super(itemView);

            storyName = itemView.findViewById(R.id.story_name);
            storyImage = itemView.findViewById(R.id.story_image);
            storyFarme = itemView.findViewById(R.id.story_circle);
            storyAdd = itemView.findViewById(R.id.story_Add);
        }

        public void bind(StoryModel model){
            storyName.setText(model.getName());
            Glide.with(itemView.getContext()).load(model.getStoryImage()).into(storyImage);
            if (model.isSeen()){
                storyName.setAlpha(0.6f);
                storyFarme.setAlpha(0.6f);
            }else {
                storyName.setAlpha(1f);
                storyFarme.setAlpha(1f);
            }
        }
    }
}
