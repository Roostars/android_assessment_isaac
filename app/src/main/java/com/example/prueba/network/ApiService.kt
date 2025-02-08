package com.example.prueba.network
import com.example.prueba.data.remote.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v1/gobmx.facts")
    suspend fun getFacts(): Response<ApiResponse>
}