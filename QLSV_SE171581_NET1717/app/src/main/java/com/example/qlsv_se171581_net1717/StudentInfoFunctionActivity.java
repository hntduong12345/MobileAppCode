package com.example.qlsv_se171581_net1717;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Surface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qlsv_se171581_net1717.API.Repos.MajorRepo;
import com.example.qlsv_se171581_net1717.API.Repos.StudentRepo;
import com.example.qlsv_se171581_net1717.API.Services.MajorService;
import com.example.qlsv_se171581_net1717.API.Services.StudentService;
import com.example.qlsv_se171581_net1717.Models.Major;
import com.example.qlsv_se171581_net1717.Models.Student;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentInfoFunctionActivity extends AppCompatActivity implements View.OnClickListener{
    StudentService studentService;
    MajorService majorService;

    EditText edt_Name, edt_Date, edt_Gender, edt_Email, edt_Address, edt_IDM;
    Button btn_Save;

    boolean isUpdate;
    Student student;
    ImageView imv_DatePicker;
    int currentDay, currentMonth, currentYear = 2024;
    ArrayList<String> listIDMajor = new ArrayList<>();
    ImageView imv_Back;
    AutoCompleteTextView autoCompleteTextView;
    String idMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student_info_function);

        imv_DatePicker = findViewById(R.id.imageViewDtaePicker);
        imv_Back = findViewById(R.id.imageViewStudentIBack);

        autoCompleteTextView = findViewById(R.id.autoCompleteTextIDM);

        edt_Name = findViewById(R.id.editTextStudentName);
        edt_Date = findViewById(R.id.editTextStudentDate);
        edt_Gender = findViewById(R.id.editTextGender);
        edt_Email = findViewById(R.id.editTextEmail);
        edt_Address = findViewById(R.id.editTextAddress);

        btn_Save = findViewById(R.id.buttonStudentSave);

        isUpdate = CheckTransferData();

        studentService = StudentRepo.getStudentService();
        majorService = MajorRepo.getMajorService();

        GetMajorIDList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listIDMajor);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idMajor = parent.getItemAtPosition(position).toString();
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
                startActivity(new Intent(StudentInfoFunctionActivity.this, StudentActivity.class));
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
                edt_Date.setText(dayOfMonth+"/"+month+"/"+year);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(StudentInfoFunctionActivity.this, dateSetListener, currentYear, currentMonth, currentDay);
        datePickerDialog.show();
    }

    private boolean CheckTransferData(){
        Intent intent = this.getIntent();
        student = (Student) intent.getSerializableExtra("Student");
        if(student == null) return false;
        edt_Name.setText(student.getName());
        edt_Gender.setText(student.getGender());
        edt_Email.setText(student.getEmail());
        edt_Address.setText(student.getAddress());
        edt_Date.setText(student.getDate());

        autoCompleteTextView.setText(student.getIdMajor());

        ExtractDateFromData(edt_Date.getText().toString());

        return true;
    }

    private void ExtractDateFromData(String date){
        String[] dateComponent = date.split("/");
        currentDay = Integer.parseInt(dateComponent[0].trim());
        currentMonth = Integer.parseInt(dateComponent[1].trim());
        currentYear = Integer.parseInt(dateComponent[2].trim());
    }

    private void Save(){
        String name = edt_Name.getText().toString();
        String gender = edt_Gender.getText().toString();
        String email = edt_Email.getText().toString();
        String date = edt_Date.getText().toString();
        String address = edt_Address.getText().toString();

        boolean isValid = true;
        if(TextUtils.isEmpty(name)){
            edt_Name.setError("Required");
            isValid = false;
        }

        if(TextUtils.isEmpty(gender)){
            edt_Gender.setError("Required");
            isValid = false;
        }

        if(TextUtils.isEmpty(email)){
            edt_Email.setError("Required");
            isValid = false;
        }

        if(TextUtils.isEmpty(date)){
            edt_Date.setError("Required");
            isValid = false;
        }

        if(TextUtils.isEmpty(address)){
            edt_Address.setError("Required");
            isValid = false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edt_Email.setError("Invalid email");
            Toast.makeText(StudentInfoFunctionActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if(!isValid) return;


        Student newStudent = new Student(name,date,gender,email,address,idMajor);
        try{
            studentService.CreateStudent(newStudent)
                    .enqueue(new Callback<Student>() {
                        @Override
                        public void onResponse(Call<Student> call, Response<Student> response) {
                            if(response.body() != null){
                                Toast.makeText(StudentInfoFunctionActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                                GoBackMainPage();
                            }
                        }

                        @Override
                        public void onFailure(Call<Student> call, Throwable t) {
                            Toast.makeText(StudentInfoFunctionActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    private void Update(){
        try{
            String name = edt_Name.getText().toString();
            String gender = edt_Gender.getText().toString();
            String email = edt_Email.getText().toString();
            String date = edt_Date.getText().toString();
            String address = edt_Address.getText().toString();


            Student newStudent = new Student(name,date,gender,email,address,idMajor);

            studentService.UpdateStudent(student.getID(), newStudent)
                    .enqueue(new Callback<Student>() {
                        @Override
                        public void onResponse(Call<Student> call, Response<Student> response) {
                            if(response.body() != null){
                                Toast.makeText(StudentInfoFunctionActivity.this, "Save Successfully", Toast.LENGTH_LONG).show();
                                GoBackMainPage();
                            }
                        }

                        @Override
                        public void onFailure(Call<Student> call, Throwable t) {
                            Toast.makeText(StudentInfoFunctionActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                        }
                    });
        }catch (Exception e){
            Log.d("Loi", e.getMessage());
        }
    }

    private void GoBackMainPage(){
        Intent intent = new Intent(StudentInfoFunctionActivity.this, StudentActivity.class);
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

    private void GetMajorIDList(){
        majorService.GetAllMajor()
                .enqueue(new Callback<Major[]>() {
                    @Override
                    public void onResponse(Call<Major[]> call, Response<Major[]> response) {
                        Major[] majors = response.body();
                        if (majors == null) {
                            Toast.makeText(StudentInfoFunctionActivity.this, "Cannot get major list", Toast.LENGTH_LONG).show();
                            return;
                        }

                        listIDMajor.clear();

                        for (Major major : majors) {
                            listIDMajor.add(String.valueOf(major.getIDMajor()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Major[]> call, Throwable t) {
                        Toast.makeText(StudentInfoFunctionActivity.this, "Get Fail", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
