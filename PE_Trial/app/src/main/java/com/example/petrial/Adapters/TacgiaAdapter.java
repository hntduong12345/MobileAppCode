package com.example.petrial.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.petrial.Models.Tacgia;
import com.example.petrial.R;

import java.util.List;

public class TacgiaAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Tacgia> tacgiaList;

    public TacgiaAdapter(Context context, int layout, List<Tacgia> tacgiaList) {
        this.context = context;
        this.layout = layout;
        this.tacgiaList = tacgiaList;
    }

    @Override
    public int getCount() {
        return tacgiaList.size();
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

        TextView tv_IDTacGia = (TextView) convertView.findViewById(R.id.textViewIdTacGia);
        TextView tv_TenTacGia = (TextView) convertView.findViewById(R.id.textViewTenTG);
        TextView tv_DiaChi = (TextView) convertView.findViewById(R.id.textViewDiachi);
        TextView tv_SDT = (TextView) convertView.findViewById(R.id.textViewSDT);

        Tacgia tacgia = tacgiaList.get(position);

        tv_SDT.setText(tacgia.dienthoai);
        tv_IDTacGia.setText(String.valueOf(tacgia.IDTacgia));
        tv_DiaChi.setText(tacgia.Diachi);
        tv_TenTacGia.setText(tacgia.TenTacGia);

        return convertView;
    }
}
