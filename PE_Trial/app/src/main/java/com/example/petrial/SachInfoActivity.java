package com.example.petrial;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.petrial.API.Repos.SachRepo;
import com.example.petrial.API.Repos.TacgiaRepo;
import com.example.petrial.API.Services.SachService;
import com.example.petrial.API.Services.TacgiaService;
import com.example.petrial.Models.Sach;
import com.example.petrial.Models.Tacgia;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SachInfoActivity extends AppCompatActivity implements View.OnClickListener{
    TacgiaService tacgiaService;
    SachService sachService;
    EditText edt_TenSach, edt_NgayXB, edt_TheLoai;
    Button btn_Save;
    boolean isUpdate;
    Sach sach;
    ImageView imv_DatePicker;

    int currentDay, currentMonth, currentYear = 2024;

    ArrayList<String> listIDTacGia = new ArrayList<>();
    ImageView imv_Back;

    AutoCompleteTextView autoCompleteTextView;

    String idTG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sach_info);

        imv_DatePicker = findViewById(R.id.imageViewDtaePicker);
        imv_Back = findViewById(R.id.imageViewSIBack);

        autoCompleteTextView = findViewById(R.id.autoCompleteTextICTG);

        edt_TenSach = findViewById(R.id.editTextTenSach);
        edt_NgayXB = findViewById(R.id.editTextNgayXB);
        edt_TheLoai = findViewById(R.id.editTextTheLoai);

        btn_Save = findViewById(R.id.buttonLuuSach);

        isUpdate = CheckTransferData();

        sachService = SachRepo.getSachService();
        tacgiaService = TacgiaRepo.getTacgiaService();

        GetTacGiaIDList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listIDTacGia);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idTG = parent.getItemAtPosition(position).toString();
            }
        });

        if(isUpdate){
            btn_Save.setText("Cập nhật");
        }
        else{
            btn_Save.setText("Lưu Data");
        }

        btn_Save.setOnClickListener(this);


        imv_DatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDatePicker();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        imv_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SachInfoActivity.this, SachActivity.class));
            }
        });
    }

    private void ShowDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                currentDay = dayOfMonth;
                currentMonth = month;
                currentYear = year;
                edt_NgayXB.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(SachInfoActivity.this, dateSetListener, currentYear, currentMonth, currentDay);
        datePickerDialog.show();
    }

    private boolean CheckTransferData(){
        Intent intent = this.getIntent();
        sach = (Sach) intent.getSerializableExtra("Sach");
        if(sach == null) return false;
        edt_TenSach.setText(sach.getTensach());
        edt_NgayXB.setText(sach.getTensach());
        edt_TheLoai.setText(sach.getTensach());
        autoCompleteTextView.setText(sach.getIdTacgia());

        ExtractDateFromData(edt_NgayXB.getText().toString());

        return true;
    }

    private void ExtractDateFromData(String date){
        String[] dateComponent = date.split("/");
        currentDay = Integer.parseInt(dateComponent[0].trim());
        currentMonth = Integer.parseInt(dateComponent[1].trim());
        currentYear = Integer.parseInt(dateComponent[2].trim());
    }

    private void Save(){
        String name = edt_TenSach.getText().toString();
        String NgayXB = edt_NgayXB.getText().toString();
        String TheLoai = edt_TheLoai.getText().toString();

        Sach  newSach = new Sach(name, NgayXB, TheLoai, idTG);
        try{
            sachService.CreateSach(newSach)
                    .enqueue(new Callback<Sach>() {
                        @Override
                        public void onResponse(Call<Sach> call, Response<Sach> response) {
                            if(response.body() != null){
                                Toast.makeText(SachInfoActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                                GoBackMainPage();
                            }
                        }

                        @Override
                        public void onFailure(Call<Sach> call, Throwable t) {
                            Toast.makeText(SachInfoActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    private void Update(){
        try{
            String name = edt_TenSach.getText().toString();
            String NgayXB = edt_NgayXB.getText().toString();
            String TheLoai = edt_TheLoai.getText().toString();

            Sach  newSachInfo = new Sach(name, NgayXB, TheLoai, idTG);

            sachService.UpdateSach(sach.getMasach(), newSachInfo)
                    .enqueue(new Callback<Sach>() {
                        @Override
                        public void onResponse(Call<Sach> call, Response<Sach> response) {
                            if(response.body() != null){
                                Toast.makeText(SachInfoActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                                GoBackMainPage();
                            }
                        }

                        @Override
                        public void onFailure(Call<Sach> call, Throwable t) {
                            Toast.makeText(SachInfoActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    private void GoBackMainPage(){
        Intent intent = new Intent(SachInfoActivity.this, SachActivity.class);
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

    private void GetTacGiaIDList(){
        tacgiaService.GetAllTacgia()
                .enqueue(new Callback<Tacgia[]>() {
                    @Override
                    public void onResponse(Call<Tacgia[]> call, Response<Tacgia[]> response) {
                        Tacgia[] tacgias = response.body();
                        if (tacgias == null) {
                            Toast.makeText(SachInfoActivity.this, "Cannot get tac gia list", Toast.LENGTH_LONG).show();
                            return;
                        }

                        listIDTacGia.clear();

                        for (Tacgia tacgia : tacgias) {
                            listIDTacGia.add(String.valueOf(tacgia.IDTacgia));
                        }
                    }

                    @Override
                    public void onFailure(Call<Tacgia[]> call, Throwable t) {
                        Toast.makeText(SachInfoActivity.this, "Get Fail", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
