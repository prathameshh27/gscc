package com.example.spydey.prototypeone.viewHoder;

import android.content.Context;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.spydey.prototypeone.R;
import com.example.spydey.prototypeone.UserData;

public class RecordViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView textView;
    public View itemView;

    public RecordViewHoder(final View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.recordName);
        this.itemView = itemView;
    }

    @Override
    public void onClick(View view) {

    }
}
