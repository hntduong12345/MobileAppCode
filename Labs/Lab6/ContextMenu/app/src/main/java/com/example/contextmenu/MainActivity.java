package com.example.contextmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn_ChooseColor;
    ConstraintLayout csl_Screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_ChooseColor = (Button) findViewById(R.id.buttonChooseColor);
        csl_Screen = (ConstraintLayout) findViewById(R.id.main);

        registerForContextMenu(btn_ChooseColor);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);

//        menu.setHeaderTitle("Chọn Màu");
//        menu.setHeaderIcon(R.drawable.ic_launcher_background);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuitemRed: csl_Screen.setBackgroundColor(Color.RED); break;
            case R.id.menuitemBlue: csl_Screen.setBackgroundColor(Color.BLUE); break;
            case R.id.menuitemYellow: csl_Screen.setBackgroundColor(Color.YELLOW); break;
        }
        return super.onContextItemSelected(item);
    }
}