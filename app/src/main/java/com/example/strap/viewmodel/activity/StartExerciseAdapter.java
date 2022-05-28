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

    public interface OnItemClickListener {
        void onItemClick(View v, int position, Intent intent);
    };

    private OnItemClickListener mListener = null;
    private OnItemClickListener getListener() {
        return this.mListener;
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

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

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView weight;
        TextView count;
        TextView set;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name_of_start_screen);
            weight = itemView.findViewById(R.id.weight_of_start_screen);
            count = itemView.findViewById(R.id.count_of_start_screen);
            set = itemView.findViewById(R.id.set_of_start_screen);

            ImageButton start = itemView.findViewById(R.id.start_exercise);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    StartExercise.pos = position;
                    StartExercise.counter = StartExercise.sets[StartExercise.pos];
                    Intent intent = new Intent(view.getContext(), NowExercisingActivity.class);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("weight", weight.getText().toString());
                    intent.putExtra("count", count.getText().toString());
                    intent.putExtra("set", set.getText().toString());
                    if (position != RecyclerView.NO_POSITION) {
                        getListener().onItemClick(view, position, intent);
                    }
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
