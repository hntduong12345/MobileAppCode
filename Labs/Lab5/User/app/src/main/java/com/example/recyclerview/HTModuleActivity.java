package com.example.recyclerview;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.Model.HTModule;

import java.util.ArrayList;

public class HTModuleActivity extends AppCompatActivity {
    ArrayList<HTModule> moduleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htmodule);

        RecyclerView rvModule = findViewById(R.id.recyclerViewCustom);

        moduleList = new ArrayList<>();
        moduleList.add(new HTModule("AE 101 Solar Energy Trainer", "The unit is a portable training system for studying the principles of solar energy, storage and conversion", "Alternative Energy", R.drawable.solar_energy_trainer));
        moduleList.add(new HTModule("Laser", "A device that generates an intense beam of coherent monochromatic ligh", "Android 7.0", R.drawable.laser));
        moduleList.add(new HTModule("GPS", "A space-based radio-navigation system consisting of a constellation of satellites broadcasting navigation signals and a network of ground stations and satellite control stations used for monitoring and control", "Android 14.0", R.drawable.gps));

        rvModule.setLayoutManager(new LinearLayoutManager(this));

        HTModuleAdapter adapter = new HTModuleAdapter(moduleList);

        rvModule.setAdapter(adapter);
    }
}
