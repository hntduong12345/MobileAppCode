package com.example.basicauthorizesystem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.basicauthorizesystem.Models.FootBallPlayer;
import com.example.basicauthorizesystem.R;

import java.util.List;

public class FootBallPlayerAdpater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<FootBallPlayer> playerList;

    public FootBallPlayerAdpater(Context context, int layout, List<FootBallPlayer> playerList) {
        this.context = context;
        this.layout = layout;
        this.playerList = playerList;
    }

    @Override
    public int getCount() {
        return playerList.size();
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

        TextView tv_Name = convertView.findViewById(R.id.txtName);
        TextView tv_BirthDay = convertView.findViewById(R.id.txtBirthday);
        ImageView iv_Player = convertView.findViewById(R.id.imgPlayer);
        ImageView iv_Flag = convertView.findViewById(R.id.imgFlag);

        FootBallPlayer player = playerList.get(position);

        tv_Name.setText(player.getName());
        tv_BirthDay.setText(player.getBirthDay());
        iv_Player.setImageResource(player.getPlayerImg());
        iv_Flag.setImageResource(player.getFlagImg());

        return convertView;
    }
}
