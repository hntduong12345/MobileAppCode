package com.example.petrial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petrial.API.Repos.TacgiaRepo;
import com.example.petrial.API.Services.TacgiaService;
import com.example.petrial.Models.Tacgia;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TacgiaInfoActivity extends AppCompatActivity implements View.OnClickListener{
    TacgiaService tacgiaService;
    EditText edt_TenTG, edt_SDT, edt_DiaChi;
    Button btn_Save;
    boolean isUpdate;
    Tacgia tacgia;
    ImageView imv_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tacgia_info);

        imv_Back = findViewById(R.id.imageViewTGIBack);

        edt_TenTG = findViewById(R.id.editTextTenTacGia);
        edt_SDT = findViewById(R.id.editTextTacGiaSDT);
        edt_DiaChi = findViewById(R.id.editTextDiaChi);

        btn_Save = findViewById(R.id.buttonLuuTacGia);

        isUpdate = CheckTransferData();

        if(isUpdate){
            btn_Save.setText("Cập nhật");
        }
        else{
            btn_Save.setText("Lưu Data");
        }

        btn_Save.setOnClickListener(this);

        tacgiaService = TacgiaRepo.getTacgiaService();
    }

    @Override
    protected void onStart() {
        super.onStart();
        imv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TacgiaInfoActivity.this, TacgiaActivity.class));
            }
        });
    }

    private boolean CheckTransferData(){
        Intent intent = this.getIntent();
        tacgia = (Tacgia) intent.getSerializableExtra("TacGia");
        if(tacgia == null) return false;

        edt_TenTG.setText(tacgia.getTenTacgia());
        edt_DiaChi.setText(tacgia.getDiachi());
        edt_SDT.setText(tacgia.getDienthoai());
        return true;
    }

    private void Save(){
        String name = edt_TenTG.getText().toString();
        String address = edt_SDT.getText().toString();
        String phone = edt_DiaChi.getText().toString();

        Tacgia newTacGia = new Tacgia(name, address, phone);
        try{
            tacgiaService.CreateTacgia(newTacGia)
                    .enqueue(new Callback<Tacgia>() {
                        @Override
                        public void onResponse(Call<Tacgia> call, Response<Tacgia> response) {
                            if(response.body() != null){
                                Toast.makeText(TacgiaInfoActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                                GoBackMainPage();
                            }
                        }

                        @Override
                        public void onFailure(Call<Tacgia> call, Throwable t) {
                            Toast.makeText(TacgiaInfoActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    private void Update(){
        try{
            String name = edt_TenTG.getText().toString();
            String address = edt_SDT.getText().toString();
            String phone = edt_DiaChi.getText().toString();

            Tacgia newTacGiaInfo = new Tacgia(name, address, phone);

            tacgiaService.UpdateTacgia(tacgia.getIDTacgia(), newTacGiaInfo)
                    .enqueue(new Callback<Tacgia>() {
                        @Override
                        public void onResponse(Call<Tacgia> call, Response<Tacgia> response) {
                            if(response.body() != null){
                                Toast.makeText(TacgiaInfoActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                                GoBackMainPage();
                            }
                        }

                        @Override
                        public void onFailure(Call<Tacgia> call, Throwable t) {
                            Toast.makeText(TacgiaInfoActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    private void GoBackMainPage(){
        Intent intent = new Intent(TacgiaInfoActivity.this, TacgiaActivity.class);
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
