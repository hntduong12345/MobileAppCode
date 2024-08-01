package com.example.customlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Fruit> fruitList;

    public FruitAdapter(Context context, int layout, List<Fruit> fruitList) {
        this.context = context;
        this.layout = layout;
        this.fruitList = fruitList;
    }

    @Override
    public int getCount() {
        return fruitList.size();
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

        TextView tv_Name = (TextView) convertView.findViewById(R.id.textViewTen);
        TextView tv_Description = (TextView) convertView.findViewById(R.id.textViewMota);
        ImageView imv_Picture = (ImageView) convertView.findViewById(R.id.imageViewHinh);

        Fruit fruit = fruitList.get(position);

        tv_Name.setText(fruit.getName());
        tv_Description.setText(fruit.getDescription());
        imv_Picture.setImageResource(fruit.getPicture());

        return convertView;
    }
}
