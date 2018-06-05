package com.example.mobileda.englishcenter.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.activity.RegistorActivity;
import com.example.mobileda.englishcenter.adapter.RegistrationAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleFragment extends Fragment
        implements EventListener<DocumentSnapshot> {
    private FirebaseFirestore mFirestore;
    private FirebaseAuth mAuth;
    private RegistrationAdapter mAdapter;

    @BindView(R.id.spnDoW)
    Spinner spnDoW;
    @BindView(R.id.rvCourse)
    RecyclerView rvCourse;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule,container,false);

        ButterKnife.bind(this,view);
        rvCourse.addItemDecoration(new DividerItemDecoration(rvCourse.getContext(), DividerItemDecoration.VERTICAL));

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        spnDoW.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                setContentRecyclerView(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return view;
    }
    void setContentRecyclerView(int position)
    {
        String dateOfWeek;
        switch (position)
        {
            case 0: dateOfWeek = "Monday";break;
            case 1: dateOfWeek = "Tuesday";break;
            case 2: dateOfWeek = "Wednesday";break;
            case 3: dateOfWeek = "Thursday";break;
            case 4: dateOfWeek = "Friday";break;
            case 5: dateOfWeek = "Saturday";break;
            default: dateOfWeek = "Sunday";break;
        }
        Query query = mFirestore
                .collection("students/" + mAuth.getUid() + "/courses/")
                .whereEqualTo("time",dateOfWeek);
        mAdapter = new RegistrationAdapter(query, new RegistrationAdapter.OnCourseClickListener() {
            @Override
            public void OnCourseClicked(DocumentSnapshot snapshot) {}});

        rvCourse.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        rvCourse.setAdapter(mAdapter);
        mAdapter.startListening();
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
    }


}

