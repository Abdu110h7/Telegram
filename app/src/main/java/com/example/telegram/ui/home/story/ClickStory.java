package com.example.telegram.ui.home.story;

import com.example.telegram.domain.model.StoryModel;

import java.util.List;

public interface ClickStory {
    void buttenAddStory();
    void showStory(int position, List<StoryModel> list);
}
