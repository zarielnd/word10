package com.vocab10.android.services;

import com.vocab10.android.network.LoginRequest;
import com.vocab10.android.network.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IAuthService {
    @POST("api/auth/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);
}
