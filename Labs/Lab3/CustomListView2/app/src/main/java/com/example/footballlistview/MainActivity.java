package com.example.footballlistview;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv_Player;
    ArrayList<FootBallPlayer> playerList;
    FootBallPlayerAdpater adpater;

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

        Reference();
        adpater = new FootBallPlayerAdpater(this, R.layout.football_player_line, playerList);
        lv_Player.setAdapter(adpater);
    }

    private void Reference() {
        lv_Player = (ListView) findViewById(R.id.lvPlayer);

        playerList = new ArrayList<>();
        playerList.add(new FootBallPlayer("Pele", "October 23, 1940 (age 72)", R.drawable.pele, R.drawable.brazil));
        playerList.add(new FootBallPlayer("Diego Maradona", "October 30, 1960 (age 52)", R.drawable.maradona, R.drawable.argentina));
        playerList.add(new FootBallPlayer("Johan Cruyff", "April 25, 1947 (age 65)", R.drawable.johan_cruyff, R.drawable.netherlands));
        playerList.add(new FootBallPlayer("Franz Beckenbauer", "September 11, 1945 (age 67)", R.drawable.beckenbauer, R.drawable.germany));
        playerList.add(new FootBallPlayer("Michel Platini", "June 21, 1955 (age 57)", R.drawable.platini, R.drawable.france));
        playerList.add(new FootBallPlayer("Ronaldo De Lima", "September 22, 1976 (age 36)", R.drawable.ronaldo_de_lima, R.drawable.brazil));
    }
}