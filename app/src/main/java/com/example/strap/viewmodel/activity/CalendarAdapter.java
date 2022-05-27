package com.example.strap.viewmodel.activity;

import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.strap.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private ArrayList<DayCard> calendar;
    private int selectedPosition = -1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.daycard_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DayCard card = calendar.get(position);
        holder.setCard(card);

        if (position != selectedPosition) {
            holder.setBack();
        }
        else {
            holder.select();
        }
    }

    @Override
    public int getItemCount() {
        return calendar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layout;
        TextView day;
        TextView date;
        boolean selected = false;


        public ViewHolder(View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.day_card_linear_layout);
            day = itemView.findViewById(R.id.day);
            date = itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();

                    if (pos != selectedPosition) {
                        if (selectedPosition != -1) {
                            notifyItemChanged(selectedPosition);
                        }
                        selectedPosition = pos;
                        select();
                    }
                }
            });
        }

        public void setCard(DayCard card) {
            day.setText(card.getDay());
            date.setText(card.getDate() + "");
        }

        private void select() {
           setAttributes();
        }

        private void setAttributes() {
            layout.setBackground(itemView.getResources().getDrawable(R.drawable.daycard_drawable));
            day.setTextColor(Color.parseColor("white"));
            date.setTextColor(Color.parseColor("white"));
        }

        private void setBack() {
            layout.setBackground(itemView.getResources().getDrawable(R.drawable.daycard_drawable_base));
            day.setTextColor(Color.parseColor("black"));
            date.setTextColor(Color.parseColor("black"));
        }
    }

    public void setCalendar(ArrayList<DayCard> calendar) {
        this.calendar = calendar;
    }

    public void addDay(DayCard day) {
        this.calendar.add(day);
    }
}
