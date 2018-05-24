package com.example.mobileda.englishcenter.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobileda.englishcenter.R;

import java.util.ArrayList;

import com.example.mobileda.englishcenter.adapter.RecyclerDataAdapter;
import com.example.mobileda.englishcenter.model.Message;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnregisteredSubjectFragment extends Fragment {


    private static final String KEY_REGISTRAITION = "key_registration";

    public UnregisteredSubjectFragment() {
        // Required empty public constructor
    }
    public static UnregisteredSubjectFragment newInstance(String registration) {
        UnregisteredSubjectFragment fragment = new UnregisteredSubjectFragment();
        Bundle args = new Bundle();
        args.putString(KEY_REGISTRAITION, registration);
        fragment.setArguments(args);
        return fragment;
    }

    ArrayList<Message> lstCourse;
    private RecyclerView rvItems;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_unregisteredsubject, container, false);

        //Mapping
        Map(rootView);

        textView.setText("Chưa đăng ký");

        return rootView;
    }
    private void Map(View view)
    {
        //db = FirebaseFirestore.getInstance();
        lstCourse= new ArrayList<>();
        rvItems = view.findViewById(R.id.rvCourse);
        textView = view.findViewById(R.id.section_label);

        lstCourse.add(new Message(R.mipmap.headphones,"Unregister","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.reading,"Unregister 3","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.listen1,"Unregister 3","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.listjob,"Unregister 2","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.openbook,"Unregister 1","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.reading,"Unregister 1","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.headphones,"Unregister 2","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.write1,"Unregister 2","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.headphones,"Unregister","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.reading,"Unregister 3","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.listen1,"Unregister 3","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.listjob,"Unregister 2","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.openbook,"Unregister 1","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.reading,"Unregister 1","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.headphones,"Unregister 2","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.write1,"Unregister 2","Nguyễn Thành Đồng"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvItems.setLayoutManager(layoutManager);
        rvItems.setHasFixedSize(true);
        rvItems.setAdapter(new RecyclerDataAdapter(getActivity(), lstCourse));
        //end data

    }

}
