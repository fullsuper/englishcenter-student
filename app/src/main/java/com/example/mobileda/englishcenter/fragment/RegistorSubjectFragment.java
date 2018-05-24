package com.example.mobileda.englishcenter.fragment;

import android.os.Bundle;
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

public class RegistorSubjectFragment extends android.support.v4.app.Fragment {

    private static final String KEY_REGISTRAITION = "key_registration";

    public RegistorSubjectFragment() {
    }

    // Method static dạng singleton, cho phép tạo fragment mới, lấy tham số đầu vào để cài đặt màu sắc.
    public static RegistorSubjectFragment newInstance(String registration) {
        RegistorSubjectFragment fragment = new RegistorSubjectFragment();
        Bundle args = new Bundle();
        args.putString(KEY_REGISTRAITION, registration);
        fragment.setArguments(args);
        return fragment;
    }

    //Mapping
    private ArrayList<Message> lstCourse;
    private RecyclerView rvItems;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_courseregistration, container, false);

        //Mapping
        Map(rootView);

        switch (getArguments().getString(KEY_REGISTRAITION)) {
            case "Registration": {
                //chuyển qua fragment lớp đã đăng ký
                textView.setText("Đã đăng ký");
            }
            break;
            default: {
                //chuyển qua fragment chưa đăng ký
                textView.setText("Đăng ký");
            }
            break;
        }
        return rootView;
    }

    private void Map(View view)
    {
        //db = FirebaseFirestore.getInstance();
        lstCourse= new ArrayList<>();
        rvItems = view.findViewById(R.id.rvCourse);
        textView = view.findViewById(R.id.section_label);

        lstCourse.add(new Message(R.mipmap.headphones,"Listen 1","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.reading,"Reading 3","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.listen1,"Listen 3","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.listjob,"Reading 2","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.openbook,"Writing 1","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.reading,"Reading 1","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.headphones,"Listen 2","Nguyễn Thành Đồng"));
        lstCourse.add(new Message(R.mipmap.write1,"Writing 2","Nguyễn Thành Đồng"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvItems.setLayoutManager(layoutManager);
        rvItems.setHasFixedSize(true);
        rvItems.setAdapter(new RecyclerDataAdapter(getActivity(), lstCourse));

        //end data

    }
}