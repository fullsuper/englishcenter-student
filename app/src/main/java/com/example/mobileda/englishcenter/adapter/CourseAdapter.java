package com.example.mobileda.englishcenter.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.model.Course;
import com.example.mobileda.englishcenter.model.Message;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CourseAdapter extends FirestoreAdapter<CourseAdapter.ViewHolder> {

    private static final String TAG = "COURSE";

    public CourseAdapter(Query query, CourseAdapter.OnCourseClickListener listener) {
        super(query);
        mListener = listener;
    }
    public CourseAdapter(Query query) {
        super(query);
    }

    public interface OnCourseClickListener{
        void OnCourseClicked(DocumentSnapshot snapshot);
    }
    private CourseAdapter.OnCourseClickListener mListener;


    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new CourseAdapter
                .ViewHolder(inflater.inflate(R.layout.subitem_course, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getSnapshot(position),mListener);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvCourse)
        TextView tvCourse;

        @BindView(R.id.tvTeacher)
        TextView tvTeacher;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bind(final DocumentSnapshot snapshot, final CourseAdapter.OnCourseClickListener mListener) {
            final Course course = snapshot.toObject(Course.class);
            tvCourse.setText(course.getCourse());
            Log.d(TAG,course.getCourse()+"");
            course.getTeacher().addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot2, @Nullable FirebaseFirestoreException e) {
                    tvTeacher.setText(documentSnapshot2.getString("Name"));
                }});

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

