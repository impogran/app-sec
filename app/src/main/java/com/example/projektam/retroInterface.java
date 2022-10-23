package com.example.projektam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface retroInterface {
    @POST("/login")
    Call<JWT> login(@Body LoginPassword loginPass);

    @GET("/api/users/{login}")
    Call<BasicResponse> getUser(@Path("login") String login, @Header("Authorization") String token);

    @GET("/api/users")
    Call<List<User>> getAllUsers(@Header("Authorization") String token);

    @GET("/api/hidden")
    Call<BasicResponse> getHidden(@Body String creepy);

    @GET("/api/allEndpoints")
    Call<List<BasicResponse>> getAllEndpoints(@Header("Authorization") String endpoints);
}
