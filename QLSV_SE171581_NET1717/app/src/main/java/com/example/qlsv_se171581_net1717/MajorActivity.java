package com.example.qlsv_se171581_net1717;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsv_se171581_net1717.API.Repos.MajorRepo;
import com.example.qlsv_se171581_net1717.API.Services.MajorService;
import com.example.qlsv_se171581_net1717.Adapters.MajorAdapter;
import com.example.qlsv_se171581_net1717.Models.Major;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MajorActivity extends AppCompatActivity {

    MajorService majorService;
    ListView lv_Major;
    List<Major> majorList;
    MajorAdapter majorAdapter;

    Button btn_Delete, btn_Add, btn_Update;
    private TextView tv_ChosenID, tv_ChosenName;
    private Major selectedMajor;
    ImageView imv_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_major);

        tv_ChosenID = findViewById(R.id.textViewChosenMajorId);
        tv_ChosenName = findViewById(R.id.textViewChosenMajorName);
        imv_Back = findViewById(R.id.imageViewMajorBack);

        btn_Add = findViewById(R.id.buttonAddMajor);
        btn_Delete = findViewById(R.id.buttonDeleteMajor);
        btn_Update = findViewById(R.id.buttonUpdateMajor);

        lv_Major = findViewById(R.id.listViewMajor);

        majorService = MajorRepo.getMajorService();
        majorList = new ArrayList<>();

        FetchData();

        //Action Event Function
        lv_Major.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedMajor = majorList.get(position);
                tv_ChosenID.setText(String.valueOf(selectedMajor.getIDMajor()));
                tv_ChosenName.setText(selectedMajor.getNameMajor());
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMajor != null) {
                    Delete();
                } else {
                    Toast.makeText(MajorActivity.this, "Please Select Major", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedMajor != null) {
                    Update();
                } else {
                    Toast.makeText(MajorActivity.this, "Please Select Major", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        imv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MajorActivity.this, MainActivity.class));
            }
        });
    }

    private void Create() {
        Intent intent = new Intent(MajorActivity.this, MajorInfoFunctionActivity.class);
        startActivity(intent);
        finish();
    }

    private void Delete() {
        majorService.DeleteMajor(selectedMajor.getIDMajor())
                .enqueue(new Callback<Major>() {
                    @Override
                    public void onResponse(Call<Major> call, Response<Major> response) {
                        if (response.body() != null) {
                            Toast.makeText(MajorActivity.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                            FetchData();

                            tv_ChosenID.setText("");
                            tv_ChosenName.setText("");
                            selectedMajor = null;
                        }
                    }

                    @Override
                    public void onFailure(Call<Major> call, Throwable t) {
                        Toast.makeText(MajorActivity.this, "Delete Fail", Toast.LENGTH_LONG).show();
                    }
                });


    }

    private void Update() {
        Intent intent = new Intent(MajorActivity.this, MajorInfoFunctionActivity.class);
        intent.putExtra("Major", selectedMajor);
        startActivity(intent);
        finish();
    }

    private void FetchData() {
        majorService.GetAllMajor()
                .enqueue(new Callback<Major[]>() {
                    @Override
                    public void onResponse(Call<Major[]> call, Response<Major[]> response) {
                        Major[] majors = response.body();
                        if (majors == null) {
                            Toast.makeText(MajorActivity.this, "Cannot get major list", Toast.LENGTH_LONG).show();
                            return;
                        }

                        majorList.clear();

                        for (Major major : majors) {
                            majorList.add(major);
                        }

                        majorAdapter = new MajorAdapter(MajorActivity.this, R.layout.component_major_row, majorList);
                        lv_Major.setAdapter(majorAdapter);
                    }

                    @Override
                    public void onFailure(Call<Major[]> call, Throwable t) {
                        Toast.makeText(MajorActivity.this, "Get Fail", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
