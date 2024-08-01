package com.example.lab567;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class PopUpMenuActivity extends AppCompatActivity {

    Button btn_PopupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_popup_menu);

        btn_PopupMenu = (Button) findViewById(R.id.buttonPopupMenuAction);

        btn_PopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMenu();
            }
        });
    }

    private void ShowMenu(){
        PopupMenu popupMenu = new PopupMenu(this, btn_PopupMenu);
        popupMenu.getMenuInflater().inflate(R.menu.menu_pop_up, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menuitemAdd: btn_PopupMenu.setText("Menu Them"); break;
                    case R.id.menuitemUpdate: btn_PopupMenu.setText("Menu Sua"); break;
                    case R.id.menuitemDelete: btn_PopupMenu.setText("Menu Xoa"); break;

                }
                return false;
            }
        });
        popupMenu.show();
    }
}
