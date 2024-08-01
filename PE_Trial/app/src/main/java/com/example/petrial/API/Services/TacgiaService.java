package com.example.petrial.API.Services;

import com.example.petrial.Models.Tacgia;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TacgiaService {
    String TACGIA = "Tacgia"; //Ten table duoc tao trong API

    @GET(TACGIA)
    Call<Tacgia[]> GetAllTacgia();

    @GET(TACGIA + "/{id}")
    Call<Tacgia> GetTacgia(@Path("id") Object id);

    @POST(TACGIA)
    Call<Tacgia> CreateTacgia(@Body Tacgia tacgia);

    @PATCH(TACGIA + "/{id}")
    Call<Tacgia> UpdateTacgia(@Path("id") Object id, @Body Tacgia tacgia);

    @DELETE(TACGIA + "/{id}")
    Call<Tacgia> DeleteTacgia(@Path("id") Object id);
}
