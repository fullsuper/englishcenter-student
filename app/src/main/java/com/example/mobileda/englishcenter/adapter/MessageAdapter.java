package com.example.mobileda.englishcenter.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.model.Message;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageAdapter extends FirestoreAdapter<MessageAdapter.ViewHolder> {

    private static final String TAG = "MESSAGE";

    public MessageAdapter(Query query, MessageAdapter.OnMessageClickListener listener) {
        super(query);
        mListener = listener;
    }
    public MessageAdapter(Query query) {
        super(query);
    }

    public interface OnMessageClickListener{
        void OnMessageClicked(DocumentSnapshot snapshot);
    }
    private MessageAdapter.OnMessageClickListener mListener;


    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MessageAdapter
                .ViewHolder(inflater.inflate(R.layout.subitem_message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        holder.bind(getSnapshot(position),mListener);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvContent)
        TextView tvContent;

        @BindView(R.id.tvTeacher)
        TextView tvTeacher;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
        public void bind(final DocumentSnapshot snapshot, final MessageAdapter.OnMessageClickListener mListener) {

            final Message message = snapshot.toObject(Message.class);
            tvTitle.setText(message.getTitle());
            tvContent.setText(message.getContent());
            if (message.getTeacher()!= null)

            message.getTeacher().addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot2, @Nullable FirebaseFirestoreException e) {
                    tvTeacher.setText(documentSnapshot2.getString("name"));
                }});

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null)
                        mListener.OnMessageClicked(snapshot);
                }
            });
        }
    }
}

