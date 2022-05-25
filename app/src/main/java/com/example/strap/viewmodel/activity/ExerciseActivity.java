package com.example.strap.viewmodel.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.strap.R;

public class ExerciseActivity extends AppCompatActivity {
    private TextView time;
    private Button start, end;

    public static final int INIT = 0;
    public static final int RUN = 1;
    public static final int STOP = 2;

    public static int status = INIT;

    //타이머 시간 값을 저장할 변수
    private long baseTime, stopTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        time = findViewById(R.id.time);
        start = findViewById(R.id.button_start);
        end = findViewById(R.id.button_end);

        start.setOnClickListener(onClickListener);
        end.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_start:
                    startButton();
                    break;

                case R.id.button_end:
                    endButton();
            }
        }
    };

    private void startButton() {
        switch (status) {
            case INIT:
                baseTime = SystemClock.elapsedRealtime();

                handler.sendEmptyMessage(0);
                start.setText("정지");

                status = RUN;
                break;

            case RUN:
                handler.removeMessages(0);
                stopTime = SystemClock.elapsedRealtime();

                start.setText("계속");

                status = STOP;
                break;

            case STOP:
                long restart = SystemClock.elapsedRealtime();
                baseTime += (restart - stopTime);

                handler.sendEmptyMessage(0);

                start.setText("정지");

                status = RUN;
        }
    }

    private void endButton() {
        finish();
    }

    private String getTime() {
        long nowTime = SystemClock.elapsedRealtime();
        long overTime = nowTime - baseTime;

        long m = overTime/1000/60;
        long s = (overTime/1000)%60;
        long ms = overTime % 100;

        String recTime = String.format("%02d : %02d . %02d", m, s, ms);
        return recTime;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            time.setText(getTime());

            handler.sendEmptyMessage(0);
        }
    };
}