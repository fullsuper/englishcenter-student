package com.example.mobileda.englishcenter.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobileda.englishcenter.R;

import java.util.ArrayList;

import com.example.mobileda.englishcenter.adapter.RecyclerDataAdapter;
import com.example.mobileda.englishcenter.model.Message;

public class MessFragment extends Fragment {

    private RecyclerView rvMessage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View view = inflater.inflate(R.layout.fragment_message,container,false);
        Map(view);

        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    private void Map(View view)
    {
        ArrayList<Message> lstMessage;
        lstMessage = new ArrayList<>();
        rvMessage = view.findViewById(R.id.rvMessage);

        //data
        lstMessage.add(new Message(R.mipmap.mail,"Thư Của Đồng Nè", "Đọc thư của Đồng đi mọi người"));
        lstMessage.add(new Message(R.mipmap.mail,"Thư Của Rum Nè", "Đọc thư của Đồng đi mọi người"));
        lstMessage.add(new Message(R.mipmap.mail,"Hello Rum", "Đọc thư của Đồng đi mọi người"));
        lstMessage.add(new Message(R.mipmap.mail,"Xin Chào", "Đọc thư của Đồng đi mọi người"));
        lstMessage.add(new Message(R.mipmap.mail,"Xin Chào", "Đọc thư của Đồng đi mọi người"));
        lstMessage.add(new Message(R.mipmap.mail,"Hello Rum", "Đọc thư của Đồng đi mọi người"));
        lstMessage.add(new Message(R.mipmap.mail,"Xin Chào", "Đọc thư của Đồng đi mọi người! Bây giờ có chịu độc không hả ahduhujduwjdijwidjijdijijfijfijijfijfijeijfeijijf"));
        lstMessage.add(new Message(R.mipmap.mail,"Xin Chào", "Đọc thư của Đồng đi mọi người"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvMessage.setLayoutManager(layoutManager);
        rvMessage.setHasFixedSize(true);
        rvMessage.setAdapter(new RecyclerDataAdapter(getActivity(), lstMessage));
        rvMessage.addItemDecoration(new DividerItemDecoration(rvMessage.getContext(), DividerItemDecoration.VERTICAL));

    }
}
