package com.example.strap.viewmodel.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.strap.R;

public class AddButtonLayout extends LinearLayout {
    public AddButtonLayout(Context context) {
        super(context);
        init(context);
    }

    public AddButtonLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.add_button_layout, this, true);
    }
}
