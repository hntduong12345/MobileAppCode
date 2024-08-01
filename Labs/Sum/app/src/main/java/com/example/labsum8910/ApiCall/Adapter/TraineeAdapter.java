package com.example.labsum8910.ApiCall.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.labsum8910.ApiCall.Models.Trainee;
import com.example.labsum8910.R;

import java.util.List;

public class TraineeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Trainee> traineesList;

    public TraineeAdapter(Context context, int layout, List<Trainee> traineesList) {
        this.context = context;
        this.layout = layout;
        this.traineesList = traineesList;
    }

    @Override
    public int getCount() {
        return traineesList.size();
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

        TextView tv_id = (TextView) convertView.findViewById(R.id.textViewId);
        TextView tv_Name = (TextView) convertView.findViewById(R.id.textViewName);
        TextView tv_Email = (TextView) convertView.findViewById(R.id.textViewEmail);
        TextView tv_Phone = (TextView) convertView.findViewById(R.id.textViewPhone);
        TextView tv_Gender = (TextView) convertView.findViewById(R.id.textViewGender);

        Trainee trainee = traineesList.get(position);

        tv_id.setText(String.valueOf(trainee.getId()));
        tv_Name.setText(trainee.getName());
        tv_Email.setText(trainee.getEmail());
        tv_Phone.setText(trainee.getPhone());
        tv_Gender.setText(trainee.getGender());

        return convertView;
    }
}
