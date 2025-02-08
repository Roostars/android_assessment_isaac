package com.example.prueba.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facts")
data class FactsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo ("dateInsert") val dateInsert: String,
    @ColumnInfo ("slug")val slug: String,
    @ColumnInfo ("columns") val columns: String,
    @ColumnInfo ("fact")val fact: String,
    @ColumnInfo ("organization")val organization: String,
    @ColumnInfo ("resource") val resource: String,
    @ColumnInfo ("url") val url: String,
    @ColumnInfo ("operations")val operations: String,
    @ColumnInfo ("dataset")val dataset: String,
    @ColumnInfo ("createdAt")val createdAt: Long
)