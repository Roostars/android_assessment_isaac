package com.example.prueba.data.local

import androidx.room.Database

import androidx.room.RoomDatabase

@Database(entities = [FactsEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun factDao(): FactDao
}