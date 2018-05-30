package com.example.mobileda.englishcenter.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.model.Course;
import com.example.mobileda.englishcenter.model.RegistrationCourse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistorActivity extends AppCompatActivity {

    RegistrationCourse course;
    String courseID;
    FirebaseFirestore mFireStore;
    FirebaseAuth mAuth;


    @BindView(R.id.btnAddCourse)
    FloatingActionButton btnAddCourse;

    @OnClick(R.id.btnAddCourse)
    public void addCourse(View view)
    {
        //get current student
        String userid = mAuth.getUid();
        mFireStore.collection("lstStudent").document(userid).collection("courses").document(courseID).set(course);
    }
    @BindView(R.id.txtCourse)
    TextView txtCourse;
    @BindView(R.id.txtTeacher)
    TextView txtTeacher;
    @BindView(R.id.txtRoom)
    TextView txtRoom;
    @BindView(R.id.txtTime)
    TextView txtTime;
    @BindView(R.id.txtCost)
    TextView txtCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registor);
        ButterKnife.bind(this);
        mFireStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        courseID = getIntent().getStringExtra("COURSE_ID");

        if(getIntent().getStringExtra("KEY").equals("NONE_REGISTOR"))
            btnAddCourse.setEnabled(false);

        mFireStore.collection("lstCourse")
                .document(courseID)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                course = task.getResult().toObject(RegistrationCourse.class);
                txtCourse.setText(course.getCourse());
                txtTime.setText(course.getTime());
                txtRoom.setText(course.getRoom());
                txtCost.setText(course.getCost());
                //get teacher
                DocumentReference rq = task.getResult().getDocumentReference("teacher");
                if (rq != null) {
                    DocumentReference teacher = FirebaseFirestore
                            .getInstance()
                            .document(task.getResult().getDocumentReference("teacher").getPath());
                    ListenerRegistration statusRegistration = teacher.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot2, @Nullable FirebaseFirestoreException e) {
                            txtTeacher.setText(documentSnapshot2.getString("Name"));
                        }
                    });
                }
            }
        });

    }
}
