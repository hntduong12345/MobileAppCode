package com.example.customlistview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lv_Fruit;
    private ArrayList<Fruit> arrayFruit;
    private FruitAdapter adapter;

    private EditText edt_Name, edt_Description;
    private Button btn_Add, btn_Edit;
    private ImageView imv_FruitImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt_Name = (EditText) findViewById(R.id.editTextName);
        edt_Description = (EditText) findViewById(R.id.editTextDescription);
        imv_FruitImg = (ImageView) findViewById(R.id.imageViewPic);

        btn_Add = (Button) findViewById(R.id.buttonAdd);
        btn_Edit = (Button) findViewById(R.id.buttonEdit);

        Mapping();

        adapter = new FruitAdapter(this, R.layout.activity_component, arrayFruit);
        lv_Fruit.setAdapter(adapter);

        lv_Fruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                edt_Name.setText(arrayFruit.get(position).getName());
                edt_Description.setText(arrayFruit.get(position).getDescription());
                imv_FruitImg.setImageResource(arrayFruit.get(position).getPicture());
            }
        });

        lv_Fruit.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayFruit.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }

    private void Mapping() {
        lv_Fruit = findViewById(R.id.listviewFruit);
        arrayFruit = new ArrayList<>();
        arrayFruit.add(new Fruit("Apple", "Apple: red and fresh", R.drawable.apple));
        arrayFruit.add(new Fruit("Banana", "Banana: good", R.drawable.banana));
        arrayFruit.add(new Fruit("BlueBerry", "BlueBerry: oke", R.drawable.blueberry));
        arrayFruit.add(new Fruit("Corn", "Corn: delicious", R.drawable.corn));
        arrayFruit.add(new Fruit("Grapes", "Grapes: nice", R.drawable.grape));
    }
}