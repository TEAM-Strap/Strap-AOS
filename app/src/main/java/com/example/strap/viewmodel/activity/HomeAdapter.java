package com.example.strap.viewmodel.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strap.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<AdapterType> routines;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    };

    private HomeAdapter.OnItemClickListener mListener = null;
    private HomeAdapter.OnItemClickListener getListener() {
        return this.mListener;
    }
    public void setOnItemClickListener(HomeAdapter.OnItemClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return routines.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        if (viewType == 0) {
            view = inflater.inflate(R.layout.routine_layout, parent, false);
            return new ItemViewHolder(view);
        }
        else {
            view = inflater.inflate(R.layout.add_button_layout, parent, false);
            return new ButtonViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            Routine routine = (Routine) routines.get(position);
            ((ItemViewHolder)holder).setName(routine);
        }
    }

    @Override
    public int getItemCount() {
        return routines.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ItemViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
        }

        public void setName(Routine item) {
            name.setText(item.getName());
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
        routines.set(getItemCount()-1, item);
        routines.add(new AddButton());
    }

    public void addItem(AddButton item) {
        routines.add(item);
    }

    public void setItems(ArrayList<AdapterType> list) {
        this.routines = list;
    }
}
