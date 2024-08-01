package com.example.qlsv_se171581_net1717;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsv_se171581_net1717.API.Repos.MajorRepo;
import com.example.qlsv_se171581_net1717.API.Services.MajorService;
import com.example.qlsv_se171581_net1717.Models.Major;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MajorInfoFunctionActivity extends AppCompatActivity implements View.OnClickListener {
    MajorService majorService;
    EditText edt_MajorName;
    Button btn_Save;
    boolean isUpdate;
    Major major;
    ImageView imv_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_major_info_function);

        imv_Back = findViewById(R.id.imageViewMajorInfoBack);
        edt_MajorName = findViewById(R.id.editTextMajorName);

        btn_Save = findViewById(R.id.buttonSaveMajor);

        isUpdate = CheckTransferData();

        if (isUpdate) {
            btn_Save.setText("Update Data");
        } else {
            btn_Save.setText("Save Data");
        }

        btn_Save.setOnClickListener(this);

        majorService = MajorRepo.getMajorService();
    }

    @Override
    protected void onStart() {
        super.onStart();
        imv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBackMainPage();
            }
        });
    }

    private boolean CheckTransferData() {
        Intent intent = this.getIntent();
        major = (Major) intent.getSerializableExtra("Major");
        if (major == null) return false;

        edt_MajorName.setText(major.getNameMajor());
        return true;
    }

    private void Save() {
        String name = edt_MajorName.getText().toString();

        boolean isValid = true;
        if(TextUtils.isEmpty(name)){
            edt_MajorName.setError("Required");
            isValid = false;
        }

        if(!isValid) return;


        Major newMajor = new Major(name);
        try {
            majorService.CreateMajor(newMajor)
                    .enqueue(new Callback<Major>() {
                        @Override
                        public void onResponse(Call<Major> call, Response<Major> response) {
                            if (response.body() != null) {
                                Toast.makeText(MajorInfoFunctionActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                                GoBackMainPage();
                            }
                        }

                        @Override
                        public void onFailure(Call<Major> call, Throwable t) {
                            Toast.makeText(MajorInfoFunctionActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    });
        } catch (Exception e) {
            Log.d("Loi", e.getMessage());
        }
    }

    private void Update() {
        try {
            String name = edt_MajorName.getText().toString();

            boolean isValid = true;
            if(TextUtils.isEmpty(name)){
                edt_MajorName.setError("Required");
                isValid = false;
            }

            if(!isValid) return;

            Major newMajorInfo = new Major(name);

            majorService.UpdateMajor(major.getIDMajor(), newMajorInfo)
                    .enqueue(new Callback<Major>() {
                        @Override
                        public void onResponse(Call<Major> call, Response<Major> response) {
                            if (response.body() != null) {
                                Toast.makeText(MajorInfoFunctionActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                                GoBackMainPage();
                            }
                        }

                        @Override
                        public void onFailure(Call<Major> call, Throwable t) {
                            Toast.makeText(MajorInfoFunctionActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    });
        } catch (Exception e) {
            Log.d("Loi", e.getMessage());
        }
    }

    private void GoBackMainPage() {
        Intent intent = new Intent(MajorInfoFunctionActivity.this, MajorActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (isUpdate) {
            Update();
        } else {
            Save();
        }
    }
}
