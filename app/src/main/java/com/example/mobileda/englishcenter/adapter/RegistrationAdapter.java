package com.example.mobileda.englishcenter.adapter;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.model.RegistrationCourse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.collection.LLRBNode;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegistrationAdapter
        extends FirestoreAdapter<RegistrationAdapter.ViewHolder> {

    private static final String TAG = "Registration";

    public RegistrationAdapter(Query query, OnCourseClickListener listener) {
        super(query);
        mListener = listener;
    }
    public RegistrationAdapter(Query query) {
        super(query);
    }

    public interface OnCourseClickListener{
        void OnCourseClicked(DocumentSnapshot snapshot);
    }
    private OnCourseClickListener mListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new RegistrationAdapter
                .ViewHolder(inflater.inflate(R.layout.subitem_registration_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RegistrationAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position),mListener);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgCourseRegistration)
        ImageView imgCourseRegistration;

        @BindView(R.id.txtCourse)
        TextView txtCourse;

        @BindView(R.id.txtRoom)
        TextView txtRoom;

        @BindView(R.id.txtTeacher)
        TextView txtTeacher;

        @BindView(R.id.txtTime)
        TextView txtTime;

        @BindView(R.id.txtState)
        TextView txtState;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bind(final DocumentSnapshot snapshot, final OnCourseClickListener mListener) {

            final RegistrationCourse course = snapshot.toObject(RegistrationCourse.class);
            txtCourse.setText(course.getCourse());
            txtTime.setText(course.getTime());
            txtRoom.setText(course.getRoom());

            course.getTeacher().addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot2, @Nullable FirebaseFirestoreException e) {
                            txtTeacher.setText(documentSnapshot2.getString("name"));
                    }});
            // check
            FirebaseFirestore.getInstance()
                    .document("students/" + FirebaseAuth.getInstance().getUid() + "/courses/" + snapshot.getId())
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            if(documentSnapshot.getString("course") == null){
                                txtState.setBackgroundResource(R.drawable.custom_shape2);
                                txtState.setText(course.getState());
                            }
                            else
                                txtState.setText("Đã đăng ký");
                        }
                    });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null)
                        mListener.OnCourseClicked(snapshot);
                }
            });
        }

    }
}
