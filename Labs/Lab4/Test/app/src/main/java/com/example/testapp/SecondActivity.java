package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private TextView tv_Info;
    private String Name;
    private int Age;
    private ArrayList<String> arrayChoice;
    private ListView lv_Choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);

        Bundle bundle = getIntent().getExtras();
        Name = bundle.getString("Name");
        Age = bundle.getInt("Age");
        arrayChoice = bundle.getStringArrayList("Choice");

        lv_Choice = findViewById(R.id.listViewChoice);
        tv_Info = (TextView) findViewById(R.id.textViewInfo);
        tv_Info.setText(String.format("Your Name:%s| Age:%d", Name, Age));

        ArrayAdapter adapter = new ArrayAdapter(
                SecondActivity.this,
                android.R.layout.simple_list_item_1,
                arrayChoice
        );
        lv_Choice.setAdapter(adapter);
    }
}
