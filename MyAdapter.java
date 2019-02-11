package com.example.kontess.studentdatabase;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Kontess on 6.5.2018.
 */

public class MyAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Student> studentList;

    public MyAdapter(Activity activity, List<Student> studentList) {
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.studentList = studentList;
    }


    @Override
    public int getCount() {
        return this.studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View adapterView;
        adapterView = inflater.inflate(R.layout.adapter_layout,null);

        TextView namesurname = (TextView) adapterView.findViewById(R.id.lbl_namesurname);
        TextView age = (TextView) adapterView.findViewById(R.id.lbl_age);
        TextView clas = (TextView) adapterView.findViewById(R.id.lbl_class);
        TextView gender = (TextView) adapterView.findViewById(R.id.lbl_gender);
        TextView gpa = (TextView) adapterView.findViewById(R.id.lbl_gpa);

        Student student = studentList.get(position);

        namesurname.setText(student.getName()+" "+student.getSurname());
        age.setText(student.getAge().toString());
        clas.setText(student.getClas().toString());
        gender.setText(student.getGender());
        gpa.setText(student.getGpa().toString());

        return adapterView;
    }
}
