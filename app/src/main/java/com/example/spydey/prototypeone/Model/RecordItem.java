package com.example.spydey.prototypeone.Model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spydey.prototypeone.R;

public class RecordItem {
    public String recordRoot;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.record_item_card, container, false);
    }
}
