package com.example.petrial.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.petrial.Models.Sach;
import com.example.petrial.R;

import java.util.List;

public class SachAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Sach> sachList;

    public SachAdapter(Context context, int layout, List<Sach> sachList) {
        this.context = context;
        this.layout = layout;
        this.sachList = sachList;
    }

    @Override
    public int getCount() {
        return sachList.size();
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
        LayoutInflater inflater =(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView tv_MaSach = (TextView) convertView.findViewById(R.id.textViewMaSach);
        TextView tv_TenSach = (TextView) convertView.findViewById(R.id.textViewName);
        TextView tv_NXB = (TextView) convertView.findViewById(R.id.textViewNXB);
        TextView tv_TheLoai = (TextView) convertView.findViewById(R.id.textViewTheLoai);
        TextView tv_TacGia = (TextView) convertView.findViewById(R.id.textViewTacGia);

        Sach sach = sachList.get(position);
        tv_MaSach.setText(String.valueOf(sach.Masach));
        tv_NXB.setText(String.valueOf(sach.NgayXB));
        tv_TenSach.setText(sach.Tensach);
        tv_TheLoai.setText(sach.Theloai);
        tv_TacGia.setText(sach.getIdTacgia());

        return convertView;
    }
}
