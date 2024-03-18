package com.example.telegram.ui.home.story;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.telegram.R;
import com.example.telegram.domain.model.StoryModel;

import java.util.List;

public class Story_viewActivity extends AppCompatActivity {

    private static final float MIN_SCALE = 0.85f;
    private static final float MIN_ALPHA = 0.5f;
    List<StoryModel> list;
    StoryViewAdapter storyViewAdapter;
    ViewPager2 viewPager2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_view);
        initViews();
        list =  getIntent().getParcelableArrayListExtra("list");

        int pos  = getIntent().getIntExtra("pos", 0);
        if (list != null){
            storyViewAdapter = new StoryViewAdapter(list);
            viewPager2.setAdapter(storyViewAdapter);
            viewPager2.setCurrentItem(pos);

        }

        viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    page.setAlpha(0f);
                }else if (position <= 1){


                    float deltaY = 0.5f;
                    float pivotX = position < 0f ? page.getWidth() : 0f;
                    float pivotY = page.getHeight() * deltaY;
                    float rotationY = 15f * position;
                    float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));

                    page.setPivotX(pivotX);
                    page.setPivotY(pivotY);
                    page.setRotationY(rotationY);
                    page.setAlpha(MIN_ALPHA +
                            (scaleFactor - MIN_SCALE) /
                                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

                }else {
                    page.setAlpha(0f);

                }
            }
        });
    }

    private void initViews() {
        viewPager2 = findViewById(R.id.viewpager2);

    }
}