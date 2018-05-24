package com.example.mobileda.englishcenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileda.englishcenter.R;

import java.util.List;

import com.example.mobileda.englishcenter.model.Message;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.DataViewHolder> {

    private List<Message> messages;
    private Context context;

    public RecyclerDataAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getItemCount() {
        return messages == null ? 0 : messages.size();
    }

    @Override
    public RecyclerDataAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subitem_message, parent, false);

        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerDataAdapter.DataViewHolder holder, int position) {
        holder.tvContent.setText( messages.get(position).getContent());
        holder.tvTitle.setText( messages.get(position).getTitle());
        holder.ivIcon.setImageResource( messages.get(position).getImageTitle());
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvContent;
        private ImageView ivIcon;

        public DataViewHolder(View itemView) {
            super(itemView);

            tvTitle =  itemView.findViewById(R.id.tvTitle);
            tvContent =  itemView.findViewById(R.id.tvContent);
            ivIcon =   itemView.findViewById(R.id.imgMessage);

        }
    }
}