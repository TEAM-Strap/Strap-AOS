package com.example.strap.viewmodel.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.strap.R;
import com.example.strap.viewmodel.fragment.ExerciseFragment;

import java.lang.reflect.Array;
import java.util.Arrays;

public class NowExercisingActivity extends AppCompatActivity {
    private ExerciseFragment fragment;
    private TextView name;
    private TextView weight;
    private TextView count;
    private TextView set;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_exercising);

        fragment = (ExerciseFragment) getSupportFragmentManager().findFragmentById(R.id.exercise_fragment);
        name = fragment.getViewName();
        weight = fragment.getViewWeight();
        count = fragment.getViewCount();
        set = fragment.getViewSet();

        back = findViewById(R.id.button_back);

        name.setText(getIntent().getStringExtra("name"));
        weight.setText(getIntent().getStringExtra("weight"));
        count.setText(getIntent().getStringExtra("count"));
        set.setText(getIntent().getStringExtra("set"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("totalTime", fragment.getTotalTime()+"");
                setResult(RESULT_OK, intent);
                fragment.setTotalTime(0);
                finish();
            }
        });
    }
}