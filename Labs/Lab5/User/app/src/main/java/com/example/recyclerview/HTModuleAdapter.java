package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.Model.HTModule;

import java.util.ArrayList;

public class HTModuleAdapter extends RecyclerView.Adapter<HTModuleAdapter.ViewHolder>{

    private ArrayList<HTModule> htmoduleList;

    public HTModuleAdapter(ArrayList<HTModule> htmoduleList) {
        this.htmoduleList = htmoduleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.htmodule_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HTModuleAdapter.ViewHolder holder, int position) {
        HTModule module = htmoduleList.get(position);
        holder.tv_Category.setText(module.getCategory());
        holder.tv_Name.setText(module.getName());
        holder.tv_Description.setText(module.getDescription());
        holder.iv_Image.setImageResource(module.getImage());
    }

    @Override
    public int getItemCount() {
        return htmoduleList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_Name, tv_Description, tv_Category;
        ImageView iv_Image;
        LinearLayout layoutAll;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            tv_Category = itemView.findViewById(R.id.tvCategory);
            tv_Name = itemView.findViewById(R.id.tvName);
            tv_Description = itemView.findViewById(R.id.tvDescription);
            iv_Image = itemView.findViewById(R.id.imageViewImg);
            layoutAll = itemView.findViewById(R.id.linearLayoutAll);
        }
    }
}
