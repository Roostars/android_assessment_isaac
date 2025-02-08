package com.example.prueba.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Facts(
    @SerializedName("_id") val id: String,
    @SerializedName("date_insert") val dateInsert: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("columns") val columns: String,
    @SerializedName("fact") val fact: String,
    @SerializedName("organization") val organization: String,
    @SerializedName("resource") val resource: String,
    @SerializedName("url") val url: String,
    @SerializedName("operations") val operations: String,
    @SerializedName("dataset") val dataset: String,
    @SerializedName("created_at") val createdAt: Long
): Parcelable
