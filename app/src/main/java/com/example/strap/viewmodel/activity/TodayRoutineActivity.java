package com.example.strap.viewmodel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.strap.R;
import com.example.strap.viewmodel.fragment.HomeFragment;

public class TodayRoutineActivity extends AppCompatActivity {
    TextView title;
    Button detail;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_routine);

        title = findViewById(R.id.title3);
        detail = findViewById(R.id.button5);
        register = findViewById(R.id.button6);

        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("routineTitle", title.getText().toString());
                setResult(RESULT_OK, intent);
                Toast.makeText(getApplicationContext(), "나의 루틴에 등록되었습니다.", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}