package com.example.prueba.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface FactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFacts(facts: List<FactsEntity>): List<Long>

    @Query("SELECT * FROM facts ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getAllFacts(limit: Int, offset: Int): List<FactsEntity>

    @Query("SELECT * FROM facts WHERE organization LIKE '%' || :query || '%' ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getFilterAllFacts(query: String, limit: Int, offset: Int): List<FactsEntity>

}
