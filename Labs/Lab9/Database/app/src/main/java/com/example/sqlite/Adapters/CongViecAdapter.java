package com.example.sqlite.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sqlite.CongViec;
import com.example.sqlite.MainActivity;
import com.example.sqlite.R;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {
    private MainActivity context;
    private  int layout;
    private List<CongViec> congViecList;

    public CongViecAdapter(MainActivity context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView tvTen;
        ImageView ivDelete, ivEdit;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.tvTen = (TextView) convertView.findViewById(R.id.textviewTen);
            holder.ivDelete = (ImageView) convertView.findViewById(R.id.imageviewDelete);
            holder.ivEdit = (ImageView) convertView.findViewById(R.id.imageviewEdit);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        CongViec congViec = congViecList.get(position);
        holder.tvTen.setText(congViec.getTenCV());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogSuaCongViec(congViec.getTenCV(), congViec.getIdCV());
            }
        });

        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogXoaCongViec(congViec.getTenCV(), congViec.getIdCV());
            }
        });

        return convertView;
    }
}
