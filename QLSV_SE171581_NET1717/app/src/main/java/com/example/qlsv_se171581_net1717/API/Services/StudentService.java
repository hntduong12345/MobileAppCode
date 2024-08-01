package com.example.qlsv_se171581_net1717.API.Services;

import com.example.qlsv_se171581_net1717.Models.Major;
import com.example.qlsv_se171581_net1717.Models.Student;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface StudentService {
    String STUDENT = "Student"; //Ten table duoc tao trong API

    @GET(STUDENT)
    Call<Student[]> GetAllStudent();

    @GET(STUDENT + "/{id}")
    Call<Student> GetStudent(@Path("id") Object id);

    @POST(STUDENT)
    Call<Student> CreateStudent(@Body Student student);

    @PATCH(STUDENT + "/{id}")
    Call<Student> UpdateStudent(@Path("id") Object id, @Body Student student);

    @DELETE(STUDENT + "/{id}")
    Call<Student> DeleteStudent(@Path("id") Object id);
}
