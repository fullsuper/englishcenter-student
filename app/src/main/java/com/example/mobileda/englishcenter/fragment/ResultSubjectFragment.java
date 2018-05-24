package com.example.mobileda.englishcenter.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.mobileda.englishcenter.activity.MarkActivity;
import com.example.mobileda.englishcenter.R;
import java.util.ArrayList;

import com.example.mobileda.englishcenter.adapter.CourseAdapter;
import com.example.mobileda.englishcenter.model.Course;

public class ResultSubjectFragment extends Fragment {

    GridView gvClass;
    ArrayList<Course> lstCourse;
    CourseAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result,container,false);
        Map(view);
        adapter = new CourseAdapter(getActivity(),R.layout.subitem_course,lstCourse);
        gvClass.setAdapter(adapter);

        gvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),MarkActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }


    private void Map(View view)
    {
        gvClass = (GridView) view.findViewById(R.id.gvClass);
        lstCourse = new ArrayList<>();
        //data
        lstCourse.add(new Course(R.mipmap.headphones,"L210","Listening lv1"));
        lstCourse.add(new Course(R.mipmap.listen1,"L543","Master Listening"));
        lstCourse.add(new Course(R.mipmap.speak,"S210","Communication Speaking"));
        lstCourse.add(new Course(R.mipmap.reading,"R210","Basic Readinging"));
        lstCourse.add(new Course(R.mipmap.write1,"W556","Writing A"));
        lstCourse.add(new Course(R.mipmap.openbook,"R92A","Reading lv2"));
        lstCourse.add(new Course(R.mipmap.speak,"S210","Speaking for Job"));
        lstCourse.add(new Course(R.mipmap.headphones,"S210","Master Listening"));
    }
}