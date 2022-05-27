package com.example.strap.viewmodel.activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strap.R;

import java.util.ArrayList;

public class StartExerciseAdapter
        extends RecyclerView.Adapter<StartExerciseAdapter.ViewHolder> {

    private ArrayList<Routine> exercises;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.workout_start_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Routine info = exercises.get(position);
        holder.setInfo(info);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView weight;
        TextView count;
        TextView set;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            weight = itemView.findViewById(R.id.weight);
            count = itemView.findViewById(R.id.count);
            set = itemView.findViewById(R.id.set);

            ImageButton start = itemView.findViewById(R.id.start_exercise);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), NowExercisingActivity.class);
                    ContextCompat.startActivity(view.getContext(), intent, null);
                }
            });
        }

        public void setInfo(Routine info) {
            name.setText(info.getName());
            weight.setText(info.getWeight() + "kg");
            count.setText(info.getCount() + "회/세트");
            set.setText(info.getSet() + "세트");
        }
    }

    public void setExercises(ArrayList<Routine> newExercises) {
        this.exercises = newExercises;
    }
}
