package com.example.foodorder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Button btn_ChooseFood, btn_ChooseDrink, btn_Quit;

    private ListView lv_Orders;
    private ArrayList<FoodAndDrink> arrayOrder;
    private FoodAndDrinkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_ChooseFood = (Button) findViewById(R.id.buttonChooseFood);
        btn_ChooseDrink = (Button) findViewById(R.id.buttonChooseDrink);
        btn_Quit = (Button) findViewById(R.id.buttonQuit);

        lv_Orders = findViewById(R.id.listViewOrders);

        Intent intent = this.getIntent();
        if(intent.hasExtra("Order")){
            arrayOrder = (ArrayList<FoodAndDrink>) intent.getExtras().getSerializable("Order");
        }
        else{
            arrayOrder = new ArrayList<>();
        }

        adapter = new FoodAndDrinkAdapter(this, R.layout.activity_custom_list_component, arrayOrder);
        lv_Orders.setAdapter(adapter);

        btn_ChooseFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, FoodActivity.class);
                intent1.putExtra("Order", (Serializable) arrayOrder);
                startActivity(intent1);
                finish();
            }
        });

        btn_ChooseDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, DrinkActivity.class);
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

        btn_Quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}