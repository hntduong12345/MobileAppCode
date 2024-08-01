package com.example.qlsv_se171581_net1717.API.Services;

import com.example.qlsv_se171581_net1717.Models.Major;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MajorService {
    String MAJOR = "Major"; //Ten table duoc tao trong API

    @GET(MAJOR)
    Call<Major[]> GetAllMajor();

    @GET(MAJOR + "/{id}")
    Call<Major> GetMajor(@Path("id") Object id);

    @POST(MAJOR)
    Call<Major> CreateMajor(@Body Major major);

    @PATCH(MAJOR + "/{id}")
    Call<Major> UpdateMajor(@Path("id") Object id, @Body Major major);

    @DELETE(MAJOR + "/{id}")
    Call<Major> DeleteMajor(@Path("id") Object id);
}
