package com.example.mobileda.englishcenter.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileda.englishcenter.R;

import java.util.ArrayList;

import com.example.mobileda.englishcenter.activity.RegistorActivity;
import com.example.mobileda.englishcenter.adapter.RecyclerDataAdapter;
import com.example.mobileda.englishcenter.adapter.RegistrationAdapter;
import com.example.mobileda.englishcenter.model.Message;
import com.example.mobileda.englishcenter.model.RegistrationCourse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UnregisteredSubjectFragment extends android.support.v4.app.Fragment
        implements EventListener<DocumentSnapshot> {

    private final String TAG = RegistorSubjectFragment.class.getSimpleName();
    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;
    private Query mQuery;
    private RegistrationAdapter mAdapter;

    private static final String KEY_REGISTRAITION = "key_registration";

    public UnregisteredSubjectFragment() {
    }

    public static UnregisteredSubjectFragment newInstance(String registration) {
        UnregisteredSubjectFragment fragment = new UnregisteredSubjectFragment();
        Bundle args = new Bundle();
        args.putString(KEY_REGISTRAITION, registration);
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.section_label)
    TextView textView;
    @BindView(R.id.rvCourse)
    RecyclerView rvItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_unregisteredsubject, container, false);
        ButterKnife.bind(this,rootView);

        rvItems.addItemDecoration(new DividerItemDecoration(rvItems.getContext(), DividerItemDecoration.VERTICAL));
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        mQuery = mFirestore.collection("lstCourse");

        mAdapter = new RegistrationAdapter(mQuery, new RegistrationAdapter.OnCourseClickListener() {
            @Override
            public void OnCourseClicked(DocumentSnapshot snapshot) {
                Intent intent = new Intent(getActivity(), RegistorActivity.class);
                intent.putExtra("COURSE_ID",snapshot.getId().toString());
                intent.putExtra("KEY", UnregisteredSubjectFragment.class.toString());
                startActivity(intent); }})
        {
            @Override
            protected void onDataChanged() {}
            @Override
            protected void onError(FirebaseFirestoreException e) {
                super.onError(e);
            }
        };

        textView.setText("Danh sách khóa học");
        rvItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvItems.setAdapter(mAdapter);

        return rootView;
    }


    @Override
    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
    }
    @Override
    public void onStart() {
        super.onStart();
        if(mAdapter!=null)
            mAdapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
}
