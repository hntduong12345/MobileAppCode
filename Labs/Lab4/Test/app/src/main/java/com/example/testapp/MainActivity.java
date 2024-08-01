package com.example.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private  int Age;
    private Button btn_NextPage;
    private EditText edt_Name, edt_Age;
    private ArrayList<String> arrayProgrammingLangague;
    private CheckBox cbx_CSharp, cbx_Java, cbx_ReactJs, cbx_NextJs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_NextPage = (Button) findViewById(R.id.buttonNextPage);
        edt_Age = (EditText) findViewById(R.id.editTextNumberAge);
        edt_Name = (EditText) findViewById(R.id.editTextTextName);

        cbx_Java = (CheckBox) findViewById(R.id.checkBoxJava);
        cbx_CSharp = (CheckBox) findViewById(R.id.checkBoxCSharp);
        cbx_NextJs = (CheckBox) findViewById(R.id.checkBox4);
        cbx_ReactJs = (CheckBox) findViewById(R.id.checkBoxReactJs);

        arrayProgrammingLangague = new ArrayList<>();

        btn_NextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(edt_Name.getText().toString())){
                    edt_Name.setError("Required");
                    return;
                }

                if(TextUtils.isEmpty(edt_Age.getText().toString())){
                    edt_Age.setError("Required");
                    return;
                }
                else{
                    Age = Integer.parseInt(edt_Age.getText().toString());
                }

                if(arrayProgrammingLangague.size() == 0){
                    arrayProgrammingLangague.add("None");
                }

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Name", edt_Name.getText().toString());
                bundle.putInt("Age", Age);
                bundle.putStringArrayList("Choice", arrayProgrammingLangague);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        cbx_ReactJs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    arrayProgrammingLangague.add(cbx_ReactJs.getText().toString());
                }
                else{
                    RemoveItem(cbx_ReactJs.getText().toString());
                }
            }
        });

        cbx_NextJs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    arrayProgrammingLangague.add(cbx_NextJs.getText().toString());
                }
                else{
                    RemoveItem(cbx_NextJs.getText().toString());
                }
            }
        });

        cbx_Java.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    arrayProgrammingLangague.add(cbx_Java.getText().toString());
                }
                else{
                    RemoveItem(cbx_Java.getText().toString());
                }
            }
        });

        cbx_CSharp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    arrayProgrammingLangague.add(cbx_CSharp.getText().toString());
                }
                else{
                    RemoveItem(cbx_CSharp.getText().toString());
                }
            }
        });
    }

    private void RemoveItem(String item){
        for(int i = 0; i<arrayProgrammingLangague.size(); i++){
            boolean isMatch = TextUtils.equals(arrayProgrammingLangague.get(i), item);
            if(isMatch){
                arrayProgrammingLangague.remove(i);
                return;
            }
        }
    }
}