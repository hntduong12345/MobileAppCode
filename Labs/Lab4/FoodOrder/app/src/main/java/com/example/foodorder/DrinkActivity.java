package com.example.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class DrinkActivity extends AppCompatActivity {
    private ListView lv_Drink;
    private ArrayList<FoodAndDrink> arrayDrink, arrayOrder;
    private FoodAndDrinkAdapter adapter;
    private FoodAndDrink chooseItem;
    private Button btn_Order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_drink);

        btn_Order = (Button) findViewById(R.id.buttonDrinkOrder);

        Intent intent = this.getIntent();
        if (intent.hasExtra("Order")) {
            arrayOrder = (ArrayList<FoodAndDrink>) intent.getExtras().getSerializable("Order");
        } else {
            arrayOrder = new ArrayList<>();
        }

        Mapping();

        adapter = new FoodAndDrinkAdapter(this, R.layout.activity_custom_list_component, arrayDrink);
        lv_Drink.setAdapter(adapter);

        lv_Drink.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chooseItem = arrayDrink.get(position);
                Toast.makeText(DrinkActivity.this, "You have choose " + chooseItem.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayOrder.add(chooseItem);
                Intent intent = new Intent(DrinkActivity.this, MainActivity.class);
                intent.putExtra("Order", (Serializable) arrayOrder);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Mapping(){
        lv_Drink = findViewById(R.id.listViewDrink);
        arrayDrink = new ArrayList<>();
        arrayDrink.add((new FoodAndDrink("Pepsi", "10.000VNĐ", "Drink", R.drawable.pepsi)));
        arrayDrink.add((new FoodAndDrink("Heineken", "17.600VNĐ", "Drink", R.drawable.heineken)));
        arrayDrink.add((new FoodAndDrink("Tiger", "17.000VNĐ", "Drink", R.drawable.tiger)));
        arrayDrink.add((new FoodAndDrink("Sài Gòn Đỏ", "15.000VNĐ", "Drink", R.drawable.sai_gon_do)));
    }
}
