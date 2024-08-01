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

import com.example.petrial.API.Repos.TacgiaRepo;
import com.example.petrial.API.Services.TacgiaService;
import com.example.petrial.Adapters.TacgiaAdapter;
import com.example.petrial.Models.Tacgia;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TacgiaActivity extends AppCompatActivity {
    TacgiaService tacgiaService;

    private ListView lv_TacgiaList;
    private List<Tacgia> listTacGia;
    private TacgiaAdapter tacgiaAdapter;

    private Button btn_Delete, btn_Update, btn_Add;
    private TextView tv_ItemChosenID, tv_ItemChosenName;
    private Tacgia selectedTacgia;
    ImageView imv_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tacgia);

        tv_ItemChosenID = findViewById(R.id.textViewChosenId);
        tv_ItemChosenName = findViewById(R.id.textViewChosenName);
        imv_Back = findViewById(R.id.imageViewTGBack);

        btn_Delete = findViewById(R.id.buttonXoaTacGia);
        btn_Update = findViewById(R.id.buttonSuaTacGia);
        btn_Add = findViewById(R.id.buttonThemTacGia);
        lv_TacgiaList = findViewById(R.id.listViewTacgia);

        tacgiaService = TacgiaRepo.getTacgiaService();
        listTacGia = new ArrayList<>();

        GetData();
        lv_TacgiaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTacgia = listTacGia.get(position);
                tv_ItemChosenID.setText(String.valueOf(selectedTacgia.getIDTacgia()));
                tv_ItemChosenName.setText(selectedTacgia.getTenTacgia());
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTacgia != null) {
                    Delete();
                } else {
                    Toast.makeText(TacgiaActivity.this, "Please Select Tac Gia", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTacgia != null) {
                    Update();
                } else {
                    Toast.makeText(TacgiaActivity.this, "Please Select Tac Gia", Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(TacgiaActivity.this, MainActivity.class));
            }
        });
    }

    private void Create() {
        Intent intent = new Intent(TacgiaActivity.this, TacgiaInfoActivity.class);
        startActivity(intent);
        finish();
    }

    private void Delete() {
        tacgiaService.DeleteTacgia(selectedTacgia.getIDTacgia())
                .enqueue(new Callback<Tacgia>() {
                    @Override
                    public void onResponse(Call<Tacgia> call, Response<Tacgia> response) {
                        if (response.body() != null) {
                            Toast.makeText(TacgiaActivity.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                            GetData();

                            tv_ItemChosenID.setText("");
                            tv_ItemChosenName.setText("");
                            selectedTacgia = null;
                        }
                    }

                    @Override
                    public void onFailure(Call<Tacgia> call, Throwable t) {
                        Toast.makeText(TacgiaActivity.this, "Delete Fail", Toast.LENGTH_LONG).show();
                    }
                });


    }

    private void Update() {
        Intent intent = new Intent(TacgiaActivity.this, TacgiaInfoActivity.class);
        intent.putExtra("TacGia", selectedTacgia);
        startActivity(intent);
        finish();
    }

    private void GetData() {
        tacgiaService.GetAllTacgia()
                .enqueue(new Callback<Tacgia[]>() {
                    @Override
                    public void onResponse(Call<Tacgia[]> call, Response<Tacgia[]> response) {
                        Tacgia[] tacgias = response.body();
                        if (tacgias == null) {
                            Toast.makeText(TacgiaActivity.this, "Cannot get tac gia list", Toast.LENGTH_LONG).show();
                            return;
                        }

                        listTacGia.clear();

                        for (Tacgia tacgia : tacgias) {
                            listTacGia.add(tacgia);
                        }

                        tacgiaAdapter = new TacgiaAdapter(TacgiaActivity.this, R.layout.component_tacgia_row, listTacGia);
                        lv_TacgiaList.setAdapter(tacgiaAdapter);
                    }

                    @Override
                    public void onFailure(Call<Tacgia[]> call, Throwable t) {
                        Toast.makeText(TacgiaActivity.this, "Get Fail", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
