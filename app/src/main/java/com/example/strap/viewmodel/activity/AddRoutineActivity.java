package com.example.strap.viewmodel.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strap.R;

import java.util.ArrayList;

public class AddRoutineActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;
    public static RoutineAdapter adapter;
    EditText routineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_routine);

        Button saveRoutine = findViewById(R.id.button_save);
        routineName = findViewById(R.id.routine_title);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        adapter = new RoutineAdapter();

        adapter.setOnItemClickListener(new RoutineAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), AddWorkoutActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });

        adapter.setItems(new ArrayList<AdapterType>(10));
        adapter.addItem(new AddButton());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);



        saveRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.getItemCount() == 1) {
                    Toast.makeText(getApplicationContext(), "등록된 운동이 없습니다.", Toast.LENGTH_LONG)
                            .show();
                }
                else {
                    Intent intent = new Intent();
                    String name;
                    if (routineName.getText().toString().equals("")) {
                        name = "나의 루틴";
                    }
                    else {
                        name = routineName.getText().toString();
                    }
                    intent.putExtra("name", name);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_MENU) {
            if (resultCode == RESULT_OK) {
                String routineName = data.getStringExtra("name");
                int weight = Integer.parseInt(data.getStringExtra("weight"));
                int count = Integer.parseInt(data.getStringExtra("count"));
                int set = Integer.parseInt(data.getStringExtra("set"));
                adapter.addItem(new Routine(routineName, weight, count, set));
                adapter.notifyDataSetChanged();
            }
        }
    }
}