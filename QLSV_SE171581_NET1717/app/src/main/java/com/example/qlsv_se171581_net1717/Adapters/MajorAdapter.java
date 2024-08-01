package com.example.qlsv_se171581_net1717.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.qlsv_se171581_net1717.Models.Major;
import com.example.qlsv_se171581_net1717.R;

import java.util.List;

public class MajorAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Major> majorList;

    public MajorAdapter(Context context, int layout, List<Major> majorList) {
        this.context = context;
        this.layout = layout;
        this.majorList = majorList;
    }

    @Override
    public int getCount() {
        return majorList.size();
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

        TextView tv_MajorId = convertView.findViewById(R.id.textViewMajorId);
        TextView tv_MajorName = convertView.findViewById(R.id.textViewMajorName);

        Major major = majorList.get(position);

        tv_MajorId.setText(String.valueOf(major.getIDMajor()));
        tv_MajorName.setText(major.getNameMajor());

        return convertView;
    }
}
