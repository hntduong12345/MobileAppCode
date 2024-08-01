package com.example.lab567.RecycleListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab567.R;
import com.example.lab567.RecycleListView.Model.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<User> userList;
    public UserAdapter(ArrayList<User> userList){
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.user_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = userList.get(position);
        holder.tvUserName.setText("Username: " + user.getUsername());
        holder.tvFullName.setText("Fullname: " + user.getFullname());
        holder.tvEmail.setText("Email " + user.getEmail());

        
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvUserName;
        TextView tvFullName;
        TextView tvEmail;

        public ViewHolder(@NonNull View row_userlist){
            super(row_userlist);

            tvUserName = row_userlist.findViewById(R.id.tvUserName);
            tvFullName = row_userlist.findViewById(R.id.tvFullName);
            tvEmail = row_userlist.findViewById(R.id.tvEmail);
        }
    }
}
