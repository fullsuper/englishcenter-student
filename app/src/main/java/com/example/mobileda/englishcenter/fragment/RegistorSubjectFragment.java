package com.example.mobileda.englishcenter.fragment;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.activity.RegistorActivity;
import com.example.mobileda.englishcenter.adapter.RegistrationAdapter;
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
import butterknife.OnItemClick;

public class RegistorSubjectFragment extends android.support.v4.app.Fragment
implements EventListener<DocumentSnapshot>
{

    private static final String KEY_REGISTRAITION = "key_registration";

    private final String TAG = RegistorSubjectFragment.class.getSimpleName();
    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;
    private Query mQuery;
    private RegistrationAdapter mAdapter;

    public RegistorSubjectFragment() { }

    // Method static dạng singleton, cho phép tạo fragment mới
    public static RegistorSubjectFragment newInstance(String registration) {
        RegistorSubjectFragment fragment = new RegistorSubjectFragment();
        Bundle args = new Bundle();
        args.putString(KEY_REGISTRAITION, registration);
        fragment.setArguments(args);
        return fragment;
    }


    @BindView(R.id.rvCourse)
    RecyclerView rvItems;

    @BindView(R.id.section_label)
    TextView textView;

    @Override
    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
        if (e != null) {
            Log.w(TAG, "restaurant:onEvent", e);
            return;
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_courseregistration, container, false);
        ButterKnife.bind(this,rootView);

        rvItems.addItemDecoration(new DividerItemDecoration(rvItems.getContext(), DividerItemDecoration.VERTICAL));
        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        mQuery = mFirestore.collection("lstStudent").document(mAuth.getUid()).collection("courses");//.whereEqualTo(strQuery,FirebaseAuth.getInstance().getUid());

        mAdapter = new RegistrationAdapter(mQuery, new RegistrationAdapter.OnCourseClickListener() {
            @Override
            public void OnCourseClicked(DocumentSnapshot snapshot) {
                Intent intent = new Intent(getActivity(), RegistorActivity.class);
                intent.putExtra("COURSE_ID",snapshot.getId().toString());
                intent.putExtra("KEY", "NONE_REGISTOR");
                startActivity(intent); }})
        {
            @Override
            protected void onDataChanged() {}
            @Override
            protected void onError(FirebaseFirestoreException e) {
                super.onError(e);
            }
        };

        rvItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvItems.setAdapter(mAdapter);

        switch (getArguments().getString(KEY_REGISTRAITION)) {
            case "Registration": {textView.setText("Đã đăng ký");}break;
            default: {textView.setText("Đăng ký");}break;
        }
        return rootView;
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