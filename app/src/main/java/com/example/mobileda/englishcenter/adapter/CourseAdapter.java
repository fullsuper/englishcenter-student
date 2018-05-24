package com.example.mobileda.englishcenter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobileda.englishcenter.R;
import com.example.mobileda.englishcenter.model.Course;

import java.util.List;

/**
 * Created by fullsuper on 5/19/2018.
 */

public class CourseAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Course> lstCourse;

    public CourseAdapter(Context context, int layout, List<Course> lstCourse) {
        this.context = context;
        this.layout = layout;
        this.lstCourse = lstCourse;
    }


    @Override
    public int getCount() {
        return lstCourse.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgSub;
        TextView tvSubject,tvClass;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CourseAdapter.ViewHolder holder;

        if (view ==null)
        {
            holder = new CourseAdapter.ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout,null);
            //holder.tvClass = (TextView) view.findViewById(R.id.tv);
            holder.tvSubject= (TextView) view.findViewById(R.id.tvSubject);
            holder.imgSub = (ImageView)view.findViewById(R.id.imgCourse);
            view.setTag(holder);
        }
        else {
            holder =(CourseAdapter.ViewHolder) view.getTag();
        }
        Course course = lstCourse.get(i);

        holder.tvSubject.setText(course.getSubject());
       // holder.tvTitle.setText(course.getTitle());
        holder.imgSub.setImageResource(course.getImgCourse());

        return view;
    }
}
