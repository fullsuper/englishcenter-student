package com.example.mobileda.englishcenter.activity;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.firestore.QuerySnapshot;

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
        AlertDialog.Builder alertRegistor =  new AlertDialog.Builder(this);
        alertRegistor.setTitle("Xác nhận");
        alertRegistor.setIcon(R.mipmap.calendar);
        alertRegistor.setMessage("Bạn có chắc chắn đăng kí khóa học này?");

        alertRegistor.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //get current student
                String userid = mAuth.getUid();
                mFireStore.collection("students").document(userid).collection("courses").document(courseID).set(course);
                Toast.makeText(RegistorActivity.this,"Đăng ký khóa học thành công!",Toast.LENGTH_SHORT).show();
            }
        });
        alertRegistor.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        alertRegistor.show();

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

        mFireStore.collection("students/"+mAuth.getUid()+"/courses").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                List<DocumentSnapshot> lstDocSnapshot = queryDocumentSnapshots.getDocuments();
                if (lstDocSnapshot!=null)
                    for (DocumentSnapshot ds : lstDocSnapshot)
                    {
                        if (courseID.equals(ds.getId()))
                            btnAddCourse.setEnabled(false);
                    }
            }
        });

        mFireStore.collection("courses")
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
                            txtTeacher.setText(documentSnapshot2.getString("name"));
                        }
                    });
                }
            }
        });

    }
}
