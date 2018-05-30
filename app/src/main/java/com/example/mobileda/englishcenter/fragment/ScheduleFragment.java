package com.example.mobileda.englishcenter.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.mobileda.englishcenter.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScheduleFragment extends Fragment{

    @BindView(R.id.cvSchedule)
    CalendarView cvSchedule;

    FirebaseAuth mAuth;
    FirebaseFirestore mFireStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragent_schedule,container,false);
        ButterKnife.bind(view);
        final List<String> lstTime = new ArrayList<>();
        mAuth = mAuth.getInstance();
        mFireStore = FirebaseFirestore.getInstance();
        mFireStore.collection("lstStudent/"+mAuth.getUid()+"/courses")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot snapshot : task.getResult()) {
                                DayOfWeek dow = DayOfWeek.valueOf(snapshot.getString("time").toUpperCase());


                               // cvSchedule.
                            }
                        }
                        else{}
                    }
        });
        //int month = ;
        List<Calendar> lstCalendar = new ArrayList<>();
        lstCalendar.add(new GregorianCalendar(2018,Calendar.MAY , 11));
        lstCalendar.add(new GregorianCalendar(2018,Calendar.MAY , 15));
        lstCalendar.add(new GregorianCalendar(2018,Calendar.MAY , 13));
        lstCalendar.add(new GregorianCalendar(2018,Calendar.MAY , 22));
        for (Calendar ca:lstCalendar)
        {
            try {
                cvSchedule.setDate(ca.getTimeInMillis());
            }
            catch (Exception e)
            {
            }
        }
        return view;
    }
}
