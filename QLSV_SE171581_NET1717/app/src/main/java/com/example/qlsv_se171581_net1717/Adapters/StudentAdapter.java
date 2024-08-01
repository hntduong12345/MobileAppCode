package com.example.qlsv_se171581_net1717.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qlsv_se171581_net1717.Models.Major;
import com.example.qlsv_se171581_net1717.Models.Student;
import com.example.qlsv_se171581_net1717.R;

import java.util.List;

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Student> studentList;

    public StudentAdapter(Context context, int layout, List<Student> studentList) {
        this.context = context;
        this.layout = layout;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView tv_SID = convertView.findViewById(R.id.textViewStudentID);
        TextView tv_SName = convertView.findViewById(R.id.textViewStudentName);
        TextView tv_SDate = convertView.findViewById(R.id.textViewStudentDate);
        TextView tv_SGender = convertView.findViewById(R.id.textViewStudentGender);
        TextView tv_SEmail = convertView.findViewById(R.id.textViewEmail);
        TextView tv_SAddress = convertView.findViewById(R.id.textViewAddress);
        TextView tv_SMajor = convertView.findViewById(R.id.textViewMajor);

        Student student = studentList.get(position);

        tv_SID.setText(String.valueOf(student.getID()));
        tv_SName.setText(student.getName());
        tv_SDate.setText(student.getDate());
        tv_SGender.setText(student.getGender());
        tv_SEmail.setText(student.getEmail());
        tv_SAddress.setText(student.getAddress());
        tv_SMajor.setText(student.getIdMajor()); //Can convert to major name

        return convertView;
    }
}
