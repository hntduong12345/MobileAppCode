package com.example.petrial;

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

import com.example.petrial.API.Repos.SachRepo;
import com.example.petrial.API.Repos.TacgiaRepo;
import com.example.petrial.API.Services.SachService;
import com.example.petrial.API.Services.TacgiaService;
import com.example.petrial.Adapters.SachAdapter;
import com.example.petrial.Models.Sach;
import com.example.petrial.Models.Tacgia;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SachActivity extends AppCompatActivity {
    SachService sachService;
    private ListView lv_SachList;
    private List<Sach> sachList;
    private SachAdapter sachAdapter;

    private Button btn_Delete, btn_Update, btn_Add;
    private TextView tv_ItemChosenName, tv_ItemChosenID;
    private Sach selectedSach;
    ImageView imv_Back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sach);

        tv_ItemChosenID = findViewById(R.id.textViewChosenMaSach);
        tv_ItemChosenName = findViewById(R.id.textViewChosenTenSach);
        imv_Back = findViewById(R.id.imageViewSachBack);

        btn_Delete = findViewById(R.id.buttonXoaSach);
        btn_Update = findViewById(R.id.buttonSuaSach);
        btn_Add = findViewById(R.id.buttonThemSach);

        lv_SachList = findViewById(R.id.listViewSach);

        sachList = new ArrayList<>();

        sachService = SachRepo.getSachService();
        GetData();

        //Btn, ListView function
        lv_SachList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedSach = sachList.get(position);
                tv_ItemChosenID.setText(String.valueOf(selectedSach.getMasach()));
                tv_ItemChosenName.setText(selectedSach.getTensach());
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sachList != null) {
                    Delete();
                } else {
                    Toast.makeText(SachActivity.this, "Please Select Sach", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedSach != null) {
                    Update();
                } else {
                    Toast.makeText(SachActivity.this, "Please Select Sach", Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(SachActivity.this, MainActivity.class));
            }
        });
    }

    private void Create() {
        Intent intent = new Intent(SachActivity.this, SachInfoActivity.class);
        startActivity(intent);
    }

    private void Delete() {
        sachService.DeleteSach(selectedSach.getMasach())
                .enqueue(new Callback<Sach>() {
                    @Override
                    public void onResponse(Call<Sach> call, Response<Sach> response) {
                        if (response.body() != null) {
                            Toast.makeText(SachActivity.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                            GetData();

                            tv_ItemChosenID.setText("");
                            tv_ItemChosenName.setText("");
                            selectedSach = null;
                        }
                    }

                    @Override
                    public void onFailure(Call<Sach> call, Throwable t) {
                        Toast.makeText(SachActivity.this, "Delete Fail", Toast.LENGTH_LONG).show();
                    }
                });


    }

    private void Update() {
        Intent intent = new Intent(SachActivity.this, SachInfoActivity.class);
        intent.putExtra("Sach", selectedSach);
        startActivity(intent);
    }

    private void GetData() {
        sachService.GetAllSach()
                .enqueue(new Callback<Sach[]>() {
                    @Override
                    public void onResponse(Call<Sach[]> call, Response<Sach[]> response) {
                        Sach[] sachs = response.body();
                        if (sachs == null) {
                            Toast.makeText(SachActivity.this, "Cannot get sach list", Toast.LENGTH_LONG).show();
                            return;
                        }

                        sachList.clear();

                        for (Sach sach : sachs) {
                            sachList.add(sach);
                        }

                        sachAdapter = new SachAdapter(SachActivity.this, R.layout.component_sach_row, sachList);
                        lv_SachList.setAdapter(sachAdapter);
                    }

                    @Override
                    public void onFailure(Call<Sach[]> call, Throwable t) {
                        Toast.makeText(SachActivity.this, "Get Fail", Toast.LENGTH_LONG).show();
                    }
                });
    }


}
