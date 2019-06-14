package com.example.spydey.prototypeone;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.spydey.prototypeone.Model.RecordItem;
import com.example.spydey.prototypeone.viewHoder.RecordViewHoder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecordsFragment<FirebaseRecyclerOptions> extends Fragment {

    public View view;
    public RecyclerView recordRecyclerView;
    public DatabaseReference dbRef;
    private FirebaseAuth auth;
    public FirebaseRecyclerAdapter<RecordItem, RecordViewHoder> recordsAdapter;
    public com.firebase.ui.database.FirebaseRecyclerOptions<RecordItem> options;

    public RecordsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_records, container, false);
        auth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("UserData").child(auth.getUid());

        options = new com.firebase.ui.database.FirebaseRecyclerOptions.Builder<RecordItem>()
                .setQuery(dbRef, RecordItem.class).build();

        recordRecyclerView = view.findViewById(R.id.recordsRecyclerView);
        recordRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recordsAdapter = new FirebaseRecyclerAdapter<RecordItem, RecordViewHoder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RecordViewHoder holder, int position, @NonNull RecordItem model) {
                holder.textView.setText(model.recordRoot);
            }

            @NonNull
            @Override
            public RecordViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.record_item_card, viewGroup, false);
                return new RecordViewHoder(view);
            }
        };

        recordsAdapter.startListening();
        recordRecyclerView.setAdapter(recordsAdapter);
        return view;
    }

}
