package com.example.qlsv_se171581_net1717;

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

import com.example.qlsv_se171581_net1717.API.Repos.StudentRepo;
import com.example.qlsv_se171581_net1717.API.Services.StudentService;
import com.example.qlsv_se171581_net1717.Adapters.StudentAdapter;
import com.example.qlsv_se171581_net1717.Models.Student;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity extends AppCompatActivity {
    StudentService studentService;
    private ListView lv_StuListView;
    private List<Student> studentList;
    private StudentAdapter adapter;

    private Button btn_Delete, btn_Update, btn_Add;
    private TextView tv_ItemChosenName, tv_ItemChosenID;
    private Student selectedStu;
    ImageView imv_Back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student);

        tv_ItemChosenID = findViewById(R.id.textViewChosenStudentID);
        tv_ItemChosenName = findViewById(R.id.textViewChosenStudentName);
        imv_Back = findViewById(R.id.imageViewStudentBack);

        btn_Delete = findViewById(R.id.buttonDeleteStudent);
        btn_Update = findViewById(R.id.buttonUpdateStudent);
        btn_Add = findViewById(R.id.buttonAddStudent);

        lv_StuListView = findViewById(R.id.listViewStudent);

        studentList = new ArrayList<>();

        studentService = StudentRepo.getStudentService();
        GetData();

        //Btn, ListView function
        lv_StuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedStu = studentList.get(position);
                tv_ItemChosenID.setText(String.valueOf(selectedStu.getID()));
                tv_ItemChosenName.setText(selectedStu.getName());
            }
        });

        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (studentList != null) {
                    Delete();
                } else {
                    Toast.makeText(StudentActivity.this, "Please Select Student", Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedStu != null) {
                    Update();
                } else {
                    Toast.makeText(StudentActivity.this, "Please Select Student", Toast.LENGTH_LONG).show();
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
                startActivity(new Intent(StudentActivity.this, MainActivity.class));
            }
        });
    }

    private void Create() {
        Intent intent = new Intent(StudentActivity.this, StudentInfoFunctionActivity.class);
        startActivity(intent);
    }

    private void Delete() {
        studentService.DeleteStudent(selectedStu.getID())
                .enqueue(new Callback<Student>() {
                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                        if (response.body() != null) {
                            Toast.makeText(StudentActivity.this, "Delete Successfully", Toast.LENGTH_LONG).show();
                            GetData();

                            tv_ItemChosenID.setText("");
                            tv_ItemChosenName.setText("");
                            selectedStu = null;
                        }
                    }

                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {
                        Toast.makeText(StudentActivity.this, "Delete Fail", Toast.LENGTH_LONG).show();
                    }
                });


    }

    private void Update() {
        Intent intent = new Intent(StudentActivity.this, StudentInfoFunctionActivity.class);
        intent.putExtra("Student", selectedStu);
        startActivity(intent);
    }

    private void GetData() {
        studentService.GetAllStudent()
                .enqueue(new Callback<Student[]>() {
                    @Override
                    public void onResponse(Call<Student[]> call, Response<Student[]> response) {
                        Student[] students = response.body();
                        if (students == null) {
                            Toast.makeText(StudentActivity.this, "Cannot get student list", Toast.LENGTH_LONG).show();
                            return;
                        }

                        studentList.clear();

                        for (Student student : students) {
                            studentList.add(student);
                        }

                        adapter = new StudentAdapter(StudentActivity.this, R.layout.component_student_row, studentList);
                        lv_StuListView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<Student[]> call, Throwable t) {
                        Toast.makeText(StudentActivity.this, "Get Fail", Toast.LENGTH_LONG).show();
                    }
                });
    }
}
