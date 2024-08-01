package com.example.labsum8910;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btn_Notification, btn_Sqlite, btn_RoomDB, btn_ApiCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_Notification = (Button) findViewById(R.id.buttonNotifacation);
        btn_Sqlite = (Button) findViewById(R.id.buttonSqlite);
        btn_RoomDB = (Button) findViewById(R.id.buttonRoomDB);
        btn_ApiCall = (Button) findViewById(R.id.buttonApiCall);

        btn_Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NotificationMainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btn_Sqlite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SQLiteMainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btn_RoomDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RoomDBMainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btn_ApiCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ApiCallMainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}