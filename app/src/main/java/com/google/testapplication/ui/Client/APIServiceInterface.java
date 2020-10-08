package com.google.testapplication.ui.Client;


import com.google.testapplication.ui.Models.Repositorio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServiceInterface {

    @GET("search/code?q=user%3Agoogle+kotlin/kotlin&per_page=10")
    Call<Repositorio> getRepositorio();
}
