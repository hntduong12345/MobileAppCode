package com.example.labsum8910.ApiCall;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.labsum8910.ApiCall.API.TraineeRepository;
import com.example.labsum8910.ApiCall.API.TraineeService;
import com.example.labsum8910.ApiCall.Models.Trainee;
import com.example.labsum8910.ApiCallMainActivity;
import com.example.labsum8910.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NhanVienInfoActivity extends AppCompatActivity implements View.OnClickListener{
    TraineeService traineeService;
    EditText edt_Name, edt_Email, edt_Phone, edt_Gender;
    Button btn_Save;
    boolean isUpdate;
    Trainee trainee;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nhan_vien_info);

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
        boolean isValid = true;

        String name = edt_Name.getText().toString();
        String email = edt_Email.getText().toString();
        String phone = edt_Phone.getText().toString();
        String gender = edt_Gender.getText().toString();

        if(TextUtils.isEmpty(email)){
            edt_Email.setError("Required");
            isValid = false;
        }

        if(TextUtils.isEmpty(name)){
            edt_Name.setError("Required");
            isValid = false;
        }

        if(TextUtils.isEmpty(phone)){
            edt_Phone.setError("Required");
            isValid = false;
        }

        if(TextUtils.isEmpty(gender)){
            edt_Gender.setError("Required");
            isValid = false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_Email.setError("Invalid email");
            Toast.makeText(NhanVienInfoActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if(!isValid){
            return;
        }
        else{
            isValid = true;
        }

        Trainee trainee = new Trainee(name, email, phone, gender);
        try{
            traineeService.CreateTrainee(trainee)
                    .enqueue(new Callback<Trainee>() {
                        @Override
                        public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                            if(response.body() != null){
                                Toast.makeText(NhanVienInfoActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                                GoBackMainPage();
                            }
                        }

                        @Override
                        public void onFailure(Call<Trainee> call, Throwable t) {
                            Toast.makeText(NhanVienInfoActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    private void Update(){
        boolean isValid = true;

        String name = edt_Name.getText().toString();
        String email = edt_Email.getText().toString();
        String phone = edt_Phone.getText().toString();
        String gender = edt_Gender.getText().toString();

        if(TextUtils.isEmpty(email)){
            edt_Email.setError("Required");
            isValid = false;
        }

        if(TextUtils.isEmpty(name)){
            edt_Name.setError("Required");
            isValid = false;
        }

        if(TextUtils.isEmpty(phone)){
            edt_Phone.setError("Required");
            isValid = false;
        }

        if(TextUtils.isEmpty(gender)){
            edt_Gender.setError("Required");
            isValid = false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_Email.setError("Invalid email");
            Toast.makeText(NhanVienInfoActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if(!isValid){
            return;
        }
        else{
            isValid = true;
        }


        Trainee newTraineeInfo = new Trainee(name, email, phone, gender);

        try{
            traineeService.UpdateTrainee(trainee.getId(), newTraineeInfo)
                    .enqueue(new Callback<Trainee>() {
                        @Override
                        public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                            if(response.body() != null){
                                Toast.makeText(NhanVienInfoActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                                GoBackMainPage();
                            }
                        }

                        @Override
                        public void onFailure(Call<Trainee> call, Throwable t) {
                            Toast.makeText(NhanVienInfoActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    private void GoBackMainPage(){
        Intent intent = new Intent(NhanVienInfoActivity.this, ApiCallMainActivity.class);
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
