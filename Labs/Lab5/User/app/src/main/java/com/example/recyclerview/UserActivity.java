package com.example.recyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.Model.User;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ArrayList<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        RecyclerView rvUser = findViewById(R.id.recyclerView);

        userList = new ArrayList<>();
        userList.add(new User("huynhnguyen1", "Huynh Hoang Khoi Nguyen", "khoinguyen@gmail.com"));
        userList.add(new User("huynhnguyen2", "Huynh Hoang Khoi Nguyen", "khoinguyen@gmail.com"));
        userList.add(new User("huynhnguyen3", "Huynh Hoang Khoi Nguyen", "khoinguyen@gmail.com"));
        userList.add(new User("huynhnguyen4", "Huynh Hoang Khoi Nguyen", "khoinguyen@gmail.com"));
        userList.add(new User("huynhnguyen5", "Huynh Hoang Khoi Nguyen", "khoinguyen@gmail.com"));
        userList.add(new User("huynhnguyen6", "Huynh Hoang Khoi Nguyen", "khoinguyen@gmail.com"));
        userList.add(new User("huynhnguyen7", "Huynh Hoang Khoi Nguyen", "khoinguyen@gmail.com"));
        userList.add(new User("huynhnguyen8", "Huynh Hoang Khoi Nguyen", "khoinguyen@gmail.com"));

        UserAdapter adapter = new UserAdapter(userList);

        rvUser.setLayoutManager(new LinearLayoutManager(this));

        rvUser.setAdapter(adapter);

    }
}
