package com.example.strap.viewmodel.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strap.R;
import com.example.strap.viewmodel.fragment.ExerciseFragment;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class FragmentAdapter extends RecyclerView.Adapter<FragmentAdapter.ViewHolder> {
    ArrayList<Record> records;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.set_record_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Record record = records.get(position);
        holder.setItem(record);
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView setCount;
        TextView setTime;

        public ViewHolder(View itemView) {
            super(itemView);

            setCount = itemView.findViewById(R.id.set_count);
            setTime = itemView.findViewById(R.id.set_time);
        }

        public void setItem(Record record) {
            int count = record.getSetName();
            String order;
            switch (count) {
                case 1:
                    order = count + "st";
                    break;
                case 2:
                    order = count + "nd";
                    break;
                case 3:
                    order = count + "rd";
                    break;
                default:
                    order = count + "th";
            }
            setCount.setText(order + " set");
            setTime.setText(record.getClock());
        }
    }

    public void addRecord(Record rec) {
        records.add(rec);
    }

    public void setRecords(ArrayList<Record> list) {
        this.records = list;
    }
}
