package com.example.strap.viewmodel.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.strap.R;
import com.example.strap.viewmodel.activity.FragmentAdapter;
import com.example.strap.viewmodel.activity.NowExercisingActivity;
import com.example.strap.viewmodel.activity.Record;
import com.example.strap.viewmodel.activity.StartExerciseAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class ExerciseFragment extends Fragment {
    private FragmentAdapter fragmentAdapter;
    private TextView time;
    private Button start, end;

    public static int count = 1;

    public static final int INIT = 0;
    public static final int RUN = 1;
    public static final int STOP = 2;

    public static int status = INIT;

    //타이머 시간 값을 저장할 변수
    private long baseTime, stopTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_exercise,
                                                            container, false);

        time = rootView.findViewById(R.id.timer);
        start = rootView.findViewById(R.id.button_start);
        end = rootView.findViewById(R.id.button_stop);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView_for_record);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        fragmentAdapter = new FragmentAdapter();
        fragmentAdapter.setRecords(new ArrayList<Record>());

        recyclerView.setAdapter(fragmentAdapter);

        start.setOnClickListener(onClickListener);
        end.setOnClickListener(onClickListener);

        return rootView;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_start:
                    startButton();
                    break;

                case R.id.button_stop:
                    endButton();
            }
        }
    };

    private void startButton() {
        switch (status) {
            case INIT:
                baseTime = SystemClock.elapsedRealtime();
                handler.sendEmptyMessage(0);
                status = RUN;
                break;

            case STOP:
                long reStart = SystemClock.elapsedRealtime();
                baseTime += (reStart - stopTime);

                handler.sendEmptyMessage(0);
                end.setText("stop");

                status = RUN;
        }
    }

    private void endButton() {
        switch (status) {
            case RUN:
                handler.removeMessages(0);
                stopTime = SystemClock.elapsedRealtime();
                start.setText("resume");
                end.setText("reset");
                status = STOP;
                break;

            case STOP:
                handler.removeMessages(0);
                fragmentAdapter.addRecord(new Record(count, time.getText().toString()));
                fragmentAdapter.notifyDataSetChanged();
                end.setText("stop");
                start.setText("start");
                time.setText("00 : 00 . 00");
                count++;
                status = INIT;
        }
    }

    private String getTime() {
        long nowTime = SystemClock.elapsedRealtime();
        long overTime = nowTime - baseTime;

        long m = overTime/1000/60;
        long s = (overTime/1000)%60;
        long ms = overTime%100;

        String recTime = String.format("%02d : %02d . %02d", m, s, ms);
        return recTime;
    }

    Handler handler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(@NonNull Message msg) {
            time.setText(getTime());

            handler.sendEmptyMessage(0);
        }
    };
}