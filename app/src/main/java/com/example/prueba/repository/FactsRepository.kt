package com.example.prueba.repository

import androidx.lifecycle.LiveData
import com.example.prueba.network.RetrofitClient
import com.example.prueba.data.local.FactsEntity
import com.example.prueba.data.remote.ApiResponse
import com.example.prueba.App
import com.example.prueba.data.model.Facts
import com.example.prueba.data.model.UiState

class FactsRepository {

    private val apiService = RetrofitClient.instance
    private val factDao = App.db.factDao()

    suspend fun getFacts(): UiState<List<Facts>> {
        val response = apiService.getFacts()
        return if (response.isSuccessful) {
            UiState.Success(response.body()?.results ?: emptyList())
        } else {
            UiState.Error(response.message())
        }
    }

    suspend fun insertFacts(factsEntity: List<FactsEntity>) : List<Long> {
       return factDao.insertFacts(factsEntity)
    }

    suspend fun getFactsFromDatabase(limit: Int, offset: Int): List<Facts>{
        return factDao.getAllFacts(limit, offset).map { fact ->
            Facts(
                id = fact.id.toString(),
                dateInsert = fact.dateInsert,
                slug = fact.slug,
                columns = fact.columns,
                fact = fact.fact,
                organization = fact.organization,
                resource = fact.resource,
                url = fact.url,
                operations = fact.operations,
                dataset = fact.dataset,
                createdAt = fact.createdAt
            )
        }
    }

    suspend fun getFilterFactsFromDatabase(query: String, limit: Int, offset: Int): List<Facts>{
        return factDao.getFilterAllFacts(query,limit, offset).map { fact ->
            Facts(
                id = fact.id.toString(),
                dateInsert = fact.dateInsert,
                slug = fact.slug,
                columns = fact.columns,
                fact = fact.fact,
                organization = fact.organization,
                resource = fact.resource,
                url = fact.url,
                operations = fact.operations,
                dataset = fact.dataset,
                createdAt = fact.createdAt
            )
        }
    }

}