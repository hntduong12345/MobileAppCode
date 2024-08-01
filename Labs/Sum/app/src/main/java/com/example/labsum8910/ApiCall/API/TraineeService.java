package com.example.labsum8910.ApiCall.API;

import com.example.labsum8910.ApiCall.Models.Trainee;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TraineeService {
    String TRAINEES = "NhanVien"; //Ten table duoc tao trong API

    @GET(TRAINEES)
    Call<Trainee[]> GetAllTrainees();

    @GET(TRAINEES + "/{id}")
    Call<Trainee> GetTrainee(@Path("id") Object id);

    @POST(TRAINEES)
    Call<Trainee> CreateTrainee(@Body Trainee trainee);

    @PATCH(TRAINEES + "/{id}")
    Call<Trainee> UpdateTrainee(@Path("id") Object id, @Body Trainee trainee);

    @DELETE(TRAINEES + "/{id}")
    Call<Trainee> DeleteTrainee(@Path("id") Object id);
}
