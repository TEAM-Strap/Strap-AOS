package com.example.strap.viewmodel.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strap.R;

import java.util.ArrayList;

public class RoutineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static ArrayList<AdapterType> items;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == 0) {
            view = inflater.inflate(R.layout.workout_info_layout, parent, false);
            return new RoutineViewHolder(view);
        }
        else {
            view = inflater.inflate(R.layout.add_button_layout, parent, false);
            return new ButtonViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RoutineViewHolder) {
            Routine item = (Routine) items.get(position);
            ((RoutineViewHolder)holder).setItem(item);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    public static class RoutineViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView weight;
        TextView count;
        TextView set;

        public RoutineViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            weight = itemView.findViewById(R.id.weight);
            count = itemView.findViewById(R.id.count);
            set = itemView.findViewById(R.id.set);
        }

        public void setItem(Routine item) {
            name.setText(item.getName());
            weight.setText(item.getWeight()+"kg");
            count.setText(item.getCount()+"회/세트");
            set.setText(item.getSet()+"세트");
        }
    }

    public class ButtonViewHolder extends RecyclerView.ViewHolder {
        public ButtonViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        getListener().onItemClick(view, pos);
                    }
                }
            });
        };
    }

    public void addItem(Routine item) {
        items.set(getItemCount()-1, item);
        items.add(new AddButton());
    }

    public void addItem(AddButton item) {
        items.add(item);
    }

    public void setItems(ArrayList<AdapterType> items) {
        this.items = items;
    }

    public AdapterType getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Routine item) {
        items.set(position, item);
    }
}
