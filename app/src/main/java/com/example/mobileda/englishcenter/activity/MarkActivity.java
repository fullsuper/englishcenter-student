package com.example.mobileda.englishcenter.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.adapter.CourseAdapter;
import com.example.mobileda.englishcenter.adapter.FirestoreAdapter;
import com.example.mobileda.englishcenter.adapter.MessageAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MarkActivity extends AppCompatActivity {


    @BindView(R.id.rvCourse)
    RecyclerView rvCourse;
    @BindView(R.id.txtFirstMark)
    TextView txtFirstMark;
    @BindView(R.id.txtSecondMark)
    TextView txtSecondMark;
    @BindView(R.id.txtCourse)
    TextView txtCourse;
    @BindView(R.id.txtNameStudent)
    TextView txtNameStudent;

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    Query mQuery;
    FirestoreAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);

        ButterKnife.bind(this);

        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        mQuery = mFirestore.collection("students").document(mAuth.getUid()).collection("courses");

        mAdapter = new CourseAdapter(mQuery, new CourseAdapter.OnCourseClickListener() {
            @Override
            public void OnCourseClicked(DocumentSnapshot snapshot) {
                //show diem
                try {
                    txtCourse.setText(snapshot.getString("course"));
                    txtFirstMark.setText(snapshot.getString("finalterm_mark"));
                    txtSecondMark.setText(snapshot.getString("midterm_mark"));
                }
                catch (Exception e) {
                    txtSecondMark.setText("Chưa cập nhật");
                    txtFirstMark.setText("Chưa cập nhật");

                }
            }
        });

        rvCourse.setLayoutManager(new LinearLayoutManager(this, LinearLayout.HORIZONTAL,false));
        rvCourse.setAdapter(mAdapter);
        mAdapter.startListening();

    }

}
