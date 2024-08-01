package com.example.labsum8910.RoomDB.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labsum8910.R;
import com.example.labsum8910.RoomDB.Constants.Constants;
import com.example.labsum8910.RoomDB.EditPersonActivity;
import com.example.labsum8910.RoomDB.Models.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    private Context context;
    private List<Person> mPersonList;

    public PersonAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_person_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Person person = mPersonList.get(position);

        holder.firstName.setText(person.getFirstName());
        holder.lastName.setText(person.getLastName());
    }

    @Override
    public int getItemCount() {
        if (mPersonList == null) {
            return 0;
        }

        return mPersonList.size();
    }

    public void setTask(List<Person> personList) {
        mPersonList = personList;
        notifyDataSetChanged();
    }

    public List<Person> getTask() {
        return mPersonList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView firstName, lastName;
        ImageView editImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            firstName = itemView.findViewById(R.id.textViewFirstName);
            lastName = itemView.findViewById(R.id.textViewLastName);

            editImage = itemView.findViewById(R.id.imageViewEdit);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int elementId = mPersonList.get(getAdapterPosition()).getUid();
                    Intent i = new Intent(context, EditPersonActivity.class);
                    i.putExtra(Constants.UPDATE_Person_Id, elementId);
                    context.startActivity(i);
                }
            });
        }
    }
}
