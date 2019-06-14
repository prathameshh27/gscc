package com.example.spydey.prototypeone.viewHoder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.spydey.prototypeone.R;

public class RecordViewHoder extends RecyclerView.ViewHolder {

    public TextView textView;
    public Button button;

    public RecordViewHoder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.recordName);
        button = (Button) itemView.findViewById(R.id.deleteRecord);
    }

    public void setTextView(String text) {
        this.textView.setText(text);
    }
}
