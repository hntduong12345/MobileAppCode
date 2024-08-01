package com.example.api;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.api.API.TraineeRepository;
import com.example.api.API.TraineeService;
import com.example.api.Adapters.TraineeAdapter;
import com.example.api.Models.Trainee;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewActivity extends AppCompatActivity {
    TraineeService traineeService;

    private ListView lv_Trainee;
    private List<Trainee> arrayTrainee = new ArrayList<>();
    private TraineeAdapter adapter;

    private Button btn_Delete, btn_Update, btn_Add;
    private TextView tv_ItemChosen;
    private Trainee selectedTrainee;

    private int save = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);

        tv_ItemChosen = findViewById(R.id.textViewItemChosen);

        btn_Delete = findViewById(R.id.buttonDelete);
        btn_Update = findViewById(R.id.buttonUpdate);
        btn_Add = findViewById(R.id.buttonAdd);
        lv_Trainee = findViewById(R.id.listViewNhanVien);

        traineeService = TraineeRepository.getTraineeService();
        GetData();

        lv_Trainee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTrainee = arrayTrainee.get(position);
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTrainee != null) {
                    Delete();
                } else {
                    Toast.makeText(ViewActivity.this, "Please Select Trainee", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTrainee != null) {
                    Update();
                } else {
                    Toast.makeText(ViewActivity.this, "Please Select Trainee", Toast.LENGTH_LONG).show();
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

    private void Create() {
        Intent intent = new Intent(ViewActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void Delete() {
        traineeService.DeleteTrainee(selectedTrainee.getId())
                .enqueue(new Callback<Trainee>() {
                    @Override
                    public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                        if (response.body() != null) {
                            Toast.makeText(ViewActivity.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                            GetData();
                        }
                    }

                    @Override
                    public void onFailure(Call<Trainee> call, Throwable t) {
                        Toast.makeText(ViewActivity.this, "Delete Fail", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void Update() {
        Intent intent = new Intent(ViewActivity.this, MainActivity.class);
        intent.putExtra("Trainee", selectedTrainee);
        startActivity(intent);
        finish();
    }

    private void GetData() {
        traineeService.GetAllTrainees()
                .enqueue(new Callback<Trainee[]>() {
                    @Override
                    public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                        Trainee[] trainees = response.body();
                        if (trainees == null) {
                            Toast.makeText(ViewActivity.this, "Cannot get trainee list", Toast.LENGTH_LONG).show();
                            return;
                        }

                        arrayTrainee.clear();

                        for (Trainee trainee : trainees) {
                            arrayTrainee.add(trainee);
                        }

                        adapter = new TraineeAdapter(ViewActivity.this, R.layout.activity_component_nhan_view, arrayTrainee);
                        lv_Trainee.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Trainee[]> call, Throwable t) {
                        Toast.makeText(ViewActivity.this, "Get Fail", Toast.LENGTH_LONG).show();
                    }
                });
    }


}
