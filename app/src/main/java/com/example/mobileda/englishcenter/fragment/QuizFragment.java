package com.example.mobileda.englishcenter.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.adapter.CourseAdapter;
import com.example.mobileda.englishcenter.adapter.MessageAdapter;
import com.example.mobileda.englishcenter.adapter.QuizAdapter;
import com.example.mobileda.englishcenter.model.Quiz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizFragment extends Fragment {

    private static final String TAG = "QuizFragment";

    FirebaseFirestore mFirestore;
    FirebaseAuth mAuth;
    CourseAdapter mAdapter;
    QuizAdapter qAdapter;
    Query mQuery;
    String courseID ="";

    @BindView(R.id.txtCourse)
    TextView txtCourse;

    @BindView(R.id.rvCourse)
    RecyclerView rvCourse;

    @BindView(R.id.rvQuizs)
    RecyclerView rvQuizs;

    @BindView(R.id.btnStart)
    Button btnStart;

    @BindView(R.id.btnEnd)
    Button btnEnd;

    @OnClick(R.id.btnStart)
    public void createNewExam(View view)
    {

        Query nQuery = mFirestore.collection("quizs").whereEqualTo("courseid",courseID);
        qAdapter = new QuizAdapter(nQuery, new QuizAdapter.onQuizClickListener() {
            @Override
            public void onQuizClicked(DocumentSnapshot snapshot) {

            }
        });
        rvQuizs.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.VERTICAL,false));
        rvQuizs.setAdapter(qAdapter);
        qAdapter.startListening();
        rvQuizs.setVisibility(View.VISIBLE);

        if(rvQuizs.getItemDecorationCount() == 0) {
            Toast.makeText(getActivity(), "wrong : "+qAdapter.getItemCount() , Toast.LENGTH_SHORT).show();
            return;
        }

        btnStart.setEnabled(false);
        btnEnd.setEnabled(true);


    }

    @OnClick(R.id.btnEnd)
    public void endExam(View view)
    {
       // Toast.makeText(getActivity(), "hello "+qAdapter.getListQuestion().size(), Toast.LENGTH_SHORT).show();

        if(rvQuizs.getItemDecorationCount() == 0) {
            Toast.makeText(getActivity(), "wrong : "+qAdapter.getItemCount() , Toast.LENGTH_SHORT).show();
            return;
        }
        //TODO: compute num of true answer and get mark

        final double mark = qAdapter.getMark();

        //TODO: update mark in course of student
        mFirestore.document("students/"+mAuth.getUid()+"/courses/"+courseID)
                .update("midterm_mark",mark)
                .addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getActivity(),"Bạn đạt được : " + mark+"/10",Toast.LENGTH_SHORT).show();
                }
        });
        //TODO: close recycle view
        rvQuizs.setVisibility(View.INVISIBLE);
        btnEnd.setEnabled(false);
        btnStart.setEnabled(false);
        //TODO: try to not repeating exam again ** if exist mark then cant do this again!

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View view = inflater.inflate(R.layout.fragment_quiz,container,false);

        ButterKnife.bind(this,view);



        mFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        mQuery = mFirestore.collection("students").document(mAuth.getUid()).collection("courses");
        rvQuizs.addItemDecoration(new DividerItemDecoration(rvQuizs.getContext(), DividerItemDecoration.VERTICAL));

        mAdapter = new CourseAdapter(mQuery, new CourseAdapter.OnCourseClickListener() {
            @Override
            public void OnCourseClicked(final DocumentSnapshot snapshot) {

                AlertDialog.Builder alertRegistor =  new AlertDialog.Builder(getActivity());
                alertRegistor.setTitle("Xác nhận");
                alertRegistor.setIcon(R.mipmap.exit);
                alertRegistor.setMessage("Xác nhận chuyển khóa học này");

                alertRegistor.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        btnEnd.setEnabled(false);
                        btnStart.setEnabled(true);
                        courseID = snapshot.getId();
                        txtCourse.setText(snapshot.getString("course"));
                    }
                });
                alertRegistor.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertRegistor.show();

            }
        });

        rvCourse.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false));
        rvCourse.setAdapter(mAdapter);
        mAdapter.startListening();
        return  view;
    }
}
