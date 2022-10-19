package com.example.projektam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface retroInterface {
    @POST("/login")
    Call<JWT> login(@Body LoginPassword loginPass);
}
