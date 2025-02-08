package com.example.prueba.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba.data.local.FactsEntity
import com.example.prueba.data.model.Facts
import com.example.prueba.data.model.UiState
import com.example.prueba.repository.FactsRepository
import kotlinx.coroutines.launch


class FactsViewModel : ViewModel() {
    private val repository = FactsRepository()
    private val _facts = MutableLiveData<UiState<List<Facts>>>()
    val facts: LiveData<UiState<List<Facts>>> get() = _facts

    private val _saveRoom = MutableLiveData<UiState<Unit>>()
    val saveRoom: LiveData<UiState<Unit>> get() = _saveRoom

    fun fetchFacts() {
        _facts.value = UiState.Loading
        viewModelScope.launch {
            val response = repository.getFacts()
            _facts.value = response
        }
    }

    fun saveFacts(facts: List<Facts>) {
        val factsEntities: List<FactsEntity> = facts.map { fact ->
            FactsEntity(
                id = null,
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

        viewModelScope.launch {
            val factsId = repository.insertFacts(factsEntities)
            if (factsId.any{it<0}){
                _saveRoom.value = UiState.Error("Something went wrong")
            }else {
                _saveRoom.value = UiState.Success(Unit)
            }
        }
    }
}