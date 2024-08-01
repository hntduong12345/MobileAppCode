package com.example.labsum8910;

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

import com.example.labsum8910.ApiCall.API.TraineeRepository;
import com.example.labsum8910.ApiCall.API.TraineeService;
import com.example.labsum8910.ApiCall.Adapter.TraineeAdapter;
import com.example.labsum8910.ApiCall.Models.Trainee;
import com.example.labsum8910.ApiCall.NhanVienInfoActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallMainActivity extends AppCompatActivity {
    TraineeService traineeService;

    private ListView lv_Trainee;
    private List<Trainee> arrayTrainee = new ArrayList<>();
    private TraineeAdapter adapter;

    private Button btn_Delete, btn_Update, btn_Add;
    private Trainee selectedTrainee;
    private ImageView iv_Back;

    private TextView tv_ChosenId, tv_ChosenName;
    private int save = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_api_call_main);

        iv_Back = (ImageView) findViewById(R.id.imageViewBackApiCall);

        btn_Delete = findViewById(R.id.buttonDelete);
        btn_Update = findViewById(R.id.buttonUpdate);
        btn_Add = findViewById(R.id.buttonAdd);
        lv_Trainee = findViewById(R.id.listViewNhanVien);

        tv_ChosenId = (TextView) findViewById(R.id.textViewChosenId);
        tv_ChosenName = (TextView) findViewById(R.id.textViewChosenName);

        traineeService = TraineeRepository.getTraineeService();
        GetData();

        lv_Trainee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedTrainee = arrayTrainee.get(position);
                tv_ChosenId.setText("Id: "+ String.valueOf(selectedTrainee.getId()));
                tv_ChosenName.setText("Name: "+ selectedTrainee.getName());
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTrainee != null) {
                    Delete();
                } else {
                    Toast.makeText(ApiCallMainActivity.this, "Please Select Trainee", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTrainee != null) {
                    Update();
                } else {
                    Toast.makeText(ApiCallMainActivity.this, "Please Select Trainee", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Create();
            }
        });

        iv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ApiCallMainActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void Create() {
        Intent intent = new Intent(ApiCallMainActivity.this, NhanVienInfoActivity.class);
        startActivity(intent);
        finish();
        ;
    }

    private void Delete() {
        traineeService.DeleteTrainee(selectedTrainee.getId())
                .enqueue(new Callback<Trainee>() {
                    @Override
                    public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                        if (response.body() != null) {
                            Toast.makeText(ApiCallMainActivity.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                            GetData();
                        }
                    }

                    @Override
                    public void onFailure(Call<Trainee> call, Throwable t) {
                        Toast.makeText(ApiCallMainActivity.this, "Delete Fail", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void Update() {
        Intent intent = new Intent(ApiCallMainActivity.this, NhanVienInfoActivity.class);
        intent.putExtra("Trainee", selectedTrainee);
        startActivity(intent);
        finish();
        ;
    }

    private void GetData() {
        traineeService.GetAllTrainees()
                .enqueue(new Callback<Trainee[]>() {
                    @Override
                    public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                        Trainee[] trainees = response.body();
                        if (trainees == null) {
                            Toast.makeText(ApiCallMainActivity.this, "Cannot get trainee list", Toast.LENGTH_LONG).show();
                            return;
                        }

                        arrayTrainee.clear();

                        for (Trainee trainee : trainees) {
                            arrayTrainee.add(trainee);
                        }

                        adapter = new TraineeAdapter(ApiCallMainActivity.this, R.layout.activity_component_nhan_view, arrayTrainee);
                        lv_Trainee.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Trainee[]> call, Throwable t) {
                        Toast.makeText(ApiCallMainActivity.this, "Get Fail", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
