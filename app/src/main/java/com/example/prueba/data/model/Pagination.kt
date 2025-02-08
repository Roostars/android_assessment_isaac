package com.example.prueba.data.model

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("page") val page: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("total") val total: Int
)
