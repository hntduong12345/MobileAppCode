package com.example.basicauthorizesystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.basicauthorizesystem.Models.FoodAndDrink;
import com.example.basicauthorizesystem.R;

import java.util.List;

public class FoodAndDrinkAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<FoodAndDrink> foodAndDrinkList;

    public FoodAndDrinkAdapter(Context context, int layout, List<FoodAndDrink> foodAndDrinkList) {
        this.context = context;
        this.layout = layout;
        this.foodAndDrinkList = foodAndDrinkList;
    }

    @Override
    public int getCount() {
        return foodAndDrinkList.size();
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

        TextView tv_Name = (TextView) convertView.findViewById(R.id.textViewName);
        TextView tv_Description = (TextView) convertView.findViewById(R.id.textViewPrice);
        ImageView imv_Picture = (ImageView) convertView.findViewById(R.id.imageViewImg);

        FoodAndDrink foodAndDrink = foodAndDrinkList.get(position);

        tv_Name.setText(foodAndDrink.getName());
        tv_Description.setText(foodAndDrink.getPrice());
        imv_Picture.setImageResource(foodAndDrink.getPicture());

        return convertView;
    }
}
