package com.example.lab567;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab567.RecycleListView.HTModuleActivity;
import com.example.lab567.RecycleListView.UserActivity;

public class MainActivity extends AppCompatActivity {

    Button btn_ContextMenu, btn_OptionMenu, btn_PopUpMenu, btn_Permission,btn_OtherPermisssion, btn_UserRLV, btn_HTTModuleRLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn_ContextMenu = (Button) findViewById(R.id.buttonContextMenu);
        btn_OptionMenu = (Button) findViewById(R.id.buttonOptionMenu);
        btn_PopUpMenu = (Button) findViewById(R.id.buttonPopupMenu);
        btn_Permission = (Button) findViewById(R.id.buttonCheckPermission);
        btn_OtherPermisssion = (Button) findViewById(R.id.buttonOtherPermission);
        btn_HTTModuleRLV = (Button) findViewById(R.id.buttonCustomRLV);
        btn_UserRLV = (Button) findViewById(R.id.buttonUserRLV);


        btn_ContextMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ContextMenuActivity.class));
            }
        });

        btn_OptionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OptionMenuActivity.class));
            }
        });
        btn_PopUpMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PopUpMenuActivity.class));
            }
        });
        btn_Permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PermissionActivity.class));
            }
        });
        btn_OtherPermisssion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OtherPermissionActivity.class));
            }
        });
        btn_UserRLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserActivity.class));
            }
        });
        btn_HTTModuleRLV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HTModuleActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}