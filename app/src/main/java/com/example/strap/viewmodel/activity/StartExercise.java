package com.example.strap.viewmodel.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.strap.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StartExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exercise);

        TextView routineName = findViewById(R.id.selected_routine_name);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_for_start_routine);
        routineName.setText(getIntent().getStringExtra("routineName"));

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        StartExerciseAdapter adapter = new StartExerciseAdapter();

        ArrayList<Routine> list = new ArrayList<>();
        for (int i=0; i < RoutineAdapter.items.size()-1; i++) {
            list.add((Routine)RoutineAdapter.items.get(i));
        }

        adapter.setExercises(list);
        recyclerView.setAdapter(adapter);
    }
}