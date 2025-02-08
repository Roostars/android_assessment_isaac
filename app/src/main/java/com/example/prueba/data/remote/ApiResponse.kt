package com.example.prueba.data.remote

import com.example.prueba.data.model.Facts
import com.example.prueba.data.model.Pagination
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("pagination") val pagination: Pagination,
    @SerializedName("results") val results: List<Facts>
)