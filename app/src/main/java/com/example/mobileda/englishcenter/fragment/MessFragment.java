package com.example.mobileda.englishcenter.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mobileda.englishcenter.R;

import com.example.mobileda.englishcenter.adapter.CourseAdapter;
import com.example.mobileda.englishcenter.adapter.MessageAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessFragment extends Fragment
        implements EventListener<DocumentSnapshot>
{
    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    CourseAdapter mAdapter;
    Query mQuery;

    @BindView(R.id.rvCourse)
    RecyclerView rvCourse;

    @BindView(R.id.rvMessage)
    RecyclerView rvMessage;

    @BindView(R.id.txtCourse)
    TextView txtCourse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View view = inflater.inflate(R.layout.fragment_message,container,false);

        ButterKnife.bind(this,view);


        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        mQuery = mFirestore.collection("students").document(mAuth.getUid()).collection("courses");

        mAdapter = new CourseAdapter(mQuery, new CourseAdapter.OnCourseClickListener() {
            @Override
            public void OnCourseClicked(DocumentSnapshot snapshot) {
                txtCourse.setText("From : " +snapshot.getString("course"));
                String courseID = snapshot.getId();
                DocumentReference dr = mFirestore.document("/courses/"+courseID);

                Query nQuery = mFirestore.collection("messages").whereEqualTo("course",dr);
                MessageAdapter nAdapter = new MessageAdapter(nQuery, new MessageAdapter.OnMessageClickListener() {
                    @Override
                    public void OnMessageClicked(DocumentSnapshot snapshot) {
                        //show mess dialog

                    }
                });
                rvMessage.addItemDecoration(new DividerItemDecoration(rvMessage.getContext(), DividerItemDecoration.VERTICAL));

                rvMessage.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false));
                rvMessage.setAdapter(nAdapter);
                nAdapter.startListening();
            }
        });

        rvCourse.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false));
        rvCourse.setAdapter(mAdapter);

        return  view;
    }

    @Override
    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {}
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
