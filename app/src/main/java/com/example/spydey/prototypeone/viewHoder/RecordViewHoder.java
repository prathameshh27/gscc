package com.example.spydey.prototypeone.viewHoder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

public class RecordViewHoder extends RecyclerView {

    public TextView textView;
    public Button button;


    public RecordViewHoder(Context context) {
        super(context);
    }

    public RecordViewHoder(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecordViewHoder(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
