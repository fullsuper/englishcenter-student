package com.example.mobileda.englishcenter.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.model.Message;
import com.example.mobileda.englishcenter.model.Quiz;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizAdapter
        extends FirestoreAdapter<QuizAdapter.ViewHolder> {

    private static final String TAG = "QuizAdapter";
    private String[]  lstAnswer = new String[50];
    public QuizAdapter(Query query) {
        super(query);
    }
    public QuizAdapter(Query query, onQuizClickListener listener) {
        super(query);
        mListener = listener;
    }
    onQuizClickListener mListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            return new QuizAdapter
                    .ViewHolder(inflater.inflate(R.layout.subitem_quiz, parent, false));
    }

    public ArrayList<DocumentSnapshot> getListQuestion() {
        return mSnapshots;
    }
    public double getMark()
    {
        double mark =0;

        for (int i =0 ;i<mSnapshots.size();i++)
        {
            if((lstAnswer[i]+"").equals(mSnapshots.get(i).getString("answer")))
                mark++;
        }

        return  (mark/(double)getListQuestion().size())*10;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getSnapshot(position),mListener,position);
    }

    public interface onQuizClickListener {
        void onQuizClicked(DocumentSnapshot snapshot);

    }

    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.txtQuestion)
        TextView txtQuestion;
        @BindView(R.id.rdbAns1)
        RadioButton rdbAns1;
        @BindView(R.id.rdbAns2)
        RadioButton rdbAns2;
        @BindView(R.id.rdbAns3)
        RadioButton rdbAns3;
        @BindView(R.id.rdbAns4)
        RadioButton rdbAns4;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bind(final DocumentSnapshot snapshot, final QuizAdapter.onQuizClickListener mListener, final int position) {

            final Quiz quiz = snapshot.toObject(Quiz.class);
            txtQuestion.setText(quiz.getQuestion());
            rdbAns1.setText(quiz.getAns1());
            rdbAns2.setText(quiz.getAns2());
            rdbAns3.setText(quiz.getAns3());
            rdbAns4.setText(quiz.getAns4());


            rdbAns1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lstAnswer[position] = rdbAns1.getText()+"";
                    Log.d(TAG, "onClick: "+lstAnswer[position]);

                }
            });
            rdbAns2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lstAnswer[position] = rdbAns2.getText()+"";
                    Log.d(TAG, "onClick: "+lstAnswer[position]);
                }
            });
            rdbAns3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lstAnswer[position] = rdbAns3.getText()+"";
                    Log.d(TAG, "onClick: "+lstAnswer[position]);

                }
            });
            rdbAns4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lstAnswer[position] = rdbAns4.getText()+"";
                    Log.d(TAG, "onClick: "+lstAnswer[position]);
                }
            });


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null)
                        mListener.onQuizClicked(snapshot);
                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
