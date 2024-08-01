package com.example.petrial.API.Services;

import com.example.petrial.Models.Sach;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SachService {
    String SACH = "Sach"; //Ten table duoc tao trong API

    @GET(SACH)
    Call<Sach[]> GetAllSach();

    @GET(SACH + "/{id}")
    Call<Sach> GetSach(@Path("id") Object id);

    @POST(SACH)
    Call<Sach> CreateSach(@Body Sach sach);

    @PATCH(SACH + "/{id}")
    Call<Sach> UpdateSach(@Path("id") Object id, @Body Sach sach);

    @DELETE(SACH + "/{id}")
    Call<Sach> DeleteSach(@Path("id") Object id);
}
