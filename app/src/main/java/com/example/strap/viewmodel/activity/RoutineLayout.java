package com.example.strap.viewmodel.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.strap.R;

public class RoutineLayout extends LinearLayout {
    TextView name;
    TextView weight;
    TextView count;
    TextView set;

    public RoutineLayout(Context context) {
        super(context);
        init(context);
    }

    public RoutineLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.workout_info_layout, this, true);

        name = findViewById(R.id.name);
        weight = findViewById(R.id.weight);
        count = findViewById(R.id.count);
        set = findViewById(R.id.set);
    }

    public void setName(String newName) {
        name.setText(newName);
    }

    public void setWeight(int newWeight) {
        weight.setText(newWeight+"");
    }

    public void setCount(int newCount) {
        count.setText(newCount+"");
    }

    public void setSet(int newSet) {
        set.setText(newSet+"");
    }
}
