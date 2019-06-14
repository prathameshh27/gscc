package com.example.spydey.prototypeone;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordsFragment extends Fragment {

    public View view;
    public RecyclerView recordRecyclerView;
    public DatabaseReference dbRef;
    public FirebaseRecyclerAdapter adapter;

    public RecordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_records, container, false);

        recordRecyclerView = view.findViewById(R.id.recordsRecyclerView);





        return view;
    }

}
