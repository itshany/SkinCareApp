package com.example.skincareapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SurveyPagerAdapter extends PagerAdapter {
    List<QuestionAnswer> questionList;
    Context context;
    LayoutInflater inflater;
    List<String> userResponses;
    ViewPager viewPager;

    public SurveyPagerAdapter(List<QuestionAnswer> questionList, Context context, ViewPager viewPager) {
        this.questionList = questionList;
        this.context = context;
        this.viewPager = viewPager;
        inflater = LayoutInflater.from(context);
        userResponses = new ArrayList<>(Collections.nCopies(questionList.size(),""));
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_survey, container, false);
        QuestionAnswer question = questionList.get(position);

        TextView questionTextView = view.findViewById(R.id.questionTextView);
        questionTextView.setText(question.getQuestion());

        RadioGroup answerRadioGroup = view.findViewById(R.id.answerRadioGroup);
        for (String answer : question.getAnswers()) {
            RadioButton radioButton = new RadioButton(context);
            radioButton.setText(answer);
            answerRadioGroup.addView(radioButton);
        }

        answerRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton radioButton = radioGroup.findViewById(checkedId);
                if (radioButton != null) {
                    userResponses.set(position, radioButton.getText().toString());
                }
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if (currentUser != null) {
                    String userEmailAddress = currentUser.getEmail();
                    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
                    DatabaseReference userResponsesRef = FirebaseDatabase.getInstance().getReference()
                            .child("Users").child(encodeEmail(userEmailAddress)).child("responses");
                    userResponsesRef.setValue(userResponses);
                }
                // Move to the next question
                if (position < questionList.size() - 1) {
                    viewPager.setCurrentItem(position + 1);
                }
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public List<String> getUserResponses() {
        return userResponses;
    }
    public String encodeEmail(String email) {
        return email.replace(".", ",");
    }
}
