package com.example.basicauthorizesystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.basicauthorizesystem.Adapters.FoodAndDrinkAdapter;
import com.example.basicauthorizesystem.Models.FoodAndDrink;

import java.io.Serializable;
import java.util.ArrayList;

public class FoodOrderActivity extends AppCompatActivity {
    private Button btn_ChooseFood, btn_ChooseDrink, btn_Main;

    private ListView lv_Orders;
    private ArrayList<FoodAndDrink> arrayOrder;
    private FoodAndDrinkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_food_order);

        btn_ChooseFood = (Button) findViewById(R.id.buttonChooseFood);
        btn_ChooseDrink = (Button) findViewById(R.id.buttonChooseDrink);
        btn_Main = (Button) findViewById(R.id.buttonMain);

        lv_Orders = findViewById(R.id.listViewOrders);

        Intent intent = this.getIntent();
        if(intent.hasExtra("Order")){
            arrayOrder = (ArrayList<FoodAndDrink>) intent.getExtras().getSerializable("Order");
        }
        else{
            arrayOrder = new ArrayList<>();
        }

        adapter = new FoodAndDrinkAdapter(this, R.layout.activity_order_list_component, arrayOrder);
        lv_Orders.setAdapter(adapter);

        btn_ChooseFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FoodOrderActivity.this, FoodActivity.class);
                intent1.putExtra("Order", (Serializable) arrayOrder);
                startActivity(intent1);
                finish();
            }
        });

        btn_ChooseDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FoodOrderActivity.this, DrinkActivity.class);
                intent1.putExtra("Order", (Serializable) arrayOrder);
                startActivity(intent1);
                finish();
            }
        });

        lv_Orders.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayOrder.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        btn_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodOrderActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_order, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
