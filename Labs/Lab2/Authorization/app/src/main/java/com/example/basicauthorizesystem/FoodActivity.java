package com.example.basicauthorizesystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basicauthorizesystem.Adapters.FoodAndDrinkAdapter;
import com.example.basicauthorizesystem.Models.FoodAndDrink;

import java.io.Serializable;
import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
    private ListView lv_Food;
    private ArrayList<FoodAndDrink> arrayFood, arrayOrder;
    private FoodAndDrinkAdapter adapter;
    private FoodAndDrink chooseItem;
    private Button btn_Order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food);

        btn_Order = (Button) findViewById(R.id.buttonFoodOrder);

        Intent intent = this.getIntent();
        if (intent.hasExtra("Order")) {
            arrayOrder = (ArrayList<FoodAndDrink>) intent.getExtras().getSerializable("Order");
        } else {
            arrayOrder = new ArrayList<>();
        }

        Mapping();

        adapter = new FoodAndDrinkAdapter(this, R.layout.activity_order_list_component, arrayFood);
        lv_Food.setAdapter(adapter);

        lv_Food.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chooseItem = arrayFood.get(position);
                Toast.makeText(FoodActivity.this, "You have choose " + chooseItem.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        btn_Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayOrder.add(chooseItem);
                Intent intent = new Intent(FoodActivity.this, FoodOrderActivity.class);
                intent.putExtra("Order", (Serializable) arrayOrder);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Mapping() {
        lv_Food = findViewById(R.id.listViewFood);
        arrayFood = new ArrayList<>();
        arrayFood.add((new FoodAndDrink("Phở Hà Nội", "40.000VNĐ", "Food", R.drawable.pho)));
        arrayFood.add((new FoodAndDrink("Bún Bò Huế", "30.000VNĐ", "Food", R.drawable.bun)));
        arrayFood.add((new FoodAndDrink("Mỳ Quảng", "25.000VNĐ", "Food", R.drawable.mi_quang)));
        arrayFood.add((new FoodAndDrink("Hủ Tiếu Sài Gòn", "30.000VNĐ", "Food", R.drawable.hu_tieu)));
    }
}
