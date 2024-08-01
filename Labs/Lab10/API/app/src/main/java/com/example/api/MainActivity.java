package com.example.api;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.api.API.TraineeRepository;
import com.example.api.API.TraineeService;
import com.example.api.Models.Trainee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TraineeService traineeService;
    EditText edt_Name, edt_Email, edt_Phone, edt_Gender;
    Button btn_Save;
    boolean isUpdate;
    Trainee trainee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt_Email = findViewById(R.id.editTextEmail);
        edt_Name = findViewById(R.id.editTextName);
        edt_Phone = findViewById(R.id.editTextPhone);
        edt_Gender = findViewById(R.id.editTextGender);

        btn_Save = findViewById(R.id.buttonSave);

        isUpdate = CheckTransferData();

        if(isUpdate){
            btn_Save.setText("Cập nhật");
        }
        else{
            btn_Save.setText("Lưu Data");
        }

        btn_Save.setOnClickListener(this);

        traineeService = TraineeRepository.getTraineeService();
    }

    private boolean CheckTransferData(){
        Intent intent = this.getIntent();
        trainee = (Trainee) intent.getSerializableExtra("Trainee");
        if(trainee == null) return false;

        edt_Name.setText(trainee.getName());
        edt_Email.setText(trainee.getEmail());
        edt_Phone.setText(trainee.getPhone());
        edt_Gender.setText(trainee.getGender());
        return true;
    }

    private void Save(){
        String name = edt_Name.getText().toString();
        String email = edt_Email.getText().toString();
        String phone = edt_Phone.getText().toString();
        String gender = edt_Gender.getText().toString();

        Trainee trainee = new Trainee(name, email, phone, gender);
        try{
            traineeService.CreateTrainee(trainee)
            .enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if(response.body() != null){
                        Toast.makeText(MainActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                        GoBackMainPage();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                }
            });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    private void Update(){
        try{
            String name = edt_Name.getText().toString();
            String email = edt_Email.getText().toString();
            String phone = edt_Phone.getText().toString();
            String gender = edt_Gender.getText().toString();

            Trainee newTraineeInfo = new Trainee(name, email, phone, gender);

            traineeService.UpdateTrainee(trainee.getId(), newTraineeInfo)
                    .enqueue(new Callback<Trainee>() {
                        @Override
                        public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                            if(response.body() != null){
                                Toast.makeText(MainActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                                GoBackMainPage();
                            }
                        }

                        @Override
                        public void onFailure(Call<Trainee> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    private void GoBackMainPage(){
        Intent intent = new Intent(MainActivity.this, ViewActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if(isUpdate){
            Update();
        }
        else{
            Save();
        }
    }
}