package com.example.strap.viewmodel.activity;

import androidx.annotation.Nullable;
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
import com.example.strap.viewmodel.fragment.ExerciseFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class StartExercise extends AppCompatActivity {
    public static final int REQUEST_CODE_FOR_RECORD = 102;

    private TextView timeViewer;
    private long totalExercisingTime;

    public static int pos;
    public static int[] sets;

    public static int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exercise);

        timeViewer = findViewById(R.id.time_of_screen);
        TextView routineName = findViewById(R.id.selected_routine_name);
        RecyclerView recyclerView = findViewById(R.id.recyclerView_for_start_routine);
        routineName.setText(getIntent().getStringExtra("routineName"));

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        StartExerciseAdapter adapter = new StartExerciseAdapter();

        adapter.setOnItemClickListener(new StartExerciseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position, Intent intent) {
                startActivityForResult(intent, REQUEST_CODE_FOR_RECORD);
            }
        });

        ArrayList<Routine> list = new ArrayList<>();
        for (int i=0; i < RoutineAdapter.items.size()-1; i++) {
            list.add((Routine)RoutineAdapter.items.get(i));
        }

        adapter.setExercises(list);

        sets = new int[adapter.getItemCount()];
        Arrays.fill(sets, 1);

        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_FOR_RECORD) {
            if (resultCode == RESULT_OK) {
                long totalTime = Long.valueOf(data.getStringExtra("totalTime"));
                timeViewer.setText(convertingTime(totalTime));
            }
        }
    }

    private String convertingTime(long time) {
        totalExercisingTime += time;

        long h = (totalExercisingTime/1000)/60/60;
        long m = (totalExercisingTime/1000)/60;
        long s = (totalExercisingTime/1000) % 60;

        String convertedTime = String.format("%02d : %02d : %02d", h, m, s);
        return convertedTime;
    }
}