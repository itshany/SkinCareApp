package com.example.skincareapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {
    ViewPager viewPager;
    SurveyPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        // Get the ViewPager from the layout
        viewPager = findViewById(R.id.viewPager);

        // Create a list of survey questions
        List<QuestionAnswer> questionList = createQuestionList();

        // Create the adapter and set it to the ViewPager
        pagerAdapter = new SurveyPagerAdapter(questionList, this, viewPager);
        viewPager.setAdapter(pagerAdapter);
    }

    private List<QuestionAnswer> createQuestionList() {
        // Create and return a list of Question objects
        List<QuestionAnswer> questionList = new ArrayList<>();

        // Add your survey questions and answer options here
        questionList.add(new QuestionAnswer("What's your skin type?",
                Arrays.asList("Dry", "Normal", "Combination", "Oily")));
        questionList.add(new QuestionAnswer("How would you rate your skin's sensitivity in the scale of 1-3?",
                Arrays.asList("1", "2", "3")));
        questionList.add(new QuestionAnswer("What's your skin concern?",
                Arrays.asList("Acne", "Uneven skin tone", "Wrinkles")));
        questionList.add(new QuestionAnswer("How much time do you have in the morning for a skincare routine?",
                Arrays.asList("I Don't have time at all", "5 minutes", "10 minutes", "I have plenty of time")));
        questionList.add(new QuestionAnswer("How much time do you have in the evening for a skincare routine?",
                Arrays.asList("I Don't have time at all", "5 minutes", "10 minutes", "I have plenty of time")));

        return questionList;
    }
}
